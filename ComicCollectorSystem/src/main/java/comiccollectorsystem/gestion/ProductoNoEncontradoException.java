package comiccollectorsystem.gestion;

//Error personalizado cuando se busca un producto no existente
public class ProductoNoEncontradoException extends Exception {
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);  
    }
}
