package aplicacion.cliente.archivo.logic;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.effect.BufferedImageOpEffect;
import org.jdesktop.jxlayer.plaf.ext.LockableUI;

import com.jhlabs.image.BoxBlurFilter;

import aplicacion.herramientas.conexion.*;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.sortableselector.*;
import aplicacion.herramientas.java.*;

import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.cliente.archivo.gui._Frame;
import aplicacion.cliente.archivo.logic._Data;
import aplicacion.cliente.cobranza.constructor._Constructor;


public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private aplicacion.herramientas.java.buscadores.Cliente bCliente=null;
	private aplicacion.herramientas.java.visualizadores.Cliente vCliente=null;
	 
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	private aplicacion.cliente.reporte.constructor._Constructor reporte=null;
	public void reporte(){
		String idcliente=frame.get_txt_idcliente().getText();
		if (idcliente.compareTo("")!=0){
			if (reporte!=null){
				reporte.dispose();
			}
			reporte= new aplicacion.cliente.reporte.constructor._Constructor();
			reporte.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
			reporte.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
			reporte.setParameter(aplicacion.cliente.reporte.interfaces._Parametros.idcliente, idcliente);
			reporte.build(this.getConstructor());
			reporte.init();	
		}
	}
	
public void verAnalitico(){
		
		String idcliente=frame.get_txt_idcliente().getText();
		if (idcliente.compareTo("")!=0){
				aplicacion.cliente.corrector.constructor._Constructor 
				CC=new aplicacion.cliente.corrector.constructor._Constructor();
				CC.setParameter(_parametros.connector, this._data.getConnectionHandler());
				CC.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
				CC.setParameter(aplicacion.cliente.corrector.interfaces._parametros.idcliente, idcliente);
				CC.build(this.getConstructor());
				CC.init();	
			
				
		}
	}

	public void cargar_categorias(String idcategoria){
		String[] categorias=data.getCategorias();
		frame.get_lst_categoria().removeAllItems();
		String categoria=data.getCategoriaNombre(idcategoria);
		frame.get_lst_categoria().addItem("");
		for (int i=0;i<categorias.length;i++){
			frame.get_lst_categoria().addItem(categorias[i]);	
		}
		if (categoria.compareTo("")!=0){
			for (int i=0;i<categorias.length;i++){
				if (categorias[i].compareTo(categoria)==0){
					frame.get_lst_categoria().setSelectedIndex(i);		
				}
			}	
		}
		
		
	}
	public void editarCobranza(String id){
		aplicacion.cliente.cobranza.constructor._Constructor
		C= new aplicacion.cliente.cobranza.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.build(this.getConstructor());
		C.init();
		aplicacion.cliente.cobranza.logic._Logic logic=(aplicacion.cliente.cobranza.logic._Logic) C.getLogic();
		logic.load_Cobranza(id);
	}
	
	public void editar(){
		int row=frame.getJTable().getSelectedRow();
		
		if (row>=0){
			String tc=frame.getJTable().getValueAt(row,1).toString();
			String idcomprobante=frame.getJTable().getValueAt(row,2).toString();
			System.out.println("Editar "+tc+" "+idcomprobante);
			if (tc.compareTo("CBCT")==0){
				this.editarCobranza(idcomprobante);
			}
		}
	
	}
	
	public void clean(){
		frame.get_txt_idcliente().setText("");
		frame.get_txt_idcliente().setEditable(true);
		frame.get_txt_proveedordescripcion().setText("");
		frame.get_txt_telefono().setText("");
		frame.get_txt_fax().setText("");
		frame.get_txt_email().setText("");
		frame.get_txt_contacto().setText("");
		frame.get_txt_localidad().setText("");
		frame.get_txt_observaciones().setText("");
		frame.get_txt_cond_iva().setText("");
		
		
		frame.get_txt_cuit().setText("");
		frame.get_txt_cond_venta().setText("");
		frame.get_txt_condicion_venta_detalle().setText("");
		frame.setJTable1(null);
		frame.get_txt_saldo().setText("");
		frame.get_txt_saldo_alfa().setText("");
		frame.get_txt_debe_alfa().setText("");
		frame.get_txt_haber_alfa().setText("");
		frame.get_txt_saldo_total().setText("");
		frame.get_txt_saldo_total_alfa().setText("");
		frame.get_txt_saldo_total_beta().setText("");
		
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
		
		frame.get_txt_debe().setText("");
		frame.get_txt_haber().setText("");
		frame.get_txt_saldo().setText("");
		frame.get_txt_limite_credito().setText("");
		frame.get_txt_descuento().setText("");
		frame.setJTable1(null);
		frame.get_lst_categoria().removeAllItems();
		this.block(true);
	}
	
	public void guardar(){
		//aca va un check
boolean ok=false;
		
		String iduser=this.validar_usuario();
		if (iduser.compareTo("")!=0){
		ok=true;	
		}
		
		if (ok){
				ok=_guardar(iduser);	
				
				
		}
		
	}
	
	public boolean _guardar(String iduser){
		
		String limite=frame.get_txt_limite_credito().getText();
		String descuento=frame.get_txt_descuento().getText();
		String idcategoria=frame.get_lst_categoria().getSelectedItem().toString();
		if (idcategoria.compareTo("")!=0){
			idcategoria=data.getCategoriaId(idcategoria);	
		}
		
		String email=frame.get_txt_email().getText();
		String idcuenta=frame.get_txt_idcliente().getText();
		String condicion=frame.get_txt_cond_venta().getText();
		String tipo_doc=frame.get_txt_tipo_doc().getText();
		String doc=frame.get_txt_cuit().getText();
		String calle=frame.get_txt_domicilio().getText();
		String numero=frame.get_txt_numero().getText();
		String piso=frame.get_txt_piso().getText();
		String postal=frame.get_txt_postal().getText();
		String idprovincia=frame.get_txt_idprovincia().getText();
		String localidad=frame.get_txt_localidad().getText();
		String telefono=frame.get_txt_localidad().getText();
		String fax=frame.get_txt_fax().getText();
		String contacto=frame.get_txt_contacto().getText();
		String iva=frame.get_txt_cond_iva().getText();
		String observaciones=frame.get_txt_observaciones().getText();
		double _limite=0.0;
		double _descuento=0.0;
		_limite=new Double(limite);
		_descuento=new Double(descuento);
		String q=data.getUpdateAdicional(idcuenta, _limite, _descuento, idcategoria, email, calle, numero, piso, postal, idprovincia,localidad, telefono, fax, contacto, observaciones, tipo_doc, doc, iva, condicion);
		data.beginTransaction();
		data.clearBatch();
		
		
		String idoperacion=""+data.getProximoOperacion();
		String registra_operacion=""+data.getOperacion(idoperacion, iduser,  "GUARDAR CLIENTE DESDE MAESTRO");
		data.addBatch(registra_operacion);
		String inc=data.getUpdateOperacion();
		data.addBatch(inc);
		
		data.addBatch(q);
		boolean error=data.executeBatch();
		if (!error){
			data.commitTransaction();
			aviso("Se grabo Correctamente");
		}else{
			data.rollbackTransaction();
			error("Error Grabando");
		}
		return error;
	}
	public void block(boolean block){
		frame.getLockableUI().setLocked(block);
	}
	

	
	private aplicacion.herramientas.java.evaluadores.Cliente cliente=null;
	
	public void initialize_cliente(){
		cliente=new aplicacion.herramientas.java.evaluadores.Cliente(){
			public void cargar(String codigo){
				cargarCliente(codigo);
			}
		};
		cliente.setConstructor(this.getConstructor());
	}
	public void BuscarCliente(JTextField tx){
		cliente.Buscar(tx);
	}
	public void buscarCliente(JTextField tx){
		cliente.buscar(tx);
	}
	
	public void BuscarCliente(){
		cliente.Buscar(frame.get_txt_idcliente());
	}
	public void evaluarCliente(JTextField tx){
		cliente.evaluate(tx);
	}
	
	public void focus(){
		frame.get_txt_idcliente().requestFocusInWindow();
	}
	private void cargarCliente(String idcliente){
		Object[][] results=this.data.getCliente(idcliente);
		if (results!=null){
			if (results.length>0){
				frame.get_txt_idcliente().setEditable(false);
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
				String idcategoria=results[0][21].toString();
				String limite=results[0][22].toString();
				String descuento=results[0][23].toString();
				
				double limite_credito=0.0;
				try {
					limite_credito=new Double(limite);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				double _descuento=0.0;
				try {
					_descuento=new Double(descuento);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.get_txt_limite_credito().setText(""+limite_credito);
				frame.get_txt_descuento().setText(""+_descuento);
				this.cargar_categorias(idcategoria);
				this.cargarSaldos(idcliente);
				this.cargarSaldosAlfa(idcliente);
				calculate_total();
				this.block(false);
				frame.get_txt_proveedordescripcion().requestFocusInWindow();
			}else {
				aviso( "Ingrese Codigo de Cliente. utilice F5 para buscar");
				this.block(true);
			}
		}
		
	}
	
	public void evaluar_descripcion(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_domicilio().requestFocusInWindow();
		}else{
			error("La descripcion no puede ser nula ");
			tx.requestFocusInWindow();
		}
	}
	
	public void evaluar_calle(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_numero().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}
	public void evaluar_numero(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_piso().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}
	
	public void evaluar_piso(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_postal().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}

	public void evaluar_postal(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_localidad().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}
	
	public void evaluar_localidad(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_idprovincia().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}
	
	public void evaluar_observaciones(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_cond_iva().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}
	
	public void evaluar_contacto(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_observaciones().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}
	
	public void evaluar_provincia(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_telefono().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}
	
	public void evaluar_telefono(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_fax().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}
	
	public void evaluar_fax(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_email().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}
	
	public void evaluar_email(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_txt_contacto().requestFocusInWindow();
		}else{
			tx.requestFocusInWindow();
		}
	}
	public void actualizar_saldo(){
		String idcliente=frame.get_txt_idcliente().getText();
		cargarSaldos(idcliente);	
	}
	public void cargarSaldos(String idcliente){
		Object[][] results=data.getSaldos(idcliente);
		if (results!=null){
			if (results.length>0){
				create_table_saldos(results);
				calculate_saldos(frame.getJTable(),frame.get_txt_debe(),frame.get_txt_haber(),frame.get_txt_saldo());
				
			}
			
		}
	}
	
	public void calculate_total(){
		double beta=0;
		double alfa=0;
		String text_beta=frame.get_txt_saldo().getText();
		frame.get_txt_saldo_total_beta().setText(text_beta);
		String text_alfa=frame.get_txt_saldo_alfa().getText();
		frame.get_txt_saldo_total_alfa().setText(text_alfa);
		String _beta=frame.get_txt_saldo_total_beta().getText().replaceAll(",", "");
		String _alfa=frame.get_txt_saldo_total_alfa().getText().replaceAll(",", "");
		if (_beta.compareTo("")==0){
			_beta="0.0";
		}
		if (_alfa.compareTo("")==0){
			_alfa="0.0";
		}
		try {
			beta=new Double(_beta);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			alfa=new Double(_alfa);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double total=0;
		total=beta+alfa;
		frame.get_txt_saldo_total().setText(new Convertidor().getMoney(total, 2));
	}
	
	public void cargarSaldosAlfa(String idcliente){
		Object[][] results=data.getCptesAlfa(idcliente);
		if (results!=null){
			if (results.length>0){
				create_table_alfa(results);
				calculate_saldos(frame.getJTable1(),frame.get_txt_debe_alfa(),frame.get_txt_haber_alfa(),frame.get_txt_saldo_alfa());	
			}
			
		}
	}
	private void calculate_saldos(JTable table,JTextField debe,JTextField haber,JTextField saldo){
		double _debe=0.0;
		double _haber=0.0;
		double sum_debe=0.0;
		double sum_haber=0.0;
		for (int i=0;i<table.getRowCount();i++){
			String _Debe=table.getValueAt(i, 3).toString();
			String _Haber=table.getValueAt(i, 4).toString();
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
		debe.setText(new Convertidor().getMoney(sum_debe,2));
		haber.setText(new Convertidor().getMoney(sum_haber,2));
		saldo.setText(new Convertidor().getMoney(sum_debe-sum_haber,2));
	}
	
	private void create_table_saldos(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();
		col = new Column();
		col.setName("Fecha");
		col.setWidth(60);
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
	
	private void create_table_alfa(Object[][] results) {
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
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("haber");
		col.setWidth(70);
		col.setAligment(JTextField.RIGHT);
		col.setEditable(false);
		table.addColumn(col);
		
		
		
		
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		
		frame.setJTable1(table.getTable());
	}
	
	public void hacerCobranza(){
		String idcliente=frame.get_txt_idcliente().getText();
		if (idcliente.compareTo("")!=0){
			_Constructor CC=new _Constructor();
			CC.setParameter(_parametros.connector, this._data.getConnectionHandler());
			CC.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
			CC.setParameter(aplicacion.cliente.cobranza.interfaces._Parametros.idcliente, idcliente);
			CC.build(this.getConstructor());
			CC.init();	
		}
		
	}
	public void evaluarLimite(JTextField tx){
		String limite=tx.getText();
		limite=limite.replaceAll(",", "");
		double _limite=0.0;
		try {
			_limite=new Double(limite);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (_limite<0 ){
			error("Valor de Limite de Credito Incorrecto ");
		}else{
			frame.get_txt_descuento().requestFocusInWindow();
		}
	}
	
	public void evaluarDescuento(JTextField tx){
		String descuento=tx.getText();
		descuento=descuento.replaceAll(",", "");
		double _descuento=0.0;
		try {
			_descuento=new Double(descuento);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (_descuento<0 | _descuento >100){
			error("Valor de Descuento incorrecto ");
		}else{
			
		}
	}
}
