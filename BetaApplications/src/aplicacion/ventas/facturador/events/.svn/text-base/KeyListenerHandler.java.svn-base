package aplicacion.ventas.facturador.events;

import aplicacion.ventas.facturador.logic.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.ventas.facturador.interfaces.*;
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
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					if (row<table.getRowCount()-1)row++;
					_logic.editCell(row,col);
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					if (row>0)row--;
					_logic.editCell(row,col);
				}
			}
			
		}
		if (event.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) event.getSource();
			
			if (lst.getName() == _Interface._list_comprobante){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_comprobante(lst);	
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
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
			
if (tx.getName()==_Interface._table_items_total){
				
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_item_total(tx, row);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
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
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
				}
			}
			if (tx.getName()==_Interface._table_items_precio){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_item_precio(tx, row);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
				}
			}
			if (tx.getName()==_Interface._table_items_costo){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._eval_item_costo(tx, row);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
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
						//_logic.BuscarArticulo(tx);
						_logic.BuscarCatalogo(tx);
					}
					if (event.getKeyCode()==KeyEvent.VK_F2){
						//_logic.BuscarArticulo(tx);
						_logic.guardar();
					}
					if (event.getKeyCode()==KeyEvent.VK_F6){
						_logic.BuscarCatalogo(tx);
					}	
				}
				
				
				
		}	
		
	
		

			if (tx.getName()== _Interface._txt_fecha){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
					_logic.BuscarFecha(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
				}
			}
			if (tx.getName()== _Interface._txt_remito){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarRemito(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarRemito(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
				}
			}
			
			if (tx.getName()== _Interface._txt_idcondicion){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarCondicion(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarCondicion(tx);
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
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
				}
			}
			if (tx.getName()== _Interface._txt_numero){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					//_logic.BuscarRemito(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluar_numero(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
				}
			}
			
			if (tx.getName()== _Interface._txt_idcliente){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarCliente(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluarCliente(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					//_logic.BuscarArticulo(tx);
					_logic.guardar();
				}
			}

			
		
			
			
			
		}
		
					
	}
}
