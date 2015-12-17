/**
 * 
 */
package aplicacion.gestion.calendario.events;
import aplicacion.gestion.calendario.interfaces._Interface;
import aplicacion.gestion.calendario.logic._Logic;
import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;
/**
 * @author Administrador
 *
 */
public class ItemListenerHandler extends _ItemListenerHandler{
	public void procesar(ItemEvent e){
		_Logic logic=(_Logic) this._logic;
		if (e.getSource() instanceof JComboBox){
			JComboBox cb=(JComboBox) e.getSource();
			
			if (cb.getName()==_Interface._list_anio){
				logic.show_time2();
			}
			if (cb.getName()==_Interface._list_mes){
				logic.show_time2();
			}
		}
		if (e.getSource() instanceof JCheckBox){
			
			System.out.println(e.getSource());
			
			JCheckBox chk=(JCheckBox) e.getSource();
			JTable table=null;
			int row=-1;
			int col=-1;
			if (chk.getParent() instanceof JTable){
				table=(JTable) chk.getParent();
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
				System.out.println("CHK Selected "+chk.isSelected()+" "+e.getStateChange());
				logic.evaluateCheck(chk, row, col);
			}
			
		}
		
	}
}
