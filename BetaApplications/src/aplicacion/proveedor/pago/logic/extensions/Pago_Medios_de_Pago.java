package aplicacion.proveedor.pago.logic.extensions;


import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;




import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.*;
import aplicacion.proveedor.pago.constructor.*;
import aplicacion.proveedor.pago.logic.*;
import aplicacion.proveedor.pago.interfaces.*;
import aplicacion.proveedor.pago.gui.*;
import aplicacion.modelo.logic.Logic_Extension;
import aplicacion.herramientas.java.table.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import stock1.Convertidor;



/**
 * Clase para el control de datos de la aplicacion
 */
public class Pago_Medios_de_Pago extends Logic_Extension {
	 
	
	public void eval_cod_banc(JTable table,JTextField tx,int row,int col){
		boolean ok=false;
		_Data data=(_Data)this._logic.getData();
		String code=tx.getText();
		code=code.replace(" ", "");
		if (code.compareTo("")!=0){
			String val=data.getBanco(code);
				if (val.compareTo("")!=0){
					
					table.setValueAt(val, row, col+1);
					table.changeSelection(row, col+2, false, false);
					table.editCellAt(row, col+2);
					table.transferFocus();
				}else {
					JOptionPane.showMessageDialog(_logic.getFrame(),"Codigo de Banco Inexistente");
					table.changeSelection(row, col, false, false);
					table.editCellAt(row, col);
					table.transferFocus();
				}
			
		}else {
			JOptionPane.showMessageDialog(_logic.getFrame(),"Ingreser el Codigo de Banco");
			table.changeSelection(row, col, false, false);
			table.editCellAt(row, col);
			table.transferFocus();
		}
}
	
	public void eval_serie(JTable table,JTextField tx,int row,int col){
		String code=tx.getText();
		code=code.replace(" ", "");
		if (code.compareTo("")!=0){
			table.changeSelection(row, col+1, false, false);
			table.editCellAt(row, col+1);
			table.transferFocus();
		}else {
			JOptionPane.showMessageDialog(_logic.getFrame(), "Debe ingresar el digito de serie del valor");
			table.changeSelection(row, col, false, false);
			table.editCellAt(row, col);
			table.transferFocus();
		}
	}
	
	public void eval_numero(JTable table,JTextField tx,int row,int col){
		String code=tx.getText();
		code=code.replace(" ", "");
		if (code.compareTo("")!=0){
			table.changeSelection(row, col+1, false, false);
			table.editCellAt(row, col+1);
			table.transferFocus();
		}else {
			JOptionPane.showMessageDialog(_logic.getFrame(), "Debe ingresar el numero del valor");
			table.changeSelection(row, col, false, false);
			table.editCellAt(row, col);
			table.transferFocus();
		}
	}
	
	public void eval_vencimiento(JTable table,JTextField tx,int row,int col){
		String code=tx.getText();
		code=code.replace(" ", "");
		if (code.compareTo("")!=0){
				if (getDate(code)){
					if (eval_venc(code)){
						table.changeSelection(row, col+1, false, false);
						table.editCellAt(row, col+1);
						table.transferFocus();	
					}else {
						 int n = JOptionPane.showConfirmDialog(
					  			 new JFrame("Vencimiento"),
					  			 "La Fecha "+code+" esta vencida. Esto es Correcto? ",
					  			 "Confirmar",
					  			 JOptionPane.YES_NO_OPTION);
					  		if ( n == JOptionPane.YES_OPTION) {
					  			table.changeSelection(row, col+1, false, false);
					  			table.editCellAt(row, col+1);
					  			table.transferFocus();
					  		}else {
					  			table.changeSelection(row, col, false, false);
					  			table.editCellAt(row, col);
					  			table.transferFocus();
					  		}
						
							
					}
						
				}else {
					JOptionPane.showMessageDialog(_logic.getFrame(), "Debe ingresar la fecha de vencimiento con el formato dd-MM-yyyy");
					table.changeSelection(row, col, false, false);
					table.editCellAt(row, col);
					table.transferFocus();
				}
				
		}else {
			JOptionPane.showMessageDialog(_logic.getFrame(), "Debe ingresar la fecha de vencimiento");
			table.changeSelection(row, col, false, false);
			table.editCellAt(row, col);
			table.transferFocus();
		}
	}
	
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
	
