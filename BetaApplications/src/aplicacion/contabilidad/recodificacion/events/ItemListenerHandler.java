package aplicacion.contabilidad.recodificacion.events;


import aplicacion.sistema.analizador.logic.*;
import aplicacion.sistema.analizador.interfaces.*;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		JCheckBox chk = (JCheckBox) e.getSource();
		_Logic _logic=(_Logic) this._logic;
		if (chk.getName()==_Interface._chk_seleccionar){
			Container comp=chk.getParent();
			int row=-1;
			int col=-1;
			if (comp instanceof JTable){
				JTable table=(JTable) comp;
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
			}
			
			_logic.seleccionar(chk.isSelected());
			//_logic.evaluar_tabla_id(chk, row);
			
		}
		
	}

}
