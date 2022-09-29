package com.bosonit.conecta4v2.application;

import com.bosonit.conecta4v2.controller.dto.PartidaDto;
import com.bosonit.conecta4v2.controller.dto.PartidaNoIdDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PartidaService {
    Mono<PartidaDto> getGame(Integer id);
    Flux<PartidaDto> getAllGames();
    Mono<Void> deleteGame(Integer id);
    Mono<PartidaDto> updateGame(PartidaDto partidaDto, Integer id);
    Mono<PartidaNoIdDto> saveGame(PartidaNoIdDto partidaDto);
}
