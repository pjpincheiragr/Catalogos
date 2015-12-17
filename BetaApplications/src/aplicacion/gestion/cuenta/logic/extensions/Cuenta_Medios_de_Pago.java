package aplicacion.gestion.cuenta.logic.extensions;


import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import aplicacion.gestion.cuenta.constructor.*;
import aplicacion.gestion.cuenta.logic.*;
import aplicacion.gestion.cuenta.interfaces.*;
import aplicacion.gestion.cuenta.gui.*;


import aplicacion.modelo.logic.Logic_Extension;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.table.*;

/**
 * Clase para el control de datos de la aplicacion
 */
public class Cuenta_Medios_de_Pago extends Logic_Extension {
	 
	
	private boolean getDate(String s){
		DateFormat formatter;
	    Date date=null;
	    boolean ok=true;
	    try {
	    	formatter = new SimpleDateFormat("dd-MM-yyyy");
	    	date = (Date)formatter.parse(s);
	        System.out.println(date);
	        
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    	ok=false;
	    }
	    return ok;
	}
	
	public void delete_renglonMedios(JTable table,int row){
		int n = JOptionPane.showConfirmDialog(
			    _logic.getFrame(),
			    "Confirma Borrar Renglon "+(row+1)+" ?",
			    "Borrar",
			    JOptionPane.YES_NO_OPTION);
		if ( n == JOptionPane.YES_OPTION) {
			DefaultTableModel defaultTableModelMedios=(DefaultTableModel) table.getModel();
			defaultTableModelMedios.removeRow(row);
			try{
				//this.codigos.remove(row);
			
			}catch(Exception e){
				
			}
			
			if (defaultTableModelMedios.getRowCount()==0){
				defaultTableModelMedios.setRowCount(1);
				//this.jTable.setValueAt(1, 0, 0);
			}
			table.repaint();
			
		}	
	}
	
	public double getPago(JTable table){
		double sum=0;
		double ant=0;
		for (int i=0;i<table.getRowCount();i++){
			String publico="";
			String codigo="";
			
			boolean error=false;
			double p2=0;
			
			try{
				codigo=table.getValueAt(i, 0).toString();
				publico=table.getValueAt(i, 7).toString();
				publico=new Convertidor().replace(publico, ",", "");
				p2=new Double(publico);
			}catch(Exception e){
				error=true;
			}
			if (!error){
					sum=sum+p2;	
				
			}else {
				System.out.println(i+" "+error);
			}
		}
		
		return sum;
	}
	
	
	public void load_medios_de_pago(String id){
		_Data data=(_Data)this._logic.getData();
		Object[][] results=data.load_medios_de_pago(id);
		if (results!=null){
			if (results.length>0){
				this.create_table_medios(results);
			}
		}
	}
	
	private boolean cheque(String idcomp){
		boolean ok=false;
		ok=(idcomp.toUpperCase().compareTo("CHT")==0);
		return ok;
	}
	
	public void create_table_medios(Object[][] results) {
		CustomTable table = new CustomTable();
		_Frame frame=(_Frame)this._logic.getFrame();
		_Constructor constructor=(_Constructor)_logic.getConstructor();
		
		Column col = null;
		
		col = new Column();
		col.setName("idbanco");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		
		col = new Column();
		col.setName("Descripcion");
		col.setWidth(180);
		
		col.setEditable(false);
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("serie");
		col.setWidth(50);
		col.setEditable(true);
		table.addColumn(col);
		
		col = new Column();
		col.setName("numero");
		col.setWidth(110);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("importe");
		col.setWidth(70);
		col.setEditable(true);
		table.addColumn(col);
		
		col = new Column();
		col.setName("vencimento");
		col.setWidth(90);
		col.setEditable(true);
		table.addColumn(col);
		
		col = new Column();
		col.setName("entrada");
		col.setWidth(90);
		col.setEditable(true);
		table.addColumn(col);
		
		col = new Column();
		col.setName("salida");
		col.setWidth(90);
		col.setEditable(true);
		
		table.addColumn(col);
		
		
		table.setData(results);
		table.addKeyListener(constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 9);
		//table.setHeaderFont(fuente);
		
		table.build();
		
		table.fillData();
		JTable _table=table.getTable();
		
		frame.setJTable(table.getTable());
	}

	
}
