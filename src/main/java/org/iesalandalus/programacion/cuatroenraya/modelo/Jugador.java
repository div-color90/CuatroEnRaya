package org.iesalandalus.programacion.cuatroenraya.modelo;

public record Jugador(String nombre, Ficha colorFichas) {

    /* public Jugador(String nombre, Ficha colorFichas) {
        this.nombre = nombre;
        this.colorFichas = colorFichas;
    } */
//Nuestro constructor pasa de eso a esto después de los métodos y para poder usar los métodos en el constructor para validar.
    //Constructor canónico con validaciones:
    public Jugador {
      validarNombre(nombre);
      validarColor(colorFichas);
    }
//Pasamos de private void validarNombre a private static void porque no acceden a atributos de instancia.

    private static void validarNombre(String nombre) {
        if( nombre == null){
            throw new NullPointerException ("El nombre no puede ser nulo.");
        }
        if(nombre.trim().isEmpty())
            throw new IllegalArgumentException ("El nombre no puede estar en blanco.");
    }
    private void validarColor(Ficha colorFichas){
        if (colorFichas == null){
            throw new NullPointerException ("El color de las fichas no puede ser nulo.");
        }
    }

    @Override
    public String toString() {

        return String.format("%s (%s)", nombre, colorFichas);
    }

}
