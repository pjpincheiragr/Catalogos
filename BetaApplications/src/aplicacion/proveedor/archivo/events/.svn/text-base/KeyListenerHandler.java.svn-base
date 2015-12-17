package aplicacion.proveedor.archivo.events;

import aplicacion.proveedor.archivo.logic.*;
import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.proveedor.archivo.interfaces.*;

import aplicacion.modelo.events._KeyListenerHandler;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		
		
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			System.out.println("table event!");
			if (table.getName()==_Interface._table_imputacion){
				if (event.getKeyCode()==KeyEvent.VK_DELETE){
					System.out.println("table event delete!");
					_logic.delete_renglon(table.getSelectedRow());
				}
				
				
			}
		}
		
		
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			
			

			if (tx.getName()== _Interface._txt_idproveedor){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarProveedor(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//check numero. para saber si edita o es nuevo
					_logic.evaluarProveedor(tx);
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
			
			if (tx.getName()== _Interface._table_imputacion_cuenta){
				JTable table=(JTable) tx.getParent();
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarCuenta(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//check numero. para saber si edita o es nuevo
					_logic.cargarCuenta(tx,table.getSelectedRow());
				}
					
			}
		
			
		}
		
		}
}
