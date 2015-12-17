package aplicacion.sistema.aplicacion.logic;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JTextField;



import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.aplicacion.gui._Frame;
import aplicacion.sistema.aplicacion.interfaces.*;
import aplicacion.sistema.aplicacion.logic._Data;

public class _Logic extends Logic {
	private aplicacion.herramientas.java.exportar.constructor._Constructor exportar=null;
	private aplicacion.herramientas.java.importar.constructor._Constructor importar=null;
	private _Frame frame;
	private _Data data;
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		
		//table.addMouseListener(this._constructor.getMouseListener());
		Column col = new Column();
		col = new Column();
		col.setName("idAplicacion");
		col.setWidth(180);
		col.setClass(String.class);

		/*CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_id);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());*/

		col.setEditable(false);
		table.addColumn(col);
		col = new Column();
		col.setName("Label");
		col.setWidth(160);
		col.setEditable(false);
		col.setClass(String.class);

/*		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_label);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());*/

		table.addColumn(col);
		
		table.addColumn(col);

		col = new Column();
		col.setName("Area");
		col.setWidth(140);
		col.setEditable(false);
		col.setClass(String.class);
/*		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_area);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());*/
		table.addColumn(col);
		
		col = new Column();
		col.setName("icono");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
/*		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_icono);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());*/
		table.addColumn(col);
		
		col = new Column();
		col.setName("launcher");
		col.setWidth(300);
		col.setEditable(false);
		col.setClass(String.class);
/*		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_launcher);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());*/
		table.addColumn(col);
		
