package homework8;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Settings extends JFrame {

    /**
     *  ласс настроек игры.
     */

    private static final int WINDOW_WIDTH = 360;
    private static final int WINDOW_HEIGHT = 270;

    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String FIELD_SIZE_PREFIX = "Field size is: ";
    private static final String WIN_LENGTH_PREFIX = "Win length is: ";

    private JRadioButton humVSAI;
    private JRadioButton humVSHUM;
    private JSlider slideWinLen;
    private JSlider slideFieldSize;

    private MainWindow mainWindow;
    private GameMap gameMap;

    Settings(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setLayout(new GridLayout(10, 1));
        setTitle("Settings Game");
        setImageIcon();
        setupWindowBounds();
        addChoiceGameMode();
        addGameSetup();
        JButton btnPlayGame = new JButton("<html><body><p style='color: #1c1e23;'>Play new game</p></body></html>");
        btnPlayGame.setBackground(Color.decode("#d4d5d7"));
        btnPlayGame.setFocusable(false);
        add(btnPlayGame);
        btnPlayGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPlayGameClick();
                setVisible(false);
            }
        });
        setResizable(false);
        setVisible(false);
    }

    /**
     * ћетод задает размер окна и устанавливает координаты расположени€ в центре главного окна MainWindow
     */

    private void setupWindowBounds() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Rectangle gameMainWindow = mainWindow.getBounds();
        int xPos = (int) gameMainWindow.getCenterX() - WINDOW_WIDTH / 2;
        int yPos = (int) gameMainWindow.getCenterY() - WINDOW_HEIGHT / 2;
        setLocation(xPos, yPos);
    }

    /**
     * ћетод добавл€ет выбор режима игры.
     */

    private void addChoiceGameMode() {
        JLabel labelMode = new JLabel("Choice mode");
        humVSAI = new JRadioButton("Human vs AI", true);
        humVSHUM = new JRadioButton("Human vs HUMAN");

        ButtonGroup gameMode = new ButtonGroup();
        add(labelMode);
        gameMode.add(humVSAI);
        gameMode.add(humVSHUM);
        add(humVSAI);
        add(humVSHUM);
    }

    /**
     * ћетод добавл€ет возможность выбора размера пол€, а также длины выигрышных фишек
     */

    private void addGameSetup() {
        JLabel labelField = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        JLabel labelWin = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH);
        slideFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slideWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        slideFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = slideFieldSize.getValue();
                labelField.setText(FIELD_SIZE_PREFIX + currentValue);
                slideWinLen.setMaximum(currentValue);
            }
        });
        slideWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWin.setText(WIN_LENGTH_PREFIX + slideWinLen.getValue());
            }
        });
        add(new JLabel("Choose field map size"));
        add(labelField);
        add(slideFieldSize);
        add(new JLabel("Choose win length"));
        add(labelWin);
        add(slideWinLen);
    }

    /**
     * ћетод провер€ет, какой режим игры был выбран, и передает его и другие параметры игры в аргументы метода startNewGame(mode, fieldSize, fieldSize, winLength).
     */

    private void btnPlayGameClick() {
        int mode;

        if (humVSAI.isSelected()) {
            mode = GameMap.MODE_HVSAI;
        } else if (humVSHUM.isSelected()) {
            mode = GameMap.MODE_HVSHM;
        } else {
            throw new RuntimeException("Unexpected game mode");
        }

        int fieldSize = slideFieldSize.getValue();
        int winLength = slideWinLen.getValue();

        mainWindow.startNewGame(mode, fieldSize, fieldSize, winLength);
    }

    /**
     * ћетод получает и устанавливает иконку дл€ приложени€.
     */

    private void setImageIcon() {
        String path = "tic-tac-toe.png";
        URL imgURL = MainWindow.class.getResource(path);
        if (imgURL != null) {
            setIconImage(new ImageIcon(imgURL).getImage());
        }
    }

}
