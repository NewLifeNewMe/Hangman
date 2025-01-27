import java.util.*;

public class Main {

    private static int MAX_MISTAKE_COUNT = 7;
    private static ArrayList<String> dictionary = new ArrayList<String>(Arrays.asList("abruptly", "lengths", "thumbscrew", "unworthy", "phlegm"));
    private static Random random = new Random();
    private static String randomWord = "";
    private static char[] guessArray;
    private static int mistakeCount = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean GAME_IS_FINISHED = false;

    public static void main(String[] args) {
        while(!GAME_IS_FINISHED) {
            menu();
        }
    }

    public static void menu() {
        System.out.println("Начать новую игру.(Y)");
        System.out.println("Закончить игру.(N)");
        String c = scanner.nextLine().toUpperCase();
        if (Objects.equals(c, "Y")) {
            startGame();
        } if(Objects.equals(c, "N")) {
            GAME_IS_FINISHED = true;
        }
    }

    public static void startGame() {
        mistakeCount = 0;
        randomWord = getRandomWord();
        guessArray = new char[randomWord.length()];
        System.out.println(randomWord);
        for (int i = 0; i < randomWord.length(); i++) {
            guessArray[i] = '_';
            System.out.print(guessArray[i]);
        }
        gameLoop(randomWord, guessArray);
    }

    public static void gameLoop(String guessWord, char[] guessArray) {
        boolean isGuessing;

        do {
            if (checkIsGuessed(guessWord, guessArray)) {
                return;
            }

            char chr = charInput();

            isGuessing = checkIfCharIsRight(guessWord, guessArray, chr);
        } while(isGuessing);
    }

    private static boolean checkIsGuessed(String guessWord, char[] guessArray) {
        if (Objects.equals(guessWord, String.valueOf(guessArray))) {
            System.out.println(" Вы отгадали слово!");
            return true;
        }
        return false;
    }

    private static char charInput() {
        System.out.println(" Введите букву:");
        char chr = scanner.next().charAt(0);
        return chr;
    }

    private static boolean checkIfCharIsRight(String guessWord, char[] guessArray, char guessChar) {
        if(guessWord.contains(Character.toString(guessChar))) {
            int guessIndex = guessWord.indexOf(guessChar);
            guessArray[guessIndex] = guessChar;
            for (int i = 0; i < guessArray.length; i++) {
                System.out.print(guessArray[i]);
            }
        } else if (mistakeCount >= MAX_MISTAKE_COUNT) {
            System.out.println("Игра окончена. Вы проиграли.");
            return false;
        } else {
            mistakeCount += 1;
            System.out.println("Вы допустили ошибку.");
            System.out.println("Ошибки: "+ mistakeCount);
        }
        return true;
    }

    public static String getRandomWord() {
        return dictionary.get(random.nextInt(dictionary.size()));
    }

}