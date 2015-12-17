/**
 * 
 */
package aplicacion.herramientas.java.hora.events;
import aplicacion.herramientas.java.hora.interfaces._Interface;
import aplicacion.herramientas.java.hora.logic._Logic;
import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
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
	}
}
