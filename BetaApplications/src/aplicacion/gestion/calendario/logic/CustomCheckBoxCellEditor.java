package aplicacion.gestion.calendario.logic;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Component;

public class CustomCheckBoxCellEditor  extends DefaultCellEditor implements TableCellEditor {
	        // This is the component that will handle the editing of the cell value
	        JComponent component = null;
	        private Class type=null;
	    
	        public void setType(Class tipo){
	        	this.type=tipo;
	        }
	        
	        public CustomCheckBoxCellEditor(JCheckBox chk){
	        	super(chk);
	        	this.component=chk;
	        }
	        
	        public Object getCellEditorValue() {
	        	boolean valor=((JCheckBox)component).isSelected();
	        	
	        	if (type==Boolean.class){
	        		return 	valor;
	        	}else {
	        		return valor;
	        	}
	            
	        }
	    }

