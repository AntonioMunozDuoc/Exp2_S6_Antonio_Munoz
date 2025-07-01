package comiccollectorsystem.manager;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import comiccollectorsystem.modelos.*;

//Clase encargada de la gestion de los archivos txt y csv
public class ArchivosManager {
    
    //Metodo encargado de guardar datos de usuario y sus respectivos prestamos en un archivo txt
    public void guardarUsuarios(String nombreArchivo, HashMap<String, Usuario> usuarios) {
        try (FileWriter fw = new FileWriter(nombreArchivo)) {
            for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
                Usuario usuario = entry.getValue();
                // Guardado de ID y Nombre separado por |
                fw.write(entry.getKey() + "|" + usuario.getNombre() + "\n");

                // Guardado de prestamos de libros activos del usuario
                for (String[] prestamo : usuario.getPrestamos()) {
                    fw.write(prestamo[0] + "," + prestamo[1] + "," + prestamo[2] + "\n");
                }
                // Separador entre usuarios en archivo txt
                fw.write("---\n");
            }
            System.out.println("Usuarios guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
    
    //Metodo encargado de la carga de datos de archivo usuarios.txt al inicio del programa
    public HashMap<String, Usuario> cargarUsuarios(String nombreArchivo) {
        HashMap<String, Usuario> usuarios = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            Usuario usuarioActual = null;
            String idActual = null;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                if (linea.equals("---")) {
                    if (usuarioActual != null && idActual != null) {
                        usuarios.put(idActual, usuarioActual);
                    }
                    usuarioActual = null;
                    idActual = null;
                    continue;
                }

                if (usuarioActual == null) {
                    String[] datosUsuario = linea.split("\\|", 2);
                    if (datosUsuario.length == 2) {
                        idActual = datosUsuario[0];
                        usuarioActual = new Usuario(datosUsuario[1], idActual);
                    } else {
                        System.out.println("Formato incorrecto en usuario: " + linea);
                    }
                } else {
                    String[] datosPrestamo = linea.split(",", 3);
                    if (datosPrestamo.length == 3) {
                        usuarioActual.getPrestamos().add(datosPrestamo);
                    } else {
                        System.out.println("Formato incorrecto en préstamo: " + linea);
                    }
                }
            }

            if (usuarioActual != null && idActual != null) {
                usuarios.put(idActual, usuarioActual);
            }

            System.out.println("Usuarios cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }

        return usuarios;
    }
    
    //Metodos para guardas datos de las subclases Comic, Manga y NovelaGrafica respectivamente en archivos csv separados
    public void guardarComics(String nombreArchivo, List<Comic> comics) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Comic c : comics) {
                pw.println(String.format("%s,%s,%d,%s,%b",
                    c.getTitulo(), c.getAutor(), c.getCapitulo(), c.getSuperheroe(), c.isPrestado()));
            }
            System.out.println("Comics guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar comics: " + e.getMessage());
        }
    }
    
    public void guardarMangas(String nombreArchivo, List<Manga> mangas) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Manga m : mangas) {
                pw.println(String.format("%s,%s,%d,%s,%b",
                    m.getTitulo(), m.getAutor(), m.getTomo(), m.getCategoria(), m.isPrestado()));
            }
            System.out.println("Mangas guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar mangas: " + e.getMessage());
        }
    }
    
    public void guardarNovelasGraficas(String nombreArchivo, List<NovelaGrafica> novelas) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (NovelaGrafica n : novelas) {
                pw.println(String.format("%s,%s,%s,%b",
                    n.getTitulo(), n.getAutor(), n.getGenero(), n.isPrestado()));
            }
            System.out.println("Novelas gráficas guardadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar novelas gráficas: " + e.getMessage());
        }
    }
    
    //Metodos para cargar los datos de las subclases desde los archivos csv correspondientes
    public List<Comic> cargarComics(String nombreArchivo) {
        List<Comic> comics = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] atributos = linea.split(",", -1);
                if (atributos.length == 5) {
                    String titulo = atributos[0];
                    String autor = atributos[1];
                    int capitulo = Integer.parseInt(atributos[2]);
                    String superheroe = atributos[3];
                    boolean prestado = Boolean.parseBoolean(atributos[4]);
                    Comic comic = new Comic(titulo, autor, capitulo, superheroe);
                    if (prestado) comic.prestar();  // restaurar estado
                    comics.add(comic);
                } else {
                    System.out.println("Formato incorrecto en línea comic: " + linea);
                }
            }
            System.out.println("Comics cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar comics: " + e.getMessage());
        }
        return comics;
    }
    
    public List<Manga> cargarMangas(String nombreArchivo) {
        List<Manga> mangas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] atributos = linea.split(",", -1);
                if (atributos.length == 5) {
                    String titulo = atributos[0];
                    String autor = atributos[1];
                    int tomo = Integer.parseInt(atributos[2]);
                    String categoria = atributos[3];
                    boolean prestado = Boolean.parseBoolean(atributos[4]);
                    Manga manga = new Manga(titulo, autor, tomo, categoria);
                    if (prestado) manga.prestar();
                    mangas.add(manga);
                } else {
                    System.out.println("Formato incorrecto en línea manga: " + linea);
                }
            }
            System.out.println("Mangas cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar mangas: " + e.getMessage());
        }
        return mangas;
    }
    
    public List<NovelaGrafica> cargarNovelasGraficas(String nombreArchivo) {
        List<NovelaGrafica> novelas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] atributos = linea.split(",", -1);
                if (atributos.length == 4) {
                    String titulo = atributos[0];
                    String autor = atributos[1];
                    String genero = atributos[2];
                    boolean prestado = Boolean.parseBoolean(atributos[3]);
                    NovelaGrafica novela = new NovelaGrafica(titulo, autor, genero);
                    if (prestado) novela.prestar();
                    novelas.add(novela);
                } else {
                    System.out.println("Formato incorrecto en línea novela: " + linea);
                }
            }
            System.out.println("Novelas gráficas cargadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar novelas gráficas: " + e.getMessage());
        }
        return novelas;
    }
}