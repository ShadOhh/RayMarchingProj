package Shapes;

import java.awt.*;

/**
 * Initializes a Circle with a designated color its coordinates on the (x,y) plane and a radius. Every method should be self-explanatory
 */
public class CircleObject extends CollisionObject {

    private int r;

    private int d;
    private Color color;
    private int x,y;


    public CircleObject(int radius,int x, int y){
        super(x,y);
        this.r = radius;
        this.d = r * 2;
        this.x = x;
        this.y = y;
        this.color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
    }


    public int getRadius(){
        return this.r;

    }
    public int getDiameter(){
        return this.d;

    }

    public Color getColor(){
        return color;
    }


    @Override
    public double computeDistance(double cameraX, double cameraY) {
        double distance;

        distance = Math.sqrt(Math.pow((cameraX - x),2) + Math.pow((cameraY - y),2)) - r;

        return distance;
    }
}
