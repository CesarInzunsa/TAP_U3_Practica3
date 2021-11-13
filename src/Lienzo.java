
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author cesar
 */
public class Lienzo extends Canvas {

    private Image img;
    private int x, y;
    private int x2, y2;
    private Color color = Color.WHITE;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.WHITE);
        g.drawImage(img, x, y, 150, 150, this);
        g.setColor(color);
        g.fillOval(x2, y2, 50, 50);
    }

    public void dibujarMascota(Image img) {
        this.img = img;
        repaint();
    }

    public void moverMascota(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void pelota(int x, int y, Color color) {
        this.color = color;
        this.x2 = x;
        this.y2 = y;
    }

}
