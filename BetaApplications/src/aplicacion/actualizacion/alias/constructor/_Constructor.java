package aplicacion.actualizacion.alias.constructor;

import aplicacion.actualizacion.alias.gui.*;
import aplicacion.actualizacion.alias.logic.*;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.actualizacion.alias.interfaces._Interface;
import aplicacion.actualizacion.alias.interfaces._parametros;
import aplicacion.actualizacion.alias.events.ActionListenerHandler;
import aplicacion.actualizacion.alias.events.ItemListenerHandler;
import aplicacion.actualizacion.alias.events.MouseListenerHandler;
import aplicacion.actualizacion.alias.events.KeyListenerHandler;
import aplicacion.actualizacion.alias.events.WindowAdapterHandler;

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
	
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
	}
	
	public void initialize_components(){
		_Frame frame=(_Frame)_frame;
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_borrar().setActionCommand(_Interface._btn_borrar);
		frame.get_btn_buscar_articulo_desde().setActionCommand(_Interface._btn_buscar_articulo_desde);
		frame.get_btn_buscar_articulo_hasta().setActionCommand(_Interface._btn_buscar_articulo_hasta);
		frame.get_btn_buscar_linea().setActionCommand(_Interface._btn_buscar_linea);
		frame.get_btn_buscar_proveedor().setActionCommand(_Interface._btn_buscar_proveedor);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_copiar().setActionCommand(_Interface._btn_copiar);
		frame.get_btn_pegar().setActionCommand(_Interface._btn_pegar);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_cancelar_tarea().setActionCommand(_Interface._btn_cancelar_tarea);
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_completar().setActionCommand(_Interface._btn_completar);
		frame.get_txt_idarticulo_desde().setName(_Interface._txt_idarticulo_desde);
		frame.get_txt_idarticulo_hasta().setName(_Interface._txt_idarticulo_hasta);
		frame.get_txt_idproveedor().setName(_Interface._txt_idproveedor);
		frame.get_txt_linea().setName(_Interface._txt_linea);
		frame.get_btn_autoalias().setActionCommand(_Interface._btn_autoalias);
		frame.get_chk_seleccionar().setName(_Interface._chk_seleccionar);
		
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this.getLogic();
		logic.initialize_Codigo();
		_Frame frame=(_Frame)_frame;
		logic.initialize_proveedor();
		String idarticulo_desde="";
		String idarticulo_hasta="";
		String idproveedor="";
		String linea="";
		idarticulo_desde="";
		try {
			idarticulo_desde=(String) this.getParameter(_parametros.articulo_desde);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		idarticulo_hasta=(String) this.getParameter(_parametros.articulo_hasta);
		try {
			idproveedor=(String) this.getParameter(_parametros.idproveedor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			linea=(String) this.getParameter(_parametros.linea);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (linea!=null & idproveedor!=null){
			if (linea.compareTo("")!=0 & idproveedor.compareTo("")!=0){
				if (idproveedor.compareTo("")!=0){
							frame.get_txt_idproveedor().setText(idproveedor);
							logic.evaluarProveedor(frame.get_txt_idproveedor());
				}
							
				frame.get_txt_linea().setText(linea);	
				logic.evaluar_linea(frame.get_txt_linea());
				logic.goCargar();
			}else{
				if (idarticulo_desde.compareTo("")!=0 & idarticulo_hasta.compareTo("")!=0){
					frame.get_txt_idarticulo_desde().setText(idarticulo_desde);
					frame.get_txt_idarticulo_hasta().setText(idarticulo_hasta);
					logic.goCargar();
				}
			}	
		}else{
			if (idarticulo_desde.compareTo("")!=0 & idarticulo_hasta.compareTo("")!=0){
				frame.get_txt_idarticulo_desde().setText(idarticulo_desde);
				frame.get_txt_idarticulo_hasta().setText(idarticulo_hasta);
				logic.goCargar();
			}
		}
		
		logic.focus();
	}
}
