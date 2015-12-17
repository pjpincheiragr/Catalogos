package aplicacion.herramientas.java.reemplazar.constructor;

import aplicacion.herramientas.java.reemplazar.events.ActionListenerHandler;
import aplicacion.herramientas.java.reemplazar.events.ItemListenerHandler;
import aplicacion.herramientas.java.reemplazar.events.KeyListenerHandler;
import aplicacion.herramientas.java.reemplazar.events.WindowAdapterHandler;
import aplicacion.herramientas.java.reemplazar.events.MouseListenerHandler;
import aplicacion.herramientas.java.reemplazar.gui._Frame;
import aplicacion.herramientas.java.reemplazar.logic._Data;
import aplicacion.herramientas.java.reemplazar.logic._Logic;
import aplicacion.herramientas.java.reemplazar.interfaces.*;
import aplicacion.modelo.constructor.Constructor;
import javax.swing.*;
public class _Constructor extends Constructor {
	public void initialize_frame(){
		_frame=new _Frame();
	}
	public void initialize_data(){
		_data=new _Data();
	}
	public void initialize_logic(){
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
	
	protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
		
	}
	
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
		
	}
	
	public void initialize_components(){
		_Frame frame=(_Frame)_frame;
		
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar);
		frame.get_btn_buscar_reemplazar().setActionCommand(_Interface._btn_buscar_reemplazar);
		frame.get_btn_reemplazar_todo().setActionCommand(_Interface._btn_buscar_reemplazar_todo);
		frame.get_btn_reemplazar().setActionCommand(_Interface._btn_reemplazar);
		frame.get_chk_adelante().setName(_Interface._chk_adelante);
		frame.get_chk_atras().setName(_Interface._chk_atras);
		//frame.get_btn_error().setActionCommand(_Interface._btn_error);
		
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this._logic;
		JTable table=null;
		try {
			table=(JTable)this.getParameter(aplicacion.herramientas.java.reemplazar.interfaces._parametros._tabla);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (table!=null){
			logic.setTable(table);
		}
		
	}
	
}
