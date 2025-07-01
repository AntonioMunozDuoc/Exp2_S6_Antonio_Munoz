package comiccollectorsystem.modelos;

import java.util.ArrayList;

//Clase usuario
public class Usuario {
    private final String nombre;
    private final String id;
    private ArrayList<String[]> prestamos = new ArrayList<>();

    //Constructores
    public Usuario(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }
    
    //Getters
    public String getNombre() {
        return nombre;
    }
    
    public String getId() {
        return id;
    }
    public ArrayList<String[]> getPrestamos() {
        return prestamos;
    }
    
}