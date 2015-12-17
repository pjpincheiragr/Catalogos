package aplicacion.gestion.caja.manejo.logic;
import javax.swing.JTextField;
import javax.swing.table.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class MoneyRenderer extends DefaultTableCellRenderer.UIResource {  
		DecimalFormat formatter;  
        public MoneyRenderer() { super(); }  
       
        public void setValue(Object value) {  
            if (formatter==null) {  
            	
            	 formatter = new DecimalFormat("0.00");
            }  
            
            try {
				double tmp=0.0;
				if (value.getClass() ==String.class){
					tmp=new Double(value.toString());
					value=tmp;
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            setText((value == null) ? "" : formatter.format(value));  
            //this.setHorizontalTextPosition(JTextField.RIGHT);
            this.setHorizontalAlignment(JTextField.RIGHT);
            
       }  

}