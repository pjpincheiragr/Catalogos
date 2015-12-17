package aplicacion.compras.carga.items.events;

import java.awt.event.KeyEvent;
import aplicacion.compras.carga.items.logic._Logic;
import aplicacion.compras.carga.items.interfaces.*;

import aplicacion.modelo.events._KeyListenerHandler;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.KeyStroke;

public class KeyListenerHandler extends _KeyListenerHandler{
	public void procesarEvento(KeyEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JComboBox){
			JComboBox cb=(JComboBox) event.getSource();
			System.out.println("Combo Event>"+cb.getName());
			if (cb.getName()==_Interface._list_letra){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic._evaluar_letra(cb);	
				}
			}
			if (cb.getName()==_Interface._list_tc){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.change_tc(cb);
				}
			}
			if (cb.getName()==_Interface._list_imputacion){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.update_imputacion(cb);	
				}
			}
			
		}
		if (event.getSource() instanceof JTable){
			JTable table=(JTable)  event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
			if (table.getName()==_Interface._table_items){
				
					if (event.getKeyCode()==KeyEvent.VK_UP){
						logic._move_up(row);
						
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						logic._move_down(row);
					}
					if (event.getKeyCode()==KeyEvent.VK_DELETE){
						logic.borrarRenglon(row);
					}
					
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						table.changeSelection(row, col,false,false);
						table.editCellAt(row, col);
						table.transferFocus();
					}	
				
								
			}
			
		}
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			tx.requestFocusInWindow();
			if (event.isControlDown()){

				if (event.getKeyCode()==KeyEvent.VK_ADD){
					logic._mover_zoomin();
				}
				if (event.getKeyCode()==KeyEvent.VK_MINUS){
					logic._mover_zoomout();
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					logic._mover_arriba();
					
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					logic._mover_abajo();
				}
				if (event.getKeyCode()==KeyEvent.VK_LEFT){
					logic._mover_izquierda();
				}
				if (event.getKeyCode()==KeyEvent.VK_RIGHT){
					logic._mover_derecha();
				}
			}

			
				if (tx.getName()==_Interface._txt_idproveedor){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._evaluar_proveedor(tx,event.isShiftDown());
					}	
					if (event.getKeyCode()==KeyEvent.VK_F5){
						logic.BuscarProveedor(tx);
					}
					
				}
				if (tx.getName()==_Interface._txt_sucursal){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._evaluar_sucursal(tx);
					}	
					
					
				}
				if (tx.getName()==_Interface._txt_numero){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._evaluar_numero(tx);
					}	
				}
				
				if (tx.getName()==_Interface._txt_fecha){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						if (tx.getText().length()==8){
							logic.evaluar_fecha(tx,event);
						}else{
							logic._evaluar_fecha(tx);	
						}
						
					}else{
						if (event.getKeyCode()==KeyEvent.VK_F5){
							logic.BuscarFecha(tx);
						}else{
							if (event.getKeyCode()!=KeyEvent.VK_DELETE & event.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
								logic.evaluar_fecha(tx,event);	
							}
						}
					}
				}
				if (tx.getName()==_Interface._txt_descuento_porcentaje){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._evaluate_descuento_subtotal(tx);
					}	
				}
				if (tx.getName()==_Interface._txt_descuento_detalle){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						
					}
				}
				if (tx.getName()==_Interface._txt_descuento){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._evaluate_descuento(tx);
					}	
				}
				if (tx.getName()==_Interface._txt_idproveedor_articulos){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						//event.isShiftDown()
						logic._evaluar_proveedor_articulos(tx);
					}	
					if (event.getKeyCode()==KeyEvent.VK_F5){
						logic.BuscarProveedor(tx);
					}
					
				}
					

				if (tx.getParent() instanceof JTable){
					
					JTable table=(JTable) tx.getParent();
					int row=table.getSelectedRow();
					if (tx.getName()==_Interface._table_importe){
						if (event.getKeyCode()==KeyEvent.VK_ENTER){
							
							logic._evaluate_importe_columns(tx, row);
						}
					}
					if (table.getName()==_Interface._table_etiquetas){
						if (tx.getName()==_Interface._table_etiquetas_cantidad){
							
							
							if (event.getKeyCode()==KeyEvent.VK_ENTER){
								logic._eval_etiquetas_cantidad(tx, row);
							}
						}
					}
					
					if (table.getName()==_Interface._table_items){
						
						if (event.isAltDown()){
							if (event.getKeyCode()==KeyEvent.VK_ADD){
								logic._mover_zoomin();
							}
							if (event.getKeyCode()==KeyEvent.VK_MINUS){
								logic._mover_zoomout();
							}
							if (event.getKeyCode()==KeyEvent.VK_UP){
								
								logic._mover_abajo();
							}
							if (event.getKeyCode()==KeyEvent.VK_DOWN){
								logic._mover_arriba();
							}
							if (event.getKeyCode()==KeyEvent.VK_LEFT){
								logic._mover_izquierda();
							}
							if (event.getKeyCode()==KeyEvent.VK_RIGHT){
								logic._mover_derecha();
							}
						}else {
							if (tx.getName()==_Interface._table_items_total){
								
								if (event.getKeyCode()==KeyEvent.VK_ENTER){
									logic._eval_item_total(tx, row);
								}
							}
							
							if (tx.getName()==_Interface._table_items_descripcion){
								
								if (event.getKeyCode()==KeyEvent.VK_ENTER){
									logic._eval_item_descripcion(tx, row);
								}
							}
							
							if (tx.getName()==_Interface._table_items_cantidad){
								
								if (event.getKeyCode()==KeyEvent.VK_ENTER){
									logic._eval_item_cantidad(tx, row);
								}
							}
							if (tx.getName()==_Interface._table_items_descuento){
								
								if (event.getKeyCode()==KeyEvent.VK_ENTER){
									logic._eval_item_descuento(tx, row);
								}
							}
							if (tx.getName()==_Interface._table_items_precio){
								
								if (event.getKeyCode()==KeyEvent.VK_ENTER){
									logic._eval_item_precio(tx, row);
								}
							}
							
							
							if (tx.getName()==_Interface._table_items_idarticulo){
								
								if (event.isControlDown()){
									if (event.getKeyCode()==KeyEvent.VK_E){
										logic.editarArticulo(tx);
									}
									if (event.getKeyCode()==KeyEvent.VK_S){
										logic.stockArticulo(tx);
									}
								}else {
									if (event.getKeyCode()==KeyEvent.VK_DOWN){
										if (row==table.getRowCount()-1){
											
											logic._mover_abajo();
											tx.requestFocusInWindow();
									}
									}
									
									if (event.getKeyCode()==KeyEvent.VK_ENTER){
										logic._eval_item_articulo(tx, row);
									}
									if (event.getKeyCode()==KeyEvent.VK_F5){
										logic.BuscarCatalogo(tx);
									}
									if (event.getKeyCode()==KeyEvent.VK_DELETE){
										String txt=tx.getText();
										if (txt.compareTo("")==0){
											logic.borrarRenglon(row);	
										}
										
									}
								}
								
								
								
						}	
						}
						
						
					}
						
				}
				
				
				if (tx.getName()==_Interface._txt_idcomprobante){
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic._evaluar_idcomprobante(tx,event.isShiftDown());
					}
					
				}	
			}
						
		}
		
	

}
