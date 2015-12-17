package aplicacion.proveedor.archivo.logic;

import java.awt.Color;
import java.awt.Font;
import aplicacion.herramientas.java.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.*;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.*;

import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.proveedor.archivo.gui._Frame;
import aplicacion.proveedor.archivo.interfaces._Interface;
import aplicacion.proveedor.archivo.logic._Data;
import aplicacion.proveedor.archivo.constructor._Constructor;


public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	
	
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public void focus(){
		frame.get_txt_idproveedor().requestFocusInWindow();
	}
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	public void initialize_proveedor(){
		proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
			public void cargar(String codigo){
				cargarProveedor(codigo);
			}
		};
		proveedor.setConstructor(this.getConstructor());
	}
	public void BuscarProveedor(JTextField tx){
		proveedor.Buscar(tx);
	}
	public void BuscarProveedor(){
		proveedor.Buscar(frame.get_txt_idproveedor());
	}
	public void evaluarProveedor(JTextField tx){
		proveedor.evaluate(tx);
	}

	
	
	public void clean(){
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_idproveedor().setEditable(true);
		frame.get_txt_proveedordescripcion().setText("");
		frame.get_txt_telefono().setText("");
		frame.get_txt_fax().setText("");
		frame.get_txt_email().setText("");
		frame.get_txt_contacto().setText("");
		frame.get_txt_localidad().setText("");
		frame.get_txt_observaciones().setText("");
		frame.get_txt_cond_iva().setText("");
		
		frame.get_txt_Descuento().setText("");
		frame.get_txt_cuit().setText("");
		frame.get_txt_cond_venta().setText("");
		frame.get_txt_condicion_venta_detalle().setText("");
		frame.get_txt_equivalencia().setText("");
		frame.get_txt_idtransporte().setText("");
		frame.get_txt_transporte_descripcion().setText("");
		frame.get_txt_domicilio().setText("");
		frame.get_txt_numero().setText("");
		frame.get_txt_piso().setText("");
		frame.get_txt_postal().setText("");
		frame.get_txt_idprovincia().setText("");
		frame.get_txt_provincia().setText("");
		frame.get_txt_condicion_detalle().setText("");
		
		frame.get_txt_tipo_doc().setText("");
		frame.get_txt_tipo_doc_detalle().setText("");
		frame.setJTable(null);
		
		frame.get_imprime_etiquetas().setSelectedIndex(-1);
		frame.get_permite_articulos().setSelectedIndex(-1);
		frame.get_requiere_ingresos_brutos().setSelectedIndex(-1);
		frame.get_requiere_iva10().setSelectedIndex(-1);
		frame.get_requiere_iva21().setSelectedIndex(-1);
		frame.get_requiere_iva27().setSelectedIndex(-1);
		frame.get_requiere_neto_no_gravado().setSelectedIndex(-1);
		frame.get_requiere_percepcion_ganancias().setSelectedIndex(-1);
		frame.get_requiere_percepcion_iva().setSelectedIndex(-1);
		frame.get_requiere_rg3337().setSelectedIndex(-1);
		frame.get_actualiza_precios().setSelectedIndex(-1);
		frame.get_requiere_remitos().setSelectedIndex(-1);
		
		frame.get_requiere_impuestos_internos().setSelectedIndex(-1);
		frame.get_txt_alicuota_impuesto_interno().setText("");
		frame.get_txt_alicuota_ingresos_brutos().setText("");
		frame.get_txt_alicuota_percepcion_iva().setText("");
		frame.setJTable1(null);
		
	}
	
	
	public void buscarCuenta(JTextField cuenta){
		if (vSelector!=null){
			vSelector.dispose();
		}
		vSelector
		=new aplicacion.herramientas.java.visualselector.constructor._Constructor();
		vSelector.setParameter(aplicacion.modelo.interfaces._parametros.connector, data.getConnectionHandler());
		vSelector.build(this.getConstructor());
		vSelector.init();
		aplicacion.herramientas.java.visualselector.logic._Logic logic=
			(aplicacion.herramientas.java.visualselector.logic._Logic)vSelector.getLogic();
		logic.setCaller(cuenta);
		aplicacion.herramientas.java.visualselector.logic.Columna c 
		= new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f 
		= new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("codigo");
		c.setAlias("codigo");
		c.setColumnField(cuenta);
		c.setWidth(120);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(240);
		logic.addColumn(c);

		logic.setFromTable("ma_cuentas ");
		
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("descripcion");
		f.setValor(cuenta.getText());
		logic.addFilter(f);
		
		
		
		logic.setTop(100);
		logic.setOrderby("descripcion");
		int x=frame.getLocation().x;
		int y=frame.getLocation().x;
		
		
		int n=logic._loadoptions();
		if (n==0){
			aviso("no hay cuentas con esa descripcion");
		}

			
		
	
	}
	
	
	
	public void BuscarCuenta(JTextField ext) {
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
						
					}
				};
			}
		};
		CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		CC.build(this.getConstructor());
		
		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic 
		logic=(aplicacion.herramientas.java.sortableselector.logic._Logic)CC.getLogic();

		columna c = new columna();
		Filtro f = new Filtro();
		c = new columna();
		c.setNombre("codigo");
		c.setAlias("codigo");
		c.setColumnField(ext);
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(250);
		c.setMaster(false);
		logic.addColumn(c);


		logic.addFromTable("ma_cuentas ");
		f = new Filtro();
		f.setNombre("codigo");
		f.setAlias("codigo");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("descripcion");
		f.setAlias("descripcion");
		f.setWidth(250);
		logic.addFilter(f);
		logic.addOrder("codigo");
		logic.addRestriction("codigo not like '21101%' and codigo not like '11201%' ");
		logic.init();
	}
	
	public void cargarCuenta(JTextField tx,int row){
		String idcuenta=tx.getText();
		int n=-1;
		try{
			n=new Integer(idcuenta);
		}catch(Exception e){
			
		}
		if (n>1){
			this.cargarCuenta(idcuenta,row);	
		}else {
			this.buscarCuenta(tx);
		}
		
	}
	
	private void cargarCuenta(String idcuenta,int row){
		Object[][] results=this.data.cargarCuenta(idcuenta);
		if (results!=null){
			if (results.length>0){
				frame.getJTable1().setValueAt((String) results[0][1], row, 1);
				DefaultTableModel model=(DefaultTableModel)frame.getJTable1().getModel();
				if (row==model.getRowCount()-1){
					model.setRowCount(model.getRowCount()+1);
				}
				frame.getJTable1().changeSelection(row+1, 0, false, false);
				frame.getJTable1().editCellAt(row+1, 0);
				frame.getJTable1().transferFocus();
			}else {
				error("Codigo inexistente");
			}
		}
	}
	
	private boolean guardar_imputaciones(){
		boolean ok=false;
		String codigo=frame.get_txt_idproveedor().getText();
		String[] parameters=new String[]{
				codigo
		};
		String q=data.getQuery("delete_imputacion", parameters);
		data.clearBatch();
		data.addBatch(q);
		ok=!data.executeBatch();
		
		data.clearBatch();
		for (int i=0;i<frame.getJTable1().getRowCount();i++){
			String cuenta="";
			
			try {
				cuenta=(String) frame.getJTable1().getValueAt(i, 0);
			}catch(Exception e){
				
			}
			if (cuenta!=null){
				if (cuenta.compareTo("")!=0){
					String _predeterminada="0";
					
					//aca debe ir un check para ver que la cuenta sea valida
					parameters=new String[]{
							codigo,
							cuenta,
							""+i,
							
					};
					q=data.getQuery("insert_imputacion", parameters);
					data.addBatch(q);
				}	
			}
			
			
			
		}
		ok=!data.executeBatch();
		return ok;
	}
	
	
	
	private void cargarProveedor(String idproveedor){
		Object[][] results=this.data.getProveedor(idproveedor);
		if (results!=null){
			if (results.length>0){
				frame.get_txt_idproveedor().setEditable(false);
				frame.get_txt_proveedordescripcion().setText(results[0][1].toString());
				frame.get_txt_telefono().setText(results[0][2].toString());
				frame.get_txt_fax().setText(results[0][3].toString());
				frame.get_txt_email().setText(results[0][4].toString());
				frame.get_txt_contacto().setText(results[0][5].toString());
				frame.get_txt_localidad().setText(results[0][6].toString());
				frame.get_txt_observaciones().setText(results[0][7].toString());
				frame.get_txt_cond_iva().setText(results[0][8].toString());
				
				
				frame.get_txt_cuit().setText(results[0][9].toString());
				frame.get_txt_cond_venta().setText(results[0][10].toString());
				frame.get_txt_condicion_venta_detalle().setText(results[0][11].toString());
				
				
				frame.get_txt_domicilio().setText(results[0][12].toString());
				frame.get_txt_numero().setText(results[0][13].toString());
				frame.get_txt_piso().setText(results[0][14].toString());
				frame.get_txt_postal().setText(results[0][15].toString());
				frame.get_txt_idprovincia().setText(results[0][16].toString());
				frame.get_txt_provincia().setText(results[0][17].toString());
				
				
				
				
				frame.get_txt_condicion_detalle().setText(results[0][18].toString());
				
				frame.get_txt_tipo_doc().setText(results[0][19].toString());
				frame.get_txt_tipo_doc_detalle().setText(results[0][20].toString());
				
				frame.get_txt_idtransporte().setText(results[0][39].toString());
				frame.get_txt_transporte_descripcion().setText(results[0][40].toString());
				String requiere_iibb=(String) results[0][21];
				String requiere_perc_iva=(String) results[0][22];
				String requiere_perc_gan=(String) results[0][23];
				String requiere_neto_ngrav=(String) results[0][24];
				String requiere_iva10=(String) results[0][25];
				String requiere_iva27=(String) results[0][26];
				String imprime_etiq=(String) results[0][27];
				String actualiza_precios=(String) results[0][28];
				String requiere_iva=(String) results[0][29];
				String permite_articulos=(String) results[0][30];
				String requiere_rg3337=(String) results[0][31];
				String requiere_remitos=(String) results[0][32];
				String requiere_impuestos_internos=(String) results[0][33];
				String alicuota_impuestos_internos=(String) results[0][34];
				String alicuota_ingresos_brutos=(String) results[0][35];
				String alicuota_percepcion_iva=(String) results[0][36];
				String tc=(String) results[0][37];
				String descuento=(String) results[0][38];
				int _requiere_iibb=0;
				int _requiere_perc_iva=0;
				int _requiere_perc_gan=0;
				int _requiere_neto_ngrav=0;
				int _requiere_iva10=0;
				int _requiere_iva27=0;
				int _imprime_etiq=0;
				int _actualiza_precios=0;
				int _requiere_iva=0;
				int _permite_articulos=0;
				int _requiere_rg3337=0;
				int _requiere_remitos=0;
				int _requiere_impuestos_internos=0;
				int _tc=0;
				double alic_ii=0.0;
				double alic_iibb=0.0;
				double alic_perc_iva=0.0;
				try {
					_requiere_iibb=new Integer(requiere_iibb);
					_requiere_perc_iva=new Integer(requiere_perc_iva);
					_requiere_perc_gan=new Integer(requiere_perc_gan);
					_requiere_neto_ngrav=new Integer(requiere_neto_ngrav);
					_requiere_iva10=new Integer(requiere_iva10);
					_requiere_iva27=new Integer(requiere_iva27);
					_imprime_etiq=new Integer(imprime_etiq);
					_actualiza_precios=new Integer(actualiza_precios);
					_requiere_iva=new Integer(requiere_iva);
					_permite_articulos=new Integer(permite_articulos);
					_requiere_rg3337=new Integer(requiere_rg3337);
					_requiere_remitos=new Integer(requiere_remitos);
					_requiere_impuestos_internos=new Integer(requiere_impuestos_internos);
					_tc=new Integer(tc);
				}catch(Exception e){
					
				}
				try {
				alic_ii=new Double(alicuota_impuestos_internos);
				alic_iibb=new Double(alicuota_ingresos_brutos);
				alic_perc_iva=new Double(alicuota_percepcion_iva);
				}catch(Exception e){
					
				}
				frame.get_imprime_etiquetas().setSelectedIndex(_imprime_etiq);
				frame.get_permite_articulos().setSelectedIndex(_permite_articulos);
				frame.get_requiere_ingresos_brutos().setSelectedIndex(_requiere_iibb);
				frame.get_requiere_iva10().setSelectedIndex(_requiere_iva10);
				frame.get_requiere_iva21().setSelectedIndex(_requiere_iva);
				frame.get_requiere_iva27().setSelectedIndex(_requiere_iva27);
				frame.get_requiere_neto_no_gravado().setSelectedIndex(_requiere_neto_ngrav);
				frame.get_requiere_percepcion_ganancias().setSelectedIndex(_requiere_perc_gan);
				frame.get_requiere_percepcion_iva().setSelectedIndex(_requiere_perc_iva);
				frame.get_requiere_rg3337().setSelectedIndex(_requiere_rg3337);
				frame.get_actualiza_precios().setSelectedIndex(_actualiza_precios);
				frame.get_requiere_remitos().setSelectedIndex(_requiere_remitos);
				frame.get_requiere_impuestos_internos().setSelectedIndex(_requiere_impuestos_internos);
				
				frame.get_txt_alicuota_impuesto_interno().setText(""+alic_ii);
				frame.get_txt_alicuota_ingresos_brutos().setText(""+alic_iibb);
				frame.get_txt_alicuota_percepcion_iva().setText(""+alic_perc_iva);
				frame.get_txt_Descuento().setText(descuento);
				//a.requiere_iibb,a.requiere_perc_iva,a.requiere_perc_gan,requiere_neto_ngrav,requiere_iva10,
				//requiere_iva27,imprime_etiq,actualiza_precios,a.codigoimputacion
				this.cargarSaldos(idproveedor);
				this.cargarImputaciones(idproveedor);
				String equivalente=data.getEquivalente(idproveedor);
				frame.get_txt_equivalencia().setText(equivalente);
			}else {
				error("Codigo inexistente");
			}
		}
		
	}
	
	private aplicacion.herramientas.java.evaluadores.Transporte Transporte = null;

	public void initialize_Transporte() {
		Transporte = new aplicacion.herramientas.java.evaluadores.Transporte() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				frame.get_txt_transporte_descripcion().setText(descripcion);
				
			}
		};
		Transporte.setConstructor(this.getConstructor());
	}

	public void reconnect_Transporte() {
		if (Transporte != null) {
			Transporte.setConstructor(this.getConstructor());
		}
	}

	public void BuscarTransporte(JTextField tx) {
		Transporte.Buscar(tx);
	}

	public void BuscarTransporte() {
		Transporte.Buscar(frame.get_txt_idtransporte());
	}

	public void buscarTransporte(JTextField tx) {
		Transporte.buscar(tx);
	}

	public void evaluarTransporte(JTextField tx) {
		Transporte.evaluate(tx);
	}
	public void guardar(){
		boolean ok=update();
		if (ok){
			ok=guardar_imputaciones();
			if (ok){
				aviso("se grabo correctamente");	
			}
			
		}
	}
	
	private boolean update(){
		boolean ok=false;
		int _requiere_iibb=frame.get_requiere_ingresos_brutos().getSelectedIndex();
		int _requiere_perc_iva=frame.get_requiere_percepcion_iva().getSelectedIndex();
		int _requiere_perc_gan=frame.get_requiere_percepcion_ganancias().getSelectedIndex();
		int _requiere_neto_ngrav=frame.get_requiere_neto_no_gravado().getSelectedIndex();
		int _requiere_iva10=frame.get_requiere_iva10().getSelectedIndex();
		int _requiere_iva27=frame.get_requiere_iva27().getSelectedIndex();
		int _imprime_etiq=frame.get_imprime_etiquetas().getSelectedIndex();
		int _actualiza_precios=frame.get_actualiza_precios().getSelectedIndex();
		int _requiere_iva=frame.get_requiere_iva21().getSelectedIndex();
		int _permite_articulos=frame.get_permite_articulos().getSelectedIndex();
		int _requiere_rg3337=frame.get_requiere_rg3337().getSelectedIndex();
		int _requiere_remitos=frame.get_requiere_remitos().getSelectedIndex();
		int _requiere_impuestos_internos=frame.get_requiere_impuestos_internos().getSelectedIndex();
		
		String alicuota_impuestos_internos=frame.get_txt_alicuota_impuesto_interno().getText();
		String alicuota_ingresos_brutos=frame.get_txt_alicuota_ingresos_brutos().getText();
		String alicuota_percepcion_iva=frame.get_txt_alicuota_percepcion_iva().getText();
		double _alic_ii=0.0;
		double _alic_iibb=0.0;
		double _alic_perc_iva=0.0;
		String idtransporte=frame.get_txt_idtransporte().getText();
		try {
			_alic_ii=new Double(alicuota_impuestos_internos.replaceAll(",", ""));
		}catch(Exception e){
			
		}
		try {
			_alic_iibb=new Double(alicuota_ingresos_brutos.replaceAll(",", ""));
		}catch(Exception e){
			
		}
		try {
			_alic_perc_iva=new Double(alicuota_percepcion_iva.replaceAll(",", ""));
		}catch(Exception e){
			
			
		}
		String codigo=frame.get_txt_idproveedor().getText();
		String descuento=frame.get_txt_Descuento().getText();
		String contacto=frame.get_txt_contacto().getText();
		String email=frame.get_txt_email().getText();
		String localidad=frame.get_txt_localidad().getText();
		String cpostal=frame.get_txt_postal().getText();
		String observaciones=frame.get_txt_observaciones().getText();
		String telefono=frame.get_txt_telefono().getText();
		String fax=frame.get_txt_fax().getText();
		String calle=frame.get_txt_domicilio().getText();
		String numero=frame.get_txt_numero().getText();
		String piso=frame.get_txt_piso().getText();
		String equivalente=frame.get_txt_equivalencia().getText();
		String q=data.getUpdateMaestro(codigo, telefono, cpostal, fax, email, localidad, observaciones, contacto,calle,numero,piso);
		data.clearBatch();
		data.addBatch(q);
		String _equivalente=data.getEquivalente(codigo);
		if (equivalente.compareTo("")!=0){
			if (_equivalente.compareTo("")!=0){
				q=data.getUpdateEquivalente(codigo, equivalente);
			}else{
				
				q=data.getInsertEquivalente(codigo, equivalente);
			}
			
			data.addBatch(q);	
		}else{
			q=data.getUpdateEquivalente(codigo, equivalente);
			data.addBatch(q);	
		}
		
		ok=!data.executeBatch();
		if (ok){
			ok=data.updateRestrictions(
					""+_requiere_iibb,
					""+_requiere_perc_iva,
					""+_requiere_perc_gan,
					""+_requiere_neto_ngrav,
					""+_actualiza_precios,
					""+_imprime_etiq,
					""+_requiere_iva10,
					""+_requiere_iva27,
					""+_requiere_iva,
					""+_permite_articulos,
					""+_requiere_rg3337,
					""+_requiere_remitos,
					""+_requiere_impuestos_internos,
					""+_alic_ii,
					""+_alic_iibb,
					""+_alic_perc_iva,
					"",
					""+descuento,
					idtransporte,
					codigo);
			
		}
		return ok;
	}
	
	public void actualizar_saldo(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		cargarSaldos(idproveedor);	
	}
	
	public void cargarSaldos(String idproveedor){
		Object[][] results=data.getSaldos(idproveedor);
		if (results!=null){
			if (results.length>0){
				create_table_saldos(results);
				calculate_saldos();	
			}
			
		}
	}
	
	private void calculate_saldos(){
		double _debe=0.0;
		double _haber=0.0;
		double sum_debe=0.0;
		double sum_haber=0.0;
		for (int i=0;i<frame.getJTable().getRowCount();i++){
			String _Debe=frame.getJTable().getValueAt(i, 3).toString();
			String _Haber=frame.getJTable().getValueAt(i, 4).toString();
			_debe=0.0;
			_haber=0.0;
			_Debe=_Debe.replace(" ", "");
			_Debe=_Debe.replace(",", "");
			_Haber=_Haber.replace(" ", "");
			_Haber=_Haber.replace(",", "");
			try {
				_debe=new Double(_Debe);	
			}catch(Exception e){
				
			}
			try {
				_haber=new Double(_Haber);	
			}catch(Exception e){
				
			}
			
			sum_debe=sum_debe+_debe;
			sum_haber=sum_haber+_haber;
		}
		frame.get_txt_debe().setText(new Convertidor().getMoney(sum_debe,2));
		frame.get_txt_haber().setText(new Convertidor().getMoney(sum_haber,2));
		frame.get_txt_saldo().setText(new Convertidor().getMoney(sum_debe-sum_haber,2));
	}
	
	private void create_table_saldos(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();
		col = new Column();
		col.setName("Fecha");
		col.setWidth(70);
		col.setEditable(false);
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("TC");
		col.setWidth(60);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idComprobante");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("debe");
		col.setWidth(70);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("haber");
		col.setWidth(70);
		col.setEditable(false);
		table.addColumn(col);
		
		
		
		
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		
		frame.setJTable(table.getTable());
	}
	
	public void cargarImputaciones(String codigo){
		Object[][] results=data.getImputaciones(codigo);
		if (results!=null){
			if (results.length<=0){
				results=new Object[][]{
						{"",""}	
				};
			}
		}else {
			results=new Object[][]{
					{"",""}	
			};
		}
		create_table_imputacion(results);
	}
	
	public void delete_renglon(int row){
		
		boolean ok=(this.preguntar("Confirmar", "Desea Eliminar esta cuenta de imputacion?"));
		if (ok){
			DefaultTableModel model=(DefaultTableModel)frame.getJTable1().getModel();
			
			if (row>=0 & row<=(model.getRowCount()-1)){
				model.removeRow(row);	
			}
			if (model.getRowCount()<=0){
				model.setRowCount(1);
			}	
		}
		
	}
	
	private void create_table_imputacion(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();
		_Constructor constructor=(_Constructor)this.getConstructor();
		col = new Column();
		col.setName("Cuenta");
		col.setWidth(120);
		col.setEditable(true);
		
		CellEditor pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_imputacion_cuenta);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);
		
		col = new Column();
		col.setName("Detalle");
		col.setWidth(200);
		col.setEditable(false);
		table.addColumn(col);
		
		
		table.setData(results);
		
		Font fuente=new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		
		JTable _table=table.getTable();
		_table.addKeyListener(this._constructor.getKeyListener());
		_table.setName(_Interface._table_imputacion);
		frame.setJTable1(_table);
	}
	public void hacerPago(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		if (idproveedor.compareTo("")!=0){
			if (data.existeProveedor(idproveedor)){
				aplicacion.proveedor.pago.constructor._Constructor 
				CC=new aplicacion.proveedor.pago.constructor._Constructor();
				CC.setParameter(_parametros.connector, this._data.getConnectionHandler());
				CC.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
				CC.setParameter(aplicacion.proveedor.pago.interfaces._Parametros.idproveedor, idproveedor);
				CC.build(this.getConstructor());
				CC.init();	
			}else {
				aviso("debe ingresar una cuenta de proveedor valida");
			}
				
		}else {
			aviso("debe ingresar una cuenta de proveedor valida");
		}
		
	}
	
public void verAnalitico(){
		
		String idproveedor=frame.get_txt_idproveedor().getText();
		if (idproveedor.compareTo("")!=0){
				aplicacion.proveedor.corrector.constructor._Constructor 
				CC=new aplicacion.proveedor.corrector.constructor._Constructor();
				CC.setParameter(_parametros.connector, this._data.getConnectionHandler());
				CC.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
				CC.setParameter(aplicacion.proveedor.corrector.interfaces._parametros.idproveedor, idproveedor);
				CC.build(this.getConstructor());
				CC.init();	
			
				
		}
	}

private aplicacion.proveedor.reporte.constructor._Constructor reporte=null;
public void reporte(){
	String idproveedor=frame.get_txt_idproveedor().getText();
	if (idproveedor.compareTo("")!=0){
		if (reporte!=null){
			reporte.dispose();
		}
		reporte= new aplicacion.proveedor.reporte.constructor._Constructor();
		reporte.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		reporte.setParameter(aplicacion.proveedor.reporte.interfaces._Parametros.idproveedor, idproveedor);
		reporte.build(this.getConstructor());
		reporte.init();	
	}
	
	
}
}
