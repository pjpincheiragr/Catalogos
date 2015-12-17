package aplicacion.ventas.transferencia.logic;

import java.awt.Font;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.ComboBoxEditor;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.gestion.caja.manejo.logic.DateRenderer;
import aplicacion.gestion.caja.manejo.logic.MoneyRenderer;
import aplicacion.ventas.gestion2.constructor._Constructor;
import aplicacion.ventas.transferencia.gui._Frame;
import aplicacion.ventas.transferencia.interfaces._Interface;

import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;

public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private String formato="dd-MM-yyyy HH:mm:ss";
	private aplicacion.ventas.pedido.constructor._Constructor pedido=null;
	private aplicacion.ventas.gestion2.constructor._Constructor gestion2=null;
	public _Logic(){
		
	}
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public void setPedido(aplicacion.ventas.pedido.constructor._Constructor pedido){
		this.pedido=pedido;
	}
	
	
	
	public void clean(){
		
		frame.get_txt_fecha().setText("");
		frame.get_txt_idtransferencia().setText("");
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_idcreador().setText("");
		frame.get_txt_vendedor_descripcion().setText("");
		frame.get_txt_creador_detalle().setText("");
		frame.get_txt_vendedor_descripcion().setText("");
		this.setInitialDate();
	}
	
	public void cancelar(){
		if (preguntar("Confirmar","Cancela?")) {
			clean();
		}
	}
	
	
public void getToday(){
_Frame _frame=(_Frame) this._frame;
_frame.get_txt_fecha().setText(
			new Convertidor().getDateWithFormat("dd-MM-yyyy")
			);	
}
	
	
	
