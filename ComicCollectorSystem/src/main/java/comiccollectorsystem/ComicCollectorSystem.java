package comiccollectorsystem;

import comiccollectorsystem.gestion.*;
import java.util.HashMap;
import comiccollectorsystem.manager.ArchivosManager;
import comiccollectorsystem.modelos.Usuario;
import java.util.Scanner;

public class ComicCollectorSystem {

    public static void main(String[] args) throws java.io.UnsupportedEncodingException, ProductoNoEncontradoException, ProductoYaPrestadoException  {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        
        Scanner scanner = new Scanner(System.in);
        GestorTienda gestor = new GestorTienda();
        ArchivosManager manager = new ArchivosManager();
        
        //Lectura de archivos csv para los productos y txt para los usuarios
        HashMap<String, Usuario> usuariosCargados = manager.cargarUsuarios("usuarios.txt");
        gestor.getUsuarios().putAll(usuariosCargados);
        gestor.getComics().addAll(manager.cargarComics("comics.csv"));
        gestor.getMangas().addAll(manager.cargarMangas("mangas.csv"));
        gestor.getNovelasGraficas().addAll(manager.cargarNovelasGraficas("novelasgraficas.csv"));
        
        //Actualizacion del catalogo ordenado (treeset) luego de la lectura de archivos
        gestor.actualizarCatalogo();
        
        System.out.println("Bienvendio a ComicCollector");
        int opcion = 10;
        do {
            mostrarMenu();
            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        gestor.agregarUsuario();
                        break;
                        
                    case 2:
                        gestor.iniciarSesion(); 
                        break;
                        
                    case 3:
                        gestor.agregarLibro();
                            break;
                        
                    case 4:
                        gestor.prestarProducto(); 
                        break;

                        
                    case 5:
                        gestor.devolverProducto();
                        break;
                        
                    case 6:
                        gestor.mostrarCatalogo(); 
                        break;
                        
                    case 0:
                        System.out.println("Muchas gracias por su visita");
                        manager.guardarUsuarios("usuarios.txt", gestor.getUsuarios());
                        manager.guardarComics("comics.csv", gestor.getComics());
                        manager.guardarMangas("mangas.csv", gestor.getMangas());
                        manager.guardarNovelasGraficas("novelasgraficas.csv", gestor.getNovelasGraficas());
                        break;
                        
                    default:
                        System.out.println("Opcion invalida, intentelo nuevamente");
                        break;
                }
            } catch (NumberFormatException e) { //Error de entrada no valida
                System.out.println("Entrada inválida. Ingrese el número de una de las alternativas.");
            }
        } while (opcion != 0);
    }
    //Funcioon que muestra menu principal
    private static void mostrarMenu() {
        System.out.println("----------MENU PRINCIPAL----------");
        System.out.println("1. Registrar Usuario");
        System.out.println("2. Inicia Sesion de Usuario");
        System.out.println("3. Agregar Nuevo Producto");
        System.out.println("4. Prestamo");
        System.out.println("5. Devolver Producto");
        System.out.println("6. Mostrar Catalogo Completo de Productos");
        System.out.println("0. Salir");
    }
}