package aplicacion.herramientas.java;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class LoadImageApp extends Component {
          
    BufferedImage img;
    public int scalex=100;
    public int scaley=100;
    public String filename="";
    
    public void setImage(BufferedImage img){
    	this.img=img;
    }
    
    public void load(String filename){
    	this.filename=filename;
    	try {
     	   img = ImageIO.read(new File(filename));
        } catch (IOException e) {
        }
    }
    
    public void setFileName(String filename){
    	this.filename=filename;
    	
    }
    
    public void paint(Graphics g) {
    	Image imgx=img.getScaledInstance(scalex, scaley,Image.SCALE_SMOOTH);
        g.drawImage(imgx, 0, 0, null);
    }

    public LoadImageApp() {
       

    }

    public void write(){
    	try {
    	  ImageIO.write(img, "jpg", new File(filename));
    	}catch(Exception e){
    		
    	}
        
    }
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
          // return new Dimension(img.getWidth(null), img.getHeight(null));
        	return new Dimension(scalex, scaley);
        	
       }
    }


	public int getScalex() {
		return scalex;
	}

	public void setScalex(int scalex) {
		this.scalex = scalex;
	}

	public int getScaley() {
		return scaley;
	}

	public void setScaley(int scaley) {
		this.scaley = scaley;
	}
	
    public static void main(String[] args) {

        JFrame f = new JFrame("Load Image Sample");
            
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        LoadImageApp map=new LoadImageApp();
        map.setFileName("e:/facturas/FCC0001-00035096.jpg");
        map.setScalex(300);
        map.setScaley(300);
        f.add(map);
        f.pack();
        f.setVisible(true);
    }
    
}

