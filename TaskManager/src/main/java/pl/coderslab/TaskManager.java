package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskManager {



    public static void List() {
        for(int i = 0; i < finalarray().length; i++){
            System.out.print(i+1+" : ");
            for(int j = 0; j < finalarray()[i].length; j++){
                System.out.print(finalarray()[i][j] + " | ");
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
                    case "add":
                        Add();
                        break;
                    case "remove":
                        remove();
                        break;
                    // other options
                    default:
                        System.out.println("Please select a correct option.");
                }
            }
        }
                public static void Add () {
                    StringBuilder sb = new StringBuilder();
                    Path path1 = Paths.get("tasks.csv");


                    System.out.println("Please add task description");
                    Scanner scanner =new Scanner(System.in);
                    String desc = scanner.nextLine();

                    System.out.println("Please add task due date - \"yyyy/MM/dd\"");

                    String date = scanner.next();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date2=null;
                    try {
                        date2 = dateFormat.parse(date);
                    } catch (ParseException e) {

                        System.out.println("wrong date, write again!");
                    }
                    String strDate= dateFormat.format(date2);
                    System.out.println(strDate);

                    System.out.println("Is your task important: true/false");
                   String impo = scanner.next();
                    while (!impo.equals("true")  && !impo.equals("false")){
                        System.out.println("podaj właściwą odpowiedź");
                        impo = scanner.next();
                    }
sb.append(desc).append(", ").append(strDate).append(", ").append(impo).append("\n");
                    System.out.println(desc + " | " + strDate + " | " + impo);
                    System.out.println(sb);
                    try {
                        Files.writeString(path1, sb,StandardOpenOption.APPEND);
                    } catch (IOException ex) {
                        System.out.println("Nie można zapisać pliku.");
                    }
                }



        public static void remove () {
        List();
            System.out.println("choose a number from list to delete task"); // tworzymy tablicę
            Scanner scanner =new Scanner(System.in);
            int index = scanner.nextInt();
            StringBuilder sb = new StringBuilder();
            Path path1 = Paths.get("tasks.csv");
          String[][] finalar = ArrayUtils.remove(finalarray(), index-1);
            // System.out.println(Arrays.deepToString(finalar));
            String finale = finalar.toString();

            System.out.println(sb);
//            sb.append(Arrays.deepToString(finalar).replaceAll("],", "\n").replaceAll("" ,""));
//            try {
//                Files.writeString(path1, sb);
//            } catch (IOException ex) {
//                System.out.println("Nie można zapisać pliku.");
//            }
        }



    public static void main(String[] args) {
 List();
      //  System.out.println(Arrays.deepToString(finalarray()));
//        menu();
//Add();
  //      remove();
    }
}
