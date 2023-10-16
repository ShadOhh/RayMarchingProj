import Mouse.Camera;
import Shapes.CircleObject;
import Shapes.CollisionObject;
import Shapes.RectangleObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Displays and updates the logic for the top-down raymarcher.
 */
public class RaymarcherPanel extends JPanel implements MouseMotionListener {
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
        addMouseMotionListener(this);
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double minDist = computeDistanceToObjects(camera.getPosition()[0]-5, camera.getPosition()[1]-5);
        camera.setRadius((int) minDist);

    }

    public double computeDistanceToObjects(double cameraX, double cameraY){
        double minDist = Double.MAX_VALUE;
        for(CollisionObject object: CollisionObjects){

            if(object instanceof RectangleObject){
                RectangleObject rect = (RectangleObject) object;
                double dist = rect.computeDistance(cameraX,cameraY);
                minDist = Math.min(dist,minDist);

            }else{
                CircleObject circ = (CircleObject) object;
                double dist = circ.computeDistance(cameraX,cameraY);
                minDist = Math.min(dist,minDist);
            }
        }




        return minDist;
    }
}
