package aplicacion.cliente.cobranza.events;
import aplicacion.cliente.cobranza.logic._Logic;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JTable;
import aplicacion.cliente.cobranza.interfaces._Interface;
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
					_logic.borrarRenglon(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					_logic.GrabarCobranza();
				}
			}
			
		}
		
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			if (tx.getName()== _Interface._txt_idcobranza){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarCobranza(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//check numero. para saber si edita o es nuevo
				
					
					_logic._cobranza_evaluar_idcobranza(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					_logic.GrabarCobranza();
				}
			}

			if (tx.getName()== _Interface._txt_idcliente){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarCliente(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					_logic.GrabarCobranza();
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//check numero. para saber si edita o es nuevo
					_logic.evaluarCliente(tx);
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
					//check numero. para saber si edita o es nuevo
				
					_logic._evaluar_fecha(tx);
					
				}
				
			}
			
			
			if (tx.getName()==_Interface._txt_fecha){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					if (tx.getText().length()==8){
						_logic.evaluar_fecha(tx,event);
					}else{
						_logic._evaluar_fecha(tx);	
					}
					
				}else{
					if (event.getKeyCode()==KeyEvent.VK_F2){
						_logic.GrabarCobranza();
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
			}
			
			if (tx.getName()== _Interface._table_medios_medio){
				Container comp=tx.getParent();
				
				int row=-1;
				int col=-1;
				if (comp instanceof JTable){
					JTable table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
				}	
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._medios_evaluar_medio(tx, row, col);
				}
				if (event.getKeyCode()==KeyEvent.VK_DELETE){
					if (tx.getText().compareTo("")==0){
						_logic.borrarRenglon(row);	
					}
					
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					_logic.GrabarCobranza();
				}
				System.out.println("tecla en editor de celda medio en tabla medios de pago "+event.getKeyCode());
			}
			
			if (tx.getName()== _Interface._table_medios_importe){
				Container comp=tx.getParent();
				
				int row=-1;
				int col=-1;
				JTable table=null;
				if (comp instanceof JTable){
					table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
				}	
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._medios_evaluar_importe(table,tx, row, col);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					_logic.GrabarCobranza();
				}
				if (event.getKeyCode()==KeyEvent.VK_DELETE){
					
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
				}	
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._medios_evaluar_banco_cheque(tx, row, col);
				}
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarBanco(row, tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					_logic.GrabarCobranza();
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
				}	
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._medios_evaluar_serie_cheque(tx, row, col);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					_logic.GrabarCobranza();
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
				}	
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._medios_evaluar_serie_cheque(tx, row, col);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					_logic.GrabarCobranza();
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
				}	
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic._medios_evaluar_vencimiento(tx, row, col);
				}
				if (event.getKeyCode()==KeyEvent.VK_F2){
					_logic.GrabarCobranza();
				}
			}
		}
		
		
		
					
	}
}
