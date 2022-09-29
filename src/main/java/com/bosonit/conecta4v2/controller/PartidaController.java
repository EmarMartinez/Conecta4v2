package com.bosonit.conecta4v2.controller;

import com.bosonit.conecta4v2.application.PartidaServiceImpl;
import com.bosonit.conecta4v2.controller.dto.PartidaDto;
import com.bosonit.conecta4v2.controller.dto.PartidaNoIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("partidas")
public class PartidaController {

    @Autowired
    PartidaServiceImpl partidaService;

    @GetMapping
    public Flux<PartidaDto> getPartidas() {
        return partidaService.getAllGames();
    }

    @PostMapping
    public Mono<PartidaNoIdDto> createGame(@RequestBody PartidaNoIdDto partidaDto) {
        return partidaService.saveGame(partidaDto);
    }

    @GetMapping("/{id}")
    public Mono<PartidaDto> getGameById(@PathVariable Integer id) {
        return partidaService.getGame(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteGameById(@PathVariable Integer id) {
        return partidaService.deleteGame(id);
    }
}
