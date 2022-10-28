package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class loading {

            public static void main(String[] args) {
                File file = new File("tasks.csv");
                String[][] tasksArray = new String[0][0];

                try {
                    Scanner scan = new Scanner(file);
                    while (scan.hasNextLine()) {

                        tasksArray=Arrays.copyOf(tasksArray,tasksArray.length+1);
                        String text = scan.nextLine();
                        String[] lines = text.split(",");


                        for (int j = 0; j < lines.length; j++) {
                            tasksArray[tasksArray.length-1] = lines;
                        }
                    }

                    } catch (FileNotFoundException e) {
                        System.out.println("brak pliku");
                    }
            }


    public static void main2 (String[] args) {
        //List();
        //  System.out.println(Arrays.deepToString(finalarray()));
//        menu();
//Add();
     //   System.out.println(Arrays.toString(ArraysCopySample));
    }
}