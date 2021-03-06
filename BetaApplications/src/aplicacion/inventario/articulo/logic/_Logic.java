package aplicacion.inventario.articulo.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import aplicacion.herramientas.java.image.logic.ImageComponent;

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
import aplicacion.inventario.articulo.constructor.*;
import aplicacion.ventas.pedido.gui._Etiquetas;



import aplicacion.inventario.articulo.gui._Frame;
import aplicacion.inventario.articulo.interfaces.*;
import aplicacion.inventario.articulo.logic._Data;
import java.util.*;

public class _Logic extends Logic {
	private _Frame frame;
	
	private _Data data;
	private List<BufferedImage> images=null;
	private Object[][] memoria = null;
	
	private int foto_selected=-1;
	private aplicacion.inventario.politica.constructor._Constructor editor_politica = null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.herramientas.java.importar.constructor._Constructor importar = null;
	private aplicacion.herramientas.java.exportar.constructor._Constructor exportar = null;
	private aplicacion.actualizacion.alias.constructor._Constructor alias = null;
	private aplicacion.actualizacion.recodificador.constructor._Constructor recodificar = null;
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	//private aplicacion.herramientas.java.buscadores.Articulo bArticulo = null;
	private String server="E:/catalogo/";
	
	// variables de tareas swingwork
	private boolean debug, done, canceled, override;

	private boolean[] marquer;
	private _Etiquetas etiquetas = null;
	DropTarget dropTarget;
	JLabel dropHereLabel;
	DataFlavor urlFlavor, uriListFlavor, macPictStreamFlavor;

	private int hoja_actual = 0;
	private int hojas = 0;
	public _Logic(){
		super();
		
		
		
		
	}
	public void initialize_dnd(){
		try { 
			urlFlavor = 
			new DataFlavor ("application/x-java-url; class=java.net.URL"); 
			uriListFlavor = 
			new DataFlavor ("text/uri-list; class=java.lang.String");
		} catch (ClassNotFoundException cnfe) { 
			cnfe.printStackTrace( );
		}
		dropTarget = new DropTarget (this.frame.getCanvas(),this.getConstructor().getDropTargetListener());
	}
	
	public void init(){
		images=new ArrayList<BufferedImage>();
	}
	public void processDND(DropTargetDropEvent dtde){
		System.out.println ("drop");
		dtde.acceptDrop (DnDConstants.ACTION_COPY_OR_MOVE);   
		Transferable trans = dtde.getTransferable( );
		System.out.println ("Flavors:"); 
		boolean gotData = false;
		try {
			// try to get an image
			if (trans.isDataFlavorSupported (DataFlavor.imageFlavor)) { 
				System.out.println ("image flavor is supported"); 
				Image img = (Image) trans.getTransferData (DataFlavor.imageFlavor); 
				//showImageInNewFrame (img); 
				gotData = true;
			} else if (trans.isDataFlavorSupported (
				DataFlavor.javaFileListFlavor)) {
				System.out.println ("javaFileList is supported");
				java.util.List list = (java.util.List)
				trans.getTransferData (DataFlavor.javaFileListFlavor);
				ListIterator it = list.listIterator( );    
				while (it.hasNext( )) {
				File f = (File) it.next( );
				System.out.println("Adding Foto DND >"+f.getAbsolutePath());
				this.addFoto(f.getAbsolutePath());
				
				}
				gotData = true;
			} else if (trans.isDataFlavorSupported (uriListFlavor)) {
				System.out.println ("uri-list flavor is supported"); 
				String uris = (String)
				trans.getTransferData (uriListFlavor);

				// url-lists are defined by rfc 2483 as crlf-delimited 
				StringTokenizer izer = new StringTokenizer (uris, "\r\n");   
				while (izer.hasMoreTokens ( )) {
				String uri = izer.nextToken( );
				System.out.println (uri);
				this.addFoto(uri);
				
				}
				gotData = true;
			} else if (trans.isDataFlavorSupported (urlFlavor)) {
				System.out.println ("url flavor is supported");
				URL url = (URL) trans.getTransferData (urlFlavor);
				System.out.println (url.toString( ));
				
				System.out.println (url);
				this.addFoto(url.getFile());
				gotData = true;
			}
		} catch (Exception e) {
			e.printStackTrace( );
		} finally {
			System.out.println ("gotData is " + gotData);
			dtde.dropComplete (gotData);
		}
	}
	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void BuscarProveedor() {
		this.BuscarProveedor(frame.get_txt_idproveedor());
	}

	public void BuscarProveedorActualizacion() {
		this.BuscarProveedor(frame.get_txt_cuenta_actualizacion());
	}
	
	public void cargar_alias() {
		String idarticulo = frame.get_txt_idarticulo().getText();
		if (data.check_idarticulo(idarticulo)) {
			this.cargarAlias(idarticulo);
		} else {
			aviso("No se encontro el articulo " + idarticulo);
		}
	}

	public void editar_alias() {
		String idarticulo = frame.get_txt_idarticulo().getText();
		if (data.check_idarticulo(idarticulo)) {
			if (alias != null) {
				alias.dispose();
			}
			alias = new aplicacion.actualizacion.alias.constructor._Constructor();
			alias.setParameter(_parametros.LookAndFeel, this.getConstructor()
					.getLookAndFeelTheme());
			alias.setParameter(_parametros.connector, this.getConstructor()
					.getConnectionHandler());
			alias.setParameter(
					aplicacion.actualizacion.alias.interfaces._parametros.articulo_desde,
					idarticulo);
			alias.setParameter(
					aplicacion.actualizacion.alias.interfaces._parametros.articulo_hasta,
					idarticulo);
			alias.build(this.getConstructor());
			alias.init();
		}
	}
	
	
	public void recodificar() {
		String idarticulo = frame.get_txt_idarticulo().getText();
		if (data.check_idarticulo(idarticulo)) {
			if (recodificar != null) {
				recodificar.dispose();
			}
			recodificar = new aplicacion.actualizacion.recodificador.constructor._Constructor();
			recodificar.setParameter(_parametros.LookAndFeel, this.getConstructor()
					.getLookAndFeelTheme());
			recodificar.setParameter(_parametros.connector, this.getConstructor()
					.getConnectionHandler());
			recodificar
					.setParameter(
							aplicacion.actualizacion.recodificador.interfaces._parametros.articulo_desde,
							idarticulo);
			recodificar
					.setParameter(
							aplicacion.actualizacion.recodificador.interfaces._parametros.articulo_hasta,
							idarticulo);
			recodificar.build(this.getConstructor());
			recodificar.init();
		}
	}
	public void cargarFotos(){
		frame.get_btn_anterior().setEnabled(false);
		frame.get_btn_siguiente().setEnabled(false);
		frame.get_btn_start().setEnabled(false);
		frame.get_btn_end().setEnabled(false);
		frame.get_txt_total().setText("0");
		frame.get_txt_actual().setText("0");
		hojas=0;
		hoja_actual=0;
		if (images.size()>0){
			frame.get_btn_zoom_in().setEnabled(true);
			frame.get_btn_zoom_out().setEnabled(true);
			hojas=images.size();
			hoja_actual=1;
			mostrar_foto(1);
			frame.get_txt_total().setText(""+images.size());
			frame.get_txt_actual().setText("1");
			if (images.size()>1){
				frame.get_btn_siguiente().setEnabled(true);
				frame.get_btn_start().setEnabled(true);
				frame.get_btn_end().setEnabled(true);
			}	
		}
		
	
	}
	
	
	public void BuscarProveedor(JTextField tx) {
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
		c.setNombre("codigo");
		c.setAlias("codigo");
		c.setColumnField(tx);
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
		logic
				.addRestriction("codigo like '21101%' and codigo not like '21101' ");
		logic.init();
	}

	public void deseleccionar() {

		frame.getJTable().clearSelection();
		frame.getJTable().transferFocus();
	}

