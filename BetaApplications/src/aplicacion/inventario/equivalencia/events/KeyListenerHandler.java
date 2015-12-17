package aplicacion.inventario.equivalencia.events;


import aplicacion.inventario.equivalencia.interfaces.*;
import aplicacion.inventario.equivalencia.logic.*;
import java.awt.event.KeyEvent;
import aplicacion.modelo.events._KeyListenerHandler;
import javax.swing.*;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic logic=(_Logic) this._logic;
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
			
			if (tx.getName()==_Interface._table_codigo){
					if (event.getKeyCode()== KeyEvent.VK_ENTER){
						logic.evalcode(tx, row, col);	
					}
					if (event.getKeyCode()== KeyEvent.VK_F5){
						logic.BuscarAlias(tx, row);	
					}
					
			}
			if (tx.getName()==_Interface._table_lineaproveedor){
				if (event.getKeyCode()== KeyEvent.VK_F5){
				logic.BuscarLinea(tx, row);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
				logic.evaluar_table_linea(tx, row);
				}
			}
			if (tx.getName()==_Interface._table_proveedor){
				if (event.getKeyCode()== KeyEvent.VK_F5){
				logic.BuscarProveedor(tx);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluar_table_proveedor(tx, row);
				}
			}
			
			if (tx.getName()==_Interface._txt_idproveedor){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarProveedor(tx);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluarProveedor(tx);
				}
			}
			if (tx.getName()==_Interface._txt_idarticulo_desde){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarArticulo(tx);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluar_articulo_desde(tx);
				}
			}
			
			if (tx.getName()==_Interface._txt_idarticulo_hasta){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarArticulo(tx);
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.evaluar_articulo_hasta(tx);
				}
			}
			
			if (tx.getName()==_Interface._txt_linea){
				if (event.getKeyCode()== KeyEvent.VK_F5){
					logic.BuscarLinea();
				}
				if (event.getKeyCode()== KeyEvent.VK_ENTER){
					logic.eval_linea(tx);
				}
			}
			
		}
	}
}
