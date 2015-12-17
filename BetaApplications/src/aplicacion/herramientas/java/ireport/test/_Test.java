package aplicacion.herramientas.java.ireport.test;
import aplicacion.herramientas.java.ireport.constructor._Constructor;
import aplicacion.herramientas.java.ireport.interfaces.*;
import aplicacion.herramientas.conexion.*;
import java.util.*;
public class _Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_Constructor C=new _Constructor();
		ConnectionHandler CH=new ConnectionHandler();
		//C.setParameter(aplicacion.modelo.interfaces._parametros.connector, CH);
		
		HashMap param=new HashMap();
		param.put("idpedido","00000028");
		param.put("fecha","01-06-2010");
		param.put("subtotal","100");
		param.put("iva","21");
		param.put("total","121");
		param.put("observacion","");
		param.put("condicion","");
		param.put("mantenimiento","7 dias");
		param.put("plazo_entrega","2 dias");
		param.put("empresa","Esquel Repuestos");
		param.put("telefono","0299 4423348");
		param.put("email","esquel");
		param.put("mostrar_marcas","");
		param.put("mostrar_iva","");
		C.setParameter(_parametros.parametros, param);
		C.setParameter(_parametros.reporte, "PRESUPUESTO.jasper");
		
		
		C.build(null);
		C.init();
	}

}
