package aplicacion.compras.carga.items.events;

import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import aplicacion.compras.carga.items.interfaces._Interface;
import aplicacion.compras.carga.items.logic._Logic;
import aplicacion.modelo.events._ItemListenerHandler;

public class ItemListenerHandler  extends  _ItemListenerHandler{
		public void procesarEvento(ItemEvent e) {
			_Logic logic=(_Logic) this._logic;
			if (e.getSource() instanceof JComboBox){
				JComboBox combo=(JComboBox) e.getSource();
				if (combo.getName()==_Interface._list_lineas){
					logic.update_prefijo(combo.getSelectedItem().toString());
				}
				if (combo.getName()==_Interface._list_tc){
					logic.change_tc(combo);
				}
				if (combo.getName()==_Interface._list_letra){
					logic._evaluar_letra(combo);
				}
				if (combo.getName()==_Interface._list_imputacion){
					logic.update_imputacion(combo);
				}
			}
			if (e.getSource() instanceof JCheckBox){
				JCheckBox chk=(JCheckBox) e.getSource();
				if (chk.getParent() instanceof JTable){
					JTable table=(JTable) chk.getParent();
					int row=table.getSelectedRow();
					if (chk.getName()==_Interface._table_etiquetas_seleccion){
						logic.seleccion_etiqueta(chk,row);
					}
					if (chk.getName()==_Interface._table_pedidos_especiales_chk){
						logic.evaluate_pedidos_especiales_selection(chk, row);
					}
					if (chk.getName()==_Interface._table_pedidos_especiales_item_chk){
						logic.seleccionar_pep_item(chk, row);
					}
					if (chk.getName()==_Interface._table_remitos_item_chk){
						//logic.seleccionar_remito_item(chk, row);
						logic.aviso("Debe seleccionar el remito completo. En la tabla de Remitos");
					}
					if (chk.getName()==_Interface._table_remitos_chk){
						logic.seleccionar_remito(chk, row);
					}
				}else {
					if (chk.getName()==_Interface._chk_seleccionar_etiquetas){
						logic.seleccionar_etiquetas(chk.isSelected());
					}
					if (chk.getName()==_Interface._chk_seleccionar){
						logic.seleccionar(chk.isSelected());
					}
					if (chk.getName()==_Interface._chk_seleccionar_pep){
						logic.seleccionar_especiales(chk.isSelected());
					}
				}
			
			}
		}

	}

