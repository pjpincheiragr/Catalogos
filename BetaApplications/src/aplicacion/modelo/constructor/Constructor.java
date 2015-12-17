package aplicacion.modelo.constructor;
/**
 * Constructor modelo para la creacion de aplicaciones. 
 * autor: Agustin Wisky!!! :-)
 */

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;          
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import org.jdesktop.jxlayer.JXLayer;
import javax.swing.plaf.metal.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.UnsupportedLookAndFeelException;


import org.pushingpixels.substance.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import aplicacion.herramientas.conexion.*;
import aplicacion.herramientas.conexion.conectores.*;

import org.pushingpixels.substance.api.skin.*;


import aplicacion.modelo.events.*;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.interfaces._parametros;


public class Constructor implements IConstructor{
	protected JFrame _frame=null;
	protected Logic _logic=null;
	protected boolean master=false;
	private float opacity=1f;
	public float getOpacity() {
		return opacity;
	}
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
	public boolean isMaster() {
		System.out.println("is Master?>"+this.getClass()+" "+master);
		return master;
	}
	public void setMaster(boolean master) {
		this.master = master;
	}



	protected Boolean graphical=true;
	protected boolean showOnStartup=true;
	
	protected LinkedList parametros=new LinkedList();
	protected boolean visible=true;
	protected String lookAndFeelTheme="";//"org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel";
	
	protected boolean lookAndFeel=true;
	protected boolean buttonlook=false;
	protected List<Constructor> childs=null;
	protected aplicacion.herramientas.java.Error ErrorHandler=null;

	public aplicacion.herramientas.java.Error getErrorHandler() {
		return ErrorHandler;
	}
	public void setErrorHandler(aplicacion.herramientas.java.Error errorHandler) {
		ErrorHandler = errorHandler;
	}
	private void initialize_ErrorHandler(){
		ErrorHandler=new aplicacion.herramientas.java.Error(this);
	}
	public String getLookAndFeelTheme() {
		return lookAndFeelTheme;
	}

	public void setLookAndFeelTheme(String lookAndFeelTheme) {
		this.lookAndFeelTheme = lookAndFeelTheme;
	}

	

	protected Constructor Caller=null;
	/*
	 * Definicion de variables Adapters y Listeners
	 */
	protected _WindowAdapter _window_adapter=null;
	protected _KeyListener _key_listener=null;
	protected _ActionListener _action_listener=null;
	protected _DropTargetListener _droptarget_listener=null;
	protected _DragSourceListener _dragsource_listener=null;
	protected _ItemListener _item_listener=null;
	protected _ChangeListener _change_listener=null;
	protected _MouseListener _mouse_listener=null;
	protected _MouseMotionListener _mousemotion_listener=null;
	protected _MouseWheelListener _mousewheel_listener=null;
	protected _TreeModelListener _treemodel_listener=null;
	protected _TreeSelectionListener _treeselection_listener=null;
	/*
	 * Definicion de variables de Handlers que corresponden 
	 * a un Adapter o Listener especifico. 
	 */
	protected _ActionListenerHandler _actionlistener_handler=null;
	protected _DropTargetListenerHandler _droptargetlistener_handler=null;
	protected _DragSourceListenerHandler _dragsourcelistener_handler=null;
	protected _ItemListenerHandler _itemlistener_handler=null;
	protected _ChangeListenerHandler _changelistener_handler=null;
	protected _KeyListenerHandler _keylistener_handler=null;
	protected _MouseListenerHandler _mouselistener_handler=null;
	protected _WindowAdapterHandler _windowadapter_handler=null;
	protected _TableTransferHandler _tabletransfer_handler=null;
	protected _TreeModelListenerHandler _treemodellistener_handler=null;
	/*
	 * Definicion de la variable _data. Instancia de la clase Data.
	 * Se utiliza para manejar todo lo relacionado con la base de datos;
	 * de forma independiente de la logica.
	 * Es la clase que realmente utiliza el conector sql
	 */
	protected Data _data=null;
	
	/*
	 * Definicion de variable del Conector y sus metodos.
	 * El constructor es quien pasa este Conector como parametro a la clase Data.
	 */
	protected ConnectionHandler manager=null;
	
	private String iduser="";
	
	public String getIduser() {
		return iduser;
	}
	
	public void setIduser(String iduser) {
		this.iduser = iduser;
		
	}
	
	private String iddeposito="";
	
	public String getIdDeposito() {
		return iddeposito;
	}
	
	public void setIdDeposito(String iddeposito) {
		this.iddeposito = iddeposito;
		
	}
	public boolean isLookAndFeel() {
		return lookAndFeel;
	}

	public void setLookAndFeel(boolean lookAndFeel) {
		this.lookAndFeel = lookAndFeel;
	}

	public JFrame getFrame() {
		return _frame;
	}
	public boolean isShowOnStartup() {
		return showOnStartup;
	}
	public void setShowOnStartup(boolean showOnStartup) {
		this.showOnStartup = showOnStartup;
	}
	public LinkedList getParametros(){
		return this.parametros;
	}
	public Logic getLogic() {
		return _logic;
	}
	
