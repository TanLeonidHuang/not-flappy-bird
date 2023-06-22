package GameProgram;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class    GamePlayer{
    private final String username;
    private final Point location;
    private final boolean deadEnd;

    private final Dimension dimensions = new Dimension(20, 20);


    public GamePlayer(String name){
        username = name;
        deadEnd = false;
        // this.name = username from account
        location = new Point(50, 100);

    }

    public String getUsername(){
        return this.username;
    }

    public void setLocation(double xPos, double yPos){
        this.location.setLocation(xPos, yPos);
    }

    public boolean isDead(){
        return this.deadEnd;
    }

    public List<Double> getLocation(){
        List<Double> coordinates = new ArrayList<>();
        double xPos = this.location.getX();
        double yPos = this.location.getY();
        coordinates.add(xPos);
        coordinates.add(yPos);
        return coordinates;
    }


    public String toString(){
        if (deadEnd) {
            return "GAME OVER";
        }
        else {
            return "GAME ON";
        }
    }

    public int getWidth() { return dimensions.width; }

    public int getHeight() { return dimensions.height; }

}
