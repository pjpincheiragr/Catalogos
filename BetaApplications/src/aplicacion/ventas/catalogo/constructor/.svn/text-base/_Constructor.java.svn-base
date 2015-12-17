package aplicacion.ventas.catalogo.constructor;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.ventas.catalogo.interfaces.*;
import aplicacion.ventas.catalogo.logic.*;
import aplicacion.ventas.catalogo.events.*;
import aplicacion.ventas.catalogo.gui.*;
import java.text.NumberFormat;
import javax.swing.JTextField;
public class _Constructor extends Constructor{
	
	
		
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
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
	}
	protected void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
	}
	
	protected void initialize_windowadapter_handler(){
		_windowadapter_handler=new WindowAdapterHandler();
	}
	
	protected void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	
	
	
	public void initialize_components(){
		
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_btn_anterior().setActionCommand(_Interface._btn_foto_anterior);
		_frame.get_btn_siguiente().setActionCommand(_Interface._btn_foto_siguiente);
		_frame.get_btn_guardar().setActionCommand(_Interface._btn_exportar);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar);
		_frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		_frame.get_btn_buscar_mas().setActionCommand(_Interface._btn_buscar_mas);
		_frame.get_chk_multimedia().setName(_Interface._chk_multimedia);
		_frame.get_chk_relaciones().setName(_Interface._chk_relaciones);
		_frame.get_btn_zoom_in().setActionCommand(_Interface._btn_foto_zoom_in);
		_frame.get_btn_zoom_oute().setActionCommand(_Interface._btn_foto_zoom_out);
	}
	
	
	
	public void init(){
		super.init();
		manager.startConnections();
		JTextField tx=null;
		try {
			tx=(JTextField)this.getParameter(_parametros._txt_caller);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_Logic _logic=(_Logic) this._logic;
		_logic.centrar();
		_logic.initTablas();
		_logic.clean();
		_logic.setColumns();
		_logic.setSystemColumns();
		if (tx!=null){
			_logic.setCaller(tx);
		}
		_logic.init();
		
		
		_logic.setFocus();
		//_logic.maximizar();
		
	}

	


	
}

