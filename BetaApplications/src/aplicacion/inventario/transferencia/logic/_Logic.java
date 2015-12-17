package aplicacion.inventario.transferencia.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.inventario.transferencia.constructor.*;
import aplicacion.inventario.transferencia.gui._Etiquetas;
import aplicacion.inventario.transferencia.gui._Frame;
import aplicacion.inventario.transferencia.interfaces.*;
import aplicacion.inventario.transferencia.logic._Data;
import aplicacion.inventario.transferencia.interfaces._Interface;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private Object[][] memoria = null;
	private aplicacion.inventario.politica.constructor._Constructor editor_politica = null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.herramientas.java.importar.constructor._Constructor importar = null;
	private aplicacion.herramientas.java.exportar.constructor._Constructor exportar = null;
	private aplicacion.actualizacion.alias.constructor._Constructor alias = null;
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	private aplicacion.herramientas.java.buscadores.Articulo bArticulo = null;
	// variables de tareas swingwork

	private String estado = "";
	private int current;
	private int lenght;
	private boolean debug, done, canceled, override;
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	int errors = 0;
	private boolean nuevo=false;
	private boolean cambios = false;
	private _Etiquetas etiquetas = null;
	private boolean[] marquer;
	private String tc = "TRSK";
	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	/**
	 * idarticulo|descripcion|stock|cantidad
	 * 
	 * @param results
	 */
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(true);
		col.setClass(String.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_transferencia_idarticulo);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());

		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(300);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("stock");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(Double.class);
		table.addColumn(col);

		col = new Column();
		col.setName("cantidad");
		col.setWidth(80);
		col.setEditable(true);
		col.setClass(Double.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_transferencia_cantidad);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("categoria");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("rotacion");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		
		col = new Column();
		col.setName("linea");
		col.setWidth(140);
		col.setEditable(true);
		col.setClass(Double.class);
		table.addColumn(col);
		
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table_control);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable(table.getTable());
	}

	/**
	 * idarticulo|descripcion|stock|cantidad
	 * 
	 * @param results
	 */
	private void create_table_importar(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_transferencia_idarticulo);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());

		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(300);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("stock origen");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(Double.class);
		table.addColumn(col);

		col = new Column();
		col.setName("stock destino");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(Double.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("categoria");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("rotacion");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		
		col = new Column();
		col.setName("linea");
		col.setWidth(140);
		col.setEditable(false);
		col.setClass(Double.class);
		table.addColumn(col);

		
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table_importar);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable1(_table);
	}
	
	private aplicacion.herramientas.java.evaluadores.Fecha Fecha=null;
	public void initialize_Fecha(){
		Fecha=new aplicacion.herramientas.java.evaluadores.Fecha(){
			public void cargar(JTextField tx){
			 frame.get_txt_iddeposito().requestFocusInWindow();
			}
		};
		Fecha.setConstructor(this.getConstructor());
	}
	
	public void reconnect_Fecha(){
		if (Fecha!=null){
			Fecha.setConstructor(this.getConstructor());
		}
	}
	
	public void BuscarFecha(JTextField tx){
		Fecha.Buscar(tx);
	}
	public void BuscarFecha(){
		Fecha.Buscar(frame.get_txt_fecha());
	}
	
	public void evaluarFecha(JTextField tx){
		cambios=true;
		Fecha.evaluate(tx);
	}
	
	public void nuevo() {
		this.clean();
		this.getToday();
		
		nuevo=true;
		String idcomprobante = data.getProximoPGCorrecto(tc);
		frame.get_txt_idcomprobante().setText(idcomprobante);
		frame.get_txt_Stock().setText("0");
		this.evaluar_transferencia(frame.get_txt_idcomprobante());
		// this.evaluar_idcomprobante(frame.get_txt_idcomprobante());
	}

	public void clean_importacion(){
		frame.setJTable1(null);
		frame.get_txt_articulo_desde().setText("");
		frame.get_txt_articulo_hasta().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_proveedor_descripcion().setText("");
		frame.get_txt_linea().setText("");
	}
	public void seleccionar_items(boolean b){
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
	
	public void seleccionar_items_importar(boolean b){
		JTable table = frame.getJTable1();
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
	
	public void deseleccionar() {
		frame.getJTable().clearSelection();
		frame.getJTable().transferFocus();
	}
	
	public void deseleccionar_importar() {
		frame.getJTable1().clearSelection();
		frame.getJTable1().transferFocus();
	}	
	
	public void evaluar_linea(JTextField tx){
		String linea=tx.getText();
		if (linea.compareTo("")!=0){
			if (data.check_linea(linea)){
				cargar_prefijos(linea);
			}else{
				this.buscarLinea(tx);
			}
		}else{
			this.buscarLinea(tx);
		}
	}
	
	public void evaluar_idarticulo_desde(JTextField tx) {
		String idarticulo = "";
		idarticulo = tx.getText();
		if (idarticulo.compareTo("") == 0) {
			aviso("Ingrese un Articulo. Busqueda F5");
		} else {
			if (idarticulo.length() > 4) {
				if (idarticulo.substring(3, 4).compareTo("-") == 0) {
					frame.get_txt_articulo_hasta().requestFocusInWindow();	
				} else {
					this.buscarArticulo(tx);
				}
			} else {
				this.buscarArticulo(tx);
			}
		}
	}
	
	public void evaluar_idarticulo_hasta(JTextField tx) {
		String idarticulo = "";
		idarticulo = tx.getText();
		if (idarticulo.compareTo("") == 0) {
			aviso("Ingrese un Articulo. Busqueda F5");
		} else {
			if (idarticulo.length() > 4) {
				if (idarticulo.substring(3, 4).compareTo("-") == 0) {
					this.cargar_stock();	
				} else {
					this.buscarArticulo(tx);
				}
			} else {
				this.buscarArticulo(tx);
			}
		}
	}

	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	public void buscarProveedor(JTextField tx) {
		if (vProveedor!=null){
			vProveedor.close();
		}
		vProveedor=new aplicacion.herramientas.java.visualizadores.Proveedor(this.getConstructor());
		int n = vProveedor.Buscar(tx);
		if (n == 0) {
				aviso("no hay Proveedors con ese codigo");
		}
	}
	
	public void evaluar_proveedor(JTextField tx){
		String idproveedor=tx.getText();
		if (idproveedor.startsWith("21101") & idproveedor.compareTo("21101")!=0){
			Object[][] results=data.getProveedor(idproveedor);
				if (results!=null){
					if (results.length>0){
						String desc=results[0][1].toString();
						String pol=results[0][2].toString();
						String poldesc=results[0][3].toString();
						String linea=results[0][6].toString();
						frame.get_txt_proveedor_descripcion().setText(desc);
						frame.get_txt_linea().requestFocusInWindow();
					}else {
						aviso("No se encontro Proveedor "+idproveedor);
					}
				}else {
					aviso("No se encontro Proveedor "+idproveedor);
				}
		}else {
				this.buscarProveedor(tx);
			
				
		}
	}
	
	public void cargar_prefijos(String linea){
		Object[][] results=data.getLinePrefix(linea);
		if (results!=null){
			if (results.length>0){
				String prefix=(String) results[0][0];
				frame.get_txt_articulo_desde().setText(prefix+"-000");
				frame.get_txt_articulo_hasta().setText(prefix+"-zzz");
				frame.get_txt_articulo_hasta().requestFocusInWindow();
				}
		}
	}
	private aplicacion.herramientas.java.visualizadores.Linea vLinea=null;
	public void buscarLinea(JTextField tx) {
		if (vLinea!=null){
			vLinea.close();
		}
		vLinea=new aplicacion.herramientas.java.visualizadores.Linea(this.getConstructor());
		vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLinea(tx);
		if (n == 0) {
				aviso("no hay Lineas con ese codigo");
		}
	}
	
	public void quitar_items(){
		if (confirmar("confirme para quitar los items seleccionados:",3)){
			JTable table = frame.getJTable();
			int imports=0;
			if (table != null) {

				for (int i = table.getRowCount()-1; i >=0 ; i--) {
					boolean selected = false;
					try {
						selected = (Boolean) table.getValueAt(i, 0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (selected) {
						DefaultTableModel model=(DefaultTableModel) table.getModel();
						model.removeRow(i);
						imports++;
					}

				}
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				if (model.getRowCount()==0){
					model.setRowCount(1);
				}
			}
			aviso("Se Quitaron "+imports+" items");
			
		}
		
	}
	public void clean() {
		nuevo=false;
		frame.setJTable(null);
		this.clean_importacion();
		frame.get_txt_control_estado().setText("");
		frame.get_txt_idcomprobante().setText("");
		frame.get_txt_idvendedor().setText("");

		frame.get_txt_idcomprobante().setEditable(true);

		frame.get_txt_iddeposito().setText("");
		frame.get_txt_deposito_descripcion().setText("");
		frame.get_txt_iddeposito_destino().setText("");
		frame.get_txt_deposito_descripcion_destino().setText("");
		this.getToday();
		frame.get_txt_idcomprobante().requestFocusInWindow();
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

	public boolean elegir_vendedor() {
		boolean ok = false;
		System.out.println("elegir vendedor");
		String idvendedor = this.validar_vendedor();
		frame.get_txt_idvendedor().setText(idvendedor);

		if (idvendedor.compareTo("") != 0) {
			ok = true;
			this.evaluarVendedor(frame.get_txt_idvendedor());
			frame.get_txt_idvendedor().setEditable(false);
		} else {
			ok = false;
		}

		return ok;
	}

	public void focus() {
		frame.get_txt_idcomprobante().requestFocusInWindow();
	}

	public Object[][] procesarDatos(Object[][] datos) {
		Object[][] tmp = new Object[datos.length][datos[0].length];
		
		for (int i = 0; i < datos.length; i++) {
			tmp[i][0] = (datos[i][0].toString().compareTo("1")==0);
			tmp[i][1] = datos[i][1];// idarticulo
			tmp[i][2] = datos[i][2];// descripcion
			tmp[i][3] = new Double((String)datos[i][3]);// stock
			tmp[i][4] = new Double((String)datos[i][4]);// cantidad
		}
		return tmp;
	}

	public void eliminar() {
		boolean ok = this
				.confirmar(
						"Confirme para eliminar este control de stock integro incluyendo los cambios producidos en el stock",
						3);
		if (ok) {
			System.out.println("ELIMINAR");
			String idcontrol = frame.get_txt_idcomprobante().getText();
			List<String> eliminar = new ArrayList<String>();
			eliminar.add(data.getDeleteStock(idcontrol));
			eliminar.add(data.getDeleteTransferencia(idcontrol));
			eliminar.add(data.getDeleteTransferenciaItems(idcontrol));
			data.beginTransaction();
			data.clearBatch();
			for (int i = 0; i < eliminar.size(); i++) {
				System.out.println(eliminar.get(i));
				data.addBatch((eliminar.get(i)));
			}
			boolean error = data.executeBatch();
			if (!error) {

				data.commitTransaction();
				aviso("Se Elimino Correctamente");
			} else {
				data.rollbackTransaction();
				aviso("Error al Eliminar");
			}
		}

	}

	public void eliminar_aplicacion() {
		boolean ok = this.confirmar(
				"Confirme para eliminar los  cambios producidos del stock", 3);
		if (ok) {
			System.out.println("ELIMINAR APLICACION");
			String idcontrol = frame.get_txt_idcomprobante().getText();
			List<String> eliminar = new ArrayList<String>();
			eliminar.add(data.getDeleteStock(idcontrol));

			data.beginTransaction();
			data.clearBatch();
			for (int i = 0; i < eliminar.size(); i++) {
				System.out.println(eliminar.get(i));
				data.addBatch((eliminar.get(i)));
			}
			boolean error = data.executeBatch();
			if (!error) {

				data.commitTransaction();
				aviso("Se Elimino Correctamente los Cambios de Stock Producidos Anteriormente");
			} else {
				data.rollbackTransaction();
				aviso("Error al Eliminar Cambios de Stock");
			}
		}

	}

	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte = null;

	public void reporte() {
		if (reporte != null) {
			reporte.dispose();
		}
		String idcontrol = frame.get_txt_idcomprobante().getText();
		String idusuario=frame.get_txt_idvendedor_descripcion().getText();
		String origen=frame.get_txt_deposito_descripcion().getText();
		String destino=frame.get_txt_deposito_descripcion_destino().getText();
		String fecha=frame.get_txt_fecha().getText();
		boolean aplicado=data.aplicado(idcontrol);
		String estado="APLICADO";
		if (!aplicado){
			estado="NO APLICADO";
		}
		if (data.exist(idcontrol)) {
			reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();
			HashMap param = new HashMap();
			param.put("idcontrol", idcontrol);
			param.put("idusuario", idusuario);
			param.put("deposito_origen", origen);
			param.put("deposito_destino", destino);
			param.put("fecha", fecha);
			param.put("estado", estado);
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
								"transferencia_stock.jasper");
			

			reporte.build(this.getConstructor());
			reporte.init();
		}

	}

	public void cargar() {
		this.evaluarTransferenciaStock(frame.get_txt_idcomprobante());
	}

	private void transfer_focus(int row, int col) {
		frame.getJTable().changeSelection(row, col, false, false);
		frame.getJTable().editCellAt(row, col);
		frame.getJTable().transferFocus();
	}

	public void crear_tabla() {
		Object[][] results = new Object[][] { { true,"", "", "", "" } };
		this.create_table(results);
	}

	public void cargar_datos(String idtransferencia) {
		
		Object[][] results = data.getTransferenciaItems(idtransferencia);
		if (results != null) {
			if (results.length > 0) {
				results = this.procesarDatos(results);
				this.create_table(results);
			}
		}
	}

	private aplicacion.herramientas.java.evaluadores.TransferenciaStock TransferenciaStock = null;

	public void initialize_TransferenciaStock() {
		TransferenciaStock = new aplicacion.herramientas.java.evaluadores.TransferenciaStock() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				// idTransferencia,iduser,convert(varchar(10),isnull(fecha,getdate()),105),iddeposito_origen,iddeposito_destino
				String iduser = (String) results[0][1];
				String fecha = (String) results[0][2];
				String iddeposito_origen = (String) results[0][3];
				String iddeposito_destino = (String) results[0][4];
				String idvendedor = (String) results[0][5];
				frame.get_txt_idvendedor().setText(idvendedor);
				evaluarVendedor(frame.get_txt_idvendedor());
				frame.get_txt_iddeposito().setText(iddeposito_origen);
				frame.get_txt_iddeposito_destino().setText(iddeposito_destino);
				frame.get_txt_fecha().setText(fecha);
				
				frame.get_txt_iddeposito().requestFocusInWindow();
				cargar_datos(codigo);
			}
		};
		
		TransferenciaStock.setConstructor(this.getConstructor());
	}

	public void BuscarTransferenciaStock(JTextField tx) {
		TransferenciaStock.Buscar(tx);
	}

	public void BuscarTransferenciaStock() {
		TransferenciaStock.Buscar(frame.get_txt_idcomprobante());
	}

	public void buscarTransferenciaStock(JTextField tx) {
		TransferenciaStock.buscar(tx);
	}

	public void evaluarTransferenciaStock(JTextField tx) {
		TransferenciaStock.evaluate(tx);
	}

	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_fecha().setText(
		new Convertidor().getDateWithFormat("dd-MM-yyyy"));
	}
	
	public void evaluar_transferencia(JTextField tx) {

		String numero = tx.getText();
		if (numero.compareTo("")==0){
			this.evaluarTransferenciaStock(tx);
		}else{
			if (TransferenciaStock.existe(numero)) {
				this.evaluarTransferenciaStock(tx);

			} else {
				String correcto = data.getProximoPGCorrecto(tc);
				if (correcto.compareTo(numero) == 0) {
					frame.get_txt_idcomprobante().setEditable(false);
					this.cargarVendedor();
					this.crear_tabla();
					frame.get_txt_iddeposito().requestFocusInWindow();
				} else {
					error("ERROR NUMERO DE TRANFERENCIA DE STOCK");
				}

			}			
		}

	}

	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;

	public void goMa_Articulos(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
		articulo.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		articulo.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		articulo
				.setParameter(
						aplicacion.inventario.articulo.interfaces._parametros.idarticulo,
						idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}

	private List<String> getInstruccionesDeEncabezadoStock() {
		List<String> instrucciones = new ArrayList<String>();
		
		String idtransferencia = frame.get_txt_idcomprobante().getText();
		String instruccion = data.getDeleteStock(idtransferencia);
		instrucciones.add(instruccion);
		
		
		return instrucciones;
	}

	private List<String> getInstruccionesDeStock() {
		List<String> instrucciones = new ArrayList<String>();
		
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		String fecha = frame.get_txt_fecha().getText();
		String iddeposito = frame.get_txt_iddeposito().getText();
		String iddeposito_destino = frame.get_txt_iddeposito_destino().getText();
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !canceled) {
			boolean selected=false;
			try {
				selected=(Boolean)frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String _idarticulo="";
			try {
				_idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_idarticulo.compareTo("")!=0 & _idarticulo.compareTo("*")!=0){
				if (selected){
					String _descripcion = frame.getJTable().getValueAt(i, 2).toString();
					String _cantidad = frame.getJTable().getValueAt(i, 4).toString();
					String _precio = "0.0";;
					double cantidad = new Double(_cantidad);
					if (cantidad !=0) {
						double salida=-cantidad;
						double entrada=cantidad;
						String _instruccioni_salida = data.getInsertQueryVMVStock(tc,
								idcomprobante, _idarticulo, _descripcion, "" + salida,
								i, fecha, _precio, iddeposito);
						instrucciones.add(_instruccioni_salida);
						String _instruccioni_entrada = data.getInsertQueryVMVStock(tc,
								idcomprobante, _idarticulo, _descripcion, "" + entrada,
								i, fecha, _precio, iddeposito_destino);
						instrucciones.add(_instruccioni_entrada);
					}	
				}
				
				
			}
				i++;
		}
		return instrucciones;
	}

	public void _delete_item_articulo(JTextField tx, int row) {
		String value=tx.getText();
		if (value.compareTo("")==0){
			this.borrarRenglon(row);
		}
	}
	
	public void borrarRenglon(int row){
		if (preguntar("Confirmar","Elimina Renglon "+row+" de la tabla?")){
			try {
				frame.getJTable().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			DefaultTableModel model=(DefaultTableModel) frame.getJTable().getModel();
			model.removeRow(row);
			if (model.getRowCount()<=0){
				model.setRowCount(1);
				model.setValueAt(true, 0, 0);
				this.editCell(0, 1);
			}
			
			cambios=true;
		}
	}
	
	
private aplicacion.ventas.catalogo.constructor._Constructor Catalogo=null;
	
	public void BuscarCatalogo(JTextField ext) {
		if (Catalogo != null) {
			Catalogo.dispose();
			Catalogo=null;
		}
		if (Catalogo == null) {
			Catalogo = 
				new aplicacion.ventas.catalogo.constructor._Constructor();
			
			Catalogo.setParameter(_parametros.connector, data.getConnectionHandler());
			Catalogo.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
			Catalogo.build(this.getConstructor());
			Catalogo.init();	
			this.getConstructor().addChild(Catalogo);
		}
		
		
		aplicacion.ventas.catalogo.logic._Logic logic=
		(aplicacion.ventas.catalogo.logic._Logic)Catalogo.getLogic();
		if (logic!=null){
			logic.setTransferencia((aplicacion.inventario.transferencia.constructor._Constructor)this.getConstructor());	
		}
		
		
	}
	public void editCell(int row,int col){
		JTable table=frame.getJTable();
		table.changeSelection(row, col, false,false);
		table.editCellAt(row, col);
		table.transferFocus();
	}

	public void editarArticulo(JTextField tx) {
		String idarticulo = tx.getText();
		Object[][] results = data.getArticulo(idarticulo);
		boolean exist = false;
		if (results != null) {
			if (results.length > 0) {
				exist = true;

			}
		}
		if (exist) {
			this.goMa_Articulos(idarticulo);
		} else {
			error("El articulo " + idarticulo + " es inexistente");
		}
	}
	private List<String> getInstruccionesDeItemsControl() {
		List<String> instrucciones = new ArrayList<String>();
		
		String idcomprobante = frame.get_txt_idcomprobante().getText();
		String fecha = frame.get_txt_fecha().getText();

		int i = 0;
		while (i < frame.getJTable().getRowCount() & !canceled) {
			boolean selected=false;
			try {
				selected=(Boolean)frame.getJTable().getValueAt(i, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String _idarticulo="";
			try {
				_idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_idarticulo.compareTo("")!=0 & _idarticulo.compareTo("*")!=0){
				String _cantidad = frame.getJTable().getValueAt(i, 4).toString()
				.replaceAll(",", "");
		String _stock = frame.getJTable().getValueAt(i, 3).toString()
				.replaceAll(",", "");
		double cantidad = new Double(_cantidad);
		double stock = new Double(_stock);
		String _instruccioni = "";
		if (data.existeItem(idcomprobante, _idarticulo)) {
			if (cantidad <= 0) {
				_instruccioni = data.deleteTransferenciaItem(idcomprobante,
						_idarticulo);
			} else {
				_instruccioni = data.updateTransferenciaItem(idcomprobante,i,
						_idarticulo, cantidad,stock,selected);
			}
		} else {
			if (cantidad > 0) {
				_instruccioni = data.insertTransferenciaItem(idcomprobante,i,
						_idarticulo, cantidad,stock,selected);
			}

		}

		if (_instruccioni.compareTo("") != 0) {
			instrucciones.add(_instruccioni);
			System.out.println(_instruccioni);
		}
			}


			i++;
		}
		return instrucciones;
	}

	public void _taskworkAplicarStock() {

		System.out.println("Aplicar Stock");
		String idcontrol = frame.get_txt_idcomprobante().getText();
		String delete = data.getDeleteStock(idcontrol);
		System.out.println(delete);

		data.clearBatch();
		data.addBatch(delete);
		errors = 0;
		current = 0;
		boolean error = data.executeBatch();
		estado = "Aplicando Cambios de Stock";
		if (!error) {
			List<String> stock = this.getInstruccionesDeStock();
			int operations = stock.size();
			this.lenght = stock.size();
			int i = 0;
			while (i < stock.size() & !canceled) {
				System.out.println(stock.get(i));
				data.clearBatch();
				data.addBatch(stock.get(i));
				error = data.executeBatch();
				if (error) {
					errors++;
				}
				current++;
				i++;
			}

			aviso("Operacion Guardar Finalizada con " + operations
					+ " operaciones y " + errors + " errores ");
		}
		done = true;

	}

	public void _taskworkGuardar() {
		System.out.println("Guardar");
		List<String> encabezado = this.getInstruccionesDeEncabezadoStock();
		List<String> items = this.getInstruccionesDeItemsControl();
		current = 0;
		data.beginTransaction();
		data.clearBatch();
		String iddeposito = frame.get_txt_iddeposito().getText();
		String iddeposito_destino = frame.get_txt_iddeposito_destino().getText();
		String fecha = frame.get_txt_fecha().getText();
		String idtransferencia = frame.get_txt_idcomprobante().getText();
		String iduser = this.getConstructor().getIduser();
		String idvendedor= frame.get_txt_idvendedor().getText();
		String q="";
		if (!nuevo){
			
			q=data.getUpdateQuery(idtransferencia, fecha, iddeposito, iddeposito_destino,iduser,idvendedor);
		}else{
			q=data.getInsertQuery(idtransferencia, fecha, iddeposito, iddeposito_destino, iduser,idvendedor);
		}
		data.addBatch(q);
		for (int i = 0; i < encabezado.size(); i++) {
			// System.out.println(encabezado.get(i));
			data.addBatch(encabezado.get(i));
		}
		errors = 0;
		boolean error = data.executeBatch();
		estado = "Guardando Control";
		if (!error) {

			int operations = items.size();
			this.lenght = items.size();
			int i = 0;

			while (i < items.size() & !canceled & !error) {
				data.clearBatch();
				data.addBatch(items.get(i));
				error = data.executeBatch();
				if (error) {
					errors++;
				}
				current++;
				i++;
			}
			if (nuevo &!error){
				System.out.println(q);
				error=data.updateTC(tc);
			}
			if (!error){
					data.commitTransaction();
					aviso("Operacion Guardar Finalizada con " + operations
							+ " operaciones y " + errors + " errores ");
					nuevo=false;
			}else{
				data.rollbackTransaction();
				aviso("Error Grabando Operacion");
			}
			done = true;
			cambios = false;
			
		}else{
			data.rollbackTransaction();
			aviso("Error Grabando Operacion");
		}

	}

	// metodos basicos de tareas swing
	public void createTimer() {
		crono = new Crono();
		crono.start();
		lenght = 0;
		current = 0;
		errors = 0;
		done = false;
		canceled = false;
		Timer = new Timer(300, new ActionListener() {
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
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
		frame.get_btn_cargar().setEnabled(true);
		frame.get_btn_guardar().setEnabled(true);
	}

	public void doCancel() {
		if (preguntar("confirmar", "Cancela Tarea?")) {
			this.canceled = true;
			frame.get_btn_cancel_task().setEnabled(true);

		}
	}

	public void goGuardar() {

		String iddeposito = frame.get_txt_iddeposito().getText();
		if (this.Deposito.existe(iddeposito)) {
			this.createTimer();
			SwingWorker worker = null;
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					return new _taskGuardar();
				}
			};
			if (Timer != null) {
				Timer.start();
			}
			worker.start();

		} else {
			error("Ingrese El Deposito");
		}
	}

	public void aplicarStock() {
		boolean ok=false;
		String iduser=this.validar_usuario();
		if (iduser.compareTo("")!=0){
			String tc="TRSK";
			String idcomprobante=frame.get_txt_idcomprobante().getText();
			String cuenta="";
			String ip=data.getIp();
			String operacion="Aplicacion de Transferencia "+iduser+" "+data.getHost()+" "+new Date()+" ";
			data.registrar_operacion(tc, idcomprobante, cuenta, iduser, ip, operacion);
		
		}
		
		if (!cambios) {
			ok = this.confirmar(
					"Confirme para aplicar cambios al stock", 3);
			if (ok) {
				this.goAplicarStock();
			}
		} else {
			aviso("Se detectaron cambios en el control. Antes de Aplicar los cambios de Stock. Debe guardar el control");
		}

	}

	public void goAplicarStock() {
		this.createTimer();
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskAplicarStock();
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	class _taskGuardar {
		_taskGuardar() {
			_taskworkGuardar();
		}
	}

	class _taskAplicarStock {
		_taskAplicarStock() {
			_taskworkAplicarStock();
		}
	}

	public void evaluate_control(JTextField tx, int row) {
		String valor = tx.getText();
		cambios = true;
		double control = 0.0;
		boolean error = false;
		try {
			control = new Double(valor.replaceAll(",", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		}
		if (!error) {
			if (control >= 0) {
				if (row < frame.getJTable().getRowCount() - 1) {
					String cant = frame.getJTable().getValueAt(row, 3)
							.toString();
					double cantidad = 0.0;
					try {
						cantidad = new Double(cant);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					double ajuste = control - cantidad;
					frame.getJTable().setValueAt(ajuste, row, 5);
					frame.getJTable().changeSelection(row + 1, 4, false, false);
					frame.getJTable().editCellAt(row + 1, 4);
					frame.getJTable().transferFocus();
				}

			} else {
				error("La cantidad controlada no puede ser menor a cero");
				tx.requestFocusInWindow();
			}
		} else {
			error("Error en cantidad, verifique. No puede ser menor a cero ");
			tx.requestFocusInWindow();
		}
	}

	public void imprimir_etiquetas() {

		LinkedList l = new LinkedList();
		try {
			etiquetas.getJTable().clearSelection();
			etiquetas.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {

		}
		etiquetas.getJTable().setEnabled(false);
		int etqs = 0;
		for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) etiquetas.getJTable().getValueAt(i, 0);
			} catch (Exception e) {

			}
			if (b)
				etqs++;
		}
		if (etqs > 0) {
			if (confirmar("Confirme Para Imprimir Etiquetas", 2)) {
				data.beginTransaction();
				data.clearBatch();
				for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
					boolean b = false;
					try {
						b = (Boolean) etiquetas.getJTable().getValueAt(i, 0);
					} catch (Exception e) {

					}

					String idarticulo = "";
					String descripcion = "";
					String cant = "";
					double _cant = 0.0;

					try {
						idarticulo = etiquetas.getJTable().getValueAt(i, 1)
								.toString();
						descripcion = etiquetas.getJTable().getValueAt(i, 2)
								.toString();
						cant = etiquetas.getJTable().getValueAt(i, 3)
								.toString();
						_cant = new Double(cant);
					} catch (Exception e) {

					}
					if (b & _cant >= 1) {
						String q = "insert into b_etiquetas (fecha,idarticulo,descripcion,cantidad) ";
						q += "values (getdate(),'" + idarticulo + "','"
								+ descripcion + "'," + _cant + ")";
						data.addBatch(q);
					}

				}
				data.executeBatch();
				data.commitTransaction();
				etiquetas.setVisible(false);
				etiquetas.dispose();
			}

		} else {
			aviso("No hay etiquetas para imprimir");
			if (preguntar("Etiquetas", "Cierra Ventana de Etiquetas?")) {
				etiquetas.setVisible(false);
				etiquetas.dispose();
			}
		}
	}

	private void create_table_etiquetas(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
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
		cbce.setName(_Interface._table_etiquetas_seleccion);
		cbce.setItemListener(constructor.getItemListener());
		col.setCellEditor(cbce.getCellCheck());
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_etiquetas_cantidad);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_etiquetas);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		etiquetas.setJTable(_table);
	}

	private void crear_empty_etiquetas() {
		Object[][] results = new Object[][] { { false, "", "", "" } };
		this.create_table_etiquetas(results);
	}

	public void Etiquetar() {

		frame.getJTable().setEnabled(false);
		int qtys = 0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {

			double qty = 0.0;
			try {
				qty = (Double) frame.getJTable().getValueAt(i, 6);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (qty > 0) {
				qtys++;
			}
		}

		Object[][] results = new String[qtys][3];
		qtys = 0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String idarticulo = (String) frame.getJTable().getValueAt(i, 0);
			String descripcion = (String) frame.getJTable().getValueAt(i, 1);
			double qty = 0.0;
			try {
				qty = (Double) frame.getJTable().getValueAt(i, 6);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (qty > 0) {
				results[qtys][0] = idarticulo;
				results[qtys][1] = descripcion;
				results[qtys][2] = "" + qty;
				qtys++;
			}
		}
		if (results != null) {
			if (results.length > 0) {
				_Constructor constructor = (_Constructor) this.getConstructor();
				if (this.etiquetas != null) {
					etiquetas.setVisible(false);
					etiquetas.dispose();
				}
				this.etiquetas = new _Etiquetas();
				this.etiquetas.get_chk_seleccionar().setName(
						_Interface._chk_seleccionar_etiquetas);
				this.etiquetas.get_chk_seleccionar().addItemListener(
						constructor.getItemListener());
				this.etiquetas.get_btn_imprimir_etiquetas().setActionCommand(
						_Interface._btn_imprimir_etiquetas);
				this.etiquetas.get_btn_imprimir_etiquetas().addActionListener(
						constructor.getActionListener());
				this.etiquetas.setVisible(true);
				this.etiquetas.requestFocus();
				this.etiquetas.requestFocusInWindow();
				this.crear_empty_etiquetas();
				int units = 0;
				for (int i = 0; i < results.length; i++) {
					String idarticulo = "";
					String descripcion = "";
					String cantidad = "";
					double _cantidad = 0.0;
					try {
						idarticulo = results[i][0].toString();
						descripcion = results[i][1].toString();
						cantidad = results[i][2].toString();
						_cantidad = new Double(cantidad.replace(",", ""));

					} catch (Exception e) {

					}
					if (_cantidad > 0) {
						DefaultTableModel model = (DefaultTableModel) etiquetas
								.getJTable().getModel();
						int row = model.getRowCount() - 1;
						etiquetas.getJTable().setValueAt(true, row, 0);
						etiquetas.getJTable().setValueAt(idarticulo, row, 1);
						etiquetas.getJTable().setValueAt(descripcion, row, 2);
						etiquetas.getJTable().setValueAt(cantidad, row, 3);

						model.setRowCount(model.getRowCount() + 1);
						units += _cantidad;
						etiquetas.get_txt_unidades().setText("" + units);
					}
				}
			}
		}

		frame.getJTable().setEnabled(true);

	}

	public void cargarDeposito() {
		String iddeposito = this.getConstructor().getIdDeposito();

		frame.get_txt_iddeposito().setText(iddeposito);
		this.evaluarDeposito(frame.get_txt_iddeposito());
	}

	private aplicacion.herramientas.java.evaluadores.Deposito Deposito = null;

	public void initialize_Deposito() {
		
		Deposito = new aplicacion.herramientas.java.evaluadores.Deposito() {
			public void cargar(JTextField tx) {
				
				String codigo=tx.getText();
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				if (tx.getName()==_Interface._txt_iddeposito){
					frame.get_txt_iddeposito().setText(cod);
					frame.get_txt_deposito_descripcion().setText(descripcion);
					frame.get_txt_iddeposito_destino().requestFocusInWindow();	
				}else{
					frame.get_txt_iddeposito_destino().setText(cod);
					frame.get_txt_deposito_descripcion_destino().setText(descripcion);
					transfer_focus_articulos();
				}
				
			}
		};
		Deposito.setConstructor(this.getConstructor());
	}

	public void BuscarDeposito(JTextField tx) {
		Deposito.Buscar(tx);
	}

	public void BuscarDeposito() {
		Deposito.Buscar(frame.get_txt_iddeposito());
	}

	public void buscarDeposito(JTextField tx) {
		Deposito.buscar(tx);
	}

	public void evaluarDeposito(JTextField tx) {
		cambios = true;
		tx.setText(tx.getText().replaceAll(" ", ""));
		Deposito.evaluate(tx);
	}

	public void evaluarDepositoDestino(JTextField tx) {
		cambios = true;
		tx.setText(tx.getText().replaceAll(" ", ""));
		String iddeposito = tx.getText();
		if (Deposito.existe(iddeposito)) {
			Object[][] results = Deposito.getInfo(iddeposito);
			if (results != null) {
				if (results.length > 0) {
					String descripcion = (String) results[0][1];
					frame.get_txt_deposito_descripcion_destino().setText(
							descripcion);
					this.transfer_focus_articulos();
				}
			}
		}
	}

	public void transfer_focus_articulos() {
		System.out.println("TRansfer Focus to Table");
		if (frame.getJTable() == null) {
			this.crear_tabla();

		} else {
			if (frame.getJTable().getRowCount() <= 0) {
				this.crear_tabla();
			}
		}

		frame.getJTable().requestFocusInWindow();
		frame.getJTable().changeSelection(0, 1, false, false);
		frame.getJTable().editCellAt(0, 1);
		frame.getJTable().transferFocus();
	}

	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor = null;

	public void initialize_Vendedor() {
		Vendedor = new aplicacion.herramientas.java.evaluadores.Vendedor() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_idvendedor_descripcion().setText(descripcion);
				transfer_focus_articulos();

			}
		};
		Vendedor.setConstructor(this.getConstructor());
	}

	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
	public void BuscarProveedor(JTextField ext) {
		 if (bProveedor==null){
			 bProveedor=new aplicacion.herramientas.java.buscadores.Proveedor(this.getConstructor());
		 }

	 bProveedor.Buscar(ext);
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

	public void buscarVendedor(JTextField tx) {
		Vendedor.buscar(tx);
	}

	public void evaluarVendedor(JTextField tx) {
		cambios = true;
		tx.setText(tx.getText().replaceAll(" ", ""));
		Vendedor.evaluate(tx);
	}

	public void cargarVendedor() {
		String iduser = this.getConstructor().getIduser();
		Object[][] results = data.getVendedor(iduser);
		boolean loaded = false;
		if (results != null) {
			if (results.length > 0) {
				String idvendedor = (String) results[0][0];
				String nombre = (String) results[0][1];
				frame.get_txt_idvendedor().setText(idvendedor);
				frame.get_txt_idvendedor_descripcion().setText(nombre);
				idvendedor = idvendedor.replaceAll(" ", "");
				loaded = idvendedor.compareTo("") != 0;
				frame.get_txt_idvendedor().setEditable(false);

			}
		}
		System.out.println("cargado de vendedor por defecto? " + loaded);
		if (!loaded) {
			if (javax.swing.SwingUtilities.isEventDispatchThread()) {
				Runnable runnable = new Runnable() {
					public void run() {
						elegir_vendedor();
					}
				};
				this.invokeLater(runnable);
			} else {
				elegir_vendedor();
			}

		}
	}
	
	public void _eval_item_articulo(JTextField tx, int row) {
		String aux = "";
		cambios=true;
		
		try {
			aux = tx.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		tx.requestFocusInWindow();
		if (aux.compareTo("") != 0) {
			if (aux.compareTo("*") == 0) {
				error("NO ESTA PERMITIDO EL * EN TRANSFERENCIAS DE STOCK ");
			} else {
				if (aux.length() > 4) {
						if (aux.substring(3, 4).compareTo("-") == 0) {
							_evaluate_idarticulo(tx, row);
						} else {
							this.buscarArticulo(tx);
						}
					} else {
							this.buscarArticulo(tx);
					
					}
				

			}
		} else {
				aviso("Ingrese un Codigo de Articulo Valido");
				
				tx.requestFocusInWindow();
			

		}
	}

	public int existEmpty(){
		int exist=-1;
		int i=0;
		JTable table=frame.getJTable();
		if (table!=null){
			while (i<table.getRowCount() & exist<0){
				String _idarticulo=table.getValueAt(i, 1).toString();
				if (_idarticulo.compareTo("")==0){
					exist=i;
				}
				i++;
			}	
		}
		return exist;
	}
	
	public int existArticulo(String idarticulo){
		int exist=-1;
		int i=0;
		JTable table=frame.getJTable();
		if (table!=null){
			while (i<table.getRowCount() & exist<0){
				String _idarticulo=table.getValueAt(i, 1).toString();
				if (idarticulo.compareTo(_idarticulo)==0){
					exist=i;
				}
				i++;
			}	
		}
		return exist;
	}
	
	
	
	public void importar_critico() {
		JTable table = frame.getJTable1();
		int imports=0;
		if (table != null) {

			for (int i = 0; i < table.getRowCount(); i++) {
				boolean selected = false;
				try {
					selected = (Boolean) table.getValueAt(i, 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (selected) {
					imports++;
					String idarticulo = (String) table.getValueAt(i, 1);
					String descripcion = (String) table.getValueAt(i, 2);
					String qty= (String) table.getValueAt(i, 3);
					String linea = (String) table.getValueAt(i, 5);
					if (frame.getJTable() == null) {
						this.crear_tabla();
					}
					this.agregar(new Object[] { idarticulo, descripcion,
							qty,linea });
				}

			}
		}
		aviso("Se importaron "+imports+" items");
		frame.getJTabbedPane().setSelectedIndex(0);
		table.requestFocusInWindow();
	}
	
	public void agregar(Object[] seleccion){
		cambios=true;
		String idarticulo=(String) seleccion[0];
		String descripcion=(String) seleccion[1];
		String linea="";
		try {
			linea=(String) seleccion[3];
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String iddeposito=frame.get_txt_iddeposito().getText();
		double stock=data.getStockArticulo(idarticulo, iddeposito);
		if (stock<=0){
			stock=0.0;
		}
		String cantidad="1.0";
		String descuento="0.0";
		JTable table=frame.getJTable();
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		Object[][] results=data.getArticulo(idarticulo);
		if (results!=null){
			if (results.length>0){
				int exist=this.existArticulo(idarticulo);
				String costo=results[0][2].toString();
				String precio=results[0][1].toString();
				if (exist<0){
					int row=0;
					if (table!=null){
						
						row=this.existEmpty();
						if (row<0){
							DefaultTableModel model=(DefaultTableModel) table.getModel();
							model.setRowCount(model.getRowCount()+1);
							row=model.getRowCount()-1;	
						}
						
					}else{
						this.crear_tabla();
					}
					table.setValueAt(true, row, 0);
					table.setValueAt(idarticulo, row, 1);
					table.setValueAt(descripcion, row, 2);
					table.setValueAt(new Convertidor().getMoney(stock,2), row, 3);
					table.setValueAt(new Convertidor().getMoney(stock,2), row, 4);
					table.setValueAt(linea, row, 5);
					
					
					
					table.changeSelection(row, 1, false,false);
					table.editCellAt(row, 1);
					table.transferFocus();
				}
				
				
				System.out.println(idarticulo+" "+descripcion+" "+cantidad+" "+costo+" "+precio);
				
			}
		}
	
	}
	
	public void buscarArticulo(JTextField tx) {
		if (vArticulo != null) {
			vArticulo.close();
		}
		vArticulo = new aplicacion.herramientas.java.visualizadores.Articulo(
				this.getConstructor());
		
		vArticulo.setPublico(true);
		vArticulo.setSuspendidov(true);
		int n = vArticulo.Buscar(tx);
		if (n == 0) {
			vArticulo.close();
			aviso("No hay articulos con ese codigo");
		}
	}
	
	
	public void _eval_item_cantidad(JTextField tx, int row) {
		cambios=true;
		String cantidad = tx.getText();
		cantidad = cantidad.replace(",", "");
		String idarticulo="";
		double stock=0.0;
		String iddeposito=frame.get_txt_iddeposito().getText();
		try {
			idarticulo = frame.getJTable().getValueAt(row, 1).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (idarticulo.compareTo("")!=0){
			stock=data.getStockArticulo(idarticulo, iddeposito);	
		}
		
		double qty = 0.0;
		boolean error = false;
		try {
			qty = new Double(cantidad);
		} catch (Exception e) {
			error = true;
		}
		if (!error) {
			if (qty  <=0) {
				
				tx.setText(new Convertidor().getMoney(stock,2));
				tx.requestFocusInWindow();
				
				
				
			} else {
				
				tx.setText(new Convertidor().getMoney(qty,2));
				DefaultTableModel model = (DefaultTableModel) this.frame.getJTable()
				.getModel();
				if (row == model.getRowCount() - 1) {
				model.setRowCount(model.getRowCount() + 1);
				} 
				this.editCell(row+1, 1);
			}
		} else {
			tx.setText(new Convertidor().getMoney(stock,2));
			tx.requestFocusInWindow();
			// error("error en cantidad");
		}
		
	}

	
	public void _evaluate_idarticulo(JTextField tx, int row) {
		cambios=true;
		String idarticulo = tx.getText();
		String iddeposito=frame.get_txt_iddeposito().getText();
		
		Object[][] results = data.getArticulo(idarticulo,iddeposito);
		boolean exist = false;
		boolean bloqueado = false;
		
		int equiv=0;
		if (results != null) {
			if (results.length > 0) {
				exist = true;
				String descripcion =(String) results[0][0];
				String stock = (String) results[0][6];
				double _stock=0.0;
				try {
					_stock=new Double(stock);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				boolean ok=true;
				String cantidad="";
				try {
					cantidad=frame.getJTable().getValueAt(row, 3).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				double qty=0.0;
				try {
					qty=new Double(cantidad);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
						
							frame.getJTable().setValueAt(descripcion, row, 2);
							frame.getJTable().setValueAt(new Convertidor().getMoney(stock,2), row, 4);	
							
							frame.getJTable().setValueAt(new Convertidor().getMoney(_stock,2), row, 3);
							frame.getJTable().setValueAt(descripcion, row, 1);
							this.editCell(row, 4);
						} else {
							error("No se encontro el articulo en el deposito "+iddeposito);
							tx.requestFocusInWindow();
						}
						
						//fillExtra(idarticulo);
				
					
					
				}
				
	}				
	
	public void cargar_stock(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		String linea=frame.get_txt_linea().getText();
		String idarticulo_desde=frame.get_txt_articulo_desde().getText();
		String idarticulo_hasta=frame.get_txt_articulo_hasta().getText();
		String iddeposito=frame.get_txt_iddeposito().getText();
		String iddeposito_destino=frame.get_txt_iddeposito_destino().getText();
		String stock=frame.get_txt_Stock().getText();
		String categorias="";
		String categoria="e.categoria_rotacion";
		if (frame.get_chk_A().isSelected()){
			if (categorias.compareTo("")!=0){
				categorias+=" or ";
			}
			categorias+=" "+categoria+" like 'A' ";
		}
		if (frame.get_chk_B().isSelected()){
			if (categorias.compareTo("")!=0){
				categorias+=" or ";
			}
			categorias+=" "+categoria+" like 'B' ";
		}
		if (frame.get_chk_C().isSelected()){
			if (categorias.compareTo("")!=0){
				categorias+=" or ";
			}
			categorias+=" "+categoria+" like 'C' ";
		}
		if (frame.get_chk_D().isSelected()){
			if (categorias.compareTo("")!=0){
				categorias+=" or ";
			}
			categorias+=" "+categoria+" like 'D' ";
		}
		
		
		boolean restriction=frame.get_chk_mostrar_faltante().isSelected();
		boolean ok=false;
		if (iddeposito.compareTo("")!=0 & iddeposito_destino.compareTo("")!=0){
			Object[][] results=data.getStock(idproveedor, linea, idarticulo_desde, idarticulo_hasta,iddeposito,iddeposito_destino,restriction,stock,categorias);
			if (results!=null){
				if (results.length>0){
					ok=true;
					Object[][] tmp=new Object[results.length][results[0].length+1];
					for (int i=0;i<results.length;i++){
						tmp[i][0]=false;
						for (int j=0;j<results[0].length;j++){
							tmp[i][j+1]=results[i][j];
						}
					}
					this.create_table_importar(tmp);
				}
			}	
		}else{
			error("Complete los depositos de Origen y Destino para la transferencia");
		}
		if (!ok){
			frame.setJTable1(null);
		}
		
	}

	public void editar_articulo(JTable table, int row) {
		String idarticulo = table.getValueAt(row, 1).toString();
		if (idarticulo.compareTo("") != 0) {
			editar_articulo(idarticulo);
		}
	}

	public void editar_articulo() {
		int row = -1;
		row = frame.getJTable().getSelectedRow();
		if (row >= 0) {
			this.editar_articulo(frame.getJTable(), row);
		}
	}

	public void editar_articulo(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
		articulo.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		articulo.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		articulo.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		articulo
				.setParameter(
						aplicacion.inventario.articulo.interfaces._parametros.idarticulo,
						idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}
}
