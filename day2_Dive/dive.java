package day2_Dive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class dive {

    public static class coordinates {
        String direction;
        int steps;

        public coordinates(String direction, int steps) {
            this.direction = direction;
            this.steps = steps;
        }
    }

    public static ArrayList<coordinates> readFile(String path) {
        ArrayList<coordinates> output = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("\\s+");
                output.add(new coordinates(parts[0], Integer.parseInt(parts[1])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return output;
    }

    public static void findPosition(ArrayList<coordinates> input) {
        int horizontal = 0;
        int depth = 0;

        for (dive.coordinates coordinates : input) {
            switch (coordinates.direction) {
                case "down" -> depth = depth + coordinates.steps;
                case "up" -> depth = depth - coordinates.steps;
                case "forward" -> horizontal = horizontal + coordinates.steps;
            }
        }
        System.out.println("The horizontal is: " + horizontal);
        System.out.println("The depth is: " + depth);
        System.out.println("The answer " + horizontal * depth);
    }

    public static void findPositionAndAim(ArrayList<coordinates> input) {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (dive.coordinates coordinates : input) {
            switch (coordinates.direction) {
                case "down" -> aim = aim + coordinates.steps;
                case "up" -> aim = aim - coordinates.steps;
                case "forward" -> {
                    horizontal = horizontal + coordinates.steps;
                    if (aim != 0) {
                        depth = depth + (aim * coordinates.steps);
                    }
                }
            }
        }
        System.out.println("PART TWO: ");
        System.out.println("The horizontal is: " + horizontal);
        System.out.println("The depth is: " + depth);
        System.out.println("The answer " + horizontal * depth);
    }

    public static void main(String[] args) {
        findPosition(readFile("day2_Dive/input.txt"));
        findPositionAndAim(readFile("day2_Dive/input.txt"));
    }
}
