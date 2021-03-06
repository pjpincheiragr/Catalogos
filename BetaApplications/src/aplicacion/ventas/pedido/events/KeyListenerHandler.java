package aplicacion.ventas.pedido.events;


import aplicacion.ventas.pedido.logic.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;

import aplicacion.ventas.pedido.interfaces.*;


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
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					_logic.show(row+1);	
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					_logic.show(row-1);
				}
				
				if (event.getKeyCode()==KeyEvent.VK_DELETE){
					_logic.borrarRenglon(row);
				}
				
			}
			
			if (table.getName()==_Interface._table_pedidos_importar){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.show_pedido_nuevos(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					_logic.show_pedido_nuevos(row+1);	
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					_logic.show_pedido_nuevos(row-1);
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
				
				_logic.setCambios(true);
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
				if (table.getName()==_Interface._table_items){
					if (event.getKeyCode()==KeyEvent.VK_UP |event.getKeyCode()==KeyEvent.VK_DOWN){
						if (event.getKeyCode()==KeyEvent.VK_DOWN){
							if (row==table.getRowCount()-1){
							tx.requestFocusInWindow();
							}else{
								_logic.down(tx, row, col);
								
								
							}
							
						}else{
							if (row==0){
								tx.requestFocusInWindow();
							}else{
								
								_logic.up(tx, row, col);
							}
							
						}
					}else{
						if (tx.getName()==_Interface._table_items_total){
							
							if (event.getKeyCode()==KeyEvent.VK_ENTER){
								_logic._eval_item_total(tx, row);
							}
							if (event.getKeyCode()==KeyEvent.VK_F12){
								_logic.registrar_faltante(row);
							}
							
							
						}
						
						if (tx.getName()==_Interface._table_items_descripcion){
							if (event.getKeyCode()==KeyEvent.VK_ENTER){
								_logic._eval_item_descripcion(tx, row);
							}
							if (event.getKeyCode()==KeyEvent.VK_F12){
								
								_logic.registrar_faltante_descripcion(tx,row);
							}
							
						}
						
						if (tx.getName()==_Interface._table_items_cantidad){
							if (event.getKeyCode()==KeyEvent.VK_ENTER){
								_logic._eval_item_cantidad(tx, row);
							}
							if (event.getKeyCode()==KeyEvent.VK_F12){
								_logic.registrar_faltante(row);
							}
							
						}
						if (tx.getName()==_Interface._table_items_descuento){
							if (event.getKeyCode()==KeyEvent.VK_ENTER){
								_logic._eval_item_descuento(tx, row);
							}
							if (event.getKeyCode()==KeyEvent.VK_F12){
								_logic.registrar_faltante(row);
							}
							
						}
						if (tx.getName()==_Interface._table_items_precio){
							if (event.getKeyCode()==KeyEvent.VK_ENTER){
							
								_logic._eval_item_precio(tx, row);	
							}
							if (event.getKeyCode()==KeyEvent.VK_F12){
								_logic.registrar_faltante(row);
							}
							
						}
						if (tx.getName()==_Interface._table_items_costo){
							if (event.getKeyCode()==KeyEvent.VK_ENTER){
								_logic._eval_item_costo(tx, row);
							}
							if (event.getKeyCode()==KeyEvent.VK_F12){
								_logic.registrar_faltante(row);
							}
							
							/*if (event.getKeyCode()==KeyEvent.VK_UP){
								_logic.up(tx, row, col);
							}
							if (event.getKeyCode()==KeyEvent.VK_DOWN){
								_logic.down(tx, row, col);
							}*/
						}
						
						
						if (tx.getName()==_Interface._table_items_idarticulo){
							if (event.isControlDown()){
								if (event.getKeyCode()==KeyEvent.VK_E){
									_logic.editarArticulo(tx);
								}
								if (event.getKeyCode()==KeyEvent.VK_R){
									_logic.Relaciones(tx);
								}
							}else {
								
								
								if (event.getKeyCode()==KeyEvent.VK_ENTER){
									_logic._eval_item_articulo(tx, row);
								}
								if (event.getKeyCode()==KeyEvent.VK_DELETE){
									_logic._delete_item_articulo(tx, row);
								}
								if (event.getKeyCode()==KeyEvent.VK_F5){
									//_logic.BuscarArticulo(tx);
									_logic.BuscarCatalogo(tx);
								}
								if (event.getKeyCode()==KeyEvent.VK_F12){
									//_logic.BuscarArticulo(tx);
									_logic.registrar_faltante_idarticulo(tx,row);
								}
									
							}
						}	
					
					}	
					
				}
				
			}
			
			if (tx.getName()==_Interface._txt_fecha){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (tx.getText().length()==8){
						_logic.evaluar_fecha(tx,event);
					}else{
						_logic.evaluarFecha(tx);	
					}
					
				}else{
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.BuscarFecha(tx);
					}else{
						if (event.getKeyCode()!=KeyEvent.VK_DELETE & event.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
							_logic.evaluar_fecha(tx,event);	
						}
					}
				}
			}

			if (tx.getName()==_Interface._txt_fecha_envio){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (tx.getText().length()==8){
						_logic.evaluar_fecha(tx,event);
					}else{
						_logic.evaluar_fecha(tx);	
					}
					
				}else{
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.BuscarFecha(tx);
					}else{
						if (event.getKeyCode()!=KeyEvent.VK_DELETE & event.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
							_logic.evaluar_fecha(tx,event);	
						}
					}
				}
			}

			
			
			if (tx.getName()== _Interface._txt_idvendedor){
				_logic.setCambios(true);
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
			
			if (tx.getName()== _Interface._txt_iddeposito){
				_logic.setCambios(true);
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarDeposito(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarDeposito(tx);
				}
			}
			if (tx.getName()== _Interface._txt_negocio){
				_logic.setCambios(true);
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarNegocio(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarNegocio(tx);
				}
			}
			if (tx.getName()== _Interface._txt_guia){
				_logic.setCambios(true);
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
				_logic.setCambios(true);
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
				_logic.setCambios(true);
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
				_logic.setCambios(true);
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
				_logic.setCambios(true);
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
				_logic.setCambios(true);
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
				_logic.setCambios(true);
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarCliente(tx);
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

			if (tx.getName()== _Interface._txt_numero){
				
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
				_logic.setCambios(true);
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_descripcion(tx);
				}
			}
			
				if (tx.getName()== _Interface._txt_cliente_descripcion){
					_logic.setCambios(true);	
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						_logic.evaluate_cliente_descripcion(tx);
					}
				}
			
			
				if (tx.getName()==_Interface._importar_idpedido){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.goCargar();
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						_logic.doclean();
					}
					if (event.getKeyCode()==KeyEvent.VK_UP){
						_logic.Up(tx, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						_logic.Down(tx, col);
					}
				}
				
				if (tx.getName()==_Interface._importar_pedido_descripcion){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.goCargar();
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						_logic.doclean();
					}
					if (event.getKeyCode()==KeyEvent.VK_UP){
						_logic.Up(tx, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						_logic.Down(tx, col);
					}
				}
				
				if (tx.getName()==_Interface._importar_idcliente){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.goCargar();
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						_logic.doclean();
					}
					if (event.getKeyCode()==KeyEvent.VK_UP){
						_logic.Up(tx, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						_logic.Down(tx, col);
					}
				}
				if (tx.getName()==_Interface._importar_cliente_descripcion){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.goCargar();
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						_logic.doclean();
					}
					if (event.getKeyCode()==KeyEvent.VK_UP){
						_logic.Up(tx, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						_logic.Down(tx, col);
					}
				}
				
				if (tx.getName()==_Interface._importar_informacion){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.goCargar();
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						_logic.doclean();
					}
					if (event.getKeyCode()==KeyEvent.VK_UP){
						_logic.Up(tx, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						_logic.Down(tx, col);
					}
				}
				
				if (tx.getName()==_Interface._importar_vendedor){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.goCargar();
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						_logic.doclean();
					}
					if (event.getKeyCode()==KeyEvent.VK_UP){
						_logic.Up(tx, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						_logic.Down(tx, col);
					}
				}
				if (tx.getName()==_Interface._importar_creador){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.goCargar();
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						_logic.doclean();
					}
					if (event.getKeyCode()==KeyEvent.VK_UP){
						_logic.Up(tx, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						_logic.Down(tx, col);
					}
				}
				if (tx.getName()==_Interface._importar_idarticulo){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.goCargar();
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						_logic.doclean();
					}
					if (event.getKeyCode()==KeyEvent.VK_UP){
						_logic.Up(tx, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						_logic.Down(tx, col);
					}
				}
				if (tx.getName()==_Interface._importar_articulo_descripcion){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.goCargar();
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						_logic.doclean();
					}
					if (event.getKeyCode()==KeyEvent.VK_UP){
						_logic.Up(tx, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						_logic.Down(tx, col);
					}
				}
				if (tx.getName()==_Interface._importar_estado){
					
					if (event.getKeyCode()==KeyEvent.VK_F5){
						_logic.goCargar();
					}
					if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
						_logic.doclean();
					}
					if (event.getKeyCode()==KeyEvent.VK_UP){
						_logic.Up(tx, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_DOWN){
						_logic.Down(tx, col);
					}
				}
	
		}
		
					
	}
}
