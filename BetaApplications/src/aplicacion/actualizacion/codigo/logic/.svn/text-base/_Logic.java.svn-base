package aplicacion.actualizacion.codigo.logic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.sortableselector.*;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.inventario.planilla.interfaces._Interface;





import aplicacion.actualizacion.codigo.gui._Frame;
import aplicacion.actualizacion.codigo.logic._Data;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
public class _Logic extends Logic {
	private _Data data;
	private _Frame frame;
	private aplicacion.herramientas.java.buscadores.Codigo bCodigo=null;
	//variables de tareas swingwork
	private String estado="";
	private int current;
	private int lenght;
	private boolean debug,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private int errors=0;
	private aplicacion.herramientas.java.exportar.constructor._Constructor exportar=null;
	private aplicacion.herramientas.java.importar.constructor._Constructor importar=null;
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);

	}
	
	

	public void BuscarCodigoDesde(){
		this.BuscarCodigo(frame.get_txt_codigo_desde());
	}
	
	public void BuscarCodigoHasta(){
		this.BuscarCodigo(frame.get_txt_codigo_hasta());
	}
	public void focus(){
		frame.get_txt_idproveedor().requestFocusInWindow();
	}
	public void evaluate_codigo_desde(JTextField tx){
		String idproveedor="";
		try {
			idproveedor=(String)frame.get_txt_idproveedor().getText();
		}catch(Exception e){
			
		}
		
		String linea="";
		try {
			linea=(String)frame.get_txt_linea().getText();
		}catch(Exception e){
			
		}
		String idcodigo=tx.getText();
		if (idcodigo.compareTo("")!=0){
			if (data.check_alias_existence(idcodigo, linea, idproveedor)){
				frame.get_txt_codigo_hasta().requestFocusInWindow();
			}else{
				error("Codigo Inexistente");
			}
		}else{
			frame.get_txt_codigo_hasta().requestFocusInWindow();
		}
		
	}
	
	public void evaluate_codigo_hasta(JTextField tx){
		String idproveedor="";
		try {
			idproveedor=(String)frame.get_txt_idproveedor().getText();
		}catch(Exception e){
			
		}
		
		String linea="";
		try {
			linea=(String)frame.get_txt_linea().getText();
		}catch(Exception e){
			
		}
		String idcodigo=tx.getText();
		if (idcodigo.compareTo("")!=0){
			if (data.check_alias_existence(idcodigo, linea, idproveedor)){
				this.goCargar();
			}else{
				error("Codigo Inexistente");
			}
		}else{
			this.goCargar();
		}
		
	}
	public void BuscarCodigo(JTextField tx) {
		String idproveedor="";
		try {
			idproveedor=(String)frame.get_txt_idproveedor().getText();
		}catch(Exception e){
			
		}
		
		String linea="";
		try {
			linea=(String)frame.get_txt_linea().getText();
		}catch(Exception e){
			
		}
		
		
	boolean ok=false;
		
		ok=idproveedor.compareTo("")!=0;
		if (ok){
			ok=data.check_proveedor(idproveedor);
		}
		if (ok){
			ok=linea.compareTo("")!=0;
		}
		if (ok){
			ok=data.check_linea(idproveedor, linea);
		}
		if (ok){
			
			bCodigo=new aplicacion.herramientas.java.buscadores.Codigo(this.getConstructor());
			bCodigo.setIdproveedor(frame.get_txt_idproveedor());
			bCodigo.setLinea(frame.get_txt_linea());
			bCodigo.Buscar(tx);
		}else{
			aviso("Debe especificar proveedor y linea");
		}
	}
	
	public int getSelected(){
		int count=0;
		int row=0;
		while(row<frame.getJTable().getRowCount()){
			boolean selected=(Boolean) frame.getJTable().getValueAt(row, 0);
			if (selected) count++;
			row++;
		}
		return count;
	}
	
	public void BuscarLinea(){
		this.BuscarLinea(frame.get_txt_linea());
	}
	private aplicacion.herramientas.java.buscadores.Linea bLinea;
	public void BuscarLinea(JTextField tx){
			
			if (bLinea==null){
				bLinea=new aplicacion.herramientas.java.buscadores.Linea(this.getConstructor());
			}
			
			bLinea.setIdproveedor(frame.get_txt_idproveedor());
			bLinea.BuscarLineaProveedor(tx);	
		
		}
	 
		
	public void doCancel(){
		canceled=true;
		endbar();
		aviso("Proceso Cancelado");
		frame.get_btn_cancelar().setEnabled(true);
		frame.get_btn_cargar().setEnabled(true);
		frame.get_btn_completar().setEnabled(true);
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
	
	class _taskBorrar {
		_taskBorrar() {
			_taskworkBorrar();
		}
	}

	class _taskGuardar {
		_taskGuardar() {
		_taskworkGuardar();
		}
	}
	
	class _taskCargar {
		_taskCargar() {
			_taskworkCargar();
		}
	}

	public void goCargar() {
		frame.getJProgressBar().setIndeterminate(true);
		estado="Cargando Datos";
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

	public void goBorrar() {
		this.createTimer();
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskBorrar();
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

	private int _preguntar_si_borra(String idcodigo){
		final String _idcodigo=idcodigo;
		final int[] answer = new int[1];
		Runnable _execute=new Runnable() {
				   public void run() {
				      String[] options = {"Si","Si a todo","No","No a todo"};
				      answer[0] =preguntar("Confirmar", "Elimina el codigo "+_idcodigo+" ?", options, options[0]);
				   }
				 };
		this.invokeAndWait(_execute);
		return answer[0];
	}
	
	public void _taskworkBorrar(){
		boolean si_a_todo=false;
		boolean no_a_todo=false;
		this.lenght=this.getSelected();
		
		
		if (frame.getJTable()!=null){
			int i=0; 
			while (i<frame.getJTable().getRowCount() & !canceled){
				boolean b=false;
				
				
				try {
					b=(Boolean) frame.getJTable().getValueAt(i,0);
				}catch(Exception e){
					
				}
				
				String q="";
				if (b){
					String idcodigo="";
					String lineaproveedor="";
					String idproveedor="";
					try {
						idcodigo=(String)frame.getJTable().getValueAt(i,1);
					}catch(Exception e){
						
					}
					
					try {
						idproveedor=(String)frame.get_txt_idproveedor().getText();
					}catch(Exception e){
						
					}
					try {
						lineaproveedor=(String)frame.getJTable().getValueAt(i,5);
					}catch(Exception e){
						
					}
					
					estado="Borrando Codigo "+idcodigo;
					current++;
					if (si_a_todo){
						this.borrar_item(idcodigo, lineaproveedor, idproveedor);
					}else {
						if (!no_a_todo){
							int n=this._preguntar_si_borra(idcodigo);
							if ( n == 0 | n==1) {
								if (n==1){
									si_a_todo=true;
									this.borrar_item(idcodigo, lineaproveedor, idproveedor);
								}else {
									this.borrar_item(idcodigo, lineaproveedor, idproveedor);
								}
							}else {
								if (n==3){
									no_a_todo=true;
								}
							}
						}	
					}
					
					
				}
				i++;
			}
		}
		
		done=true;
	}

	private void _aviso_operacion_borrar_completa(){
		Runnable _execute = new Runnable() {
	        public void run() {
	        	  aviso("Operacion Borrar Completa con "+current+" operaciones y "+errors+" errores");    	
	        }
		};
		this.invokeAndWait(_execute);
	}
	private boolean check_data(){
		boolean ok=true;
		String idproveedor=frame.get_txt_idproveedor().getText();
		String linea=frame.get_txt_linea().getText();
		ok=data.check_proveedor(idproveedor);
		if (ok){
			ok=data.check_linea(idproveedor, linea);
		}
		return ok;
	}


	


	private void borrar_item(String idcodigo,String lineaproveedor,String idproveedor){
		String q="";
		boolean error=false;
		if (data.check_alias_existence(idcodigo, lineaproveedor, idproveedor)){
			q=data.getBorrarAlias(idcodigo, lineaproveedor, idproveedor);
			
			data.clearBatch();
			data.addBatch(q);
			error=data.executeBatch();
			
			System.out.println(q);
		}
		q=data.getBorrarCodigo(idcodigo, lineaproveedor, idproveedor);
		if (q.compareTo("")!=0){
			data.clearBatch();
			data.addBatch(q);
		error=data.executeBatch();
			
			if (!error){
				System.out.println(idcodigo+">[ok]");
			}else {
				System.out.println(idcodigo+">[error]"+q);
				errors++;
			}
			System.out.println(q);
		}
	}



	public void cancelar_tarea(){
		if (preguntar("Confirma","Cancela la Operacion ?")){
			canceled=true;	
		}
	}


	
	public Object[][] procesar_datos(Object[][] results){
		Object[][] temp=null;
		System.out.println("procesando datos "+results.length);
		temp=new Object[results.length][7];
		try {
			for (int i=0;i<results.length;i++){
				System.out.println("procesando datos "+i);
				temp[i][0]=false;
				temp[i][1]=results[i][0];
				temp[i][2]=results[i][1];
				double p5=0.0;
				try {
				p5=new Double((String) results[i][2]);	
				}catch(Exception e){
					e.printStackTrace();
				}
				temp[i][3]=p5;
				temp[i][4]=results[i][3];
				temp[i][5]=results[i][4];
				temp[i][6]=results[i][5];
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	public void _taskworkGuardar(){
		this.lenght=this.getSelected();
		String idproveedor=frame.get_txt_idproveedor().getText();
		if (frame.getJTable()!=null){
			
				
			
			for (int i=0;i<frame.getJTable().getRowCount();i++){
				boolean b=false;
				try {
					b=(Boolean) frame.getJTable().getValueAt(i,0);
				}catch(Exception e){
					
				}
				String idcodigo=(String)frame.getJTable().getValueAt(i,1);
				String descripcion=(String)frame.getJTable().getValueAt(i,2);
				String precio=frame.getJTable().getValueAt(i,3).toString();
				String linea=(String)frame.getJTable().getValueAt(i,5);
				
				String politica=(String)frame.getJTable().getValueAt(i,6);
				
				
				
				//qty++;
				
				if (b){
					estado="Guardando codigo "+idcodigo;
					current++;
					boolean error=false;
					String q="";
					if (data.check_code(idproveedor, linea, idcodigo)){
							q=data.getUpdate(idcodigo, linea, idproveedor, descripcion, precio, politica);
						//System.out.println("Update "+idproveedor+" "+linea+" "+idcodigo);
					}else {
						//System.out.println("Insert "+idproveedor+" "+linea+" "+idcodigo);
							q=data.getInsert(idcodigo, linea, idproveedor, descripcion, precio, politica);
					}
					data.clearBatch();
					data.addBatch(q);
					error=data.executeBatch();
					if (error)errors++;	
				}
				
			}
				aviso("Operacion guardar Completa con "+current+" operaciones y "+errors+" errores");
			
			
		}
		done=true;
	}

	public void completar(){
		
		int[] columns=frame.getJTable().getSelectedColumns();
		
		for (int cx=0;cx<columns.length;cx++){
			int col=columns[cx];
			if (col==6){
				
				if (frame.getJTable()!=null){
					
					
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
		}
	}
	public void _taskworkCargar() {
		//SearchBody(_alias.get_txt_idpedido());
		String idproveedor=frame.get_txt_idproveedor().getText();
		String linea=frame.get_txt_linea().getText();
		boolean ok=data.check_proveedor(idproveedor);
		if (ok){
			ok=data.check_linea(idproveedor, linea);
			if (!ok){
				String[] options=new String[]{"Si Carga todas las lineas","No!"};
				int n=this.preguntar("Confirmar", "Carga todas las lineas del proveedor?", options, options[1]);
				if (n==0){
					ok=true;
				}
			}
			if (ok){
				String desde=frame.get_txt_codigo_desde().getText();
				String hasta=frame.get_txt_codigo_hasta().getText();
				String q = data.getQuery(desde, hasta, idproveedor, linea);
				 System.out.println(q);
				Object[][] results = data.getResults(q);
				results=this.procesar_datos(results);
				
				_taskworkCargarSwing(results);	
			}
				
		}else {
			aviso("Debe ingresar proveedor valido!!!.Si no sabe busque :-)");
			frame.setJTable(null);
		}
		
	}

	public void _taskworkCargarSwing(Object[][] _results){
		final Object[][] results=_results;
		Runnable _execute = new Runnable() {
	        public void run() {
	        	CustomTable table = create_table(false);
	    		table.setData(results);
	    		table.build();
	    		table.fillData();
	    		block();
	    		frame.setJTable(table.getTable());    	
	        }
		};
		this.invokeLater(_execute);
		done=true;
	}
	
	public void block(){
		frame.get_txt_codigo_desde().setEditable(false);
		frame.get_txt_codigo_hasta().setEditable(false);
		frame.get_txt_linea().setEditable(false);
		frame.get_txt_idproveedor().setEditable(false);
		frame.get_btn_cargar().setEnabled(false);
		frame.get_btn_buscar_desde().setEnabled(false);
		frame.get_btn_buscar_hasta().setEnabled(false);
		frame.get_btn_buscar_linea().setEnabled(false);
		frame.get_btn_buscar_proveedor().setEnabled(false);
		
	}

	public void unblock(){
		frame.get_txt_codigo_desde().setEditable(true);
		frame.get_txt_codigo_hasta().setEditable(true);
		frame.get_txt_linea().setEditable(true);
		frame.get_txt_idproveedor().setEditable(true);
		frame.get_btn_cargar().setEnabled(true);
		frame.get_btn_buscar_desde().setEnabled(true);
		frame.get_btn_buscar_hasta().setEnabled(true);
		frame.get_btn_buscar_linea().setEnabled(true);
		frame.get_btn_buscar_proveedor().setEnabled(true);
	}

	private CustomTable create_table(boolean editable) {
		System.out.println("create table");
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
		col.setName("idcodigo");
		col.setWidth(120);
		col.setEditable(editable);
		col.setEditable(false);
		//col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		
		col = new Column();
		col.setName("Descripcion");
		col.setWidth(300);
		col.setEditable(false);
		//col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		col = new Column();
		col.setName("Precio");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(Double.class);
		//col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		col = new Column();
		col.setName("actualizacion");
		col.setWidth(140);
		col.setEditable(false);
		//col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);
		
		col = new Column();
		col.setName("linea");
		col.setWidth(140);
		col.setEditable(false);
		//col.setCellRenderer(new TableItemColorCellRenderer());
		Table.addColumn(col);

		col = new Column();
		col.setName("Politica");
		col.setWidth(90);
		col.setEditable(true);
		col.setClass(String.class);
		CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_politica);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		Table.addColumn(col);
		
		return Table;
	}



	public void _cargar_proveedor() {
		this.evaluarProveedor(frame.get_txt_idproveedor());
	}
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	public void initialize_proveedor(){
		proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_proveedor_descripcion().setText(descripcion);
				frame.get_txt_linea().requestFocusInWindow();
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


	public void evaluar_linea(JTextField tx){
		String linea=tx.getText();
		String idproveedor=frame.get_txt_idproveedor().getText();
		if (linea.compareTo("")!=0){
			if (data.check_linea(idproveedor, linea)){
				
				frame.get_txt_codigo_desde().requestFocusInWindow();
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
		int n = vLinea.buscarLineaProveedor(tx);
		if (n == 0) {
				aviso("no hay Lineas con ese codigo");
		}
	}
	
	
	public void clean(){
		frame.get_txt_codigo_desde().setText("");
		frame.get_txt_codigo_hasta().setText("");
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_proveedor_descripcion().setText("");
		frame.get_txt_linea().setText("");
		frame.setJTable(null);
		this.unblock();
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
	
	
	public void exportar(){
		if (this.getSelected()>0){
			_exportar();
		}else{
			error("Debe Seleccionar que Filas Desea Exportar");
		}
	}

	public void _exportar(){
		if (exportar!=null){
			exportar.dispose();
		}
		exportar= new aplicacion.herramientas.java.exportar.constructor._Constructor();
		exportar.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		exportar.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		exportar.setParameter(aplicacion.herramientas.java.exportar.interfaces._parametros._tabla, frame.getJTable());
		exportar.build(this.getConstructor());
		exportar.init();
		aplicacion.herramientas.java.exportar.logic._Logic 
		_logic=(aplicacion.herramientas.java.exportar.logic._Logic)exportar.getLogic();
		
		columna col=null;
		
		col=new columna();
		col.setNombre("codigo");
		col.setColumn(1);
		col.setMaster(true);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("descripcion");
		col.setColumn(2);
		col.setMaster(false);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("precio");
		col.setColumn(3);
		col.setMaster(false);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("linea");
		col.setColumn(5);
		col.setMaster(false);
		_logic.addColumn(col);
		
		
		_logic.crear_tabla();
		
	}
	
	public void importar(){
		if (importar!=null){
			importar.dispose();
		}
		importar= new aplicacion.herramientas.java.importar.constructor._Constructor();
		importar.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		importar.setParameter(aplicacion.herramientas.java.importar.interfaces._parametros._tabla, frame.getJTable());
		importar.build(this.getConstructor());
		importar.init();
		aplicacion.herramientas.java.importar.logic._Logic 
		_logic=(aplicacion.herramientas.java.importar.logic._Logic)importar.getLogic();
		
		columna col=null;
		
		col=new columna();
		col.setNombre("codigo");
		col.setColumn(1);
		col.setMaster(true);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("descripcion");
		col.setColumn(2);
		col.setMaster(false);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("linea");
		col.setColumn(5);
		col.setMaster(false);
		_logic.addColumn(col);
		
			
		col=new columna();
		col.setNombre("precio");
		col.setColumn(3);
		col.setMaster(false);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("politica");
		col.setColumn(6);
		col.setMaster(false);
		_logic.addColumn(col);
		
		
	}

	private aplicacion.herramientas.java.visualizadores.Politica vPolitica = null;
	
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
	
	
	public boolean evaluar_table_politica(String valor, int row, int col) {
		boolean error=false;
		if (valor.compareTo("") != 0) {
			if (data.check_politica(valor)) {
				Object[][] results = data.getPolitica(valor);
				
			} else {
				error=true;
			}
		}
		return error;
	}
	public void evaluar_table_politica(JTextField tx, int row, int col) {
		String valor = "";
		valor = tx.getText();
		 if (!this.evaluar_table_politica(valor, row, col)) {
			if (row<frame.getJTable().getRowCount()-1){
				frame.getJTable().changeSelection(row+1, col, false,false);
				frame.getJTable().editCellAt(row+1, col);
				frame.getJTable().transferFocus();
			}
		 } 	else {
			error("Error en politica");
			tx.requestFocusInWindow();
		}

	}
}
