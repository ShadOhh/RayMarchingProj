package Shapes;

import Mouse.Camera;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * RectangleObject initializes a rectangle with width w, height h, and a coordinate on the (x,y) plane. The method should be self-explanatory.
 */

public class RectangleObject extends CollisionObject{
    private int height;
    private int width;
    private Color color;
    private int x,y;

    public RectangleObject(int h, int w, int x, int y){
        super(x,y);
        this.x = x;
        this.y = y;
        this.height = h;
        this.width = w;
        this.color = this.color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
    }
    public int getWidth(){
        return width;

    }
    public int getHeight(){
        return height;

    }
    public Color getColor(){
        return color;
    }

    @Override
    public double computeDistance(double cameraX, double cameraY) {
        Point2D.Double p1 = new Point2D.Double(x, y);
        Point2D.Double p2 = new Point2D.Double(x + width, y);
        Point2D.Double p3 = new Point2D.Double(x + width, y + height);
        Point2D.Double p4 = new Point2D.Double(x, y + height);

        Point2D.Double camera = new Point2D.Double(cameraX, cameraY);

        Line2D.Double l1 = new Line2D.Double(p1, p2);
        Line2D.Double l2 = new Line2D.Double(p2, p3);
        Line2D.Double l3 = new Line2D.Double(p3, p4);
        Line2D.Double l4 = new Line2D.Double(p4, p1);

        double dist1 = l1.ptSegDist(camera);
        double dist2 = l2.ptSegDist(camera);
        double dist3 = l3.ptSegDist(camera);
        double dist4 = l4.ptSegDist(camera);

        return Math.min(Math.min(dist1, dist2), Math.min(dist3, dist4));
    }
}
