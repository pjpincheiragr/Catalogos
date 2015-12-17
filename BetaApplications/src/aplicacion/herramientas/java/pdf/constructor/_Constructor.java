package aplicacion.herramientas.java.pdf.constructor;

import aplicacion.modelo.constructor.Constructor;

import aplicacion.herramientas.java.pdf.events.WindowAdapterHandler;
import aplicacion.herramientas.java.pdf.gui._Frame;
import aplicacion.herramientas.java.pdf.logic._Data;
import aplicacion.herramientas.java.pdf.logic._Logic;

public class _Constructor extends Constructor {

	public _Constructor(){
		
	}
	
	protected void initialize_frame(){
		_frame=new _Frame();
	}
	
	protected void initialize_logic(){
		_logic=new _Logic();
		
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	protected void initialize_windowadapter_handler(){
		_windowadapter_handler=new WindowAdapterHandler();
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this.getLogic();
		logic.initSwing();
	}
}
