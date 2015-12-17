package aplicacion.modelo.events;
import java.awt.event.MouseEvent;
import aplicacion.modelo.logic.Logic;

public class _MouseListenerHandler extends ExceptionHandler{
	protected Logic _logic=null;
	
	public _MouseListenerHandler(){
		
	}
	
	public void setLogic(Logic _logic){
		this._logic=_logic;
	}
	
	
	}
