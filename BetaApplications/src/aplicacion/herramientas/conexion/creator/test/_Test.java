package aplicacion.herramientas.conexion.creator.test;
import aplicacion.herramientas.conexion.conectores.SQLite;
import aplicacion.herramientas.conexion.creator.constructor.*;
public class _Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_Constructor CC=new _Constructor();
		CC.build(null);
		SQLite s=new SQLite(CC);
		s.setId("Beta");
		s.setDatabase("lib/beta.sqlite");
		CC.getConnectionHandler().addConector(s);
		CC.init();
		
	}

}
