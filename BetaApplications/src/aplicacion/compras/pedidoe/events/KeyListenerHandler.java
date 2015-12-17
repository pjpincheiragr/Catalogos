package aplicacion.compras.pedidoe.events;

import aplicacion.compras.pedidoe.logic.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;

import aplicacion.compras.pedidoe.interfaces.*;

import aplicacion.modelo.events._KeyListenerHandler;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
			if (table.getName()==_Interface._table_items){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.editCell(row,col);
				}
				if (event.isControlDown()){
					if (event.getKeyCode()==KeyEvent.VK_E){
						_logic.editarArticulo(table,row,1);
					}
				}
				if (event.getKeyCode()==KeyEvent.VK_DELETE){
					_logic.borrarRenglon(row);
				}
				
			}
			if (table.getName()==_Interface._table_critico){
				if (event.isControlDown()){
					if (event.getKeyCode()==KeyEvent.VK_E){
						_logic.editarArticulo(table,row,1);
					}
				}	
			}
			if (table.getName()==_Interface._table_faltantes){
				if (event.isControlDown()){
					if (event.getKeyCode()==KeyEvent.VK_E){
						_logic.editarArticulo(table,row,1);
					}
				}	
			}
			
			
		}
		if (event.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) event.getSource();
			/*
			if (lst.getName() == _Interface._list_comprobante){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_comprobante(lst);	
				}
			}*/
		}
		if (event.getSource() instanceof JTextArea){
			
			JTextArea txa=(JTextArea) event.getSource();
			if (txa.getName()== _Interface._txt_informacion){
				
				if (event.getKeyCode()==KeyEvent.VK_TAB){
					_logic.evaluate_informacion(txa);
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (event.isControlDown()|event.isShiftDown()){
						_logic.evaluate_informacion(txa);
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
			}
			
			if (tx.getName()== _Interface._txt_fecha){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
					_logic.BuscarFecha(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarFecha(tx);
				}
			}
			
			if (tx.getName()== _Interface._txt_linea_critico){
				
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluar_linea_critico(tx);
				}
			}
			if (tx.getName()== _Interface._txt_linea_faltantes){
				
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluar_linea_faltantes(tx);
				}
			}
			if (tx.getName()== _Interface._txt_fecha_envio){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarFecha(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarFecha(tx);
				}
			}
			
			if (tx.getName()== _Interface._txt_idvendedor){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarVendedor(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarVendedor(tx);
				}
			}
			if (tx.getName()== _Interface._txt_guia){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarGuia(tx);
				}
			}
			if (tx.getName()== _Interface._txt_idprovincia){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarProvincia(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarProvincia(tx);
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
					_logic.evaluate_ciudad(tx);
				}
			}
			if (tx.getName()== _Interface._txt_cpostal){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					//_logic.BuscarProvincia(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_cpostal(tx);
				}
			}
			
			if (tx.getName()== _Interface._txt_domicilio){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					//_logic.BuscarProvincia(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_domicilio(tx);
				}
			}
			if (tx.getName()== _Interface._txt_idtransporte){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.buscarTransporte(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarTransporte(tx);
				}
			}
			if (tx.getName()== _Interface._txt_telefono){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_telefono(tx);
				}
			}
			
			if (tx.getName()== _Interface._txt_idcliente){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarProveedor(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarCliente(tx);
				}
			}

			if (tx.getName()== _Interface._txt_idpedido){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarPDC(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluar_numero(tx);
				}
			}
		
			
			if (tx.getName()== _Interface._txt_pedido_descripcion){
				
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_descripcion(tx);
				}
			}
			
			if (tx.getName()== _Interface._txt_cliente_descripcion){
				
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_cliente_descripcion(tx);
				}
			}
			
			if (tx.getName()==_Interface._table_items_total){
				
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_item_total(tx, row);
				}
				
			}
			
			if (tx.getName()==_Interface._table_items_descripcion){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_item_descripcion(tx, row);
				}
			}
			
			if (tx.getName()==_Interface._table_items_cantidad){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_item_cantidad(tx, row);
				}
			}
			if (tx.getName()==_Interface._table_items_descuento){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_item_descuento(tx, row);
				}
			}
			if (tx.getName()==_Interface._table_items_precio){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_item_precio(tx, row);
				}
			}
			if (tx.getName()==_Interface._table_items_costo){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_item_costo(tx, row);
				}
			}
			
			if (tx.getName()==_Interface._table_items_pedido){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_item_pedido(tx, row);
				}
			}
			if (tx.getName()==_Interface._table_critico_pedir){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_critico_cantidad(tx, row);
				}
				
			}
			if (tx.getName()==_Interface._table_faltante_pedir){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_faltante_cantidad(tx, row);
				}
				if (event.isControlDown()){
					if (event.getKeyCode()==KeyEvent.VK_E){
						_logic.editarArticulo(table,row,1);
					}
				}
			}

			
			
			
			
			
			if (tx.getName()==_Interface._table_remitos_idpedido){
				if (event.isControlDown()){
					if (event.getKeyCode()==KeyEvent.VK_E){
						//_logic.editarArticulo(tx);
					}
					
				}else {
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						if (row==table.getRowCount()-1){
							
							
							tx.requestFocusInWindow();
					}
					}
					
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						_logic._eval_remitos_idpedido(tx, row);
					}
					if (event.getKeyCode()==KeyEvent.VK_DELETE){
						_logic._delete_remito_idpedido(tx, row);
					}
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.BuscarPedidoCliente(tx);
					}
					
				}
				
				
				
		}
			if (tx.getName()==_Interface._table_items_idarticulo){
				if (event.isControlDown()){
					if (event.getKeyCode()==KeyEvent.VK_E){
						_logic.editarArticulo(tx);
					}
					
				}else {
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						if (row==table.getRowCount()-1){
							
							
							tx.requestFocusInWindow();
					}
					}
					
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						_logic._eval_item_articulo(tx, row);
					}
					if (event.getKeyCode()==KeyEvent.VK_DELETE){
						_logic._delete_item_articulo(tx, row);
					}
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.BuscarArticulo(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_F6){
						_logic.BuscarCatalogo(tx);
					}	
				}
				
				
				
		}	
		
	
		}
		
					
	}
}
