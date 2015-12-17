package aplicacion.inventario.equivalencia.logic;

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
import aplicacion.inventario.equivalencia.gui.*;
import aplicacion.inventario.equivalencia.interfaces.*;


public class _Logic extends Logic {
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	private aplicacion.herramientas.java.buscadores.Articulo bArticulo=null;
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo=null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
    private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
    private aplicacion.herramientas.java.buscadores.Linea bLinea;		
    
    
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String estado="";
	private int current;
	private int lenght;
	private boolean done,canceled;
	private int errors=0;
	
	private Object[][] memoria=null;
	private _Data data;
	private _Frame frame;
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public void goAutocompletar() {
		this.createTimer();
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskAutoCompletarAlias();
				}
			};
		}
		if (Timer!=null) {
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
		if (Timer!=null) {
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
		if (Timer!=null) {
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
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
	}
	
	class _taskBorrarAlias {
			_taskBorrarAlias() {
				_taskworkBorrarAlias();
			}
	}
	
	class _taskAutoCompletarAlias {
		_taskAutoCompletarAlias() {
			_taskworkAutoCompletarAlias();
		}
	}
	class _taskAutoCompletarAliasDescripcion {
		_taskAutoCompletarAliasDescripcion() {
			_taskworkAutoCompletarAliasDescripcion();
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

	private int _preguntar_si_omite_error_en_formacion_de_alias(String idarticulo){
		final String _idarticulo=idarticulo;
		final int[] answer = new int[1];
		Runnable _execute=new Runnable() {
				   public void run() {
					   String[] options = {"OK","Omitir errores de este tipo"};
				      answer[0] =preguntar("Error de Formacion", "Faltan en formacion de alias para el articulo  "+_idarticulo+"!",
								options,options[0]);
				   }
				 };
		this.invokeAndWait(_execute);
		return answer[0];
	}

	private void _aviso_operacion_completa(String operacion){
		aviso("Operacion "+operacion+" Completa con "+current+" operaciones y "+errors+" errores");
	}
	
	private void _aviso_operacion_cancelada(String operacion){
		error("Operacion "+operacion+" Cancelada con "+current+" operaciones y "+errors+" errores");
	}
	
	private void _aviso_requiere_seleccion(){
		aviso("Debe seleccionar Los Items que desea procesar");		   
	}
	
	public void _taskworkAutoCompletarAlias(){
		boolean omitir_errores_formacion=false;
		lenght=this.getSelected();
		current=0;
		if (frame.getJTable()!=null){
			int i=0; 
			while (i<frame.getJTable().getRowCount() & !canceled){
				boolean b=false;
				String idarticulo="";
				String linea="";
				try {
					b=(Boolean) frame.getJTable().getValueAt(i,0);
				}catch(Exception e){
					
				}
				if (b){
					current++;
					try {
						idarticulo=(String)frame.getJTable().getValueAt(i,1);
					}catch(Exception e){
						
					}
					
					estado="Buscando Alias para "+idarticulo;
					
						
							final int row=i;
							this.autocompletar_row(i);
						
				}
				i++;
			}

		}
		if (this.getSelected()>0){
			if (!canceled){
				this._aviso_operacion_completa("AutoCompletar");	
			}else{
				this._aviso_operacion_cancelada("AutoCompletar");
			}
		}else {
			this._aviso_requiere_seleccion();	
		}	
		done=true;
	}
	
	public void _taskworkAutoCompletarAliasDescripcion(){
		boolean omitir_errores_formacion=false;
		lenght=this.getSelected();
		current=0;
		if (frame.getJTable()!=null){
			int i=0; 
			while (i<frame.getJTable().getRowCount() & !canceled){
				boolean b=false;
				String idarticulo="";
				String linea="";
				try {
					b=(Boolean) frame.getJTable().getValueAt(i,0);
				}catch(Exception e){
					
				}
				if (b){
					current++;
					try {
						idarticulo=(String)frame.getJTable().getValueAt(i,1);
					}catch(Exception e){
						
					}
					
					estado="Buscando Alias para "+idarticulo;
					
						
							final int row=i;
							this.autocompletar_row(i);
						
				}
				i++;
			}

		}
		if (this.getSelected()>0){
			if (!canceled){
				this._aviso_operacion_completa("AutoCompletar");	
			}else{
				this._aviso_operacion_cancelada("AutoCompletar");
			}
		}else {
			this._aviso_requiere_seleccion();	
		}	
		done=true;
	}

	public void _taskworkBorrarAliasSwing(int row){
		final int _row=row;
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
	
	
	public void _taskworkCargar(){
		estado="Cargando Datos";
		frame.getJProgressBar().setIndeterminate(true);
		String idproveedor=frame.get_txt_idproveedor().getText();
		String desde=frame.get_txt_idarticulo_desde().getText();
		String hasta=frame.get_txt_idarticulo_hasta().getText();
		String linea=frame.get_txt_linea_equivalente().getText();
		Object[][] results =null;
		results=data.getArticulos( desde, hasta,linea);;
		
		final Object[][] _results=results;
		Runnable _execute = new Runnable() {
	        public void run() {
	        	_taskworkCargarSwing(_results);
	        }
		};
		this.invokeLater(_execute);
		done=true;
	}

	public void buscar_articulo_desde(){
		this.BuscarArticulo(frame.get_txt_idarticulo_desde());
	}
	public void buscar_articulo_hasta(){
		this.BuscarArticulo(frame.get_txt_idarticulo_hasta());
	}
	
	
	public void seleccionar(int col){
		
		 if (frame.getJTable().getSelectedColumn()==col){
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
		//col.setCellRenderer(new TableItemColorCellRenderer());
		
		
		Table.addColumn(col);
		col = new Column();
		col.setName("Descripcion");
		col.setWidth(240);
		col.setEditable(false);
		//col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Equivalencia");
		col.setWidth(110);
		col.setEditable(true);
		CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_codigo);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		//col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		Table.addColumn(col);
		col = new Column();
		col.setName("Descripcion Equiv");
		col.setWidth(240);
		col.setEditable(false);
		//col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		return Table;
	}
	
	public void create_table(Object[][] results){
		if (results!=null){
			results=this.procesar_items_linea(results);
			CustomTable Table = this.crearTablaDeItems(false);
			Table.setData(results);
			Table.build();
			Table.fillData();
			JTable _table=Table.getTable();
			_table.setName(_Interface._table);
			_table.addMouseListener(this.getConstructor().getMouseListener());
			_table.addKeyListener(this.getConstructor().getKeyListener());
			_table.getTableHeader().addMouseListener(this.getConstructor().getMouseListener());
			frame.setJTable(_table);	
		}else {
			frame.setJTable(null);
		}
	}
	public void _taskworkCargarSwing(Object[][] results) {
		create_table(results);
       	
	}
	
	public void copiar(){
		//System.out.println("cOPIAR A MEMORIA");
		if (frame.getJTable()!=null){
			int[] rows=frame.getJTable().getSelectedRows();
			int[] columns=frame.getJTable().getSelectedColumns();
			memoria=new Object[rows.length][columns.length];
			for (int i=0;i<rows.length;i++){
				for (int j=0;j<columns.length;j++){
					memoria[i][j]=frame.getJTable().getValueAt(rows[i], columns[j]);
				}
				
			}
			try {
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
public void deseleccionar(){
		
		frame.getJTable().clearSelection();
		frame.getJTable().transferFocus();
	}
public void seleccionar(boolean b){
		
		JTable table=frame.getJTable();
		int[] rows=table.getSelectedRows();
		if (rows.length>0){
			for (int row=0;row<rows.length;row++){
				table.setValueAt(b, rows[row], 0);
			}	
		}else{
			for (int row=0;row<table.getRowCount();row++){
				table.setValueAt(b, row, 0);
			}
		}
	}
	
	public void pegar(){
		if (frame.getJTable()!=null){
			int[] rows=frame.getJTable().getSelectedRows();
			int[] columns=frame.getJTable().getSelectedColumns();
			
			
			if (memoria!=null){
					if (memoria.length==rows.length & memoria[0].length==columns.length){
						try {
							
							for (int i=0;i<memoria.length;i++){
								for (int j=0;j<memoria[0].length;j++){
									frame.getJTable().setValueAt(memoria[i][j],rows[i], columns[j]);
								}
							}		
						}catch(Exception e){
							e.printStackTrace();
						}	
					}
				
				
					
				
				
			}
		}
	}
	public void complete_alias_row(String code,String linea,String idproveedor,int row){
		String aux="";
		Object[][] results=data.getCode(code, linea, idproveedor);
		if (results!=null){
			if (results.length>0){
				String codex=(String) results[0][0];
				String p5=(String) results[0][1];
				String alias=(String) results[0][2];
				double p5x=0.0;
				double aliasx=0.0;
				try {
					p5x=new Double(p5);
				}catch(Exception e){
					
				}
				try {
					aliasx=new Double(alias);
				}catch(Exception e){
					
				}
				frame.getJTable().setValueAt(p5x, row, 4);
				frame.getJTable().setValueAt(aliasx, row, 5);
			}
		}
	}
	
public void completar(){
		
		int[] columns=frame.getJTable().getSelectedColumns();
		
		for (int cx=0;cx<columns.length;cx++){
			int col=columns[cx];
			if (col==5|col==8|col==9|col==0){
				
				if (frame.getJTable()!=null){
					
					if (col==0){
						boolean val=(Boolean)frame.getJTable().getValueAt(frame.getJTable().getSelectedRows()[0], col);
					    int[] indexes=frame.getJTable().getSelectedRows();
					    for (int i =1;i<indexes.length;i++){
						try {
							frame.getJTable().setValueAt(val, indexes[i], col);	
						}catch(Exception e){
							
						}
					    }
					}
					if (col==5){
						Double val=(Double)frame.getJTable().getValueAt(frame.getJTable().getSelectedRows()[0], col);
					    
					    int[] indexes=frame.getJTable().getSelectedRows();
					    for (int i =1;i<indexes.length;i++){
						try {
							frame.getJTable().setValueAt(val, indexes[i], col);	
						}catch(Exception e){
							
						}
					    }
					}
					if (col==8|col==9){
						String val=(String)frame.getJTable().getValueAt(frame.getJTable().getSelectedRows()[0], col);
					    
					    int[] indexes=frame.getJTable().getSelectedRows();
					    for (int i =1;i<indexes.length;i++){
						try {
							frame.getJTable().setValueAt(val, indexes[i], col);	
						}catch(Exception e){
							
						}	
					}
				}
					
				}
			}else {
				aviso("No esta permitido completar en esta columna!");
			}
		}
		
		
		
	}



public void setLinea(String linea,String prefijo){
	frame.get_txt_linea().setText(linea);
	frame.get_txt_idarticulo_desde().setText(prefijo+"-0000");
	frame.get_txt_idarticulo_hasta().setText(prefijo+"-zzzzz");
}


public void fillLinea(String prefijo){
	Object[][] results=data.getLinea(prefijo);
	if (results!=null){
		if (results.length>0){
			frame.get_txt_linea().setText((String) results[0][0]);
		}
	}
}

	private Object[][] procesar_items_linea(Object[][] aux) {
		double sum_linea = 0.0;
		double sum_linea_items = 0.0;
		// String linea=this._pedidoproveedor.get_txt_linea().getText();
		
		Object[][] tmp = new Object[aux.length][aux[0].length+1];
		//_alias.getPBar().setMaximum(aux.length);
		//_pedidoproveedor.getPBar().setStringPainted(true);
		//boolean existe = this.pedido_existe();
		
		for (int i = 0; i < aux.length; i++) {
		tmp[i][0]=false;
		for (int j = 0; j < aux[0].length; j++) {
			tmp[i][j+1]=aux[i][j];
		}
		}
		return tmp;
	}

	
	public void evaluar_articulo_desde(JTextField tx){
		String value=tx.getText();
		if (value.compareTo("")!=0){
			frame.get_txt_idarticulo_hasta().requestFocusInWindow();
		}else{
			aviso("Debe completar este campo para poder cargar los Alias");
		}
	}
	
	public void evaluar_articulo_hasta(JTextField tx){
		String value=tx.getText();
		if (value.compareTo("")!=0){
			this.goCargar();
		}else{
			aviso("Debe completar este campo para poder cargar los Alias");
		}
	}
	
	

	
	public void buscarLinea(JTextField tx,String idproveedor){
		
		
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
		c.setNombre("lineaproveedor");
		c.setAlias("Linea");
		c.setColumnField(tx);
		c.setWidth(120);
		logic.addColumn(c);
		

		logic.setFromTable("b_codigos ");
		logic.setRestriction("idproveedor like '"+idproveedor+"'");
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("lineaproveedor");
		f.setValor(tx.getText());
		logic.addFilter(f);
		
		
		
		logic.setTop(100);
		logic.setGroupby("lineaproveedor");
		logic.setOrderby("lineaproveedor");
		
		int x=frame.getLocation().x;
		int y=frame.getLocation().x;
		
		logic.setCaller(tx);
		int n=logic._loadoptions();
		if (n==0){
			aviso("no hay cuentas con esa descripcion");
		}
	}
	
	
	public void BuscarLinea(JTextField tx,int row){
		
		CustomCellEditor cell=(CustomCellEditor)frame.getJTable().getCellEditor(row, 8);
		JTextField idproveedor=(JTextField)cell.getComponent();
		if (bLinea==null){
			bLinea=new aplicacion.herramientas.java.buscadores.Linea(this.getConstructor());
		}
		
		
		bLinea.setIdproveedor(idproveedor);
		
		
		boolean ok=data.check_proveedor(idproveedor.getText())&idproveedor.getText().compareTo("")!=0;
		if (ok){
			bLinea.BuscarLineaProveedor(tx);	
		}else {
			aviso("Para buscar una linea en esta fila, debe ingresar un proveedor valido primero!");
		}
	}
	
	public void clean(){
		frame.get_txt_idarticulo_desde().setText("");
		frame.get_txt_idarticulo_hasta().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_proveedor_descripcion().setText("");
		
		frame.get_txt_prefijo_codigo().setText("");
		frame.get_txt_art_desde().setText("3");
		frame.get_txt_art_qty().setText("");
		frame.get_txt_linea().setText("");
		frame.setJTable(null);
		
	}
	
	public void BuscarLinea(){
		this.BuscarLinea(frame.get_txt_linea());
	}
	public void BuscarLinea(JTextField tx){
			if (bLinea==null){
				bLinea=new aplicacion.herramientas.java.buscadores.Linea(this.getConstructor());
			}
			
			bLinea.setIdproveedor(frame.get_txt_idproveedor());
			bLinea.BuscarLinea(tx);	
		
		}
	
	public void evalcode(JTextField tx,int row,int col){
		String linea="";
		try {
			linea=(String)frame.getJTable().getValueAt(row, 9);
		}catch(Exception e){
			
		}
		String idprov="";
		try {
			idprov=(String)frame.getJTable().getValueAt(row, 8);
		}catch(Exception e){
			
		}
		String aux="";
		if (linea.compareTo("")!=0 & idprov.compareTo("")!=0){
			String code=tx.getText();
			while (code.substring(1).compareTo(" ")==0){
				code=code.substring(1, code.length());
			}
			
		boolean ok=data.checkcode(code,linea,idprov);
		if (ok) {
			this.complete_alias_row(code, linea, idprov,row);
			frame.getJTable().changeSelection(row, col+2, false, false);
			frame.getJTable().editCellAt(row, col+2);
			frame.getJTable().transferFocus();	
		}else{
			aviso("El codigo "+tx.getText()+" no existe.");
			frame.getJTable().setValueAt(aux, row,col);
			frame.getJTable().changeSelection(row, col, false, false);
			frame.getJTable().editCellAt(row, col);
			frame.getJTable().transferFocus();
		}
		}else {
			aviso( "Complete los campos de proveedor y linea de proveedor");
			frame.getJTable().setValueAt(aux, row,col);
			frame.getJTable().changeSelection(row, col, false, false);
			frame.getJTable().editCellAt(row, col);
			frame.getJTable().transferFocus();
		}
	}
	public void BuscarAlias(JTextField txt,int row) {

		String idproveedor="";
		String idarticulo="";
		String lineaproveedor="";
		try {
			idproveedor=(String) frame.getJTable().getValueAt(row, 8);
		}catch(Exception e){
			
		}
		try {
			lineaproveedor=(String) frame.getJTable().getValueAt(row, 9);
		}catch(Exception e){
			
		}
		try {
			idarticulo=(String) frame.getJTable().getValueAt(row, 1);
		}catch(Exception e){
			
		}
		
		idarticulo=idarticulo.substring(4, idarticulo.length());
		
		aplicacion.herramientas.java.sortableselector.constructor._Constructor 
		CC=new aplicacion.herramientas.java.sortableselector.constructor._Constructor();
		CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		CC.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic 
		logic=(aplicacion.herramientas.java.sortableselector.logic._Logic)CC.getLogic();
		

		columna c = new columna();
		Filtro f = new Filtro();
		c = new columna();
		c.setNombre("c.idcodigo");
		c.setAlias("c.idcodigo");

		c.setColumnField(txt);
		//c.setCell(_alias.getJTable(), row, 2);
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

		logic.addFromTable("b_codigos c left outer join ma_cuentas m on c.idproveedor=m.codigo ");
		f = new Filtro();
		f.setNombre("c.idcodigo");
		f.setAlias("c.idcodigo");
		f.setWidth(120);
		f.setFocus(true);
		if (idarticulo.compareTo("")!=0 & frame.get_chk_precargar_codigos().isSelected()){
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
		if (lineaproveedor.compareTo("")!=0 & frame.get_chk_precargar_codigos().isSelected()){
			f.setInitialValue(lineaproveedor);
		}
		f.setWidth(180);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("c.idproveedor");
		f.setAlias("c.idproveedor");
		if (idproveedor.compareTo("")!=0 & frame.get_chk_precargar_codigos().isSelected()){
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
		 if (bArticulo==null){
			 bArticulo=new aplicacion.herramientas.java.buscadores.Articulo(this.getConstructor());
		 }
		 
		 bArticulo.Buscar(ext);
	}
	
	public void buscarArticulo(JTextField tx) {
		if (vArticulo!=null){
			vArticulo.close();
		}
		vArticulo=new aplicacion.herramientas.java.visualizadores.Articulo(this.getConstructor());
		vArticulo.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vArticulo.Buscar(tx);
		if (n == 0) {
			vArticulo.close();
			aviso("no hay articulos con ese codigo");
		}
	}
	public void cargarProveedor(String idcliente){
		
		Object[][] results=data.getClientInformation(idcliente);
		if (results!=null){
			if (results.length>0){
				String desc=results[0][1].toString();
				frame.get_txt_proveedor_descripcion().setText(desc);
				frame.get_txt_linea().requestFocusInWindow();
			}else {
				aviso("El Codigo de Cuenta es Inexistente. Utilice F5 para Buscar");
				frame.get_txt_idproveedor().requestFocusInWindow();
			}
		}
	}
	public void eval_linea(JTextField tx){
		String idproveedor="";
		try {
			idproveedor=(String)frame.get_txt_idproveedor().getText();
		}catch(Exception e){
			
		}
		String lineaproveedor=tx.getText();
		if (!data.check_linea(lineaproveedor, idproveedor)){
			this.buscarLinea(tx);
		}else{
			this.cargar_prefijos(lineaproveedor);
			frame.get_txt_idarticulo_desde().requestFocusInWindow();
		}
	}
	
	public void focus(){
		frame.get_txt_idproveedor().requestFocusInWindow();
	}
	public void evaluar_table_linea(JTextField tx,int row){
		String idproveedor=frame.getJTable().getValueAt(row, 8).toString();
		String lineaproveedor=tx.getText();
		if (data.check_linea(lineaproveedor, idproveedor)){
			frame.getJTable().changeSelection(row, 10, false,false);
			frame.getJTable().editCellAt(row,10);
			frame.getJTable().transferFocus();
		}else{
			this.buscarLinea(tx, idproveedor);
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
		vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLinea(tx);
		if (n == 0) {
				aviso("no hay Lineas con ese codigo");
		}
	}
	
	public void cargar_prefijos(String linea){
		Object[][] results=data.getLinePrefix(linea);
		if (results!=null){
			if (results.length>0){
				String prefix=(String) results[0][0];
				frame.get_txt_idarticulo_desde().setText(prefix+"-000");
				frame.get_txt_idarticulo_hasta().setText(prefix+"-zzz");
				frame.get_txt_idarticulo_hasta().requestFocusInWindow();
				}
		}
	}
	public void evaluar_table_proveedor(JTextField tx,int row){
		String aux=tx.getText();
		if (aux.compareTo("")!=0){
				int n=0;
				try {
					n=new Integer(aux);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				if (n>0){
					if (data.check_proveedor(aux)){
						frame.getJTable().changeSelection(row, 9,false,false);
						frame.getJTable().editCellAt(row, 9);
						frame.getJTable().transferFocus();
					}else{
						error("Proveedor "+aux+" no existe");
						tx.requestFocusInWindow();
					}
				}else {
					this.buscarProveedor(tx);
				}
					
		}else {
			aviso("Ingrese Codigo de Proveedor. utilice F5 para buscar");
		}
	}
	
	
	public void cancelar_tarea(){
		if (preguntar("confirmar","Cancela Tarea?")){
			this.canceled=true;	
		}
	}
	
	
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	public void initialize_proveedor(){
		proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
			public void cargar(String codigo){
				cargarProveedor(codigo);
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


	
public void autocompletar_row(int row){
	String linea_requerida=frame.get_txt_linea_equivalente().getText();
	String codigo=(String)frame.getJTable().getValueAt(row, 1);
	String idarticulo=(String)frame.getJTable().getValueAt(row, 1);
	Convertidor Cv=new Convertidor();
		codigo=Cv.LimpiarString(codigo," ");
		codigo=Cv.LimpiarString(codigo,"/");
		codigo=Cv.LimpiarString(codigo,".");
		codigo=Cv.LimpiarString(codigo,"-");
	
	if (frame.get_chk_art_substring().isSelected()){
		int i=4;
		String qty=frame.get_txt_art_qty().getText();
		int j=codigo.length();
		if (qty.compareTo("")!=0){
			try {
				j=new Integer(qty);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try{
			i=new Integer(frame.get_txt_art_desde().getText());
		}catch(Exception e){
			//System.out.println("exception posicion inicial filtro substring art "+e.getMessage());
		}
		try{
			if (frame.get_txt_art_qty().getText().compareTo("")!=0){
			j=new Integer(frame.get_txt_art_desde().getText())+new Integer(frame.get_txt_art_qty().getText());
			if (j>=codigo.length())j=codigo.length();}else {
				j=codigo.length();
			}
			
		}catch(Exception e){
			//System.out.println("exception cantidad de carcateres substring art "+e.getMessage());
		}
		codigo=codigo.substring(i,j);
	}else {
		codigo=codigo.substring(4,codigo.length());
	}
	
	
	
	/*
	if (frame.get_txt_precode().getText().compareTo("")!=0){
		codigo=frame.get_txt_precode().getText()+codigo;
		codigo=Cv.LimpiarString(codigo," ");
		codigo=Cv.LimpiarString(codigo,"/");
		codigo=Cv.LimpiarString(codigo,".");
		codigo=Cv.LimpiarString(codigo,"-");
	}*/
	
				
				String idarticulo_equivalente="";
				Object[][] codes=data.getAliasCode(idarticulo);
				if (codes!=null){
					if (codes.length>0){
						String codigo1=(String) codes[0][0];
						String linea1=(String) codes[0][1];
						System.out.println("Buscando x Alias Codigo>"+codigo1+" "+linea1);
						Object[][] equivalencias=data.getCodigosEquivalentes(codigo1, linea1,linea_requerida);
						if (equivalencias!=null){
							if (equivalencias.length>0){
								String codigo2=(String) equivalencias[0][0];
								String linea2=(String) equivalencias[0][1];
								Object[][] articulos=data.getAliasArticle(codigo2, linea2);
								if (articulos!=null){
									if (articulos.length>0){
										idarticulo_equivalente=(String) articulos[0][0];
									}
								}
							}
						}
					}
				}
				if (idarticulo_equivalente.compareTo("")==0){
						Object[][] results=data.getArticulo(idarticulo);
						String linea1=frame.get_txt_linea().getText();
						if (linea1.compareTo("")==0){
							linea1=results[0][4].toString();	
						}
						
						
						System.out.println("Buscando directo Codigo>"+codigo+" "+linea1);
						Object[][] equivalencias=data.getCodigosEquivalentes(codigo, linea1,linea_requerida);
						if (equivalencias!=null){
							if (equivalencias.length>0){
								String codigo2=(String) equivalencias[0][0];
								String linea2=(String) equivalencias[0][1];
								Object[][] articulos=data.getAliasArticle(codigo2, linea2);
								if (articulos!=null){
									if (articulos.length>0){
										idarticulo_equivalente=(String) articulos[0][0];
									}
								}
							}
						}
					}
				
				if (idarticulo_equivalente.compareTo("")!=0){
					Object[][] results=data.getArticulo(idarticulo_equivalente);
					if (results!=null){
						if (results.length>0){
							String descripcion=results[0][1].toString();
							frame.getJTable().setValueAt(descripcion, row, 4);		
						}
					}
					
					frame.getJTable().setValueAt(idarticulo_equivalente, row, 3);
					
				}
}

public void complete(int row,String code,String linea,double lista,String idproveedor, double factor,String actualizacion){
		
		final int _row=row;
		final String _code=code;
		final String _idproveedor=idproveedor;
		final String _lineaproveedor=linea;
		final double _factor=factor;
		final double _lista=lista;
		final String _actualizacion=actualizacion;
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
public int getSelected(){
	int max=0;
	
	if (frame.getJTable()!=null){
		for (int i=0;i<frame.getJTable().getRowCount();i++){
			boolean b=false;
			try {
				b=(Boolean) frame.getJTable().getValueAt(i,0);
			}catch(Exception e){
				
			}
			if (b) max++;
		}
	}
	return max;
}

private int _preguntar_si_modifica_alias(String idarticulo){
	final String _idarticulo=idarticulo;
	final int[] answer = new int[1];
	Runnable _execute=new Runnable() {
			   public void run() {
				   String[] options = {"Si","Si a todo","No","No a todo"};
			      answer[0] =preguntar("Confirmar", "Modifica el alias del articulo "+_idarticulo+" ?", options, options[0]);
			   }
			 };
	this.invokeAndWait(_execute);
	return answer[0];
}

private int _preguntar_si_borra_alias(String idarticulo){
	final String _idarticulo=idarticulo;
	final int[] answer = new int[1];
	Runnable _execute=new Runnable() {
			   public void run() {
				   String[] options = {"Si","Si a todo","No","No a todo"};
			      answer[0] =preguntar("Confirmar", "Borra el alias del articulo "+_idarticulo+" ?", options, options[2]);
			   }
			 };
	this.invokeAndWait(_execute);
	return answer[0];
}
public void _taskworkGuardar(){
		//recorro renglones
		//si tiene todos los campos de un alias analizo si reemplaza algun alias
		//pregunto si modifica el alias; si quiere modificar todos o ninguno
		boolean si_a_todo=false;
		boolean no_a_todo=false;
		boolean omitir_errores=false;
		boolean omitir_errores_formacion=false;
		
		this.lenght=this.getSelected();
		current=0;
		
		if (frame.getJTable()!=null){
			int i=0; 
			while (i<frame.getJTable().getRowCount() & !canceled){
				boolean b=false;
				String idarticulo="";
				String idarticulo2="";
				try {
					b=(Boolean) frame.getJTable().getValueAt(i,0);
				}catch(Exception e){
					
				}
				
				String q="";
				
				if (b){
					estado="Guardando "+idarticulo;
					current++;
					try {
						idarticulo=(String)frame.getJTable().getValueAt(i,1);
					}catch(Exception e){
						
					}
					try {
						idarticulo2=(String)frame.getJTable().getValueAt(i,3);
					}catch(Exception e){
						
					}
					
					boolean check=(idarticulo.compareTo("")!=0 & idarticulo2.compareTo("")!=0);
					if (check){
						
						if (data.exist_equivalencia(idarticulo, idarticulo2)){
							}else {
							q=data.getInsert(idarticulo, idarticulo2);
						}
					}else {
						this.errors++;
						if (!omitir_errores_formacion){
							int n=this._preguntar_si_omite_error_en_formacion_de_alias(idarticulo);
							if (n==1){
								omitir_errores_formacion=true;
							}	
						}
						
						
					}
					
				}
				
				if (q.compareTo("")!=0){
					boolean check=(idarticulo.compareTo("")!=0 & idarticulo2.compareTo("")!=0);
					if (check){
						if (q.compareTo("")!=0){
							data.clearBatch();
							data.addBatch(q);
							boolean error=data.executeBatch();
							if (error){
								errors++;
							}
						}
						
					}else {
						
							if (!omitir_errores_formacion){
								int n=this._preguntar_si_omite_error_en_formacion_de_alias(idarticulo);
								if (n==1){
									omitir_errores_formacion=true;
								}	
							}
						
						
						errors++;
					}
					
				}
				i++;
			}	
		}
		if (this.getSelected()>0){
				if (!canceled){
					this._aviso_operacion_completa("Guardar");	
				}else{
					this._aviso_operacion_cancelada("Guardar");
				}
		}else {
				this._aviso_requiere_seleccion();	
		}	
		done = true;
		
		
	}

	public void _taskworkBorrarAlias(){
		
		boolean si_a_todo=false;
		boolean no_a_todo=false;
		current=0;
		lenght=this.getSelected();
		
		
		int qty=1;
		if (frame.getJTable()!=null){
			
			int i=0; 
			while (i<frame.getJTable().getRowCount() & !canceled){
				boolean b=false;
				String idarticulo="";
				String idcodigo="";
				String lineaproveedor="";
				String idproveedor="";
				double factor=1.0;
				try {
					b=(Boolean) frame.getJTable().getValueAt(i,0);
				}catch(Exception e){
					
				}
				try {
					idarticulo=(String)frame.getJTable().getValueAt(i,1);
				}catch(Exception e){
					
				}
				try {
					idcodigo=(String)frame.getJTable().getValueAt(i,2);
				}catch(Exception e){
					
				}
				try {
					factor=(Double) frame.getJTable().getValueAt(i,5);
				}catch(Exception e){
					
				}
				try {
					idproveedor=(String)frame.getJTable().getValueAt(i,8);
				}catch(Exception e){
					
				}
				try {
					lineaproveedor=(String)frame.getJTable().getValueAt(i,9);
				}catch(Exception e){
					
				}
				String q="";
				if (b){
					estado="Borrando Alias "+idarticulo;
					qty++;
					current++;
					if (data.check_alias_existence(idarticulo, idcodigo, lineaproveedor, idproveedor, factor)){
						
							if (si_a_todo){
								q=data.getBorrarAlias(idarticulo, idcodigo, lineaproveedor, idproveedor, factor);
								_taskworkBorrarAliasSwing(i);
							}else {
								if (!no_a_todo){
									int n=this._preguntar_si_borra_alias(idarticulo);
									if ( n == 0 | n==1) {
										q=data.getBorrarAlias(idarticulo, idcodigo, lineaproveedor, idproveedor, factor);
										_taskworkBorrarAliasSwing(i);		
										if (n==1){
											si_a_todo=true;
										}
									}else {
										if (n==3){
											no_a_todo=true;
										}
									}
								}
							}
						
					}
					if (q.compareTo("")!=0){
						data.clearBatch();
						data.addBatch(q);
						
						boolean error=data.executeBatch();
						if (error){
							errors++;
						}
					}
				}
				i++;
			}
		}
		if (this.getSelected()>0){
			if (!canceled){
				this._aviso_operacion_completa("Borrar");	
			}else{
				this._aviso_operacion_cancelada("Borrar");
			}
		}else {
			this._aviso_requiere_seleccion();	
		}	
		done=true;
	}	

	public void createTimer(){
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
		Timer=new Timer(1000, new ActionListener() {
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
		frame.get_btn_completar().setEnabled(true);
		frame.get_btn_cargar().setEnabled(true);
		frame.get_btn_guardar().setEnabled(true);
	}
	
	public void cargar(String idarticulo_desde,String idarticulo_hasta,String idproveedor){
		frame.get_txt_idarticulo_desde().setText(idarticulo_desde);
		frame.get_txt_idarticulo_hasta().setText(idarticulo_hasta);
		frame.get_txt_idproveedor().setText(idproveedor);
		this.goCargar();
	}
}
