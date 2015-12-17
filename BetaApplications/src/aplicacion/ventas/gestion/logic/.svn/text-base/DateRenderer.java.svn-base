package aplicacion.ventas.gestion.logic;
import javax.swing.table.*;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateRenderer extends DefaultTableCellRenderer.UIResource {
	  private  _Logic logic=null;
	    
	    public void setLogic(_Logic logic){
	    	this.logic=logic;
	    }
        DateFormat formatter;  
        public DateRenderer() { super(); }  
       
        public void setValue(Object value) {  
            if (formatter==null) {  
            	formatter = new SimpleDateFormat("dd-MM-yyyy");  
            }  
    	    try {
				setText((value == null) ? "" : formatter.format(value));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}  
       }  

}