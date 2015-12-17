package aplicacion.gestion.buscador.events;

import aplicacion.gestion.buscador.logic.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.gestion.buscador.interfaces.*;
import aplicacion.modelo.events._KeyListenerHandler;
public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			/*if (table.getName()==_Interface._table_medios){
				if (event.getKeyCode()==KeyEvent.VK_DELETE){
					_logic._medios_borrar_renglon(table,row);
				}
			}*/
			
		}
		if (event.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) event.getSource();
			if (lst.getName() == _Interface._lst_modos){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_modos(lst);
				}
				
			}
			if (lst.getName() == _Interface._lst_tc){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_tc(lst);
				}
				
			}
		}
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			if (tx.getName()== _Interface._txt_cuenta){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarCuenta(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//check numero. para saber si edita o es nuevo
				
					_logic.evaluar_cuenta(tx);
					
				}
			}
			if (tx.getName()== _Interface._txt_buscar_fecha_desde){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarFechaDesde();
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluate_fecha_desde(tx);
					
				}
			}
			if (tx.getName()== _Interface._txt_buscar_fecha_hasta){
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarFechaHasta();
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//check numero. para saber si edita o es nuevo
					_logic.evaluate_fecha_hasta(tx);
					
					
				}
			}
			
		}
		
		
		
					
	}
}
