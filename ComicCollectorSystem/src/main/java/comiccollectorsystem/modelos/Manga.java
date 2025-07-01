package comiccollectorsystem.modelos;

//Subclase Manga que hereda de libro
public class Manga extends Libro{
    private int tomo;
    private String categoria;
    
    //Constructores
    public Manga(String titulo, String autor, int tomo, String categoria) {
      super(titulo, autor);
      this.tomo = tomo;
      this.categoria = categoria;
    }
    
    public Manga(String titulo, String autor, boolean prestado, int tomo, String categoria) {
      super(titulo, autor, prestado);
      this.tomo = tomo;
      this.categoria = categoria;
    }
    
    //Getters
    public int getTomo() {
        return tomo;
    }
    
    public String getCategoria(){
        return categoria;
    }
    
    @Override //Sobrecarga del metodo toString
    public String toString() {
        return getTitulo() + "/ de " + getAutor() + " /Manga, Tomo: " + tomo + ", Categoría: " + categoria;
    }
    
}