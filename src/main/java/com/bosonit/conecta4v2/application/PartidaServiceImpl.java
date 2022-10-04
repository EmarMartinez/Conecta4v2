package com.bosonit.conecta4v2.application;

import com.bosonit.conecta4v2.controller.dto.PartidaDto;
import com.bosonit.conecta4v2.controller.dto.PartidaNoIdDto;
import com.bosonit.conecta4v2.domain.Partida;
import com.bosonit.conecta4v2.infraestructure.PartidaRepository;
import com.bosonit.conecta4v2.util.PartidaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class PartidaServiceImpl implements PartidaService{

    @Autowired
    PartidaRepository partidaRepository;

    @Autowired
    PartidaUtil partidaUtil;

    @Override
    public Mono<PartidaDto> getGame(Integer id) {
        return partidaRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("No se encontró la partida con la id: " + id)))
                .map(PartidaDto::new);
    }

    @Override
    public Flux<PartidaDto> getAllGames() {
        return partidaRepository.findAll()
                .map(PartidaDto::new);

    }

    @Override
    public Mono<Void> deleteGame(Integer id) {
        Mono<Void> existePartida = partidaRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Id no encontrada")))
                .then();
        return Mono
                .when(existePartida)
                .then(partidaRepository.deleteById(id))
                .then();
    }

    @Override
    public Mono<PartidaDto> updateGame(PartidaDto partidaDto, Integer id) {
        Mono<Void> existePartida = partidaRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Id no encontrada")))
                .then();
        Partida partida = new Partida(partidaDto);
        partida.setId(id);

        return Mono
                .when(existePartida)
                .then(partidaRepository.save(partida))
                .map(PartidaDto::new);
    }

    @Override
    public Mono<PartidaNoIdDto> saveGame(PartidaNoIdDto partidaDto) {
        return partidaRepository.save(new Partida(partidaDto))
                .map(PartidaNoIdDto::new);
    }

    @Override
    @Transactional
    public Mono<PartidaDto> joinGame(Integer id, PartidaNoIdDto partidaDto) {

        return partidaRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Id no encontrada")))
                .filter(partidaUtil::isGuestNull)
                .switchIfEmpty(Mono.error(new Exception("Partida completa")))
                .doOnNext(n -> n.joinGame(partidaDto))
                .flatMap(partidaRepository::save)
                .map(PartidaDto::new);
    }

    @Override
    public Mono<PartidaDto> play(int gameid, String nombre, int columna) {
        return partidaRepository.findById(gameid)
                .switchIfEmpty(Mono.error(new Exception("Partida no encontrada")))
                .filter(n -> partidaUtil.turnCheck(n,nombre))
                .switchIfEmpty(Mono.error(new Exception("No es tu turno")))
                .doOnNext(n -> n.play(columna))
                .doOnNext(n -> partidaUtil.evaluateGame(n))
                .flatMap(partidaRepository::save)
                .map(PartidaDto::new);

    }
}
