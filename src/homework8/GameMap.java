package homework8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameMap extends JPanel {

    private int STATE_MODE; // Режим игры
    public static final int MODE_HVSAI = 1;
    public static final int MODE_HVSHM = 2;

    private static final Random RANDOM = new Random();
    private static final int EMPTY_DOT = 0;
    private static final int X_DOT = 1; // Фишка пользователя - X (Если выбран режим игры для 2 игроков, то используется фишка бота O_DOT)
    private static final int O_DOT = 2; // Фишка компьютера - 0
    private static final int PADDING_DOT = 5; // Отступ фишек в ячейке.

    private int fieldSizeX; // Размер поля по X.
    private int fieldSizeY; // Размер поля по Y.
    private int winLength;
    private int[][] field; // Поле для игры.

    private int cellWidth; // Размер ячейки по ширине.
    private int cellHeight; // Размер ячейки по высоте.
    private boolean initialMap;
    private boolean isGameOver;

    private int stateGameOver; // Состояние игры.
    private static final int STATE_DRAW = 0; // Ничья
    private static final int STATE_WIN_X = 1; // Победил пользователь
    private static final int STATE_WIN_O = 2; // Победил компьютер.

    private boolean isXTurn = false; // True - ходит X; False - ходит О;

    private static final String MSG_WIN_X = "Победили крестики!";
    private static final String MSG_WIN_O = "Победили нолики!";
    private static final String MSG_DRAW = "Ничья!";

    GameMap() {
        setBackground(Color.WHITE);
        initialMap = false;
    }

    /**
     * Метод запускает новую игру с параметрами, которые указал пользователь.
     * @param mode - Режим игры.
     * @param fieldSizeX - Размер поля по X.
     * @param fieldSizeY - Размер поля по Y.
     * @param winLength - Длина одинаковых фишек для победы.
     */

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.STATE_MODE = mode;
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        field = new int[fieldSizeX][fieldSizeY];
        initialMap = true;
        repaint();
        isGameMode();
        isGameOver = false;
    }

    /**
     * Переопределенный метод.
     * @param graphics the <code>Graphics</code> object to protect
     */

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        render(graphics);
    }

    /**
     * Метод рисует нашу карту, а также фишки X / 0
     * @param graphics - the <code>Graphics</code> object to protect
     */

    private void render(Graphics graphics) {
        if (!initialMap) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;
        graphics.setColor(Color.GRAY);

        // Рисуем вертикальные линии

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            graphics.drawLine(x, 0, x, height);
        }

        // Рисуем горизонтальные линии

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            graphics.drawLine(0, y, width, y);
        }

        // Рисуем фишки

        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isEmptyCell(x, y)) {
                    continue;
                }
                if (field[x][y] == X_DOT) {
                    graphics.setColor(new Color(227, 38, 54));
                    Graphics2D g2 = (Graphics2D) graphics;
                    g2.setStroke(new BasicStroke(3));
                    g2.drawLine((x * cellWidth + PADDING_DOT), (y * cellHeight + PADDING_DOT), (x + 1) * cellWidth - PADDING_DOT, (y + 1) * cellHeight - PADDING_DOT);
                    g2.drawLine((x + 1) * cellWidth - PADDING_DOT, (y * cellHeight + PADDING_DOT), (x * cellWidth + PADDING_DOT), (y + 1) * cellHeight - PADDING_DOT);
                } else if (field[x][y] == O_DOT) {
                    graphics.setColor(new Color(73, 77, 78));
                    graphics.fillOval(x * cellWidth + PADDING_DOT, y * cellHeight + PADDING_DOT, cellWidth - PADDING_DOT * 2, cellHeight - PADDING_DOT * 2);
                } else {
                    throw new RuntimeException("Error");
                }
            }
        }

        // Проверяем завершена ли игра, если да вызываем метод showMessageGameOver

        if (isGameOver) {
            showMessageGameOver(graphics);
        }
    }

    /**
     * Этот метод создает прямоугольник с сообщением, если кто-то выиграл или сыграл вничью, в зависимости от того, что произошло в игре.
     * @param graphics - the <code>Graphics</code> object to protect
     */

    private void showMessageGameOver(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 185, getWidth(), 60);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(new Font("Montserrat", Font.BOLD, 25));

        switch (stateGameOver) {
            case STATE_WIN_X -> graphics.drawString(MSG_WIN_X, 130, getHeight() / 2);
            case STATE_WIN_O -> graphics.drawString(MSG_WIN_O, 110, getHeight() / 2);
            case STATE_DRAW -> graphics.drawString(MSG_DRAW, 180, getHeight() / 2);
            default -> throw new RuntimeException("Unexpected game parameter");
        }
    }

    /**
     * Метод отлавливает клики по полю.
     * Если STATE_MODE = MODE_HVSAI, то вызывается метод gameModeHVSAI.
     * Если STATE_MODE = MODE_HVSHM, то вызывается метод gameModeHVSH.
     * Если STATE_MODE = значению, которое не указано в программе, то возвращает Exception - <text>Unexpected game mode: STATE_MODE</text>
     */

    private void isGameMode() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                switch (STATE_MODE) {
                    case MODE_HVSAI -> gameModeHVSAI(e);
                    case MODE_HVSHM -> gameModeHVSH(e);
                    default -> throw new RuntimeException("Unexpected game mode: " + STATE_MODE);
                }
            }
        });
    }

    /**
     * Метод для режима игры HUMAN vs AI.
     * @param e - событие.
     */

    private void gameModeHVSAI(MouseEvent e) {
        if (!initialMap) {
            return;
        }
        if (isGameOver) {
            return;
        }
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) {
            return;
        }
        field[cellX][cellY] = X_DOT;
        if (checkWin(X_DOT)) {
            setGameOver(STATE_WIN_X);
            return;
        }
        if (isFullMap()) {
            setGameOver(STATE_DRAW);
            return;
        }
        aiTurn();
        repaint();
        if (checkWin(O_DOT)) {
            setGameOver(STATE_WIN_O);
            return;
        }
        if (isFullMap()) {
            setGameOver(STATE_DRAW);
            return;
        }
    }

    /**
     * Метод для режима игры HUMAN vs HUMAN.
     * @param e - событие.
     */

    private void gameModeHVSH(MouseEvent e) {
        if (!initialMap) {
            return;
        }
        if (isGameOver) {
            return;
        }
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) {
            return;
        }
        isXTurn = !isXTurn;
        field[cellX][cellY] = isXTurn ? X_DOT : O_DOT;
        if (checkWin(X_DOT)) {
            setGameOver(STATE_WIN_X);
            return;
        }
        if (isFullMap()) {
            setGameOver(STATE_DRAW);
            return;
        }
        field[cellX][cellY] = isXTurn ? X_DOT : O_DOT;
        repaint();
        if (checkWin(O_DOT)) {
            setGameOver(STATE_WIN_O);
            return;
        }
        if (isFullMap()) {
            setGameOver(STATE_DRAW);
            return;
        }
    }

    /**
     * Метод для установки завершения игры.
     * @param gameOver - состояние игры. STATE_DRAW / STATE_WIN_X / STATE_WIN_O.
     */

    private void setGameOver(int gameOver) {
        isGameOver = true;
        stateGameOver = gameOver;
        repaint();
    }

    /**
     * Метод проверяет, являются ли введенные значения допустимыми.
     * @param x - координаты по X.
     * @param y - координаты по Y.
     * @return - результат проверки координат. [True || False]
     */

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Метод проверяет свободна ли ячейка.
     * @param x - координаты по X.
     * @param y - координаты по Y.
     * @return - результат проверки ячейки. [True || False]
     */

    private boolean isEmptyCell(int x, int y) {
        return field[x][y] == EMPTY_DOT;
    }

    /**
     *  Метод генерирует случайные координаты для хода компьютера, в зависимости от проверки метода turnAIWinCell и turnHumanWinCell.
     *  Если turnAIWinCell или turnHumanWinCell возвращают True, то выходим из метода.
     */

    private void aiTurn() {
        if (turnAIWinCell()) {
            return;
        }
        if (turnHumanWinCell()) {
            return;
        }
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!(isEmptyCell(x, y)));
        field[x][y] = O_DOT;
    }

    /**
     * Метод проверяет, может ли выиграть компьютер своим следующим ходом.
     * @return - правда или ложь.
     */

    private boolean turnAIWinCell() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(y, x)) {
                    field[y][x] = O_DOT;
                    if (checkWin(O_DOT)) {
                        return true;
                    }
                    field[y][x] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    /**
     * Метод проверяет, может ли выиграть игрок своим следующим ходом.
     * @return - правда или ложь.
     */

    private boolean turnHumanWinCell() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(y, x)) {
                    field[y][x] = X_DOT;
                    if (checkWin(X_DOT)) {
                        field[y][x] = O_DOT;
                        return true;
                    }
                    field[y][x] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    /**
     * Метод проверки победы.
     * @param gameChip - игровая фишка. [ X_DOT || O_DOT ]
     * @return - True = выиграл || False = нет.
     */

    private boolean checkWin(int gameChip) {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (checkLine(x, y, 1, 0, winLength, gameChip)) {
                    return true;
                }
                if (checkLine(x, y, 1, 1, winLength, gameChip)) {
                    return true;
                }
                if (checkLine(x, y, 0, 1, winLength, gameChip)) {
                    return true;
                }
                if (checkLine(x, y, 1, -1, winLength, gameChip)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод проверки линии.
     * @param x - Координаты по X.
     * @param y - Координаты по Y.
     * @param vx - Вектор по X.
     * @param vy - Вектор по Y.
     * @param winLen - Длина одинаковых фишек для победы.
     * @param gameChip - Игровая фишка. [ X_DOT || O_DOT ]
     * @return - результат проверки. [True || False]
     */

    private boolean checkLine(int x, int y, int vx, int vy, int winLen, int gameChip) {
        final int farX = x + (winLen - 1) * vx;
        final int farY = y + (winLen - 1) * vy;
        if (!isValidCell(farX, farY)) {
            return false;
        }
        for (int i = 0; i < winLen; i++) {
            if (field[y + i * vy][x + i * vx] != gameChip) {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод проверяет, есть ли свободные ячейки на карте или нет, и возвращает логическое значение.
     * @return - результат проверки. [True || False]
     */

    private boolean isFullMap() {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                if (field[x][y] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

}
