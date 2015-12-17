package aplicacion.modelo.events;
import java.awt.event.ActionEvent;

import aplicacion.modelo.logic.Logic;

/**
 * Clase Modelo de un Handler para un Drag Source Listener
 * Este es un modelo de como debe implementarse un handler
 * @author Agustin Wisky
 * @since 01-08-2008
 */
public class _DragSourceListenerHandler extends ExceptionHandler{
	protected Logic _logic=null;
	
	public _DragSourceListenerHandler(){
		
	}
	
	public void setLogic(Logic _logic){
		this._logic=_logic;
	}
	
	public void procesar(){
		
	}
	
	}
