package aplicacion.ventas.gestion2.events;
import aplicacion.ventas.gestion2.logic._Logic;
import aplicacion.modelo.events._ItemListenerHandler;
import aplicacion.ventas.gestion2.interfaces._Interface;

import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;
public class ItemListenerHandler extends _ItemListenerHandler {

	public void procesar(ItemEvent e){
		_Logic logic=(_Logic) this._logic;
		JTable table=null;
		int row=-1;
		int col=-1;
		if (e.getSource() instanceof JCheckBox	){
			JCheckBox chk=(JCheckBox) e.getSource();
			if (chk.getParent() instanceof JTable){
				table=(JTable)chk.getParent();
				
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
			}
			
			if (chk.getName()==_Interface._chk_seleccionar_pedidos_categoria){
				logic.seleccionar_categoria(chk.isSelected());
			}
			if (chk.getName()==_Interface._chk_seleccionar){
				logic.seleccionar(chk.isSelected());
			}
			if (chk.getName()==_Interface._chk_seguimiento){
				if (table!=null){
					if (row>=0 & row<table.getRowCount()){
						System.out.println("Change Seguimiento "+table.getValueAt(row, 0).toString());
						logic.update_seguimiento(chk, row,col);	
					}	
				}
				
				
			}
		}
			if (e.getSource() instanceof JComboBox){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					JComboBox cb=(JComboBox) e.getSource();
					
					if (cb.getParent() instanceof JTable){
						table=(JTable)cb.getParent();
						
						row=table.getSelectedRow();
						
						if (cb.getName()==_Interface._lst_nivel){
							
								logic.update_level(cb,row);	
							
							
						}	
						
					}	
					if (cb.getName()==_Interface._lst_anio){
						logic.update_anio(cb);
					}
					if (cb.getName()==_Interface._lst_mes){
						logic.update_mes(cb);
					}
					if (cb.getName()==_Interface._lst_estado){
						logic.evaluar_estado(cb);
					}
					
					
					if (cb.getName()==_Interface._lst_modo){
						logic.update_modo(cb);
					}	
				}
				
				
				
				
		}
		
	}
}
