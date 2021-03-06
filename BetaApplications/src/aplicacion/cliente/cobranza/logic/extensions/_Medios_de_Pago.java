package aplicacion.cliente.cobranza.logic.extensions;


import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import aplicacion.cliente.cobranza.constructor._Constructor;
import aplicacion.cliente.cobranza.gui._Frame;
import aplicacion.cliente.cobranza.interfaces._Interface;
import aplicacion.modelo.events.*;
import aplicacion.cliente.cobranza.logic._Data;
import aplicacion.cliente.cobranza.logic._Logic;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.modelo.logic.Logic_Extension;
import beta.tools.table.Column;
import beta.tools.table.CustomTable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import stock1.Convertidor;

/**
 * Clase para el control de datos de la aplicacion
 */
public class _Medios_de_Pago extends Logic_Extension {
	 
	
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
				this.create_table_medios(results,false);
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
		
		String aux=data.checkcodeMedios(tx.getText());
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
				
				if (tx.getText().toUpperCase().compareTo("DO")==0){
					Double rst=this.getRestanteMedios();
					frame.getJTableMedios().setValueAt(new Convertidor().getMoney(rst,2), row,7);	
				}else {
					if (tx.getText().toUpperCase().compareTo("EF")==0|tx.getText().toUpperCase().compareTo("EF")==0){
						Double rst=this.getRestanteMedios();
						frame.getJTableMedios().setValueAt(new Convertidor().getMoney(rst,2), row,7);
					}else{
						tx.setText("");
						_logic.error("Este Medio de Pago no esta disponible");
						
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
			
			if (rst>0 & !existe("DO")){
				tx.setText("DO");
				//jTable.setValueAt(aux, row,col);
				frame.getJTableMedios().changeSelection(row, col, false, false);
				frame.getJTableMedios().editCellAt(row, col);
				frame.getJTableMedios().transferFocus();
			}else {
				
			}
			
			
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
	
	public void eval_row_medios(JTable table,JTextField tx,int row,int col){
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
			medio=table.getValueAt(row, 0).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try {
			des=table.getValueAt(row, 1).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		try {
			cod_banc=table.getValueAt(row, 2).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		try {
			banc=table.getValueAt(row, 3).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try {
			serie=table.getValueAt(row, 4).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		try {
			numero=table.getValueAt(row, 5).toString();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try {
			venc=table.getValueAt(row, 6).toString();
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
		if (!(medio.compareTo("EF")==0|medio.compareTo("DO")==0|medio.compareTo("CHT")==0)){
			medio="";
		}
		if (medio.compareTo("")!=0){
			
				if (imp>=0) {
					
					tx.setText(new Convertidor().getMoney(imp,2));
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					if (row == model.getRowCount() - 1) {
						model.setRowCount(model.getRowCount() + 1);

					}
					
					table.changeSelection(row + 1, 0, false, false);
					table.editCellAt(row + 1, 0);
					table.transferFocus();
					
					/*	*/
				}else {
					if (imp<0){
						JOptionPane.showMessageDialog(frame, "El Importe debe ser mayor igual a cero!");	
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
				if (medio.compareTo("EF")==0 | medio.compareTo("CHT")==0 ){
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

	public boolean recalculateMedios(){
		boolean ok=false;
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
		
		double _saldo=0;
		if (credits-subtot<0){
			_saldo=subtot-credits-sum;	
		}else{
			_saldo=-subtot+credits-sum;
		}
		ok=false;
		frame.get_txt_saldo().setText(new Convertidor().getMoney(_saldo, 2));
			if (ant>=0){
				frame.get_txt_anticipo().setText(new Convertidor().getMoney(ant, 2));
				frame.get_btn_grabar().setEnabled(true);
				ok=true;
				if (ant>0){
					frame.get_txt_leyenda().setText("El pago es Correcto. Se generara un anticipo por el excedente");
					frame.get_btn_grabar().setEnabled(true);
					ok=true;
				}else {
					frame.get_txt_leyenda().setText("El pago es Correcto");
					frame.get_btn_grabar().setEnabled(true);
					ok=true;
				}
				frame.get_txt_leyenda().setForeground(Color.green);
			}else {
				if (subtot>0){
					ok=false;
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
					ok=true;
				}else {
					if (-subtot+credits-sum<=0){
						frame.get_btn_grabar().setEnabled(true);
						ok=true;
						frame.get_txt_leyenda().setText("Pago Correcto");
						frame.get_txt_leyenda().setForeground(Color.green);
					}else{
						ok=false;
						frame.get_btn_grabar().setEnabled(false);
						frame.get_txt_leyenda().setText("Debe Especificar los comprobantes a cancelar o medios de pago");
						frame.get_txt_leyenda().setForeground(Color.red);	
					}
					
				}
				
			}else {
				
			}
		
		
		frame.get_txt_total_pago().setText(new Convertidor().getMoney(sum, 2));
		return ok;
	}
	
	
	public void create_table_medios(Object[][] results,boolean focus) {
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
		col.setWidth(70);
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
		table.setName(_Interface._table_medios);
		table.addKeyListener(this.getLogic().getConstructor().getKeyListener());
		table.addMouseListener(this.getLogic().getConstructor().getMouseListener());
		Font fuente=new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		
		table.build();
		
		table.fillData();
		
		frame.setJTableMedios(table.getTable());
		if (focus){
			frame.getJTableMedios().changeSelection(0,0,false,false);
			frame.getJTableMedios().editCellAt(0,0);
			frame.getJTableMedios().transferFocus();
		}
	}

	private Object[][] procesar_medios_de_pago(Object[][] results){
		stock1.Convertidor C=new stock1.Convertidor();
		
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
			if (cuenta.compareTo("42101")==0){
				cuenta="DO";
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
