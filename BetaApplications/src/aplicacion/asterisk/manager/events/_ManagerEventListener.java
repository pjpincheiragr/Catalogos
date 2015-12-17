package aplicacion.asterisk.manager.events;

import org.asteriskjava.manager.ManagerEventListener;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.NewExtenEvent;
import org.asteriskjava.manager.event.StatusCompleteEvent;
import aplicacion.asterisk.manager.logic._Asterisk;
import aplicacion.modelo.logic.Logic;

public class _ManagerEventListener implements ManagerEventListener {
	protected _Asterisk _logic=null;
	public void setLogic(_Asterisk _logic){
		this._logic=_logic;
	}
	public void onManagerEvent(ManagerEvent event)
    {
        // just print received events
    	if (event instanceof NewExtenEvent){
    		
    		NewExtenEvent custom=(NewExtenEvent) event;
    		_Asterisk logic=(_Asterisk)this._logic;
    		logic.evalCall(custom);
    	}else{
    		if (event instanceof StatusCompleteEvent){
    			StatusCompleteEvent custom=(StatusCompleteEvent) event;
    			System.out.print("[StatusCompleteEvent]");
    			System.out.println(custom);
    		}
    	}
    	
    	
    	
        
    }
}
