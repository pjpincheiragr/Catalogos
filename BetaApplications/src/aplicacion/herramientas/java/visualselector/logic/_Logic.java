package aplicacion.herramientas.java.visualselector.logic;
import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.*;
import java.util.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.text.*;
import javax.swing.JLabel;
import aplicacion.herramientas.java.visualselector.events.*;

import aplicacion.herramientas.java.Crono;
import javax.swing.SwingWorker;

import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.*;
import aplicacion.herramientas.java.visualselector.constructor._Constructor;
import aplicacion.herramientas.java.visualselector.gui.*;
import aplicacion.herramientas.java.visualselector.interfaces._Interface;
import aplicacion.herramientas.java.visualselector.logic.*;

import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import javax.swing.JTextField;

import java.util.LinkedList;
import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.AbstractLayerUI;
import org.jdesktop.jxlayer.plaf.effect.BufferedImageOpEffect;

import org.jdesktop.jxlayer.plaf.ext.LockableUI;

import com.jhlabs.image.BoxBlurFilter;

//import org.jdesktop.swinghelper.layer.painter.DefaultPainter;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JProgressBar;
import java.awt.Rectangle;
import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;

public class _Logic  extends Logic {
	private _Frame frame=null;
	private _Data data=null;
	private List<Columna> Columns=null;
	private List<Filtro> Filters=null;
	private String FromTable="";
	private String restriction="";
	private String orderby="";
	private String groupby="";
	private int top=0;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String estado="";
	private int current;
	private int lenght;
	private boolean done,canceled;
	private int errors=0;
	private JTextField caller=null;
	private LinkedList lista = new LinkedList();
	private int locx=0;
	private int locy=0;
	private String[] list_values=null;
	private Logic external;
	public void setCaller(JTextField tx){
		this.caller=tx;
		locx=caller.getLocationOnScreen().x;
		locy=caller.getLocationOnScreen().y+caller.getHeight();
		Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("Caller text?"+caller.getText()+" "+caller.getLocationOnScreen());
		if (locx+frame.getWidth()>s.width){
				//locx=locx-(frame.getWidth()-caller.getWidth());
				int dif=(locx+frame.getWidth())-s.width;
				locx=locx-dif-10;
		}
		if (locy+frame.getHeight()>s.height){
				locy=locy-(caller.getHeight()+frame.getHeight());
		}
		
	}
	public JTextField getCaller(){
		return caller;
	}
	public void setFrame(JFrame frame){
		this.frame= (_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data data){
		this.data=(_Data)data;
		super.setData(data);
	}
	
	public _Logic(){
		Columns=new ArrayList<Columna>();
		Filters=new ArrayList<Filtro>();
	}
	
	public void addFilter(Filtro filter){
		int idx=hasFilter(filter);
		if (idx<0){
			Filters.add(filter);	
		}else{
			Filters.get(idx).setValor(filter.getValor());
		}
		
	}
	
	public void addColumn(Columna col){
		if (!this.hasColumn(col)){
			Columns.add(col);	
		}
		
	}
	private boolean hasColumn(Columna c){
		boolean found=false;
		int i=0;
		while(i<Columns.size() & !found){
			found=Columns.get(i).getNombre().compareTo(c.getNombre())==0;
			i++;
		}
		return found;
	}
	private int hasFilter(Filtro f){
		boolean found=false;
		int i=0;
		while(i<Filters.size() & !found){
			found=Filters.get(i).getNombre().compareTo(f.getNombre())==0;
			if (!found){
				i++;	
			}
			
		}
		if (!found)i=-1;
		return i;
	}
	
	private String getWhereString(int i){
		String description="";
		Filtro filtro= Filters.get(i);
		String column=filtro.getNombre();
		String aux=filtro.getValor();
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
			
			
		
		return description;
	}

	public void cleanStructure(){
		Columns=new LinkedList();
		Filters=new LinkedList();
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
				Columna column=(Columna) Columns.get(i);
				tmpQuery=tmpQuery+column.getNombre()+",";
			}
			
			Columna column=(Columna) Columns.get(Columns.size()-1);
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
		
		if (this.restriction.compareTo("")!=0){
			if (filterx==0){
				tmpQuery=tmpQuery+" where ";
			} else {
				tmpQuery=tmpQuery+" and ";
			}
			tmpQuery=tmpQuery+restriction;
		}
		
		if (groupby.compareTo("")!=0){
			
			tmpQuery=tmpQuery+" Group by "+groupby;
		}
		if (orderby.compareTo("")!=0){
			
			tmpQuery=tmpQuery+" order by ";
			
			tmpQuery=tmpQuery+orderby;
			
		}
		
		System.out.println("Sortable Query:"+tmpQuery);
		
	 return tmpQuery;

	}

