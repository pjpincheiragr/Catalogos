package aplicacion.herramientas.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.constructor.*;
public class Error {
	private Constructor C;
	private boolean reconection=true;
	public Error(Constructor C){
		this.C=C;
	}
	
	public static String getCustomStackTrace(Throwable aThrowable) {
	    //add the class name and any message passed to constructor
	    final StringBuilder result = new StringBuilder( "BUG-BUG: " );
	    result.append(aThrowable.toString());
	    final String NEW_LINE = System.getProperty("line.separator");
	    result.append(NEW_LINE);

	    //add each element of the stack trace
	    for (StackTraceElement element : aThrowable.getStackTrace() ){
	      result.append( element );
	      result.append( NEW_LINE );
	    }
	    return result.toString();
	  }

	public void sendError(String mensaje,Exception e,String xml){
		String stack=this.getCustomStackTrace(e);
		mensaje+="StackTrace:";
		mensaje+=stack;
		final String _mensaje=mensaje;
		final String _xml=xml;	
			Runnable _execute=new Runnable() {
				   public void run() {
					  
					   caller(_mensaje,_xml);
				   }
			};
			if (!javax.swing.SwingUtilities.isEventDispatchThread()){
				javax.swing.SwingUtilities.invokeLater(_execute);	
			}else{
				caller(_mensaje,_xml);
			}
	}
	
	public void sendError(String mensaje,java.lang.Error e,String xml){
		String stack=this.getCustomStackTrace(e);
		mensaje+="StackTrace:";
		mensaje+=stack;
		final String _mensaje=mensaje;
		final String _xml=xml;	
			Runnable _execute=new Runnable() {
				   public void run() {
					  
					   caller(_mensaje,_xml);
				   }
			};
			if (!javax.swing.SwingUtilities.isEventDispatchThread()){
				javax.swing.SwingUtilities.invokeLater(_execute);	
			}else{
				caller(_mensaje,_xml);
			}
	}
	
	public void caller(String error,String xml){
		aplicacion.sistema.error.constructor._Constructor C=new aplicacion.sistema.error.constructor._Constructor();
		ConnectionHandler handler=new ConnectionHandler();
		C.setParameter(_parametros.connector, handler);
		C.setParameter(_parametros.LookAndFeel, this.C.getLookAndFeelTheme());
		C.setParameter(_parametros.iduser, this.C.getIduser());
		C.setShowOnStartup(false);
		C.build(this.C);
		C.init();
		aplicacion.sistema.error.logic._Logic logic=(aplicacion.sistema.error.logic._Logic)C.getLogic();
		logic.createScreenImage();
		logic.setXML(xml);
		logic.centrar();
		logic.setAsunto("Error en Beta");
		logic.setError(error);
		logic.focus();
	}

	public int preguntarSwing(String titulo,String pregunta,String[] options,String valorpordefecto){
		final int[] Answer=new int[1];
		final String _titulo=titulo;
		final String _pregunta=pregunta;
		final String[] _options=options;
		final String _valorpordefecto=valorpordefecto;
		Runnable showModalDialog = new Runnable() {
	        public void run() {
	        	JFrame fx = new JFrame("Error");
	        	Answer[0]=JOptionPane.showOptionDialog(fx,
						_pregunta,
						_titulo,
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						_options,
						_valorpordefecto); 
	        }
	    };
	    try {
	    	
			javax.swing.SwingUtilities.invokeAndWait(showModalDialog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("HASH CODE"+e.hashCode());
			
		}
		return Answer[0];
	}
	
	public int _displayError(String titulo,String mensaje,String detalle,Exception e,String xml){
		String[] options=new String[]{"Ver","Omitir","Ocultar Errores"};
		
		int n=0;
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			n=preguntar(titulo, mensaje, options, options[0]);
		}else{
			n=preguntarSwing(titulo, mensaje, options, options[0]);
		}
		if (n==0){
			showError(titulo,mensaje,detalle,e,xml);
		}
		return n;
	}
	public int displayError(String titulo,String mensaje,String detalle,java.lang.Error e,String xml){
		int n=showError(titulo,mensaje,detalle,e,xml);
		return n;
	}
	public int displayError(String titulo,String mensaje,String detalle,Exception e,String xml){
		int n=showError(titulo,mensaje,detalle,e,xml);
		return n;
	}
	
	public void reconnect(){
			if (C!=null){
				
					 this.C.reconnect(); 
				 
				
					
						
			}
			
		
		
	}
	public int preguntar(String titulo,String pregunta,String[] options,String valorpordefecto){
		 int[] answer = new int[1];
		 final JFrame fx = new JFrame("Error");
  	     answer[0] = JOptionPane.showOptionDialog(fx,
						pregunta,
						titulo,
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						valorpordefecto);
		return answer[0];
	}
	
	public int showError(String titulo,String mensaje,String detalle,Exception e,String xml){
		
		String newline = System.getProperty("line.separator");
		mensaje+=newline;
		mensaje+=detalle;
		mensaje+=newline;
		  
		int n=-1;
		final String _mensaje=mensaje;
		String[] options=new String[]{"Aceptar","Omitir","Enviar Email"};
		if (!javax.swing.SwingUtilities.isEventDispatchThread()){
			
			n=this.preguntarSwing(titulo, _mensaje, options, options[0]);
			
		}else{
			n=this.preguntar(titulo, _mensaje, options, options[0]);
			
		}
		if (n==2){sendError(_mensaje,e,xml);}
		return n;
	}

	public int showError(String titulo,String mensaje,String detalle,java.lang.Error e,String xml){
		
		String newline = System.getProperty("line.separator");
		mensaje+=newline;
		mensaje+=detalle;
		mensaje+=newline;
		int n=-1;
		final String _mensaje=mensaje;
		String[] options=new String[]{"Aceptar","Omitir","Enviar Email"};
		if (!javax.swing.SwingUtilities.isEventDispatchThread()){
			
			n=this.preguntarSwing(titulo, _mensaje, options, options[0]);
			
		}else{
			n=this.preguntar(titulo, _mensaje, options, options[0]);
			
		}
		if (n==1){sendError(_mensaje,e,xml);}
		return n;
	}

	public void setIduser(String iduser){
		C.setIduser(iduser);
	}
	
	
}
