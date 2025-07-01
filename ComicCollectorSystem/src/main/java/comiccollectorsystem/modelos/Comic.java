package comiccollectorsystem.modelos;

//Subclase Comic que hereda de libro
public class Comic extends Libro{
    private int capitulo;
    private String superheroe;
    
    //Constructores
    public Comic(String titulo, String autor, int capitulo, String superheroe) {
      super(titulo, autor);
      this.capitulo = capitulo;
      this.superheroe = superheroe;
    }
    
    public Comic(String titulo, String autor, boolean prestado, int capitulo, String superheroe) {
      super(titulo, autor, prestado);
      this.capitulo = capitulo;
      this.superheroe = superheroe;
    }
    
    //Getters
    public int getCapitulo() {
        return capitulo;
    }
    
    public String getSuperheroe(){
        return superheroe;
    }
    
    @Override //Sobrecarga del metodo to String
    public String toString() {
        return getTitulo() + "/ de " + getAutor() + "/ Capitulo: " + capitulo + ", Superheroe: " + superheroe;
    }
}