	/**
	 * Metodo que asigna los distintos manejadores de eventos segun la clase del componente
	 * Por ejemplo si el componente es un JTextField le asigna el key_listener que es el mismo 
	 * para todas las aplicaciones. lo que cambia es el handler, es decir, el que comunica la
	 * ocurrencia de los eventos con la resolucion de los mismos. los handlers estan ubicados
	 * en la carpeta events de cada aplicacion. 
	 * @param componnent
	 */
	private void addComponent(Component componnent){
		//System.out.println("Adding Listener to componnent "+componnent.getClass());
		if (componnent instanceof JTextField){
			JTextField tx=(JTextField) componnent;
			//System.out.println("Adding Listener to textfield "+_key_listener.getClass());
			
			tx.addKeyListener(this._key_listener);
		}
		if (componnent instanceof JSlider){
			JSlider js=(JSlider) componnent;
			//System.out.println("Adding Listener to textfield "+_key_listener.getClass());
			
			js.addKeyListener(this._key_listener);
			js.addChangeListener(this._change_listener);
		}
		if (componnent instanceof JTabbedPane){
			JTabbedPane tabbed=(JTabbedPane) componnent;
			//System.out.println("Adding Listener to textfield "+_key_listener.getClass());
			this.addListener(tabbed);
			
		}
		if (componnent instanceof JXLayer){
			JXLayer layer=(JXLayer) componnent;
			this.addListener(layer);
		}
		
		if (componnent instanceof JTextArea){
			JTextArea tx=(JTextArea) componnent;
			//System.out.println("Adding Listener to textfield "+_key_listener.getClass());
			tx.addKeyListener(this._key_listener);
		}
		
		if (componnent instanceof JTree){
			//System.out.println("Adding Listener to tree "+componnent.getClass());
			JTree tree=(JTree) componnent;
			tree.addMouseListener(this._mouse_listener);
			tree.addKeyListener(this._key_listener);
			
			tree.addKeyListener(this._key_listener);
			DefaultTreeModel model=(DefaultTreeModel)tree.getModel();
			model.addTreeModelListener(this._treemodel_listener);
			
		}
		
		if (componnent instanceof JButton){
			JButton button=(JButton) componnent;
			button.addActionListener(this._action_listener);
			button.addKeyListener(this._key_listener);
			if (this.isLookAndFeel()){
				if (buttonlook){
					this.process(button);	
				}
			}
			//System.out.println("Adding Listener to "+button.getActionCommand());
		}
	
		
		if (componnent instanceof JComboBox){
			JComboBox combo=(JComboBox) componnent;
			combo.addItemListener(this._item_listener);
			combo.addKeyListener(this._key_listener);
			//System.out.println("Adding Listener to combobox "+combo.getName());
		}
		
		if (componnent instanceof JTable){
			JTable table=(JTable) componnent;
			table.addKeyListener(this._key_listener);
			table.addMouseListener(this._mouse_listener);
			
		}
		
		if (componnent instanceof JCheckBox){
			JCheckBox chk=(JCheckBox) componnent;
			chk.addItemListener(this._item_listener);
			chk.addKeyListener(this._key_listener);
			//System.out.println("Adding Listener to checkbox "+chk.getName());
		}
		
		if (componnent instanceof JRadioButton){
			JRadioButton chk=(JRadioButton) componnent;
			chk.addItemListener(this._item_listener);
			//System.out.println("Adding Listener to checkbox "+chk.getName());
		}
		if (componnent instanceof JPanel){
			JPanel panel=(JPanel) componnent;
			this.addListener(panel);
		}
		if (componnent instanceof JScrollPane){
			JScrollPane panel=(JScrollPane) componnent;
			this.addListener(panel);
		}
		if (componnent instanceof JSplitPane){
			JSplitPane panel=(JSplitPane) componnent;
			this.addListener(panel);
		}
	}
	
	
	public ConnectionHandler getSql() {
		return manager;
	}

	public Constructor getCaller() {
		return Caller;
	}

	public void setCaller(Constructor caller) {
		Caller = caller;
	}
/*
	public void setConnectionHandler(ConnectionHandler sql){
		this.manager=sql;
	}*/
	
	public ConnectionHandler getConnectionHandler(){
		return this.manager;
		
	}
	
	 
	
