package aplicacion.flota.parte.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.flota.parte.events.ActionListenerHandler;
import aplicacion.flota.parte.events.WindowAdapterHandler;
import aplicacion.flota.parte.events.KeyListenerHandler;
import aplicacion.flota.parte.gui._Frame;
import aplicacion.flota.parte.interfaces.*;
import aplicacion.flota.parte.logic._Data;
import aplicacion.flota.parte.logic._Logic;
import aplicacion.flota.parte.interfaces._Interface;
import aplicacion.flota.parte.events.DropTargetListenerHandler;

public class _Constructor extends Constructor {
	protected void initialize_frame(){
		_frame=new _Frame();
		
	}
	protected void initialize_data(){
		_data=new _Data();
	}
	protected void initialize_logic(){
		_logic=new _Logic();
	}
	
	protected void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	protected void initialize_windowadapter_handler(){
		_windowadapter_handler=new WindowAdapterHandler();
	}

	protected void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	protected void initialize_droptargetlistener_handler(){
		_droptargetlistener_handler=new DropTargetListenerHandler();
	}
	
	/*protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
		
	}*/
	
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
	
	}
	
	public void init(){
			
	}
}
