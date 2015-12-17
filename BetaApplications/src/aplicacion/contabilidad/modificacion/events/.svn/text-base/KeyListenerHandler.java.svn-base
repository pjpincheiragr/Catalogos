package aplicacion.contabilidad.modificacion.events;
import aplicacion.contabilidad.modificacion.interfaces._Interface;
import aplicacion.contabilidad.modificacion.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesarEvento(KeyEvent event){
		_Logic logic=(_Logic) this._logic;
		
		if (event.getSource() instanceof JComboBox){
			JComboBox cb=(JComboBox) event.getSource();
			if (cb.getName()==_Interface._lst_letra){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic._evaluar_letra(cb);	
				}
				
			}
			
		}
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			if (tx.getName()==_Interface._txt_fecha){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					
					logic._evaluar_fecha(tx);
				}
				
			}
			
			if (tx.getName()==_Interface._txt_numero){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					
					logic._evaluar_numero(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_BACK_SPACE){
					
				}
			}
			
			if (tx.getName()==_Interface._txt_sucursal){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					
					logic._evaluar_sucursal(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_BACK_SPACE){
					
				}
			}
			if (tx.getName()==_Interface._txt_idcomprobante){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					
					logic._evaluar_idcomprobante(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_BACK_SPACE){
					
				}
			}
		}
		
	}
}
