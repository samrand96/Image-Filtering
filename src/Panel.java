import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Panel extends JFrame implements MouseMotionListener {
    
    static BufferedImage image;
    static JLabel img;
    static int W,H;
    
    Panel(File file) throws IOException{

        Main.MAIN.setVisible(false);
        image = ImageIO.read(file);
        
        W = image.getWidth();
        H = image.getHeight();
        
        if(((W>900)||(H>900))&&(!(W>=600)||(!(H >= 600)))){
            W %= 600;
            H %= 600;
        }
        
        
        
        setTitle("Image Viewer");
        setSize(W+10,H+20);
        setResizable(false);
        setLocation(300,300);
        img = new JLabel(new ImageIcon(image.getScaledInstance(W, H, Image.SCALE_DEFAULT)));
        
        add(img);
        addMouseMotionListener(this);
        
        Main.cpanel = new ColorPanel();
        Main.filter = new Filter();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        show();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        try{
            Color tmp = new Color(image.getRGB(x,y));
            ColorPanel.x_axis.setText(x + "");
            ColorPanel.y_axis.setText(y + "");
            ColorPanel.red.setText(tmp.getRed() + "");
            ColorPanel.green.setText(tmp.getGreen() + "");
            ColorPanel.blue.setText(tmp.getBlue() + "");
        }catch(Exception ex){ }
    }
    
    
}
