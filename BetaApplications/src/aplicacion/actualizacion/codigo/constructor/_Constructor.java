package aplicacion.actualizacion.codigo.constructor;

import aplicacion.actualizacion.codigo.interfaces._parametros;
import aplicacion.actualizacion.codigo.events.ActionListenerHandler;
import aplicacion.actualizacion.codigo.events.ItemListenerHandler;
import aplicacion.actualizacion.codigo.events.KeyListenerHandler;
import aplicacion.actualizacion.codigo.events.WindowAdapterHandler;
import aplicacion.actualizacion.codigo.gui._Frame;
import aplicacion.actualizacion.codigo.logic._Data;
import aplicacion.actualizacion.codigo.logic._Logic;
import aplicacion.actualizacion.codigo.interfaces.*;
import aplicacion.modelo.constructor.Constructor;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
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
		frame.get_txt_codigo_desde().setName(_Interface._txt_codigo_desde);
		frame.get_txt_codigo_hasta().setName(_Interface._txt_codigo_hasta);
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		frame.get_txt_linea().setName(_Interface._txt_linea);
		frame.get_btn_buscar_desde().setActionCommand(_Interface._btn_buscar_desde);
		frame.get_btn_buscar_hasta().setActionCommand(_Interface._btn_buscar_hasta);
		frame.get_btn_buscar_linea().setActionCommand(_Interface._btn_buscar_linea);
		frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_cancelar_operacion().setActionCommand(_Interface._btn_cancelar_tarea);
		frame.get_btn_completar().setActionCommand(_Interface._btn_completar);
		frame.get_btn_borrar().setActionCommand(_Interface._btn_borrar);
		frame.get_chk_marcar().setName(_Interface._chk_seleccionar);
		frame.get_btn_exportar().setActionCommand(_Interface._btn_exportar);
		frame.get_btn_importar().setActionCommand(_Interface._btn_importar);
	}
	public void init(){
		super.init();
		_Logic logic=(_Logic) this.getLogic();
		logic.initialize_proveedor();
		_Frame frame=(_Frame) this.getFrame();
		
		String idproveedor="";
		try {
			idproveedor = (String) this.getParameter(_parametros.idproveedor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String linea="";
		try {
			linea = (String) this.getParameter(_parametros.linea);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (idproveedor.compareTo("")!=0 & linea.compareTo("")!=0){
			frame.get_txt_idproveedor().setText(idproveedor);
			logic.evaluarProveedor(frame.get_txt_idproveedor());
			frame.get_txt_linea().setText(linea);
			
		}
		
		logic.focus();
	}
}
