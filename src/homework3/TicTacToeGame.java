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
    public static int charSeries = 3; // Количество одинаковых символов для победы (Серия)

    public static int x;
    public static int y;

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
            pcInputValue(); // Можно использовать pcInputValue() или aiStep(x, y). Что-то одно!
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

    /**
     * Метод инициализирует карту.
     * @param fieldSizeX - координаты по X.
     * @param fieldSizeY - координаты по Y.
     * @return - созданное поле
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

    /**
     * Метод печатает карту в консоли.
     */

    public static void printMap() {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                System.out.print("| " + field[x][y] + " |");
            }
            System.out.println();
        }
    }

    /**
     * Метод запрашивает у пользователя координаты и помещает на них фишку игрока
     */

    public static void inputValue() {
        do {
            System.out.println("Введите координаты: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!(isValidCell(x, y)) || !(isEmptyCell(x, y)));
        field[x][y] = HUMAN_DOT;
    }

    /**
     * Метод проверяет, являются ли введенные значения допустимыми.
     * @param x - координаты по X.
     * @param y - координаты по Y.
     * @return - результат проверки координат. [True || False]
     */

    public static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Метод проверяет свободна ли ячейка.
     * @param x - координаты по X.
     * @param y - координаты по Y.
     * @return - результат проверки ячейки. [True || False]
     */

    public static boolean isEmptyCell(int x, int y) {
        return field[x][y] == EMPTY_DOT;
    }

    /**
     * Метод генерирует случайные координаты для хода компьютера
     */

    public static void pcInputValue() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!(isEmptyCell(x, y)));
        field[x][y] = PC_DOT;
    }

    /**
     * Что-то типа ИИ который блокирует ходы пользователя.
     * Метод возвращает массив с координатами. В цикле идет проверка, если числа валидные и в этой ячейке
     * стоит HUMAN_DOT и ячейка с другой стороны свободна. То Бот ставит туда свой символ.
     * @param xInt - координаты по X.
     * @param yInt - координаты по Y.
     * @return - массив с координатами.
     */

    public static char aiStep(int xInt, int yInt) {
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
        } else {
            do {
                xInt = RANDOM.nextInt(fieldSizeX);
                yInt = RANDOM.nextInt(fieldSizeY);
            } while (!(isEmptyCell(xInt, yInt)));
        }

        return field[xInt][yInt] = PC_DOT;
    }

    /**
     * Способ проверки победы. Если компьютер или игрок выиграли, метод возвращает True, в противном случае false
     * @param c - фишка игрока / компьютера.
     * @return - результат проверки победы. [True || False]
     */

    public static boolean checkWin(char c) {
        for (int x = 0; x < field.length; x++) {
            int row = 0;
            int col = 0;
            int[] diagonalArray = {0, 0, 0, 0};
            for (int y = 0; y < field.length; y++) {
                // Проверка в колонках и строках
                if (field[x][y] == c && field[x][y] == c && field[x][y] == c) {
                    row++;
                } else if (field[x][y] != c) {
                    row = 0;
                }
                if (field[y][x] == c && field[y][x] == c && field[y][x] == c) {
                    col++;
                } else if (field[y][x] != c) {
                    col = 0;
                }

                if (row == charSeries || col == charSeries) return true;

                // Проверка в диагоналях
                if ((field.length - 1 - x + y) < field.length) {
                    if (field[y][field.length - 1 - x + y] == c) {
                        diagonalArray[0] += 1;
                    } else if (field[y][field.length - 1 - x + y] != c) {
                        diagonalArray[0] = 0;
                    }
                } else {
                    diagonalArray[0] = 0;
                }
                if (x + y < field.length && x > 0) {
                    if (field[y + x][y] == c) {
                        diagonalArray[1] += 1;
                    } else if (field[y + x][y] != c) {
                        diagonalArray[1] = 0;
                    }
                } else {
                    diagonalArray[1] = 0;
                }
                if ((x - y) >= 0) {
                    if (field[y][x - y] == c) {
                        diagonalArray[2] += 1;
                    } else if (field[y][x - y] != c) {
                        diagonalArray[2] = 0;
                    }
                } else {
                    diagonalArray[2] = 0;
                }
                if (y > 0) {
                    if (field[y][field.length - y] == c) {
                        diagonalArray[3] += 1;
                    } else if (field[y][field.length - y] != c) {
                        diagonalArray[3] = 0;
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

    /**
     * Метод проверяет, есть ли свободные ячейки на карте или нет, и возвращает логическое значение. [True || False]
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
