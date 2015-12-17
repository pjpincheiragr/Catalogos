/*
 * Created on Apr 4, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package aplicacion.herramientas.java.sortableselector.logic;

import java.util.Comparator;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.table.*;



/**
 * @version 1.0 02/25/99
 */
public class TableSorter implements Comparator {
	SortableTableModel model;
	int colIndex;
    boolean ascending;
    
    public TableSorter (int colIndex, boolean ascending,SortableTableModel model) {
		this.model = model;
        this.colIndex = colIndex;
        this.ascending = ascending;
    }

    


    
	 public int compare(Object a, Object b) {
         Vector v1 = (Vector)a;
         Vector v2 = (Vector)b;
         Object o1 = v1.get(colIndex);
         Object o2 = v2.get(colIndex);
 
         // Treat empty strains like nulls
         if (o1 instanceof String && ((String)o1).length() == 0) {
             o1 = null;
         }
         if (o2 instanceof String && ((String)o2).length() == 0) {
             o2 = null;
         }
        
         // Sort nulls so they appear last, regardless
         // of sort order
         if (o1 == null && o2 == null) {
             return 0;
         } else if (o1 == null) {
             return 1;
         } else if (o2 == null) {
             return -1;
         } else {
        	 return compareTool(o1,o2);
         }
     }
	 
	 public int compareTool(Object o1,Object o2){
		 int c=0;
		 Class type = model.getColumnClass(colIndex);
         System.out.println("sort "+o1.getClass()+" class!!");
         
         boolean _double=true;
         try {
        	 new Double(o1.toString());
         }catch(Exception e){
        	 _double=false;;
         }
		 if (_double){
			 if (ascending) {
				 c=compareDouble(o1,o2);
			 }else {
				 c=compareDouble(o2,o1);	 
			 }
		 }
		 /*
		 if (o1 instanceof Comparable) {
             if (ascending) {
            	 
                 return ((Comparable)o1).compareTo(o2);
             } else {
                 return ((Comparable)o2).compareTo(o1);
             }
         } else {
        	 
        	 
             if (ascending) {
            	 if (type == Double.class){
            		 System.out.println("sort double class!!");
            		 return compareDouble(o1,o2);
            	 }else{
            		 return o1.toString().compareTo(o2.toString());	 
            	 }
                 
             } else {
            	 if (type == Double.class){
            		 System.out.println("sort double class!!");
            		 return compareDouble(o2,o1);
            	 }else{
            		 return o2.toString().compareTo(o1.toString());
            	 }
             }
         } 
		  * */
		 return c;
	 }
	 public int compareDouble(Object o1, Object o2) {
			Double v1=0.0;
			Double v2=0.0;
			
				String val1=(String) o1;
				String val2=(String) o2;
				boolean error=false;
				try {
					v1=new Double(val1.replace(",", ""));
				}catch(Exception e){
					error=true;
				}
				try {
					v2=new Double(val2.replace(",", ""));
				}catch(Exception e){
					error=true;
				}
				if (!error){
					return compare(v1,v2);	
				}else {
					return compare(o1, o2);
				}	
			
			
			
		}
	 
}

