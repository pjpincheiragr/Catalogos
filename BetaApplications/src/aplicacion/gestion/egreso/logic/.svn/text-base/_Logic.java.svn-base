package aplicacion.gestion.egreso.logic;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import aplicacion.herramientas.java.*;
import aplicacion.gestion.egreso.gui._Frame;
import aplicacion.gestion.egreso.interfaces._Interface;
import aplicacion.gestion.egreso.logic.extensions.Egreso_Medios_de_Pago;
import aplicacion.gestion.egreso.logic.extensions.Egreso_Asiento;
import aplicacion.herramientas.conexion.conectores.MsSQL;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;

	/*
	 * variables para la cobranza directa de caja. online
	 */

	public void AjusteEfectivo(double importe) {
		frame.getJTableMedios().setValueAt("EF", 0, 0);
		frame.getJTableMedios().setValueAt("Caja Efectivo", 0, 1);
		frame.getJTableMedios().setValueAt(importe, 0, 7);

	}

	public _Logic() {
		Egreso_Medios_de_Pago control = new Egreso_Medios_de_Pago();
		Egreso_Asiento asiento = new Egreso_Asiento();
		this.addExtension(control);
		this.addExtension(asiento);
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
	 * Para obtener el proximo numero de Pago disponible
	 */
	public void obtener_proximo_cpte() {
		_Data data = (_Data) _data;
		String cb = data.getProximoPGCorrecto();
		// Pago_frame _frame=(Pago_frame) this._frame;
		frame.get_txt_idPago().setText(cb);
	}

	public void clean() {

		frame.get_txt_idcuenta().setText("");
		frame.get_txt_total_pago().setText("0.00");
		frame.get_txt_clientedescripcion().setText("");
		frame.get_txt_fecha().setText("");
		frame.get_txt_leyenda().setText("");
		frame.get_txt_clientedescripcion().setText("");
		frame.setJTableMedios(null);
		frame.get_txt_estado().setText("");
		frame.get_txt_concepto().setText("");
		frame.get_txt_estado().setForeground(Color.white);
		frame.get_txt_idPago().setEditable(true);
		frame.get_btn_buscar_pago().setEnabled(true);
		frame.get_list_cajas().setEnabled(true);
		frame.get_btn_buscar_proveedor().setEnabled(true);
		frame.get_btn_buscar_pago().setEnabled(true);
		frame.get_txt_caja_detalle().setText("");
		this.obtener_proximo_cpte();
		cargar_cajas();

	}
public void _anular_egreso(String id){
	_Data _data = (_Data) this._data;
	if (preguntar("Confirmar", "Elimina Canje " + id + "?")) {
		boolean error = _data.anular_PG("EGR", id);
		if (!error) {
			aviso("Se anulo Correctamente el Egreso");
			this.clean();
		} else {
			error("Error anulando el Egreso");
		}
	}
}
	public void anular() {
		_Data _data = (_Data) this._data;
		String id = frame.get_txt_idPago().getText();
		if (id.compareTo("") != 0) {
			
			if (_data.anulada(id)) {
				aviso("El Egreso " + id	+ " ya fue anulado");
			} else {

				if (_data.exist_pago(id)) {


					String fecha=frame.get_txt_fecha().getText();
					boolean su=!(this.esFechaVieja(fecha));
					if (!su){
						aviso("Advertencia: Eliminar operaciones con fechas anteriores puede alterar los saldos actuales de caja");
						su=(this.data.getIsSuperUser(this.getConstructor().getIduser()));
						if (su){
							su=confirmar("Confirme anulacion de comprobante viejo:",3);
						}else{
							su=this.pedir_autorizacion();
						}
					}
					if (su){
						this._anular_egreso(id);
					}
				} else {
					JOptionPane.showMessageDialog(_frame, "El Egreso " + id
							+ " no existe");
				}

			}
		}

	}

	public void cancelar() {
		int operacion = JOptionPane.showConfirmDialog(this._frame,
				"Cancela Operacion?", "Confirmar", JOptionPane.YES_NO_OPTION);
		if (operacion == JOptionPane.YES_OPTION) {
			clean();
			frame.get_txt_idPago().setEditable(true);
			obtener_proximo_cpte();
		}
	}

	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_fecha().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy"));
	}

	public void _evaluar_idegreso() {
		this._evaluar_idegreso(frame.get_txt_idPago());
	}

	public void _evaluar_idegreso(JTextField tx) {
		String idx = tx.getText();
		_Data _data = (_Data) this._data;
		if (_data.exist_pago(idx)) {
			cargarPago(idx);
			// /aca mensaje de edita o dame un numero nuevo.
		} else {
			frame.get_btn_buscar_pago().setEnabled(false);
			cargar_cajas();
			if (frame.get_txt_fecha().getText().compareTo("") != 0) {
				boolean aux = this.checkFecha(frame.get_txt_fecha().getText());
				if (aux) {
					this.setInitialDate();
				}
			} else {
				this.setInitialDate();
			}

			frame.get_btn_buscar_pago().setEnabled(false);
			frame.get_btn_buscar_proveedor().setEnabled(true);
			frame.get_txt_idPago().setEditable(false);
			frame.get_txt_idcuenta().setEditable(true);
			frame.get_btn_grabar().setEnabled(true);
			frame.get_txt_fecha().requestFocusInWindow();
		}
	}

	public void cargarPago(String id) {
		frame.get_txt_idPago().setText(id);
		frame.get_txt_idPago().setEditable(false);
		frame.get_btn_buscar_pago().setEnabled(false);
		this.cargar_cajas();
		if (data.exist_pago(id)) {
				load_Egreso(id);
		} else {
			System.out.println("Pago nueva");
		}
	}

	public void load_encabezado(String id) {
		Convertidor C = new Convertidor();
		Object[][] results = data.load_encabezado(id);
		if (results != null) {
			if (results.length > 0) {
				String importe = (String) results[0][0];
				String cuenta = (String) results[0][1];
				String desc = (String) results[0][2];
				String concepto = (String) results[0][3];
				String fecha = (String) results[0][4];
				String idcajas = (String) results[0][5];
				fecha = C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss",
						fecha);
				frame.get_txt_idcuenta().setText(cuenta);
				frame.get_txt_clientedescripcion().setText(desc);
				frame.get_txt_fecha().setText(fecha);
				frame.get_txt_concepto().setText(concepto);
				for (int i = 0; i < frame.get_list_cajas().getItemCount(); i++) {
					if (frame.get_list_cajas().getItemAt(i).toString()
							.compareTo(idcajas) == 0) {
						frame.get_list_cajas().setSelectedIndex(i);
					}
				}
				double imp = 0.0;
				double ant = 0.0;
				try {
					imp = new Double(importe);
				} catch (Exception e) {

				}

				frame.get_txt_total_pago().setText(C.getMoney(imp, 2));

			}
		}
		frame.get_txt_idcuenta().setEditable(false);

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
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_cod_banc(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_serie_cheque(JTextField tx, int row, int col) {
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_serie(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_numero_cheque(JTextField tx, int row, int col) {
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_numero(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_vencimiento(JTextField tx, int row, int col) {
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_vencimiento(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_borrar_renglon(int row) {
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.delete_renglonMedios(frame.getJTableMedios(), row);
	}

	public void _medios_recalcular() {
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.recalculateMedios();
	}

	public void _medios_crear_tabla(Object[][] results) {
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.create_table_medios(results);
	}

	public void _medios_cargar_medios_de_pago(String id) {
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.load_medios_de_pago(id);
	}

	public void _medios_evaluar_medio(JTextField tx, int row, int col) {
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_medio(tx, row, col);

	}

	public Double _medios_obtener_restante() {
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		double restante = 0.0;
		return restante;
	}

	public void _medios_evaluar_importe(JTextField tx, int row, int col) {
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_row_medios(tx, row, col);
	}

	private boolean eval_rows() {
		System.out.println("Eval rows");
		boolean ok = true;
		int i = 0;
		while (i < frame.getJTableMedios().getRowCount() & ok) {
			String tipo = "";
			String _imp = "";
			try {
				tipo = frame.getJTableMedios().getValueAt(i, 0).toString();
				_imp = frame.getJTableMedios().getValueAt(i, 7).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (tipo.compareTo("") != 0 & _imp.compareTo("") != 0) {
				ok = this.eval_row(i);
			}

			i++;
		}
		return ok;
	}

	private boolean eval_row(int r) {
		boolean ok = false;
		Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		String tipo = "";
		String _imp = "";
		try {
			tipo = frame.getJTableMedios().getValueAt(r, 0).toString();
			_imp = frame.getJTableMedios().getValueAt(r, 7).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		double imp = 0;
		if (_imp.compareTo("") != 0) {
			try {
				imp = new Double(_imp.replace(",", ""));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (tipo.compareTo("EF") == 0) {
			if (imp > 0) {
				ok = true;
			} else {
				this.error("El importe no puede ser nulo");
			}
		}
		if (tipo.compareTo("US") == 0) {
			if (imp > 0) {
				ok = true;
			} else {
				this.error("El importe no puede ser nulo");
			}
		}
		if (tipo.compareTo("CHT") == 0) {
			if (imp > 0) {
				String idbanco = "" + frame.getJTableMedios().getValueAt(r, 2);
				String serie = "" + frame.getJTableMedios().getValueAt(r, 4);
				String numero = "" + frame.getJTableMedios().getValueAt(r, 5);

				ok = control.evaluar_disponibilidad_cheque(idbanco, serie,
						numero);
				String idcajas = frame.get_list_cajas().getSelectedItem()
						.toString().replaceAll(" ", "");
				if (!ok) {
					this
							.error("El Cheque " + idbanco + " " + serie + "-"
									+ numero + " no esta disponible en Caja "
									+ idcajas);
				}
			} else {
				this.error("El importe no puede ser nulo");

			}
		}
		return ok;
	}

	public void load_Egreso(String id) {
		frame.get_txt_idPago().setText(id);
		frame.get_txt_idPago().setEditable(false);
		this._medios_cargar_medios_de_pago(id);
		if (data.pago_anulada(id)) {
			frame.get_txt_estado().setText("ANULADA");
			frame.get_txt_estado().setForeground(Color.red);
			frame.get_btn_anular().setEnabled(false);
		} else {
			frame.get_txt_estado().setText("OK");
			frame.get_txt_estado().setForeground(Color.white);
			frame.get_btn_anular().setEnabled(true);
		}

		this.load_encabezado(id);
		this._medios_recalcular();

		frame.getJTableMedios().setEnabled(false);
		frame.get_btn_grabar().setEnabled(false);

	}

	public void evaluar_concepto(JTextField tx) {
		String aux = "";
		aux = tx.getText();
		boolean ok = aux.compareTo("") != 0;
		if (!ok) {
			ok = this.preguntar("confirmar", "Confirma concepto en blanco?");
		}
		if (ok) {
			int rows = 0;
			try {
				rows = frame.getJTableMedios().getRowCount();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (rows <= 0) {
				create_tabla();
			}
			frame.getJTableMedios().requestFocusInWindow();
			frame.getJTableMedios().changeSelection(0, 0, false, false);
			frame.getJTableMedios().editCellAt(0, 0);
			frame.getJTableMedios().transferFocus();
		}

	}

	public void create_tabla() {
		Object[][] empty_medios = new Object[][] { { "", "", "", "", "", "",
				"", "", "" } };
		this._medios_crear_tabla(empty_medios);
	}

	public void _evaluar_codigo_cuenta(JTextField tx) {
		String aux = tx.getText();
		if (aux.compareTo("") != 0) {
			int n = 0;
			try {
				n = new Integer(aux);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (n > 0) {
				this.load_All(tx);
			} else {
				this.buscarCuenta(tx);
			}

		} else {
			aviso("Ingrese Codigo de Cuenta. utilice F5 para buscar");
		}
	}

	public void load_All(JTextField tx) {
		String aux = tx.getText();
		if (aux.compareTo("") != 0) {
			if (Character.isLetter(aux.charAt(0))) {
				buscarCuenta(tx);
			} else {
				loadAll(aux);
			}

		}

	}

	public void loadAll(String idproveedor) {

		Object[][] results = data.getClientInformation(idproveedor);
		if (results != null) {
			if (results.length > 0) {

				String desc = results[0][1].toString();
				frame.get_txt_clientedescripcion().setText(desc);

				// _Pago_cargar_creditos();

				this.frame.get_list_cajas().requestFocusInWindow();
			} else {
				JOptionPane
						.showMessageDialog(frame,
								"El Codigo de Cuenta es Inexistente o No es Imputable. Utilice F5 para Buscar");
				frame.get_txt_idcuenta().requestFocusInWindow();
			}
		}
	}

	public void cargar_cajas() {
		frame.get_list_cajas().removeAllItems();
		frame.get_list_cajas().removeActionListener(null);
		
		String iduser=this.getConstructor().getIduser();
		Object[][] results = data.get_cajas_origen(iduser);
		for (int i = 0; i < results.length; i++) {

			try {
				frame.get_list_cajas().addItem(results[i][0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private aplicacion.herramientas.java.buscadores.Cuenta bCuenta = null;

	public void BuscarCuenta(JTextField ext) {
		if (bCuenta == null) {
			bCuenta = new aplicacion.herramientas.java.buscadores.Cuenta(this
					.getConstructor());
		}
		
		bCuenta.Buscar(ext);
	}

	private aplicacion.herramientas.java.visualizadores.Cuenta vCuenta = null;

	public void buscarCuenta(JTextField tx) {
		if (vCuenta != null) {
			vCuenta.close();
		}
		vCuenta = new aplicacion.herramientas.java.visualizadores.Cuenta(this
				.getConstructor());
		int n = vCuenta.Buscar(tx);
		if (n == 0) {
			vCuenta.close();
			aviso("no hay Cuentas con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Cheque bCheque = null;

	public void BuscarCheque(int finalrow, JTextField tx) {
		

		final Egreso_Medios_de_Pago control = (Egreso_Medios_de_Pago) getExtension(_Interface._extension_medios_de_pago);
		if (bCheque == null) {
			bCheque = new aplicacion.herramientas.java.buscadores.Cheque(this
					.getConstructor()) {

				public void initializeConstructor() {
					C = new aplicacion.herramientas.java.sortableselector.constructor._Constructor() {
						@Override
						protected void initialize_logic() {
							_logic = new aplicacion.herramientas.java.sortableselector.logic._Logic() {
								@Override
								public void Close(JTable table, int row) {
									System.out.println("LOGIC>double click " + row
											+ " on sortable selector");
									int[] select_idx = table.getSelectedRows();
									for (int i = 0; i < select_idx.length; i++) {
										String idbanco = table.getValueAt(
												select_idx[i], 0).toString();
										String nombre = table.getValueAt(
												select_idx[i], 1).toString();
										String serie = table.getValueAt(
												select_idx[i], 2).toString();
										String numero = table.getValueAt(
												select_idx[i], 3).toString();
										String importe = table.getValueAt(
												select_idx[i], 4).toString();
										String vencimiento = table.getValueAt(
												select_idx[i], 5).toString();
										control.addCheque( idbanco, nombre,serie, numero, importe,vencimiento);
									}
									super.Close(table, row);
									
								}
							};
						}
					};
				}

			};

		}

		bCheque.setIdcaja(frame.get_list_cajas().getSelectedItem().toString());
		bCheque.BuscarCheque(tx);

	}

	public void BuscarEgreso() {
		this.BuscarEgreso(frame.get_txt_idPago());
	}

	public void BuscarCuenta() {
		this.BuscarCuenta(frame.get_txt_idcuenta());
	}

	public void setInitialDate() {
		String aux = data.getSystemDate();
		frame.get_txt_fecha().setText(aux);
	}

	private aplicacion.herramientas.java.buscadores.Egreso bEgreso = null;

	public void BuscarEgreso(JTextField ext) {
		if (bEgreso == null) {
			bEgreso = new aplicacion.herramientas.java.buscadores.Egreso(this
					.getConstructor());
		}
		
		bEgreso.Buscar(ext);
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

	private boolean checkFecha(String fecha) {
		boolean error = false;
		DateFormat formatter;
		try {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			formatter.parse(fecha);

		} catch (Exception e) {
			error = true;
			JOptionPane.showMessageDialog(frame, e.getMessage(),
					"Error en Fecha", JOptionPane.ERROR_MESSAGE);
		}
		return error;
	}

	public void evaluate_caja() {
		frame.get_txt_idcuenta().setEditable(false);
		frame.get_btn_buscar_proveedor().setEnabled(false);
		frame.get_list_cajas().setEnabled(false);
		frame.get_txt_concepto().requestFocusInWindow();
	}

	public void setCaja(String idcaja) {
		for (int i = 0; i < frame.get_list_cajas().getItemCount(); i++) {
			if (idcaja
					.compareTo(frame.get_list_cajas().getItemAt(i).toString()) == 0) {
				frame.get_list_cajas().setSelectedIndex(i);
			}
		}
	}

	public void evaluate_caja(JComboBox cb) {
		String desc = cb.getSelectedItem().toString();
		desc = data.getDetalleCaja(desc);
		if (desc.compareTo("") != 0) {
			frame.get_txt_caja_detalle().setText(desc);
		}

	}

	public void evaluarFecha(JTextField txt) {
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
			JOptionPane.showMessageDialog(frame, e.getMessage(),
					"Error en Fecha", JOptionPane.ERROR_MESSAGE);
		}
		if (!error) {
			txt.setText(s1);
			frame.get_txt_idcuenta().requestFocusInWindow();

		} else {
			txt.requestFocusInWindow();
		}
	}

	public void egresoACuenta(String idproveedor) {
		this.obtener_proximo_cpte();
		this._evaluar_idegreso(frame.get_txt_idPago());
		frame.get_txt_idcuenta().setText(idproveedor);
		_evaluar_codigo_cuenta(frame.get_txt_idcuenta());
		loadAll(idproveedor);
	}

	public void _grabar_egreso(){
		String fecha=frame.get_txt_fecha().getText();
		boolean su=!this.esFechaVieja(fecha);
		if (!su){
			aviso("Advertencia: Grabar operaciones con fechas anteriores puede alterar los saldos actuales de caja");
			su=(this.data.getIsSuperUser(this.getConstructor().getIduser()));
			
			if (su){
				su=confirmar("Confirme grabacion con fecha vieja:",3);
			}else{
				su=this.pedir_autorizacion("Para Poder Grabar un Egreso con fecha vieja pida Autorizacion: ");
				if (!su){
					error("No esta autorizado para efecutar esta operacion");
				}	
			}
		}
		if (su){
			Egreso_Asiento control = (Egreso_Asiento) this
			.getExtension(_Interface._extension_asiento);
		control.GenerarAsientodePago();
		}
	}
	public void GrabarEgreso() {
		try {
			frame.getJTableMedios().getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!this.checkFecha(frame.get_txt_fecha().getText())) {
			if (this.eval_rows()) {
				_grabar_egreso();
				
			} else {
				error("El sistema no permite grabar este egreso incorrecto");

			}

		} else {

		}

	}

}