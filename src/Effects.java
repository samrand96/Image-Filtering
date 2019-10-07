
import java.awt.Color;
import java.awt.image.BufferedImage;


public class Effects extends Util {
    
    public static BufferedImage grey(BufferedImage img){
        
        Color tmp;
        
        for(int y = 0 ; y < img.getHeight() ; y++ ){
            for(int x = 0 ; x < img.getWidth() ; x++ ){
                tmp = new Color(img.getRGB(x, y));
                int pixle = (int) Math.round((tmp.getRed()*.21) + (tmp.getGreen()*.72) + (tmp.getBlue()*.07));
                img.setRGB(x, y, (new Color(pixle,pixle,pixle).getRGB()));
            }
        }
        
        return img;
    }
    
    public static BufferedImage blur(BufferedImage img){
        
        Color cnt,p00,p01,p02,p10,p12,p20,p21,p22;
        int red,green,blue;
        
        for(int y = 1 ; y < img.getHeight() -1 ; y++ ){
            for(int x = 1 ; x < img.getWidth() -1 ; x++ ){
                
                cnt = new Color(img.getRGB(x, y));
                p00 = new Color(img.getRGB(x-1, y-1));
                p01 = new Color(img.getRGB(x, y-1));
                p02 = new Color(img.getRGB(x+1, y-1));
                p10 = new Color(img.getRGB(x-1, y));
                p12 = new Color(img.getRGB(x+1, y));
                p20 = new Color(img.getRGB(x-1, y+1));
                p21 = new Color(img.getRGB(x, y+1));
                p22 = new Color(img.getRGB(x+1, y+1));
                
                red = (int) Math.round((cnt.getRed() + p00.getRed() + p01.getRed() + p02.getRed() + p10.getRed() + p12.getRed() + p20.getRed() + p21.getRed() + p22.getRed())/9);
                green = (int) Math.round((cnt.getGreen() + p00.getGreen() + p01.getGreen() + p02.getGreen() + p10.getGreen() + p12.getGreen() + p20.getGreen() + p21.getGreen() + p22.getGreen())/9);
                blue = (int) Math.round((cnt.getBlue() + p00.getBlue() + p01.getBlue() + p02.getBlue() + p10.getBlue() + p12.getBlue() + p20.getBlue() + p21.getBlue() + p22.getBlue())/9);
                
                img.setRGB(x, y, (new Color(red,green,blue).getRGB()));
            }
        }        
        
        return img;
    }
    
    public static BufferedImage sharpe(BufferedImage img){
        int [] [] filter = new int [] []
        {{-1, -1, -1},
         {-1,  9, -1},
         {-1, -1, -1}};
        
        double factor=1.0,bias=0.0;
        
        return algorithm(img,filter,factor,bias);
    }
    
    public static BufferedImage motion(BufferedImage img){
        
        int coY = 9/2,coX = 9/2;
        
        int Width = img.getWidth(),Height = img.getHeight();
        
        double factor = 1.0 / 9.0 , bias = 0.0;
        Color tmp;
        
        for(int y = coY ; y < Height-coY ; y++ ){
            for(int x = coX ; x < Width-coX ; x++ ){
                
                double red=0.0,green=0.0,blue=0.0;
                
                for(int i = 0 ; i < 9 ; i++ ){
                        
                        tmp = new Color(img.getRGB(x-coX+i, y-coY+i));
                        red += tmp.getRed();
                        green += tmp.getGreen();
                        blue += tmp.getBlue();
                        
                }
                
                red = (factor * red + bias);
                green = (factor * green + bias);
                blue = (factor * blue + bias);
                
                img.setRGB(x, y, (new Color((int)Math.round(red), (int)Math.round(green),(int)Math.round(blue))).getRGB());
                
                
            }
        }
        
        
        
        return img;
    }
    
    public static BufferedImage invert(BufferedImage img){
        
        Color tmp;
        
        for(int y = 0 ; y < img.getHeight() ; y++ ){
            for(int x = 0 ; x < img.getWidth() ; x++){
                tmp = new Color(img.getRGB(x, y));
                img.setRGB(x, y, (new Color(255-tmp.getRed(),255-tmp.getGreen(),255-tmp.getBlue()).getRGB()));
            }
        }
        
        return img;
    }
    
    public static BufferedImage edge(BufferedImage img){
        int [] [] filter1 = new int [] []
        {{-1, 0, 1},
         {-2, 0, 2},
         {-1, 0, 1}
        };
        int [] [] filter2 = new int [] []
        {{ 1, 2, 1},
         { 0, 0, 0},
         {-1,-2,-1}
        };      
        
        img = grey(img);
        
        int con = (int) filter1.length/2;
        
        Color tmp;
        
        for(int y = con ; y < img.getHeight()-con ; y++ ){
            for(int x = con ; x < img.getWidth()-con ; x++ ){
                
                int grey1=0,grey2=0;
                for(int i = 0 ; i < filter1.length ; i++ ){
                    for(int j = 0 ; j < filter1[0].length ; j++){
                        grey1 += (new Color(img.getRGB(x-con+i, y-con+j))).getRed() * filter1[i][j];
                        grey2 += (new Color(img.getRGB(x-con+i, y-con+j))).getRed() * filter2[i][j];
                    }
                }
                int grey = 255 - RGB(Math.sqrt(grey1*grey1 + grey2*grey2));
                img.setRGB(x, y, (new Color(grey, grey, grey)).getRGB());

            }
        }
        return sharpe(sharpe(img));
    }
    
    public static BufferedImage bright(BufferedImage img,int bright){
        
        for(int y = 0 ; y < img.getHeight(); y++){
            for(int x = 0 ; x < img.getWidth() ; x++){
                Color tmp = new Color(img.getRGB(x, y));
                img.setRGB(x, y, (new Color(RGB(tmp.getRed()+bright),RGB(tmp.getGreen()+bright),RGB(tmp.getBlue()+bright)).getRGB()));
            }
        }
        return img;
    }
    

























    public static BufferedImage grey1(BufferedImage img){
        
        Color tmp;
        
        for(int y = 0 ; y < img.getHeight() ; y++ ){
            for(int x = 0 ; x < img.getWidth() ; x++ ){
                tmp = new Color(img.getRGB(x, y));
                int pixle = (int) (tmp.getRed() + tmp.getGreen() + tmp.getBlue())/3;
                img.setRGB(x, y, (new Color(pixle,pixle,pixle).getRGB()));
            }
        }
        
        return img;
    }
}