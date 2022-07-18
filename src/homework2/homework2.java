package homework2;


import java.util.Random;
import java.util.Arrays;

public class homework2 {

    public static void main(String[] args) {

        // Вызов метода генерирует массив с размером 7 и диапазоном чисел от 0 до 10
        int[] arrayGenerate = generateArray(7, 10);

        // Вызов метода заменяет все 1 в массиве на 0 и наоборот все 0 на 1
        int[] arrayNumber = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Если в массиве есть цифры помимо 1 и 0, то массив не будет изменен полностью.");
        printArray("Массив до изменений", arrayNumber);
        printArray("Массив после изменений", replaceElementArray(arrayNumber));
        System.out.println();

        // Вызов метода заполняет массив с шагом в 3. В результат в консоль выводятся 0 3 6 9 12 15 18 21
        printArray("Массив с шагом 3", fillArray());
        System.out.println();

        // Вызов метода проверяет числа в массиве если они меньше 6, то умножаются на 2.
        int[] numbers = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray("Массив до изменений", numbers);
        printArray("Массив после изменений", numbersChangeArray(numbers));
        System.out.println();

        // Вызов метода заполняет двумерный массив единицами по диагоналям.
        fillTwoDimensionalArray();
        System.out.println();

        // Вызов метода находит Максимальное и Минимальное число массива.
        System.out.println("Максимальное число массива: " + findMaxNumberArray(arrayGenerate));
        System.out.println("Минимальное число массива: " + finMinNumberArray(arrayGenerate));
        System.out.println();


       /* Вызов метода возращает True, если сумма левой и правой части массива равны. Иначе False
            {1, 1, 0, || 5, 2} - false
            {1, 1, 1, || 2, 1} - true
            {10, 0, || 5, 2, 7} - false
            {1, 2, || 0, 3} - true
            {4, 2, 0, || 1, 5} - true
            {2, 2, 2, 1, 2, 2, || 10, 1} - true
            {10, || 2, 1, 5, 2} - true
            {20, 4, 6, 2, 5, 8, 15} - true
         */
        int[] balanceArray = {20, 4, 6, 2, 5, 8, 15};
        System.out.println("Сумма левой и правой части массива равны? " + checkBalance(balanceArray));
        System.out.println();

        // Вызов метода перемещает элементы массива вправо, если n - положительное. Влево, если n - отрицательное.
        printArray("Массив до сдвига", arrayGenerate);
        printArray("Массив после сдвига", moveArray(arrayGenerate, 1));
        System.out.println();
    }

    /*
     (1)Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1,
        0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
    */

    public static int[] replaceElementArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            switch (array[i]) {
                case 0:
                    array[i] = 1;
                    break;
                case 1:
                    array[i] = 0;
                    break;
            }
        }
        return array;
    }

    /*
     (2)Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его
        значениями 0 3 6 9 12 15 18 21;
    */

    public static int[] fillArray() {
        int[] numbers = new int[8];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i * 3;
        }
        return numbers;
    }

    /*
      (3)Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6
        умножить на 2;
     */

    public static int[] numbersChangeArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 6) {
                numbers[i] *= 2;
            }
        }
        return numbers;
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


    // Метод поиска максимального значения в массиве

    public static int findMaxNumberArray(int[] numbers) {
        int max = numbers.length;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    // Метод поиска минимального значения в массиве

    public static int finMinNumberArray(int[] numbers) {
        int min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }

    /*
     (6)** Написать метод, в который передается не пустой одномерный целочисленный массив,
        метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части
        массива равны. Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, ||
        2, 1]) → true, граница показана символами ||, эти символы в массив не входят
     */

    public static boolean checkBalance(int[] array) {
        int leftSumArray = 0;
        int rightSumArray = 0;

        for (int i = 0; i < array.length; i++) {
            leftSumArray += array[i];
        }

        for (int j = 0; j < array.length; j++) {
            if (leftSumArray == rightSumArray) {
                return true;
            }
            leftSumArray -= array[j];
            rightSumArray += array[j];
        }
        return false;
    }

    /*
     (7)**** Написать метод, которому на вход подается одномерный массив и число n (может быть
        положительным, или отрицательным), при этом метод должен сместить все элементы
        массива на n позиций. Элементы смещаются циклично. Для усложнения задачи нельзя
        пользоваться вспомогательными массивами. Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) ->
        [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ]. При каком n в какую сторону сдвиг
        можете выбирать сами.
     */

    public static int[] moveArray(int[] array, int n) {
        int copy = 0;

        if (n > 0) {
            for (int i = 0; i < n; i++) {
                copy = array[array.length - 1];
                for (int j = array.length - 1; j > 0; j--) {
                    array[j] = array[j - 1];
                }
                array[0] = copy;
            }
            return array;
        } else if (n < 0) {
            for (int i = array.length; i > n; i--) {
                copy = array[array.length - 1];
                for (int j = array.length - 1; j > 0; j--) {
                    array[j] = array[j - 1];
                }
                array[0] = copy;
            }
            return array;
        } else {
            return array;
        }
    }

    /*
        Вызов метода генерирует случайный массив с определенными параметрами.
         - size = размер генерируемого массива
         - range = диапазон значений генерируемого массива.
         Например, generateArray(7, 10) - Сгенерируется массив с размером 7 и диапозоном чисел от 0 до 10.
     */
    public static int[] generateArray(int size, int range) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(range);
        }
        return array;
    }

    /*
        Метод распечатывает массив.
     */

    public static void printArray(String message, int[] array) {
        System.out.print(message + ":\t");
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print("\t" + array[i]);
        }
        System.out.println(" ]");
    }
}
