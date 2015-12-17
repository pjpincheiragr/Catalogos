package aplicacion.inventario.articulo.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.inventario.articulo.interfaces._Interface;
import aplicacion.inventario.articulo.logic._Logic;
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
				if (event.getKeyCode()==KeyEvent.VK_DELETE){
					
				}
			}
		}	
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			 if (tx.getParent() instanceof JTable){
					table=(JTable) tx.getParent();
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
					if (tx.getName()==_Interface._table_equivalencias_idarticulo){
						if (event.getKeyCode()==KeyEvent.VK_ENTER){
							logic._eval_item_articulo(tx, row);	
						}
						if (event.getKeyCode()==KeyEvent.VK_DELETE){
							logic.borrarRenglonEquivalencia(row);	
						}
						if (event.getKeyCode()==KeyEvent.VK_F5){
							 logic.BuscarArticulo(tx);
						 }		
					}
				}
			 if (tx.getName()==_Interface._txt_idarticulo){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_idarticulo(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarArticulo(tx);
				 }
			 }
			 
			 if (tx.getName()==_Interface._txt_idproveedor){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_proveedor(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarProveedor(tx);
				 }
			 }
			 
			 if (tx.getName()==_Interface._txt_cuenta_actualizacion){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_proveedor_actualizacion(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarProveedor(tx);
				 }
			 }
			 
			 if (tx.getName()==_Interface._txt_descripcion){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_descripcion(tx);
				 }
				 
			 }

			 if (tx.getName()==_Interface._txt_linea){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_linea(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarLinea(tx);
				}
			 }
			 if (tx.getName()==_Interface._txt_minimo){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_minimo(tx);
				 }
	 			}		 
			 if (tx.getName()==_Interface._txt_idpolitica){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_politica(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarPolitica(tx);
				}
			 }
			 
			 if (tx.getName()==_Interface._txt_precio_lista){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_precio_lista(tx);
				 }
			 }
			 if (tx.getName()==_Interface._txt_precio_publico){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_precio_publico(tx);
				 }
			 }
			 if (tx.getName()==_Interface._txt_precio_costo){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_precio_costo(tx);
				 }
			 }
		}
		
	}
	
}
