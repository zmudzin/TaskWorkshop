package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    public static void readFromFile(String fileName) {


        Path filePath = Paths.get(fileName);
        StringBuilder sb = new StringBuilder();
        try {
            for (String line : Files.readAllLines(filePath)){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("""
                wybierz właściwą opcję
                add
                remove
                list
                exit
                """);
        String input = "";

        while (!input.equals("exit")) {
            input = scan.nextLine();
            sb.append(input).append(" ");
            System.out.println("""
                    wybierz właściwą opcję
                    add
                    remove
                    list
                    exit
                    """);
            sb.delete(sb.length()-input.length()-1,sb.length() );
        }



        String text = sb.toString();
        Path path1 = Paths.get(fileName);
        List<String> outList = new ArrayList<>();

        outList.add(text);

        try {
            Files.write(path1, outList);
        } catch (IOException ex) {
            System.out.println("Nie można zapisać pliku.");
        }

    }

    public static void main(String[] args) {

        readFromFile("tasks.csv");
    }
}