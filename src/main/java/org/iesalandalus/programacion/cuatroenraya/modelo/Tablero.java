package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Arrays;

public class Tablero {
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
    //Atributo array bidimensional de objeto Casilla
    private final Casilla[][] casillas;

    // Constructor
    public Tablero() {
        //crear el array (6 filas x 7 columnas = 42 espacio)
        this.casillas = new Casilla[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new Casilla(); //crea objeto en [i][j].
            }
        }
    }

    //Inicializar cada casilla, de filas de 0-5 y columnas de 0 a 6.
    //Pri/vate solo accesible dentro de la clase y void que no devuelve nada.

    private boolean columnaVacia(int columna) {
        return !casillas[0][columna].estaOcupada();
        //Espero que me devuelva un true cuando no haya nada y un false cuando no esté vacía.
        /*Con el operador NOT le decimos al mé/to/do que me devuelva lo contrario a lo que me devolvería el met.
        estaOcupada sin \!*/
        // estaOcupada es true --> ! lo vuelve false --> Resultado: "No está vacía". (Correcto) y viceversa.
//Devuelve true si la primera fila de esa columna no está vacía
    }

    public boolean estaVacio() {
        //declarar la variable vacío
        boolean tableroVacio = true;//de momento asumimos de que está vacío
        for (int j = 0; j < COLUMNAS ; j++) {
            if (!columnaVacia(j)) {
                tableroVacio = false;
            }
        }
        return tableroVacio;
    }
    private boolean columnaLlena(int columna) {
        return casillas[FILAS - 1][columna].estaOcupada();
    }
    //estamos mirando si la el tamaño - 1 la parte de arriba del tablero. la última fila 5
    //devuelve true si la casilla de arriba tiene ficha, columna llena.
    //devuelve false si la casilla de arriba está vacía, todavía caben fichas
    public boolean estaLleno() {
        boolean tableroLleno = true; // inicia lleno
        for (int j = 0; j < COLUMNAS; j++) {
            if (!columnaLlena(j)) {
                tableroLleno = false;
            }
        }
        return tableroLleno;
    }
    //si todas las columnas están llenas devuelve un true y no cambia la variable
    //Si hay alguna columna vacía tableroLleno se cambia a false.

    private void comprobarFicha(Ficha ficha) {
        if (ficha == null)
            throw new NullPointerException("La ficha no puede ser nula.");
    }

    private void comprobarColumna(int columna) {
        if (columna < 0 || columna == 7)
            throw new IllegalArgumentException("Columna incorrecta.");
    }

    private int getPrimeraFilaVacia(int columna) {
        for (int i = 0; i < 6; i++) {
            if (!casillas[i][columna].estaOcupada()) {
                return i;
            }
        }
        return -1;
    }
    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        return fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }
    public boolean introducirFicha(int columna, Ficha ficha) throws CuatroEnRayaExcepcion {
        comprobarColumna(columna);
        comprobarFicha(ficha);
        if (columnaLlena(columna)) {
            throw new CuatroEnRayaExcepcion("Columna llena.");
        }
        int filaLibre = 0;
        while(filaLibre < FILAS && casillas[filaLibre][columna].estaOcupada()){
            filaLibre++;
        }
        casillas[filaLibre][columna].setFicha(ficha);
        boolean ganadora = comprobarTirada(filaLibre, columna, ficha);

        return ganadora;
    }
    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int contador = 0;
        boolean resultado = false;
        for (int j = 0; j < COLUMNAS && !resultado; j++) {
            if (casillas[fila][j].getFicha() == ficha) {
                contador++;
                // Usamos el método de utilidad aquí
                if (objetivoAlcanzado(contador)) {
                    resultado = true;
                }
            } else {
                contador = 0;
            }
        }
        return resultado;
    }
    private boolean comprobarVertical(int columna, Ficha ficha) {
        int contador = 0;
        boolean resultado = false;

        for (int i = 0; i < FILAS && !resultado; i++) {
            if (casillas[i][columna].getFicha() == ficha) {
                contador++;
                // Usamos el método de utilidad aquí
                if (objetivoAlcanzado(contador)) {
                    resultado = true;
                }
            } else {
                contador = 0;
            }
        }
        return resultado;
    }
    private int menor(int fila, int columna) {
        return  (fila < columna) ? fila : columna;
    } //condición ? valorsitrue :valorsifalse.
    //si fila < columna devuelve fila, si no devuelve columna.

    private boolean comprobarDiagonalINE(int filaActual, int columnaActual, Ficha ficha) {
        int fichasIgualesConsecutivas = 0;
        int desplazamiento = menor(filaActual, columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual - desplazamiento;

        // Inicializamos fila y columna, y el bucle corre mientras no se alcance el objetivo
        // y estemos dentro de los límites del tablero.
        for (int fila = filaInicial, columna = columnaInicial;
             !objetivoAlcanzado(fichasIgualesConsecutivas) && (fila < FILAS && columna < COLUMNAS);
             fila++, columna++) {

            if (casillas[fila][columna].estaOcupada() && casillas[fila][columna].getFicha().equals(ficha)) {
                fichasIgualesConsecutivas++;
            } else {
                fichasIgualesConsecutivas = 0;
            }
        }

        // Un único punto de salida que evalúa el resultado final
        return objetivoAlcanzado(fichasIgualesConsecutivas);
    }
    private boolean comprobarDiagonalINO(int filaActual, int columnaActual, Ficha ficha) {
        int fichasIgualesConsecutivas = 0;
        int desplazamiento = menor(filaActual, COLUMNAS - 1 - columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual + desplazamiento;

        // El bucle se ejecuta mientras no se alcance el objetivo Y estemos dentro de los límites
        for (int fila = filaInicial, columna = columnaInicial;
             !objetivoAlcanzado(fichasIgualesConsecutivas) && (fila < FILAS && columna >= 0);
             fila++, columna--) {

            if (casillas[fila][columna].estaOcupada() && casillas[fila][columna].getFicha().equals(ficha)) {
                fichasIgualesConsecutivas++;
            } else {
                fichasIgualesConsecutivas = 0;
            }
        }

        return objetivoAlcanzado(fichasIgualesConsecutivas);
    }
    private boolean comprobarTirada(int fila, int columna, Ficha ficha) {
        boolean ganadora = false;
        if (comprobarHorizontal(fila, ficha)){
            ganadora = true;
        }
        if (comprobarVertical(columna, ficha)) {
            ganadora = true;
        }
        if (comprobarDiagonalINE(fila, columna,ficha)){
            ganadora = true;
        }
        if (comprobarDiagonalINO(fila, columna, ficha)){
            ganadora = true;
        }
        return ganadora;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // 1. Recorremos filas de arriba hacia abajo
        for (int fila = FILAS - 1; fila >= 0; fila--) {
            sb.append("|"); // Borde izquierdo

            for (int col = 0; col < COLUMNAS; col++) {
                // Importante: Cada casilla debe aportar 1 carácter al StringBuilder
                if (casillas[fila][col].estaOcupada()) {
                    sb.append(casillas[fila][col].getFicha().toString()); // Imprime 'A' o 'V'
                } else {
                    sb.append(" "); // Imprime un espacio si está vacía
                }
            }

            sb.append("|\n"); // Borde derecho y salto de línea
        }
        sb.append(" ");
        sb.append("-".repeat(COLUMNAS));
        sb.append("\n");

        return sb.toString();
    }
}
