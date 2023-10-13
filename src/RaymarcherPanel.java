import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Displays and updates the logic for the top-down raymarcher.
 */
public class RaymarcherPanel extends JPanel { 
    
    /**
     * We need to keep a reference to the parent swing app for sizing and 
     * other bookkeeping.
     */
    private final RaymarcherRunner raymarcherRunner;
    
    public RaymarcherPanel(RaymarcherRunner raymarcherRunner) {
        this.raymarcherRunner = raymarcherRunner;
        this.setPreferredSize(new Dimension(this.raymarcherRunner.getFrame().getWidth(),
                this.raymarcherRunner.getFrame().getHeight())); 
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, this.getWidth(),this.getHeight());
    }
}
