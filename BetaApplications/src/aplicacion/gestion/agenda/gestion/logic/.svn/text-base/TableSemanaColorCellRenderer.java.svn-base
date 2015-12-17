package aplicacion.gestion.agenda.gestion.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import aplicacion.gestion.agenda.gestion.interfaces._Interface;
import aplicacion.gestion.agenda.gestion.logic.*;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableSemanaColorCellRenderer extends DefaultTableCellRenderer {
    private  _Logic logic=null;
    private int x,y;
    private boolean day=false;
    private String table="";
    
    public void setLogic(_Logic logic){
    	this.logic=logic;
    }
    
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		
		
		Color default_foreground = new Color(22, 22, 22);
		Color default_background = new Color(155, 155, 155);
		Color verde = new Color(255, 100, 100);
		Color azul = new Color(100, 100, 255);
		c.setBackground(default_background);
		c.setForeground(default_foreground);
		String color="";
		if (this.table==_Interface._table_mensual_item){
			color=logic.getColorAvisoSemanaMonth(table, rowIndex,y,x);
		}else{
			color=logic.getColorAvisoSemana(table, rowIndex,y,x);	
		}
		
		if (color.compareTo("")!=0){
			Color _color=logic.getColor(color);
			if (isSelected){
				_color=logic.getSelectedColor(color);
			}
			c.setBackground(_color);	
		}
		if (c instanceof JComponent){
			String tooltip="";
			if (this.table==_Interface._table_mensual_item){
				tooltip=logic.getToolTipAvisoSemanaMonth(table, rowIndex,y,x);
			}else{
				tooltip=logic.getToolTipAvisoSemana(table, rowIndex,y,x);
			}
			((JComponent)c).setToolTipText(tooltip);
		}
		
		
		
		
		return c;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isDay() {
		return day;
	}

	public void setDay(boolean day) {
		this.day = day;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
}