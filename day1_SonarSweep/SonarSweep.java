package day1_SonarSweep;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class SonarSweep {

    public static ArrayList<Integer> readFile(String path) {
        ArrayList<Integer> output = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                output.add(Integer.parseInt(data.trim()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return output;
    }

    public static int findInc(ArrayList<Integer> input) {
        int numberOfInc = 0;
        for (int i = 0; i < input.size() - 1; i++) {
            if (input.get(i) < input.get(i + 1)) {
                numberOfInc++;
            }
        }
        return numberOfInc;
    }

    public static int findNrOfSums(ArrayList<Integer> input) {
        int numberOfSums = 0;
        for (int i = 0; i < input.size() - 3; i++) {
            if ((input.get(i) + input.get(i + 1) + input.get(i + 2)) < (input.get(i + 1) + input.get(i + 2) + input.get(i + 3))) {
                numberOfSums++;
            }
        }
        return numberOfSums;
    }

    public static void main(String[] args) {
        System.out.println("Number of increases: " + findInc(readFile("day1_SonarSweep/input.txt")));
        System.out.println("Number of sum increases: " + findNrOfSums(readFile("day1_SonarSweep/input.txt")));

    }
}