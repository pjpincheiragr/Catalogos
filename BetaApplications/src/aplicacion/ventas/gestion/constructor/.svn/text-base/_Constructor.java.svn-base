package aplicacion.ventas.gestion.constructor;



import aplicacion.ventas.gestion.events.ActionListenerHandler;
import aplicacion.ventas.gestion.events.ItemListenerHandler;
import aplicacion.ventas.gestion.events.KeyListenerHandler;
import aplicacion.ventas.gestion.events.MouseListenerHandler;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.ventas.gestion.logic.*;
import aplicacion.ventas.gestion.gui.*;
import aplicacion.ventas.gestion.interfaces.*;
public class _Constructor extends Constructor {

	public void initialize_logic(){
		_logic=new _Logic();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	public void initialize_frame(){
		_frame=new _Frame();
	}
	
	public void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	public void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	
	public void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
	}
	public void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
	}
	
	public void initialize_components(){
		_Frame frame=(_Frame) this._frame;
		frame.get_list_anio().setName(_Interface._lst_anio);
		frame.get_list_mes().setName(_Interface._lst_mes);
		frame.get_lst_estado().setName(_Interface._lst_estado);
		frame.get_txt_idarticulo().setName(_Interface._txt_idarticulo);
		frame.get_txt_idarticulo_descripcion().setName(_Interface._txt_idarticulo_descripcion);
		frame.get_txt_idarticulo_linea().setName(_Interface._txt_idarticulo_linea);
		frame.get_txt_idcliente().setName(_Interface._txt_cliente);
		frame.get_txt_cliente_descripcion().setName(_Interface._txt_cliente_descripcion);
		frame.get_txt_idpedido().setName(_Interface._txt_idpedido);
		frame.get_txt_pedido_descripcion().setName(_Interface._txt_idpedido_descripcion);
		frame.get_txt_idvendedor().setName(_Interface._txt_idvendedor);
		frame.get_txt_vendedor_descripcion().setName(_Interface._txt_idvendedor_nombre);
		frame.get_txt_idcreador().setName(_Interface._txt_idcreador);
		frame.get_txt_creador().setName(_Interface._txt_idcreador_nombre);
		frame.get_btn_cargar().setActionCommand(_Interface._btn_cargar);
		frame.get_btn_cancelar().setActionCommand(_Interface._btn_cancelar);
		frame.get_btn_editar_cpte().setActionCommand(_Interface._btn_editar);
		frame.get_btn_consultar().setActionCommand(_Interface._btn_consulta);
		frame.get_btn_nuevo().setActionCommand(_Interface._btn_nuevo);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_txt_pedido_informacion().setName(_Interface._txt_idpedido_informacion);
		frame.get_txt_pedido_remito().setName(_Interface._txt_idpedido_remito);
	}
	public void init(){
		super.init();
		_Logic logic=(_Logic)this._logic;
		_Frame frame=(_Frame)this._frame;
		logic.initialize_cliente();
		logic.initialize_PDC();
		logic.initialize_Vendedor();
		logic.initialize_Articulo();
		logic.initialize_Linea();
		
		logic.fechas();
		logic.centrar();
		try {
			logic.initial();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logic.fillTable();
		logic.load_estados();
		String idcliente="";
		try {
			idcliente=(String)this.getParameter(_parametros.idcuenta);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		if (idcliente!=null){
			if (idcliente.compareTo("")!=0){
				frame.get_txt_idcliente().setText(idcliente);
			}	
		}
		
		System.out.println("Cargar Pedidos");
		logic.completeDiaCarga();
		logic.goCargar();
		
	}
}
