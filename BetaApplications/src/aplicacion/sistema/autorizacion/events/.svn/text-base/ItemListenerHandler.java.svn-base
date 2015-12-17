package aplicacion.sistema.autorizacion.events;


import aplicacion.sistema.autorizacion.logic.*;
import aplicacion.sistema.autorizacion.interfaces.*;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		JCheckBox chk = (JCheckBox) e.getSource();
		_Logic _logic=(_Logic) this._logic;
		if (chk.getName()==_Interface._table_chk){
			Container comp=chk.getParent();
			
			int row=-1;
			int col=-1;
			if (comp instanceof JTable){
				JTable table=(JTable) comp;
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
			}
			_logic.evaluar_tabla_id(chk, row);
			
		}
		if (chk.getName()==_Interface._table_chk_icono){
			Container comp=chk.getParent();
			
			int row=-1;
			int col=-1;
			if (comp instanceof JTable){
				JTable table=(JTable) comp;
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
			}
			_logic.evaluar_tabla_id_icono(chk, row);
			
		}
	}

}
