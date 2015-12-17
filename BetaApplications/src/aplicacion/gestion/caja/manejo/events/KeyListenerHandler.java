package aplicacion.gestion.caja.manejo.events;

import aplicacion.gestion.caja.manejo.logic.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.gestion.caja.manejo.interfaces.*;
import aplicacion.modelo.events._KeyListenerHandler;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			if (table.getName()==_Interface._table_movimientos){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.editCell(row,col);
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
			if (lst.getName() == _Interface._list_cajas){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_caja(lst);	
				}
				
			}
		}
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			if (tx.getName()== _Interface._txt_desde){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
					_logic.BuscarFecha(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					
				}
			}
			

			if (tx.getName()== _Interface._txt_hasta){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					
					_logic.BuscarFecha(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
				
				}
			}
		
			
			if (tx.getName()== _Interface._table_arqueo_cantidad){
				Container comp=tx.getParent();
				
				int row=-1;
				int col=-1;
				if (comp instanceof JTable){
					JTable table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
					if (event.getKeyCode()==KeyEvent.VK_ENTER){
						_logic.evaluate_cantidad(tx,row,col);
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
			
		}
		
					
	}
}
