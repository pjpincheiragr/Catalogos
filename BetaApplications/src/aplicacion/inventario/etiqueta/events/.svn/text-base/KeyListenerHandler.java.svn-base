package aplicacion.inventario.etiqueta.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.inventario.etiqueta.interfaces._Interface;
import aplicacion.inventario.etiqueta.logic._Logic;
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
			 
			 
			 if (tx.getName()==_Interface._txt_idproveedor){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_proveedor(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarProveedor(tx);
				 }
			 }

			 if (tx.getName()==_Interface._txt_linea){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_linea(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.evaluar_linea(tx);
				}
			 }
			 if (tx.getName()==_Interface._table_control_idarticulo){
					if (event.isControlDown()){
						if (event.getKeyCode()==KeyEvent.VK_E){
							logic.editarArticulo(tx);
						}						
					}else {
						if (event.getKeyCode()==KeyEvent.VK_DOWN){
							if (row==table.getRowCount()-1){
								
								
								tx.requestFocusInWindow();
						}
						}
						
						if (event.getKeyCode()==KeyEvent.VK_ENTER){
							logic._eval_item_articulo(tx, row);
						}
						if (event.getKeyCode()==KeyEvent.VK_DELETE){
							logic._delete_item_articulo(tx, row);
						}
						if (event.getKeyCode()==KeyEvent.VK_F5){
							//_logic.BuscarArticulo(tx);
							logic.BuscarCatalogo(tx);
						}
						if (event.getKeyCode()==KeyEvent.VK_F6){
							logic.BuscarCatalogo(tx);
						}	
					}
					
					
					
			}
			 
			 if (tx.getName()==_Interface._table_control_cantidad){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._eval_item_cantidad(tx, row);
					}
				}
			 
			 if (tx.getName()==_Interface._table_control_descripcion){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._eval_item_descripcion(tx, row);
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
			 
			 if (tx.getName()== _Interface._txt_fecha_desde){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						
						logic.BuscarFecha(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						//cancelar
					}
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluarFecha(tx);
					}
				}
			 if (tx.getName()== _Interface._txt_fecha_hasta){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						
						logic.BuscarFecha(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						//cancelar
					}
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.evaluarFecha(tx);
					}
				}
			 if (tx.getName()== _Interface._txt_iddeposito_destino){
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
