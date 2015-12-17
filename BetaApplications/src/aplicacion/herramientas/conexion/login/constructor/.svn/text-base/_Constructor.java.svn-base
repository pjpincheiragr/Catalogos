package aplicacion.herramientas.conexion.login.constructor;

import javax.swing.SwingUtilities;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.herramientas.conexion.login.events.ActionListenerHandler;
import aplicacion.herramientas.conexion.login.events.ChangeListenerHandler;
import aplicacion.herramientas.conexion.login.events.ItemListenerHandler;
import aplicacion.herramientas.conexion.login.events.KeyListenerHandler;
import aplicacion.herramientas.conexion.login.events.WindowAdapterHandler;
import aplicacion.herramientas.conexion.login.gui._Frame;
import aplicacion.herramientas.conexion.login.interfaces._Interface;
import aplicacion.herramientas.conexion.login.interfaces._parametros;
import aplicacion.herramientas.conexion.login.logic._Data;
import aplicacion.herramientas.conexion.login.logic._Logic;


public class _Constructor extends Constructor {

	protected void initialize_frame(){
		try {
			_frame=new _Frame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	protected void initialize_logic(){
		_logic=new _Logic();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void initialize_components(){
		_Frame _frame=(_Frame) this._frame;
		_frame.setResizable(false);
		_frame.get_btn_salir().setActionCommand(_Interface._btn_salir);
		_frame.get_btn_error().setActionCommand(_Interface._btn_error);
		_frame.get_btn_login().setActionCommand(_Interface._btn_login);
		_frame.get_btn_login2().setActionCommand(_Interface._btn_login);
		_frame.get_txt_password().setName(_Interface._txt_password);
		_frame.get_txt_usuario().setName(_Interface._txt_user);
		_frame.get_bar_traslucent().setName(_Interface._bar_trnslucent);
		_frame.get_btn_editar_conexion().setActionCommand(_Interface._btn_editar_conexion);
		
	}
	
	protected void initialize_actionlistener_handler(){
		_actionlistener_handler=new ActionListenerHandler();
	}
	
	protected void initialize_windowadapter_handler(){
		_windowadapter_handler=new WindowAdapterHandler();
	}
	
	protected void initialize_keylistener_handler(){
		_keylistener_handler=new KeyListenerHandler();
	}
	
	protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new ItemListenerHandler();
	}
	
	protected void initialize_changelistener_handler(){
		_changelistener_handler=new ChangeListenerHandler();
	}
	public void init(){
		
		
		this.setShowOnStartup(false);
		super.init();
		boolean reconexion=false;
		try {
			reconexion=(Boolean)this.getParameter(_parametros.reconexion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		final _Logic _logic=(_Logic) this._logic;
		
		
        			if (javax.swing.SwingUtilities.isEventDispatchThread()){
        				if (reconexion){
        					_logic.setIngreso(false);
        				}
        				_logic.cargar_modos();
        				_logic.loadVersion();				
              			_logic.cargar_conexiones();
              			_logic.cargarSkins();
        				_logic.cargar_memoria();
        				_logic.cargar_background();
              		  	_logic.centrar();
              		  	_logic.focus();
              			
        			}else{
        				final boolean _reconexion=reconexion;
        				Runnable runnable=new Runnable(){
        					public void run(){
        						if (_reconexion){
                					_logic.setIngreso(false);
                				}
        						_logic.cargar_modos();
        						_logic.loadVersion();
                      			_logic.cargar_conexiones();
                      			_logic.cargarSkins();
                				_logic.cargar_memoria();
                				_logic.cargar_background();
                      		  _logic.centrar();
                      		_logic.focus();
        					}
        				};
        				javax.swing.SwingUtilities.invokeLater(runnable);
        			}
        			
        			
                  

		
		
	}
}