		table.setData(results);

		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table);
		aplicacion.sistema.aplicacion.constructor._Constructor C=(aplicacion.sistema.aplicacion.constructor._Constructor) this.getConstructor();
		_table.addMouseListener(C.getMouseListener());
		_table.addKeyListener(C.getKeyListener());
		
		final JTable _tablex=_table;
		Runnable _execute=new Runnable(){
			   public void run() {
				   frame.setJTable(_tablex);		   
			   }
		};
		this.invokeLater(_execute);
		
	}
	
	public void delete(int row){
		
		String id=frame.getJTable().getValueAt(row, 0).toString();
		
		if (preguntar("Confirmar","Desea eliminar el item de menu "+id)){
			data.delete(id);
			DefaultTableModel model=(DefaultTableModel) frame.getJTable().getModel();
			model.removeRow(row);
		}
	}
	
	public void nuevo(){
		
		if (frame.getJTable()==null){
			Object[][] results=new Object[][]{{"","",""}};
			this.create_table(results);
		}
		else{
			if (frame.getJTable().getRowCount()<=0){
				Object[][] results=new Object[][]{{"","",""}};
				this.create_table(results);	
			}
			else{
				aviso("Esta Opcion solo esta disponible al momento de generar una nueva tabla de aplicaciones");	
			}
			
		}
	}
	
	
	public void evaluar_tabla_id(JTextField tx,int row){
		String id=tx.getText();
	
		if (id.compareTo("")!=0){
			frame.getJTable().changeSelection(row, 1, false, false);
			frame.getJTable().editCellAt(row, 1);
			frame.getJTable().transferFocus();
		}
		else{
			tx.requestFocusInWindow();
		}
	}
	
	
	public void evaluar_tabla_label(JTextField tx,int row){
	
		String id=tx.getText();
	
		if (id.compareTo("")!=0){
			frame.getJTable().changeSelection(row, 2, false, false);
			frame.getJTable().editCellAt(row, 2);
			frame.getJTable().transferFocus();
		}
		else{
			tx.requestFocusInWindow();
		}
	}
	
	
	public void evaluar_tabla_area(JTextField tx,int row){
	
		String id=tx.getText();
		
		if (id.compareTo("")!=0){
			frame.getJTable().changeSelection(row, 3, false, false);
			frame.getJTable().editCellAt(row, 3);
			frame.getJTable().transferFocus();
		}
		else{
			tx.requestFocusInWindow();
		}
	}
	
	
	public void evaluar_tabla_launcher(JTextField tx,int row){
		
		String id=frame.getJTable().getValueAt(row, 0).toString();
		String label=frame.getJTable().getValueAt(row, 1).toString();
		String area=frame.getJTable().getValueAt(row, 2).toString();
		String icono=frame.getJTable().getValueAt(row, 3).toString();
		String launcher=tx.getText();
		
		if (data.existe(id)){
				data.update(id, label,area,launcher,icono);
		}
		else{
				data.insert(id, label,area,launcher,icono);
		}
		
		DefaultTableModel model=(DefaultTableModel) frame.getJTable().getModel();
		
		if (row>=model.getRowCount()-1){
				model.setRowCount(model.getRowCount()+1);
		}
		row++;
		frame.getJTable().changeSelection(row, 0, false, false);
		frame.getJTable().editCellAt(row, 0);
		frame.getJTable().transferFocus();
		
	}
	
	public void evaluar_tabla_icono(JTextField tx,int row){
		String id=tx.getText();
		
		if (id.compareTo("")!=0){
			frame.getJTable().changeSelection(row, 4, false, false);
			frame.getJTable().editCellAt(row, 4);
			frame.getJTable().transferFocus();
		}
		else{
			tx.requestFocusInWindow();
		}
	}
	
	public void clean(){
		frame.setJTable(null);
		frame.get_txt_id().setText("");
		frame.get_txt_id().requestFocusInWindow();
	}
	
	
	public void cargar_parametros(){
		frame.get_txt_id().setText("");
		this.cargar_parametros("");
	}
	
	public void cargar_parametros(String val){
		//aviso("cargar parametros");
		Object[][] results=data.getParametroSqlite(val);
	
		if (results!=null){
			if (results.length>0){
				final Object[][] _results=results;
				Runnable execute=new Runnable(){
					public void run(){
						create_table(_results);		
					}
				};
				this.invokeLater(execute);
			}
		}
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
		col.setNombre("idaplicacion");
		col.setColumn(0);
		col.setMaster(true);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("menu_nombre");
		col.setColumn(1);
		col.setMaster(false);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("area");
		col.setColumn(2);
		col.setMaster(false);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("lanzador");
		col.setColumn(3);
		col.setMaster(false);
		_logic.addColumn(col);
		
	}

	
	public void exportar(){
	
		if (exportar!=null){
			exportar.dispose();
		}
		
		exportar= new aplicacion.herramientas.java.exportar.constructor._Constructor();
		exportar.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		exportar.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		exportar.setParameter(aplicacion.herramientas.java.exportar.interfaces._parametros._tabla, frame.getJTable());
		exportar.build(this.getConstructor());
		exportar.init();
		aplicacion.herramientas.java.exportar.logic._Logic 
		_logic=(aplicacion.herramientas.java.exportar.logic._Logic)exportar.getLogic();
		
		columna col=null;
		
		col=new columna();
		col.setNombre("idaplicacion");
		col.setColumn(0);
		col.setMaster(true);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("menu_nombre");
		col.setColumn(1);
		col.setMaster(false);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("area");
		col.setColumn(2);
		col.setMaster(false);
		_logic.addColumn(col);
		
		col=new columna();
		col.setNombre("lanzador");
		col.setColumn(3);
		col.setMaster(false);
		_logic.addColumn(col);
		
		
		_logic.crear_tabla();
	}
	
/*	public void editar_aplicacion(int row){
		if (frame.getJTable()!=null){
			if (row>=0 & row<frame.getJTable().getRowCount()){
				String idaplicacion=frame.getJTable().getValueAt(row, 0).toString();
				editar_aplicacion(idaplicacion);
			}
			
		}
	}
	*/
	
	
	public void editar_aplicacion(int row){
		
		if (frame.getJTable()!=null){
			if (row>=0 & row<frame.getJTable().getRowCount()){
				String idaplicacion=frame.getJTable().getValueAt(row, 0).toString();
				editar_aplicacion(idaplicacion);
			}
			else
				this.error("columna inexistente");
		}
		else
			this.error("tabla nula");
	}
	
	public void editar_aplicacion(String idaplicacion){
	
		System.out.println("idaplicacion: "+idaplicacion);
		aplicacion.sistema.aplicacion2.constructor._Constructor
		aplicacion2=new aplicacion.sistema.aplicacion2.constructor._Constructor();
		aplicacion2.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler().Clone());
		aplicacion2.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		aplicacion2.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		aplicacion2.setParameter(aplicacion.sistema.aplicacion.interfaces._Parametros._idaplicacion2, idaplicacion);
		aplicacion2.build(this.getConstructor());

		this.getConstructor().addChild(aplicacion2);
		aplicacion2.init();
	}
	
}