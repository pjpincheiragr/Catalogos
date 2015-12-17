package aplicacion.gestion.transferencia.logic;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import java.util.Locale;
import javax.swing.table.*;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import aplicacion.herramientas.java.*;



import aplicacion.gestion.transferencia.gui._Frame;
import aplicacion.gestion.transferencia.interfaces._Interface;
import aplicacion.gestion.transferencia.logic.extensions.Transferencia_Medios_de_Pago;
import aplicacion.gestion.transferencia.logic.extensions.Transferencia_Asiento;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;

public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	
	/*
	 * variables para la cobranza directa de caja. online 
	 */
	private String cpte_tc="TR";
	
	
	public _Logic(){
		Transferencia_Medios_de_Pago control=new Transferencia_Medios_de_Pago();
		Transferencia_Asiento asiento=new Transferencia_Asiento();
		this.addExtension(control);
		this.addExtension(asiento);
		
	}
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	

	
	/*
	 * Para obtener el proximo numero de Pago disponible
	 */
	public void obtener_proximo_cpte(){
		
		String cb=data.getProximoPGCorrecto();
		//Pago_frame _frame=(Pago_frame) this._frame;
		frame.get_txt_idtransferencia().setText(cb);
	}
	

	
	
	public void clean(){
		
		frame.get_txt_total_pago().setText("0.00");
		frame.get_btn_confirmar().setEnabled(false);
		frame.get_txt_fecha().setText("");
		frame.get_txt_leyenda().setText("");
		frame.setJTableMedios(null);
		frame.get_txt_estado().setText("");
		frame.get_txt_concepto().setText("");
		frame.get_txt_fecha_desde().setText("");
		frame.get_txt_fecha_hasta().setText("");
		frame.get_txt_estado().setForeground(Color.white);
		frame.get_txt_concepto().setEditable(true);
		frame.get_txt_caja_destino_detalle().setText("");
		frame.get_txt_caja_origen_detalle().setText("");
		frame.get_txt_idtransferencia().setEditable(true);
		frame.get_btn_buscar_pago().setEnabled(true);
		frame.get_list_caja_origen().setEnabled(true);
		frame.get_list_caja_destino().setEnabled(true);
		frame.get_btn_fecha().setEnabled(true);
		frame.get_txt_fecha().setEditable(true);
		frame.get_txt_fecha_desde().setEditable(true);
		frame.get_txt_fecha_hasta().setEditable(true);
		frame.get_btn_autocompletar().setEnabled(true);
		frame.get_btn_fecha_desde().setEnabled(true);
		frame.get_btn_fecha_hasta().setEnabled(true);
		
		
		
		
		//get_list_caja_origen(
		this.obtener_proximo_cpte();
		cargar_cajas();
			
	}
	
	private boolean eval_rows(){
		System.out.println("Eval rows");
		int rows=0;
		boolean ok=true;
		int i=0;
		while (i<frame.getJTableMedios().getRowCount() & ok){
			String tipo="";
			String _imp="";
			try {
				tipo=frame.getJTableMedios().getValueAt(i, 0).toString();
				_imp=frame.getJTableMedios().getValueAt(i, 7).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (tipo.compareTo("")!=0 & _imp.compareTo("")!=0){
				ok=this.eval_row(i);
				if (ok){
					rows++;
				}
			}
			
		i++;
		}
		if (ok){
			ok=(rows>0);
		}
		return ok;
	}

	public void confirmar_recepcion(){
		String idtransferencia=frame.get_txt_idtransferencia().getText();
		String iduser=this.getConstructor().getIduser();
		String tc="TR";
		String idcaja=frame.get_list_caja_destino().getSelectedItem().toString();
		
		boolean su=data.puedeEscribirCaja(iduser, idcaja);
		if (!su){
			error("Requiere validacion de un Supervisor");	
		}
		String password=this.requestPassword("Ingrese Clave de Supervisor");
		iduser=this.getValidacion(password);
		if (iduser.compareTo("")!=0){
				su=true;
		}else{
			su=false;
				error("No Esta Autorizado");
		}
				
		
		if (su){
			data.beginTransaction();
			String ip=data.getIp();
			boolean error=data.registrar_operacion(tc, idtransferencia, "", iduser, ip, "Confirmacion de Recepcion");
			if (!error){
				data.clearBatch();
				String q=data.getConfirmarAutorizacion(tc, idtransferencia, iduser, ip);
				data.addBatch(q);
				error=data.executeBatch();
					
			}
			if (!error){
			
				data.commitTransaction();
				this.clean();
				aviso("Se Confirmo Correctamente la Transferencia");
				
			}else{
				data.rollbackTransaction();
				error("Error Grabando Confirmacion");
			}
		}else{
			error("Confirmacion Abortada");
		}
	}
	
	private boolean eval_row(int r){
		boolean ok=false;
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		String tipo="";
		String _imp="";
		try {
			tipo=frame.getJTableMedios().getValueAt(r, 0).toString();
			_imp=frame.getJTableMedios().getValueAt(r, 7).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		double imp=0;
		if (_imp.compareTo("")!=0){
			try {
				imp=new Double(_imp.replace(",", ""));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if (tipo.compareTo("DO")==0){
			if (imp>0){
				ok=true;
			}else {
				this.error("El importe no puede ser nulo");
			}
		}
		
		if (tipo.compareTo("EF")==0){
			if (imp>0){
				ok=true;
			}else {
				this.error("El importe no puede ser nulo");
			}
		}
		if (tipo.compareTo("US")==0){
			if (imp>0){
				ok=true;
			}else {
				this.error("El importe no puede ser nulo");
			}
		}
		if (tipo.compareTo("CHT")==0){
			if (imp>0){
				String idbanco=""+frame.getJTableMedios().getValueAt(r, 2);
				String serie=""+frame.getJTableMedios().getValueAt(r, 4);
				String numero=""+frame.getJTableMedios().getValueAt(r, 5);
				
				ok=control.evaluar_disponibilidad_cheque(idbanco, serie, numero);
				String idcajas=frame.get_list_caja_origen().getSelectedItem().toString().replaceAll(" ", "");
				if (!ok){
					this.error("El Cheque "+idbanco+" "+serie+"-"+numero+" no se encuentra disponible en Caja "+idcajas);
				}
			}else {
				this.error("El importe no puede ser nulo");
				
			}
		}
		return ok;
	}
	
	public void _anular_transferencia(String idcomprobante){
		String tc=this.cpte_tc;
		if (this.confirmar("Confirme eliminacion de transferencia ", 2)){
			List<String> instrucciones=data.getInstruccionesEliminacion(tc, idcomprobante);
			data.beginTransaction();
			data.clearBatch();
			for (int i=0;i<instrucciones.size();i++){
				data.addBatch(instrucciones.get(i));
			}
			boolean error=data.executeBatch();
			
			if (!error){
				data.commitTransaction();
				aviso("Se anulo Correctamente el Transferencia");
				this.clean();
			}else {
				data.rollbackTransaction();
				error("Error anulando el Transferencia");
			}	
		}
		
	}
	public void anular(){
		String ip=data.getIp();
		String fecha=frame.get_txt_fecha().getText();
		String tc=this.cpte_tc;
		String idcomprobante=frame.get_txt_idtransferencia().getText();
		String iduser=this.getConstructor().getIduser();
		String idtransferencia=frame.get_txt_idtransferencia().getText();
		if (idtransferencia.compareTo("")!=0){
			
			if (data.anulada(idtransferencia)){
				error("La Transferencia "+idtransferencia+" ya fue anulada");
			}else {
				
				if (data.exist_transferencia(idtransferencia)){
					
							
					boolean su=!(this.esFechaVieja(fecha));
					if (!su){
						error("Esta eliminando una Transferencia Vieja");
					}
					if (!su){
							if (iduser.compareTo("")!=0){
								su=true;
								//validacion=iduser;
								
							}else{
								data.registrar_operacion(tc, idcomprobante, "", iduser,ip, "Intento de Anular Transferencia Vieja");
								error("No Esta Autorizado");
							}
					}else{
							su=true;
					
					}
					
					
					if (su){
						data.registrar_operacion(tc, idcomprobante, "", iduser,ip, "Anulacion de Transferencia");
						this._anular_transferencia(idtransferencia);
					}
					
						
				}else {
					error("La Transferencia "+idtransferencia+" no existe");
				}
				
			}	
		}
		
		
	}
	
	
	
	public void cancelar(){
		int operacion = JOptionPane.showConfirmDialog(
			    this._frame,
			    "Cancela Operacion?",
			    "Confirmar",
			    JOptionPane.YES_NO_OPTION);
		if ( operacion == JOptionPane.YES_OPTION) {
			clean();
			frame.get_txt_idtransferencia().setEditable(true);
			obtener_proximo_cpte();
		}
	}
	
	
	public void getToday(){
		_Frame _frame=(_Frame) this._frame;
		_frame.get_txt_fecha().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy")
				);	
	}
	
	public void _evaluar_idtransferencia(){
		this._evaluar_idtransferencia(frame.get_txt_idtransferencia());
	}
	public boolean permite(String idx){
		boolean permite=false;
		
		Object[][] results=data.getCajaOrigen(idx);
		if (results!=null){
			String idcaja=results[0][0].toString();
			String iduser=this.getConstructor().getIduser();
			permite=data.puedeVerCaja(iduser, idcaja);
		}
		
		return permite;
	}
	public void _evaluar_idtransferencia(JTextField tx){
		String idx=tx.getText();
		
		if (data.exist_transferencia(idx)){
			if (this.permite(idx)){
				cargarTransferencia(idx);	
			}else{
				error("Usted no esta autorizado para ver este comprobante");
				this.clean();
			}
			
			
			///aca mensaje de edita o dame un numero nuevo.
		}else {
			cargar_cajas();
			if (frame.get_txt_fecha().getText().compareTo("")!=0){
				boolean aux=this.checkFecha(frame.get_txt_fecha().getText());
				if (aux){
					this.setInitialDate();		
				}
			}else {
				this.setInitialDate();
			}
			
			
			
			frame.get_btn_buscar_pago().setEnabled(false);
			
			frame.get_txt_idtransferencia().setEditable(false);
			frame.get_btn_grabar().setEnabled(true);
			frame.get_txt_fecha().requestFocusInWindow();
		}
	}
	
	
	public void cargarTransferencia(String id){
		frame.get_txt_idtransferencia().setText(id);
		frame.get_txt_idtransferencia().setEditable(false);
		frame.get_btn_buscar_pago().setEnabled(false);
		
		this.cargar_cajas();
		if (data.exist_transferencia(id)){
				load_Transferencia(id);
			
			
		}else {
			System.out.println("Transferencia nueva");
		}
	}
	
	public void load_encabezado(String id){
		Convertidor C=new Convertidor();
		Object[][] results=data.load_encabezado(id);
		if (results!=null){
			if (results.length>0){
				String importe=(String) results[0][0];
				String cuenta=(String) results[0][1];
				String desc=(String) results[0][2];
				String concepto=(String) results[0][3];
				String fecha=(String) results[0][4];
				String idcajas=(String) results[0][5];
				fecha=C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss", fecha);
				
				frame.get_txt_fecha().setText(fecha);
				frame.get_txt_concepto().setText(concepto);
				for (int i=0;i<frame.get_list_caja_origen().getItemCount();i++){
					if (frame.get_list_caja_origen().getItemAt(i).toString().compareTo(idcajas)==0){
						frame.get_list_caja_origen().setSelectedIndex(i);
					}
				}
				double imp=0.0;
				double ant=0.0;
				try {
					imp=new Double(importe);
				}catch(Exception e){
					
				}
				frame.get_txt_total_pago().setText(C.getMoney(imp,2));
			}
		}
		this.load_caja_destino(id);
		
	}
	
	
	public void load_caja_destino(String id){
		Convertidor C=new Convertidor();
		Object[][] results=data.load_caja_destino(id);
		if (results!=null){
			if (results.length>0){
				String idcajas=(String) results[0][5];
				for (int i=0;i<frame.get_list_caja_destino().getItemCount();i++){
					if (frame.get_list_caja_destino().getItemAt(i).toString().compareTo(idcajas)==0){
						frame.get_list_caja_destino().setSelectedIndex(i);
					}
				}
				
			}
		}
		
		
	}
	
	
		
	public _Data getData(){
		return this.data;
	}
	
	/**
	 * 
	 * ========================================================================
	 * OPERACIONES UTILIZADAS PARA EL CONTROL Y MANEJO DE MEDIOS DE PAGO
	 */
	public void _medios_evaluar_banco_cheque(JTextField tx,int row,int col){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_cod_banc(frame.getJTableMedios(), tx, row, col);
	}
	
	public void _medios_evaluar_serie_cheque(JTextField tx,int row,int col){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_serie(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_numero_cheque(JTextField tx,int row,int col){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_numero(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_vencimiento(JTextField tx,int row,int col){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_vencimiento(frame.getJTableMedios(), tx, row, col);
	}


	public void _medios_borrar_renglon(int row){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.delete_renglonMedios(frame.getJTableMedios(), row);
	}
	
	public void _medios_recalcular(){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.recalculateMedios();
	}
	
	private void _medios_crear_tabla(Object[][] results) {
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.create_table_medios(results);
	}
	
	public void _medios_cargar_medios_de_pago(String id){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.load_medios_de_pago(id);
	}
	
	public void _medios_evaluar_medio(JTextField tx,int row,int col){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_medio(tx, row, col);
		
	}
	
	public Double _medios_obtener_restante(){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		double restante=0.0;
		return restante;
	}

	public void _medios_evaluar_importe(JTextField tx,int row,int col){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_row_medios(tx, row, col);
	}

	
	private void load_Transferencia(String id){
		String tc=this.cpte_tc;
		frame.get_txt_idtransferencia().setText(id);
		frame.get_txt_idtransferencia().setEditable(false);
		frame.get_list_caja_origen().setEnabled(false);
		frame.get_list_caja_destino().setEnabled(false);
		frame.get_txt_fecha_desde().setEditable(false);
		frame.get_txt_fecha_hasta().setEditable(false);
		frame.get_txt_concepto().setEditable(false);
		frame.get_btn_buscar_pago().setEnabled(false);
		
		frame.get_txt_fecha_desde().setText("");
		frame.get_txt_fecha_hasta().setText("");
		frame.get_txt_fecha().setEditable(false);
		frame.get_btn_fecha().setEnabled(false);
		
		frame.get_btn_fecha_desde().setEnabled(false);
		frame.get_btn_fecha_hasta().setEnabled(false);
		frame.get_btn_autocompletar().setEnabled(false);
		this._medios_cargar_medios_de_pago(id);
		boolean anulada=false;
		if (data.pago_anulada(id)){
			anulada=true;
			frame.get_txt_estado().setText("ANULADA");
			frame.get_txt_estado().setForeground(Color.red);
			frame.get_btn_anular().setEnabled(false);
		}else {
			frame.get_txt_estado().setText("OK");
			frame.get_txt_estado().setForeground(Color.white);
			frame.get_btn_anular().setEnabled(true);
		}
		
		this.load_encabezado(id);
		this._medios_recalcular();
		boolean pendiente=data.getAutorizacionesPendiente(tc, id);
		String iduser=this.getConstructor().getIduser();
		frame.get_btn_confirmar().setEnabled(false);
		if (!anulada){
		boolean confirma=data.puedeEscribirCaja(iduser, frame.get_list_caja_destino().getSelectedItem().toString());		
		if (pendiente){
			frame.get_btn_confirmar().setEnabled(confirma);
			frame.get_txt_estado().setText("PENDIENTE");
			frame.get_txt_estado().setForeground(Color.orange);
			
		}else{
			frame.get_btn_anular().setEnabled(confirma);
			frame.get_btn_confirmar().setEnabled(false);
			frame.get_txt_estado().setText("CONFIRMADA");
			frame.get_txt_estado().setForeground(Color.green);
			frame.get_btn_anular().setEnabled(true);	
			}
		}
		boolean origen=data.puedeEscribirCaja(iduser, frame.get_list_caja_destino().getSelectedItem().toString());
		if (origen){
			frame.get_btn_anular().setEnabled(true);
		}else{
			frame.get_btn_anular().setEnabled(false);
		}
		frame.getJTableMedios().setEnabled(false);
		frame.get_btn_grabar().setEnabled(false);
		
	}
	
	
public void evaluar_concepto(JTextField tx){
String aux="";
aux=tx.getText();
boolean ok=aux.compareTo("")!=0;
if (!ok){
	ok=this.preguntar("confirmar", "Confirma concepto en blanco?");	
}
if (ok){
	int rows=0;
	try {
		rows=frame.getJTableMedios().getRowCount();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (rows<=0){
		create_tabla();
	}
	frame.getJTableMedios().requestFocusInWindow();
	frame.getJTableMedios().changeSelection(0,0,false,false);
	frame.getJTableMedios().editCellAt(0,0);
	frame.getJTableMedios().transferFocus();
}
	
}

public void _evaluar_codigo_proveedor(JTextField tx){
	String aux=tx.getText();
	if (aux.compareTo("")!=0){
			int n=0;
			try {
				n=new Integer(aux);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (n>0){
				this.load_All(tx);	
			}else {
				this.buscarProveedor(tx);
			}
				
	}else {
		JOptionPane.showMessageDialog(frame, "Ingrese Codigo de Proveedor. utilice F5 para buscar");
	}
}

public void load_All(JTextField tx){
	String aux=tx.getText();
	if (aux.compareTo("")!=0){
		if (Character.isLetter(aux.charAt(0))){
			//goVisualGuessCliente(tx);
		}else {
			loadAll(aux);	
		}
		
	}
	
}

public void loadAll(String idcliente){
	
	Object[][] results=data.getClientInformation(idcliente);
	if (results!=null){
		if (results.length>0){
			
			String desc=results[0][1].toString();
			//frame.get_txt_clientedescripcion().setText(desc);
			//_Pago_cargar_creditos();
			Object[][] empty_medios=new Object[][]{{"","","","","","","","",""}};
			this._medios_crear_tabla(empty_medios);
			this.frame.get_list_caja_origen().requestFocusInWindow();
		}else {
			JOptionPane.showMessageDialog(frame, "El Codigo de Cuenta es Inexistente o No es Imputable. Utilice F5 para Buscar");
			//frame.get_txt_idproveedor().requestFocusInWindow();
		}
	}

}
public void cargar_cajas(){
	this.cargar_caja_origen();
	this.cargar_caja_destino();
}

public void cargar_cheques(){
	
	String caja=frame.get_list_caja_origen().getSelectedItem().toString();
	Object[][] aux=data.getCheques(caja);
	if (aux!=null){
		if (aux.length>0){
		Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		DefaultTableModel model=(DefaultTableModel)frame.getJTableMedios().getModel();
		model.setRowCount(model.getRowCount()+1);
		int row=model.getRowCount()-1;
		for (int i=0;i<aux.length;i++){
			String idbanco=(String)aux[i][0];
			String nombre=(String)aux[i][1];
			String serie=(String)aux[i][2];
			String numero=(String)aux[i][3];
			String importe=(String)aux[i][4];
			String vencimiento=(String)aux[i][5];
			control.addCheque(row, idbanco, nombre, serie, numero, importe, vencimiento);
			row++;
		}
		
		}
	}
}

public void cargar_caja_origen(){
	frame.get_list_caja_origen().removeItemListener(this.getConstructor().getItemListener());
	
	frame.get_list_caja_origen().removeAllItems();
	frame.get_list_caja_origen().removeActionListener(null);
	String iduser=this.getConstructor().getIduser();
	Object[][] results=data.get_cajas_origen(iduser);
	String desc="";
	if (results!=null){
		if (results.length>0){
			desc=data.getDetalleCaja(results[0][0].toString());
			for (int i=0;i<results.length;i++){
				try {
					frame.get_list_caja_origen().addItem(results[i][0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	frame.get_list_caja_origen().addItemListener(this.getConstructor().getItemListener());	
	}
	
	
	frame.get_txt_caja_origen_detalle().setText(desc);
}

private void cargar_caja_destino(){
	frame.get_list_caja_destino().removeItemListener(this.getConstructor().getItemListener());
	
	frame.get_list_caja_destino().removeAllItems();
	frame.get_list_caja_destino().removeActionListener(null);
	String iduser=this.getConstructor().getIduser();
	Object[][] results=data.get_cajas_destino(iduser);
	String desc="";
	if (results!=null){
		if (results.length>0){
	desc=data.getDetalleCaja(results[0][0].toString());
	for (int i=0;i<results.length;i++){
		
		try {
			frame.get_list_caja_destino().addItem(results[i][0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		}
	}
	frame.get_txt_caja_destino_detalle().setText(desc);
	frame.get_list_caja_destino().addItemListener(this.getConstructor().getItemListener());
}

public void buscarProveedor(JTextField proveedor){
	if (vSelector!=null){
		vSelector.dispose();
	}
	vSelector
	=new aplicacion.herramientas.java.visualselector.constructor._Constructor();
	vSelector.setParameter(aplicacion.modelo.interfaces._parametros.connector, data.getConnectionHandler());
	vSelector.build(this.getConstructor());
	vSelector.init();
	aplicacion.herramientas.java.visualselector.logic._Logic logic=
		(aplicacion.herramientas.java.visualselector.logic._Logic)vSelector.getLogic();
	aplicacion.herramientas.java.visualselector.logic.Columna c 
	= new aplicacion.herramientas.java.visualselector.logic.Columna();
	aplicacion.herramientas.java.visualselector.logic.Filtro f 
	= new aplicacion.herramientas.java.visualselector.logic.Filtro();
	c = new aplicacion.herramientas.java.visualselector.logic.Columna();
	c.setNombre("codigo");
	c.setAlias("cuenta");
	c.setColumnField(proveedor);
	c.setWidth(120);
	logic.addColumn(c);
	
	c = new aplicacion.herramientas.java.visualselector.logic.Columna();
	c.setNombre("descripcion");
	c.setAlias("descripcion");
	c.setWidth(240);
	logic.addColumn(c);

	logic.setFromTable("ma_cuentas ");
	logic.setRestriction("titulo = 0 ");
	
	f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
	f.setNombre("descripcion");
	f.setValor(proveedor.getText());
	logic.addFilter(f);
	
	
	
	logic.setTop(100);
	logic.setOrderby("descripcion");
	
	int n=logic._loadoptions();
	if (n==0){
		aviso("no hay cuentas con esa descripcion");
	}

		
	
}
public void BuscarProveedor(JTextField ext) {
	aplicacion.herramientas.java.sortableselector.constructor._Constructor 
	CC=new aplicacion.herramientas.java.sortableselector.constructor._Constructor();
	CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
	CC.build(this.getConstructor());
	
	CC.init();
	aplicacion.herramientas.java.sortableselector.logic._Logic 
	logic=(aplicacion.herramientas.java.sortableselector.logic._Logic)CC.getLogic();

	columna c = new columna();
	Filtro f = new Filtro();
	c = new columna();
	c.setNombre("codigo");
	c.setAlias("codigo");
	c.setColumnField(ext);
	c.setWidth(120);
	c.setMaster(true);
	logic.addColumn(c);

	c = new columna();
	c.setNombre("descripcion");
	c.setAlias("descripcion");
	c.setWidth(250);
	c.setMaster(false);
	logic.addColumn(c);


	logic.addFromTable("ma_cuentas ");
	f = new Filtro();
	f.setNombre("codigo");
	f.setAlias("codigo");
	f.setWidth(120);
	logic.addFilter(f);
	f = new Filtro();
	f.setNombre("descripcion");
	f.setAlias("descripcion");
	f.setWidth(250);
	logic.addFilter(f);
	logic.addOrder("codigo");
	logic.addRestriction("titulo =0");
	logic.init();
}


public void BuscarBanco(int finalrow,JTextField ext){
	final int ux=finalrow;
	final Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)getExtension(_Interface._extension_medios_de_pago);

	aplicacion.herramientas.java.sortableselector.constructor._Constructor 
	CC=new aplicacion.herramientas.java.sortableselector.constructor._Constructor(){
		@Override
		protected void initialize_logic(){
			_logic=new 
			aplicacion.herramientas.java.sortableselector.logic._Logic(){
				@Override
				public void Close(JTable table,int row){
					
					String idbanco=table.getValueAt(row, 0).toString();
					String nombre=table.getValueAt(row, 1).toString();
					control.addBanco(ux,idbanco, nombre);
					
				}
			};
		}
	};
	CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
	CC.build(this.getConstructor());
	
	CC.init();
	aplicacion.herramientas.java.sortableselector.logic._Logic 
	logic=(aplicacion.herramientas.java.sortableselector.logic._Logic)CC.getLogic();


	columna c = new columna();
	Filtro f = new Filtro();
	c = new columna();
	c.setNombre("idbanco");
	c.setAlias("idbanco");
	c.setColumnField(ext);
	c.setWidth(80);
	c.setMaster(true);
	logic.addColumn(c);

	c = new columna();
	c.setNombre("descripcion");
	c.setAlias("descripcion");
	c.setWidth(120);
	c.setMaster(false);
	logic.addColumn(c);



	logic.addFromTable(" v_ta_bancos");
	f = new Filtro();
	f.setNombre("idbanco");
	f.setAlias("idbanco");
	f.setWidth(120);
	logic.addFilter(f);
	
	f = new Filtro();
	f.setNombre("descripcion");
	f.setAlias("descripcion");
	f.setWidth(120);
	logic.addFilter(f);
	logic.addOrder("idbanco");
	logic.init();
}

public void BuscarCheque(int finalrow,JTextField ext) {
	final int ux=finalrow;
	final Transferencia_Medios_de_Pago control=(Transferencia_Medios_de_Pago)getExtension(_Interface._extension_medios_de_pago);
	aplicacion.herramientas.java.sortableselector.constructor._Constructor 
	CC=new aplicacion.herramientas.java.sortableselector.constructor._Constructor()
	{
		@Override
		protected void initialize_logic(){
			_logic=new 
			aplicacion.herramientas.java.sortableselector.logic._Logic(){
				@Override
				public void Close(JTable table,int row){
					System.out.println("LOGIC>double click "+row+" on sortable selector" );
					
					
					int[] select_idx=table.getSelectedRows();
					for (int i=0;i<select_idx.length;i++){
						String idbanco=table.getValueAt(select_idx[i], 0).toString();
						String nombre=table.getValueAt(select_idx[i], 1).toString();
						String serie=table.getValueAt(select_idx[i], 2).toString();
						String numero=table.getValueAt(select_idx[i], 3).toString();
						String importe=table.getValueAt(select_idx[i], 4).toString();
						String vencimiento=table.getValueAt(select_idx[i], 5).toString();
						System.out.println("Tratando de agregar cheque "+i+" en "+ux+i+" num:"+serie+"-"+numero);
						control.addCheque(ux+i,idbanco, nombre, serie, numero, importe, vencimiento);	
					}
					
					super.Close(table, row);
					frame.getJTableMedios().requestFocusInWindow();
					frame.getJTableMedios().changeSelection(ux+select_idx.length-1, 7,false,false);
					frame.getJTableMedios().editCellAt(ux+select_idx.length-1, 7);
					frame.getJTableMedios().transferFocus();
					
				}
			};
		}
	};
	CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
	CC.build(this.getConstructor());
	
	CC.init();
	aplicacion.herramientas.java.sortableselector.logic._Logic 
	logic=(aplicacion.herramientas.java.sortableselector.logic._Logic)CC.getLogic();

	String box=frame.get_list_caja_origen().getSelectedItem().toString().replaceAll(" ", "");
	

	columna c = new columna();
	Filtro f = new Filtro();
	c = new columna();
	c.setNombre("ltrim(a.cht_idbanco)");
	c.setAlias("idbanco");
	c.setColumnField(ext);
	c.setWidth(80);
	c.setMaster(true);
	logic.addColumn(c);

	c = new columna();
	c.setNombre("b.descripcion");
	c.setAlias("descripcion");
	c.setWidth(120);
	c.setMaster(false);
	logic.addColumn(c);

	c = new columna();
	c.setNombre("a.cht_serie");
	c.setAlias("serie");
	c.setWidth(120);
	c.setMaster(false);
	logic.addColumn(c);
	
	c = new columna();
	c.setNombre("a.cht_numero");
	c.setAlias("numero");
	c.setWidth(120);
	c.setMaster(false);
	logic.addColumn(c);
	
	c = new columna();
	c.setNombre("a.cht_importe");
	c.setAlias("importe");
	c.setWidth(120);
	c.setMaster(false);
	logic.addColumn(c);
	
	c = new columna();
	c.setNombre("CONVERT(VARCHAR(10), a.cht_vencimiento  , 105)");
	c.setAlias("vencimiento");
	c.setWidth(120);
	c.setMaster(false);
	logic.addColumn(c);
	
	//
	logic.addFromTable(" b_mv_asientos a left outer join v_ta_bancos b on ltrim(a.cht_idbanco)=ltrim(b.idbanco) ");
	f = new Filtro();
	f.setNombre("a.cht_idbanco");
	f.setAlias("idbanco");
	f.setWidth(120);
	logic.addFilter(f);
	
	f = new Filtro();
	f.setNombre("b.descripcion");
	f.setAlias("descripcion");
	f.setWidth(120);
	logic.addFilter(f);
	f = new Filtro();
	f.setNombre("ltrim(a.cht_serie)");
	f.setAlias("serie");
	f.setWidth(50);
	logic.addFilter(f);
	f = new Filtro();
	f.setNombre("a.cht_numero");
	f.setAlias("numero");
	f.setWidth(50);
	logic.addFilter(f);
	
	f = new Filtro();
	f.setNombre("a.cht_importe");
	f.setAlias("numero");
	f.setWidth(50);
	logic.addFilter(f);
	
	f = new Filtro();
	f.setNombre("a.cht_vencimiento");
	f.setAlias("numero");
	f.setWidth(50);
	logic.addFilter(f);
	
	logic.addRestriction("a.cuenta like '111010002' and a.anulado =0 and a.idcajas='"+frame.get_list_caja_origen().getSelectedItem().toString()+"'");
	logic.addGroup("ltrim(a.cht_idbanco),ltrim(a.cht_serie),a.cht_numero,a.cht_importe,a.cht_vencimiento,b.descripcion having sum(case when a.debe_haber like 'D' then a.importe else -a.importe end)>0 ");
	logic.addOrder("a.cht_vencimiento");
	logic.init();
}

public void BuscarTransferencia() {
	this.BuscarTransferencia(frame.get_txt_idtransferencia());
}

public void BuscarProveedor(){
	//this.BuscarProveedor(frame.get_txt_idproveedor());
}

public void setInitialDate(){
	String aux=data.getSystemDate();
	frame.get_txt_fecha().setText(aux);
}

public void BuscarTransferencia(JTextField ext) {

	aplicacion.herramientas.java.sortableselector.constructor._Constructor 
	CC=new aplicacion.herramientas.java.sortableselector.constructor._Constructor()
	{
		@Override
		protected void initialize_logic(){
			_logic=new 
			aplicacion.herramientas.java.sortableselector.logic._Logic(){
				@Override
				public void Close(JTable table,int row){
					super.Close(table, row);
					frame.get_txt_idtransferencia().requestFocusInWindow();
				}
			};
		}
	};
	CC.build(this.getConstructor());
	CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
	
	CC.init();
	aplicacion.herramientas.java.sortableselector.logic._Logic 
	logic=(aplicacion.herramientas.java.sortableselector.logic._Logic)CC.getLogic();

	columna c = new columna();
	Filtro f = new Filtro();
	c = new columna();
	c.setNombre("CONVERT(VARCHAR(10), a.fecha, 105)");
	c.setAlias("fecha");
	c.setWidth(80);
	c.setMaster(false);
	
	logic.addColumn(c);

	c = new columna();
	c.setNombre("a.tc");
	c.setAlias("TC");
	c.setWidth(50);
	c.setMaster(false);
	logic.addColumn(c);

	c = new columna();
	c.setNombre("a.idcomprobante");
	c.setAlias("idcomprobante");
	c.setColumnField(ext);
	c.setWidth(80);
	c.setMaster(false);
	logic.addColumn(c);
	
	c = new columna();
	c.setNombre("a.importe");
	c.setAlias("importe");
	c.setWidth(60);
	c.setMaster(false);
	logic.addColumn(c);
	
	c = new columna();
	c.setNombre("a.cuenta");
	c.setAlias("cuenta");
	c.setWidth(60);
	c.setMaster(true);
	logic.addColumn(c);
	
	c = new columna();
	c.setNombre("c.descripcion");
	c.setAlias("descripcion");
	c.setWidth(160);
	c.setMaster(false);
	logic.addColumn(c);

	c = new columna();
	c.setNombre("(case when a.anulado = 1 then  'ANULADO' else 'OK' end)");
	c.setAlias("Estado");
	
	c.setWidth(80);
	c.setMaster(false);
	logic.addColumn(c);
	
	logic.addFromTable("b_mv_asientos a left outer join ma_cuentas c on a.cuenta=c.codigo ");
	
	f = new Filtro();
	f.setNombre("a.fecha");
	f.setAlias("fecha");
	f.setWidth(60);
	logic.addFilter(f);
	f = new Filtro();
	f.setNombre("a.idcomprobante");
	f.setAlias("idcomprobante");
	
	f.setWidth(120);
	logic.addFilter(f);
	f = new Filtro();
	f.setNombre("c.codigo");
	f.setAlias("cuenta");
	f.setWidth(70);
	logic.addFilter(f);
	f = new Filtro();
	f.setNombre("c.descripcion");
	f.setAlias("descripcion");
	f.setWidth(150);
	logic.addFilter(f);
	
	logic.addRestriction(" a.tc like 'TR' and ("+this.getCajasPermitidasRestricion()+") ");
	logic.addOrder("a.fecha desc");
	logic.init();
}

public String getCajasPermitidasRestricion(){
	String q="";
	String iduser=this.getConstructor().getIduser();
	Object[][] cajas=data.get_cajas_origen(iduser);
	for (int i=0;i<cajas.length;i++){
		if (q.length()>0){
			q+=" or ";
		}
		q+="a.idcajas like '"+cajas[i][0].toString()+"'";
	}
	return q;
}
public void load_calendar(){
	this.BuscarFecha(frame.get_txt_fecha());
}

public void load_calendar_desde(){
	this.BuscarFecha(frame.get_txt_fecha_desde());
}

public void load_calendar_hasta(){
	this.BuscarFecha(frame.get_txt_fecha_hasta());
}
private aplicacion.herramientas.java.buscadores.Fecha bFecha=null;
public void BuscarFecha(JTextField tx){
	if (bFecha==null){
		bFecha=new aplicacion.herramientas.java.buscadores.Fecha(this.getConstructor());
	}
	
	
	bFecha.Buscar(tx);
}

private boolean checkFecha(String fecha){
	boolean error=false;
	DateFormat formatter;
    try {
    	formatter = new SimpleDateFormat("dd-MM-yyyy");
    	formatter.parse(fecha);
    	
    }catch (Exception e){
    	error=true;
    	JOptionPane.showMessageDialog(frame,
    			e.getMessage(),
    		    "Error en Fecha",
    		    JOptionPane.ERROR_MESSAGE);
    }
    return error;
}
public void evaluate_caja_origen(){
	
	frame.get_list_caja_destino().requestFocusInWindow();
	
}
public void evaluate_caja(JComboBox cb){
	
	String desc=cb.getSelectedItem().toString();
	desc=data.getDetalleCaja(desc);
	if (desc.compareTo("")!=0){
		frame.get_txt_caja_origen_detalle().setText(desc);
		
	}
	
}

public void evaluate_caja_destino_sel(JComboBox destino){
	String desc=destino.getSelectedItem().toString();
		desc=data.getDetalleCaja(desc);
	if (desc.compareTo("")!=0){
		frame.get_txt_caja_destino_detalle().setText(desc);
	}
}
public void evaluate_caja_destino(JComboBox destino){
	
	if (destino.getSelectedIndex()==frame.get_list_caja_origen().getSelectedIndex()){
		error("No puede hacer transferencias en la misma caja");
		frame.get_list_caja_origen().requestFocusInWindow();
	}else {
		frame.get_list_caja_destino().setEnabled(false);
		frame.get_list_caja_origen().setEnabled(false);
		frame.get_btn_fecha().setEnabled(false);
		frame.get_btn_fecha_desde().setEnabled(false);
		frame.get_btn_fecha_hasta().setEnabled(false);
		frame.get_txt_fecha().setEditable(false);
		frame.get_txt_fecha_hasta().setEditable(false);
		frame.get_txt_fecha_desde().setEditable(false);
		
		frame.get_txt_concepto().requestFocusInWindow();	
		
	}
	
	
}



private boolean evaluarFecha(JTextField txt){
	String s=txt.getText();
	DateFormat formatter;
    Date date=null;
    boolean error=false;
    String s1="";
    try {
    	formatter = new SimpleDateFormat("dd-MM-yyyy");
    	date = (Date)formatter.parse(s);
    	s1 = formatter.format(date);
    }catch (Exception e){
    	error=true;
    	JOptionPane.showMessageDialog(frame,
    			e.getMessage(),
    		    "Error en Fecha",
    		    JOptionPane.ERROR_MESSAGE);
    }
    if (!error){
    	txt.setText(s1);
    	
    	
    	
    }else {
    	txt.requestFocusInWindow();
    }
    return !error;
}

public void create_tabla(){
	Object[][] empty_medios=new Object[][]{{"","","","","","","","",""}};
	this._medios_crear_tabla(empty_medios);
	
}

private void insert_medios(Object[][] aux){
	boolean empty=false;
	String tipo="";
	String _imp="";
	int row=0;
	if (aux!=null){
		for (int i=0;i<aux.length;i++){
			try {
				tipo=frame.getJTableMedios().getValueAt(row, 0).toString();
				_imp=frame.getJTableMedios().getValueAt(row, 7).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			empty=(tipo.compareTo("")==0 | _imp.compareTo("")==0);
			if (!empty){
				DefaultTableModel model=(DefaultTableModel)frame.getJTableMedios().getModel();
				model.setRowCount(model.getRowCount()+1);
				row++;
			}
			for (int j=0;j<aux[0].length;j++){
				frame.getJTableMedios().setValueAt(aux[i][j], row, j);	
			}
		}
	}
}
private void cargar_cheques_disponible(){
	
	String idcaja=frame.get_list_caja_origen().getSelectedItem().toString();
	String fecha_desde=frame.get_txt_fecha_desde().getText();
	String fecha_hasta=frame.get_txt_fecha_hasta().getText();
	Object[][] aux=data.get_cheques_disponibles(idcaja, fecha_desde, fecha_hasta);
	this.insert_medios(aux);
}

public void cargar_medios_disponibles(){
	this.cargar_cheques_disponible();
}

public void evaluar_fecha_operacion(JTextField tx){
	if (this.evaluarFecha(tx)){
		this.cargar_fechas();
		frame.get_txt_fecha_desde().requestFocusInWindow();
	}
}

public void evaluar_fecha_desde(JTextField tx){
	if (this.evaluarFecha(tx)){
		frame.get_txt_fecha_hasta().requestFocusInWindow();
	}
}

public void evaluar_fecha_hasta(JTextField tx){
	if (this.evaluarFecha(tx)){
		frame.get_list_caja_origen().requestFocusInWindow();
	}
}

public void cargar_fechas(){
	frame.get_txt_fecha_desde().setText("01-03-2006");
	frame.get_txt_fecha_hasta().setText(frame.get_txt_fecha().getText());
}

public void pagoAProveedor(String idproveedor){
	this.obtener_proximo_cpte();
	this._evaluar_idtransferencia(frame.get_txt_idtransferencia());
	//frame.get_txt_idproveedor().setText(idproveedor);
	//_Pago_evaluar_codigo_proveedor(frame.get_txt_idproveedor());
	loadAll(idproveedor);
}

public void loadTransferencia(String idcomprobante){
	frame.get_txt_idtransferencia().setText(idcomprobante);
	frame.get_txt_idtransferencia().setEditable(false);
	this.load_Transferencia(idcomprobante);
}

public void _grabar_transferencia(){
	String ip=data.getIp();
	String fecha=frame.get_txt_fecha().getText();
	String tc=this.cpte_tc;
	String idcomprobante=frame.get_txt_idtransferencia().getText();
	String iduser=this.getConstructor().getIduser();
	boolean su=!(this.esFechaVieja(fecha));
	if (!su){
		error("Esta intentando grabar una transferencia con fecha vieja");
	}
	
	
	if (su){
		if (!data.getIsSuperUser(iduser)){
			su=!data.existeAutorizacionesPendientes(tc, iduser);
			if (!su){
				error("tiene una transferencia pendiente de confirmacion");
			}
		}	
	}
	
	
	if (!su){
		String password=this.requestPassword("Ingrese Clave de Supervisor");
		iduser=this.getValidacion(password);
		if (iduser.compareTo("")!=0){
			su=true;
			//validacion=iduser;
			
		}else{
			data.registrar_operacion(tc, idcomprobante, "", iduser,ip, "Intento de Grabar transferencia Vieja");
			error("No Esta Autorizado");
		}
	}else{
		su=true;
	}
	
	if (su){
	Transferencia_Asiento control=(Transferencia_Asiento)this.getExtension(_Interface._extension_asiento);
	List<String> instrucciones=control.getInstruccionesDeAsiento();
	if (instrucciones.size()>0){
		data.beginTransaction();
		data.clearBatch();
		for (int i=0;i<instrucciones.size();i++){
			data.addBatch(instrucciones.get(i));
		}
		
		String q=data.getPedirAutorizacion(tc, idcomprobante, iduser, ip);
		data.addBatch(q);
		if (data.getIsSuperUser(iduser)){
			q=data.getConfirmarAutorizacion(tc, idcomprobante, iduser, ip);
			data.addBatch(q);
		}
		boolean error=data.executeBatch();
		if (!error){
			data.commitTransaction();
			aviso("Se grabo Correctamente");
		}else{
			data.rollbackTransaction();
			error("Error Grabando Comprobante");
		}	
	}else{
		error("Error Grabando Comprobante");
	}
	
	}
}


public void GrabarTransferencia(){
	String tc="TR";
	String iduser=this.getConstructor().getIduser();
	if (!this.error_salida()){
		if (!this.checkFecha(frame.get_txt_fecha().getText())){
			if (this.eval_rows()){
				if (!data.existeAutorizacionesPendientes(tc, iduser)){
					_grabar_transferencia();	
				}else{
					error("tiene transferencia pendiente de confirmacion.");
				}
				
				//aviso("Supuetamente graba aqui");
			}else {
				error("El sistema no permite grabar una Transferencia erronea. Verifique");
			}
				
		}else {
			
		}	
	}else{
		error("Revise la Transferencia antes de grabar!");
	}
	
	
	
}
/**
 * Carga todos los medios de pago listos para transferir.
 * Cheques.. cada uno de ellos
 * Efectivo el total
 * descuento el total
 */
public void autocompletar(){
	this.create_tabla();
	this.cargarEfectivo();
	this.cargarDescuentos();
	this.cargar_cheques();
	this._medios_recalcular();
	this.cargarConcepto();
}


public void cargarConcepto(){
	String desde=frame.get_list_caja_origen().getSelectedItem().toString();
	desde=desde.replace(" ", "");
	String hasta=frame.get_list_caja_destino().getSelectedItem().toString();
	hasta=hasta.replace(" ", "");
	String hora=new Convertidor().getDateWithFormat("hh:mm:ss");
	String concpeto="Transferencia ("+desde+"-"+hasta+")  "+hora;
	frame.get_txt_concepto().setText(concpeto);
	
}

public boolean error_salida(){
	boolean error=false;
	int i=0;
	while(i<frame.getJTableMedios().getRowCount() &!error){
		String cuenta="";
		try {
			cuenta = (String)frame.getJTableMedios().getValueAt(i, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cuenta==null){
			cuenta="";
		}
		String importe="";
		try {
			importe = (String)frame.getJTableMedios().getValueAt(i, 7);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cuenta.compareTo("")!=0){
			if (cuenta.compareTo("EF")==0){
				double imp=this.getSaldo("111010001");
				double _imp=new Double(importe.replaceAll(",", ""));
				error=_imp>imp;
				if (error){
					error("Esta Extrayendo mas Efectivo del Disponible en la cuenta "+_imp+" > "+imp);	
				}
				
			}
			if (!error & cuenta.compareTo("DO")==0){
				double imp=this.getSaldo("42101");
				double _imp=new Double(importe.replaceAll(",", ""));
				error=_imp>imp;
				if (error){
					error("Esta Extrayendo mas Descuento del Disponible en la cuenta "+_imp+" > "+imp);	
				}
				
			}
			
			if (!error & cuenta.compareTo("US")==0){
				double imp=this.getSaldo("111010099");
				double _imp=new Double(importe.replaceAll(",", ""));
				error=_imp>imp;
				if (error){
					error("Esta Extrayendo mas Dolares del Disponible en la cuenta "+_imp+" > "+imp);	
				}
				
			}
			if (!error & cuenta.compareTo("CHT")==0){
				double imp=this.getSaldo("111010002");
				double _imp=new Double(importe.replaceAll(",", ""));
				error=_imp>imp;
				if (error){
					error("Esta Extrayendo mas Valores del Disponible en la cuenta "+_imp+" > "+imp);	
				}
				
			}
		}
		i++;
	}
	return error;
}

public double getSaldo(String cuenta){
	String idcaja=frame.get_list_caja_origen().getSelectedItem().toString();
	String fecha_desde=frame.get_txt_fecha_desde().getText();
	String fecha_hasta=frame.get_txt_fecha_hasta().getText();
	Object[][] results=data.get_saldo_cuenta(idcaja, cuenta,fecha_desde, fecha_hasta);
	double imp=0.0;
	
	if (results!=null){
		if (results.length>0){
			String importe=(String) results[0][0];
			try {
				imp=new Double(importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	return imp;
}
public void cargarEfectivo(){
	String idcaja=frame.get_list_caja_origen().getSelectedItem().toString();
	String fecha_desde=frame.get_txt_fecha_desde().getText();
	String fecha_hasta=frame.get_txt_fecha_hasta().getText();
	String efectivo="111010001";
	Object[][] results=data.get_saldo_cuenta(idcaja, efectivo,fecha_desde, fecha_hasta);
	if (results!=null){
		if (results.length>0){
			String importe=(String) results[0][0];
			double imp=new Double(importe);
			if (imp>0){
				frame.getJTableMedios().setValueAt("EF", 0, 0);
				frame.getJTableMedios().setValueAt("Caja Efectivo Pesos", 0, 1);
				frame.getJTableMedios().setValueAt(importe, 0, 7);	
			}
			
		}
	}
}

public void cargarDescuentos(){
	String idcaja=frame.get_list_caja_origen().getSelectedItem().toString();
	String fecha_desde=frame.get_txt_fecha_desde().getText();
	String fecha_hasta=frame.get_txt_fecha_hasta().getText();
	String descuento="42101";
	Object[][] results=data.get_saldo_cuenta(idcaja, descuento,fecha_desde, fecha_hasta);
	if (results!=null){
		if (results.length>0){
			String importe=(String) results[0][0];
			double imp=0.0;
			try {
				imp = new Double(importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (imp>0){
				DefaultTableModel model=(DefaultTableModel)frame.getJTableMedios().getModel();
				int rows=model.getRowCount();
				model.setRowCount(rows+1);
				
				frame.getJTableMedios().setValueAt("DO", rows, 0);
				frame.getJTableMedios().setValueAt("Descuentos Otorgados", rows, 1);
				frame.getJTableMedios().setValueAt(importe, rows, 7);	
			}
			
		}
	}
	
}

public void setCaja(String idcaja){
	for (int i=0;i<frame.get_list_caja_origen().getItemCount();i++){
		if (idcaja.compareTo(frame.get_list_caja_origen().getItemAt(i).toString())==0){
			frame.get_list_caja_origen().setSelectedIndex(i);
			String desc=data.getDetalleCaja(idcaja);
			frame.get_txt_caja_origen_detalle().setText(desc);
			if (i<frame.get_list_caja_destino().getItemCount()-1){
				frame.get_list_caja_destino().setSelectedIndex(i+1);	
			}else{
				frame.get_list_caja_destino().setSelectedIndex(0);
			}
			
		}
	}
}

public void setCajaDestino(String idcaja){
	for (int i=0;i<frame.get_list_caja_destino().getItemCount();i++){
		if (idcaja.compareTo(frame.get_list_caja_destino().getItemAt(i).toString())==0){
			frame.get_list_caja_destino().setSelectedIndex(i);
			String desc=data.getDetalleCaja(idcaja);
			frame.get_txt_caja_destino_detalle().setText(desc);
		}
	}
	
}

}