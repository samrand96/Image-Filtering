import java.awt.Color;
import java.awt.image.BufferedImage;


public class Util {
    
    public static boolean isPicture(String fileName){
        String fileType = getType(fileName);
        if(fileType.equals("jpg")) return true;
        else if(fileType.equals("jpeg")) return true;
        else if(fileType.equals("png")) return true;
        else if(fileType.equals("bmp")) return true;
        else return false;
    }
    
    public static String getType(String fileName){
        return fileName.substring(fileName.lastIndexOf('.')+1);
    }
    
    protected static BufferedImage algorithm(BufferedImage img,int [][] filter,double factor,double bias){
        
        int con = (int) filter.length/2;
        Color tmp;
        
        for(int y = con ; y < img.getHeight()-con ; y++ ){
            for(int x = con ; x < img.getWidth()-con ; x++ ){
                double red=0.0,green=0.0,blue=0.0;
                for(int filterY = 0 ; filterY < filter.length ; filterY++ ){
                    for(int filterX = 0 ; filterX < filter[0].length ; filterX++){
                        
                        tmp = new Color(img.getRGB(x-con+filterX, y-con+filterY));
                        red += tmp.getRed() * filter[filterX][filterY];
                        green += tmp.getGreen() * filter[filterX][filterY];
                        blue += tmp.getBlue() * filter[filterX][filterY];
                    
                    }
                }
                red = RGB(factor * red + bias);
                green = RGB(factor * green + bias);
                blue = RGB(factor * blue + bias);
                
                img.setRGB(x, y, (new Color((int)Math.round(red), (int)Math.round(green),(int)Math.round(blue))).getRGB());

            }
        }
        
        return img;
    }
    
    protected static int RGB(double RGB){
        if(RGB > 255) return 255;
        else if(RGB < 0) return 0;
        else return (int) Math.round(RGB);
    }

    
}
