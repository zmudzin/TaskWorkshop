package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskManager {
    public static void main(String[] args) throws IOException{
        menu();
        System.out.println("\n"+ConsoleColors.RED + "bye,bye");
    }

    public static void exit() {;
        System.out.println("\n" + ConsoleColors.RED + "bye,bye");
        System.exit(1);
    }

    public static void menu() throws IOException {

        System.out.println(ConsoleColors.BLUE + "Please select an option:\n" + ConsoleColors.RESET +
                "add\n" +
                "remove\n" +
                "list\n" +
                ConsoleColors.RED + "exit\n" + ConsoleColors.RESET);


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        while (true) {
            switch (input) {
                case "list" -> {}
                case "add" -> add();
                case "remove" -> remove();
                case "exit" -> exit();
                default -> System.out.println(ConsoleColors.RED + "Please select a correct option." + ConsoleColors.RESET);
            }
            list();
            menu();
        }
    }
    public static String[][] tasksArray() {
        File file = new File("/home/istredd/WorkshopTask/TaskManager/tasks.csv");
        String[][] tasksArray = new String[0][0];

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {

                tasksArray = Arrays.copyOf(tasksArray, tasksArray.length + 1);
                String text = scan.nextLine();
                String[] lines = text.split(",");
                tasksArray[tasksArray.length - 1] = lines;
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file exception");
        }
        return tasksArray;
    }

    public static void list() {
        for (int i = 0; i < tasksArray().length; i++) {
            System.out.print(i + 1 + " : ");
            for (int j = 0; j < tasksArray()[i].length; j++) {
                System.out.print(tasksArray()[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void add() {
        StringBuilder sb = new StringBuilder();
        Path path1 = Paths.get("/home/istredd/WorkshopTask/TaskManager/tasks.csv");
        System.out.println("Please add task description");
        Scanner scanner = new Scanner(System.in);
        String desc = scanner.nextLine();

        System.out.println(ConsoleColors.BLUE + "Please add task due date - \"yyyy-MM-dd\"" + ConsoleColors.RESET);
        System.out.println("Write year - \"yyyy\"");
        String date = scanner.nextLine();
        while (!NumberUtils.isParsable(date)) {
            System.out.println("It is not valid year:");
            date = scanner.nextLine();
        }

        System.out.println("Write month - \"MM\"");
        String date2 = scanner.nextLine();
        while (!NumberUtils.isParsable(date2)) {
            System.out.println("It is not valid month:");
            date2 = scanner.nextLine();
        }

        System.out.println("Write day - \"dd\"");
        String date3 = scanner.nextLine();
        while (!NumberUtils.isParsable(date3)) {
            System.out.println("It is not valid day:");
            date3 = scanner.nextLine();
        }

        String date4 = date + "-" + date2 + "-" + date3;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date5 = null;
        try {
            date5 = dateFormat.parse(date4);
        } catch (ParseException e) {
            System.out.println("wrong date, write again!");
        }

        String strDate = dateFormat.format(date5);
        System.out.println(strDate);
        System.out.println("Is your task important: true/false");
        String important = scanner.next();
        while (!important.equals("true") && !important.equals("false")) {
            System.out.println("wrong answer");
            important = scanner.next();
        }

        sb.append(desc).append(", ").append(strDate).append(", ").append(important).append("\n");
        System.out.println(ConsoleColors.RED + "New task added to list:" + "\n" + ConsoleColors.YELLOW + desc + " | " + strDate + " | " + important + "\n" + ConsoleColors.RESET);
        try {
            Files.writeString(path1, sb, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Cant write to file");
        }
    }
    public static void remove() {
        list();
        System.out.println(ConsoleColors.YELLOW + "choose a number from list to delete task");
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int index = scanner.nextInt();
        while (index > tasksArray().length || index <=0 ) {
            System.out.println("choose a valid number from list to delete task");
            index = scanner.nextInt();
        }
        Path path1 = Paths.get("/home/istredd/WorkshopTask/TaskManager/tasks.csv");
        String[][] finalArr = ArrayUtils.remove(tasksArray(), index - 1);

        for (String[] strings : finalArr) {
            for (int j = 0; j < strings.length; j++) {
                sb.append(strings[j]).append(",");
                if (j >= 2) {
                    sb.delete(sb.length() - 1, sb.length()).append("\n");
                }
                try {
                    Files.writeString(path1, sb);
                } catch (IOException ex) {
                    System.out.println("Cant write to file");
                }
            }
        }
        System.out.println(ConsoleColors.RED + "removed task from index: " + index + "\n" + ConsoleColors.RESET + "Active Tasks:");
    }
}


