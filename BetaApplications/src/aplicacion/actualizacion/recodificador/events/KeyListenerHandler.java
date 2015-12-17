package aplicacion.actualizacion.recodificador.events;


import aplicacion.actualizacion.recodificador.interfaces.*;
import aplicacion.actualizacion.recodificador.logic.*;
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
			
			if (tx.getName()==_Interface._table_idrecodificacion){
					
					if (event.getKeyCode()== KeyEvent.VK_F5){
						logic.BuscarArticuloF5(tx);	
					}
					
			}
			
			if (tx.getName()==_Interface._txt_idarticulo_desde){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarArticuloF5(tx);
				}
				
			}
			
			if (tx.getName()==_Interface._txt_idarticulo_hasta){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarArticuloF5(tx);
				}
			}
			
			if (tx.getName()==_Interface._table_idrecodificacion){
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluar_table_recodificador(tx, row, col);
				}
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarArticuloF5(tx);
				}
			}
			
		}
	}
}
