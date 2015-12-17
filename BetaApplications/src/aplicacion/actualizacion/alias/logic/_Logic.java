package aplicacion.actualizacion.alias.logic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import aplicacion.modelo.interfaces.*;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.actualizacion.alias.gui.*;
import aplicacion.actualizacion.alias.interfaces.*;

/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class _Logic extends Logic {
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.herramientas.java.buscadores.Articulo bArticulo = null;
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor = null;
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor = null;
	private aplicacion.herramientas.java.buscadores.Linea bLinea;

	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	private String estado = "";
	private int current;
	private int lenght;
	private boolean done, canceled;
	private int errors = 0;

	private Object[][] memoria = null;
	private _Data data;
	private _Frame frame;

	public void setFrame(JFrame frame) {
		this.frame = (_Frame) frame;
		super.setFrame(frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void goAutocompletar() {
		this.createTimer();
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker()
			{
				public Object construct() {
					return new _taskAutoCompletarAlias();
				}
			};
		}
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void goBorrar() {
		this.createTimer();
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					return new _taskBorrarAlias();
				}
			};
		}
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void goCargar() {
		frame.getJProgressBar().setIndeterminate(true);
		this.createTimer();
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskCargar();
				}
			};
		}
		if (Timer != null) {
			Timer.start();
		}
		worker.start();

	}

	public void goGuardar() {
		this.createTimer();
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskGuardar();
				}
			};
		}
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
		
	}

	class _taskBorrarAlias {
		_taskBorrarAlias() {
			_taskworkBorrarAlias();
		}
	}
	
	/**
	 * Clase utilizada en el swingworker 
	 * para completar los alias
	 */
	class _taskAutoCompletarAlias {
		_taskAutoCompletarAlias() {
			_taskworkAutoCompletarAlias();
		}
	}

	class _taskCargar {
		_taskCargar() {
			_taskworkCargar();
		}
	}

	class _taskGuardar {
		_taskGuardar() {
			_taskworkGuardar();
		}
	}

	private int _preguntar_si_omite_error_en_formacion_de_alias(
			String idarticulo) {
		final String _idarticulo = idarticulo;
		final int[] answer = new int[1];
		Runnable _execute = new Runnable() {
			public void run() {
				String[] options = { "OK", "Omitir errores de este tipo" };
				answer[0] = preguntar("Error de Formacion",
						"Faltan en formacion de alias para el articulo  "
								+ _idarticulo + "!", options, options[0]);
			}
		};
		this.invokeAndWait(_execute);
		return answer[0];
	}

	private void _aviso_operacion_completa(String operacion) {
		aviso("Operacion " + operacion + " Completa con " + current
				+ " operaciones y " + errors + " errores");
		
	}

	private void _aviso_operacion_cancelada(String operacion) {
		error("Operacion " + operacion + " Cancelada con " + current
				+ " operaciones y " + errors + " errores");
	}

	private void _aviso_requiere_seleccion() {
		aviso("Debe seleccionar Los Items que desea procesar");
		
	}

	/**
	 * Metodo para autocompletar los valores de alias 
	 * de los articulos que no esten completos que se ejecuta en un hilo diferente
	 * utilizando Swingworker  
	 */
	public void _taskworkAutoCompletarAlias() {
		boolean omitir_errores_formacion = false;
		lenght = this.getSelected();
		current = 0;
		if (frame.getJTable() != null) {
			int i = 0;
			while (i < frame.getJTable().getRowCount() & !canceled) {
				boolean b = false;
				String idarticulo = "";
				String idcodigo = "";
				String linea = "";
				String lineaproveedor = "";
				String idproveedor = "";
				//factor de multiplicacion entre el valor de un producto de proveedor y uno nuestro
				double factor = 1.0;
				try {
					b = (Boolean) frame.getJTable().getValueAt(i, 0);
				} catch (Exception e) {

				}
				if (b) {
					current++;
					try {
						idarticulo = (String) frame.getJTable()
								.getValueAt(i, 1);
					} catch (Exception e) {

					}
					try {
						idcodigo = (String) frame.getJTable().getValueAt(i, 2);
					} catch (Exception e) {

					}
					try {
						factor = (Double) frame.getJTable().getValueAt(i, 5);
					} catch (Exception e) {

					}
					try {
						linea = (String) frame.getJTable().getValueAt(i, 7);
					} catch (Exception e) {

					}

					try {
						idproveedor = (String) frame.getJTable().getValueAt(i,
								8);
					} catch (Exception e) {

					}
					try {
						lineaproveedor = (String) frame.getJTable().getValueAt(
								i, 9);
					} catch (Exception e) {

					}
					if (idproveedor.compareTo("") == 0) {
						String _idarticulo = idarticulo.substring(0, 5) + "%";
						idproveedor = data.getDefaultProveedor(_idarticulo);
						if (idproveedor.compareTo("") != 0) {
							frame.getJTable().setValueAt(idproveedor, i, 8);
						}
					}
					if (lineaproveedor.compareTo("") == 0) {
						String _idarticulo = idarticulo.substring(0, 5) + "%";
						lineaproveedor = data.getDefaultLinea(_idarticulo,
								idproveedor);
						if (lineaproveedor.compareTo("") != 0) {
							frame.getJTable().setValueAt(lineaproveedor, i, 9);
						}
					}
					String q = "";

					estado = "Buscando Alias para " + idarticulo;

					if (data.check_linea(lineaproveedor, idproveedor)) {
						final int row = i;
						this.autocompletar_row(i);
					} else {
						if (!omitir_errores_formacion) {
							int n = this
									._preguntar_si_omite_error_en_formacion_de_alias(idarticulo);
							if (n == 1) {
								omitir_errores_formacion = true;
							}
						}
						errors++;
					}
				}
				i++;
			}

		}
		if (this.getSelected() > 0) {
			if (!canceled) {
				this._aviso_operacion_completa("AutoCompletar");
			} else {
				this._aviso_operacion_cancelada("AutoCompletar");
			}
		} else {
			this._aviso_requiere_seleccion();
		}
		done = true;
	}

	public void _taskworkBorrarAliasSwing(int row) {
		final int _row = row;
		Runnable _execute = new Runnable() {
			public void run() {
				frame.getJTable().setValueAt("", _row, 2);
				frame.getJTable().setValueAt("", _row, 4);
				frame.getJTable().setValueAt(0.0, _row, 5);
				frame.getJTable().setValueAt("", _row, 9);
				frame.getJTable().setValueAt("", _row, 10);
				frame.getJTable().setValueAt("", _row, 11);
			}
		};
		this.invokeLater(_execute);
	}

	public void _taskworkCargar() {
		estado = "Cargando Datos";
		frame.getJProgressBar().setIndeterminate(true);
		String idproveedor = frame.get_txt_idproveedor().getText();
		String desde = frame.get_txt_idarticulo_desde().getText();
		String hasta = frame.get_txt_idarticulo_hasta().getText();
		Object[][] results = null;
		if (frame.get_lst_modo().getSelectedIndex() == 0) {
			results = data.getArticulos(idproveedor, desde, hasta, frame
					.get_chk_stock().isSelected());
			;
		} else {
			results = data.getAlias(idproveedor, desde, hasta);
		}
		final Object[][] _results = results;
		Runnable _execute = new Runnable() {
			public void run() {
				_taskworkCargarSwing(_results);
			}
		};
		this.invokeLater(_execute);
		done = true;
	}

	public void buscar_articulo_desde() {
		this.BuscarArticulo(frame.get_txt_idarticulo_desde());
	}

	public void buscar_articulo_hasta() {
		this.BuscarArticulo(frame.get_txt_idarticulo_hasta());
	}

	public void seleccionar(int col) {

		if (frame.getJTable().getSelectedColumn() == col) {
			frame.getJTable().clearSelection();
			frame.getJTable().setColumnSelectionInterval(col, col);
			int row = 0;
			boolean toggle = false;
			boolean extend = false;
			frame.getJTable().changeSelection(row, col, toggle, extend);
			row = frame.getJTable().getRowCount();
			toggle = false;
			extend = true;
			frame.getJTable().changeSelection(row, col, toggle, extend);
		}
	}

	private CustomTable crearTablaDeItems(boolean editable) {
		CustomTable Table = new CustomTable();
		Column col = new Column();

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setTipo(Double.class);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		col.setEditable(true);
		Table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(120);
		col.setEditable(editable);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());

		Table.addColumn(col);

		col = new Column();
		col.setName("Codigo");
		col.setWidth(110);
		col.setEditable(true);
		CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_codigo);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(240);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);

		col = new Column();
		col.setName("Lista");
		col.setWidth(70);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Factor");
		col.setWidth(70);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);

		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Precio5");
		col.setWidth(70);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Linea");
		col.setWidth(120);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);

		col = new Column();
		col.setName("idproveedor");
		col.setWidth(100);
		col.setEditable(true);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_proveedor);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);

		col = new Column();
		col.setName("LineaProveedor");
		col.setWidth(140);
		col.setEditable(true);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_lineaproveedor);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		// col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);

		col = new Column();
		col.setName("Aliases");
		col.setWidth(70);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);

		col = new Column();
		col.setName("Actualizado");
		col.setWidth(160);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		// col.setCellRenderer(new TableItemColorCellRenderer());
		col.setClass(Double.class);
		Table.addColumn(col);
		return Table;
	}

	public void create_table(Object[][] results) {
		if (results != null) {
			results = this.procesar_items_linea(results);
			CustomTable Table = this.crearTablaDeItems(false);
			Table.setData(results);
			Table.build();
			Table.fillData();
			JTable _table = Table.getTable();
			_table.setName(_Interface._table);
			_table.addMouseListener(this.getConstructor().getMouseListener());
			_table.addKeyListener(this.getConstructor().getKeyListener());
			_table.getTableHeader().addMouseListener(
					this.getConstructor().getMouseListener());
			frame.setJTable(_table);
		} else {
			frame.setJTable(null);
		}
	}

	public void _taskworkCargarSwing(Object[][] results) {
		create_table(results);

	}

	public void copiar() {
		// System.out.println("cOPIAR A MEMORIA");
		if (frame.getJTable() != null) {
			int[] rows = frame.getJTable().getSelectedRows();
			int[] columns = frame.getJTable().getSelectedColumns();
			memoria = new Object[rows.length][columns.length];
			for (int i = 0; i < rows.length; i++) {
				for (int j = 0; j < columns.length; j++) {
					memoria[i][j] = frame.getJTable().getValueAt(rows[i],columns[j]);
				}
			}
			try {	} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deseleccionar() {

		frame.getJTable().clearSelection();
		frame.getJTable().transferFocus();
	}

	public void seleccionar(boolean b) {

		JTable table = frame.getJTable();
		int[] rows = table.getSelectedRows();
		if (rows.length > 0) {
			for (int row = 0; row < rows.length; row++) {
				table.setValueAt(b, rows[row], 0);
			}
		} else {
			for (int row = 0; row < table.getRowCount(); row++) {
				table.setValueAt(b, row, 0);
			}
		}
	}

	public void pegar() {
		if (frame.getJTable() != null) {
			int[] rows = frame.getJTable().getSelectedRows();
			int[] columns = frame.getJTable().getSelectedColumns();

			if (memoria != null) {
				if (memoria.length == rows.length
						& memoria[0].length == columns.length) {
					try {

						for (int i = 0; i < memoria.length; i++) {
							for (int j = 0; j < memoria[0].length; j++) {
								frame.getJTable().setValueAt(memoria[i][j],
										rows[i], columns[j]);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	public void complete_alias_row(String code, String linea,
			String idproveedor, int row) {
		String aux = "";
		Object[][] results = data.getCode(code, linea, idproveedor);
		if (results != null) {
			if (results.length > 0) {
				String codex = (String) results[0][0];
				String p5 = (String) results[0][1];
				String alias = (String) results[0][2];
				double p5x = 0.0;
				double aliasx = 0.0;
				try {
					p5x = new Double(p5);
				} catch (Exception e) {

				}
				try {
					aliasx = new Double(alias);
				} catch (Exception e) {

				}
				frame.getJTable().setValueAt(p5x, row, 4);
				frame.getJTable().setValueAt(aliasx, row, 5);
			}
		}
	}

	public void completar() {

		int[] columns = frame.getJTable().getSelectedColumns();

		for (int cx = 0; cx < columns.length; cx++) {
			int col = columns[cx];
			if (col == 5 | col == 8 | col == 9 | col == 0) {

				if (frame.getJTable() != null) {

					if (col == 0){
						boolean val = (Boolean) frame.getJTable().getValueAt(
								frame.getJTable().getSelectedRows()[0], col);
						int[] indexes = frame.getJTable().getSelectedRows();
						for (int i = 1; i < indexes.length; i++) {
							try {
								frame.getJTable().setValueAt(val, indexes[i],col);
								}
							catch (Exception e) {
								
								}
							}
					}
					if (col == 5) {
						Double val = (Double) frame.getJTable().getValueAt(
								frame.getJTable().getSelectedRows()[0], col);

						int[] indexes = frame.getJTable().getSelectedRows();
						for (int i = 1; i < indexes.length; i++) {
							try {
								frame.getJTable().setValueAt(val, indexes[i],
										col);
							} catch (Exception e) {

							}
						}
					}
					if (col == 8 | col == 9) {
						String val = (String) frame.getJTable().getValueAt(
								frame.getJTable().getSelectedRows()[0], col);

						int[] indexes = frame.getJTable().getSelectedRows();
						for (int i = 1; i < indexes.length; i++) {
							try {
								frame.getJTable().setValueAt(val, indexes[i],
										col);
							} catch (Exception e) {

							}
						}
					}

				}
			} else {
				aviso("No esta permitido completar en esta columna!");
			}
		}

	}

	public void complete_prefijo() {
		String prefijo = frame.get_txt_prefijo().getText();
		frame.get_txt_idarticulo_desde().setText(prefijo + "-0000");
		frame.get_txt_idarticulo_hasta().setText(prefijo + "-zzzzz");
	}

	public void setLinea(String linea, String prefijo) {
		frame.get_txt_linea().setText(linea);
		frame.get_txt_prefijo().setText(prefijo);
		complete_prefijo();
	}

	public void fillLinea(String prefijo) {
		Object[][] results = data.getLinea(prefijo);
		if (results != null) {
			if (results.length > 0) {
				frame.get_txt_linea().setText((String) results[0][0]);
			}
		}
	}

	private Object[][] procesar_items_linea(Object[][] aux) {
		double sum_linea = 0.0;
		double sum_linea_items = 0.0;
		// String linea=this._pedidoproveedor.get_txt_linea().getText();

		Object[][] tmp = new Object[aux.length][12];
		// _alias.getPBar().setMaximum(aux.length);
		// _pedidoproveedor.getPBar().setStringPainted(true);
		// boolean existe = this.pedido_existe();
		for (int i = 0; i < aux.length; i++) {
			String art = (String) aux[i][0];
			String cod = (String) aux[i][1];
			String desc = (String) aux[i][2];
			String lista = (String) aux[i][3];
			String factor = (String) aux[i][4];
			String precio5 = (String) aux[i][5];
			String linea = (String) aux[i][6];
			String idproveedor = (String) aux[i][7];
			String lineaproveedor = (String) aux[i][8];
			String alias = "0.0";// (String) aux[i][9];
			String update = (String) aux[i][9];

			double list = 0.0;
			double kfactor = 0.0;
			double p5 = 0.0;
			double qty = 0.0;

			boolean b = false;

			try {
				list = new Double(lista.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() stock> "
						+ e.getMessage());
			}
			try {
				kfactor = new Double(factor.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() stock> "
						+ e.getMessage());
			}

			try {
				qty = new Double(alias.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() qty> "
						+ e.getMessage());
			}

			try {
				p5 = new Double(precio5.replace(",", ""));
			} catch (Exception e) {
				System.out.println("Error. load_items_values() minimo> "
						+ e.getMessage());
			}

			if (b) {
				tmp[i][0] = "1";
			} else {
				tmp[i][0] = "0";
			}

			tmp[i][1] = art;
			tmp[i][2] = desc;
			tmp[i][3] = cod;
			tmp[i][4] = list;
			tmp[i][5] = kfactor;
			tmp[i][6] = p5;
			tmp[i][7] = linea;
			tmp[i][8] = idproveedor;
			tmp[i][9] = lineaproveedor;
			tmp[i][10] = qty;
			tmp[i][11] = update;

		}

		return tmp;
	}

	public void evaluar_articulo_desde(JTextField tx) {
		String value = tx.getText();
		if (value.compareTo("") != 0) {
			frame.get_txt_idarticulo_hasta().requestFocusInWindow();
		} else {
			aviso("Debe completar este campo para poder cargar los Alias");
		}
	}

	public void evaluar_articulo_hasta(JTextField tx) {
		String value = tx.getText();
		if (value.compareTo("") != 0) {
			this.goCargar();
		} else {
			aviso("Debe completar este campo para poder cargar los Alias");
		}
	}

	public void buscarLinea(JTextField tx, String idproveedor) {

		if (vSelector != null) {
			vSelector.dispose();
		}
		vSelector = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
		vSelector.setParameter(
				aplicacion.modelo.interfaces._parametros.connector, data
						.getConnectionHandler());
		vSelector.build(this.getConstructor());
		vSelector.init();
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) vSelector
				.getLogic();
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("lineaproveedor");
		c.setAlias("Linea");
		c.setColumnField(tx);
		c.setWidth(120);
		logic.addColumn(c);

		logic.setFromTable("b_codigos ");
		logic.setRestriction("idproveedor like '" + idproveedor + "'");

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("lineaproveedor");
		f.setValor(tx.getText());
		logic.addFilter(f);

		logic.setTop(100);
		logic.setGroupby("lineaproveedor");
		logic.setOrderby("lineaproveedor");

		int x = frame.getLocation().x;
		int y = frame.getLocation().x;

		logic.setCaller(tx);
		int n = logic._loadoptions();
		if (n == 0) {
			aviso("no hay cuentas con esa descripcion");
		}
	}

	public void BuscarLinea(JTextField tx, int row) {

		CustomCellEditor cell = (CustomCellEditor) frame.getJTable()
				.getCellEditor(row, 8);
		JTextField idproveedor = (JTextField) cell.getComponent();
		if (bLinea == null) {
			bLinea = new aplicacion.herramientas.java.buscadores.Linea(this
					.getConstructor());
		}

		bLinea.setIdproveedor(idproveedor);

		boolean ok = data.check_proveedor(idproveedor.getText())
				& idproveedor.getText().compareTo("") != 0;
		if (ok) {
			bLinea.BuscarLineaProveedor(tx);
		} else {
			aviso("Para buscar una linea en esta fila, debe ingresar un proveedor valido primero!");
		}
	}

	public void clean() {
		frame.get_txt_idarticulo_desde().setText("");
		frame.get_txt_idarticulo_hasta().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_proveedor_descripcion().setText("");
		frame.get_txt_prefijo().setText("");
		frame.get_txt_prefijo_codigo().setText("");
		frame.get_txt_art_desde().setText("3");
		frame.get_txt_art_qty().setText("");
		frame.get_txt_linea().setText("");
		frame.setJTable(null);

	}

	public void BuscarLinea() {
		this.BuscarLinea(frame.get_txt_linea());
	}

	public void BuscarLinea(JTextField tx) {
		if (bLinea == null) {
			bLinea = new aplicacion.herramientas.java.buscadores.Linea(this
					.getConstructor());
		}

		bLinea.setIdproveedor(frame.get_txt_idproveedor());
		bLinea.BuscarLinea(tx);

	}
	
 	private aplicacion.herramientas.java.evaluadores.Codigo Codigo=null;
	public void initialize_Codigo(){
		Codigo=new aplicacion.herramientas.java.evaluadores.Codigo(){
			public void cargar(JTextField tx){
				
				
			}
		};
		Codigo.setConstructor(this.getConstructor());
	}
	public void BuscarCodigo(JTextField tx){
		Codigo.Buscar(tx);
	}
	public void BuscarCodigo(){
	//	Codigo.Buscar(frame.get_txt_idCodigo());
	}
	public void buscarCodigo(JTextField tx){
		Codigo.buscar(tx);
	}
	
	public void evaluarCodigo(JTextField tx){
		Codigo.evaluate(tx);
	}
	
	public void evalcode(JTextField tx, JTable table,int row, int col) {
		String linea = "";
		try {
			linea = (String) frame.getJTable().getValueAt(row, 9);
		} catch (Exception e) {

		}
		String idprov = "";
		try {
			idprov = (String) frame.getJTable().getValueAt(row, 8);
		} catch (Exception e) {

		}
		
		String aux = "";

		if(tx.getText().compareTo("")!=0){
			if (linea.compareTo("") != 0 & idprov.compareTo("") != 0) {
				String code = tx.getText();
				while (code.substring(1).compareTo(" ") == 0) {
					code = code.substring(1, code.length());
				}
				boolean ok = data.checkcode(code, linea, idprov);
				if (ok) {
					this.complete_alias_row(code, linea, idprov, row);
					frame.getJTable().changeSelection(row+1, col , false, false);
					frame.getJTable().editCellAt(row+1, col );
					frame.getJTable().transferFocus();
				} else {
					aviso("El codigo " + tx.getText() + " no existe.");
					frame.getJTable().setValueAt(aux, row, col);
					frame.getJTable().changeSelection(row, col, false, false);
					frame.getJTable().editCellAt(row, col);
					frame.getJTable().transferFocus();
				}
				JTextField _txt_linea=new JTextField();
				_txt_linea.setText(linea);
				JTextField _txt_idproveedor=new JTextField();
				_txt_idproveedor.setText(idprov);
				
				

			}else{
				this.buscarCodigo(tx, table, col, row);
			}
		}
		else{
			this.buscarCodigo(tx, table, col, row);
		}
			
	}
	
	private aplicacion.herramientas.java.visualizadores.Codigo vCodigo=null;
	public void buscarCodigo(JTextField tx,JTable table,int col,int row) {
		if (vCodigo!=null){
			vCodigo.close();
		}
		vCodigo=new aplicacion.herramientas.java.visualizadores.Codigo(this.getConstructor());
		int colproveedor=8;
		int collinea=9;
		String idarticulo=table.getValueAt(row, 1).toString();
		
		String codigo="";
		try {
			codigo = this.getCodeClean(idarticulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("getCodeClean > "+codigo);
		String idproveedor =table.getValueAt(row, colproveedor).toString();
		if (idproveedor.compareTo("")==0){
			idproveedor=data.getDefaultProveedor(idarticulo);
		}
		String linea =table.getValueAt(row, collinea).toString();
		if (linea.compareTo("")==0){
			linea = data.getDefaultLinea(idarticulo, idproveedor);
		}
		
		tx.setText(codigo);
		int n = vCodigo.Buscar(tx, table, col, row, colproveedor, collinea);
		
		if (n == 0) {
			tx.setText("");
				aviso("no hay Productos con ese codigo");
		}
		
	}
	public void BuscarAlias(JTextField txt, int row) {

		String idproveedor = "";
		String idarticulo = "";
		String lineaproveedor = "";
		try {
			idproveedor = (String) frame.getJTable().getValueAt(row, 8);
		} catch (Exception e) {

		}
		try {
			lineaproveedor = (String) frame.getJTable().getValueAt(row, 9);
		} catch (Exception e) {

		}
		try {
			idarticulo = (String) frame.getJTable().getValueAt(row, 1);
		} catch (Exception e) {

		}

		idarticulo = idarticulo.substring(4, idarticulo.length());

		aplicacion.herramientas.java.sortableselector.constructor._Constructor CC = new aplicacion.herramientas.java.sortableselector.constructor._Constructor();
		CC.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) CC
				.getLogic();

		columna c = new columna();
		Filtro f = new Filtro();
		c = new columna();
		c.setNombre("c.idcodigo");
		c.setAlias("c.idcodigo");

		c.setColumnField(txt);
		// c.setCell(_alias.getJTable(), row, 2);
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.descripcion");
		c.setAlias("c.descripcion");
		c.setWidth(250);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.precio5");
		c.setAlias("c.precio5");
		c.setWidth(70);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.ultimo_upd");
		c.setAlias("c.ultimo_upd");
		c.setWidth(250);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.lineaproveedor");
		c.setAlias("c.lineaproveedor");
		c.setWidth(150);
		c.setJTable(frame.getJTable());
		c.setColumn(9);
		c.setRow(row);

		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.idproveedor");
		c.setAlias("c.idproveedor");
		c.setWidth(120);

		c.setJTable(frame.getJTable());
		c.setColumn(8);
		c.setRow(row);

		logic.addColumn(c);

		c = new columna();
		c.setNombre("m.descripcion");
		c.setAlias("m.descripcion");
		c.setWidth(180);
		logic.addColumn(c);

		logic
				.addFromTable("b_codigos c left outer join ma_cuentas m on c.idproveedor=m.codigo ");
		f = new Filtro();
		f.setNombre("c.idcodigo");
		f.setAlias("c.idcodigo");
		f.setWidth(120);
		f.setFocus(true);
		if (idarticulo.compareTo("") != 0
				& frame.get_chk_precargar_codigos().isSelected()) {
			f.setInitialValue(idarticulo);
		}
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("c.descripcion");
		f.setAlias("c.descripcion");
		f.setWidth(250);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("c.lineaproveedor");
		f.setAlias("c.lineaproveedor");
		if (lineaproveedor.compareTo("") != 0
				& frame.get_chk_precargar_codigos().isSelected()) {
			f.setInitialValue(lineaproveedor);
		}
		f.setWidth(180);
		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("c.idproveedor");
		f.setAlias("c.idproveedor");
		if (idproveedor.compareTo("") != 0
				& frame.get_chk_precargar_codigos().isSelected()) {
			f.setInitialValue(idproveedor);
		}
		f.setWidth(120);
		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("m.descripcion");
		f.setAlias("m.descripcion");
		f.setWidth(120);
		logic.addFilter(f);
		logic.setTop(100);
		logic.addOrder("c.idcodigo,c.lineaproveedor");
		logic.init();
	}

	public void BuscarArticulo(JTextField ext) {
		if (bArticulo == null) {
			bArticulo = new aplicacion.herramientas.java.buscadores.Articulo(
					this.getConstructor());
		}

		bArticulo.Buscar(ext);
	}

	public void buscarArticulo(JTextField tx) {
		if (vArticulo != null) {
			vArticulo.close();
		}
		vArticulo = new aplicacion.herramientas.java.visualizadores.Articulo(
				this.getConstructor());
		vArticulo.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vArticulo.Buscar(tx);
		if (n == 0) {
			vArticulo.close();
			aviso("no hay articulos con ese codigo");
		}
	}

	public void cargarProveedor(String idcliente) {

		Object[][] results = data.getClientInformation(idcliente);
		if (results != null) {
			if (results.length > 0) {
				String desc = results[0][1].toString();
				frame.get_txt_proveedor_descripcion().setText(desc);
				frame.get_txt_linea().requestFocusInWindow();
			} else {
				aviso("El Codigo de Cuenta es Inexistente. Utilice F5 para Buscar");
				frame.get_txt_idproveedor().requestFocusInWindow();
			}
		}
	}

	public void eval_linea(JTextField tx) {
		String idproveedor = "";
		idproveedor = (String) frame.get_txt_idproveedor().getText();

		String lineaproveedor = tx.getText();
		if(lineaproveedor.compareTo("")!=0){
			if (!data.check_linea(lineaproveedor, idproveedor)) {
				this.buscarLinea(tx);
			} else {
				this.cargar_prefijos(lineaproveedor);
				frame.get_txt_idarticulo_hasta().requestFocusInWindow();
			}
		}else{
			this.buscarLinea(tx);
		}
	}

	public void focus() {
		frame.get_txt_idproveedor().requestFocusInWindow();
	}

	public void evaluar_table_linea(JTextField tx, int row) {
		String idproveedor = frame.getJTable().getValueAt(row, 8).toString();
		String lineaproveedor = tx.getText();
		if (data.check_linea(lineaproveedor, idproveedor)) {
			frame.getJTable().changeSelection(row, 10, false, false);
			frame.getJTable().editCellAt(row, 10);
			frame.getJTable().transferFocus();
		} else {
			this.buscarLinea(tx, idproveedor);
		}
	}

	public void evaluar_linea(JTextField tx) {
		String linea = tx.getText();
		if (linea.compareTo("") != 0) {
			if (data.check_linea(linea)) {
				cargar_prefijos(linea);
			} else {
				this.buscarLinea(tx);
			}
		} else {
			this.buscarLinea(tx);
		}
	}

	private aplicacion.herramientas.java.visualizadores.Linea vLinea = null;

	public void buscarLinea(JTextField tx) {
		if (vLinea != null) {
			vLinea.close();
		}
		vLinea = new aplicacion.herramientas.java.visualizadores.Linea(this
				.getConstructor());
		vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLinea(tx);
		if (n == 0) {
			aviso("no hay Lineas con ese codigo");
		}
	}

	public void cargar_prefijos(String linea) {
		Object[][] results = data.getLinePrefix(linea);
		if (results != null) {
			if (results.length > 0) {
				String prefix = (String) results[0][0];
				frame.get_txt_idarticulo_desde().setText(prefix + "-000");
				frame.get_txt_idarticulo_hasta().setText(prefix + "-zzz");
				frame.get_txt_idarticulo_hasta().requestFocusInWindow();
			}
		}
	}

	public void evaluar_table_proveedor(JTextField tx, int row) {
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
				if (data.check_proveedor(aux)) {
					frame.getJTable().changeSelection(row, 9, false, false);
					frame.getJTable().editCellAt(row, 9);
					frame.getJTable().transferFocus();
				} else {
					error("Proveedor " + aux + " no existe");
					tx.requestFocusInWindow();
				}
			} else {
				this.buscarProveedor(tx);
			}

		} else {
			aviso("Ingrese Codigo de Proveedor. utilice F5 para buscar");
		}
	}

	public void cancelar_tarea() {
		if (preguntar("confirmar", "Cancela Tarea?")) {
			this.canceled = true;
		}
	}

	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor = null;

	public void initialize_proveedor() {
		proveedor = new aplicacion.herramientas.java.evaluadores.Proveedor() {
			public void cargar(String codigo) {
				cargarProveedor(codigo);
			}
		};
		proveedor.setConstructor(this.getConstructor());
	}

	public void BuscarProveedor(JTextField tx) {
		proveedor.Buscar(tx);
	}

	public void buscarProveedor(JTextField tx) {
		proveedor.buscar(tx);
	}

	public void BuscarProveedor() {
		proveedor.Buscar(frame.get_txt_idproveedor());
	}

	public void evaluarProveedor(JTextField tx) {
		proveedor.evaluate(tx);
	}
	public String getCodeClean(String idarticulo){
		String codigo = idarticulo;

		codigo = codigo.replaceAll("/"," ");
//		codigo = codigo.replaceAll("."," ");
		codigo = codigo.replaceAll("-"," ");
		codigo = codigo.replaceAll(","," ");
		System.err.println("codigo: "+codigo);
		
		if (frame.get_chk_art_substring().isSelected()) {
			int i = 4;
			int j = codigo.length();
			try {
				i = new Integer(frame.get_txt_art_desde().getText());
			} catch (Exception e) {
				// System.out.println("exception posicion inicial filtro substring art "+e.getMessage());
			}
			try {
				if (frame.get_txt_art_qty().getText().compareTo("") != 0) {
					j = new Integer(frame.get_txt_art_desde().getText())
							+ new Integer(frame.get_txt_art_qty().getText())+1;
					if (j >= codigo.length())
						j = codigo.length();
				} else {
					j = codigo.length();
				}

			} catch (Exception e) {
				// System.out.println("exception cantidad de carcateres substring art "+e.getMessage());
			}
			codigo = codigo.substring(i, j);
		} else {
			codigo = codigo.substring(4, codigo.length());
		}

		if (frame.get_txt_precode().getText().compareTo("") != 0) {
			codigo = frame.get_txt_precode().getText() + codigo;
			codigo = codigo.replaceAll("/"," ");
			codigo = codigo.replaceAll("."," ");
			codigo = codigo.replaceAll("-"," ");
			codigo = codigo.replaceAll(","," ");
		}
		System.err.println("codigo after clean: "+codigo);
		return codigo;
	}
	
	public void autocompletar_row(int row) {

		String codigo = (String) frame.getJTable().getValueAt(row, 1);

		Convertidor Cv = new Convertidor();
		codigo = Cv.LimpiarString(codigo, " ");
		codigo = Cv.LimpiarString(codigo, "/");
		codigo = Cv.LimpiarString(codigo, ".");
		codigo = Cv.LimpiarString(codigo, ",");
		codigo = Cv.LimpiarString(codigo, "-");

		if (frame.get_chk_art_substring().isSelected()) {
			int i = 4;
			int j = codigo.length();
			try {
				i = new Integer(frame.get_txt_art_desde().getText());
			} catch (Exception e) {
				// System.out.println("exception posicion inicial filtro substring art "+e.getMessage());
			}
			try {
				if (frame.get_txt_art_qty().getText().compareTo("") != 0) {
					j = new Integer(frame.get_txt_art_desde().getText())
							+ new Integer(frame.get_txt_art_qty().getText());
					if (j >= codigo.length())
						j = codigo.length();
				} else {
					j = codigo.length();
				}

			} catch (Exception e) {
				// System.out.println("exception cantidad de carcateres substring art "+e.getMessage());
			}
			codigo = codigo.substring(i, j);
		} else {
			codigo = codigo.substring(4, codigo.length());
		}

		String idproveedor = (String) frame.getJTable().getValueAt(row, 8);
		String linea = (String) frame.getJTable().getValueAt(row, 9);
		if (frame.get_txt_precode().getText().compareTo("") != 0) {
			codigo = frame.get_txt_precode().getText() + codigo;
			codigo = Cv.LimpiarString(codigo, " ");
			codigo = Cv.LimpiarString(codigo, "/");
			codigo = Cv.LimpiarString(codigo, ".");
			codigo = Cv.LimpiarString(codigo, "-");
		}
		if (frame.get_txt_aftercode().getText().compareTo("") != 0) {
			codigo = codigo+frame.get_txt_aftercode().getText();
			codigo = Cv.LimpiarString(codigo, " ");
			codigo = Cv.LimpiarString(codigo, "/");
			codigo = Cv.LimpiarString(codigo, ".");
			codigo = Cv.LimpiarString(codigo, "-");
		}
		Object[][] results = data.getAliasCode(codigo, idproveedor, linea,
				true, " /.-", true);
		if (results != null) {
			if (results.length > 0) {
				double factor = 1.0;

				String _codigo = (String) results[0][0];
				String _linea = (String) results[0][1];
				String _lista = (String) results[0][2];
				double list = 0.0;
				try {
					list = new Double(_lista);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String _actualizacion = (String) results[0][3];
				this.complete(row, _codigo, _linea, list, idproveedor, factor,
						_actualizacion);

			}
		}

	}

	public void complete(int row, String code, String linea, double lista,
			String idproveedor, double factor, String actualizacion) {

		final int _row = row;
		final String _code = code;
		final String _idproveedor = idproveedor;
		final String _lineaproveedor = linea;
		final double _factor = factor;
		final double _lista = lista;
		final String _actualizacion = actualizacion;
		Runnable _execute = new Runnable() {
			public void run() {
				frame.getJTable().setValueAt(_code, _row, 2);
				frame.getJTable().setValueAt(_lista, _row, 4);
				frame.getJTable().setValueAt(_factor, _row, 5);
				frame.getJTable().setValueAt(_lista, _row, 4);
				frame.getJTable().setValueAt(_idproveedor, _row, 8);
				frame.getJTable().setValueAt(_lineaproveedor, _row, 9);
				frame.getJTable().setValueAt(_actualizacion, _row, 11);

			}
		};
		javax.swing.SwingUtilities.invokeLater(_execute);

	}

	public int getSelected() {
		int max = 0;

		if (frame.getJTable() != null) {
			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
				boolean b = false;
				try {
					b = (Boolean) frame.getJTable().getValueAt(i, 0);
				} catch (Exception e) {

				}
				if (b)
					max++;
			}
		}
		return max;
	}

	private int _preguntar_si_modifica_alias(String idarticulo) {
		final String _idarticulo = idarticulo;
		final int[] answer = new int[1];
		Runnable _execute = new Runnable() {
			public void run() {
				String[] options = { "Si", "Si a todo", "No", "No a todo" };
				answer[0] = preguntar("Confirmar",
						"Modifica el alias del articulo " + _idarticulo + " ?",
						options, options[0]);
			}
		};
		this.invokeAndWait(_execute);
		return answer[0];
	}

	private int _preguntar_si_borra_alias(String idarticulo) {
		final String _idarticulo = idarticulo;
		final int[] answer = new int[1];
		Runnable _execute = new Runnable() {
			public void run() {
				String[] options = { "Si", "Si a todo", "No", "No a todo" };
				answer[0] = preguntar("Confirmar",
						"Borra el alias del articulo " + _idarticulo + " ?",
						options, options[2]);
			}
		};
		this.invokeAndWait(_execute);
		return answer[0];
	}

	public void _taskworkGuardar() {
		// recorro renglones
		// si tiene todos los campos de un alias analizo si reemplaza algun
		// alias
		// pregunto si modifica el alias; si quiere modificar todos o ninguno
		boolean si_a_todo = false;
		boolean no_a_todo = false;
		boolean omitir_errores = false;
		boolean omitir_errores_formacion = false;

		this.lenght = this.getSelected();
		current = 0;

		if (frame.getJTable() != null) {
			int i = 0;
			while (i < frame.getJTable().getRowCount() & !canceled) {
				boolean b = false;
				String idarticulo = "";
				String idcodigo = "";
				String linea = "";
				String lineaproveedor = "";
				String idproveedor = "";
				double factor = 1.0;
				try {
					b = (Boolean) frame.getJTable().getValueAt(i, 0);
				} catch (Exception e) {

				}

				String q = "";

				if (b) {
					estado = "Guardando " + idarticulo;
					current++;
					try {
						idarticulo = (String) frame.getJTable().getValueAt(i, 1);
					} catch (Exception e) {

					}
					try {
						idcodigo = (String) frame.getJTable().getValueAt(i, 2);
					} catch (Exception e) {

					}
					try {
						factor = (Double) frame.getJTable().getValueAt(i, 5);
					} catch (Exception e) {

					}
					try {
						linea = (String) frame.getJTable().getValueAt(i, 7);
					} catch (Exception e) {

					}
					try {
						idproveedor = (String) frame.getJTable().getValueAt(i,
								8);
					} catch (Exception e) {

					}
					try {
						lineaproveedor = (String) frame.getJTable().getValueAt(
								i, 9);
					} catch (Exception e) {

					}

					boolean check = (idcodigo.compareTo("") != 0
							& lineaproveedor.compareTo("") != 0 & idproveedor
							.compareTo("") != 0);
					if (check) {

						if (data.check_alias_existence(idarticulo, idcodigo,
								lineaproveedor, idproveedor, factor)) {
							if (data.check_alias_difference(idarticulo,
									idcodigo, lineaproveedor, idproveedor,
									factor)) {
								if (si_a_todo) {
									q = data.getActualizarAlias(idarticulo,
											idcodigo, lineaproveedor,
											idproveedor, factor);
								} else {
									if (!no_a_todo) {
										int n = this._preguntar_si_modifica_alias(idarticulo);
										if (n == 0 | n == 1) {
											q = data.getActualizarAlias(
													idarticulo, idcodigo,
													lineaproveedor,
													idproveedor, factor);
											if (n == 1) {
												si_a_todo = true;
											}
										} else {
											if (n == 3) {
												no_a_todo = true;
											}
										}
									}
								}
							} else {
								// por las dudas grabo! por si le modificaron el
								// factor

								q = data.getInsertarAlias(idarticulo, idcodigo,
										lineaproveedor, idproveedor,
										lineaproveedor, factor);
							}
						} else {
							q = data.getInsertarAlias(idarticulo, idcodigo,
									lineaproveedor, idproveedor,
									lineaproveedor, factor);
						}
					} else {
						this.errors++;
						if (!omitir_errores_formacion) {
							int n = this
									._preguntar_si_omite_error_en_formacion_de_alias(idarticulo);
							if (n == 1) {
								omitir_errores_formacion = true;
							}
						}

					}

				}

				if (q.compareTo("") != 0) {

					if (data.check_code(idcodigo, lineaproveedor, idproveedor)) {
						data.clearBatch();
						data.addBatch(q);
						boolean error = data.executeBatch();
						if (error) {
							errors++;
						}
					} else {

						if (!omitir_errores_formacion) {
							int n = this
									._preguntar_si_omite_error_en_formacion_de_alias(idarticulo);
							if (n == 1) {
								omitir_errores_formacion = true;
							}
						}

						errors++;
					}

				}
				i++;
			}
		}
		if (this.getSelected() > 0) {
			if (!canceled) {
				this._aviso_operacion_completa("Guardar");
			} else {
				this._aviso_operacion_cancelada("Guardar");
			}
		} else {
			this._aviso_requiere_seleccion();
		}
		done = true;

	}

	public void _taskworkBorrarAlias() {

		boolean si_a_todo = false;
		boolean no_a_todo = false;
		current = 0;
		lenght = this.getSelected();

		int qty = 1;
		if (frame.getJTable() != null) {

			int i = 0;
			while (i < frame.getJTable().getRowCount() & !canceled) {
				boolean b = false;
				String idarticulo = "";
				String idcodigo = "";
				String lineaproveedor = "";
				String idproveedor = "";
				double factor = 1.0;
				try {
					b = (Boolean) frame.getJTable().getValueAt(i, 0);
				} catch (Exception e) {

				}
				try {
					idarticulo = (String) frame.getJTable().getValueAt(i, 1);
				} catch (Exception e) {

				}
				try {
					idcodigo = (String) frame.getJTable().getValueAt(i, 2);
				} catch (Exception e) {

				}
				try {
					factor = (Double) frame.getJTable().getValueAt(i, 5);
				} catch (Exception e) {

				}
				try {
					idproveedor = (String) frame.getJTable().getValueAt(i, 8);
				} catch (Exception e) {

				}
				try {
					lineaproveedor = (String) frame.getJTable()
							.getValueAt(i, 9);
				} catch (Exception e) {

				}
				String q = "";
				if (b) {
					estado = "Borrando Alias " + idarticulo;
					qty++;
					current++;
					if (data.check_alias_existence(idarticulo, idcodigo,
							lineaproveedor, idproveedor, factor)) {

						if (si_a_todo) {
							q = data.getBorrarAlias(idarticulo, idcodigo,
									lineaproveedor, idproveedor, factor);
							_taskworkBorrarAliasSwing(i);
						} else {
							if (!no_a_todo) {
								int n = this._preguntar_si_borra_alias(idarticulo);
								if (n == 0 | n == 1) {
									q = data.getBorrarAlias(idarticulo,
											idcodigo, lineaproveedor,
											idproveedor, factor);
									_taskworkBorrarAliasSwing(i);
									if (n == 1) {
										si_a_todo = true;
									}
								} else {
									if (n == 3) {
										no_a_todo = true;
									}
								}
							}
						}

					}
					if (q.compareTo("") != 0) {
						data.clearBatch();
						data.addBatch(q);

						boolean error = data.executeBatch();
						if (error) {
							errors++;
						}
					}
				}
				i++;
			}
		}
		if (this.getSelected() > 0) {
			if (!canceled) {
				this._aviso_operacion_completa("Borrar");
			} else {
				this._aviso_operacion_cancelada("Borrar");
			}
		} else {
			this._aviso_requiere_seleccion();
		}
		done = true;
	}

	public void createTimer() {
		crono = new Crono();
		crono.start();
		lenght = 0;
		current = 0;
		errors = 0;
		done = false;
		canceled = false;
		Timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done | canceled) {
					endbar();
					Timer.stop();
				} else {

					updateBar();
				}
			}
		});

	}

	public void updateBar() {
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}

	public void endbar() {
		estado = "";
		frame.getJProgressBar().setString(estado);
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
		frame.get_btn_completar().setEnabled(true);
		frame.get_btn_cargar().setEnabled(true);
		frame.get_btn_guardar().setEnabled(true);
	}

	public void cargar(String idarticulo_desde, String idarticulo_hasta,
			String idproveedor) {
		frame.get_txt_idarticulo_desde().setText(idarticulo_desde);
		frame.get_txt_idarticulo_hasta().setText(idarticulo_hasta);
		frame.get_txt_idproveedor().setText(idproveedor);
		this.goCargar();
	}
	public void focus(JTable table, int row, int col) {
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
	}

}
