package aplicacion.inventario.planilla.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import aplicacion.inventario.planilla.interfaces._Interface;
import aplicacion.inventario.planilla.logic._Logic;
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
						logic.editar_articulo(table,row);
						
					}
					
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					logic._move_up(row);
					
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					logic._move_down(row);
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
			 
			 if (tx.getName()==_Interface._txt_descripcion){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.goCargar();
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
			 if (tx.getName()==_Interface._table_descripcion){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_table_descripcion(tx, row, col);
				 }
				
			 }
			 if (tx.getName()==_Interface._table_linea){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_table_linea(tx, row, col);
				 }
	 			 if (event.getKeyCode()==KeyEvent.VK_F5){
	 				 logic.BuscarLineaTabla(tx, row, col);
	 			 }
				
			 }
			 if (tx.getName()==_Interface._table_proveedor){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_table_proveedor(tx, row, col);
				 }
	 			 if (event.getKeyCode()==KeyEvent.VK_F5){
	 				 logic.BuscarProveedor(tx);
	 			 }
			 }
			 
			 if (tx.getName()==_Interface._table_proveedor_actualizacion){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_table_proveedor_actualizacion(tx, row, col);
				 }
	 			 if (event.getKeyCode()==KeyEvent.VK_F5){
	 				 logic.BuscarProveedor(tx);
	 			 }
			 }
			 if (tx.getName()==_Interface._table_politica){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_table_politica(tx, row, col);
				 }
	 			 if (event.getKeyCode()==KeyEvent.VK_F5){
	 				 logic.BuscarPolitica(tx);
	 			 }
				
			 }
			 
			 if (tx.getName()==_Interface._table_politica_actualizacion){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_table_politica_actualizacion(tx, row, col);
				 }
	 			 if (event.getKeyCode()==KeyEvent.VK_F5){
	 				 logic.BuscarPolitica(tx);
	 			 }
				
			 }
			 
			 if (tx.getName()==_Interface._table_lista){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_table_lista(tx, row, col);
				 }
	 		 }
			 if (tx.getName()==_Interface._table_costo){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_table_costo(tx, row, col);
				 }
	 		 }
			 if (tx.getName()==_Interface._table_publico){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_table_publico(tx, row, col);
				 }
	 		 }
			 if (tx.getName()==_Interface._table_clasificacion){
	 			 if (event.getKeyCode()==KeyEvent.VK_ENTER){
	 				 logic.evaluar_table_clasificacion(tx, row, col);
				 }
	 		 }
		}
		
	}
	
}
