package aplicacion.ventas.catalogo.logic;
import aplicacion.modelo.*;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.*;

import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.image.logic.ImageComponent;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;


import aplicacion.ventas.catalogo.constructor.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.DefaultFocusManager;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.image.BufferedImage;
import javax.swing.table.*;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aplicacion.ventas.catalogo.interfaces.*;
import aplicacion.ventas.catalogo.*;
import aplicacion.herramientas.java.sortableselector.logic.*;
import aplicacion.ventas.catalogo.gui.*;
//import aplicacion.ventas.pedido.constructor._Constructor;
//import aplicacion.ventas.pedido.interfaces._Interface;
import aplicacion.herramientas.java.table.*;



public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data=null;
	private List<columna> Columns;
	private List<Filtro> Filters;
	private String carpeta_destino = "e:/indexados/";
	private List<columna> SColumns;
	private List<Filtro> SFilters;
	private String SFromTable;
	private LinkedList SOrders;
	private LinkedList Sexcluders;
	private LinkedList SiniValues;
	private LinkedList SLabels;
	private LinkedList SacumFilters;
	private String SRestriction;
	private String Sidconector;
	private String SGroup;
	private JTextField caller=null;
	public int count=0;
	public List<Object[]> items=null;
	
	private String FromTable;
	private LinkedList Orders;
	private LinkedList excluders;
	private LinkedList iniValues;
	private LinkedList Labels;
	private LinkedList acumFilters;
	private List<BufferedImage> images=null;
	private String Restriction;
	private String idconector;
	private String Group;
	private boolean colors=false;
	private List<ImageComponent> fotos=null;
	private int top;
	private int Stop=100;
	private Logic external;
	private String title="";
	private aplicacion.herramientas.java.image.constructor._Constructor CF=null;
	
	private boolean clean=true;
	
	public void setCaller(JTextField tx){
		this.caller=tx;
	}
	
	public JTextField getCaller(){
		return caller;
	}
	public boolean isColors() {
		return colors;
	}
	public void setColors(boolean colors) {
		this.colors = colors;
	}
	private int hoja_actual = 0;
	private int hojas = 0;
	//variables de tareas swingwork
	private String estado="";
	private int current;
	private int lenght;
	private boolean debug,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	
	private Timer TimerE;  //  @jve:decl-index=0:
	private boolean doneE=false;
	private boolean canceledE=false;
	private Crono cronoE;
	private int foto_selected=0;
	
	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}
	
	public void setTop(int top){
		this.top=top;
	}
	public void setSystemTop(int top){
		this.Stop=top;
	}
	public void setLocation(int x,int y){
		this._frame.setLocation(x, y);
	}
	
	
	
	
	public void setFocus(){
		boolean focus_default=true;
		if (Filters!=null & frame.getJTable_fields()!=null){
		for (int i=0;i<Filters.size();i++){
			Filtro f=(Filtro)Filters.get(i);
			if (f.hasFocus()){
				this.focus(i);
				focus_default=false;
			}
		}
		if (focus_default){
			if (frame.getJTable_fields().getColumnCount()>0){
				this.focus(0);
			}
		}
		}
	}
	
	public void addFilter(Filtro filtro){
		if (!this.hasFilter(filtro)){
			this.Filters.add(filtro);	
		}
	}
	
	public void addSystemFilter(Filtro filtro){
		if (!this.hasSystemFilter(filtro)){
			this.SFilters.add(filtro);	
		}
		
	}
	public void addFromTable(String From){
		this.FromTable=From;
	}
	public void addSystemFromTable(String From){
		this.SFromTable=From;
	}
	public void addRestriction(String restriction){
		this.Restriction=restriction;
	}
	public void addSystemRestriction(String restriction){
		this.SRestriction=restriction;
	}
	private boolean hasColumn(columna c){
		boolean found=false;
		int i=0;
		while(i<Columns.size() & !found){
			found=Columns.get(i).getNombre().compareTo(c.getNombre())==0;
			i++;
		}
		return found;
	}
	private boolean hasSystemColumn(columna c){
		boolean found=false;
		int i=0;
		while(i<SColumns.size() & !found){
			found=SColumns.get(i).getNombre().compareTo(c.getNombre())==0;
			i++;
		}
		return found;
	}
	private boolean hasFilter(Filtro f){
		boolean found=false;
		int i=0;
		while(i<Filters.size() & !found){
			found=Filters.get(i).getNombre().compareTo(f.getNombre())==0;
			i++;
		}
		return found;
	}
	
	private boolean hasSystemFilter(Filtro f){
		boolean found=false;
		int i=0;
		while(i<SFilters.size() & !found){
			found=SFilters.get(i).getNombre().compareTo(f.getNombre())==0;
			i++;
		}
		return found;
	}
	public void addColumn(columna c){
		if (!this.hasColumn(c)){
			this.Columns.add(c);	
		}
	}
	
	public void addSystemColumn(columna c){
		if (!this.hasSystemColumn(c)){
			this.SColumns.add(c);	
		}
	}
	
	private void addMasterColumn(){
		columna c=new columna();
		c.setNombre("");
		c.setAlias("");
		c.setClass(Boolean.class);
		c.setWidth(40);
		addColumn(c);
	}
	
	private void addSystemMasterColumn(){
		columna c=new columna();
		c.setNombre("");
		c.setAlias("");
		c.setClass(Boolean.class);
		c.setWidth(40);
		addSystemColumn(c);
	}
	public void addGroup(String rest){
		this.Group=rest;
	}
	public void addSystemGroup(String rest){
		this.SGroup=rest;
	}
	private void acumulate_sum(){
		for (int i=0;i<this.Filters.size();i++){
			LinkedList sfilter=(LinkedList)this.SacumFilters.get(i);
			LinkedList filter=(LinkedList)this.acumFilters.get(i);
			filter.addLast(frame.getJTable_fields().getValueAt(0, i).toString());
			sfilter.addLast(frame.getJTable_fields().getValueAt(0, i).toString());
			acumFilters.set(i, filter);
			SacumFilters.set(i, sfilter);
			frame.getJTable_fields().setValueAt("",0,i);
		}
	}
	private boolean has_acumulate_sum(){
		boolean has=false;
		int i=0;
		if (this.acumFilters!=null){
			while(i<this.acumFilters.size() & !has){
				LinkedList filter=(LinkedList)this.acumFilters.get(i);
				int j=0;
				while (!has & j<filter.size()){
					has=filter.get(j).toString().replaceAll(" ", "").compareTo("")!=0;
					
					j++;
				}
				i++;
			}	
		}
		
		return has;
	}
	
	private void Systemacumulate_sum(){
		
		for (int i=0;i<this.SFilters.size();i++){
			
			LinkedList filter=(LinkedList)this.SacumFilters.get(i);
			filter.addLast(frame.getJTable_fields().getValueAt(0, i).toString());
			SacumFilters.set(i, filter);
			frame.getJTable_fields().setValueAt("",0,i);
		}
	}


	private void back_sum(){
		for (int i=0;i<this.Filters.size();i++){
			LinkedList filter=(LinkedList)this.acumFilters.get(i);
			if (filter.size()>=1){
				String tmp=(String)filter.get(filter.size()-1);
				filter.removeLast();
				acumFilters.set(i, filter);
				frame.getJTable_fields().setValueAt("",0,i);
			}
		}
	}

	public void setExternalLogic(Logic external){
		this.external=external;
	}
	
	private String getWhereString(int i){
		String description="";
		Filtro filtro=(Filtro) Filters.get(i);
		String column=filtro.getNombre();
		String descript=this.acumFilters.get(i).toString();
		LinkedList filter=(LinkedList)this.acumFilters.get(i);
		if (filter.size()>0){
			int j=0;
			while (j<filter.size()){
				String flt=(String) filter.get(j);
				String aux=flt;
				while (aux.contains(" ")){
					String sub=aux.substring(0,aux.indexOf(" "));
					if (sub.compareTo("")!=0){
						if (description.length()>0){
							description=description+" and ";
						}
						description=description+" "+column+" like '%"+sub+"%'";
					}
					aux=aux.substring(aux.indexOf(" ")+1);
				}
				if (aux.compareTo("")!=0){
						if (description.length()>0){
							description=description+" and ";
						}
						description=description+"  "+column+" like '%"+aux+"%'";
				}
			
				j++;
			}
		}
		return description;
	}
	
	private String getWhereStringDocumentos(int i){
		String description="";
		Filtro filtro=(Filtro) Filters.get(i);
		String column=filtro.getNombre();
		String descript=this.acumFilters.get(i).toString();
		LinkedList filter=(LinkedList)this.acumFilters.get(i);
		if (filter.size()>0){
			int j=0;
			while (j<filter.size()){
				String flt=(String) filter.get(j);
				String aux=flt;
				while (aux.contains(" ")){
					String sub=aux.substring(0,aux.indexOf(" "));
					if (sub.compareTo("")!=0){
						description=description+" "+sub;
					}
					aux=aux.substring(aux.indexOf(" ")+1);
				}
				if (aux.compareTo("")!=0){
						description=description+" "+aux+"";
				}
			
				j++;
			}
		}
		return description;
	}
	private String getWhereStringCatalogos(int i){
		String description="";
		Filtro filtro=(Filtro) Filters.get(i);
		String column="filename";
		String descript=this.acumFilters.get(i).toString();
		LinkedList filter=(LinkedList)this.acumFilters.get(i);
		if (filter.size()>0){
			int j=0;
			while (j<filter.size()){
				String flt=(String) filter.get(j);
				String aux=flt;
				while (aux.contains(" ")){
					String sub=aux.substring(0,aux.indexOf(" "));
					if (sub.compareTo("")!=0){
						if (description.length()>0){
							description=description+" and ";
						}
						description=description+" "+column+" like '%"+sub+"%'";
					}
					aux=aux.substring(aux.indexOf(" ")+1);
				}
				if (aux.compareTo("")!=0){
						if (description.length()>0){
							description=description+" and ";
						}
						description=description+"  "+column+" like '%"+aux+"%'";
				}
			
				j++;
			}
		}
		return description;
	}
	
	private String getSystemWhereString(int i){
		String description="";
		Filtro filtro=(Filtro) SFilters.get(i);
		String column=filtro.getNombre();
		String descript=this.SacumFilters.get(i).toString();
		LinkedList filter=(LinkedList)this.SacumFilters.get(i);
		if (filter.size()>0){
			int j=0;
			while (j<filter.size()){
				String flt=(String) filter.get(j);
				String aux=flt;
				while (aux.contains(" ")){
					String sub=aux.substring(0,aux.indexOf(" "));
					if (sub.compareTo("")!=0){
						if (description.length()>0){
							description=description+" and ";
						}
						description=description+" "+column+" like '%"+sub+"%'";
					}
					aux=aux.substring(aux.indexOf(" ")+1);
				}
				if (aux.compareTo("")!=0){
						if (description.length()>0){
							description=description+" and ";
						}
						description=description+"  "+column+" like '%"+aux+"%'";
				}
			
				j++;
			}
		}
		return description;
	}
	
	public boolean getAutoRestriction(){
		boolean auto=false;
		int i=0;
		while (i<Filters.size() &!auto){
			auto=Filters.get(i).getValor().compareTo("")!=0;
			i++;
		}
		return auto;
	}
	
	public String getAutoRestrictionQuery(){
		String auto="";
		for (int i=0;i<Filters.size();i++){
			if (Filters.get(i).getValor().compareTo("")!=0){
				if (auto.length()>0) auto+=" and ";
				auto+=Filters.get(i).getNombre()+"  like '"+Filters.get(i).getValor()+"' ";
			}
		}
		return auto;
	}

	public String getSystemAutoRestrictionQuery(){
		String auto="";
		for (int i=0;i<SFilters.size();i++){
			if (SFilters.get(i).getValor().compareTo("")!=0){
				if (auto.length()>0) auto+=" and ";
				auto+=SFilters.get(i).getNombre()+"  like '"+SFilters.get(i).getValor()+"' ";
			}
		}
		return auto;
	}
	private String doQuerys(){
		String tmpQuery;
		top=100;
		if (this.Columns.contains("*")){
		tmpQuery="select top "+top+" * ";
		}else {
			tmpQuery="select ";
			if (top>0){
				tmpQuery=tmpQuery+" top "+top+" ";
			}
			for (int i=0;i<Columns.size()-1;i++){
				columna column=(columna) Columns.get(i);
				if (column.getNombre().compareTo("")!=0){
					tmpQuery=tmpQuery+column.getNombre()+",";	
				}
				
			}
			
			columna column=(columna) Columns.get(Columns.size()-1);
			tmpQuery=tmpQuery+column.getNombre()+" ";
			
		}
		tmpQuery=tmpQuery+" from "+this.FromTable;
		
		int filterx=0;
		
		for (int i=0;i<Filters.size();i++){
			
			String x=this.getWhereString(i);
					if (x.compareTo("")!=0){
						if (filterx==0){
							tmpQuery=tmpQuery+" where ";
						}
						if (filterx>0) {
							tmpQuery=tmpQuery+" and ";
						}	
						tmpQuery=tmpQuery+" "+x;
						filterx++;
					}
		}
		
		if (Restriction.compareTo("")!=0){
			if (filterx==0){
				tmpQuery=tmpQuery+" where ";
				filterx++;
			} else {
				tmpQuery=tmpQuery+" and ";
			}
			tmpQuery=tmpQuery+Restriction;
		}
		
		if (this.getAutoRestriction()){
			if (filterx==0){
				tmpQuery=tmpQuery+" where ";
			} else {
				tmpQuery=tmpQuery+" and ";
			}
			tmpQuery=tmpQuery+this.getAutoRestrictionQuery();
		}
		
		if (Group.compareTo("")!=0){
			
			tmpQuery=tmpQuery+" Group by "+Group;
		}
		if (!Orders.isEmpty()){
			
			tmpQuery=tmpQuery+" order by ";
			for (int i=0;i<Orders.size()-1;i++){
				tmpQuery=tmpQuery+Orders.get(i)+",";
			}
			tmpQuery=tmpQuery+Orders.get(Orders.size()-1);
			
		}
		
		System.out.println(tmpQuery);
		
	 return tmpQuery;

	}
	
	private void create_table_documentos(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();

		col = new Column();
		col.setName("idproveedor");
		col.setWidth(100);
		col.setEditable(false);

		table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(120);
		col.setEditable(false);

		table.addColumn(col);

		col = new Column();
		col.setName("archivo");
		col.setWidth(280);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("pagina");
		col.setWidth(80);
		col.setEditable(false);
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_documentos);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Arial", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		frame.setJTable_documentos(table.getTable());
	}
	
	public void buscar_documentos(JTextField tx) {
		String text=tx.getText();
		this.buscar_documentos(text);
	}
	public void buscar_documentos(String text) {
		
		Object[][] results=data.buscar(text);
		if (results!=null){
			if (results.length>0){
				this.create_table_documentos(results);		
			}else{
				aviso("resultado nulo");
				this.clean();
				
			}
		}else{
			aviso("resultado nulo");
			this.clean();
		}
		
	}
	private String doSystemQuerys(){
		String tmpQuery;
		if (this.SColumns.contains("*")){
		tmpQuery="select * ";
		}else {
			tmpQuery="select ";
			if (Stop>0){
				tmpQuery=tmpQuery+" top "+Stop+" ";
			}
			for (int i=0;i<SColumns.size()-1;i++){
				columna column=(columna) SColumns.get(i);
				if (column.getNombre().compareTo("")!=0){
					tmpQuery=tmpQuery+column.getNombre()+",";	
				}
				
			}
			
			columna column=(columna) SColumns.get(SColumns.size()-1);
			tmpQuery=tmpQuery+column.getNombre()+" ";
			
		}
		tmpQuery=tmpQuery+" from "+this.SFromTable;
		
		int filterx=0;
		
		for (int i=0;i<SFilters.size();i++){
			
			String x=this.getSystemWhereString(i);
					if (x.compareTo("")!=0){
						if (filterx==0){
							tmpQuery=tmpQuery+" where ";
						}
						if (filterx>0) {
							tmpQuery=tmpQuery+" and ";
						}	
						tmpQuery=tmpQuery+" "+x;
						filterx++;
					}
		}
		
		if (SRestriction.compareTo("")!=0){
			if (filterx==0){
				tmpQuery=tmpQuery+" where ";
				filterx++;
			} else {
				tmpQuery=tmpQuery+" and ";
			}
			tmpQuery=tmpQuery+SRestriction;
		}
		
		if (this.getAutoRestriction()){
			if (filterx==0){
				tmpQuery=tmpQuery+" where ";
			} else {
				tmpQuery=tmpQuery+" and ";
			}
			tmpQuery=tmpQuery+this.getSystemAutoRestrictionQuery();
		}
		
		if (SGroup.compareTo("")!=0){
			
			tmpQuery=tmpQuery+" Group by "+SGroup;
		}
		if (!SOrders.isEmpty()){
			
			tmpQuery=tmpQuery+" order by ";
			for (int i=0;i<SOrders.size()-1;i++){
				tmpQuery=tmpQuery+SOrders.get(i)+",";
			}
			tmpQuery=tmpQuery+SOrders.get(SOrders.size()-1);
			
		}
		
		System.out.println(tmpQuery);
		
	 return tmpQuery;

	}
	//metodos basicos de tareas swing
	public void createTimer(){
		crono=new Crono();
		crono.start();
		Timer=new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					endbar();
					crono.pause();
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
		
	}
	
	public void exit(){
		if (!clean){
			this.clean();
		}else{
			if (this.preguntar("Cierra Aplicacion", "Cierra Aplicacion")){
				
				this.exit_command();
			}	
		}
		
	}
	
	public void salir(){
		if (this.preguntar("Cierra Aplicacion", "Cierra Aplicacion")){
			images=null;
			this.exit_command();
		}
	}
	public void doCancel(){
		canceled=true;
		frame.get_btn_cancelar().setEnabled(true);
	}
	
	class CargarTask {
		CargarTask() {
			search();
			}
	}
	
	class BuscarTask {
		BuscarTask() {
			cargar_mas();
			}
	}
	
	public void goCargar() {
		this.createTimer();
		SwingWorker worker=null;
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					current = 0;
					done = false;
					return new CargarTask();
				}
			};
			done = false;
			canceled=false;
			worker.start();
			if (Timer!=null) {
				Timer.start();
			}
		}
	
	public void goBuscarEquivalencias() {
		estado="Buscando Equivalencias";
		this.createTimer();
		SwingWorker worker=null;
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					current = 0;
					done = false;
					return new BuscarTask();
				}
			};
			done = false;
			canceled=false;
			worker.start();
			if (Timer!=null) {
				Timer.start();
			}
		}
	public void goShowIt(int row) {
		images=new ArrayList<BufferedImage>();
		frame.getCanvas().resetVars();
		frame.getCanvas().setVisible(false);
		evaluar_tabla_proveedores(row);
		}
	
	public void clean(){
		done=true;
		clean=true;
		if (Timer!=null){
				this.doCancel();	
		}
		
		frame.getCanvas().resetVars();
		frame.getCanvas().setVisible(false);
		this.hoja_actual=0;
		this.hojas=0;
		frame.setJTable_equivalencias(null);
		frame.setJTable_deposito(null);
		frame.setJTable_relaciones(null);
		images=new ArrayList<BufferedImage>();
		frame.get_btn_anterior().setEnabled(false);
		frame.get_btn_siguiente().setEnabled(false);
		frame.get_btn_end().setEnabled(false);
		frame.get_btn_start().setEnabled(false);
		frame.get_btn_zoom().setEnabled(false);
		frame.get_btn_zoom_in().setEnabled(false);
		frame.get_btn_zoom_oute().setEnabled(false);
	
		for (int i=0;i<Filters.size();i++){
			frame.getJTable_fields().setValueAt("",0,i);
			this.acumFilters.set(i, new LinkedList());
			this.SacumFilters.set(i, new LinkedList());
		}
		frame.get_txt_codigo().setText("");
		frame.get_txt_descripcion().setText("");
		frame.get_txt_linea().setText("");
		frame.get_txt_marca().setText("");
		frame.get_txt_modelo().setText("");
		frame.get_txt_rubro().setText("");
		frame.get_txt_subrubro().setText("");
		
		frame.setJTable_equivalencias(null);
		frame.setJTable_deposito(null);
		frame.setJTable_articulos(null);
		
		if (frame.get_lst_modo().getSelectedIndex()==0){
			try {
				frame.setJTable_sistema(null);
				int i=found("Proveedores");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				i=found("Documentos");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				frame.getJTabbedPane2().setTitleAt(0, "Resultado Base de Sistema");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if (frame.get_lst_modo().getSelectedIndex()==2){
			
			try {
				frame.setJTable_documentos(null);
				int i=found("Proveedores");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				i=found("Sistema");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				frame.getJTabbedPane2().setTitleAt(0, "Resultado Documentos");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if (frame.get_lst_modo().getSelectedIndex()==1){
			
			try {
				frame.setJTable_proveedores(null);
				int i=found("Documentos");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				i=found("Sistema");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				frame.getJTabbedPane2().setTitleAt(0, "Resultado Proveedores");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (frame.get_lst_modo().getSelectedIndex()==3){
			try {
				int i=found("Sistema");
				if (i>0){
					frame.getJTabbedPane2().setTitleAt(i, "Resultado Base de Sistema");	
				}
				
				i=found("Documentos");
				if (i>0){
					frame.getJTabbedPane2().setTitleAt(i, "Resultado Documentos");	
				}
				
				i=found("Proveedor");
				if (i>0){
					frame.getJTabbedPane2().setTitleAt(i, "Resultado Proveedores");	
				}
				
				frame.setJTable_documentos(null);
				frame.setJTable_proveedores(null);
				frame.setJTable_sistema(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		if (frame.get_chk_multimedia().isSelected()){
			try {
				frame.setJTable_equivalencias(null);
				frame.setJTable_articulos(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		frame.get_txt_detalle_actualizacion().setText("");
		frame.get_txt_detalle_descripcion().setText("");
		frame.get_txt_detalle_estado().setText("");
		frame.get_txt_detalle_idarticulo().setText("");
		frame.get_txt_detalle_stock().setText("");
		frame.get_txt_detalle_linea().setText("");
		
		this.setFocus();
		
	}
	
	public void initTablas(){
		frame.setJTable_seleccion(null);
		frame.setJTable_articulos(null);
		frame.setJTable_equivalencias(null);
	}
	
	
	public void clean(JTextField tx,int col){
		clean=true;
		
		for (int i=0;i<Filters.size();i++){
			frame.getJTable_fields().setValueAt("",0,i);
			this.acumFilters.set(i, new LinkedList());
			this.SacumFilters.set(i, new LinkedList());
		}
		tx.setText("");
		frame.get_txt_codigo().setText("");
		frame.get_txt_descripcion().setText("");
		frame.get_txt_linea().setText("");
		frame.get_txt_marca().setText("");
		frame.get_txt_modelo().setText("");
		frame.get_txt_rubro().setText("");
		frame.get_txt_subrubro().setText("");
		
		frame.getCanvas().resetVars();
		frame.getCanvas().setVisible(false);
		frame.get_txt_actual().setText("0");
		frame.get_txt_total().setText("0");
		this.hoja_actual=0;
		this.hojas=0;
		images=new ArrayList<BufferedImage>();
		frame.get_btn_anterior().setEnabled(false);
		frame.get_btn_siguiente().setEnabled(false);
		frame.get_btn_end().setEnabled(false);
		frame.get_btn_start().setEnabled(false);
		frame.get_btn_zoom().setEnabled(false);
		frame.get_btn_zoom_in().setEnabled(false);
		frame.get_btn_zoom_oute().setEnabled(false);
		
		frame.setJTable_equivalencias(null);
		frame.setJTable_deposito(null);
		frame.setJTable_articulos(null);
		frame.setJTable_relaciones(null);
		if (frame.get_lst_modo().getSelectedIndex()==0){
			try {
				frame.setJTable_sistema(null);
				int i=found("Proveedores");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				i=found("Documentos");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				frame.getJTabbedPane2().setTitleAt(0, "Resultado Base de Sistema");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if (frame.get_lst_modo().getSelectedIndex()==2){
			
			try {
				frame.setJTable_documentos(null);
				int i=found("Proveedores");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				i=found("Sistema");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				frame.getJTabbedPane2().setTitleAt(0, "Resultado Documentos");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if (frame.get_lst_modo().getSelectedIndex()==1){
			
			try {
				frame.setJTable_proveedores(null);
				int i=found("Documentos");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				i=found("Sistema");
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
				frame.getJTabbedPane2().setTitleAt(0, "Resultado Proveedores");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (frame.get_lst_modo().getSelectedIndex()==3){
			try {
				int i=found("Sistema");
				if (i>0){
					frame.getJTabbedPane2().setTitleAt(i, "Resultado Base de Sistema");	
				}
				
				i=found("Documentos");
				if (i>0){
					frame.getJTabbedPane2().setTitleAt(i, "Resultado Documentos");	
				}
				
				i=found("Proveedor");
				if (i>0){
					frame.getJTabbedPane2().setTitleAt(i, "Resultado Proveedores");	
				}
				
				frame.setJTable_documentos(null);
				frame.setJTable_proveedores(null);
				frame.setJTable_sistema(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (frame.get_chk_multimedia().isSelected()){
			try {
				frame.setJTable_equivalencias(null);
				frame.setJTable_articulos(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		frame.get_txt_detalle_actualizacion().setText("");
		frame.get_txt_detalle_descripcion().setText("");
		frame.get_txt_detalle_estado().setText("");
		frame.get_txt_detalle_idarticulo().setText("");
		frame.get_txt_detalle_stock().setText("");
		frame.get_txt_detalle_linea().setText("");
		
		focus(col);
	}
	
	
	public void addtop(int top){
		this.top=top;
	}

	public void addOrder(String Column){
		this.Orders.add(Column);
	}
	public void addSystemOrder(String Column){
		this.SOrders.add(Column);
	}
	public void focus(){
		boolean found=false;
		int i=0;
		while(i<this.Columns.size() &!found){
			columna c=(columna) Columns.get(i);
			found=c.getMaster();
				if (found){
					JTextField columnField=(JTextField)c.getColumnField();
					if (columnField!=null)columnField.requestFocusInWindow();
				}else{
					i++;
				}
			}
	}
	
	public void Close(JTable table,int row){
		if (external==null){
			for (int i=0;i<this.Columns.size();i++){
				columna c=(columna) Columns.get(i);
				String value="";
				try {
					value=(String) table.getValueAt(row, i);
				}catch(Exception e){
						
				}
				JTextField columnField=(JTextField)c.getColumnField();
				if (columnField!=null){
					columnField.setText(value);	
					columnField.requestFocusInWindow();
				}
				if (c.getJtable()!=null){
					JTable tablex=c.getJtable();
					tablex.setValueAt(value, c.getRow(), c.getColumn());
				}
				c.fillData(value);
			}	
		}
		focus();
		frame.setVisible(false);
		this.getConstructor().dispose();
	}
	

	private CustomTable crearTabla(Object[][] auzx){
		final CustomTable Table=new CustomTable();
		for (int i=0;i<Columns.size();i++) {
			columna c=(columna)Columns.get(i);
			Column col=new Column();
			col.setName(c.getAlias());
			col.setWidth(c.getWidth());
			col.setClass(c.getClase());
			if (c.getNombre().compareTo("")==0){
				col.setEditable(true);
				CheckBoxCellEditor chkce = new CheckBoxCellEditor();
				chkce.setItemListener(this.getConstructor().getItemListener());
				chkce.setTipo(Boolean.class);
				chkce.setName(_Interface._table_chk_sistema);
				col.setCellEditor(chkce.getCellCheck());
				col.setWidth(20);
			}
			Table.addColumn(col);		
		}
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		Table.setFont(fuente);
		Table.setHeaderFont(fuente);
		
		Table.setData(auzx);
		Table.build();
		Table.fillData();
		
		
		return Table;
	}
	
	private CustomTable crearSystemTabla(Object[][] auzx){
		final CustomTable Table=new CustomTable();
		for (int i=0;i<SColumns.size();i++) {
			columna c=(columna)SColumns.get(i);
			Column col=new Column();
			col.setName(c.getAlias());
			col.setWidth(c.getWidth());
			col.setClass(c.getClase());
			if (c.getNombre().compareTo("")==0){
				col.setEditable(true);
				CheckBoxCellEditor chkce = new CheckBoxCellEditor();
				chkce.setItemListener(this.getConstructor().getItemListener());
				chkce.setTipo(Boolean.class);
				chkce.setName(_Interface._table_chk_sistema);
				col.setCellEditor(chkce.getCellCheck());
				col.setWidth(20);
			}else{
				TableColorCellRenderer cellrender=	new TableColorCellRenderer();
				cellrender.setLogic(this);
				col.setCellRenderer(cellrender);		
				
			}
			
			System.out.println("Agregando Columna?"+col.getName());
			Table.addColumn(col);		
		}
		
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		Table.setHeaderFont(fuente);
		Table.setFont(fuente);
		Table.setData(auzx);
		Table.build();
		Table.fillData();
		
		
		return Table;
	}
	private CustomTable crearTablaSelections(Object[][] auzx){
		final CustomTable Table=new CustomTable();
		for (int i=0;i<Columns.size();i++) {
			columna c=(columna)Columns.get(i);
			Column col=new Column();
			col.setName(c.getAlias());
			col.setWidth(c.getWidth());
			col.setClass(c.getClase());
			if (c.getNombre().compareTo("")==0){
				col.setEditable(true);
				CheckBoxCellEditor chkce = new CheckBoxCellEditor();
				chkce.setItemListener(this.getConstructor().getItemListener());
				chkce.setTipo(Boolean.class);
				chkce.setName(_Interface._table_chk_seleccion);
				col.setCellEditor(chkce.getCellCheck());
			}
			Table.addColumn(col);		
		}
		Table.setFont(new Font("Dialog", Font.PLAIN, 10));
		Table.setData(auzx);
		Table.build();
		Table.fillData();
		
		return Table;
	}
	
	private void crearTablaEquivalencia(Object[][] auzx){
		final CustomTable Table=new CustomTable();
		
		
		Column col=new Column();
		col.setName("codigo");
		col.setWidth(120);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("descripcion");
		col.setWidth(200);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("linea");
		col.setWidth(180);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("idproveedor");
		col.setWidth(100);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		
		col=new Column();
		col.setName("nombre");
		col.setWidth(180);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		Table.setFont(new Font("Dialog", Font.PLAIN, 10));
		Table.setData(auzx);
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		Table.setHeaderFont(fuente);
		Table.setFont(fuente);
		
		Table.build();
		Table.fillData();
		
		JTable table=Table.getTable();
		table.setName(_Interface._table_equivalencias);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setDragEnabled(true);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		frame.setJTable_equivalencias(table);
		
	}
	
	
	private void crearTablaDeposito(Object[][] auzx){
		final CustomTable Table=new CustomTable();
		
		
		Column col=new Column();
		col.setName("idarticulo");
		col.setWidth(120);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("descripcion");
		col.setWidth(200);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("linea");
		col.setWidth(180);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("ubicacion");
		col.setWidth(100);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		
		col=new Column();
		col.setName("cantidad");
		col.setWidth(70);
		col.setClass(Double.class);
		col.setEditable(false);
		Table.addColumn(col);
		Table.setData(auzx);
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		Table.setHeaderFont(fuente);
		Table.setFont(fuente);
		Table.build();
		Table.fillData();
		JTable table=Table.getTable();
		table.setName(_Interface._table_deposito);
		table.addMouseListener(this.getConstructor().getMouseListener());
		frame.setJTable_deposito(table);
		
	}
	private void crearTablaArticulos(Object[][] auzx){
		final CustomTable Table=new CustomTable();
		System.out.println("Creando Tabla Articulos");
		
		Column col=new Column();
		col.setName("");
		col.setWidth(20);
		col.setClass(Boolean.class);
		col.setEditable(false);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this.getConstructor().getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_chk_articulos);
		col.setCellEditor(chkce.getCellCheck());
		Table.addColumn(col);
		
		col=new Column();
		col.setName("idarticulo");
		col.setWidth(120);
		col.setClass(String.class);
		col.setEditable(false);
		TableColorCellRenderer cellrender=	new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender=	new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("linea");
		col.setWidth(180);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender=	new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("publico");
		col.setWidth(80);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender=	new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);
		
		
		col=new Column();
		col.setName("stock");
		col.setWidth(70);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender=	new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);
		Table.setData(auzx);
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		Table.setFont(fuente);
		Table.setHeaderFont(fuente);
		Table.setName(_Interface._table_articulo);
		Table.setHeaderFont(fuente);
		
		Table.build();
		Table.fillData();
		JTable _table=Table.getTable();
		_table.setColumnSelectionAllowed(false);
		_table.setRowSelectionAllowed(true);
		frame.setJTable_articulos(_table);
	}
	
	private void crearTablaCatalogos(Object[][] auzx){
		final CustomTable Table=new CustomTable();
		System.out.println("Creando Tabla Catalogos");
		
		Column col;
		col=new Column();
		col.setName("Catalogo");
		col.setWidth(480);
		col.setClass(String.class);
		Table.addColumn(col);
		
		Table.setData(auzx);
		Table.setFont(new Font("Dialog", Font.PLAIN, 10));
		Table.setName(_Interface._table_catalogos);
		Table.addMouseListener(this.getConstructor().getMouseListener());
		Table.build();
		Table.fillData();
		
		frame.setJTable(Table.getTable());
		
	}
	
	private void crearTablaSelecciones(Object[][] auzx){
		final CustomTable Table=new CustomTable();
		
		
		Column col=new Column();
		col.setName("");
		col.setWidth(40);
		col.setClass(Boolean.class);
		col.setEditable(false);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this.getConstructor().getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_chk_seleccion);
		col.setCellEditor(chkce.getCellCheck());
		Table.addColumn(col);
		
		col=new Column();
		col.setName("idarticulo");
		col.setWidth(120);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("linea");
		col.setWidth(180);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		col=new Column();
		col.setName("publico");
		col.setWidth(70);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		
		
		col=new Column();
		col.setName("stock");
		col.setWidth(60);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		Table.setData(auzx);
		Table.setFont(new Font("Dialog", Font.PLAIN, 10));
		Table.setHeaderFont(new Font("Dialog", Font.PLAIN, 10));
		Table.setName(_Interface._table_seleccion);
		Table.build();
		Table.fillData();
		frame.setJTable_seleccion(Table.getTable());
		
	}
	/**
	 * Este metodo solo agrega y marca una seleccion de una tabla origen en la tabla selecciones
	 * @param row
	 * @param origen
	 */
	public void agregarSeleccion(int row,JTable origen){
		JTable selecciones=null;
		try {
			
			selecciones = frame.getJTable_seleccion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int existe=-1;
		if (selecciones!=null){
			existe=this.existeSeleccion(row,origen,selecciones);	
			if (existe<0){
				if (selecciones!=null){
					if (selecciones.getRowCount()>0){
						DefaultTableModel model=(DefaultTableModel) selecciones.getModel();
						model.setRowCount(model.getRowCount()+1);
						existe=model.getRowCount()-1;
						this.agregarSeleccionRenglon(existe,row,origen);
					}else{
						this.createTableSelecciones(row,origen);
						existe=0;
					}
			    }else{
			    	this.createTableSelecciones(row,origen);
			    	existe=0;
			    }
				//
			}
		}else{
			this.createTableSelecciones(row,origen);
		}
		
				
	}
	/**
	 * Este metodo marca el renglon booleano indicado de la tabla destino
	 * @param row
	 * @param destino
	 */
	public void marcarSeleccionRenglon(int row,JTable destino){
		boolean selected=(Boolean)destino.getValueAt(row, 0);
		destino.setValueAt(!selected, row, 0);
	}
	
	public void marcarSeleccionRenglon(int row,boolean selected,JTable destino){
		try {
			System.out.println("Marcando Tabla "+destino.getName()+" fila "+row+" con valor "+selected);
			destino.setValueAt(selected, row, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			/e.printStackTrace();
		}
	}
	
	/**
	 * Este metodo agrega un renglon determinado desde una tabla origen en la tabla de selecciones
	 * @param row
	 * @param desde
	 * @param origen
	 */
	public void agregarSeleccionRenglon(int row,int desde,JTable origen){
		JTable selecciones=frame.getJTable_seleccion();
		boolean error=false;
		int j=1;
		selecciones.setValueAt(true,row,0);
		while (j<origen.getColumnCount()){
				if (j<selecciones.getColumnCount()){
					try {
						selecciones.setValueAt(origen.getValueAt(desde, j), row, j);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						error=true;
					}	
				}
				
			j++;
		}
		
	}
	/**
	 * Metodo para crear la tabla de selecciones con un renglon vacio
	 */
	public void oldcreateTableSelecciones(){
		Runnable _execute=new Runnable(){
			public void run(){
				Object[][] tmp=new Object[1][Columns.size()];
				crearTablaSelecciones(tmp);	
			}
		};
		this.invokeLater(_execute);
	}
	
	public void createTableSelecciones(int row,JTable Origen){
		final JTable _table=Origen;
		final int _row=row;
		Runnable _execute=new Runnable(){
			public void run(){
				Object[][] tmp=new Object[1][6];
				tmp[0][0]=true;
				System.out.println("Creando tabla De Seleccion de Articulos");
				for (int i=0;i<5;i++){
					tmp[0][i+1]=_table.getValueAt(_row, i+1);
					System.out.println("valor("+i+")="+tmp[0][i+1]);
				}
				crearTablaSelecciones(tmp);	
			}
		};
		this.invokeLater(_execute);
	}
	/**
	 * Metodo para cargar las equivalencias dado la cupla codigo,linea e idproveedor 
	 * @param codigo
	 * @param linea
	 * @param idproveedor
	 */
	
	public List<String[]> getEquivalencias(String codigo,String linea,List<String[]> hst,boolean recursive){
		estado="buscando "+codigo+"+"+linea;
		hst.add(new String[]{codigo,linea});
		Object[][] results=data.getEquivalencias(codigo, linea);
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					String _codigo=(String) results[i][0];
					String _linea=(String) results[i][2];
					String[] key=new String[]{_codigo,_linea};
					if(!this.contiene(hst, key)){
						System.out.println("No no contiene la clave "+_codigo+" "+_linea+" explorando.. ");
						if (recursive){
							hst=this.getEquivalencias(_codigo, _linea, hst,recursive);	
						}else{
							System.out.println("Esta suspendida la busqueda recursiva en forma dinamica por el bajo rendimiento de tiempo");	
						}
						
						//	
					}
				}
		}	
		}
	return hst;
	}
	
	public List<String[]> getEquivalenciasIdenticos(String codigo,String linea,String idproveedor,List<String[]> hst){
		Object[][] results=data.getIdenticos(codigo, linea, idproveedor);
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					String _codigo=(String) results[i][0];
					String _linea=(String) results[i][2];
					String[] key=new String[]{_codigo,_linea};
					if(!this.contiene(hst, key)){
						System.out.println("No no contiene la clave "+_codigo+" "+_linea+"? ");
						hst.add(key);	
					}
				}
		}	
		}
	return hst;
	}
	
	public void cargar_equivalencia(String codigo,String linea,String idproveedor,boolean recursive){
		estado="Buscando Equivalencia para "+codigo+" "+linea;
		List<String[]> hst=new ArrayList<String[]>();
		hst=this.getEquivalencias(codigo, linea, hst,recursive);
		
		if (hst.size()>0){
			final List<String[]> _hst=hst;
			Runnable _creation=new Runnable(){
				public void run(){
					
					Object[][] tmp=new Object[][]{{_hst.get(0)[0],"",_hst.get(0)[1],"",""}};
					crearTablaEquivalencia(tmp);		
					if (_hst.size()>1){
						for (int i=1;i<_hst.size();i++){
							
								DefaultTableModel model=(DefaultTableModel) frame.getJTable_equivalencias().getModel();
								int row=model.getRowCount();
								if (row==i){
									model.setRowCount(model.getRowCount()+1);
									row=model.getRowCount()-1;
								}
								String[] key=_hst.get(i);
								frame.getJTable_equivalencias().setValueAt(key[0],row,0);
								frame.getJTable_equivalencias().setValueAt("",row,1);
								frame.getJTable_equivalencias().setValueAt(key[1],row,2);
								frame.getJTable_equivalencias().setValueAt("",row,3);
								frame.getJTable_equivalencias().setValueAt("",row,4);
								
							
						}		
					}
					
					Iterator it = _hst.iterator();
					while (it.hasNext()) { 
						// Get key Object 
						String[] key = (String[])it.next(); 
						cargar_articulos(key[0], key[1]);
						
						if(images.size()<=0){
							try {
								getImages(key[0], key[1]);
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						}
						
					}
					frame.get_btn_siguiente().setEnabled(false);
					frame.get_btn_start().setEnabled(false);
					frame.get_btn_end().setEnabled(false);
					frame.get_txt_total().setText("0");
					frame.get_txt_actual().setText("0");
					hojas=0;
					hoja_actual=0;
					if (images.size()>0){
						hojas=images.size();
						hoja_actual=1;
						mostrar_foto(1);
						frame.get_txt_total().setText(""+images.size());
						frame.get_txt_actual().setText("1");
						if (images.size()>1){
							frame.get_btn_siguiente().setEnabled(true);
							frame.get_btn_start().setEnabled(true);
							frame.get_btn_end().setEnabled(true);
						}	
					}
				}
			};
			this.invokeLater(_creation);
			
			
			
		}
		
		
		
		doneE=true;
	}
	
	public void centrar_imagen(){
		if (frame.getCanvas()!=null){
			frame.getCanvas().maximizar();
		}
	}
	public void _mover_zoomin() {
		int x = 6;
		frame.getCanvas().zoom_add(x);
	}

	public void _mover_zoomout() {
		int x = 6;
		frame.getCanvas().zoom_rem(x);

	}
	
	public void _anterior() {
		if (this.hoja_actual > 1) {
			this.hoja_actual--;
			frame.get_txt_actual().setText("" + hoja_actual);
			this.mostrar_foto(this.hoja_actual);
			if (this.hoja_actual <=1) {
				frame.get_btn_anterior().setEnabled(false);	
			}
			if (this.hoja_actual <this.hojas) {
				frame.get_btn_siguiente().setEnabled(true);
			}
			
		}
	}
	
	public void _siguiente() {
		if (this.hoja_actual < this.hojas) {
			this.hoja_actual++;
			frame.get_txt_actual().setText("" + hoja_actual);
			this.mostrar_foto(this.hoja_actual);
			if (hoja_actual>=this.hojas){
				frame.get_btn_siguiente().setEnabled(false);
			}
			if (this.hoja_actual >1) {
				frame.get_btn_anterior().setEnabled(true);	
			}
		}

	}
	
	private boolean contiene(List<String[]> hst,String[] key){
		Iterator it = hst.iterator();
		boolean tmp=false;
		while (it.hasNext() & !tmp) { 
			String[] _key = (String[])it.next();
			boolean ok=true;
			int j=0;
			while (ok & j<key.length){
				ok=_key[j].compareTo(key[j])==0;
				j++;
			}
			tmp=ok;
		}
		return tmp;
	}
	
	public void cargar_equivalencia_desde_articulo(String idarticulo,boolean recursive){
		estado="Buscando Equivalencia para "+idarticulo;
		Object[][] results=data.getCodigos(idarticulo);
		
		List<String[]> hst=new ArrayList<String[]>();
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					String _codigo=(String) results[i][0];
					String _linea=(String) results[i][2];
					String idproveedor=(String) results[i][3];
					this.cargar_equivalencia(_codigo, _linea, idproveedor,recursive);
				}
			}else{
				frame.setJTable_equivalencias(null);
			}
		}else{
			frame.setJTable_equivalencias(null);
		}
		
	}
	/**
	 * Funcion que devuelve el numero de articulos seleccionados. es decir boolean=true
	 * @return
	 */
	public int getNumberOfSelections(){
		int selections=0;
		JTable selecciones=frame.getJTable_seleccion();
		if (selecciones!=null){
			int i=0;
			while(i<selecciones.getRowCount()){
				boolean selected=(Boolean) selecciones.getValueAt(i, 0);
				if (selected) selections++;
				i++;
			}
		}
		System.out.println("Seleccciones Encontradas?"+selections);
		return selections;
	}
	
	public void setPedido(aplicacion.ventas.pedido.constructor._Constructor pedido){
		this.pedido=pedido;
	}
	aplicacion.ventas.facturador.constructor._Constructor facturador=null;
	public void setFacturador(aplicacion.ventas.facturador.constructor._Constructor facturador){
		this.facturador=facturador;
	}
	public void setTransferencia(aplicacion.inventario.transferencia.constructor._Constructor transferencia){
		this.transferencia=transferencia;
	}
	
	public void setCargaItems(aplicacion.compras.carga.items.constructor._Constructor carga){
		this.carga=carga;
	}
	aplicacion.inventario.transferencia.constructor._Constructor transferencia=null;
	aplicacion.compras.carga.items.constructor._Constructor carga=null;
	aplicacion.ventas.pedido.constructor._Constructor pedido=null;
	public void crear_pedido(){
		System.out.println("Crear Pedido>");
		pedido=new aplicacion.ventas.pedido.constructor._Constructor();
		pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler().Clone());
		pedido.build(this.getConstructor());
		pedido.init();
		aplicacion.ventas.pedido.logic._Logic logic=
			(aplicacion.ventas.pedido.logic._Logic)pedido.getLogic();
		logic.automatico();
	}
	
	
	public void agregar(Object[] item){
		
		if (caller!=null){
			try {
				caller.setText(item[0].toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			if (pedido==null & carga==null & transferencia ==null & facturador==null){
				if (count==0){
					if (preguntar("Confirmar","Crea Pedido Con los items Seleccionados?")){
						this.crear_pedido();
						aplicacion.ventas.pedido.logic._Logic logic=
							(aplicacion.ventas.pedido.logic._Logic)pedido.getLogic();
						logic.agregar(item);
					}
				}	
			}else{
				if (carga!=null){
					aplicacion.compras.carga.items.logic._Logic logic=
					(aplicacion.compras.carga.items.logic._Logic)carga.getLogic();
					
					logic.agregar4(item);
				}else{
					if (pedido!=null){
						aplicacion.ventas.pedido.logic._Logic logic=
							(aplicacion.ventas.pedido.logic._Logic)pedido.getLogic();
						logic.agregar(item);			
					}else{
						if (transferencia!=null){
							aplicacion.inventario.transferencia.logic._Logic logic=
								(aplicacion.inventario.transferencia.logic._Logic)transferencia.getLogic();
							logic.agregar(item);
						}else{
							if (facturador!=null){
								aplicacion.ventas.facturador.logic._Logic logic=
									(aplicacion.ventas.facturador.logic._Logic)facturador.getLogic();
								logic.agregar(item);
							}else{
								
									if (caller!=null){
										this.caller.setText(item[0].toString());	
									}
									
									
								
							}
						}
					}
				}
			}
			
			
			count++;
			
		}
		}
	
	
	/**
	 * MEtodo para exportar las selecciones a un pedido existente. O crear uno nuevo
	 */
	public void exportar(){
		int selections=this.getNumberOfSelections();
		boolean exit=false;
		if (selections>0){
			exit=true;
			JTable selecciones=frame.getJTable_seleccion();
			if (selecciones!=null){
				int i=0;
				while(i<selecciones.getRowCount()){
					boolean selected=(Boolean) selecciones.getValueAt(i, 0);
					Object[] selection=new Object[selecciones.getColumnCount()-1];
					for (int j=0;j<selection.length;j++){
						selection[j]=selecciones.getValueAt(i, j+1);
					}
					if (selected){
						this.agregar(selection);	
					}
					i++;
				}
			}
		}else{
			exit=preguntar("Confirma","No hay items Seleccionado para exportar. Cierra Esta Ventana?");
		}
		if (exit){
			frame.setVisible(false);
			focus();
			this.getConstructor().dispose();	
		}
		
	}
	
	private boolean cargado(String idarticulo){
		boolean cargado=false;
		int i=0;
		while (i<frame.getJTable_articulos().getRowCount() & !cargado){
			String _idarticulo="";
			try {
				_idarticulo=frame.getJTable_articulos().getValueAt(i,1).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			cargado=(_idarticulo.compareTo(idarticulo)==0);
			i++;
		}
		return cargado;
	}
	
public void evaluar_relaciones(JCheckBox chk){
	if (chk.isSelected()){
		frame.getJTabbedPane1().setSelectedIndex(1);
		String idarticulo=frame.get_txt_detalle_idarticulo().getText();
		if (idarticulo.compareTo("")!=0){
			this.cargar_relaciones(idarticulo);
		}
	}else{
		frame.getJTabbedPane1().setSelectedIndex(0);
		frame.setJTable_relaciones(null);
	}
}
	public void cargar_deposito(String _idarticulo){
		Object[][] results=data.getDeposito(_idarticulo);
		for (int i=0;i<results.length;i++){
			String cantidad=(String) results[i][4];
			double _cantidad=0.0;
			try {
				_cantidad=new Double(cantidad);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			results[i][4]=_cantidad;
		}
		this.crearTablaDeposito(results);
	}
	
	
	public void cargar_relaciones(String _idarticulo) {
		Object[][] results = data.getArticulosRelacionados(_idarticulo);
		frame.setJTable_relaciones(null);
		if (results != null) {
			if (results.length > 0) {
				Object[][] tmp = new Object[results.length][results[0].length + 1];
				for (int i = 0; i < results.length; i++) {
					tmp[i][0] = false;
					for (int j = 0; j < results[0].length; j++) {
						tmp[i][j + 1] = results[i][j];
					}

				}

				if (tmp != null) {
					if (tmp.length > 0) {
						this.create_table_relaciones(tmp);
					}
				}
			}
		}
	}
	
	public void cargar_equivalencias(String _idarticulo){
		Object[][] results=data.getEquivalencias(_idarticulo);
		if (results!=null){
			for (int i=0;i<results.length;i++){
				String idarticulo=(String) results[i][0];
				if (!this.cargado(idarticulo)){
					boolean empty=true;
					DefaultTableModel model=(DefaultTableModel) frame.getJTable_articulos().getModel();
					int row=model.getRowCount()-1;
					try {
						empty=frame.getJTable_articulos().getValueAt(row, 1).toString().compareTo("")==0;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (!empty){
						model.setRowCount(model.getRowCount()+1);
						row=model.getRowCount()-1;
					}
					boolean selected=this.isSelected((String)results[i][0]);
					frame.getJTable_articulos().setValueAt(selected, row, 0);
					
					for (int j=0;j<results[0].length;j++){
						frame.getJTable_articulos().setValueAt(results[i][j].toString(), row, j+1);	
					}
					
				}
			}	
		}
		
	}
		
	
	public void cargar_articulos(String codigo,String linea){
		
		
		Object[][] results=data.getArticulos(codigo, linea);
		if (results!=null){
			if (results.length>0){
						
				System.out.println("Cargando Articulos para "+codigo+" "+linea+" ?"+results.length);
				
				
					for (int i=0;i<results.length;i++){
					String idarticulo=(String) results[i][0];
					if (!this.cargado(idarticulo)){
						boolean empty=true;
						DefaultTableModel model=(DefaultTableModel) frame.getJTable_articulos().getModel();
						int row=model.getRowCount()-1;
						
						try {
							empty=frame.getJTable_articulos().getValueAt(row, 1).toString().compareTo("")==0;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (!empty){
							model.setRowCount(model.getRowCount()+1);
							row=model.getRowCount()-1;
						}
						boolean selected=this.isSelected((String)results[i][0]);
						frame.getJTable_articulos().setValueAt(selected, row, 0);
						
						for (int j=0;j<results[0].length;j++){
							frame.getJTable_articulos().setValueAt(results[i][j].toString(), row, j+1);	
						}
						this.cargar_equivalencias(idarticulo);
					}
					
				}
				
			}
		}
		int len=0;
		if (frame.getJTable_articulos()!=null){
			len=frame.getJTable_articulos().getRowCount();
		}
		
	}
	

	
public void cargar_articulos_dinamicos(String codigo,String linea){
		
		Object[][] results=data.getArticuloDinamico(codigo, linea);
		int total=0;
		if (results!=null){
			if (results.length>0){
				total++;
				
				
				
				for (int i=0;i<results.length;i++){
					String idarticulo=(String) results[i][0];
					if (!this.cargado(idarticulo)){
							boolean empty=true;
							DefaultTableModel model=(DefaultTableModel) frame.getJTable_articulos().getModel();
							int row=model.getRowCount()-1;
							try {
								empty=frame.getJTable_articulos().getValueAt(row, 1).toString().compareTo("")==0;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (!empty){
								model.setRowCount(model.getRowCount()+1);
								row=model.getRowCount()-1;
							}
						boolean selected=this.isSelected((String)results[i][0]);
						frame.getJTable_articulos().setValueAt(selected,row,0);
						for (int j=0;j<results[0].length;j++){
							frame.getJTable_articulos().setValueAt(results[i][j].toString(), row, j+1);	
						}
					}
					
				}
				
			}
		}
		int len=0;
		
			if (frame.getJTable_articulos()!=null){
				len=frame.getJTable_articulos().getRowCount();
			}	
		
		
		
	}
	private JLabel getCustomLabel(int w,String label){
		JLabel lab=new JLabel(label);
		lab.setSize(w, 20);
		lab.setMaximumSize(new Dimension(w,20));
		lab.setMinimumSize(new Dimension(w,20));
		return lab;
	}
	
	private JTextField getCustomField(int w){
		JTextField txt=new JTextField();
		txt.setSize(w,18);
		txt.setMaximumSize(new Dimension(w,18));
		txt.setMinimumSize(new Dimension(w,18));
		txt.setName("_column");
		return txt;
	}
	
	/**
	 * MEtodo para crear la tabla Fields. De filtros de busqueda
	 */
	private void create_table_fields(){
		CustomTable Table=new CustomTable();
		Object[][] results=new Object[1][Filters.size()];
		for (int i=0;i<Filters.size();i++) {
			results[0][i]="";
		}
		for (int i=0;i<Filters.size();i++) {
			Filtro f=(Filtro) Filters.get(i);
			Column col=new Column();
			col.setName(f.getAlias());
			col.setWidth(f.getWidth());
			CellEditor pce = new CellEditor();
			
			pce.setSelectedBackgroundColor(Color.lightGray);
			pce.setName(_Interface._column);
			pce.addKeyListener(this.getConstructor().getKeyListener());
			pce.setTipo(String.class);
			col.setCellEditor(pce.getCellEditor());
			col.setEditable(true);
			Table.addColumn(col);		
		}
		
		Table.setData(results);	
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		Table.setHeaderFont(fuente);
		Table.setFont(fuente);
		Table.build();
		Table.fillData();
		
		JTable table=Table.getTable();
		table.setName(_Interface._table_fields);
		table.addMouseListener(this.getConstructor().getMouseListener());
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),"none" );
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),"none" );
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"none" );
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN,0),"none" );
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP,0),"none" );
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0),"none" );
		
		frame.setJTable_fields(table);
	
		
	}
	
	/**
	 * Metodo para crear los filtros y sus acumuladores. para las busquedas acumulativas
	 */
	private void build_fields(){
		this.acumFilters=new LinkedList();
		for (int i=0;i<Filters.size();i++){
			this.acumFilters.add(new LinkedList());
		}
		this.SacumFilters=new LinkedList();
		for (int i=0;i<SFilters.size();i++){
			this.SacumFilters.add(new LinkedList());
		}
		this.create_table_fields();
		this.frame.setVisible(true);
	}
	
	
	
	public void _compatibleSearch(){
		this.acumulate_sum();
		if (this.has_acumulate_sum()){
			this.customSearch();	
		}else{
			aviso("Ingrese las descripciones para filtrar ");
			done=true;	
		}
	}
	
	/**
	 * MEtodo para disparar la busqueda desde el boton lupita 
	 */
	public void search() {
		this.frame.getJProgressBar().setIndeterminate(true);
		estado="Buscando";
		clean=false;
		_compatibleSearch();
	}
	
	/**
	 * Metodo que dispara la busqeuda a partir de un TextField. Cuando se presiona F5
	 * @param tx
	 * @param col
	 */
	public void search(JTextField tx,int col){
		frame.getJTable_fields().setValueAt(tx.getText(), 0, col);
		tx.setText("");
		tx.requestFocusInWindow();
		this.goCargar();	
	}
	

	public String getIdconector() {
		return idconector;
	}

	public void setIdconector(String idconector) {
		this.idconector = idconector;
	}

	/**
	 * Metodo para crear la tabla de busqueda de sistema a partir de los resultados obtenidos
	 * @param auzx
	 */
	private void createResults_sistema(Object[][] auzx){
		frame.repaint();
		CustomTable create=this.crearSystemTabla(auzx);
		Font fuente=new Font("Arial", Font.PLAIN, 9);
		create.setHeaderFont(fuente);
		JTable table=create.getTable();
		table.setName(_Interface._table_sistema);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		table.setFont(fuente);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setDragEnabled(true);
		
		
		frame.setJTable_sistema(table);
		//frame.repaint();
	}
	/**
	 * Metodo para crear la tabla de busqueda de proveedores a partir de los resultados obtenidos
	 * @param auzx
	 */
	private void createResults_proveedores(Object[][] auzx){
		frame.repaint();
		CustomTable create=this.crearTabla(auzx);
		Font fuente=new Font("Arial", Font.PLAIN, 9);
		create.setHeaderFont(fuente);
		JTable table=create.getTable();
		table.setName(_Interface._table_proveedores);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		table.setFont(fuente);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setDragEnabled(true);
		frame.setJTable_proveedores(table);
		frame.repaint();
	}
	
	/**
	 * Metodo para marcar los articulos que se muestran el una tabla de resultados destino que hayan sido seleccionados previamente
	 * @param row
	 * @param selected
	 */
	public void marcarEnResultados(int row,boolean selected,JTable destino){
		JTable origen=frame.getJTable_seleccion();
		int existe=this.existeSeleccion(row, origen, destino);
		if (existe>=0){
			destino.setValueAt(selected, existe, 0);
		}
	}
	public void selection(JTextField tx,int col){
		int row=frame.getJTable_fields().getSelectedRow();
		this.Close(frame.getJTable_fields(), row);
	}
	
	public Object[][] processData(Object[][] results){
		Object[][] tmp=new Object[results.length][results[0].length+1];
		for (int i=0;i<results.length;i++){
			//check si ya no fue elegido
			
			boolean selected=false;
			for (int j=0;j<results[0].length;j++){
				tmp[i][j+1]=results[i][j];	
			}
			tmp[i][0]=selected;
			
		}
		return tmp;
	}
	
	public Object[][] processData_sistema(Object[][] results){
		Object[][] tmp=new Object[results.length][results[0].length+1];
		for (int i=0;i<results.length;i++){
			//check si ya no fue elegido
			
			boolean selected=this.isSelected((String)results[i][0]);
			for (int j=0;j<results[0].length;j++){
				tmp[i][j+1]=results[i][j];	
			}
			tmp[i][0]=selected;
			
		}
		return tmp;
	}
	
	private boolean SearchProveedores(Object[][] auzx){
		boolean empty=true;
		if (auzx!=null){
			if (auzx.length>0){
				auzx=this.processData(auzx);
				final Object[][] _results=auzx;
				empty=false;
				Runnable _execute = new Runnable() {
			        public void run() {
			        	
			        	createResults_proveedores(_results);
			        }
			    };
			    this.invokeLater(_execute);
			}
		}else{
			Runnable _execute2 = new Runnable(){
		        public void run() {
		        	int i=found("Proveedores");
		        	if (i>0){
		        		frame.getJTabbedPane2().removeTabAt(i);
		        	}	
		        }
		    };
		    this.invokeLater(_execute2);
		    
		}
		
		return empty;
	}
	
	private boolean SearchDocumentos(Object[][] auzx){
		boolean empty=true;
		if (auzx!=null){
			if (auzx.length>0){
				
				final Object[][] _results=auzx;
				empty=false;
				Runnable _execute = new Runnable() {
			        public void run() {
			        	 create_table_documentos(_results);
			        }
			    };
			    this.invokeLater(_execute);
			}
		}
		return empty;
	}
	private boolean SearchCatalogos(Object[][] auzx){
		boolean empty=true;
		if (auzx!=null){
			if (auzx.length>0){
				
				final Object[][] _results=auzx;
				empty=false;
				Runnable _execute = new Runnable() {
			        public void run() {
			        	 crearTablaCatalogos(_results);
			        }
			    };
			    this.invokeLater(_execute);
			}
		}
		return empty;
	}
	private boolean createArticulos(Object[][] auzx){
		boolean empty=true;
		if (auzx!=null){
			if (auzx.length>0){
				
				final Object[][] _results=auzx;
				empty=false;
				Runnable _execute = new Runnable() {
			        public void run() {
			        	 crearTablaArticulos(_results);
			        }
			    };
			    this.invokeLater(_execute);
			}
		}
		return empty;
	}
	
	public void addTabSistema(int length){
		final String _title="Sistema";
		final int _length=length;
		Runnable _execute=new Runnable(){
			public void run(){
				int i=found(_title);
				if (i<0){
					frame.getJTabbedPane2().addTab("Resultado Base "+_title, null, frame.getJPanel5(), null);
					i=found(_title);
				}
				frame.getJTabbedPane2().setTitleAt(i, "Resultado Base "+_title+"");		
			}
		};
		this.invokeLater(_execute);
	}
	
	public void addTabProveedores(int length){
		final String _title="Proveedores";
		final int _length=length;
		Runnable _execute=new Runnable(){
			public void run(){
				int i=found(_title);
				if (i<0){
					frame.getJTabbedPane2().addTab("Resultado Base "+_title, null, frame.getJPanel4(), null);
					i=found(_title);
				}
				
				frame.getJTabbedPane2().setTitleAt(i, "Resultado Base "+_title+"");		
			}
		};
		this.invokeLater(_execute);
	}
	
	public void addTabDocumentos(int length){
		final String _title="Documentos";
		final int _length=length;
		Runnable _execute=new Runnable(){
			public void run(){
				int i=found(_title);
				if (i<0){
					frame.getJTabbedPane2().addTab("Resultado Base "+_title, null, frame.getJPanel9(), null);
					i=found(_title);
				}
				frame.getJTabbedPane2().setTitleAt(i, "Resultado Base "+_title+"");		
			}
		};
		this.invokeLater(_execute);
	}
	
	public void removeTab(String title){
		final String _title=title;
		
		Runnable _execute=new Runnable(){
			public void run(){
				int i=found(_title);
				if (i>=0){
					frame.getJTabbedPane2().removeTabAt(i);
				}
			}
		};
		this.invokeLater(_execute);
	}
	
	private void customSearch(){
			
		Object[][] resultados_proveedores=null;
		Object[][] resultados_sistema=null;
		Object[][] resultados_documentos=null;
		Object[][] resultados_catalogos=null;
		boolean empty_proveedores=true;
		boolean empty_sistema=true;
		boolean empty_documentos=true;
		boolean empty_catalogos=true;
		if (frame.get_lst_modo().getSelectedIndex()==0){
			this.removeTab("Proveedores");
			this.removeTab("Documentos");
			
		}
		if (frame.get_lst_modo().getSelectedIndex()==1){
			this.removeTab("Sistema");
			this.removeTab("Documentos");
		}
		if (frame.get_lst_modo().getSelectedIndex()==2){
			this.removeTab("Sistema");
			this.removeTab("Proveedores");
		}
	
		if(frame.get_lst_modo().getSelectedIndex()==0|frame.get_lst_modo().getSelectedIndex()==3){
			
			if (idconector.compareTo("")!=0){
				resultados_sistema=data.getConnectionHandler().getConnector(idconector).getResults(this.doSystemQuerys());
			}else{
				resultados_sistema=data.getConnectionHandler().getDefaultConnector().getResults(this.doSystemQuerys());
			}
			
			if (resultados_sistema!=null){
				int length=0;
				try {
					length=resultados_sistema.length;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.addTabSistema(length);
				empty_sistema=this.SearchSistema(resultados_sistema);	
			}
			
				
		}
		
		if(frame.get_lst_modo().getSelectedIndex()==1|frame.get_lst_modo().getSelectedIndex()==3){
			if (idconector.compareTo("")!=0){
				resultados_proveedores=data.getConnectionHandler().getConnector(idconector).getResults(this.doQuerys());
			}else{
				resultados_proveedores=data.getConnectionHandler().getDefaultConnector().getResults(this.doQuerys());
			}
			
			int length=0;
			try {
				length=resultados_proveedores.length;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.addTabProveedores(length);
			empty_proveedores=this.SearchProveedores(resultados_proveedores);
			
				
		}
		
		
		
		if (frame.get_lst_modo().getSelectedIndex()==2|frame.get_lst_modo().getSelectedIndex()==3){
		
			resultados_documentos=data.buscar(this.getWhereStringDocumentos(1));
			int length=0;
			try {
				length=resultados_documentos.length;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.addTabDocumentos(length);
			
			empty_documentos=this.SearchDocumentos(resultados_documentos);
		}
		
		
		
		/*
		resultados_catalogos=data.getCatalogos(this.getWhereStringCatalogos(1));
		if (resultados_catalogos!=null){
			frame.getJTabbedPane2().setTitleAt(3, "Catalogos("+resultados_catalogos.length+")");
			empty_documentos=this.SearchCatalogos(resultados_catalogos);
		}else{
			frame.getJTabbedPane2().setTitleAt(3, "Catalogos(0)");
		}
		*/
		if (empty_proveedores & empty_sistema & empty_documentos){
			this.aviso_resultado_nulo();
			
			this.clean();
		}else{
			
		}
		
		if (empty_proveedores){
			if (frame.get_lst_modo().getSelectedIndex()==1|frame.get_lst_modo().getSelectedIndex()==3){
				try {
					frame.setJTable_proveedores(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		if (empty_sistema){
			if (frame.get_lst_modo().getSelectedIndex()==0|frame.get_lst_modo().getSelectedIndex()==3){
				try {
					frame.setJTable_sistema(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		if (empty_documentos){
			if (frame.get_lst_modo().getSelectedIndex()==2|frame.get_lst_modo().getSelectedIndex()==3){
				try {
					frame.setJTable_documentos(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
		}	
		
			try {
				frame.getJTabbedPane2().setSelectedIndex(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		done=true;	
		System.out.println("DONE?>"+done);
	}
		
	public void expand(){
		frame.setSize(868, 790);
		this.centrar();
		int row=-1;
		int selected=frame.getJTabbedPane2().getSelectedIndex();
		int i=found("Sistema");
		if (i==selected){
			try {
				row = frame.getJTable_sistema().getSelectedRow();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.goShowItSistema(row);	
		}else{
			i=found("Proveedores");
			if (i==selected){
				try {
					row = frame.getJTable_proveedores().getSelectedRow();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.goShowIt(row);
			}
		}
		
		
		
		
		
	}
	public void conpact(){
		frame.setSize(866, 448);
		
		this.centrar();
	}
	
	public int found(String tab){
		boolean found=false;
		int i=0;
		while(i<frame.getJTabbedPane2().getTabCount() & !found){
			if (frame.getJTabbedPane2().getTitleAt(i).contains(tab)){
				found=true;
			}else{
				i++;
			}
		}
		if (!found){
			i=-1;
		}
		return i;
	}
	private boolean SearchSistema(Object[][] auzx){
		boolean empty=true;
		if (auzx!=null){
			if (auzx.length>0){
				
				empty=false;
				final Object[][] _results=auzx;
				Runnable _execute = new Runnable() {
			        public void run() {
			        	 createResults_sistema(processData_sistema(_results));
			        }
			    };
			    this.invokeLater(_execute);
			}
		}
		return empty;
	}
	
	public void aviso_resultado_nulo(){
			Runnable _execute = new Runnable() {
			    public void run() {
			    	aviso("Resultado Nulo para su Busqueda");
			    }
			};
			this.invokeAndWait(_execute);
	}
	
	public _Logic(){
		 Filters=new LinkedList();
 	     Columns=new LinkedList();
		 Orders=new LinkedList();
		 acumFilters=new LinkedList();
		 excluders=new LinkedList();
		 iniValues=new LinkedList();
		 Restriction="";
		 Group="";
		 
		 SFilters=new LinkedList();
 	     SColumns=new LinkedList();
		 SOrders=new LinkedList();
		 SacumFilters=new LinkedList();
		 Sexcluders=new LinkedList();
		 SiniValues=new LinkedList();
		 SRestriction="";
		 SGroup="";
		 idconector="";
		 this.addMasterColumn();
		 this.addSystemMasterColumn();
	}
	public void Down(JTable table){
		int row=-1;
		try {
			row=table.getSelectedRow();	
		}catch(Exception e){	}
		try {

			if (row<table.getRowCount()-1){
				table.changeSelection(row+1, 0, false, false);
				
				table.transferFocus();
				
				if (table.getName()==_Interface._table_proveedores){
					this.goShowIt(row+1);
				}
				if (table.getName()==_Interface._table_sistema){
					this.goShowItSistema(row+1);	
				}
				
				//frame.requestFocusInWindow();
				//frame.getJTable_fields().requestFocusInWindow();
			}
		}catch(Exception e){	}
		
	}
	
	public void Down(JTextField tx,int col){
		int i=frame.getJTabbedPane2().getSelectedIndex();
		if (i==1){
			this.Down(frame.getJTable_proveedores());
		}
		if (i==0){
			this.Down(frame.getJTable_sistema());
		}
		if (i==2){
			this.Down(frame.getJTable_documentos());
		}
		this.focus(col);
		tx.requestFocusInWindow();
	}

	public void focus(int col){
		frame.requestFocus();
		frame.getJTable_fields().requestFocusInWindow();
		frame.getJTable_fields().changeSelection(0,col,false,false);
		frame.getJTable_fields().editCellAt(0,col);
		frame.getJTable_fields().transferFocus();
	}
	
	public void DoSomethingSeleccion(JTextField tx,int row,int col,JTable table){
		this.evaluar_tabla_proveedores(row);
	}
	public void setModos(){
		frame.get_lst_modo().removeAllItems();
		frame.get_lst_modo().addItem("Sistema");
		frame.get_lst_modo().addItem("Proveedores");
		frame.get_lst_modo().addItem("Catalogos");
		frame.get_lst_modo().addItem("Todo");
	}
	public void Up(JTable table){
		int row=-1;
		try {
			row=table.getSelectedRow();	
		}catch(Exception e){	}
		
		try {
			if (row>0){
				table.changeSelection(row-1, 0, false, false);
				table.transferFocus();
				if (table.getName()==_Interface._table_proveedores){
					this.goShowIt(row-1);
				}
				if (table.getName()==_Interface._table_sistema){
					this.goShowItSistema(row-1);	
				}
				frame.requestFocusInWindow();
				table.requestFocusInWindow();
			}
		
		}catch(Exception e){
		}
	}
	
	
	public void Up(JTextField tx,int col){
		int i=frame.getJTabbedPane2().getSelectedIndex();
		if (i==1){
			this.Up(frame.getJTable_proveedores());
		}
		if (i==0){
			this.Up(frame.getJTable_sistema());
		}
		if (i==2){
			this.Up(frame.getJTable_sistema());
		}
		this.focus(col);
		tx.requestFocusInWindow();
	}
	
	public void setTitle(String title){
		this.title=title;
		frame.setTitle(title);
	}
	
	public void init(){
		images=new ArrayList<BufferedImage>();
	 this.build_fields();
	 this.setModos();
	 this.setFocus();
	 
	}
	
	public void transferNextFocus(JTextField tx,int col){
		if (col<this.Filters.size()-1){
			this.focus(col+1);
		}else{
			this.focus(0);
		}
	}
	public void transferBackFocus(JTextField tx,int col){
		if (col>0){
			this.focus(col-1);	
		}else{
			this.focus(this.Filters.size()-1);
		}
		
	}
	
	public void evaluat_escape(JTextField tx,int col){
		if (clean){
			this.exit();	
		}else{
			this.clean(tx, col);
		}
	}
	
	
	
	public void quitarSeleccion(int row,JTable origen,JTable destino){
		int existe=-1;
		existe=this.existeSeleccion(row,origen,destino);
		if (existe>=0){
			this.marcarSeleccionRenglon(existe, false,destino);
		}
	}
	
	
	
	
	public void evaluar_tabla_sistema(JTextField tx,int col){
		String valor="";
		
		if (tx.getText()!=null){
			if (tx.getText().compareTo("")!=0){
				valor=tx.getText();
			}
		}
		if (valor.compareTo("")!=0){
			search(tx,col);
		}else{
			int row=frame.getJTable_sistema().getSelectedRow();
			boolean chk=false;
			if (row>=0){
				try {
					chk=(Boolean)frame.getJTable_sistema().getValueAt(row, 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.getJTable_sistema().setValueAt(!chk,row, 0);
				this.evaluar_tabla_sistema(!chk, row);
			}	
		}
		
		
	}
	
	public void evaluar_tabla_f12(JTextField tx,int col){
		int row=frame.getJTable_sistema().getSelectedRow();
		if (row>=0){
				this.registrar_faltante(row);
		}	
	}
	
	
	public void evaluar_tabla_sistema(boolean chk,int row){
		JTable seleccion=frame.getJTable_seleccion();
		System.out.println("Chk??"+chk+" en Tabla sistema fila "+row);
		//evaluar_tabla_sistema(chk, row);
		
		if (caller!=null){
			this.quitar_selecciones();
		}
		this.goShowItSistema(row);
		int n=this.existeSeleccion(row, frame.getJTable_sistema(), seleccion);
		System.out.println("Existe seleccion en selecciones?"+n);
		if (chk){
			if (n<0){
				
				this.agregarSeleccion(row, frame.getJTable_sistema());	
			}	else{
				this.marcarSeleccionRenglon(n, chk,frame.getJTable_seleccion());
			}
		}else{
			if (n>=0){
				this.marcarSeleccionRenglon(n, chk,frame.getJTable_seleccion());
			}
		}
		
		//this.marcarSeleccionRenglon(n, chk,frame.getJTable_seleccion());
		n=this.existeSeleccion(row, frame.getJTable_sistema(), frame.getJTable_articulos());
		System.out.println("Existe seleccion en articulos?"+n);
		this.marcarSeleccionRenglon(n, chk,frame.getJTable_articulos());
	}
	
	public void evaluar_tabla_selecciones(boolean chk,int row){
		JTable seleccion=frame.getJTable_seleccion();
		System.out.println("Chk??"+chk+" en Tabla sistema fila "+row);
		int n=this.existeSeleccion(row, seleccion, frame.getJTable_sistema());
		System.out.println("Existe seleccion en selecciones?"+n);
		this.marcarSeleccionRenglon(n, chk,frame.getJTable_sistema());
		n=this.existeSeleccion(row, seleccion, frame.getJTable_articulos());
		System.out.println("Existe seleccion en articulos?"+n);
		this.marcarSeleccionRenglon(n, chk,frame.getJTable_articulos());
	}
	
	public boolean isSelected(String idarticulo){
		boolean selected=false;
		int i=0;
		boolean ok=false;
		try {
			ok=frame.getJTable_seleccion()!=null;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (ok){
			while (i<frame.getJTable_seleccion().getRowCount() & !selected){
				String _idarticulo="";
				try {
					_idarticulo = frame.getJTable_seleccion().getValueAt(i, 1).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (_idarticulo.compareTo("")!=0){
					if (_idarticulo.compareTo(idarticulo)==0){
						selected=(Boolean)frame.getJTable_seleccion().getValueAt(i, 0);
					}	
				}
				
				i++;
			}	
		}
		return selected;
	}
	
	public void evaluar_tabla_articulos(boolean chk,int row){
		JTable seleccion=frame.getJTable_seleccion();
		System.out.println("Chk??"+chk+" en Tabla sistema fila "+row);
		int n=this.existeSeleccion(row, frame.getJTable_articulos(), seleccion);
		System.out.println("Existe seleccion en selecciones?"+n);
		if (chk){
			if (n<0){
				this.agregarSeleccion(row, frame.getJTable_articulos());	
			}else{
				this.marcarSeleccionRenglon(n, chk,frame.getJTable_seleccion());
			}
		}else{
			if (n>=0){
				this.marcarSeleccionRenglon(n, chk,frame.getJTable_seleccion());
			}
		}
		
		
		n=this.existeSeleccion(row, frame.getJTable_articulos(), frame.getJTable_sistema());
		System.out.println("Existe seleccion en articulos?"+n);
		this.marcarSeleccionRenglon(n, chk,frame.getJTable_sistema());
		
	}
	
	public void evaluar_tabla_relaciones(boolean chk,int row){
		JTable seleccion=frame.getJTable_seleccion();
		System.out.println("Chk??"+chk+" en Tabla sistema fila "+row);
		int n=this.existeSeleccion(row, frame.getJTable_relaciones(), seleccion);
		System.out.println("Existe seleccion en selecciones?"+n);
		if (chk){
			if (n<0){
				this.agregarSeleccion(row, frame.getJTable_relaciones());	
			}else{
				this.marcarSeleccionRenglon(n, chk,frame.getJTable_relaciones());
			}
		}else{
			if (n>=0){
				this.marcarSeleccionRenglon(n, chk,frame.getJTable_relaciones());
			}
		}
		
		
		n=this.existeSeleccion(row, frame.getJTable_relaciones(), frame.getJTable_sistema());
		System.out.println("Existe seleccion en articulos?"+n);
		this.marcarSeleccionRenglon(n, chk,frame.getJTable_sistema());
		
	}
	
	public void goShowItSistema(int row){
		images=new ArrayList<BufferedImage>();
		frame.getCanvas().resetVars();
		frame.getCanvas().setVisible(false);
		if (row>=0 & row<frame.getJTable_sistema().getRowCount()){
			this.evaluar_tabla_sistema(row);
		}
	}
	
	public void cargarStock(String idarticulo) {
		Object[][] results = data.getStock(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_stock(results);
			}
		}
	}
	
	private void create_table_stock(Object[][] results) {
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		Column col = null;

		col = new Column();
		col.setName("fecha");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("cuenta");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("nombre");
		col.setWidth(120);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		col = new Column();
		
		col.setName("tc");
		col.setWidth(60);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(130);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("entrada");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("salida");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("saldo");
		col.setWidth(90);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		col = new Column();
		
		col.setName("vendedor");
		col.setWidth(100);
		col.setEditable(false);
		col.setClass(String.class);
		table.addColumn(col);
		results = this.procesar_datos_stock(results);
		table.setData(results);
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		JTable _table = table.getTable();

		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		_table.getTableHeader().addMouseListener(
				this.getConstructor().getMouseListener());

		_table.setColumnSelectionAllowed(false);
		_table.setRowSelectionAllowed(true);
		frame.setJTable_stock(table.getTable());
	}

	public Object[][] procesar_datos_stock(Object[][] results) {

		double entradas = 0.0;
		double salidas = 0.0;
		double saldo = 0;
		double precio = 0;
		for (int i = 0; i < results.length; i++) {
			String _e = (String) results[i][5];
			String _s = (String) results[i][6];
			String _p = (String) results[i][8];
			double _entrada = 0.0;
			double _salida = 0.0;
			double _saldo = 0.0;
			double _precio = 0.0;
			try {
				_entrada = new Double(_e);
				_salida = new Double(_s);
				_precio = new Double(_p);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			entradas += _entrada;
			salidas += _salida;
			_saldo = _entrada - _salida;
			saldo += _saldo;
			results[i][7] = saldo;
			results[i][8] = _precio;
		}
		return results;
	}

	public void evaluar_tabla_sistema(int row){
		String idarticulo=frame.getJTable_sistema().getValueAt(row, 1).toString();
		String equivalencia="";
		String descripcion="";
		String rubro="";
		String subrubro="";
		String marca="";
		String linea="";
		String vehiculo="";
		String precio="";
		try {
			descripcion = frame.getJTable_sistema().getValueAt(row, 2).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		try {
			linea = frame.getJTable_sistema().getValueAt(row, 3).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//String idproveedor=frame.getJTable22().getValueAt(row, 8).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			precio=frame.getJTable_proveedores().getValueAt(row, 10).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		this.fillExtra(idarticulo);
		if (frame.get_chk_multimedia().isSelected()){
			frame.get_txt_codigo().setText(idarticulo);
			frame.get_txt_descripcion().setText(descripcion);
			frame.get_txt_linea().setText(linea);
			frame.get_txt_modelo().setText(vehiculo);
			frame.get_txt_rubro().setText(rubro);
			frame.get_txt_subrubro().setText(subrubro);
			frame.get_txt_marca().setText(marca);
			this.cargarStock(idarticulo);
			this.cargar_equivalencia_desde_articulo(idarticulo,false);
			
		}
		
	}
	public void show_tabla_equivalencia(int row){
		String codigo=frame.getJTable_equivalencias().getValueAt(row, 0).toString();
		String linea=frame.getJTable_equivalencias().getValueAt(row, 2).toString();
		String idproveedor=frame.getJTable_equivalencias().getValueAt(row, 3).toString();
		Object[][] results=data.getCodigo(codigo, linea, idproveedor);
		
		if (results!=null){
			if (results.length>0){
				frame.get_txt_codigo().setText(codigo);
				frame.get_txt_descripcion().setText((String)results[0][1]);
				frame.get_txt_linea().setText(linea);
				frame.get_txt_modelo().setText((String)results[0][3]);
				frame.get_txt_rubro().setText((String)results[0][5]);
				frame.get_txt_subrubro().setText((String)results[0][6]);
				frame.get_txt_marca().setText((String)results[0][2]);
				
				cargar_equivalencia(codigo,linea,idproveedor,false);		
			}else{
				this.aviso("No esta cargado este Producto Equivalente");
			}
		}else{
			this.aviso("No esta cargado este Producto Equivalente");
		}
		
	}
	public void evaluar_tabla_proveedores(int row){
		if (frame.getJTable_proveedores()!=null){
			if (row>=0 & row<frame.getJTable_proveedores().getRowCount()){
				this._evaluar_tabla_proveedores(row);
			}
		}
	}
	
	public void cargar_mas(){
		String codigo=frame.get_txt_codigo().getText();
		if (data.esArticulo(codigo)){
			this.cargar_equivalencia_desde_articulo(codigo, true);
		}else{
			String linea=frame.get_txt_linea().getText();
			String idproveedor="";
			cargar_equivalencia(codigo,linea,idproveedor,true);	
		}
		done=true;
	}
	
	private void _evaluar_tabla_proveedores(int row){
		String codigo=frame.getJTable_proveedores().getValueAt(row, 1).toString();
		String equivalencia="";
		String descripcion="";
		String rubro="";
		String subrubro="";
		String marca="";
		String linea="";
		String vehiculo="";
		String precio="";
		try {
			descripcion = frame.getJTable_proveedores().getValueAt(row, 2).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			rubro = frame.getJTable_proveedores().getValueAt(row, 6).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			subrubro = frame.getJTable_proveedores().getValueAt(row, 7).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			linea = frame.getJTable_proveedores().getValueAt(row, 5).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String idproveedor="";
		try {
			idproveedor=frame.getJTable_proveedores().getValueAt(row, 8).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			marca = frame.getJTable_proveedores().getValueAt(row, 3).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			vehiculo = frame.getJTable_proveedores().getValueAt(row, 4).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			equivalencia=frame.getJTable_proveedores().getValueAt(row, 9).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			precio=frame.getJTable_proveedores().getValueAt(row, 10).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.get_txt_codigo().setText(codigo);
		frame.get_txt_descripcion().setText(descripcion);
		frame.get_txt_linea().setText(linea);
		frame.get_txt_modelo().setText(vehiculo);
		frame.get_txt_rubro().setText(rubro);
		frame.get_txt_subrubro().setText(subrubro);
		frame.get_txt_marca().setText(marca);
		
		
		
		cargar_equivalencia(codigo,linea,idproveedor,false);
		
	}
	
	/*
	public boolean load_foto(String source){
		boolean ok=false;
		
		File file=new File(source);
		if (file.exists()){
			System.out.println(source);
			this.loadFoto(source);
			ok=true;
		}else{
			System.out.println("NO EXISTE "+source);	
		}	
		return ok;
	}*/
	private aplicacion.herramientas.java.pdf.constructor._Constructor PDF=null;
	public void cargarPDFPorPagina(String filename, String page){
    	BufferedImage _image=null;
    	boolean ok=false;
    	try {
			Statement stmt = data.getConnector("MySQL").createStatement();
				//String q="SELECT data FROM pdf_archivo  where filename like '"+filename+"' ";
				String q="SELECT split FROM pdf_split where filename like '"+filename+"' and page like '"+page+"'";
				System.out.println(q);
				ResultSet resultSet = stmt.executeQuery(q);
				
					 if (resultSet.next()){
						 ok=true;
						 Blob image = resultSet.getBlob(1);
						  InputStream input = image.getBinaryStream();
						  //int _page=new Integer(page);
						  //_page--;
							
							int _page=0;
							if (PDF!=null){
								PDF.dispose();
							}
							PDF=new aplicacion.herramientas.java.pdf.constructor._Constructor();
							PDF.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler().Clone());
							PDF.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
							PDF.build(this.getConstructor());
							this.getConstructor().addChild(PDF);
							PDF.init();
							aplicacion.herramientas.java.pdf.logic._Logic
							logic=(aplicacion.herramientas.java.pdf.logic._Logic) PDF.getLogic();
							logic.load(input, _page);
							
					        
					        
					 }else{
						 aviso("PDF Por Paginas no Disponible. Pruebe la version Completa");
					 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		
    }
	
	public void cargarPDFCompleto(String filename, String page){
    	BufferedImage _image=null;
    	boolean ok=false;
    	try {
			Statement stmt = data.getConnector("MySQL").createStatement();
			
				String q="SELECT data FROM pdf_archivo  where filename like '"+filename+"' ";
				//String q="SELECT split FROM pdf_split where filename like '"+filename+"' and page like '"+page+"'";
				System.out.println(q);
				ResultSet resultSet = stmt.executeQuery(q);
				
					 if (resultSet.next()){
						 System.out.println("Cargando Archivo "+filename);
						 ok=true;
						 Blob image = resultSet.getBlob(1);
						  InputStream input = image.getBinaryStream();
						  
						  int _page=1;
						  try {
							_page=new Integer(page);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						  _page--;
							
							
							
							
						  if (PDF!=null){
								PDF.dispose();
							}
							PDF=new aplicacion.herramientas.java.pdf.constructor._Constructor();
							PDF.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler().Clone());
							PDF.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
							PDF.build(this.getConstructor());
							PDF.init();
							this.getConstructor().addChild(PDF);
							aplicacion.herramientas.java.pdf.logic._Logic
							logic=(aplicacion.herramientas.java.pdf.logic._Logic) PDF.getLogic();
							logic.load(input, _page);
							
				           
					 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		
    }
	public void getImages(String codigo,String linea){
    	BufferedImage _image=null;
    	boolean ok=false;
    	try {
			Statement stmt = data.getConnector("MySQL").createStatement();
				String q="SELECT imagen FROM imagen_catalogo  where codigo like '"+codigo+"' and linea like '"+linea+"' ";
				System.out.println(q);
				ResultSet resultSet = stmt.executeQuery(q);
				
					 while (resultSet.next()){
						 ok=true;
						 Blob image = resultSet.getBlob(1);
						  InputStream input = image.getBinaryStream();
				          _image = javax.imageio.ImageIO.read(input);
				          images.add(_image);
				           
					 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
    }
	
	
	public void registrar_faltante(int row){
		String idarticulo=frame.getJTable_sistema().getValueAt(row, 1).toString();
		String descripcion=frame.getJTable_sistema().getValueAt(row, 2).toString();
		String stock=frame.getJTable_sistema().getValueAt(row, 5).toString();
		String ip=data.getIp();
		
		String cantidad="1.0";
		String iduser=this.validar_usuario("Faltante "+idarticulo+". Ingrese su clave: ");
		if (iduser.compareTo("")!=0){
			String idvendedor="";
			try {
				idvendedor=(String)data.getVendedor(iduser)[0][0];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (idvendedor.compareTo("")!=0){
				String q=data.getInsertFaltante(idarticulo, descripcion,ip, idvendedor,cantidad,stock);
				data.beginTransaction();
				data.clearBatch();
				data.addBatch(q);
				boolean error=data.executeBatch();
				if (!error){
					data.commitTransaction();
					aviso("Se grabo correctamente el faltante");
				}else{
					data.rollbackTransaction();
					aviso("Error grabando faltante");
				}
				
			}	else{
			error("OPERACION CANCELADA");
		}
		}	else{
			error("OPERACION CANCELADA");
		}
		
	}
	
	public void mostrar_foto(int i) {
		if (i >= 0 & i <= images.size()) {
			i=i-1;
			BufferedImage bi = images.get(i);
			if (bi != null) {
				frame.getCanvas().setImage(bi);
				frame.getCanvas().maximizar();
				frame.getCanvas().setVisible(true);
				frame.get_btn_zoom_in().setEnabled(true);
				frame.get_btn_zoom_oute().setEnabled(true);
			}
			
		}

	}
	
	
	
	public int existeSeleccionObject(int row,Object[][] origen,JTable destino){
		int existe=-1;
		int i=0;
		
		if (destino!=null){
			while (i<destino.getRowCount() &existe<0){
				int j=1;
				existe=i;
				while (j<destino.getColumnCount()& existe>=0){
					try {
						if(origen[row][j].toString().compareTo(destino.getValueAt(i, j).toString())!=0){
							existe=-1;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					
					j++;
				}
				i++;
			}
		}
		return existe;
	}
	
	public void quitar_selecciones(){
		int selections=this.getNumberOfSelections();
		boolean exit=false;
		if (selections>0){
			exit=true;
			JTable selecciones=frame.getJTable_seleccion();
			if (selecciones!=null){
				int i=0;
				while(i<selecciones.getRowCount()){
					boolean selected=(Boolean) selecciones.getValueAt(i, 0);
					Object[] selection=new Object[selecciones.getColumnCount()-1];
					for (int j=0;j<selection.length;j++){
						selection[j]=selecciones.getValueAt(i, j+1);
					}
					if (selected){
						selecciones.setValueAt(false, i, 0);
						
					}
					i++;
				}
			}
			selecciones=frame.getJTable_sistema();
			if (selecciones!=null){
				int i=0;
				while(i<selecciones.getRowCount()){
					boolean selected=(Boolean) selecciones.getValueAt(i, 0);
					Object[] selection=new Object[selecciones.getColumnCount()-1];
					for (int j=0;j<selection.length;j++){
						selection[j]=selecciones.getValueAt(i, j+1);
					}
					if (selected){
						selecciones.setValueAt(false, i, 0);
						
					}
					i++;
				}
			}
			
		}
	}
	public int existeSeleccion(int row,JTable origen,JTable destino){
		int existe=-1;
		int columns=2;
		int i=0;
		
		if (destino!=null){
			while (i<destino.getRowCount() &existe<0){
				int j=1;
				existe=i;
				while (j<columns& existe>=0){
					try {
						if(origen.getValueAt(row,j).toString().compareTo(destino.getValueAt(i, j).toString())!=0){
							existe=-1;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						existe=-1;
					}
					
					j++;
				}
				i++;
			}
		}
		return existe;
	}
	
	
	
	
	public void editar(){
		JTable table=frame.getJTable_sistema();
		int row=table.getSelectedRow();
		if (row>=0){
			String idarticulo=table.getValueAt(row,1).toString();
			this.goMa_Articulos(idarticulo);	
		}
		
	}
	public void editar(int row,int col,JTable table){
		String idarticulo=table.getValueAt(row,1).toString();
		this.goMa_Articulos(idarticulo);
	}
	
	public Color getColor(int row,int col,JTable table){
		Color color=Color.white;
		double stock=0.0;
		String _stock="0.0";
		if (table!=null){
			try {
				_stock = table.getValueAt(row, 5).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_stock!=null){
				if (_stock.compareTo("")!=0){
					stock=new Double(_stock.replaceAll(",", ""));
				}
			}	
		}
		//System.out.println("Stock>"+_stock);
		if (stock>0){
			color=new Color(140, 255, 155);
		}
		return color;
	}
	
	public void setColumns(){
		columna c = new columna();
		Filtro f = new Filtro();
		
		

		c = new columna();
		c.setNombre("c.codigo");
		c.setAlias("codigo");
		c.setWidth(100);
		c.setMaster(false);
		addColumn(c);

		c = new columna();
		c.setNombre("c.descripcion");
		c.setAlias("descripcion");
		c.setWidth(200);
		c.setMaster(false);
		addColumn(c);

		c = new columna();
		c.setNombre("c.marca_vehiculo");
		c.setAlias("marca vehiculo");
		c.setWidth(140);
		c.setMaster(false);
		addColumn(c);
		
		c = new columna();
		c.setNombre("c.vehiculo");
		c.setAlias("vehiculo");
		c.setWidth(140);
		c.setMaster(false);
		addColumn(c);

		c = new columna();
		c.setNombre("c.linea");
		c.setAlias("linea");
		c.setWidth(140);
		c.setMaster(false);
		addColumn(c);
		
		c = new columna();
		c.setNombre("c.categoria1");
		c.setAlias("Categoria");
		c.setWidth(200);
		c.setMaster(false);
		addColumn(c);

		c = new columna();
		c.setNombre("c.categoria2");
		c.setAlias("SubCategoria");
		c.setWidth(200);
		c.setMaster(false);
		addColumn(c);

		
		
		c = new columna();
		c.setNombre("c.idproveedor");
		c.setAlias("idproveedor");
		c.setWidth(140);
		c.setMaster(false);
		addColumn(c);

		
		c = new columna();
		c.setNombre("c.equivalencia");
		c.setAlias("equivalencia");
		c.setWidth(140);
		c.setMaster(false);
		addColumn(c);
		
		c = new columna();
		c.setNombre("c.precio");
		c.setAlias("precio");
		c.setWidth(140);
		c.setMaster(false);
		addColumn(c);
		
		
		
		
		
		c = new columna();
		c.setNombre("convert(varchar(10),c.fecha,105)");
		c.setAlias("actualizado");
		c.setWidth(120);
		c.setMaster(false);
		addColumn(c);
		
		addFromTable("b_catalogo c ");
		f = new Filtro();
		f.setNombre("c.codigo");
		f.setAlias("codigo");
		f.setWidth(120);
		addFilter(f);
		f = new Filtro();
		f.setNombre("c.descripcion+c.categoria1+c.categoria2+c.marca_vehiculo+c.vehiculo+c.equivalencia");
		f.setAlias("descripcion");
		f.setWidth(350);
		f.setFocus(true);
		addFilter(f);
		f = new Filtro();
		f.setNombre("c.linea");
		f.setAlias("linea");
		f.setWidth(150);
		addFilter(f);
		f = new Filtro();
		f.setNombre("c.idproveedor");
		f.setAlias("idproveedor");
		f.setWidth(150);
		addFilter(f);
		setTop(top);
		addOrder("c.linea,c.codigo");
		
		setTitle("Buscador por Catalogos");
	}
	
	
	public void setSystemColumns(){
		columna c = new columna();
		Filtro f = new Filtro();
		
		

		c.setNombre("v.idarticulo");
		c.setAlias("idarticulo");
		c.setWidth(110);
		c.setMaster(true);
		addSystemColumn(c);

		c = new columna();
		c.setNombre("v.descripcion");
		c.setAlias("descripcion");
		c.setWidth(340);
		c.setMaster(false);
		addSystemColumn(c);

		c = new columna();
		c.setNombre("r.descripcion");
		c.setAlias("linea");
		c.setWidth(140);
		c.setMaster(false);
		addSystemColumn(c);
		
		c = new columna();
		c.setNombre("round(isnull(v.precio2,0),2)");
		c.setAlias("Publico");
		c.setWidth(60);
		c.setMaster(false);
		addSystemColumn(c);
		
		c = new columna();
		//c.setNombre("isnull(s.cantidadud,0)");
		c.setNombre("isnull(s.stock,0)");
		c.setAlias("stock");
		c.setWidth(50);
		c.setMaster(false);
		addSystemColumn(c);
		
		c = new columna();
		c.setNombre("v.cuentaproveedor");
		c.setAlias("idproveedor");
		c.setWidth(100);
		c.setMaster(false);
		addSystemColumn(c);
		addSystemFromTable("V_MA_ARTICULOS v LEFT OUTER JOIN view_stock s ON  v.IDARTICULO = s.IdArticulo left outer join v_ta_rubros r on v.idrubro = r.idrubro ");
		//addSystemFromTable("V_MA_ARTICULOS v LEFT OUTER JOIN Stk_Stock_UDKG s ON  v.IDARTICULO = s.IdArticulo ");
		f = new Filtro();
		f.setNombre("v.idarticulo");
		f.setAlias("idarticulo");
		f.setWidth(120);
		addSystemFilter(f);
		f = new Filtro();
		f.setNombre("v.descripcion");
		f.setAlias("descripcion");
		f.setWidth(350);
		f.setFocus(true);
		addSystemFilter(f);
		f = new Filtro();
		f.setNombre("r.descripcion");
		f.setAlias("linea");
		f.setWidth(150);
		addSystemFilter(f);
		
		f = new Filtro();
		f.setNombre("v.cuentaproveedor");
		f.setAlias("idproveedor");
		f.setWidth(150);
		addSystemFilter(f);
		
		//addSystemOrder("isnull(s.cantidadud,0) desc,v.idarticulo");
		addSystemOrder("s.stock desc,v.idarticulo");
		//addSystemRestriction(" v.idarticulo between '001-000' and '999-zzzz' ");
		
		//addSystemGroup("v.idarticulo,v.descripcion,v.descripabrev,v.precio2,v.cuentaproveedor");
		
		setSystemTop(top);
		
	}

	public void fillExtra(String idarticulo) {
		Object[][] results = data.getData(idarticulo);
		frame.get_txt_detalle_idarticulo().setText("");
		frame.get_txt_detalle_descripcion().setText("");
		frame.get_txt_detalle_stock().setText("");
		if (results != null) {
			if (results.length > 0) {
				Convertidor cv=new Convertidor();
				String _articulo = (String) results[0][0];
				String _descripcion = (String) results[0][1];
				String _linea = (String) results[0][2];
				String _stock = (String) results[0][3];
				String _suspendidov = (String) results[0][4];
				String _actualizacion = (String) results[0][5];
				String _publico = (String) results[0][6];
				Object[][] tmp=new Object[][]{{false,idarticulo,_descripcion,_linea,_publico,_stock}};
				crearTablaArticulos(tmp);
				cargar_equivalencias(idarticulo);
				
				cargar_deposito(idarticulo);
				if (frame.get_chk_relaciones().isSelected()){
					cargar_relaciones(idarticulo);	
				}else{
					frame.setJTable_relaciones(null);
				}
				
				frame.get_txt_detalle_idarticulo().setText(_articulo);
				frame.get_txt_detalle_descripcion().setText(_descripcion);
				frame.get_txt_detalle_stock().setText(_stock);
				frame.get_txt_detalle_linea().setText(_linea);
				
				if (_suspendidov.compareTo("1")==0){
					frame.get_txt_detalle_estado().setText("BLOQUEADO");
					frame.get_txt_detalle_estado().setBackground(Color.red);
				}else{
					frame.get_txt_detalle_estado().setText("HABILITADO");
					frame.get_txt_detalle_estado().setBackground(Color.green);
				}
				
				if (_actualizacion.compareTo("")==0){
					frame.get_txt_detalle_actualizacion().setBackground(Color.red);
					_actualizacion="SIN FECHA";
				}else{
					if (this.eval_venc(_actualizacion)){
						frame.get_txt_detalle_actualizacion().setBackground(Color.green);
					}else{
						frame.get_txt_detalle_actualizacion().setBackground(Color.red);
					}	
				}
				frame.get_txt_detalle_actualizacion().setText(_actualizacion);
				/*
				double _pedido=data.getPedidoCantidad(idarticulo);
				double stock=new Double(_stock);
				frame.get_txt_detalle_pedido().setText(cv.getMoney(_pedido, 2));*/
			}
		}
	}
	
	private boolean eval_venc(String fecha){
		boolean ok=false;
		if (fecha.compareTo("")!=0){
		Calendar _fecha_actualizacion=Calendar.getInstance();
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
		    	_fecha_actualizacion.setTime(date);
		    	Date today=new Date();
				java.util.GregorianCalendar _fecha_limite=new java.util.GregorianCalendar();
				_fecha_limite.setTime(today);
				//_today.roll(Calendar.DAY_OF_YEAR, days);
				_fecha_limite.add(Calendar.DAY_OF_YEAR, -30);
				
				Calendar now=Calendar.getInstance();
				_fecha_actualizacion.add(Calendar.DATE, 27);
				ok=_fecha_limite.before(_fecha_actualizacion);
			//	System.out.println(now.getTime()+" "+cal.getTime());
		    }
			//System.out.println("dif "+c+" "+diferencia);
		}else {
			//"Fecha Nula"
		}
		return ok;
	}
	
	public void cargar(int row){
		String file=frame.getJTable_documentos().getValueAt(row,2).toString();
		String page=frame.getJTable_documentos().getValueAt(row,3).toString();
		//this.cargarPDF(file, page);
		int n=this.preguntar("Seleccion", "Seleccione como cargar el PDF", new String[]{"Pagina","Completo"}, "Pagina");
		if (n==0){
			this.cargarPDFPorPagina(file, page);	
		}else{
			this.cargarPDFCompleto(file, page);
		}
		
	}
	public void cargar_catalogo(int row){
		String file=frame.getJTable().getValueAt(row,0).toString();
		if (confirmar("Confirme la carga del catalogo "+file+" :",2)){
			aviso("La carga puede demorar unos segundos. Por favor espere....");
			this.cargarPDFCompleto(file, "");	
		}
		
		
	}
	/*
	public void cargarPDF(String file,String page){
		int _page=new Integer(page);
		_page--;
		Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
		//String filePath=this.carpeta_local+file;
		String filePath=this.carpeta_destino+file;
		
        SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        // add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        applicationFrame.getContentPane().add(viewerComponentPanel);

        // Now that the GUI is all in place, we can try openning a PDF
        controller.openDocument(filePath);
        
        controller.goToDeltaPage(_page);
        controller.setZoom(new Float(1.5));
        
        // show the component
        
        applicationFrame.setExtendedState(applicationFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        applicationFrame.pack();
        applicationFrame.setVisible(true);	
	}
	*/
	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;
	public void goMa_Articulos(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
		articulo.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		articulo.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler().Clone());
		articulo.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		articulo.setParameter(aplicacion.inventario.articulo.interfaces._parametros.idarticulo,idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}
	
	
	private void create_table_relaciones(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		CheckBoxCellEditor cbce = null;

		
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		col.setClass(Boolean.class);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this.getConstructor().getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_chk_relaciones);
		col.setCellEditor(chkce.getCellCheck());
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		TableColorCellRenderer cellrender=	new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(240);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		col.setCellRenderer(cellrender);
		table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(140);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		col.setCellRenderer(cellrender);
		table.addColumn(col);

		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(cellrender);
		table.addColumn(col);
		
		col = new Column();
		col.setName("stock");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(cellrender);
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("referencias");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(cellrender);
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_referencias);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		
		
		JTable _table = table.getTable();
		_table.setColumnSelectionAllowed(false);
		_table.setRowSelectionAllowed(true);
		frame.setJTable_relaciones(_table);
	}

}
