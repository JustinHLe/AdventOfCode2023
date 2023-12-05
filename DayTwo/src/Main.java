import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        part1();
          part2();
    }
    public static void part1() {
        // only 12 red cubes, 13 green cubes, and 14 blue cubes
        File file = new File("C:\\Users\\Justin\\Java\\AdventOfCode2023\\DayTwo\\part1.txt");
        int total = 0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] game = line.split(":");
                String[] cubes = game[1].split(";");
                String gameNumber = game[0].split(" ")[1];
                boolean isValid = true;

                for (String s : cubes) {
                    String trimmedRoll = s.trim();
                    String[] cube = trimmedRoll.split(",");
                    HashMap<String, Integer> set = new HashMap<>();
                    for (String roll : cube) {
                        String color = roll.trim().split(" ")[1];
                        int number = Integer.parseInt(roll.trim().split(" ")[0]);
                        set.put(color, number);
                    }
                    if (set.get("red") == null) {
                        set.put("red", 0);
                    }
                    if (set.get("green") == null) {
                        set.put("green", 0);
                    }
                    if (set.get("blue") == null) {
                        set.put("blue", 0);
                    }
                    if (set.get("red") > 12 || set.get("green") > 13 || set.get("blue") > 14) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    total += Integer.parseInt(gameNumber);
                }
            }
            System.out.println(total);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static void part2() {
        File file = new File("C:\\Users\\Justin\\Java\\AdventOfCode2023\\DayTwo\\part2.txt");
        int total = 0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] game = line.split(":");
                String[] cubes = game[1].split(";");
                String gameNumber = game[0].split(" ")[1];
                Map<String, Integer> set = new HashMap<>(Map.of("red", 0, "blue", 0, "green", 0));
                int product = 1;
                for (String s : cubes) {
                    String trimmedRoll = s.trim();
                    String[] cube = trimmedRoll.split(",");
                    for (String roll : cube) {
                        String color = roll.trim().split(" ")[1];
                        int number = Integer.parseInt(roll.trim().split(" ")[0]);
                        if(number > set.get(color)) {
                            set.put(color, number);
                        }
                    }
                }
                for (String key : set.keySet()) {
                    product = product * set.get(key);
                }
                total += product;
                System.out.println(total);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
}

