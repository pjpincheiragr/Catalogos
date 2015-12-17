package aplicacion.herramientas.java.buscadores;
import javax.swing.*;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.constructor.*;
/*
 private aplicacion.herramientas.java.buscadores.Fecha bFecha=null;
public void BuscarFecha(JTextField tx){
	if (bFecha!=null){
		bFecha.close();
	}
	bFecha=new aplicacion.herramientas.java.buscadores.Fecha(this.getConstructor());
	bFecha.Buscar(tx);
}
 */
public class Fecha extends Generico{
	
	public Fecha(Constructor C){
		super(C);
	}
	public void initializeConstructor(){
		C=new aplicacion.herramientas.java.calendario.constructor._Constructor();
	}
	public void initializeLogic(JTextField tx){
		aplicacion.herramientas.java.calendario.logic._Logic 
		logic=(aplicacion.herramientas.java.calendario.logic._Logic)C.getLogic();
		
		logic.setCampo(tx);
		
		logic.init();
	}

	
}
