package aplicacion.ventas.catalogo.events;


import aplicacion.ventas.catalogo.logic.*;
import aplicacion.ventas.catalogo.interfaces.*;


import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk = (JCheckBox) e.getSource();
			_Logic _logic=(_Logic) this._logic;
			
				Container comp=chk.getParent();
				
				int row=-1;
				int col=-1;
				JTable table=null;
				if (comp instanceof JTable){
					table=(JTable) comp;
					row=table.getSelectedRow();
					col=table.getSelectedColumn();
				}
				if (chk.getName()==_Interface._chk_multimedia){
					if (chk.isSelected()){
						_logic.expand();
					}else{
						_logic.conpact();
					}
				}
				
			if (chk.getName()==_Interface._table_chk_sistema){	
				if (row>=0){
					System.out.println("Evento en tabla de sistemas> "+e.getSource());
					_logic.evaluar_tabla_sistema(chk.isSelected(), row);
				}
			}
			
			if (chk.getName()==_Interface._chk_relaciones){	
					_logic.evaluar_relaciones(chk);
				
			}
			if (chk.getName()==_Interface._table_chk_articulos){	
				if (row>=0){
					System.out.println("Evento en tabla de articulos> "+e.getSource());
					_logic.evaluar_tabla_articulos(chk.isSelected(), row);
				}
			}
			
			if (chk.getName()==_Interface._table_chk_relaciones){	
				if (row>=0){
					System.out.println("Evento en tabla de relaciones> "+e.getSource());
					_logic.evaluar_tabla_relaciones(chk.isSelected(), row);
				}
			}
			
			if (chk.getName()==_Interface._table_chk_seleccion){
				if (row>=0){
					System.out.println("Evento en tabla de selecciones> "+e.getSource());
					_logic.evaluar_tabla_selecciones(chk.isSelected(), row);
				}
				
			}	
		}
		
	}

}
