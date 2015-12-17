package aplicacion.ventas.catalogo.events;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import aplicacion.ventas.catalogo.interfaces.*;
import aplicacion.ventas.catalogo.logic.*;
import aplicacion.modelo.events._MouseListenerHandler;
public class MouseListenerHandler extends _MouseListenerHandler {

	public void procesarEvento(MouseEvent event){
		_Logic logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTable){
			
			JTable table=(JTable)  event.getSource();
			
			int row=table.getSelectedRow();
			int col=table.getSelectedColumn();
			
			if (table.getName()==_Interface._table_proveedores){
				/*if (event.getClickCount()>=2){
					if (col>0){
						boolean chk=(Boolean) table.getValueAt(row, 0);
						chk=!chk;
						table.setValueAt(chk, row, 0);
						
					}
					
				}*/
				
				if (event.getClickCount()>=1){
						
						if (event.getID()==event.MOUSE_CLICKED){
							if (event.getButton()==MouseEvent.BUTTON1){
								
									logic.goShowIt(row);
									
							}
						}
				}
				
			}
			if (table.getName()==_Interface._table_sistema){
				if (event.getClickCount()>=2){
					if (col>0){
						boolean chk=(Boolean) table.getValueAt(row, 0);
						chk=!chk;
						table.setValueAt(chk, row, 0);
						logic.evaluar_tabla_sistema(chk, row);
					}
					
				}
				
				if (event.getClickCount()<=1){
					if (event.getID()==event.MOUSE_CLICKED){
						if (event.getButton()==MouseEvent.BUTTON1){
							
							if (col>0){
								logic.goShowItSistema(row);	
							}
								
						}
					}
						
				}
				
			}
			if (table.getName()==_Interface._table_documentos){
				
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=2){
							logic.cargar(row);
					}		
					}
				}
				
				
			}
			if (table.getName()==_Interface._table_equivalencias){
				
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=2){
							logic.show_tabla_equivalencia(row);
					}		
					}
				}
				
				
			}
if (table.getName()==_Interface._table_catalogos){
				
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=2){
							logic.cargar_catalogo(row);
					}		
					}
				}
				
				
			}
			if (table.getName()==_Interface._table_fields){
				//System.out.println("Event Source >"+row+":"+col+" >"+event.getSource());
				if (event.getID()==event.MOUSE_CLICKED){
					if (event.getButton()==MouseEvent.BUTTON1){
						if (event.getClickCount()>=1){
							table.changeSelection(row, col,false,false);
							table.editCellAt(row, col);
							table.transferFocus();			
						}
						}
				}
				
			}
			
			
		}
	}
}
