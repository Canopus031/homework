package homework3;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    /*
      1. Написать программу, которая загадывает случайное число от 0 до 9,
         и пользователю дается 3 попытки угадать это число. При каждой попытке
         компьютер должен сообщить больше ли указанное пользователем число чем
         загаданное, или меньше. После победы или проигрыша выводится запрос
         «Повторить игру еще раз? 1 – да / 0 – нет» (1 – повторить, 0 – нет).
     */

    public static final Scanner SCANNER = new Scanner(System.in);
    public static int maxTryCount = 2; // Максимальное число попыток, которое дается пользователю, чтобы отгадать число.
    public static int userAnswer; // В переменную записывается введенное пользователем число от 0 до 9, при вызове метода inputValue(message, min, max)
    public static int randomNumber; // В переменную записывается рандомное число от 0 до size, при вызове метода randomNumberGeneration(size)

    public static void main(String[] args) {

        // Вызов метода генерирует случайное число в диапазоне от 0 до параметра size.
        randomNumberGeneration(10);

        // Вызов метода выполняет проверку
        isInputValueCorrect();
    }

    /*
        Метод генерирует случайное число от 0 до size.
     */

    public static int randomNumberGeneration(int size) {

        Random random = new Random();
        randomNumber = random.nextInt(size);
        return randomNumber;

    }

    /*
        Метод возращает введенное число пользователем в переменную userAnswer
     */

    public static int inputValue(String message, int min, int max) {

        do {
            System.out.println(message);
            userAnswer = SCANNER.nextInt();
        } while (userAnswer < min || userAnswer > max);
        return userAnswer;

    }

    /*
        Метод спрашивает у пользователя хочет ли он повторить игру и возращает ответ в переменную userAnswer
     */

    public static int startAgainGame() {
        return inputValue("Повторить игру еще раз?\n1 – повторить, 0 – нет", 0, 1);
    }

    /*
        Метод проверяет совпадает ли введеное число пользователем с загаданным числом. Если да, то в консоли пишется "Вы отгадали!" и выполняется метод startAgainGame()
        Если пользователь не отгадал и его попытки закончились в консоль пишется "Вы не угадали. Верное число = randomNumber" и выполняется метод startAgainGame().
        Если у игрока остались попытки и он ввел число больше загаданного, то ему выводится сообщение - "Введенное вами значение меньше загаданого числа. Попробуйте еще раз".
        Если меньше - "Введенное вами значение больше загаданого числа. Попробуйте еще раз".
     */

    public static void isInputValueCorrect() {

        while (true) {
            inputValue("Введите число которое было загадно, от 0 до 9: ", 0, 9);
            if (userAnswer == randomNumber) {
                System.out.println("Вы отгадали!");

                userAnswer = startAgainGame();
                if (userAnswer == 0) {
                    System.out.println("Игра завершена");
                    break;
                } else if (userAnswer == 1) {
                    randomNumberGeneration(10);
                    maxTryCount = 2;
                }

            } else if (maxTryCount <= 0) {
                System.out.println("Вы не угадали.\nВерное число = " + randomNumber);

                userAnswer = startAgainGame();
                if (userAnswer == 0) {
                    System.out.println("Игра завершена");
                    break;
                } else if (userAnswer == 1) {
                    randomNumberGeneration(10);
                    maxTryCount = 2;
                }

            } else if (userAnswer < randomNumber) {
                System.out.println("Введенное вами значение меньше загаданого числа. Попробуйте еще раз");
                maxTryCount -= 1;
            } else if (userAnswer > randomNumber) {
                System.out.println("Введенное вами значение больше загаданого числа. Попробуйте еще раз");
                maxTryCount -= 1;
            }
        }

    }

}
