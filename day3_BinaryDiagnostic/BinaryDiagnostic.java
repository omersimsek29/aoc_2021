package day3_BinaryDiagnostic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryDiagnostic {

    public static ArrayList<String> readFile(String path) {
        ArrayList<String> output = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                output.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return output;
    }

    public static void calculateGammaEpsilon(ArrayList<String> input) {
        int oneBit = 0;
        int zeroBit = 0;
        ArrayList<String> gamma = new ArrayList<>();
        ArrayList<String> epsilon;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < input.size(); j++) {
                if (input.get(j).charAt(i) == '1') {
                    oneBit++;
                } else {
                    zeroBit++;
                }
            }
            if (oneBit < zeroBit) {
                gamma.add("0");
            } else {
                gamma.add("1");
            }
            oneBit = 0;
            zeroBit = 0;
        }
        epsilon = convertOpposite(gamma);
        System.out.println("Gamma is:" + convertToDecimal(gamma));
        System.out.println("Epsilon is: " + convertToDecimal(epsilon));
        System.out.println("The answer is: " + convertToDecimal(gamma) * convertToDecimal(epsilon));
    }

    public static ArrayList<String> convertOpposite(ArrayList<String> input) {
        ArrayList<String> newList = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {

            if (input.get(i).equals("1")) {
                newList.add("0");
            } else {
                newList.add("1");
            }
        }
        return newList;
    }

    public static int convertToDecimal(ArrayList<String> input) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < input.size(); i++) {
            sb.append(input.get(i));
        }
        String str = sb.toString();
        return Integer.parseInt(str, 2);
    }

    public static ArrayList<String> newInputList(ArrayList<String> input, String value, int index) {
        ArrayList<String> list = new ArrayList<>();
        char c = value.charAt(0);
        for (int j = 0; j < input.size(); j++) {
            char x = input.get(j).charAt(index);
            if (x == c) {
                list.add(input.get(j));
            }
        }
        return list;
    }

    public static ArrayList<String> oxygenGeneratorRating(ArrayList<String> input) {
        int oneBit = 0;
        int zeroBit = 0;
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> input2 = input;

        for (int i = 0; i < 12; i++) {
            for (String s : input2) {
                if (s.charAt(i) == '1') {
                    oneBit++;
                } else {
                    zeroBit++;
                }
            }
            if (oneBit < zeroBit) {
                list.add("0");
            } else if (oneBit > zeroBit) {
                list.add("1");
            } else {
                list.add("1");
            }
            oneBit = 0;
            zeroBit = 0;
            if (input2.size() != 1) {
                input2 = newInputList(input2, list.get(list.size() - 1), i);
            }
        }
        System.out.println("Oxygen generator rating: " + input2);
        return input2;
    }

    public static ArrayList<String> scrubberRating(ArrayList<String> input) {
        int oneBit = 0;
        int zeroBit = 0;
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> input2 = input;

        for (int i = 0; i < 12; i++) {
            for (String s : input2) {
                if (s.charAt(i) == '1') {
                    oneBit++;
                } else {
                    zeroBit++;
                }
            }
            if (oneBit < zeroBit) {
                list.add("1");
            } else if (oneBit > zeroBit) {
                list.add("0");
            } else {
                list.add("0");
            }
            oneBit = 0;
            zeroBit = 0;
            if (input2.size() != 1) {
                input2 = newInputList(input2, list.get(list.size() - 1), i);
            }
        }
        System.out.println("CO2 Scrubber Rating: " + input2);
        return input2;
    }

    public static void main(String[] args) {
        ArrayList<String> list = readFile("day3_BinaryDiagnostic/input.txt");
        calculateGammaEpsilon(list);
        System.out.println("The answer is " + convertToDecimal(oxygenGeneratorRating(list)) * convertToDecimal(scrubberRating(list)));
    }
}
