package aplicacion.herramientas.java.calendario.logic;
/*
import aplicacion.gestion.calendario.constructor._Constructor;
import aplicacion.gestion.calendario.gui.*;
import aplicacion.gestion.calendario.interfaces._Interface;
*/

import aplicacion.herramientas.java.calendario.constructor._Constructor;
import aplicacion.herramientas.java.calendario.gui.*;
import aplicacion.herramientas.java.calendario.interfaces._Interface;

import aplicacion.modelo.logic.*;
import aplicacion.herramientas.java.table.*;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Point;

public class _Logic extends Logic {
	private _Frame frame=null;
	private String default_Format="dd-MM-yyyy"; 
	private JTextField caller=null;
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void load(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		mark(cal);
	}
	
	public void calculate_coordinates(){
		
			Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
			if (caller!=null){
				int locx=caller.getLocationOnScreen().x;
				int locy=caller.getLocationOnScreen().y+caller.getHeight();
				if (locx+frame.getWidth()>s.width){
					int dif=(locx+frame.getWidth())-s.width;
					locx=locx-dif-10;
					//locx=locx-(frame.getWidth()-caller.getWidth());
				}
				if (locy+frame.getHeight()>s.height){
					locy=locy-(caller.getHeight()+frame.getHeight());
				}
				frame.setLocation(locx, locy);	
			}
		
	}
	
	public void updateSizes(){
		
	}
	
	public void move(int row,int col){
		int max=this.get_days();
		if (row<0)row=0;
		int number=row*7-this.get_day_week()+col+1;//this.get_day_week()
		System.out.println("initial day="+this.get_day_week()+" dias="+max+" number="+number);
		if (number<=max & number>0){
			if (row<=frame.getJTable().getRowCount()-1 & row>=0){
				if (col<=frame.getJTable().getColumnCount()-1 & col>=0){
					this.show_time(row,col);
				}
				
			}	
		}else {
			frame.get_txt_fecha().setText("");
		}
		
		
		
		
	}
	
	public void fillTable(){
		this.create_table();
		int ini=get_day_week();//domingo
		  
		int max=6;
		int cellx=ini;
		int celly=0;
		for (int i=0;i<=this.get_days();i++){
			if (cellx>max){
				cellx=0;
				celly++;
			}else {
				cellx++;	
			}
			
		}
		DefaultTableModel defaultTableModel=(DefaultTableModel) frame.getJTable().getModel();	
		for (int j=0;j<celly+1;j++){
			if (j==(defaultTableModel.getRowCount()-1)){
				defaultTableModel.setRowCount(defaultTableModel.getRowCount()+1);
			}
		}
		cellx=ini;
		celly=0;
		for (int i=1;i<=this.get_days();i++){
			frame.getJTable().setValueAt(""+i, celly, cellx);
			//System.out.println("cell"+celly+":"+cellx);
			if (cellx==max){
				cellx=0;
				celly++;
			}else {
				cellx++;	
			}
			
		}
		mark(Calendar.getInstance());
	}
	
