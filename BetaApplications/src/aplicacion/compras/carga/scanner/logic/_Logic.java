package aplicacion.compras.carga.scanner.logic;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


import aplicacion.compras.carga.scanner.events._ScannerListener;
import aplicacion.compras.carga.scanner.gui.*;
import uk.co.mmscomputing.device.scanner.Scanner;

import uk.co.mmscomputing.device.scanner.ScannerIOException;
import aplicacion.modelo.logic.Logic;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class _Logic extends Logic {

	private Scanner scanner;
	private _ScannerListener scannerlistener;
	private String filename="";
	private _Frame frame=null;
	private aplicacion.compras.carga.encabezado.gui._Frame _Framex=null;
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(this.frame);
	}
	
	public void setFrameX(JFrame f){
		_Framex=(aplicacion.compras.carga.encabezado.gui._Frame) f;
	}
		
	public _Logic(){
		
		
		
		scanner=Scanner.getDevice();
		scannerlistener=new _ScannerListener();
		scannerlistener.setLogic(this);
		scanner.addListener(scannerlistener);
		
	}

	
	


	public void select(){
		boolean error=false;
		try {
			scanner.setCancel(true);
			String[] lists=scanner.getDeviceNames();
			boolean twain=false;
			int i=0;
			while (i<lists.length){
				System.out.println("SCANNER>"+lists[i]);
				if (lists[i].toLowerCase().contains("wia")){
					twain=true;
					scanner.select(lists[i]);
					aviso("Scanner "+lists[i]);
				}
				i++;
			}
			
			
			//scanner.select("HP PSC 1600 series");
		}catch(ScannerIOException se){
			System.out.println("Error!! "+se.getMessage());
			//this.displayError("Error Seleccionando Scanner", se.getMessage(), se.getLocalizedMessage(), se);
		    se.printStackTrace();
		    
		    error=true;
		}
		
		
			
			
			
	}
	public void acquire(){
		try {
		
		scanner.acquire();
		
		}catch(ScannerIOException se){
	    se.printStackTrace();
	  }

	}
	
public void OpenImage(BufferedImage img){
		aplicacion.herramientas.java.image.constructor._Constructor C
    	=new aplicacion.herramientas.java.image.constructor._Constructor();
    	C.build(this.getConstructor());
    	C.init();
    	aplicacion.herramientas.java.image.logic._Logic logic=
    		(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
    	logic.setImage(img);
    	frame.setCanvas(logic.getImagePanel());
    }

public void setFileName(String cuenta,String descripcion,String tc,String idcomprobante,String secuencia){
	
	frame.get_txt_idproveedor().setText(cuenta);
	frame.get_txt_proveedor_descripcion().setText(descripcion);
	frame.get_txt_tc().setText(tc);
	frame.get_txt_idcomprobante().setText(idcomprobante);
	frame.get_txt_secuencia().setText(secuencia);
	String server="//192.168.4.150/windows storage/fotos/";
	
	server+=cuenta+"-"+tc+idcomprobante+"-"+secuencia+".jpg";
	frame.get_txt_ruta().setText(server);
}

public void write(){
	/*
	frame.getCanvas().setFileName(frame.get_txt_ruta().getText());
	
	boolean ok=frame.getCanvas().write();
	if (ok){
		JOptionPane.showMessageDialog(null,"Se grabo correctamente la imagen");
	}else {
		JOptionPane.showMessageDialog(null,"no se grabo la imagen");
		
	}*/
	/*
	System.out.println("addfoto");
	ImagePanel map=new ImagePanel();
    map.setImage(frame.getCanvas().getImage());
    map.setScalex(75);
    map.setScaley(90);
    
    int c=_Framex.getJPanel4().getComponentCount();
    map.setBounds(10+(c-2)*90,44,75,90);
    
    */
	
	//_Framex.getJPanel4().add(c);
    _Framex.getJPanel4().repaint();
    _Framex.getJPanel4().validate();
    
	this.getConstructor().dispose();
}


public void OpenImage(){
	
	aplicacion.herramientas.java.image.constructor._Constructor C
    =new aplicacion.herramientas.java.image.constructor._Constructor();
	C.build(this.getConstructor());
	
	aplicacion.herramientas.java.image.logic._Logic logic=
		(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
	
	logic.setFileName("e:/facturas/DARSUR.jpg");
	logic.loadImage();
    
	Component c=logic.getImagePanel();
	c.setBounds(new Rectangle(9, 81, 278, 238));
	
    frame.getJContentPane().add(c);
    frame.getJContentPane().validate();
    frame.getJContentPane().revalidate();
    frame.getJContentPane().repaint();
    
    
    
    
}

}
