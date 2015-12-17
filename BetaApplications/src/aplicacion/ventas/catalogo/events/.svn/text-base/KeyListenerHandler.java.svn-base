package aplicacion.ventas.catalogo.events;

import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.DefaultFocusManager;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

import aplicacion.ventas.catalogo.interfaces.*;
import aplicacion.ventas.catalogo.logic.*;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		
		//System.out.println("KeyPress");
		if (event.getSource() instanceof JTable){
			JTable table=(JTable) event.getSource();
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
			if (table.getName()==_Interface._table_proveedores){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluar_tabla_proveedores(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_SPACE){
					_logic.evaluar_tabla_proveedores(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					_logic.goShowIt(row-1);
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					_logic.goShowIt(row+1);
				}
				if (event.getKeyCode()==KeyEvent.VK_E){
					if (event.isControlDown()){
						_logic.aviso("La Edicion Solo Esta disponible en la Solapa de Articulos de Sistema");
					}
				}
			}
			if (table.getName()==_Interface._table_equivalencias){
				//System.out.println("Equivalencia KeyPress");
				if (event.getKeyCode()==KeyEvent.VK_E){
					if (event.isControlDown()){
						_logic.editar(row, col, table);
					}
				}
			}
			if (table.getName()==_Interface._table_sistema){
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
				
					//_logic.evaluar_tabla_id();
					
				}
				if (event.getKeyCode()==KeyEvent.VK_F12){
					_logic.registrar_faltante(row);
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					_logic.goShowItSistema(row-1);
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					_logic.goShowItSistema(row+1);
				}
				if (event.getKeyCode()==KeyEvent.VK_E){
					if (event.isControlDown()){
						_logic.editar(row, col, table);
					}
				}
			}
		}
		
		if (event.getSource() instanceof JTextField){
			
			JTextField tx=(JTextField) event.getSource();
			int row=-1;
			int col=-1;
			JTable table=null;
			if (tx.getParent() instanceof JTable){
				table=(JTable) tx.getParent();
				row=table.getSelectedRow();
				col=table.getSelectedColumn();
			}
			if (tx.getName()== _Interface._column){
				if (event.isControlDown()){
					if (event.getKeyCode()==KeyEvent.VK_E){
						_logic.editar();	
					}
				}
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.search(tx,col);
					
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					_logic.evaluat_escape(tx, col);
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					
					_logic.evaluar_tabla_sistema(tx,col);
					
				}
				if (event.getKeyCode()==KeyEvent.VK_F12){
					
					_logic.evaluar_tabla_f12(tx, col);
					
				}
				if (event.getKeyCode()==KeyEvent.VK_UP){
					
					_logic.Up(tx,col);
					
				}
				if (event.getKeyCode()==KeyEvent.VK_TAB){
					if (event.isControlDown()){
						_logic.transferBackFocus(tx, col);
					}else{
						_logic.transferNextFocus(tx, col);	
					}
					
					
				}
				if (event.getKeyCode()==KeyEvent.VK_DOWN){
					
					_logic.Down(tx,col);
					
					
				}
			}
			

			
		
			
						
			
			
		}
		
					
	}
}