	public void clean(){
		for (int i=0;i<Columns.size();i++){
			Columna _col=(Columna) Columns.get(i);
			if (_col.getColumnField()!=null){
				_col.getColumnField().setText("");
			}
		}
	}
	
	
	public void _taskworkCargar(){
		estado="Cargando";
		String q=this.doQuerys();
		System.out.println("query>"+q);
		Integer options=0;
		Object[][] results=data.getResults(q);
		
		
		if (results!=null){
			
			if (results.length>0){
				options=results.length;
				create_results(results);
			}
		}
		System.out.println("Finish Options>"+options);
		final Integer[] ops=new Integer[1];
		ops[0]=options;
		
		
		Runnable runnable = new Runnable() {
			public void run(){
				addDato(ops[0]);	
			}
		};
		Thread threadB = new Thread(runnable, "ThreadB");
		threadB.start();
		
		
		
	}
	
	public void create_results(Object[][] results){
		final Object[][] _results=results;
		Runnable _execute=new Runnable(){
			public void run(){
				_build(_results);
				
			}
		};
		this.invokeLater(_execute);
		
	
	}
	JXLayer<JPanel> pbar_layer=null;
	public JXLayer<JPanel> getPBar(Rectangle bounds,JTextField text,Font font){
		if (pbar_layer==null){
			
			JTextField textfield=new JTextField();
			textfield.setText(text.getText());
			textfield.setHorizontalAlignment(text.getHorizontalAlignment());
			textfield.setFont(font);
			JPanel panel=new JPanel();
			panel.setBounds(0,0, bounds.width,bounds.height);
			panel.setLayout(null);
			pbar_layer = new JXLayer<JPanel>(panel);
			
			textfield.setBounds(0,0, bounds.width,bounds.height);
			textfield.setForeground(Color.black);
			panel.add(textfield);
			pbar_layer.setUI(this.getLockableUI());
			pbar_layer.setBounds(0,0, bounds.width,bounds.height);
		}
		return pbar_layer;
	}
	
		public void block(){
			Runnable _execute=new Runnable(){
				public void run(){
					loadoptions_gui();
			    }
			};
			invokeLater(_execute);
			
		}
	 public class LoadOptions extends SwingWorker<Integer, Integer> {
		 
	       @Override
	       public Integer doInBackground() {
	    	   
	    	   System.out.println("doInBackground() esta en el hilo "
	                   + Thread.currentThread().getName());
	           

	           // Supuesto resultado de la tarea que tarda mucho.
	           
	    	   int n=_loadoptions_back();
	    	   return n;
	       }

