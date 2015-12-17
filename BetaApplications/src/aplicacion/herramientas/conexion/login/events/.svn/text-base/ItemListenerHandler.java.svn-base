package aplicacion.herramientas.conexion.login.events;


import aplicacion.herramientas.conexion.login.logic.*;
import aplicacion.herramientas.conexion.login.interfaces.*;

import aplicacion.modelo.events._ItemListenerHandler;

import java.awt.Container;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;


public class ItemListenerHandler extends  _ItemListenerHandler{
	public void procesarEvento(ItemEvent e) {
		
		
		
		
		_Logic _logic=(_Logic) this._logic;
		
		if (e.getSource() instanceof JComboBox){
			JComboBox lst = (JComboBox) e.getSource();
			  if (lst.getName() == _Interface._lst_skins){
				_logic.setSkin(lst);
			 }
		}
		
		
	}

}
