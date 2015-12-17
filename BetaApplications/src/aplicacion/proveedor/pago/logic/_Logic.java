package aplicacion.proveedor.pago.logic;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import javax.swing.table.*;
import javax.swing.JCheckBox;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import aplicacion.proveedor.pago.gui._Frame;
import aplicacion.proveedor.pago.interfaces._Interface;
import aplicacion.proveedor.pago.logic.extensions.Pago_Medios_de_Pago;
import aplicacion.proveedor.pago.logic.extensions.Pago_Asiento;

//import aplicacion.gestion.caja.manejo.logic._Data;
//import aplicacion.cliente.cobranza.logic._Data;
import aplicacion.gestion.egreso.logic.extensions.Egreso_Medios_de_Pago;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.*;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;
import aplicacion.herramientas.java.*;

public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	
	/*
	 * variables para la cobranza directa de caja. online 
	 */
	private String cpte_fecha="";
	private String cpte_tc="";
	private String cpte_idc="";
	private String cpte_importe="";
	
	
	public _Logic(){
		Pago_Medios_de_Pago control=new Pago_Medios_de_Pago();
		Pago_Asiento asiento=new Pago_Asiento();
		
		this.addExtension(control);
		this.addExtension(asiento);
		System.out.println("Extension=?"+this.getExtension("Pago_Control"));
		System.out.println("Extension=?"+this.getExtension("Pago_Asiento"));
	}
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public void focus(){
		frame.get_txt_idpago().requestFocusInWindow();
	}
	
	/*
	 * Para obtener el proximo numero de Pago disponible
	 */
	public void obtener_proximo_cpte(){
		_Data data=(_Data) _data;
		String cb=data.getProximoPGCorrecto();
		//Pago_frame _frame=(Pago_frame) this._frame;
		frame.get_txt_idpago().setText(cb);
	}
	
	
	
	
	
	public void clean(){
		
		frame.get_txt_idpago().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_anticipo().setText("0.00");
		frame.get_txt_total_cpte().setText("0.00");
		frame.get_txt_total_creditos().setText("0.00");
		frame.get_txt_total_pago().setText("0.00");
		frame.get_txt_proveedor_descripcion().setText("");
		frame.get_txt_fecha().setText("");
		frame.get_txt_leyenda().setText("");
		frame.get_txt_detalle().setText("");
		frame.get_txt_detalle().setEditable(true);
		frame.setJTableCpte(null);
		frame.setJTableMedios(null);
		frame.setJTableOPG(null);
		frame.get_txt_estado().setText("");
		frame.get_txt_estado().setForeground(Color.white);
		frame.get_chk_seleccionar_cpte().setEnabled(true);
		frame.get_txt_idpago().setEditable(true);
		frame.get_btn_buscar_pago().setEnabled(true);
		frame.get_btn_buscar_proveedor().setEnabled(false);
		frame.get_lst_caja().setEnabled(true);
		
		this.getToday();
		this.cargar_cajas();
		frame.get_txt_idpago().requestFocusInWindow();	
	}
	
	public void anular(){
		String id=frame.get_txt_idpago().getText();
		if (id.compareTo("")!=0){
			_Data _data=(_Data) this._data;
			if (_data.anulada(id)){
				aviso( "El Pago "+id+" ya fue anulado");
			}else {
				
				if (_data.exist_pago(id)){
					if (this.data.PagoRelacionado(id)){
						String aux=this.data.getPrimeraRelacion(id);
						aviso( "El Pago "+id+" esta relacionado con "+aux+"!. No puede eliminarse! ");	
					}else {
						if ((confirmar("Elimina Pago "+id+"?",3))) {
				  			boolean error=_data.anular_PG("PG",id);
				  			if (!error){
				  				aviso( "Se anulo Correctamente el Pago");
				  				this.clean();
				  			}else {
				  				aviso( "Error anulando el Pago");
				  			}
				  		}	
					}
						
				}else {
					aviso( "El Pago "+id+" no existe");
				}
				
			}	
		}
		
		
	}
	
	
	public void nuevo(){
		clean();
		frame.get_txt_idpago().setEditable(true);
		obtener_proximo_cpte();
	}
	
	public void cancelar(){
		int operacion = JOptionPane.showConfirmDialog(
			    this._frame,
			    "Cancela Operacion?",
			    "Confirmar",
			    JOptionPane.YES_NO_OPTION);
		if ( operacion == JOptionPane.YES_OPTION) {
			clean();
			frame.get_txt_idpago().setEditable(true);
			
		}
	}
	
	
	public void getToday(){
		_Frame _frame=(_Frame) this._frame;
		_frame.get_txt_fecha().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy")
				);	
	}
	
	public void _pago_evaluar_idpagoold(JTextField tx){
		String idx=tx.getText();
		_Data _data=(_Data) this._data;
		int n=0;
		try {
			n=new Integer(idx);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (n>0){
			if (_data.exist_pago(idx)){
				cargarPago(idx);
			}else{
				if (frame.get_txt_fecha().getText().compareTo("")!=0){
					boolean aux=this.checkFecha(frame.get_txt_fecha().getText());
					if (aux){
						this.setInitialDate();		
					}
				}else {
					this.setInitialDate();
				}
				
				
				
				frame.get_btn_buscar_pago().setEnabled(false);
				frame.get_btn_buscar_proveedor().setEnabled(true);
				frame.get_txt_idpago().setEditable(false);
				frame.get_txt_idproveedor().setEditable(true);
				frame.get_btn_grabar().setEnabled(true);
				frame.get_txt_fecha().requestFocusInWindow();	
			}
		}else{
			
		}
		
	}
	
	
	public void cargarPago(String id){
		frame.get_txt_idpago().setText(id);
		frame.get_txt_idpago().setEditable(false);
		frame.get_btn_buscar_pago().setEnabled(false);
		frame.get_btn_buscar_proveedor().setEnabled(false);
		if (data.exist_pago(id)){
			int n = JOptionPane.showConfirmDialog(
	  			    frame,
	  			    "Modifica Pago "+id+"?",
	  			    "Modificar",
	  			    JOptionPane.YES_NO_OPTION);
	  		if ( n == JOptionPane.YES_OPTION) {
	  				load_Pago(id);
	  		}else {
	  			clean();	
	  		}
			
			
		}else {
			System.out.println("Pago nuevo");
		}
	}
	
	public void load_encabezado(String id){
		Convertidor C=new Convertidor();
		Object[][] results=data.load_encabezado(id);
		if (results!=null){
			if (results.length>0){
				String importe=(String) results[0][0];
				String anticipo=(String) results[0][1];
				String cuenta=(String) results[0][2];
				String desc=(String) results[0][3];
				String fecha=(String) results[0][4];
				fecha=C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss", fecha);
				frame.get_txt_idproveedor().setText(cuenta);
				frame.get_txt_proveedor_descripcion().setText(desc);
				frame.get_txt_fecha().setText(fecha);
				frame.get_chk_seleccionar_cpte().setEnabled(true);
				double imp=0.0;
				double ant=0.0;
				try {
					imp=new Double(importe);
				}catch(Exception e){
					
				}
				try {
					ant=new Double(anticipo);
				}catch(Exception e){
					
				}
				frame.get_txt_total_pago().setText(C.getMoney(imp,2));
				frame.get_txt_anticipo().setText(C.getMoney(ant,2));
			}
		}
		frame.get_txt_idproveedor().setEditable(false);
		
	}
	
	
	
	private void create_table_opg(Object[][] results) {
		//sizesOPG=new int[]{30,60,60,120,70,70};
		//columnsOPG=new String[]{"","Fecha","TC","idComprobante","importe","Estado"};	
		CustomTable table = new CustomTable();
		
		Column col = new Column();
		col = new Column();
		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_opg_chk);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
	    table.addColumn(col);

	    col = new Column();
		col.setName("Fecha");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(Date.class);
		col.setCellRenderer(new DateRenderer());
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("TC");
		col.setWidth(60);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idComprobante");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("importe");
		col.setWidth(70);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("estado");
		col.setWidth(70);
		col.setEditable(false);
		table.addColumn(col);
		
		
		
		//results=this.procesar_cpte(results);
		table.setData(results);
		Font fuente=new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		frame.setJTableOPG(table.getTable());
	}
	
	public void evaluatecheckOPG(JCheckBox jx,int row, int col,boolean transfer){
		this.recalculate_cptes(-1,false);
		this.recalculate_creditos(row,jx.isSelected());
	}
	public void evaluatecheckCpte(JCheckBox jx,int row, int col,boolean transfer){
		this.recalculate_cptes(row,jx.isSelected());
		this.recalculate_creditos(-1,false);
	}
	
	public Double getCreditos(){
		Double imp=0.0;
		for (int i=0;i<frame.getJTableOPG().getRowCount();i++){
			boolean b=false;
			try {
				b=(Boolean) frame.getJTableOPG().getValueAt(i,0);	
			}catch(Exception e){
				
			}
			
			Double impx=0.0;
			String importe="";
			try {
				importe=frame.getJTableOPG().getValueAt(i, 4).toString();
			}catch(Exception e){
				
			}
		
			if (b){
				importe=importe.replace(",", "");
				try {
					impx=new Double(importe);	
				}catch(Exception e){
					
				}
				
			}
			imp=imp+impx;
		}
		return imp;
	}
	
	public void recalculate_creditos(int r,boolean ux){
		Double imp=0.0;
		for (int i=0;i<frame.getJTableOPG().getRowCount();i++){
			boolean b=false;
			try {
				b=(Boolean) frame.getJTableOPG().getValueAt(i,0);	
			}catch(Exception e){
				
			}
			
			Double impx=0.0;
			String importe="";
			try {
				importe=frame.getJTableOPG().getValueAt(i, 4).toString();
			}catch(Exception e){
				
			}
			System.out.println(r+" "+ux+") "+"  "+importe);
			if (i==r){
			b=ux;	
			}
			if (b){
				importe=importe.replace(",", "");
				try {
					impx=new Double(importe);	
				}catch(Exception e){
					
				}
				
			}
			imp=imp+impx;
		}
		String impx=frame.get_txt_total_cpte().getText();
		double impw=new Double(impx.replace(",",""));
		
		frame.get_txt_total_creditos().setText(new Convertidor().getMoney(imp, 2));
		_medios_recalcular();
	}
	
	private void create_table_cpte(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();
		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_cpte_chk);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
	    table.addColumn(col);

		
	    col = new Column();
		col.setName("Fecha");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(Date.class);
		col.setCellRenderer(new DateRenderer());
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("TC");
		col.setWidth(60);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idComprobante");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("importe");
		col.setWidth(70);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("estado");
		col.setWidth(70);
		col.setEditable(false);
		table.addColumn(col);
		
		
		
		//results=this.procesar_cpte(results);
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		JTable _table=table.getTable();
		_table.setName(_Interface._table_cpte);
		frame.setJTableCpte(table.getTable());
	}

	

	public void Seleccionar(boolean b){
		for (int i=0;i<frame.getJTableCpte().getRowCount();i++){
			frame.getJTableCpte().setValueAt(b, i, 0);
		}
		recalculate_cptes(-1,false);
	}
	
	public void SeleccionarCreditos(boolean b){
		for (int i=0;i<frame.getJTableOPG().getRowCount();i++){
			frame.getJTableOPG().setValueAt(b, i, 0);
		}
		this.recalculate_cptes(-1,false);
		this.recalculate_creditos(-1,false);
	}
	
	public void SeleccionarAnticipos(boolean b){
		for (int i=0;i<frame.getJTableOPG().getRowCount();i++){
			frame.getJTableOPG().setValueAt(b, i, 0);
		}
		recalculate_cptes(-1,false);
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
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_cod_banc(frame.getJTableMedios(), tx, row, col);
	}
	
	public void _medios_evaluar_serie_cheque(JTextField tx,int row,int col){
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_serie(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_numero_cheque(JTextField tx,int row,int col){
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_numero(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_vencimiento(JTextField tx,int row,int col){
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_vencimiento(frame.getJTableMedios(), tx, row, col);
	}


	public void _medios_borrar_renglon(int row){
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.delete_renglonMedios(frame.getJTableMedios(), row);
	}
	
	public void _medios_recalcular(){
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.recalculateMedios();
	}
	
	private void _medios_crear_tabla(Object[][] results) {
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.create_table_medios(results);
	}
	
	public void _medios_cargar_medios_de_pago(String id){
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.load_medios_de_pago(id);
	}
	
	public void _medios_evaluar_medio(JTextField tx,int row,int col){
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_medio(tx, row, col);
		
	}
	
	public Double _medios_obtener_restante(){
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		double restante=control.getRestanteMedios();
		return restante;
	}

	public void _medios_evaluar_importe(JTextField tx,int row,int col){
		Pago_Medios_de_Pago control=(Pago_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_row_medios(tx, row, col);
	}

	public void seleccionarCaja(String idcaja){
		for (int i=0;i<frame.get_lst_caja().getItemCount();i++){
			if (frame.get_lst_caja().getItemAt(i).toString().compareTo(idcaja)==0){
				frame.get_lst_caja().setSelectedIndex(i);
			}
		}
	}
	public void load_Pago(String id){
		this.cargar_cajas();
		String idcaja="";
		String detalle="";
		Object[][] results=data.getPago(id);
		if (results!=null){
			if (results.length>0){
				idcaja=(String) results[0][0];
				detalle=(String) results[0][1];
			}
		}
		
		boolean ok=data.autoriza_caja(this.getConstructor().getIduser(), idcaja);
		if (ok){
			this.seleccionarCaja(idcaja);
			frame.get_lst_caja().setEnabled(false);
			frame.get_txt_detalle().setText(detalle);
			frame.get_txt_detalle().setEditable(false);
			frame.get_txt_idpago().setText(id);
			frame.get_txt_idpago().setEditable(false);
			this._medios_cargar_medios_de_pago(id);
			System.out.println("/////////////////CARGAR ANTICIPOS POR DIOS!!");
			this._Pago_cargar_anticipos_utilizados();
			this.load_comprobantes_pagados();
			//this.load_creditos_utilizados();
			if (data.pago_anulada(id)){
				frame.get_txt_estado().setText("ANULADA");
				frame.get_txt_estado().setForeground(Color.red);
				frame.get_btn_anular().setEnabled(false);
			}else {
				frame.get_txt_estado().setText("OK");
				frame.get_txt_estado().setForeground(Color.white);
				frame.get_btn_anular().setEnabled(true);
			}
			
			this.SeleccionarAnticipos(true);
			this.load_encabezado(id);
			this.recalculate_creditos(-1, true);
			this._medios_recalcular();
			
			frame.getJTableCpte().setEnabled(false);
			frame.getJTableMedios().setEnabled(false);
			frame.getJTableOPG().setEnabled(false);
			frame.get_btn_grabar().setEnabled(false);
			frame.get_chk_seleccionar_cpte().setEnabled(false);
			
		}else{
			error("No puede visualizar este pago.");
			this.clean();
		}
			
	}
	
	public void recalculate_cptes(int r,boolean ux){
		Double imp=0.0;
		for (int i=0;i<frame.getJTableCpte().getRowCount();i++){
			boolean b=false;
			try {
				b=(Boolean) frame.getJTableCpte().getValueAt(i,0);
			}catch(Exception e){
				
			}
			Double impx=0.0;
			String importe="";
			try {
				importe=frame.getJTableCpte().getValueAt(i, 4).toString();	
			}catch(Exception e){
				
			}
			
			if (i==r){
			b=ux;	
			}
			if (b){
				importe=importe.replace(",", "");
				try {
					impx=new Double(importe);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			imp=imp+impx;
		}
		frame.get_txt_total_cpte().setText(new Convertidor().getMoney(imp, 2));
		_medios_recalcular();
	}
	
	
	
	
	public void recalculate_cptes(){
		double imp=0.0;
		
		for (int i=0;i<frame.getJTableCpte().getRowCount();i++){
			boolean b=false;
			try {
				b=(Boolean) frame.getJTableCpte().getValueAt(i,0);
			}catch(Exception e){
				
			}
			Double impx=0.0;
			String importe="";
			try {
				importe=frame.getJTableCpte().getValueAt(i, 4).toString();	
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(i+"> frame "+importe);
			importe=importe.replace(",", "");
			try {
				impx=new Double(importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			imp=imp+impx;
		}
		frame.get_txt_total_cpte().setText(new Convertidor().getMoney(imp, 2));
		
	}
	
	public void _Pago_cargar_anticipos(){
		String idcliente=frame.get_txt_idproveedor().getText();
		Object[][] results=data.getAnticiposDisponibles(idcliente);
		if (results!=null){
			if (results.length>0){
				results=this.procesar_cpte(results);
				this.create_table_opg(results);
				frame.getJTableOPG().setEnabled(true);
			}else {
				frame.setJTableOPG(null);
			}
		}
	}
	
	public void _Pago_cargar_anticipos_contables(){
		String idcliente=frame.get_txt_idproveedor().getText();
		Object[][] results=data.getAnticiposContablesDisponibles(idcliente);
		if (results!=null){
			if (results.length>0){
				if (frame.getJTableOPG()!=null){
					if (frame.getJTableOPG().getRowCount()>0){
						this.addAnticipos(results);	
					}else{
						this.create_table_opg(results);
						frame.getJTableOPG().setEnabled(true);	
					}
						
				}else{
					this.create_table_opg(results);
					frame.getJTableOPG().setEnabled(true);
				}
				
				
			}else {
				frame.setJTableOPG(null);
			}
		}
	}
	
	public void addAnticipos(Object[][] results){
		for (int i=0;i<results.length;i++){
			DefaultTableModel model=(DefaultTableModel)frame.getJTableOPG().getModel();
			int r=model.getRowCount();
			model.setRowCount(r+1);
			frame.getJTableOPG().setValueAt(false, r, 0);
			frame.getJTableOPG().setValueAt((String)results[r][0], r, 1);
			frame.getJTableOPG().setValueAt((String)results[r][1], r, 2);
			frame.getJTableOPG().setValueAt((String)results[r][2], r, 3);
			frame.getJTableOPG().setValueAt((String)results[r][3], r, 4);
			frame.getJTableOPG().setValueAt((String)results[r][4], r, 5);
		}
	}
	
	public void _Pago_cargar_anticipos_utilizados(){
		String idPago=frame.get_txt_idpago().getText();
		Object[][] results=data.getAnticiposUtilizados(idPago);
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					String fecha=(String) results[i][0];
					Date _fecha=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha); 
					String tc=(String) results[i][1];
					String idcomprobante=(String) results[i][2];
					String importe=(String) results[i][3];
					this.addCpteCredito(frame.getJTableOPG(),false,_fecha, tc, idcomprobante, importe);
					
				}
			}
		}
		results=data.getEgresosUtilizados(idPago);
		for (int i=0;i<results.length;i++){
			String fecha=(String) results[i][0];
			Date _fecha=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha);
			String tc=(String) results[i][1];
			String idcomprobante=(String) results[i][2];
			String importe=((String) results[i][3]).replace(",", "");
			this.addCpteCredito(frame.getJTableOPG(),true,_fecha, tc, idcomprobante, importe);
			
		}
	}
	
	private boolean tableEmpty(JTable table){
		boolean empty=true;
		if (table!=null){
			empty=!(table.getRowCount()>0);
		}
		return empty;
	}
	
	
	
	public void addCpte(JTable table,boolean selected,Date fecha,String tc,String idcomprobante,String importe){
		if (this.tableEmpty(table)){
			Object[][] aux=new Object[][]{{selected,fecha,tc,idcomprobante,importe,""}};
			this.create_table_cpte(aux);
		}else {
			
			
			DefaultTableModel model=(DefaultTableModel) table.getModel();
			int r=model.getRowCount();
			model.setRowCount(r+1);
			
			table.setValueAt(selected, r,0);
			table.setValueAt(fecha,r,1);
			table.setValueAt(tc,r,2);
			table.setValueAt(idcomprobante,r,3);
			table.setValueAt(importe,r,4);
			table.setValueAt("",r,5);
		}
	}
	
	public void addCpteCredito(JTable table,boolean selected,Date fecha,String tc,String idcomprobante,String importe){
		if (this.tableEmpty(table)){
			Object[][] aux=new Object[][]{{selected,fecha,tc,idcomprobante,importe,""}};
			this.create_table_opg(aux);
		}else {
			
			
			DefaultTableModel model=(DefaultTableModel) table.getModel();
			int r=model.getRowCount();
			model.setRowCount(r+1);
			
			table.setValueAt(selected, r,0);
			table.setValueAt(fecha,r,1);
			table.setValueAt(tc,r,2);
			table.setValueAt(idcomprobante,r,3);
			table.setValueAt(importe,r,4);
			table.setValueAt("",r,5);
		}
	}
	
	public void cargar_deudas(String idcuenta,String desde,String hasta){
		Object[][] results=null;
		results=data.getDeudas(idcuenta,desde,hasta);
		for (int i=0;i<results.length;i++){
			String fecha=(String) results[i][0];
			Date _fecha=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha); 
			String tc=(String) results[i][1];
			String idcomprobante=(String) results[i][2];
			String importe=(String) results[i][3];
			this.addCpte(frame.getJTableCpte(),false,_fecha, tc, idcomprobante, importe);
		}
		results=data.getFCNPendientes(idcuenta,desde,hasta);
		for (int i=0;i<results.length;i++){
			String fecha=(String) results[i][0];
			Date _fecha=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha); 
			String tc=(String) results[i][1];
			String idcomprobante=(String) results[i][2];
			String importe=(String) results[i][3];
			this.addCpte(frame.getJTableCpte(),false,_fecha, tc, idcomprobante, importe);
		}
		results=data.getIGRPendientes(idcuenta,desde,hasta);
		for (int i=0;i<results.length;i++){
			String fecha=(String) results[i][0];
			Date _fecha=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha); 
			String tc=(String) results[i][1];
			String idcomprobante=(String) results[i][2];
			String importe=(String) results[i][3];
			this.addCpte(frame.getJTableCpte(),false,_fecha, tc, idcomprobante, importe);
		}
		
	}
	
	public void cargar_datos(){
		String idcuenta=frame.get_txt_idproveedor().getText();
		String _deuda_desde=frame.get_txt_deuda_desde().getText();
		String _deuda_hasta=frame.get_txt_deuda_hasta().getText();
		String _credito_desde=frame.get_txt_credito_desde().getText();
		String _credito_hasta=frame.get_txt_credito_hasta().getText();
		String deuda_desde="01-01-1900";
		String deuda_hasta="01-01-2900";
		String credito_desde="01-01-1900";
		String credito_hasta="01-01-2900";
		if (_deuda_desde.compareTo("")!=0){
			deuda_desde=_deuda_desde;
		}
		if (_deuda_hasta.compareTo("")!=0){
			deuda_hasta=_deuda_hasta;
		}
		if (_credito_desde.compareTo("")!=0){
			credito_desde=_credito_desde;
		}
		if (_credito_hasta.compareTo("")!=0){
			credito_hasta=_credito_hasta;
		}
		frame.setJTableCpte(null);
		frame.setJTableMedios(null);
		frame.setJTableOPG(null);
		this.cargar_deudas(idcuenta,deuda_desde,deuda_hasta);
		//this._Pago_cargar_anticipos();
		this.cargar_creditos(idcuenta,credito_desde,credito_hasta);
		
	}

	public void cargar_creditos(String idcuenta,String desde,String hasta){

		
		Object[][] results=null;
		results=data.getAFavor(idcuenta,desde,hasta);
		for (int i=0;i<results.length;i++){
			String fecha=(String) results[i][0];
			Date _fecha=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha);
			String tc=(String) results[i][1];
			String idcomprobante=(String) results[i][2];
			String importe=((String) results[i][3]).replace(",", "");
			boolean conciliado=data.getConciliado(tc, idcomprobante);
			double imp=0.0;
			try {
				imp=new Double(importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Creditos Pendientes "+idcuenta+" "+tc+" "+importe+"?");
			if (!conciliado & imp>0){
				System.out.println("Si agrega> Creditos Pendientes "+idcuenta+" "+tc+" "+importe+"?");
				
				this.addCpteCredito(frame.getJTableOPG(),false,_fecha, tc, idcomprobante, importe);
			}
		}
		/*
		results=data.getNCNPendientes(idcuenta,desde,hasta);
		for (int i=0;i<results.length;i++){
			String fecha=(String) results[i][0];
			Date _fecha=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha);
			String tc=(String) results[i][1];
			String idcomprobante=(String) results[i][2];
			String importe=((String) results[i][3]).replace(",", "");
			double imp=0.0;
			try {
				imp=new Double(importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean conciliado=data.getConciliado(tc, idcomprobante);
			if (!conciliado & imp>0){
				this.addCpteCredito(frame.getJTableOPG(),false,_fecha, tc, idcomprobante, importe);
			}
		}*/
		results=data.getAnticiposPendientes(idcuenta,desde,hasta);
		for (int i=0;i<results.length;i++){
			String fecha=(String) results[i][0];
			Date _fecha=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha);
			String tc=(String) results[i][1];
			String idcomprobante=(String) results[i][2];
			boolean conciliado=data.getConciliado(tc, idcomprobante);
			String importe=((String) results[i][3]).replace(",", "");
			double imp=0.0;
			try {
				imp=new Double(importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Creditos Pendientes "+idcuenta+" "+tc+" "+importe+"?");
			if (!conciliado & imp>0){
				System.out.println("Si agrega> Creditos Pendientes "+idcuenta+" "+tc+" "+importe+"?");
				
				this.addCpteCredito(frame.getJTableOPG(),false,_fecha, tc, idcomprobante, importe);
			}
			
		}
	}
	
	public void _Pago_cargar_saldos(){
		String idcliente=frame.get_txt_idproveedor().getText();
		Object[][] results=data.getSaldos(idcliente);
		if (results!=null){
			if (results.length>0){
				this.create_table_cpte(results);
				for (int i=0;i<results.length;i++){
					frame.get_txt_leyenda().setText("Debe especificar los comprobantes a cancelar");
					frame.get_txt_leyenda().setForeground(Color.red);
					String fecha=(String) results[i][0];
					fecha=new Convertidor().ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss", fecha);
					String tc=(String) results[i][1];
					String idcomp=(String) results[i][2];
					String importe=(String) results[i][3];
					String estado=(String) results[i][4];
					frame.getJTableCpte().setValueAt(false, i, 0);
					frame.getJTableCpte().setValueAt(fecha, i, 1);
					frame.getJTableCpte().setValueAt(tc, i, 2);
					frame.getJTableCpte().setValueAt(idcomp, i, 3);
					frame.getJTableCpte().setValueAt(importe, i, 4);
					frame.getJTableCpte().setValueAt(estado, i, 5);
				}
				frame.getJTableCpte().setEnabled(true);
				//frame.getJScrollPaneCpte().setViewportView(frame.getJTableCpte());
			}else {
				frame.get_txt_leyenda().setText("No hay Comprobantes Pendientes");
				frame.get_txt_leyenda().setForeground(Color.red);
				frame.setJTableCpte(null);
			}
		}
		
	}
	
	public void fillSaldosOnline(){
		int row=0;
		if (cpte_fecha.compareTo("")!=0){
			if (frame.getJTableCpte()!=null){
				if (frame.getJTableCpte().getRowCount()>0){
					boolean empty=false;
					try {
						empty=frame.getJTableCpte().getValueAt(frame.getJTableCpte().getRowCount()-1, 0)==null;
					}catch(Exception e){
						
					}
					if (!empty){
						DefaultTableModel defaultTableModelCpte =(DefaultTableModel) frame.getJTableCpte().getModel();
						defaultTableModelCpte.setRowCount(defaultTableModelCpte.getRowCount()+1);
					}
						row=frame.getJTableCpte().getRowCount()-1;
				}else {
					Object[][] temp=new Object[][]{{true,cpte_fecha,cpte_tc,cpte_idc,cpte_importe,"Pendiente"}};
					this.create_table_cpte(temp);
				}
			}else {
				Object[][] temp=new Object[][]{{true,cpte_fecha,cpte_tc,cpte_idc,cpte_importe,"Pendiente"}};
				this.create_table_cpte(temp);
			}
			this.recalculate_cptes(row, true);
			
		}
		
		
	}
	public void load_creditos_utilizados(){
		String id=this.frame.get_txt_idpago().getText();
		
		Object[][] results=data.load_creditos_utilizados(id);
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					String fecha=(String) results[i][0];
					String tc=(String) results[i][1];
					String idcomprobante=(String) results[i][2];
					String importe=(String) results[i][3];
					Date _fecha=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha);
					this.addCpteCredito(frame.getJTableOPG(),true,_fecha, tc, idcomprobante, importe);	
				}
			}
		}
		
		recalculate_cptes();
	}
	
	public void load_comprobantes_pagados(){
		String id=this.frame.get_txt_idpago().getText();
		
		Object[][] results=data.load_comprobantes_pagados(id);
		if (results!=null){
			if (results.length>0){
				
				for (int i=0;i<results.length;i++){
					String fecha=(String) results[i][0];
					Date _fecha=new Convertidor().getDateWithFormat2("dd-MM-yyyy", fecha);
					String tc=(String) results[i][1];
					String idcomprobante=(String) results[i][2];
					String importe=(String) results[i][3];
					this.addCpte(frame.getJTableCpte(),true,_fecha, tc, idcomprobante, importe);	
				}
			}
		}
		
		recalculate_cptes();
	}
 	

	public Object[][] procesar_cpte(Object[][] results){
		Object[][] temp=null;
		Convertidor C=new Convertidor();
		temp=new Object[results.length][6];
		for (int i=0;i<results.length;i++){
			String fecha=(String) results[i][0];
			fecha=C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss", fecha);
			String tc=(String) results[i][1];
			String idc=(String) results[i][2];
			String imp=(String) results[i][3];
		String est="PAGADO";
		temp[i][0]=new Boolean(false);
		temp[i][1]=fecha;
		temp[i][2]=tc;
		temp[i][3]=idc;
		temp[i][4]=imp;
		temp[i][5]=est;
	}
	return temp;
	
}



