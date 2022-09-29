package com.bosonit.conecta4v2.controller.dto;

import com.bosonit.conecta4v2.domain.Partida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidaNoIdDto {
    String host_name;
    String host_ip;
    String guest_name;
    String guest_ip;
    String tablero;
    String turno;
    Boolean existe_ganador;

    public PartidaNoIdDto (Partida partida) {
        this.host_name = partida.getHost_name();
        this.host_ip = partida.getHost_ip();
        this.guest_name = partida.getGuest_name();
        this.guest_ip = partida.getGuest_ip();
        this.tablero = partida.getTablero();
        this.turno = partida.getTurno();
        this.existe_ganador = partida.getExiste_ganador();
    }
}
