package aplicacion.inventario.control.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.KeyStroke;
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
import aplicacion.inventario.control.constructor.*;
import aplicacion.inventario.control.gui._Etiquetas;
import aplicacion.inventario.control.gui._Frame;
import aplicacion.inventario.control.interfaces.*;
import aplicacion.inventario.control.logic._Data;
import aplicacion.inventario.control.interfaces._Interface;


public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private String tc="CTRL";
	private Object[][] memoria = null;
	private aplicacion.inventario.politica.constructor._Constructor editor_politica = null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.herramientas.java.importar.constructor._Constructor importar = null;
	private aplicacion.herramientas.java.exportar.constructor._Constructor exportar = null;
	private aplicacion.actualizacion.alias.constructor._Constructor alias = null;
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	private aplicacion.herramientas.java.buscadores.Articulo bArticulo = null;
	// variables de tareas swingwork
	
	private String estado="";
	private int current;
	private int lenght;
	private boolean debug,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	
	int errors=0;
	private boolean cambios=false;
	private _Etiquetas etiquetas = null;
	private boolean[] marquer;

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	private void create_empty_table() {
		Object[][] results=new Object[][]{{"","","","","","",""}};
		this.create_table(results,true);
	}
	/**
	 * idarticulo|descripcion|costo|stock|control|ajuste|etiquetas
	 * @param results
	 * @param free
	 */
	private void create_table(Object[][] results,boolean free) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(free);
		col.setClass(String.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_idarticulo);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(340);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("Costo");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(Double.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("stock");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(Double.class);
		table.addColumn(col);

		col = new Column();
		col.setName("control");
		col.setWidth(80);
		col.setEditable(true);
		col.setClass(Double.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_control);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("ajuste");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(Double.class);
		table.addColumn(col);

		col = new Column();
		col.setName("etiquetas");
		col.setWidth(90);
		col.setEditable(true);
		col.setClass(Double.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_etiqueta);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);
		
				table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table);

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		_table.setColumnSelectionAllowed(false);
		_table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),"none" );
		_table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),"none" );
		frame.setJTable(table.getTable());
	}

	private void create_table_cambios(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;
		col = new Column();
		col.setName("id");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(Date.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("fecha");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(Date.class);
		table.addColumn(col);

		col = new Column();
		col.setName("tc");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(340);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		
		col = new Column();
		col.setName("cantidad");
		col.setWidth(80);
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
		//_table.setName(_Interface._table);

		//_table.addMouseListener(this.getConstructor().getMouseListener());
		//_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());

		frame.setJTable1(table.getTable());
	}

	public void clean() {
		frame.setJTable(null);
		frame.get_chk_free().setSelected(false);
		frame.get_txt_fecha().setText("");
		frame.get_txt_fecha_stock().setText("");
		frame.get_chk_punto_control().setSelected(true);
		frame.get_txt_linea().setText("");
		frame.get_txt_control_estado().setText("");
		frame.get_txt_idcontrol().setText("");
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_idcontrol().setEnabled(true);
		frame.get_txt_idcontrol().setEditable(true);
		frame.get_chk_punto_control().setSelected(false);
		frame.get_txt_linea().setEditable(false);
		frame.get_txt_linea().setEnabled(false);
		frame.get_txt_fecha().setEnabled(false);
		frame.get_chk_free().setEnabled(true);
		frame.get_txt_idvendedor().setEnabled(false);
		frame.get_txt_idmovimiento().setEnabled(false);
		frame.get_txt_articulo_desde().setEnabled(false);
		frame.get_txt_articulo_hasta().setEnabled(false);
		frame.get_txt_articulo_desde().setText("");
		frame.get_txt_articulo_hasta().setText("");
		frame.get_txt_vendedor_descripcion().setText("");
		frame.get_txt_cantidad_diferencia().setText("");
		frame.get_txt_cantidad_sistema().setText("");
		frame.get_txt_cantidad_control().setText("");
		frame.get_txt_items_control().setText("");
		frame.get_txt_items_sistema().setText("");
		frame.get_txt_items_diferencia().setText("");
		frame.get_txt_idmovimiento().setText("");
		frame.get_txt_valor_control().setText("");
		frame.get_txt_valor_sistema().setText("");
		frame.get_txt_valor_diferencia().setText("");
		frame.get_txt_iddeposito().setText("");
		frame.get_txt_deposito_descripcion().setText("");
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		frame.get_txt_articulo_linea().setText("");
		frame.get_txt_articulo_bloqueado().setText("");
		frame.get_txt_articulo_actualizacion().setText("");
		frame.setJTable1(null);
		frame.get_chk_punto_control().setSelected(true);
		frame.get_txt_idcontrol().requestFocusInWindow();
	}

	
	public void focus() {
		frame.get_txt_idcontrol().requestFocusInWindow();
	}

	public Object[][] procesarDatos(Object[][] datos) {
		Object[][] tmp = new Object[datos.length][datos[0].length+1];
		
		for (int i = 0; i < datos.length; i++) {
			tmp[i][0] = datos[i][0];// idartiuculo
			tmp[i][1] = datos[i][1];// desc
			tmp[i][2] = datos[i][2];// costo
			
			String stock = (String) datos[i][3];
			double _stock=new Double(stock);
			tmp[i][3] = new Convertidor().roundDouble(_stock, 2);
			
			
			
			String costo = (String) datos[i][2];
			
			
			
			String control = (String) datos[i][4];
			double _control=new Double(control);
			double _costo=0.0;
			try {
				_costo=new Double(costo);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tmp[i][4] = new Convertidor().roundDouble(_control, 2);
			tmp[i][5] = _control-_stock;
			String etiquetas = (String) datos[i][5];
			double _etiquetas=new Double(etiquetas);
			tmp[i][6] = new Convertidor().roundDouble(_etiquetas, 2);
		}
		

		return tmp;
	}

	public void calculate_control(int row,JTextField tx){
		double sum_cantidad_sistema=0.0;
		double sum_cantidad_control=0.0;
		double sum_cantidad_diferencias=0.0;
		double sum_items_control=0.0;
		double sum_items_sistema=0.0;
		double sum_valor_sistema=0.0;
		double sum_valor_control=0.0;
		for (int i=0;i<frame.getJTable().getRowCount();i++){
			String stock = "";
			double _stock=0.0;
			_stock=new Double(frame.getJTable().getValueAt(i, 3).toString().replaceAll(",", ""));
			double _costo=0.0;
			try {
				String costo=(String)frame.getJTable().getValueAt(i, 2);
				_costo=new Double(costo);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_stock>0) {
				sum_items_sistema++;
				sum_valor_sistema+=_costo*_stock;
			}
			
			sum_cantidad_sistema+=_stock;	
			double _control=0.0;
			if (i==row){
				try {
					String control ="";
					if (tx !=null){
						 control=tx.getText();
					}else{
						control=frame.getJTable().getValueAt(i, 3).toString().replaceAll(",", "");
						
					}
					_control=new Double(control);
					if (tx==null){
						frame.getJTable().setValueAt(new Convertidor().roundDouble(_control, 2),i, 3);
					}
					_stock=new Double(frame.getJTable().getValueAt(i, 3).toString().replaceAll(",", ""));
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}else{
				try {
					Object _value=frame.getJTable().getValueAt(i, 4);
					if (_value instanceof String){
						String control = (String) _value;
						_control=new Double(control);
					}else{
						_control=(Double) _value;	
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (_control>0) {
				sum_items_control++;
				sum_valor_control+=_costo*_control;
			}
			sum_cantidad_control+=_control;
		}
		frame.get_txt_cantidad_diferencia().setText(""+new Convertidor().roundDouble(sum_cantidad_control-sum_cantidad_sistema, 2));
		frame.get_txt_cantidad_sistema().setText(""+new Convertidor().roundDouble(sum_cantidad_sistema, 2));
		frame.get_txt_cantidad_control().setText(""+new Convertidor().roundDouble(sum_cantidad_control, 2));
		
		frame.get_txt_items_control().setText(""+new Convertidor().roundDouble(sum_items_control, 2));
		frame.get_txt_items_sistema().setText(""+new Convertidor().roundDouble(sum_items_sistema, 2));
		frame.get_txt_items_diferencia().setText(""+new Convertidor().roundDouble(sum_items_control-sum_items_sistema, 2));
		
		frame.get_txt_valor_control().setText(""+new Convertidor().roundDouble(sum_valor_control, 2));
		frame.get_txt_valor_sistema().setText(""+new Convertidor().roundDouble(sum_valor_sistema, 2));
		frame.get_txt_valor_diferencia().setText(""+new Convertidor().roundDouble(sum_valor_control-sum_valor_sistema, 2));
	}
	
	public void calculate_etiquetas(int row,JTextField tx){
		double sum_etiquetas=0.0;
		
		for (int i=0;i<frame.getJTable().getRowCount();i++){
			
			
			double _control=0.0;
			String control = "";
			if (i==row){
				try {
					control = tx.getText();
					_control=new Double(control);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}else{
				try {
					Object _value=frame.getJTable().getValueAt(i, 6);
					if (_value instanceof String){
						control = (String) _value;
						_control=new Double(control);
					}else{
						_control=(Double) _value;	
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			sum_etiquetas+=_control;
		}
		frame.get_txt_etiquetas().setText(new Convertidor().getMoney(sum_etiquetas, 2));
	}

	public void eliminar(){
		boolean ok=this.confirmar("Confirme para eliminar este control de" +
				" stock integro incluyendo los cambios producidos en el stock", 3);
		if (ok){
			String idvendedor=this.validar_usuario();
			if (idvendedor.compareTo("")!=0){
				System.out.println("ELIMINAR");
				
				
				String idcontrol=frame.get_txt_idcontrol().getText();
				String ip=data.getIp();
				data.registrar_operacion(tc, idcontrol, "", idvendedor, ip, "ELIMINACION DE CONTROL");
				List<String> eliminar=new ArrayList<String>();
				eliminar.add(data.getEliminarStock(idcontrol));
				eliminar.add(data.getEliminarControl(idcontrol));
				
				data.beginTransaction();
				data.clearBatch();
				for (int i=0;i<eliminar.size();i++){
		 			System.out.println(eliminar.get(i));
		 			data.addBatch((eliminar.get(i)));
		 		}	
				boolean error=data.executeBatch();
				if (!error){
					data.commitTransaction();
					aviso("Se Elimino Correctamente");
				}else {
					data.rollbackTransaction();
					aviso("Error al Eliminar");
				}	
			}else{
				error("OPERACION CANCELADA");
			}
			
		}
	}
	
	
	public void eliminar_aplicacion(){
		boolean ok=this.confirmar("Confirme para eliminar los  cambios producidos del stock", 3);
		if (ok){
			System.out.println("ELIMINAR APLICACION");
			String idcontrol=frame.get_txt_idcontrol().getText();
			List<String> eliminar=new ArrayList<String>();
			eliminar.add(data.getDeleteStock(idcontrol));
			
			data.beginTransaction();
			data.clearBatch();
			for (int i=0;i<eliminar.size();i++){
	 			System.out.println(eliminar.get(i));
	 			data.addBatch((eliminar.get(i)));
	 		}	
			boolean error=data.executeBatch();
			if (!error){
				
				data.commitTransaction();
				aviso("Se Elimino Correctamente los Cambios de Stock Producidos Anteriormente");
			}else {
				data.rollbackTransaction();
				aviso("Error al Eliminar Cambios de Stock");
			}
		}
	}
	
	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;
	public void reporte(){
		if (reporte!=null){
			 reporte.dispose();
		}
		String idcontrol=frame.get_txt_idcontrol().getText();
		if (data.exist(idcontrol)){
			reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
			HashMap param=new HashMap();
			param.put("idcontrol",idcontrol);
			
			reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
			reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
			reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
			String[] options=new String[]{"Reporte de Ajustes","Reporte Control Completo"};
			int n=this.preguntar("Seleccione Reporte", "Seleccione El tipo de Reporte", options, options[0]);
			if (n==0){
				reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_control.jasper");	
			}else{
				reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_control_completo.jasper");
			}
			
			reporte.build(this.getConstructor());
			reporte.init();	
		}
			
	}
	
	public void obtener_proximo_cpte() {
		String idcontrol = data.getProximoPGCorrecto(tc);
		frame.get_txt_idcontrol().setText(idcontrol);
	}
	
	public void nuevo(){
		this.clean();
		this.obtener_proximo_cpte();
		frame.get_chk_punto_control().setSelected(true);
		frame.get_txt_fecha().setText(data.getSystemDate());
		frame.get_txt_fecha_stock().setText(data.getSystemDate());
		frame.get_txt_idcontrol().requestFocusInWindow();
		
	}
	
	public void cargar() {
		this.evaluarControl(frame.get_txt_idcontrol());
	}

	
	private void transfer_focus(int row, int col) {
		frame.getJTable().changeSelection(row, col, false, false);
		frame.getJTable().editCellAt(row, col);
		frame.getJTable().transferFocus();
	}
	
	public void evaluate_free(JCheckBox free){
		if (free.isSelected()){
			frame.get_txt_articulo_desde().setEditable(false);
			frame.get_txt_articulo_desde().setText("");
			frame.get_txt_articulo_hasta().setEditable(false);
			frame.get_txt_articulo_hasta().setText("");
			frame.get_txt_idvendedor().requestFocusInWindow();
		}else{
			frame.get_txt_articulo_desde().setEditable(true);
			frame.get_txt_articulo_hasta().setEditable(true);
			frame.get_txt_articulo_desde().requestFocusInWindow();
			
			
		}
	}
	public void cargar_datos(String idcontrol) {
		String iddeposito=frame.get_txt_iddeposito().getText();
		String fecha=frame.get_txt_fecha_stock().getText();
		String idmovimiento=frame.get_txt_idmovimiento().getText();
		String idarticulo_desde=frame.get_txt_articulo_desde().getText();
		String idarticulo_hasta=frame.get_txt_articulo_hasta().getText();
		boolean free=frame.get_chk_free().isSelected();
		Object[][] results=null;
		if (free){
			results=data.getControlItemsFree(idcontrol,iddeposito,fecha,idmovimiento);
		}else{
			results=data.getControlItems(idcontrol,iddeposito,fecha,idmovimiento);	
		}
		
		if (results!=null){
			if (results.length >0) {
				results=this.procesarDatos(results);
				this.create_table(results,free);
				
			}
		}
		String linea=frame.get_txt_linea().getText();
		if (!free){
			Object[][] results2=data.getMovimientos(idmovimiento, linea, iddeposito);
			if (results2!=null){
				if (results2.length >0) {
					
					this.create_table_cambios(results2);
				}
			}	
		}
		
		this.calculate_control(-1, null);
		this.calculate_etiquetas(-1, null);
		this.cambios=false;
		if (free){
			frame.getJTable().changeSelection(0, 0, false, false);
			frame.getJTable().editCellAt(0, 0);
			frame.getJTable().transferFocus();
		}
	}
	
	
	public boolean crear_pedido(){
		
		
		String idcontrol=frame.get_txt_idcontrol().getText();
		String fecha=frame.get_txt_fecha().getText();
		String iddeposito=frame.get_txt_iddeposito().getText();
		String idusuario=frame.get_txt_idvendedor().getText();
		String linea=frame.get_txt_linea().getText();
		String desde="001-000";
		String hasta="999-zzz";
		String punto="0";
		if (frame.get_chk_punto_control().isSelected()){
			punto="1";
		}
		String free="0";
		if (frame.get_chk_free().isSelected()){
			free="1";
		}
		if (frame.get_txt_articulo_desde().getText().compareTo("")!=0){
			desde=frame.get_txt_articulo_desde().getText();
		}
		if (frame.get_txt_articulo_hasta().getText().compareTo("")!=0){
			hasta=frame.get_txt_articulo_hasta().getText();
		}
		boolean error=!data.getStock(linea, desde, hasta);
		if (!error){
			String q=data.getInsert(idcontrol, fecha, iddeposito, idusuario, linea,desde,hasta,punto,free);
			data.beginTransaction();
			data.clearBatch();
			data.addBatch(q);
			q="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"' ";
			data.addBatch(q);
			error=data.executeBatch();
			if (!error){
				data.commitTransaction();
				aviso("Se creo correctamente el control "+idcontrol);
			}else{
				data.rollbackTransaction();
				error("Error creando el control");
			}	
		}else{
			error("Revise Los Datos de linea y articulos para que sean correctos!");
		}
		return !error;
	}
	
	private aplicacion.herramientas.java.evaluadores.Control Control=null;
	public void initialize_Control(){
		
		Control=new aplicacion.herramientas.java.evaluadores.Control(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String idusuario=(String) results[0][1];
				String linea=(String) results[0][2];
				String fecha=(String) results[0][3];
				String iddeposito=(String) results[0][4];
				String idmovimiento=(String) results[0][5];
				String desde=(String) results[0][6];
				String hasta=(String) results[0][7];
				String punto=(String) results[0][8];
				String free="";
				try{
				free=(String) results[0][9];
				}catch(Exception ex){
					
				}
				frame.get_txt_linea().setEditable(false);
				frame.get_txt_fecha().setEditable(false);
				frame.get_txt_idvendedor().setEditable(false);
				frame.get_txt_idcontrol().setEditable(false);
				frame.get_txt_iddeposito().setEditable(false);
				frame.get_txt_idmovimiento().setEditable(false);
				frame.get_txt_articulo_desde().setEditable(false);
				frame.get_txt_articulo_hasta().setEditable(false);
				frame.get_chk_punto_control().setSelected(punto.compareTo("1")==0);
				frame.get_chk_free().setSelected(free.compareTo("1")==0);
				frame.get_txt_linea().setText(linea);
				frame.get_txt_fecha().setText(fecha);
				frame.get_txt_idvendedor().setText(idusuario);
				frame.get_txt_idcontrol().setEditable(false);
				frame.get_txt_iddeposito().setText(iddeposito);
				frame.get_txt_idmovimiento().setText(idmovimiento);
				frame.get_txt_articulo_desde().setText(desde);
				frame.get_txt_articulo_hasta().setText(hasta);
				if (data.aplicado(codigo)){
					frame.get_txt_iddeposito().setEditable(false);
					frame.get_btn_buscar_deposito().setEnabled(false);
				}else{
					frame.get_txt_iddeposito().setEditable(true);
					frame.get_btn_buscar_deposito().setEnabled(true);
				}
				frame.get_chk_free().setEnabled(false);
				evaluarVendedor(frame.get_txt_idvendedor());
				evaluarDeposito(frame.get_txt_iddeposito());		
				cargar_datos(codigo);
				frame.get_txt_idvendedor().setEnabled(true);
				frame.get_txt_idvendedor().setEditable(true);
			}
		};
		Control.setConstructor(this.getConstructor());
	}
	
	public void BuscarControl(JTextField tx){
			Control.Buscar(tx);	
		
		
	}
	public void BuscarControl(){
		String iddeposito=frame.get_txt_iddeposito().getText();;
		if (iddeposito.compareTo("")!=0){
			Control.setDeposito(iddeposito);
			Control.Buscar(frame.get_txt_idcontrol());	
		}else{
			error("Defina el Deposito");
		}
		
	}
	public void buscarControl(JTextField tx){
			Control.buscar(tx);	
		
		
	}
	
	public void evaluarControl(JTextField tx){
		
			String _nuevo=data.getProximoPGCorrecto(tc);
			boolean nuevo=_nuevo.compareTo(tx.getText())==0;
			
			if (nuevo){
				nuevo=confirmar("Confirmar la creacion de un control nuevo: ",2);
				if (nuevo){
					frame.get_txt_idcontrol().setEditable(false);
					frame.get_txt_iddeposito().setEditable(true);
					frame.get_txt_iddeposito().setEditable(true);
					frame.get_txt_linea().setEditable(true);
					frame.get_txt_linea().setEnabled(true);
					frame.get_txt_fecha().setEnabled(true);
					frame.get_txt_idvendedor().setEnabled(true);
					frame.get_txt_idcontrol().setEnabled(true);
					frame.get_txt_iddeposito().setEnabled(true);
					frame.get_txt_idmovimiento().setEnabled(true);
					frame.get_txt_articulo_desde().setEnabled(true);
					frame.get_txt_articulo_hasta().setEnabled(true);
					
					int id=data.getIdMovimiento();
					if (id>0){
						frame.get_txt_idmovimiento().setText(""+id);	
					}
					
					frame.get_txt_fecha().setText(data.getSystemDate());
					frame.get_txt_fecha_stock().setText(data.getSystemDate());
					frame.get_txt_iddeposito().requestFocusInWindow();
				}
			}else{
				Control.evaluate(tx);
				
			}
		
		
	}
	
	private aplicacion.herramientas.java.evaluadores.Fecha Fecha=null;
	public void initialize_Fecha(){
		Fecha=new aplicacion.herramientas.java.evaluadores.Fecha(){
			public void cargar(JTextField tx){
			 transfer_fecha_focus(tx);
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
	public void BuscarFechaLimite(){
		Fecha.Buscar(frame.get_txt_fecha_stock());
	}
	public void evaluarFecha(JTextField tx){
		cambios=true;
		Fecha.evaluate(tx);
	}
	
	public void transfer_fecha_focus(JTextField tx){
		
	}
	
	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;
	public void goMa_Articulos(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
		articulo.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme()
				);
		articulo.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		articulo
				.setParameter(
						aplicacion.inventario.articulo.interfaces._parametros.idarticulo,
						idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}
	
	private List<String> getInstruccionesDeEncabezadoStock(){
		List<String> instrucciones=new ArrayList<String>();
		String tc="CTRL";
		String idcomprobante=frame.get_txt_idcontrol().getText();
		String instruccion=data.disable_triggers();
		instrucciones.add(instruccion);
		instruccion=data.getDeleteStock(idcomprobante);
		instrucciones.add(instruccion);
		String fecha=frame.get_txt_fecha().getText();
		String iddeposito=frame.get_txt_iddeposito().getText();
		String iduser=this.getConstructor().getIduser();
		String idmovimiento=frame.get_txt_idmovimiento().getText();
		String punto="0";
		if (frame.get_chk_punto_control().isSelected()){
			punto="1";
		}
		instruccion=data.getUpdateQuery(idcomprobante,fecha,iddeposito,iduser,idmovimiento,punto);
		return instrucciones;
	}
	
	private List<String> getInstruccionesDeStock(){
		List<String> instrucciones=new ArrayList<String>();
	
		String idcomprobante=frame.get_txt_idcontrol().getText();
		String fecha=frame.get_txt_fecha().getText();
		String iddeposito=frame.get_txt_iddeposito().getText();
		int i=0;
		while(i<frame.getJTable().getRowCount() & !canceled){
			String _idarticulo="";
			try {
				_idarticulo = frame.getJTable().getValueAt(i, 0).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_idarticulo.compareTo("")!=0){
				String _descripcion=frame.getJTable().getValueAt(i, 1).toString();
				String _ajuste=frame.getJTable().getValueAt(i, 5).toString().replaceAll(",", "");
				String _precio=frame.getJTable().getValueAt(i, 2).toString().replaceAll(",", "");
				double ajuste=new Double(_ajuste);
				if (ajuste>0 | ajuste < 0){
					String _instruccioni=data.getInsertQueryVMVStock(tc, idcomprobante, _idarticulo, _descripcion, ""+ajuste, i, fecha, _precio,iddeposito);
					instrucciones.add(_instruccioni);	
				}	
			}
			
			i++;
		}
		return instrucciones;
	}
	
	public String validar_usuario(){
		String idvendedor="";
		String password=this.requestPassword("Ingrese Su Clave:");
		idvendedor=data.getUserValidacion(password);
		if (idvendedor.compareTo("")==0){
			error("Error de Validacion de Usuario");
		}
		return idvendedor;
	}
	
	public void autocompletar(){
		int i=0;
 		while(i<frame.getJTable().getRowCount() & !canceled){
 			String _stock=frame.getJTable().getValueAt(i, 3).toString().replaceAll(",", "");
 			double stock=0;
 			try {
				stock=new Double(_stock);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (stock<0){
				stock=0;
			}
			frame.getJTable().setValueAt(stock, i,4);
			frame.getJTable().setValueAt(0, i,5);
			current++;
 			i++;
 		}
	}
	private List<String> getInstruccionesDeItemsControl(){
		estado="PREPARANDO INSTRUCCIONES";
		List<String> instrucciones=new ArrayList<String>();
		String tc="CTRL";
		String idcomprobante=frame.get_txt_idcontrol().getText();
		String fecha=frame.get_txt_fecha().getText();
		current=0;
		lenght=frame.getJTable().getRowCount();
		int i=0;
 		while(i<frame.getJTable().getRowCount() & !canceled){
			String _idarticulo="";
			try {
				_idarticulo = frame.getJTable().getValueAt(i, 0).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_idarticulo.compareTo("")!=0){
				String _descripcion=frame.getJTable().getValueAt(i, 1).toString();
				String _precio=frame.getJTable().getValueAt(i, 2).toString().replaceAll(",", "");
				String _stock=frame.getJTable().getValueAt(i, 3).toString().replaceAll(",", "");
				String _cantidad=frame.getJTable().getValueAt(i, 4).toString().replaceAll(",", "");
				String _ajuste=frame.getJTable().getValueAt(i, 5).toString().replaceAll(",", "");
				String _etiqueta=frame.getJTable().getValueAt(i, 6).toString().replaceAll(",", "");
				double stock=new Double(_stock);
				double ajuste=new Double(_ajuste);
				double cantidad=new Double(_cantidad);
				double etiqueta=new Double(_etiqueta);
				String _instruccioni="";
				String idusuario=frame.get_txt_idvendedor().getText();
				
				if (cantidad>0 | etiqueta>0 | ajuste!=0){
					String _instruccion=data.insertControlHistory(idcomprobante, _idarticulo, cantidad, etiqueta, idusuario, stock,ajuste);
					instrucciones.add(_instruccion);	
				}
				
				if (data.existeItem(idcomprobante, _idarticulo)){
					if (cantidad<=0 & etiqueta<=0 & ajuste==0){
						_instruccioni=data.deleteControlItem(idcomprobante, _idarticulo, cantidad);
					}else{
						_instruccioni=data.updateControlItem(idcomprobante, _idarticulo, cantidad,etiqueta);	
					}
				}else{
					if (cantidad>0 | etiqueta>0 | ajuste!=0){
						_instruccioni=data.insertControlItem(idcomprobante, _idarticulo, cantidad,etiqueta);
					}
					
				}
				if (_instruccioni.compareTo("")!=0){
					instrucciones.add(_instruccioni);	
						
				}
				
			}
				
			
			current++;
		    i++;	
		}
		return instrucciones;
	}
	
	public void check_usuario_control_nuevo(String idcontrol){
		if (data.exist(idcontrol)){
			
		}else{
			boolean ok=this.crear_pedido();
			if (ok){
				if (frame.get_chk_free().isSelected()){
					frame.get_chk_free().setEnabled(false);
					this.create_empty_table();
					frame.getJTable().changeSelection(0, 0, false, false);
					frame.getJTable().editCellAt(0, 0);
					frame.getJTable().transferFocus();
				}else{
					this.cargar_datos(idcontrol);	
				}
				
			}
		}
	}

	
	public void _taskworkAutocompletarStock(){
		errors=0;
		current=0;
		estado="Aplicando Cambios de Stock";
		if (frame.getJTable()!=null){
			this.autocompletar();
		}
		done=true;
	}
	public void _taskworkAplicarStock(){
		
			System.out.println("Aplicar Stock");
			String idcontrol=frame.get_txt_idcontrol().getText();
			String delete=data.getDeleteStock(idcontrol);
			System.out.println(delete);
			data.beginTransaction();
	 		data.clearBatch();
			data.addBatch(delete);
			errors=0;
			current=0;
			boolean error=data.executeBatch();
			estado="Aplicando Cambios de Stock";
			if (!error){
				List<String> stock=this.getInstruccionesDeStock();
				int operations=stock.size();
				this.lenght=stock.size();
				int i=0;
				while(i<stock.size() & !canceled){
		 			System.out.println(stock.get(i));
		 			data.clearBatch();
		 			data.addBatch(stock.get(i));
		 			error=data.executeBatch();
		 			if (error){
		 				errors++;
		 			}
		 			current++;
		 			i++;
		 		}
				data.commitTransaction();
				aviso("Operacion Guardar Finalizada con "+operations+" operaciones y "+errors+" errores ");
			}else{
				data.rollbackTransaction();
			}
			done=true;
		
	}
	
	public void _taskworkGuardar(){
		System.out.println("Guardar");
		List<String> encabezado=this.getInstruccionesDeEncabezadoStock();
		List<String> items=this.getInstruccionesDeItemsControl();
 		current=0;
 		data.beginTransaction();
 		data.clearBatch();
 		String iddeposito=frame.get_txt_iddeposito().getText();
 		String fecha=frame.get_txt_fecha().getText();
 		String idcontrol=frame.get_txt_idcontrol().getText();
 		String iduser=frame.get_txt_idvendedor().getText();
 		String idmovimiento=frame.get_txt_idmovimiento().getText();
 		String punto="0";
		if (frame.get_chk_punto_control().isSelected()){
			punto="1";
		}
 		data.addBatch(data.getUpdateQuery(idcontrol, fecha, iddeposito,iduser,idmovimiento,punto));
 		for (int i=0;i<encabezado.size();i++){
 			System.out.println(encabezado.get(i));
 			data.addBatch(encabezado.get(i));
 		}
 		errors=0;
 		boolean error=data.executeBatch();
 		estado="Guardando Control";
 		
 		
 			int operations=items.size();
 			this.lenght=items.size();
 			int i=0;
 			
 			while(i<items.size() & !canceled){
 				data.clearBatch();	
 				String qx=items.get(i);
 				System.out.println(qx);
 	 			data.addBatch(qx);
 	 			error=data.executeBatch();
 	 			if (error){
 	 				errors++;
 	 			}
 	 			current++;
 	 			i++;
 	 		}
 			data.commitTransaction();
 			done=true;
 			cambios=false;
 			aviso("Operacion Guardar Finalizada con "+operations+" operaciones y "+errors+" errores ");
 		
 		
	}
	
	public void _taskworkCheck(){
		System.out.println("Check");
		current=0;
 		
 		
 			
 			this.lenght=frame.getJTable().getRowCount();
 			int i=0;
 			int operations=0;
 			while(i<lenght & !canceled){
 					
 				this.evaluate_control_row(i);
 				operations++;
 	 			current++;
 	 			i++;
 	 		}
 			data.commitTransaction();
 			done=true;
 			cambios=false;
 			aviso("Operacion Check Finalizada con "+operations+" operaciones y "+errors+" errores ");
 		
 		
	}
	
	//metodos basicos de tareas swing
	public void createTimer(){
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
		Timer=new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					endbar();
					Timer.stop();
					}else {
						
					updateBar();
				}
			}
		}); 
		
	}
	
	
	public void updateBar(){
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(estado+" "+current+"/"+lenght+" "+crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}
	
	public void endbar(){
		estado="";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
		frame.get_btn_cargar().setEnabled(true);
		frame.get_btn_guardar().setEnabled(true);
	}
	
	public void doCancel(){
		if (preguntar("confirmar","Cancela Tarea?")){
			this.canceled=true;
			frame.get_btn_cancel_task().setEnabled(true);
			
		}
	}
	
	public void check() {
			this.goCheck();
		
	}
	public void Guardar() {
		String iduser=this.validar_usuario();
		if (iduser.compareTo("")!=0){
			String idvendedor=(String)data.getVendedor(iduser)[0][0];
			frame.get_txt_idvendedor().setText(idvendedor);
			this.evaluarVendedor(frame.get_txt_idvendedor());
			this.goGuardar();
		}else{
			error("Operacion Cancelada");
		}
	}
	private void goGuardar() {
		
		String iddeposito=frame.get_txt_iddeposito().getText();
		if (this.Deposito.existe(iddeposito)){
			this.createTimer();
			SwingWorker worker=null;
				worker = new SwingWorker() {
					@Override
					public Object construct() {
						return new _taskGuardar();
					}
				};
				if (Timer!=null) {
					Timer.start();
				}
				worker.start();
				
		}else{
			error("Ingrese El Deposito");
		}
	}
	
	
private void goCheck() {
		
		String iddeposito=frame.get_txt_iddeposito().getText();
		if (this.Deposito.existe(iddeposito)){
			this.createTimer();
			SwingWorker worker=null;
				worker = new SwingWorker() {
					@Override
					public Object construct() {
						return new _taskCheck();				}
				};
				if (Timer!=null) {
					Timer.start();
				}
				worker.start();
				
		}else{
			error("Ingrese El Deposito");
		}
	}
	public void aplicarStock(){
		if (!cambios){
			boolean ok=this.confirmar("Confirme para aplicar cambios al stock", 3);
			if (ok){
				this.goAplicarStock();
			}	
		}else{
			aviso("Se detectaron cambios en el control. Antes de Aplicar los cambios de Stock. Debe guardar el control");
		}
		
	}
	
	public void goAutocompletarStock() {
		error("Atencion: Esta Operacion va a Sobrescribir los valores actuales de la columna control");
		if (this.confirmar("Confirme para sobrescribir los valores de control: ", 2)){
			this.createTimer();
			SwingWorker worker=null;
				worker = new SwingWorker() {
					@Override
					public Object construct() {
						return new _taskAutocompletarStock();
					}
				};
				if (Timer!=null) {
					Timer.start();
				}
				worker.start();	
		}
		
		}
	
	public void goAplicarStock() {
		this.createTimer();
		SwingWorker worker=null;
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					return new _taskAplicarStock();
				}
			};
			if (Timer!=null) {
				Timer.start();
			}
			worker.start();
		}
	
	class _taskGuardar {
		_taskGuardar() {
			_taskworkGuardar();
			}
	}
	
	class _taskCheck {
		_taskCheck() {
			_taskworkCheck();
			}
	}
	
	class _taskAplicarStock {
		_taskAplicarStock() {
			_taskworkAplicarStock();
			}
	}
	
	class _taskAutocompletarStock {
		_taskAutocompletarStock() {
			_taskworkAutocompletarStock();
			}
	}
	public void up_control(JTextField tx,int row){
		try {
			frame.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (row>0){
			frame.getJTable().requestFocusInWindow();
			frame.getJTable().changeSelection(row-1, 4, false, false);
			frame.getJTable().editCellAt(row-1, 4);
			frame.getJTable().transferFocus();	
		}
		
	}
	public void down_control(JTextField tx,int row){
		try {
			frame.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (row<frame.getJTable().getRowCount()-1){
			frame.getJTable().requestFocusInWindow();
			frame.getJTable().changeSelection(row+1, 4, false, false);
			frame.getJTable().editCellAt(row+1, 4);
			frame.getJTable().transferFocus();	
		}
		
	}
	
	public void borrarRenglon(JTextField tx,int row) {
		if (tx.getText().compareTo("")==0){
			if (preguntar("Confirmar", "Elimina Renglon " + row + " de la tabla?")) {
				try {
					frame.getJTable().getCellEditor().stopCellEditing();
				} catch (Exception e) {
				}
				DefaultTableModel model = (DefaultTableModel) frame.getJTable()
						.getModel();
				model.removeRow(row);
				if (model.getRowCount() <= 0) {
					model.setRowCount(1);
					model.setValueAt(true, 0, 0);
				}
			}	
		}
		
	}
	
	
	public void evaluate_control_row(int row){
		String valor=frame.getJTable().getValueAt(row, 4).toString();
		cambios=true;
		boolean suma=false;
		boolean resta=false;
		if (valor.substring(0, 1).compareTo("+")==0){
			suma=true;
			valor=valor.substring(1);
		}
		if (valor.substring(0, 1).compareTo("-")==0){
			resta=true;
			valor=valor.substring(1);
		}
		
		double control=0.0;
		boolean error=false;
		try {
			control=new Double(valor.replaceAll(",", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;
		}
		if (!error){
			if (control>=0){
				if (row<=frame.getJTable().getRowCount()-1){
					String cant="";
					try {
						cant = frame.getJTable().getValueAt(row, 3).toString();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					double cantidad=0.0;
					try {
						cantidad=new Double(cant);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (suma){
						control=cantidad+control;
					}
					if (resta){
						control=cantidad-control;
					}
					double ajuste=control-cantidad;
					frame.getJTable().setValueAt(new Convertidor().getMoney(control, 2),row, 4);
					this.calculate_control(row, null);
					frame.getJTable().setValueAt(ajuste, row, 5);
				}
			}
		}
	}
	
	public void evaluate_control(JTextField tx,int row){
		String valor=tx.getText();
		cambios=true;
		boolean suma=false;
		boolean resta=false;
		if (valor.substring(0, 1).compareTo("+")==0){
			suma=true;
			valor=valor.substring(1);
		}
		if (valor.substring(0, 1).compareTo("-")==0){
			resta=true;
			valor=valor.substring(1);
		}
		
		double control=0.0;
		boolean error=false;
		try {
			control=new Double(valor.replaceAll(",", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;
		}
		if (!error){
			if (control>=0){
				if (row<=frame.getJTable().getRowCount()-1){
					String cant="";
					try {
						cant = frame.getJTable().getValueAt(row, 3).toString();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					double cantidad=0.0;
					try {
						cantidad=new Double(cant);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (suma){
						control=cantidad+control;
					}
					if (resta){
						control=cantidad-control;
					}
					double ajuste=control-cantidad;
					tx.setText(new Convertidor().getMoney(control, 2));
					this.calculate_control(row, tx);
					frame.getJTable().setValueAt(ajuste, row, 5);
					if (!frame.get_chk_free().isSelected()){
						frame.getJTable().changeSelection(row+1, 4, false, false);
						frame.getJTable().editCellAt(row+1, 4);
						frame.getJTable().transferFocus();	
					}else{
						DefaultTableModel model=(DefaultTableModel) frame.getJTable().getModel();
						if (model.getRowCount()-1==row){
							model.setRowCount(model.getRowCount()+1);
						}
						frame.getJTable().changeSelection(row+1, 0, false, false);
						frame.getJTable().editCellAt(row+1, 0);
						frame.getJTable().transferFocus();
						
					}
					
					
				}
				
			}else{
				
				error("La cantidad controlada no puede ser menor a cero");
				tx.requestFocusInWindow();
			}
		}else{
			error("Error en cantidad, verifique. No puede ser menor a cero ");
			tx.requestFocusInWindow();
		}
	}
	
	
	public void evaluate_etiqueta(JTextField tx,int row){
		String valor=tx.getText();
		cambios=true;
		double etiqueta=0.0;
		boolean error=false;
		try {
			etiqueta=new Double(valor.replaceAll(",", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=true;
		}
		if (!error){
			if (etiqueta>=0){
				if (row<frame.getJTable().getRowCount()-1){
					String cant=frame.getJTable().getValueAt(row, 6).toString();
					double cantidad=0.0;
					try {
						cantidad=new Double(cant);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//tx.setText(new Convertidor().getMoney(cantidad, 2));
					
					this.calculate_etiquetas(row, tx);
					
					frame.getJTable().changeSelection(row+1, 6, false, false);
					frame.getJTable().editCellAt(row+1, 6);
					frame.getJTable().transferFocus();
					
				}
				
			}else{
				error("La cantidad controlada no puede ser menor a cero");
				tx.requestFocusInWindow();
			}
		}else{
			error("Error en cantidad, verifique. No puede ser menor a cero ");
			tx.requestFocusInWindow();
		}
	
	}
	
	public void focus(JTable table,int row,int col) {
		table.changeSelection(row,col,false,false);
		table.editCellAt(row,col);
		table.transferFocus();
	}
	
	public void imprimir_etiquetas() {
		
		LinkedList l = new LinkedList();
		try {
			etiquetas.getJTable().clearSelection();
			etiquetas.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {

		}
			etiquetas.getJTable().setEnabled(false);
			int etqs=0;
			for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
				boolean b = false;
				try {
					b = (Boolean) etiquetas.getJTable().getValueAt(i, 0);
				} catch (Exception e) {

				}
				if (b) etqs++;
			}
			if (etqs>0){
				if (confirmar("Confirme Para Imprimir Etiquetas",2)){
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
						idarticulo = etiquetas.getJTable().getValueAt(i, 1).toString();
						descripcion = etiquetas.getJTable().getValueAt(i, 2).toString();
						cant = etiquetas.getJTable().getValueAt(i, 3).toString();
						_cant = new Double(cant);
					} catch (Exception e) {

					}
					if (b & _cant >= 1) {
						String q="insert into b_etiquetas (fecha,idarticulo,descripcion,cantidad) ";
						q+="values (getdate(),'"+idarticulo+"','"+descripcion+"',"+_cant+")";
						data.addBatch(q);
					}
					
				}
				data.executeBatch();
				data.commitTransaction();
				etiquetas.setVisible(false);
				etiquetas.dispose();
				}
					
			}else{
				aviso("No hay etiquetas para imprimir");
				if (preguntar("Etiquetas","Cierra Ventana de Etiquetas?")){
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
			int qtys=0;
			for (int i=0;i<frame.getJTable().getRowCount();i++){
				
				double qty=0.0;
				try {
					Object _value=frame.getJTable().getValueAt(i, 6);
					if (_value instanceof Double){
						qty=(Double) frame.getJTable().getValueAt(i, 6);
					}else{
						qty=new Double((String)_value);
					}
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (qty>0){
					qtys++;
				}
			}
			
			Object[][] results = new String[qtys][3];
			qtys=0;
			for (int i=0;i<frame.getJTable().getRowCount();i++){
				String idarticulo=(String) frame.getJTable().getValueAt(i, 0);
				String descripcion=(String) frame.getJTable().getValueAt(i, 1);
				double qty=0.0;
				try {
					Object _value=frame.getJTable().getValueAt(i, 6);
					if (_value instanceof Double){
						qty=(Double) _value;	
					}else{
						qty=new Double((String) _value);
					}
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (qty>0){
					results[qtys][0]=idarticulo;
					results[qtys][1]=descripcion;
					results[qtys][2]=""+qty;
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
						String descripcion="";
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
	
	public void cargarDeposito(){
		String iddeposito=this.getConstructor().getIdDeposito();
		
				frame.get_txt_iddeposito().setText(iddeposito);
				this.evaluarDeposito(frame.get_txt_iddeposito());
	}
	
	private aplicacion.herramientas.java.evaluadores.Deposito Deposito=null;
	public void initialize_Deposito(){
		Deposito=new aplicacion.herramientas.java.evaluadores.Deposito(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				String cod=(String) results[0][0];
				frame.get_txt_iddeposito().setText(cod);
				frame.get_txt_deposito_descripcion().setText(descripcion);
				frame.get_txt_linea().requestFocusInWindow();
			}
		};
		Deposito.setConstructor(this.getConstructor());
	}
	public void BuscarDeposito(JTextField tx){
		Deposito.Buscar(tx);
	}
	public void BuscarDeposito(){
		Deposito.Buscar(frame.get_txt_iddeposito());
	}
	public void buscarDeposito(JTextField tx){
		Deposito.buscar(tx);
	}
	
	public void evaluarDeposito(JTextField tx){
		cambios=true;
		tx.setText(tx.getText().replaceAll(" ", ""));
		Deposito.evaluate(tx);
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
	
	private aplicacion.herramientas.java.visualizadores.Linea vLinea=null;
	public void buscarLinea(JTextField tx) {
		if (vLinea!=null){
			vLinea.close();
		}
		vLinea=new aplicacion.herramientas.java.visualizadores.Linea(this.getConstructor());
		//vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLinea(tx);
		if (n == 0) {
				aviso("no hay Lineas con ese codigo");
		}
	}
	
	
	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor=null;
	public void initialize_Vendedor(){
		Vendedor=new aplicacion.herramientas.java.evaluadores.Vendedor(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				String cod=(String) results[0][0];
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_vendedor_descripcion().setText(descripcion);
				String idcontrol=frame.get_txt_idcontrol().getText();
				check_usuario_control_nuevo(idcontrol);
			}
		};
		Vendedor.setConstructor(this.getConstructor());
	}
	
	public void reconnect_Vendedor(){
		if (Vendedor!=null){
			Vendedor.setConstructor(this.getConstructor());	
		}
		
	}
	public void BuscarVendedor(JTextField tx){
		Vendedor.Buscar(tx);
	}
	public void BuscarVendedor(){
		Vendedor.Buscar(frame.get_txt_idvendedor());
	}
	public void buscarVendedor(JTextField tx){
		Vendedor.buscar(tx);
	}
	
	public void evaluarVendedor(JTextField tx){
		cambios=true;
		tx.setText(tx.getText().replaceAll(" ", ""));
		Vendedor.evaluate(tx);
	}
	
	public void fillExtra(String idarticulo) {
		
		Object[][] results = data.getData(idarticulo);
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		if (results != null) {
			if (results.length > 0) {
				Convertidor cv=new Convertidor();
				String _articulo = (String) results[0][0];
				String _descripcion = (String) results[0][1];
				String _linea = (String) results[0][2];
				String _stock = (String) results[0][3];
				String _suspendidov = (String) results[0][4];
				String _actualizacion = (String) results[0][5];
				frame.get_txt_articulo().setText(_articulo);
				frame.get_txt_articulo_descripcion().setText(_descripcion);
				frame.get_txt_articulo_stock().setText(_stock);
				frame.get_txt_articulo_linea().setText(_linea);
				
				if (_suspendidov.compareTo("1")==0){
					frame.get_txt_articulo_bloqueado().setText("BLOQUEADO");
					frame.get_txt_articulo_bloqueado().setBackground(Color.red);
				}else{
					frame.get_txt_articulo_bloqueado().setText("HABILITADO");
					frame.get_txt_articulo_bloqueado().setBackground(Color.green);
				}
				
				if (_actualizacion.compareTo("")==0){
					frame.get_txt_articulo_actualizacion().setBackground(Color.red);
					_actualizacion="SIN FECHA";
				}else{
					if (this.eval_venc(_actualizacion)){
						frame.get_txt_articulo_actualizacion().setBackground(Color.green);
					}else{
						frame.get_txt_articulo_actualizacion().setBackground(Color.red);
					}	
				}
				frame.get_txt_articulo_actualizacion().setText(_actualizacion);
				
				double _pedido=data.getPedidoCantidad(idarticulo);
				double stock=new Double(_stock);
				frame.get_txt_articulo_pedido().setText(cv.getMoney(_pedido, 2));
			}
		}
	}

	public void _eval_item_articulo(JTextField tx, int row) {
		String aux = "";
		
		tx.setBackground(Color.GREEN);
		try {
			aux = tx.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		tx.requestFocusInWindow();
		if (aux.compareTo("") != 0) {
			if (aux.compareTo("*") == 0) {
				frame.getJTable().changeSelection(row, 2, false, false);
				frame.getJTable().editCellAt(row, 2);
				frame.getJTable().transferFocus();
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

				error("Ingrese un codigo de articulo valido");
				tx.requestFocusInWindow();
			

		}
	}

	
	public void _evaluate_idarticulo(JTextField tx, int row) {
		if (row < 0) {
			row = 0;
		}
		String idarticulo = tx.getText();
		String iddeposito=frame.get_txt_iddeposito().getText();
		Object[][] results = data.getArticulo(idarticulo,iddeposito);
		boolean exist = false;
		double descuento = 0.0;
		boolean bloqueado = false;
		int equiv = 0;
		if (results != null) {
			if (results.length > 0) {
				exist = true;
				String stock = (String) results[0][6];
				String suspendido = (String) results[0][5];

				double _stock = 0.0;
				try {
					_stock = new Double(stock);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				boolean ok = true;


				if (!ok) {
				} else {
					tx.setText(idarticulo.toUpperCase());
					String descripcion = (String) results[0][0];
					String precio = (String) results[0][1];
					double _precio = 0.0;
					try {
						_precio = new Double(precio);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String costo = (String) results[0][2];
					double _costo = 0.0;
					try {
						_costo = new Double(precio);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					boolean empty = true;
					try {
						empty = frame.getJTable().getValueAt(row, 2).toString()
								.compareTo("") == 0;
					} catch (Exception e) {

					}
					boolean doit = true;
					if (!empty) {
							doit = false;
					} else {
						doit = true;
					}
					System.out.println(_precio);

					if (doit) {
						//idarticulo|descripcion|costo|stock|control|ajuste|etiquetas
						frame.getJTable().setValueAt(descripcion, row, 1);
						frame.getJTable().setValueAt(costo, row, 2);
						frame.getJTable().setValueAt(_stock, row, 3);//stock
						frame.getJTable().setValueAt("1.0" , row, 4);//control
						
						double ajuste=0.0;
						ajuste=_stock-1;
						frame.getJTable().setValueAt(ajuste, row, 5);//ajuste
						frame.getJTable().setValueAt("1.0", row, 6);//etiquetas
					}
						
					frame.getJTable().changeSelection(row, 4, false, false);
					frame.getJTable().editCellAt(row, 4);
					frame.getJTable().transferFocus();
					
					
					
					fillExtra(idarticulo);
				}
			}
		}
		if (exist) {
					frame.getJTable().changeSelection(row, 4, false, false);
					frame.getJTable().editCellAt(row, 4);
					frame.getJTable().transferFocus();
				
		} else {
			if (preguntar("confirmar", "El articulo " + idarticulo
					+ " no existe. Desdea Crearlo?")) {
				this.goMa_Articulos(tx.getText());
				tx.requestFocusInWindow();
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			} else {
				tx.requestFocusInWindow();
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}

		}
	}

	
	private boolean eval_venc(String fecha){
		boolean ok=false;
		if (fecha.compareTo("")!=0){
		Calendar _fecha_actualizacion=Calendar.getInstance();
		Date date=null;
		DateFormat formatter;
		Locale locale = Locale.getDefault();
		try {
		   	formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
		    date = (Date)formatter.parse(fecha);
		        
		    }
		    catch(Exception e){
		        	
		    }
		    long diferencia=1;
		    if (date!=null){
		    	_fecha_actualizacion.setTime(date);
		    	Date today=new Date();
				java.util.GregorianCalendar _fecha_limite=new java.util.GregorianCalendar();
				_fecha_limite.setTime(today);
				//_today.roll(Calendar.DAY_OF_YEAR, days);
				_fecha_limite.add(Calendar.DAY_OF_YEAR, -30);
				
				Calendar now=Calendar.getInstance();
				_fecha_actualizacion.add(Calendar.DATE, 27);
				ok=_fecha_limite.before(_fecha_actualizacion);
			//	System.out.println(now.getTime()+" "+cal.getTime());
		    }
			//System.out.println("dif "+c+" "+diferencia);
		}else {
			//"Fecha Nula"
		}
		return ok;
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
					frame.get_txt_idvendedor().requestFocusInWindow();	
				} else {
					this.buscarArticulo(tx);
				}
			} else {
				this.buscarArticulo(tx);
			}
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
			
			//if (!(ext.getParent() instanceof JTable)){
				Catalogo.setParameter(aplicacion.ventas.catalogo.interfaces._parametros._txt_caller, ext);	
			//}
			
			Catalogo.setParameter(_parametros.connector, data.getConnectionHandler());
			Catalogo.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
			Catalogo.build(this.getConstructor());
			Catalogo.init();
			
			this.getConstructor().addChild(Catalogo);
		}
		
		
		
		
	}
	
	
	public void importar() {
		if (importar != null) {
			importar.dispose();
		}
		if (frame.getJTable() == null) {

			Object[][] results = new Object[][] { { false, "", "", "", "", "",
					"", "", "", "", "", "", "", "", "" } };
			//this.create_table(results);
		} else {
			if (frame.getJTable().getRowCount() <= 0) {
				Object[][] results = new Object[][] { { false, "", "", "", "",
						"", "", "", "", "", "", "", "", "", "" } };
				//this.create_table(results);
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
		_logic.setCustom(true);
		columna col = null;

		col = new columna();
		col.setNombre("idarticulo");
		col.setColumn(0);
		col.setMaster(true);
		_logic.addColumn(col);

	
		col = new columna();
		col.setNombre("control");
		col.setColumn(4);
		col.setMaster(false);
		_logic.addColumn(col);


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
}