	private void create_table(){
		_Constructor constructor=(_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();

		Column col = new Column();
		
		String []dias=new String[]{
				"Dom",
				"Lun",
				"Mar",
				"Mie",
				"Jue",
				"Vie",
				"Sab",
				
		};
		for (int i=0;i<dias.length;i++){
			col = new Column();
			col.setName(dias[i]);
			col.setWidth(30);
			col.setAligment(JTextField.CENTER);
			col.setEditable(false);
			table.addColumn(col);
				
		}
		
		
		table.setSortable(false);
		Object[][] results=new Object[][]{
				{"","","","","","",""}
		};
		table.setData(results);
		table.setName(_Interface._table_dias);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();
		
		JTable _table = table.getTable();
		
		
		

		frame.setJTable(_table);
		
	}
	
	public void init(){
		System.out.println("frma null?"+frame);
		Runnable execute=new Runnable(){
			public void run(){
					frame.setVisible(true);
					calculate_coordinates();
					fechas();
					fillTable();
					Calendar Ca=Calendar.getInstance();
					
					frame.requestFocusInWindow();
					load(Ca.getTime());
				
						
			}
		};
		this.invokeLater(execute);
	}
	
	public void fechas(){
		
		Calendar Cal=Calendar.getInstance();
		int year=Cal.get(Calendar.YEAR);
		int m=Cal.get(Calendar.MONTH);
		for (int i=year-5;i<year+5;i++){
			frame.get_list_anio().addItem(""+(i));	
		}
		
		frame.get_list_anio().setSelectedIndex(5);
		
		frame.get_list_mes().setSelectedIndex(m);
		
	}
	
	public String getTime(){
		int row=frame.getJTable().getSelectedRow();
		int col=frame.getJTable().getSelectedColumn();
		String s1=this._getTime(row,col,frame.get_list_mes(),frame.get_list_anio());
		return s1;
	}
	
	public String _getTime(int row, int col,JComboBox _mes,JComboBox _anio){
		
		int mes=_mes.getSelectedIndex();
		int anio=new Integer(_anio.getSelectedItem().toString());
		int day=-1;
		try{
			day=new Integer((String)frame.getJTable().getValueAt(row, col));
		}catch(Exception e){
			//System.out.println("Error Dia:"+e.getMessage());
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, anio);
		
		if (day>0){
			cal.set(Calendar.DAY_OF_MONTH, day);	
		}
		
		//System.out.println(cal.getTime());
		 Locale locale = Locale.getDefault();
		    String s1="";
		    Date date=null;
		    DateFormat formatter;
		    try {
		    	
		    	date = cal.getTime();
		    	formatter = new SimpleDateFormat(default_Format, locale);
		        s1 = formatter.format(date);
		    }
		        catch(Exception e){
		        	
		        }
		        return s1;
	}
	
	public int get_days(){
		String s_year=frame.get_list_anio().getSelectedItem().toString();
		int year=new Integer(s_year);
		int month=0;
		switch(frame.get_list_mes().getSelectedIndex()){
		case 0:
			month=Calendar.JANUARY;
		break;
		case 1:
			month=Calendar.FEBRUARY;
		break;
		case 2:
			month=Calendar.MARCH;
		break;
		case 3:
			month=Calendar.APRIL;
		break;
		case 4:
			month=Calendar.MAY;
		break;
		case 5:
			month=Calendar.JUNE;
		break;
		case 6:
			month=Calendar.JULY;
		break;
		case 7:
			month=Calendar.AUGUST;
		break;
		case 8:
			month=Calendar.SEPTEMBER;
		break;
		case 9:
			month=Calendar.OCTOBER;
		break;
		case 10:
			month=Calendar.NOVEMBER;
		break;
		case 11:
			month=Calendar.DECEMBER;
		break;
		}

		Calendar cal = new GregorianCalendar(year, month, 1);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//days++;
		//System.out.println("dias del mes "+days);
		return days;
		}
	
	public void mark(Calendar cal){
		
		
		int t_m=cal.get(Calendar.MONTH);
		int t_y=cal.get(Calendar.YEAR);
		int t_d=cal.get(Calendar.DAY_OF_MONTH);
		int s_m=frame.get_list_mes().getSelectedIndex();
		int s_y=new Integer(frame.get_list_anio().getSelectedItem().toString());
		int ini=get_day_week();//domingo
		System.out.println("buscando FECHA>"+t_d+" en "+this.get_days()+" dias");
		  if (t_m==s_m && t_y==s_y){
			  int max=6;
				int cellx=ini;
				int celly=0;
				for (int i=0;i<=this.get_days();i++){
					if (cellx>max){
						cellx=0;
						celly++;
					}else {
						cellx++;	
					}
					
				}
				DefaultTableModel defaultTableModel=(DefaultTableModel) frame.getJTable().getModel();
				for (int j=0;j<celly+1;j++){
					if (j==(defaultTableModel.getRowCount()-1)){
						defaultTableModel.setRowCount(defaultTableModel.getRowCount()+1);
					}
				}
				cellx=ini;
				celly=0;
				
				for (int i=1;i<this.get_days()+1;i++){
					System.out.println("dia "+i+" en celda "+cellx+":"+celly);
					if (i==t_d){
					frame.getJTable().requestFocusInWindow();
					frame.getJTable().clearSelection();	
					frame.getJTable().changeSelection(celly, cellx, false, false);
					System.out.println("SELECCIONANDO FECHA>"+cellx+":"+celly);
					//frame.getJTable().editCellAt(celly, cellx);
					
					
					}
					
					if (cellx==max){
						cellx=0;
						celly++;
					}else {
						cellx++;	
					}
					
				}
		  }
		  show_time();
	}

	public void show_time(){
		System.out.println("showtime");
		frame.get_txt_fecha().setText(""+this.getTime());
		
	}
	
	public void show_time2(){
		System.out.println("showtime2");
		this.create_table();
		this.fillTable();
		int col=this.get_day_week();
		this.frame.getJTable().changeSelection(0, col, false,false);
		
	}
	public void show_time(int row,int col){
		//System.out.println("showtime");
		frame.get_txt_fecha().setText(""+this._getTime(row, col,frame.get_list_mes(),frame.get_list_anio()));
		
	}
	
	public void make_fecha(){
		String fecha=this.getTime();
		System.out.println("fecha: "+fecha);
			if (this.caller!=null){
				try {
					caller.setText(fecha);
				}catch(Exception e){
					
				}
			}
			//this.getConstructor().dispose();
			frame.setVisible(false);
	}
	public void exit(){
		//this.getConstructor().dispose();
		frame.setVisible(false);
	}
	
	
	public int get_day_week(){
		String s_year=frame.get_list_anio().getSelectedItem().toString();
		int year=new Integer(s_year);
		int month=0;
		switch(frame.get_list_mes().getSelectedIndex()){
		case 0:
			month=Calendar.JANUARY;
		break;
		case 1:
			month=Calendar.FEBRUARY;
		break;
		case 2:
			month=Calendar.MARCH;
		break;
		case 3:
			month=Calendar.APRIL;
		break;
		case 4:
			month=Calendar.MAY;
		break;
		case 5:
			month=Calendar.JUNE;
		break;
		case 6:
			month=Calendar.JULY;
		break;
		case 7:
			month=Calendar.AUGUST;
		break;
		case 8:
			month=Calendar.SEPTEMBER;
		break;
		case 9:
			month=Calendar.OCTOBER;
		break;
		case 10:
			month=Calendar.NOVEMBER;
		break;
		case 11:
			month=Calendar.DECEMBER;
		break;
		}

		Calendar cal = new GregorianCalendar(year, month, 1);
		int day=cal.get(Calendar.DAY_OF_WEEK)-1;
		return day;
	}

	public String getDefault_Format() {
		return default_Format;
	}

	public void setDefault_Format(String default_Format) {
		this.default_Format = default_Format;
	}

	public JTextField getCampo() {
		return caller;
	}

	public void setCampo(JTextField campo) {
		this.caller = campo;
	}
	
}
