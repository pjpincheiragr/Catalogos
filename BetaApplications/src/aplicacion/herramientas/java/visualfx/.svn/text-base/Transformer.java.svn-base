package aplicacion.herramientas.java.visualfx;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JPanel;


import java.awt.event.*;
import javax.swing.event.*;
public class Transformer {
	private Robot robot;
    private BufferedImage screenImg;
    private Rectangle screenRect;
    private float alfa=0.5f;
	private javax.swing.Timer timer =null;
    private JFrame master=null;
    public Transformer(JFrame frame){
    	this.master=frame;
    	 timer=new Timer(1000, new AbstractAction() {
    	      public void actionPerformed(ActionEvent e) {
    	      transform(master);
    	      }
    	    });
    	timer.setRepeats(false);	
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
        screenRect=new Rectangle(0,0,screenSize.width,screenSize.height);
        screenImg=robot.createScreenCapture(screenRect);
        
    }
	
	public void ResetComponent(JComponent component){
		
        if (robot!=null && screenImg!=null) {
        	final BufferedImage[] underFrameImg=new BufferedImage[1];
        	final int[] paintX=new int[1],paintY=new int[1];
        	
        	Rectangle frameRect=component.getBounds();
        	//System.out.print("pane bounds?"+frameRect);
        	paintX[0]=0;
        	paintY[0]=0;
            int x=frameRect.x;
            if (x<0) {
            	paintX[0]=-x;
                x=0;
            }
            int y=frameRect.y;
            if (y<0) {
            	paintY[0]=-y;
                y=0;
            }
            int w=frameRect.width;
            //System.out.print("screen image ?"+screenImg.getWidth()+" "+screenImg.getHeight());
            int h=frameRect.height;
            Graphics g=component.getGraphics();
            
            g.clearRect(0, 0, w, h);
            //g.fillRect(0, 0, w, h);
        	
            java.awt.Point screen=null;
            try {
            	screen=component.getLocationOnScreen();
            	try {
        			new Thread().sleep(10);
        		} catch (InterruptedException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			if (screen!=null){
				x=screen.x;
		        y=screen.y;
		        underFrameImg[0]=screenImg.getSubimage(x,y,w,h);
		      //  System.out.print("screen subimage ?"+x+","+y+","+w+","+h);
	            
	            
	             Graphics2D g2d = (Graphics2D)g;
	        	 
	        	 AlphaComposite Alfa=((AlphaComposite)g2d.getComposite());
	        	 g2d.setComposite(Alfa.getInstance(AlphaComposite.SRC_OVER, alfa)); 
	        	 
	        	 
	        	 g.drawImage(underFrameImg[0],0,0,null);
	            
			}else{
				
	            
			}
            
            
        	
        }		
	}
	
	public void ResetComponent(Component component,float alfa,boolean traslucent){
		component.repaint();
		}
    
	
    private void transform(JFrame frame){
		if (frame!=null){
			try {
				
				for (int i=0;i<frame.getComponentCount();i++){
					//System.out.println("Component "+i+" >"+this._frame.getComponent(i).getClass()+" "+
					//this._frame.getComponent(i).getName());
					if (frame.getComponent(i) instanceof JRootPane){
						JRootPane C=(JRootPane) frame.getComponent(i);
						this.transform(C);
					}
					
				}		
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	}
    
    private void transform(JRootPane C){
		for (int i=0;i<C.getComponentCount();i++){
			Component _C=C.getComponent(i);
			if (_C instanceof JPanel){
				
				this.transform((JPanel)C.getComponent(i));
			}
			if (_C instanceof JLayeredPane){
				
				this.transform((JLayeredPane) C.getComponent(i));
			}
			
		}	
	}
    private void transform(JLayeredPane C){
    	ResetComponent(C);
		for (int i=0;i<C.getComponentCount();i++){
			if (C.getComponent(i) instanceof JPanel){
				this.transform((JPanel)C.getComponent(i));
			}
		}	
	}
	
	private void transform(JPanel C){
		ResetComponent(C);
		for (int i=0;i<C.getComponentCount();i++){
			if (C.getComponent(i) instanceof JPanel){
				transform((JPanel)C.getComponent(i));
			}else {
				if (C.getComponent(i) instanceof JToolBar){
					this.transform((JToolBar)C.getComponent(i));
				}else{
					this.transform(C.getComponent(i));	
				}
					
			}
			
				
			}
			
	}
	
	private void transform(JToolBar C){
		for (int i=0;i<C.getComponentCount();i++){
			if (C.getComponent(i) instanceof JPanel){
				transform((JPanel)C.getComponent(i));
			}else {
				this.transform(C.getComponent(i));	
			}
			
				
			}
			
	}
	private void transform(JScrollPane C){
		ResetComponent(C);
		if (C.getViewport().getView() instanceof JTable){
			this.transform((JTable)C.getViewport().getView());
		}
		if (C.getViewport().getView() instanceof JTree){
			this.transform((JTree)C.getViewport().getView());
		}
		
	}
	
	private void transform(Component componnent){
		ResetComponent(componnent,0.4f,true);
		if (componnent instanceof JTextField){
			JTextField tx=(JTextField) componnent;
		}
		
		if (componnent instanceof JTextArea){
			JTextArea tx=(JTextArea) componnent;
		}
		
		if (componnent instanceof JTree){
			JTree tree=(JTree) componnent;
			
		}
		
		if (componnent instanceof JButton){
			JButton button=(JButton) componnent;
		}
	
		
		if (componnent instanceof JComboBox){
			JComboBox combo=(JComboBox) componnent;
		}
		
		if (componnent instanceof JTable){
			JTable table=(JTable) componnent;
		
		}
		
		if (componnent instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) componnent;
	
		}
	}
	public void addMoveListener(JFrame frame){
		frame.addComponentListener(new WindowPrefsListener());
	}
	public void transformer(JFrame frame){
		final JFrame framex=frame;
		
		Runnable _execute=new Runnable(){
			public void run(){
				transform(framex);
			}
		};
		javax.swing.SwingUtilities.invokeLater(_execute);
	}

	public class WindowPrefsListener implements ComponentListener {
		 public void componentResized(ComponentEvent e) {
			 
			 
			 }

			 public void componentMoved(ComponentEvent e) {
				 
				 timer.start();
			 }

			 public void componentShown(ComponentEvent e) {

			 }

			 public void componentHidden(ComponentEvent e) {

			 }
	}
	
	public void refresh(JFrame frame){
		
	}
	public static void main(String[] args){
		JFrame frame =new JFrame();
		frame.setVisible(false);
		frame.setBounds(300, 300, 300, 300);
		
		JButton button=new JButton("prueba");
		button.setBounds(10,10,50,50);
		frame.getContentPane().setLayout(null);
		frame.add(button);
		
		final JFrame _frame=frame;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
  			@Override
  			public void run() {
  				com.sun.awt.AWTUtilities.setWindowOpacity(_frame, 0.90f);
  				
  				
  			}
  			});
		frame.setVisible(true);
		
				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
