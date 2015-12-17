package aplicacion.compras.pedidoe.events;


import aplicacion.compras.pedidoe.logic.*;
import aplicacion.compras.pedidoe.interfaces.*;

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
			if (chk.getName()==_Interface._chk_proveedor_critico){
				_logic.evaluate_critico(null);
			}
			
			if (chk.getName()==_Interface._chk_mostrar_faltantes_proveedor){
				_logic.evaluate_faltantes(null);
			}
			if (chk.getName()==_Interface._chk_mostrar_eliminados){
				_logic.evaluate_faltantes(null);
			}
			if (chk.getName()==_Interface._chk_seleccionar_critico_item){
				if (chk.getParent() instanceof JTable){
					JTable table=(JTable) chk.getParent();
					int row=table.getSelectedRow();
					int col=table.getSelectedColumn();
					_logic.evaluate_chk_importar(table, row, chk.isSelected());
				}	
			}
			
			if (chk.getName()==_Interface._chk_seleccionar_faltante_item){
				if (chk.getParent() instanceof JTable){
					JTable table=(JTable) chk.getParent();
					int row=table.getSelectedRow();
					int col=table.getSelectedColumn();
					_logic.evaluate_chk_importar(table, row, chk.isSelected());
				}	
			}
			
			if (chk.getName()==_Interface._chk_seleccionar){
				_logic.seleccionar(chk.isSelected());	
			}
			if (chk.getName()==_Interface._chk_seleccionar_criticos){
				_logic.seleccionarCriticos(chk.isSelected());	
			}
			if (chk.getName()==_Interface._chk_seleccionar_faltantes){
				_logic.seleccionarFaltantes(chk.isSelected());	
			}
			if (chk.getName()==_Interface._chk_sobrescribir){
				_logic.evaluate_sobrescribir(chk);	
			}
			if (chk.getName()==_Interface._chk_costo){
				_logic.showCost(chk);	
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
		}
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			if (lst.getName() == _Interface._lst_estado){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					_logic.evaluate_estado(lst);	
				}
			}
			if (lst.getName() == _Interface._lst_categoria_faltantes){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					_logic.evaluate_faltantes(lst);	
				}
			}
			if (lst.getName() == _Interface._lst_faltantes){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					_logic.evaluate_faltantes(lst);	
				}
			}
			if (lst.getName() == _Interface._lst_categoria){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					_logic.evaluate_critico(lst);	
				}
			}
			if (lst.getName() == _Interface._lst_stock){
				if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
					_logic.evaluate_critico(lst);	
				}
			}
		}
		/*
		
		if (lst.getName() == _Interface._list_moneda){
			if (e.getID()==ItemEvent.ITEM_STATE_CHANGED){
				_logic.goCargar(_logic.getCuenta(lst));
			}
		}*/
	}

}
