/**
 * Lanzador utilizado en el menu beta para ejecutar esta aplicacion.
 */
package aplicacion.inventario.articulo.launcher;

import aplicacion.inventario.articulo.constructor._Constructor;



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
