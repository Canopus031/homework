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
        map.setLayout(new GridLayout(FieldSizeX, FieldSizeY));
        for (int i = 0; i < (FieldSizeX * FieldSizeY); i++) {
            JButton emptyDot = new JButton(EMPTY_DOT);
            emptyDot.setBackground(Color.decode("#d4d5d7"));
            emptyDot.setFocusable(false);
            map.add(emptyDot);
        }
        add(map);
        setVisible(true);
    }

}
