package homework3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class GuessWord {

    /**
     * 2 * Создать массив из слов
     *  String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
     *  "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
     *  "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
     *  При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
     *  сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь. Если
     *  слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
     *  apple – загаданное
     *  apricot - ответ игрока
     *  ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
     *  Для сравнения двух слов посимвольно можно пользоваться:
     *  String str = "apple";
     *  char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
     *  Играем до тех пор, пока игрок не отгадает слово.
     *  Используем только маленькие буквы.
     */

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Random RANDOM = new Random();

    public static String userAnswer; // В переменную записывается ответ введенный пользователем, при вызове метода inputValue()
    public static String randomWord; // В переменную записывается случайное слово из массива words, при вызове метода randomWord
    public static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
            "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};


    public static void main(String[] args) {
        randomWord();
        do {
            inputValue("Угадайте какое слово из массива загадал бот: \n" + Arrays.toString(words));
        } while(!(isCorrectAnswer()));
        System.out.println("Поздравляю, вы угадали слово!");
    }

    /**
     * Метод загадывает случайное слово из массива words.
     * @return - случайное слово из массива words.
     */

    public static String randomWord() {
        int randomNumber = RANDOM.nextInt(words.length);
        return randomWord = words[randomNumber];
    }

    /**
     * Метод запрашивает у пользователя ответ на слово, которое загадал бот.
     * @param message - сообщение, выводимое в консоль.
     * @return - ответ пользователя
     */

    public static String inputValue(String message) {
        System.out.println(message);
        return userAnswer = SCANNER.nextLine().toLowerCase();
    }

    /**
     * Метод возвращает True, если загаданное слово совпадает с ответом пользователя.
     * Иначе вызывается метод isCorrectLetters и возвращается false.
     * @return - результат логической проверки. [True || False]
     */

    public static boolean isCorrectAnswer() {
        if (userAnswer.equals(randomWord)) {
            return true;
        } else {
            isCorrectLetters(userAnswer, randomWord);
            return false;
        }
    }

    /**
     *  Метод принимает два параметра:
     * @param userAnswer = Ответ пользователя
     * @param randomWord = случайное слово из массива words
     * Метод выполняет проверку символов, если местоположение символа совпадает, выводит строку с расположением букв(ы) среди 15 символов '#'.
     * Пример apple - загаданное / apricot - ответ игрока, в консоль будет напечатана строка ap#############
     */

    public static void isCorrectLetters(String userAnswer, String randomWord) {
        for (int i = 0; i < 15; i++) {
            if (randomWord.length() > i && i < userAnswer.length() && randomWord.charAt(i) == userAnswer.charAt(i)) {
                System.out.print(randomWord.charAt(i));
            } else {
                if (randomWord.equals(userAnswer)) {
                    System.out.print("");
                } else {
                    System.out.print("#");
                }
            }
        }
        System.out.println();
    }

}