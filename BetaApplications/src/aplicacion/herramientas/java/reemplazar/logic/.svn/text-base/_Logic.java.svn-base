package aplicacion.herramientas.java.reemplazar.logic;

import java.util.LinkedList;
import java.util.ArrayList;
import aplicacion.herramientas.java.reemplazar.gui.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import stock1.Convertidor;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import java.util.List;
import java.awt.Rectangle;
public class _Logic extends Logic {
	private _Data data;
	private _Frame frame;
	private String Memory="";
	private JTable Table=null;
	private int col=-1;
	private int proximo=-1;
	private List<String> Busquedas;
	private List<String> Reemplazos;
	
	public _Logic(){
		
		super();
		Busquedas=new ArrayList<String>();
		Reemplazos=new ArrayList<String>();
	}
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public void clean(){
		Busquedas=new ArrayList<String>();
		Reemplazos=new ArrayList<String>();
	}
	
	public void Ctrl_c(boolean searchf){
		if (searchf){
			Memory=(String)frame.get_lst_search().getSelectedItem().toString();	
		}else {
			Memory=(String)frame.get_lst_replacement().getSelectedItem().toString();
		}
		
	}
	
	public void Ctrl_P(boolean searchf){
	if (Memory!=null){
		if (searchf){
			frame.get_lst_search().addItem(Memory);
			frame.get_lst_search().setSelectedItem(Memory);
		}else {
			frame.get_lst_replacement().addItem(Memory);
			frame.get_lst_replacement().setSelectedItem(Memory);	
		}
		
	}
	}
	
	public void setMemory(String mem){
		this.Memory=mem;
		frame.get_lst_search().addItem(Memory);
		frame.get_lst_search().setSelectedItem(Memory);
		
	}
	
	public void setSearch(List<String> l){
		this.Busquedas=l;
	}
	public void setReplacements(List<String>  l){
		this.Reemplazos=l;
	}
	public void setColumn(int i){
		this.col=i;
	}
	public void setTable(JTable t){
		this.Table=t;
	}
	public void replace_event(){
		int currentrow=Table.getSelectedRow();
		int idx=currentrow;
		String rr="";
		try {
			rr=frame.get_lst_replacement().getSelectedItem().toString();
			
		}catch(Exception e){
			
		}
		if (rr.compareTo("")!=0){
			if (!Reemplazos.contains(rr)){
				Reemplazos.add(rr);
				this.frame.get_lst_replacement().setDataList(Reemplazos);
				this.frame.get_lst_replacement().setSelectedItem(rr);
			}
		}
		boolean found=false;
		String match=((String)Table.getValueAt(idx, col));
		System.out.println("Buscando "+match);
		if (frame.get_chk_exacta().isSelected()){
			found=match.toUpperCase().compareTo(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase())==0;
		}else {
			found=match.toUpperCase().contains(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());		
		}
		
		System.out.println("Si lo encontras reemplazalo por "+rr);
		if (found){
			System.out.println("Reemplazo "+this.frame.get_lst_search().getSelectedItem().toString().toUpperCase()+" "+match+" ");
			
				int i1=match.toUpperCase().indexOf(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());
				int i2=this.frame.get_lst_search().getSelectedItem().toString().length();
				String auxmatch=match.substring(i1, i1+i2);
				match=match.replace(auxmatch, rr);
				System.out.println("reemplazo "+match);
				Table.setValueAt(match, idx, col);	
				Table.addRowSelectionInterval(idx, idx);
				Table.addColumnSelectionInterval(col, col);
		}
	}
	
	public void cut_off_event(){
		int currentrow=Table.getSelectedRow();
		int idx=currentrow;
		Convertidor CV=new Convertidor();
		boolean found=false;
		String match=((String)Table.getValueAt(idx, col));
		if (frame.get_chk_exacta().isSelected()){
			found=match.toUpperCase().compareTo(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase())==0;
		}else {
			found=match.toUpperCase().contains(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());		
		}
		if (found){
				match=CV.sacarRepeticiones(match, this.frame.get_lst_search().getSelectedItem().toString());
				System.out.println("reemplazo "+match);
				Table.setValueAt(match, idx, col);	
				Table.addRowSelectionInterval(idx, idx);
				Table.addColumnSelectionInterval(col, col);
		}
	}
	
