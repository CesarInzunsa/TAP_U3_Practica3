
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
    private int energiaComer = 5;
    private Image img;
    private Image imgPequeno;
    private int nivel = 1;
    private Lienzo lienzo;
    private int x = 90, y =50;
    private int desplazamiento = 5;

    public Tamagochi(Ventana ventana, String nombre, Lienzo lienzo) {
        this.puntero = ventana;
        this.puntero.setTitle(nombre);
        this.lienzo = lienzo;
    }

    @Override
    public void run() {
        super.run();

        do {
            try {
                mostrarMascota();
                moverMascota();
                puntero.jLabel1.setText(energia + "");
                puntero.jLabel4.setText(energiaMaxima + "");
                puntero.jLabel5.setText(nivel + "");
                --energia;
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tamagochi.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (energia >= -1);

    }

    private void mostrarMascota() {

        switch (estado) {
            case 1:
                img = new ImageIcon(getClass().getResource("/Imagenes/normal.png")).getImage();
                //imgPequeno = escalarImagen(img);
                //puntero.jLabel2.setIcon(imgPequeno);
                lienzo.dibujarMascota(img);
                if (energia <= (energiaMaxima * 0.2)) {
                    estado = 2;
                }
                break;
            case 2:
                img = new ImageIcon(getClass().getResource("/Imagenes/cansado.png")).getImage();
                //imgPequeno = escalarImagen(img);
                //puntero.jLabel2.setIcon(imgPequeno);
                lienzo.dibujarMascota(img);
                if (energia <= 0) {
                    estado = 3;
                }
                break;
            case 3:
                puntero.setTitle("FIN DEL JUEGO");
                img = new ImageIcon(getClass().getResource("/Imagenes/tumba.png")).getImage();
                //imgPequeno = escalarImagen(img);
                //puntero.jLabel2.setIcon(imgPequeno);
                lienzo.dibujarMascota(img);
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tamagochi.class.getName()).log(Level.SEVERE, null, ex);
                }
                stop();
                break;

            case 4:
                try {
                img = new ImageIcon(getClass().getResource("/Imagenes/dormir.png")).getImage();
                //imgPequeno = escalarImagen(img);
                //puntero.jLabel2.setIcon(imgPequeno);
                lienzo.dibujarMascota(img);
                sleep(3000);
                energia = energiaMaxima;
                estado = 1;
            } catch (InterruptedException ex) {
                Logger.getLogger(Tamagochi.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case 5:
                try {
                img = new ImageIcon(getClass().getResource("/Imagenes/comer.png")).getImage();
                //imgPequeno = escalarImagen(img);
                //puntero.jLabel2.setIcon(imgPequeno);
                lienzo.dibujarMascota(img);
                sleep(3000);
                energia += energiaComer;
                if (energia > energiaMaxima) {
                    energia = energiaMaxima;
                }
                if (energia <= (energiaMaxima * 0.2)) {
                    estado = 2;
                } else {
                    estado = 1;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Tamagochi.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

        }

    }

    public void dormir() {
        estado = 4;
    }

    public void comer() {
        estado = 5;
    }

    public void jugar() {

        energiaMaxima += 3;
        energiaComer += 1;
        energia -= 5;
        nivel++;
    }
    
    public void moverMascota(){
        y += desplazamiento;
        if (y >= 200 || y<=80) {
            desplazamiento *= -1;
        }
        lienzo.moverMascota(x, y);
    }

}
