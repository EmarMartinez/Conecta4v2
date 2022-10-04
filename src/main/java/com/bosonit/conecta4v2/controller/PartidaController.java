package com.bosonit.conecta4v2.controller;

import com.bosonit.conecta4v2.application.PartidaServiceImpl;
import com.bosonit.conecta4v2.controller.dto.PartidaDto;
import com.bosonit.conecta4v2.controller.dto.PartidaNoIdDto;
import com.bosonit.conecta4v2.util.PartidaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequestMapping("partidas")
public class PartidaController {

    @Autowired
    PartidaServiceImpl partidaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PartidaDto> getPartidas() {
        return partidaService.getAllGames();
    }

//    @GetMapping(value = "/flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
//    public Flux<PartidaDto> getPartidasFlux() {
//        return partidaService.getAllGames();
//    }

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

    @PutMapping("/join/{id}")
    public Mono<PartidaDto> joinGameById(@PathVariable Integer id, @RequestBody PartidaNoIdDto partidaNoIdDto) {
        return partidaService.joinGame(id, partidaNoIdDto);
    }

    @Autowired
    PartidaUtil partidaUtil;
    @PostMapping("pruebaevaluacion")
    public PartidaDto prueba(@RequestBody PartidaDto partidaDto) {
        return partidaUtil.evaluateGame(partidaDto);
    }
}
