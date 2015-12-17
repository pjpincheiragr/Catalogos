package aplicacion.herramientas.java.comprobante.logic;
import aplicacion.modelo.*;
import aplicacion.modelo.logic.*;

import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.swing.DefaultFocusManager;
import java.awt.DefaultKeyboardFocusManager;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aplicacion.herramientas.java.comprobante.interfaces.*;
import aplicacion.herramientas.java.comprobante.*;
import aplicacion.herramientas.java.comprobante.gui.*;
import aplicacion.herramientas.java.table.*;



public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data=null;
	private List<columna> Columns;
	private List<Filtro> Filters;
	private String FromTable;
	private String FromTableEncabezado;
	private JTextField caller=null;
	private LinkedList Orders;
	private LinkedList excluders;
	private LinkedList iniValues;
	private LinkedList Labels;
	private LinkedList acumFilters;
	private String Restriction;
	private String idconector;
	private String Group;
	private Object[][] auzx;
	private int top;
	private Logic external;
	private String title="";
	private boolean editable=false;
	public void setCaller(JTextField tx){
		this.caller=tx;
	}
	public JTextField getCaller(){
		return caller;
	}
	
	//variables de tareas swingwork
	private String estado="";
	private int current;
	private int lenght;
	private boolean debug,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private Point p;
	
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
	
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public void setLocation(int x,int y){
		this._frame.setLocation(x, y);
	}
	
	public boolean escape(JTextField tx){
		boolean escape=false;
		if (tx.getText().compareTo("")==0){
			if (frame.getJTable()==null){
				escape=true;
			}else{
				if (frame.getJTable().getRowCount()<=0){
					escape=true;
				}
			}
		}
		return escape;
	}
	
	public void setFocus(){
		boolean focus_default=true;
		if (Filters!=null & frame.getJTable1()!=null){
		for (int i=0;i<Filters.size();i++){
			Filtro f=(Filtro)Filters.get(i);
			if (f.hasFocus()){
				this.focus(i);
				focus_default=false;
			}
		}
		if (focus_default){
			if (frame.getJTable1().getColumnCount()>0){
				this.focus(0);
			}
		}
		}
	}
	
	public void addFilter(Filtro filtro){
		this.Filters.add(filtro);
	}
	
	public void setFromTable(String From){
		this.FromTable=From;
	}
	public void setFromTableEncabezado(String From){
		this.FromTableEncabezado=From;
	}
	
	public void addRestriction(String restriction){
		this.Restriction=restriction;
	}
	
	public void addColumn(columna c){
		this.Columns.add(c);
	}
	
	public void addGroup(String rest){
		this.Group=rest;
	}

	private void acumulate_sum(){
		for (int i=0;i<this.Filters.size();i++){
			LinkedList filter=(LinkedList)this.acumFilters.get(i);
			filter.addLast(frame.getJTable1().getValueAt(0, i).toString());
			acumFilters.set(i, filter);
			//frame.getJTable1().setValueAt("",0,i);
		}
	}



	private void back_sum(){
		for (int i=0;i<this.Filters.size();i++){
			LinkedList filter=(LinkedList)this.acumFilters.get(i);
			if (filter.size()>=1){
				String tmp=(String)filter.get(filter.size()-1);
				filter.removeLast();
				acumFilters.set(i, filter);
				frame.getJTable1().setValueAt("",0,i);
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

	public boolean getAutoRestriction(){
		boolean auto=false;
		int i=0;
		while (i<Filters.size() &!auto){
			if (Filters.get(i).isMaster()){
				auto=Filters.get(i).getValor().compareTo("")!=0;	
			}
			
			i++;
		}
		return auto;
	}
	
	public String getAutoRestrictionQuery(){
		String auto="";
		for (int i=0;i<Filters.size();i++){
			if (Filters.get(i).isMaster()){
				if (Filters.get(i).getValor().compareTo("")!=0){
					if (auto.length()>0) auto+=" and ";
					auto+=Filters.get(i).getNombre()+"  like '"+Filters.get(i).getValor()+"' ";
				}	
			}
			
		}
		return auto;
	}

	private String doQuerys(){
		String tmpQuery;
		if (this.Columns.contains("*")){
		tmpQuery="select * ";
		}else {
			tmpQuery="select ";
			if (top>0){
				tmpQuery=tmpQuery+" top "+top+" ";
			}
			for (int i=0;i<Columns.size()-1;i++){
				columna column=(columna) Columns.get(i);
				tmpQuery=tmpQuery+column.getNombre()+",";
			}
			
			columna column=(columna) Columns.get(Columns.size()-1);
			tmpQuery=tmpQuery+column.getNombre()+" ";
			
		}
		tmpQuery=tmpQuery+" from "+this.FromTable;
		
		int filterx=0;
		
		for (int i=0;i<Filters.size();i++){
			if (Filters.get(i).isMaster()){
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
			System.out.println("Orders Size?"+Orders.size());
			tmpQuery=tmpQuery+" order by ";
			for (int i=0;i<Orders.size()-1;i++){
				tmpQuery=tmpQuery+Orders.get(i)+",";
			}
			tmpQuery=tmpQuery+Orders.get(Orders.size()-1);
			
		}
		
		System.out.println(tmpQuery);
		
	 return tmpQuery;

	}

	//metodos basicos de tareas swing
	public void createTimer(){
		crono=new Crono();
		crono.start();
		Timer=new Timer(500, new ActionListener() {
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
		
	}
	
	public void exit(){
		this.frame.setVisible(false);
		this.getConstructor().dispose();
	}
	public void doCancel(){
		
		canceled=true;
		
		
		
		
	}
	
	class CargarTask {
		CargarTask() {
			search();
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
	
	public void clean(){
		if (Timer!=null){
				this.doCancel();	
		}
		
		
		auzx=null;
		try{
			frame.setJTable(null);
			
		}catch(Exception e){
			
		}
		for (int i=0;i<Filters.size();i++){
			frame.getJTable1().setValueAt("",0,i);
			this.acumFilters.set(i, new LinkedList());
		}
		this.setFocus();
	}
	public void clean(JTextField tx,int col){
		auzx=null;
		try{
			frame.setJTable(null);
			
		}catch(Exception e){
			
		}
		for (int i=0;i<Filters.size();i++){
			frame.getJTable1().setValueAt("",0,i);
			this.acumFilters.set(i, new LinkedList());
		}
		tx.setText("");
		focus(col);
	}
	
	public void addtop(int top){
		this.top=top;
	}

	public void addOrder(String Column){
		Orders.add(Column);
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
		frame.dispose();
	}
	

	private CustomTable crearTabla(){
		final CustomTable Table=new CustomTable();
		for (int i=0;i<Columns.size();i++) {
			columna c=(columna)Columns.get(i);
			Column col=new Column();
			col.setName(c.getAlias());
			col.setWidth(c.getWidth());
			col.setClass(c.getClase());
			Table.addColumn(col);		
		}
		
		Table.setData(this.auzx);
		Table.build();
		Table.fillData();
		
		
		return Table;
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
	
	private void create_table_fields(){
		CustomTable Table=new CustomTable();
		Object[][] results=new Object[1][Filters.size()];
		for (int i=0;i<Filters.size();i++) {
			results[0][i]=Filters.get(i).getValor();
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
			col.setEditable(false);
			Table.addColumn(col);		
		}
		
		Table.setData(results);	
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		Table.setHeaderFont(fuente);
		Table.setFont(fuente);
		Table.build();
		Table.fillData();
		JTable table=Table.getTable();
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),"none" );
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),"none" );
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"none" );
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN,0),"none" );
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP,0),"none" );
		table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0),"none" );
		
		frame.setJTable1(table);
		this.updateSizes();
		
	}
	
	private void build_fields(){
		frame.get_btn_editar().setEnabled(this.editable);
		
		this.acumFilters=new LinkedList();
		for (int i=0;i<Filters.size();i++){
			this.acumFilters.add(new LinkedList());
		}
		this.create_table_fields();
		if (caller!=null)this.setLocation();
		this.frame.setVisible(true);
	}
	
	public void updateSizes(){
		int width=24;
		for (int i=0;i<Filters.size();i++) {
			Filtro f=(Filtro) Filters.get(i);
			width+=f.getWidth();
		}
		System.out.println("Filters Width:"+width);
		int w2=24;
		for (int i=0;i<Columns.size();i++) {
			columna c=(columna)Columns.get(i);
			w2+=c.getWidth();
		}
		if (w2>width){
			width=w2;
		}
		System.out.println("Columnas Width:"+w2);
		
		if (width>frame.getWidth()){
			if (width<=1000){
				frame.setSize(width, frame.getHeight());
				frame.getJScrollPane1().setSize(width-20, frame.getJScrollPane1().getHeight());
				frame.getJScrollPane().setSize(width-20, frame.getJScrollPane().getHeight());
				frame.getJProgressBar().setSize(width, frame.getJProgressBar().getHeight());
				frame.get_toolbar_header().setSize(width, frame.get_toolbar_header().getHeight());
			}else{
				width=1000;
				frame.setSize(width, frame.getHeight());
				frame.getJScrollPane1().setSize(width-20, frame.getJScrollPane1().getHeight());
				frame.getJScrollPane().setSize(width-20, frame.getJScrollPane().getHeight());
				frame.getJProgressBar().setSize(width, frame.getJProgressBar().getHeight());
				frame.get_toolbar_header().setSize(width, frame.get_toolbar_header().getHeight());
			}
		}
	}
	public void _compatibleSearch(){
		
		this.acumulate_sum();
		this.customSearch();
		
	}
	public void search() {
		this.frame.getJProgressBar().setIndeterminate(true);
		estado="Cargando Datos";
		/*
		
		 */
		
		_compatibleSearch();
		
	    //try {
			
		/*} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	
	}
	
	
	public void search(JTextField tx,int col){
		frame.getJTable1().setValueAt(tx.getText(), 0, col);
		//tx.setText("");
		tx.requestFocusInWindow();
		this.goCargar();	
			
		
	}
	

	public String getIdconector() {
		return idconector;
	}

	public void setIdconector(String idconector) {
		this.idconector = idconector;
	}

	private void minimalSearch(){
		frame.repaint();
		CustomTable create=this.crearTabla();
		Font fuente=new Font("Arial", Font.PLAIN, 9);
		create.setHeaderFont(fuente);
		JTable table=create.getTable();
		table.setName(_Interface._table);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		table.setFont(fuente);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		frame.setJTable(table);
		frame.repaint();
	}

	public void selection(JTextField tx,int col){
		
		int row=frame.getJTable().getSelectedRow();
		this.Close(frame.getJTable(), row);
	}
	
	private void customSearch(){
		if (idconector.compareTo("")!=0){
			this.auzx=data.getConnectionHandler().getConnector(idconector).getResults(this.doQuerys());	
		}else{
			this.auzx=data.getConnectionHandler().getDefaultConnector().getResults(this.doQuerys());	
		}
		if (auzx!=null){
			if (auzx.length>0){
				Runnable _execute = new Runnable() {
			        public void run() {
			        	 minimalSearch();
			        }
			    };
			    this.invokeLater(_execute);
			}else {
				
				this.aviso_resultado_nulo();
			    
				this.clean();
			}
		}else {
			this.aviso_resultado_nulo();
			this.clean();
			try {
				frame.setJTable(null);	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		done=true;
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
		 idconector="";
	}
	
	public void Down(JTextField tx,int col){
		int row=-1;
		try {
			row=frame.getJTable().getSelectedRow();	
		}catch(Exception e){	}
		try {

			if (row<frame.getJTable().getRowCount()-1){
				frame.getJTable().changeSelection(row+1, 0, false, false);
				frame.getJTable1().requestFocusInWindow();
				frame.getJTable().transferFocus();
			}
		}catch(Exception e){	}
		this.focus(col);
		tx.requestFocusInWindow();
	}

	public void focus(int col){
		frame.getJTable1().changeSelection(0,col,false,false);
		frame.getJTable1().editCellAt(0,col);
		frame.getJTable1().transferFocus();
	}
	
	public void Up(JTextField tx,int col){
		int row=-1;
		try {
			row=frame.getJTable().getSelectedRow();	
		}catch(Exception e){	}
		
		try {
			if (row>0){
				frame.getJTable().changeSelection(row-1, 0, false, false);
				frame.getJTable1().requestFocusInWindow();
				frame.getJTable().transferFocus();
			}
		
		}catch(Exception e){
		}
		this.focus(col);
		tx.requestFocusInWindow();
	}
	
	public void setTitle(String title){
		this.title=title;
		frame.setTitle(title);
	}
	
	public void init(){
	 this.build_fields();
	 this.setFocus();
	 this.fillEncabezado();
	 this.goCargar();
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
	
	public void setLocation(){
		System.out.println("Caller Null?"+caller);
		System.out.println("Frame Null?"+frame);
		frame.setLocation(caller.getLocationOnScreen().x, caller.getLocationOnScreen().y+caller.getHeight());
	}
	
	public void evaluat_escape(JTextField tx,int col){
		if (this.escape(tx)){
			this.exit();
		}else{
			this.clean(tx, col);
		}
	}
	
	public void fillEncabezado(){
		String q=this.doQuerysEncabezado();
		Object[][] results=data.getResults(q);
		if (results!=null){
			if (results.length>0){
				for(int i=0;i<results[0].length;i++){
					String valor="";
					valor=results[0][i].toString();
					
					
					frame.getJTable1().setValueAt(valor, 0, i);
					Filters.get(i).setValor(valor);
				}
			}
		}
	}
	
	private String doQuerysEncabezado(){
		String tmpQuery;
		tmpQuery="select ";
			for (int i=0;i<Filters.size()-1;i++){
				Filtro column=(Filtro) Filters.get(i);
				tmpQuery=tmpQuery+column.getNombre()+",";
			}
			Filtro column=(Filtro) Filters.get(Filters.size()-1);
			
			tmpQuery=tmpQuery+column.getNombre()+" ";
			
		
		tmpQuery=tmpQuery+" from "+this.FromTableEncabezado;
		
		int filterx=0;
		
		for (int i=0;i<Filters.size();i++){
			if (Filters.get(i).isMaster()){
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
		}
		/*
		if (Restriction.compareTo("")!=0){
			if (filterx==0){
				tmpQuery=tmpQuery+" where ";
				filterx++;
			} else {
				tmpQuery=tmpQuery+" and ";
			}
			tmpQuery=tmpQuery+Restriction;
		}
		*/
		if (this.getAutoRestriction()){
			
			if (filterx==0){
				tmpQuery=tmpQuery+" where ";
			} else {
				tmpQuery=tmpQuery+" and ";
			}
			tmpQuery=tmpQuery+this.getAutoRestrictionQuery();
		}
		/*
		if (Group.compareTo("")!=0){
			
			tmpQuery=tmpQuery+" Group by "+Group;
		}
		if (!Orders.isEmpty()){
			System.out.println("Orders Size?"+Orders.size());
			tmpQuery=tmpQuery+" order by ";
			for (int i=0;i<Orders.size()-1;i++){
				tmpQuery=tmpQuery+Orders.get(i)+",";
			}
			tmpQuery=tmpQuery+Orders.get(Orders.size()-1);
			
		}
		*/
		System.out.println(tmpQuery);
		
	 return tmpQuery;
	
	}
	
	
	public void editar(){
		
	}
}
