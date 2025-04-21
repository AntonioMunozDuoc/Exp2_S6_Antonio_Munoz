package com.mycompany.exp2_s6_antonio_munoz;

import java.util.Scanner;
import java.util.ArrayList;

public class Exp2_S6_Antonio_Munoz {
    
    //Constantes del algoritmo
    public static final int PrecioVip = 10000;
    public static final int PrecioPlatea = 7500;
    public static final int PrecioGeneral = 5000;
    
    //Variables a usar en todo el algoritmo
    public static Scanner scanner = new Scanner(System.in);
    public static  ArrayList<String[]> entradasReservadas = new ArrayList<>();
    public static  ArrayList<String[]> entradasCompradas = new ArrayList<>();
    public static  ArrayList<String[]> vip = new ArrayList<>();
    public static  ArrayList<String[]> platea = new ArrayList<>();
    public static  ArrayList<String[]> general = new ArrayList<>();
    public static int cantidadComprada = 0;
    public static boolean salida = false;
    
    //Metodo destinado a validar si el input del usuario es valido
    public static int esValido(int numeroOpciones){
        int opcion;
        while (true){
            if (scanner.hasNextInt()){
                opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion > 0 && opcion <= numeroOpciones) {
                    return opcion;
                }else{
                    System.out.println("Opción fuera de rango, intente nuevamente");
                }
            }else{
                System.out.println("Entrada no valida, intentelo nuevamente");
                scanner.nextLine();
            }
        }
    }
    //Generador de grafica de asientos
    public static void generarAsientos(ArrayList<String[]> seccion){
        String [] columnas  = {"   ", "|1|", "|2|", "|3|", "|4|", "|5|"};
        String [] filaA = {" A ", "|_|", "|_|", "|_|", "|_|", "|_|"};
        String [] filaB = {" B ", "|_|", "|_|", "|_|", "|_|", "|_|"};
        String [] filaC = {" C ", "|_|", "|_|", "|_|", "|_|", "|_|"};
        seccion.add(columnas.clone());
        seccion.add(filaA.clone());
        seccion.add(filaB.clone());
        seccion.add(filaC.clone());
    }
    //Imprime los asientos de una seccion
    public static void imprimirSeccion(ArrayList<String[]> seccion){
        for (int j = 0; j < 4; j++){
            for (int i = 0; i < 6; i++){
                System.out.print(seccion.get(j)[i]);
            }
            System.out.println("");
        }     
    }
    
    //Metodo del menu principal
    public static void menu (){
        //Primero generamos los asientos para las distintas secciones
        generarAsientos(vip);
        generarAsientos(platea);
        generarAsientos(general);
        System.out.println("Bienvenido al sistema de ventas de entradas del Teatro Moro");
        while (!salida){
           System.out.println("""
                              =========================================================
                              Menu Principal
                              1 - Reservar Entradas
                              2 - Comprar Entradas
                              3 - Modificar Entradas Compradas
                              4 - Imprimir Boleta
                              Ingrese el número de la opción que desea elegir
                              ========================================================="""); 
           
           int opcionMenu = esValido(4);
           
           switch (opcionMenu){
               case 1:
                   reservarEntrada();
                   break;
                   
               case 2:
                   comprarEntrada();
                   break;
                   
               case 3:
                   modificarEntrada();
                   break;
                
               case 4:
                   imprimirBoleta();
                   break;
       
           }
        }    
    }
    //Algoritmo de reserva de entrada
    public static void reservarEntrada(){
        System.out.println("Seleccione el tipo de entrada que quiere comprar");
        System.out.println("1- Vip $10000  2- Platea $7500  3- General $5000");
        int seccion = esValido(3);
        System.out.println("A continuacion le mostraremos una grafica con los asientos del teatro");
        System.out.println("Donde |_| corresponde a los asientos vacios y |X| corresponde a los asientos en uso");
        switch (seccion){
            case 1:
                imprimirSeccion(vip);
                escogerAsiento(vip, "VIP");
                break;
            case 2:
                imprimirSeccion(platea);
                escogerAsiento(platea, "Platea");
                break;
            case 3:
                imprimirSeccion(general);
                escogerAsiento(general, "General");
                break;
        } 
    }
    //Funcion que escoge asiento
    public static void escogerAsiento(ArrayList<String[]> seccion, String tipoEntrada){
        System.out.println("Por favor seleccione la fila que desea comprar");
        System.out.println("1- A   2- B   3- C");
        int fila = esValido(3);
        System.out.println("Ahora seleccione el número del asiento que desea comprar");
        int asiento = esValido(5);
        if ("|X|".equals(seccion.get(fila)[asiento]) ){
            System.out.println("El asiento seleccionado ya esta ocupado, intentelo nuevamente");
        }else{
            System.out.println("Usted eligio el asiento " + asiento + " de la fila " + seccion.get(fila)[0]);
            seccion.get(fila)[asiento] = "|X|";
            String[] entrada = {tipoEntrada, seccion.get(fila)[0], String.valueOf(asiento)};  
            entradasReservadas.add(entrada);
        } 
    }
    //Funcion de compra de entradas
    public static void comprarEntrada(){
        if (entradasReservadas.isEmpty()){
            System.out.println("Usted aun no a relizado ninguna reserva");
        }else{
            System.out.println("Usted tiene en su carrito de reservas las siguientes entradas");
            imprimirEntradas(entradasReservadas);
            System.out.println("Por un valor total de $" + calcularPrecioTotal(entradasReservadas));
            System.out.println("¿Desea realizar la compra de las entradas? o ¿Reiniciar su carrito de reservas?");
            System.out.println("1- Realizar compra   2- Salir");
            int respuesta = esValido(2);
            switch (respuesta){
                case 1:
                    for (int i = 0; i < entradasReservadas.size(); i++){
                        entradasCompradas.add(entradasReservadas.get(i).clone());
                    }
                    entradasReservadas.clear();
                    System.out.println("Se a realizado la compra de las entradas");
                    break;
                      
                case 2:
                    System.out.println("Se a cancelado la operación, será  redirigido al menu principal");
                    break;
            }    
        }
    }
    //Funcion de impresion de entradas
    public static void imprimirEntradas(ArrayList<String[]> reservas){
        for (int i = 0; i < reservas.size(); i++){
            System.out.println((i + 1) + "- " + "Sección: " + reservas.get(i)[0] + " Fila:" + reservas.get(i)[1] + "Asiento: " + reservas.get(i)[2]);
        }
    }
    //Calcula el precio total de las entradas en un ArrayList
    public static int calcularPrecioTotal(ArrayList<String[]> reservas){
        int precioTotal = 0;
        for (int i = 0; i < reservas.size(); i++){
            if ("VIP".equals(reservas.get(i)[0])){
                precioTotal += PrecioVip;
            }else if ("Platea".equals(reservas.get(i)[0])){
                precioTotal += PrecioPlatea;
            }else if ("General".equals(reservas.get(i)[0])){
                precioTotal += PrecioGeneral;
            }
        }
        return precioTotal;
    }
    //Modifica las entradas ya compradas
    public static void modificarEntrada(){
        if (entradasCompradas.isEmpty()){
            System.out.println("Usted aun no a relizado ninguna compra");
        }else{
            System.out.println("Usted a realizado la compra de las siguientes entradas");
            imprimirEntradas(entradasCompradas);
            System.out.println("¿Cual entrada desea eliminar?");
            System.out.println("Porfavor ingrese el numero correspondiente a la entrada que desea seleecionar");
            int respuesta = esValido(entradasCompradas.size());
            desmarcarAsiento(entradasCompradas.get(respuesta-1));
            entradasCompradas.remove((respuesta - 1));
            System.out.println("La entrada a sido eliminada de forma exitosa");
        }
    }
    //Desmarca un asiento usado y lo deja disponible
    public static void desmarcarAsiento(String[] entrada){
        int fila = 0;
        int asiento = Integer.parseInt(entrada[2]);
        if (" A ".equals(entrada[1])){
            fila = 1;
        }else if(" B ".equals(entrada[1])){
            fila = 2;
        }else if(" C ".equals(entrada[1])){
            fila = 3;
        }
        switch (entrada[0]){
            case "VIP":
                vip.get(fila)[asiento] = "|_|";
                break;
            case "Platea":
                platea.get(fila)[asiento] = "|_|";
                break;
            case "General":
                general.get(fila)[asiento] = "|_|";
                break;
        }
    }
    //Calcula el valor total de las entradas compradas por tipo
    public static int calcularTotalTipo(String tipo, ArrayList<String[]> compras ){
        int total = 0;
        int precio = 0;
        switch (tipo){
                    case "VIP":
                        precio = PrecioVip;
                        break;
                    case "Platea":
                        precio = PrecioPlatea;
                        break;
                    case "General":
                        precio = PrecioGeneral;
                        break;
                }
        for(int i = 0; i < compras.size(); i++){
            if (compras.get(i)[0].equals(tipo)){
                total += precio;
            }
        }
        return total;
    }
    //Calcula la cantidad de entradas de cada tipo
    public static int cantidadTipo(String tipo){
        int contador = 0;
        for (int i = 0; i < entradasCompradas.size(); i++){
            if (entradasCompradas.get(i)[0].equals(tipo)){
                contador ++;
            }
        }
        return contador;
    }
    //Imprimir Boleta
    public static void imprimirBoleta(){
        int ancho = 60;
        int totalPlatea = calcularTotalTipo("Platea", entradasCompradas);
        int totalGeneral = calcularTotalTipo("General", entradasCompradas);
        int totalVip = calcularTotalTipo("VIP",entradasCompradas);
        int total = calcularPrecioTotal(entradasCompradas);
        int contadorPlatea = cantidadTipo("Platea");
        int contadorGeneral = cantidadTipo("General");
        int contadorVip = cantidadTipo("VIP");
        
        if (entradasCompradas.isEmpty()){
            System.out.println("Aun no a realizado sus compras"); 
        }else{
            imprimirBorde (ancho);
            imprimirVacio(ancho);
            imprimirCentro("Teatro Moro", ancho);
            imprimirCentro("Avenida Duoc 1697", ancho);
            imprimirCentro("RUT 77777777777", ancho);
            if (totalVip != 0){
                imprimirIzquierda("Entradas compradas.....VIP x" + contadorVip, ancho);
                imprimirIzquierda("$ " + totalVip, ancho);
            }else if (totalGeneral != 0){
                imprimirIzquierda("Entradas compradas.....General x" + contadorGeneral, ancho);
                imprimirIzquierda("$ " + totalGeneral, ancho);
            }else if (totalPlatea != 0){
                imprimirIzquierda("Entradas compradas.....Platea x" + contadorPlatea, ancho);
                imprimirIzquierda("$ " + totalPlatea, ancho);
                
            }
            imprimirVacio(ancho);
            imprimirIzquierda("Total..... $ " + total, ancho);
            salida = true;  
        }
            
    }
    //Imprime un texto en el borde izquierdo
    public static void imprimirIzquierda(String texto, int ancho) {
        System.out.print("== ");
        System.out.print(texto);
        for (int i = 0; i < (ancho - 4) - texto.length() - 1; i++) {
            System.out.print(" ");
        }
        System.out.println("==");
    }
    //Imprime un texto en el centro
    public static void imprimirCentro(String texto, int ancho) {
        int espacioDisponible = ancho - 4;
        int espaciosAntes = (espacioDisponible - texto.length()) / 2;
        int espaciosDespues = espacioDisponible - espaciosAntes - texto.length();
        System.out.print("==");
        for (int i = 0; i < espaciosAntes; i++) {
            System.out.print(" ");
        }
        System.out.print(texto);
        for (int i = 0; i < espaciosDespues; i++) {
            System.out.print(" ");
        }
        System.out.println("==");
    }
    //Imprime una linea vacia en la boleta
    public static void imprimirVacio(int ancho){
        System.out.print("==");
        for(int j = 0; j < (ancho - 4); j++){
            System.out.print(" ");
        }
        System.out.println("==");        
    }
    //Imprime el borde superior e inferior de la boleta 
    public static void imprimirBorde(int ancho) {
        for (int i = 0; i < ancho; i++) {
            System.out.print("=");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        menu();
    }
}
