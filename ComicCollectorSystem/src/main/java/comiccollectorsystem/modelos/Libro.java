package comiccollectorsystem.modelos;

//Clase abstracta Libro de la que heredan todos los productos del catalogo
public abstract class Libro  {
    private String titulo;
    private String autor;
    private boolean prestado;
    
    //Constructores
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }
    
    public Libro(String titulo, String autor, boolean prestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = prestado;
    }
    
    //Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor(){
        return autor;
    }
    
    public boolean isPrestado() {
        return prestado;
    }
    
    //Metodos para cambiar el estado de prestado a disponible
    public void prestar() {
        this.prestado = true;
    }

    public void devolver() {
        this.prestado = false;
    }

}