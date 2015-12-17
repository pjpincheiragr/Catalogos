package aplicacion.sistema.acercade.logic;

/*
 * Name:     Water
 * Date:     December 2004
 * Author:   Neil Wallis
 * Purpose:  Simulate ripples on water.
 */

import java.awt.*;
import java.net.*;
import java.io.*;

import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;



import java.net.URL;
import java.awt.Dimension;
import aplicacion.modelo.logic.*;
import aplicacion.sistema.acercade.gui._Frame;
import aplicacion.sistema.acercade.logic._Data;

public class _Logic extends Logic {
	
	   class _Runnable implements Runnable{
			public void run() {
				
		        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		        long startTime = System.currentTimeMillis();

		        while (Thread.currentThread() == animatorThread) {
		      	  
		          newframe();
		          source.newPixels();
		          offGraphics.drawImage(image,0,0,width,height,null);
		          
		          
		          frame.setOffImage(offImage);
		          frame.repaint();

		          try {
		              startTime += delay;
		              Thread.sleep(Math.max(0,startTime-System.currentTimeMillis()));
		          } catch (InterruptedException e) {
		        	  e.printStackTrace();
		              break;
		          }
		        }
		      }
		    		   
	   }

	
    Robot robot;  //  @jve:decl-index=0:
    BufferedImage screenImg;  //  @jve:decl-index=0:
    Rectangle screenRect;  //  @jve:decl-index=0:

    String str;
    int width,height,hwidth,hheight;
    MemoryImageSource source;  //  @jve:decl-index=0:
    Image image, offImage;
    Graphics offGraphics;  //  @jve:decl-index=0:
    int i,a,b;
    int MouseX,MouseY;
    int fps,delay,size;

    short ripplemap[];
    int texture[];
    int ripple[];
    int oldind,newind,mapind;
    int riprad;
    Image im;
    private String revision="";  //  @jve:decl-index=0:
    Thread animatorThread;  //  @jve:decl-index=0:
    boolean frozen = false;
    private _Runnable runnable;  //  @jve:decl-index=0:
    private _Frame frame;
	private _Data data;  //  @jve:decl-index=0:
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		
	    
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}


	public void exit_command(){
		runnable=null;
		animatorThread=null;
		super.exit_command();
	}
	
	public void init() {
		
		runnable=new _Runnable();
      frame.addMouseListener(this.getConstructor().getMouseListener());
      frame.addMouseMotionListener(this.getConstructor().getMouseMotionListener());
      Object[][] ruser=data.getParametroSqlite("Version");
      if (ruser!=null){
    	  if (ruser.length>0){
    		  revision=ruser[0][1].toString();	  
    	  }
      }
      
      InetAddress Ip =null;
      try {
		Ip=InetAddress.getLocalHost();
	} catch (UnknownHostException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
      this.createScreenImage();
      try {
          
          MediaTracker mt=new MediaTracker(frame);
          im =(Image) this.screenImg;
          Font font = new Font("Serif", Font.PLAIN, 30);
          Graphics2D gp = this.screenImg.createGraphics();
          gp.setColor(Color.LIGHT_GRAY);
          gp.setFont(font);
          gp.drawString("Beta RC 2009", 74,165);
          font = new Font("Serif", Font.PLAIN, 10);
          gp.setFont(font);
          gp.drawString("revision:"+revision, 100,195);
          gp.drawString("usuario:"+this.getConstructor().getIduser(), 100,215);
          
          gp.drawString("host:"+Ip.getHostAddress()+" "+Ip.getHostName(), 100,235);
          offImage =(Image) this.screenImg;
          mt.addImage(im,0);
          try {
            mt.waitForID(0);
            } catch (InterruptedException e) {
              return;
            }
        } catch(Exception e) {}
      

      //How many milliseconds between frames?
      fps=500;
      delay = (fps > 0) ? (1000 / fps) : 100;

      width = frame.getWidth();
      height = frame.getHeight();
      hwidth = width>>1;
      hheight = height>>1;
      riprad=2;

      size = width * (height+2) * 2;
      ripplemap = new short[size];
      ripple = new int[width*height];
      texture = new int[width*height];
      oldind = width;
      newind = width * (height+3);

      PixelGrabber pg = new PixelGrabber(im,0,0,width,height,texture,0,width);
      try {
        pg.grabPixels();
        } catch (InterruptedException e) {}

        
      source = new MemoryImageSource(width, height, ripple, 0, width);
      source.setAnimated(true);
      source.setFullBufferUpdates(true);
      image = frame.createImage(source);
      
      offGraphics = offImage.getGraphics();
      
     
    	  
     }
      
    

    public void start() {
    	
    	
        if (frozen) {
            //Do nothing.
        } else {
            //Start animation thread
            if (animatorThread == null) {
                //animatorThread = new Thread(new _Runnable());
                animatorThread = new Thread(runnable);
            }
            	animatorThread.start();
			
        }
        
    }

    public void stop() {
      animatorThread = null;
    }
    
    public void createScreenImage() {
    	try {
            if (robot==null)
                robot=new Robot();
        }
        catch (AWTException ex) {
            ex.printStackTrace();
        }
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        int y=new Double(frame.getLocation().getY()).intValue();
        int x=new Double(frame.getLocation().getX()).intValue();
        screenRect=new Rectangle(frame.getLocationOnScreen().x,frame.getLocationOnScreen().y,frame.getWidth(),frame.getHeight());
        screenImg=robot.createScreenCapture(screenRect);
        
        
    }

    


    public void frozen() {
      if (frozen) {
        frozen = false;
        start();
      } else {
        frozen = true;
        animatorThread = null;
      }
    }

    





    public void disturb(int dx, int dy) {
      for (int j=dy-riprad;j<dy+riprad;j++) {
        for (int k=dx-riprad;k<dx+riprad;k++) {
          if (j>=0 && j<height && k>=0 && k<width) {
	    ripplemap[oldind+(j*width)+k] += 4096;            
          } 
        }
      }
      
    }

    public void newframe() {
      //Toggle maps each frame
      i=oldind;
      oldind=newind;
      newind=i;

      i=0;
      mapind=oldind;
      for (int y=0;y<height;y++) {
        for (int x=0;x<width;x++) {
	  short data = (short)((ripplemap[mapind-width]+ripplemap[mapind+width]+ripplemap[mapind-1]+ripplemap[mapind+1])>>1);
          data -= ripplemap[newind+i];
          data -= data >> 5;
          ripplemap[newind+i]=data;

	  //where data=0 then still, where data>0 then wave
	  data = (short)(1024-data);

          //offsets
  	  a=((x-hwidth)*data/1024)+hwidth;
          b=((y-hheight)*data/1024)+hheight;

 	  //bounds check
          if (a>=width) a=width-1;
          if (a<0) a=0;
          if (b>=height) b=height-1;
          if (b<0) b=0;

          ripple[i]=texture[a+(b*width)];
          mapind++;
	  i++;
        }
      }
    }

    public void run(){
    	runnable.run();
    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

