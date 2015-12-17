package aplicacion.herramientas.java.msortableselector.events;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import aplicacion.herramientas.java.msortableselector.interfaces.*;
import aplicacion.herramientas.java.msortableselector.logic.*;
import aplicacion.modelo.events._MouseListenerHandler;
public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
			if (table.getName()==_Interface._table){
				if (event.getClickCount()>=2){
					if (col>0){
						boolean chk=(Boolean) table.getValueAt(row, 0);
						chk=!chk;
						table.setValueAt(chk, row, 0);
						logic.evaluar_tabla_id(chk, row);
					}
					
				}
				
			}
			if (table.getName()==_Interface._table_fields){
				//System.out.println("Event Source >"+row+":"+col+" >"+event.getSource());
				
				table.changeSelection(row, col,false,false);
				table.editCellAt(row, col);
				table.transferFocus();
			}
			
			
		}
	}
}
