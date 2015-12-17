package aplicacion.herramientas.java.buscadores;

import javax.swing.JTextField;
import javax.swing.JButton;
import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;

public class Generico {
	protected Constructor constructor=null;
	protected Constructor C=null;
	
	public Generico(){
		
	}
	public void dispose(){
		this.C.dispose();
		C=null;
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

	public Generico(Constructor C){
		this.constructor=C;
	}

	public void initializeConstructorParameters(){
		
		C.setShowOnStartup(false);
		C.setParameter(_parametros.connector, this.constructor.getConnectionHandler());
		C.setParameter(_parametros.LookAndFeel, this.constructor.getLookAndFeelTheme());
		C.setParameter(_parametros.iduser, this.constructor.getIduser());
		C.build(this.getConstructor());
		C.init();
		this.constructor.addChild(C);
	}
	
	public void Buscar() {
		JTextField tx=null;
		this.Buscar(tx);
	}
	
	public void Buscar(JTextField tx) {
		if (C==null){
			this.initializeConstructor();
			this.initializeConstructorParameters();
			
		}
		System.out.println("Creando buscador con tx "+tx);
		
		this.initializeLogic(tx);
		
	}
	
	public void Buscar(JButton tx) {
		if (C==null){
			this.initializeConstructor();
			this.initializeConstructorParameters();
			
		}
		System.out.println("Creando buscador con tx "+tx);
		
		this.initializeLogic(tx);
		
	}
	
	public void initializeConstructor(){
		
	}
	
	public void initializeLogic(JButton tx){
		
	}
	
	public void initializeLogic(JTextField tx){
		
	}
}
