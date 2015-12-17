package aplicacion.sistema.menu.constructor;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import aplicacion.sistema.menu.events.WindowAdapterHandler;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.sistema.menu.gui._Frame;
import aplicacion.sistema.menu.logic._Logic;
import aplicacion.sistema.menu.logic._Data;
import aplicacion.sistema.menu.interfaces._Interface;
import aplicacion.sistema.menu.events.ActionListenerHandler;
import aplicacion.sistema.menu.events.MouseListenerHandler;
import aplicacion.sistema.menu.events.KeyListenerHandler;
public class _Constructor extends Constructor{

	protected void initialize_frame(){
		_frame=new _Frame();
		
	}
	protected void initialize_data(){
		_data=new _Data();
	}
	protected void initialize_logic(){
		_logic=new _Logic();
	}
	
	protected void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	protected void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	protected void initialize_windowadapter_handler(){
		_windowadapter_handler=new WindowAdapterHandler();
	}
	
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new MouseListenerHandler();
		
	}
	
	public void initialize_components(){
		_Frame frame=(_Frame) _frame;
		frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		frame.get_btn_agregar().setActionCommand(_Interface._btn_abrir);
		frame.get_btn_eliminar().setActionCommand(_Interface._btn_eliminar);
		frame.get_btn_error().setActionCommand(_Interface._btn_error);
		frame.get_btn_stop().setActionCommand(_Interface._btn_pause);
		frame.get_btn_play().setActionCommand(_Interface._btn_play);
		frame.get_btn_siguiente().setActionCommand(_Interface._btn_siguiente);
		frame.get_btn_anterior().setActionCommand(_Interface._btn_anterior);
		frame.get_btn_minimizar().setActionCommand(_Interface._btn_minimizar);
		
		frame.get_btn_kill().setActionCommand(_Interface._btn_kill);
		frame.get_txt_codigo().setName(_Interface._txt_codigo);
	}
	
	private void refresh(){
	   javax.swing.SwingUtilities.invokeLater(new Runnable(){
     	  public void run() {
     		 try{
     	        UIManager.setLookAndFeel(lookAndFeelTheme);
     			   setLookAndFeelTheme(lookAndFeelTheme);
     	       
     	       } catch (Exception e) {
     	     	  e.printStackTrace();
     	     	  
     	       }
     		  SwingUtilities.updateComponentTreeUI(_frame);
           }
       });
	}
	public void init(){
		
		super.init();
		this.setMaster(true);
		try {
			_Logic logic=(_Logic) _logic;
			String iduser=(String)this.getValueParameter(_parametros.iduser);
			
			if (iduser.compareTo("")!=0){
				logic.setUser(iduser);
						
			}else{
				iduser=logic.getConstructor().getIduser();
			}
			logic.init();
			logic.focus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