	public void seleccionar(int col) {

		if (frame.getJTable().getSelectedColumn() == col) {
			System.out.println("click en header+" + col);
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

	public void cargarAlias(String idarticulo) {
		Object[][] results = data.getAlias(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				this.create_table(results);
				for (int i=0;i<results.length;i++){
					String codigo=(String) results[i][0];
					String linea=(String) results[i][1];
					this.getImages(codigo, linea);
				}
				frame.get_btn_sincronizar().setEnabled(true);
			} else {
				frame.get_btn_sincronizar().setEnabled(false);
			}
		}
	}

	public void ajustar(){
		String idarticulo=frame.get_txt_idarticulo().getText();
		int row=frame.getJTable_deposito().getSelectedRow();
		String iddeposito="";
		String deposito="";
		if (row>=0){
			iddeposito=frame.getJTable_deposito().getValueAt(row, 3).toString();
			deposito=frame.getJTable_deposito().getValueAt(row, 4).toString();
			String cantidad=this.ingresar("Ingrese el Stock Correcto para "+idarticulo+" en el Deposito "+deposito+":");
			
			double _stock=0.0;
			
			try {
				_stock=data.getSaldoStock(idarticulo, iddeposito);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double _cantidad=0.0;
			try {
				_cantidad=new Double(cantidad);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String NEW_LINE = System.getProperty("line.separator");
			String msj="Confirme La modificacion del Stock Para "+idarticulo;
			msj+=" "+NEW_LINE+"Stock actual="+_stock+"  ";
			msj+=NEW_LINE+"Stock Ingresado="+_cantidad+" ";
			double ajuste=(_cantidad-_stock);
			if (ajuste==0){
				error("No Hace Falta Hacer un Ajuste");
			}else{
				
				boolean ok=false;
				String iduser=this.validar_usuario();
				if (iduser.compareTo("")!=0){
				ok=true;
				
				String iddeposito_default=data.getDepositoUser(iduser);
				System.out.println(">>>> iddeposito_default"+iddeposito_default+" /deposito "+deposito+ " /iduser"+iduser);
				if(iddeposito_default.compareTo(iddeposito)!=0){
					this.error("su deposito de operacion es:"+iddeposito_default);
					ok=false;
				}
				
				}
				if (ok){
				
					String ajm=data.getProximoAJM_Ceros();
					String descripcion=frame.get_txt_descripcion().getText();
					int sec=0;
					String fecha=data.getSystemDate();
					String precio=frame.get_txt_precio_publico().getText();
					
					String q=data.getInsertQueryVMVStock("AJM", ajm, idarticulo, descripcion, ""+ajuste, sec, fecha, precio, iddeposito);
					data.beginTransaction();
					data.clearBatch();
					data.addBatch(q);
					int idoperacion=data.getProximoOperacion();
					q=data.getOperacion(""+idoperacion, iduser, "Ajuste Stock Manual "+idarticulo+" iduser:"+iduser+" "+data.getHost()+" "+idarticulo+" Stock ingresado: "+_stock);
					data.addBatch(q);
					q=data.getUpdateAJM();
					data.addBatch(q);
					boolean error=data.executeBatch();
					if (!error){
						data.commitTransaction();
						aviso("Se Guardo Correctamente");
					}else{
						data.rollbackTransaction();
						error("Error Grabando");
					}
					
				}else{
					error("OPERACION CANCELADA");
				}
					
			}
			
			
		}else{
			error("Seleccione el Deposito Donde Modificara el Stock");
		}
		
		
		
	}
	public Object[][] procesar_datos_stock(Object[][] results) {

		double entradas = 0.0;
		double salidas = 0.0;
		double saldo = 0;
		for (int i = 0; i < results.length; i++) {
			String _e = (String) results[i][5];
			String _s = (String) results[i][6];
			double _entrada = 0.0;
			double _salida = 0.0;
			double _saldo = 0.0;
			try {
				_entrada = new Double(_e);
				_salida = new Double(_s);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			entradas += _entrada;
			salidas += _salida;
			_saldo = _entrada - _salida;
			saldo += _saldo;
			results[i][7] = saldo;

		}
		return results;
	}
	/*
	public void cargarPuntoControl(String idarticulo) {
		frame.getJTabbedControl().removeAll();
		
		Object[][] depositos=data.getDepositos();
		for (int u=0;u<depositos.length;u++){
			String iddeposito=depositos[u][0].toString();
			String nombre=depositos[u][1].toString();
			Object[][] results = data.getPuntoControl(idarticulo, iddeposito);
			if (results != null) {
				if (results.length > 0) {
					String idcontrol=results[0][0].toString();
					String fecha=results[0][1].toString();
					String idmovimiento=results[0][2].toString();
					String cantidad=results[0][3].toString();
					boolean movimientos=false;
					Object[][] tmp=data.getStockPuntoControl(idarticulo, iddeposito, idmovimiento);
					if (tmp != null) {
						if (tmp.length > 0) {
							movimientos=true;
							Object[][] tmp2=new Object[tmp.length+1][tmp[0].length];
							for (int i=0;i<tmp2[0].length;i++){
								tmp2[i][0]="";	
							}
							tmp2[0][0]=fecha;
							tmp2[0][3]="CTRL";
							tmp2[0][4]=idcontrol;
							tmp2[0][5]=cantidad;
							tmp2[0][6]="0.0";
							tmp2[0][7]="0.0";
							tmp2[0][8]=iddeposito;
							tmp2[0][9]="Deposito Principal";
							for (int i=0;i<tmp.length;i++){
								for (int j=0;j<tmp2[0].length;j++){
									tmp2[i+1][j]=tmp[i][j];	
								}		
							}
							
							JTable table=this.create_table_punto_control(tmp2);
							JScrollPane jspane=new JScrollPane();
							jspane.setViewportView(table);
							frame.getJTabbedControl().addTab(nombre,null,jspane,null);
						}
					}
					if (!movimientos){
						Object[][] tmp2=new Object[1][10];
						
						tmp2[0][0]=fecha;
						tmp2[0][1]="";
						tmp2[0][2]="";
						tmp2[0][3]="CTRL";
						tmp2[0][4]=idcontrol;
						tmp2[0][5]=cantidad;
						tmp2[0][6]="0.0";
						tmp2[0][7]="0.0";
						tmp2[0][8]=iddeposito;
						tmp2[0][9]="Deposito Principal";
						JTable table=this.create_table_punto_control(tmp2);
						JScrollPane jspane=new JScrollPane();
						jspane.setViewportView(table);
						frame.getJTabbedControl().addTab("Deposito Principal",null,jspane,null);
					}
				}
			}
	
		}
				
	}
*/
	public void cargarStock(String idarticulo) {
		Object[][] results = data.getStock(idarticulo,"");
		if (results != null) {
			if (results.length > 0) {
				this.create_table_stock(results);
			}
		}
	}
	public void cargarCompras(String idarticulo) {
		Object[][] results = data.getCompras(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_compras(results);
			}
		}
	}
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("codigo");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(140);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idproveedor");
		col.setWidth(80);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(140);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("lista");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("politica");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("costo");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("publico");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("actualizado");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
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

		frame.setJTable(table.getTable());
	}
	
	public void cargar_equivalencias(String idarticulo){
		Object[][] results=data.getEquivalencias(idarticulo);
		boolean exist=false;
		if (results!=null){
			if (results.length>0){
				Object[][] tmp=new Object[results.length][results[0].length+1];
				for (int i=0;i<results.length;i++){
					tmp[i][0]=false;
					for (int j=0;j<results[0].length;j++){
					tmp[i][j+1]=results[i][j];
					}
				}
				this.create_table_equivalencias(tmp);
				exist=true;
			}
		}
		if (!exist){
			results=new Object[][]{{false,"","",""}};
			this.create_table_equivalencias(results);
		}
	}
	
	private void create_table_equivalencias(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.setAligment(JTextField.RIGHT);
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_equivalencias_idarticulo);
		pce.setTipo(String.class);

		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(320);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(140);
		col.setEditable(false);
		col.setClass(String.class);
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
		frame.setJTable2(table.getTable());
	}
	
	public void borrarRenglonEquivalencia(int row){
		if (preguntar("Confirmar","Elimina Renglon "+row+" de las Equivalencias?")){
			try {
				frame.getJTable2().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			DefaultTableModel model=(DefaultTableModel) frame.getJTable2().getModel();
			model.removeRow(row);
			if (model.getRowCount()<=0){
				model.setRowCount(1);
				model.setValueAt(true, 0, 0);
				
			}
		}
	}
	
	public void _eval_item_articulo(JTextField tx, int row) {
		String aux = "";
		
		
		try {
			aux = tx.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		tx.requestFocusInWindow();
		if (aux.compareTo("") != 0) {
			if (aux.length() > 4) {
						if (aux.substring(3, 4).compareTo("-") == 0) {
							_evaluate_idarticulo(tx, row);
						} else {
							this.buscarArticulo(tx);
						}
					} else {
							this.buscarArticulo(tx);
					
			}
		} else {

				tx.setText("*");
				tx.requestFocusInWindow();
			

		}
	}
	
	public void _evaluate_idarticulo(JTextField tx, int row) {
		
		String idarticulo = tx.getText();
		Object[][] results = data.getArticulo(idarticulo);
		boolean exist = false;
		boolean bloqueado = false;
		
		if (results != null) {
			if (results.length > 0) {
				exist = true;
				tx.setText(idarticulo.toUpperCase());
				String linea = (String) results[0][4];
				String descripcion = (String) results[0][1];
				frame.getJTable2().setValueAt(descripcion, row, 2);
				frame.getJTable2().setValueAt(linea, row, 3);
				
			}
		}
		
		if (exist) {
				DefaultTableModel model=(DefaultTableModel)frame.getJTable2().getModel();
				if (model.getRowCount()-1==row){
					model.setRowCount(model.getRowCount()+1);
					row++;
				}
				
				frame.getJTable2().changeSelection(row, 1, false, false);
				frame.getJTable2().editCellAt(row, 1);
				frame.getJTable2().transferFocus();	
		} else {
			error("El Articulo "+idarticulo+" es inexistente");
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}
	
	private void create_table_stock(Object[][] results) {
		CustomTable table = new CustomTable();
		System.out.println("Creando tabla para mostar mov stock");
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("fecha");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("cuenta");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("nombre");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		col = new Column();
		
		col.setName("tc");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("entrada");
		col.setWidth(54);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("salida");
		col.setWidth(54);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("saldo");
		col.setWidth(54);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("id");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("deposito");
		col.setWidth(110);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		results = this.procesar_datos_stock(results);
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());

		frame.setJTable1(table.getTable());
	}

	private JTable create_table_punto_control(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("fecha");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("cuenta");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("nombre");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		col = new Column();
		
		col.setName("tc");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("entrada");
		col.setWidth(54);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("salida");
		col.setWidth(54);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("saldo");
		col.setWidth(54);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("id");
		col.setWidth(40);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("deposito");
		col.setWidth(110);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		results = this.procesar_datos_stock(results);
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());

		return _table;
	}
	
	private void create_table_compras(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("TC");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idpedido");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("cuenta");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("nombre");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("fecha");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("estado");
		col.setWidth(130);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("cantidad");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("venta");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());
		frame.setJTable3(table.getTable());
	}
	
	
	private aplicacion.herramientas.java.importadores.Articulo bArticulo = null;
		public void BuscarArticulo(JTextField ext) {
		if (bArticulo != null) {
			bArticulo.close();
		}
		if (ext.getParent() instanceof JTable){
			bArticulo = new aplicacion.herramientas.java.importadores.Articulo(this
					.getConstructor()) {
				public void cargar(Object[] seleccion) {
					agregar(seleccion);
				}
			};	
		}else{
			bArticulo = new aplicacion.herramientas.java.importadores.Articulo(this
					.getConstructor()) {
				public void cargar(Object[] seleccion) {
					agregar2(seleccion);
				}
			};
		}
		
		bArticulo.setSuspendidov(true);
		
		bArticulo.Buscar(ext);
	}

		public int existArticulo(String idarticulo) {
			int exist = -1;
			int i = 0;
			JTable table = frame.getJTable2();
			if (table != null) {
				while (i < table.getRowCount() & exist < 0) {
					String _idarticulo = table.getValueAt(i, 1).toString();
					if (idarticulo.compareTo(_idarticulo) == 0) {
						exist = i;
					}
					i++;
				}
			}
			return exist;
		}
		public int existEmpty() {
			int exist = -1;
			int i = 0;
			JTable table = frame.getJTable2();
			if (table != null) {
				while (i < table.getRowCount() & exist < 0) {
					String _idarticulo = table.getValueAt(i, 1).toString();
					if (_idarticulo.compareTo("") == 0) {
						exist = i;
					}
					i++;
				}
			}
			return exist;
		}
		
		public void agregar2(Object[] seleccion) {
			String idarticulo = (String) seleccion[0];
			frame.get_txt_idarticulo().setText(idarticulo);
		}
		
		public void agregar(Object[] seleccion) {
			System.out.println("Agregar Seleccion ");
			String idarticulo = (String) seleccion[0];
			String descripcion = (String) seleccion[1];
			JTable table = frame.getJTable2();
			try {
				table.getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			
			Object[][] results = data.getArticulo(idarticulo);
			if (results != null) {
				if (results.length > 0) {
					
					int exist = this.existArticulo(idarticulo);

						String linea = results[0][4].toString();
					
						if (exist < 0) {
						int row = 0;
						if (table != null) {

							row = this.existEmpty();
							if (row < 0) {
								DefaultTableModel model = (DefaultTableModel) table
										.getModel();
								model.setRowCount(model.getRowCount() + 1);
								row = model.getRowCount() - 1;
							}

						} else {
							results=new Object[][]{{false,"","",""}};
							this.create_table_equivalencias(results);
						}
						table.setValueAt(true, row, 0);
						table.setValueAt(idarticulo, row, 1);
						table.setValueAt(descripcion, row, 2);
						table.setValueAt(linea, row, 3);
						
						table.changeSelection(row, 1, false, false);
						table.editCellAt(row, 1);
						table.transferFocus();
					}

							}
			}
			
		}

	public void addItem(boolean seleccionado, String idarticulo,
			String descripcion, String linea) {
		JTable table = frame.getJTable2();
		if (table == null) {
			Object[][]results=new Object[][]{{false,"","",""}};
			this.create_table_equivalencias(results);
			
			table = frame.getJTable2();
		} else {
			if (table.getRowCount() <= 0) {
				Object[][]results=new Object[][]{{false,"","",""}};
				this.create_table_equivalencias(results);
				table = frame.getJTable2();
			}
		}

		int row = table.getModel().getRowCount() - 1;
		if (table.getValueAt(row, 1).toString().compareTo("") != 0) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(model.getRowCount() + 1);
			row = model.getRowCount() - 1;
		}
		table.setValueAt(seleccionado, row, 0);
		table.setValueAt(idarticulo, row, 1);
		table.setValueAt(descripcion, row, 2);
		table.setValueAt(linea, row, 3);
		
	}

	public void clean() {
		
		frame.setJTable(null);
		frame.setJTable1(null);
		frame.setJTable2(null);
		frame.setJTable3(null);
		frame.get_txt_idarticulo().setEditable(true);
		frame.get_txt_idarticulo().setEnabled(true);
		frame.setJTable_deposito(null);
		frame.getJTabbedControl().removeAll();
		frame.get_txt_categoria().setText("");
		images=new ArrayList<BufferedImage>();
		frame.get_txt_cuenta_actualizacion().setText("");
		frame.get_txt_descripcion_proveedor_actualizacion().setText("");
		frame.get_txt_descripcion().setText("");
		frame.get_txt_descripcion_politica().setText("");
		frame.get_txt_descripcion_proveedor().setText("");
		frame.get_txt_idarticulo().setText("");
		frame.get_txt_clasificacion().setText("");
		frame.get_txt_idarticulo().setEnabled(true);
		frame.get_txt_fecha_actualizacion().setText("");
		frame.get_txt_iduser_actualizador().setText("");
		frame.get_btn_buscar_articulo().setEnabled(true);
		frame.get_btn_cargar_alias().setEnabled(false);
		frame.get_btn_editar_alias().setEnabled(false);
		frame.get_btn_recodificar().setEnabled(false);
		frame.get_btn_sincronizar().setEnabled(false);
		frame.get_chk_bloquea_venta().setEnabled(false);
		frame.get_chk_bloqueo_compras().setEnabled(false);
		frame.get_chk_bloquea_venta().setSelected(false);
		frame.get_chk_bloqueo_compras().setSelected(false);
		frame.get_txt_idpolitica().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_linea().setText("");
		frame.get_txt_minimo().setText("");
		frame.get_txt_precio_costo().setText("");
		frame.get_txt_precio_lista().setText("");
		frame.get_txt_precio_publico().setText("");
		frame.get_txt_stock().setText("");
		frame.get_txt_iduser_modificacion().setText("");
		frame.get_txt_iduser_creador().setText("");
		frame.get_txt_fecha_creacion().setText("");
		frame.get_txt_fecha_modificacion().setText("");
		frame.getCanvas().resetVars();
		frame.getCanvas().setVisible(true);
		this.hoja_actual=0;
		this.hojas=0;
		images=new ArrayList<BufferedImage>();
		frame.get_btn_anterior().setEnabled(false);
		frame.get_btn_siguiente().setEnabled(false);
		frame.get_btn_end().setEnabled(false);
		frame.get_btn_start().setEnabled(false);
		
		frame.get_btn_zoom_in().setEnabled(false);
		frame.get_btn_zoom_out().setEnabled(false);
		frame.get_txt_categoria().setText("");
		frame.get_txt_categoria_ranking().setText("");
	}

	public boolean exist_alias_row(int row) {
		boolean exist = false;
		String idarticulo = frame.getJTable().getValueAt(row, 1).toString();
		String idproveedor = frame.getJTable().getValueAt(row, 4).toString();
		exist = data.check_alias(idarticulo, idproveedor);
		return exist;
	}

	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor = null;

	public void buscarProveedor(JTextField tx) {
		if (vProveedor != null) {
			vProveedor.close();
		}
		vProveedor = new aplicacion.herramientas.java.visualizadores.Proveedor(
				this.getConstructor());
		int n = vProveedor.Buscar(tx);
		if (n == 0) {
			aviso("no hay Proveedors con ese codigo");
		}
	}

	public void buscarProveedorAdivina(JTextField tx, String codigo) {
		if (vProveedor != null) {
			vProveedor.close();
		}

		vProveedor = new aplicacion.herramientas.java.visualizadores.Proveedor(
				this.getConstructor());
		vProveedor.setPrefijo(codigo);
		int n = vProveedor.BuscarAdivina(tx);
		if (n == 0) {
			aviso("no hay Proveedors con ese codigo");
		}
	}

	private aplicacion.herramientas.java.visualizadores.Politica vPolitica = null;
	public void BuscarPolitica() {
		this.BuscarPolitica(frame.get_txt_idpolitica());
	}
	public void buscarPolitica(JTextField tx) {
		if (vPolitica != null) {
			vPolitica.close();
		}
		vPolitica = new aplicacion.herramientas.java.visualizadores.Politica(
				this.getConstructor());
		int n = vPolitica.Buscar(tx);
		if (n == 0) {
			aviso("no hay Politicas con ese codigo");
		}
	}

	public void buscarPoliticaAdivina(JTextField tx, String codigo) {
		if (vPolitica != null) {
			vPolitica.close();
		}
		vPolitica = new aplicacion.herramientas.java.visualizadores.Politica(
				this.getConstructor());
		vPolitica.setPrefijo(codigo);
		int n = vPolitica.BuscarAdivina(tx);
		if (n == 0) {
			aviso("no hay Politicas con ese codigo");
		}
	}

	public void BuscarPolitica(JTextField ext) {
		aplicacion.herramientas.java.sortableselector.constructor._Constructor CC = new aplicacion.herramientas.java.sortableselector.constructor._Constructor() {
			@Override
			protected void initialize_logic() {
				_logic = new aplicacion.herramientas.java.sortableselector.logic._Logic() {
					@Override
					public void Close(JTable table, int row) {

						super.Close(table, row);

					}
				};
			}
		};
		CC.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) CC
				.getLogic();

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

		logic.addFromTable("v_ta_politicaprecios ");
		f = new Filtro();
		f.setNombre("codigo");
		f.setAlias("codigo");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("descripcion");
		f.setAlias("descripcion");
		f.setWidth(250);
		f.setFocus(true);
		logic.addFilter(f);
		logic.addOrder("codigo");
		logic.init();
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

	public void buscarLineaAdivina(JTextField tx, String codigo) {
		if (vLinea != null) {
			vLinea.close();
		}
		vLinea = new aplicacion.herramientas.java.visualizadores.Linea(this
				.getConstructor());
		vLinea.setPrefijo(codigo);
		vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLineaAdivina(tx);
		if (n == 0) {
			aviso("no hay Lineas con ese codigo");
		}
	}
	public void BuscarLinea() {
		this.BuscarLinea(frame.get_txt_linea());
	}

	public void BuscarLinea(JTextField txt) {
		aplicacion.herramientas.java.sortableselector.constructor._Constructor CC = new aplicacion.herramientas.java.sortableselector.constructor._Constructor();
		CC.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		CC.build(this.getConstructor());

		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) CC
				.getLogic();

		String idproveedor = "";
		try {
			idproveedor = (String) frame.get_txt_idproveedor().getText();
		} catch (Exception e) {

		}
		if (idproveedor.compareTo("") == 0) {
			idproveedor = "%";
		}
		columna c = new columna();
		c.setNombre("c.lineaproveedor");
		c.setAlias("Linea");
		c.setWidth(200);
		c.setMaster(true);
		c.setColumnField(txt);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.idproveedor");
		c.setAlias("Cod.Proveedor");
		c.setWidth(120);
		logic.addColumn(c);
		c = new columna();
		c.setNombre("m.descripcion");
		c.setAlias("Des.Proveedor");
		c.setWidth(180);

		logic
				.addFromTable("ma_cuentas m left outer join b_codigos c on m.codigo=c.idproveedor left outer join v_ta_rubros r on m.descripcion like r.descripcion ");
		Filtro f = new Filtro();
		f.setNombre("c.lineaproveedor");
		f.setAlias("Linea Proveedor");
		f.setWidth(200);
		f.setFocus(true);
		logic.addFilter(f);

		logic
				.addRestriction("c.lineaproveedor not like '' and c.idproveedor like '"
						+ idproveedor + "'");
		logic.addGroup("c.lineaproveedor,c.idproveedor,m.descripcion");
		logic.addOrder("c.lineaproveedor");
		logic.init();

	}

