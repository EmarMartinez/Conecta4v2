package com.bosonit.conecta4v2.application;

import com.bosonit.conecta4v2.controller.dto.PartidaDto;
import com.bosonit.conecta4v2.controller.dto.PartidaNoIdDto;
import com.bosonit.conecta4v2.domain.Partida;
import com.bosonit.conecta4v2.infraestructure.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PartidaServiceImpl implements PartidaService{

    @Autowired
    PartidaRepository partidaRepository;

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

}
