package aplicacion.actualizacion.recodificador.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.actualizacion.alias.interfaces._parametros;
import aplicacion.actualizacion.recodificador.events.ActionListenerHandler;
import aplicacion.actualizacion.recodificador.events.ItemListenerHandler;
import aplicacion.actualizacion.recodificador.events.KeyListenerHandler;
import aplicacion.actualizacion.recodificador.events.WindowAdapterHandler;
import aplicacion.actualizacion.recodificador.gui._Frame;
import aplicacion.actualizacion.recodificador.logic._Data;
import aplicacion.actualizacion.recodificador.logic._Logic;
import aplicacion.actualizacion.recodificador.interfaces.*;
import aplicacion.modelo.constructor.Constructor;

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
	
	public void initialize_components(){
		_Frame frame=(_Frame)_frame;
		frame.get_txt_idarticulo_desde().setName(_Interface._txt_idarticulo_desde);
		frame.get_txt_idarticulo_hasta().setName(_Interface._txt_idarticulo_hasta);
		frame.get_btn_buscar_desde().setActionCommand(_Interface._btn_buscar_desde);
		frame.get_btn_buscar_hasta().setActionCommand(_Interface._btn_buscar_hasta);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_cancelar_operacion().setActionCommand(_Interface._btn_cancelar_operacion);
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar_reemplazar);
		frame.get_chk_marcar().setName(_Interface._chk_seleccionar);
		frame.get_btn_completar().setActionCommand(_Interface._btn_precodificar);
		frame.get_btn_importar().setActionCommand(_Interface._btn_importar);
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this.getLogic();
		logic.centrar();
		String idarticulo_desde="";
		String idarticulo_hasta="";
		
		idarticulo_desde=(String) this.getParameter(_parametros.articulo_desde);
		idarticulo_hasta=(String) this.getParameter(_parametros.articulo_hasta);
		
		try {
			if (idarticulo_desde!=null){
				if (idarticulo_desde.compareTo("")!=0 & idarticulo_desde.compareTo("")!=0){
					logic.Cargar(idarticulo_desde, idarticulo_hasta);
				}	
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
