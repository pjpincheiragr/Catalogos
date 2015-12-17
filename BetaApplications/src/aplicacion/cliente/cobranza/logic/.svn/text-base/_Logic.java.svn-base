package aplicacion.cliente.cobranza.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import stock1.Convertidor;

import java.awt.*;

import aplicacion.cliente.cobranza.gui._Frame;
import aplicacion.cliente.cobranza.interfaces._Interface;
import aplicacion.cliente.cobranza.logic.extensions._Medios_de_Pago;
import aplicacion.cliente.cobranza.logic.extensions._Asiento;
import aplicacion.gestion.ingreso.logic.extensions.Ingreso_Medios_de_Pago;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.events.*;
import aplicacion.modelo.interfaces.*;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.sortableselector.*;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.calendario.*;
import aplicacion.herramientas.java.comprobantes.fvn;
import aplicacion.herramientas.java.*;

//esto debe eliminarse! es codigo viejo

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;

	/*
	 * ====================================================== variables para la
	 * cobranza directa de caja. online no me gusta mucho esto...
	 * =======================================================
	 */
	private String cpte_fecha = "";
	private String cpte_tc = "";
	private String cpte_idc = "";
	private String cpte_importe = "";
	// =====================================
	// esto debe eliminarse codigo viejo
	private aplicacion.ventas.facturador.constructor._Constructor ventas = null;
	

	// set fvn. metodo para pasarle el facturador a esta aplicacion de cobranza
	public void setVentas(
			aplicacion.ventas.facturador.constructor._Constructor ventas) {
		this.ventas = ventas;

	}

	public void evaluate_cobranza() {
		this._cobranza_evaluar_idcobranza(frame.get_txt_idcobranza());
	}

	public aplicacion.ventas.facturador.constructor._Constructor getVentas() {
		return this.ventas;
	}

	// =====================================

	public _Logic() {
		_Medios_de_Pago control = new _Medios_de_Pago();
		_Asiento asiento = new _Asiento();
		this.addExtension(control);
		this.addExtension(asiento);
		System.out.println("Extension=?"
				+ this.getExtension(_Interface._extension_medios_de_pago));
		System.out.println("Extension=?"
				+ this.getExtension(_Interface._extension_asiento));
	}

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	/*
	 * Para obtener el proximo numero de cobranza disponible
	 */
	public void obtener_proximo_cpte() {
		_Data data = (_Data) _data;
		String cb = data.getProximoCBCTCorrecto();
		// Cobranza_frame _frame=(Cobranza_frame) this._frame;
		frame.get_txt_idcobranza().setText(cb);
	}

	public void cobranzaCliente(String idcliente) {
		this.obtener_proximo_cpte();
		this._cobranza_evaluar_idcobranza(frame.get_txt_idcobranza());
		frame.get_txt_idcliente().setText(idcliente);
		this.evaluarCliente(frame.get_txt_idcliente());
	}

	/*
	 * metodo para limpiar el frame
	 */
	public void clean() {
		frame.get_txt_idcliente().setText("");
		frame.get_txt_anticipo().setText("0.00");
		frame.get_txt_total_cpte().setText("0.00");
		frame.get_txt_total_creditos().setText("0.00");
		frame.get_txt_total_pago().setText("0.00");
		frame.get_txt_clientedescripcion().setText("");
		frame.get_txt_fecha().setText("");
		frame.get_txt_leyenda().setText("");
		frame.setJTableCpte(null);
		frame.setJTableMedios(null);
		frame.setJTableOPG(null);
		frame.get_lst_modo().setEnabled(true);
		frame.get_lst_modo().setSelectedIndex(0);
		frame.get_txt_estado().setText("");
		frame.get_txt_estado().setForeground(Color.white);
		frame.get_chk_seleccionar_cpte().setEnabled(true);
		frame.get_btn_anular().setEnabled(false);
		frame.get_btn_imprimir().setEnabled(false);
		frame.get_txt_idcobranza().setEditable(true);
		frame.get_btn_buscar().setEnabled(true);
		frame.get_btn_buscar_cliente().setEnabled(true);
		frame.get_btn_fecha().setEnabled(true);
		frame.get_txt_fecha().setEnabled(true);
		frame.get_btn_cargar_cliente().setEnabled(true);
		frame.get_btn_imprimir_cptes().setEnabled(true);
		frame.get_txt_credito_desde().setEnabled(true);
		frame.get_txt_credito_hasta().setEnabled(true);
		frame.get_txt_deuda_desde().setEnabled(true);
		frame.get_txt_deuda_hasta().setEnabled(true);
		frame.get_btn_buscar_credito_desde().setEnabled(true);
		frame.get_btn_buscar_credito_hasta().setEnabled(true);
		frame.get_btn_buscar_deuda_desde().setEnabled(true);
		frame.get_btn_buscar_deuda_hasta().setEnabled(true);
		frame.get_chk_seleccionar_cpte().setEnabled(true);
		frame.get_chk_seleccionar_creditos().setEnabled(true);
		frame.get_txt_idcobranza().requestFocusInWindow();
	}

	public void _anular_cobranza(String id) {
		_Data _data = (_Data) this._data;
		if (this.confirmar("Confirme eliminacion de cobranza ", 2)) {
			boolean error = _data.anular_CBCT("CBCT", id);
			if (!error) {
				aviso("Se anulo Correctamente la Cobranza");
				this.clean();
			} else {
				error("Error anulando la cobranza");

			}

		}

	}

	/*
	 * Metodo para anular una cobranza existente
	 */

	public void anular() {
		_Data _data = (_Data) this._data;
		String id = frame.get_txt_idcobranza().getText();
		if (id.compareTo("") != 0) {

			if (_data.exist_cobranza(id)) {
				if (_data.anulada(id)) {
					error("La cobranza " + id + " ya fue anulada");
				} else {
					String fecha = frame.get_txt_fecha().getText();
					String tc = "CBCT";
					String idcomprobante = frame.get_txt_idcobranza().getText();
					String cuenta = frame.get_txt_idcliente().getText();
					String ip = data.getIp();
					boolean su = !(this.esFechaVieja(fecha));
					if (!su) {
						aviso("Advertencia: Eliminar operaciones con fechas anteriores puede alterar los saldos actuales de caja");
					}
					String iduser = this.validar_usuario();
					su = iduser.compareTo("") != 0;
					if (su) {
							this._anular_cobranza(id);
							data.registrar_operacion(tc, idcomprobante, cuenta,
									iduser, ip, "anulacion de cobranza vieja");
						} else {
							
						
							data
									.registrar_operacion(tc, idcomprobante,
											cuenta, iduser, ip,
											"intento de anulacion de cobranza vieja. no fue autorizado");
							error("Operacion cancelada");
						}

					

				}
			}
		} else {
			error("La cobranza " + id + " no existe");
		}

	}

	/*
	 * metodo para cancelar y limpiar el frame
	 */
	public void cancelar() {
		int operacion = JOptionPane.showConfirmDialog(this._frame,
				"Cancela Operacion?", "Confirmar", JOptionPane.YES_NO_OPTION);
		if (operacion == JOptionPane.YES_OPTION) {
			clean();
			frame.get_txt_idcobranza().setEditable(true);
			obtener_proximo_cpte();
		}
	}

	/*
	 * metodo para validar el id de cobranza si no existe lo toma como nuevo si
	 * existe carga la aplicacion con los datos de la cobranza para visualizarlo
	 */
	public boolean existe(String idcobranza) {
		_Data _data = (_Data) this._data;
		return _data.exist_cobranza(idcobranza);
	}

	public boolean isCredito(){
		return frame.get_lst_modo().getSelectedIndex()==0;
	}
	public void _cobranza_evaluar_idcobranza(JTextField tx) {
		String idx = tx.getText();
		_Data _data = (_Data) this._data;
		if (idx.compareTo("")==0){
			this.evaluarCobranza(tx);
		}else{
			if (_data.exist_cobranza(idx)) {
				this.evaluarCobranza(tx);	
				// /aca mensaje de edita o dame un numero nuevo.
			} else {
				
				if (isCredito()) {

					frame.get_txt_estado().setForeground(Color.green);
					frame.get_txt_estado().setText("COBRANZA");
				} else {
					frame.get_txt_estado().setForeground(Color.RED);
					frame.get_txt_estado().setText("DEVOLUCION");
				}
				String fecha = frame.get_txt_fecha().getText();
				System.out.println("CHECK DATE >>>>" + fecha + "<");
				this.setInitialDate();

				frame.get_txt_idcobranza().setEditable(false);
				frame.get_txt_idcliente().setEditable(true);
				frame.get_btn_grabar().setEnabled(true);
				frame.get_txt_idcliente().requestFocusInWindow();
			}
		}
		
	}

	/*
	 * metodo para cargar el frame con la cobranza id
	 */
	public void loadCobranza(String id) {
		frame.get_txt_idcobranza().setText(id);
		frame.get_txt_idcobranza().setEditable(false);
		if (data.exist_cobranza(id)) {
			int n = JOptionPane.showConfirmDialog(frame, "Modifica Cobranza "
					+ id + "?", "Modificar", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				load_Cobranza(id);
			} else {
				clean();
			}

		} else {

		}
	}

	public void setInitialDate() {
		String aux = data.getSystemDate();
		frame.get_txt_fecha().setText(aux);
	}

	public void load_encabezado(String id) {
		stock1.Convertidor C = new stock1.Convertidor();
		Object[][] results = data.load_encabezado(id);
		if (results != null) {
			if (results.length > 0) {
				String importe = (String) results[0][0];
				String anticipo = (String) results[0][1];
				String cuenta = (String) results[0][2];
				String desc = (String) results[0][3];
				String fecha = (String) results[0][4];
				fecha = C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss",
						fecha);
				frame.get_txt_idcliente().setText(cuenta);
				frame.get_txt_clientedescripcion().setText(desc);
				frame.get_txt_fecha().setText(fecha);
				frame.get_chk_seleccionar_cpte().setEnabled(true);
				double imp = 0.0;
				double ant = 0.0;
				try {
					imp = new Double(importe);
				} catch (Exception e) {

				}
				try {
					ant = new Double(anticipo);
				} catch (Exception e) {

				}
				frame.get_txt_total_pago().setText(C.getMoney(imp, 2));
				frame.get_txt_anticipo().setText(C.getMoney(ant, 2));
			}
		}
		frame.get_txt_idcliente().setEditable(false);

	}

	private void create_table_opg(Object[][] results) {
		// sizesOPG=new int[]{30,60,60,120,70,70};
		// columnsOPG=new
		// String[]{"","Fecha","TC","idComprobante","importe","Estado"};
		CustomTable table = new CustomTable();

		Column col = new Column();
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

		// results=this.procesar_cpte(results,"");
		table.setData(results);
		table.addMouseListener(this.getConstructor().getMouseListener());
		Font fuente = new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		frame.setJTableOPG(table.getTable());
	}

	public void evaluatecheckOPG(JCheckBox jx, int row, int col,
			boolean transfer) {
		this.recalculate_cptes(-1, false);
		this.recalculate_creditos(row, jx.isSelected());
		this.evaluateCreditos(jx, row);
	}

	public void evaluatecheckCpte(JCheckBox jx, int row, int col,
			boolean transfer) {
		this.evaluateCptes(jx, row);
		
		this.recalculate_cptes(row, jx.isSelected());
		this.recalculate_creditos(-1, false);
		
		
	}

	public double getPago(JTable table){
		double sum=0;
		double ant=0;
		for (int i=0;i<table.getRowCount();i++){
			String publico="";
			String codigo="";
			
			boolean error=false;
			double p2=0;
			
			try{
				codigo=table.getValueAt(i, 0).toString();
				publico=table.getValueAt(i, 7).toString();
				publico=new Convertidor().replace(publico, ",", "");
				p2=new Double(publico);
			}catch(Exception e){
				error=true;
			}
			if (!error){
					sum=sum+p2;	
				
			}else {
				System.out.println(i+" "+error);
			}
		}
		
		return sum;
	}
	
	public void evaluateCptes(JCheckBox jx, int row){
		double imp=this.getCptes(row, jx.isSelected());
		double cre=this.getCreditos(-1, false);
		double pag=this.getPago(frame.getJTableMedios());
		double saldo=imp-cre-pag;
		
		if (saldo>=0){
			this.setCredito(true);
			
		}else{
			this.setCredito(false);
			
		}
		
		
	}
	
	public void cargar_modos(){
		frame.get_lst_modo().removeItemListener(this.getConstructor().getItemListener());
		frame.get_lst_modo().removeAllItems();
		frame.get_lst_modo().addItem("Cobranza");
		frame.get_lst_modo().addItem("Devolucion");
		frame.get_lst_modo().setName(_Interface._lst_modo);
		frame.get_lst_modo().addItemListener(this.getConstructor().getItemListener());
		
	}
	public void evaluateModo(JComboBox cb){
		if (cb.getSelectedIndex()==0){
			frame.get_txt_estado().setForeground(Color.green);
			frame.get_txt_estado().setText("COBRANZA");
		}else{
			frame.get_txt_estado().setForeground(Color.RED);
			frame.get_txt_estado().setText("DEVOLUCION");
		}
	}
	
	public void evaluateCreditos(JCheckBox jx, int row){
		double imp=this.getCptes(-1, false);
		double cre=this.getCreditos(row, jx.isSelected());
		System.out.println("creditos:"+cre);
		System.out.println("comprobantes:"+imp);
		double saldo=imp-cre;
		if (saldo<0){
			this.setCredito(false);
		}else{
			this.setCredito(true);
		}
		
	}
	
	public Double getCreditos() {
		Double imp = 0.0;
		for (int i = 0; i < frame.getJTableOPG().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) frame.getJTableOPG().getValueAt(i, 0);
			} catch (Exception e) {

			}

			Double impx = 0.0;
			String importe = "";
			try {
				importe = frame.getJTableOPG().getValueAt(i, 4).toString();
			} catch (Exception e) {

			}

			if (b) {
				importe = importe.replace(",", "");
				try {
					impx = new Double(importe);
				} catch (Exception e) {

				}

			}
			imp = imp + impx;
		}
		return imp;
	}

	public void recalculate_creditos(int r, boolean ux) {
		Double imp = 0.0;
		for (int i = 0; i < frame.getJTableOPG().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) frame.getJTableOPG().getValueAt(i, 0);
			} catch (Exception e) {

			}

			Double impx = 0.0;
			String importe = "";
			try {
				importe = frame.getJTableOPG().getValueAt(i, 4).toString();
			} catch (Exception e) {

			}
			System.out.println(r + " " + ux + ") " + "  " + importe);
			if (i == r) {
				b = ux;
			}
			if (b) {
				importe = importe.replace(",", "");
				try {
					impx = new Double(importe);
				} catch (Exception e) {

				}

			}
			imp = imp + impx;
		}
		String impx = frame.get_txt_total_cpte().getText();
		double impw = new Double(impx.replace(",", ""));
		/*
		 * if (impw>0){ frame.get_txt_total_creditos().setText(new
		 * Convertidor().getMoney(imp, 2)); }else { imp=0.0;
		 * frame.get_txt_total_creditos().setText(new
		 * Convertidor().getMoney(imp, 2)); }
		 */
		frame.get_txt_total_creditos().setText(
				new Convertidor().getMoney(imp, 2));
		_medios_recalcular();
	}

	public double getCreditos(int r, boolean ux) {
		Double imp = 0.0;
		for (int i = 0; i < frame.getJTableOPG().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) frame.getJTableOPG().getValueAt(i, 0);
			} catch (Exception e) {

			}

			Double impx = 0.0;
			String importe = "";
			try {
				importe = frame.getJTableOPG().getValueAt(i, 4).toString();
			} catch (Exception e) {

			}
			System.out.println(r + " " + ux + ") " + "  " + importe);
			if (i == r) {
				b = ux;
			}
			if (b) {
				importe = importe.replace(",", "");
				try {
					impx = new Double(importe);
				} catch (Exception e) {

				}

			}
			imp = imp + impx;
		}
		
		return imp;
	}
	
	private void create_table_cpte(Object[][] results) {

		System.out.println("Creando tabla de comprobantes!! medios");
		CustomTable table = new CustomTable();

		Column col = new Column();
		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);

		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(_constructor.getItemListener());
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

		// results=this.procesar_cpte(results,"");
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		Font fuente = new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table_cpte);
		frame.setJTableCpte(table.getTable());
	}

	public void Seleccionar(boolean b) {
		for (int i = 0; i < frame.getJTableCpte().getRowCount(); i++) {
			frame.getJTableCpte().setValueAt(b, i, 0);
		}
		recalculate_cptes(-1, false);
	}

	public void SeleccionarAnticipos(boolean b) {
		for (int i = 0; i < frame.getJTableOPG().getRowCount(); i++) {
			frame.getJTableOPG().setValueAt(b, i, 0);
		}
		recalculate_cptes(-1, false);
	}

	public _Data getData() {
		return this.data;
	}

	/**
	 * 
	 * ========================================================================
	 * OPERACIONES UTILIZADAS PARA EL CONTROL Y MANEJO DE MEDIOS DE PAGO
	 */
	public void _medios_evaluar_banco_cheque(JTextField tx, int row, int col) {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_cod_banc(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_serie_cheque(JTextField tx, int row, int col) {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_serie(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_numero_cheque(JTextField tx, int row, int col) {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_numero(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_vencimiento(JTextField tx, int row, int col) {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_vencimiento(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_borrar_renglon(int row) {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.delete_renglonMedios(frame.getJTableMedios(), row);
	}

	public boolean _medios_recalcular() {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		boolean ok=control.recalculateMedios();
		return ok;
	}

	private void _medios_crear_tabla(Object[][] results,boolean focus) {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.create_table_medios(results,focus);
		
	}

	public void _medios_cargar_medios_de_pago(String id) {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.load_medios_de_pago(id);
	}

	public void _medios_evaluar_medio(JTextField tx, int row, int col) {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_medio(tx, row, col);

	}

	public Double _medios_obtener_restante() {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		double restante = control.getRestanteMedios();
		return restante;
	}

	public void _medios_evaluar_importe(JTable table,JTextField tx, int row, int col) {
		_Medios_de_Pago control = (_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_row_medios(table,tx, row, col);
	}

	public void cargar_datos() {
		String idcuenta = frame.get_txt_idcliente().getText();
		String _deuda_desde = frame.get_txt_deuda_desde().getText();
		String _deuda_hasta = frame.get_txt_deuda_hasta().getText();
		String _credito_desde = frame.get_txt_credito_desde().getText();
		String _credito_hasta = frame.get_txt_credito_hasta().getText();
		String deuda_desde = "01-01-1900";
		String deuda_hasta = "01-01-2900";
		String credito_desde = "01-01-1900";
		String credito_hasta = "01-01-2900";
		if (_deuda_desde.compareTo("") != 0) {
			deuda_desde = _deuda_desde;
		}
		if (_deuda_hasta.compareTo("") != 0) {
			deuda_hasta = _deuda_hasta;
		}
		if (_credito_desde.compareTo("") != 0) {
			credito_desde = _credito_desde;
		}
		if (_credito_hasta.compareTo("") != 0) {
			credito_hasta = _credito_hasta;
		}
		frame.setJTableCpte(null);
		frame.setJTableMedios(null);
		frame.setJTableOPG(null);

		this.cargar_deudas(idcuenta, deuda_desde, deuda_hasta);
		// this._cobranza_cargar_anticipos();

		this.cargar_creditos(idcuenta, credito_desde, credito_hasta);
	}

	public void editCell(int row, int col) {
		JTable table = frame.getJTableMedios();
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
	}
	
	public void borrarRenglon(int row) {
		
		if (preguntar("Confirmar", "Elimina Renglon " + row + " de la tabla?")) {
			try {
				frame.getJTableMedios().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			DefaultTableModel model = (DefaultTableModel) frame.getJTableMedios()
					.getModel();
			model.removeRow(row);
			if (model.getRowCount() <= 0) {
				model.setRowCount(1);
				this.editCell(0, 0);
			}
			this.recalculate_cptes();
			
		}
	}
	public void cargar_creditos(String idcuenta, String desde, String hasta) {

		Object[][] results = null;
		results = data.getAFavor(idcuenta, desde, hasta);
		for (int i = 0; i < results.length; i++) {
			String fecha = (String) results[i][0];
			Date _fecha = new Convertidor().getDateWithFormat2("dd-MM-yyyy",
					fecha);
			String tc = (String) results[i][1];
			String idcomprobante = (String) results[i][2];
			String importe = ((String) results[i][3]).replace(",", "");
			boolean conciliado = data.getConciliado(tc, idcomprobante);

			double imp = 0.0;
			try {
				imp = new Double(importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!conciliado & imp > 0) {
				this.addCpteCredito(frame.getJTableOPG(), false, _fecha, tc,
						idcomprobante, importe);
			}
		}
		results = data.getNCNPendientes(idcuenta, desde, hasta);
		for (int i = 0; i < results.length; i++) {
			String fecha = (String) results[i][0];
			Date _fecha = new Convertidor().getDateWithFormat2("dd-MM-yyyy",
					fecha);
			String tc = (String) results[i][1];
			String idcomprobante = (String) results[i][2];
			String importe = ((String) results[i][3]).replace(",", "");
			double imp = 0.0;
			try {
				imp = new Double(importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean conciliado = data.getConciliado(tc, idcomprobante);
			if (!conciliado & imp > 0) {
				this.addCpteCredito(frame.getJTableOPG(), false, _fecha, tc,
						idcomprobante, importe);
			}
		}
		results = data.getAnticiposPendientes(idcuenta, desde, hasta);
		for (int i = 0; i < results.length; i++) {
			String fecha = (String) results[i][0];
			Date _fecha = new Convertidor().getDateWithFormat2("dd-MM-yyyy",
					fecha);
			String tc = (String) results[i][1];
			String idcomprobante = (String) results[i][2];
			boolean conciliado = data.getConciliado(tc, idcomprobante);
			String importe = ((String) results[i][3]).replace(",", "");
			double imp = 0.0;
			try {
				imp = new Double(importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!conciliado & imp > 0) {
				this.addCpteCredito(frame.getJTableOPG(), false, _fecha, tc,
						idcomprobante, importe);
			}
		}
	}

	public void buscar_cliente() {
		this.BuscarCliente(frame.get_txt_idcliente());
	}

	public void cargar_cliente() {
		this.evaluarCliente(frame.get_txt_idcliente());
	}

	public void addCpteCredito(JTable table, boolean selected, Date fecha,
			String tc, String idcomprobante, String importe) {
		if (this.tableEmpty(table)) {
			Object[][] aux = new Object[][] { { selected, fecha, tc,
					idcomprobante, importe, "" } };
			this.create_table_opg(aux);
		} else {

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int r = model.getRowCount();
			model.setRowCount(r + 1);

			table.setValueAt(selected, r, 0);
			table.setValueAt(fecha, r, 1);
			table.setValueAt(tc, r, 2);
			table.setValueAt(idcomprobante, r, 3);
			table.setValueAt(importe, r, 4);
			table.setValueAt("", r, 5);
		}
	}

	public void cargar_deudas(String idcuenta, String desde, String hasta) {
		Object[][] results = null;
		results = data.getDeudas(idcuenta, desde, hasta);
		for (int i = 0; i < results.length; i++) {
			String fecha = (String) results[i][0];
			Date _fecha = new Convertidor().getDateWithFormat2("dd-MM-yyyy",
					fecha);
			String tc = (String) results[i][1];
			String idcomprobante = (String) results[i][2];
			String importe = (String) results[i][3];
			boolean conciliado = data.getConciliado(tc, idcomprobante);
			System.out.println("Conciliado> " + tc + "-" + idcomprobante + " "
					+ conciliado);
			if (!conciliado) {
				this.addCpte(frame.getJTableCpte(), false, _fecha, tc,
						idcomprobante, importe);
			}
		}
		results = data.getFVNPendientes(idcuenta, desde, hasta);
		for (int i = 0; i < results.length; i++) {
			String fecha = (String) results[i][0];
			Date _fecha = new Convertidor().getDateWithFormat2("dd-MM-yyyy",
					fecha);
			String tc = (String) results[i][1];
			String idcomprobante = (String) results[i][2];
			String importe = (String) results[i][3];
			this.addCpte(frame.getJTableCpte(), false, _fecha, tc,
					idcomprobante, importe);
		}
		results = data.getEGRPendientes(idcuenta, desde, hasta);
		for (int i = 0; i < results.length; i++) {
			String fecha = (String) results[i][0];
			Date _fecha = new Convertidor().getDateWithFormat2("dd-MM-yyyy",
					fecha);
			String tc = (String) results[i][1];
			String idcomprobante = (String) results[i][2];
			String importe = (String) results[i][3];
			this.addCpte(frame.getJTableCpte(), false, _fecha, tc,
					idcomprobante, importe);
		}
	}

	private boolean tableEmpty(JTable table) {
		boolean empty = true;
		if (table != null) {
			empty = !(table.getRowCount() > 0);
		}
		return empty;
	}

	public void addCpte(JTable table, boolean selected, Date fecha, String tc,
			String idcomprobante, String importe) {
		if (this.tableEmpty(table)) {
			Object[][] aux = new Object[][] { { selected, fecha, tc,
					idcomprobante, importe, "" } };
			this.create_table_cpte(aux);
		} else {

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int r = model.getRowCount();
			model.setRowCount(r + 1);

			table.setValueAt(selected, r, 0);
			table.setValueAt(fecha, r, 1);
			table.setValueAt(tc, r, 2);
			table.setValueAt(idcomprobante, r, 3);
			table.setValueAt(importe, r, 4);
			table.setValueAt("", r, 5);
		}
	}

	public void load_Cobranza(String id) {
		frame.get_txt_idcobranza().setText(id);
		frame.get_txt_idcobranza().setEditable(false);
		frame.get_btn_buscar().setEnabled(false);

		frame.get_btn_buscar_cliente().setEnabled(false);
		frame.get_btn_fecha().setEnabled(false);
		frame.get_txt_fecha().setEnabled(false);
		this._medios_cargar_medios_de_pago(id);
		this._cobranza_cargar_anticipos_utilizados();
		// this._cobranza_cargar_notasdecredito_utilizadas(id);
		this.load_comprobantes_pagados();
		if (data.cobranza_anulada(id)) {
			frame.get_btn_imprimir().setEnabled(false);
			frame.get_txt_estado().setText("ANULADA");
			frame.get_txt_estado().setForeground(Color.red);
			frame.get_btn_anular().setEnabled(false);
		} else {
			frame.get_txt_estado().setText("OK");
			frame.get_txt_estado().setForeground(Color.white);
			frame.get_btn_anular().setEnabled(true);
			frame.get_btn_imprimir().setEnabled(true);
		}
		this.SeleccionarAnticipos(true);
		this.load_encabezado(id);
		boolean credito=data.esCredito("CBCT", id);
		frame.get_lst_modo().setEnabled(false);
		this.setCredito(credito);
		this.recalculate_creditos(-1, true);
		frame.getJTableCpte().setEnabled(false);
		frame.getJTableMedios().setEnabled(false);
		frame.getJTableOPG().setEnabled(false);
		frame.get_btn_grabar().setEnabled(false);
		frame.get_btn_cargar_cliente().setEnabled(false);
		frame.get_btn_imprimir_cptes().setEnabled(false);
		frame.get_txt_credito_desde().setEnabled(false);
		frame.get_txt_credito_hasta().setEnabled(false);
		frame.get_txt_deuda_desde().setEnabled(false);
		frame.get_txt_deuda_hasta().setEnabled(false);
		frame.get_chk_seleccionar_cpte().setEnabled(false);
		frame.get_btn_buscar_credito_desde().setEnabled(false);
		frame.get_btn_buscar_credito_hasta().setEnabled(false);
		frame.get_btn_buscar_deuda_desde().setEnabled(false);
		frame.get_btn_buscar_deuda_hasta().setEnabled(false);
		frame.get_chk_seleccionar_cpte().setEnabled(false);
		frame.get_chk_seleccionar_creditos().setEnabled(false);
	}

	public void recalculate_cptes(int r, boolean ux) {
		Double imp = 0.0;
		for (int i = 0; i < frame.getJTableCpte().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) frame.getJTableCpte().getValueAt(i, 0);
			} catch (Exception e) {

			}
			Double impx = 0.0;
			String importe = "";
			try {
				importe = frame.getJTableCpte().getValueAt(i, 4).toString();
			} catch (Exception e) {

			}

			if (i == r) {
				b = ux;
			}
			if (b) {
				importe = importe.replace(",", "");
				impx = new Double(importe);
			}
			
			imp = imp + impx;
		}
		
		frame.get_txt_total_cpte().setText(new Convertidor().getMoney(imp, 2));
		_medios_recalcular();
	}
	
public double getCptes(int r, boolean ux) {
	Double imp = 0.0;
	for (int i = 0; i < frame.getJTableCpte().getRowCount(); i++) {
		boolean b = false;
		try {
			b = (Boolean) frame.getJTableCpte().getValueAt(i, 0);
		} catch (Exception e) {

		}
		Double impx = 0.0;
		String importe = "";
		try {
			importe = frame.getJTableCpte().getValueAt(i, 4).toString();
		} catch (Exception e) {

		}

		if (i == r) {
			b = ux;
		}
		if (b) {
			importe = importe.replace(",", "");
			impx = new Double(importe);
		}
		imp = imp + impx;
	}
	return imp;

}
	public void recalculate_cptes() {
		double imp = 0.0;

		for (int i = 0; i < frame.getJTableCpte().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) frame.getJTableCpte().getValueAt(i, 0);
			} catch (Exception e) {

			}
			Double impx = 0.0;
			String importe = "";
			try {
				importe = frame.getJTableCpte().getValueAt(i, 4).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(i + "> frame " + importe);
			importe = importe.replace(",", "");
			impx = new Double(importe);
			if (b) {
				imp = imp + impx;
			}

		}
		frame.get_txt_total_cpte().setText(new Convertidor().getMoney(imp, 2));

	}

	public Object[][] procesar_cpte(Object[][] results) {
		Object[][] temp = null;
		Convertidor C = new Convertidor();
		temp = new Object[results.length][6];
		for (int i = 0; i < results.length; i++) {
			String fecha = (String) results[i][0];
			fecha = C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss", fecha);
			String tc = (String) results[i][1];
			String idc = (String) results[i][2];
			String imp = (String) results[i][3];
			String est = "PAGADO";
			temp[i][0] = new Boolean(false);
			temp[i][1] = fecha;
			temp[i][2] = tc;
			temp[i][3] = idc;
			temp[i][4] = imp;
			temp[i][5] = est;
		}
		return temp;

	}

	public void _cobranza_cargar_anticipos() {
		String idcliente = frame.get_txt_idcliente().getText();
		Object[][] results = data.getAnticiposDisponibles(idcliente);
		if (results != null) {
			if (results.length > 0) {
				results = this.procesar_cpte(results);
				this.create_table_opg(results);
				frame.getJTableOPG().setEnabled(true);
			} else {
				frame.setJTableOPG(null);
			}
		} else {
			frame.setJTableOPG(null);
		}
	}

	public void _cobranza_cargar_anticipos_utilizados() {
		String idcobranza = frame.get_txt_idcobranza().getText();
		Object[][] results = data.getAnticiposUtilizados(idcobranza);
		if (results != null) {
			if (results.length > 0) {
				for (int i = 0; i < results.length; i++) {
					String fecha = (String) results[i][0];
					Date _fecha = new Convertidor().getDateWithFormat2(
							"dd-MM-yyyy", fecha);
					String tc = (String) results[i][1];
					String idcomprobante = (String) results[i][2];
					String importe = (String) results[i][3];
					this.addCpteCredito(frame.getJTableOPG(), false, _fecha,
							tc, idcomprobante, importe);

				}
			} else {
				frame.setJTableOPG(null);
			}
		}
	}

	public void _cobranza_cargar_saldos() {
		String idcliente = frame.get_txt_idcliente().getText();
		Object[][] results = data.getSaldos(idcliente);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_cpte(results);
				frame.get_txt_leyenda().setText(
						"Debe especificar los comprobantes a cancelar");
				frame.get_txt_leyenda().setForeground(Color.red);
				frame.getJTableCpte().setEnabled(true);

			} else {
				frame.get_txt_leyenda().setText(
						"No hay Comprobantes Pendientes");
				frame.get_txt_leyenda().setForeground(Color.red);
				frame.setJTableCpte(null);
			}
		}

	}

	public void _cobranza_cargar_notasdecredito() {
		String idcliente = frame.get_txt_idcliente().getText();
		Object[][] results = data.getNotasDeCreditosDisponibles(idcliente);
		if (results != null) {
			if (results.length > 0) {

				if (frame.getJTableOPG() == null) {
					this.create_table_opg(results);
				} else {
					DefaultTableModel model = (DefaultTableModel) frame
							.getJTableOPG().getModel();
					int row = model.getRowCount() - 1;
					if (row < 0) {
						this.create_table_opg(results);
					} else {
						System.out
								.println("Creando tabla para notas de credito ROW> "
										+ row);
						for (int i = 0; i < results.length; i++) {
							model.setRowCount(model.getRowCount() + 1);
							row = model.getRowCount() - 1;
							String fecha = (String) results[i][0];
							fecha = new Convertidor().ConvertDate("dd-MM-yyyy",
									"yyyy-MM-dd hh:mm:ss", fecha);
							String tc = (String) results[i][1];
							String idcomp = (String) results[i][2];
							String importe = (String) results[i][3];
							String estado = (String) results[i][4];
							frame.getJTableOPG().setValueAt(false, row, 0);
							frame.getJTableOPG().setValueAt(fecha, row, 1);
							frame.getJTableOPG().setValueAt(tc, row, 2);
							frame.getJTableOPG().setValueAt(idcomp, row, 3);
							frame.getJTableOPG().setValueAt(importe, row, 4);
							frame.getJTableOPG().setValueAt(estado, row, 5);
						}
					}

					frame.getJTableOPG().setEnabled(true);

				}

				// frame.getJScrollPaneCpte().setViewportView(frame.getJTableCpte());
			} else {
				frame.get_txt_leyenda().setText(
						"No hay Comprobantes Pendientes");
				frame.get_txt_leyenda().setForeground(Color.red);

			}
		}

	}

	public void agregarCpte(String fecha, String tc, String id, String importe) {
		Date _fecha = new Convertidor().getDate(fecha);
		this.addCpte(frame.getJTableCpte(), true, _fecha, tc, id, importe);
		this.recalculate_cptes();
		this.setCpteLastFocus();

	}

	public void focusOnMedios() {
		frame.getJTableMedios().requestFocusInWindow();
		frame.getJTableMedios().changeSelection(0, 0, false, false);
		frame.getJTableMedios().editCellAt(0, 0);
		frame.getJTableMedios().transferFocus();
	}

	public void setCpteLastFocus() {
		Rectangle rect = frame.getJTableCpte().getCellRect(
				frame.getJTableCpte().getRowCount() - 1, 0, true);

		// The location of the viewport relative to the table
		Point pt = frame.getJScrollPaneCpte().getViewport().getViewPosition();

		// Translate the cell location so that it is relative
		// to the view, assuming the northwest corner of the
		// view is (0,0)
		rect.setLocation(rect.x - pt.x, 40 + rect.y - pt.y);

		// Scroll the area into view
		frame.getJScrollPaneCpte().getViewport().scrollRectToVisible(rect);

		// frame.getJTableCpte().requestFocusInWindow();

	}

	public void _cobranza_cargar_notasdecredito_utilizadas(String idcomprobante) {
		String idcliente = frame.get_txt_idcliente().getText();
		Object[][] results = data.getNotasDeCreditoUtilizadas(idcomprobante);
		if (results != null) {
			if (results.length > 0) {
				if (frame.getJTableOPG() == null) {
					this.create_table_opg(results);
				} else {
					DefaultTableModel model = (DefaultTableModel) frame
							.getJTableOPG().getModel();
					int row = model.getRowCount() - 1;
					if (row < 0) {
						this.create_table_opg(results);
					} else {
						System.out
								.println("Creando tabla para notas de credito ROW> "
										+ row);
						for (int i = 0; i < results.length; i++) {
							model.setRowCount(model.getRowCount() + 1);
							row = model.getRowCount() - 1;
							String fecha = (String) results[i][0];
							fecha = new Convertidor().ConvertDate("dd-MM-yyyy",
									"yyyy-MM-dd hh:mm:ss", fecha);
							String tc = (String) results[i][1];
							String idcomp = (String) results[i][2];
							String importe = (String) results[i][3];
							String estado = (String) results[i][4];
							frame.getJTableOPG().setValueAt(false, row, 0);
							frame.getJTableOPG().setValueAt(fecha, row, 1);
							frame.getJTableOPG().setValueAt(tc, row, 2);
							frame.getJTableOPG().setValueAt(idcomp, row, 3);
							frame.getJTableOPG().setValueAt(importe, row, 4);
							frame.getJTableOPG().setValueAt(estado, row, 5);
						}
					}

					frame.getJTableOPG().setEnabled(true);

				}

				// frame.getJScrollPaneCpte().setViewportView(frame.getJTableCpte());
			} else {
				frame.get_txt_leyenda().setText(
						"No hay Comprobantes Pendientes");
				frame.get_txt_leyenda().setForeground(Color.red);

			}
		}

	}

	public void fillSaldosOnline() {
		int row = 0;
		if (cpte_fecha.compareTo("") != 0) {
			if (frame.getJTableCpte() != null) {
				if (frame.getJTableCpte().getRowCount() > 0) {
					boolean empty = false;
					try {
						empty = frame.getJTableCpte().getValueAt(
								frame.getJTableCpte().getRowCount() - 1, 0) == null;
					} catch (Exception e) {

					}
					if (!empty) {
						DefaultTableModel defaultTableModelCpte = (DefaultTableModel) frame
								.getJTableCpte().getModel();
						defaultTableModelCpte.setRowCount(defaultTableModelCpte
								.getRowCount() + 1);
					}
					row = frame.getJTableCpte().getRowCount() - 1;
				} else {
					Object[][] temp = new Object[][] { { true, cpte_fecha,
							cpte_tc, cpte_idc, cpte_importe, "Pendiente" } };
					this.create_table_cpte(temp);
				}
			} else {
				Object[][] temp = new Object[][] { { true, cpte_fecha, cpte_tc,
						cpte_idc, cpte_importe, "Pendiente" } };
				this.create_table_cpte(temp);
			}
			this.recalculate_cptes(row, true);

		}

	}

	public void load_comprobantes_pagados() {
		String id = this.frame.get_txt_idcobranza().getText();
		Object[][] results = data.load_comprobantes_pagados(id);
		if (results != null) {
			if (results.length > 0) {
				for (int i = 0; i < results.length; i++) {

					String fecha = (String) results[i][0];
					Date _fecha = new Convertidor().getDateWithFormat2(
							"dd-MM-yyyy", fecha);
					String tc = (String) results[i][1];
					String idcomprobante = (String) results[i][2];
					String importe = (String) results[i][3];
					double imp = 0.0;
					try {
						imp = new Double(importe);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.setCredito(imp >= 0);
					if (!this.isCredito()) {
						imp = -imp;
						frame.get_txt_estado().setForeground(Color.RED);
						frame.get_txt_estado().setText("DEVOLUCION");

					} else {
						frame.get_txt_estado().setForeground(Color.green);
						frame.get_txt_estado().setText("COBRANZA");
					}
					System.out.println("Agregando Cpte " + fecha + "|" + tc
							+ " " + idcomprobante + "|" + importe);
					this.addCpte(frame.getJTableCpte(), true, _fecha, tc,
							idcomprobante, importe);

				}
			}
		}
		this.Seleccionar(true);
		recalculate_cptes();
	}

	public Object[][] procesar_cpte(Object[][] results, String estado) {
		Object[][] temp = null;
		stock1.Convertidor C = new stock1.Convertidor();
		temp = new Object[results.length][6];
		for (int i = 0; i < results.length; i++) {
			// String fecha=(String) results[i][];
			String fecha = (String) results[i][0];
			fecha = C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss", fecha);
			String tc = (String) results[i][1];
			String idc = (String) results[i][2];
			String imp = (String) results[i][3];

			temp[i][0] = new Boolean(false);
			temp[i][1] = fecha;
			temp[i][2] = tc;
			temp[i][3] = idc;
			temp[i][4] = imp;
			temp[i][5] = estado;
		}
		return temp;

	}

	public Object[][] procesar_nc(Object[][] results) {
		Object[][] temp = null;
		stock1.Convertidor C = new stock1.Convertidor();
		temp = new Object[results.length][6];
		for (int i = 0; i < results.length; i++) {
			String fecha = (String) results[i][0];
			fecha = C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss", fecha);
			String tc = (String) results[i][1];
			String idc = (String) results[i][2];
			String imp = (String) results[i][3];
			String est = "";
			temp[i][0] = new Boolean(false);
			temp[i][1] = fecha;
			temp[i][2] = tc;
			temp[i][3] = idc;
			temp[i][4] = imp;
			temp[i][5] = est;
		}
		return temp;

	}

	private aplicacion.herramientas.java.evaluadores.Cliente cliente = null;

	public void initialize_cliente() {
		cliente = new aplicacion.herramientas.java.evaluadores.Cliente() {
			public void cargar(String codigo) {
				cargarCliente(codigo);
			}
		};
		cliente.setConstructor(this.getConstructor());
	}

	public void BuscarCliente(JTextField tx) {
		cliente.Buscar(tx);
	}

	public void BuscarCliente() {
		cliente.Buscar(frame.get_txt_idcliente());
	}

	public void buscarCliente(JTextField tx) {
		cliente.buscar(tx);
	}

	public void evaluarCliente(JTextField tx) {
		cliente.evaluate(tx);
	}

	public void focus() {
		frame.get_txt_idcobranza().requestFocusInWindow();
	}

	public void _cobranza_evaluar_codigo_cliente(String idcliente) {

		if (this.ventas == null) {
			this.cargar_datos();
		}
		_cobranza_crearMediosVacios();

	}

	public void cargarCliente(String idcliente) {
		Object[][] results = data.getClientInformation(idcliente);
		if (results != null) {
			if (results.length > 0) {
				frame.get_txt_idcliente().setText(idcliente);
				frame.get_txt_idcliente().setEditable(false);
				String desc = results[0][1].toString();
				frame.get_txt_clientedescripcion().setText(desc);
				// cargarCliente(idcliente);
				this._cobranza_evaluar_codigo_cliente(idcliente);
			}
		}
	}

	public void cargarComprobantesCliente(String idcliente) {

		Object[][] results = data.getClientInformation(idcliente);
		if (results != null) {
			if (results.length > 0) {
				frame.get_txt_idcliente().setEditable(false);
				String desc = results[0][1].toString();
				frame.get_txt_clientedescripcion().setText(desc);
				cargar_datos();
				Object[][] empty_medios = new Object[][] { { "", "", "", "",
						"", "", "", "", "" } };
				this._medios_crear_tabla(empty_medios,false);

			} else {
				error("El Codigo de Cliente es Inexistente. Utilice F5 para Buscar");
				frame.get_txt_idcliente().requestFocusInWindow();
			}
		}
	}

	private void _cobranza_crearMediosVacios() {
		Object[][] empty_medios = new Object[][] { { "", "", "", "", "", "",
				"", "", "" } };
		this._medios_crear_tabla(empty_medios,true);
		
	}

	

	public void load_calendar() {
		this.BuscarFecha(frame.get_txt_fecha());
	}

	private aplicacion.herramientas.java.buscadores.Fecha bFecha = null;

	public void BuscarFecha(JTextField tx) {
		if (bFecha == null) {
			bFecha = new aplicacion.herramientas.java.buscadores.Fecha(this
					.getConstructor());
		}

		bFecha.Buscar(tx);
	}

	public void buscar_deuda_desde() {
		BuscarFecha(frame.get_txt_deuda_desde());
	}

	public void buscar_deuda_hasta() {
		BuscarFecha(frame.get_txt_deuda_hasta());
	}

	public void buscar_credito_desde() {
		BuscarFecha(frame.get_txt_credito_desde());
	}

	public void buscar_credito_hasta() {
		BuscarFecha(frame.get_txt_credito_hasta());
	}

	private boolean checkFecha(String fecha) {
		boolean error = false;
		DateFormat formatter;
		try {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			formatter.parse(fecha);

		} catch (Exception e) {
			error = true;
			error("Error en Fecha");
		}
		return error;
	}

	
	public void _evaluar_fecha(JTextField txt) {
		String s = txt.getText();
		DateFormat formatter;
		Date date = null;
		boolean error = false;
		String s1 = "";
		try {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			date = (Date) formatter.parse(s);
			s1 = formatter.format(date);
		} catch (Exception e) {
			error = true;
			error("Error en Fecha");
		}
		if (!error) {
			txt.setText(s1);
			frame.get_txt_idcliente().requestFocusInWindow();

		} else {
			txt.requestFocusInWindow();
		}
	}
	public void imprimir_comprobantes() {
		if (preguntar("Confirmar", "Imprime Copia de Comprobantes ")){
			this.reporte_cptes();	
		}
	}
	public String get_comprobantes() {
		
		String where="";
		for (int i = 0; i < frame.getJTableCpte().getRowCount(); i++) {
			boolean selected = (Boolean) frame.getJTableCpte().getValueAt(i, 0);
			String tc = frame.getJTableCpte().getValueAt(i, 2).toString();
			String idcomprobante = frame.getJTableCpte().getValueAt(i, 3).toString();
			if (selected) {
				if (where.length()>0){
					where+=" or ";
				}
				where+=" (b.tc like '"+tc+"' and b.idcomprobante like '"+idcomprobante+"' )";

			} else {

			}
		}
		return where;
	}

	public void SeleccionarCreditos(boolean b) {
		for (int i = 0; i < frame.getJTableOPG().getRowCount(); i++) {
			frame.getJTableOPG().setValueAt(b, i, 0);
		}
		this.recalculate_cptes(-1, false);
		this.recalculate_creditos(-1, false);
	}

	public double getDescuentos() {
		double desc = 0;
		if (frame.getJTableMedios() != null) {
			for (int i = 0; i < frame.getJTableMedios().getRowCount(); i++) {
				String medio = "";
				String importe = "";
				try {
					medio = frame.getJTableMedios().getValueAt(i, 0).toString();
					importe = frame.getJTableMedios().getValueAt(i, 7)
							.toString();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}
				importe = importe.replaceAll(",", "");
				double imp = 0.0;
				try {
					imp = new Double(importe);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block

				}
				if (medio.compareTo("DO") == 0) {
					desc += imp;
				}

			}
		}
		return desc;
	}

	public void GrabarCobranza() {
		// double importe=this.
		this.GrabarCobranza(this.isCredito());

	}

	// =true;//q pasa si hay q devolver plata?
	public void GrabarCobranza(boolean credito) {
		String fecha = frame.get_txt_fecha().getText();
		this.recalculate_cptes();
		boolean ok=_medios_recalcular();
		if (ok){
			if (fecha.compareTo("") != 0) {
				_Asiento control = (_Asiento) this
						.getExtension(_Interface._extension_asiento);

				boolean su = !this.esFechaVieja(fecha);
				if (!su) {
					aviso("Advertencia: Grabar operaciones con fechas anteriores puede alterar los saldos actuales de caja");
				}

				String iduser = this.validar_usuario();
				su = iduser.compareTo("") != 0;
				if (su) {

					if (this.getDescuentos() >= 1) {
						error("Descuento:" + this.getDescuentos());

					}
					control.GenerarAsientodePago(credito);

				}

			} else {
				error("Debe ingresar una fecha valida");
			}
	
		}else{
			error("Revise La Cobranza");
		}
			
		
		
	}

	public void verComprobante(JTable table, int row, int col, Point p) {
		int _tc = 2;

		String tc = table.getValueAt(row, _tc).toString();
		String idcomprobante = table.getValueAt(row, _tc + 1).toString();

		if (tc.compareTo("CBCT") == 0) {
			// this.verCobranza(idcomprobante);
		}
		if (tc.compareTo("FVN") == 0) {
			this.verFvn("FVN", idcomprobante, p);
		}
		if (tc.compareTo("NCN") == 0) {
			this.verFvn("NCN", idcomprobante, p);
		}
	}

	public void verFvn(String tc, String idcomprobante, Point p) {
		fvn f = new fvn(this.getConstructor());

		f.initializeConstructor();
		f.setTc(tc);
		f.setIdcomprobante(idcomprobante);
		f.setLocation(p);
		f.Mostrar();
	}

	public void checkFVN() {

	}

	

	public void setCredito(boolean credito) {

			if (credito){
				frame.get_lst_modo().setSelectedIndex(0);
			}else{
				frame.get_lst_modo().setSelectedIndex(1);
			}
	}

	private aplicacion.herramientas.java.buscadores.Banco bBanco = null;

	public void BuscarBanco(int finalrow, JTextField ext) {
		final int ux = finalrow;

		final Ingreso_Medios_de_Pago control = (Ingreso_Medios_de_Pago) getExtension(_Interface._extension_medios_de_pago);
		if (bBanco == null) {
			bBanco = new aplicacion.herramientas.java.buscadores.Banco(this
					.getConstructor()) {
				public void initializeConstructor() {
					C = new aplicacion.herramientas.java.sortableselector.constructor._Constructor() {
						@Override
						protected void initialize_logic() {
							_logic = new aplicacion.herramientas.java.sortableselector.logic._Logic() {
								@Override
								public void Close(JTable table, int row) {
									String idbanco = table.getValueAt(row, 0)
											.toString();
									String nombre = table.getValueAt(row, 1)
											.toString();
									control.addBanco(ux, idbanco, nombre);
									super.Close(table, row);
								}
							};
						};
					};
				};
			};

		}

		bBanco.Buscar(ext);
	}

	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte = null;

	public void imprimir() {
		String tc = "CBCT";
		String idcomprobante = frame.get_txt_idcobranza().getText();
		if (preguntar("Confirmar", "Imprime Copia de Comprobante " + tc + "-"
				+ idcomprobante)) {
			reporte();
		}
	}

	public void reporte() {
		if (reporte != null) {
			reporte.dispose();
		}
		String tc = "CBCT";
		String idcomprobante = frame.get_txt_idcobranza().getText();
		String cuenta = frame.get_txt_idcliente().getText();
		reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param = new HashMap();
		param.put("tc", tc);
		param.put("idcomprobante", idcomprobante);
		param.put("cuenta", cuenta);
		reporte.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.parametros,
						param);
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.reporte,
						"cobranza.jasper");
		reporte.build(this.getConstructor());
		reporte.init();
	}
	
	
	

	public void reporte_cptes() {
		if (reporte != null) {
			reporte.dispose();
		}
		reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param = new HashMap();
		String where=this.get_comprobantes();
		param.put("where_clause", where);
		reporte.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.parametros,
						param);
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.reporte,
						"fvns.jasper");
		reporte.build(this.getConstructor());
		reporte.init();
	}
	
	private aplicacion.herramientas.java.evaluadores.Cobranza Cobranza=null;
	public void initialize_Cobranza(){
		Cobranza=new aplicacion.herramientas.java.evaluadores.Cobranza(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				loadCobranza(codigo);
			}
		};
		Cobranza.setConstructor(this.getConstructor());
	}
	public void BuscarCobranza(JTextField tx){
		Cobranza.Buscar(tx);
	}
	public void BuscarCobranza(){
		Cobranza.Buscar(frame.get_txt_idcobranza());
	}
	public void buscarCobranza(JTextField tx){
		Cobranza.buscar(tx);
	}
	
	public void evaluarCobranza(JTextField tx){
		Cobranza.evaluate(tx);
	}
	
	public void nuevo(){
		this.clean();
		this.setInitialDate();
		obtener_proximo_cpte();
		frame.get_txt_idcobranza().requestFocusInWindow();
	}
	
}