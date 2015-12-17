package aplicacion.gestion.canje.events;

import aplicacion.gestion.canje.logic.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.gestion.canje.interfaces.*;
import aplicacion.modelo.events._KeyListenerHandler;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_medios){
				if (event.getKeyCode()==KeyEvent.VK_DELETE){
					_logic._medios_borrar_renglon(table,row);
				}
			}
			
		}
		if (event.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) event.getSource();
			if (lst.getName() == _Interface._list_cajas){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_caja();	
				}
				
			}
		}
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			if (tx.getName()== _Interface._txt_fecha){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
					_logic.BuscarFecha(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//check numero. para saber si edita o es nuevo
				
					_logic.evaluarFecha(tx);
					
				}
			}
			if (tx.getName()== _Interface._txt_idcanje){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
					_logic.BuscarCanje(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//check numero. para saber si edita o es nuevo
				
					
					_logic._evaluar_idcanje(tx);
				}
			}

			

			
			if (tx.getName()== _Interface._txt_concepto){
				
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//check numero. para saber si edita o es nuevo
					_logic.evaluar_concepto(tx);
				}
					
			}
			
			if (tx.getName()== _Interface._table_medios_medio){
				Container comp=tx.getParent();
				
				int row=-1;
				int col=-1;
				if (comp instanceof JTable){
					JTable table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						_logic._medios_evaluar_medio(table,tx, row, col);
					}
				}	
				
				System.out.println("tecla en editor de celda medio en tabla medios de pago "+event.getKeyCode());
			}
			
			if (tx.getName()== _Interface._table_medios_importe){
				Container comp=tx.getParent();
				
				int row=-1;
				int col=-1;
				if (comp instanceof JTable){
					JTable table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						_logic._medios_evaluar_importe(table,tx, row, col);
					}
				}	
				
			}
			
			if (tx.getName()== _Interface._table_medios_banco){
				Container comp=tx.getParent();
				
				int row=-1;
				int col=-1;
				if (comp instanceof JTable){
					JTable table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						_logic._medios_evaluar_banco_cheque(table,tx, row, col);
					}
					if (event.getKeyCode()==KeyEvent.VK_F5){
						if (table.getName()==_Interface._table_medios){
							_logic.BuscarCheque(table,row,tx);	
						}else {
							_logic.BuscarBanco(table, row, tx);
						}
						
					}
				}	
				
				
			}
			
			if (tx.getName()== _Interface._table_medios_serie){
				Container comp=tx.getParent();
				
				int row=-1;
				int col=-1;
				if (comp instanceof JTable){
					JTable table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						_logic._medios_evaluar_serie_cheque(table,tx, row, col);
					}
				}	
				
			}
			
			if (tx.getName()== _Interface._table_medios_numero){
				Container comp=tx.getParent();
				
				int row=-1;
				int col=-1;
				if (comp instanceof JTable){
					JTable table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						System.out.println("Evaluar numero de cheque!!!");
						if (table.getName()==_Interface._table_medios){
							_logic._medios_evaluar_numero_cheque(table,tx, row, col);	
						}else{
							_logic._medios_evaluar2_numero_cheque(table,tx, row, col);
						}
						
					}
				}	
				
			}
			
			if (tx.getName()== _Interface._table_medios_vencimiento){
				Container comp=tx.getParent();
				
				int row=-1;
				int col=-1;
				if (comp instanceof JTable){
					JTable table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						_logic._medios_evaluar_vencimiento(table,tx, row, col);
					}
				}	
				
			}
		}
		
		
		
					
	}
}
