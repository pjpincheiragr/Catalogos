package aplicacion.actualizacion.codigo.launcher;

import aplicacion.herramientas.java.launcher.logic.Task_Model;
import aplicacion.actualizacion.codigo.constructor.*;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class _Launcher extends Task_Model {
	public void initialize_constructor(){
		C=new _Constructor();
	}
}
