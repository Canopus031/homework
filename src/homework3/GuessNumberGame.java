package homework3;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {

    /**
     * 1. Написать программу, которая загадывает случайное число от 0 до 9,
     * и пользователю дается 3 попытки угадать это число. При каждой попытке
     * компьютер должен сообщить больше ли указанное пользователем число чем
     * загаданное, или меньше. После победы или проигрыша выводится запрос
     * «Повторить игру еще раз? 1 – да / 0 – нет» (1 – повторить, 0 – нет).
     */

    public static final Scanner SCANNER = new Scanner(System.in);
    public static int userAnswer; // В переменную записывается введенное пользователем число от 0 до 9, при вызове метода inputValue(message, min, max)
    public static int randomNumber; // В переменную записывается случайное число от 0 до size, при вызове метода randomNumberGeneration(size)

    public static void main(String[] args) {
        do {
            isInputValueCorrect();
        } while (startAgainGame() == 1);
        System.out.println("Игра завершена");
    }

    /**
     * Метод генерирует случайное число от 0 до size.
     * @param size - диапазон генерации случайных чисел.
     * @return - случайное число.
     */

    public static int randomNumberGeneration(int size) {

        Random random = new Random();
        randomNumber = random.nextInt(size);
        return randomNumber;

    }

    /**
     * Метод запрашивает в пользователя число от min до max и записывает число, введенное пользователем, в переменную userAnswer.
     * @param message - сообщение, выводимое в консоль.
     * @param min     - минимальное значение.
     * @param max     - максимальное значение.
     * @return - ответ пользователя.
     */

    public static int inputValue(String message, int min, int max) {

        do {
            System.out.println(message);
            userAnswer = SCANNER.nextInt();
        } while (userAnswer < min || userAnswer > max);
        return userAnswer;

    }

    /**
     * Метод спрашивает у пользователя хочет ли он повторить игру.
     */

    public static int startAgainGame() {
        return inputValue("Повторить игру еще раз?\n1 – повторить, 0 – нет", 0, 1);
    }

    /**
     * Метод проверяет, соответствует ли введенное число пользователем с загаданным числом. Если да, то в консоли пишется "Вы отгадали!" и выполняется метод startAgainGame()
     * Если пользователь не отгадал и его попытки закончились в консоль пишется "Вы не угадали. Верное число = randomNumber" и выполняется метод startAgainGame().
     * Если у игрока остались попытки и он ввел число больше загаданного, то ему выводится сообщение - "Введенное вами значение больше загаданного числа. Попробуйте еще раз".
     * Если меньше - "Введенное вами значение меньше заданного числа. Попробуйте еще раз".
     */

    public static void isInputValueCorrect() {
        randomNumberGeneration(10);
        int maxTryCount = 0;
        while (userAnswer != randomNumber && maxTryCount < 3) {
            inputValue("Введите число которое было загадано, от 0 до 9: ", 0, 9);
            if (userAnswer == randomNumber) {
                System.out.println("Вы отгадали!");
            } else {
                maxTryCount += 1;
                System.out.printf("Введенное вами число %s", (userAnswer < randomNumber ? "меньше" : "больше") + " загаданного числа.\n");
            }
        }
        if (maxTryCount == 3) {
            System.out.println("Вы не угадали.\nВерное число = " + randomNumber);
        }
    }

}
