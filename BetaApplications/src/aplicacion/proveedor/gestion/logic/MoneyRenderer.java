package aplicacion.proveedor.gestion.logic;
import javax.swing.table.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class MoneyRenderer extends DefaultTableCellRenderer.UIResource {  
        NumberFormat formatter;  
        public MoneyRenderer() { 
        	super();
        	setAlignmentY(RIGHT_ALIGNMENT );
        }  
       
        public void setValue(Object value) {  
            if (formatter==null) {  
            	  
            	 
            	 formatter = new DecimalFormat("#0.00");
            	 
            	 

            }  
            
            try {
				setText((value == null) ? "" : formatter.format(value));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}  
       }  

}