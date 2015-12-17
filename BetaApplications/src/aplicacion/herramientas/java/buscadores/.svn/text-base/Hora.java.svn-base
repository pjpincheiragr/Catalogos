package aplicacion.herramientas.java.buscadores;
import javax.swing.*;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.constructor.*;
/*
 private aplicacion.herramientas.java.buscadores.Hora bHora=null;
public void BuscarHora(JTextField tx){
	if (bHora!=null){
		bHora.close();
	}
	bHora=new aplicacion.herramientas.java.buscadores.Hora(this.getConstructor());
	bHora.Buscar(tx);
}
 */
public class Hora extends Generico{
	
	public Hora(Constructor C){
		super(C);
	}
	public void initializeConstructor(){
		C=new aplicacion.herramientas.java.hora.constructor._Constructor();
	}
	public void initializeLogic(JTextField tx){
		aplicacion.herramientas.java.hora.logic._Logic 
		logic=(aplicacion.herramientas.java.hora.logic._Logic)C.getLogic();
		
		logic.setCampo(tx);
		
		logic.init();
	}

	
}
