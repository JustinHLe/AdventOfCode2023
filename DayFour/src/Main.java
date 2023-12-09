import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        File file = new File("C:\\Users\\Justin\\Java\\AdventOfCode2023\\DayFour\\part1.txt");
        Scanner scan = new Scanner(file);
        int result = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parsedWinningAndGuessedNumbers = line.split(":")[1].split("\\|");
            String[] winningNumbersString = parsedWinningAndGuessedNumbers[0].split(",")[0].trim().split(" ");
            String[] guessedNumbersString = parsedWinningAndGuessedNumbers[1].split(",")[0].trim().split(" ");
            Set<Integer> winningNumbers = new HashSet<>();
            Set<Integer> guessedNumbers = new HashSet<>();

            for (String num : winningNumbersString) {
                if (!num.isEmpty() || !num.isBlank()) {
                    winningNumbers.add(Integer.parseInt(num));
                }
            }

            for (String num : guessedNumbersString) {
                if (!num.isEmpty() || !num.isBlank()) {
                    guessedNumbers.add(Integer.parseInt(num));
                }
            }

            int matches = 0;
            for (Integer num : guessedNumbers) {
                if (winningNumbers.contains(num)) {
                    matches++;
                }
            }
            int exponent = matches - 1;

            result += Math.pow(2, exponent);
        }
        System.out.println(result);
    }

    public static void part2() throws FileNotFoundException {
        File file = new File("C:\\Users\\Justin\\Java\\AdventOfCode2023\\DayFour\\part2.txt");
        Scanner scan = new Scanner(file);
        int cards = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] cardNum = line.split(":")[0].split(" ");
            map.put(Integer.parseInt(cardNum[cardNum.length - 1]), 1);
        }

        Scanner scan2 = new Scanner(file);
        while (scan2.hasNextLine()) {
            String line = scan2.nextLine();

            String[] cardNum = line.split(":")[0].split(" ");
            int currentCard = Integer.parseInt(cardNum[cardNum.length - 1]);
            String[] parsedWinningAndGuessedNumbers = line.split(":")[1].split("\\|");
            String[] winningNumbersString = parsedWinningAndGuessedNumbers[0].split(",")[0].trim().split(" ");
            String[] guessedNumbersString = parsedWinningAndGuessedNumbers[1].split(",")[0].trim().split(" ");
            Set<Integer> winningNumbers = new HashSet<>();
            Set<Integer> guessedNumbers = new HashSet<>();

            for (String num : winningNumbersString) {
                if (!num.isEmpty() || !num.isBlank()) {
                    winningNumbers.add(Integer.parseInt(num));
                }
            }

            for (String num : guessedNumbersString) {
                if (!num.isEmpty() || !num.isBlank()) {
                    guessedNumbers.add(Integer.parseInt(num));
                }
            }

            int matches = 0;
            for (Integer num : guessedNumbers) {
                if (winningNumbers.contains(num)) {
                    matches++;
                }
            }

            if (matches > 0) {
                int endIndex = matches + currentCard;
                int beginIndex = currentCard;
                ArrayList<Integer> rangeOfCards = new ArrayList<>();
                for(int i = beginIndex + 1; i <= endIndex; i++){
                    rangeOfCards.add(i);
                }

                for (Integer card : rangeOfCards) {
                    map.put(card, map.get(card) + map.get(currentCard));
                }
            }
        }
        for (Integer key : map.keySet()) {
            cards += map.get(key);
        }
        System.out.println(cards);
    }
}