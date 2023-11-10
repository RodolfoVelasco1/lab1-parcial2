//Alumno: Rodolfo Nicolás Velasco Fessler
//Repositorio de GitHub: https://github.com/RodolfoVelasco1/lab1-parcial2

//Parcial 2

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido, Magneto.");
        while(true){
            System.out.println("¿Qué operación desea realizar?");
            System.out.println("1. Agregar ADN.");
            System.out.println("2. Salir.");
            System.out.print("Respuesta: ");
            String answer = sc.nextLine();
            if (answer.equals("1")){
                String [] dna = new String [6];
                dna = add_dna(dna);
                print_dna(dna);
                boolean mutant = is_mutant(dna);
                if (mutant == true){
                    System.out.println("¡Es un mutante!");
                } else {
                    System.out.println("No es un mutante.");
                }
            } else if (answer.equals("2")){
                System.out.println("Saliendo del programa...");
                System.out.println("Gracias por usar este programa.");
                System.out.println("Hasta luego.");
                break;
            } else {
                System.out.println("Error: Respuesta inválida.");
                System.out.println("Inténtelo de nuevo.");
            }
        }
    }

    public static String [] add_dna(String [] dna){
        Scanner sc = new Scanner(System.in);
        System.out.println("AGREGAR ADN.");
        System.out.println("El ADN sólo contiene las letras: A, T, C y G");
        for (int i = 0; i < 6; i++){
            while(true){
                System.out.println("Ingrese las 6 letras del ADN de la fila #" + (i + 1) +": ");
                System.out.print("Respuesta: ");
                String answer1 = sc.nextLine();
                answer1 = answer1.toUpperCase();
                if (answer1.matches("[ATGC]{6}")){
                    dna[i] = answer1;
                    break;
                } else {
                    System.out.println("Error: longitud o caracteres incorrectos.");
                    System.out.println("Inténtelo de nuevo.");
                }
            }
        }
        System.out.println("ADN GUARDADO");
        return dna;
    }

    public static void print_dna(String [] dna){
        System.out.println("El ADN ingresado:");
        for (int i = 0; i < 6; i++){
            System.out.println(dna[i]);
        }
    }

    public static boolean is_mutant(String [] dna){
        int sequence = 0;
        sequence = sequence + vertical_check(dna, 0);
        sequence = sequence + horizontal_check(dna, 0);
        sequence = sequence + diagonal_sequence(dna, 0, 2, 0);
        sequence = sequence + diagonal_sequence(dna, 0, 0, 2);
        sequence = sequence + diagonal_sequence(dna, 0, 1,0);
        sequence = sequence + diagonal_sequence(dna, 0, 0,1);
        sequence = sequence + diagonal_sequence(dna, 0, 0, 0);
        sequence = sequence + reverse_diagonal_sequence(dna, 0, 2, 5);
        sequence = sequence + reverse_diagonal_sequence(dna, 0, 0, 3);
        sequence = sequence + reverse_diagonal_sequence(dna, 0, 1, 5);
        sequence = sequence + reverse_diagonal_sequence(dna, 0, 0, 4);
        sequence = sequence + reverse_diagonal_sequence(dna, 0, 0, 5);
        System.out.println("Cantidad de secuencias de cuatro letras iguales:" + sequence);
        if (sequence > 1){
            return true;
        } else {
            return false;
        }
    }

    public static int vertical_check(String [] dna,      int sequence){
        for (int i = 0; i <6; i++) {
            int similar_letter = 1;
            for (int j = 0; j<5; j++){
                if (dna[j].charAt(i) == dna[j+1].charAt(i)){
                    similar_letter = similar_letter + 1;
                    if (similar_letter == 4){
                        sequence = sequence + 1;
                        similar_letter = 1;
                        break;
                    }
                } else {
                    similar_letter = 1;
                }
            }
        }
        return sequence;
    }

    public static int horizontal_check(String [] dna, int sequence){
        for (int i = 0; i<6; i++){
            int similar_letter = 1;
            for (int j = 0; j < 5; j++){
                if (dna[i].charAt(j) == dna[i].charAt(j+1)){
                    similar_letter = similar_letter + 1;
                    if (similar_letter == 4) {
                        sequence = sequence + 1;
                        similar_letter = 1;
                        break;
                    }
                } else {
                    similar_letter = 1;
                }
            }
        }
        return sequence;
    }

    public static int diagonal_sequence(String [] dna, int sequence, int row, int column){
        int similar_letter = 1;
        while (row<5 & column <5){
            if (dna[row].charAt(column) == dna[row+1].charAt(column+1)){
                similar_letter = similar_letter + 1;
                if (similar_letter == 4){
                    sequence = sequence + 1;
                    break;
                }
            } else {
                similar_letter = 1;
            }
            row = row + 1;
            column = column + 1;
        }
        return sequence;
    }

    public static int reverse_diagonal_sequence(String [] dna, int sequence, int row, int column){
        int similar_letter = 1;
        while (row<5 & column>0){
            if (dna[row].charAt(column) == dna[row+1].charAt(column-1)){
                similar_letter = similar_letter + 1;
                if (similar_letter == 4){
                    sequence = sequence + 1;
                    break;
                }
            } else {
                similar_letter = 1;
            }
            row = row + 1;
            column = column - 1;
        }
        return sequence;
    }
}


/*
---CASOS DE PRUEBA---
--- Caso No Mutante ---
dna = [
    "ATGCCA",
    "CGTGGT",
    "TCTATG",
    "GAATTG",
    "TCACAT",
    "TGGTCA"]

--- Caso Mutante ---
dna = [
    "ATGCCA",
    "CGGGGT",
    "TCTATG",
    "TACTTG",
    "TCACAT",
    "TGGTCA"]

 */