package comiccollectorsystem.gestion;

//Error personalizado cuando se trata de pedir prestado un producto ya en prestamo
public class ProductoYaPrestadoException extends Exception {
    public ProductoYaPrestadoException(String mensaje) {
        super(mensaje);
    }
}