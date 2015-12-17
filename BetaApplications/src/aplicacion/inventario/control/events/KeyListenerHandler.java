package aplicacion.inventario.control.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.inventario.control.interfaces._Interface;
import aplicacion.inventario.control.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesar(KeyEvent event){
		
		_Logic logic=(_Logic) this._logic;
		
		JTable table=null;
		int row=-1;
		int col=-1;
		

		if (event.getSource() instanceof JTable){
			table=(JTable)  event.getSource();
			row=table.getSelectedRow();
			col=table.getSelectedColumn();
			if (table.getName()==_Interface._table){
				if (event.getKeyCode()==KeyEvent.VK_E){
					if (event.isControlDown()){
						String idarticulo=table.getValueAt(row, 0).toString();
						logic.goMa_Articulos(idarticulo);
					}
				}
			}
		}	
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			 if (tx.getParent() instanceof JTable){
					table=(JTable) tx.getParent();
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
				}
			 if (tx.getName()==_Interface._txt_idcontrol){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarControl(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarControl(tx);
				 }
			 }
			 
			 if (tx.getName()==_Interface._table_control){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 
					 logic.evaluate_control(tx, row);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_UP){
					 logic.up_control(tx, row);
					 
				 }
				 if (event.getKeyCode()==KeyEvent.VK_DOWN){
					 logic.down_control(tx, row);
				 }
				 
			 }
			 
			 	 if (tx.getName()==_Interface._table_idarticulo){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 
					 logic._eval_item_articulo(tx, row);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_DELETE){
					 logic.borrarRenglon(tx,row);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarCatalogo(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_UP){
					 //logic.up_control(tx, row);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_DOWN){
					 //logic.down_control(tx, row);
				 }
				 
			 }
			 
			 if (tx.getName()==_Interface._table_item_etiqueta){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluate_etiqueta(tx, row);
				 }
				 
			 }
			 
			 if (tx.getName()==_Interface._txt_fecha){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarFecha(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarFecha(tx);
				 }
			 }
			 
			 if (tx.getName()==_Interface._txt_fecha_limite){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarFecha(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarFecha(tx);
				 }
			 }
			
			 if (tx.getName()==_Interface._txt_idvendedor){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarVendedor(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarVendedor(tx);
				 }
			 }
 

if (tx.getName()==_Interface._txt_idarticulo_desde){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_idarticulo_desde(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarCatalogo(tx);
				 }
			 }
			 
			 if (tx.getName()==_Interface._txt_idarticulo_hasta){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_idarticulo_hasta(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarCatalogo(tx);
				 }
			 }
			 if (tx.getName()==_Interface._txt_linea){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					// logic.evaluarFecha(tx);
					 logic.evaluar_linea(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.evaluar_linea(tx);
				 }
			 }

			 if (tx.getName()== _Interface._txt_iddeposito){
					if (event.getKeyCode()==KeyEvent.VK_F5){
						logic.BuscarDeposito(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						//cancelar
					}
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluarDeposito(tx);
					}
				}
		}
		
	}
	
}
