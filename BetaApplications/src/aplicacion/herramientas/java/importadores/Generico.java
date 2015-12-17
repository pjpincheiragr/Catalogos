package aplicacion.herramientas.java.importadores;

import javax.swing.JTextField;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;

public class Generico {
	protected Constructor constructor=null;
	protected Constructor C=null;
	
	public Generico(){
		
	}
	public void dispose(){
		System.out.println("Dispose from "+this.getClass());
		this.C.dispose();
	}
	public Constructor getCaller() {
		return constructor;
	}

	public void setCaller(Constructor caller) {
		constructor = caller;
	}
	
	public Constructor getConstructor(){
		return C;
	}
	
	public void close(){
		if (C!=null){
			C.dispose();	
		}
		
	}

	public Generico(Constructor C){
		this.constructor=C;
	}

	public void initializeConstructorParameters(){
		C.setShowOnStartup(false);
		C.setParameter(_parametros.connector, this.constructor.getConnectionHandler());
		C.setParameter(_parametros.LookAndFeel, this.constructor.getLookAndFeelTheme());
		C.setParameter(_parametros.iduser, this.constructor.getIduser());
		C.setCaller(this.getCaller());
		C.build(this.getConstructor());
		C.init();
	}
	
	public void Buscar(JTextField tx) {
		this.initializeConstructor();
		this.initializeConstructorParameters();
		this.initializeLogic(tx);
		
	}
	
	public void initializeConstructor(){
		
	}
	public void initializeLogic(JTextField tx){
		
	}
}