	/*
	 * Metodo para inicializar la variable _frame.
	 * Este metodo en una subclase llama al constructor de 
	 * un frame especifico de esa aplicacion.
	 * Ejemplo: 
	 * 
	 * 	aplicacion: cobranza
	 *  nombre del frame: Cobranza_frame
	 *  instruccion redefinida: _frame=new Cobranza_frame();
	 * 
	 *  nota: en el constructor de la aplicacion debe escribirse este metodo 
	 *  nuevamente para hacer un override!! 
	 */
	protected void initialize_frame(){
		
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			_frame=new JFrame();						
		}else{
			
			Runnable _execute=new Runnable(){
	        	  public void run() {
	        		  _frame=new JFrame();	  
	        	  }
	          };
	          
	          javax.swing.SwingUtilities.invokeLater(_execute);
		}
	}
	
	/*
	 * Metodo para inicializar la variable _data.
	 * Este metodo en una subclase llama al constructor de 
	 * un extension de la clase Data que corresponde a una nueva aplicacion.
	 * Ejemplo: 
	 * 
	 * 	aplicacion: cobranza
	 *  nombre de la extension: Cobranza_data
	 *  instruccion redefinida: _data=new Cobranza_data();
	 *  
	 *  nota: en el constructor de la aplicacion debe escribirse este metodo 
	 *  nuevamente para hacer un override!!
	 */
	protected void initialize_data(){
		_data=new Data();	
	}
	
	protected void initialize_child_list(){
		childs=new ArrayList<Constructor>();	
	}
	
	/*
	 * Metodo para inicializar la variable _logic.
	 * Este metodo en una subclase llama al constructor de 
	 * un extension de la clase Logic que corresponde a una nueva aplicacion.
	 * Ejemplo: 
	 * 
	 * 	aplicacion: cobranza
	 *  nombre de la extension: Cobranza_logic
	 *  instruccion redefinida: _logic=new Cobranza_logic();
	 *  
	 *  nota: en el constructor de la aplicacion debe escribirse este metodo 
	 *  nuevamente para hacer un override!!
	 */
	protected void initialize_logic(){
		_logic=new Logic();
	}
	
	/*
	 * Metodo utilizado para configurar comportamientos de algunos elementos.
	 * Por ejemplo el comportamiento default de un frame al hacer click en 
	 * la cruz para cerrarlo. para evitar que se cierre, se utiliza la instruccion 
	 * a continuacion. 
	 */
	protected void customSettings(){
		if (this.graphical)
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}
	/*
	 * Metodo para setear los atributos esenciales de una clase data.
	 * El conector es esencial! es la unica clase que lo utiliza!
	 */
	protected void setDataEssentialAttributes(){
		_data.setSql(manager);
	}
	/*
	 * Metodo para setar los atributos essenciales de una clase logic.
	 * la clase data es esencial porque es la forma en que la logica 
	 * se comunica con la base de datos.
	 * la clase frame es esencial porque la logica puede acceder a los
	 * distintos elementos graficos y obtener informacion para procesar;
	 * o setear estos elementos para mostrar el resultado de algun proceso
	 */
	protected void setLogicEssentialAttributes(){
		_logic.setData(_data);
		try {
			_logic.setFrame(_frame);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**=========================================================
	 * Inicio de handlers!
	 * Estos metodos son los que utiliza el constructor para crear la aplicacion
	 *  en una determinada secuencia; deben respetarse sus nombres para que el 
	 *  constructor al utilizar su metodo build() la aplicacion se cree correctamente!
	 *  Los overrides de estos metodos es la forma que se debe utilizar para
	 *  que la aplicacion a partir de un evento en particular ejecute un metodo 
	 *  particular en la logica. Ese es el fin de un handler.
	 ===========================================================*/
	
	/*
	 * Metodo para inicializar el handler que corresponde al actionlister.
	 * En una subclase de este listener, la instruccion cambia de la siguiente forma.
	 * 
	 * 	_actionlistener_handler=new ActionListenerHandler();
	 * 
	 * donde ActionListenerHandler es una extension de _ActionListenerHandler 
	 * y esta definida dentro del paquete events de la aplicacion en desarrollo.
	 */
	protected void initialize_actionlistener_handler(){
		_actionlistener_handler=new _ActionListenerHandler();
	}
	
	protected void initialize_droptargetlistener_handler(){
		_droptargetlistener_handler=new _DropTargetListenerHandler();
	}
	
	protected void initialize_dragsourcelistener_handler(){
		_dragsourcelistener_handler=new _DragSourceListenerHandler();
	}
	/*
	 * Metodo para inicializar el handler que corresponde al itemlister.
	 * En una subclase de este listener, la instruccion cambia de la siguiente forma.
	 * 
	 * 	_itemlistener_handler=new ItemListenerHandler();
	 * 
	 * donde ItemListenerHandler es una extension de _ItemListenerHandler 
	 * y esta definida dentro del paquete events de la aplicacion en desarrollo.
	 */
	protected void initialize_itemlistener_handler(){
		_itemlistener_handler=new _ItemListenerHandler();
	}
	
	protected void initialize_changelistener_handler(){
		_changelistener_handler=new _ChangeListenerHandler();
	}
	
	protected void initialize_keylistener_handler(){
		_keylistener_handler=new _KeyListenerHandler();
	}
	
	protected void initialize_mouselistener_handler(){
		_mouselistener_handler=new _MouseListenerHandler();
	}
	
	protected void initialize_windowadapter_handler(){
		_windowadapter_handler=new _WindowAdapterHandler();	
	}
	
	protected void initialize_tabletransfer_handler(){
		_tabletransfer_handler=new _TableTransferHandler();
	}
	
	protected void initialize_treemodellistener_handler(){
		_treemodellistener_handler=new _TreeModelListenerHandler();
	}
	/*
	 *Metodo general para la inicializacion de todos los handlers.
	 *El constructor va a ejecutar cada una de las instrucciones a continuacion.
	 *Si la aplicacion en su constructor extension de esta clase redefine uno 
	 *de los metodos que se llaman aqui. El contructor utilizara ese metodo 
	 *y no los definidos aqui. logrando el efecto override para personalizar 
	 *la aplicacion 
	 */
	protected void initialize_handlers(){
		this.initialize_actionlistener_handler();
		this.initialize_itemlistener_handler();
		this.initialize_changelistener_handler();
		this.initialize_keylistener_handler();
		this.initialize_mouselistener_handler();
		this.initialize_windowadapter_handler();
		this.initialize_tabletransfer_handler();
		this.initialize_treemodellistener_handler();
		this.initialize_droptargetlistener_handler();
		this.initialize_dragsourcelistener_handler();
	}
	
	/*
	 * Metodo que utiliza el constructor para asociar a cada Adapter
	 * o Listener la logica que debe utilizar; La logica es unica para una aplicacion!
	 * Este metodo no debe ser modificado ni hacerse un override!
	 */
	protected void assignLogictoHandlers(){
		if (_actionlistener_handler!=null)
			_actionlistener_handler.setLogic(_logic);
		if (_droptargetlistener_handler!=null)
			_droptargetlistener_handler.setLogic(_logic);
		if (_dragsourcelistener_handler!=null)
			_dragsourcelistener_handler.setLogic(_logic);
		if (_itemlistener_handler!=null)
			_itemlistener_handler.setLogic(_logic);
		if (_changelistener_handler!=null)
			_changelistener_handler.setLogic(_logic);
		if (_keylistener_handler!=null)
			_keylistener_handler.setLogic(_logic);
		if (_mouselistener_handler!=null)
			_mouselistener_handler.setLogic(_logic);
		if (_windowadapter_handler!=null)
			_windowadapter_handler.setLogic(_logic);
		if (_tabletransfer_handler!=null)
			_tabletransfer_handler.setLogic(_logic);
		if (_treemodellistener_handler!=null)
			_treemodellistener_handler.setLogic(_logic);
	}
	
	protected void assignConstructortoHandlers(){
		if (_actionlistener_handler!=null)
			_actionlistener_handler.setConstructor(this);
		if (_droptargetlistener_handler!=null)
			_droptargetlistener_handler.setConstructor(this);
		if (_dragsourcelistener_handler!=null)
			_dragsourcelistener_handler.setConstructor(this);
		if (_itemlistener_handler!=null)
			_itemlistener_handler.setConstructor(this);
		if (_keylistener_handler!=null)
			_keylistener_handler.setConstructor(this);
		if (_mouselistener_handler!=null)
			_mouselistener_handler.setConstructor(this);
		if (_windowadapter_handler!=null)
			_windowadapter_handler.setConstructor(this);
		if (_tabletransfer_handler!=null);
			//_tabletransfer_handler.setConstructor(this);
		if (_treemodellistener_handler!=null);
			//_treemodellistener_handler.setConstructor(this);
	}
	
	/*
	 * Metodo para construir la aplicacion completa
	 * Este metodo sigue una secuencia para crear la aplicacion 
	 * con sus listener, adapters y la logica de manera que funcione.
	 * Esa secuencia tiene un orden que se ve a continuacion.
	 * Este metodo no debe ser modificado ni hacerse un override! 
	 */
	
	public void _build(){
		if (this.graphical){
				initialize_frame();		
		}
		startConnectionHandler();
		initialize_data();
		initialize_logic();
		initialize_child_list();
		customSettings();
		setDataEssentialAttributes();
		setLogicEssentialAttributes();
		
		initialize_handlers();
		assignLogictoHandlers();
		this.initialize_ErrorHandler();
		this.assignConstructortoHandlers();
		assignEventHandlersToListeners();
		assignConstructorToLogic();
		if (this.graphical){
			initialize_components();
			addListeners();
			
			if (this.isLookAndFeel()){
			//setLookAndFeel();
			}
			
		}
		
	}
	
	public void build(Constructor Caller){
		if (Caller!=null){
			//System.out.println("Tratando de Obtener los Parametros de "+Caller.getClass());
			if (this.opacity==1f){
				this.opacity=Caller.getOpacity();
			}
			if (this.lookAndFeelTheme.compareTo("")==0){
				this.lookAndFeelTheme=Caller.getLookAndFeelTheme();
			}	
			this.reloadLookAndFeel();
		}
		
		this._build();
	}
	
	
	
	
	
	/*
	 * Metodo para asociar los handlers a los listeners o adapters!
	 * Este metodo no debe ser modificado ni hacerse un override!
	 */
	protected void assignEventHandlersToListeners(){
		this._window_adapter=new _WindowAdapter();
		this._window_adapter.setEventHandler(_windowadapter_handler);
		if (this.graphical)
		_frame.addWindowListener(this._window_adapter);
		
		this._key_listener=new _KeyListener();
		this._key_listener.setEventHandler(_keylistener_handler);
		
		this._action_listener=new _ActionListener();
		this._action_listener.setEventHandler(_actionlistener_handler);
		
		this._droptarget_listener=new _DropTargetListener();
		this._droptarget_listener.setEventHandler(_droptargetlistener_handler);
		
		this._dragsource_listener=new _DragSourceListener();
		this._dragsource_listener.setEventHandler(_dragsourcelistener_handler);
		
		this._item_listener=new _ItemListener();
		this._item_listener.setEventHandler(_itemlistener_handler);
		
		this._change_listener=new _ChangeListener();
		this._change_listener.setEventHandler(_changelistener_handler);
		
		this._mouse_listener=new _MouseListener();
		this._mouse_listener.setEventHandler(_mouselistener_handler);
		
		this._mousemotion_listener=new _MouseMotionListener();
		this._mousemotion_listener.setEventHandler(_mouselistener_handler);
		
		this._mousewheel_listener=new _MouseWheelListener();
		this._mousewheel_listener.setEventHandler(_mouselistener_handler);
		
		this._treemodel_listener=new _TreeModelListener();
		this._treemodel_listener.setEventHandler(_treemodellistener_handler);
		
		this._treeselection_listener=new _TreeSelectionListener();
		this._treeselection_listener.setEventHandler(_treemodellistener_handler);
	}
	
	
	public void initialize_components(){
	
	}
	
	public void setLookAndFeel(){
		
		try {
            
           UIManager.setLookAndFeel(this.lookAndFeelTheme);
			
			//SubstanceLookAndFeel.setSkin("org.jvnet.substance.theme.SubstanceAquaTheme");
            
          
           
          } catch (Exception e) {
        	  e.printStackTrace();
            //System.out.println("Substance Raven Graphite failed to initialize");
          }
          
          
          javax.swing.SwingUtilities.invokeLater(new Runnable(){
        	  public void run() {
        		  SwingUtilities.updateComponentTreeUI(_frame);
                  
              }

          });
          
	}
	
	protected void assignConstructorToLogic(){
		this._logic.setConstructor(this);
	}
	/*
	 * Metodo para inicializar una aplicacion.
	 * Puede hacer un override en la subclase y personalizar el inicio de la aplicacion
	 */
	
	
	public void initialize_parameters(){
		System.out.println("Iniciando Parametros en "+this.getClass());
		int i=0;
		boolean conector=false;
		while (i<this.getParametros().size()){
			Object[] p=(Object[]) parametros.get(i);
			if (this.processParameter(p)){
				conector=true;
			}
			i++;
		}
		if (this.graphical){
			try {
				_frame.setIconImage(new ImageIcon(getClass().getResource("/icons/chrome.png")).getImage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!conector){
			//System.out.println("No se Paso Ningun Connector como parametro. Se Inicia El Login");
			if (!(this instanceof aplicacion.herramientas.conexion.login.constructor._Constructor)
					& !(this instanceof aplicacion.herramientas.java.launcher.constructor._Constructor)){
					connect();
			}
		}
	}
	
	private boolean processParameter(Object[] p){
		boolean conector=false;
		
		String _id_parameter=(String)p[0];
		//System.out.println("Procesando Parametro "+_id_parameter);
		if (_id_parameter.compareTo(_parametros.connector)==0){
			System.out.println("Procesando Parametro de Conexion "+_id_parameter);
			this.processParameterConnectionHandler((ConnectionHandler) p[1]);
			conector=true;
		}
		if (_id_parameter.compareTo(_parametros.opacity)==0){
			this.processParameterOpacity((Float)p[1]);
		}
		if (_id_parameter.compareTo(_parametros.logger)==0){
			this.processParameterLogger((Logger)p[1]);
		}
		if (_id_parameter.compareTo(_parametros.LookAndFeel)==0){
			this.processParameterLookAndFeel((String)p[1]);
		}
		if (_id_parameter.compareTo(_parametros.iduser)==0){
			this.processParameterUser((String)p[1]);
		}
		if (_id_parameter.compareTo(_parametros.iddeposito)==0){
			this.processParameterDeposito((String)p[1]);
		}
		return conector;
	}
	
	private void processParameterOpacity(float opacity){
		this.opacity=opacity;
	}
	private void processParameterLogger(Logger logger){
		
	}
	private void processParameterLookAndFeel(String LookAndFeel){
		this.lookAndFeelTheme=LookAndFeel;
		//System.out.println("LookAndFeel>"+this.lookAndFeelTheme);
		this.reloadLookAndFeel();
	}
	private void processParameterUser(String iduser){
		this.iduser=iduser;
		if (iduser!=null){
			if (iduser.compareTo("")!=0){
				this.iduser=iduser;
				if (this.ErrorHandler!=null){
					ErrorHandler.setIduser(iduser);
				}
			}	
		}
		
	}
	
	private void processParameterDeposito(String iddeposito){
		//System.out.println("Procesando Parametro IDDEPOSITO:"+iddeposito);
		this.iddeposito=iddeposito;
		if (iddeposito!=null){
			if (iddeposito.compareTo("")!=0){
				this.iddeposito=iddeposito;
				if (this.ErrorHandler!=null){
					ErrorHandler.setIduser(iduser);
				}
			}	
		}
		
	}
	private void processParameterConnectionHandler(ConnectionHandler c){
		//manager=c.Clone();
		manager=c;
		manager.startConnections();
		manager.setConstructor(this);
		try {
			this._data.setSql(manager);	
		}catch(Exception e){
			
		}	
		if (this.graphical){
			_frame.setIconImage(new ImageIcon(getClass().getResource("/icons/chrome.png")).getImage());
		}
	}
	public void init(){
		this.initialize_parameters();
	}
	public void initOLD(){
		
		ConnectionHandler conector=null;
		Logger logger=null;
		float opacity=1f;
		
		try {
			conector=(ConnectionHandler)this.getValueParameter(_parametros.connector);	
		}catch(Exception e){
			
		}
		try {
			opacity=(Float)this.getValueParameter(_parametros.opacity);	
		}catch(Exception e){
			
		}
		this.opacity=opacity;
		try {
			logger=(Logger)this.getValueParameter(_parametros.logger);	
		}catch(Exception e){
			
		}
		String LookAndFeel="";
		try {
			LookAndFeel=(String)this.getValueParameter(_parametros.LookAndFeel);	
		}catch(Exception e){
			
		}
		String iduser="";
		 try {
			iduser=(String) this.getValueParameter(_parametros.iduser);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (iduser!=null){
			if (iduser.compareTo("")!=0){
				this.iduser=iduser;
				if (this.ErrorHandler!=null){
					ErrorHandler.setIduser(iduser);
				}
			}	
		}
		String iddeposito="";
		 try {
			iddeposito=(String) this.getValueParameter(_parametros.iddeposito);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (iddeposito!=null){
			if (iddeposito.compareTo("")!=0){
				this.iddeposito=iddeposito;
				
			}	
		}
		if (logger!=null){
			//this.logger=logger;
			//this.assignLoggerToHandlers();
		}else{
			//this.initialize_logger();
		}
		if (conector!=null){
			manager=conector;
			manager.startConnections();
			manager.setConstructor(this);
			try {
				this._data.setSql(manager);	
			}catch(Exception e){
				
			}
		if (this.graphical){
			_frame.setIconImage(new ImageIcon(getClass().getResource("/icons/chrome.png")).getImage());
		}
			
		}else {
			
			if (!(this instanceof aplicacion.herramientas.conexion.login.constructor._Constructor)
					& !(this instanceof aplicacion.herramientas.java.launcher.constructor._Constructor)){
		
					connect();
								
				
		
			}
			
		}
		this.lookAndFeelTheme=LookAndFeel;
		this.reloadLookAndFeel();
			 
	          
	          
		
	}
	
	public void connect(){
		if (!(this instanceof aplicacion.herramientas.conexion.login.constructor._Constructor)
				& !(this instanceof aplicacion.herramientas.java.launcher.constructor._Constructor)){
	
		aplicacion.herramientas.conexion.login.constructor._Constructor
		C=new aplicacion.herramientas.conexion.login.constructor._Constructor();
		C.setCaller(this);
		SQLite s=new SQLite(this);
		s.setId("Beta");
		s.setDatabase("lib/beta.sqlite");
		ConnectionHandler CH=new ConnectionHandler();
		CH.addConector(s);
		C.setParameter(_parametros.connector, CH);
		C.build(this);				
		C.init();
		}
	
	}
	public void print(){
		//System.out.println("Constructor>"+this.getClass());
		if (childs!=null){
			if (this.childs.size()>0){
				for (int i=0;i<childs.size();i++){
					childs.get(i).print();
				}
			}	
		}
		
	}
	
	public void removeChild(Constructor C){
		if (childs.contains(C)){
			childs.remove(C);
			//System.out.println("Quitando puente padre:"+this.getClass()+" hijo:"+C.getClass());
		}else{
			//System.out.println("No se pudo quitar puente padre:"+this.getClass()+" hijo:"+C.getClass());
		}
	}
	public void addChild(Constructor C){
		System.out.println("Creando puente padre:"+this.getClass()+" hijo:"+C.getClass());
		if (childs!=null){
			C.setCaller(this);
			this.childs.add(C);
			this.print();	
		}
		
	}
	public void resetConnectionHandler(ConnectionHandler CH){
		//System.out.println("Seteando Connection Handler en Constructor Principal! "+this.getClass());
		Object[][] results=CH.getDefaultConnector().getResults("Select * from b_users");
		if (results!=null){
			if (results.length>0){
				//System.out.println("Result Test [ok] Seteando Connection Handler en Constructor Principal! "+this.getClass());
				
				if (this.isMaster()){
					_reconecter.dispose();
					_reconecter=null;
					
					this.setConnectionHandler(CH);
				}		
			}
		}
		
	}
	
	public void setConnectionHandler(ConnectionHandler CH){
		
		this.manager=CH;
		Object[][] results=null;
		try {
			results = manager.getDefaultConnector().getResults("Select * from b_users");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (results!=null){
			if (results.length>0){
				//System.out.println("Results[ok]> Seteando Connection Handler en "+this.getClass()+" conectionHandler="+CH);
				
				
				if (this.getLogic()!=null){
					if (this.getLogic().getData()!=null){
						//System.out.println("Seteando Connection Handler a Data de "+this.getClass());
						this.getLogic().getData().setSql(this.manager);
					}
				}
					if (this.childs.size()>0){
						for (int i=0;i<childs.size();i++){
							childs.get(i).setConnectionHandler(this.getConnectionHandler());
						}
					}
			}
		}
			
		
		
		
	}
	public void reconnect(){
		System.out.println("Reconnect Command Constructor");
		this.reconnect(false);
	}
	
	private aplicacion.herramientas.conexion.login.constructor._Constructor _reconecter;
	public void reconnect(boolean force){
		if (!force){
			if (this.isMaster()){
				//System.out.println("Reconectar Master!! "+this.getClass());
				if (!(this instanceof aplicacion.herramientas.conexion.login.constructor._Constructor)
						& !(this instanceof aplicacion.herramientas.java.launcher.constructor._Constructor)){
			
				if (_reconecter!=null){
					_reconecter.getFrame().setVisible(true);
				}	
				else{
					if(getLogic().preguntar("Conexion", "Se Perdio la conexion. Intenta Reconexion?")){
						_reconecter=new aplicacion.herramientas.conexion.login.constructor._Constructor();
						_reconecter.setParameter(aplicacion.herramientas.conexion.login.interfaces._parametros.reconexion, true);
						_reconecter.setCaller(this);
						//System.out.println("Creando Login Frame: Caller?"+this);
						SQLite s=new SQLite(this);
						s.setId("Beta");
						s.setDatabase("lib/beta.sqlite");
						ConnectionHandler CH=new ConnectionHandler();
						CH.addConector(s);
						_reconecter.setParameter(_parametros.connector, CH);
						_reconecter.build(this);				
						_reconecter.init();	
					}
					
				}
						
				
				}	
			}else{
				//System.out.println("Buscando Reconectar Master!! "+this.getClass());
				if (this.getCaller()!=null){
					this.getCaller().reconnect();
				}
			}	
		}else{
			//System.out.println("Reconectar Master!! "+this.getClass());
			if (!(this instanceof aplicacion.herramientas.conexion.login.constructor._Constructor)
					& !(this instanceof aplicacion.herramientas.java.launcher.constructor._Constructor)){
		
			if (_reconecter!=null){
				_reconecter.getFrame().setVisible(true);
			}	
			else{
				if(getLogic().preguntar("Conexion", "Se Perdio la conexion. Intenta Reconexion?")){
					_reconecter=new aplicacion.herramientas.conexion.login.constructor._Constructor();
					_reconecter.setParameter(aplicacion.herramientas.conexion.login.interfaces._parametros.reconexion, true);
					_reconecter.setCaller(this);
					//System.out.println("Creando Login Frame: Caller?"+this);
					SQLite s=new SQLite(this);
					s.setId("Beta");
					s.setDatabase("lib/beta.sqlite");
					ConnectionHandler CH=new ConnectionHandler();
					CH.addConector(s);
					_reconecter.setParameter(_parametros.connector, CH);
					_reconecter.build(this);				
					_reconecter.init();	
				}
				
			}
					
			
			}
		}
		
		
	}
	public void reloadLookAndFeel(){
		
		try {
				//System.out.println(this.getClass()+">"+this.lookAndFeelTheme);
				if (lookAndFeelTheme.compareTo("")==0){
					
					lookAndFeelTheme=UIManager.getCrossPlatformLookAndFeelClassName();
					System.out.println("crossplataform l&f ??"+lookAndFeelTheme);
				}
					setLookAndFeelTheme(lookAndFeelTheme);
					if (javax.swing.SwingUtilities.isEventDispatchThread()){
						if (lookAndFeelTheme.compareTo("")!=0){
							UIManager.setLookAndFeel(lookAndFeelTheme);	
						}
												
					}else{
						final String _lookAndFeelTheme=lookAndFeelTheme;
				          javax.swing.SwingUtilities.invokeLater(new Runnable(){
				        	  public void run() {
				        		  try {
									if (_lookAndFeelTheme.compareTo("")!=0){
										UIManager.setLookAndFeel(_lookAndFeelTheme);	
									}
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InstantiationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (UnsupportedLookAndFeelException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				        		  
				                  
				              }

				          });
					}
			           	
				
				
			   
	          
	          } catch (Exception e) {
	        	  e.printStackTrace();
	        	  
	          }
	          
		          
		          
	          if (_frame!=null){
	          javax.swing.SwingUtilities.invokeLater(new Runnable(){
	        	  public void run() {
	        		  try {
						SwingUtilities.updateComponentTreeUI(_frame);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	              }
	          });
	          if (showOnStartup){
	        	  javax.swing.SwingUtilities.invokeLater(new Runnable() {
			  			@Override
			  			public void run() {
			  				if (opacity!=1f){
			  					com.sun.awt.AWTUtilities.setWindowOpacity(_frame, opacity);
			  					//System.out.println("Utiliza Transferencia "+opacity);
			  				}else{
			  					//System.out.println("No Utiliza Transferencia");
			  				}
			  				_frame.setVisible(true);
			  				
			  			}
			  			});
		          }  
	          }
	          
	}
	public void start(){
		
	}
	private void startConnectionHandler(){
		manager=new ConnectionHandler();
	}
	public void addConnector(Generic C){
		manager.addConector(C);
	}
	
	
	
	public void setVisible(boolean visible){
		this.visible=visible;
	}
	public _ActionListener getActionListener(){
		return this._action_listener;
	}
	public _DropTargetListener getDropTargetListener(){
		return this._droptarget_listener;
	}
	public _DragSourceListener getDragSourceListener(){
		return this._dragsource_listener;
	}
	public _KeyListener getKeyListener(){
		return this._key_listener;
	}
	
	public _ItemListener getItemListener(){
		return this._item_listener;
	}
	
	public _MouseMotionListener getMouseMotionListener(){
		return this._mousemotion_listener;
	}
	public _MouseWheelListener getMouseWheelListener(){
		return this._mousewheel_listener;
	}
	public _WindowAdapter getWindowListener(){
		return this._window_adapter;
	}
	public _TableTransferHandler getTableTransferHandler(){
		return this._tabletransfer_handler;
	}
	
	public _MouseListener getMouseListener(){
		return this._mouse_listener;
	}

	public _TreeModelListener getTreeModelListener(){
		return this._treemodel_listener;
	}
	
	public _TreeSelectionListener getTreeSelectionListener(){
		return this._treeselection_listener;
	}
	public Object getParameter(String id){
		Object aux=null;
		boolean found=false;
		int i=0;
		while (!found & i<parametros.size()){
			Object[] p=(Object[]) parametros.get(i);
			String idx=(String)p[0];
			if (idx.compareTo(id)==0){
				found=true;
			}
			if (!found){
				i++;
			}
		}
		if (found){
			Object[] p=(Object[]) parametros.get(i);
			aux=p[1];
		}	
	
		return aux;
	}
	
	public void setParameter(String id,Object value){
		Object[] param=new Object[2];
		param[0]=id;
		param[1]=value;
		boolean found=false;
		int i=0;
		if (parametros==null){
			parametros=new LinkedList();
		}
		while (!found & i<parametros.size()){
			Object[] p=(Object[]) parametros.get(i);
			String idx=(String)p[0];
			if (idx.compareTo(id)==0){
				found=true;
			}
			if (!found){
				i++;
			}
		}
		if (found){
			parametros.set(i, param);
		}else {
			parametros.add(param);	
		}
	}
	
	public Object getValueParameter(String id){
		Object aux="";
		boolean found=false;
		int i=0;
		if (parametros!=null){
			while (!found & i<parametros.size()){
				Object[] p=(Object[]) parametros.get(i);
				String idx=(String)p[0];
				if (idx.compareTo(id)==0){
					found=true;
					aux=p[1];
				}
				if (!found){
					i++;
				}
			}	
		}
		
		return aux;
	}
	
	/**
	 * Metodo principal para la asignacion de los listeners a cada componente dentro de el frame
	 * principal de la aplicacion. Este es el mas importante. Este examina los componentes del frame 
	 * y llama a otros metodos privados recursivamente para poder asignarle los listeners segun sea el caso
	 * Por ejemplo al encontrar un JLayeredPane. llama al addListener() con el objeto JLayeredPane 
	 * para que ademas asigne a los elementos dentro del layeredpane.
	 */
	private void addListeners(){
		////System.out.println("Adding Listeners to componnents of "+this._frame.getClass());
		if (this._frame!=null){
			try {
				
				for (int i=0;i<this._frame.getComponentCount();i++){
					//System.out.println("Component "+i+" >"+this._frame.getComponent(i).getClass()+" "+
					//this._frame.getComponent(i).getName());
					if (this._frame.getComponent(i) instanceof JRootPane){
						JRootPane C=(JRootPane) this._frame.getComponent(i);
						this.addListener(C);
					}
					
				}		
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	}
	private void addListener(JXLayer C){
		for (int i=0;i<C.getComponentCount();i++){
			Component _C=C.getComponent(i);
			if (_C instanceof JPanel){
				addListener((JPanel) C.getComponent(i));
			}
			if (_C instanceof JTabbedPane){
				addListener((JTabbedPane) C.getComponent(i));
			}
		}
	}
	private void addListener(JRootPane C){
		for (int i=0;i<C.getComponentCount();i++){
			Component _C=C.getComponent(i);
			if (_C instanceof JPanel){
				
				this.addListener((JPanel)C.getComponent(i));
			}
			if (_C instanceof JLayeredPane){
				
				this.addListener((JLayeredPane) C.getComponent(i));
			}
			
		}	
	}
	
	private void addListener(JSplitPane C){
		for (int i=0;i<C.getComponentCount();i++){
				this.addComponent(C.getComponent(i));	
				
					
			
			
				
		}
			
	}
	private void addListener(JPanel C){
		for (int i=0;i<C.getComponentCount();i++){
			if (C.getComponent(i) instanceof JPanel){
				addListener((JPanel)C.getComponent(i));
			}else {
				if (C.getComponent(i) instanceof JToolBar){
					this.addListener((JToolBar)C.getComponent(i));
				}else{
					this.addComponent(C.getComponent(i));	
				}
					
			}
			
				
			}
			
	}
	
	private void addListener(JTabbedPane C){
		for (int i=0;i<C.getComponentCount();i++){
			if (C.getComponent(i) instanceof JPanel){
				addListener((JPanel)C.getComponent(i));
			}else {
				if (C.getComponent(i) instanceof JToolBar){
					this.addListener((JToolBar)C.getComponent(i));
				}else{
					this.addComponent(C.getComponent(i));	
				}
					
			}
			
				
			}
			
	}
	
	private void addListener(JToolBar C){
		for (int i=0;i<C.getComponentCount();i++){
			if (C.getComponent(i) instanceof JPanel){
				addListener((JPanel)C.getComponent(i));
			}else {
				this.addComponent(C.getComponent(i));	
			}
			
				
			}
			
	}
	private void addListener(JScrollPane C){
		if (C.getViewport().getView() instanceof JTable){
			this.addComponent((JTable)C.getViewport().getView());
		}
		if (C.getViewport().getView() instanceof JTree){
			this.addComponent((JTree)C.getViewport().getView());
		}
		
	}
	
	/**
	 * Este metodo asigna los listeners a partir de un componente JLayeredPane
	 * Y llama a otros metodos recursivamente segun la clase del componente encontrado 
	 * @param C
	 */
	private void addListener(JLayeredPane C){
		for (int i=0;i<C.getComponentCount();i++){
			if (C.getComponent(i) instanceof JPanel){
				this.addListener((JPanel)C.getComponent(i));
			}
		}	
	}
	
	
	public void setGraphical(boolean graphical){
		this.graphical=graphical;
	}
	
	public boolean isGraphical(){
		return this.graphical;
	}
	public void dispose(){
		this.dispose(true);
	}
	/**
	 * libera la memoria
	 */
	public void dispose(boolean kill_childs){
		
		if (this._frame!=null){
			this._frame.setVisible(false);
			this._frame.dispose();
			
		}
		
		
		
		
		this._logic=null;
		this._data=null;
		this._action_listener=null;
		this._actionlistener_handler=null;
		this._item_listener=null;
		this._itemlistener_handler=null;
		this._key_listener=null;
		this._keylistener_handler=null;
		this._treemodel_listener=null;
		this._treemodellistener_handler=null;
		this._treeselection_listener=null;
		this._tabletransfer_handler=null;
		this._window_adapter=null;
		this._windowadapter_handler=null;
		this._mouse_listener=null;
		this._mouselistener_handler=null;
		this._mousemotion_listener=null;
		this._mousewheel_listener=null;
		
		
		this.iduser=null;
		this.iddeposito=null;
		
		/*
		if (kill_childs){
			if (childs!=null){
				for (int i=0;i<childs.size();i++){
					System.out.println("KILL <"+this.getClass()+"> CHILDS >"+i+" "+childs.get(i).getClass());
					childs.get(i).dispose();
				}
				childs=new ArrayList<Constructor>();
			}
			freeResources();
		}
		*/
		
		
	}
	
	public void freeResources(){
		try {
			Runtime runtime = Runtime.getRuntime ();
			System.out.println("Memory:"+runtime.freeMemory());
			runtime.gc();
			runtime.runFinalization();
			System.gc();
			System.runFinalization();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
	public Constructor(){
		parametros=new LinkedList();
	}
	
	public void process(JButton button){
		ImageIcon img=(ImageIcon) button.getIcon();
		BufferedImage bimg=this.toBufferedImage(img.getImage());
	    LookupOp lop = new LookupOp(this.contrastDecLUT(), null);
	    lop.filter(bimg, bimg);  
	    Toolkit.getDefaultToolkit().createImage(bimg.getSource());
  		button.setIcon(new ImageIcon(bimg));
	}
	
	public ShortLookupTable contrastDecLUT() {
	    short brighten[] = new short[256];
	    for (int i = 0; i < 256; i++) {
	      short pixelValue = (short) (i / 90);
	      if (pixelValue > 255)
	        pixelValue = 255;
	      else if (pixelValue < 0)
	        pixelValue = 0;
	      brighten[i] = pixelValue;
	    }
	    ShortLookupTable lookupTable = new ShortLookupTable(0, brighten);
	    return lookupTable;
	  }
	
public BufferedImage toBufferedImage(Image image) {
		
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
    
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
    
        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent Pixels
        //boolean hasAlpha = hasAlpha(image);
    
        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.TRANSLUCENT;
          /*  if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }*/
    
            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
    
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            /*if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }*/
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
    
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
    
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
    
        return bimage;
    }

public void assignLoggerToHandlers(){
	
	int level=2;
	try {
		String log_level=_data.getParametroSqlite("log_level")[0][1].toString();
		level=new Integer(log_level);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this._actionlistener_handler.setLogLevel(level);
	this._changelistener_handler.setLogLevel(level);
	this._itemlistener_handler.setLogLevel(level);
	this._keylistener_handler.setLogLevel(level);
	this._mouselistener_handler.setLogLevel(level);
	this._windowadapter_handler.setLogLevel(level);
	//faltan mas handlers!
}

public List<Constructor> getChilds(){
	return this.childs;
}
public void initialize_logger(){
	
	this.assignLoggerToHandlers();

}
}

