package aplicacion.actualizacion.codigo.events;


import aplicacion.actualizacion.codigo.interfaces.*;
import aplicacion.actualizacion.codigo.logic.*;
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
		JTable table=null;
		int row=-1;
		int col=-1;
		if (event.getSource() instanceof JTable){
			table=(JTable)  event.getSource();
			row=table.getSelectedRow();
			col=table.getSelectedColumn();
			
		}	
		
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			
			if (tx.getName()==_Interface._txt_idproveedor){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarProveedor(tx);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluarProveedor(tx);
				}
			}
			if (tx.getName()==_Interface._txt_codigo_desde){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarCodigo(tx);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluate_codigo_desde(tx);
				}
			}
			if (tx.getName()==_Interface._table_politica){
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluar_table_politica(tx, row, col);	
				}
				
			}
			if (tx.getName()==_Interface._txt_codigo_hasta){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarCodigo(tx);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluate_codigo_hasta(tx);
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
			
			
			
		}
	}
}
