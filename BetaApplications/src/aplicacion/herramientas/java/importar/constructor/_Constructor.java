package aplicacion.herramientas.java.importar.constructor;

import aplicacion.herramientas.java.importar.events.ActionListenerHandler;
import aplicacion.herramientas.java.importar.events.ItemListenerHandler;
import aplicacion.herramientas.java.importar.events.KeyListenerHandler;
import aplicacion.herramientas.java.importar.events.WindowAdapterHandler;
import aplicacion.herramientas.java.importar.events.MouseListenerHandler;
import aplicacion.herramientas.java.importar.gui._Frame;
import aplicacion.herramientas.java.importar.logic._Data;
import aplicacion.herramientas.java.importar.logic._Logic;
import aplicacion.herramientas.java.importar.interfaces.*;
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
		frame.get_btn_buscar_archivo().setActionCommand(_Interface._btn_buscar_archivo);
		frame.get_btn_cargar_archivo().setActionCommand(_Interface._btn_cargar_archivo);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_actualizar().setActionCommand(_Interface._btn_importar);
		
		frame.get_txt_archivo().setName(_Interface._txt_archivo);
		frame.get_chk_seleccionar().setName(_Interface._chk_seleccionar);
		
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this._logic;
		_Frame frame=(_Frame)_frame;
		frame.setVisible(true);
		JTable table=null;
		table=(JTable)this.getParameter(aplicacion.herramientas.java.importar.interfaces._parametros._tabla);
		if (table!=null){
			logic.set_table(table);	
		}
		logic.cargar_modos();
		logic.focus();
	}
}
