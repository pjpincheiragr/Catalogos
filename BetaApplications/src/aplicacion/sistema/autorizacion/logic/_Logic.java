package aplicacion.sistema.autorizacion.logic;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.JCheckBox;


import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.inventario.planilla.logic.TableItemColorCellRenderer;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.autorizacion.gui._Frame;
import aplicacion.sistema.autorizacion.interfaces.*;
import aplicacion.sistema.autorizacion.logic._Data;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	
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

		Column col = new Column();
		
		
		col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_chk);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Nombre");
		col.setWidth(180);
		col.setEditable(true);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("Area");
		col.setWidth(160);
		col.setEditable(true);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idaplicacion");
		col.setWidth(100);
		col.setEditable(true);
		col.setClass(String.class);
		table.addColumn(col);
		table.setData(results);

		col = new Column();
		col.setName("Icono");
		col.setWidth(70);
		col.setEditable(true);
		chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_chk_icono);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);

		table.build();

		table.fillData();
		JTable _table = table.getTable();
		
		aplicacion.sistema.autorizacion.constructor._Constructor C=
			(aplicacion.sistema.autorizacion.constructor._Constructor) this.getConstructor();
		_table.addMouseListener(C.getMouseListener());
		_table.addKeyListener(C.getKeyListener());
		frame.setJTable(table.getTable());
	}
	
	
	
	public void evaluar_tabla_id(JCheckBox chk,int row){
		String iduser=frame.get_txt_usuario().getText();
		String idaplicacion=frame.getJTable().getValueAt(row, 3).toString();
		boolean icono=(Boolean)frame.getJTable().getValueAt(row, 4);
		if (chk.isSelected()){
			data.insert(iduser, idaplicacion,icono);
		}else{
			data.delete(iduser, idaplicacion);
		}
	}
	
	public void evaluar_tabla_id_icono(JCheckBox chk,int row){
		String iduser=frame.get_txt_usuario().getText();
		String idaplicacion=frame.getJTable().getValueAt(row, 3).toString();
		boolean selected=(Boolean)frame.getJTable().getValueAt(row, 0);
		if (!selected){
			if (chk.isSelected()){
				frame.getJTable().setValueAt(true, row, 0);
				data.insert(iduser, idaplicacion,true);	
			}
		}else{
			data.update(iduser, idaplicacion, chk.isSelected());
		}
		
	}
	
	
	public void clean(){
		frame.setJTable(null);
		frame.get_txt_usuario().setText("");
		frame.get_txt_usuario().requestFocusInWindow();
		frame.get_txt_usuario().setEnabled(true);
		frame.get_btn_buscar_usuario().setEnabled(true);
	}
	
	public void cargar_parametros(){
		String valor=frame.get_txt_usuario().getText();
		if (data.existeUsuario(valor)){
			this.cargar_parametros(valor);	
		}else {
			error("Debe cargar un usuario valido");
			frame.setJTable(null);
		}
		
	}
	public Object[][] preparar_datos(Object[][] results){
		for (int i=0;i<results.length;i++){
			System.out.println(i+">"+results[i][0].toString());
			results[i][0]=results[i][0].toString().compareTo("1")==0;
			results[i][4]=results[i][4].toString().compareTo("1")==0;
		}
		return results;
	}
	
	public void cargar_parametros(String val){
		frame.get_txt_usuario().setText(val);
		frame.get_txt_usuario().setEnabled(false);
		frame.get_btn_buscar_usuario().setEnabled(false);
		Object[][] results=data.getParametroSqlite(val);
		if (results!=null){
			if (results.length>0){
				results=this.preparar_datos(results);
				this.create_table(results);
				
			}
		}
	}
	public void buscarUsuario(JTextField tx) {
		if (vSelector != null) {
			vSelector.dispose();
		}
		vSelector = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
		vSelector.setParameter(aplicacion.modelo.interfaces._parametros.connector, data.getConnectionHandler());
		vSelector.build(this.getConstructor());
		vSelector.init();
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) vSelector
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("iduser");
		c.setAlias("idUser");
		c.setColumnField(tx);
		c.setWidth(120);
		logic.addColumn(c);
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("nombre");
		c.setAlias("nombre");
		c.setWidth(120);
		logic.addColumn(c);
		logic.setFromTable("b_users ");
		

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("iduser");
		f.setValor(tx.getText());
		logic.addFilter(f);

		logic.setTop(100);
		
		logic.setOrderby("iduser");

		int x = frame.getLocation().x;
		int y = frame.getLocation().x;

	
		int n = logic._loadoptions();
		if (n == 0) {
			aviso("No existe el usuario");
		}
		
	}
	
	public void BuscarUsuario(JTextField txt) {
		aplicacion.herramientas.java.sortableselector.constructor._Constructor CC = new aplicacion.herramientas.java.sortableselector.constructor._Constructor();
		CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) CC.getLogic();
		columna c = new columna();
		c.setNombre("iduser");
		c.setAlias("IdUser");
		c.setWidth(120);
		c.setMaster(true);
		c.setColumnField(txt);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("nombre");
		c.setAlias("Nombre");
		c.setWidth(120);
		logic.addColumn(c);

		logic.addFromTable("b_users");
		Filtro f = new Filtro();
		f.setNombre("iduser");
		f.setAlias("IdUser");
		f.setWidth(200);
		f.setFocus(true);
		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("nombre");
		f.setAlias("Nombre");
		f.setWidth(200);
		f.setFocus(true);
		logic.addFilter(f);
			
		logic.addOrder("iduser");
		logic.init();
	}
	
	public void focus(){
		frame.get_txt_usuario().requestFocusInWindow();
	}
	
	public void BuscarUsuario(){
		this.BuscarUsuario(frame.get_txt_usuario());
	}
}