	public void search_and_cut_offall_event(){
		int currentrow=Table.getSelectedRow();
		int idx=currentrow;
		boolean found=false;
		Convertidor CV=new Convertidor();
		for (idx=currentrow;idx<Table.getRowCount();idx++){
			String match=((String)Table.getValueAt(idx, col));
			if (frame.get_chk_exacta().isSelected()){
				found=match.toUpperCase().compareTo(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase())==0;
			}else {
				found=match.toUpperCase().contains(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());		
			}
			if (found){
				match=CV.sacarRepeticiones(match, this.frame.get_lst_search().getSelectedItem().toString());
				System.out.println("reemplazo "+match);
				int rows[]=Table.getSelectedRows();
				for (int u=0;u<rows.length;u++){
					Table.removeRowSelectionInterval(rows[u], rows[u]);	
				}
				Table.setValueAt(match, idx, col);	
				Table.addRowSelectionInterval(idx, idx);
				Table.addColumnSelectionInterval(col, col);
				this.showCell(idx, col);
			}
		}
	}
	
	public void search_and_replaceall_event(){
		int currentrow=Table.getSelectedRow();
		int idx=currentrow;
		boolean found=false;
		for (idx=currentrow;idx<Table.getRowCount();idx++){
			String match=((String)Table.getValueAt(idx, col));
			if (frame.get_chk_exacta().isSelected()){
				found=match.toUpperCase().compareTo(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase())==0;
			}else {
				found=match.toUpperCase().contains(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());		
			}
			if (found){
				if(frame.get_lst_replacement().getItemCount()<=0 | frame.get_lst_replacement().getSelectedIndex()==-1){
					
						int i1=match.toUpperCase().indexOf(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());
						int i2=this.frame.get_lst_search().getSelectedItem().toString().length();
						String auxmatch=match.substring(i1, i1+i2);
						match=match.replace(auxmatch, "");
						System.out.println("reemplazo "+match);
						Table.setValueAt(match, idx, col);
						int rows[]=Table.getSelectedRows();
						for (int u=0;u<rows.length;u++){
							Table.removeRowSelectionInterval(rows[u], rows[u]);	
						}
						Table.addRowSelectionInterval(idx, idx);
						Table.addColumnSelectionInterval(col, col);
						this.showCell(idx, col);
				}else {
					if (frame.get_lst_replacement().getSelectedItem().toString()!=null){
						int i1=match.toUpperCase().indexOf(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());
						int i2=this.frame.get_lst_search().getSelectedItem().toString().length();
						String auxmatch=match.substring(i1, i1+i2);
						match=match.replace(auxmatch, frame.get_lst_replacement().getSelectedItem().toString());
						System.out.println("reemplazo "+match);
						int rows[]=Table.getSelectedRows();
						for (int u=0;u<rows.length;u++){
							Table.removeRowSelectionInterval(rows[u], rows[u]);	
						}
						Table.setValueAt(match, idx, col);	
						Table.addRowSelectionInterval(idx, idx);
						Table.addColumnSelectionInterval(col, col);
					}	
				}
				
			}
		}
	}
	
	
	public void search_and_replace_event(){
		this.replace_event();
	
		if (frame.get_chk_adelante().isSelected()){
			this.search_forward_event();
		}else {
			this.search_backward_event();
		}
		
	}
	
	public void search_and_cut_off_event(){
		this.cut_off_event();
	
		if (frame.get_chk_adelante().isSelected()){
			this.search_forward_event();
		}else {
			this.search_backward_event();
		}
		
	}
	
	public void showCell(int row, int column) {
	    Rectangle rect =  Table.getCellRect(row, column, true);
	    Table.scrollRectToVisible(rect);
	    /*Table.clearSelection();
	    Table.changeSelection(row,col,false,false);
	    DefaultTableModel model = (DefaultTableModel) Table.getModel();
	    model.fireTableDataChanged();*/;
	    }
	
