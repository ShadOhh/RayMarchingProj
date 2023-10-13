package Shapes;

import java.awt.*;

/**
 * RectangleObject initializes a rectangle with width w, height h, and a coordinate on the (x,y) plane. The method should be self-explanatory.
 */

public class RectangleObject extends CollisionObject{
    private int height;
    private int width;
    private Color color;

    public RectangleObject(int h, int w, int x, int y){
        super(x,y);

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
}
