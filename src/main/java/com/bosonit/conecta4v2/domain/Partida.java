package com.bosonit.conecta4v2.domain;

import com.bosonit.conecta4v2.controller.dto.PartidaDto;
import com.bosonit.conecta4v2.controller.dto.PartidaNoIdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Arrays;


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
    String ganador;


    public Partida(PartidaDto partidaDto) {
        this.id = partidaDto.getId();
        this.host_name = partidaDto.getHost_name();
        this.host_ip = partidaDto.getHost_ip();
        this.guest_name = partidaDto.getGuest_name();
        this.guest_ip = partidaDto.getGuest_ip();
        this.tablero = partidaDto.getTablero();
        this.turno = partidaDto.getTurno();
        this.existe_ganador = partidaDto.getExiste_ganador();
        this.ganador = partidaDto.getGanador();
    }

    public Partida(PartidaNoIdDto partidaDto) {
        this.host_name = partidaDto.getHost_name();
        this.host_ip = partidaDto.getHost_ip();
        this.guest_name = partidaDto.getGuest_name();
        this.guest_ip = partidaDto.getGuest_ip();
        this.tablero = partidaDto.getTablero();
        this.turno = partidaDto.getTurno();
        this.existe_ganador = partidaDto.getExiste_ganador();
        this.ganador = partidaDto.getGanador();
    }


    public PartidaDto PartidaToOutputDto() {
        return new PartidaDto(this.id,
                this.host_name,
                this.host_ip,
                this.guest_name,
                this.guest_ip,
                this.tablero,
                this.turno,
                this.existe_ganador,
                this.ganador);
    }

    public Partida joinGame(PartidaNoIdDto partidaDto) {
        String turno = Math.random() > 0.5 ? "Host" : "Guest";
        this.tablero = "000000,000000,000000,000000,000000,000000,000000";
        this.setTurno(turno);
        this.setGuest_name(partidaDto.getGuest_name());
        this.setGuest_ip(partidaDto.getGuest_ip());
        this.setExiste_ganador(false);
        return this;
    }

    public PartidaDto play(int columna) {
        String[] tablero = this.getTablero().split(",");
        char[] array = tablero[columna].toCharArray();
        for(int i = 0; i<6; i++) {
            if(array[i] == '0') {
                if(this.getTurno().equals("Host")) {
                    array[i] = 'H';
                }
                else {
                    array[i] = 'G';
                }
                break;
            }
        }
        tablero[columna] = (Character.toString(array[0])+
                Character.toString(array[1])+
                Character.toString(array[2])+
                Character.toString(array[3])+
                Character.toString(array[4])+
                Character.toString(array[5])
        );
        String s = Arrays.toString(tablero);
        this.tablero = tablero[0] + ","+
                tablero[1] + ","+
                tablero[2] + ","+
                tablero[3] + ","+
                tablero[4] + ","+
                tablero[5] + ","+
                tablero[6];
        if (this.getTurno().equals("Host")) {
            this.setTurno("Guest");
        } else {
            this.setTurno("Host");
        }
        return new PartidaDto(this);
    }
}
