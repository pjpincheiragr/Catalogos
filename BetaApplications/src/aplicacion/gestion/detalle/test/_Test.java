package aplicacion.gestion.detalle.test;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import javax.swing.*;

import aplicacion.gestion.detalle.constructor._Constructor;
import aplicacion.modelo.interfaces.*;
public class _Test {

	public static void main(String[] args){
		_Constructor CC=new _Constructor();

		CC.build(null);
		
		CC.init();
		
		
		/*CC.setSubTotal("0.0");
		CC.setCliente("112019370");
		CC.loadAll("112019370");
		CC.load_alfa_vars();*/
		//CC.load_Cobranza("00000038");
	}
}
