package aplicacion.modelo.events;
import java.awt.event.KeyEvent;
import aplicacion.modelo.logic.Logic;
public class _KeyListenerHandler extends ExceptionHandler {
	protected Logic _logic=null;
	
	public _KeyListenerHandler(){
		
	}
	
	public void setLogic(Logic _logic){
		this._logic=_logic;
		super.setLogic(_logic);
	}

	
}