	public void show(){
		frame.setVisible(true);
		frame.get_lst_search().requestFocusInWindow();
	}
	public void search_forward_event(){

		int currentrow=Table.getSelectedRow();
		String busqueda=frame.get_lst_search().getSelectedItem().toString();
		if (busqueda.compareTo("")!=0){
			if (!Busquedas.contains(busqueda)){
				Busquedas.add(busqueda);
				this.frame.get_lst_search().setDataList(Busquedas);
				this.frame.get_lst_search().setSelectedItem(busqueda);
			}
		}
		if (proximo==-1){
			proximo=currentrow;
		}else {
			currentrow=proximo;
		}
		if (col!=Table.getSelectedColumn()){
			col=Table.getSelectedColumn();
		}
		boolean found=false;
		int idx=currentrow;
		if (frame.get_chk_exacta().isSelected()){
			found=((String)Table.getValueAt(idx, col)).toUpperCase().compareTo(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase())==0;
		}else {
			found=((String)Table.getValueAt(idx, col)).toUpperCase().contains(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());		
		}
		
		while (!found & idx<Table.getRowCount()){
			if (frame.get_chk_exacta().isSelected()){
				found=((String)Table.getValueAt(idx, col)).toUpperCase().compareTo(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase())==0;
			}else {
				try {
					found=((String)Table.getValueAt(idx, col)).toUpperCase().contains(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());					
				}catch(Exception e){
					
				}
				
			}
			
			if (!found)idx++;
		}
		if (found){
			int rows[]=Table.getSelectedRows();
			for (int u=0;u<rows.length;u++){
				Table.removeRowSelectionInterval(rows[u], rows[u]);	
			}
			
			
			Table.addRowSelectionInterval(idx, idx);
			this.showCell(idx, col);
			//Table.s
			proximo=idx+1;
			System.out.println("Found at "+idx);
		}else{
			error("No Encontrado");
		}
	}
	
	public void search_backward_event(){

		int currentrow=Table.getSelectedRow();
		if (proximo==-1){
			proximo=currentrow;
		}else {
			currentrow=proximo;
		}
		if (col!=Table.getSelectedColumn()){
			col=Table.getSelectedColumn();
		}
		boolean found=false;
		int idx=currentrow;
		if (frame.get_chk_exacta().isSelected()){
			found=((String)Table.getValueAt(idx, col)).toUpperCase().compareTo(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase())==0;
		}else {
			found=((String)Table.getValueAt(idx, col)).toUpperCase().contains(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());		
		}
		
		while (!found & idx>0){
			if (frame.get_chk_exacta().isSelected()){
				found=((String)Table.getValueAt(idx, col)).toUpperCase().compareTo(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase())==0;
			}else {
				found=((String)Table.getValueAt(idx, col)).toUpperCase().contains(this.frame.get_lst_search().getSelectedItem().toString().toUpperCase());
			}
			
			if (!found)idx--;
		}
		if (found){
			int rows[]=Table.getSelectedRows();
			for (int u=0;u<rows.length;u++){
				Table.removeRowSelectionInterval(rows[u], rows[u]);	
			}
			Table.addRowSelectionInterval(idx, idx);
			proximo=idx-1;
			System.out.println("Found at "+idx);
			this.showCell(idx, col);
		}else{
			error("No Encontrado");
		}
	}
	
	public void search_event(){
		if (frame.get_chk_adelante().isSelected()){
			this.search_forward_event();
		}else {
			this.search_backward_event();
		}
	}

	public int getProximo() {
		return proximo;
	}

	public void setProximo(int proximo) {
		this.proximo = proximo;
	}
	
	public void evaluate_adelante(JRadioButton chk){
			if (chk.isSelected()){
				frame.get_chk_atras().removeItemListener(this.getConstructor().getItemListener());
				frame.get_chk_atras().setSelected(false);
				frame.get_chk_atras().addItemListener(this.getConstructor().getItemListener());
			}
	}
	
	public void exit(){
		frame.setVisible(false);
	}
	public void evaluate_atras(JRadioButton chk){
		if (chk.isSelected()){
			frame.get_chk_adelante().removeItemListener(this.getConstructor().getItemListener());
			frame.get_chk_adelante().setSelected(false);
			frame.get_chk_adelante().addItemListener(this.getConstructor().getItemListener());
		}
}
}
