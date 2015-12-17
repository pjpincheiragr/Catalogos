package aplicacion.herramientas.java.comprobantes;

import javax.swing.JTextField;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.sistema.acercade.constructor._Constructor;

import java.awt.Point;

public class Generico {
	protected Constructor constructor;
	protected String tc = "";
	protected String idcomprobante = "";
	protected Point p;
	protected Constructor C = null;
	protected boolean editable=false;
	
	public Constructor getConstructor() {
		return constructor;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public void setConstructor(Constructor c) {
		constructor = c;
	}

	

	public void setLocation(Point p) {
		this.p = p;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		System.out.println("Setting? tc="+tc);
		this.tc = tc;
	}

	public String getIdcomprobante() {
		return idcomprobante;
	}

	public void setIdcomprobante(String idcomprobante) {
		System.out.println("Setting? idcomprobante="+idcomprobante);
		this.idcomprobante = idcomprobante;
	}

	public Generico(Constructor C) {
		this.constructor=C;
	}

	public void close() {
		C.dispose();
	}

	public void initializeConstructorParameters() {
		C.setShowOnStartup(false);
		C.setParameter(_parametros.connector, this.constructor
				.getConnectionHandler());
		C.setParameter(_parametros.LookAndFeel, this.constructor
				.getLookAndFeelTheme());
		C.setParameter(_parametros.iduser, this.constructor.getIduser());
		C.setParameter(aplicacion.herramientas.java.comprobante.interfaces._Parametros.location,
						p);
		C.build(this.getConstructor());
		
		C.init();
		aplicacion.herramientas.java.comprobante.logic._Logic 
		logic=(aplicacion.herramientas.java.comprobante.logic._Logic) C.getLogic();
		logic.setEditable(editable);
		logic.setTitle("Visor de Comprobante "+tc+"-"+idcomprobante);
	}

	public void Mostrar() {
		System.out.println("Visualizador de Comprobantes Generico "+tc+"-"+idcomprobante);
		this.initializeConstructor();
		this.initializeConstructorParameters();
		System.out.println("Visualizador de Comprobantes Generico "+tc+"-"+idcomprobante);
		this.initializeLogic();

	}

	public void initializeConstructor() {
		C=new aplicacion.herramientas.java.comprobante.constructor._Constructor();
	}

	
	public void initializeLogic() {

	}
}
