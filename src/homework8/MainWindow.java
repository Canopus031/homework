package homework8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MainWindow extends JFrame {

    /**
     * Главное окно игры.
     */

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static final int POS_X = 650;
    private static final int POS_Y = 270;

    private GameMap gameMap;
    private Settings settings;

    MainWindow() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(POS_X, POS_Y);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic-Tac-Toe");
        setImageIcon();

        JButton btnStart = new JButton("<html><body><p style='color: #1c1e23;'>Start Game</p></body></html>");
        btnStart.setBackground(Color.decode("#d4d5d7"));
        btnStart.setFocusable(false);
        JButton btnClose = new JButton("<html><body><p style='color: #1c1e23;'>Close Game</p></body></html>");
        btnClose.setBackground(Color.decode("#d4d5d7"));
        btnClose.setFocusable(false);
        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1, 2));
        panelBottom.add(btnStart);
        panelBottom.add(btnClose);

        settings = new Settings(this);
        gameMap = new GameMap();
        add(panelBottom, BorderLayout.SOUTH);
        add(gameMap, BorderLayout.CENTER);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setResizable(false);
        setVisible(true);
    }

    /**
     * @param mode - Режим игры.
     * @param FieldSizeX - Размер поля по X.
     * @param FieldSizeY - Размер поля по Y.
     * @param WinLength - Длина одинаковых фишек для победы.
     */

    void startNewGame(int mode, int FieldSizeX, int FieldSizeY, int WinLength) {
        gameMap.startNewGame(mode, FieldSizeX, FieldSizeY, WinLength);
    }

    /**
     * Метод получает и устанавливает иконку для приложения.
     */

    private void setImageIcon() {
        String path = "tic-tac-toe.png";
        URL imgURL = MainWindow.class.getResource(path);
        if (imgURL != null) {
            setIconImage(new ImageIcon(imgURL).getImage());
        }
    }
}
