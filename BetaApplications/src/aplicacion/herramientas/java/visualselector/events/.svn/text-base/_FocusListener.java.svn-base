package aplicacion.herramientas.java.visualselector.events;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JFrame;
import aplicacion.herramientas.java.visualselector.logic.*;
public class _FocusListener  implements FocusListener {
	private _Logic logic;
	
	public _FocusListener(_Logic logic){
		this.logic=logic;
	}
	
	public void focusGained(FocusEvent e) {
		//System.out.println("Focus gained"+ e.getID()+" "+e.getSource());
		logic.hide();
	    }

	public void focusLost(FocusEvent e) {
	    	//System.out.println("Focus Lost"+ e.getID()+" "+e.getSource());
	    	if (e.getSource() instanceof JTextField){
	    		JTextField tx=(JTextField)e.getSource();
	    		//logic.evalLostFocus(tx);	
	    	}
	    	if (e.getSource() instanceof JTable){
	    		
	    	}
	}
}
