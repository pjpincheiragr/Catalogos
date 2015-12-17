package aplicacion.compras.gestion.events;
import aplicacion.compras.gestion.interfaces._Interface;
import aplicacion.compras.gestion.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;
import java.awt.event.*;

import javax.swing.JTextField;
import javax.swing.JTable;
public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesarEvento(KeyEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			if (tx.getName()==_Interface._txt_cliente){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
			
			if (tx.getName()==_Interface._txt_estado){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
			
			if (tx.getName()==_Interface._txt_idarticulo){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
			
			if (tx.getName()==_Interface._txt_idarticulo_descripcion){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
			if (tx.getName()==_Interface._txt_idarticulo_linea){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
				if (event.getKeyCode()==KeyEvent.VK_TAB){
					logic.focus();
				}
			}
			if (tx.getName()==_Interface._txt_idpedido){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
			
			if (tx.getName()==_Interface._txt_idpedido_descripcion){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
			
			
			if (tx.getName()==_Interface._txt_cliente_descripcion){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
			
			if (tx.getName()==_Interface._txt_idvendedor){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
			
			if (tx.getName()==_Interface._txt_idvendedor_nombre){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
		}
		
		
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_pedidos){
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					row++;
					logic.show_pedido(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					row--;
					logic.show_pedido(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.editar_pedido(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
		}
	}
}
