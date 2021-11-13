
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author cesar
 */
public class Lienzo extends Canvas{
    private Image img;
    private int x, y;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.WHITE);
        g.drawImage(img, x, y, 150, 150, this);
    }
    
    
    public void dibujarMascota(Image img){
        this.img = img;
        repaint();
    }
    
    public void moverMascota(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    
}
