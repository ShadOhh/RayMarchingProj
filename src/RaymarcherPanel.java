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
public class RaymarcherPanel extends JPanel implements MouseMotionListener{
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
        camera = new Camera(1);
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
        w = (int)(Math.random()*200)+10;
        h = (int)(Math.random()*200)+10;
        x = (int)(Math.random()*(1280 - w));
        y = (int)(Math.random()*(640 - h));


        RectangleObject rect = new RectangleObject(h,w,x,y);

        return rect;
    }
    public CircleObject generateCircle() {
        int x,y,r;
        r = (int)(Math.random()*200)+10;
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

        //forEach Loop that loops through the list and paints it.
        for(CollisionObject x: CollisionObjects){
            x.drawable((Graphics2D) g);
        }


    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double minDist = computeDistanceToObjects(camera.getPosition()[0], camera.getPosition()[1]);
        camera.setRadius((int) minDist*2);

    }
    public double computeDistanceToObjects(double cameraX, double cameraY){
        double minDist = Double.MAX_VALUE;
        for(CollisionObject object: CollisionObjects) {
            minDist = Math.min(object.computeDistance(cameraX, cameraY), minDist);
        }
        return minDist;
    }
}
