package aplicacion.sistema.acercade.test;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.SQLite;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.sistema.acercade.constructor._Constructor;

public class _Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_Constructor CC=new _Constructor();
		CC.setParameter(_parametros.connector, new ConnectionHandler());
		
		CC.setShowOnStartup(false);
		CC.build(null);
		
		CC.init();
		
	}

}