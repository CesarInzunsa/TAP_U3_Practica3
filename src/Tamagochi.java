
import java.awt.Color;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author cesar
 */
public class Tamagochi extends Thread {

    private Ventana puntero;
    private Lienzo lienzo = new Lienzo();
    private int energia = 100;
    private int energiaMaxima = 100;
    private int energiaComer = 5;
    private int nivel = 1;
    private int estado = 1;
    private Image img;
    private int x = 90, y = 50;
    private int desplazamiento = 5;
    private int x2 = 20, y2 = 20;
    private int desplazamientoX2 = 10;
    private int desplazamientoY2 = 10;
    private boolean h = false;
    private int tiempoDormir = 1000;

    public Tamagochi(Ventana ventana, String nombre) {
        this.puntero = ventana;
        this.puntero.setTitle(nombre);
        lienzo.setSize(337, 209);
        lienzo.setLocation(1, 1);
        ventana.jPanel4.add(lienzo);
    }

    @Override
    public void run() {
        super.run();

        do {
            try {
                mostrarMascota();
                moverMascota();
                moverPelota();
                puntero.jLabel1.setText(energia + "");
                puntero.jLabel4.setText(energiaMaxima + "");
                puntero.jLabel5.setText(nivel + "");
                --energia;
                sleep(tiempoDormir);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tamagochi.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (energia >= -1);

    }

    private void mostrarMascota() {

        switch (estado) {
            case 1:
                img = new ImageIcon(getClass().getResource("/Imagenes/normal.png")).getImage();
                lienzo.dibujarMascota(img);
                if (energia <= (energiaMaxima * 0.2)) {
                    estado = 2;
                }
                break;
            case 2:
                img = new ImageIcon(getClass().getResource("/Imagenes/cansado.png")).getImage();
                lienzo.dibujarMascota(img);
                if (energia <= 0) {
                    estado = 3;
                }
                break;
            case 3:
                puntero.setTitle("FIN DEL JUEGO");
                img = new ImageIcon(getClass().getResource("/Imagenes/tumba.png")).getImage();
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

        h = !h;

        if (h) {
            puntero.jButton3.setText("PARAR");
            energiaMaxima += 3;
            energiaComer += 1;
            energia -= 5;
            nivel++;
            tiempoDormir = 300;
        } else {
            puntero.jButton3.setText("JUGAR");
            tiempoDormir = 1000;
        }

    }

    private void moverPelota() {
        if (h) {
            lienzo.pelota(x2, y2, Color.RED);

            y2 += desplazamientoY2;
            if (y2 >= 159 || y2 <= 0) {
                desplazamientoY2 *= -1;
            }
            x2 += desplazamientoX2;
            if (x2 >= 287 || x2 <= 0) {
                desplazamientoX2 *= -1;
            }
        }else{
            lienzo.pelota(1, 1, Color.WHITE);
        }
    }

    public void moverMascota() {
        y += desplazamiento;
        if (y >= 200 || y <= 80) {
            desplazamiento *= -1;
        }
        lienzo.moverMascota(x, y);
    }

}
