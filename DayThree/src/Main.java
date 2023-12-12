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

//        part1();
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
        File file = new File("./DayThree/part2.txt");
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
        for (int i = row; i < matrix.size(); i++) {
            for (int j = col; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j).equals("*")) {
                    ArrayList<Integer> parts = findSurroundingParts(i, j, matrix);
                    for(Integer ints : parts) {
                        System.out.println(ints);
                    }
                    if (parts.size() == 2) {
                        total += parts.get(0) * parts.get(1);
                    }
                }
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

    public static ArrayList<Integer> findSurroundingParts(int row, int col, ArrayList<ArrayList<String>> matrix) {
        ArrayList<Integer> parts = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> visited = new HashMap<>();
        for (ArrayList<Integer> dir : directions) {
            if (row + dir.get(0) < 0 || row + dir.get(0) >= matrix.size() ||
            col + dir.get(1) < 0 || col + dir.get(1) >= matrix.get(0).size()) {
                continue;
            }

            if (isInteger(matrix.get(row + dir.get(0)).get(col + dir.get(1)))) {
                int newRow = row + dir.get(0);
                int newCol = col + dir.get(1);
                Integer num = buildInteger(matrix, newRow, newCol, row, col, visited);
                parts.add(num);
            }
        }
        while (parts.remove(null));
        return parts;
    }

    public static Integer buildInteger(ArrayList<ArrayList<String>> matrix, int row, int col, int oldRow, int oldCol,
                                       HashMap<Integer, ArrayList<Integer>> visited) {
        String num = "";

        if (row == oldRow) {
            int j = col;
            if (col == oldCol - 1) {
                while (j >= 0 && isInteger(matrix.get(row).get(j))) {
                    num = matrix.get(row).get(j) + num;
                    j--;
                }
            }
            else if (col == oldCol + 1) {
                while (j < matrix.get(0).size() && isInteger(matrix.get(row).get(j))) {
                    num = num + matrix.get(row).get(j);
                    j++;
                }
            }
        } else {
            int i = row;
            int j = col;
            int startIndex = 0;
            int endIndex = 0;
            while (j >= 0 && isInteger(matrix.get(i).get(j))) {
                startIndex = j;
                j--;
            }
            j = col;
            while (j < matrix.get(0).size() && isInteger(matrix.get(i).get(j))) {
                endIndex = j;
                j++;
            }
            if (visited.isEmpty()) {
                visited.put(row, new ArrayList<>(Arrays.asList(startIndex, endIndex)));
            } else {
                if (visited.containsKey(row) && visited.get(row).containsAll(Arrays.asList(startIndex, endIndex))) {
                    return null;
                } else {
                    visited.put(row, new ArrayList<>(Arrays.asList(startIndex, endIndex)));
                }
            }
            for (int start = startIndex; start <= endIndex; start++) {
                num += matrix.get(row).get(start);
            }
        }
        return Integer.parseInt(num);
    }
}

