package aplicacion.actualizacion.global.events;
import aplicacion.modelo.events.*;
import aplicacion.actualizacion.global.logic.*;
import aplicacion.actualizacion.global.interfaces.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesar(KeyEvent e){
		_Logic logic=(_Logic) this._logic;
		
		if (e.getSource() instanceof JTextField){
			JTextField tx=(JTextField) e.getSource();
			
			if (tx.getName()==_Interface._txt_idcomprobante){
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluarIdcomprobante(tx);
				}
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.clean();
				}
				if (e.getKeyCode()==KeyEvent.VK_F5){
					logic.BuscarGlobal(tx);
				}
			}
			if (tx.getName()==_Interface._txt_path){
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluarPath(tx);
				}
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.borrarRuta();
				}

			}
			
			
		}
		

		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) e.getSource();

			if (chk.getName()==_Interface._chk_seleccionar){
//				 if (e.getKeyCode()==KeyEvent.VK_SPACE){
//					 System.out.println("evento teclado");
//
//					 logic.seleccionar(chk);
//				 	}
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.seleccionar(chk);
				 	}
				 if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}

			 	}
		}
	}
}