public void init(){
		nuevo();
		setInitialDate();
		if (pedido!=null){
			aplicacion.ventas.pedido.gui._Frame _frame_pedido=(aplicacion.ventas.pedido.gui._Frame) pedido.getFrame();
			String idpedido=_frame_pedido.get_txt_idpedido().getText();
			String idvendedor=_frame_pedido.get_txt_idvendedor().getText();
			if (idpedido.compareTo("")!=0){
				frame.get_txt_idcreador().setText(idvendedor);
				evaluarVendedor(frame.get_txt_idcreador());
				
				String descripcion=_frame_pedido.get_txt_pedido_descripcion().getText();
				String idcliente=_frame_pedido.get_txt_idcliente().getText();
				String cliente_nombre=_frame_pedido.get_txt_cliente_descripcion().getText();
				initial_focus();
				Object[] tmp=new Object[]{true,idpedido,descripcion,idcliente,cliente_nombre,"0"};
				List<Object[]> lista=new ArrayList<Object[]>();
				lista.add(tmp);
				frame.setJTable(this.crear_table_pedidos(lista));
				this.cargarDatosDefault();
			}	
		}
		if (gestion2!=null){
			aplicacion.ventas.gestion2.gui._Frame _frame_gestion=(aplicacion.ventas.gestion2.gui._Frame) gestion2.getFrame();
			String idvendedor=_frame_gestion.get_txt_idvendedor().getText();
			
			frame.get_txt_idcreador().setText(idvendedor);
			evaluarVendedor(frame.get_txt_idcreador());
			
			JTable table=_frame_gestion.getJTable();
			List<Object[]> lista=new ArrayList<Object[]>();
			
			int rows=0;
			for (int i=0;i<table.getRowCount();i++){
				boolean selected=false;
				try {
					selected=(Boolean) table.getValueAt(i, 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (selected){
					String idpedido=table.getValueAt(i, 1).toString();
					String descripcion=table.getValueAt(i, 2).toString();
					String idcliente=table.getValueAt(i, 3).toString();
					String cliente_nombre=table.getValueAt(i, 4).toString();
					String nivel=table.getValueAt(i, 5).toString();
					Object[] tmp=new Object[]{true,idpedido,descripcion,idcliente,cliente_nombre,nivel};
					lista.add(tmp);
				}
			}
			
			frame.setJTable(this.crear_table_pedidos(lista));
			this.cargarDatosDefault();
		}
}
	
		
public _Data getData(){
		return this.data;
	}
	
public void eliminar(){
	
}

public void exit_command(){
	pedido=null;
	super.exit_command();
}
/*
public void transferir(){
	String iduser_origen=frame.get_txt_idcreador().getText();
	String iduser_destino=frame.get_txt_idvendedor().getText();
	if (iduser_destino.compareTo("")!=0){
		if (this.Vendedor.existe(iduser_destino)& this.Vendedor.existe(iduser_origen)){
			String iduser = this.validar_usuario();
			if (iduser.compareTo("") != 0) {
				String idvendedor = "";
				try {
					idvendedor = (String) data.getVendedor(iduser)[0][0];
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idvendedor.compareTo("") != 0) {
					frame.get_txt_idvendedor().setText(idvendedor);
					this.evaluarVendedor(frame.get_txt_idvendedor());
					data.beginTransaction();
					String idpedido="";
					String idtransferencia=data.getProximoCorrecto();	
					
					
					
					String q="";
					//data.getInsert(idtransferencia, idpedido, iduser_origen, iduser_destino);
					
					data.clearBatch();
					data.addBatch(q);
					q=data.getUpdate(idpedido, iduser_destino);
					data.addBatch(q);
					boolean error=data.executeBatch();
					if (!error){
						data.commitTransaction();
						aviso("Se grabo correctamente la transferencia");
						pedido.dispose();
						this.exit_command();
					}else{
						data.rollbackTransaction();
						error("Error Grabando transferencia de pedido");
					}	
				
				}
			}else{
				error("OPERACION CANCELADA");
			}
			
		}
			
	}
	
	
}

*/
public void setInitialDate(){
	String aux=data.getSystemDate();
	frame.get_txt_fecha().setText(aux);
}

private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor = null;
public void initialize_Vendedor() {
	Vendedor = new aplicacion.herramientas.java.evaluadores.Vendedor() {
		public void cargar(JTextField tx) {
			String codigo=tx.getText();
			Object[][] results = this.getInfo(codigo);
			String descripcion = (String) results[0][1];
			String cod = (String) results[0][0];
			if (tx.getName()==_Interface._txt_idvendedor_destino){
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_vendedor_descripcion().setText(descripcion);
				cargarDatosDefault();
			}else{
				frame.get_txt_idcreador().setText(cod);
				frame.get_txt_creador_detalle().setText(descripcion);
				frame.get_txt_idvendedor().requestFocusInWindow();
			}
		}
	};
	Vendedor.setConstructor(this.getConstructor());
}


public void reconnect_Vendedor() {
	if (Vendedor != null) {
		Vendedor.setConstructor(this.getConstructor());
	}

}

public void BuscarVendedor(JTextField tx) {
	Vendedor.Buscar(tx);
}

public void BuscarVendedor() {
	Vendedor.Buscar(frame.get_txt_idvendedor());
}

public void BuscarVendedorDestino() {
	Vendedor.Buscar(frame.get_txt_idvendedor());
}

public void buscarVendedor(JTextField tx) {
	Vendedor.buscar(tx);
}

public void evaluarVendedor(JTextField tx) {
	tx.setText(tx.getText().replaceAll(" ", ""));
	Vendedor.evaluate(tx);	
	
}	

public void initial_focus(){
	frame.get_txt_idvendedor().requestFocusInWindow();
}

public void nuevo() {
	
	String idtransferencia = data.getProximoCorrecto();
	
	 int inc=0;
	while (data.existe(idtransferencia) & inc<3){
		data.getUpdateTC();
		idtransferencia = data.getProximoCorrecto();
		inc++;
	}
	if (inc<3){
		frame.get_txt_idtransferencia().setText(idtransferencia);
	  }
	 
	
}

public String validar_vendedor() {
	String idvendedor = "";
	while (idvendedor.compareTo("") == 0) {
		String password = this.requestPassword("Ingrese Su Clave:");
		idvendedor = data.getVendedorValidacion(password);
		if (idvendedor.compareTo("") == 0) {
			error("Error de Validacion de Usuario");
		}
	}
	return idvendedor;
}
private void _guardar_transferencia(){
	JTable table=frame.getJTable();
	
	String iduser_destino=frame.get_txt_idvendedor().getText();
	if (this.Vendedor.existe(iduser_destino)){
		int selections=0;
		for (int i=0;i<table.getRowCount();i++){
			if ((Boolean)table.getValueAt(i, 0)){
				selections++;
			}
		}
		if (table!=null){
			
			if (selections>0){
				String idvendedor = this.validar_vendedor();
				String old=frame.get_txt_idcreador().getText();
				
				if (idvendedor.compareTo(old)==0){
					
					
					data.beginTransaction();
					data.clearBatch();
					String idtransferencia=data.getProximoCorrecto();
					for (int i=0;i<table.getRowCount();i++){
						if ((Boolean)table.getValueAt(i, 0)){
							String idpedido=table.getValueAt(i, 1).toString();
							String level=table.getValueAt(i, 5).toString();
							
							if (level.compareTo("")==0){
								level="0";
							}
							String q=data.getInsert(idtransferencia, idpedido, idvendedor, iduser_destino, "", "");
							data.addBatch(q);
							q=data.getUpdate(idpedido, iduser_destino);
							data.addBatch(q);
							if (data.existeCategoria(idpedido, iduser_destino)){
								q=data.getUpdateLevel(idpedido, iduser_destino, level);
							}else{
								q=data.insertCategoria(idpedido, iduser_destino, "0",level);
							}
							data.addBatch(q);
							q=data.getUpdateTC();
							data.addBatch(q);	
						}
						
					}
					boolean error=data.executeBatch();
					if (!error){
						if (frame.get_chk_avisar_destino().isSelected()){
						  error=this.guardar_aviso_destinatario();
						  if (error){
							  error("Error grabando aviso a destinatario");
						  }
						}
						if (!error){
							if (frame.get_chk_recordarme().isSelected()){
								error=this.guardar_aviso_remitente();
								if (error){
									  error("Error grabando aviso a remitente");
								}
							}	
						}
					}
					
					if (!error){
						data.commitTransaction();
						aviso("Se transfirio Correctamente");
						
						if (pedido!=null){
							pedido.dispose();
						}
						if (gestion2!=null){
							aplicacion.ventas.gestion2.logic._Logic _logic=
							(aplicacion.ventas.gestion2.logic._Logic) gestion2.getLogic();
							_logic.goCargar();
						}
						this.getConstructor().dispose();
					}else{
						data.rollbackTransaction();
						error("Error transfiriendo");
					}
					
				}else{
					error("No esta autorizado");
				}
			}else{
				error("No hay pedidos seleccionados para transferir");
			}
		}
		
	}else{
		error("Error en usuario destino");
	}
}

public void cerrar_trasnferencia(){
	frame.setVisible(false);
	frame.dispose();
}
public boolean _check_transferir(){
	boolean ok=true;
	JTable table=frame.getJTable();
	String iduser_destino=frame.get_txt_idvendedor().getText();
	if (this.Vendedor.existe(iduser_destino)){
		int selections=0;
		for (int i=0;i<table.getRowCount();i++){
			if ((Boolean)table.getValueAt(i, 0)){
				selections++;
			}
		}
		if (table!=null){
			
			if (selections>0){
				if (frame.get_chk_avisar_destino().isSelected()){
					ok=this.check_aviso_destinatario();
					if (ok){
						if (frame.get_chk_recordarme().isSelected()){
							ok=this.check_aviso_remitente();
							if (!ok){
								error("Verifique aviso a remitente");
							}
						}
					}else{
						error("Verifique aviso a destinatario");
					}
				}
			}else{
				error("No hay pedidos seleccionados para transferir");
				ok=false;
			}
		}
		
	}else{
		ok=false;
		error("Error en usuario destino");
	}
	return ok;
}

public boolean check_aviso_remitente(){
	boolean ok=true;
	if (frame.get_chk_recordarme().isSelected()){
		String titulo=frame.get_txt_titulo_remitente().getText();
		String idcreador=frame.get_txt_idcreador().getText();
		String agenda=frame.get_txt_agenda_remitente().getText();
		if (Vendedor.existe(idcreador)){
				if (Hora.esCorrecta(agenda)){
					if (titulo.compareTo("")!=0){
							
					}else{
					error("Error en Titulo de Aviso de Remitente");
					ok=false;
				}
				}else{
					error("Error en fecha de Aviso de Remitente");
					ok=false;
				}
				
			}else{
				error("Creador Invalido");
				ok=false;
			}
	}
	return ok;
}

private aplicacion.herramientas.java.evaluadores.Hora Hora = null;

public void initialize_Hora() {
	Hora = new aplicacion.herramientas.java.evaluadores.Hora() {
		public void cargar(JTextField tx) {
			if (tx.getName()==_Interface._txt_agenda_remitente){
				frame.get_txt_titulo_remitente().requestFocusInWindow();	
			}else{
				frame.get_txt_titulo_destinatario().requestFocusInWindow();
			}
			
		}
	};
	Hora.setConstructor(this.getConstructor());
}

public void BuscarHora(JTextField tx) {
	Hora.Buscar(tx);
}

public void BuscarHoraRemitente() {
	Hora.Buscar(frame.get_txt_agenda_remitente());
}
public void BuscarHoraDestinatario() {
	Hora.Buscar(frame.get_txt_agenda_destinatario());
}

public void evaluarHora(JTextField tx) {
	Hora.evaluate(tx);
}
public boolean check_aviso_destinatario(){
	boolean ok=true;
	if (frame.get_chk_recordarme().isSelected()){
		String titulo=frame.get_txt_titulo_destinatario().getText();
		String idcreador=frame.get_txt_idvendedor().getText();
		String agenda=frame.get_txt_agenda_destinatario().getText();
		if (Vendedor.existe(idcreador)){
				if (Hora.esCorrecta(agenda)){
					if (titulo.compareTo("")!=0){
							
					}else{
					error("Error en Titulo de Aviso de Destinatario");
					ok=false;
				}
				}else{
					error("Error en fecha de Aviso de Destinatario");
					ok=false;
				}
				
			}else{
				error("Destinatario Invalido");
				ok=false;
			}
	}
	return ok;
}

public boolean guardar_aviso_remitente(){
	String titulo=frame.get_txt_titulo_remitente().getText();
	String idcreador=frame.get_txt_idcreador().getText();
	String destinatario=idcreador;
	String agenda=frame.get_txt_agenda_remitente().getText();
	String mensaje=frame.get_txt_mensaje_remitente().getText();
	String privado="0";
	String pantalla="1";
	String leido="0";
	String idtransferencia=frame.get_txt_idtransferencia().getText();
	boolean error=
		this._guardar(titulo, mensaje, idcreador, agenda, privado, pantalla, leido, destinatario,idtransferencia);
	return error;
}

public boolean guardar_aviso_destinatario(){
	String titulo=frame.get_txt_titulo_destinatario().getText();
	String idcreador=frame.get_txt_idcreador().getText();
	String destinatario=frame.get_txt_idvendedor().getText();
	String agenda=frame.get_txt_agenda_destinatario().getText();
	String mensaje=frame.get_txt_mensaje_destinatario().getText();
	String privado="0";
	String pantalla="1";
	String leido="0";
	String idtransferencia=frame.get_txt_idtransferencia().getText();
	boolean error=
		this._guardar(titulo, mensaje, idcreador, agenda, privado, pantalla, leido, destinatario,idtransferencia);
	return error;
}
private boolean _guardar(String titulo,String mensaje,String idcreador,String agenda,String privado,String pantalla,String leido,String destinatario,String idtransferencia){
	String tc="TRPC";
	String q="";
	String idaviso=data.getProximoAvisoCorrecto();
	int n=0;
	boolean existe=data.exist(idaviso);
	while (n<3 & existe){
			idaviso=data.getProximoAvisoCorrecto();
			existe=data.exist(idaviso);
			n++;
	}
	if (!existe){
		q=data.getInsert(idaviso, titulo, mensaje, idcreador, agenda,privado,pantalla);
		
	}
	data.clearBatch();
	System.out.println(q);
	data.addBatch(q);
	q=data.getDeleteAsociado(idaviso);
	data.addBatch(q);
	q=data.getInsertAsociado(idaviso, tc, idtransferencia);
	data.addBatch(q);
	q=data.getInsertAvisoUser(idaviso, destinatario,agenda,pantalla);
	System.out.println(q);
	data.addBatch(q);	
	q=data.getUpdateAvisos();
	System.out.println(q);
	data.addBatch(q);	
	boolean error=data.executeBatch();
	return error;
}

public String getRoll(String fecha,int months,int days,int hours,int minutes) {
	Date today = new Convertidor().getDateWithFormat(formato, formato, fecha);
	java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
	_date.setTime(today);
	if (months!=0){
		_date.add(Calendar.MONTH, months);	
	}
	if (days!=0){
		_date.add(Calendar.DAY_OF_MONTH, days);	
	}
	if (hours!=0){
		_date.add(Calendar.HOUR_OF_DAY, hours);	
	}
	if (minutes!=0){
		_date.add(Calendar.MINUTE, minutes);	
	}
	// _today.add(Calendar.DAY_OF_YEAR, days);
	Date before = _date.getTime();
	String s2 = new Convertidor().getDateWithFormat(formato, before);
	return s2;
}

public void evaluar_posponer(JComboBox cb){
	int option=cb.getSelectedIndex();
	String fecha=data.getSystemDateTime();
	if (Hora.esCorrecta(fecha)){
		if (option==1){
			fecha=this.getRoll(fecha, 0, 0, 0, 5);
		}
		if (option==2){
			fecha=this.getRoll(fecha, 0, 0, 0, 10);
		}
		if (option==3){
			fecha=this.getRoll(fecha, 0, 0, 0, 15);
		}
		if (option==4){
			fecha=this.getRoll(fecha, 0, 0, 0, 30);
		}
		if (option==5){
			fecha=this.getRoll(fecha, 0, 0, 1, 0);
		}
		if (option==6){
			fecha=this.getRoll(fecha, 0, 0, 2, 0);
		}
		if (option==7){
			fecha=this.getRoll(fecha, 0, 1, 0, 0);
		}
		if (cb.getName()==_Interface._lst_posponer_remitente){
			frame.get_txt_agenda_remitente().setText(fecha);	
		}else{
			frame.get_txt_agenda_destinatario().setText(fecha);
		}
	}
	
}

private String get_pedidos() {
	String msj="";
	JTable table=frame.getJTable();
	
	for (int i=0;i<table.getRowCount();i++){
		boolean selected=false;
		try {
			selected=(Boolean) table.getValueAt(i, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (selected){
			if (msj.length()==0){
				msj+="Listado de Pedidos Transferidos al "+data.getSystemDate()+":";
				msj+=this.LINE_SEPARATOR;
			}
			String idpedido=table.getValueAt(i, 1).toString();
			String descripcion=table.getValueAt(i, 2).toString();
			String cuenta=table.getValueAt(i, 3).toString();
			String nombre=table.getValueAt(i, 4).toString();
			msj+=idpedido+" "+descripcion+" "+cuenta+" "+nombre+"";
			msj+=this.LINE_SEPARATOR;
		}
	}
	return msj;
}
public void cargar_posponer(){
	frame.get_lst_posponer().removeItemListener(this.getConstructor().getItemListener());
	frame.get_lst_posponer().removeAllItems();
	frame.get_lst_posponer().addItem("");
	frame.get_lst_posponer().addItem("5 Minutos");
	frame.get_lst_posponer().addItem("10 Minutos");
	frame.get_lst_posponer().addItem("15 Minutos");
	frame.get_lst_posponer().addItem("30 Minutos");
	frame.get_lst_posponer().addItem("1 Hora");
	frame.get_lst_posponer().addItem("2 Horas");
	//frame.get_lst_posponer().addItem("Proximo Turno");
	frame.get_lst_posponer().addItem("1 Dia");
	frame.get_lst_posponer().addItemListener(this.getConstructor().getItemListener());
}
public aplicacion.ventas.gestion2.constructor._Constructor getGestion2() {
	return gestion2;
}
public void setGestion2(
		aplicacion.ventas.gestion2.constructor._Constructor gestion2) {
	this.gestion2 = gestion2;
}
public void transferir(){
	if (this._check_transferir()){
		this._guardar_transferencia();
	}else{
		error("Operacion Cancelada");
	}
}



private JTable crear_table_pedidos(List<Object[]> pedidos) {
	
	Object[][] results = new Object[pedidos.size()][6];
	int rows=0;
	for (int i=0;i<pedidos.size();i++){
		Object[] tmp =pedidos.get(i);
		boolean selected=false;
		try {
			selected=(Boolean) tmp[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (selected){
			results[rows][0]=true;
			results[rows][1]=tmp[1].toString();
			results[rows][2]=tmp[2].toString();
			results[rows][3]=tmp[3].toString();
			results[rows][4]=tmp[4].toString();
			results[rows][5]=tmp[5].toString();
			rows++;
		}
	}
	return this.create_table_pedidos(results);
}

/**
 * chk|idarticulo|descripcion|cant
 * 
 * @param results
 */
private JTable create_table_pedidos(Object[][] results) {
	
	CustomTable table = new CustomTable();
	CellEditor pce = null;
	CheckBoxCellEditor cbce = null;

	table.setSortable(false);
	Column col = null;

	col = new Column();
	col.setName("");
	col.setWidth(30);
	col.setEditable(true);
	col.setClass(Boolean.class);
	cbce = new CheckBoxCellEditor();
	cbce.setItemListener(this.getConstructor().getItemListener());
	col.setCellEditor(cbce.getCellCheck());
	table.addColumn(col);

	col = new Column();
	col.setName("idpedido");
	col.setWidth(80);
	col.setEditable(false);
	col.setAligment(JTextField.RIGHT);
	table.addColumn(col);

	col = new Column();
	col.setName("descripcion");
	col.setWidth(210);
	col.setEditable(false);
	col.setAligment(JTextField.LEFT);
	table.addColumn(col);

	col = new Column();
	col.setName("cuenta");
	col.setWidth(90);
	col.setEditable(false);
	col.setClass(String.class);
	col.setAligment(JTextField.RIGHT);
	table.addColumn(col);

	col = new Column();
	col.setName("Nombre");
	col.setWidth(160);
	col.setEditable(false);
	col.setClass(String.class);
	col.setAligment(JTextField.RIGHT);
	table.addColumn(col);

	col = new Column();
	col.setName("Nivel");
	col.setWidth(44);
	col.setEditable(true);
	col.setAligment(JTextField.RIGHT);
	ComboBoxEditor cb=new ComboBoxEditor();
	String[] values=new String[10];
	for (int i=0;i<values.length;i++){
		values[i]=""+i;
	}
	cb.setValues(values);
	
	col.setCellEditor(cb.getCellEditor());
	table.addColumn(col);
	
	table.setData(results);
	table.addKeyListener(this._constructor.getKeyListener());
	Font fuente = new Font("Dialog", Font.PLAIN, 10);
	table.setHeaderFont(fuente);
	table.setFont(fuente);
	table.build();
	table.fillData();

	JTable _table = table.getTable();

	return _table;
}
public aplicacion.ventas.pedido.constructor._Constructor getPedido() {
	return pedido;
}

public void cargarDatosDefault(){
	
	String iduser_origen=frame.get_txt_idcreador().getText();
	String iduser_destino=frame.get_txt_idvendedor().getText();
	String msj_pedidos=this.get_pedidos();
	frame.get_txt_mensaje_destinatario().setText(msj_pedidos);
	frame.get_txt_mensaje_remitente().setText(msj_pedidos);
	if (iduser_destino.compareTo("")!=0){
		if (Vendedor.existe(iduser_destino)){
			String destinatario=data.getVendedorNombre(iduser_destino);
			String titulo_remitente="Verifique el estado de los pedidos transferidos a "+destinatario;
			this.frame.get_txt_titulo_remitente().setText(titulo_remitente);
		}
	}
	
	String remitente=frame.get_txt_creador_detalle().getText();
	String titulo_destinatario="Tiene nuevos Pedidos transferidos de "+remitente+" para resolver ";
	
	this.frame.get_txt_idcreador().setText(iduser_origen);
	this.frame.get_txt_creador_detalle().setText(remitente);
	
	this.frame.get_txt_titulo_destinatario().setText(titulo_destinatario);
	String fecha1=data.getSystemDateTime();
	String fecha2=this.getRoll(fecha1, 0, 0, 0, 20);
	this.frame.get_txt_agenda_destinatario().setText(fecha1);
	this.frame.get_txt_agenda_remitente().setText(fecha2);
	
}



}

