import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Filter extends JFrame implements ActionListener{
    
    private JButton save,close,grey,blur,invert,motionBlur,edgeDetecter,sharpe,bright;
    
    Filter(){

        setTitle("Image Filter");
        setSize(350,150);
        setResizable(false);
        setLayout(new BorderLayout(10,10));
        setLocation(100,100);
        
        JPanel filter = new JPanel();
        filter.setLayout(new GridLayout(2,3));
        
        grey = new JButton("Greyscale");
        sharpe = new JButton("Sharpe");        
        edgeDetecter = new JButton("Edge Detecter");
        invert = new JButton("Invert");
        blur = new JButton("Blur");
        motionBlur = new JButton("Motion Blur");
        
        grey.addActionListener(this);
        sharpe.addActionListener(this);
        edgeDetecter.addActionListener(this);
        invert.addActionListener(this);
        blur.addActionListener(this);
        motionBlur.addActionListener(this);
        
        filter.add(grey);
        filter.add(sharpe);
        filter.add(edgeDetecter);
        filter.add(invert);
        filter.add(blur);
        filter.add(motionBlur);
        
        JPanel op = new JPanel();
        op.setLayout(new GridLayout(1,2));
        
        save = new JButton("Save");
        close = new JButton("New");
        
        save.addActionListener(this);
        close.addActionListener(this);
        
        op.add(save);
        op.add(close);
        
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1,2));
        
        bright = new JButton("Brightness");
        bright.addActionListener(this);
        
        top.add(new Label(" Filter & Save Panel"));
        top.add(bright);
        
        add(top,BorderLayout.NORTH);
        add(filter,BorderLayout.CENTER);
        add(op,BorderLayout.SOUTH);
        
        ///new Histogram();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        
        if(obj.equals(close)){
            
            int result = JOptionPane.showConfirmDialog(null, "You want to close before saving uour work?");

            if(result == JOptionPane.OK_OPTION){
                this.setVisible(false);
                Main.cpanel.setVisible(false);
                Main.panel.setVisible(false);
                Main.MAIN.setVisible(true);
            } else if (result == JOptionPane.NO_OPTION){
                
                JFileChooser fl = new JFileChooser();
                fl.setFileFilter(Main.extFilter);
                int btn = fl.showSaveDialog(null);
                if(btn == JFileChooser.APPROVE_OPTION){
                    File file = fl.getSelectedFile();
                    file.renameTo(new File(file.getName() + ".jpg"));
                    try {
                            ImageIO.write(Panel.image, "jpg", file);
                        } catch (IOException ex) {
                             JOptionPane.showMessageDialog(null, "The Image Couldn't Be Saved Because:\n" + e.toString());
                        }
                    
                    this.setVisible(false);
                    Main.cpanel.setVisible(false);
                    Main.panel.setVisible(false);
                    Main.MAIN.setVisible(true);
                    
                }
                
            }
            
        } else if(obj.equals(save)){
            
            JFileChooser fl = new JFileChooser();
            fl.setFileFilter(Main.extFilter);
            
            int btn = fl.showSaveDialog(null);
            
            if(btn == JFileChooser.APPROVE_OPTION){
                
                File file = fl.getSelectedFile();
                
                String name = file.getName();
                String ext = Util.getType(Main.file.getName());
                
                file.renameTo(new File(name + "." + ext));
                
                try {
                    ImageIO.write(Panel.image, "jpg", file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "The Image Couldn't Be Saved Because:\n" + e.toString());
                }
            }
  
        } else if(obj.equals(grey)){
            
            Panel.image = Effects.grey(Panel.image);
            Panel.img.setIcon(new ImageIcon(Panel.image.getScaledInstance(Panel.W, Panel.H, Image.SCALE_DEFAULT)));
            Panel.img.repaint();
            
        } else if(obj.equals(blur)){

            Panel.image = Effects.blur(Panel.image);
            Panel.img.setIcon(new ImageIcon(Panel.image.getScaledInstance(Panel.W, Panel.H, Image.SCALE_DEFAULT)));
            Panel.img.repaint();
            
        } else if(obj.equals(invert)){

            Panel.image = Effects.invert(Panel.image);
            Panel.img.setIcon(new ImageIcon(Panel.image.getScaledInstance(Panel.W, Panel.H, Image.SCALE_DEFAULT)));
            Panel.img.repaint();
            
            
        } else if(obj.equals(motionBlur)){
            

            Panel.image = Effects.motion(Panel.image);
            Panel.img.setIcon(new ImageIcon(Panel.image.getScaledInstance(Panel.W, Panel.H, Image.SCALE_DEFAULT)));
            Panel.img.repaint();
            
        } else if(obj.equals(edgeDetecter)){
           
            Panel.image = Effects.edge(Panel.image);
            Panel.img.setIcon(new ImageIcon(Panel.image.getScaledInstance(Panel.W, Panel.H, Image.SCALE_DEFAULT)));
            Panel.img.repaint();
            
        } else if(obj.equals(sharpe)){
            
            Panel.image = Effects.sharpe(Panel.image);
            Panel.img.setIcon(new ImageIcon(Panel.image.getScaledInstance(Panel.W, Panel.H, Image.SCALE_DEFAULT)));
            Panel.img.repaint();
        }
        if(obj.equals(bright)){
             
            Panel.image = Effects.bright(Panel.image,20);
            Panel.img.setIcon(new ImageIcon(Panel.image.getScaledInstance(Panel.W, Panel.H, Image.SCALE_DEFAULT)));
            Panel.img.repaint();
        
        }
    }
    
}
