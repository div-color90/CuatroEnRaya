package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Casilla {
    private Ficha ficha; //atributo de la clase

    /*Los métodos constructores nos permiten crear objetos e instancia de la clase. En el constructor no se pone el tipo
    de valor de retorno. El constructor inicializa el objeto*/
    public Casilla(){ //esto es un constructor vacío que no recibe parámetros.
        this.ficha = null; // le decimos que empieza vacío.

    }
    //Métodos get y set--->
    public Ficha getFicha() {
        return ficha;
    }
    //Con esto obtengo los datos y mostrar por pantalla. Muestra u obtiene valores del atributo. Sin modificar nada.
    public void setFicha(Ficha ficha) throws CuatroEnRayaExcepcion{
        if (ficha == null){
            throw new NullPointerException ("No se puede poner una ficha nula.");
        }
        if(this.ficha != null){
            throw new CuatroEnRayaExcepcion ("La casilla ya contiene una ficha.");
        }
        this.ficha = ficha;
    }
    //Colocar valores a los atributos. El set sirve para cuando creo un objeto vacío O quiera modificar un valor.
    // Al modificar datos hay que validar.

    //No necesitamos parámetros porque verifica el estado interno del objeto.
    public boolean estaOcupada(){
      return this.ficha != null;
    }
    //me devuelve un true si está ocupada y un false cuando no

    @Override
    public String toString() {
        return String.format("%s", ficha == null ? " " : ficha.toString().substring(0,1));
    }

}
