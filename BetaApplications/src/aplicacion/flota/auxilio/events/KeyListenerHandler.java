
package aplicacion.flota.auxilio.events;

import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import aplicacion.flota.auxilio.interfaces._Interface;
import aplicacion.flota.auxilio.logic._Logic;
import aplicacion.modelo.events._KeyListenerHandler;

public class KeyListenerHandler extends _KeyListenerHandler {
	
	public void procesar(KeyEvent event){
		
		_Logic logic=(_Logic) this._logic;
		
		JTable table=null;
		int row=-1;
		int col=-1;
		
		
		
		if (event.getSource() instanceof JTextField){
			JTextField tx=(JTextField) event.getSource();

			if (tx.getName()==_Interface._txt_idauxilio){
				
					if (tx.getParent() instanceof JTable){
						table=(JTable) tx.getParent();
						row=table.getSelectedRow();
						col=table.getSelectedColumn();
						}
					
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarIdauxilio(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			if (tx.getName()==_Interface._txt_chofer){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarChofer(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			if (tx.getName()==_Interface._txt_choferAuxilio){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarChoferAuxilio(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			if (tx.getName()==_Interface._txt_detalle){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarDetalle(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			if (tx.getName()==_Interface._txt_detalleAuxilio){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarDetalleAuxilio(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_detalleReemplazo){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarDetalleReemplazo();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_dominio){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarDominio(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_dominioAuxilio){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarIdunidadAuxilio(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_dominioReemplazo){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarIdunidadReemplazo(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_falloAparente ){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 //logic.evaluarIdauxilio(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 //logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_falloReal){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 //logic.evaluarIdauxilio(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 //logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_fecha){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluar_fecha(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 //logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}
			
			if (tx.getName()==_Interface._txt_idunidad){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarIdunidad(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 //logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}

			if (tx.getName()==_Interface._txt_idunidadAuxilio){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarIdunidadAuxilio(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 //logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}

			if (tx.getName()==_Interface._txt_idunidadReemplazo){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarIdunidadReemplazo(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 //logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}

			
			if (tx.getName()==_Interface._txt_numero){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarNumero(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 //logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}

			if (tx.getName()==_Interface._txt_sucursal){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarSucursal(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 //logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}

			if (tx.getName()==_Interface._txt_ubicacion){
				 
				 if (event.getKeyCode()==KeyEvent.VK_ENTER){
					 logic.evaluarUbicacion(tx);
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_ESCAPE){
					 //logic.clean();
				 	}
				 if (event.getKeyCode()==KeyEvent.VK_F5){
					// logic.buscarAuxilio(tx);
				 	}
			 	}

			

		}
		
	}
	
}
