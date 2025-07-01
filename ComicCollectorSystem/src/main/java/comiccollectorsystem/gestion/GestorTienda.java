package comiccollectorsystem.gestion;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Scanner;
import comiccollectorsystem.modelos.*;

//Clase encargada de la gestion de la tienda. Realiza todas las operaciones salvo el guardado de archivos
public class GestorTienda {

    private String idUsuarioActivo = null; //Id del usuario en sesion
    private ArrayList<Comic> comics = new ArrayList<>(); //Lista con instancias de la clase Comic
    private ArrayList<Manga> mangas = new ArrayList<>(); // Lista con instancias de la clase Manga
    private ArrayList<NovelaGrafica> novelasGraficas = new ArrayList<>(); // Lista con instancias de la clase Novela Grafica
    private HashMap<String, Usuario> usuarios = new HashMap<>(); // Hashmap Key ID Value instancia de la clase Usuario
    private HashSet<String> idsUsuarios = new HashSet<>(); //HashSet con ID unicas de cada usuario
    private TreeSet<String> productosOrdenados = new TreeSet<>();//TreeSet de Strings con el catalogo completo de productos
    private Scanner sc = new Scanner(System.in);
    
    //Getters
    public ArrayList<Comic> getComics() {
        return comics;
    }

    public ArrayList<Manga> getMangas() {
        return mangas;
    }

    public ArrayList<NovelaGrafica> getNovelasGraficas() {
        return novelasGraficas;
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public HashSet<String> getIdsUsuarios() {
        return idsUsuarios;
    }

    public TreeSet<String> getProductosOrdenados() {
        return productosOrdenados;
    }
    
    //Metodo que agrega un libro nuevo
    public void agregarLibro(){
        try{
            System.out.println("¿Que tipo de libro desea agregar a la coleccion?");
            System.out.println("1- Comic  2- Manga  3- Novela Grafica");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion){
                case 1:
                    agregarComic(); //Metodo para agregar comic
                    break;
                    
                case 2:
                    agregarManga();
                    break;
                    
                case 3:
                    agregarNovela();
                    break;
                    
                default:
                    System.out.println("Debe ingresar una opcion valida");
            }
        }catch(InputMismatchException e){
            System.out.println("Error: Debes ingresar un numero entero ");
        }
    }
    
