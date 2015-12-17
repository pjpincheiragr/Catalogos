package aplicacion.gestion.canje.logic;

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

import aplicacion.herramientas.java.sortableselector.*;
import aplicacion.gestion.canje.gui._Frame;
import aplicacion.gestion.canje.interfaces._Interface;
import aplicacion.gestion.canje.logic.extensions.Canje_Medios_de_Pago;
import aplicacion.gestion.canje.logic.extensions.Canje_Asiento;
import aplicacion.gestion.ingreso.logic.extensions.Ingreso_Medios_de_Pago;
import aplicacion.gestion.rechazados.logic.extensions.Rechazados_Medios_de_Pago;


import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;

public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;

	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;

	/*
	 * TC es la variable para indicar cual es el tipo de comprobante de esta
	 * aplicacion. Para canje el tipo de comprobante es CJE.
	 */
	private String TC = "CJE";

	/**
	 * Incializa la logica de esta aplicacion y carga las extensiones que
	 * utiliza
	 */
	public _Logic() {
		Canje_Medios_de_Pago control = new Canje_Medios_de_Pago();
		Canje_Asiento asiento = new Canje_Asiento();
		this.addExtension(control);
		this.addExtension(asiento);
		System.out.println("Extension=?"
				+ this.getExtension("Canje_Medios_de_Pago"));
		System.out.println("Extension=?" + this.getExtension("Canje_Asiento"));
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

	/**
	 * Metodo que ejecuta al salir. pero es igual al default. es al pedo en
	 * realidad
	 */
	public void exit_command() {
		_frame.setVisible(false);
		_frame.dispose();
	}

	/**
	 * Metodo clean. que se utiliza para limpiar el frame.
	 */
	public void clean() {
		frame.get_txt_total_egreso().setText("0.00");
		frame.get_txt_total_ingreso().setText("0.00");
		frame.get_txt_total_diferencia().setText("0.00");
		frame.get_txt_fecha().setText("");
		frame.get_txt_leyenda().setText("");
		frame.setJTableMedios(null);
		frame.setJTableMedios2(null);
		frame.get_txt_estado().setText("");
		frame.get_txt_concepto().setText("");
		frame.get_txt_estado().setForeground(Color.white);
		frame.get_txt_idPago().setEditable(true);
		frame.get_btn_buscar_pago().setEnabled(true);
		frame.get_list_cajas().setEnabled(true);
		frame.get_btn_buscar_pago().setEnabled(true);
		frame.get_txt_caja_detalle().setText("");
		this.obtener_proximo_cpte();
		cargar_cajas();
		this.setInitialDate();
	}

	public void _anular_canje(String id){
		_Data _data = (_Data) this._data;
		if (preguntar("Confirmar", "Elimina Canje " + id + "?")) {
			boolean error = _data.anular_PG("CJE", id);
			if (!error) {
				aviso("Se anulo Correctamente el Canje");
				this.clean();
			} else {
				aviso("Error anulando el Canje");
			}
		}
	}
	
	/**
	 * Metodo que se ejecuta para anular un comprobante
	 */
	public void anular() {
		_Data _data = (_Data) this._data;
		String id = frame.get_txt_idPago().getText();
		if (id.compareTo("") != 0) {
		
			if (_data.anulada(id)) {
				aviso("El Canje " + id + " ya fue anulado");
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
						this._anular_canje(id);
					}
					
				} else {
					aviso("El Canje " + id + " no existe");
				}
			}
		}

	}

	/**
	 * Metodo cancelar
	 */
	public void cancelar() {
		if (preguntar("Confirmar", "Cancela?")) {
			clean();
			frame.get_txt_idPago().setEditable(true);
			obtener_proximo_cpte();
		}
	}

	/**
	 * Metodo para cargar la fecha del dia en un textField determinado
	 */
	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_fecha().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy"));
	}

	public void _evaluar_idcanje() {
		this._evaluar_idcanje(frame.get_txt_idPago());
	}

	/**
	 * Metodo para evaluar si el idcanje existe o es nuevo.
	 * 
	 * @param tx
	 */
	public void _evaluar_idcanje(JTextField tx) {
		String idx = tx.getText();
		_Data _data = (_Data) this._data;
		if (_data.exist_pago(idx)) {
			evaluarCanje(idx);
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
			frame.get_txt_idPago().setEditable(false);
			frame.get_btn_grabar().setEnabled(true);
			frame.get_txt_fecha().requestFocusInWindow();
		}
	}

	public void evaluarCanjeId() {
		this.evaluarCanje(frame.get_txt_idPago().getText());
	}
	/**
	 * Metodo para cargar un pago utilizando el id como parametro.
	 * 
	 * @param id
	 */
	public void evaluarCanje(String id) {
		frame.get_txt_idPago().setText(id);
		frame.get_txt_idPago().setEditable(false);
		frame.get_btn_buscar_pago().setEnabled(false);
		this.cargar_cajas();
		if (data.exist_pago(id)) {
				load_Canje(id);
		} else {
			System.out.println("Pago nueva");
			this.setInitialDate();
			frame.get_txt_fecha().requestFocusInWindow();
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
				String uscanje = (String) results[0][6];
				
				fecha = C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss",
						fecha);
				frame.get_txt_fecha().setText(fecha);
				frame.get_txt_cotizacion_dolar().setText(new Convertidor().getMoney(uscanje,2));
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

				frame.get_txt_total_egreso().setText(C.getMoney(imp, 2));

			}
		}

	}

	public _Data getData() {
		return this.data;
	}

	/**
	 * 
	 * ========================================================================
	 * OPERACIONES UTILIZADAS PARA EL CONTROL Y MANEJO DE MEDIOS DE PAGO
	 */
	public void _medios_evaluar_banco_cheque(JTable table, JTextField tx,
			int row, int col) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_cod_banc(table, tx, row, col);
	}

	public void _medios_evaluar_serie_cheque(JTable table, JTextField tx,
			int row, int col) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_serie(table, tx, row, col);
	}

	public void _medios_evaluar_numero_cheque(JTable table, JTextField tx,
			int row, int col) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_numero(table, tx, row, col);
	}
	
	public void _medios_evaluar2_numero_cheque(JTable table, JTextField tx,
			int row, int col) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_numero2(table, tx, row, col);
	}

	public void _medios_evaluar_vencimiento(JTable table, JTextField tx,
			int row, int col) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_vencimiento(table, tx, row, col);
	}

	public void _medios_borrar_renglon(JTable table, int row) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.delete_renglonMedios(table, row);
	}

	public void _medios_recalcular(JTable table) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.recalculateMedios();
	}
	
	public boolean tieneDolares(JTable table){
		boolean tiene=false;
		for (int i=0;i<table.getRowCount();i++){
			String val=table.getValueAt(i, 0).toString();
			if (val.compareTo("US")==0){
				tiene=true;
			}
		}
		return tiene;
	}
	
	public void recalculate(){
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
		.getExtension(_Interface._extension_medios_de_pago);
		double pag=control.getPago(frame.getJTableMedios());
		double pag2=control.getPago(frame.getJTableMedios2());
		double sum=pag2-pag;
		double cotiz=0;
		if (sum!=0){
			if (this.tieneDolares(frame.getJTableMedios())){
				cotiz=pag/pag2;
			}else{
				cotiz=pag2/pag;
			}
			cotiz=new Convertidor().roundDouble(cotiz, 2);
			frame.get_txt_cotizacion_dolar().setText(new Convertidor().getMoney(cotiz, 2));
		}else{
			frame.get_txt_cotizacion_dolar().setText(new Convertidor().getMoney(0, 2));
		}
		control.recalculateMedios();
	}

	public void _medios_crear_tabla(Object[][] results) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		JTable table_egreso = control.create_table_medios(results);
		table_egreso.setName(_Interface._table_medios);
		frame.setJTableMedios(table_egreso);
		JTable table_ingreso = control.create_table_medios(results);
		table_ingreso.setName(_Interface._table_medios2);
		frame.setJTableMedios2(table_ingreso);
	}

	public void _medios_cargar_medios_de_pago(String id) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		frame.setJTableMedios(control.load_medios_de_pago_egreso(id));
		frame.setJTableMedios2(control.load_medios_de_pago_ingreso(id));
	}

	public void _medios_evaluar_medio(JTable table, JTextField tx, int row,
			int col) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_medio(table, tx, row, col);

	}

	public Double _medios_obtener_restante() {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		double restante = 0.0;
		return restante;
	}

	public void pedirCotizacion() {
		String cotiza = ingresar("Ingrese una cotizacion para la moneda");
		frame.get_txt_cotizacion_dolar().setText(cotiza);
	}

	public void _medios_evaluar_importe(JTable table, JTextField tx, int row,
			int col) {
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		control.eval_row_medios(table, tx, row, col);
	}

	private boolean eval_rows(JTable table) {
		System.out.println("Eval rows");
		boolean ok = true;
		int i = 0;
		while (i < table.getRowCount() & ok) {
			String tipo = "";
			String _imp = "";
			try {
				tipo = table.getValueAt(i, 0).toString();
				_imp = table.getValueAt(i, 7).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			if (tipo.compareTo("") != 0 & _imp.compareTo("") != 0) {
				ok = this.eval_row(table, i);
			}

			i++;
		}
		return ok;
	}

	private boolean eval_row(JTable table, int r) {
		boolean ok = false;
		Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) this
				.getExtension(_Interface._extension_medios_de_pago);
		String tipo = "";
		String _imp = "";
		try {
			tipo = table.getValueAt(r, 0).toString();
			_imp = table.getValueAt(r, 7).toString();
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
		if (tipo.compareTo("DOB") == 0) {
			if (imp > 0) {
				ok = true;
			} else {
				this.error("El importe no puede ser nulo");
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
				String idbanco = "" + table.getValueAt(r, 2);
				String serie = "" + table.getValueAt(r, 4);
				String numero = "" + table.getValueAt(r, 5);
				String idcajas = frame.get_list_cajas().getSelectedItem().toString().replaceAll(" ", "");
				
				if (table.getName()==_Interface._table_medios2){
					System.out.println("Cheque en Ingreso");
					ok = control.evaluar_disponibilidad_cheque(idbanco, serie,
							numero);
					if (ok) {
						error("El Cheque " + idbanco + " " + serie + "-"
								+ numero + " ya se encuentra disponible en Caja "
								+ idcajas);
						
						
						
					}else{
						ok=true;
					}
				}else{
					System.out.println("Cheque en Egreso");
					ok = control.evaluar_disponibilidad_cheque(idbanco, serie,
							numero);
					if (!ok) {
						error("El Cheque " + idbanco + " " + serie + "-"
								+ numero + " no esta disponible en Caja "
								+ idcajas);
					}
				}
				
				
			} else {
				this.error("El importe no puede ser nulo");

			}
		}
		return ok;
	}

	public void load_Canje(String id) {
		frame.get_txt_idPago().setText(id);
		frame.get_txt_idPago().setEditable(false);
		this._medios_cargar_medios_de_pago(id);
		
		if (data.pago_anulada(id)) {
			frame.get_txt_estado().setText("ANULADO");
			frame.get_txt_estado().setForeground(Color.red);
			frame.get_btn_anular().setEnabled(false);
		} else {
			frame.get_txt_estado().setText("OK");
			frame.get_txt_estado().setForeground(Color.white);
			frame.get_btn_anular().setEnabled(true);
		}

		this.load_encabezado(id);
		
		this._medios_recalcular(frame.getJTableMedios());
		this._medios_recalcular(frame.getJTableMedios2());

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

	public void cargar_cajas() {
		frame.get_list_cajas().removeAllItems();
		frame.get_list_cajas().removeActionListener(null);
		_Data data = (_Data) _data;
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

	/**
	 * Metodo para buscar banco. utiliza la clase Sortable Selector. Hace un
	 * override del metodo Close. Para definir que debe hacerse cuando el
	 * usuario selecciona un banco. En este caso al seleccionar un banco con
	 * doble clik o enter en el buscador Automaticamente se cierra la ventana y
	 * se cargan los datos de idbanco y descripcion en la tabla en la que esta
	 * trabajando el usuario. Ingreso o Egreso
	 * 
	 * @param table
	 * @param finalrow
	 * @param ext
	 */

	
	private aplicacion.herramientas.java.buscadores.Banco bBanco = null;
	public void BuscarBanco(JTable table, int finalrow,JTextField ext) {
		final int ux = finalrow;
		final JTable tablez = table;
		final Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) getExtension(_Interface._extension_medios_de_pago);
		if (bBanco == null) {
			bBanco = new aplicacion.herramientas.java.buscadores.Banco(this
					.getConstructor()){
				public void initializeConstructor() {
					C = new aplicacion.herramientas.java.sortableselector.constructor._Constructor() {
						@Override
						protected void initialize_logic() {
							_logic = new aplicacion.herramientas.java.sortableselector.logic._Logic() {
								@Override
								public void Close(JTable table, int row) {
									String idbanco = table.getValueAt(row, 0).toString();
									String nombre = table.getValueAt(row, 1).toString();
									control.addBanco(tablez, ux, idbanco, nombre);
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

	
	/**
	 * Metodo para buscar un cheque utiliza la clase SortableSelector En este
	 * caso se hace un override del metodo close de la clase SortableSelector.
	 * De esta forma cuando el usuario selecciona uno o mas cheques y hace doble
	 * clik o enter sobre el buscador de cheques. automaticamente se cierra la
	 * ventana de busqueda y se insertan los cheques en la tabla desde donde se
	 * llamo al buscador
	 * 
	 * @param table
	 * @param finalrow
	 * @param ext
	 */
	private aplicacion.herramientas.java.buscadores.Cheque bCheque = null;
	public void BuscarCheque(JTable table, int finalrow, JTextField tx) {
		final int ux = finalrow;
		final JTable tablez = table;
		final Canje_Medios_de_Pago control = (Canje_Medios_de_Pago) getExtension(_Interface._extension_medios_de_pago);
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
										System.out
												.println("Tratando de agregar cheque "
														+ i
														+ " en "
														+ ux
														+ i
														+ " num:"
														+ serie
														+ "-"
														+ numero);
										control.addCheque(tablez, ux + i, idbanco,
												nombre, serie, numero, importe,
												vencimiento);
									}

									super.Close(table, row);
									tablez.requestFocusInWindow();
									tablez.changeSelection(ux + select_idx.length
											- 1, 7, false, false);
									tablez
											.editCellAt(ux + select_idx.length - 1,
													7);
									tablez.transferFocus();
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


	/**
	 * Metodo para buscar Canjes anteriores
	 */
	public void BuscarCanje() {
		this.BuscarCanje(frame.get_txt_idPago());
	}

	/**
	 * Carga la fecha del dia en el frame
	 */
	public void setInitialDate() {
		String aux = data.getSystemDate();
		frame.get_txt_fecha().setText(aux);
	}

	/**
	 * Metodo para buscar Canjes anteriores y devolver el elemento seleccionado
	 * de la busqueda a un textfield.
	 * 
	 * @param ext
	 */
	private aplicacion.herramientas.java.buscadores.Canje bCanje = null;

	public void BuscarCanje(JTextField ext) {
		if (bCanje == null) {
			bCanje = new aplicacion.herramientas.java.buscadores.Canje(this
					.getConstructor());
		}
		
		bCanje.Buscar(ext);
	}

	public void load_calendar() {
		this.BuscarFecha(frame.get_txt_fecha());
	}

	/**
	 * Metodo para mostrar un calendario para seleccionar la fecha y cargar la
	 * fecha seleccionada en un textfield
	 * 
	 * @param tx
	 */
	private aplicacion.herramientas.java.buscadores.Fecha bFecha = null;

	public void BuscarFecha(JTextField tx) {
		if (bFecha == null) {
			bFecha = new aplicacion.herramientas.java.buscadores.Fecha(this
					.getConstructor());
			
		}
		
		bFecha.Buscar(tx);
	}

	/**
	 * Metodo para verificar una fecha a partir de un string
	 * 
	 * @param fecha
	 * @return
	 */
	private boolean checkFecha(String fecha) {
		boolean error = false;
		DateFormat formatter;
		try {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			formatter.parse(fecha);

		} catch (Exception e) {
			error = true;
			aviso(e.getMessage().toString(), "Error en Fecha");
		}
		return error;
	}

	/**
	 * Metodo para evaluar la seleccion de la caja. En realidad se utiliza para
	 * bloquear la caja. Para evitar errores de manipulacion!!!
	 */
	public void evaluate_caja() {
		frame.get_list_cajas().setEnabled(false);
		frame.get_txt_concepto().requestFocusInWindow();
	}

	/**
	 * Establece cual es la caja inicial
	 * 
	 * @param idcaja
	 */
	public void setCaja(String idcaja) {
		for (int i = 0; i < frame.get_list_cajas().getItemCount(); i++) {
			if (idcaja
					.compareTo(frame.get_list_cajas().getItemAt(i).toString()) == 0) {
				frame.get_list_cajas().setSelectedIndex(i);
			}
		}
	}

	/**
	 * Metodo para cargar la descripcion de la caja a partir del item
	 * seleccionado.
	 * 
	 * @param cb
	 */
	public void evaluate_caja(JComboBox cb) {
		if (cb!=null){
			String desc = cb.getSelectedItem().toString();
			if (desc!=null){
				desc = data.getDetalleCaja(desc);
				if (desc.compareTo("") != 0) {
					frame.get_txt_caja_detalle().setText(desc);
				}		
			}
			
		}
		

	}

	/**
	 * Metodo para evaluar la fecha a partir de un textfield
	 * 
	 * @param txt
	 */
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
			frame.get_list_cajas().requestFocusInWindow();
		} else {
			txt.requestFocusInWindow();
		}
	}
	
	public void _grabar_canje(){
		Canje_Asiento control = (Canje_Asiento) this
		.getExtension(_Interface._extension_asiento);
		String fecha=frame.get_txt_fecha().getText();
		boolean su=!this.esFechaVieja(fecha);
		if (!su){
			aviso("Advertencia: Grabar operaciones con fechas anteriores puede alterar los saldos actuales de caja");
			su=(this.data.getIsSuperUser(this.getConstructor().getIduser()));
			if (su){
				su=confirmar("Confirme grabacion con fecha vieja:",3);
			}else{
				su=this.pedir_autorizacion("Para Poder Grabar un Canje con fecha vieja pida Autorizacion: ");
				if (!su){
					error("No esta autorizado para efecutar esta operacion");
				}	
			}
			
			
		}
		if (su){
			control.GenerarAsientodePago();	
		}
			
	}

	/**
	 * Metodo para grabar el canje
	 */
	public void GrabarCanje() {
		try {
			frame.getJTableMedios().getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		Canje_Asiento control = (Canje_Asiento) this
				.getExtension(_Interface._extension_asiento);
		if (!this.checkFecha(frame.get_txt_fecha().getText())) {
			if (this.eval_rows(frame.getJTableMedios())
					& this.eval_rows(frame.getJTableMedios2())) {
				_grabar_canje();
	
			} else {
				error("El sistema no permite grabar este canje incorrecto");

			}

		} else {

		}
	}

	public boolean usaMoneda(JTable medios, String moneda) {
		boolean ok = false;
		int i = 0;
		while (i < medios.getRowCount() & !ok) {
			String codigo = "";
			try {
				codigo = medios.getValueAt(i, 0).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			ok = (codigo.compareTo(moneda) == 0);
			i++;
		}
		return ok;
	}

	public Double getCotizacion() {
		double tmp = 0.0;
		try {
			tmp = new Double(frame.get_txt_cotizacion_dolar().getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tmp;
	}

	public void calculate_cotizacion(){
		
	}
	public void evaluate_cotizacion(JTextField tx) {
		double tmp = 0.0;
		try {
			tmp = new Double(tx.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (tmp >= 0) {

		}
	}

}