package The_GUI;

import javax.swing.*;
import java.awt.*;

public class GamePlayerVisual extends Visual {
    public GamePlayerVisual(int xpos, int ypos){
        this.setBounds(xpos, ypos, 25, 15);
        this.setBackground(new Color(129, 50, 168));
    }
}