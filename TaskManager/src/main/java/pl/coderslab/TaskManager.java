package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {


    public static void List() {
        for(int i = 0; i < finalarray().length; i++){
            System.out.print(i+1+" : ");
            for(int j = 0; j < finalarray()[i].length; j++){
                System.out.print(finalarray()[i][j] + " ");
            }

            System.out.println();
        }
    }


    public static String [][] finalarray() {


        File file = new File("tasks.csv");

        int count2 = 0;
        try {
            Scanner scan2 = new Scanner(file);
            while (scan2.hasNextLine()) {
                scan2.nextLine();
                count2++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("brak pliku");
        }

        String[][] tasksArray = new String[count2][3];
        int count = 0;

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String text = scan.nextLine();
                String[] lines = text.split(",");
                for (int j = 0; j < lines.length; j++) {
                    tasksArray[count][j] = lines[j];
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("brak pliku");
        }
        return tasksArray;
    }


        public static void menu () {

        Scanner scan = new Scanner(System.in);
        StringBuilder sb2 = new StringBuilder();
        String input = "";

        while (!input.equals("exit")) {
            System.out.println("""
                    wybierz właściwą opcję
                    add
                    remove
                    list
                    exit
                    """);
            input = scan.nextLine();
            switch (input) {
                case "list":
                    List();
                    break;
                // other options
                default:
                    System.out.println("Please select a correct option.");
            }

//        StringBuilder sb = new StringBuilder();
//        String text = sb.toString();
//        Path path1 = Paths.get(fileName);
//        List<String> outList = new ArrayList<>();
//
//        outList.add(text);
//
//        try {
//            Files.write(path1, outList);
//        } catch (IOException ex) {
//            System.out.println("Nie można zapisać pliku.");
//        }
        }
    }


    public static void main(String[] args) {
// List();
      //  System.out.println(Arrays.deepToString(finalarray()));
        menu();

    }
}
