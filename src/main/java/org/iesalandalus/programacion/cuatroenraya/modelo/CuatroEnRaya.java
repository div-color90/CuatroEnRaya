package org.iesalandalus.programacion.cuatroenraya.modelo;

import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

import java.util.Objects;

public class CuatroEnRaya {

    private static final int NUMERO_JUGADORES = 2;
    private final Jugador[] jugadores;
    private final Tablero tablero;

    public CuatroEnRaya(Jugador jugador1, Jugador jugador2){
        Objects.requireNonNull(jugador1, "ERROR: El jugador 1 no puede ser nulo.");
        Objects.requireNonNull(jugador2, "ERROR: El jugador 2 no puede ser nulo.");
        jugadores = new Jugador[NUMERO_JUGADORES];
        jugadores[0] = jugador1;
        jugadores[1] = jugador2;
        tablero = new Tablero();
    }

    public void jugar() {
        int turno = 0;
        boolean hayGanador = false;
        Jugador jugadorQueJuega = jugadores[turno];

        while (!tablero.estaLleno() && !hayGanador) {
            jugadorQueJuega = jugadores[turno++ % NUMERO_JUGADORES];
            hayGanador = tirar(jugadorQueJuega);
        }

        if (hayGanador) {
            System.out.printf("ENHORABUENA, %s ha ganado!!!", jugadorQueJuega.nombre());
        } else {
            System.out.println("Habéis empatado ya que no quedan más casillas libres.");
        }
    }
    private boolean tirar(Jugador jugador) {
        boolean jugadaGanadora = false;
        boolean fichaColocada = false;

        // Repetir el proceso mientras no se introduzca una ficha correctamente
        do {
            try {
                int columna = Consola.leerColumna(jugador);
                jugadaGanadora = tablero.introducirFicha(columna, jugador.colorFichas());

                fichaColocada = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Por favor, elige otra columna.");
            }
        } while (!fichaColocada);

        return jugadaGanadora;
    }
}
