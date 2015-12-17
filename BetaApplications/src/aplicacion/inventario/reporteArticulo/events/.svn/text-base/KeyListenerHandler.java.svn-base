package aplicacion.inventario.reporteArticulo.events;

import aplicacion.inventario.reporteArticulo.logic.*;
import aplicacion.inventario.reporteArticulo.interfaces._Interface;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler{

	public void procesarEvento(KeyEvent event){
		_Logic _logic=(_Logic) this._logic;
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();
			
		
			

			if (tx.getName()== _Interface._txt_descripcion){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					//_logic.evaluarDescripcion(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					//_logic.evaluarDescripcion(tx);				
				}
			}			
			if (tx.getName()== _Interface._txt_idproveedor){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarProveedor(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluar_proveedor(tx);
				
				}
				
			}
			
			if (tx.getName()== _Interface._txt_linea){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarLinea(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluar_linea(tx);
				
				}
				
			}
			
			if (tx.getName()== _Interface._txt_idarticulo_desde){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarArticulo(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluar_idarticulo_desde(tx);
				
				}
				
			}
			if (tx.getName()== _Interface._txt_idarticulo_hasta){
				
				if (event.getKeyCode()==KeyEvent.VK_F5){
					_logic.BuscarArticulo(tx);
				}
				if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					//cancelar
				}
				if (event.getKeyCode()==KeyEvent.VK_ENTER){
					_logic.evaluar_idarticulo_hasta(tx);
				
				}
				
			}
	}
	}
}
