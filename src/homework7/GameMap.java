package homework7;

import javax.swing.*;
import java.awt.*;

public class GameMap extends JPanel {

    public static final int MODE_HVSAI = 1;
    public static final int MODE_HVSHM = 2;
    public static final String EMPTY_DOT = "_";

    GameMap() {
        setBackground(Color.BLACK);
        setVisible(false);
    }

    void startNewGame(int mode, int FieldSizeX, int FieldSizeY, int WinLength) {
        JPanel map = new JPanel();
        JButton[] emptyDot = new JButton[FieldSizeX * FieldSizeY];
        map.setLayout(new GridLayout(FieldSizeX, FieldSizeY));
        for (int i = 0; i < (FieldSizeX * FieldSizeY); i++) {
            emptyDot[i] = new JButton(EMPTY_DOT);
            emptyDot[i].setBackground(Color.decode("#d4d5d7"));
            emptyDot[i].setFocusable(false);
            map.add(emptyDot[i]);
        }
        add(map);
        setVisible(true);
    }

}
