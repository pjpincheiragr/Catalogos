package aplicacion.gestion.canje.logic.extensions;


import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import aplicacion.herramientas.java.*;
import aplicacion.gestion.canje.constructor.*;
import aplicacion.gestion.canje.logic.*;
import aplicacion.gestion.canje.interfaces.*;
import aplicacion.gestion.canje.gui.*;



import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.table.*;
import aplicacion.modelo.logic.Logic_Extension;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
 * Clase para el control de datos de la aplicacion
 */
public class Canje_Medios_de_Pago extends Logic_Extension {
	 
	/**
	 * Evalua que el codigo de banco sea correcto. si es asi, carga la descripcion.
	 * @param table
	 * @param tx
	 * @param row
	 * @param col
	 */
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
					
					JOptionPane.showMessageDialog(_logic.getFrame(),"Codigo de Banco Inexistente. Busqueda con F5");
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
	
	/**
	 * Metodo para insertar los valores de banco y descripcion, de manera sencilla.
	 * Se utiliza cuando se busca el banco. al obtener el codigo en el buscador (SortableSelector)
	 * y presionar enter se toman los datos y se cargan en la tabla que se esta editando;
	 *  que sera ingreso o egreso.
	 * @param table
	 * @param row
	 * @param idbanco
	 * @param nombre
	 */
	public void addBanco(JTable table,int row,String idbanco,String nombre){
		table.setValueAt(idbanco, row, 2);
		table.setValueAt(nombre, row, 3);
	}
	
	/**
	 * Evalua la serie del cheque. No se hace ninguna verificacion
	 * Solo se controla que se ingrese algo!
	 * @param table
	 * @param tx
	 * @param row
	 * @param col
	 */
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
	
	/**
	 * Retorna verdadero si el cheque esta disponible en la caja
	 * @param idbanco
	 * @param serie
	 * @param numero
	 * @return
	 */
	public boolean evaluar_disponibilidad_cheque(String idbanco,String serie, String numero){
		_Data data=(_Data)this._logic.getData();
		_Frame frame=(_Frame)this._logic.getFrame();
		
		String idcajas=frame.get_list_cajas().getSelectedItem().toString();
		int n=data.getCheque(idcajas,idbanco, serie, numero);
		return (n>0);
	}
	
	/**
	 * Evalua el numero de un cheque. para este momento se considera por la disposicion de las columnas
	 * que la persona ya cargo, idbanco y serie.
	 * la tupla idbanco+serie+numero debe ser unica en este punto. 
	 * No pueden haber 2 cheques iguales disponibles. 
	 * @param table
	 * @param tx
	 * @param row
	 * @param col
	 */
	public void eval_numero(JTable table,JTextField tx,int row,int col){
		String code=tx.getText();
		code=code.replace(" ", "");
		if (code.compareTo("")!=0){
			

			String idbanco=""+table.getValueAt(row, 2);
			String serie=""+table.getValueAt(row, 4);
			String numero=""+code;
			if (this.evaluar_disponibilidad_cheque(idbanco, serie, numero)){
				
					table.changeSelection(row, col+1, false, false);
					table.editCellAt(row, col+1);
					table.transferFocus();	
			}else{
				this._logic.error("Este cheque no esta disponible para egreso");
				JOptionPane.showMessageDialog(_logic.getFrame(), "Debe ingresar el numero del cheque");
				table.changeSelection(row, col, false, false);
				table.editCellAt(row, col);
				table.transferFocus();	
				
			}
		}else {
			JOptionPane.showMessageDialog(_logic.getFrame(), "Debe ingresar el numero del cheque");
			table.changeSelection(row, col, false, false);
			table.editCellAt(row, col);
			table.transferFocus();
		}
	}
	
	public void eval_numero2(JTable table,JTextField tx,int row,int col){
		String code=tx.getText();
		code=code.replace(" ", "");
		if (code.compareTo("")!=0){
			

			String idbanco=""+table.getValueAt(row, 2);
			String serie=""+table.getValueAt(row, 4);
			String numero=""+code;
			if (!this.evaluar_disponibilidad_cheque(idbanco, serie, numero)){
				
					table.changeSelection(row, col+1, false, false);
					table.editCellAt(row, col+1);
					table.transferFocus();	
			}else{
				this._logic.error("Este cheque ya esta disponible");
				table.changeSelection(row, col, false, false);
				table.editCellAt(row, col);
				table.transferFocus();	
				
			}
		}else {
			JOptionPane.showMessageDialog(_logic.getFrame(), "Debe ingresar el numero del cheque");
			table.changeSelection(row, col, false, false);
			table.editCellAt(row, col);
			table.transferFocus();
		}
	}
	
