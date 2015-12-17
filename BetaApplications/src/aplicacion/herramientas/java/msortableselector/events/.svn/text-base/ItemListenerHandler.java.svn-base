package aplicacion.herramientas.java.msortableselector.events;


import aplicacion.herramientas.java.msortableselector.logic.*;
import aplicacion.herramientas.java.msortableselector.interfaces.*;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		JCheckBox chk = (JCheckBox) e.getSource();
		_Logic _logic=(_Logic) this._logic;
		
			Container comp=chk.getParent();
			
			int row=-1;
			int col=-1;
			if (comp instanceof JTable){
				JTable table=(JTable) comp;
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
			}
			
		if (chk.getName()==_Interface._table_chk){	
			if (row>=0){
				_logic.evaluar_tabla_id(chk.isSelected(), row);	
			}
			
			
		}
		if (chk.getName()==_Interface._table_chk2){
			if (row>=0){
				System.out.println("Evento en tabla de selecciones> "+e.getSource());
				_logic.evaluar_tabla_id2(chk, row);	
			}
			
		}
		
	}

}
