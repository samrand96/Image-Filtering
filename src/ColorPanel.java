import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JFrame {
    
    static JLabel red,green,blue,x_axis,y_axis;
    
    ColorPanel(){
        setTitle("Image Information");
        setSize(250,150);
        setLocation(100,100);
        setResizable(false);
        setAlwaysOnTop(true);
        setLayout(new GridLayout(5,2));
        
        red = new JLabel("");
        green = new JLabel("");
        blue = new JLabel("");
        x_axis = new JLabel("");
        y_axis = new JLabel("");
        
        add(new JLabel("The X-Axis: "));
        add(x_axis);
        add(new JLabel("Ths Y-Axis: "));
        add(y_axis);
        add(new JLabel("The Red: "));
        add(red);
        add(new JLabel("The Green: "));
        add(green);
        add(new JLabel("The Blue: "));
        add(blue);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        show();
    }
}
