package aplicacion.gestion.calendario.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class TableColorCellRenderer extends DefaultTableCellRenderer {
    private  _Logic logic=null;
    private String nombre="";
    
    public void setNombre(String nombre){
    	this.nombre=nombre;
    }
    
    public void setLogic(_Logic logic){
    	this.logic=logic;
    	System.out.println("Logic> "+logic.getData());
    }
    
	public void setValue(Object value) {  
        	setText(nombre);
        	this.setHorizontalAlignment(JTextField.LEADING);
        
   } 
    
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
		//Color azul = new Color(100, 100, 255);
		//Color color=logic.getColor(rowIndex, vColIndex,table);
		//Color default_background = color;
		c.setForeground(Color.black);
		/*if (isSelected){
			c.setBackground(azul);
		}else {
			c.setBackground(default_background);	
		}*/
		JLabel tx=new JLabel();
		tx.setText(logic.getDayValue(rowIndex, vColIndex));
		tx.setHorizontalAlignment(JLabel.CENTER);
		tx.setFont(new Font("Dialog", Font.BOLD, 8));
		boolean selected=logic.getValue(rowIndex, vColIndex);
		if (selected){
			
			tx.setBackground(Color.white);
		}else{
			tx.setBackground(Color.red);
		}
		//tx.setOpaque(true);
		return tx;
	}
	
}