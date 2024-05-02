//16-04-2024
package programacion2_pro5;
//@author jesus
import Ui.Menu;
import java.awt.geom.RoundRectangle2D;
public class Programacion2_Pro5 {
    public static void main(String[] args) {
        //System.out.println("hola mundo");
        Menu Vie = new Menu();        
        Vie.setSize(700, 600);
        Vie.setShape(new RoundRectangle2D.Double(0, 0, 700, 600, 40, 40));
        Vie.setResizable(false);
        Vie.setLocationRelativeTo(null);
        Vie.setVisible(true);
    }
}