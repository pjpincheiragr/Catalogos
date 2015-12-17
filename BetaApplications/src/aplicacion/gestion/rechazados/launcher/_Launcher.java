package aplicacion.gestion.rechazados.launcher;

import javax.swing.JTextField;
import aplicacion.gestion.rechazados.constructor._Constructor;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.modelo.interfaces._parametros;

import aplicacion.herramientas.java.launcher.logic.*;

public class _Launcher extends Task_Model{
	
	public void initialize_constructor(){
		C=new _Constructor();
	}
}
