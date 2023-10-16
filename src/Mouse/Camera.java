package Mouse;

import Shapes.CircleObject;
import Shapes.CollisionObject;
import Shapes.RectangleObject;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Camera implements MouseMotionListener {
    private int[] xy = new int[]{0,0};
    private int r;
    public Camera(int r){
        this.r = r;
    }
    public void setRadius(int newRadius) {
        this.r = newRadius;
    }

    public void DrawCamera(Graphics2D g2d){
        g2d.drawOval(xy[0],xy[1],r,r);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e){
        xy[0] = e.getX()-5;
        xy[1] = e.getY()-5;
    }

    public int[] getPosition() {
        return xy;
    }
    public int GetX(){
        return xy[0];
    }
    public int GetY(){
        return xy[1];
    }
}
