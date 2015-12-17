package aplicacion.inventario.reporteArticulo.launcher;

import javax.swing.JTextField;
import aplicacion.inventario.reporteArticulo.constructor._Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.herramientas.conexion.conectores.*;

import aplicacion.herramientas.java.launcher.logic.*;
public class _Launcher extends Task_Model{
	
	
	public void initialize_constructor(){
		
		C=new _Constructor();
	}
	
}
