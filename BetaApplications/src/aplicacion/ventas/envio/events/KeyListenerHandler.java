package aplicacion.ventas.envio.events;
import aplicacion.ventas.envio.logic._Logic;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import aplicacion.ventas.envio.interfaces._Interface;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		
		
		if (event.getSource() instanceof JComboBox){
			JComboBox lst=(JComboBox) event.getSource();
			_logic.evaluate_tipo_reporte(lst);
		}
		
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			

			
			
			if (tx.getName()== _Interface._txt_fecha_desde){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarFecha(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					
				}
			}
			
			
		}
		
		
		
					
	}
}
