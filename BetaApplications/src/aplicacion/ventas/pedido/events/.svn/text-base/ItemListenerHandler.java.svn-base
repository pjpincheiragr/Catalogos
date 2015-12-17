package aplicacion.ventas.pedido.events;


import aplicacion.ventas.pedido.logic.*;
import aplicacion.ventas.pedido.interfaces.*;
import aplicacion.ventas.pedido.interfaces._Interface;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		
		
		_Logic _logic=(_Logic) this._logic;
		if (e.getSource() instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) e.getSource();
			if (chk.getName()==_Interface._chk_seleccionar){
				_logic.seleccionar(chk.isSelected());	
			}
			if (chk.getName()==_Interface._chk_sobrescribir){
				_logic.evaluate_sobrescribir(chk);	
			}
			if (chk.getName()==_Interface._chk_costo){
				_logic.showCost(chk);	
			}
			if (chk.getName()==_Interface._chk_enviar_cta){
				_logic.evaluate_enviar_a_cta(chk);	
			}
			if (chk.getName()==_Interface._table_items_seleccionar){
				//_logic.seleccionar(chk.isSelected());
				if (chk.getParent() instanceof JTable){
					JTable table=(JTable) chk.getParent();
					int row=table.getSelectedRow();
					int col=table.getSelectedColumn();
					_logic._calculate(row, chk.isSelected());
				}
				
				
			}
			if (chk.getName()==_Interface._chk_seleccionar_item_importar){
				//_logic.seleccionar(chk.isSelected());
				if (chk.getParent() instanceof JTable){
					JTable table=(JTable) chk.getParent();
					int row=table.getSelectedRow();
					int col=table.getSelectedColumn();
					_logic.evaluarSeleccion(chk, table, row);
				}
				
				
			}
		}
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			/*if (lst.getName() == _Interface._list_comprobante){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					//_logic.evaluate_comprobante(lst);	
				}
			}*/
		}
		/*
		
		if (lst.getName() == _Interface._list_moneda){
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				_logic.goCargar(_logic.getCuenta(lst));
			}
		}*/
	}

}
