
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author cesar
 */
public class Tamagochi extends Thread {

    private int energia = 100;
    private Ventana puntero;
    private int estado = 1;
    private int energiaMaxima = 100;
    ImageIcon img;
    ImageIcon imgPequeno;
    private int x, y;

    public Tamagochi(Ventana ventana, String nombre) {
        this.puntero = ventana;
        this.puntero.setTitle(nombre);
    }

    @Override
    public void run() {
        super.run();

        do {
            try {
                mostrarMascota();
                puntero.jLabel1.setText(energia + "");
                --energia;
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tamagochi.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (energia >= 0);

    }

    private void mostrarMascota() {
        
        switch (estado) {
            case 1:
                img = new ImageIcon(getClass().getResource("/Imagenes/normal.png"));
                imgPequeno = escalarImagen(img, 200, 140);
                puntero.jLabel2.setIcon(imgPequeno);
                if (energia == (energiaMaxima * 0.3)) {
                    estado = 2;
                }
                break;
            case 2:
                img = new ImageIcon(getClass().getResource("/Imagenes/cansado.png"));
                imgPequeno = escalarImagen(img,200,160 );
                puntero.jLabel2.setIcon(imgPequeno);
                if (energia == 1) {
                    estado = 3;
                }
                break;   
            case 3:
                img = new ImageIcon(getClass().getResource("/Imagenes/tumba.png"));
                imgPequeno = escalarImagen(img,200,140);
                puntero.jLabel2.setIcon(imgPequeno);
                break;
        }

    }
    
    private ImageIcon escalarImagen(ImageIcon img, int x, int y) {
        Image imgLabel = img.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(imgLabel);
    }
    
    public void dormir(){
        
    }
    
    public void comer(){
        
    }
    
    public void jugar(){
        
    }
    
}
