package aplicacion.sistema.query.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JTextField;




import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;

import aplicacion.sistema.query.gui._Frame;
import aplicacion.sistema.query.interfaces.*;
import aplicacion.sistema.query.logic._Data;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.XML;
public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.sistema.autorizacion.constructor._Constructor aplicaciones = null;
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public void guardar(){
		boolean error=false;
		String idquery=frame.get_txt_idquery().getText();
		String idproveedor=frame.get_txt_idproveedor().getText();
		String odbc=frame.get_txt_odbc().getText();
		String tabla=frame.get_txt_tabla().getText();
		boolean limit=frame.get_chk_limit().isSelected();
		data.clearBatch();
		String q="";
		if (!Query.existe(idquery)){
			q=data.insert(idquery, idproveedor, odbc, tabla,limit);	
		}else{
			q=data.update(idquery, idproveedor, odbc, tabla,limit);
		}
		System.out.println(q);
		data.addBatch(q);
		String qd=data.getDelete(idquery);
		data.addBatch(qd);
		for (int i=0;i<frame.getJTable().getRowCount();i++){
			try {
				String _nombre=frame.getJTable().getValueAt(i, 0).toString();
				_nombre=_nombre.replaceAll("'", "''");
				String _alias=frame.getJTable().getValueAt(i, 1).toString();
				boolean columna=false;
				
				boolean filtro=false;
				try {
					columna=(Boolean)frame.getJTable().getValueAt(i, 2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					filtro=(Boolean)frame.getJTable().getValueAt(i, 3);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String qx=data.insert(idquery, _nombre, _alias, columna, filtro);
				System.out.println(qx);
				data.addBatch(qx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		error=data.executeBatch();
		if (!error){
			aviso("Se Grabo Correctamente");
		}else{
			error("Error Grabando Query");
		}
		
	}
	
	private aplicacion.herramientas.java.buscadores.Dynamic bDynamic=null;
	public void BuscarDynamic(JTextField ext) {
		 if (bDynamic!=null){
			 bDynamic.getConstructor().dispose();
		 }
	 bDynamic=new aplicacion.herramientas.java.buscadores.Dynamic(this.getConstructor());
	 bDynamic.setTop(500);
	 columna c=new columna();
	 c.setNombre("");
	 c.setAlias("");
	 bDynamic.AddColumn(c);
	 bDynamic.Buscar(ext);
	} 
	
	
	public void aplicaciones(){
		bDynamic=new aplicacion.herramientas.java.buscadores.Dynamic(this.getConstructor());
		 columna c=null;
		 Filtro f=null;
		 String table=frame.get_txt_tabla().getText();
		 bDynamic.setTop(500);
		 bDynamic.setTable(table);
		 String odbc=frame.get_txt_odbc().getText();
		 bDynamic.setLimit(frame.get_chk_limit().isSelected());
		 bDynamic.setIdconector(odbc);
		 
		 for (int i=0;i<frame.getJTable().getRowCount();i++){
				try {
					String _nombre=frame.getJTable().getValueAt(i, 0).toString();
					String _alias=frame.getJTable().getValueAt(i, 1).toString();
					boolean columna=false;
					try {
						columna=(Boolean)frame.getJTable().getValueAt(i, 2);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					boolean filtro=false;
					try {
						filtro=(Boolean)frame.getJTable().getValueAt(i, 3);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (columna){
						c=new columna();
						 c.setNombre(_nombre);
						 c.setAlias(_alias);
						 bDynamic.AddColumn(c);
					}
					if (filtro){
						f=new Filtro();
						f.setNombre(_nombre);
						f.setAlias(_alias);
						bDynamic.AddFiltro(f);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 
		 bDynamic.Buscar();	
	}
	
	public void delete(){
		String id=frame.get_txt_idquery().getText();
		if (preguntar("Confirmar","Desea eliminar la consulta "+id+"?")){
			data.delete(id);
			data.delete_aplicaciones(id);
			clean();
		}
	}
	
	
	
	public void clean(){
		frame.get_txt_idquery().setText("");
		frame.get_txt_odbc().setText("");
		frame.get_txt_tabla().setText("");
		frame.get_txt_idproveedor().setText("");
		
		frame.get_btn_buscar_usuario().setEnabled(true);
		frame.setJTable(null);
		frame.get_txt_idquery().setEditable(true);
		frame.get_txt_idquery().requestFocusInWindow();
	}
	
	
	
	
	public void cargar_parametros(){
		String iduser=frame.get_txt_idquery().getText();
		this.cargar_parametros(iduser);
	
	}
	
	public void cargar_parametros(String val){
		Object[][] results=data.getParametroSqlite(val);
		if (results!=null){
			if (results.length>0){
				String iduser=(String) results[0][0];
				String nombre=(String) results[0][1];
				String pass=(String) results[0][2];
				String background=(String) results[0][3];
				String superusuario=(String) results[0][4];
				String monitor=(String) results[0][5];
				frame.get_txt_idproveedor().setText(nombre);
				frame.get_txt_idquery().setEnabled(false);
				frame.get_btn_buscar_usuario().setEnabled(false);
				
			}
		}
	}
	
	public void cargar(String codigo){
		Object[][] results=data.getQuery(codigo);
		if (results!=null){
			if (results.length>0){
				String idproveedor=(String)results[0][1];
				String odbc=(String)results[0][2];
				String tabla=(String)results[0][3];
				String limit=(String)results[0][4];
				frame.get_txt_idproveedor().setText(idproveedor);
				frame.get_txt_odbc().setText(odbc);
				frame.get_txt_tabla().setText(tabla);
				frame.get_chk_limit().setSelected(limit.compareTo("1")==0);
				
				Object[][] columns=data.getQueryColumns(codigo);
				this.create_table(columns);
				
			}
		}
	}
	
	
	private aplicacion.herramientas.java.evaluadores.Query Query=null;
	public void initialize_Query(){
		Query=new aplicacion.herramientas.java.evaluadores.Query(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_idproveedor().setText(descripcion);
			}
		};
		Query.setConstructor(this.getConstructor());
	}
	public void BuscarQuery(JTextField tx){
		Query.Buscar(tx);
	}
	public void BuscarQuery(){
		Query.Buscar(frame.get_txt_idquery());
	}
	public void buscarQuery(JTextField tx){
		Query.buscar(tx);
	}
	
	public void evaluarQuery(JTextField tx){
		String codigo=tx.getText();
		if (codigo.compareTo("")!=0){
			if (Query.existe(codigo)){
				this.cargar(codigo);	
			}else{
				if (preguntar("Confirmar","La consulta "+codigo+" no existe. Desea Crearla?")){
					frame.get_txt_idquery().setEditable(false);
					frame.get_txt_idproveedor().requestFocusInWindow();
				}
			}	
		}else{
			Query.evaluate(tx);
		}
		
		
	}
	

	
	
	
	public void focus(){
		frame.get_txt_idquery().requestFocusInWindow();
	}
	

	public Object[][] processData(Object[][] results){
		for (int i=0;i<results.length;i++){
			results[i][2]=results[i][2].toString().compareTo("1")==0;
			results[i][3]=results[i][3].toString().compareTo("1")==0;
		}
		return results;
	}
	
	public void updateOrigen(boolean origen,int row){
		String iduser=frame.get_txt_idquery().getText();
		String idcaja=frame.getJTable().getValueAt(row, 0).toString();
		
	}
	
	public void updateDestino(boolean destino,int row){
		String iduser=frame.get_txt_idquery().getText();
		String idcaja=frame.getJTable().getValueAt(row, 0).toString();
		
	}
	
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();
		
		
		col = new Column();
		col.setName("nombre");
		col.setWidth(360);
		col.setEditable(true);
		CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._txt_tabla_nombre);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);
		
		col = new Column();
		col.setName("alias");
		col.setWidth(120);
		col.setEditable(true);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._txt_tabla_alias);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);
		
		col = new Column();
		col.setName("columna");
		col.setWidth(80);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._chk_origen);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("filtro");
		col.setClass(Boolean.class);
		col.setWidth(80);
		col.setEditable(true);
		chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._chk_destino);
		col.setCellEditor(chkce.getCellCheck());
		
		table.addColumn(col);
		
		
		
		results=this.processData(results);
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		
		frame.setJTable(table.getTable());
	}
	
	public void evaluate_nombre(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_odbc().requestFocusInWindow();
		}
	}
	public void evaluate_odbc(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_tabla().requestFocusInWindow();
		}
	}
	public void evaluate_tabla(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			Object[][] results=new Object[][]{{"","",false,false}};
			this.create_table(results);
		}
	}
	
	public void evaluate_tabla_nombre(JTextField tx,int col,int row){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.getJTable().changeSelection(row, col+1, false, false);
			frame.getJTable().editCellAt(row, col+1);
			frame.getJTable().transferFocus();
		}else{
			aviso("Ingrese una descripcion de Columna");
		}
	}
	
	public void evaluate_tabla_alias(JTextField tx,int col,int row){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.getJTable().setValueAt(true, row, col+1);
			DefaultTableModel model=(DefaultTableModel)frame.getJTable().getModel();
			if (row==model.getRowCount()-1){
				model.setRowCount(model.getRowCount()+1);
			}
			frame.getJTable().changeSelection(row+1, 0, false, false);
			frame.getJTable().editCellAt(row+1, 0);
			frame.getJTable().transferFocus();	
		}else{
			aviso("Ingrese un alias de Columna");
		}
	}
	
	}
