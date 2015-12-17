package aplicacion.herramientas.java.image.logic;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.MySQL;
import aplicacion.herramientas.java.image.gui.*;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.herramientas.java.image.logic._Data;

import javax.imageio.ImageIO;
import javax.swing.*;

public class _Logic extends Logic{
	private Component ipanel=null;
	private JButton B=null;
	private boolean eliminar=false;
	private double proportion=0;
	private _Data data;
	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}
	public boolean isEliminar() {
		return eliminar;
	}


	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}

	BufferedImage img;
    public int scalex=200;
    public int scaley=200;
    public String filename="";
    public _Logic(){
    
    }
    
    public void clean(){
    	img=null;
    }
    public void setProportional(){
    	System.out.println("Determinando Proporcion");
    	try {
    		System.out.println("Proporcion Imagen:"+proportion);
    		System.out.println("Escala x:"+scalex);
    		System.out.println("Escala y:"+scaley);
    		
			int _scaley=new Double(scalex/this.proportion).intValue();
			if (_scaley>scaley){
				scalex=new Double(scaley*this.proportion).intValue();
			}else{
				scaley=_scaley;
			}
			if (ipanel!=null){
				ipanel.repaint();	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public boolean  write(){
    	boolean ok=true;
    	
    	try {
    	  ImageIO.write(img, "jpg", new File(filename));
    	}catch(Exception e){
    	
    		ok=false;
    	}
    	
    	return ok;
    }
    public void setFileName(String filename){
    	this.filename=filename;
    	
    }
    
    public BufferedImage getImage(){
    	return img;
    }
    public ScrollableImage getOriginalImagePanel(){
    	
    	
    	ScrollableImage si=new ScrollableImage(new ImageIcon(img),1);
    	
    	
    	return si;
    }
    
    public ImageComponent getImagePanel(){
    	ImageComponent ipanel=new ImageComponent(img){
    		
    		public void paint(Graphics g) {
    			Image imgx=null;
    			if (scalex>0 & scaley>0){
    				imgx=img.getScaledInstance(scalex, scaley,Image.SCALE_SMOOTH);	
    			}else{
    				imgx=img.getScaledInstance(100, 100,Image.SCALE_SMOOTH);
    			}
    			
    	    	g.drawImage(imgx, 0, 0, null);
            }	
    	};
    	
    	ipanel.addMouseMotionListener(this.getConstructor().getMouseMotionListener());
    	ipanel.addMouseListener(this.getConstructor().getMouseListener());
    	return ipanel;
    }
    
    public void mouseClick(Component c,int x, int y,java.awt.Point p){
    	Graphics g=c.getGraphics();
    	if (y>0 & y<20){
    		if (x>24 & x<44){
    				ampliar(new Double(p.getX()).intValue(),new Double(p.getY()).intValue());
    		}
    		if (x>0 & x<24){
    			eliminar(c);	
    		}
    		Image imgx=img.getScaledInstance(scalex, scaley,Image.SCALE_SMOOTH);
        	g.drawImage(imgx, 0, 0, null);
        	
    	}else {
    		
    	}
    }
    
    private void eliminar(Component c){
    	
    	if (preguntar("confirmar","Confirma eliminar esta imagen?")){
    		this.getConstructor().dispose();
    		System.out.println(c.getParent());
    		if (c.getParent()!=null){
    			if (c.getParent() instanceof JPanel){
    				JPanel panel=(JPanel) c.getParent();
    				int cps=panel.getComponentCount();
    				
    				panel.remove(c);
    				this.sortComponents(panel);
    				panel.revalidate();
    				panel.repaint();
    			}
    		}
    	}
    	
    }
    
    private void sortComponents(JPanel panel){
    	int c=panel.getComponentCount();
    	int comps=0;
    	for (int i=0;i<c;i++){
			if (panel.getComponent(i) instanceof ImageComponent){
				
				ImageComponent IC=(ImageComponent)panel.getComponent(i);
				IC.setBounds(comps*90+50,30,80,100);		
				comps++;
			}
		}
    }
    private void ampliar(){
    	this.ampliar(100,100);
    }
    
    private void ampliar(int x, int y){
    	JFrame frame=new JFrame();
    	int h=img.getHeight()+20;
    	int w=img.getWidth()+20;
    	frame.setSize(w,h);
    	int h1=new Double(h/2).intValue();
    	frame.setLocation(x,y);
    	JScrollPane j=new JScrollPane();
    	j.setBounds(0, 0, w, h);
    	
    	j.setViewportView(this.getOriginalImagePanel());
    	j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	frame.add(j);
    	frame.setVisible(true);
    }
    
    public void mouseExited(Component c){
    	
    	Graphics g=c.getGraphics();
    	
    	Image imgx=null;
		try {
			imgx = img.getScaledInstance(scalex, scaley,Image.SCALE_SMOOTH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (imgx!=null){
			g.drawImage(imgx, 0, 0, null);	
		}
    	
    }
    
    public void mouseMove(Component c,int x, int y){
    	
    				
    		
    		Graphics g=c.getGraphics();
        	if (y>0 & y<20){
        		
        		
        		Image cancel=new ImageIcon(getClass().getResource(
    			"/icons/stock_calc-cancel.png")).getImage();
        		
        		Image zoom=new ImageIcon(getClass()
    					.getResource("/icons/gnome-searchtool.png")).getImage();
        		if (eliminar){
        			g.drawImage(cancel, 4, 0, null);	
        		}
            	
            	g.drawImage(zoom, 24, 0, null);
            	/*
            	if (x>34 & x<54){
            		g.drawString("Ampliar", x+10, y+30);	
        		}
        		if (x>0 & x<24){
        			
        			g.drawString("Eliminar", x+10, y+30);	
        		}*/
        	}else{
        		Image zoom=new ImageIcon(getClass()
    					.getResource("/icons/gnome-searchtool.png")).getImage();
        		g.drawImage(zoom, 24, 0, null);
        	}	
    		
    	
    }
    
    
        
    public void loadImage(){
    	img=null;
    	
    	System.out.println("loadImage> "+filename);
    	try {
      	   img = ImageIO.read(new File(filename));
         } catch (IOException e) {
        	 e.printStackTrace();
         }
         if (img!=null){
             double w=img.getWidth();
             double h=img.getHeight();
             System.out.println("Imagen x:"+w);
             System.out.println("Imagen y:"+h);
             try {
    			this.proportion=new Double(w/h);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		System.out.println("proprocion:"+proportion);
            ipanel=this.getImagePanel();
            ipanel.repaint();
            
//            JFrame fx=new JFrame();
//            fx.setSize(500,500);
//            fx.add(this.getImagePanel());
//            fx.setVisible(true);
//            fx.pack();
         }else{
        	error("Imagen Nula"); 
         }
          
        
    }
    
    
    
    public void loadImageFromDatabase(){
    	img=null;
    	System.out.println("loadImage> "+filename);
    	
      	   img = this.getImage(filename);
         
         if (img!=null){
             double w=img.getWidth();
             double h=img.getHeight();
             System.out.println("Imagen x:"+w);
             System.out.println("Imagen y:"+h);
             try {
    			this.proportion=new Double(w/h);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			//e.printStackTrace();
    		}
    		System.out.println("proprocion:"+proportion);
            ipanel=this.getImagePanel();
            ipanel.repaint(); 
         }
          
        
    }
public void loadImageFTP(){
    	
    	try {
      	   img = ImageIO.read(new File(filename));
         } catch (IOException e) {
         }
         ipanel=this.getImagePanel();
         ipanel.repaint();
        
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
		if (ipanel!=null){
			ipanel.repaint();	
		}
		
	}

	public int getScaley() {
		return scaley;
	}

	
	public void setScaley(int scaley) {
		this.scaley = scaley;
		if (ipanel!=null){
			ipanel.repaint();	
		}
	}
	
	public void setImage(BufferedImage img){
    	this.img=img;
    	try {
    		if (ipanel!=null){
    			ipanel.repaint();		
    		}
    	
    	}catch(Exception e){
    		
    	}
    }
	
	 public MySQL getMySQL(){
			MySQL s=new MySQL(null);
			s.setId("MySQL");
			s.setDatabase("test");
			s.setUser("root");
			s.setPassword("ipsilon");
			s.connect();
			return s;
		}
	    
	    public void initConnector(){
	    	data.getConnectionHandler().addConector(getMySQL());
		}
	    
	    public BufferedImage getImage(String filename){
	    	BufferedImage _image=null;
	    	try {
				Statement stmt = data.getConnector("MySQL").createStatement();
					String q="SELECT filename,data FROM files where url like '"+filename+"'";
					System.out.println(q);
					ResultSet resultSet = stmt.executeQuery(q);
					if (!resultSet.next()){
						System.out.println("no se encontro el archivo");
					}else{
						 Blob image = resultSet.getBlob(2);
						 String _file = resultSet.getString(1);
				          
				          String name="e:/"+_file;
				       // get blob
				          
				           // setup the streams to process blob
				          InputStream input = image.getBinaryStream();
				          /*
				          ByteArrayOutputStream output = new ByteArrayOutputStream();
				          // set read buffer size
				          byte[] rb = new byte[1024];
				          int ch = 0;
				           // process blob
				          while ((ch=input.read(rb)) != -1) {
				            output.write(rb, 0, ch);
				          }
				          // transfer to byte buffer
				          byte[] b = output.toByteArray();
				          input.close();
				          output.close();*/
				          _image = javax.imageio.ImageIO.read(input);
				          
				          
						
				        
						
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			return _image;
	    }
	    
	    public void storeImage(String filename){
	    	FileInputStream fis = null;
	        PreparedStatement ps = null;
	        String INSERT_PICTURE = "insert into files(url, filename, size, data) values (?, ?, ?, ?)";
	        try {
	          
	          File file = new File(filename);
	          try {
				fis = new FileInputStream(file);
	          } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	          }
	          
	          try {
				ps = this.data.getConnector("MySQL").prepareStatement(INSERT_PICTURE);
				  ps.setString(1, file.getAbsolutePath());
				  ps.setString(2, file.getName());
				  ps.setString(3, ""+file.length());
				  ps.setBinaryStream(4, fis, (int) file.length());
				  
				  ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          
	        } finally {
	          try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	    }
	    
	
}