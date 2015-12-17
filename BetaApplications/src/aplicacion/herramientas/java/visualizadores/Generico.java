package aplicacion.herramientas.java.visualizadores;

import javax.swing.JTextField;
import java.awt.Rectangle;
import aplicacion.modelo.constructor.*;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.herramientas.conexion.ConnectionHandler;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */


public class Generico {
	protected String LookAndFeel="";
	protected Constructor Constructor=null;
	protected ConnectionHandler connection=null;
	protected Constructor C=null;
	
	public void dispose(){
		C.dispose();
		
	}

	public String getLookAndFeel() {
		return LookAndFeel;
	}

	public void setLookAndFeel(String lookAndFeel) {
		LookAndFeel = lookAndFeel;
	}
	public ConnectionHandler getConnection() {
		return connection;
	}

	public void setConnection(ConnectionHandler connection) {
		this.connection = connection;
	}

	public Generico(ConnectionHandler connection){
		this.connection=connection;
	}
	

	public Generico(){
	}
	
	public Constructor getCaller() {
		return Constructor;
	}

	public void setCaller(Constructor caller) {
		
		Constructor = caller;
		this.LookAndFeel=Constructor.getLookAndFeelTheme();
		this.connection=Constructor.getConnectionHandler();
	}
	
	public Generico(Constructor C){
		System.out.println("Setting Caller for VisualSelector On Generic Class");
		this.Constructor=C;
		
		this.connection=C.getConnectionHandler();
		this.LookAndFeel=C.getLookAndFeelTheme();
	}
	
	public void initializeConstructorParameters(){
		System.out.println("Iniiciando Constructor Parametros");
		C.setShowOnStartup(false);
		C.setParameter(_parametros.connector, connection);
		C.setParameter(_parametros.LookAndFeel, this.LookAndFeel);
		C.build(this.C);
		C.init();
		this.Constructor.getChilds().add(C);
	}
	
	public int Buscar(JTextField tx) {
		if (C!=null){
			System.out.println("Ocultando Visualizador anterior");
			C.getFrame().setVisible(false);
			C.dispose();
		}else{
			
		}
		System.out.println("Iniciando Constructor");
		intializeConstructor();
		initializeConstructorParameters();
		System.out.println("Iniciando logica");
		
		
		int n=initializeLogic(tx);
		
		return n;
	}
	
	public int Buscar(JTextField tx,String[] options) {
		System.out.println("Buscar Para ComboBox");
		if (C!=null){
			System.out.println("Ocultando Visualizador anterior");
			C.getFrame().setVisible(false);
			C.dispose();
		}else{
			
		}
		System.out.println("Iniciando Constructor");
		intializeConstructor();
		initializeConstructorParameters();
		System.out.println("Iniciando logica");
		
		
		int n=initializeLogic(tx,options);
		
		return n;
	}
	public void intializeConstructor(){
		System.out.println("Iniiciando Constructor");
		C = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
	}
	
	
	public int initializeLogic(JTextField tx) {
		int n=0;
		return n;
	}
	public int initializeLogic(JTextField tx,String[] options) {
		int n=0;
		return n;
	}
	
	public void close(){
		if (C!=null){
			System.out.println("Close anterior");
			C.getFrame().setVisible(false);
			C.dispose();	
		}
	}
}
