package homework8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameMap extends JPanel {

    public static final int MODE_HVSAI = 1;
    public static final int MODE_HVSHM = 2;

    private static final Random RANDOM = new Random();
    private static final int EMPTY_DOT = 0;
    private static final int HUMAN_DOT = 1;
    private static final int AI_DOT = 2;
    private static final int PADDING_DOT = 5;

    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;
    private int[][] field;

    private int cellWidth;
    private int cellHeight;
    private boolean initialMap;

    GameMap() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
        initialMap = false;
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        field = new int [fieldSizeX][fieldSizeY];
        initialMap = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        render(graphics);
    }

    private void render(Graphics graphics) {
        if (!initialMap) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;
        graphics.setColor(Color.GRAY);

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            graphics.drawLine(x, 0, x, height);
        }

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            graphics.drawLine(0,y,width,y);
        }

        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isEmptyCell(x, y)) {
                    continue;
                }
                if (field[x][y] == HUMAN_DOT) {
                    graphics.setColor(new Color(1, 1, 255));
                    graphics.fillOval(x * cellWidth + PADDING_DOT, y * cellHeight + PADDING_DOT, cellWidth - PADDING_DOT * 2, cellHeight - PADDING_DOT * 2);
                } else if (field[x][y] == AI_DOT) {
                    graphics.setColor(Color.RED);
                    graphics.fillOval(x * cellWidth + PADDING_DOT, y * cellHeight + PADDING_DOT, cellWidth - PADDING_DOT * 2, cellHeight - PADDING_DOT * 2);
                } else {
                    throw new RuntimeException("Error");
                }
            }
        }
    }

    void update(MouseEvent e) {
        if (!initialMap) {
            return;
        }

        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)){
            return;
        }
        field[cellX][cellY] = HUMAN_DOT;
        if (checkWin(HUMAN_DOT)) {
            repaint();
            return;
        }
        if (isFullMap()) {
            repaint();
            return;
        }
        aiTurn();
        repaint();
        if (checkWin(AI_DOT)) {
            repaint();
            return;
        }
        if (isFullMap()) {
            repaint();
            return;
        }
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[x][y] == EMPTY_DOT;
    }


    private void aiTurn() {
//        if (turnAIWinCell()) {
//            return;
//        }
//        if (turnHumanWinCell()) {
//            return;
//        }
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!(isEmptyCell(x, y)));
        field[x][y] = AI_DOT;
    }

    private boolean turnAIWinCell() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y)) {
                    field[y][x] = AI_DOT;
                    if (checkWin(AI_DOT)) {
                        return true;
                    }
                    field[y][x] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    private boolean turnHumanWinCell() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y)) {
                    field[y][x] = HUMAN_DOT;
                    if (checkWin(HUMAN_DOT)) {
                        field[y][x] = AI_DOT;
                        return true;
                    }
                    field[y][x] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

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

    private boolean checkLine(int x, int y, int vx, int vy, int winLen,int gameChip) {
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

    private boolean isFullMap() {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                if (field[x][y] == EMPTY_DOT) return false;
            }
        }
        return true;
    }
}
