package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

    private Consola(){
//evitamos que se crean instancias de esta clase
    }
    //Como es una clase de utilidad todos los métodos tienen que llevar static
    public static String leerNombre(){
        String nombre;
        do{
            System.out.print("Introduce el nombre del jugador: ");
            nombre = Entrada.cadena();
        }while (nombre.isBlank());
        return nombre;
    }
    public static Ficha elegirColorFicha(){
        int eleccion;
        do{
            System.out.print("Elige el color de tus fichas (0-AZUL, 1-VERDE):");
            eleccion = Entrada.entero();
        }while (eleccion < 0 || eleccion > 1);
        return Ficha.values()[eleccion];
    }
    public static Jugador leerJugador(){
        System.out.println("Introduce los datos del PRIMER jugador");
        return new Jugador(leerNombre(), elegirColorFicha());
    }
    public static Jugador leerJugador(Ficha ficha){
        System.out.println("Introduce los datos del SEGUNDO jugador");
        return new Jugador(leerNombre(), ficha);
    }

    public static int leerColumna(Jugador jugador){
        int columna;
        do{
            System.out.printf("%s, introduce la columna en la que deseas introducir la ficha (0 - %d): ", jugador.nombre(), Tablero.COLUMNAS - 1);
                    columna = Entrada.entero();
        }while (columna < 0 || columna >= Tablero.COLUMNAS);
        return columna;

    }
}
