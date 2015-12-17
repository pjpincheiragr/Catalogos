package aplicacion.cliente.gestion.test;
import aplicacion.cliente.gestion.constructor.*;
import aplicacion.herramientas.conexion.conectores.MsSQL;
public class _Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_Constructor CC=new _Constructor();
		
		CC.build(null);
		CC.init();
	}
}
