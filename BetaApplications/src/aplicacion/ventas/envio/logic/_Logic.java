package aplicacion.ventas.envio.logic;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import aplicacion.ventas.presupuesto.gui._Frame;
import aplicacion.ventas.presupuesto.logic._Data;
import aplicacion.herramientas.java.Convertidor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	private aplicacion.herramientas.java.buscadores.Fecha bFecha=null;
	public void BuscarFecha(JTextField tx){
		if (bFecha==null){
			bFecha=new aplicacion.herramientas.java.buscadores.Fecha(this.getConstructor());
		}
		
		
		bFecha.Buscar(tx);
	}
	public void cargarCliente(String idcliente){
		
		Object[][] results=data.getClientInformation(idcliente);
		if (results!=null){
			if (results.length>0){
				String descripcion=(String) results[0][0];
				frame.get_txt_cliente_descripcion().setText(descripcion);
				frame.get_txt_fecha_desde().requestFocusInWindow();
				
			}
		}
	}
	
	private aplicacion.herramientas.java.evaluadores.PedidoCliente PedidoCliente=null;
	public void initialize_PedidoCliente(){
		PedidoCliente=new aplicacion.herramientas.java.evaluadores.PedidoCliente(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_cliente_descripcion().setText(descripcion);
			}
		};
		PedidoCliente.setConstructor(this.getConstructor());
	}
	public void BuscarPedidoCliente(JTextField tx){
		PedidoCliente.Buscar(tx);
	}
	public void BuscarPedidoCliente(){
		PedidoCliente.Buscar(frame.get_txt_idpedido());
	}
	public void buscarPedidoCliente(JTextField tx){
		PedidoCliente.buscar(tx);
	}
	
	public void evaluarPedidoCliente(JTextField tx){
		PedidoCliente.evaluate(tx);
	}


	
	public void buscar_fecha_desde(){
		this.BuscarFecha(frame.get_txt_fecha_desde());
	}

	
	
	public void cargar(){
		reporte();
	}
	
	public void evaluate_fecha_desde(JTextField tx){
		frame.get_txt_fecha_desde().requestFocusInWindow();
	}
	
	public void evaluate_fecha_hasta(JTextField tx){
		cargar();
	}
	
	
	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;
	@SuppressWarnings("unchecked")
	public void reporte(){
		if (reporte!=null){
			 reporte.dispose();
		}
		String empresa=data.getNombreEmpresa();
		String telefono=data.getTelefonoEmpresa();
		String email=data.getEmail();
		String idpedido=frame.get_txt_idpedido().getText();
		String fecha=frame.get_txt_fecha_desde().getText();
		String subtotal=frame.get_txt_subtotal().getText();
		String iva=frame.get_txt_iva().getText();
		String total=frame.get_txt_total().getText();
		String plazo_entrega=frame.get_lst_plazo_de_entrega().getSelectedItem().toString();
		String condicion_venta=frame.get_lst_condicion_de_venta().getSelectedItem().toString();
		String mantenimiento=frame.get_lst_mantenimiento().getSelectedItem().toString();
		reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param=new HashMap();
		param.put("idpedido",idpedido);
		param.put("fecha",fecha);
		param.put("subtotal",subtotal);
		param.put("iva",iva);
		param.put("total",total);
		param.put("condicion",condicion_venta);
		param.put("mantenimiento",mantenimiento);
		param.put("plazo_entrega",plazo_entrega);
		param.put("empresa",empresa);
		param.put("telefono",telefono);
		param.put("email",email);
		reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "PRESUPUESTO.jasper");	
		reporte.build(this.getConstructor());
		reporte.init();	
	}
	public void cargarPlazodeEntrega(){
		frame.get_lst_plazo_de_entrega().removeAllItems();
		frame.get_lst_plazo_de_entrega().addItem("Inmediata");
		frame.get_lst_plazo_de_entrega().addItem("24 Horas");
		frame.get_lst_plazo_de_entrega().addItem("24-48 Horas");
		frame.get_lst_plazo_de_entrega().addItem("48-72 Horas");
		frame.get_lst_plazo_de_entrega().addItem("7 Dias");
		frame.get_lst_plazo_de_entrega().addItem("10 Dias");
		frame.get_lst_plazo_de_entrega().addItem("15 Dias");
		
	}
		
	public void cargarMantenimientoOferta(){
		frame.get_lst_mantenimiento().removeAllItems();
		frame.get_lst_mantenimiento().addItem("7 dias");
		frame.get_lst_mantenimiento().addItem("15 dias");
		frame.get_lst_mantenimiento().addItem("20 Dias");
		frame.get_lst_mantenimiento().addItem("30 Dias");
		frame.get_lst_mantenimiento().addItem("45 Dias");
	}
	
	public void cargarCondiciondeVenta(){
		frame.get_lst_condicion_de_venta().removeAllItems();
		frame.get_lst_condicion_de_venta().addItem("Contado");
		frame.get_lst_condicion_de_venta().addItem("10 Dias Fecha Factura");
		frame.get_lst_condicion_de_venta().addItem("15 Dias Fecha Factura");
		frame.get_lst_condicion_de_venta().addItem("30 Dias Fecha Factura");
		frame.get_lst_condicion_de_venta().addItem("45 Dias Fecha Factura");
	}
	
	public void CargarPedido(String idpedido){
		frame.get_txt_idpedido().setText(idpedido);
		this.evaluarPedidoCliente(frame.get_txt_idpedido());
	}
	
	public void evaluate_tipo_reporte(JComboBox lst){
		frame.get_txt_idcliente().requestFocusInWindow();
	}
	
	public void clean(){
		frame.get_txt_cliente_descripcion().setText("");
		frame.get_txt_fecha_desde().setText("");
		frame.get_txt_idpedido().setText("");
		frame.get_txt_iva().setText("");
		frame.get_txt_subtotal().setText("");
		frame.get_txt_total().setText("");
		frame.get_txt_idcliente().setText("");
		getToday();
	}
	
	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_fecha_desde().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy"));
	}

}