	/**
	 * Evalua el vencimiento ingresado al cargar un cheque
	 * @param table
	 * @param tx
	 * @param row
	 * @param col
	 */
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
	
	/**
	 * Transforma un String del tipo dd-MM-yyyy a la clase Date
	 * @param s
	 * @return
	 */
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
	
	/**
	 * Retorna verdadero si una fecha esta vencida.
	 * @param fecha
	 * @return
	 */
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
		    }
		}
		return ok;
	}
	
	/**
	 * Metodo para borrar un renglon de una tabla al presionar delete
	 * @param table
	 * @param row
	 */
	public void delete_renglonMedios(JTable table,int row){
		if (_logic.preguntar("Confirmar", "Borra este renglon "+(row+1)+"de la tabla?")){
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
		};
	}
	
	/**
	 * Obtiene la suma que totaliza una tabla, es decir la cantidad que suma entre cheques y efectivo
	 * @param table
	 * @return
	 */
	public double getPago(JTable table){
		double sum=0;
		double ant=0;
		aplicacion.gestion.canje.logic._Logic 
		logic=(aplicacion.gestion.canje.logic._Logic) this.getLogic();
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
				if (codigo.compareTo("US")==0){
					double tmp=logic.getCotizacion();
					while (tmp<=0){
						logic.pedirCotizacion();
						tmp=logic.getCotizacion();
					}
					p2=p2*tmp;	
					
				}
					sum=sum+p2;	
			}else {
				System.out.println(i+" "+error);
			}
		}
		
		return sum;
	}
	
	/**
	 * Carga los medios de pago del comprobante indicado.
	 * Es decir carga las tablas de ingreso y egreso...
	 * @param id
	 */
	public JTable load_medios_de_pago_egreso(String id){
		_Data data=(_Data)this._logic.getData();
		Object[][] results=data.load_medios_de_pago(id,"H");
		JTable table=null;
		if (results!=null){
			if (results.length>0){
				System.out.println("Creando Resultados de Medios de Pago de Canje");
				table=this.create_table_medios(results);
			}
		}
		return table;
	}
	
	public JTable load_medios_de_pago_ingreso(String id){
		_Data data=(_Data)this._logic.getData();
		Object[][] results=data.load_medios_de_pago(id,"D");
		JTable table=null;
		if (results!=null){
			if (results.length>0){
				System.out.println("Creando Resultados de Medios de Pago de Canje");
				table=this.create_table_medios(results);
			}
		}
		return table;
	}
	
	/**
	 * Metodo re estupido para verificar si el medio de pago es cht. Cheque
	 * @param idcomp
	 * @return
	 */
	private boolean cheque(String idcomp){
		boolean ok=false;
		ok=(idcomp.toUpperCase().compareTo("CHT")==0);
		return ok;
	}
	
	
	/**
	 * Metodo para evaluar el medio de pago ingresado en la celda correspondiente
	 * @param table
	 * @param tx
	 * @param row
	 * @param col
	 */
	public void eval_medio(JTable table,JTextField tx,int row,int col){
		_Frame frame=(_Frame)this._logic.getFrame();
		_Data data=(_Data)this._logic.getData();
		if (row<0) row=0;
		if (col<0) col=0;
		String aux=data.checkcodeMedios(tx.getText());
		if (aux.compareTo("")!=0) {
			tx.setText(tx.getText().toUpperCase());
			System.out.println("Medio Pago: "+tx.getText().toUpperCase());
			if (!cheque(tx.getText())){
					table.setValueAt(aux, row,1);
					table.setValueAt("", row,2);
					table.setValueAt("", row,3);
					table.setValueAt("", row,4);
					table.setValueAt("", row,5);
					table.setValueAt("", row,6);
					
					if (tx.getText().toUpperCase().compareTo("DO")==0){
						Double rst=0.0;
						table.setValueAt(new Convertidor().getMoney(rst,2), row,7);	
					}else {
						if (tx.getText().toUpperCase().compareTo("EF")==0){
							this.set_efectivo_disponible(table,row);
						}
						
						
					}
					
					table.changeSelection(row, 7, false, false);
					table.editCellAt(row, 7);	
				
				
			}else {
				try {
					table.getCellEditor().stopCellEditing();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				table.setValueAt(aux, row,col+1);
				
				table.changeSelection(row, col+2, false, false);
				table.editCellAt(row, col+2);	
			}
			
			table.transferFocus();	
		}else{
			Double rst=0.0;
			
			if (rst>0 & !existe(table,"DO")){
				tx.setText("DO");
				//jTable.setValueAt(aux, row,col);
				table.changeSelection(row, col, false, false);
				table.editCellAt(row, col);
				table.transferFocus();
			}else {
				//JOptionPane.showMessageDialog(this, "El codigo "+tx.getText()+" no existe. utilice * sino sabe!!");
			}
			
			
		}
	}
	
	/**
	 * Verifica si un medio de pago ya fue cargado en una tabla. aunque no perjudica en nada
	 * que cualquier medio de pago se repita. por ej cht.. por varios cheques siempre y 
	 * cuando sean distintos. EL efectivo se puede repetir. y generara un asiento con multiples 
	 * renglones con la cuenta de efectivo.
	 * 
	 * @param table
	 * @param TC
	 * @return
	 */
	private boolean existe(JTable table,String TC){
		boolean ok=false;
		_Frame frame=(_Frame)this._logic.getFrame();
		int i=0;
		while (i<table.getRowCount() & !ok){
			try {
			ok=frame.getJTableMedios().getValueAt(i, 0).toString().toUpperCase().compareTo(TC.toUpperCase())==0;
			}catch(Exception e){
				
			}
			i++;
		}
		return ok;
	}
	
	/**
	 * Verifica una fila. Para evitar grabar datos inconsistentes
	 * @param table
	 * @param tx
	 * @param row
	 * @param col
	 */
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
		if (medio.compareTo("")!=0){
			
				if (imp>0) {
					
					tx.setText(new Convertidor().getMoney(imp,2));
					if (table.getRowCount()-1==row){
						DefaultTableModel model=(DefaultTableModel)table.getModel();
						model.setRowCount(model.getRowCount()+1);
						
						try{
							impx=new Double(imp);
						
						}catch(Exception e){
						}
					}
					
						table.changeSelection(row+1, 0, false, false);
						table.editCellAt(row+1, 0);
						table.transferFocus();	
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
	
	/**
	 * Suma todos los medios de pago y calcula la suma
	 */
	public void recalculateMedios(){
		aplicacion.gestion.canje.logic._Logic 
		logic=(aplicacion.gestion.canje.logic._Logic) this.getLogic();
		_Frame frame=(_Frame)this._logic.getFrame();

		boolean us=logic.usaMoneda(frame.getJTableMedios(), "US");
		if (!us)us=logic.usaMoneda(frame.getJTableMedios2(), "US");
		if (us){
			double tmp=logic.getCotizacion();
			if (tmp<=0){
				logic.error("La cotizacion de la moneda debe ser Mayor a Cero!");
			}
		}
		double pag=getPago(frame.getJTableMedios());
		double pag2=getPago(frame.getJTableMedios2());
		double sum=pag2-pag;
		
		frame.get_txt_total_egreso().setText(new Convertidor().getMoney(pag,2));
		frame.get_txt_total_ingreso().setText(new Convertidor().getMoney(pag2,2));
		System.out.println("pago:"+sum);
		
			if (sum==0.00){
				frame.get_btn_grabar().setEnabled(true);
				frame.get_txt_leyenda().setText("El Canje es Correcto.");
				frame.get_btn_grabar().setEnabled(true);
				frame.get_txt_leyenda().setForeground(Color.green);
			}else {
				frame.get_btn_grabar().setEnabled(false);
				frame.get_txt_leyenda().setForeground(Color.red);
				if (sum>0){
					frame.get_txt_leyenda().setText("El importe de Ingreso es mayor al Egreso. Deben ser iguales");
				}else {
					
					frame.get_txt_leyenda().setText("El importe de Egreso es mayor al Ingreso. Deben ser iguales");
						
				}
				
				
			}	
			
		frame.get_txt_total_diferencia().setText(new Convertidor().getMoney(sum, 2));
	}
	
	/**
	 * Metodo para Verificar si el cheque esta disponible en la caja 
	 * @param table
	 * @param idbanco
	 * @param serie
	 * @param numero
	 * @return
	 */
	public boolean existe_cheque(JTable table,String idbanco,String serie,String numero){
		boolean existe=false;
		
		if (table!=null){
			int i=0;
			while (i<table.getRowCount() & !existe){
				String _idbanco="";
				String _serie="";
				String _numero="";
				try {
					_idbanco=table.getValueAt(i, 2).toString();
					_serie=table.getValueAt(i, 4).toString();
					_numero=table.getValueAt(i, 5).toString();	
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
	
	/**
	 * Metodo utilizado para precargar el valor de efectivo necesario para compensar
	 * en los ingresos o egresos de manera que balancee el asiento
	 * @param table
	 * @param row
	 */
	private void set_efectivo_disponible(JTable table,int row){
		_Data data=(_Data) this._logic.getData();
		_Frame frame=(_Frame)this._logic.getFrame();
		String idcaja=frame.get_list_cajas().getSelectedItem().toString();
		
		double _debe=0;
		double _haber=0;
		double sld=0.0;
		try {
			_debe=new Double(frame.get_txt_total_ingreso().getText().replaceAll(",", ""));
			_haber=new Double(frame.get_txt_total_egreso().getText().replaceAll(",", ""));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (table.getName()==_Interface._table_medios){
			//egreso
			sld=_debe-_haber;
			if (sld<0)sld=0;
		}else {
			sld=_haber-_debe;
			if (sld<0)sld=0;
		}
		
		
		table.setValueAt(new Convertidor().getMoney(sld,2), row,7);
		
	}
	
	/** 
	 * Metodo para agregar un cheque a traves del buscador SortableSelector.
	 * Se cargan los cheques seleccionados en la tabla editada, ingreso o egreso.
	 * @param table
	 * @param row
	 * @param idbanco
	 * @param nombre
	 * @param serie
	 * @param numero
	 * @param importe
	 * @param vencimiento
	 */
	public void addCheque(JTable table,int row,String idbanco,String nombre,String serie,String numero,String importe,String vencimiento){
	if (!this.existe_cheque(table,idbanco, serie, numero)){
		System.out.println("Cheque>"+idbanco+" "+nombre+" "+serie+"-"+numero+" $"+importe);
		
		
		
		try {
			table.getCellEditor().cancelCellEditing();
			//frame.getJTableMedios().getCellEditor().stopCellEditing();
		}catch(Exception e){
			//e.printStackTrace();
		}
		System.out.println("ROW QUE ONDA?"+row);
		String tipo="";
		String _imp="";
		boolean empty=true;
		try {
			tipo=table.getValueAt(row, 0).toString();
			_imp=table.getValueAt(row, 7).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		empty=(tipo.compareTo("")==0 | _imp.compareTo("")==0);
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		if (!empty){
			System.out.println("La fila "+row+" no esta vacia. agragando fila a la tabla");
			model.setRowCount(model.getRowCount()+1);
			
			row++;
		}else {
			System.out.println("La fila "+row+" esta disponible ");
			
		}
		table.setValueAt("CHT", row, 0);
		table.setValueAt("Caja Cheques 3ros a depositar", row, 1);
		table.setValueAt(idbanco, row, 2);
		table.setValueAt(nombre, row, 3);
		table.setValueAt(serie, row, 4);
		table.setValueAt(numero, row, 5);
		table.setValueAt(vencimiento, row, 6);
		table.setValueAt(importe, row, 7);
		if (row>=model.getRowCount()-1){
			model.setRowCount(model.getRowCount()+1);
			row++;
		}
		
	}
	}
	
	/**
	 * Verifica que el cheque no se haya cargado previamente en la tabla!
	 * @param table
	 * @param idbanco
	 * @param serie
	 * @param numero
	 * @return
	 */
	public boolean existe(JTable table,String idbanco,String serie,String numero){
		boolean existe=false;
		_Frame frame=(_Frame)this._logic.getFrame();
		if (table!=null){
			int i=0;
			while (i<table.getRowCount()){
				String _idbanco="";
				String _serie="";
				String _numero="";
				try {
					_idbanco=table.getValueAt(i, 2).toString();
					_serie=table.getValueAt(i, 4).toString();
					_numero=table.getValueAt(i, 5).toString();	
				}catch(Exception e){
					
				}
				existe=(_idbanco.compareTo(idbanco)==0 &
				        _serie.compareTo(serie)==0 &
				        _numero.compareTo(numero)==0);
				i++;
			}
		}
		return existe;
	}
	
	/**
	 * Crea la tabla de egresos
	 * @param results
	 * @return
	 */
	public JTable create_table_medios(Object[][] results) {
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
		col.setWidth(70);
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
		
		return _table;
	}

	/**
	 * Esto transforma los datos de la base y los pone como van en la interfaz grafica.
	 * Por ejemplo la cuenta 111010001 se traduce como EF
	 * Esto es para evitar que el usuario tipee un chorizo!
	 * @param results
	 * @return
	 */
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
			if (cuenta.compareTo("111010099")==0){
				cuenta="US";
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
