package aplicacion.proveedor.corrector.events;

import aplicacion.proveedor.corrector.interfaces.*;
import aplicacion.proveedor.corrector.logic.*;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;

import javax.swing.*;
public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) e.getSource();
			Container comp=chk.getParent();
			
			int row=-1;
			int col=-1;
			if (chk.getName()==_Interface._chk_seleccionar_creditos){
				_logic.SeleccionarCreditos(chk.isSelected());
			}
			if (chk.getName()==_Interface._chk_seleccionar_deudas){
				_logic.SeleccionarDeudas(chk.isSelected());
			}
			if (chk.getName()==_Interface._chk_seleccionar_anticipos){
				_logic.SeleccionarAnticipos(chk.isSelected());
			}
			if (comp instanceof JTable){
				JTable table=(JTable) comp;
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
				if (chk.getName()==_Interface._table_creditos_chk){
					_logic.evaluatecheckcreditos(chk, row, col, false);
				}
				if (chk.getName()==_Interface._table_deudas_chk){
					_logic.evaluatecheckdeudas(chk, row, col, false);
				}
				if (chk.getName()==_Interface._table_anticipos_chk){
					_logic.evaluatecheckanticipos(chk, row, col, false);
				}
			}
				
		}
		
		
		
	}

}
