package aplicacion.compras.carga.pedido.events;
import aplicacion.compras.carga.pedido.interfaces._Interface;
import aplicacion.compras.carga.pedido.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;
import java.awt.event.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;
public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesarEvento(KeyEvent event){
		_Logic logic=(_Logic) this._logic;
		
		if (event.getSource() instanceof JTextArea){
			
			JTextArea txa=(JTextArea) event.getSource();
			if (txa.getName()== _Interface._txt_informacion){
				
				if (event.getKeyCode()==KeyEvent.VK_TAB){
					logic.evaluate_informacion(txa);
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (event.isControlDown()|event.isShiftDown()){
						logic.evaluate_informacion(txa);
					}
				}
			
		}
		}
		
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			JTable table=null;
			int row=-1;
			int col=-1;
			if (tx.getParent() instanceof JTable){
				table=(JTable) tx.getParent();
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
			}else{
				
			}
			
			if (tx.getName()==_Interface._txt_idproveedor){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluarProveedor(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.BuscarProveedor(tx);
				}
			}
			if (tx.getName()==_Interface._txt_idpedido){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic._evaluate_idpedido(tx);	
				}
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.BuscarPedidoProveedor(tx);	
				}
			}
			
			
			if (tx.getName()==_Interface._txt_fecha){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (tx.getText().length()==8){
						logic.evaluar_fecha(tx,event);
					}else{
						logic.evaluarFecha(tx);	
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
			
			
			if (tx.getName()==_Interface._txt_desde){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (tx.getText().length()==8){
						logic.evaluar_fecha(tx,event);
					}else{
						logic.evaluar_fecha(tx);	
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

			if (tx.getName()==_Interface._txt_hasta){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (tx.getText().length()==8){
						logic.evaluar_fecha(tx,event);
					}else{
						logic.evaluar_fecha(tx);	
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
			
			if (tx.getName()==_Interface._txt_descripcion_pedido){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.guardar_pedido();	
				}
			}
			if (tx.getName()==_Interface._table_items_minimo){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
				logic.process_proyectado(tx);
				}
			}
			if (tx.getName()==_Interface._table_items_critico){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
				logic.process_critico(tx);
				}
			}	
			if (tx.getName()==_Interface._table_idarticulo){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
				logic.process_codigo(tx);
				}
			}
			if (tx.getName()==_Interface._table_items_pedir){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
						logic.process_pedir(tx);
				}
			}
			if (tx.getName()== _Interface._txt_fecha_envio){
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
			
			if (tx.getName()== _Interface._txt_idvendedor){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.BuscarVendedor(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluarVendedor(tx);
				}
			}
			if (tx.getName()== _Interface._txt_guia){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluarGuia(tx);
				}
			}
			if (tx.getName()== _Interface._txt_idprovincia){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.BuscarProvincia(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluarProvincia(tx);
				}
			}
			if (tx.getName()== _Interface._txt_idciudad){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					//_logic.BuscarProvincia(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluate_ciudad(tx);
				}
			}
			if (tx.getName()== _Interface._txt_cpostal){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					//logic.BuscarProvincia(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluate_cpostal(tx);
				}
			}
			
			if (tx.getName()== _Interface._txt_domicilio){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					//logic.BuscarProvincia(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluate_domicilio(tx);
				}
			}
			if (tx.getName()== _Interface._txt_idtransporte){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.buscarTransporte(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluarTransporte(tx);
				}
			}
			if (tx.getName()== _Interface._txt_telefono){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluate_telefono(tx);
				}
			}
			
			if (tx.getName()== _Interface._txt_idproveedor){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.BuscarProveedor(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluarProveedor(tx);
				}
			}

			
		}
//		if (event.getSource() instanceof JTable){
//			JTable table=(JTable) event.getSource();
//			int row=table.getSelectedRow();
//			int col=table.getSelectedColumn();
//			if (table.getName()==_Interface._table_items){
//				if (event.getKeyCode()==KeyEvent.VK_DOWN){
//						logic.mostrar_item_down(row+1, table);	
//				}
//				if (event.getKeyCode()==KeyEvent.VK_UP){
//					logic.mostrar_item_up(row-1, table);	
//				}
//				
//					if (event.isControlDown()){
//						if (event.getKeyCode()==KeyEvent.VK_E){
//							logic.editarArticulo(row);
//					}
//			}
//			}
//		
//		}
	}
}
