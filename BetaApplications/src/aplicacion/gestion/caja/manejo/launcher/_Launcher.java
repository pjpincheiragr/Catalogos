package aplicacion.gestion.caja.manejo.launcher;

import javax.swing.JTextField;
import aplicacion.gestion.caja.manejo.constructor._Constructor;
import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.modelo.interfaces._parametros;

import aplicacion.herramientas.java.launcher.logic.Task_Model;
public class _Launcher extends Task_Model{
	
	public _Launcher(){
		super();
	}
	
	public void initialize_constructor(){
		
		C=new _Constructor();
	}
	public void run(){
		super.run();
	
	}
}
