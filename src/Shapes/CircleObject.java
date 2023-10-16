package Shapes;

import java.awt.*;

/**
 * Initializes a Circle with a designated color its coordinates on the (x,y) plane and a radius. Every method should be self-explanatory
 */
public class CircleObject extends CollisionObject {

    private int r;

    private int d;
    private Color color;
    private int x,y,cX,cY;


    public CircleObject(int d,int x, int y){
        super(x,y);
        this.r = d/2;
        this.d = d;
        this.x = x - r;
        this.y = y - r;
        this.cX = x;
        this.cY = y;
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

        distance = Math.sqrt(Math.pow((cameraX - cX),2) + Math.pow((cameraY - cY),2)) - r;

        return distance;
    }
    @Override
    public void drawable(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(x ,y,d,d);


        g2d.setColor(Color.BLACK);
        g2d.fillOval(cX,cY,4,4);
    }
}
