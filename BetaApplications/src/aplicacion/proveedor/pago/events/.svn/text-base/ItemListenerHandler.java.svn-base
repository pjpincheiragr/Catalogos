package aplicacion.proveedor.pago.events;


import aplicacion.proveedor.pago.logic.*;
import aplicacion.proveedor.pago.interfaces.*;


import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		
		_Logic _logic=(_Logic) this._logic;
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			
			
			if (lst.getName() == _Interface._lst_caja){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					//_logic.evaluate_caja(lst);	
				}
				
			}	
		}
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk = (JCheckBox) e.getSource();
			if (chk.getName()==_Interface._chk_seleccionar_cpte){
				_logic.Seleccionar(chk.isSelected());
				
			}
			if (chk.getName()==_Interface._chk_seleccionar_creditos){
				_logic.SeleccionarCreditos(chk.isSelected());
				
			}
			if (chk.getName()==_Interface._table_cpte_chk){
				Container comp=chk.getParent();
				
				int row=-1;
				int col=-1;
				if (comp instanceof JTable){
					JTable table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
				}
				_logic.evaluatecheckCpte(chk, row, col, false);
			}
			if (chk.getName()==_Interface._table_opg_chk){
				Container comp=chk.getParent();
				
				int row=-1;
				int col=-1;
				if (comp instanceof JTable){
					JTable table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
				}
				_logic.evaluatecheckOPG(chk, row, col, false);
			}
	
		}
	}

}
