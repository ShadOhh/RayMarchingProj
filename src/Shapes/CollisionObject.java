package Shapes;

import java.awt.*;

public abstract class CollisionObject {
    private int[] xy = new int[]{0,0};
    public abstract double computeDistance(double cameraX, double cameraY);
    public abstract void drawable(Graphics2D g);



    public CollisionObject(int x, int y){
        this.xy[0] = x;
        this.xy[1] = y;
    }
    public int GetX(){
        return this.xy[0];
    }

    public int GetY(){
        return this.xy[1];
    }


}
