package org.iesalandalus.programacion.cuatroenraya.modelo;

public enum Ficha {
    AZUL,
    VERDE;

    @Override
    public String toString() {
        return name().substring(0,1);
    }
}

/*
Substring coge un pedazo de string, aquí le estamos diciendo que coja la primera letra de cada palabra.
(inicio, fin)= (0, 1) los números son posiciones. Si la palabra es Azul el nombre que devuelve será la A, porque
se encuentra entre el 0 y el 1.

*/
