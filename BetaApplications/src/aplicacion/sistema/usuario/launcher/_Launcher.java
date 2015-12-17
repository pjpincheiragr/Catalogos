/**
 * Lanzador utilizado en el menu beta para ejecutar esta aplicacion.
 */
package aplicacion.sistema.usuario.launcher;

import aplicacion.sistema.usuario.constructor._Constructor;



import aplicacion.herramientas.java.launcher.logic.Task_Model;
public class _Launcher extends Task_Model{
	
	public void initialize_constructor(){
		C=new _Constructor();
	}
	
}
