package The_GUI;

import GameProgram.Listener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GUI extends JPanel {

    private final Rectangle player;

    private final List<Rectangle> obstacles = new ArrayList<>();

    private final List<Rectangle> goldenApples = new ArrayList<>();

    private final List<Rectangle> poisonApples = new ArrayList<>();

    private final int width = 300;
    private final int height = 600;

    public JFrame frame = new JFrame();

    //source to help make initial GUI frame: https://www.guru99.com/java-swing-gui.html#4

    public GUI(Rectangle player, Listener myListener){
        addKeyListener(myListener);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        frameSetup();
        frameVisible();
        this.player = player;
    }

    public void frameSetup(){
        frame.setTitle("Not Flappy Bird");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void frameVisible(){
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void close() {
        setFocusable(false);
    }

    public void addObstacle(Rectangle obstacle) {
        obstacles.add(obstacle);
    }

    public void moveGamePlayer(int y) {
        player.setLocation(player.x, player.y + y);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.orange);
        g.fillRect(0, height - 120, width, 120);

        g.setColor(Color.green);
        g.fillRect(0, height - 120, width, 20);

        g.setColor(Color.RED);
        g.fillRect(player.x, player.y, player.width, player.height);

        for (Rectangle o: obstacles){
            paintObstacle(g, o);
        }

        for(Rectangle poison: poisonApples) {
            paintPoisonApple(g, poison);
        }

        for (Rectangle golden: goldenApples) {
            paintGoldenApple(g, golden);
        }
    }

    public void paintObstacle(Graphics g, Rectangle column){
        g.setColor(Color.BLACK);
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void paintPoisonApple(Graphics g, Rectangle column) {
        g.setColor(Color.BLACK);
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void paintGoldenApple(Graphics g, Rectangle column) {
        g.setColor(Color.ORANGE);
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void moveAllLeft() {
        for (Rectangle i : obstacles) {
            i.setLocation(i.x - 1, i.y);
        }

        for (Rectangle i : goldenApples) {
            i.setLocation(i.x - 1, i.y);
        }

        for (Rectangle i : poisonApples) {
            i.setLocation(i.x - 1, i.y);
        }
        repaint();
    }

    public void removeFirstGoldenApple() { goldenApples.remove(0); }

    public void removeFirstPoisonApple() { poisonApples.remove(0);  }

    public void removeGoldenApple(int x, int y) {
        goldenApples.removeIf(golden -> golden.x == x && golden.y == y);
    }

    public void addPoisonApple(Rectangle poison) { poisonApples.add(poison); }

    public void addGoldenApple(Rectangle golden) { goldenApples.add(golden); }

    public void removeObstacle() {
        obstacles.remove(0);
        obstacles.remove(0);
    }

}



