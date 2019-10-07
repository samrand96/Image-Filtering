import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame implements ActionListener {
    
    static Main MAIN;
    static Panel panel;
    static Filter filter;
    static ColorPanel cpanel;
    static FileNameExtensionFilter extFilter;
    static File file;
    private JButton open,close;
    
    Main(){
        try{
             UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
             SwingUtilities.updateComponentTreeUI(this);
             this.pack();
        }catch(Exception e){}

        setTitle("Image Filtering");
        setSize(300,100);
        setLocation(200,200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,2));
        
        extFilter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
        
        open = new JButton("Open an Image");
        close = new JButton("Close the Program");
        add(open);
        add(close);
        add(new JLabel(" Coded by SAM."));
        
        open.addActionListener(this);
        close.addActionListener(this);
        show();
    }
    
    public static void main(String [] args){
        MAIN = new Main();
        
    }
    
        
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj.equals(close))
            System.exit(0);
        else if(obj.equals(open)){
            
            JFileChooser fl = new JFileChooser();
            fl.setFileFilter(extFilter);
            
            int btn = fl.showOpenDialog(null);
            if(btn == JFileChooser.APPROVE_OPTION){
                file = fl.getSelectedFile();
                if(Util.isPicture(file.getName())){
                    try {
                        panel = new Panel(file);
                    }catch(IOException ex){}
                } else {
                    JOptionPane.showMessageDialog(null, "The file that you choose it, is not Supported by this program.");
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "The file choosing cancled by user");
            }
        }
    }
    
}
