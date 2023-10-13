import Mouse.Camera;
import Shapes.CircleObject;
import Shapes.CollisionObject;
import Shapes.RectangleObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Displays and updates the logic for the top-down raymarcher.
 */
public class RaymarcherPanel extends JPanel {
    private Camera camera;
    
    /**
     * We need to keep a reference to the parent swing app for sizing and 
     * other bookkeeping.
     */
    private final RaymarcherRunner raymarcherRunner;
    private ArrayList<CollisionObject> CollisionObjects;
    
    public RaymarcherPanel(RaymarcherRunner raymarcherRunner) {
        this.raymarcherRunner = raymarcherRunner;
        this.setPreferredSize(new Dimension(this.raymarcherRunner.getFrame().getWidth(),
                this.raymarcherRunner.getFrame().getHeight()));

        this.CollisionObjects = new ArrayList<>();
        populate(20);
        camera = new Camera(10);
        addMouseMotionListener(camera);
    }

    public void populate(int n){
        for(int i = 0;i <= n; i++){
            int SOR = (int)(Math.random()*10);

            if(SOR%2==0){
                CollisionObjects.add(generateRect());
            }else{
                CollisionObjects.add(generateCircle());
            }
        }


    }
    public RectangleObject generateRect(){
        int x,y,w,h;
        w = (int)(Math.random()*200);
        h = (int)(Math.random()*200);
        x = (int)(Math.random()*(1280 - w));
        y = (int)(Math.random()*(640 - h));


        RectangleObject rect = new RectangleObject(h,w,x,y);

        return rect;
    }
    public CircleObject generateCircle() {
        int x,y,r;
        r = (int)(Math.random()*200);
        x = r + (int)(Math.random()*(1280-2*r));
        y = r + (int)(Math.random()*(640-2*r));
        CircleObject circ = new CircleObject(r, x, y);
        return circ;
    }


    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        double minDistance = computeMinDistance();
        camera.setRadius((int) minDistance*2);
        g2d.setColor(Color.BLUE);
        camera.DrawCamera(g2d);

        for(CollisionObject object: CollisionObjects){

            if(object instanceof RectangleObject){
                RectangleObject rect = (RectangleObject) object;
                g2d.setColor(rect.getColor());
                g2d.fillRect(rect.GetX(),rect.GetY(),rect.getWidth(),rect.getHeight());

            }else{

                CircleObject circ = (CircleObject) object;
                g2d.setColor(circ.getColor());
                g2d.fillOval(circ.GetX(),circ.GetY(),circ.getRadius(),circ.getRadius());

            }
        }
//        g2d.fillRect(0, 0, this.getWidth(),this.getHeight());

    }

//    public static boolean isInsideRectangle(double rx, double ry, double w, double h, double px, double py) {
//        double halfWidth = w / 2.0;
//        double halfHeight = h / 2.0;
//
//
//        double left = rx - halfWidth;
//        double right = rx + halfWidth;
//        double top = ry + halfHeight;
//        double bottom = ry - halfHeight;
//
//
//        if (px > left && px < right && py > bottom && py < top) {
//
//            return true;
//        } else {
//
//            return false;
//        }
//    }
    private double computeMinDistance() {
        int[] cPos = camera.getPosition();
        double min = Double.MAX_VALUE;
        for(CollisionObject object: CollisionObjects){
            if(object instanceof RectangleObject){
                RectangleObject rect = (RectangleObject) object;
                double hw = rect.getWidth()/2, hh = rect.getHeight()/2;
                double xLeft = rect.GetX()-hw, xRight = rect.GetX()+hw;
                double yTop = rect.GetY()+hh, yBottom = rect.GetY()-hh;

                double dx = Math.max(Math.max(xLeft - cPos[0], cPos[0] - xRight), 0);
                double dy = Math.max(Math.max(yBottom - cPos[1], cPos[1] - yTop), 0);

                double distance = Math.sqrt(dx * dx + dy * dy);
                min = Math.min(min, distance);


            }else{

                CircleObject circ = (CircleObject) object;
            }
        }
        return min;
    }
}
