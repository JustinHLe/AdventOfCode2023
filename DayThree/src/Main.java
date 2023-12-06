import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static ArrayList<ArrayList<Integer>> directions = new ArrayList<>();

    public static void main(String[] args) {
        directions.add(new ArrayList<>(Arrays.asList(-1 , 0)));
        directions.add(new ArrayList<>(Arrays.asList(-1 , 1)));
        directions.add(new ArrayList<>(Arrays.asList(0 , 1)));
        directions.add(new ArrayList<>(Arrays.asList(1 , 1)));
        directions.add(new ArrayList<>(Arrays.asList(1 , 0)));
        directions.add(new ArrayList<>(Arrays.asList(1 , -1)));
        directions.add(new ArrayList<>(Arrays.asList(0 , -1)));
        directions.add(new ArrayList<>(Arrays.asList(-1 , -1)));

        part1();
        part2();
    }
    public static void part1() {
        int total = 0;
       File file = new File("./DayThree/part1.txt");
       ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
       int row = 0;
       try {
           Scanner scan = new Scanner(file);
           while (scan.hasNextLine()) {
               String line = scan.nextLine();
               matrix.add(row, new ArrayList<>(Arrays.asList(line.split(""))));
               row++;
           }
       } catch (Exception exception) {
           System.out.println(exception);
       }

       row = 0;
       int col = 0;
       ArrayList<ArrayList<Integer>> positionsOfCurrentDigit = new ArrayList<ArrayList<Integer>>();

       for (int i = row; i < matrix.size(); i++) {
           for (int j = col; j < matrix.get(i).size(); j++) {
               String element = matrix.get(i).get(j);
               while (isInteger(element)) {
                   positionsOfCurrentDigit.add(new ArrayList<>(Arrays.asList(i, j)));
                   j++;
                   if (j == matrix.get(i).size()) {
                       break;
                   }
                   element = matrix.get(i).get(j);
               }
               if (hasSymbol(positionsOfCurrentDigit, matrix)) {
                   String numString = "";
                   for (ArrayList<Integer> pos : positionsOfCurrentDigit) {
                       numString += matrix.get(pos.get(0)).get(pos.get(1));
                   }
                   total += Integer.parseInt(numString);
               };
               positionsOfCurrentDigit.clear();

           }
       }
        System.out.println(total);
    }

    public static void part2() {
        int total = 0;
        File file = new File("./DayThree/part1.txt");
        ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
        int row = 0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                matrix.add(row, new ArrayList<>(Arrays.asList(line.split(""))));
                row++;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        row = 0;
        int col = 0;
        ArrayList<ArrayList<Integer>> positionsOfCurrentDigit = new ArrayList<ArrayList<Integer>>();

        for (int i = row; i < matrix.size(); i++) {
            for (int j = col; j < matrix.get(i).size(); j++) {
                String element = matrix.get(i).get(j);
                while (isInteger(element)) {
                    positionsOfCurrentDigit.add(new ArrayList<>(Arrays.asList(i, j)));
                    j++;
                    if (j == matrix.get(i).size()) {
                        break;
                    }
                    element = matrix.get(i).get(j);
                }
                if (hasSymbol(positionsOfCurrentDigit, matrix)) {
                    String numString = "";
                    for (ArrayList<Integer> pos : positionsOfCurrentDigit) {
                        numString += matrix.get(pos.get(0)).get(pos.get(1));
                    }
                    total += Integer.parseInt(numString);
                };
                positionsOfCurrentDigit.clear();

            }
        }
        System.out.println(total);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    public static boolean hasSymbol(ArrayList<ArrayList<Integer>> positionsOfCurrentDigit,
                                       ArrayList<ArrayList<String>> matrix) {
        boolean hasSymbol = false;
        for (int i = 0; i < positionsOfCurrentDigit.size(); i++) {
            int matrixRow = positionsOfCurrentDigit.get(i).get(0);
            int matrixCol = positionsOfCurrentDigit.get(i).get(1);
            for (ArrayList<Integer> dir : directions) {
                if (matrixRow + dir.get(0) < 0 || matrixRow + dir.get(0) >= matrix.size() ||
                matrixCol + dir.get(1) < 0 || matrixCol + dir.get(1) >= matrix.get(0).size()) {
                    continue;
                }

                if (!matrix.get(matrixRow + dir.get(0)).get(matrixCol + dir.get(1)).equals(".") &&
                        !isInteger(matrix.get(matrixRow + dir.get(0)).get(matrixCol + dir.get(1)))) {
                    hasSymbol = true;
                }
            }

        }
        return hasSymbol;
    }

    public static boolean nextToGear(ArrayList<ArrayList<Integer>> positionsOfCurrentDigit,
                                    ArrayList<ArrayList<String>> matrix) {
        boolean hasSymbol = false;
        for (int i = 0; i < positionsOfCurrentDigit.size(); i++) {
            int matrixRow = positionsOfCurrentDigit.get(i).get(0);
            int matrixCol = positionsOfCurrentDigit.get(i).get(1);
            for (ArrayList<Integer> dir : directions) {
                if (matrixRow + dir.get(0) < 0 || matrixRow + dir.get(0) >= matrix.size() ||
                        matrixCol + dir.get(1) < 0 || matrixCol + dir.get(1) >= matrix.get(0).size()) {
                    continue;
                }

                if (!matrix.get(matrixRow + dir.get(0)).get(matrixCol + dir.get(1)).equals(".") &&
                        !isInteger(matrix.get(matrixRow + dir.get(0)).get(matrixCol + dir.get(1)))) {
                    hasSymbol = true;
                }
            }

        }
        return hasSymbol;
    }
}

