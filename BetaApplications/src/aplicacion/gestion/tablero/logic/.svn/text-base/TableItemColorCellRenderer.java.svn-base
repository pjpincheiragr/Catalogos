package aplicacion.gestion.tablero.logic;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class TableItemColorCellRenderer extends DefaultTableCellRenderer.UIResource {
	private _Logic logic=null;
	DecimalFormat formatter;
	public _Logic getLogic() {
		return logic;
	}

	public void setLogic(_Logic logic) {
		this.logic = logic;
	}

	public void setValue(Object value) {  
        if (formatter==null) {  
        	
        	 formatter = new DecimalFormat("0.00");
        }  
        boolean ok=true;
        try {
			double tmp=0.0;
			if (value!=null){
				if (value.getClass() ==String.class){
					tmp=new Double(value.toString());
					value=tmp;
				}	
			}else{
				value="";
				ok=false;
			}
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ok=false;
		}
        if (!ok){
        	setText(value.toString());
        	this.setHorizontalAlignment(JTextField.LEADING);
        }else{
        	setText((value == null) ? "" : formatter.format(value));
        	this.setHorizontalAlignment(JTextField.RIGHT);
        }
          
        //
        
        
   } 
	
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, rowIndex, vColIndex);
					c.setForeground(logic.getForegroundColor(rowIndex, vColIndex,isSelected));
					c.setBackground(logic.getBackgroundColor(rowIndex, vColIndex,isSelected));
					
		return c;

	}

}