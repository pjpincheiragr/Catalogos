package aplicacion.herramientas.java.importar.events;


import aplicacion.herramientas.java.importar.interfaces.*;
import aplicacion.herramientas.java.importar.logic.*;
import java.awt.event.KeyEvent;
import aplicacion.modelo.events._KeyListenerHandler;
import javax.swing.*;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			JTable table=null;
			int row=-1;
			int col=-1;
			if (tx.getParent() instanceof JTable){
				table=(JTable) tx.getParent();
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
			}
			
			if (tx.getName()==_Interface._txt_archivo){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.buscar_archivo();
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.cargar_archivo();
				}
			}
			
	}
}
}
