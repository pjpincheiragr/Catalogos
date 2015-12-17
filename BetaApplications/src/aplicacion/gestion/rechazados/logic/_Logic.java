package aplicacion.gestion.rechazados.logic;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import aplicacion.modelo.interfaces.*;


import aplicacion.gestion.rechazados.logic._Data;
import aplicacion.gestion.rechazados.constructor._Constructor;
import aplicacion.gestion.rechazados.gui._Frame;
import aplicacion.gestion.rechazados.interfaces._Interface;
import aplicacion.gestion.rechazados.logic.extensions.Rechazados_Medios_de_Pago;
import aplicacion.gestion.rechazados.logic.extensions.Rechazodos_Asiento;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.*;

public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	
	/*
	 * variables para la cobranza directa de caja. online 
	 */
	private String cpte_fecha="";
	private String cpte_tc="RCH";
	private String cpte_idc="";
	private String cpte_importe="";
	
	public void AjusteEfectivo(double importe){
		frame.getJTableMedios().setValueAt("EF", 0, 0);
		frame.getJTableMedios().setValueAt("Caja Efectivo", 0, 1);
		frame.getJTableMedios().setValueAt(importe, 0, 7);
	}
	public _Logic(){
		Rechazados_Medios_de_Pago control=new Rechazados_Medios_de_Pago();
		Rechazodos_Asiento asiento=new Rechazodos_Asiento();
		this.addExtension(control);
		this.addExtension(asiento);
		System.out.println("Extension=?"+this.getExtension("Rechazados_Control"));
		System.out.println("Extension=?"+this.getExtension("Rechazados_Asiento"));
	}
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	
	/*
	 * Para obtener el proximo numero de Pago disponible
	 */
	public void obtener_proximo_cpte(){
		_Data data=(_Data) _data;
		String cb=data.getProximoPGCorrecto();
		//Pago_frame _frame=(Pago_frame) this._frame;
		frame.get_txt_idPago().setText(cb);
	}
	
	
	
	
	public void clean(){
		
		
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_total_pago().setText("0.00");
		frame.get_txt_clientedescripcion().setText("");
		frame.get_txt_fecha().setText("");
		frame.get_txt_leyenda().setText("");
		frame.get_txt_clientedescripcion().setText("");
		frame.setJTableMedios(null);
		frame.get_txt_estado().setText("");
		frame.get_txt_concepto().setText("");
		frame.get_txt_estado().setForeground(Color.white);
		frame.get_txt_idPago().setEditable(true);
		frame.get_btn_buscar_pago().setEnabled(true);
		frame.get_txt_idproveedor().setEditable(true);
		frame.get_btn_buscar_proveedor().setEnabled(true);
		frame.get_list_cajas().setEnabled(true);
		frame.get_txt_caja_detalle().setText("");
		this.obtener_proximo_cpte();
		cargar_cajas();
			
	}
	
	private boolean eval_rows(){
		System.out.println("Eval rows");
		boolean ok=true;
		int i=0;
		while (i<frame.getJTableMedios().getRowCount() & ok){
			String tipo="";
			String _imp="";
			try {
				tipo=frame.getJTableMedios().getValueAt(i, 0).toString();
				_imp=frame.getJTableMedios().getValueAt(i, 7).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			if (tipo.compareTo("")!=0 & _imp.compareTo("")!=0){
				
				ok=this.eval_row(i);	
			}
			
		i++;
		}
		return ok;
	}

	private boolean eval_row(int r){
		boolean ok=false;
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		String tipo="";
		String _imp="";
		try {
			tipo=frame.getJTableMedios().getValueAt(r, 0).toString();
			_imp=frame.getJTableMedios().getValueAt(r, 7).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		double imp=0;
		if (_imp.compareTo("")!=0){
			try {
				imp=new Double(_imp.replace(",", ""));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		if (tipo.compareTo("EF")==0){
			if (imp>0){
				ok=true;
			}else {
				this.error("El importe no puede ser nulo");
			}
		}
		if (tipo.compareTo("US")==0){
			if (imp>0){
				ok=true;
			}else {
				this.error("El importe no puede ser nulo");
			}
		}
		if (tipo.compareTo("CHT")==0){
			if (imp>0){
				String idbanco=""+frame.getJTableMedios().getValueAt(r, 2);
				String serie=""+frame.getJTableMedios().getValueAt(r, 4);
				String numero=""+frame.getJTableMedios().getValueAt(r, 5);
				
				ok=!control.evaluar_disponibilidad_cheque(idbanco, serie, numero);
				String idcajas=frame.get_list_cajas().getSelectedItem().toString().replaceAll(" ", "");
				if (!ok){
					this.error("El Cheque "+idbanco+" "+serie+"-"+numero+" ya se encuentra disponible en Caja "+idcajas);
				}
			}else {
				this.error("El importe no puede ser nulo");
				
			}
		}
		return ok;
	}
	
	public void anular(){
		
		
		String id=frame.get_txt_idPago().getText();
		if (id.compareTo("")!=0){
			_Data _data=(_Data) this._data;
			if (_data.anulada(id)){
				JOptionPane.showMessageDialog(_frame, "El Ingreso "+id+" ya fue anulado");
			}else {
				
				if (_data.exist_pago(id)){
					
						int n = JOptionPane.showConfirmDialog(
				  			    _frame,
				  			    "Elimina Ingreso "+id+"?",
				  			    "Eliminar",
				  			    JOptionPane.YES_NO_OPTION);
				  		if ( n == JOptionPane.YES_OPTION) {
				  			boolean error=_data.anular_PG("IGR",id);
				  			if (!error){
				  				JOptionPane.showMessageDialog(_frame, "Se anulo Correctamente el Ingreso");
				  				this.clean();
				  			}else {
				  				JOptionPane.showMessageDialog(_frame, "Error anulando el Ingreso");
				  			}
				  		}	
					
						
				}else {
					JOptionPane.showMessageDialog(_frame, "El Ingreso "+id+" no existe");
				}
				
			}	
		}
		
		
	}
	
	
	
	public void cancelar(){
		int operacion = JOptionPane.showConfirmDialog(
			    this._frame,
			    "Cancela Operacion?",
			    "Confirmar",
			    JOptionPane.YES_NO_OPTION);
		if ( operacion == JOptionPane.YES_OPTION) {
			clean();
			frame.get_txt_idPago().setEditable(true);
			obtener_proximo_cpte();
		}
	}
	
	
	public void getToday(){
		_Frame _frame=(_Frame) this._frame;
		_frame.get_txt_fecha().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy")
				);	
	}
	public void _pago_evaluar_idpago(){
		this._pago_evaluar_idpago(frame.get_txt_idPago());
	}
	public void _pago_evaluar_idpago(JTextField tx){
		String idx=tx.getText();
		_Data _data=(_Data) this._data;
		if (_data.exist_pago(idx)){
			cargarPago(idx);
			///aca mensaje de edita o dame un numero nuevo.
		}else {
			cargar_cajas();
			if (frame.get_txt_fecha().getText().compareTo("")!=0){
				boolean aux=this.checkFecha(frame.get_txt_fecha().getText());
				if (aux){
					this.setInitialDate();		
				}
			}else {
				this.setInitialDate();
			}
			
			
			
			frame.get_btn_buscar_pago().setEnabled(false);
			frame.get_btn_buscar_proveedor().setEnabled(true);
			frame.get_txt_idPago().setEditable(false);
			frame.get_txt_idproveedor().setEditable(true);
			frame.get_btn_grabar().setEnabled(true);
			frame.get_txt_fecha().requestFocusInWindow();
		}
	}
	
	
	public void cargarPago(String id){
		frame.get_txt_idPago().setText(id);
		frame.get_txt_idPago().setEditable(false);
		frame.get_btn_buscar_pago().setEnabled(false);
		this.cargar_cajas();
		if (data.exist_pago(id)){
			int n = JOptionPane.showConfirmDialog(
	  			    frame,
	  			    "Modifica Pago "+id+"?",
	  			    "Modificar",
	  			    JOptionPane.YES_NO_OPTION);
	  		if ( n == JOptionPane.YES_OPTION) {
	  				load_Pago(id);
	  		}else {
	  			clean();	
	  		}
			
			
		}else {
			System.out.println("Pago nueva");
		}
	}
	
	public void load_encabezado(String id){
		Convertidor C=new Convertidor();
		Object[][] results=data.load_encabezado(id);
		if (results!=null){
			if (results.length>0){
				String importe=(String) results[0][0];
				String cuenta=(String) results[0][1];
				String desc=(String) results[0][2];
				String concepto=(String) results[0][3];
				String fecha=(String) results[0][4];
				String idcajas=(String) results[0][5];
				fecha=C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd hh:mm:ss", fecha);
				frame.get_txt_idproveedor().setText(cuenta);
				frame.get_txt_clientedescripcion().setText(desc);
				frame.get_txt_fecha().setText(fecha);
				frame.get_txt_concepto().setText(concepto);
				for (int i=0;i<frame.get_list_cajas().getItemCount();i++){
					if (frame.get_list_cajas().getItemAt(i).toString().compareTo(idcajas)==0){
						frame.get_list_cajas().setSelectedIndex(i);
					}
				}
				double imp=0.0;
				double ant=0.0;
				try {
					imp=new Double(importe);
				}catch(Exception e){
					
				}
				
				frame.get_txt_total_pago().setText(C.getMoney(imp,2));
				
			}
		}
		frame.get_txt_idproveedor().setEditable(false);
		
	}
	
	
	
	
		
	public _Data getData(){
		return this.data;
	}
	
	/**
	 * 
	 * ========================================================================
	 * OPERACIONES UTILIZADAS PARA EL CONTROL Y MANEJO DE MEDIOS DE PAGO
	 */
	public void _medios_evaluar_banco_cheque(JTextField tx,int row,int col){
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_cod_banc(frame.getJTableMedios(), tx, row, col);
	}
	
	public void _medios_evaluar_serie_cheque(JTextField tx,int row,int col){
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_serie(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_numero_cheque(JTextField tx,int row,int col){
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_numero(frame.getJTableMedios(), tx, row, col);
	}

	public void _medios_evaluar_vencimiento(JTextField tx,int row,int col){
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_vencimiento(frame.getJTableMedios(), tx, row, col);
	}


	public void _medios_borrar_renglon(int row){
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.delete_renglonMedios(frame.getJTableMedios(), row);
	}
	
	public void _medios_recalcular(){
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.recalculateMedios();
	}
	
	public void _medios_crear_tabla(Object[][] results) {
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.create_table_medios(results);
	}
	
	public void _medios_cargar_medios_de_pago(String id){
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.load_medios_de_pago(id);
	}
	
	public void _medios_evaluar_medio(JTextField tx,int row,int col){
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_medio(tx, row, col);
		
	}
	
	public Double _medios_obtener_restante(){
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		double restante=0.0;
		return restante;
	}

	public void _medios_evaluar_importe(JTextField tx,int row,int col){
		Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)this.getExtension(_Interface._extension_medios_de_pago);
		control.eval_row_medios(tx, row, col);
	}

	
	public void load_Pago(String id){
		frame.get_txt_idPago().setText(id);
		frame.get_txt_idPago().setEditable(false);
		this._medios_cargar_medios_de_pago(id);
		if (data.pago_anulada(id)){
			frame.get_txt_estado().setText("ANULADA");
			frame.get_txt_estado().setForeground(Color.red);
			frame.get_btn_anular().setEnabled(false);
		}else {
			frame.get_txt_estado().setText("OK");
			frame.get_txt_estado().setForeground(Color.white);
			frame.get_btn_anular().setEnabled(true);
		}
		
		this.load_encabezado(id);
		this._medios_recalcular();
		
		frame.getJTableMedios().setEnabled(false);
		frame.get_btn_grabar().setEnabled(false);
		
	}
	public void create_tabla(){
		Object[][] empty_medios=new Object[][]{{"","","","","","","","",""}};
		this._medios_crear_tabla(empty_medios);
	}
	
public void evaluar_concepto(JTextField tx){
String aux="";
aux=tx.getText();
boolean ok=aux.compareTo("")!=0;
if (!ok){
	ok=this.preguntar("confirmar", "Confirma concepto en blanco?");	
}
if (ok){
	int rows=0;
	try {
		rows=frame.getJTableMedios().getRowCount();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (rows<=0){
		create_tabla();
	}
	frame.getJTableMedios().editCellAt(0,0);
	frame.getJTableMedios().transferFocus();
}
	
}

public void create_table_origen(Object[][] results) {
	CustomTable table = new CustomTable();
	
	
	Column col = new Column();
	col = new Column();
	col.setName("Medio");
	col.setWidth(60);
	col.setEditable(true);
	col.setClass(String.class);
	table.addColumn(col);

	
	col = new Column();
	col.setName("Descripcion");
	col.setWidth(180);
	col.setEditable(false);
	col.setEditable(false);
	table.addColumn(col);
	
	
	col = new Column();
	col.setName("idBanco");
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
	col.setWidth(44);
	col.setEditable(true);
	table.addColumn(col);
	
	col = new Column();
	col.setName("numero");
	col.setWidth(90);
	col.setEditable(true);
	table.addColumn(col);
	
	col = new Column();
	col.setName("vencimento");
	col.setWidth(90);
	col.setEditable(true);
	table.addColumn(col);
	
	col = new Column();
	col.setName("importe");
	col.setWidth(70);
	col.setEditable(true);
	
	table.addColumn(col);
	
	table.setData(results);
	
	Font fuente=new Font("Arial", Font.PLAIN, 9);
	//table.setHeaderFont(fuente);
	
	table.build();
	
	table.fillData();
	JTable _table=table.getTable();
	_table.setName(_Interface._table_medios);
	frame.setJTableMedios(table.getTable());
}

public void _evaluar_codigo_cuenta(JTextField tx){
	String aux=tx.getText();
	if (aux.compareTo("")!=0){
			int n=0;
			try {
				n=new Integer(aux);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (n>0){
				this.load_All(tx);	
			}else {
				this.buscarCuenta(tx);
			}
				
	}else {
		JOptionPane.showMessageDialog(frame, "Ingrese Codigo de Cuenta. utilice F5 para buscar");
	}
}

public void load_All(JTextField tx){
	String aux=tx.getText();
	if (aux.compareTo("")!=0){
		if (Character.isLetter(aux.charAt(0))){
			//goVisualGuessCliente(tx);
		}else {
			loadAll(aux);	
		}
		
	}
	
}

public void loadAll(String idcliente){
	
	Object[][] results=data.getClientInformation(idcliente);
	if (results!=null){
		if (results.length>0){
			
			String desc=results[0][1].toString();
			frame.get_txt_clientedescripcion().setText(desc);
			//_Pago_cargar_creditos();
			Object[][] empty_medios=new Object[][]{{"","","","","","","","",""}};
			this._medios_crear_tabla(empty_medios);
			this.frame.get_list_cajas().requestFocusInWindow();
		}else {
			JOptionPane.showMessageDialog(frame, "El Codigo de Cuenta es Inexistente o No es Imputable. Utilice F5 para Buscar");
			frame.get_txt_idproveedor().requestFocusInWindow();
		}
	}
}
public void cargar_cajas(){
	frame.get_list_cajas().removeAllItems();
	frame.get_list_cajas().removeActionListener(null);
	_Data data=(_Data) _data;
	
	Object[][] results=data.get_cajas();
	for (int i=0;i<results.length;i++){
		
		try {
			frame.get_list_cajas().addItem(results[i][0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

private aplicacion.herramientas.java.buscadores.Cuenta bCuenta=null;
public void BuscarCuenta(JTextField ext) {
	 if (bCuenta==null){
		 bCuenta=new aplicacion.herramientas.java.buscadores.Cuenta(this.getConstructor());
	 }
 
 bCuenta.Buscar(ext);
}

private aplicacion.herramientas.java.visualizadores.Cuenta vCuenta=null;
public void buscarCuenta(JTextField tx) {
	if (vCuenta!=null){
		vCuenta.close();
	}
	vCuenta=new aplicacion.herramientas.java.visualizadores.Cuenta(this.getConstructor());
	int n = vCuenta.Buscar(tx);
	if (n == 0) {
			aviso("no hay Cuentas con ese codigo");
	}
}
public void BuscarBanco(int finalrow,JTextField ext){
	final int ux=finalrow;
	final Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)getExtension(_Interface._extension_medios_de_pago);
	aplicacion.herramientas.java.sortableselector.constructor._Constructor 
	CC=new aplicacion.herramientas.java.sortableselector.constructor._Constructor(){
		@Override
		protected void initialize_logic(){
			_logic=new 
			aplicacion.herramientas.java.sortableselector.logic._Logic(){
				@Override
				public void Close(JTable table,int row){
					System.out.println("LOGIC>double click "+row+" on sortable selector" );
					
					String idbanco=table.getValueAt(row, 0).toString();
					String nombre=table.getValueAt(row, 1).toString();
					control.addBanco(ux,idbanco, nombre);
					super.Close(table, row);
				}
			};
		}
	};
	CC.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
	CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
	CC.build(null);
	
	CC.init();
	aplicacion.herramientas.java.sortableselector.logic._Logic 
	logic=(aplicacion.herramientas.java.sortableselector.logic._Logic)CC.getLogic();
	

	columna c = new columna();
	Filtro f = new Filtro();
	c = new columna();
	c.setNombre("idbanco");
	c.setAlias("idbanco");
	c.setColumnField(ext);
	c.setWidth(80);
	c.setMaster(true);
	logic.addColumn(c);

	c = new columna();
	c.setNombre("descripcion");
	c.setAlias("descripcion");
	c.setWidth(120);
	c.setMaster(false);
	logic.addColumn(c);



	logic.addFromTable(" v_ta_bancos");
	f = new Filtro();
	f.setNombre("idbanco");
	f.setAlias("idbanco");
	f.setWidth(120);
	logic.addFilter(f);
	
	f = new Filtro();
	f.setNombre("descripcion");
	f.setAlias("descripcion");
	f.setWidth(120);
	logic.addFilter(f);
	logic.addOrder("idbanco");
	logic.init();
}

private aplicacion.herramientas.java.buscadores.Cheque bCheque=null; 
public void BuscarCheque(int finalrow,JTextField ext) {
	final int ux=finalrow;
	final Rechazados_Medios_de_Pago control=(Rechazados_Medios_de_Pago)getExtension(_Interface._extension_medios_de_pago);
	if (bCheque==null){ 
		bCheque=new aplicacion.herramientas.java.buscadores.Cheque(this.getConstructor()){
			 
			 public void initializeConstructor(){
				 C = new aplicacion.herramientas.java.sortableselector.constructor._Constructor() {
						@Override
						protected void initialize_logic() {
							_logic = new aplicacion.herramientas.java.sortableselector.logic._Logic() {
								@Override
								public void Close(JTable table, int row) {
									System.out.println("LOGIC>double click "+row+" on sortable selector" );
									int[] select_idx=table.getSelectedRows();
									for (int i=0;i<select_idx.length;i++){
										String idbanco=table.getValueAt(select_idx[i], 3).toString();
										String nombre=table.getValueAt(select_idx[i], 4).toString();
										String serie=table.getValueAt(select_idx[i], 5).toString();
										String numero=table.getValueAt(select_idx[i], 6).toString();
										String vencimiento=table.getValueAt(select_idx[i], 7).toString();
										String importe=table.getValueAt(select_idx[i], 8).toString();
										control.addCheque(ux, idbanco, nombre, serie, numero, importe, vencimiento);
									}
									
									super.Close(table, row);
									frame.getJTableMedios().requestFocusInWindow();
									frame.getJTableMedios().changeSelection(ux+select_idx.length-1, 7,false,false);
									frame.getJTableMedios().editCellAt(ux+select_idx.length-1, 7);
								}
							};
						}
					};
			 }
			
		 };
		 
		} 
		
	  bCheque.setIdcaja(frame.get_list_cajas().getSelectedItem().toString());
	 bCheque.setCuenta(frame.get_txt_idproveedor());
	 bCheque.Buscar(ext);
	
	
}

public void BuscarCheque(JTextField ext) { 
  
 }

public void BuscarPago() {
	this.BuscarPago(frame.get_txt_idPago());
}

public void BuscarCuenta(){
	this.BuscarCuenta(frame.get_txt_idproveedor());
}

public void setInitialDate(){
	String aux=data.getSystemDate();
	frame.get_txt_fecha().setText(aux);
}

public void BuscarPago(JTextField ext) {
	aplicacion.herramientas.java.sortableselector.constructor._Constructor 
	CC=new aplicacion.herramientas.java.sortableselector.constructor._Constructor()
	{
		@Override
		protected void initialize_logic(){
			_logic=new 
			aplicacion.herramientas.java.sortableselector.logic._Logic(){
				@Override
				public void Close(JTable table,int row){
					
					super.Close(table, row);
					frame.get_txt_idPago().requestFocusInWindow();
				}
			};
		}
	};
	CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
	CC.build(null);
	
	CC.init();
	aplicacion.herramientas.java.sortableselector.logic._Logic 
	logic=(aplicacion.herramientas.java.sortableselector.logic._Logic)CC.getLogic();
	
	columna c = new columna();
	Filtro f = new Filtro();
	c = new columna();
	c.setNombre("CONVERT(VARCHAR(10), a.fecha, 105)");
	c.setAlias("fecha");
	c.setWidth(80);
	c.setMaster(false);
	
	logic.addColumn(c);

	c = new columna();
	c.setNombre("a.tc");
	c.setAlias("TC");
	c.setWidth(50);
	c.setMaster(false);
	logic.addColumn(c);

	c = new columna();
	c.setNombre("a.idcomprobante");
	c.setAlias("idcomprobante");
	c.setColumnField(ext);
	c.setWidth(80);
	c.setMaster(false);
	logic.addColumn(c);
	
	c = new columna();
	c.setNombre("a.importe");
	c.setAlias("importe");
	c.setWidth(60);
	c.setMaster(false);
	logic.addColumn(c);
	
	c = new columna();
	c.setNombre("a.cuenta");
	c.setAlias("cuenta");
	c.setWidth(60);
	c.setMaster(true);
	logic.addColumn(c);
	
	c = new columna();
	c.setNombre("c.descripcion");
	c.setAlias("descripcion");
	c.setWidth(160);
	c.setMaster(false);
	logic.addColumn(c);

	c = new columna();
	c.setNombre("(case when a.anulado = 1 then  'ANULADO' else 'OK' end)");
	c.setAlias("Estado");
	
	c.setWidth(80);
	c.setMaster(false);
	logic.addColumn(c);
	
	logic.addFromTable("b_mv_asientos a left outer join ma_cuentas c on a.cuenta=c.codigo ");
	
	f = new Filtro();
	f.setNombre("a.fecha");
	f.setAlias("fecha");
	f.setWidth(60);
	logic.addFilter(f);
	f = new Filtro();
	f.setNombre("a.idcomprobante");
	f.setAlias("idcomprobante");
	f.setWidth(120);
	logic.addFilter(f);
	f = new Filtro();
	f.setNombre("c.codigo");
	f.setAlias("cuenta");
	f.setWidth(70);
	logic.addFilter(f);
	f = new Filtro();
	f.setNombre("c.descripcion");
	f.setAlias("descripcion");
	f.setWidth(150);
	logic.addFilter(f);
	logic.addRestriction(" a.tc like '"+this.cpte_tc+"' and a.debe_haber like 'd' ");
	logic.addOrder("a.fecha desc");
	logic.init();
}

public void load_calendar(){
	this.BuscarFecha(frame.get_txt_fecha());
}

private aplicacion.herramientas.java.buscadores.Fecha bFecha=null;
public void BuscarFecha(JTextField tx){
	if (bFecha==null){
		bFecha=new aplicacion.herramientas.java.buscadores.Fecha(this.getConstructor());
	}
	
	
	bFecha.Buscar(tx);
}

private boolean checkFecha(String fecha){
	boolean error=false;
	DateFormat formatter;
    try {
    	formatter = new SimpleDateFormat("dd-MM-yyyy");
    	formatter.parse(fecha);
    	
    }catch (Exception e){
    	error=true;
    	JOptionPane.showMessageDialog(frame,
    			e.getMessage(),
    		    "Error en Fecha",
    		    JOptionPane.ERROR_MESSAGE);
    }
    return error;
}

public void evaluate_caja(JComboBox cb){
	String desc=cb.getSelectedItem().toString();
	desc=data.getDetalleCaja(desc);
	if (desc.compareTo("")!=0){
		frame.get_txt_caja_detalle().setText(desc);
	}
	
}

public void evaluate_caja(){
	String desc=frame.get_list_cajas().getSelectedItem().toString();
	desc=data.getDetalleCaja(desc);
	if (desc.compareTo("")!=0){
		frame.get_txt_caja_detalle().setText(desc);
	}
	frame.get_txt_concepto().requestFocusInWindow();
	frame.get_txt_idproveedor().setEditable(false);
	frame.get_btn_buscar_proveedor().setEnabled(false);
	frame.get_list_cajas().setEnabled(false);
}

public void evaluarFecha(JTextField txt){
	String s=txt.getText();
	DateFormat formatter;
    Date date=null;
    boolean error=false;
    String s1="";
    try {
    	formatter = new SimpleDateFormat("dd-MM-yyyy");
    	date = (Date)formatter.parse(s);
    	s1 = formatter.format(date);
    }catch (Exception e){
    	error=true;
    	JOptionPane.showMessageDialog(frame,
    			e.getMessage(),
    		    "Error en Fecha",
    		    JOptionPane.ERROR_MESSAGE);
    }
    if (!error){
    	txt.setText(s1);
    	frame.get_txt_idproveedor().requestFocusInWindow();
    	
    }else {
    	txt.requestFocusInWindow();
    }
}


public void pagoAProveedor(String idproveedor){
	this.obtener_proximo_cpte();
	this._pago_evaluar_idpago(frame.get_txt_idPago());
	frame.get_txt_idproveedor().setText(idproveedor);
	_evaluar_codigo_cuenta(frame.get_txt_idproveedor());
	loadAll(idproveedor);
}

public void GrabarPago(){
	Rechazodos_Asiento control=(Rechazodos_Asiento)this.getExtension(_Interface._extension_asiento);
	if (!this.checkFecha(frame.get_txt_fecha().getText())){
		if (this.eval_rows()){
			
			control.GenerarAsientodePago();	
		}else {
			error("El sistema no permite grabar un ingreso erroneo");
		}
			
	}else {
		
	}
	
	
}

public boolean checkOrigen(){
	boolean check=false;
	
	return check;
}

public void setCaja(String idcaja){
	for (int i=0;i<frame.get_list_cajas().getItemCount();i++){
		if (idcaja.compareTo(frame.get_list_cajas().getItemAt(i).toString())==0){
			frame.get_list_cajas().setSelectedIndex(i);
		}
	}
}

}