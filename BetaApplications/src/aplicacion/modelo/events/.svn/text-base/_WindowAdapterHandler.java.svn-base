package aplicacion.modelo.events;
import java.awt.event.WindowEvent;


import aplicacion.modelo.logic.Logic;

public class _WindowAdapterHandler extends ExceptionHandler {
	protected Logic _logic=null;
	
	public _WindowAdapterHandler(){
		
	}
	
	public void setLogic(Logic _logic){
		this._logic=_logic;
	}
	
	public void procesarEvento(WindowEvent we){
		if (we.getID()==WindowEvent.WINDOW_CLOSING){
			
			_logic.exit();	
		}
	}
	
	}