	       @Override
	       protected void process(List<Integer> chunks) {
	    	   System.out.println("process() esta en el hilo "
	                   + Thread.currentThread().getName());
	           
	    	   System.out.println("process() "+chunks.get(0));
	    	   //block();
	    	   //loadoptions_gui();
	       }
	       
	       
	   }
	 
	
	
	 
	public int _loadoptions(){
		//System.out.println("loadoptions() esta en el hilo "+ Thread.currentThread().getName());
		
		this.block();
		LoadOptions lo=new LoadOptions();
		lo.execute();
			
		int n=0;
		try {
			n = lo.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	
	public void loadoptions_gui(){
		if (caller!=null){
			System.out.println(caller.getLocationOnScreen());
			_FocusListener _focus=new _FocusListener(this);
			caller.addFocusListener(_focus);
			System.out.println(caller.getParent());
			Rectangle bounds=caller.getBounds();
			//caller.add(this.getPBar(bounds,caller,new Font("Dialog", Font.BOLD, 10)));
			frame.addFocusListener(_focus);
			frame.getJTable().addFocusListener(_focus);
			frame.getJScrollPane().addFocusListener(_focus);
			frame.getJContentPane().addFocusListener(_focus);
			frame.addWindowFocusListener(new WindowAdapter() {
			    public void windowGainedFocus(WindowEvent e) {
			        //System.out.println("VisualSelector Gain Focus");
			        
			    }
			    public void windowLostFocus(WindowEvent e) {
			    	//System.out.println("VisualSelector Lost Focus");
			    	hide();
			    }
			});	
			
		}
	}
	public int _loadoptions_back(){
		String q=this.doQuerys();
		System.out.println(q);
		
		
		
		
		Object[][] results=data.getResults(q);
		int n=0;
		
		if (results!=null){
			
			if (results.length>0){
				n=results.length;
				create_results(results);
				
			}
		}
		if (n<0){
			this.frame.setVisible(false);
		}
		return n;
	}
	public void setCoords(){
			frame.setLocation(locx, locy);	
			
	}
	
	public void hide(){
		try {
			caller.remove(this.pbar_layer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		frame.setVisible(false);
		
		this.getConstructor().dispose(false);
	}
	
	public int _loadoptions(String idconnector){
		String q=this.doQuerys();
		System.out.println(q);
		
		Object[][] results=data.getConnector(idconnector).getResults(q);
		int n=0;
		
		if (results!=null){
			
			if (results.length>0){
				n=results.length;
				create_results(results);
				
			}
		}
		if (n<0){
			
			try {
				caller.remove(this.pbar_layer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.frame.setVisible(false);
		}
		return n;
	}
	
	public int _loadoptionsOld(){
		
		lista=new LinkedList();
				
			/*
		final Integer[] ops=new Integer[1];
		Runnable runnable = new Runnable() {
			public void run(){
				ops[0]=getDato();		
				caller.setEnabled(true);
				caller.requestFocusInWindow();
			}
		};
		Thread threadA = new Thread(runnable, "ThreadA");
		threadA.start();*/
		//goCargar();
		final Integer[] ops=new Integer[1];
		ops[0]=this.getDato();
		return ops[0];
		
	}
	
	
	
	private void _build(Object[][] results){
		this.setCoords();
		JTable oldtable=frame.getJTable();
		if (oldtable!=null){
			System.out.println("LOAD OPTIONS OLDTABLE IT'S ALIVE!!!"+oldtable.getRowCount());	
		}
		System.out.println("Build Resutls");
		_Constructor constructor=(_Constructor) this.getConstructor();
		
		
		CustomTable table = new CustomTable();
		//table.setAlfa(0.6f);
		CellEditor pce =null;
		CheckBoxCellEditor cbce=null;
		
		//table.setSortable(false);
		Column col = null;
		
		int _width=20;
		int _height=18;
		int _enc=20;
		if (results.length>9){
			_height=_enc+10*_height;
		}else{
			_height=_enc+(results.length)*_height;
		}
		if (this.getOS().contains("Windows 7")){
			_height+=2;
		}
		if (this.isLinux()){
			_height+=4;
		}
		for (int i=0;i<Columns.size();i++){
			Columna _col=(Columna) Columns.get(i);
			col = new Column();
			col.setName(_col.getAlias());
			col.setWidth(_col.getWidth());
			_width+=_col.getWidth();
			col.setEditable(false);
			col.setAligment(_col.getAlignment());
			table.addColumn(col);
		}
		
		System.out.println("total width:"+_width);
		System.out.println("total height:"+_height);
		
		
		System.out.println("frame width:"+frame.getWidth()+" "+frame.getHeight());
		table.setData(results);
		table.setName(_Interface._table);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		
		JTable _table = table.getTable();
		_table.setName(_Interface._table);
		_table.setColumnSelectionAllowed(false);
		_table.setRowSelectionAllowed(true);
		_table.addMouseListener(this.getConstructor().getMouseListener());
		_table.addKeyListener(this.getConstructor().getKeyListener());
		
		frame.getJScrollPane().setBounds(new Rectangle(0, 0, _width, _height));
		
		frame.setSize(_width,_height);
		frame.setJTable(_table);
		
		
		frame.setVisible(true);
		
		frame.getJTable().changeSelection(0, 0, false,false);
		frame.getJTable().requestFocusInWindow();		
	}

	public String getFromTable() {
		return FromTable;
	}

	public void setFromTable(String fromTable) {
		FromTable = fromTable;
	}

	public String getRestriction() {
		return restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getGroupby() {
		return groupby;
	}

	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}
	public void exit(){
		try {
			caller.remove(this.pbar_layer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		this.caller.requestFocusInWindow();
		frame.setVisible(false);
		this.getConstructor().dispose(false);
		
	}
	public void Close(JTable table, int row) {
		if (external == null) {
			for (int i = 0; i < this.Columns.size(); i++) {
				Columna c = (Columna) Columns.get(i);
				String value = "";
				try {
					value = (String) table.getValueAt(row, i);
				} catch (Exception e) {

				}
				JTextField columnField = (JTextField) c.getColumnField();
				if (columnField != null) {
					columnField.setText(value);
					columnField.requestFocusInWindow();
				}
				if (c.getJtable() != null) {
					JTable tablex = c.getJtable();
					tablex.setValueAt(value, c.getRow(), c.getColumn());
				}
				c.fillData(value);
			}
		}
		focus();
		this.frame.setVisible(false);
		this.clean();
	}
	public void focus() {
		boolean found = false;
		int i = 0;
		while (i < this.Columns.size() & !found) {
			Columna c = (Columna) Columns.get(i);
			found = c.getMaster();
			if (found) {
				JTextField columnField = (JTextField) c.getColumnField();
				if (columnField != null)
					columnField.requestFocusInWindow();
			} else {
				i++;
			}
		}
	}
	public void select(JComboBox cb ,String valor){
		System.out.println("Select from combobox "+cb+" valor="+valor+" list_Values "+list_values);
		if (list_values!=null){
			boolean found=false;
			int i=0;
			while (i<list_values.length & !found){
				if (list_values[i].compareTo(valor)==0){
					found=true;
					cb.setSelectedIndex(i);
				}
				i++;
			}
		}
	}
	
	public void close(int row){
		if (row>=0){
			for (int i=0;i<Columns.size();i++){
				Columna _col=(Columna) Columns.get(i);
				String valor="";
				try {
					valor=frame.getJTable().getValueAt(row, i).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (_col.getColumnList()!=null){
					System.out.println("ComboBox Column!!!");
					
					select(_col.getColumnList(),valor);
				}
				
				if (_col.getJtable() != null) {
					JTable tablex = _col.getJtable();
					tablex.setValueAt(valor, _col.getRow(), _col.getColumn());
				}
				_col.fillData(valor);
				if (_col.getColumnField()!=null){
					
					
					_col.getColumnField().setText(valor);
					
				}
				
			}	
		}
		
		
		this.exit();
	}
	
	public void setTitle(String title){
		
		frame.setTitle(title);
	}
	
	
	
	public void createTimer(){
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
		Timer=new Timer(100, new ActionListener() {
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
		
	}
	
	public void endbar(){
		
		estado="";
		
	}
	public synchronized Integer getDato()
	   {
		if (lista.size()==0){
			try {
				System.out.println("Esperando Resultado");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Dato Cargado");
	      Integer dato = (Integer)lista.get(0);
	      lista.remove(0);
	      return dato;
	   }
	
	public synchronized void addDato(Integer dato)
	   {
		System.out.println("Agregando Dato ");
		 lista.add(dato);
	      try {
			notify();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   }
	
	
	public String[] getList_values() {
		return list_values;
	}
	public void setList_values(String[] list_values) {
		this.list_values = list_values;
	}


	private LockableUI lockableUI=null;
	public LockableUI getLockableUI(){
		if (lockableUI==null){
			lockableUI= new LockableUI();        
			BoxBlurFilter blur=new BoxBlurFilter(1,1,1);
			BufferedImageOpEffect effect = new BufferedImageOpEffect(blur);
			
			lockableUI.setLockedEffects(effect);
			
			
			lockableUI.setLocked(true);
		}
		return lockableUI; 

	}
	
	
}
