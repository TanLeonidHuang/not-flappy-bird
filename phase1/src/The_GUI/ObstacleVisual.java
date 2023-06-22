package The_GUI;

import javax.swing.*;
import java.awt.*;

public class ObstacleVisual extends Visual  {
    public ObstacleVisual(int xpos, int ypos, int obstacle_height){
        this.setBounds(xpos, ypos, 30, obstacle_height);
        this.setBackground(new Color(62, 67, 74));
    }
}