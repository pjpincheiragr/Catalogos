package aplicacion.herramientas.java.visualfx;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.event.*;
public class TranslucentPanel extends JPanel   {
    BufferedImage underFrameImg;
    int paintX=0;
    int paintY=0;
    float alfa=0.1f;
    
    public void setPaintX(int i){
    	this.paintX=i;
    }
    public void setPaintY(int i){
    	this.paintY=i;
    }
    public TranslucentPanel() {
        super();
        
        setOpaque(true);
    }
    public void setAlfa(float b){
    	this.alfa=b;
    }
    public void setUnderFrame(BufferedImage under){
    	this.underFrameImg=under;
    	System.out.println("Seteada la imagen y??");
    }
    public void paint(Graphics g) {
    	Graphics2D g2d = (Graphics2D)g;
   		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alfa));
        super.paint(g);
    }
    
    protected void paintComponent(Graphics g) {
    	Graphics2D g2d = (Graphics2D)g;
    	AlphaComposite Alfa=((AlphaComposite)g2d.getComposite());
    	
    	g2d.setComposite(Alfa.getInstance(AlphaComposite.SRC_OVER, alfa));
    	
    	super.paintComponent(g);
        //g.drawImage(underFrameImg,paintX,paintY,null);
    }

}