	private boolean eval_venc(String fecha){
		boolean ok=false;
		if (fecha.compareTo("")!=0){
		Calendar cal=Calendar.getInstance();
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
		    	cal.setTime(date);
				Calendar now=Calendar.getInstance();
				cal.add(Calendar.DATE, 27);
				ok=now.before(cal);
			//	System.out.println(now.getTime()+" "+cal.getTime());
		    }
			//System.out.println("dif "+c+" "+diferencia);
		}else {
			//"Fecha Nula"
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
			recalculateMedios();
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
	
	public void eval_medio(JTextField tx,int row,int col){
		_Frame frame=(_Frame)this._logic.getFrame();
		_Data data=(_Data)this._logic.getData();
		String aux=tx.getText();
		if (aux.compareTo("")!=0){
			aux=data.checkcodeMedios(tx.getText());
		}
		
		if (aux.compareTo("")!=0) {
			tx.setText(tx.getText().toUpperCase());
			System.out.println("Medio Pago: "+tx.getText().toUpperCase());
			if (!cheque(tx.getText())){
				frame.getJTableMedios().setValueAt(aux, row,col+1);
				frame.getJTableMedios().setValueAt("", row,2);
				frame.getJTableMedios().setValueAt("", row,3);
				frame.getJTableMedios().setValueAt("", row,4);
				frame.getJTableMedios().setValueAt("", row,5);
				frame.getJTableMedios().setValueAt("", row,6);
				
				if (tx.getText().toUpperCase().compareTo("DOB")==0){
					System.out.println("Descuentos Obtenidos");
					Double rst=this.getRestanteMedios();
					frame.getJTableMedios().setValueAt(new Convertidor().getMoney(rst,2), row,7);	
				}else {
					if (tx.getText().toUpperCase().compareTo("EF")==0){
						Double rst=this.getRestanteMedios();
						frame.getJTableMedios().setValueAt(new Convertidor().getMoney(rst,2), row,7);
					}
					
				}
				
				frame.getJTableMedios().changeSelection(row, 7, false, false);
				frame.getJTableMedios().editCellAt(row, 7);
			}else {
				frame.getJTableMedios().setValueAt(aux, row,col+1);
				frame.getJTableMedios().changeSelection(row, col+2, false, false);
				frame.getJTableMedios().editCellAt(row, col+2);	
			}
			
			frame.getJTableMedios().transferFocus();	
		}else{
			
			Double rst=getRestanteMedios();
			
			if (rst>=0 & !existe("DOB")){
				frame.getJTableMedios().setValueAt(new Convertidor().getMoney(rst,2), row,7);
				frame.getJTableMedios().changeSelection(row, col, false, false);
				frame.getJTableMedios().editCellAt(row, col);
				frame.getJTableMedios().transferFocus();
			}else {
				//JOptionPane.showMessageDialog(this, "El codigo "+tx.getText()+" no existe. utilice * sino sabe!!");
			}
			
			tx.requestFocusInWindow();
		}
	}

	private boolean existe(String TC){
		boolean ok=false;
		_Frame frame=(_Frame)this._logic.getFrame();
		int i=0;
		while (i<frame.getJTableMedios().getRowCount() & !ok){
			try {
			ok=frame.getJTableMedios().getValueAt(i, 0).toString().toUpperCase().compareTo(TC.toUpperCase())==0;
			}catch(Exception e){
				
			}
			i++;
		}
		return ok;
	}
	
	public void eval_row_medios(JTextField tx,int row,int col){
		_Frame frame=(_Frame)this._logic.getFrame();
		String importe=tx.getText().replace(",", "");
		
		String medio="";
		String des="";
		String cod_banc="";
		String banc="";
		String serie="";
		String numero="";
		String venc="";
		try {
			medio=frame.getJTableMedios().getValueAt(row, 0).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try {
			des=frame.getJTableMedios().getValueAt(row, 1).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		try {
			cod_banc=frame.getJTableMedios().getValueAt(row, 2).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		try {
			banc=frame.getJTableMedios().getValueAt(row, 3).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try {
			serie=frame.getJTableMedios().getValueAt(row, 4).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		try {
			numero=frame.getJTableMedios().getValueAt(row, 5).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try {
			venc=frame.getJTableMedios().getValueAt(row, 6).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try {
			//importe=jTable.getValueAt(row, 7).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Double impx=0.0;
		Double comps=0.0;
		
		double imp=0;
		boolean error=false;
		try{
			imp=new Double(importe);
		}catch(Exception e){
			System.out.println(imp);
			error=true;
		}
		
		if (error){
			imp=0;
			tx.setText(""+imp);
		}
		if (medio.compareTo("")!=0){
			
				if (imp>0) {
					
					tx.setText(new Convertidor().getMoney(imp,2));
					try{
						impx=new Double(imp);
						comps=new Double(frame.get_txt_total_cpte().getText());
					}catch(Exception e){
					}
					
					if (frame.getJTableMedios().getRowCount()-1==row){
						DefaultTableModel model=(DefaultTableModel)frame.getJTableMedios().getModel();
						model.setRowCount(model.getRowCount()+1);
					}
						frame.getJTableMedios().changeSelection(row+1, 0, false, false);
						frame.getJTableMedios().editCellAt(row+1, 0);
						frame.getJTableMedios().transferFocus();
						
					/*	*/
				}else {
					if (imp<=0){
						JOptionPane.showMessageDialog(frame, "El Importe debe ser mayor a cero!");	
					}
				}
			}else {
				JOptionPane.showMessageDialog(frame, "Debe definir algun medio de pago");	
			}
		
		recalculateMedios();
		}
	
	public Double getRestanteMedios(){
		_Frame frame=(_Frame)this._logic.getFrame();
		Double restante=0.0;
		double total_cpte=0.0;
		double total_pagado=0.0;
		try {
			total_cpte=new Double(frame.get_txt_total_cpte().getText().replace(",", ""));
		}catch(Exception e){
			
		}
		if (total_cpte>0){
			System.out.println("Automatic Call total cpte "+total_cpte);
			for (int i=0;i<frame.getJTableMedios().getRowCount();i++){
				String medio="";
				try {
					medio=frame.getJTableMedios().getValueAt(i, 0).toString();
				}catch(Exception e){
					
				}
				Double importe=0.0;
				if (medio.compareTo("EF")==0 | medio.compareTo("CHT")==0 | medio.compareTo("DOB")==0){
					try {
						importe=new Double(frame.getJTableMedios().getValueAt(i, 7).toString().replace(",", ""));
						
					}catch(Exception e){
						
					}
					
					total_pagado=total_pagado+importe;
				}
			}
			_Logic logic=(_Logic) this._logic;
			double creditos=logic.getCreditos();
			restante=total_cpte-total_pagado-creditos;
		}
		return restante;
	}

	public void recalculateMedios(){
		
		_Frame frame=(_Frame)this._logic.getFrame();
		double sum=getPago(frame.getJTableMedios());
		Double subtot=0.0;
		try {
			subtot=new Double(frame.get_txt_total_cpte().getText().replace(",", ""));
		}catch(Exception e){
			
		}
		Double credits=0.0;
		try {
			credits=new Double(frame.get_txt_total_creditos().getText().replace(",", ""));
		}catch(Exception e){
			
		}
		double ant=sum+credits-subtot;
		ant=new Convertidor().roundDouble(ant, 2);
		System.out.println("pago:"+sum);
		System.out.println("creditos:"+credits);
		System.out.println("cptes:"+subtot);
		System.out.println("anticipo?:"+ant);
		System.out.println("saldo");
		double _saldo=subtot-credits-sum;
		frame.get_txt_saldo().setText(new Convertidor().getMoney(_saldo, 2));
		ant=new Convertidor().roundDouble(ant, 2);
			if (ant>=0.00){
				frame.get_txt_anticipo().setText(new Convertidor().getMoney(ant, 2));
				frame.get_btn_grabar().setEnabled(true);
				if (ant>0.0){
					frame.get_txt_leyenda().setText("El pago es Correcto. Se generara un anticipo por el excedente");
					frame.get_btn_grabar().setEnabled(true);
				}else {
					frame.get_txt_leyenda().setText("El pago es Correcto");
					frame.get_btn_grabar().setEnabled(true);
				}
				frame.get_txt_leyenda().setForeground(Color.green);
			}else {
				frame.get_txt_anticipo().setText("0.0");
				if (subtot>0){
					frame.get_btn_grabar().setEnabled(false);
					frame.get_txt_leyenda().setText("El Pago es menor al importe a Cancelar ");
					frame.get_txt_leyenda().setForeground(Color.red);	
				}else {
					
				}
				
			}	
			if (subtot<=0){
				if (ant>0){
					frame.get_btn_grabar().setEnabled(true);
					frame.get_txt_leyenda().setText("Esto Solo Generara un anticipo.");
					frame.get_txt_leyenda().setForeground(Color.green);	
				}else {
					frame.get_btn_grabar().setEnabled(false);
					frame.get_txt_leyenda().setText("Debe Especificar los comprobantes a cancelar o medios de pago");
					frame.get_txt_leyenda().setForeground(Color.red);
				}
				
			}else {
				
			}
			
		frame.get_txt_total_pago().setText(new Convertidor().getMoney(sum, 2));
	}
	
	
	public boolean existe_cheque(String idbanco,String serie,String numero){
		boolean existe=false;
		_Frame frame=(_Frame)this._logic.getFrame();
		if (frame.getJTableMedios()!=null){
			int i=0;
			while (i<frame.getJTableMedios().getRowCount() &!existe){
				String _idbanco="";
				String _serie="";
				String _numero="";
				try {
					_idbanco=frame.getJTableMedios().getValueAt(i, 2).toString();
					_serie=frame.getJTableMedios().getValueAt(i, 4).toString();
					_numero=frame.getJTableMedios().getValueAt(i, 5).toString();	
				}catch(Exception e){
					
				}
				existe=(_idbanco.compareTo(idbanco)==0 &
				        _serie.compareTo(serie)==0 &
				        _numero.compareTo(numero)==0);
				if (!existe)i++;
			}
		}
		return existe;
	}
	public void addCheque(int row,String idbanco,String nombre,String serie,String numero,String importe,String vencimiento){
		_Frame frame=(_Frame)this._logic.getFrame();
	
	if (!this.existe_cheque(idbanco, serie, numero)){
		System.out.println("Cheque>"+idbanco+" "+nombre+" "+serie+"-"+numero+" $"+importe);
		
		try {
			frame.getJTableMedios().getCellEditor().cancelCellEditing();
			//frame.getJTableMedios().getCellEditor().stopCellEditing();
		}catch(Exception e){
			//e.printStackTrace();
		}
		System.out.println("ROW QUE ONDA?"+row);
		String tipo="";
		String _imp="";
		boolean empty=true;
		try {
			tipo=frame.getJTableMedios().getValueAt(row, 0).toString();
			_imp=frame.getJTableMedios().getValueAt(row, 7).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		empty=(tipo.compareTo("")==0 | _imp.compareTo("")==0);
		DefaultTableModel model=(DefaultTableModel)frame.getJTableMedios().getModel();
		if (!empty){
			System.out.println("La fila "+row+" no esta vacia. agragando fila a la tabla");
			model.setRowCount(model.getRowCount()+1);
			
			row++;
		}else {
			System.out.println("La fila "+row+" esta disponible ");
			
		}
		frame.getJTableMedios().setValueAt("CHT", row, 0);
		frame.getJTableMedios().setValueAt("Caja Cheques 3ros a depositar", row, 1);
		frame.getJTableMedios().setValueAt(idbanco, row, 2);
		frame.getJTableMedios().setValueAt(nombre, row, 3);
		frame.getJTableMedios().setValueAt(serie, row, 4);
		frame.getJTableMedios().setValueAt(numero, row, 5);
		frame.getJTableMedios().setValueAt(vencimiento, row, 6);
		frame.getJTableMedios().setValueAt(importe, row, 7);
		
		if (row>=model.getRowCount()-1){
			model.setRowCount(model.getRowCount()+1);
			row++;
		}
		
	}
	}
	
	public boolean existe(String idbanco,String serie,String numero){
		boolean existe=false;
		_Frame frame=(_Frame)this._logic.getFrame();
		if (frame.getJTableMedios()!=null){
			int i=0;
			while (i<frame.getJTableMedios().getRowCount()){
				String _idbanco="";
				String _serie="";
				String _numero="";
				try {
					_idbanco=frame.getJTableMedios().getValueAt(i, 2).toString();
					_serie=frame.getJTableMedios().getValueAt(i, 4).toString();
					_numero=frame.getJTableMedios().getValueAt(i, 5).toString();	
				}catch(Exception e){
					
				}
				existe=(_idbanco.compareTo(idbanco)==0 &
				        _serie.compareTo(serie)==0 &
				        _numero.compareTo(numero)==0);
				if (!existe)i++;
			}
		}
		return existe;
	}
	
	public void create_table_medios(Object[][] results) {
		CustomTable table = new CustomTable();
		_Frame frame=(_Frame)this._logic.getFrame();
		_Constructor constructor=(_Constructor)_logic.getConstructor();
		Column col = new Column();
		col = new Column();
		col.setName("Medio");
		col.setWidth(60);
		col.setEditable(true);
		col.setClass(String.class);
		CellEditor pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_medios_medio);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
	    table.addColumn(col);

		
		col = new Column();
		col.setName("Descripcion");
		col.setWidth(180);
		col.setEditable(false);
		col.setEditable(false);
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("idBanco");
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_medios_banco);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setWidth(60);
		col.setEditable(true);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Banco");
		col.setWidth(110);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("serie");
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_medios_serie);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setWidth(44);
		col.setEditable(true);
		table.addColumn(col);
		
		col = new Column();
		col.setName("numero");
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_medios_numero);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setWidth(90);
		col.setEditable(true);
		table.addColumn(col);
		
		col = new Column();
		col.setName("vencimento");
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_medios_vencimiento);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setWidth(90);
		col.setEditable(true);
		table.addColumn(col);
		
		col = new Column();
		col.setName("importe");
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_medios_importe);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		col.setWidth(80);
		col.setEditable(true);
		
		table.addColumn(col);
		if (results!=null){
			if (results.length>0){
				results=this.procesar_medios_de_pago(results);	
			}
				
		}
		
		table.setData(results);
		table.addKeyListener(constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		
		table.build();
		
		table.fillData();
		JTable _table=table.getTable();
		_table.setName(_Interface._table_medios);
		frame.setJTableMedios(table.getTable());
	}

	private Object[][] procesar_medios_de_pago(Object[][] results){
		Convertidor C=new Convertidor();
		
		Object[][] temp=new Object[results.length][8];
		
		for (int i=0;i<results.length;i++){
			String cuenta=(String) results[i][0];
			String descripcion=(String) results[i][1];
			String importe=(String) results[i][2];
			String idbanco="";
			String serie="";
			String numero="";
			String vencimiento="";
			String cht_importe="";
			String banco="";
			
			if (cuenta.compareTo("111010001")==0){
				cuenta="EF";
			}
			if (cuenta.compareTo("111010002")==0){
				cuenta="CHT";
				idbanco=(String) results[i][3];
				serie=(String) results[i][4];
				numero=(String) results[i][5];
				vencimiento=(String) results[i][6];
				vencimiento=C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss", vencimiento);
				cht_importe=(String) results[i][7];
				banco=(String) results[i][8];
			}
			if (cuenta.compareTo("43104")==0){
				cuenta="DOB";
			}
			temp[i][0]=cuenta;
			temp[i][1]=descripcion;
			temp[i][2]=idbanco;
			temp[i][3]=banco;
			temp[i][4]=serie;
			temp[i][5]=numero;
			temp[i][6]=vencimiento;
			temp[i][7]=importe;
			
		}
		return temp;
	}
	
}
