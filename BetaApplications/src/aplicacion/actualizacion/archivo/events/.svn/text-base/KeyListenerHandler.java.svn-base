package aplicacion.actualizacion.archivo.events;


import aplicacion.actualizacion.archivo.interfaces.*;
import aplicacion.actualizacion.archivo.logic.*;
import java.awt.event.KeyEvent;
import aplicacion.modelo.events._KeyListenerHandler;
import javax.swing.*;
/**
 * @author Agustin Wisky
 * @company Wismi S.A.
 * @since 10-10-2009
 */
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
			
			if (tx.getName()==_Interface._txt_idproveedor){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarProveedor(tx);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluarProveedor(tx);
				}
			}

			if (tx.getName()==_Interface._txt_idcomprobante){
				
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluar_idcomprobante(tx);
				}
			}
			if (tx.getName()==_Interface._txt_idpolitica){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarPolitica(tx);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluar_politica(tx);
				}
			}
			
			if (tx.getName()==_Interface._txt_linea){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarLinea(tx);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluar_linea(tx);
				}
			}
			if (tx.getName()==_Interface._txt_archivo){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.buscar_archivo();
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.goRead();
				}
			}
			
	}
}
}