	public void BuscarLineaTabla(JTextField txt, int row, int col) {
		aplicacion.herramientas.java.sortableselector.constructor._Constructor CC = new aplicacion.herramientas.java.sortableselector.constructor._Constructor();
		CC.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		CC.build(this.getConstructor());

		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) CC
				.getLogic();

		String idproveedor = "";
		try {
			idproveedor = (String) frame.getJTable().getValueAt(row, 4);
		} catch (Exception e) {

		}
		boolean ok = false;

		ok = idproveedor.compareTo("") != 0;
		if (ok) {
			ok = data.check_proveedor(idproveedor);
		}
		if (ok) {
			columna c = new columna();
			c.setNombre("c.lineaproveedor");
			c.setAlias("Linea");
			c.setWidth(200);
			c.setMaster(true);
			c.setColumnField(txt);
			logic.addColumn(c);

			c = new columna();
			c.setNombre("c.idproveedor");
			c.setAlias("Cod.Proveedor");
			c.setWidth(120);
			logic.addColumn(c);
			c = new columna();
			c.setNombre("m.descripcion");
			c.setAlias("Des.Proveedor");
			c.setWidth(180);

			logic
					.addFromTable("ma_cuentas m left outer join b_codigos c on m.codigo=c.idproveedor ");
			Filtro f = new Filtro();
			f.setNombre("c.lineaproveedor");
			f.setAlias("Linea Proveedor");
			f.setWidth(200);
			f.setFocus(true);
			logic.addFilter(f);

			logic
					.addRestriction("c.lineaproveedor not like '' and c.idproveedor like '"
							+ idproveedor + "'");
			logic.addGroup("c.lineaproveedor,c.idproveedor,m.descripcion");
			logic.addOrder("c.lineaproveedor");
			logic.init();
		} else {
			aviso("Para buscar una linea en esta fila, debe ingresar un proveedor valido primero!");
		}

	}

	public void BuscarArticulo() {
		this.BuscarArticulo(frame.get_txt_idarticulo());
	}

	public double convert_formula(String formula) {
		double tmp = 0.0;
		try {
			tmp = new Convertidor().formula(formula);
		} catch (Exception e) {

		}
		return tmp;
	}

	// private aplicacion.herramientas.java.buscadores.Articulo bArticulo=null;
	/*public void BuscarArticulo(JTextField ext) {
		if (bArticulo == null) {
			bArticulo = new aplicacion.herramientas.java.buscadores.Articulo(this
					.getConstructor());
		}
		
		bArticulo.Buscar(ext);
	}*/

	// private aplicacion.herramientas.java.visualizadores.Articulo
	// vArticulo=null;
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

	public void evaluar_idarticulo(JTextField tx) {
		String idarticulo = "";
		idarticulo = tx.getText();
		if (idarticulo.compareTo("") == 0) {
			aviso("Ingrese un Articulo. Busqueda F5");
		} else {
			if (idarticulo.length() > 4) {
				if (idarticulo.substring(3, 4).compareTo("-") == 0) {
					if (data.check_idarticulo(idarticulo)) {
						tx.setEnabled(false);
						
						frame.get_btn_buscar_articulo().setEnabled(false);
						this.cargar(tx);

					} else {
						if (preguntar("Confirmar", "Crea Articulo Nuevo "
								+ idarticulo + "?")) {
							tx.setEnabled(false);
							frame.get_txt_minimo().setText("0");
							
							frame.get_btn_buscar_articulo().setEnabled(false);
							frame.get_txt_descripcion().requestFocusInWindow();
						} else {
							tx.requestFocusInWindow();

						}

					}
				} else {
					this.buscarArticulo(tx);
				}
			} else {
				this.buscarArticulo(tx);
			}
		}
	}

	public void focus() {
		frame.get_txt_idarticulo().requestFocusInWindow();
	}

	public Object[][] procesarDatos(Object[][] datos) {
		Object[][] tmp = new Object[datos.length][datos[0].length];
		for (int i = 0; i < datos.length; i++) {
			tmp[i][0] = false;
			tmp[i][1] = datos[i][1];// id
			tmp[i][2] = datos[i][2];// desc
			tmp[i][3] = datos[i][3];// linea
			tmp[i][4] = datos[i][4];// prov

			String lista = (String) datos[i][5];
			tmp[i][5] = new Convertidor().roundDouble(new Double(lista), 2);

			tmp[i][6] = datos[i][6];// polit
			String costo = (String) datos[i][7];
			tmp[i][7] = new Convertidor().roundDouble(new Double(costo), 2);

			String publico = (String) datos[i][8];
			tmp[i][8] = new Convertidor().roundDouble(new Double(publico), 2);
		}
		return tmp;
	}

	public void eliminar() {
		aviso("Operacion no Implementada");
	}

	public void cargar() {
		String idarticulo=frame.get_txt_idarticulo().getText();
		if (data.existe(idarticulo)){
			this.cargar(frame.get_txt_idarticulo());	
		}else{
			frame.get_txt_idarticulo().setEnabled(false);
			frame.get_txt_descripcion().requestFocusInWindow();
		}
		
		
	}

	public void cargar(JTextField tx) {
		
		String idarticulo = tx.getText();
		Object[][] results = data.getArticulo(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				images=new ArrayList<BufferedImage>();
				frame.getCanvas().resetVars();
				frame.getCanvas().setVisible(true);
				
				String descripcion = (String) results[0][1];
				String idproveedor = (String) results[0][2];
				String politica = (String) results[0][3];
				String linea = (String) results[0][4];
				String lista = (String) results[0][5];
				String costo = (String) results[0][6];
				String publico = (String) results[0][7];
				String minimo = (String) results[0][8];
				String suspendidov = (String) results[0][9];
				String suspendidoc = (String) results[0][10];
				String stock = (String) results[0][11];
				String idproveedor_actualizacion = (String) results[0][12];
				String creador=(String) results[0][13];
				String modificador=(String) results[0][14];
				String actualizador=(String) results[0][15];
				String fecha_creacion=(String) results[0][16];
				String fecha_modificacion=(String) results[0][17];
				String fecha_actualizacion=(String) results[0][18];
				String clasificacion=(String) results[0][19];
				String categoria=(String) results[0][20];
				String ranking_rotacion=(String) results[0][21];
				String idrubro=(String) results[0][22];
				frame.get_btn_imprimir_etiqueta().setEnabled(true);
				frame.get_chk_bloquea_venta().setEnabled(true);
				frame.get_chk_bloqueo_compras().setEnabled(true);
				frame.get_btn_cargar_alias().setEnabled(true);
				frame.get_btn_editar_alias().setEnabled(true);
				frame.get_btn_recodificar().setEnabled(true);
				frame.get_txt_descripcion().setText(descripcion);
				frame.get_txt_linea().setText(idrubro);
				frame.get_txt_linea_descripcion().setText(linea);
				frame.get_txt_idpolitica().setText(politica);
				frame.get_txt_idproveedor().setText(idproveedor);
				frame.get_txt_cuenta_actualizacion().setText(idproveedor_actualizacion);
				frame.get_txt_precio_lista().setText(
						new Convertidor().getMoney(lista, 2));
				frame.get_txt_descripcion().requestFocusInWindow();
				;
				frame.get_txt_minimo().setText(
						new Convertidor().getMoney(minimo, 2));
				frame.get_chk_bloquea_venta().setSelected(suspendidov.compareTo("1")==0);
				frame.get_chk_bloqueo_compras().setSelected(suspendidoc.compareTo("1")==0);
				frame.get_txt_stock().setText(stock);
				
				frame.get_txt_iduser_creador().setText(creador);
				frame.get_txt_iduser_modificacion().setText(modificador);
				frame.get_txt_iduser_actualizador().setText(actualizador);
				frame.get_txt_fecha_actualizacion().setText(fecha_actualizacion);
				frame.get_txt_fecha_creacion().setText(fecha_creacion);
				frame.get_txt_fecha_modificacion().setText(fecha_modificacion);
				frame.get_txt_clasificacion().setText(clasificacion);
				frame.get_txt_categoria().setText(categoria);
				frame.get_txt_categoria_ranking().setText(ranking_rotacion);
				this.cargar_politica(politica);
				this.cargar_proveedor(idproveedor);
				this.cargar_proveedor_actualizacion(idproveedor_actualizacion);
				this.cargarAlias(idarticulo);
				
				//this.cargarPuntoControl(idarticulo);
				this.cargarStock(idarticulo);
				this.cargarCompras(idarticulo);
				this.cargar_equivalencias(idarticulo);
				this.cargar_deposito(idarticulo);
				frame.get_txt_precio_costo().setText(
						new Convertidor().getMoney(costo, 2));
				frame.get_txt_precio_publico().setText(
						new Convertidor().getMoney(publico, 2));
				
				cargarFotos();
			}
		}
	}

	public void evaluar_descripcion(JTextField tx) {
		String descripcion = tx.getText();
		if (descripcion.compareTo("") != 0) {
			frame.get_txt_idproveedor().requestFocusInWindow();
		} else {
			error("La descripcion no debe ser nula");
		}
	}

	private void transfer_focus(int row, int col) {
		frame.getJTable().changeSelection(row, col, false, false);
		frame.getJTable().editCellAt(row, col);
		frame.getJTable().transferFocus();
	}

	public void editar_politica() {
		if (this.editor_politica != null) {
			this.editor_politica.dispose();
		}
		this.editor_politica = new aplicacion.inventario.politica.constructor._Constructor();
		editor_politica.setParameter(_parametros.LookAndFeel, this
				.getConstructor().getLookAndFeelTheme());
		this.editor_politica.setParameter(_parametros.connector,
				getConstructor().getConnectionHandler());
		String idpolitica = frame.get_txt_idpolitica().getText();
		this.editor_politica
				.setParameter(
						aplicacion.inventario.politica.interfaces._parametros.idpolitica,
						idpolitica);
		this.editor_politica.build(this.getConstructor());
		this.editor_politica.init();
	}

	public void copiar() {
		System.out.println("cOPIAR A MEMORIA");
		if (frame.getJTable() != null) {
			int[] rows = frame.getJTable().getSelectedRows();
			int[] columns = frame.getJTable().getSelectedColumns();
			memoria = new Object[rows.length][columns.length];
			for (int i = 0; i < rows.length; i++) {
				for (int j = 0; j < columns.length; j++) {
					memoria[i][j] = frame.getJTable().getValueAt(rows[i],
							columns[j]);
				}

			}
			try {

			} catch (Exception e) {
				e.printStackTrace();
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

	public boolean _completar_columna(int col) {
		boolean ok = false;
		ok = (col == 2) | (col == 3) | (col == 4) | (col == 6);
		return ok;
	}

	public int getSelected() {
		int count = 0;
		int row = 0;
		while (row < frame.getJTable().getRowCount() & !canceled) {
			boolean selected = (Boolean) frame.getJTable().getValueAt(row, 0);
			if (selected)
				count++;
			row++;
		}
		return count;
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

	public void evaluar_minimo(JTextField tx) {
		String minimo = tx.getText();
		if (minimo.compareTo("")==0){
			frame.get_txt_minimo().setText("0.0");
		}else{
			double _minimo = 0.0;

			try {
				_minimo = new Double(minimo);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_minimo < 0) {
				tx.setText("0.0");
			}			
		}

	}

	public void evaluar_precio_lista(JTextField tx) {
		String precio = tx.getText();
		double _precio = 0.0;
		precio=precio.replaceAll(",", "");
		try {
			_precio = new Double(precio);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (_precio > 0) {
			frame.get_txt_idpolitica().requestFocusInWindow();
		} else {
			error("El precio debe ser mayor a cero");
		}
	}

	public void evaluar_precio_costo(JTextField tx) {
		String precio = tx.getText();
		double _precio = 0.0;

		try {
			_precio = new Double(precio);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (_precio > 0) {
			frame.get_txt_precio_publico().requestFocusInWindow();
		} else {
			error("El precio debe ser mayor a cero");
		}
	}

	public void evaluar_precio_publico(JTextField tx) {
		String precio = tx.getText();
		double _precio = 0.0;

		try {
			_precio = new Double(precio);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (_precio > 0) {
			
			frame.get_txt_minimo().requestFocusInWindow();
		} else {
			error("El precio debe ser mayor a cero");
		}
	}
	
	public void evaluar_linea(JTextField tx) {
		String linea = tx.getText();
		if (linea.compareTo("") != 0) {
			if (data.check_linea(linea)) {
				this.cargar_linea(linea);
				frame.get_txt_cuenta_actualizacion().requestFocusInWindow();
			} else {
				this.buscarLinea(tx);
			}
		} else {
			String codigo = frame.get_txt_idarticulo().getText().substring(
					0, 3);
			this.buscarLineaAdivina(tx, codigo);
		}
	}

	public void cargar_politica(String valor) {
		Object[][] results = data.getPolitica(valor);
		if (results != null) {
			if (results.length > 0) {
				String descripcion = (String) results[0][1];
				frame.get_txt_descripcion_politica().setText(descripcion);
				String fcosto = (String) results[0][2];
				String fpublico = (String) results[0][3];
				double mcosto = this.convert_formula(fcosto);
				double mpublico = this.convert_formula(fpublico);
				double lista = 0;
				String list=frame.get_txt_precio_lista().getText();
				list=list.replaceAll(",", "");
				try {
					lista = new Double(list);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				double costo = lista * mcosto;
				double publico = costo * mpublico;
				frame.get_txt_precio_costo().setText(
						new Convertidor().getMoney(costo, 2));
				frame.get_txt_precio_publico().setText(
						new Convertidor().getMoney(publico, 2));

			}
		}
	}

	public void evaluar_politica(JTextField tx) {
		String idpolitica = tx.getText();
		if (idpolitica.compareTo("") == 0) {
			String codigo = frame.get_txt_idarticulo().getText()
					.substring(0, 3);
			this.buscarPoliticaAdivina(tx, codigo);
		} else {
			if (data.check_politica(idpolitica)) {
				this.cargar_politica(idpolitica);
				frame.get_txt_precio_costo().requestFocusInWindow();

			} else {
				error("Politica Inexistente");
			}
		}

	}

	public void cargar_proveedor(String idproveedor) {
		Object[][] results = data.getProveedor(idproveedor);
		if (results != null) {
			if (results.length > 0) {
				String desc = results[0][1].toString();
				String pol = results[0][2].toString();
				String poldesc = results[0][3].toString();
				String linea = results[0][6].toString();
				frame.get_txt_descripcion_proveedor().setText(desc);

			}
		}
	}

	
	public void cargar_linea(String idrubro) {
		Object[][] results = data.getRubro(idrubro);
		if (results != null) {
			if (results.length > 0) {
				String desc = results[0][1].toString();
				frame.get_txt_linea_descripcion().setText(desc);
			}
		}
	}
	
	public void cargar_proveedor_actualizacion(String idproveedor) {
		Object[][] results = data.getProveedor(idproveedor);
		if (results != null) {
			if (results.length > 0) {
				String desc = results[0][1].toString();
				String pol = results[0][2].toString();
				String poldesc = results[0][3].toString();
				String linea = results[0][6].toString();
				frame.get_txt_descripcion_proveedor_actualizacion().setText(desc);

			}
		}
	}
	public void evaluar_proveedor(JTextField tx) {
		String idproveedor = tx.getText();
		if (idproveedor.startsWith("21101")
				& idproveedor.compareTo("21101") != 0) {
			if (data.check_proveedor(idproveedor)) {
				this.cargar_proveedor(idproveedor);
				frame.get_txt_linea().requestFocusInWindow();
			} else {
				error("No exite proveedor " + idproveedor);
			}

		} else {
			if (idproveedor.compareTo("") == 0) {
				String codigo = frame.get_txt_idarticulo().getText().substring(
						0, 3);
				this.buscarProveedorAdivina(tx, codigo);
			} else {
				this.buscarProveedor(tx);
			}

		}
	}
	
	
	public void evaluar_rubro(JTextField tx) {
		String idproveedor = tx.getText();
    	if (idproveedor.compareTo("") == 0) {
				String codigo = frame.get_txt_linea().getText().substring(
						0, 3);
				this.buscarLineaAdivina(tx, codigo);
			} else {
				this.buscarLinea(tx);
	    }

		
	}
	
	public void evaluar_proveedor_actualizacion(JTextField tx) {
		String idproveedor = tx.getText();
		if (idproveedor.startsWith("21101")
				& idproveedor.compareTo("21101") != 0) {
			if (data.check_proveedor(idproveedor)) {
				this.cargar_proveedor_actualizacion(idproveedor);
				frame.get_txt_precio_lista().requestFocusInWindow();
			} else {
				error("No exite proveedor " + idproveedor);
			}

		} else {
			if (idproveedor.compareTo("") == 0) {
				String codigo = frame.get_txt_idarticulo().getText().substring(0, 3);
				this.buscarProveedorAdivina(tx, codigo);
			} else {
				this.buscarProveedor(tx);
			}

		}
	}

	
	public String getInstruccionRegistro(String iduser,String ip,String validacion,String idoperacion){
		String idarticulo=frame.get_txt_idarticulo().getText();
		String descripcion=frame.get_txt_descripcion().getText();
		String precio=frame.get_txt_precio_publico().getText();
		String clasificacion=frame.get_txt_clasificacion().getText();
		precio=precio.replaceAll(",", "");
		
		
		
		Object[][] results=data.getCurrentValues(idarticulo);
		String instruccion="";
		if (results!=null){
			if (results.length>0){
				String descripcion_old=(String) results[0][0];
				String precio_old=(String) results[0][1];
				String costo_old=(String) results[0][2];
				String precio5_old=(String) results[0][3];
				String politicaprecios_old=(String) results[0][4];
				String suspendidov_old=(String) results[0][5];
				String suspendidoc_old=(String) results[0][6];
				String minimo_old=(String) results[0][7];
				String clasificacion_old=(String) results[0][19];
				String minimo=frame.get_txt_minimo().getText();
				String costo=frame.get_txt_precio_costo().getText();
				String precio5=frame.get_txt_precio_lista().getText();
				String politicaprecios=frame.get_txt_idpolitica().getText();
				precio_old=precio_old.replaceAll(",", "");		
				String suspendidov="0";
				if (frame.get_chk_bloquea_venta().isSelected()){
					suspendidov="1";
				}
				String suspendidoc="0";
				if (frame.get_chk_bloqueo_compras().isSelected()){
					suspendidoc="1";
				}
				minimo=minimo.replaceAll(",", "");
				if (minimo.compareTo("")==0){
					minimo="0.0";
				}
				precio5=precio5.replaceAll(",", "");
				precio=precio.replaceAll(",", "");
				costo=costo.replaceAll(",", "");
				instruccion=
				data.getRegistrarCambio(iduser, ip, idarticulo,
						descripcion, descripcion_old, precio, precio_old,
						costo, costo_old, precio5, precio5_old,
						politicaprecios, politicaprecios_old,
						minimo, minimo_old, suspendidov, suspendidov_old,
						suspendidoc, suspendidoc_old, validacion,clasificacion,clasificacion_old,idoperacion);
			}
		}
		return instruccion;
	}
	
	public boolean check_equivalencias(){
		boolean ok=true;
		String idarticulo=frame.get_txt_idarticulo().getText();
		int equivalencias=0;
		int i=0;
		if (frame.getJTable2()!=null){
			while (i<frame.getJTable2().getRowCount() & ok){
				
				String idarticulo2="";
				try {
					idarticulo2 = frame.getJTable2().getValueAt(i, 1).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				if (idarticulo2.compareTo("")!=0){
					if (idarticulo.compareTo(idarticulo2)==0){
						error("Un articulo no puede ser equivalente consigo mismo");
						ok=false;
					}else{
						boolean check=data.check_idarticulo(idarticulo2);
						if (check){
							equivalencias++;
						}else{
							ok=false;
							error("equivalencia incorrecta: "+idarticulo2);
						}
					}
				}
				i++;
			}
			if (ok){
				if (equivalencias<=0){
					if (data.exist_equivalencia(idarticulo)){
						ok=false;
						ok=confirmar("Confirme que eliminara las equivalencias existentes bidireccionales de "+idarticulo+": ",2);
					}
				}	
			}
			
				
		}
		return ok;
	}
	
	public boolean guardar() {
		boolean ok=false;
		
		String iduser=this.validar_usuario();
		if (iduser.compareTo("")!=0){
		ok=true;	
		}
		
		if (ok){
			ok=this.check_equivalencias();
			if (ok){
				ok=_guardar(iduser);	
			}else{
				error("Verifique las equivalencias");
			}
				
		}
		
		return ok;
	}
	
	public void getImages(String codigo,String linea){
    	BufferedImage _image=null;
    	boolean ok=false;
    	try {
			Statement stmt = data.getConnector("MySQL").createStatement();
			
			if (stmt!=null){
				String q="SELECT imagen FROM imagen_catalogo  where codigo like '"+codigo+"' and linea like '"+linea+"' ";
				System.out.println(q);
				ResultSet resultSet = stmt.executeQuery(q);
				
					 while (resultSet.next()){
						 ok=true;
						 Blob image = resultSet.getBlob(1);
						  InputStream input = image.getBinaryStream();
				          _image = javax.imageio.ImageIO.read(input);
				          images.add(_image);
				           
					 }
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
			
		
    }

	public void focus(JTable table,int row,int col) {
		table.changeSelection(row,col,false,false);
		table.editCellAt(row,col);
		table.transferFocus();
	}
	
	
	public boolean _guardar(String validacion) {
		String iduser=this.getConstructor().getIduser();
		String idarticulo = "";
		String descripcion = "";
		String linea = "";
		String idproveedor = "";
		String politica = "";
		String lista = "";
		String costo = "";
		String publico = "";
		String minimo = "";
		String clasificacion = "";
		boolean suspendidoc = false;
		boolean suspendidov = false;
		idarticulo = frame.get_txt_idarticulo().getText();
		descripcion = frame.get_txt_descripcion().getText().replaceAll("'", "");
		linea = frame.get_txt_linea().getText().replaceAll("'", "");
		idproveedor = frame.get_txt_idproveedor().getText();
		lista = frame.get_txt_precio_lista().getText();
		politica = frame.get_txt_idpolitica().getText();
		costo = frame.get_txt_precio_costo().getText();
		publico = frame.get_txt_precio_publico().getText();
		minimo = frame.get_txt_minimo().getText();
		suspendidov=frame.get_chk_bloquea_venta().isSelected();
		suspendidoc=frame.get_chk_bloqueo_compras().isSelected();
		clasificacion=frame.get_txt_clasificacion().getText();
		lista = new Convertidor().getMoney(lista, 2).replaceAll(",", "");
		costo = new Convertidor().getMoney(costo, 2).replaceAll(",", "");
		publico = new Convertidor().getMoney(publico, 2).replaceAll(",", "");
		minimo = new Convertidor().getMoney(minimo, 2).replaceAll(",", "");
		String cuenta_actualizacion=frame.get_txt_cuenta_actualizacion().getText();
		String old_clasificacion="";
		boolean error = false;
		Object[][] old=data.getArticulo(idarticulo);
		boolean actualiza=true;
		String old_precio5="";
		String old_costo="";
		String old_publico="";
		double _oprecio5=0.0;
		double _ocosto=0.0;
		double _opublico=0.0;
		if (old!=null){
			if (old.length>0){
				old_precio5=(String) old[0][5];
				old_costo=(String) old[0][6];
				old_publico=(String) old[0][7];
				old_clasificacion=(String) old[0][18];
			}
			
		}
		
		try {
			_oprecio5=new Double(old_precio5);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			_ocosto=new Double(old_costo);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			_opublico=new Double(old_publico);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		double _precio5=0.0;
		double _costo=0.0;
		double _publico=0.0;
		try {
			_precio5=new Double(lista.replaceAll(",", ""));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			_costo=new Double(costo.replaceAll(",", ""));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			_publico=new Double(publico.replaceAll(",", ""));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		actualiza=(
				Math.abs(_oprecio5-_precio5)>0.01 |
				Math.abs(_ocosto -_costo)>0.01 |
				Math.abs(_opublico - _publico)>0.01 
				);
		data.beginTransaction();
		data.clearBatch();
		
		
		String idoperacion=""+data.getProximoOperacion();
		String registra_operacion=""+data.getOperacion(idoperacion, iduser,  "GUARDAR ARTICULO DESDE MAESTRO "+idarticulo+" "+validacion+" "+data.getHost());
		data.addBatch(data.disable_triggers());
		data.addBatch(registra_operacion);
		String inc=data.getUpdateOperacion();
		data.addBatch(inc);
		
		String q="";
		
		if (data.check_idarticulo(idarticulo)) {
			
			q=data.getUpdate(idarticulo, descripcion, linea, idproveedor, politica, lista, costo, publico, minimo, suspendidov, suspendidoc,cuenta_actualizacion,actualiza,clasificacion,validacion);
		}else{
			q=data.getInsert(idarticulo, descripcion, linea, idproveedor, politica, lista, costo, publico, minimo, suspendidov, suspendidoc,cuenta_actualizacion,clasificacion,validacion);
		}
		System.out.println(q);
		data.addBatch(q);
		data.addBatch(data.getDeleteEquivalencia(idarticulo));
		int equivalencias=0;
		for (int i=0;i<frame.getJTable2().getRowCount();i++){
			String idarticulo2="";
			try {
				idarticulo2 = frame.getJTable2().getValueAt(i, 1).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (idarticulo2.compareTo("")!=0){
				boolean check=data.check_idarticulo(idarticulo2);
				if (check){
					equivalencias++;
					//if (!data.exist_equivalencia(idarticulo, idarticulo2)){
						String qx=data.getInsert(idarticulo, idarticulo2);
						data.addBatch(qx);	
					//}
					
				}
			}
			
		}
		
		error = data.executeBatch();
		
		/*if (!error){
			int errors=this.guardarFoto();
			error=errors>0;
		}*/
		if (!error) {
			data.commitTransaction();
			aviso("Se grabo Correctamente");
			
			this.clean();
			this.cargar(idarticulo);
		} else {
			
			data.rollbackTransaction();
			
			error("Error Grabando");
		}
		return error;
	}

	public void BuscarReemplazar() {
		aviso("Operacion aun no implementada");
	}

	public void paint() {
		frame.getJTable().getModel();
	}

	
	public void importar() {
		if (importar != null) {
			importar.dispose();
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
		col.setNombre("idarticulo");
		col.setColumn(1);
		col.setMaster(true);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("descripcion");
		col.setColumn(2);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("linea");
		col.setColumn(3);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("proveedor");
		col.setColumn(4);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("lista");
		col.setColumn(5);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("politica");
		col.setColumn(6);
		col.setMaster(false);
		_logic.addColumn(col);

	}

	public void exportar() {
		if (this.getSelected() > 0) {
			_exportar();
		} else {
			error("Debe Seleccionar que Filas Desea Exportar");
		}
	}

	public void _exportar() {
		if (exportar != null) {
			exportar.dispose();
		}
		exportar = new aplicacion.herramientas.java.exportar.constructor._Constructor();
		exportar.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		exportar
				.setParameter(
						aplicacion.herramientas.java.exportar.interfaces._parametros._tabla,
						frame.getJTable());
		exportar.build(this.getConstructor());
		exportar.init();
		aplicacion.herramientas.java.exportar.logic._Logic _logic = (aplicacion.herramientas.java.exportar.logic._Logic) exportar
				.getLogic();

		columna col = null;

		col = new columna();
		col.setNombre("idarticulo");
		col.setColumn(1);
		col.setMaster(true);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("descripcion");
		col.setColumn(2);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("linea");
		col.setColumn(3);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("proveedor");
		col.setColumn(4);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("lista");
		col.setColumn(5);
		col.setMaster(false);
		_logic.addColumn(col);

		col = new columna();
		col.setNombre("politica");
		col.setColumn(6);
		col.setMaster(false);
		_logic.addColumn(col);

		_logic.crear_tabla();

	}

	public void sincronizar() {
		int row = frame.getJTable().getSelectedRow();
		if (row >= 0) {
			String lista = frame.getJTable().getValueAt(row, 4).toString();
			frame.get_txt_precio_lista().setText(
					new Convertidor().getMoney(lista, 2));
			String idpolitica =frame.getJTable().getValueAt(row, 5).toString();
			if (idpolitica.compareTo("")==0){
				idpolitica = frame.get_txt_idpolitica().getText();	
			}
			
			if (data.check_politica(idpolitica)) {
				frame.get_txt_idpolitica().setText(idpolitica);
				this.cargar_politica(idpolitica);
			}
		} else {
			aviso("Para sincronizar un precio de proveedor debe seleccionar u alias de la tabla");

		}

	}

	public void cargar(String idarticulo) {
		frame.get_txt_idarticulo().setText(idarticulo);
		frame.get_txt_idarticulo().setEditable(false);
		this.cargar();
	}
	
	public void addFoto(){
		System.out.println("addfoto");
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.addFoto(fc.getSelectedFile().getAbsolutePath());
		}
	}
	
	public void mostrar_foto(int i) {
		if (i >= 0 & i <= images.size()) {
			i=i-1;
			BufferedImage bi = images.get(i);
			if (bi != null) {
				frame.getCanvas().setImage(bi);
				frame.getCanvas().maximizar();
				frame.getCanvas().setVisible(true);
				//frame.get_btn_zoom_in().setEnabled(true);
				//frame.get_btn_zoom_oute().setEnabled(true);
			}
			
		}

	}
	
	
	public void centrar_imagen(){
		if (frame.getCanvas()!=null){
			frame.getCanvas().maximizar();
		}
	}
	public void _mover_zoomin() {
		int x = 6;
		frame.getCanvas().zoom_add(x);
	}

	public void _mover_zoomout() {
		int x = 6;
		frame.getCanvas().zoom_rem(x);

	}
	
	public void _anterior() {
		if (this.hoja_actual > 1) {
			this.hoja_actual--;
			frame.get_txt_actual().setText("" + hoja_actual);
			this.mostrar_foto(this.hoja_actual);
			if (this.hoja_actual <=1) {
				frame.get_btn_anterior().setEnabled(false);	
			}
			if (this.hoja_actual <this.hojas) {
				frame.get_btn_siguiente().setEnabled(true);
			}
			
		}
	}
	
	public void _siguiente() {
		if (this.hoja_actual < this.hojas) {
			this.hoja_actual++;
			frame.get_txt_actual().setText("" + hoja_actual);
			this.mostrar_foto(this.hoja_actual);
			if (hoja_actual>=this.hojas){
				frame.get_btn_siguiente().setEnabled(false);
			}
			if (this.hoja_actual >1) {
				frame.get_btn_anterior().setEnabled(true);	
			}
		}

	}
	public void addFoto(String path){
		System.out.println("addfoto");
		aplicacion.herramientas.java.image.constructor._Constructor C=
		new aplicacion.herramientas.java.image.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		
		C.setParameter(aplicacion.herramientas.java.image.interfaces._Parametros._eliminar, false);
		C.setShowOnStartup(false);
		C.build(this.getConstructor());
		C.init();
		
		aplicacion.herramientas.java.image.logic._Logic logic=
			(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
		
		//In response to a button click:
			
			logic.setFileName(path);
			logic.loadImage();
			
			
			
			
		
	        
		
	}
	
	public void eliminar_foto(){
		if (confirmar("Confirme para eliminar la foto:",3)){
			
		}
	}
	
	
	private boolean write(BufferedImage img,int sec){
		
		String idarticulo=frame.get_txt_idarticulo().getText()+"";
		String filename=server;
		idarticulo=idarticulo.replaceAll("/", "");
		filename+=idarticulo;
		filename+="-"+sec+".jpg";
		boolean ok=this.write(img, filename);
		return ok;
		}
	
		private boolean write(BufferedImage img,String filename){
	    	boolean error=false;
	    	String msj="";
	    	
	    	String rename=filename.replace(".jpg", ".back");
	    	File file=new File(filename);
	    	File filebak=new File(rename);
	    	if (file.exists()){
	    		file.renameTo(filebak);
	    	}
	    	try {
	    	  ImageIO.write(img, "jpg", file);
	    	}catch(Exception e){
	    		msj=e.getMessage();
	    		e.printStackTrace();
	    		error=true;
	    	}
	    	return !error;
	    }
		
		public BufferedImage loadImage(){
			BufferedImage img=null;
			String filename="//192.168.4.150/windows storage/fotos/test.jpg";
	    	try {
	      	   img = ImageIO.read(new File(filename));
	         } catch (IOException e) {
	         }
	         return img;
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
		public void Etiquetar() {
			System.out.println("Etiquetar");
			frame.getJTable().setEnabled(false);
			
			Object[][] results = new String[1][3];
			results[0][0]=frame.get_txt_idarticulo().getText();
			results[0][1]=frame.get_txt_descripcion().getText();
			results[0][2]="1.0";
			
			String descripcion="";
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
							_Interface._btn_imprimir_etiqueta);
					this.etiquetas.get_btn_imprimir_etiquetas().addActionListener(
							constructor.getActionListener());
					this.etiquetas.setVisible(true);
					this.etiquetas.requestFocus();
					this.etiquetas.requestFocusInWindow();
					this.crear_empty_etiquetas();
					int units = 0;
					for (int i = 0; i < results.length; i++) {
						String idarticulo = "";
						
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
		
		private void crear_empty_etiquetas() {
			Object[][] results = new Object[][] { { false, "", "", "" } };
			this.create_table_etiquetas(results);
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
		
		public String validar_usuario(){
			String idvendedor="";
			String password=this.requestPassword("Ingrese Su Clave:");
			idvendedor=data.getUserValidacion(password);
			if (idvendedor.compareTo("")==0){
				error("Error de Validacion de Usuario");
				
			}
			return idvendedor;
		}
		
		private void crearTablaDeposito(Object[][] auzx){
			final CustomTable Table=new CustomTable();
			
			
			Column col=new Column();
			col.setName("idarticulo");
			col.setWidth(120);
			col.setClass(String.class);
			col.setEditable(false);
			Table.addColumn(col);
			
			col=new Column();
			col.setName("descripcion");
			col.setWidth(200);
			col.setClass(String.class);
			col.setEditable(false);
			Table.addColumn(col);
			
			col=new Column();
			col.setName("linea");
			col.setWidth(140);
			col.setClass(String.class);
			col.setEditable(false);
			Table.addColumn(col);
			
			col=new Column();
			col.setName("iddeposito");
			col.setWidth(50);
			col.setClass(String.class);
			col.setEditable(false);
			Table.addColumn(col);
			
			col=new Column();
			col.setName("deposito");
			col.setWidth(100);
			col.setClass(String.class);
			col.setEditable(false);
			Table.addColumn(col);
			
			
			col=new Column();
			col.setName("cantidad");
			col.setWidth(70);
			col.setClass(Double.class);
			col.setEditable(false);
			col.setAligment(JTextField.RIGHT);
			Table.addColumn(col);
			Table.setFont(new Font("Dialog", Font.BOLD, 10));
			Table.setData(auzx);
			Table.build();
			Table.fillData();
			JTable table=Table.getTable();
			table.setName(_Interface._table_deposito);
			table.addMouseListener(this.getConstructor().getMouseListener());
			frame.setJTable_deposito(table);
			
		}
		
		public void cargar_deposito(String _idarticulo){
			Object[][] results=data.getDeposito(_idarticulo);
			for (int i=0;i<results.length;i++){
				String cantidad=(String) results[i][5];
				double _cantidad=0.0;
				try {
					_cantidad=new Double(cantidad);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				results[i][5]=_cantidad;
			}
			this.crearTablaDeposito(results);
		}
}
