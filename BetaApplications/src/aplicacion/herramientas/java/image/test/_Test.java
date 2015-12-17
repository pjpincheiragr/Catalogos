package aplicacion.herramientas.java.image.test;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


import aplicacion.herramientas.java.image.constructor.*;

public class _Test {
	public static void main(String[] args){
		_Constructor C=new _Constructor();
		C.build(null);
		//C.init();
		aplicacion.herramientas.java.image.logic._Logic logic=
			(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
		logic.initConnector();
		logic.setFileName("e:%");
		logic.loadImageFromDatabase();
		logic.setEliminar(false);
		
		//logic.setScalex(200);
		//logic.setScaley(300);
		
		
		final aplicacion.herramientas.java.image.logic._Logic lg=logic;
		Runnable imager=new Runnable(){
			public void run(){
				JFrame F=new JFrame();
				F.setUndecorated(true);
				F.add(lg.getImagePanel());
				F.setSize(200,200);
				F.setVisible(true);
			}
		};
		javax.swing.SwingUtilities.invokeLater(imager);
		
		
	}
}
