package com.bosonit.conecta4v2.util;

import com.bosonit.conecta4v2.controller.dto.PartidaDto;
import com.bosonit.conecta4v2.domain.Partida;
import org.springframework.stereotype.Component;

@Component
public class PartidaUtil {

    public Boolean isGuestNull(Partida partida) {
        return partida.getGuest_name() == null;
    }

    public PartidaDto evaluateGame(PartidaDto partidaDto) {
        String tableroCadena = partidaDto.getTablero();
        String[] tablero = tableroCadena.split(",");


        int contador = 1;
        //columnas
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                if ((int)tablero[i].charAt(j) == (int)tablero[i].charAt(j + 1) && (int)tablero[i].charAt(j + 1) != 48) {
                    contador += 1;
                    if (contador == 4) {
                        partidaDto.setGanador(partidaDto.getTurno());
                        partidaDto.setExiste_ganador(true);
                        return partidaDto;
                    }
                } else {
                    contador = 1;
                }
            }
        }
        contador = 1;
        //filas
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if ((int)tablero[j].charAt(i) == (int)tablero[j + 1].charAt(i) && (int)tablero[j + 1].charAt(i) != 48) {
                    contador += 1;
                    if (contador == 4) {
                        partidaDto.setGanador(partidaDto.getTurno());
                        partidaDto.setExiste_ganador(true);
                        return partidaDto;
                    }
                } else {
                    contador = 1;
                }
            }
        }
        //diagonales1
        contador = 1;
        int jtemp = 0;

        for (int j = 0; j < 6; j++) {
            jtemp = j - 2;
            for (int i = 0; i < 6; i++) {

                if (jtemp >= 0 && (i+1<6) && (int)tablero[jtemp].charAt(i) == (int)tablero[jtemp + 1].charAt(i + 1) && (int)tablero[jtemp + 1].charAt(i + 1) != 48) {
                    contador += 1;
                    jtemp++;
                    if (contador == 4) {
                        partidaDto.setGanador(partidaDto.getTurno());
                        partidaDto.setExiste_ganador(true);
                        return partidaDto;
                    }
                } else {
                    contador = 1;
                }
            }
        }

        //diagonales2
        contador = 1;
        jtemp = 0;

        for (int j = 0; j < 6; j++) {
            jtemp = j - 2;
            for (int i = 5; i >= 0; i--) {

                if (jtemp >= 0 && (i+1<6) && (int)tablero[jtemp].charAt(i) == (int)tablero[jtemp + 1].charAt(i - 1) && (int)tablero[jtemp + 1].charAt(i - 1) != 48) {
                    contador += 1;
                    jtemp++;
                    if (contador == 4) {
                        partidaDto.setGanador(partidaDto.getTurno());
                        partidaDto.setExiste_ganador(true);
                        return partidaDto;
                    }

                } else {
                    contador = 1;
                }
            }
        }
        System.out.println("He llegado al final de evaluarpartida");
        return partidaDto;

    }
}
