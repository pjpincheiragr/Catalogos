package aplicacion.compras.carga.encabezado.logic;
import java.awt.Color;
import aplicacion.herramientas.java.*;
import java.awt.Font;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.*;

import aplicacion.alfa.comprobantes.mv_asientos.objetos.Asiento;
import aplicacion.alfa.comprobantes.mv_asientos.objetos.Renglon;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.sortableselector.interfaces.*;
import aplicacion.herramientas.java.sortableselector.*;
import aplicacion.herramientas.java.sortableselector.gui.*;
import aplicacion.herramientas.java.table.*;
//import aplicacion.proveedor.archivo.constructor._Constructor;
import aplicacion.proveedor.archivo.interfaces._Parametros;


import aplicacion.herramientas.java.image.logic.*;
import aplicacion.compras.carga.encabezado.constructor._Constructor;
import aplicacion.compras.carga.encabezado.gui._Frame;
import aplicacion.compras.carga.encabezado.gui._Confirmacion;
import aplicacion.compras.carga.encabezado.interfaces._Interface;
import aplicacion.herramientas.conexion.*;





import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.table.*;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.io.File;

import uk.co.mmscomputing.device.scanner.Scanner;
import uk.co.mmscomputing.device.scanner.ScannerIOException;

import java.util.*;
public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private _Confirmacion C=null;
	private LinkedList images=null;
	private int requiere_iva10=0;
	private int requiere_iva21=0;
	private int requiere_iva27=0;
	private int requiere_percepcion_ingresos_brutos=0;
	private int requiere_percepcion_iva=0;
	private int tipo_tc=0;
	private int requiere_percepcion_ganancias=0;
	private int requiere_neto_no_grabado=0;
	private int requiere_rg3337=0;
	private int requiere_impuestos_internos=0;
	private int permite_articulos=0;

	
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	//private String server="//192.168.4.150/windows storage/fotos/";
	private String server="tmp/";
	private Scanner scanner;
	private _ScannerListener scannerlistener;
	private double alic_iva1=0.21;
	private double alic_iva2=0.105;
	private double alic_iva3=0.27;
	private double alic_iibb=0.02;
	private double alic_perc_iva=0.0;
	private double alic_impuestos_internos=0.0;
	private double alic_rg3337=0.03;
	private double base_iibb=50.0;
	
	private ImageIcon _icon_articulos=null;
	private ImageIcon _icon_cpte=null;
	private ImageIcon _icon_imputacion=null;
	private ImageIcon _icon_imagen=null;
	
	private String _x_tc="";
	private String _x_cuenta="";
	private String _x_idcomprobante="";
	
	public _Logic(){
		_icon_articulos=new ImageIcon(getClass().getResource("/icons/gtk-stop.png"));
		_icon_cpte=new ImageIcon(getClass().getResource("/icons/gtk-stop.png"));
		_icon_articulos=new ImageIcon(getClass().getResource("/icons/gtk-stop.png"));
		
		scanner=Scanner.getDevice();
		scannerlistener=new _ScannerListener();
		scannerlistener.setLogic(this);
		scanner.addListener(scannerlistener);
	}
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void _load_fecha_vencimiento(){
		this.BuscarFecha(frame.get_txt_cai_vencimiento());
	}
	
	public void _load_fecha_factura(){
		this.BuscarFecha(frame.get_txt_fecha_factura());
	}
	
	public void _load_fecha_contable(){
		this.BuscarFecha(frame.get_txt_fecha_contable());
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
	public void buscarProveedor(JTextField tx){
		proveedor.buscar(tx);
	}
	public void BuscarProveedor(){
		proveedor.Buscar(frame.get_txt_idproveedor());
	}
	public void evaluarProveedor(JTextField tx){
		proveedor.evaluate(tx);
	}
	
	public void cleanComponents(){
		int c=frame.getJPanel4().getComponentCount();
		int comps=0;
		for (int i=0;i<c;i++){
			Component cx=null;
			try {
				cx=frame.getJPanel4().getComponent(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(cx!=null){
				if (cx instanceof ImageComponent){
					comps++;
					frame.getJPanel4().remove(i);
				}	
			}
			
		}
		frame.getJPanel4().validate();
		frame.getJPanel4().revalidate();
		frame.getJPanel4().repaint();
		
    }
	
	
	public void sortComponents(){
		//int c=frame.getJPanel4().getComponentCount()-2;
		int comps=0;
		for (int i=0;i<frame.getJPanel4().getComponentCount();i++){
			if (frame.getJPanel4().getComponent(i) instanceof ImageComponent){
				comps++;
				ImageComponent IC=(ImageComponent)frame.getJPanel4().getComponent(i);
				IC.setBounds(comps*90+50,30,80,100);		
			}
		}
		frame.getJPanel4().revalidate();
		frame.getJPanel4().validate();
		frame.getJPanel4().repaint();
        
	}
	public void setData(Data _data){
		this.data=(_Data)_data;
		this.server=data.getPrimitiveMessage("fotostorage");
		super.setData(_data);
		
	}
	
	public String getProveedor(String cuit){
		String proveedor="";
		Object[][] results=data.getProveedorCuit(cuit);
		if (results!=null){
			if (results.length>0){
				proveedor=(String) results[0][0];
			}
		}
		return proveedor;
	}
	
	public void _evaluate_cai(JTextField tx){
		String cai=tx.getText();
		Convertidor C= new Convertidor();
		if (cai.compareTo("")!=0){
			if (cai.length()==40){
				String cuit=cai.substring(0,11);
				String tc=cai.substring(11,13);
				String sucursal=cai.substring(13,17);
				String nro=cai.substring(17,31);
				boolean es=false;
				int year=0;
				try{
					year=new Integer(cai.substring(35,39));
				}catch(Exception e){
					
				}
				es=(year>2000);
				String fecha="";
					if (es){
						fecha=cai.substring(31,33)+"-";
						fecha+=cai.substring(33,35)+"-";
						fecha+=cai.substring(35,39);
								
					}else {
						fecha=cai.substring(31,35)+"-";
						fecha+=cai.substring(35,37)+"-";
						fecha+=cai.substring(37,39);
						fecha=C.ConvertDate("dd-MM-yyyy", "yyyy-MM-dd", fecha);
					}
				
					if (this.evaluarFecha(fecha)){
						String verif=cai.substring(39,40);
						String idproveedor=this.getProveedor(cuit);
						if (idproveedor.compareTo("")!=0){
							frame.get_txt_cai().setText(nro);
							frame.get_txt_cai_vencimiento().setText(fecha);
							frame.get_txt_idproveedor().setText(idproveedor);
							frame.get_txt_sucursal().setText(sucursal);
							frame.get_txt_fecha_factura().setEditable(true);
							frame.get_txt_fecha_contable().setEditable(true);
							this.evaluarProveedor(frame.get_txt_idproveedor());
							
						}	
					}else{
						
					}
				
			}else{
				if (cai.length()==14){
					double nro=0;
					try{
						nro=new Double(cai);
					}catch(Exception e){
						e.printStackTrace();
					}
					if (nro>0){
						frame.get_txt_cai_vencimiento().requestFocusInWindow();
					}else{
						error("CAI Incorrecto!");
					}
				}else {
					error("Ingrese un CAI de 14 Digitos o escanee el codigo de barras del comprobante");	
				}
			}
		}else{
				frame.get_txt_cai_vencimiento().requestFocusInWindow();
		}
	}
	
	public boolean check_cai_number(String number){
		boolean ok=false;
		//consulta base
		return ok;
	}
	
	private void load_fecha_contable(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		Object[][] results=data.getAsiento(idproveedor, this.getTipoTC(), sucursal, numero, letra);
		if (results!=null){
			if (results.length>0){
				frame.get_txt_fecha_contable().setText((String) results[0][2]);
			}
		}
	}
	
	private void cargarTipoTc(){
		frame.get_list_tc().removeAllItems();
		frame.get_list_tc().addItem("Factura");
		frame.get_list_tc().addItem("Nota de Credito");
		frame.get_list_tc().addItem("Remito");
		frame.get_list_tc().addItem("FCN");
	}
	
	private void cargarLetras(){
		frame.get_list_letra().removeAllItems();
		frame.get_list_letra().addItem("A");
		frame.get_list_letra().addItem("B");
		frame.get_list_letra().addItem("C");
		frame.get_list_letra().addItem("M");
		frame.get_list_letra().addItem("X");
	}
	
	public void init(){
		this.images=new LinkedList();
		this.cargarTipoTc();
		this.cargarLetras();
		this.clean();
		frame.get_txt_idproveedor().requestFocusInWindow();
	}
	
	
	public boolean esMayor(String mayor,String menor){
		DateFormat formatter;
	    Locale locale = Locale.getDefault();
	    int ix=0;
	    Date date1=null;
	    Date date2=null;
	    Calendar C=Calendar.getInstance();
		Calendar C2=Calendar.getInstance();
		boolean esmayor=false;
	    try {
	    	formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
	    	date1 = (Date)formatter.parse(mayor);
	    	//System.out.println("Transformado fecha: "+s1+" "+date1);
	    	formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
	    	date2 = (Date)formatter.parse(menor);
	    	//System.out.println("Transformado fecha: "+s2+" "+date2);
	    	Date now=new Date();
	    	C.setTime(date1);
	    	C2.setTime(date2);
	    	ix=now.compareTo(date1);
	        if (C.getTimeInMillis()>=C2.getTimeInMillis()) {
	        	//System.out.println("Porque esta vencida?");
	        	//System.out.println("Ahora es:"+C.getTimeInMillis()+" = "+C.getTime());
	        	//System.out.println("Lo Programado era:"+C2.getTimeInMillis()+" = "+C2.getTime());
	        	
	        	esmayor=true;
	        }
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return esmayor;
	}
	
	public void _evaluar_fecha_factura(JTextField tx){
		String fecha=tx.getText();
		String fecha_cai=frame.get_txt_cai_vencimiento().getText();
		if (fecha.compareTo("")==0){
			this.BuscarFecha(tx);
		}else{
			if (this.evaluarFecha(fecha)){
				if (this.evaluarFecha(fecha_cai)){
					if (this.esMayor(fecha_cai, fecha)){
						//this._calculate_fecha_contable();
						frame.get_txt_fecha_contable().setEditable(true);
						frame.get_txt_fecha_contable().requestFocusInWindow();	
					}else{
						error("Este comprobante esta vencido");
					}
				}
			//this._calculate_fecha_contable();
			frame.get_txt_fecha_contable().setEditable(true);
			frame.get_txt_fecha_contable().requestFocusInWindow();
				
			}else{
				error("Fecha "+fecha+" incorrecta");
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}	
		}
		
	}
	
	public void delete_fecha_contable(JTextField tx){
		String fecha=tx.getText();
		if (fecha.compareTo("")==0){
			frame.get_txt_fecha_factura().requestFocusInWindow();
		}
	}
	
	public void delete_fecha_cai(JTextField tx){
		String fecha=tx.getText();
		if (fecha.compareTo("")==0){
			frame.get_txt_cai().requestFocusInWindow();
		}
	}
	public void delete_fecha_factura(JTextField tx){
		String fecha=tx.getText();
		if (fecha.compareTo("")==0){
			frame.get_txt_sucursal().setEditable(true);
			frame.get_txt_sucursal().setEnabled(true);
			frame.get_txt_numero().setEditable(true);
			frame.get_txt_numero().setEnabled(true);
			
			frame.get_list_letra().setEnabled(true);
			
			frame.get_list_letra().requestFocusInWindow();
		}
	}
	
	public void _evaluar_fecha_contable(JTextField tx){
		String fecha=tx.getText();
		if (fecha.compareTo("")==0){
			this.BuscarFecha(tx);
		}else {
			if (this.evaluarFecha(fecha)){
				
				crear_asiento();
				this.frame.getJTable().requestFocusInWindow();
				this.frame.getJTable().changeSelection(0, 2,false,false);
				this.frame.getJTable().editCellAt(0, 2);
				this.frame.getJTable().transferFocus();
			}else{
				error("Fecha "+fecha+" incorrecta");
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}	
		}
		
	}
	
	public void _evaluar_fecha_cai(JTextField tx){
		String fecha=tx.getText();
		if (fecha.compareTo("")==0){
			this.BuscarFecha(tx);
			/*if (preguntar("Confirmar","Carga Fecha de Cai?")){
					this.load_calendar(tx);
			}else{
				frame.get_txt_sucursal().setEditable(true);
				frame.get_txt_sucursal().setEnabled(true);
				frame.get_txt_numero().setEditable(true);
				frame.get_txt_numero().setEnabled(true);
				frame.get_list_letra().setEnabled(true);
				//frame.get_txt_sucursal().requestFocusInWindow();
				frame.get_list_tc().requestFocusInWindow();
			}*/
			
		}else{
			if (this.evaluarFecha(fecha)){
				//frame.get_txt_sucursal().requestFocusInWindow();
				frame.get_list_tc().requestFocusInWindow();
			}else{
				error("Fecha "+fecha+" incorrecta");
				tx.setSelectionStart(0);
				tx.setSelectionEnd(tx.getText().length());
			}	
		}
		
	}
	
	
	public void clean(){
		this._x_cuenta="";
		this._x_tc="";
		this._x_idcomprobante="";
		frame.get_txt_fecha_contable().setText("");
		frame.get_txt_fecha_factura().setText("");
		frame.get_txt_numero().setText("");
		
		frame.get_txt_sucursal().setText("");
		frame.get_txt_total().setText("");
		//frame.get_btn_grabar().setEnabled(false);
		frame.get_txt_sucursal().setEnabled(false);
		frame.get_txt_numero().setEnabled(false);
		frame.get_list_letra().setEnabled(false);
		frame.get_btn_eliminar().setEnabled(false);
		this.clean_proveedor();
		frame.get_txt_idproveedor().requestFocusInWindow();
		this.cleanComponents();
		frame.setJTable(null);
		frame.get_btn_grabar().setEnabled(false);
		
		frame.get_txt_cai().setText("");
		frame.get_txt_cai().setEnabled(true);
		
		frame.get_txt_cai_vencimiento().setText("");
		frame.get_txt_cai_vencimiento().setEnabled(true);
	}
	
	
	private void clean_proveedor(){
		frame.get_btn_editar_proveedor().setEnabled(false);
		frame.get_btn_buscar_proveedor().setEnabled(true);
		frame.get_txt_proveedor_descripcion().setText("");
		
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_idproveedor().setEditable(true);
		frame.get_txt_idproveedor().setEnabled(true);
		frame.get_txt_condicion_compra().setText("");
		frame.get_txt_condicion_compra().setEditable(false);
		frame.get_txt_condicion_compra_detalle().setText("");
		frame.get_txt_condicion_iva().setText("");
		frame.get_txt_cuit().setText("");
		frame.get_txt_domicilio().setText("");
		frame.get_txt_imputacion_detalle().setText("");
		try {
		frame.get_list_imputacion().removeAllItems();
		}catch(Exception e){
			
		}
	}
	
	public void _evaluar_sucursal(JTextField tx){
		String sucursal=tx.getText();
		boolean ok=this._evaluar_sucursal(sucursal);
		if (ok){
			frame.get_txt_numero().requestFocusInWindow();
		}else {
			error("Error en numero de sucursal");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}
	
	
	
	private boolean _evaluar_codigo_proveedor(String idproveedor){
		boolean ok=false;
		if (idproveedor.startsWith("21101")){
			Object[][] results=data.getProveedor(idproveedor);
			if (results!=null){
				if (results.length>0){
					ok=true;
				}
			}
		}
		return ok;
	}
	
	
	public void cargarProveedor(String idproveedor){
		frame.get_btn_editar_proveedor().setEnabled(true);
				if (this.configurado(idproveedor)){
					this._cargar_proveedor(idproveedor);	
						
				}else {
					error("El proveedor no esta configurado. debe configurar para poder cargar una factura");
					frame.get_txt_idproveedor().requestFocusInWindow();
				}
		
		
	}
	
	public void cancelar(){
		if (preguntar("Confirmar","Cancelar la carga del comprobante?")){
			clean();
		}
	}
	
	public void exit(){
		if (preguntar("Confirmar","Cierra la aplicacion?")){
			this.getConstructor().dispose();
		}
	}
	
	public void _recargar_proveedor(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		if (idproveedor.compareTo("")!=0){
			this._cargar_proveedor(idproveedor);	
		}
		
	}
		
	public void _cargar_proveedor(String idproveedor){
		Object[][] results=data.getProveedor(idproveedor);
		if (results!=null){
			if (results.length>0){
				frame.get_txt_idproveedor().setEnabled(false);
				frame.get_btn_editar_proveedor().setEnabled(true);
				frame.get_btn_buscar_proveedor().setEnabled(false);
				String codigo=(String) results[0][0];
				String descripcion=(String) results[0][1];
				String domicilio=(String) results[0][2];
				String condicion_iva=(String) results[0][3];
				String condicion_iva_detalle=(String) results[0][4];
				String cuit=(String) results[0][5];
				String condicion_compra=(String) results[0][6];
				String condicion_compra_detalle=(String) results[0][7];
				
				//a.requiere_iibb,a.requiere_perc_iva,a.requiere_perc_gan,requiere_neto_ngrav,requiere_iva10,
				//requiere_iva27
				String iibb=(String) results[0][8];
				String iva=(String) results[0][9];
				String gan=(String) results[0][10];
				String neto=(String) results[0][11];
				String iva10=(String) results[0][12];
				String iva27=(String) results[0][13];
				String iva21=(String) results[0][17];
				String articulos=(String) results[0][18];
				String rg3337=(String) results[0][19];
				String impuestos_internos=(String) results[0][20];
				String alicuota_impuestos_internos=(String) results[0][21];
				String alicuota_ingresos_brutos=(String) results[0][22];
				String alicuota_percepcion_iva=(String) results[0][23];
				String tc=(String) results[0][24];
				try {
				this.requiere_percepcion_ingresos_brutos=new Integer(iibb);
				this.requiere_percepcion_iva=new Integer(iva);
				this.requiere_percepcion_ganancias=new Integer(gan);
				this.requiere_neto_no_grabado=new Integer(neto);
				this.requiere_iva10=new Integer(iva10);
				this.requiere_iva27=new Integer(iva27);
				this.requiere_iva21=new Integer(iva21);
				this.permite_articulos=new Integer(articulos);
				this.requiere_impuestos_internos=new Integer(impuestos_internos);
				this.requiere_rg3337=new Integer(rg3337);
				this.tipo_tc=new Integer(tc);
				}catch(Exception e){
					
				}
				try {
				this.alic_iibb=new Double(alicuota_ingresos_brutos);	
				}catch(Exception e){
					
				}
				try {
				this.alic_impuestos_internos=new Double(alicuota_impuestos_internos);	
				}catch(Exception e){
					
				}
				try {
				this.alic_perc_iva=new Double(alicuota_percepcion_iva);	
				}catch(Exception e){
					
				}
				try {
					this.alic_perc_iva=new Double(alicuota_percepcion_iva);	
				}catch(Exception e){
						
				}
				frame.get_chk_publica().setSelected(permite_articulos==1);
				frame.get_txt_proveedor_descripcion().setText(descripcion);
				frame.get_txt_condicion_compra().setText(condicion_compra);
				frame.get_txt_condicion_compra_detalle().setText(condicion_compra_detalle);
				frame.get_txt_domicilio().setText(domicilio);
				frame.get_txt_cuit().setText(cuit);
				frame.get_txt_condicion_iva().setText(condicion_iva_detalle);
				frame.get_txt_sucursal().setEnabled(true);
				frame.get_txt_numero().setEnabled(true);
				frame.get_list_letra().setEnabled(true);
				frame.get_txt_cai().setEnabled(true);
				frame.get_txt_cai_vencimiento().setEnabled(true);
				frame.get_txt_cai().requestFocusInWindow();
								
				_fill_Imputaciones();
				/*
				
				*/
			}
		}
	}
	
	
	public void addFoto(BufferedImage img){
		System.out.println("addfoto");
		
		aplicacion.herramientas.java.image.constructor._Constructor C=
			new aplicacion.herramientas.java.image.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.build(this.getConstructor());
		C.setShowOnStartup(false);
		C.init();
		aplicacion.herramientas.java.image.logic._Logic logic=
				(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
			
		logic.setImage(img);
		logic.setScalex(80);
		logic.setScaley(100);
		logic.setEliminar(true);
		
		int c=frame.getJPanel4().getComponentCount()-2;
		
        Component cx=logic.getImagePanel();
        cx.setBounds(c*90+50,30,80,100);
        if (javax.swing.SwingUtilities.isEventDispatchThread()){
        	frame.getJPanel4().add(cx);
        	frame.getJPanel4().validate();
            frame.getJPanel4().revalidate();
            frame.getJPanel4().repaint();
            
        }else{
        	final Component _cx=cx;
        	Runnable runnable=new Runnable(){
        		public void run(){
        			frame.getJPanel4().add(_cx);
        			frame.getJPanel4().validate();
        	        frame.getJPanel4().revalidate();
        	        frame.getJPanel4().repaint();
        	        
        		}
        	};
        	javax.swing.SwingUtilities.invokeLater(runnable);
        }
        
        
	}
	
	
	
	
	public void addFoto(String path){
		System.out.println("addfoto");
		aplicacion.herramientas.java.image.constructor._Constructor C=
		new aplicacion.herramientas.java.image.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		C.setShowOnStartup(false);
		C.build(this.getConstructor());
		C.init();
		
		aplicacion.herramientas.java.image.logic._Logic logic=
			(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
		
		//In response to a button click:
			
			logic.setFileName(path);
			logic.setEliminar(true);
			logic.loadImage();
			logic.setScalex(80);
			logic.setScaley(100);
			int c=frame.getJPanel4().getComponentCount();
			int comps=0;
			for (int i=0;i<c;i++){
				if (frame.getJPanel4().getComponent(i) instanceof ImageComponent){
					comps++;
				}
			}
			
	        Component cx=logic.getImagePanel();
	        cx.setBounds(comps*90+50,30,80,100);
	        frame.getJPanel4().add(cx);
	        frame.getJPanel4().validate();
	        frame.getJPanel4().revalidate();
	        frame.getJPanel4().repaint();
	        this.sortComponents();
		
		
	}
	public void addFoto(){
		System.out.println("addfoto");
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.addFoto(fc.getSelectedFile().getAbsolutePath());
		}
	}
	
	private boolean existe_cpte(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante=sucursal+numero+letra;
		boolean exist=false;
		Object[][] results=data.existe_cpte(idproveedor, this.getTipoTC(),idcomprobante);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public void _fill_Imputaciones(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		Object[][] results=data.getImputacionCodigos(idproveedor);
		if (results!=null){
			if (results.length>0)
				frame.get_list_imputacion().removeAllItems();
				for (int i=0;i<results.length;i++){
					String imputacion=(String) results[i][0];
					if (imputacion!=null & frame.get_list_imputacion()!=null){
						frame.get_list_imputacion().addItem(imputacion);	
					}
				}
			}
		}
	
	public void update_tc(JComboBox C){
		if (C.getSelectedIndex()==1){
			//aviso("Cuando es nota de credito se deshabilita la carga de articulos. Puede habilitarla de ser necesario");
			frame.get_chk_publica().setSelected(false);
			
		}else{
			if (this.permite_articulos==1){
				frame.get_chk_publica().setSelected(true);	
			}
		}
	}
	
	
	public void _evaluar_tc(JComboBox lst){
		frame.get_txt_sucursal().requestFocusInWindow();
	}
	
	
	public void _update_imputacion(JComboBox C){
		if (C!=null){
			if (C.getSelectedIndex()>=0){
				String cuenta=C.getSelectedItem().toString();
				Object[][] results=data.getCuentaDescripcion(cuenta);
				if (results!=null){
					if (results.length>0){
						String descripcion=(String) results[0][0];
						frame.get_txt_imputacion_detalle().setText(descripcion);
					}
				}	
			}
				
		}
		
	}
	public void editar(String cuenta,String tc,String idcomprobante){
		
		String sucursal=idcomprobante.substring(0,4);
		String numero=idcomprobante.substring(4,12);
		String letra=idcomprobante.substring(12,13);
		frame.get_txt_idproveedor().setText(cuenta);
		frame.get_txt_sucursal().setText(sucursal);
		frame.get_txt_numero().setText(numero);
		if (letra.compareTo("A")==0){
			frame.get_list_letra().setSelectedIndex(0);	
		}
		if (tc.compareTo("NCC")==0){
			frame.get_list_tc().setSelectedIndex(1);
		}
		if (tc.compareTo("FCC")==0){
			frame.get_list_tc().setSelectedIndex(0);
		}
		if (tc.compareTo("RMC")==0){
			frame.get_list_tc().setSelectedIndex(2);
		}
		if (tc.compareTo("FCN")==0){
			frame.get_list_tc().setSelectedIndex(3);
		}
		this._cargar_proveedor(cuenta);
		this.editar();
		
	}
	public void editar(){
		//guardar las variables unicas
		
		String tc=this.getTipoTC();
		String idproveedor=frame.get_txt_idproveedor().getText();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante=sucursal+numero+letra;
		this._x_cuenta=idproveedor;
		this._x_tc=tc;
		this._x_idcomprobante=idcomprobante;
		frame.get_btn_eliminar().setEnabled(true);
		frame.get_txt_fecha_contable().setEditable(true);
		frame.get_txt_fecha_factura().setEditable(true);
		
		Object[][] results=data.getAsiento(idproveedor, tc, sucursal, numero, letra);
		boolean asiento=false;
		if (results!=null){
			if (results.length>0){
				this._cargar_comprobante();
				this.load_fecha_contable();
				this.load_impuestos(idproveedor, tc, idcomprobante);
				this.cargar_fotos();
				asiento=true;
			}
		}
		if (!asiento){
			String fecha=data.getFecha(idproveedor, tc, sucursal, numero, letra);
			frame.get_txt_sucursal().setEnabled(false);
			frame.get_txt_numero().setEnabled(false);
			frame.get_list_letra().setEnabled(false);
			frame.get_txt_fecha_factura().setText(fecha);
			frame.get_txt_fecha_factura().setEditable(true);
			frame.get_txt_fecha_factura().setEditable(true);
			frame.get_txt_fecha_factura().requestFocusInWindow();
		}
	}
	
	
	public void _evaluar_letra(JComboBox combo){
		
		//if (combo.getSelectedItem().toString().compareTo("A")==0){
			
			if (this.existe_cpte()){
				if (preguntar("Confirmar","Este comprobante ya fue cargado. Desea Editarlo?")){
					frame.get_txt_sucursal().setEnabled(false);
					frame.get_txt_numero().setEnabled(false);
					frame.get_list_letra().setEnabled(false);
					editar();
					//frame.get_txt_idproveedor().setEditable(true);
					//frame.get_btn_buscar_proveedor().setEnabled(true);
				}else {
					frame.get_txt_numero().requestFocusInWindow();
				}
			}else{
				
				frame.get_txt_sucursal().setEnabled(false);
				frame.get_txt_numero().setEnabled(false);
				frame.get_list_letra().setEnabled(false);
				frame.get_txt_fecha_factura().setEditable(true);
				frame.get_txt_fecha_factura().setEditable(true);
				frame.get_txt_fecha_factura().requestFocusInWindow();
					
			}
			
//			frame.get_btn_eliminar().setEnabled(true);	
		
	
		
	}
	
	
	public void _cargar_comprobante(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante=sucursal+numero+letra;
		String tc=this.getTipoTC();
		
		Convertidor C=new Convertidor();
		String[] parameters=new String[] {
				idproveedor,
				tc,
				idcomprobante
		};
		Object[][] results=data.getEncabezado(idproveedor,tc, idcomprobante);
		if (results!=null){
			if (results.length>0){
				String fecha=(String) results[0][0];
				String importe=(String) results[0][1];
				String importe_s_iva=(String) results[0][2];
				String importeiva=(String) results[0][3];
				String articulos=(String) results[0][4];
				frame.get_txt_fecha_factura().setText(fecha);
				frame.get_txt_total().setText(C.getMoney(importe, 2));
				if (articulos.compareTo("1")==0){
					frame.get_chk_publica().setSelected(true);
				}else{
					frame.get_chk_publica().setSelected(false);
				}
			}
		}
	}
	
	private void selectImputacion(String imputacion){
		for (int i=0;i<frame.get_list_imputacion().getItemCount();i++){
			if (frame.get_list_imputacion().getItemAt(i).toString().compareTo(imputacion)==0){
				frame.get_list_imputacion().setSelectedIndex(i);
				
			}
		}
		
	}
	
	private void load_impuestos(String idproveedor,String tc,String idcomprobante){
		Object[][] results=data.getImpuestos(idproveedor, tc, idcomprobante);
		if (results!=null){
			if (results.length>0){
				this.crear_asiento();
				
				
				for (int i=0;i<results.length;i++){
					String _secuencia=(String) results[i][0];
					String _cuenta=(String) results[i][1];
					String _importe=(String) results[i][2];
					int sec=-1;
					double importe=0.0;
					try{
						sec=new Integer(_secuencia);
						importe=new Double(_importe.replaceAll(",", ""));
					}catch(Exception e){
						
					}
					if (i>0){
						if (sec>=0){
							sec--;
							Convertidor C=new Convertidor();
							frame.getJTable().setValueAt(C.getMoney(importe, 2), sec, 2);
							if (sec==1 | sec== 8 | sec ==9 | sec==10){
								frame.getJTable().setValueAt(_cuenta, sec, 0);	
							}
						}
							
					}
					
				}
				String _imputacion=(String) results[1][1];
				System.out.println("select imputacion "+_imputacion);
				this.selectImputacion(_imputacion);
			}
		}
	}
	
	public void _evaluar_numero(JTextField tx){
		String numero=tx.getText();
		boolean ok=this._evaluar_numero(numero);
		if (ok){
			frame.get_list_letra().requestFocusInWindow();
		}else {
			error("Error en numero de comprobante");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}
	private boolean _evaluar_sucursal(String s){
		boolean ok=true;
		int suc=-1;
		String aux="";
		
		if (s.compareTo("")==0){
			s="1";
		}
		try {
			suc=new Integer(s);
			
			aux=""+suc;
			while (aux.length()<4){
				aux="0"+aux;
			}
			
		}catch(Exception e){
			ok=false;
			
		}
		if (ok){
			this.frame.get_txt_sucursal().setText(aux);
		}
		return ok;
	}
	
	public void editar_proveedor(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		aplicacion.proveedor.archivo.constructor._Constructor CC=new aplicacion.proveedor.archivo.constructor._Constructor();
		CC.setParameter(_parametros.connector, data.getConnectionHandler());
		CC.setParameter(_Parametros.idproveedor, idproveedor);
		CC.build(this.getConstructor());
		CC.init();
	}
	
	private boolean _evaluar_numero(String s){
		boolean ok=true;
		int num=-1;
		String aux="";
		
		if (s.compareTo("")==0){
			s="1";
		}
		try {
			num=new Integer(s);
			
			aux=""+num;
			while (aux.length()<8){
				aux="0"+aux;
			}
			
		}catch(Exception e){
			ok=false;
			
		}
		if (ok){
			this.frame.get_txt_numero().setText(aux);
		}
		return ok;
	}
	
	private boolean _grabar_asiento(){
		boolean ok=false;
		int i=frame.get_list_tc().getSelectedIndex();
		if (i==0){
			
			ok=this._grabar_asiento_fcc();
		}
		if (i==1){
			ok=this._grabar_asiento_ncc();
		}
		if (i==2){
			ok=true;
		}
		return ok;
	}
	
	private boolean _grabar_asiento_fcc(){
		System.out.println("grabar asiento fcc");
		Asiento A=null;
		Renglon r=null;
		String idcajas="   2";
		Object[][] info=data.getAcountInfo(frame.get_txt_idproveedor().getText());
		String iva="";
		if (info!=null){
			if (info.length>0){
						iva=(String) info[0][8];
			}
		}
		
		A=new Asiento(this.getConstructor().getConnectionHandler());
		A.setDetalle(frame.get_txt_proveedor_descripcion().getText());
		A.setTc(this.getTipoTC());
		A.setSucursal(frame.get_txt_sucursal().getText());
		A.setNumero(frame.get_txt_numero().getText());
		A.setLetra(frame.get_list_letra().getSelectedItem().toString());
		A.setFecha(frame.get_txt_fecha_contable().getText());
		A.setSubtotal(this.get_subtotal());
		if (frame.get_txt_cai().getText().compareTo("")!=0){
			A.setNrocai(frame.get_txt_cai().getText());
			A.setFhvtocai(frame.get_txt_cai_vencimiento().getText());	
		}else{
			A.setNrocai("");
			A.setFhvtocai("");
		}
		A.setNrocai("");
		A.setFhvtocai("");
		A.setId_cotiz("0");
		A.setCotizacion("1.0");
		A.setFechasubdiario(frame.get_txt_fecha_contable().getText());
		A.setUnegocio("   1");
		A.setIdmotivo("   1");
		r=new Renglon();
		r.setCuenta(frame.get_txt_idproveedor().getText());
		r.setCabcuenta(frame.get_txt_idproveedor().getText());
		System.out.println(">>FCC> condicion de iva? "+iva);
		r.setCabcondiva(iva);
		r.setLiva_aliciva("0.0");
		r.setLiva_aliciva2("0.0");
		r.setLiva_aliciva3("0.0");
		
		r.setCabcuit(frame.get_txt_cuit().getText());
		r.setCabnombre(frame.get_txt_proveedor_descripcion().getText());
		
			r.setLiva_impnetongrav(""+this._get_importe_row(_Interface.row_importe_neto_no_gravado));;
			r.setLiva_impnetograv(""+this._get_importe_row(_Interface.row_subtotal));;
			
			r.setLiva_impiva(""+this._get_importe_row(_Interface.row_iva));
			if (this._get_importe_row(_Interface.row_iva)>0){
				r.setLiva_aliciva("21");
			}else{
				r.setLiva_aliciva("0.0");
			}
			r.setLiva_impiva2(""+this._get_importe_row(_Interface.row_iva10));
			if (this._get_importe_row(_Interface.row_iva10)>0){
				r.setLiva_aliciva2("10.5");		
			}else {
				r.setLiva_aliciva2("0.0");
			}
			
			r.setLiva_impiva3(""+this._get_importe_row(_Interface.row_iva27));
			if (this._get_importe_row(_Interface.row_iva27)>0){
				r.setLiva_aliciva3("27");
			}else{
				r.setLiva_aliciva3("0.0");
			}
			r.setLiva_exento(""+_get_importe_row(_Interface.row_importe_neto_no_gravado));
			r.setLiva_ret_ganancias(""+this._get_importe_row(_Interface.row_retencion_ganancias));
			r.setLiva_ret_ibtos(""+this._get_importe_row(_Interface.row_percepcion_iibb));
			r.setLiva_ret_perc(""+this._get_importe_row(_Interface.row_retencion_iva));
			r.setLiva_total(""+this.get_total());	
			r.setLiva_tipo("COMPRAS");	
		
		double total=0.0;
		try {
			total=new Double(frame.get_txt_total().getText().replaceAll(",", ""));
		}catch(Exception e){
			
		}
		
		r.setImporte(""+total);
		r.setDebe_haber("H");
		r.setIdcajas(idcajas);
		A.addRenglon(r);
		
		r=new Renglon();
		
		r.setCuenta(this._get_cuenta_row(_Interface.row_subtotal));
		r.setImporte(""+this._get_importe_row(_Interface.row_subtotal));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva));
		r.setImporte(""+this._get_importe_row(_Interface.row_iva));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva10));
		r.setImporte(""+this._get_importe_row(_Interface.row_iva10));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva27));
		r.setImporte(""+this._get_importe_row(_Interface.row_iva27));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_retencion_iva));
		r.setImporte(""+this._get_importe_row(_Interface.row_retencion_iva));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_rg3337));
		r.setImporte(""+this._get_importe_row(_Interface.row_rg3337));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_percepcion_iibb));
		r.setImporte(""+this._get_importe_row(_Interface.row_percepcion_iibb));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_retencion_ganancias));
		r.setImporte(""+this._get_importe_row(_Interface.row_retencion_ganancias));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_importe_neto_no_gravado));
		r.setImporte(""+this._get_importe_row(_Interface.row_importe_neto_no_gravado));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_impuestos_internos));
		r.setImporte(""+this._get_importe_row(_Interface.row_impuestos_internos));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_ajuste_por_redondeo));
		r.setImporte(""+this._get_importe_row(_Interface.row_ajuste_por_redondeo));
		r.setIdcajas(idcajas);
		r.setDebe_haber("D");
		A.addRenglon(r);
		
		boolean ok=A.grabar();
		return ok;
	}
	
	private boolean _grabar_asiento_ncc(){
		Asiento A=null;
		Renglon r=null;
		String idcajas="   2";
		A=new Asiento(this.getConstructor().getConnectionHandler());
		String iva="";
		Object[][] info=data.getAcountInfo(frame.get_txt_idproveedor().getText());
		if (info!=null){
			if (info.length>0){
						iva=(String) info[0][8];
			}
		}
		A.setDetalle(frame.get_txt_proveedor_descripcion().getText());
		A.setTc(this.getTipoTC());
		A.setSucursal(frame.get_txt_sucursal().getText());
		A.setNumero(frame.get_txt_numero().getText());
		A.setLetra(frame.get_list_letra().getSelectedItem().toString());
		A.setFecha(frame.get_txt_fecha_contable().getText());
		A.setSubtotal(this.get_subtotal());
		
		if (frame.get_txt_cai().getText().compareTo("")!=0){
			A.setNrocai(frame.get_txt_cai().getText());
			A.setFhvtocai(frame.get_txt_cai_vencimiento().getText());	
		}else{
			A.setNrocai("");
			A.setFhvtocai("");
		}
		A.setNrocai("");
		A.setFhvtocai("");
		r=new Renglon();
		r.setCuenta(frame.get_txt_idproveedor().getText());
		r.setCabcuenta(frame.get_txt_idproveedor().getText());
		r.setCabcuit(frame.get_txt_cuit().getText());
		System.out.println(">>NCC> condicion de iva? "+iva);
		r.setCabcondiva(iva);
		r.setCabnombre(frame.get_txt_proveedor_descripcion().getText());
		
		r.setLiva_impnetongrav(""+-this._get_importe_row(_Interface.row_importe_neto_no_gravado));;
		r.setLiva_impnetograv(""+-this._get_importe_row(_Interface.row_subtotal));;
		r.setLiva_impiva(""+-this._get_importe_row(_Interface.row_iva));
		r.setLiva_impiva2(""+-this._get_importe_row(_Interface.row_iva10));
		r.setLiva_impiva3(""+-this._get_importe_row(_Interface.row_iva27));
		r.setLiva_ret_ganancias(""+-this._get_importe_row(_Interface.row_retencion_ganancias));
		r.setLiva_ret_ibtos(""+-this._get_importe_row(_Interface.row_percepcion_iibb));
		r.setLiva_ret_perc(""+-this._get_importe_row(_Interface.row_retencion_iva));
		r.setLiva_total(""+-this.get_total());	
		r.setLiva_tipo("COMPRAS");	
		
		double total=0.0;
		try {
			total=new Double(frame.get_txt_total().getText().replaceAll(",", ""));
		}catch(Exception e){
			
		}
		r.setImporte(""+total);
		r.setDebe_haber("D");
		r.setIdcajas(idcajas);
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_subtotal));
		r.setImporte(""+this._get_importe_row(_Interface.row_subtotal));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva));
		r.setImporte(""+this._get_importe_row(_Interface.row_iva));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva10));
		r.setImporte(""+this._get_importe_row(_Interface.row_iva10));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_iva27));
		r.setImporte(""+this._get_importe_row(_Interface.row_iva27));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_retencion_iva));
		r.setImporte(""+this._get_importe_row(_Interface.row_retencion_iva));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_rg3337));
		r.setImporte(""+this._get_importe_row(_Interface.row_rg3337));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_percepcion_iibb));
		r.setImporte(""+this._get_importe_row(_Interface.row_percepcion_iibb));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_retencion_ganancias));
		r.setImporte(""+this._get_importe_row(_Interface.row_retencion_ganancias));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_importe_neto_no_gravado));
		r.setImporte(""+this._get_importe_row(_Interface.row_importe_neto_no_gravado));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_impuestos_internos));
		r.setImporte(""+this._get_importe_row(_Interface.row_impuestos_internos));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		r=new Renglon();
		r.setCuenta(this._get_cuenta_row(_Interface.row_ajuste_por_redondeo));
		r.setImporte(""+this._get_importe_row(_Interface.row_ajuste_por_redondeo));
		r.setIdcajas(idcajas);
		r.setDebe_haber("H");
		A.addRenglon(r);
		
		boolean ok=A.grabar();
		return ok;
	}
	
	private void crear_asiento(){
		frame.get_btn_grabar().setEnabled(true);
		String idproveedor=frame.get_txt_idproveedor().getText();
		String proveedor_descripcion=frame.get_txt_proveedor_descripcion().getText();
		Object[][] tmp=null;
		Object[][] results=null;
		
		String imputacion="11301";
		boolean error=false;
		if (frame.get_list_imputacion().getItemCount()>0){
			try {
				imputacion=frame.get_list_imputacion().getSelectedItem().toString();
			}catch(Exception e){
			error=true;	
			}
		}
		if (error){
			aviso("NO SE CONFIGURA UNA CUENTA DE IMPUTACION PARA EL PROVEEDOR. SE UTLIZA 13101 POR DEFECTO");
		}
		results=new Object[][]{
				{imputacion,"Subtotal"},
				{"11203","Iva   21%"},
				{"11261","Iva 10.5%"},
				{"11262","Iva   27%"},
				{"11263","Retencion Iva "},
				{"11208","RG 3337"},
				{"11209","Percepcion Ingresos Brutos "},
				{"11210","Retencion Ganancias "},
				{imputacion,"Importe Neto No Gravado "},
				{imputacion,"Impuestos Internos "},
				{imputacion,"Ajuste por redondeo "},
				
		};
		
		if (results!=null){
			if (results.length>0){
				tmp=new Object[results.length][3];
				for (int i=0;i<results.length;i++){
					String _imputacion=(String) results[i][0];
					String imputacion_alias=(String) results[i][1];
					Object[] tmpx=new Object[]{
							_imputacion,
							imputacion_alias,
							"0.0"
					};
					tmp[i]=tmpx;
				}
				
			}
		}
		this.create_table(tmp);
		
	}
	
	public void _facturador(){
		boolean ok=false;
		if (!this._tiene_imagenes()){
			aviso("No puede grabar una factura sin asociar una imagen!");
			
		}else {
			if (this.get_subtotal()>0){
				facturar();	
			}else {
				aviso("El subtotal no puede ser nulo");	
			}
			
		}
		
	}
	
	private void facturar(){
		if (C==null){
			C=new _Confirmacion();
		}
		C.get_txt_idproveedor().setText(frame.get_txt_idproveedor().getText());
		C.get_txt_descripcion_proveedor().setText(frame.get_txt_proveedor_descripcion().getText());
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante=sucursal+numero+letra;
		C.get_txt_idcomprobante().setText(idcomprobante);
		C.get_txt_total().setText(frame.get_txt_total().getText());
		C.get_txt_fecha().setText(frame.get_txt_fecha_factura().getText());
		C.get_txt_fecha_contable().setText(frame.get_txt_fecha_contable().getText());
		
		int rnd=this.getRandomNumber(3);
		C.get_txt_confirmacion().setText(""+rnd);
		_Constructor Cx=(_Constructor)this.getConstructor();
		
		C.get_btn_grabar().addActionListener(Cx.getActionListener());
		C.get_btn_grabar().setActionCommand(_Interface._btn_grabar_facturador);
		C.get_txt_usuario().addKeyListener(Cx.getKeyListener());
		C.get_txt_usuario().setName(_Interface._txt_usuario);
		C.setVisible(true);
		C.setResizable(false);
		C.get_txt_usuario().requestFocusInWindow();
		C.setLocation(200,200);
		if (frame.get_chk_publica().isSelected()){
			C.get_txt_detalle().setText("CON ARTICULOS");
			C.get_txt_detalle().setForeground(Color.orange);
		}else{
			C.get_txt_detalle().setForeground(Color.white);
			C.get_txt_detalle().setText("SIN ARTICULOS");
		}
	}
	
	public void _evaluar_confirmacion(JTextField tx,KeyEvent event){
		
		String request=C.get_txt_confirmacion().getText();
		if (request.compareTo("")!=0){
			String tmp=((Character)event.getKeyChar()).toString();
			
			if (event.getKeyCode()==KeyEvent.VK_ENTER){
				if (request.compareTo(tx.getText())==0){
					this.guardar();
					
				}
			}else{
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					C.setVisible(false);
					C.dispose();
					C=null;
				}else{
					if (request.compareTo(tx.getText()+tmp)==0){
						C.get_btn_grabar().setEnabled(true);
					}else {
						C.get_btn_grabar().setEnabled(false);
					}	
				}
				
			}	
		}
		
		
	}
	
	private void create_table(Object[][] results) {
		_Constructor constructor=(_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();

		Column col = new Column();
		
		col = new Column();
		col.setName("Cuenta");
		col.setWidth(120);
		col.setEditable(true);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Descripcion");
		col.setWidth(300);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("Importe");
		col.setWidth(120);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		
		CellEditor pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_importe);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		
		table.addColumn(col);

		table.setSortable(false);
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		
		JTable _table = table.getTable();
		
		

		frame.setJTable(table.getTable());
	}




	public boolean _evaluate_importe(JTextField tx,int row){
		String impx=tx.getText();
		impx=impx.replaceAll(",", "");
		boolean error=false;
		double imp=0.0;
		try {
			imp=new Double(impx);
		}catch(Exception e){
			error=true;
			e.printStackTrace();
		}
		
		if (imp<0){
			error=true;
		}
		
		return !error;
	}
	
	public double _get_importe(JTextField tx){
		String impx=tx.getText();
		impx=impx.replaceAll(",", "");
		double imp=this._get_importe(impx);
		return imp;
	}
	
	private void _do_automatic_calc_percepcion_iibb(double impx,JTextField tx){
		if (impx>this.base_iibb){
			impx=impx*this.alic_iibb;
		}
		Convertidor C=new Convertidor();
		String importe=C.getMoney(impx, 2);
		if (tx!=null){
			tx.setText(importe);
		}else {
		frame.getJTable().setValueAt(importe, _Interface.row_percepcion_iibb, 2);
		}
	}
	
	private void _do_automatic_calc_iva27(double impx,JTextField tx){
		impx=impx*this.alic_iva3;
		Convertidor C=new Convertidor();
		String importe=C.getMoney(impx, 2);
		if (tx!=null){
			tx.setText(importe);
		}else {
		frame.getJTable().setValueAt(importe, _Interface.row_iva27, 2);
		}
	}
	private void _do_automatic_calc_ganacias(double impx,JTextField tx){
		impx=impx*1;//alicuota percepcion ganancias
		Convertidor C=new Convertidor();
		String importe=C.getMoney(impx, 2);
		if (tx!=null){
			tx.setText(importe);
		}else {
		frame.getJTable().setValueAt(importe, _Interface.row_retencion_ganancias, 2);
		}
	}
	
	private void _do_automatic_calc_impuestos_internos(double impx,JTextField tx){
		impx=impx*this.alic_impuestos_internos;
		Convertidor C=new Convertidor();
		String importe=C.getMoney(impx, 2);
		if (tx!=null){
			tx.setText(importe);
		}else {
		frame.getJTable().setValueAt(importe, _Interface.row_impuestos_internos, 2);
		}
	}
	
	private void _do_automatic_calc_percepcion_iva(double impx,JTextField tx){
		impx=impx*this.alic_perc_iva;
		Convertidor C=new Convertidor();
		String importe=C.getMoney(impx, 2);
		if (tx!=null){
			tx.setText(importe);
		}else {
			frame.getJTable().setValueAt(importe, _Interface.row_retencion_iva, 2);	
		}
		
	}
	
	private void _do_automatic_calc_iva(double impx,JTextField tx){
		impx=impx*this.alic_iva1;
		Convertidor C=new Convertidor();
		String importe=C.getMoney(impx, 2);
		if (tx!=null){
			tx.setText(importe);
		}else {
		frame.getJTable().setValueAt(importe, _Interface.row_iva, 2);
		}
	}
	
	private void _do_automatic_calc_iva10(double impx,JTextField tx){
		impx=impx*this.alic_iva2;
		Convertidor C=new Convertidor();
		String importe=C.getMoney(impx, 2);
		if (tx!=null){
			tx.setText(importe);
		}else {
		frame.getJTable().setValueAt(importe, _Interface.row_iva10, 2);
		}
	}
	
	private void _do_automatic_calc_rg3337(double impx,JTextField tx){
		impx=impx*this.alic_rg3337;
		Convertidor C=new Convertidor();
		String importe=C.getMoney(impx, 2);
		if (tx!=null){
			tx.setText(importe);
		}else {
		frame.getJTable().setValueAt(importe, _Interface.row_rg3337, 2);
		}

	}
	private void _do_automatic_calcs(double imp){
		if (this.requiere_percepcion_ingresos_brutos==1){
			this._do_automatic_calc_percepcion_iibb(imp,null);
		}
		if (this.requiere_iva27==1){
			this._do_automatic_calc_iva27(imp,null);
		}
		if (this.requiere_percepcion_ganancias==1){
			this._do_automatic_calc_ganacias(imp,null);
		}
		if (this.requiere_impuestos_internos==1){
			this._do_automatic_calc_impuestos_internos(imp,null);
		}
		if (this.requiere_percepcion_iva==1){
			this._do_automatic_calc_percepcion_iva(imp,null);
		}
		
		if (this.requiere_iva21==1){
			this._do_automatic_calc_iva(imp,null);
		}
		if (this.requiere_iva10==1){
			this._do_automatic_calc_iva10(imp,null);
		}
		if (this.requiere_rg3337==1){
			this._do_automatic_calc_rg3337(imp,null);
		}
		
	}
	
	public boolean _evaluate_subtotal(JTextField tx,int row){
		boolean transfer=true;
		if (_get_importe(tx)>0){
			
			
			this._do_automatic_calcs(_get_importe(tx));
			frame.getJTable().changeSelection(row+1, 2, false,false);
			frame.getJTable().editCellAt(row+1,2);
			frame.getJTable().transferFocus();
		}else {
			transfer=false;
			error("El subtotal no puede ser nulo");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
		return transfer;
	}
	
	private double get_subtotal(){
		double imp=0.0;
		try{
			String importe=frame.getJTable().getValueAt(0,2).toString();
			importe=importe.replace(",", "");
			imp=new Double(importe);
		}catch(Exception e){
			
		}
		return imp;
	}
	private double get_total(){
		
		double imp=0.0;
		try{
			String importe=frame.get_txt_total().getText();
			importe=importe.replace(",", "");
			imp=new Double(importe);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("get total para asiento? "+imp);
		return imp;
	}
	public boolean _evaluar_override_restriccion_impuesto(JTextField tx,int row){
		boolean ok=false;
		if (this._get_importe(tx)>0){
			aviso("Este impuesto no debe cargarse para este proveedor");
			if (preguntar("Confirmar","Carga este impuesto de todas formas?")){
				ok=true;
			}
		}else{
			ok=true;
		}
		return ok;
	}
	
	public boolean _evaluate_impuesto(JTextField tx,int row,int activo,String impuesto){
		boolean transfer_focus=false;
		boolean automatic_calc=false;
		double imp=this._get_importe(tx);
			if (activo==1){
				if (imp<=0){
					aviso("Este proveedor requiere el impuesto "+impuesto+" en la carga");
					if (this.preguntar("Confirmar","Evita la carga de este impuesto?")){
						transfer_focus=true;
					}else {
						automatic_calc=true;
					}
				}else {
					transfer_focus=true;
				}
			}else {
				if (imp>0){
					
					if (activo==0){
						aviso("Este proveedor no requiere el impuesto "+impuesto+" en la carga");
						if (this.preguntar("Confirmar","Confirma la carga de este impuesto?")){
							transfer_focus=true;
						}
					}
					if (activo==2){
						transfer_focus=true;
					}
					
				}else{
					if (activo==2){
						aviso("Revise el comprobante por la existencia de este impuesto");
						if (this.preguntar("Confirmar","Evita la carga de este impuesto?")){
							transfer_focus=true;
						}else {
							automatic_calc=true;
							transfer_focus=false;
						}
					}else {
						transfer_focus=true;	
					}
					
				}
			}
		
			if (automatic_calc){
				_automatic_calc(tx,row);
			}
			
		return transfer_focus;

	}
	private void _automatic_calc(JTextField tx,int row){
		double imp=this.get_subtotal();
		if (imp>0){
			if (this.requiere_percepcion_ingresos_brutos==1
					& row==_Interface.row_percepcion_iibb){
				this._do_automatic_calc_percepcion_iibb(imp,tx);
			}
			if (this.requiere_iva27==1
					& row==_Interface.row_iva27){
				this._do_automatic_calc_iva27(imp,tx);
			}
			if (this.requiere_percepcion_ganancias==1
					& row==_Interface.row_retencion_ganancias){
				this._do_automatic_calc_ganacias(imp,tx);
			}
			if (this.requiere_impuestos_internos==1
					& row==_Interface.row_impuestos_internos){
				this._do_automatic_calc_impuestos_internos(imp,tx);
			}
			if (this.requiere_percepcion_iva==1
					& row==_Interface.row_retencion_iva){
				this._do_automatic_calc_percepcion_iva(imp,tx);
			}
			
			if (this.requiere_iva21==1
					& row==_Interface.row_iva){
				this._do_automatic_calc_iva(imp,tx);
			}
			if (this.requiere_iva10==1
					& row==_Interface.row_iva10){
				this._do_automatic_calc_iva10(imp,tx);
			}
			if (this.requiere_rg3337==1
					& row==_Interface.row_rg3337){
				this._do_automatic_calc_rg3337(imp,tx);
			}	
		}
		
	}
	public void _evaluate_ajuste(JTextField tx,int row){
		boolean transfer=this._evaluate_importe(tx, row);
		if (transfer){
			frame.getJTable().changeSelection(row+1, 2, false,false);
			frame.getJTable().editCellAt(row+1,2);
			frame.getJTable().transferFocus();
		}else {
			error("Error en importe");
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}
	
	public void scan(){
		
		aviso("RECUERDE ESCANEAR LA IMAGEN CON RESOLUCION DE 100 DPI");
		boolean error=false;
		try {
			String[] lists=scanner.getDeviceNames();
			boolean twain=false;
			int i=0;
			while (i<lists.length){
				System.out.println("SCANNER>"+lists[i]);
				if (lists[i].toLowerCase().contains("wia")){
					twain=true;
					scanner.select(lists[i]);
					
				}
				i++;
			}
			scanner.acquire();
			
			}catch(ScannerIOException se){
				error=true;
		    
		    //displayError("Escaner no disponible", se.getLocalizedMessage(), se.getMessage(), se);
		    se.printStackTrace();
		  }
		if (error){
				error("Escaner no disponible");
		}
	}
	
	private boolean write(BufferedImage img,int sec){
	
	
	String idproveedor=frame.get_txt_idproveedor().getText();
	String idcomprobante=frame.get_txt_sucursal().getText()+frame.get_txt_numero().getText()+frame.get_list_letra().getSelectedItem().toString();
	String tc=this.getTipoTC();
	
	boolean	ok=this.storeImage(idproveedor, tc, idcomprobante, (sec-1), img);	
	
	return ok;
	}
	
	public boolean storeImage(String idproveedor,String tc,String idcomprobante,int secuencia,BufferedImage img){
    	FileInputStream fis = null;
        PreparedStatement ps = null;
        System.out.println("Store Image "+tc+" "+idproveedor);
        boolean ok=true;
        if (idcomprobante.compareTo("")!=0 & idproveedor.compareTo("")!=0 & idproveedor.compareTo("")!=0){
        	String INSERT_PICTURE = "insert into facturas(idproveedor, tc, idcomprobante,secuencia,imagen) values (?, ?, ?, ?, ?)";
        	ByteArrayOutputStream buffer_img = new ByteArrayOutputStream();

        	try {
				ImageIO.write(img, "jpg", buffer_img);
				InputStream in=new ByteArrayInputStream(buffer_img.toByteArray());
				        	  
							ps = data.getConnector("MySQL").prepareStatement(INSERT_PICTURE);
							ps.setString(1, idproveedor);
							ps.setString(2, tc);
							ps.setString(3, idcomprobante);
							ps.setInt(4,secuencia);
							ps.setBinaryStream(5, in, (int) buffer_img.size());
							  
							  int n=ps.executeUpdate();
							  ok=n>0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 	
        }
        
         return ok; 
    }
	
	private boolean write(BufferedImage img,String filename){
    	boolean error=false;
    	String msj="";
    	String rename=filename.replace(".jpg", ".back");
    	File file=new File(filename);
    	File filebak=new File(rename);
    	if (file.exists()){
    		file.renameTo(filebak);
    	}
    	try {
    	  ImageIO.write(img, "jpg", file);
    	}catch(Exception e){
    		msj=e.getMessage();
    		e.printStackTrace();
    		error=true;
    	}
    	return !error;
    }
	
	public BufferedImage loadImage(){
		BufferedImage img=null;
		String filename="//192.168.4.150/windows storage/fotos/test.jpg";
    	try {
      	   img = ImageIO.read(new File(filename));
         } catch (IOException e) {
         }
         return img;
    }
	
	public boolean _eliminar_fotos(String cuenta,String tc,String idcomprobante){
		boolean error=data.deleteFotos(cuenta, tc, idcomprobante);
		return !error;	
	}
	public boolean eliminar_fotos(){
		
		String cuenta=frame.get_txt_idproveedor().getText();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante=sucursal+numero+letra;
		boolean ok=this._eliminar_fotos(cuenta, this.getTipoTC(), idcomprobante);
		return ok;
	}
	public boolean getImages(String idproveedor,String tc,String idcomprobante){
    	BufferedImage _image=null;
    	boolean ok=false;
    	try {
			Statement stmt = data.getConnector("MySQL").createStatement();
				String q="SELECT imagen FROM facturas  where idproveedor like '"+idproveedor+"' and tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"'";
				System.out.println(q);
				ResultSet resultSet = stmt.executeQuery(q);
				
					 while (resultSet.next()){
						 ok=true;
						 Blob image = resultSet.getBlob(1);
						  InputStream input = image.getBinaryStream();
				          _image = javax.imageio.ImageIO.read(input);
				          this.addFoto(_image);
				           
					 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return ok;
    }
	
	public boolean cargar_fotos(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		String sucursal=frame.get_txt_sucursal().getText();
		String tc=this.getTipoTC();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante=sucursal+numero+letra;
		boolean ok=this.getImages(idproveedor, tc, idcomprobante);
		return ok;
	}
	
	public boolean cargar_fotosold(){
		String filename="";
		filename+=this.server;
		String cuenta=frame.get_txt_idproveedor().getText();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante=sucursal+numero+letra;
		
		Object[][] results=this.data.getFotos(cuenta, getTipoTC(), idcomprobante);
		int fotos=0;
		if (results!=null){
			
			try{
				
				String _fotos=(String) results[0][0];
				fotos=new Integer(_fotos);
			}catch(Exception e){
				
			}
			
		}
		boolean error=false;
		System.out.println("Intentando Cargar "+fotos+" Fotos");
		for (int i=0;i<fotos;i++){
			String name=filename+cuenta+"-"+idcomprobante+"-"+(i+1)+".jpg";
			try{
				System.out.println("Intentando Cargar imagen "+name);
				
				File f=new File(name);
				if (f.exists()){
					this.addFoto(f.getAbsolutePath());
				}
				}catch(Exception e){
				error=true;	
				}	
		}
		
		
		return !error;
	}
	
	public boolean _sobreescribir(){
		boolean stock=false;
		System.out.println("SOBRESCRIBIENDO _X ("+_x_cuenta+") "+_x_tc+"-"+_x_idcomprobante+" ");
		stock=preguntar("confirmar","Conserva la carga de articulos existente?");
		String cuenta=_x_cuenta;
		String tc=_x_tc;
		String idcomprobante=_x_idcomprobante;
		String sucursal=idcomprobante.substring(0,4);
		String numero=idcomprobante.substring(4,12);
		String letra=idcomprobante.substring(12,13);
		
		boolean ok=_eliminar_fotos(cuenta, tc, idcomprobante);
		if (ok){
		data.beginTransaction();
		data.clearBatch();
		String q=data.getunBlock();
		data.addBatch(q);
		
		q=data.getEliminarAsiento(cuenta,tc, sucursal, numero, letra);
		data.addBatch(q);
		q=data.getEliminarCpte(cuenta, tc, idcomprobante);
		System.out.println("Eliminar Cpte> "+q);
		data.addBatch(q);
		
		if (!stock){
		q=data.getEliminarStock(cuenta, tc, idcomprobante);
		data.addBatch(q);
		q=data.getEliminarCpteInsumos(cuenta, tc, idcomprobante);
		data.addBatch(q);
		}else {
			String _cuenta=frame.get_txt_idproveedor().getText();
			String _tc=this.getTipoTC();
			String _idcomprobante=frame.get_txt_sucursal().getText();
			_idcomprobante+=frame.get_txt_numero().getText();
			_idcomprobante+=frame.get_list_letra().getSelectedItem().toString();
			q=data.getUpdateStock(_cuenta,_tc,_idcomprobante,cuenta, tc, idcomprobante);
			data.addBatch(q);
			q=data.getUpdateCpteInsumos(_cuenta,_tc,_idcomprobante,cuenta, tc, idcomprobante);
			data.addBatch(q);	
			
		}
		//q=data.getEliminarDigital(cuenta, tc, idcomprobante);
		//data.addBatch(q);
			
		
		q=data.getBlock();
		data.addBatch(q);
		ok=!data.executeBatch();
		if (ok){
			ok=this._grabar_asiento();
			if (ok){
				ok=this._guardar_cpte();
				if (ok){
					ok=this._guardar_imagenes(_x_cuenta,_x_tc,_x_idcomprobante,stock);
					if (ok){
						data.commitTransaction();
						
						aviso("El comprobante se sobreescribio correctamente");
					}else {
						error("error guardando imagenes");
						data.rollbackTransaction();
					}	
				}else{
					error("error guardando comprobante");
					data.rollbackTransaction();
				}
				
			}else{
				error("error grabando asiento");
				data.rollbackTransaction();	
			}
		}else {
			error("error eliminando comprbante anterior");
			data.rollbackTransaction();
		}
		
		}else {
			error("error eliminando comprobante de compras");
			data.rollbackTransaction();
		}
		return ok;
	}
		
	public boolean _guardar_cpte(
			){
		String importe_s_iva=""+this._get_importe_subtotal();
		String importe_insumos=""+this._get_importe_subtotal();
		String neto_gravado=""+this._get_importe_subtotal();
		String importe_iva=""+this._get_importe_iva();
		String cuenta=frame.get_txt_idproveedor().getText();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante=sucursal+numero+letra;
		String fecha=frame.get_txt_fecha_factura().getText();
		String fecha_contable=frame.get_txt_fecha_contable().getText();
		Convertidor C=new Convertidor();
		String vencimiento=fecha;
		String importe=frame.get_txt_total().getText();
		importe=importe.replace(",", "");
		importe_s_iva=importe_s_iva.replaceAll(",", "");
		importe_iva=importe_iva.replaceAll(",", "");
		importe_insumos=importe_insumos.replaceAll(",", "");
		neto_gravado=neto_gravado.replaceAll(",", "");
		String tc=this.getTipoTC();
		System.out.println("////////GRABANDO COMPROBANTE///////////");
		System.out.println("cpte            ="+tc+" "+sucursal+"-"+numero+letra);
		System.out.println("importe total   ="+importe);
		System.out.println("importe neto    ="+importe_s_iva);
		System.out.println("iva             ="+importe_iva);
		System.out.println("insumos         ="+importe_insumos);
		System.out.println("neto gravado    ="+neto_gravado);
		
		String q=data.getInsert(cuenta,
				tc,
				idcomprobante,
				fecha,
				fecha_contable,
				vencimiento,
				importe,
				importe_s_iva,
				importe_insumos,
				importe_iva,
				neto_gravado, 
				sucursal,
				numero,
				letra);
		System.out.println(q);
		data.clearBatch();
		data.addBatch(q);
		_Constructor Co=(_Constructor)this._constructor;
		String iduser=(String)Co.getValueParameter(_parametros.iduser);
		q=this.data._getLog(iduser, "GRABAR", cuenta,this.getTipoTC(), idcomprobante);
		
		System.out.println(q);
		//data.addBatch(q);
		boolean ok=!data.executeBatch();
		return ok;
	}
	
	public void eliminar(){
		//si no tiene opg asociadas
		//si tiene articulos asociados advertir
		if (confirmar("Confirme para Eliminar este Comprobante:",3)){
			_eliminar();
		}
		
	}
	
	private void _eliminar(){
		
		String cuenta=frame.get_txt_idproveedor().getText();
		String sucursal=frame.get_txt_sucursal().getText();
		String numero=frame.get_txt_numero().getText();
		String letra=frame.get_list_letra().getSelectedItem().toString();
		String idcomprobante=sucursal+numero+letra;
		boolean ok=this.eliminar_fotos();
		if (ok){
		data.beginTransaction();
		data.clearBatch();
		String q=data.getunBlock();
		data.addBatch(q);
		q=data.getEliminarAsiento(cuenta, this.getTipoTC(), sucursal, numero, letra);
		data.addBatch(q);
		q=data.getEliminarEnlace(this.getTipoTC(), idcomprobante, cuenta);
		data.addBatch(q);
		q=data.getEliminarCpte(cuenta, this.getTipoTC(), idcomprobante);
		data.addBatch(q);
		q=data.getEliminarCpteInsumos(cuenta, this.getTipoTC(), idcomprobante);
		data.addBatch(q);
		q=data.getEliminarDigital(cuenta, this.getTipoTC(), idcomprobante);
		data.addBatch(q);
		q=data.getEliminarStock(cuenta, this.getTipoTC(), idcomprobante);
		data.addBatch(q);
		q=data.getBlock();
		data.addBatch(q);
		boolean error=data.executeBatch();
		if (!error){
			aviso("se elimino correctamente el comprobante");
			data.commitTransaction();
			this.clean();
		}else{
			error("error al intentar eliminar el comprobante");
			data.rollbackTransaction();
		}
		}
	}
	public void guardar(){
		C.setVisible(false);
		C.dispose();
		C=null;
		boolean ok=true;
		boolean grabar=false;
		boolean eliminar=false;
		
		if (frame.get_list_imputacion().getItemCount()>1){
			if (preguntar("confirmar","Reviso la imputacion para este comprobante?",this._icon_imputacion)){
				ok=true;
			}else {
				ok=false;
			}
		}
		if (ok){
			if (!this._tiene_imagenes()){
				aviso("Aviso","No puede grabar una factura sin asociar una imagen!",this._icon_imagen);
				
			}else {
				if (this.existe_cpte()){
					if (this.preguntar("confirmar", "Desea sobrescirbir el comprobante?",this._icon_cpte)){
						eliminar=true;
						grabar=true;
					}else{
						
					}
				}else{
					grabar=true;
				}
				if (grabar){
					if (this.existe_cpte()){
						ok=this._sobreescribir();
					}else {
						if (this._x_cuenta.compareTo("")!=0){
							if (preguntar("confirmar","recodifica comprobante "+_x_cuenta+" "+_x_idcomprobante)){
								ok=this._sobreescribir();	
								
							}else{
								aviso("escritura cancelada!");
							}
								
						}else {
							ok=this._guardar();	
						}
						
							
					}
					
					if (ok){
						aviso("se grabo correctamente el comprobante");
						clean();
					}else{
						error("error grabando comprobante");
					}	
				}
					
			}	
		}
		
		
	}
	
	public void update_imputacion(JComboBox cb){
		String imputacion=cb.getSelectedItem().toString();
		
		try {
			frame.getJTable().setValueAt(imputacion,0,0);
			frame.getJTable().setValueAt(imputacion,8,0);
			frame.getJTable().setValueAt(imputacion,9,0);
			frame.getJTable().setValueAt(imputacion,10,0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean _guardar(){
		
		boolean ok=this._guardar_imagenes(_x_cuenta,_x_tc,_x_idcomprobante,false);
		if (ok){
			System.out.println("Grabando Imagen ");
			data.beginTransaction();
			//data.addBatch(q);
			ok=this._guardar_cpte();
			if (ok){
				ok=this._grabar_asiento();
				
				boolean error=!ok;
				if (ok){
					if (ok){
						data.commitTransaction();
						//this.data._Log(iduser, "GRABAR", cuenta, this.getTipoTC(), idcomprobante);
					}else {
						error("Error guardando imagenes");
						data.rollbackTransaction();	
					}
						
				}else {
					error("error grabando asiento");
					data.rollbackTransaction();
				}	
			}else {
				error("error grabando comprobante");
				data.rollbackTransaction();
			}
			
			
		}
			return ok;
	}
	
	public String getTipoTC(){
		
		String tc="";
		if (frame.get_list_tc().getSelectedIndex()==0){
			tc="FCC";
			this.tipo_tc=1;
		}
		
		if (frame.get_list_tc().getSelectedIndex()==1){
			tc="NCC";
			
			this.tipo_tc=0;
		}
		
		if (frame.get_list_tc().getSelectedIndex()==2){
			tc="RMC";
			
			this.tipo_tc=0;
		}
		if (frame.get_list_tc().getSelectedIndex()==3){
			tc="FCN";
			this.tipo_tc=0;
		}
		return tc;
	}
	
	public boolean _tiene_imagenes(){
		int cps=frame.getJPanel4().getComponentCount();
		int images=0;
		boolean ok=false;
		
		for (int i=0;i<cps;i++){
			if (frame.getJPanel4().getComponent(i) instanceof ImageComponent){
				images++;
			}
		}
		return images>0;
	}
	
	public boolean _guardar_imagenes(String _x_cuenta,String _x_tc,String _x_idcomprobante,boolean update){
		int cps=frame.getJPanel4().getComponentCount();
		int images=0;
		boolean ok=false;
		System.out.println("Guardando imagenes "+cps);
		for (int i=0;i<cps;i++){
			if (frame.getJPanel4().getComponent(i) instanceof ImageComponent){
				images++;
				ImageComponent img=(ImageComponent)frame.getJPanel4().getComponent(i);
				BufferedImage image=img.getImage();
				ok=this.write(image,images);
				if(!ok){
					error("Error grabando imagen "+images);
				}
			}
		}
		if (ok){
			String cuenta=frame.get_txt_idproveedor().getText();
			String sucursal=frame.get_txt_sucursal().getText();
			String numero=frame.get_txt_numero().getText();
			String letra=frame.get_list_letra().getSelectedItem().toString();
			String idcomprobante=sucursal+numero+letra;
			String tc=this.getTipoTC();
			int carga_articulos=0;
			if (frame.get_chk_publica().isSelected()){
				carga_articulos=1;
			}
			String q="";
			if (update){
				q=data.getUpdateDigital(images, carga_articulos,cuenta, tc, idcomprobante,_x_cuenta, _x_tc, _x_idcomprobante);
			}else {
				q=data.getInsertDigital(cuenta, tc, idcomprobante, images,carga_articulos);
			}
			
			data.clearBatch();
			data.addBatch(q);
			ok=!data.executeBatch();
		}
		System.out.println("guardando imagenes? "+images);
		return ok;
	}
	
	public void recalculate_total(int row,JTextField tx){
		double total=0.0;
		
		for (int i=0;i<frame.getJTable().getRowCount();i++){
			String importe="";
			if (i!=row){
				importe=frame.getJTable().getValueAt(i, 2).toString();
					
			}else {
				importe=tx.getText();
			}
			importe=importe.replaceAll(",","");
			double imp=0.0;
			try{
				imp=new Double(importe);
			}catch(Exception e){
				
			}
			total+=imp;
		}
		Convertidor C=new Convertidor();
		String importe=C.getMoney(total, 2);
		frame.get_txt_total().setText(importe);
		
	}
	
	private double _get_importe(String impx){
		impx.replaceAll(",", "");
		
		double imp=0.0;
		try {
			imp=new Double(impx);
		}catch(Exception e){
			
		}
		
		
		return imp;
	}
	
	private double _get_importe_row(int row){
		double imp=0.0;
		String _imp="";
		try {
			_imp=frame.getJTable().getValueAt(row, 2).toString();
			_imp=_imp.replaceAll(",", "");
		imp=new Double(_imp);
		}catch(Exception e){
			
		}
		return imp;
	}
	
	private String _get_cuenta_row(int row){
		
		String cuenta="";
		try {
			cuenta=frame.getJTable().getValueAt(row, 0).toString();
			
		
		}catch(Exception e){
			
		}
		return cuenta;
	}
	
	private double _get_importe_subtotal(){
		String impx=frame.getJTable().getValueAt(0, 2).toString();
		double tmp=this._get_importe(impx);
		return tmp;
	}
	
	private double _get_importe_iva(){
		String impx=frame.getJTable().getValueAt(1, 2).toString();
		double tmp=this._get_importe(impx);
		return tmp;
	}
	
	public boolean cerrado(String fecha){
		boolean cerrado=true;
		Object[][] results=data.getCerrado(fecha);
		if (results!=null){
			if (results.length>0){
				String _cerrado=(String) results[0][0];
				if (_cerrado.compareTo("0")==0){
					cerrado=false;
				}
			}
		}
		return cerrado;
	}
	
	public boolean valida(String fecha){
		boolean valida=false;
		Object[][] results=data.getValida(fecha);
		if (results!=null){
			if (results.length>0){
				String _valida=(String) results[0][0];
				if (_valida.compareTo("1")==0){
					valida=true;
				}
			}
		}
		return valida;
	}
	
	public boolean configurado(String idproveedor){
		boolean _configurado=false;
		Object[][] results=data.getConfigurado(idproveedor);
		if (results!=null){
			if (results.length>0){
					_configurado=true;
				
			}
		}
		return _configurado;
	}
	
	

	 private aplicacion.herramientas.java.buscadores.Fecha bFecha=null;
	 public void BuscarFecha(JTextField tx){
	 	if (bFecha==null){
	 		bFecha=new aplicacion.herramientas.java.buscadores.Fecha(this.getConstructor());
	 	}
	 	
	 	
	 	bFecha.Buscar(tx);
	 	
	 }
	public void k_calculate_fecha_contable(){
		String fecha=frame.get_txt_fecha_factura().getText();
		if (this.valida(fecha)){
			if (this.cerrado(fecha)){
				Object[][] results=data.getFechaValida(fecha);
				if (results!=null){
					if (results.length>0){
						
						String _fecha=(String) results[0][0];
						aviso("La fecha contable fue ajustada por el sistema");
						frame.get_txt_fecha_contable().setText(_fecha);
					}else {
						aviso("Para poder cargar este comprobante ajuste del calendario administrativo contable");
						frame.get_txt_fecha_contable().setText("");
						frame.get_txt_fecha_contable().setEditable(false);
					}
				}	
			}else {
				frame.get_txt_fecha_contable().setText(fecha);
			}	
		}else {
			aviso("Este comprobante tiene mas de 90 dias. No puede utilizarse");
			frame.get_txt_fecha_contable().setText("");
			frame.get_txt_fecha_contable().setEditable(false);
		}
		
		
	}
	
	public void _evaluate_importe_columns(JTextField tx,int row){
		boolean transfer=true;
		
		if (this._evaluate_importe(tx, row)){
			if (row==_Interface.row_subtotal){
				//esto es subtotal. no pasa nada. pasa a la siguiente
				transfer=this._evaluate_subtotal(tx, row);
			}
			if (row==_Interface.row_iva){
				//esto es iva 21%
				transfer=this._evaluate_impuesto(tx, row, this.requiere_iva21, "IVA C.F. 21%");
			}
			if (row==_Interface.row_iva10){
				//esto es iva 10.5%
				transfer=this._evaluate_impuesto(tx, row, this.requiere_iva10, "IVA C.F. 10.5%");
			}
			if (row==_Interface.row_iva27){
				//esto es iva 27%
				transfer=this._evaluate_impuesto(tx, row, this.requiere_iva27, "IVA C.F. 27%");
			}
			if (row==_Interface.row_retencion_iva){
				//esto es retencionn iva
				transfer=this._evaluate_impuesto(tx, row, this.requiere_percepcion_iva, "IVA Retencion");
			}
			if (row==_Interface.row_percepcion_iibb){
				//esto es percepcion iva
				transfer=this._evaluate_impuesto(tx, row, this.requiere_percepcion_ingresos_brutos, "Percepcion Ingresos Brutos");
			}
			if (row==_Interface.row_retencion_ganancias){
				//esto es retencion ganancias
				transfer=this._evaluate_impuesto(tx, row, this.requiere_percepcion_ganancias, "Retencion de Ganancias");
			}
			if (row==_Interface.row_importe_neto_no_gravado){
				//esto es importe neto no gravado
				transfer=this._evaluate_impuesto(tx, row, this.requiere_neto_no_grabado, "Importe neto no gravado");
			}
			if (row==_Interface.row_ajuste_por_redondeo){
				//esto es ajuste por redondeo
				this._evaluate_ajuste(tx, row);
			}
			if (row==_Interface.row_impuestos_internos){
				//esto es ajuste por redondeo
				transfer=this._evaluate_impuesto(tx, row, this.requiere_impuestos_internos, "Impuestos Internos");
			}
			
			if (row==_Interface.row_rg3337){
				//esto es rg 3337
				this._evaluate_impuesto(tx, row, this.requiere_rg3337, "RG 3337");
			}
			
			
			
		}else {
			error("Error en importe");
			transfer=false;
		}
		
		this.recalculate_total(row,tx);
		if (transfer){
			frame.getJTable().changeSelection(row+1, 2, false,false);
			frame.getJTable().editCellAt(row+1,2);
			frame.getJTable().transferFocus();
		}else {
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
			
		}
		
	}
	
	
}
