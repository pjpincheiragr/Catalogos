package aplicacion.compras.carga.pedido.events;
import aplicacion.compras.carga.pedido.logic._Logic;
import aplicacion.modelo.events._ItemListenerHandler;
import aplicacion.compras.carga.pedido.interfaces._Interface;

import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;
public class ItemListenerHandler extends _ItemListenerHandler {

	public void procesar(ItemEvent e){
		_Logic logic=(_Logic) this._logic;
		if (e.getSource() instanceof JCheckBox	){
			JCheckBox chk=(JCheckBox) e.getSource();
			int row=-1;
			if (chk.getParent() instanceof JTable){
				JTable table=(JTable)chk.getParent();
				row=table.getSelectedRow();
				
				System.out.println("_chek_linea Selected Row?"+row);
			}
			if (chk.getName()==_Interface._chk_linea){
				
				logic.evaluate_seleccion(row, chk.isSelected());
			}
			if (chk.getName()==_Interface._chk_items){
				logic.seleccionar_items(chk.isSelected());
			}
			
			if (chk.getName()==_Interface._chk_selecccionar_lineas_predeterminadas){
				logic.seleccionar_lineas_predeterminadas(chk.isSelected());
			}
			if (chk.getName()==_Interface._chk_item){
				logic.evaluate_seleccion_item(row, chk.isSelected());
			}
		}
		
		if (e.getSource() instanceof JComboBox){
				JComboBox cb=(JComboBox) e.getSource();
				
				if (cb.getName()==_Interface._lst_categoria){
				logic.evaluate_categoria(cb);	
				}
				if (cb.getName()==_Interface._lst_lineas_categoria){
					logic.importar_lineas_search();	
					}
				if (cb.getName()==_Interface._lst_lineas_modo){
					logic.importar_lineas_search();	
					}
				if (cb.getName()==_Interface._lst_modo){
					logic.evaluate_categoria(cb);		
					}
				if (cb.getName()==_Interface._lst_lineas){
					logic.ordenar_lineas();	
					}
				if (cb.getName()==_Interface._lst_lineas_frame_categoria){
					logic.ordenar_lineas();	
					}
		}
		
	}
}
