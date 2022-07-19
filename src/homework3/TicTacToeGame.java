package homework3;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    public static final char HUMAN_DOT = 'X';
    public static final char PC_DOT = 'O';
    public static final char EMPTY_DOT = '_';

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Random RANDOM = new Random();

    public static int fieldSizeX = 3; // Размеры поля по X
    public static int fieldSizeY = 3; // Размеры поля по Y
    public static char[][] field; // Поле
    public static int charSeries = 3; // Количество одинаковых символов для победы ( Серия )

    public static void main(String[] args) {
        initialMap(fieldSizeX, fieldSizeY);
        printMap();
        while (true) {
            inputValue();
            printMap();
            if (checkWin(HUMAN_DOT)) {
                System.out.println("Поздравляем, победил пользователь");
                break;
            }
            if (isFullMap()) {
                System.out.println("Ничья!");
                break;
            }
            System.out.println();
            pcInputValue();
            printMap();
            if (checkWin(PC_DOT)) {
                System.out.println("Поздравляем,победил компьютер!");
                break;
            }
            if (isFullMap()) {
                System.out.println("Ничья!");
                break;
            }
            System.out.println();
        }
    }

    /*
        Метод инициализирует карту.
     */

    public static char[][] initialMap(int fieldSizeX, int fieldSizeY) {
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = EMPTY_DOT;
            }
        }
        return field;
    }

    /*
        Метод рисует карту в консоли.
     */

    public static void printMap() {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                System.out.print("| " + field[x][y] + " |");
            }
            System.out.println();
        }
    }

    /*
        Метод запрашивает у пользователя координаты.
     */

    public static void inputValue() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!(isValidCell(x, y)) || !(isEmptyCell(x, y)));
        field[x][y] = HUMAN_DOT;
    }

    /*
        Метод проверяет допустимы ли введенные значения
     */

    public static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /*
        Метод проверяет свободна ли ячейка
     */

    public static boolean isEmptyCell(int x, int y) {
        return field[x][y] == EMPTY_DOT;
    }

    /*
        Метод позволяет компьютеру сделать ход.
     */

    public static void pcInputValue() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!(isEmptyCell(x, y)));
        field[x][y] = PC_DOT;
        // Для работы метода aiStep() нужно закоментировать все что есть в методе pcInputValue() и раскоментировать метод aiStep()
//        aiStep();
    }

    /*
        Что-то типа ИИ который блокирует ходы пользователя.
        Метод возращает массив с координатами. В цикле идет проверка, если числа валидные и в этой ячейке стоит HUMAN_DOT и ячейка с другой строны свободна. То Бот ставит туда свой символ
     */

    public static char aiStep() {
        int xInt = 0;
        int yInt = 0;
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                if (isValidCell(x, y - 1) && (field[x][y] == HUMAN_DOT) && isEmptyCell(x, y - 1)) {
                    xInt = x;
                    yInt = y - 1;
                } else if (isValidCell(x, y + 1) && (field[x][y] == HUMAN_DOT) && isEmptyCell(x, y + 1)) {
                    xInt = x;
                    yInt = y + 1;
                } else if (isValidCell(x + 1, y) && (field[x][y] == HUMAN_DOT) && isEmptyCell(x + 1, y)) {
                    xInt = x + 1;
                    yInt = y;
                } else if (isValidCell(x - 1, y) && (field[x][y] == HUMAN_DOT) && isEmptyCell(x - 1, y)) {
                    xInt = x - 1;
                    yInt = y;
                }
            }
        }
        return field[xInt][yInt] = PC_DOT;
    }

    /*
        Метод проверяет выиграл кто-то или нет. Метод возращает булево значение
     */

    public static boolean checkWin(char c) {
        for (int x = 0; x < field.length; x++) {
            int xWin = 0;
            int yWin = 0;
            int[] diagonalArray = {0, 0, 0, 0};
            for (int y = 0; y < field.length; y++) {
                // Проверка в колонках и строках
                if (field[x][y] == c && field[x][y] == c && field[x][y] == c) {
                    yWin++;
                }
                if (field[y][x] == c && field[y][x] == c && field[y][x] == c) {
                    xWin++;
                }
                if (xWin == charSeries || yWin == charSeries) return true;

                // Проверка в диагоналях
                if ((field.length - 1 - x + y) < field.length) {
                    if (field[y][field.length - 1 - x + y] == c) {
                        diagonalArray[0] += 1;
                    }
                } else {
                    diagonalArray[0] = 0;
                }

                if (x + y < field.length && x > 0) {
                    if (field[y + x][y] == c) {
                        diagonalArray[1] += 1;
                    }
                } else {
                    diagonalArray[1] = 0;
                }

                //  Подсчет обратной диагонали
                if ((x - y) >= 0) {
                    if (field[y][x - y] == c) {
                        diagonalArray[2] += 1;
                    }
                } else {
                    diagonalArray[2] = 0;
                }

                if (y > 0) {
                    if (field[y][field.length - y] == c) {
                        diagonalArray[3] += 1;
                    }
                } else {
                    diagonalArray[3] = 0;
                }
                if (diagonalArray[0] == charSeries || diagonalArray[1] == charSeries || diagonalArray[2] == charSeries || diagonalArray[3] == charSeries)
                    return true;
            }
        }
        return false;
    }

    /*
        Метод проверяет есть свободные ячейки на карте или нет и возращает булево значение.
     */

    public static boolean isFullMap() {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                if (field[x][y] == EMPTY_DOT) return false;
            }
        }
        return true;
    }
}
