package aplicacion.herramientas.conexion.login.test;
import aplicacion.herramientas.conexion.conectores.SQLite;
import aplicacion.herramientas.conexion.*;

import aplicacion.herramientas.conexion.login.constructor.*;
import aplicacion.modelo.interfaces._parametros;
import javax.swing.*;
import org.pushingpixels.substance.api.skin.*;
public class _Test {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
        
		
		
		// TODO Auto-generated method stub
		_Constructor CC=new _Constructor();
		SQLite s=new SQLite(CC);
		s.setId("Beta");
		s.setDatabase("lib/beta.sqlite");
		//s.setDatabase("/home/agustinw/beta/lib/beta.sqlite");
		ConnectionHandler C=new ConnectionHandler();
		C.addConector(s);
		CC.setParameter(_parametros.connector, C);
		CC.build(null);
		CC.init();
	}

}
