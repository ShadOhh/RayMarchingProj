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
    private int d;
    public Camera(int d){
        this.d = d;
        this.r = d/2;
    }
    public void setRadius(int newDiameter) {
        this.d = newDiameter;
        this.r = d/2;
    }

    public void DrawCamera(Graphics2D g2d){
        g2d.drawOval(xy[0]-r,xy[1]-r,d,d);
        g2d.setColor(Color.black);
        g2d.fillOval(xy[0],xy[1],5,5);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e){
        xy[0] = e.getX();
        xy[1] = e.getY();
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
