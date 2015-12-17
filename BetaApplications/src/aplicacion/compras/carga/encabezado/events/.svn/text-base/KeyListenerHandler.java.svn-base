package aplicacion.compras.carga.encabezado.events;

import aplicacion.compras.carga.encabezado.interfaces._Interface;
import aplicacion.compras.carga.encabezado.logic._Logic;

import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler{
	
	public void procesarEvento(KeyEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JComboBox){
			JComboBox combo=(JComboBox) event.getSource();
			if (combo.getName()==_Interface._list_letra){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic._evaluar_letra(combo);
				}
			}
			if (combo.getName()==_Interface._list_tc){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic._evaluar_tc(combo);
				}
			}
		}
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
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
			
			if (tx.getName()==_Interface._txt_fecha_factura){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic._evaluar_fecha_factura(tx);	
				}
				if (event.getKeyCode()==KeyEvent.VK_BACK_SPACE){
					logic.delete_fecha_factura(tx);
				}
			}
			
			if (tx.getName()==_Interface._txt_idproveedor){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.evaluarProveedor(tx);	
				}
				if (event.getKeyCode()==KeyEvent.VK_F5){
					logic.BuscarProveedor(tx);	
				}
			}

			if (tx.getName()==_Interface._table_importe){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					JTable table=(JTable) tx.getParent();
					int row=table.getSelectedRow();
					logic._evaluate_importe_columns(tx, row);
				}
			}
			
		
			
			
			
			if (tx.getName()==_Interface._txt_fecha_contable){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic._evaluar_fecha_contable(tx);	
				}
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
				}
				if (event.getKeyCode()==KeyEvent.VK_BACK_SPACE){
					logic.delete_fecha_contable(tx);
				}
			}
			if (tx.getName()==_Interface._txt_cai){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic._evaluate_cai(tx);	
				}
				
			}
			if (tx.getName()==_Interface._txt_cai_vencimiento){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic._evaluar_fecha_cai(tx);	
				}
				if (event.getKeyCode()==KeyEvent.VK_BACK_SPACE){
					logic.delete_fecha_cai(tx);
				}
				
			}
			if (tx.getName()==_Interface._txt_usuario){
					
					logic._evaluar_confirmacion(tx,event);	
				
				
			}
		}
	}
}
