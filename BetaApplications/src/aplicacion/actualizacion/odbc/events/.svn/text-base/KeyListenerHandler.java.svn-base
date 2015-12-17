package aplicacion.actualizacion.odbc.events;
import aplicacion.modelo.events.*;
import aplicacion.actualizacion.odbc.logic.*;
import aplicacion.actualizacion.odbc.interfaces.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
public class KeyListenerHandler extends _KeyListenerHandler {
	
		public void procesar(KeyEvent e){
			_Logic logic=(_Logic) this._logic;
			
			if (e.getSource() instanceof JTextField){
				JTextField tx=(JTextField) e.getSource();
				if (tx.getName()==_Interface._txt_idproveedor){
					if (e.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluarProveedor(tx);
					}
				}
				if (tx.getName()==_Interface._txt_idpolitica){
					if (e.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluarPolitica(tx);
					}
				}
				if (tx.getName()==_Interface._txt_idcomprobante){
					if (e.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluar_idcomprobante(tx);
					}
				}
			}
		}
}

