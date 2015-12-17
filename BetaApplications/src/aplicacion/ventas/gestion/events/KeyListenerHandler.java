package aplicacion.ventas.gestion.events;
import aplicacion.ventas.gestion.interfaces._Interface;
import aplicacion.ventas.gestion.logic._Logic;
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
			
if (tx.getName()==_Interface._txt_idpedido_informacion){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}

if (tx.getName()==_Interface._txt_idpedido_remito){
	
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
			if (tx.getName()==_Interface._txt_idcreador){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.show_with_filter(false);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
			
			if (tx.getName()==_Interface._txt_idcreador_nombre){
				
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
			if (table.getName()==_Interface._table_pedidos_nuevos){
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					row++;
					logic.show_pedido_nuevos(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					row--;
					logic.show_pedido_nuevos(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.editar_pedido_nuevo(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					logic.doclean();
				}
			}
		}
	}
}
