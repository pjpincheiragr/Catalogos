package aplicacion.gestion.buscador.test;

import javax.swing.*;

import aplicacion.gestion.buscador.constructor._Constructor;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.modelo.interfaces.*;
public class _Test {

	public static void main(String[] args){
		_Constructor CC=new _Constructor();
		CC.build(null);
		CC.init();
		
	}
}
