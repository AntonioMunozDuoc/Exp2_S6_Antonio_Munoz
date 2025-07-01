package comiccollectorsystem.modelos;

public class NovelaGrafica extends Libro{
    private String genero;
    
    //Constructores
    public NovelaGrafica(String titulo, String autor, String genero) {
      super(titulo, autor);
      this.genero = genero;
    }
    
    public NovelaGrafica(String titulo, String autor, boolean prestado, String genero) {
      super(titulo, autor, prestado);
      this.genero = genero;
    }
    
    //Getter
    public String getGenero(){
        return genero;
    }   
    
    @Override //Sobrecarga del metodo toString
    public String toString() {
        return getTitulo() + "\" de " + getAutor() + "/ Novela Grafica, Genero " + genero;
    }
}