package aplicacion.contabilidad.recodificacion.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.JCheckBox;



import aplicacion.herramientas.java.Crono;
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
import aplicacion.sistema.analizador.gui._Frame;
import aplicacion.sistema.analizador.interfaces.*;
import aplicacion.sistema.analizador.logic._Data;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String estado="";
	private int current;
	private int lenght;
	private boolean done,canceled;
	private int errors=0;
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	public void goCargar(){
		
		goCargar(frame.getJTextArea());
	}
	public void _taskworkCargar(JTextArea tx){
		String query=tx.getText();
		Object[][] results=data.getResults(query);
		if (results!=null){
			if (results.length>0){
				results=this.prepareResults(results);
				this.createTable(results);
			}
		}
		
	}
	private void createTable(Object[][] results){
		final Object[][] _results=results;
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			create_table(_results);
		}else{
			Runnable _execute=new Runnable(){
				public void run(){
					create_table(_results);		
				}
			};
			javax.swing.SwingUtilities.invokeLater(_execute);
		}
	}
	private Object[][] prepareResults(Object[][] results){
		Object[][] tmp=new Object[results.length][results[0].length+1];
		for (int i=0;i<results.length;i++){
			tmp[i][0]=false;
			for (int j=0;j<results[0].length;j++){
				tmp[i][j+1]=results[i][j];
			}
		}
		return tmp;
	}
	
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();

		Column col = new Column();
		String[] names=data.getColumns();
		
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
		
		for (int i=1;i<results[0].length;i++){
			col = new Column();
			col.setName(names[i-1]);
			col.setWidth(180);
			col.setEditable(false);
			col.setClass(String.class);
			table.addColumn(col);
			
		}

		table.setData(results);

		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);

		table.build();

		table.fillData();
		JTable _table = table.getTable();
		
		
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		frame.setJTable(table.getTable());
		frame.get_chk_seleccionar().setSelected(false);
		done=true;
	}
	
	
	
	
	
	public void clean(){
		frame.setJTable(null);
		frame.getJTextArea().setText("");
		canceled=false;
		frame.get_chk_seleccionar().setSelected(false);
		done=true;
	}
	
		
	public void focus(){
		frame.getJTextArea().requestFocusInWindow();
	}
	
	public void goCargar(JTextArea tx) {
		frame.getJProgressBar().setIndeterminate(true);
		this.createTimer();
		final JTextArea _tx=tx;
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskCargar(_tx);
				}
			};
		}
		if (Timer!=null) {
			Timer.start();
		}
		worker.start();
		
	}	
	
	class _taskCargar {
		_taskCargar(JTextArea tx) {
			_taskworkCargar(tx);
		}
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
		frame.get_btn_guardar().setEnabled(true);
	}
	
	public void seleccionar(boolean b){
		if (frame.getJTable()!=null){
			int[] indexes=null;
			try {
				indexes=frame.getJTable().getSelectedRows();
			}catch(Exception e){
				
			}
			if (indexes!=null & frame.getJTable().getSelectedColumn()==2){
				for (int i=0;i<indexes.length;i++){
					frame.getJTable().setValueAt(b, indexes[i], 0);
				}
			}else {
				for (int i=0;i<frame.getJTable().getRowCount();i++){
					frame.getJTable().setValueAt(b, i, 0);
				}	
			}
			
		}
	}
	
public void deseleccionar(){
		
		frame.getJTable().clearSelection();
		frame.getJTable().transferFocus();
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

public void exportar(){
	if (this.getSelected()>0){
		_exportar();
	}else{
		error("Debe Seleccionar que Filas Desea Exportar");
	}
}
private aplicacion.herramientas.java.exportar.constructor._Constructor exportar=null;
public void _exportar(){
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
	
	for (int i=1;i<frame.getJTable().getColumnCount();i++){
		col=new columna();
		
		String name=frame.getJTable().getColumnName(i);
		col.setNombre(name);
		col.setColumn(i);
		_logic.addColumn(col);	
	}
	
	
	
	
	
	_logic.crear_tabla();
	
}
}
