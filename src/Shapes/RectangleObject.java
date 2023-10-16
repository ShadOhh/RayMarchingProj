package Shapes;

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
    private int x,y,cX,cY;

    public RectangleObject(int h, int w, int x, int y){
        super(x,y);

        this.height = h;
        this.width = w;
        this.x = x-width/2;
        this.y = y-height/2;
        this.cX = x;
        this.cY = y;
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
        Point2D.Double p1 = new Point2D.Double(cX, cY);
        Point2D.Double p2 = new Point2D.Double(cX + width, cY);
        Point2D.Double p3 = new Point2D.Double(cX + width, cY + height);
        Point2D.Double p4 = new Point2D.Double(cX, cY + height);

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

    @Override
    public void drawable(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(x,y,width,height);

        g2d.setColor(Color.BLACK);
        g2d.fillOval(cX,cY,4,4);
    }
}
