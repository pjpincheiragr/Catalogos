package aplicacion.herramientas.java.hora.test;
import aplicacion.herramientas.java.hora.constructor.*;
import aplicacion.herramientas.java.hora.interfaces._parametros;
import aplicacion.herramientas.java.hora.logic.*;
import aplicacion.herramientas.conexion.*;
public class _Test {
	public static void main(String[] args){
		_Constructor C=new _Constructor();
		
		C.build(null);
		C.setConnectionHandler(new ConnectionHandler());
		C.init();
		
	}
}
