package The_GUI;

import GameProgram.Reward;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GUI extends JPanel implements GUIInterface {

    private static GamePlayerVisual g = new GamePlayerVisual(20, 20);

    public static ObstacleVisual top_o1 = new ObstacleVisual(120, 0, 320);
    public static ObstacleVisual bottom_o1 = new ObstacleVisual(120, 440, 160);

    public static ObstacleVisual top_o2 = new ObstacleVisual(120, 0, 160);
    public static ObstacleVisual bottom_o2 = new ObstacleVisual(120, 280, 320);

    public static ObstacleVisual top_o3 = new ObstacleVisual(120, 0, 240);
    public static ObstacleVisual bottom_o3 = new ObstacleVisual(120, 360, 240);

    private static List<GoldenAppleVisual> goldenapplevisualList = new ArrayList<GoldenAppleVisual>();

    private static List<PoisonAppleVisual> poisonapplevisualList = new ArrayList<PoisonAppleVisual>();

    private static List<ObstacleVisual> obstaclevisualList = new ArrayList<ObstacleVisual>();

    private static JFrame frame;

    //source to help make initial GUI frame: https://www.guru99.com/java-swing-gui.html#4

    public GUI(GamePlayerVisual p){
        frame_setup();
        frame_visible();
        player = p;
    }

    public static void frame_setup(){

        frame = new JFrame("Not Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(300, 600);
        frame.add(player);

    }

    public static void frame_visible(){

        frame.setResizable(false);
        frame.setVisible(true);

    }


    public void moveGamePlayerVisual(int ypos) {
        g.setYpos(ypos);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.orange);
        g.fillRect(0, HEIGHT - 120, WIDTH, 120);

        g.setColor(Color.green);
        g.fillRect(0, HEIGHT - 120, WIDTH, 20);

        g.setColor(Color.RED);
        g.fillRect((int) birdX, (int) birdY, 20, 20);

        for (Rectangle r: obstacles){
            paintObstacle(g, r);
        }
    }

    public void paintObstacle(Graphics g, Rectangle column){
        g.setColor(Color.BLACK);
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public static void makeObstacleVisual(int xpos, int height) {
        int bottom_obs_y = height + 100;
        ObstacleVisual top_obs = new ObstacleVisual(xpos, 0, height);
        ObstacleVisual bottom_obs = new ObstacleVisual(xpos, bottom_obs_y, 600 - height);
        //frame.add(top_obs);
        //frame.add(bottom_obs);
        obstaclevisualList.add(top_obs);
        obstaclevisualList.add(bottom_obs);

    }

    public static void makeGoldenAppleVisual(int xpos, int ypos) {

        GoldenAppleVisual ga = new GoldenAppleVisual(xpos, ypos);

        frame.add(ga);
        goldenapplevisualList.add(ga);

    }

    public static void makePoisonAppleVisual(int xpos, int ypos) {

        PoisonAppleVisual pa = new PoisonAppleVisual(xpos, ypos);

        frame.add(pa);
        poisonapplevisualList.add(pa);

    }

    public static void moveAllLeftVisual() {

        for (ObstacleVisual i : obstaclevisualList) {
            int x_position = i.getXPos();
            i.setXPos(x_position - 1);
        }

        for (GoldenAppleVisual i : goldenapplevisualList) {
            int x_position = i.getXpos();
            i.setXpos(x_position - 1);
        }

        for (PoisonAppleVisual i : poisonapplevisualList) {
            int x_position = i.getXpos();
            i.setXpos(x_position - 1);
        }

    }

}



