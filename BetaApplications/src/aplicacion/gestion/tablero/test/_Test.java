package aplicacion.gestion.tablero.test;

import javax.swing.*;

import aplicacion.gestion.tablero.constructor._Constructor;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.modelo.interfaces.*;
public class _Test {

	public static void main(String[] args){
		_Constructor CC=new _Constructor();
		CC.build(null);
		CC.init();
	}
}