private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
public void initialize_proveedor(){
	proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
		public void cargar(String codigo){
			loadAll(codigo);
		}
	};
	proveedor.setConstructor(this.getConstructor());
}
public void BuscarProveedor(JTextField tx){
	proveedor.Buscar(tx);
}
public void buscarProveedor(JTextField tx){
	proveedor.buscar(tx);
}
public void BuscarProveedor(){
	proveedor.Buscar(frame.get_txt_idproveedor());
}
public void evaluarProveedor(JTextField tx){
	proveedor.evaluate(tx);
}






public void loadAll(String idcliente){
	
	Object[][] results=data.getProveedorInformation(idcliente);
	if (results!=null){
		if (results.length>0){
			frame.get_txt_idproveedor().setEditable(false);
			frame.get_btn_buscar_proveedor().setEnabled(false);
			String desc=results[0][1].toString();
			frame.get_txt_proveedor_descripcion().setText(desc);
			this.cargar_datos();
			Object[][] empty_medios=new Object[][]{{"","","","","","","","",""}};
			this._medios_crear_tabla(empty_medios);
		}else {
			JOptionPane.showMessageDialog(frame, "El Codigo de Proveedor es Inexistente. Utilice F5 para Buscar");
			frame.get_txt_idproveedor().requestFocusInWindow();
		}
	}
}

