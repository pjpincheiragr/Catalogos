package aplicacion.flota.auxilio.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.flota.auxilio.events.ActionListenerHandler;
import aplicacion.flota.auxilio.events.WindowAdapterHandler;
import aplicacion.flota.auxilio.events.KeyListenerHandler;
import aplicacion.flota.auxilio.gui._Frame;
import aplicacion.flota.auxilio.interfaces.*;
import aplicacion.flota.auxilio.logic._Data;
import aplicacion.flota.auxilio.logic._Logic;
import aplicacion.flota.auxilio.interfaces._Interface;
import aplicacion.flota.auxilio.events.DropTargetListenerHandler;

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

		frame.get_btn_buscar().setActionCommand(_Interface._btn_buscar);
		frame.get_btn_buscarAuxilio().setActionCommand(_Interface._btn_buscarAuxilio);
		frame.get_btn_buscarChofer().setActionCommand(_Interface._btn_buscarChofer);
		frame.get_btn_buscarChoferAuxilio().setActionCommand(_Interface._btn_buscarChoferAuxilio);
		frame.get_btn_buscarReemplazo().setActionCommand(_Interface._btn_buscarReemplazo);
		frame.get_btn_buscarUnidad().setActionCommand(_Interface._btn_buscarUnidad);
		frame.get_btn_calendario().setActionCommand(_Interface._btn_calendario);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_guardar().setActionCommand(_Interface._btn_guardar);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_lst_letra().setActionCommand(_Interface._lst_letra);
		frame.get_lst_localidad().setActionCommand(_Interface._lst_localidad);
		frame.get_lst_provincia().setActionCommand(_Interface._lst_localidad);
		frame.get_lst_tipoFalla().setActionCommand(_Interface._lst_tipoFalla);
		frame.get_table_mecanico().setName(_Interface._table_mecanico);
		frame.get_txt_chofer().setName(_Interface._txt_chofer);
		frame.get_txt_choferAuxilio().setName(_Interface._txt_choferAuxilio);
		frame.get_txt_detalle().setName(_Interface._txt_detalle);
		frame.get_txt_detalleAuxilio().setName(_Interface._txt_detalleAuxilio);
		frame.get_txt_detalleReemplazo().setName(_Interface._txt_detalleReemplazo);
		frame.get_txt_dominio().setName(_Interface._txt_dominio);
		frame.get_txt_falloAparente().setName(_Interface._txt_falloAparente);
		frame.get_txt_falloReal().setName(_Interface._txt_falloReal);
		frame.get_txt_fecha().setName(_Interface._txt_fecha);
		frame.get_txt_idauxilio().setName(_Interface._txt_idauxilio);
		frame.get_txt_idunidad().setName(_Interface._txt_idunidad);
		frame.get_txt_idunidadAuxilio().setName(_Interface._txt_idunidadAuxilio);
		frame.get_txt_idunidadReemplazo().setName(_Interface._txt_idunidadReemplazo);
		frame.get_txt_nombreChofer().setName(_Interface._txt_nombreChofer);
		frame.get_txt_nombreChoferAuxilio().setName(_Interface._txt_numero);
		frame.get_txt_numero().setName(_Interface._txt_numero);
		frame.get_txt_dominioAuxilio().setName(_Interface._txt_dominioReemplazo);
		frame.get_txt_dominioReemplazo().setName(_Interface._txt_dominioReemplazo);
		frame.get_txt_sucursal().setName(_Interface._txt_sucursal);
		frame.get_txt_ubicacion().setName(_Interface._txt_ubicacion);
		frame.get_btn_editarUnidad().setActionCommand(_Interface._btn_editarUnidad);
		frame.get_btn_editarUnidad1().setActionCommand(_Interface._btn_editarUnidad1);
		frame.get_btn_editarUnidad2().setActionCommand(_Interface._btn_editarUnidad2);
		frame.get_btn_editarChofer().setActionCommand(_Interface._btn_editarChofer);
		frame.get_btn_editarChofer2().setActionCommand(_Interface._btn_editarChofer2);
		
	}
	
	public void init(){
		super.init();
		
		_Logic _logic=(_Logic) this._logic;
		_logic.focus(); 
		this._frame.setVisible(true);
	}
	
}
