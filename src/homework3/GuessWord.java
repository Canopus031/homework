package homework3;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class GuessWord {

    /*
        2 * Создать массив из слов
            String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
            "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
            При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
            сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь. Если
            слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
            apple – загаданное
            apricot - ответ игрока
            ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
            Для сравнения двух слов посимвольно можно пользоваться:
            String str = "apple";
            char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
            Играем до тех пор, пока игрок не отгадает слово.
            Используем только маленькие буквы.
     */

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Random RANDOM = new Random();

    public static String userAnswer; // В переменную записывается ответ введенный пользователем, при вызове метода inputValue()
    public static String randomWord; // В переменную записывается рандомное слово из массива words, при вызове метода randomWord
    public static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
            "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};


    public static void main(String[] args) {
        randomWord();
        while (true) {
            inputValue("Угадайте какое слово из массива загадал бот: \n" + Arrays.toString(words));
            boolean correctAnswer = isCorrectAnswer();
            if (correctAnswer) {
                System.out.println("Поздравляю, вы отгадали слово!");
                break;
            } else {
                System.out.println("\nВы не отгадали, попробуйте еще раз\n");
            }
        }
    }

    /*
        Метод загадывает случайное слово из массива words.
        И возращает в переменную - randomWord
     */

    public static String randomWord() {
        int randomNumber = RANDOM.nextInt(words.length);
        return randomWord = words[randomNumber];
    }

    /*
        Метод запрашивает у пользователя ответ на слово, которое загадал бот.
        и возращает ответ в переменную - userAnswer
     */

    public static String inputValue(String message) {
        System.out.println(message);
        return userAnswer = SCANNER.nextLine().toLowerCase();
    }

    /*
        Метод возращает True если загаданное слово совпадает с ответом пользователя.
        Иначе вызывается метод isCorrectLetters и возращается false
     */

    public static boolean isCorrectAnswer() {
        if (userAnswer.equals(randomWord)) {
            return true;
        } else {
            isCorrectLetters(userAnswer, randomWord);
            return false;
        }
    }

    /*
        Метод принимает два параметра:
            - userAnswer = Ответ пользователя
            - randomWord = Рандомное слово из массива words
        Метод делает проверку символов, если расположение символа совпадает, то выводит строку с расположение букв(ы) среди 15 символов '#'.
        Пример apple - загаднное / apricot - ответ игрока, в консоль будет напечатана строка ap#############
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