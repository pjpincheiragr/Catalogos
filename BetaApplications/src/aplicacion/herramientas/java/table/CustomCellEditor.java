package aplicacion.herramientas.java.table;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Component;

public class CustomCellEditor  extends DefaultCellEditor implements TableCellEditor {
	        // This is the component that will handle the editing of the cell value
	        JComponent component = null;
	        private Class type=null;
	    
	        public void setType(Class tipo){
	        	this.type=tipo;
	        }
	        
	        public CustomCellEditor(JTextField tx){
	        	super(tx);
	        	this.component=tx;
	        }
	        
	        public Object getCellEditorValue() {
	        	String valor=((JTextField)component).getText();
	        	System.out.println("valor de txt?"+valor);
	        	if (type==Double.class){
	        		valor=valor.replace(",", ".");
	        		return 	new Double(valor);
	        	}else {
	        		return valor;
	        	}
	            
	        }
	    }

