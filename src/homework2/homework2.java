package homework2;


import java.util.Random;

public class homework2 {

    public static void main(String[] args) {

        // Вызов метода заменяет все 1 в массиве на 0 и наоборот все 0 на 1
        replaceElementArray();

        // Вызов метода заполняет массив с шагом в 3. В результат в консоль выводятся 0 3 6 9 12 15 18 21
        fillArray();

        // Вызов метода проверяет числа в массиве если они меньше 6, то умножаются на 2.
        numbersChangeArray();

        // Вызов метода заполняет двумерный массив единицами по диагоналям.
        fillTwoDimensionalArray();

        // Вызов метода находит Максимальное и Минимальное число массива.
        findMaxAndMinNumberArray();

        // Вызов метода возращает True, если сумма левой и правой части массива равны. Иначе False
//        int[] balanceArray = {1, 1, 1, 2, 1};
//        System.out.println(checkBalance(balanceArray));

        // Вызов метода перемещает элементы массива вправо, если n - положительное. Влево, если n - отрицательное.
//        moveArray(1);
    }

    /*
     (1)Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1,
        0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
    */

    public static void replaceElementArray() {
        int[] arrayNumber = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i = 0; i < arrayNumber.length; i++) {
            if (arrayNumber[i] == 0) {
                arrayNumber[i] = 1;
                System.out.print(arrayNumber[i] + " ");
            } else {
                arrayNumber[i] = 0;
                System.out.print(arrayNumber[i] + " ");
            }
        }
        System.out.println();
    }

    /*
     (2)Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его
        значениями 0 3 6 9 12 15 18 21;
    */

    public static void fillArray() {
        int[] numbers = new int[8];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i * 3;
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }

    /*
      (3)Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6
        умножить на 2;
     */

    public static void numbersChangeArray() {
        int[] numbers = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 6) {
                numbers[i] *= 2;
            }
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }

    /*
      (4)Создать квадратный двумерный целочисленный массив (количество строк и столбцов
        одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
     */

    public static void fillTwoDimensionalArray() {
        int[][] numbers = new int[5][5];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if (j == i || j == numbers.length - 1 - i) {
                    numbers[i][j] = 1;
                } else {
                    numbers[i][j] = 0;
                }
                System.out.print(numbers[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /*
     (5)** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без
        помощи интернета
     */

    public static void findMaxAndMinNumberArray() {
        int[] numbers = {24, 342, 214, 62, 31, 51, 53, 36, 14, 63, 34};
        int max = numbers.length;
        int min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            } else if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        System.out.println("Максимальное число массива = " + max + "\nМинимальное число массива = " + min);
    }

    /*
     (6)** Написать метод, в который передается не пустой одномерный целочисленный массив,
        метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части
        массива равны. Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, ||
        2, 1]) → true, граница показана символами ||, эти символы в массив не входят
     */

    public static boolean checkBalance(int[] array) {
        int centerArray = array.length / 2;
        int leftSumArray = 0;
        int rightSumArray = 0;

        for (int i = 0; i < array.length; i++) {
            if (centerArray <= i) {
                leftSumArray += array[i];
            } else {
                rightSumArray += array[i];
            }
        }
        if (leftSumArray == rightSumArray) {
            return true;
        } else {
            return false;
        }
    }

    /*
     (7)**** Написать метод, которому на вход подается одномерный массив и число n (может быть
        положительным, или отрицательным), при этом метод должен сместить все элементы
        массива на n позиций. Элементы смещаются циклично. Для усложнения задачи нельзя
        пользоваться вспомогательными массивами. Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) ->
        [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ]. При каком n в какую сторону сдвиг
        можете выбирать сами.
     */

    public static void moveArray(int n) {
        Random random = new Random();
        int[] numbers = new int[7];
        int[] copyArrayNumbers = numbers;

        // Цикл генерирует массив состоящий из 7 рандомных элемнтов от 0 до 100
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < numbers.length; i++) {
            if (n >= 0) {
                copyArrayNumbers[i] = numbers[i + n];
                System.out.print(copyArrayNumbers[i] + " ");
            }
        }
    }
}
