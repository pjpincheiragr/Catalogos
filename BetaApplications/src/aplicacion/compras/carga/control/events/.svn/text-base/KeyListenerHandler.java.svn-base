package aplicacion.compras.carga.control.events;
import aplicacion.compras.carga.control.interfaces._Interface;
import aplicacion.compras.carga.control.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;
import java.awt.event.*;

import javax.swing.JTextField;
import javax.swing.JTable;
public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesarEvento(KeyEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			
			if (tx.getName()==_Interface._txt_fecha_desde){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (tx.getText().length()==8){
						logic.evaluar_fecha(tx,event);
					}else{
						logic.evaluate_fecha_desde(tx);	
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
			if (tx.getName()==_Interface._txt_fecha_carga_desde){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (tx.getText().length()==8){
						logic.evaluar_fecha(tx,event);
					}else{
						logic.evaluate_fecha_carga_desde(tx);	
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
			
			if (tx.getName()==_Interface._txt_idproveedor){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					
					logic.evaluarProveedor(tx);
				}
				
			}
			
			if (tx.getName()==_Interface._txt_fecha_hasta){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (tx.getText().length()==8){
						logic.evaluar_fecha(tx,event);
					}else{
						logic.evaluate_fecha_hasta(tx);	
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
			
			if (tx.getName()==_Interface._txt_fecha_carga_hasta){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (tx.getText().length()==8){
						logic.evaluar_fecha(tx,event);
					}else{
						logic.evaluate_fecha_carga_hasta(tx);	
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
			
			
		}
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_libro){
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					row++;
					logic.goCargarCpte(row);	
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					row--;
					logic.goCargarCpte(row);	
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					logic.goCargarCpte(row);
				}
			}
			if (table.getName()==_Interface._table_dias){
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					row++;
						
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					row--;
				}
				if (event.getKeyCode()==KeyEvent.VK_LEFT){
					col--;
				}
				if (event.getKeyCode()==KeyEvent.VK_RIGHT){
					col++;
				}
				if (logic.checkPostition(row, col)){
					
					logic.completeDiaCarga(row,col);
				}
					
			
			
			}
		}
	}
}
