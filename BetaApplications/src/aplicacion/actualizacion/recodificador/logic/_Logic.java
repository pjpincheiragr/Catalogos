package aplicacion.actualizacion.recodificador.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import aplicacion.actualizacion.recodificador.gui._Frame;
import aplicacion.actualizacion.recodificador.logic._Data;
import aplicacion.actualizacion.recodificador.interfaces._Interface;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;

public class _Logic extends Logic {
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

	private Timer TimerP = null;
	private boolean done = false;
	private boolean cancel = false;
	private int errors = 0;
	private int operations = 0;
	private int max_operations = 0;

	private class recodificarArticulos {
		recodificarArticulos() {
			Runnable _execute = new Runnable() {
				public void run() {
					create_timer();
					recodificar();
				}
			};
			javax.swing.SwingUtilities.invokeLater(_execute);

		}
	}

	public void _recodificar() {
		SwingWorker worker = null;
		operations = 0;
		max_operations = 0;
		errors = 0;
		if (worker == null) {
			worker = new SwingWorker() {
				@Override
				public Object construct() {

					done = false;
					return new recodificarArticulos();
				}
			};
		}
		done = false;
		worker.start();
	}

	private void create_timer() {
		frame.getPBar().setIndeterminate(true);
		done = false;
		cancel = false;
		if (TimerP != null)
			TimerP.stop();
		TimerP = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done) {
					TimerP.stop();
					frame.getPBar().setIndeterminate(false);
					frame.getPBar().setValue(frame.getPBar().getMaximum());
					frame.getPBar().setStringPainted(false);
				}
				if (cancel) {
					TimerP.stop();
					frame.getPBar().setIndeterminate(false);
					frame.getPBar().setValue(frame.getPBar().getMaximum());
					frame.getPBar().setStringPainted(false);

				}
			}
		});
		TimerP.start();
	}

	public void BuscarArticuloDesde() {
		this.BuscarArticuloF5(frame.get_txt_idarticulo_desde());
	}

	public void BuscarArticuloHasta() {
		this.BuscarArticuloF5(frame.get_txt_idarticulo_hasta());
	}

	public void BuscarArticuloF5(JTextField ext) {
		aplicacion.herramientas.java.sortableselector.constructor._Constructor CC = new aplicacion.herramientas.java.sortableselector.constructor._Constructor();
		CC.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		CC.build(this.getConstructor());

		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) CC
				.getLogic();

		columna c = new columna();
		Filtro f = new Filtro();
		c = new columna();
		c.setNombre("a.idarticulo");
		c.setAlias("idarticulo");
		c.setColumnField(ext);
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("a.descripcion");
		c.setAlias("descripcion");
		c.setWidth(250);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("a.descripabrev");
		c.setAlias("linea");
		c.setWidth(180);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("sum(isnull(s.cantidadud,0))");
		c.setAlias("stock");
		c.setWidth(100);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("a.precio2");
		c.setAlias("publico $");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("a.precio5");
		c.setAlias("Lista $");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);

		logic
				.addFromTable("v_ma_articulos a left outer join v_mv_stock s on a.idarticulo=s.idarticulo ");
		f = new Filtro();
		f.setNombre("a.idarticulo");
		f.setAlias("a.idarticulo");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("a.descripcion");
		f.setAlias("a.descripcion");
		f.setWidth(250);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("a.descripabrev");
		f.setAlias("a.linea");
		f.setWidth(150);
		logic.addFilter(f);
		logic
				.addGroup("a.idarticulo,a.descripcion,a.descripabrev,a.precio2,a.precio5");
		logic.addOrder("a.idarticulo");
		logic.init();

	}

	public Object[][] procesar_datos(Object[][] results) {
		Object[][] temp = null;
		temp = new Object[results.length][4];
		for (int i = 0; i < results.length; i++) {
			temp[i][0] = false;
			temp[i][1] = results[i][0];
			temp[i][2] = results[i][1];
			temp[i][3] = "";

		}
		return temp;
	}

	public void Cargar() {
		String desde = this.frame.get_txt_idarticulo_desde().getText();
		String hasta = this.frame.get_txt_idarticulo_hasta().getText();
		this.Cargar(desde, hasta);
	}

	public void Cargar(String desde, String hasta) {
		frame.get_txt_idarticulo_desde().setText(desde);
		frame.get_txt_idarticulo_hasta().setText(hasta);
		Object[][] results = data.getQuery(desde, hasta);
		results = this.procesar_datos(results);
		CustomTable table = this.crearTablaDeItems(results);

	}

	public void recodificar() {
		this.frame.getPBar().setIndeterminate(false);
		boolean si_a_todo = false;
		boolean no_a_todo = false;
		int max = 0;

		if (this.frame.getJTable() != null) {
			for (int i = 0; i < this.frame.getJTable().getRowCount(); i++) {
				boolean b = false;
				try {
					b = (Boolean) this.frame.getJTable().getValueAt(i, 0);
				} catch (Exception e) {

				}
				if (b)
					max++;
			}
		}
		this.operations = 0;
		this.max_operations = max;
		this.frame.getPBar().setMaximum(this.max_operations);
		this.frame.getPBar().setStringPainted(true);
		int oks=0;
		int qty = 0;
		if (this.frame.getJTable() != null) {

			int i = 0;
			while (i < this.frame.getJTable().getRowCount() & !cancel) {
				boolean b = false;

				String idarticulo = "";
				String idrecodificacion = "";

				try {
					b = (Boolean) this.frame.getJTable().getValueAt(i, 0);
				} catch (Exception e) {

				}

				try {
					idarticulo = (String) this.frame.getJTable().getValueAt(i,
							1);
				} catch (Exception e) {

				}

				try {
					idrecodificacion = (String) this.frame.getJTable()
							.getValueAt(i, 3);
				} catch (Exception e) {

				}

				if (b) {
					operations++;

					if (si_a_todo) {
						boolean ok=this.recodificar_item(idarticulo, idrecodificacion);
						if (ok)oks++;
						qty++;
					} else {
						if (!no_a_todo) {
							Object[] options = { "Si", "Si a todo", "No",
									"No a todo" };
							int n = JOptionPane.showOptionDialog(frame,
									"Recodifica el articulo " + idarticulo
											+ " a " + idrecodificacion + "?",
									"Confirmar", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, options[0]);
							if (n == 0 | n == 1) {
								qty++;
								boolean ok=this.recodificar_item(idarticulo, idrecodificacion);
								if (ok)oks++;
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
				this.frame.getPBar().setValue(qty);
				this.frame.getPBar().setString(
						"Recodificando Articulo " + idarticulo + " a  "
								+ idrecodificacion + "(" + qty + "/" + max
								+ ")");
				i++;
			}
		}
		if (operations > 0) {
			errors=operations-oks;
			aviso("Operacion Recodificacion Completa con " + operations
					+ " operaciones y " + errors + " errores");
		} else {
			aviso("Seleccione los articulos que desea recodificar!!!");
		}
		done = true;
		if (qty > 0) {
			clean();
		}

	}

	public boolean recodificar_item(String idarticulo, String idrecodificacion) {
		boolean ok=false;
		if (idarticulo.compareTo("") != 0 & idrecodificacion.compareTo("") != 0) {
			if (data.exist_article(idarticulo)) {
				ok=reasignar(idarticulo, idrecodificacion, false);
				
			}
		}
		return ok;
	}

	public void clean() {
		frame.get_txt_idarticulo_desde().setText("");
		frame.get_txt_idarticulo_hasta().setText("");

		frame.setJTable(null);
	}

	public void elimininar_articulo(String idarticulo) {
		String q = "";
		q += "delete from v_ma_articulos where idarticulo like '" + idarticulo
				+ "' ";
		q += "";
		data.clearBatch();
		data.addBatch(data.constraints_deactivate("v_ma_articulos"));
		data.addBatch(q);
		data.addBatch(data.constraints_activate("v_ma_articulos"));
		data.executeBatch();

	}

	public boolean reasignar(String idarticulo, String nuevo, boolean existe) {
		boolean existeNuevo=data.exist_article(nuevo);
		data.beginTransaction();
		String[] tablas = new String[] { "v_ma_articulos", "c_mv_cpteinsumos",
				"s_ta_equiv", "v_ma_artcatrel", "v_ma_insumos", "v_ma_precios",
				"v_ma_precios_borrador", "v_mv_cpteinsumos", "v_mv_stock",
				"v_mv_stockhis", "b_articulos_aplicacion",
				"b_articulos_imagen", "b_arts_pendientes", "b_alias",
				"b_fcn_item", "b_fvn_item", "b_pce_items", "b_pdc_item",
				"b_pdp_items", "b_pm_item", "b_pep_item","b_pdc_item"};

		data.clearBatch();
		
		for (int i = 0; i < tablas.length; i++) {
			String tabla = tablas[i];
			String q = data.constraints_deactivate(tabla);
			System.out.println(q);
			data.addBatch(q);

		}
		if (existeNuevo){
			String q = "";
			q += "delete from v_ma_articulos where idarticulo like '" + idarticulo+ "' ";
			data.addBatch(q);
		
		}
		for (int i = 0; i < tablas.length; i++) {
			String tabla = tablas[i];
			String q = "";
			if (tabla.compareTo("v_ma_articulos") == 0 & existe) {
			} else {
		

				if (tabla.compareTo("s_ta_equiv") == 0) {
					q = data.trigger_disable("s_ta_equiv");
					// System.out.println(q);
					data.addBatch(q);

				}
				q = data.delete_unidad(idarticulo);
				 System.out.println(q);
				data.addBatch(q);
				if (data.existeNuevo(tabla, nuevo)) {
					if (tabla.compareTo("b_alias") == 0) {
						q = data.getDeleteQuery(tabla, nuevo);
						data.addBatch(q);
					}

				}
				q = data.getUpdateQuery(tabla, idarticulo, nuevo);
				data.addBatch(q);
				if (tabla.compareTo("s_ta_equiv") == 0) {
					q = data.trigger_enable("s_ta_equiv");
					data.addBatch(q);
				}
			}

		}
		
		for (int i = 0; i < tablas.length; i++) {
			String tabla = tablas[i];
			String q = data.constraints_activate(tabla);
			data.addBatch(q);

		}
		
		boolean error=data.executeBatch();
		if (!error){
			data.commitTransaction();
		}else{
			data.rollbackTransaction();
		}
		System.out.println("recodificacion "+idarticulo+" "+nuevo+" OK=>"+(!error));
		return !error;
	}

	private CustomTable crearTablaDeItems(Object[][] results) {
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
		col.setName("idArticulo");
		col.setWidth(140);
		col.setEditable(true);

		col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(340);
		col.setEditable(false);
		col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);

		Table.addColumn(col);
		col = new Column();
		col.setName("idRecodificacion");
		col.setWidth(140);
		col.setEditable(true);
		CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_idrecodificacion);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		Font font = new Font("Dialog", Font.PLAIN, 10);
		Table.setData(results);
		Table.setFont(font);
		Table.build();
		Table.fillData();
		JTable table = Table.getTable();
		table.setColumnSelectionAllowed(false);
		frame.setJTable(table);

		return Table;
	}

	public void cancelar_tarea() {
		Object[] options = { "Cancelar", "Continuar" };
		if (preguntar("Confirmar", "Cancela Operacion?")) {
			this.cancel = true;
		}

	}

	public void precodificar() {

		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) this.frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (selected) {
				String idarticulo = "";

				try {
					idarticulo = (String) this.frame.getJTable().getValueAt(i,
							1);
				} catch (Exception e) {

				}

				if (idarticulo.compareTo("") != 0) {
					this.frame.getJTable().setValueAt(idarticulo, i, 3);
				}

			}
		}

	}

	public void completar() {

		int[] columns = this.frame.getJTable().getSelectedColumns();

		for (int cx = 0; cx < columns.length; cx++) {
			int col = columns[cx];
			if (col == 3) {

				if (this.frame.getJTable() != null) {

					if (col == 3) {
						String val = "";

						int[] indexes = this.frame.getJTable()
								.getSelectedRows();
						for (int i = 0; i < indexes.length; i++) {
							try {
								this.frame.getJTable().setValueAt(val,
										indexes[i], col);
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

	public void seleccionar(boolean b) {
		if (this.frame.getJTable() != null) {
			int[] indexes = null;
			try {
				indexes = this.frame.getJTable().getSelectedRows();
			} catch (Exception e) {

			}

			if (indexes != null
					& this.frame.getJTable().getSelectedColumn() == 2) {
				for (int i = 0; i < indexes.length; i++) {
					this.frame.getJTable().setValueAt(b, indexes[i], 0);
				}
			} else {
				for (int i = 0; i < this.frame.getJTable().getRowCount(); i++) {
					this.frame.getJTable().setValueAt(b, i, 0);
				}
			}

		}
	}

	public void evaluar_table_recodificador(JTextField tx, int row, int col) {
		String valor = "";
		valor = tx.getText();
		if (valor.compareTo("") != 0) {
			DefaultTableModel model = (DefaultTableModel) this.frame
					.getJTable().getModel();
			if (row == model.getRowCount() - 1) {
				model.setRowCount(model.getRowCount() + 1);
			}
			frame.getJTable().changeSelection(row + 1, 0, false, false);
			frame.getJTable().editCellAt(row + 1, 0);
			frame.getJTable().transferFocus();
		} else {
			tx.requestFocusInWindow();
		}
	}

	public void borrarRenglon(int row) {
		if (preguntar("Confirmar", "Elimina Renglon " + row + " de la tabla?")) {
			DefaultTableModel model = (DefaultTableModel) frame.getJTable()
					.getModel();
			model.removeRow(row);
			if (model.getRowCount() <= 0) {
				model.setRowCount(1);

			}
		}
	}

	aplicacion.herramientas.java.reemplazar.constructor._Constructor reemplazar = null;

	public void BuscarReemplazar() {
		JTable table = frame.getJTable();
		int col = table.getSelectedColumn();
		int row = table.getSelectedRow();
		if (reemplazar == null) {
			if (col == 3) {

				reemplazar = new aplicacion.herramientas.java.reemplazar.constructor._Constructor();
				reemplazar.setParameter(_parametros.LookAndFeel, this
						.getConstructor().getLookAndFeelTheme());
				reemplazar.setParameter(_parametros.connector, this
						.getConstructor().getConnectionHandler());
				reemplazar.build(this.getConstructor());
				reemplazar.init();
				aplicacion.herramientas.java.reemplazar.logic._Logic logic = (aplicacion.herramientas.java.reemplazar.logic._Logic) reemplazar
						.getLogic();
				logic.centrar();
			}

		}
		if (col == 3) {
			aplicacion.herramientas.java.reemplazar.logic._Logic logic = (aplicacion.herramientas.java.reemplazar.logic._Logic) reemplazar
					.getLogic();

			logic.setColumn(col);
			logic.setProximo(row);
			logic.setTable(table);
			logic.show();
		}

	}
	
	private aplicacion.herramientas.java.importar.constructor._Constructor importar = null;
	public void importar() {
		if (importar != null) {
			importar.dispose();
		}
		if (frame.getJTable() == null) {

			Object[][] results = new Object[][] { { false, "", "", ""} };
			
			this.crearTablaDeItems(results);
		} else {
			if (frame.getJTable().getRowCount() <= 0) {
				Object[][] results = new Object[][] { { false, "", "", ""} };
				this.crearTablaDeItems(results);
			}
		}
		importar = new aplicacion.herramientas.java.importar.constructor._Constructor();
		importar.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		importar
				.setParameter(
						aplicacion.herramientas.java.importar.interfaces._parametros._tabla,
						frame.getJTable());
		importar.build(this.getConstructor());
		importar.init();
		aplicacion.herramientas.java.importar.logic._Logic _logic = (aplicacion.herramientas.java.importar.logic._Logic) importar
				.getLogic();

		columna col = null;

		col = new columna();
		col.setNombre("idarticulo1");
		col.setColumn(1);
		col.setMaster(true);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("descripcion");
		col.setColumn(2);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("idarticulo2");
		col.setColumn(3);
		col.setMaster(false);
		_logic.addColumn(col);

	}

}
