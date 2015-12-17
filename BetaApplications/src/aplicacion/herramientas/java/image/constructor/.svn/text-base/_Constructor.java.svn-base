package aplicacion.herramientas.java.image.constructor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import aplicacion.herramientas.java.image.events.MouseListenerHandler;
import aplicacion.herramientas.java.image.logic.*;
import aplicacion.herramientas.java.image.gui.*;
import aplicacion.herramientas.java.image.interfaces._Parametros;
import aplicacion.modelo.constructor.Constructor;
public class _Constructor extends Constructor {
	
	public void initialize_logic(){
		this._logic=new _Logic();
	}
	public void initialize_data(){
		this._data=new _Data();
	}
	public void initialize_frame(){
		this._frame=new _Frame();
	}
	public void initialize_mouselistener_handler(){
		
		this._mouselistener_handler=new MouseListenerHandler();
	}
	
	public void init(){
		super.init();
		boolean eliminar=false;
		try {
			eliminar=(Boolean) this.getParameter(_Parametros._eliminar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		_Logic logic=(_Logic) this._logic;
		logic.setEliminar(eliminar);
		
		/*
		_Frame frame=(_Frame) this._frame;
        
        _Logic logic=(_Logic) this._logic;
        logic.setFileName("e:/facturas/ARCORE.jpg");
        logic.loadImage();
        logic.setScalex(200);
        logic.setScaley(300);
        
        frame.getJContentPane().add(logic.getImagePanel());
        frame.pack();
        frame.setSize(200,300);
        frame.setVisible(true);
		super.init();*/
		//super.init();
	}
}