    //Metodo para agregar comic
    public void agregarComic(){
        System.out.print("Ingrese el título del cómic: ");
        String titulo = sc.nextLine().trim();
        System.out.print("Ingrese el autor del cómic: ");
        String autor = sc.nextLine().trim();

        System.out.print("Ingrese el superhéroe principal del comic: ");
        String superheroe = sc.nextLine().trim();
        int capitulo = 0;
        while (true) {
            try {
                System.out.print("Ingrese el número de capítulo: ");
                capitulo = sc.nextInt();
                sc.nextLine();
                if (capitulo <= 0) {
                    System.out.println("El capítulo debe ser un número positivo.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) { //Error de input del usuario
                System.out.println("¡Error! Debes ingresar un número entero para el capítulo.");
                sc.nextLine(); // limpiar entrada inválida
            }
        }
        Comic nuevoComic = new Comic(titulo, autor, capitulo, superheroe);
        String comicTree = ("Comic: " + titulo + " Autor: " + autor + " Capitulo: " + capitulo + " Superheroe: " + superheroe).toLowerCase(); //Entrada para el catalogo global
        if (!productosOrdenados.add(comicTree)){
            System.out.println("El comic que intenta agregar ya existe en el catalogo");
        }else{
            comics.add(nuevoComic);
            System.out.println("El Comic " + titulo + " ha sido agregado exitosamente.");
        }
    }
    
    //Metodo para agregar un Manga
    public void agregarManga() {
        System.out.print("Ingrese el título del manga: ");
        String titulo = sc.nextLine();
        System.out.print("Ingrese el autor del manga: ");
        String autor = sc.nextLine();

        System.out.print("Ingrese la categoría del manga: ");
        String categoria = sc.nextLine().trim();

        int tomo = 0;
        while (true) {
            try {
                System.out.print("Ingrese el número de tomo: ");
                tomo = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                if (tomo <= 0) {
                    System.out.println("El tomo debe ser un número positivo.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) { //Error de input del usuario
                System.out.println("¡Error! Debes ingresar un número entero para el tomo.");
                sc.nextLine(); // limpiar entrada inválida
            }
        }

        Manga nuevoManga = new Manga(titulo, autor, tomo, categoria);
        String mangaTree = ("Manga: " + titulo + " Autor: " + autor + " Tomo: " + tomo + " Categoria: "+ categoria).toLowerCase(); //Entrada para el catalogo global

        if (!productosOrdenados.add(mangaTree)) {
            System.out.println("El manga que intenta agregar ya existe en el catálogo.");
        } else {
            mangas.add(nuevoManga);
            System.out.println("El Manga " + titulo + " ha sido agregado exitosamente.");
        }
    }
    
    //Metodo para agregar una Novela Grafica
    public void agregarNovela() {
        System.out.print("Ingrese el título de la novela gráfica: ");
        String titulo = sc.nextLine().trim();

        System.out.print("Ingrese el autor de la novela gráfica: ");
        String autor = sc.nextLine().trim();

        System.out.print("Ingrese el género de la novela gráfica: ");
        String genero = sc.nextLine().trim();

        NovelaGrafica nuevaNovela = new NovelaGrafica(titulo, autor, genero);
        String novelaTree = ("Novela Grafica: " + titulo + " Autor: " + autor + " Genero: " + genero).toLowerCase(); //Entrada para el catalogo global

        if (!productosOrdenados.add(novelaTree)) {
            System.out.println("La novela gráfica que intenta agregar ya existe en el catálogo.");
        } else {
            novelasGraficas.add(nuevaNovela);
            System.out.println("La Novela gráfica " + titulo + " ha sido agregada exitosamente.");
        }
    }
    
    //Metodo para realizar prestamos de libros
    public void prestarProducto() {
       if (idUsuarioActivo == null){
           System.out.println("Primero debes iniciar sesion para realizar un pedido");
       }else{
            try {
                System.out.println("¿Qué tipo de producto busca?");
                System.out.println("1- Comic  2- Manga  3- Novela Gráfica");

                int opcion = sc.nextInt();
                sc.nextLine();

                System.out.print("Ingrese el título del producto que desea pedir: ");
                String titulo = sc.nextLine();

                switch (opcion) {
                    case 1:
                        procesarPrestamo(comics, titulo, "Comic");
                        break;
                    case 2:
                        procesarPrestamo(mangas, titulo, "Manga");
                        break;
                    case 3: 
                        procesarPrestamo(novelasGraficas, titulo, "Novela Grafica");
                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
                }
            } catch (InputMismatchException e) { //Error de input del usuario
                System.out.println("Debe ingresar un número válido.");
                sc.nextLine();
            } catch (ProductoNoEncontradoException | ProductoYaPrestadoException e) { //Errores personalizados en caso que el libro este prestado o no exista
                System.out.println("ERROR: " + e.getMessage());
            }
       }
    }
    //Metodo auxiliar que busca en el arraylist correspondiente todas las coincidencias con el nombre indicado y lo muestra en pantalla
    public void busquedaIndice(ArrayList<? extends Libro> lista, String nombre)throws ProductoNoEncontradoException{
        int j = 0;
        for (int i = 0; i < lista.size() ; i++){
            if (lista.get(i).getTitulo().equalsIgnoreCase(nombre)){
                j++;
                 System.out.println(i + "- " + lista.get(i));
            }
        }
        if (j == 0){
          throw new ProductoNoEncontradoException("No se encontro el producto " + nombre);  
        }
    }
   
    //Metodo que procesa la opcion elegida por el usuario y realiza el prestamo del producto
   public void procesarPrestamo(ArrayList<? extends Libro> lista, String titulo, String tipo)
        throws ProductoNoEncontradoException, ProductoYaPrestadoException {
        busquedaIndice(lista, titulo);
        System.out.print("Ingresa el número del producto que deseas solicitar: ");
        int numero = sc.nextInt();
        sc.nextLine();
        
        if (numero < 0 || numero >= lista.size()) {
            System.out.println("Índice fuera de rango."); 
        }else{
            if (!lista.get(numero).isPrestado()) {
                lista.get(numero).prestar();
                usuarios.get(idUsuarioActivo).getPrestamos().add(new String[]{tipo, lista.get(numero).getTitulo(), String.valueOf(numero)});
                System.out.println("Su préstamo ha sido realizado con éxito.");
            } else {
                throw new ProductoYaPrestadoException("El producto ya se encuentra en préstamo."); //Error por producto ya en prestamo
            }  
        }
        
    }
    
    //Metodo encargado de la devolucion de un prestamo
    public void devolverProducto(){
        if (idUsuarioActivo == null){
            System.out.println("Primero debes iniciar sesion para ver tus prestamos pendientes");
        }else{
            if(usuarios.get(idUsuarioActivo).getPrestamos().isEmpty()){
                System.out.println("No tienes productos pendientes para devolucion");
            }else{
                System.out.println("¿Cual producto deseas devolver");
                for (int i = 0; i < usuarios.get(idUsuarioActivo).getPrestamos().size(); i++){
                    System.out.println((i+1) + "- " + "Tipo: " + usuarios.get(idUsuarioActivo).getPrestamos().get(i)[0] + 
                            " Titulo: " + usuarios.get(idUsuarioActivo).getPrestamos().get(i)[1]);
                }
                System.out.println("Ingrese el numero del producto que desea devolver");
                int numero = sc.nextInt();
                if ((numero -1) < 0 || usuarios.get(idUsuarioActivo).getPrestamos().size()> numero ){
                    System.out.println("Ingrese un numero valido");
                }else{
                    int indice = Integer.parseInt(usuarios.get(idUsuarioActivo).getPrestamos().get(numero-1)[2]);
                    switch (usuarios.get(idUsuarioActivo).getPrestamos().get(numero-1)[0]){
                        case "Manga":
                            mangas.get(indice).devolver();
                            break;
                        case "Comic":
                            comics.get(indice).devolver();
                            break;
                        case "Novela Grafica":
                            novelasGraficas.get(indice).devolver();
                            break;    
                    }
                    System.out.println("El producto fue regresado con excito");
                    usuarios.get(idUsuarioActivo).getPrestamos().remove(numero - 1);
                }
            }
        }
    }
    
    //Metodo que muestra el catalogo global ordenado
    public void mostrarCatalogo(){
        System.out.println("Este es nuetro catalogo completo de productos");
        for (String p: productosOrdenados){
            System.out.println(p);
        }
    }   
    
    //Metodo para agregar usuario
    public void agregarUsuario() {
        try {
            System.out.println("ID de usuario: ");
            String id = sc.nextLine();
            if (!idsUsuarios.add(id)){
                throw new Exception("El ID de usuario ya existe.");
            }
            System.out.println("Nombre: ");
            String nombre = sc.nextLine();
            Usuario usuario = new Usuario(nombre, id);
            usuarios.put(id, usuario);
            System.out.println("Usuario agregado correctamente.");
            idUsuarioActivo = id;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); //Error por ID de usuario ya existente
        }
    }
    
    //Metodo para iniciar sesion y dejar al usuario como el usuario activo para prestamos y devoluciones
    public void iniciarSesion(){
        System.out.println("Ingrese el ID de su usuario");
        String id = sc.nextLine();
        if (usuarios.containsKey(id)) {
            System.out.println("Bienvenido " + usuarios.get(id).getNombre());
            idUsuarioActivo = id;
        } else {
            System.out.println("ID no registrado, porfavor ingrese un ID valido o registrese en nuestra pagina.");
        }
       
    }
    
    //Metodo que actualiza el catalogo una vez cargados los datos de los archivos csv
    public void actualizarCatalogo(){
        for (Comic c : comics){
            String comicTree = ("Comic: " + c.getTitulo() + " Autor: " + c.getAutor() + " Capitulo: " + c.getCapitulo() + " Superheroe: " + c.getSuperheroe()).toLowerCase();
            productosOrdenados.add(comicTree);
        }
        for (Manga m : mangas){
            String mangaTree = ("Manga: " + m.getTitulo() + " Autor: " + m.getAutor() + " Tomo: " + m.getTomo() + " Categoria: "+ m.getCategoria()).toLowerCase();
            productosOrdenados.add(mangaTree);
        }
        for (NovelaGrafica n : novelasGraficas){
            String novelaTree = ("Novela Grafica: " + n.getTitulo() + " Autor: " + n.getAutor() + " Genero: " + n.getGenero()).toLowerCase();
            productosOrdenados.add(novelaTree);
        }
    }
    
    public void actualizarID(){
        for (String id : usuarios.keySet()) {
            idsUsuarios.add(id);
        }
    }
}
   

