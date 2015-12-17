package aplicacion.sistema.cptes.logic;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JTextField;



import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.configuracionserver.gui._Frame;
import aplicacion.sistema.configuracionserver.interfaces.*;
import aplicacion.sistema.configuracionserver.logic._Data;

public class _Logic extends Logic {
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

		Column col = new Column();
		col = new Column();
		col.setName("id");
		col.setWidth(140);
		col.setEditable(true);
		col.setClass(String.class);
		CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_id);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("value");
		col.setWidth(260);
		col.setEditable(true);
		col.setClass(String.class);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_item_value);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		table.setData(results);

		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);

		table.build();

		table.fillData();
		JTable _table = table.getTable();
		_table.setName(_Interface._table);
		aplicacion.sistema.configuracionserver.constructor._Constructor C=
			(aplicacion.sistema.configuracionserver.constructor._Constructor) this.getConstructor();
		_table.addMouseListener(C.getMouseListener());
		_table.addKeyListener(C.getKeyListener());
		frame.setJTable(table.getTable());
	}
	
	public void delete(int row){
		String id=frame.getJTable().getValueAt(row, 0).toString();
		if (preguntar("Confirmar","Desea eliminar el parametro "+id)){
			data.deleteParametro(id);
			DefaultTableModel model=(DefaultTableModel) frame.getJTable().getModel();
			model.removeRow(row);
		}
	}
	
	public void evaluar_tabla_id(JTextField tx,int row){
		String id=tx.getText();
		if (id.compareTo("")!=0){
			frame.getJTable().changeSelection(row, 1, false, false);
			frame.getJTable().editCellAt(row, 1);
			frame.getJTable().transferFocus();
		}else{
			tx.requestFocusInWindow();
		}
	}
	public void evaluar_tabla_value(JTextField tx,int row){
		
		String id=frame.getJTable().getValueAt(row, 0).toString();
		String valor=tx.getText();
		
		if (data.existeParametro(id)){
				data.updateParametro(id, valor);
		}else{
				data.insertParametro(id, valor);
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
		Object[][] results=data.getParametro(val);
		if (results!=null){
			if (results.length>0){
				this.create_table(results);
			}
		}
	}
}
