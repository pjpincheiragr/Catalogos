package aplicacion.herramientas.java.launcher.test;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.herramientas.java.launcher.constructor._Constructor;
import aplicacion.herramientas.java.launcher.logic._Logic;

public class _Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_Constructor CC=new _Constructor();
		CC.setGraphical(false);

		CC.build(null);
		
		CC.init();
		_Logic logic=(_Logic)CC.getLogic();
		logic.runAplication("aplicacion.sistema.menu.launcher._Launcher");
		
	}

}
