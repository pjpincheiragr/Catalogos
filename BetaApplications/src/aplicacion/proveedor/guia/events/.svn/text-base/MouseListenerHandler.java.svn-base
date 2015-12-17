package aplicacion.proveedor.guia.events;

import aplicacion.proveedor.guia.interfaces._Interface;
import aplicacion.proveedor.guia.logic._Logic;
import aplicacion.modelo.events._MouseListenerHandler;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.TrayIcon;

import javax.swing.JTable;
import javax.swing.JButton;

public class MouseListenerHandler extends _MouseListenerHandler{
	
	
	public void procesarEvento(MouseEvent e){
		_Logic logic=(_Logic) this._logic;
	
			if(e.getSource() instanceof JTable){
				JTable table=(JTable) e.getSource();
				
				int row=table.getSelectedRow();
				int col=table.getSelectedColumn();
			
			}
		
	}
	
}
