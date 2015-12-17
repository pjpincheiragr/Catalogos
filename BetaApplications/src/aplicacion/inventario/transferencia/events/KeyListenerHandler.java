package aplicacion.inventario.transferencia.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.inventario.transferencia.interfaces._Interface;
import aplicacion.inventario.transferencia.logic._Logic;
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
			if (table.getName()==_Interface._table_importar){
				if (event.getKeyCode()==KeyEvent.VK_E){
					if (event.isControlDown()){
						logic.editar_articulo(table,row);
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
			
			 if (tx.getName()==_Interface._txt_idtransferencia){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_transferencia(tx);
				 }
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					 logic.BuscarTransferenciaStock(tx);
				 }
			 }
			 
			 if (tx.getName()==_Interface._table_control){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluate_control(tx, row);
				 }
					if (event.getKeyCode()==KeyEvent.VK_E){
						if (event.isControlDown()){
							logic.editar_articulo(table,row);
							
						}
					}
			 }

			 if (tx.getName()==_Interface._table_importar){
				 System.err.println("entra al key listener!");
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					// logic.evaluate_control(tx, row);
				 }
				 
					if (event.getKeyCode()==KeyEvent.VK_E){
						
						if (event.isControlDown()){
							
							logic.editar_articulo(table,row);
							
						}
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
			 if (tx.getName()==_Interface._table_transferencia_idarticulo){
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
			 if (tx.getName()==_Interface._table_transferencia_cantidad){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._eval_item_cantidad(tx, row);
					}
					if (event.getKeyCode()==KeyEvent.VK_E){
						if (event.isControlDown()){
							logic.editar_articulo(table,row);
							
						}
						
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
			 
			 if (tx.getName()== _Interface._txt_fecha){
					
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
