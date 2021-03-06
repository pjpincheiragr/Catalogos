package aplicacion.compras.carga.items.logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.geom.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class DisplayCanvas extends JPanel  implements Printable{
	  int x, y;
	  int current_x,current_y;
	  boolean move=true;
	  BufferedImage bi;
	  double scx=640;
	  double scy=480;
	  int zoom_x=0;
	  int zoom_y=0;
	  boolean proportional=true;
	  int lumix=0;
	  
	  private double angle=0.0;
	  private Image imgx=null;
	  private String filename="";
	  LookupTable lookupTable;
	  private BufferedImage master=null;
	  private int max_width=-1;
	  private int max_height=-1;
	  private boolean tools=true;
	  private boolean doScale=true;
	  public void setTools(boolean tools){
		  this.tools=tools;
	  }
	  
	  public void dispose(){
		  bi=null;
		  master=null;
		  lookupTable=null;
	  }
	  
	  public void setProportional(boolean proportional){
		  this.proportional=proportional;
	  }
	  
	  public void setImage(BufferedImage img){
		 if(img!=null){
			 this.bi=img;
			 if (tools){
				  this.master=bi;  
			  }else{
				  this.master=null;
			  }
			  
			  max_width=img.getWidth();
			  max_height=img.getHeight();
		 }
	  }
	  
	  public void dropMaster(){
		  this.master=null;
		  
	  }
	  public BufferedImage getBufferedImage(){
		  return master;
	  }
	  public void setMove(boolean move){
		  this.move=move;
	  }
	  public boolean getMove(){
		  return this.move;
	  }
	  
	  public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
	        if (pageIndex > 0) {
	          return(NO_SUCH_PAGE);
	        } else {
	          Graphics2D g2d = (Graphics2D)g;
	          int h=g.getClipBounds().height;
	          int w=g.getClipBounds().width;
	          System.out.println("GRAPHICS>"+w+":"+h);
	          g2d.scale(0.7, 0.7);
	          g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	          // Turn off double buffering
	          
	          paint(g2d);
	          // Turn double buffering back on
	          return(PAGE_EXISTS);
	        }
	      }
	  public void print(){
	    	PrinterJob printJob = PrinterJob.getPrinterJob();
	        printJob.setPrintable(this);
	        if (printJob.printDialog())
	          try { 
	            printJob.print();
	          } catch(PrinterException pe) {
	            System.out.println("Error printing: " + pe);
	       }
	    }
	  
	  public void applyFilter() {
		  if (lookupTable!=null){
			  LookupOp lop = new LookupOp(lookupTable, null);
			    lop.filter(bi, bi);  
		  }
		    
		  }
	  
	  /*public DisplayCanvasOld(String filename) {
		  this.filename=filename;
	    setBackground(Color.white);
	    setSize(850, 400);
	    addMouseMotionListener(new MouseMotionHandler());
	    addMouseListener(new _MouseListener());
	    zoom(0);
	  }*/
	  public DisplayCanvas() {
		  setBackground(Color.white);
		    setSize(850, 400);
		    addMouseMotionListener(new MouseMotionHandler());
		    addMouseListener(new _MouseListener());
		  /*
		  if (img!=null){
			  this.bi=img;
			    
			    
			    zoom(0);	  
		  }else{
			  img=null;
		  }
		*/
	  }
	  public void brightenLUT() {
		  //System.out.println("brighten lut");
		    short brighten[] = new short[256];
		    for (int i = 0; i < 256; i++) {
		      short pixelValue = (short) (i + 10*lumix);
		      if (pixelValue > 255)
		        pixelValue = 255;
		      else if (pixelValue < 0)
		        pixelValue = 0;
		      brighten[i] = pixelValue;
		    }
		    lookupTable = new ShortLookupTable(0, brighten);
		  }

	  	public void setZoom(int zoom){
	  		this.zoom_x=0;
	  	}
	  	
	  	public void setLumix(int lumix){
	  		this.lumix=lumix;
	  	}
	  	
	  	
		  public void darkenLUT() {
			  //System.out.println("darken lut");
		    short brighten[] = new short[256];
		    for (int i = 0; i < 256; i++) {
		      short pixelValue = (short) (i - 10*-lumix);
		      if (pixelValue > 255)
		        pixelValue = 255;
		      else if (pixelValue < 0)
		        pixelValue = 0;
		      brighten[i] = pixelValue;
		    }
		    lookupTable = new ShortLookupTable(0, brighten);
		  }

		  public void contrastIncLUT() {
		    short brighten[] = new short[256];
		    for (int i = 0; i < 256; i++) {
		      short pixelValue = (short) (i * 1.2);
		      if (pixelValue > 255)
		        pixelValue = 255;
		      else if (pixelValue < 0)
		        pixelValue = 0;
		      brighten[i] = pixelValue;
		    }
		    lookupTable = new ShortLookupTable(0, brighten);
		  }

		  public void contrastDecLUT() {
		    short brighten[] = new short[256];
		    for (int i = 0; i < 256; i++) {
		      short pixelValue = (short) (i / 1.2);
		      if (pixelValue > 255)
		        pixelValue = 255;
		      else if (pixelValue < 0)
		        pixelValue = 0;
		      brighten[i] = pixelValue;
		    }
		    lookupTable = new ShortLookupTable(0, brighten);
		  }

		  public void reverseLUT() {
		    byte reverse[] = new byte[256];
		    for (int i = 0; i < 256; i++) {
		      reverse[i] = (byte) (255 - i);
		    }
		    lookupTable = new ByteLookupTable(0, reverse);
		  }
		  public void loadFileFromDisk(){
			  Image image = getToolkit().getImage(filename);
			  

			    MediaTracker mt = new MediaTracker(this);
			    mt.addImage(image, 1);
			    try {
			      mt.waitForAll();
			    } catch (Exception e) {
			     System.out.println("Exception while loading image.");
			    }

			    if (image.getWidth(this) == -1) {
			     System.out.println("no file");
			      //System.exit(0);
			    }
			    BufferedImage bi = new BufferedImage(image.getWidth(this), image.getHeight(this),
			        BufferedImage.TYPE_INT_ARGB);
			    this.setImage(bi);
			    
			    
		  }
		  public void fitin(int x,int y){
		  
		  Image image = getToolkit().getImage(filename);
		  

		    MediaTracker mt = new MediaTracker(this);
		    mt.addImage(image, 1);
		    try {
		      mt.waitForAll();
		    } catch (Exception e) {
		      //System.out.println("Exception while loading image.");
		    }

		    if (image.getWidth(this) == -1) {
		      //System.out.println("no gif file");
		      //System.exit(0);
		    }
		    try {
		    bi = new BufferedImage(image.getWidth(this), image.getHeight(this),
		        BufferedImage.TYPE_INT_ARGB);
		    Graphics2D big = bi.createGraphics();
		    
		    BufferedImage bi2 = new BufferedImage(image.getWidth(this), image.getHeight(this),
			        BufferedImage.TYPE_INT_ARGB);
			Graphics2D back = bi2.createGraphics();
			    
		    //System.out.println("image width:"+image.getWidth(this));
		    
		    //System.out.println("image scale"+scx+"+x"+x+" =? "+scx);
		    double sc=(x/image.getWidth(this));
		    
		    int scalex=new Double(image.getWidth(this)*sc).intValue();
		    int scaley=new Double(image.getHeight(this)*sc).intValue();
		    
		    Double difx=0.0;
		    Double dify=0.0;
		    try{
		    difx=new Double((scalex-imgx.getWidth(this))/2);
		    dify=new Double((scaley-imgx.getHeight(this))/2);
		    }catch(Exception ee){
		    	
		    }
		    
		    this.x=this.x-difx.intValue();
		    this.y=this.y-dify.intValue();
		    //System.out.println("difx:"+difx);
		    //System.out.println("dify:"+dify);
		    
		    back.drawImage(image, 0, 0, this);
		    imgx=bi2.getScaledInstance(scalex, scaley,Image.SCALE_SMOOTH);
		    back.dispose();
		    
		    
		    big.drawImage(imgx, 0, 0, this);
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		  
	  }	  
	  public void setSCX(int scale){
		  this.scx=scale;
	  }
	  
	  public void maximizar(){
		  this.resetVars();
		  int w=this.getWidth();
		  int h=this.getHeight();
		  
		  
		  //System.out.println("width?"+w+" <> "+_w);
		  if (max_width>w){
			  double _x=new Double(max_width);
			  double x=new Double(w);
			  double scalex=-(100-100*x/_x);
			  //System.out.println("SCale"+scale);
			  zoom_x=new Double(scalex).intValue();
			  
		  }else{
			  if (w>max_width){
				  double _x=new Double(max_width);
				  double x=new Double(w);
				  double scale=(100-100*_x/x);
				 // System.out.println("SCale"+scale);
				  zoom_x=new Double(scale).intValue();
			  }
		  }
		  if (max_height>h){
			  double _y=new Double(max_height);
			  double y=new Double(h);
			  double scaley=-(100-100*y/_y);
			  //System.out.println("SCale"+scale);
			  zoom_y=new Double(scaley).intValue();
			  
		  }else{
			  if (h>max_height){
				  double _y=new Double(max_height);
				  double y=new Double(h);
				  double scale=(100-100*_y/y);
				 // System.out.println("SCale"+scale);
				  zoom_y=new Double(scale).intValue();
			  }
		  }
		  if (proportional){
			  zoom_y=zoom_x;
		  }
		  this.repaint();
	  }
	  
	  
	  public void reduce(){
		  this.resetVars();
		  int w=this.getWidth();
		  int h=this.getHeight();
		  int max_width=980;
		  System.out.println("max?"+max_width);
		  System.out.println("width?"+w);
		  System.out.println("height?"+h);
		  if (max_width>w){
			  double _x=new Double(max_width);
			  double x=new Double(w);
			  double scalex=-(100-100*x/_x);
			  //System.out.println("SCale"+scale);
			  zoom_x=new Double(scalex).intValue();
			  
		  }else{
			  if (w>max_width){
				  double _x=new Double(max_width);
				  double x=new Double(w);
				  double scale=(100-100*_x/x);
				 // System.out.println("SCale"+scale);
				  zoom_x=new Double(scale).intValue();
			  }
		  }
		  if (max_height>h){
			  double _y=new Double(max_height);
			  double y=new Double(h);
			  double scaley=-(100-100*y/_y);
			  //System.out.println("SCale"+scale);
			  zoom_y=new Double(scaley).intValue();
			  
		  }else{
			  if (h>max_height){
				  double _y=new Double(max_height);
				  double y=new Double(h);
				  double scale=(100-100*_y/y);
				 // System.out.println("SCale"+scale);
				  zoom_y=new Double(scale).intValue();
			  }
		  }
		  if (proportional){
			  zoom_y=zoom_x;
		  }
		  this.repaint();
	  }
	  public BufferedImage getZoom(BufferedImage master){
		  
		  Image image =null;
		  BufferedImage imgz=null;
		  int height=0;
		  int width=0;
		  
		  if (master!=null){
				  width=master.getWidth();
				  height=master.getHeight();  
			  }
			  
		  
			    try {
				    /*bi = new BufferedImage(width, height,
				        BufferedImage.TYPE_INT_ARGB);*/
			    	
			  
				    Graphics2D big = master.createGraphics();
				    
				    BufferedImage bi2 = new BufferedImage(width,height,
					        BufferedImage.TYPE_INT_ARGB);
				
					    
				    //System.out.println("image width:"+width);
				    
				    scx=width*(100+zoom_x)/100;
				    scy=height*(100+zoom_y)/100;
				    //System.out.println("image scale"+scx+"+x"+zoom+" =? "+scx_old);
				    double sc_x=(scx/width);
				    double sc_y=(scy/height);
				    if (zoom_x==0){
				    	sc_x=1;
				    }
				    if (zoom_y==0){
				    	sc_y=1;
				    }
				    int scalex=new Double(width*sc_x).intValue();
				    int scaley=new Double(height*sc_y).intValue();
				    if (proportional){
				    	scaley=new Double(height*sc_x).intValue();
				    }
				    //System.out.println("scalex: "+scalex);
				    //System.out.println("scaley: "+scaley);
				 	imgz=this.getScale(master, scalex, scaley);
				 	
				    }catch(Exception e){
				    	//e.printStackTrace();
				    }

				    return imgz;
	  }
	  
	  public BufferedImage toBufferedImage(Image image) {
			
	        if (image instanceof BufferedImage) {
	            return (BufferedImage)image;
	        }
	    
	        // This code ensures that all the pixels in the image are loaded
	        image = new ImageIcon(image).getImage();
	    
	        // Determine if the image has transparent pixels; for this method's
	        // implementation, see e661 Determining If an Image Has Transparent Pixels
	        //boolean hasAlpha = hasAlpha(image);
	    
	        // Create a buffered image with a format that's compatible with the screen
	        BufferedImage bimage = null;
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        try {
	            // Determine the type of transparency of the new buffered image
	            int transparency = Transparency.TRANSLUCENT;
	          /*  if (hasAlpha) {
	                transparency = Transparency.BITMASK;
	            }*/
	    
	            // Create the buffered image
	            GraphicsDevice gs = ge.getDefaultScreenDevice();
	            GraphicsConfiguration gc = gs.getDefaultConfiguration();
	            bimage = gc.createCompatibleImage(
	                image.getWidth(null), image.getHeight(null), transparency);
	        } catch (HeadlessException e) {
	            // The system does not have a screen
	        }
	    
	        if (bimage == null) {
	            // Create a buffered image using the default color model
	            int type = BufferedImage.TYPE_INT_RGB;
	            /*if (hasAlpha) {
	                type = BufferedImage.TYPE_INT_ARGB;
	            }*/
	            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
	        }
	    
	        // Copy image to buffered image
	        Graphics g = bimage.createGraphics();
	    
	        // Paint the image onto the buffered image
	        g.drawImage(image, 0, 0, null);
	        g.dispose();
	    
	        return bimage;
	    }
		public  Image scale(Image image, int width, int height)  {
			
			BufferedImage bsrc = toBufferedImage(image);
			BufferedImage bdest =new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bdest.createGraphics();
			AffineTransform at =AffineTransform.getScaleInstance((double)width/bsrc.getWidth(),
						(double)height/bsrc.getHeight());
			g.drawRenderedImage(bsrc,at);
			image=Toolkit.getDefaultToolkit().createImage(bdest.getSource());
			return image;
			
		}
	  
		public  BufferedImage getScale(BufferedImage bsrc, int width, int height)  {
			
			
			BufferedImage bdest =new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bdest.createGraphics();
			AffineTransform at =AffineTransform.getScaleInstance((double)width/bsrc.getWidth(),
						(double)height/bsrc.getHeight());
			g.drawRenderedImage(bsrc,at);
			
			return bdest;
			
		}
		
		public void zoom(int x){
			zoom_x=x;
			zoom_y=x;
			this.repaint();
		}
		
		public void zoom_rem(int x){
	  		zoom_x-=x;
	  		zoom_y-=x;
	  		this.repaint();
	  		
		}
		
	  public void zoom_add(int x){
		  		zoom_x+=x;
		  		zoom_y+=x;
		  		this.repaint();
	  }
	  
	  public void lumix_rem(int x){
	  		lumix-=x;
	  		//System.out.println("lumix?"+lumix);
	  		this.repaint();
	  		
		}
		
	  public void lumix_add(int x){
		  	lumix+=x;
		  	//System.out.println("lumix?"+lumix);
		  	this.repaint();
	  }

	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2D = (Graphics2D) g;
	    AffineTransform affineTransform = new AffineTransform();
	    affineTransform.setToTranslation(x, y);
		affineTransform.rotate(Math.toRadians(angle), 0,0);
		if (tools){
			bi=this.getZoom(master);	
		}else{
			bi=this.getZoom(bi);
		}
		
		LookupOp lop =null;
		if (lumix>0){
				this.brightenLUT();
				this.applyFilter();
		}else{
			if (lumix<0){
				this.darkenLUT();
				this.applyFilter();
			}
		}
		
		 g2D.drawImage(bi, affineTransform, this);
	    
	  }
	  public void resetAngle(){
		  angle=0;
	  }
	  
	  public void resetVars(){
		  this.angle=0;
		  this.x=0;
		  this.y=0;
		  this.zoom_x=0;
		  this.zoom_y=0;
		  this.lumix=0;
		  
	  }
	  
	  public void setAngle(double angle){
		  this.angle+=angle;
		  
		  this.repaint();
	  }
	  public void move_Y(int inc){
		y=y+inc;
    	current_y=y;
    	System.out.println("current y?"+current_y);
    	repaint();
  		
      }
	  public void move_X(int inc){
			x=x+inc;
	    	current_x=x;
	    	repaint();
	  		
	      }
	  
	  
	  class _MouseListener implements MouseListener {
			//where initialization occurs:
	      //Register for mouse events on blankArea and the panel.
	    

	  public void mousePressed(MouseEvent e) {
		  if (!e.isAltDown()){
			  current_x=e.getX();
			     current_y=e.getY();	  
		  }
	     
	  }

	  public void mouseReleased(MouseEvent e) {
	     
	  }

	  public void mouseEntered(MouseEvent e) {
	     
	  }

	  public void mouseExited(MouseEvent e) {
	     
	  }

	  public void mouseClicked(MouseEvent e) {
	     if (e.getClickCount()==2){
	    	 
	    	 
	     }
	   
	  }
	  }
	  class MouseMotionHandler extends MouseMotionAdapter {
	    public void mouseDragged(MouseEvent e) {
	    	if (move){
	    	int mx=e.getX();
	    	int my=e.getY();
	    	if (current_x==-1){
	    		current_x=mx;
	        	current_y=my;
	    	}else{
	    		x=x-(current_x-mx);
	    		y=y-(current_y-my);
	    		current_x=mx;
	        	current_y=my;
	    	}
	    	repaint();
	    	}
	        
	      
	    }
	    
	  }
	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}

	public boolean isDoScale() {
		return doScale;
	}

	public void setDoScale(boolean doScale) {
		this.doScale = doScale;
	}
	}
