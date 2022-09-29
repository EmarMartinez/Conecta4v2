package com.bosonit.conecta4v2.domain;

import com.bosonit.conecta4v2.controller.dto.PartidaDto;
import com.bosonit.conecta4v2.controller.dto.PartidaNoIdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partida {
    @Id
    @GeneratedValue
    Integer id;
    String host_name;
    String host_ip;
    String guest_name;
    String guest_ip;
    String tablero;
    String turno;
    Boolean existe_ganador;


    public Partida(PartidaDto partidaDto) {
        this.id = partidaDto.getId();
        this.host_name = partidaDto.getHost_name();
        this.host_ip = partidaDto.getHost_ip();
        this.guest_name = partidaDto.getGuest_name();
        this.guest_ip = partidaDto.getGuest_ip();
        this.tablero = partidaDto.getTablero();
        this.turno = partidaDto.getTurno();
        this.existe_ganador = partidaDto.getExiste_ganador();
    }

    public Partida(PartidaNoIdDto partidaDto) {
        this.host_name = partidaDto.getHost_name();
        this.host_ip = partidaDto.getHost_ip();
        this.guest_name = partidaDto.getGuest_name();
        this.guest_ip = partidaDto.getGuest_ip();
        this.tablero = partidaDto.getTablero();
        this.turno = partidaDto.getTurno();
        this.existe_ganador = partidaDto.getExiste_ganador();
    }


    public PartidaDto PartidaToOutputDto() {
        return new PartidaDto(this.id,
                this.host_name,
                this.host_ip,
                this.guest_name,
                this.guest_ip,
                this.tablero,
                this.turno,
                this.existe_ganador);
    }
}