public void BuscarCheque(int finalrow,JTextField ext) {
	final int ux=finalrow;
	final Pago_Medios_de_Pago control=(Pago_Medios_de_Pago) this.getExtension(_Interface._extension_medios_de_pago);
	
	aplicacion.herramientas.java.sortableselector.constructor._Constructor 
	CC=new aplicacion.herramientas.java.sortableselector.constructor._Constructor(){
		@Override
	protected void initialize_logic(){
			_logic=new 
			aplicacion.herramientas.java.sortableselector.logic._Logic(){
				
				@Override
				public void Close(JTable table,int row){
					
					if (control==null){
						error("Error Buscando Medios de Pago");
						super.Close(table, row);
					}else{
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
	c.setNombre("ltrim(a.cht_serie)");
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
	
	c = new columna();
	c.setNombre("max(case when a.debe_haber like 'd' then CONVERT(varchar(10), a.fecha, 105) else null end)");
	c.setAlias("fecha entrada");
	c.setWidth(160);
	c.setMaster(false);
	logic.addColumn(c);
	
	c = new columna();
	c.setNombre("max(case when a.debe_haber like 'h' then CONVERT(varchar(10), a.fecha, 105) else null end)");
	c.setAlias("fecha salida");
	c.setWidth(160);
	c.setMaster(false);
	logic.addColumn(c);
	
	//
	logic.addFromTable(" b_mv_asientos a left outer join v_ta_bancos b on ltrim(a.cht_idbanco)=ltrim(b.idbanco) ");
	f = new Filtro();
	f.setNombre("ltrim(a.cht_idbanco)");
	f.setAlias("idbanco");
	f.setWidth(120);
	logic.addFilter(f);
	
	f = new Filtro();
	f.setNombre("b.descripcion");
	f.setAlias("descripcion");
	f.setWidth(120);
	logic.addFilter(f);
	f = new Filtro();
	f.setNombre("a.cht_serie");
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
	f.setAlias("importe");
	f.setWidth(50);
	logic.addFilter(f);
	
	f = new Filtro();
	f.setNombre("a.cht_vencimiento");
	f.setAlias("vencimiento");
	f.setWidth(50);
	logic.addFilter(f);
	logic.setTitle("Buscador de Cheques");
	logic.addRestriction("a.cht_importe is not null and a.anulado =0 and a.idcajas='"+this.getCaja()+"'");
	logic.addGroup("ltrim(a.cht_idbanco),ltrim(a.cht_serie),a.cht_numero,a.cht_importe,a.cht_vencimiento,b.descripcion having sum(case when a.debe_haber like 'd' then 1 else -1 end)>0 ");
	logic.addOrder("a.cht_vencimiento");
	logic.init();
	
}

public String getCaja(){
	String idcaja=frame.get_lst_caja().getSelectedItem().toString();
	return idcaja;
}



public void setInitialDate(){
	String aux=data.getSystemDate();
	frame.get_txt_fecha().setText(aux);
}




public void load_calendar(){
	this.BuscarFecha(frame.get_txt_fecha());
}

private aplicacion.herramientas.java.buscadores.Fecha bFecha=null;
public void BuscarFecha(JTextField tx){
	if (bFecha==null){
		bFecha=new aplicacion.herramientas.java.buscadores.Fecha(this.getConstructor());
	}

	
	bFecha.Buscar(tx);
}

public void buscar_deuda_desde(){
	BuscarFecha(frame.get_txt_deuda_desde());
}

public void buscar_deuda_hasta(){
	BuscarFecha(frame.get_txt_deuda_hasta());
}

public void buscar_credito_desde(){
	BuscarFecha(frame.get_txt_credito_desde());
}

public void buscar_credito_hasta(){
	BuscarFecha(frame.get_txt_credito_hasta());
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

public void evaluarFecha(JTextField txt){
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
    	frame.get_txt_idproveedor().requestFocusInWindow();
    	
    }else {
    	txt.requestFocusInWindow();
    }
}


public void pagoAProveedor(String idproveedor){
	this.obtener_proximo_cpte();
	this._pago_evaluar_idpago(frame.get_txt_idpago());
	frame.get_txt_idproveedor().setText(idproveedor);
	this.evaluarProveedor(frame.get_txt_idproveedor());
	//loadAll(idproveedor);
}

public void GrabarPago(){
	Pago_Asiento control=(Pago_Asiento)this.getExtension(_Interface._extension_asiento);
	if (!this.checkFecha(frame.get_txt_fecha().getText())){
		control.setCaja(this.getCaja());
		control.setDetalle(frame.get_txt_detalle().getText());
		control.GenerarAsientodePago();	
	}else {
		
	}
	
	
}

private aplicacion.herramientas.java.evaluadores.Pago Pago=null;
public void initialize_Pago(){
	Pago=new aplicacion.herramientas.java.evaluadores.Pago(){
		public void cargar(String codigo){
			Object[][] results=this.getInfo(codigo);
			load_Pago(codigo);
		}
	};
	Pago.setConstructor(this.getConstructor());
}
public void BuscarPago(JTextField tx){
	Pago.Buscar(tx);
}
public void BuscarPago(){
	Pago.Buscar(frame.get_txt_idpago());
}
public void buscarPago(JTextField tx){
	Pago.buscar(tx);
}

public void evaluarPago(JTextField tx){
	Pago.evaluate(tx);
}

public void cargar_proveedor(){
	this.evaluarProveedor(frame.get_txt_idproveedor());
}

public void cargarProveedor(String idproveedor){
	Object[][] results=data.getProveedorInformation(idproveedor);
	if (results!=null){
		if (results.length>0){
			frame.get_txt_idproveedor().setText(idproveedor);
			frame.get_txt_idproveedor().setEditable(false);
			String desc=results[0][1].toString();
			frame.get_txt_proveedor_descripcion().setText(desc);
			this.evaluarProveedor(frame.get_txt_idproveedor());
		}
	}
}

public boolean existe(String idpago){
	_Data _data=(_Data) this._data;
	return _data.exist_pago(idpago);
}



private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;

public void imprimir(){
	String tc="PG";
	String idcomprobante=frame.get_txt_idpago().getText();
	if (preguntar("Confirmar","Imprime Copia de Comprobante "+tc+"-"+idcomprobante)){
		reporte();
	}
}



public void reporte(){
	if (reporte!=null){
		 reporte.dispose();
	}
	String tc="PG";
	String idcomprobante=frame.get_txt_idpago().getText();
	String cuenta=frame.get_txt_idproveedor().getText();
	reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
	HashMap param=new HashMap();
	param.put("tc",tc);
	param.put("idcomprobante",idcomprobante);
	param.put("cuenta",cuenta);
	reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
	reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "pago.jasper");
	reporte.build(this.getConstructor());
	reporte.init();	
}

 	public void cargar_cajas() {
		frame.get_lst_caja().removeAllItems();
		frame.get_lst_caja().removeItemListener(this.getConstructor().getItemListener());
		_Data data = (_Data) _data;
		String iduser=this.getConstructor().getIduser();
		Object[][] results = data.get_cajas(iduser);
		for (int i = 0; i < results.length; i++) {
			try {
				frame.get_lst_caja().addItem(results[i][0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		frame.get_lst_caja().addItemListener(this.getConstructor().getItemListener());
	}
 
 	public void evaluate_caja(JComboBox cb) {
		String desc = cb.getSelectedItem().toString();
		desc = data.getDetalleCaja(desc);
		if (desc.compareTo("") != 0) {
			frame.get_txt_caja_detalle().setText(desc);
			frame.get_txt_idproveedor().requestFocusInWindow();
			//this.goCargar();
		}

	}
 	
 
	public void _pago_evaluar_idpago(JTextField tx) {
		String idx = tx.getText();
		_Data _data = (_Data) this._data;
		if (idx.compareTo("")==0){
			this.evaluarPago(tx);
		}else{
			if (_data.exist_pago(idx)) {
				this.evaluarPago(tx);	
				// /aca mensaje de edita o dame un numero nuevo.
			} else {
				

				String fecha = frame.get_txt_fecha().getText();
				System.out.println("CHECK DATE >>>>" + fecha + "<");
				this.setInitialDate();

				frame.get_txt_idpago().setEditable(false);
				frame.get_txt_idproveedor().setEditable(true);
				frame.get_btn_grabar().setEnabled(true);
				frame.get_lst_caja().requestFocusInWindow();
			}
		}
		
	}

 	
}