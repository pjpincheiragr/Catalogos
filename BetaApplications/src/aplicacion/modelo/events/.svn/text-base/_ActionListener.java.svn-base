package aplicacion.modelo.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Agustin Wisky
 * @since 01-08-2008
 *
 * Clase para escuchar los eventos de botones. 
 * Tiene asociado un handler del tipo _ActionListenerHandler
 * Al escuchar un evento del tipo "ActionEvent" invoca al metodo 
 * procesar(evento) del handler.
 * Esta clase no debe ser reescrita. 
 */
public class _ActionListener implements ActionListener {
public _ActionListenerHandler _handler=null;

	/**
	 * Metodo para indicar al listener que Handler debe utilizar para procesar 
	 * los eventos que recibe
	 * @param handler
	 */
	public void setEventHandler(_ActionListenerHandler handler){
		this._handler=handler;
	}
	
	/**
	 * Este metodo es el que se dispara cuando un evento ocurre. Y es implementado
	 * en este listener porque asi lo requiere la interfaz ActionListener.
	 * Este metodo es el que ejecuta el metodo procesar del handler especificado.
	 */
	public void actionPerformed(ActionEvent e){
			this._handler.procesar(e);	
		
	}
	
	
}
