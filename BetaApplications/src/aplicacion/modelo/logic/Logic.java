package aplicacion.modelo.logic;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

import org.apache.poi.hssf.record.formula.functions.Char;
import org.jdesktop.jxlayer.JXLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.io.File;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Toolkit;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.xml.*;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import aplicacion.modelo.constructor.Constructor;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.interfaces.ILogic;
import aplicacion.sistema.autorizar.logic.CryptUtil;
import aplicacion.ventas.pedido.interfaces._Interface;

import java.awt.Dimension;
/**
 * Clase modelo de la logica que implementa la interfaz {@link}
 * 
 * @author Agustin Wisky
 * @since 01-08-2008
 * @version 1
 */
public class Logic implements ILogic {
	protected double memory_limit=8;
	protected double memory_warning=15;
	protected Data _data = null;
	protected JFrame _frame = null;
	protected Constructor _constructor = null;
	protected List<Logic_Extension> logic_extensions = null;
	protected Element _messages = null;
	protected String iidaplicacions = "";
	protected int _debug= 0;
	protected String LINE_SEPARATOR=System.getProperty("line.separator");
	public int is_debug(){
		return _debug;
	}
	
	public boolean isLinux(){
		return this.getOS().toLowerCase().contains("linux");
	}
	
	public String getOS(){
		String os=System.getProperty("os.name");
		return os;
	}

	public void set_debug(int _debug){
		this._debug = _debug;
	}

	/**
	 * Constructor de la clase Logic
	 */
	public Logic(){
		logic_extensions = new ArrayList<Logic_Extension>();
		this._read_messages();
		try {
			iidaplicacions = (String) this._constructor.getValueParameter(_parametros.iduser);
		}
		catch (Exception e){

		}
	}

	public String getUser(){
		return this.iidaplicacions;
	}
	
	public double getTotalMemory(){
		double max=0;
	
		try {
			int mb = 1024*1024;
			Runtime runtime = Runtime.getRuntime ();
			max=runtime.maxMemory()/mb;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return max;
	}
	
	public String requestPassword(String titulo){
		final JPasswordField jpf = new JPasswordField();
		JOptionPane jop = new JOptionPane(jpf,
		  JOptionPane.QUESTION_MESSAGE,
		  JOptionPane.OK_CANCEL_OPTION);
		jop.setFont(new Font("Dialog", Font.PLAIN, 10));
		JDialog dialog = jop.createDialog(titulo);
		dialog.setAlwaysOnTop(true);
		jop.requestFocusInWindow();
		dialog.addComponentListener(new ComponentAdapter(){
		  @Override
		  public void componentShown(ComponentEvent e){
		    SwingUtilities.invokeLater(new Runnable(){
		      @Override
		      public void run(){
		        jpf.requestFocusInWindow();
		      }
		    });
		  }
		});
		dialog.setVisible(true);
		int result=-1;
		try {
			result = (Integer)jop.getValue();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		}
		dialog.dispose();
		char[] password = null;
		
		if(result == JOptionPane.OK_OPTION){
		  password = jpf.getPassword();
			}
		String tmp="";
		if(password!=null){
			try{
				tmp=new String(password);
				}
			catch (Exception e1){
				e1.printStackTrace();
			}	
		}
		
		return tmp;
	}
	
	public String getValidacion(String password){
		String iduser="";
		String q="select iduser from b_users where pass like '"+password+"' and superusuario=1 ";
		Object[][] results=this._data.getResults(q);
		
		if (results!=null){
			if (results.length>0){
				iduser=(String) results[0][0];
			}
		}
		return iduser;
	}
	
	
	
	public double getFreeMemory(){
		double free=0;
		int mb = 1024*1024;
	
		try {
			Runtime runtime = Runtime.getRuntime ();
			free=runtime.freeMemory()/runtime.maxMemory();
			double max=runtime.maxMemory()/mb;
			double avaible=runtime.freeMemory()/mb;
			double total=runtime.totalMemory()/mb;
			//debug("Memory free:"+avaible+" (MB)");
			//debug("Memory Maximum:"+max+" (MB)");
			//debug("Memory Total:"+total+" (MB)");
			free=((max-(total-avaible))/max)*100;
			//debug("Memory free(%):"+free);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return free;
	}
	
	public double getFreeMemoryMB(){
		double free=0;
		int mb = 1024*1024;
	
		try {
			Runtime runtime = Runtime.getRuntime ();
			free=runtime.freeMemory()/runtime.maxMemory();
			double max=runtime.maxMemory()/mb;
			double avaible=runtime.freeMemory()/mb;
			double total=runtime.totalMemory()/mb;
			//debug("Memory free:"+avaible+" (MB)");
			//debug("Memory Maximum:"+max+" (MB)");
			//debug("Memory Total:"+total+" (MB)");
			free=(max-(total-avaible));
			//debug("Memory free(%):"+free);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return free;
	}
	public String getCodigoDeAutorizacion(String clave){
		CryptUtil crypter=new CryptUtil();
		String solution=crypter.getSolution(clave);
		return solution;
	}
	
	public boolean pedir_autorizacion(){
		String mensaje="Ingrese La clave de autorizacion para la secuencia:";
		boolean autorizado=false;
		autorizado=this.pedir_autorizacion(mensaje);
		return autorizado;
	}
	
	/**
	 * Encripta un texto dado un Password dentro de la compilacion del nucleo
	 * Estandarizado para toda la aplicacion
	 * @param texto
	 * @return
	 */
	public String getEncrypted(String texto){
		CryptUtil crypter=new CryptUtil();
		String encriptado="";
		try {
			encriptado=crypter.getEncriptado(texto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encriptado;
	}
	
	/**
	 * Desncripta un texto encriptado dado un Password dentro de la compilacion del nucleo
	 * Estandarizado para toda la aplicacion
	 * @param texto_encriptado
	 * @return
	 */
	public String getDecrypted(String texto_encriptado){
		CryptUtil crypter=new CryptUtil();
		String desencriptado="";
		try {
			desencriptado=crypter.getDesencryptado(texto_encriptado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return desencriptado;
	}
	
	public boolean pedir_autorizacion(String mensaje){
		boolean autorizado=false;
		CryptUtil crypter=new CryptUtil();
		  String generated=crypter.getRandomSecuence(6);
		  String message = mensaje + generated;
		  String input = JOptionPane.showInputDialog(message);
	
			if (input!=null){
				autorizado=crypter.check(generated, input);
				if (!autorizado){
					error("La Clave de Autorizacion no es Correcta para "+generated);
				}
			}

		return autorizado;
	}
	
	public String getRandomSecuence(int digitos){
		Random randomGenerator = new Random();
		String secuence="";
		String _str = "";
		
		for (int idx = 1; idx <= digitos; ++idx){
			int randomInt = 32+randomGenerator.nextInt(32);
			_str += (char) randomInt;
			secuence+=_str;
		}
		return secuence;
	}
	
	public int getRandomNumber(int digitos){
		Random randomGenerator = new Random();
		String _str = "";
		
		for (int idx = 1; idx <= digitos; ++idx){
			int randomInt = randomGenerator.nextInt(10);
			_str += "" + randomInt;
			}
		
		int number = new Integer(_str);
		return number;
	}

	public String ingresar(String text){
		String input = JOptionPane.showInputDialog(text);
		return input;
	}
	
	public String ingresarSwing(String text){
		final String[] input=new String[1];
		final String _text=text;
		Runnable _execute=new Runnable(){
			public void run(){
				input[0] = JOptionPane.showInputDialog(_text);		
			}
		};
		this.invokeAndWait(_execute);
		return input[0];
	}
	
	public boolean confirmar(String mensaje, int digitos){
		int generated = this.getRandomNumber(digitos);
		String message = mensaje + generated;
		String input = JOptionPane.showInputDialog(message);
		
		if (input!=null){
			boolean ok=(input.compareTo("" + generated) == 0);
			if (!ok){
				error("La Confirmacion Ingresada no es Correcta "+generated+"<>"+input);
				}
			
			return ok;	
			}
		else{
			return false;
			}
		
	}

	public void clean(){
		
	}

	public String getMessage(String id, String[] params){
		String q = this.getMessage(id);
		int parameters = 0;
		int idx = 0;
		String aux = q;
		int lon= q.length();
		//*******************
		while(idx < lon && idx >= 0){
			idx = aux.indexOf("?");

			if (idx >= 0){
				aux = aux.substring(idx + 1, aux.length());
				parameters++;
				}
			//debug("params> (" + parameters + ")> " + aux);
			}
		if (parameters == params.length){

			for(int i = 0; i < parameters; i++){
				int idz = q.indexOf("?");
				q = q.substring(0, idz) + params[i] + q.substring(idz + 1);
				}
			}
		else{
			//debug("Data> Error definicion de parametros "+ parameters + "<>" + params.length);
		}

		return q;
	}
	
	/**
	 * Metodo para imprimir un debug del sistema si esta seteado el debug en el usuario
	 * @param linea
	 */
	public void debug(String linea){
		
	}
	public String getMessage(String idmessage){
		String msg = "";
		List<Element> _elements = _messages.getElements();
		boolean found = false;
		int i = 0,lon =_elements.size();
		
		while (i < lon && !found){
			Element e = _elements.get(i);
			Atributo a = e.getAtributo("id");
			//debug("id>" + a.getValor());
			
			if(a.getValor().compareTo(idmessage) == 0){
				a = e.getAtributo("text");
				//debug("text>" + a.getValor());
				msg = a.getValor();
				found = true;
				}
			i++;
			}

		return msg;
	}

	public Constructor getConstructor(){
		return this._constructor;
	}

	public void setData(Data _data){
		this._data = _data;
		//debug("Set Data?" + _data);
	}

	public Data getData(){
		return this._data;
	}

	public void setFrame(JFrame _frame){
		this._frame = _frame;
	}

	public JFrame getFrame(){
		return this._frame;
	}

	public void exit_command(){
		this.getConstructor().dispose(false);
	}
	
	public void exit(){
		if (preguntar("Confirmar", "Cierra Aplicacion?")){
			exit_command();
			}
	}
	
	public void _exitSwing(){
		if (_preguntarSwing("Confirmar", "Cierra Aplicacion?")) {
			exit_command();
		}
	}
	public boolean preguntar(JFrame frame,String titulo, String pregunta){
		boolean b=false;
		
		if(javax.swing.SwingUtilities.isEventDispatchThread()){
			b=this._preguntar(frame,titulo, pregunta);
			}
		else{
			b=this._preguntarSwing(frame,titulo, pregunta);
			}
		return b;
	}
	
	public boolean preguntar(String titulo, String pregunta){
		boolean b=this.preguntar(_frame, titulo, pregunta);
		return b;
	}
	
	private boolean _preguntar(String titulo, String pregunta){
		return this._preguntar(_frame, titulo, pregunta);
	}
	
	private boolean _preguntarSwing(String titulo, String pregunta){
		return this._preguntarSwing(_frame, titulo, pregunta);
	}
	
	private boolean _preguntar(JFrame frame,String titulo, String pregunta){
		Boolean[] Answer=new Boolean[1];
		
    	int n = JOptionPane.showConfirmDialog(frame, pregunta, titulo,JOptionPane.YES_NO_OPTION);
		Answer[0] = (n == JOptionPane.YES_OPTION);
		return Answer[0];
	}
	
	private boolean _preguntarSwing(JFrame frame,String titulo, String pregunta){
		final Boolean[] Answer=new Boolean[1];
		final String _titulo=titulo;
		final String _pregunta=pregunta;
		final JFrame _frame=frame;
		Runnable showModalDialog = new Runnable(){
	        public void run(){
	        	int n = JOptionPane.showConfirmDialog(_frame, _pregunta, _titulo,
	    				JOptionPane.YES_NO_OPTION);
	    		Answer[0] = (n == JOptionPane.YES_OPTION);
	        }
	    };
	    try {
			javax.swing.SwingUtilities.invokeAndWait(showModalDialog);
			}
	    catch(InterruptedException e){
			e.printStackTrace();
			}
	    catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return Answer[0];
	}
	
	

	/*
	public int preguntarInvoker(String titulo,String pregunta,String[] options,String valorpordefecto){
		 final int[] answer = new int[1];
		 final String _titulo=titulo;
		 final String _pregunta=pregunta;
		 final String[] _options=options;
		 final String _valor=valorpordefecto;
		 
         try {
			SwingUtilities.invokeAndWait(new Runnable() {
			   public void run() {
			     answer[0] = JOptionPane.showOptionDialog(_frame,
						_pregunta,
						_titulo,
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						_options,
						_valor);
			   }
			 });
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return answer[0];
	}
	*/
	
	public void invokeAndWait(Runnable _execute){
		
		try {
			javax.swing.SwingUtilities.invokeAndWait(_execute);
			}
		catch (InterruptedException e){
			e.printStackTrace();
			}
		catch(InvocationTargetException e){
			e.printStackTrace();
		}
	}
	
	public void invokeLater(Runnable _execute){
			javax.swing.SwingUtilities.invokeLater(_execute);
		
	}
	
	public int preguntar(String titulo,String pregunta,String[] options,String valorpordefecto){
		int n=0;
		
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			n=this._preguntar(titulo, pregunta, options, valorpordefecto);
			}
		else{
			n=this._preguntarSwing(titulo, pregunta, options, valorpordefecto);
		}
		return n;
	}
		
	private int _preguntarSwing(String titulo,String pregunta,String[] options,String valorpordefecto){
		final int[] Answer=new int[1];
		final String _titulo=titulo;
		final String _pregunta=pregunta;
		final String[] _options=options;
		final String _valorpordefecto=valorpordefecto;
		Runnable showModalDialog = new Runnable() {
	        public void run() {
	        	Answer[0]=JOptionPane.showOptionDialog(_frame,
						_pregunta,
						_titulo,
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						_options,
						_valorpordefecto); 
	        }
	    };
	    try {
			javax.swing.SwingUtilities.invokeAndWait(showModalDialog);
			}
	    catch (Exception e) {
			//debug("HASH CODE"+e.hashCode());
		}
		return Answer[0];
	}
	
	public int _preguntar(String titulo,String pregunta,String[] options,String valorpordefecto){
		 int[] answer = new int[1];
   	     answer[0] = JOptionPane.showOptionDialog(_frame,
						pregunta,
						titulo,
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						valorpordefecto);
		return answer[0];
	}
	
	
	public boolean preguntar(String titulo, String pregunta, ImageIcon icon) {
		boolean si = false;
		int n = JOptionPane.showConfirmDialog(_frame, pregunta, titulo,
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				icon);
		si = (n == JOptionPane.YES_OPTION);
		return si;
	}

	public void addExtension(Logic_Extension logic) {
		if (this.logic_extensions != null) {
			if (_constructor != null)
				logic.setLogic(this);
			this.logic_extensions.add(logic);
		}
	}

	public Logic_Extension getExtension(String classname) {
		
		Logic_Extension logic = null;
		boolean found = false;
		Logic_Extension tmp = null;
		int lon;
		if (this.logic_extensions != null) {
			int i = 0;
			lon= this.logic_extensions.size(); 
			
			while (i < lon && !found) {
				tmp = (Logic_Extension) logic_extensions.get(i);
				found = (tmp.getClass().getSimpleName().compareTo(classname) == 0);
				if (!found)
					i++;
				}
			}
		if (found) {
			return tmp;
			}
		else {
			return null;
		}

	}

	public void setConstructor(Constructor constructor) {
		this._constructor = constructor;
		int lon=this.logic_extensions.size();
		if (this.logic_extensions != null) {
			if (lon > 0) {
				for (int i = 0; i < lon; i++) {
					Logic_Extension logic = (Logic_Extension) logic_extensions.get(i);
					logic.setLogic(this);
				}
			}
		}
	}

	public void error(String titulo, String mensaje) {
		JFrame fx = new JFrame("Aviso");
		if (_frame != null) {
			fx = _frame;
		}
		JOptionPane.showMessageDialog(fx, mensaje, titulo,
				JOptionPane.WARNING_MESSAGE);
		if (_frame == null) {
			fx.dispose();
		}
	}

	public void error(String mensaje) {
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			this._error(mensaje);
			}
		else{
			this.errorSwing(mensaje);
			}
	}
	
	public void _error(String mensaje) {
		JFrame fx = new JFrame("Error");
	
		if (_frame != null) {
			fx = _frame;
			}
		JOptionPane pane=new JOptionPane();
		pane.setFont(new Font("Dialog", Font.PLAIN, 10));
		pane.showMessageDialog(fx, mensaje, "Error en " + fx.getTitle(),
				JOptionPane.WARNING_MESSAGE);
		if (_frame == null) {
			fx.dispose();
			}
	}
	
	public String seleccion(String titulo,String mensaje,String[] options){
		String selected="";
		//debug("seleccion!!!");
		String s = (String)JOptionPane.showInputDialog(
		                    _frame,
		                    mensaje,
		                    titulo,
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    options,
		                    options[0]);

		
		if ((s != null) && (s.length() > 0)) {
		    selected=s;
		}
		return selected;
	}
	
	/**
	 * Metodo basico para mostrar un aviso al usuario
	 */
	public void aviso(String mensaje){
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			this._aviso(mensaje);
			}
		else{
			this._avisoSwing(mensaje);
		}
	}
	
	public void _aviso(String mensaje) {
		this.aviso("Aviso", mensaje);
	}

	private void _avisoSwing(String aviso){
		final String _aviso=aviso; 
		Runnable _execute=new Runnable() {
			   public void run() {
				   aviso(_aviso);		   
			   }
		};
		this.invokeAndWait(_execute);
	}
	
	private void errorSwing(String error){
		final String _error=error; 
		Runnable _execute=new Runnable() {
			   public void run() {
				   error(_error);		   
			   }
		};
		this.invokeAndWait(_execute);
	}
	
	public void aviso(String titulo, String mensaje, ImageIcon icon) {
		JFrame fx = new JFrame("Aviso");

		if (_frame != null) {
			fx = _frame;
			}
		JOptionPane.showMessageDialog(fx, mensaje, titulo,
				JOptionPane.INFORMATION_MESSAGE, icon);
		if (_frame == null) {
			fx.dispose();
			}
	}

	/*public void avisoInvoker(String titulo,String mensaje){
		final String msg=mensaje;
		final String _titulo=titulo;
		Runnable showModalDialog = new Runnable() {
	        public void run() {
	        	 _aviso(_titulo,msg);
	        }
	    };
	    try {
			javax.swing.SwingUtilities.invokeAndWait(showModalDialog);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void aviso(String titulo,String mensaje) {
		this._aviso(titulo, mensaje);
	}
	
	private void _avisoSwing(JFrame frame,String titulo,String mensaje) {
		final String _titulo=titulo;
		final String _mensaje=mensaje;
		final JFrame _frame=frame;
		Runnable _execute=new Runnable() {
			   public void run() {
				   _aviso(_frame, _titulo, _mensaje);		   
			   }
		};
		this.invokeAndWait(_execute);
	}
	private void _aviso(JFrame frame,String titulo,String mensaje) {
		if (frame==null){
			frame = new JFrame(titulo);
		}
		centrar_frame(frame);
		JOptionPane.showMessageDialog(frame, mensaje, titulo,
				JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	public void aviso(JFrame frame,String titulo,String mensaje) {
		
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			this._aviso(frame,titulo,mensaje);
			}
		else{
			this._avisoSwing(frame,titulo,mensaje);
		}
	}
	private void _aviso(String titulo,String mensaje) {
		JFrame fx = new JFrame(titulo);
	
		if (_frame != null) {
			fx = _frame;
			}
		
		JOptionPane pane=new JOptionPane();
		pane.setFont(new Font("Dialog", Font.PLAIN, 10));
		pane.showMessageDialog(fx, mensaje, titulo,
				JOptionPane.INFORMATION_MESSAGE);
		if (_frame == null) {
			fx.dispose();
			}
	}

	public void _read_messages() {
		XML xml = new XML();
		Object _content = null;
		URL resourceURL = null;

		try {
			String _package = this.getClass().getName();
			int lastDot = _package.lastIndexOf('.');
			if (lastDot == -1) {
				}
			else {
				_package = _package.substring(0, lastDot);
				_package = _package.replace(".", "/");
				resourceURL = this.getClass().getClassLoader().getResource(
						_package + "/xml/messages.xml");
				//debug(_package + "/xml/messages.xml");
			}
			xml.setConfigFile(resourceURL.getPath());
			xml.readAll();
			_messages = xml.getRoot();
		}
		catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void saveToWorkspace() {
		//debug("Saving To Workspace " + this._frame.getClass());
		if (this._frame != null) {
			try {
				int lon= this._frame.getComponentCount();
				for (int i = 0; i < lon ; i++) {
					// debug("Component "+i+" >"+this._frame.getComponent(i).getClass()+" "+
					// this._frame.getComponent(i).getName());
					if (this._frame.getComponent(i) instanceof JRootPane) {
						JRootPane C = (JRootPane) this._frame.getComponent(i);
						this.saveComponent(C);
					}

				}
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * Metodo que forma parte del kit de herramientas para guardar el workspace
	 * en la base No utilizar Lo mantengo por si retomo ese camino :-)
	 * 
	 * @param C
	 * @param C
	 */
	private void saveComponent(JRootPane C) {
		int lon=C.getComponentCount();
		for (int i = 0; i < lon; i++) {
			Component _C = C.getComponent(i);

			if (_C instanceof JPanel) {
				this.saveComponent((JPanel) C.getComponent(i));
				}
			if (_C instanceof JLayeredPane) {
				this.saveComponent((JLayeredPane) C.getComponent(i));
				}
			}
	}

	/**
	 * Metodo que forma parte del kit de herramientas para guardar el workspace
	 * en la base No utilizar Lo mantengo por si retomo ese camino :-)
	 * 
	 * @param C
	 */
	private void saveComponent(JPanel C) {
		int lon=C.getComponentCount();

		for (int i = 0; i < lon; i++) {
			if (C.getComponent(i) instanceof JTextField) {
				this.saveComponent((JTextField) C.getComponent(i));
				}
			if (C.getComponent(i) instanceof JPanel) {
				this.saveComponent((JPanel) C.getComponent(i));
				}
			if (C.getComponent(i) instanceof JScrollPane) {
				this.saveComponent((JScrollPane) C.getComponent(i));
				}
			}
	}

	/**
	 * Metodo que forma parte del kit de herramientas para guardar el workspace
	 * en la base No utilizar Lo mantengo por si retomo ese camino :-)
	 * 
	 * @param C
	 */
	private void saveComponent(JScrollPane C) {
		if (C.getViewport().getView() instanceof JTable) {
			this.saveComponent((JTable) C.getViewport().getView());
		}

	}

	/**
	 * Metodo que forma parte del kit de herramientas para guardar el workspace
	 * en la base No utilizar Lo mantengo por si retomo ese camino :-)
	 * 
	 * @param C
	 */
	private void saveComponent(JTextField C) {

		JTextField tx = (JTextField) C;
		String _name = tx.getName();
		String _value = tx.getText();

		if (_value != null) {
			//debug("textfield " + _name + "='" + _value + "'");
			}
	}

	/**
	 * Metodo que forma parte del kit de herramientas para guardar el workspace
	 * en la base No utilizar Lo mantengo por si retomo ese camino :-)
	 * 
	 * @param C
	 */
	private void saveComponent(JTable C) {
		JTable table = (JTable) C;
		String _name = table.getName();
		//debug("table " + _name + " rows=" + table.getRowCount());
	}

	/**
	 * Metodo que forma parte del kit de herramientas para guardar el workspace
	 * en la base No utilizar Lo mantengo por si retomo ese camino :-)
	 * 
	 * @param C
	 */
	private void saveComponent(JLayeredPane C) {
		int lon=C.getComponentCount();
		for (int i = 0; i < lon; i++) {
			if (C.getComponent(i) instanceof JPanel) {
				this.saveComponent((JPanel) C.getComponent(i));
				}
			}
	}

	public void maximizar(JFrame frame){
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        Double x=screenSize.getWidth();
        Double y=(screenSize.getHeight()-60);
        frame.setSize(x.intValue(),y.intValue());
        frame.setMinimumSize(new Dimension(x.intValue(),y.intValue()));
        frame.setExtendedState(frame.getExtendedState() | frame.MAXIMIZED_BOTH);
        
     }

	public void maximizar(){
		this.maximizar(_frame);
        
     }	
	public void maximize(){
		_frame.setExtendedState(_frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
	}
	
	protected void centrar_frame(JFrame frame){
		 Dimension d=frame.getSize();
		 Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
		 double w=s.getWidth()*0.5;
		 double h=s.getHeight()*0.5;
		 double wf=d.getWidth()*0.5;
		 double hf=d.getHeight()*0.5;
		 int x=new Double(w-wf).intValue();
		 int y=new Double(h-hf).intValue();
		 frame.setLocation(x, y);
	}
	
	public void centrar(){
		 this.centrar_frame(_frame);
		 this._frame.setVisible(true);
	}

	public void cancelar(){
		if (this.preguntar("Confirmar", "cancela?")){
			clean();
			}
	}
	
	public void displayError(String titulo,String mensaje,String detalle,Exception e){
		String xml=this.saveToXML();
		this.getConstructor().getErrorHandler().displayError(titulo, mensaje, detalle, e,xml);
	}
	
	public void displayError(String titulo,String mensaje,String detalle,java.lang.Error e){
		String xml=this.saveToXML();
		this.getConstructor().getErrorHandler().displayError(titulo, mensaje, detalle, e,xml);
	}
		
	/**
	 * Seccion XML////////////////////////////////////////////////
	 */
	
	private Element parseToXML(Component C){
		Element e=new Element("Component");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
	
		if (C instanceof JTextField){
			e=parseToXML((JTextField)C);
			}
		if (C instanceof JTextArea){
			e=parseToXML((JTextArea)C);
			}
		if (C instanceof JTree){
			//e=parseToXML((JTree)C);
			}
		if (C instanceof JButton){
			e=parseToXML((JButton)C);
			}
		if (C instanceof JComboBox){
			e=parseToXML((JComboBox)C);
			}
		if (C instanceof JTable){
			e=parseToXML((JTable)C);
			}
		if (C instanceof JCheckBox){
			e=parseToXML((JCheckBox)C);
			}
		if (C instanceof JXLayer){
			e=parseToXML((JXLayer)C);
			}
		return e;
	}
	
	private Element parseToXML(JSplitPane C){
		Element e=new Element("JSplitPane");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		int lon=C.getComponentCount();
		
		for (int i=0;i< lon;i++){
			if (C.getComponent(i) instanceof JPanel){
				Element ex=parseToXML((JPanel)C.getComponent(i));
				e.addElement(ex);
				}
			if (C.getComponent(i) instanceof JScrollPane){
				Element ex=parseToXML((JScrollPane)C.getComponent(i));
				e.addElement(ex);
				}
			}
		return e;
	}
	
	/**
	 * permite enviar informacion de algun error o comentario por mail
	 */
	public void sendInfo(){
		String titulo="Envio de Error/Bug/Informacion a Sistemas";
		String asunto="Error en Beta";
		this.sendInfo(titulo, asunto);
	}
	
	public void sendInfo(String titulo,String asunto){
		
		String xml=this.saveToXML();
		aplicacion.sistema.error.constructor._Constructor C=new aplicacion.sistema.error.constructor._Constructor();
		ConnectionHandler handler=new ConnectionHandler();
		C.setParameter(_parametros.connector, handler);
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		C.setShowOnStartup(false);
		C.build(this.getConstructor());
		C.init();
		aplicacion.sistema.error.logic._Logic logic=(aplicacion.sistema.error.logic._Logic)C.getLogic();
		logic.createScreenImage();
		logic.setXML(xml);
		logic.centrar();
		logic.setAsunto(asunto);
		logic.setError(titulo);
		logic.focus();
	}
	
	private Element parseToXML(JPanel C){
		Element e=new Element("JPanel");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		int lon=C.getComponentCount();
		
		for (int i=0;i< lon;i++){
			if (C.getComponent(i) instanceof JPanel){
				Element ex=parseToXML((JPanel)C.getComponent(i));
				e.addElement(ex);
				}
			else {
				if (C.getComponent(i) instanceof JToolBar){
					Element ex=parseToXML((JToolBar)C.getComponent(i));
					e.addElement(ex);
					}
				else{
					if (C.getComponent(i) instanceof JScrollPane){
						Element ex=parseToXML((JScrollPane)C.getComponent(i));
						e.addElement(ex);
						}
					else{
						if (C.getComponent(i) instanceof JSplitPane){
							Element ex=parseToXML((JSplitPane)C.getComponent(i));
							e.addElement(ex);
							}
						else{
							if (C.getComponent(i) instanceof JTabbedPane){
								Element ex=parseToXML((JTabbedPane)C.getComponent(i));
								e.addElement(ex);
								}
							else{
								Element ex=parseToXML(C.getComponent(i));
								e.addElement(ex);	
								}
								
							}
							
						}
					
					}
					
				}
			
				
	    	}
		return e;
	}
	
	
	private Element parseToXML(JXLayer C){
		Element e=new Element("JXLayer");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			if (C.getComponent(i) instanceof JPanel){
				Element ex=parseToXML((JPanel)C.getComponent(i));
				e.addElement(ex);
				}
			else{
				//debug("Component "+i+" "+C.getComponent(i).getClass());
				}
			
			}	
		return e;
	}
	
	private Element parseToXML(JLayeredPane C){
		Element e=new Element("JLayeredPane");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			if (C.getComponent(i) instanceof JPanel){
				Element ex=parseToXML((JPanel)C.getComponent(i));
				e.addElement(ex);
				}
			}	
		return e;
	}
	
	private Element parseToXML(JRootPane C){
		Element e=new Element("JRootPane");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			Component _C=C.getComponent(i);
			if (_C instanceof JPanel){
				Element ex=parseToXML((JPanel)C.getComponent(i));
				e.addElement(ex);
				}
			if (_C instanceof JLayeredPane){
				Element ex=parseToXML((JLayeredPane) C.getComponent(i));
				e.addElement(ex);
				}	
			
			}
		return e;
	}
	
	private Element parseToXML(JTabbedPane C){
		Element e=new Element("JTabbedPane");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			Component _C=C.getComponent(i);
			if (_C instanceof JPanel){
				Element ex=parseToXML((JPanel)C.getComponent(i));
				e.addElement(ex);
				}
			if (_C instanceof JLayeredPane){
				Element ex=parseToXML((JLayeredPane) C.getComponent(i));
				e.addElement(ex);
				}
			if (_C instanceof JScrollPane){
				Element ex=parseToXML((JScrollPane) C.getComponent(i));
				e.addElement(ex);
				}
			if (_C instanceof JTabbedPane){
				Element ex=parseToXML((JTabbedPane) C.getComponent(i));
				e.addElement(ex);
				}
			if (_C instanceof JSplitPane){
				Element ex=parseToXML((JSplitPane) C.getComponent(i));
				e.addElement(ex);
				}
			
			}
		return e;
	}
	
	protected void parseFromXML(JRootPane C,Element values){
		//debug("Parsing From XML JRootPane"); 
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			Component _C=C.getComponent(i);
			if (_C instanceof JPanel){
				parseFromXML((JPanel)C.getComponent(i),values.getElements().get(i));
				
				}
			if (_C instanceof JTabbedPane){
				parseFromXML((JTabbedPane)C.getComponent(i),values.getElements().get(i));
				
				}
			if (_C instanceof JLayeredPane){
				parseFromXML((JLayeredPane) C.getComponent(i),values.getElements().get(i));
				
				}
			
			}
	}
	
	protected void parseFromXML(JToolBar C,Element values){
		//debug("Parsing From XML JToolBar");
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
				if (C.getComponent(i) instanceof JPanel){
					parseFromXML((JPanel)C.getComponent(i),values.getElements().get(i));
					}
				else {
					parseFromXML(C.getComponent(i),values.getElements().get(i));
					}
			}
		
	}
	
	protected void parseFromXML(JXLayer C,Element values){
		//debug("Parsing From XML JXLayer");
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			if (C.getComponent(i) instanceof JPanel){
				parseFromXML((JPanel)C.getComponent(i),values.getElements().get(i));
				}
			else{
				//debug("Component "+i+" "+C.getComponent(i).getClass());
				}
			}	
	}
	
	protected void parseFromXML(JTextField C,Element e){
		//debug("Parsing From XML JTextField");
		Atributo a=e.getAtributo("text");
		C.setText(a.getValor());
	}
	
	protected void parseFromXML(JTextArea C,Element e){
		//debug("Parsing From XML JTextArea");
		Atributo a=e.getAtributo("text");
		C.setText(a.getValor());
	}
	
	protected void parseFromXML(JCheckBox C,Element e){
		//debug("Parsing From XML JCheckBox");
		Atributo a=e.getAtributo("selected");
		boolean selected=a.getValor().compareTo("true")==0;
		C.setSelected(selected);
	}
	
	protected JTable create_table_xml(String nombre){
		return null;
	}
	
	protected void parseFromXML(JTable C,Element e){
			C=this.create_table_xml(e.getAtributo("nombre").getValor());
			int lon,lonrow;
			if (C!=null){
				lon=e.getElements().size();
				
				for (int i=0;i<lon;i++){
					Element row=e.getElements().get(i);
					if (i>C.getRowCount()-1){
						DefaultTableModel model=(DefaultTableModel) C.getModel();
						model.setRowCount(model.getRowCount()+1);
						}
					if (row.getId()=="row"){
						lonrow=row.getElements().size();
						for (int j=0;j<lonrow;j++){
							Element column=row.getElements().get(j);
							if (column.getId()=="celda"){
								String valor=column.getAtributo("valor").getValor();
								if (valor.compareTo("")==0){
									if (C.getClass().isInstance(Boolean.class)){
										C.setValueAt(false,i,j);
										}
									else{
										C.setValueAt("",i,j);
										}
									}
								else{
									if (valor.compareTo("true")==0|valor.compareTo("false")==0){
										boolean selected=valor.compareTo("true")==0;
										
										try {
											C.setValueAt(selected,i,j);
											}
										catch (Exception e1) {
											e1.printStackTrace();
											}
										}
									else{
										C.setValueAt(valor,i,j);
										}	
									}
								}
							}
						}
					}
				}
			else{
				//debug("No esta definido correctamente la creacion de tabla desde xml "+this.getClass());
			}
		
	}

	
	protected void parseFromXML(JComboBox C,Element e){
		//debug("Parsing From XML JComboBox");
	}
	
	protected void parseFromXML(JButton C,Element e){
		//debug("Parsing From XML JButton");
		Atributo a=e.getAtributo("enabled");
	
		if (a!=null){
			C.setEnabled(a.getValor().compareTo("true")==0);
			}
	}
	
	protected void parseFromXML(Component C,Element values){
		//debug("Parsing From XML Component");
		if (C instanceof JTextField){
			parseFromXML((JTextField)C,values);
			}
		
		if (C instanceof JTextArea){
			parseFromXML((JTextArea)C,values);
			}
		
		if (C instanceof JTree){
			//parseFromXML((JTree)C,values);
			}
		
		if (C instanceof JButton){
			parseFromXML((JButton)C,values);
			}
	
		if (C instanceof JComboBox){
			parseFromXML((JComboBox)C,values);
			
			}
		
		if (C instanceof JTable){
			parseFromXML((JTable)C,values);
			}
		
		if (C instanceof JTabbedPane){
			parseFromXML((JTabbedPane)C,values);
			}
		
		if (C instanceof JCheckBox){
			parseFromXML((JCheckBox)C,values);
			}
		
		if (C instanceof JXLayer){
			parseFromXML((JXLayer)C,values);
			}
		
	}
	
	protected void parseFromXML(JSplitPane C,Element values){
		//debug("Parsing From XML JSplitPane");
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			if (C.getComponent(i) instanceof JPanel){
				parseFromXML((JPanel)C.getComponent(i),values.getElements().get(i));
				}
			if (C.getComponent(i) instanceof JScrollPane){
				parseFromXML((JScrollPane)C.getComponent(i),values.getElements().get(i));
				}
			}
	}
	
	protected void parseFromXML(JScrollPane C,Element values){
		//debug("Parsing From XML JScrollPane "+values.getId()+" "+C.getViewport().getView());
		if (values!=null){
			if (values.getElements()!=null){
				Element child=values.getElements().get(0);
				if (child!=null){
					if (C.getViewport().getView() instanceof JTable){
						parseFromXML((JTable)C.getViewport().getView(),child);
						}
					if (C.getViewport().getView() instanceof JTree){
						parseFromXML((JTree)C.getViewport().getView(),child);
						}
					if (C.getViewport().getView() instanceof JTextArea){
						parseFromXML((JTextArea)C.getViewport().getView(),child);
						}
					if (C.getViewport().getView() ==null){
								if (child.getId().compareTo("JTable")==0){
									parseFromXML((JTable)C.getViewport().getView(),values.getElements().get(0));	
									}
								if (child.getId().compareTo("JTextArea")==0){
									parseFromXML((JTextArea)C.getViewport().getView(),values.getElements().get(0));	
									}		
						}				
					}	
				}
			}
		
	}
	
	protected void parseFromXML(JLayeredPane C,Element values){
		//debug("Parsing From XML JlayeredPane");
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			if (C.getComponent(i) instanceof JPanel){
				parseFromXML((JPanel)C.getComponent(i),values.getElements().get(i));
				}
			}	
		
	}
	
	protected void parseFromXML(JTabbedPane C,Element values){
		//debug("Parsing From XML JPanel");
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			if (C.getComponent(i) instanceof JPanel){
				parseFromXML((JPanel)C.getComponent(i),values.getElements().get(i));
				}
			else{
				if (C.getComponent(i) instanceof JScrollPane){
					parseFromXML((JScrollPane)C.getComponent(i),values.getElements().get(i));
					}
				else{
					if (C.getComponent(i) instanceof JSplitPane){
						parseFromXML((JSplitPane)C.getComponent(i),values.getElements().get(i));
						}
					else{
						parseFromXML(C.getComponent(i),values.getElements().get(i));
						}
					}
				}
			
			}
	}
	
	protected void parseFromXML(JPanel C,Element values){
		//debug("Parsing From XML JPanel");
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			if (C.getComponent(i) instanceof JPanel){
				parseFromXML((JPanel)C.getComponent(i),values.getElements().get(i));
				}
			else {
				if (C.getComponent(i) instanceof JToolBar){
					parseFromXML((JToolBar)C.getComponent(i),values.getElements().get(i));
					}
				else{
					if (C.getComponent(i) instanceof JScrollPane){
						parseFromXML((JScrollPane)C.getComponent(i),values.getElements().get(i));
						}
					else{
						if (C.getComponent(i) instanceof JSplitPane){
							parseFromXML((JSplitPane)C.getComponent(i),values.getElements().get(i));
							}
						else{
							parseFromXML(C.getComponent(i),values.getElements().get(i));
							}
						}
					}
				}
			}
		
	}

	public String saveToXML(){
		Element root=new Element("Aplicacion");
		Atributo atributo=new Atributo("clase");
		atributo.setValor(this.getClass().getCanonicalName());
		root.addAtribute(atributo);
		int lon=this._frame.getComponentCount();
		
		for (int i=0;i<lon;i++){
			//debug("Component "+i+" >"+this._frame.getComponent(i).getClass()+" "+
			//this._frame.getComponent(i).getName());
			if (this._frame.getComponent(i) instanceof JRootPane){
				JRootPane C=(JRootPane) this._frame.getComponent(i);
				root.addElement(this.parseToXML(C));
				}
			}
		 
		String xml=	new XML().toString(root);
		return xml;
	}
	
	
	/**
	 * carga la configuracion del host desde el XML en que fue guardado
	 */
	public void loadFromXML(){
		
		javax.swing.JFileChooser fc=new javax.swing.JFileChooser();
		int n=fc.showOpenDialog(null);
		File fx = fc.getSelectedFile();
		
		if (fx.exists()){
			this.loadFromXML(fx);
			}
	}
	
	
	public void loadFromXML(File fx){
		int lon;
		if (fx.exists()){
			XML xml=new XML();
			xml.setFile(fx);
			xml.readAll();
			Element root=xml.getRoot();
			lon=this._frame.getComponentCount();
			
			for (int i=0;i<lon;i++){
				if (root.getElements()!=null){
					Element e=root.getElements().get(i);
					if (e!=null){
						try {
							//debug(e.getId());
							}
						catch (Exception e1) {
							e1.printStackTrace();
							}
						if (this._frame.getComponent(i) instanceof JRootPane){
							JRootPane C=(JRootPane) this._frame.getComponent(i);
							if (C!=null)
								this.parseFromXML(C, e);	
							}
						}
					}
				}
			}
		
	}
	
	
	private Element parseToXML(JScrollPane C){
		Element e=new Element("JScrollPane");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		
		if (C.getViewport().getView() instanceof JTable){
			Element ex=parseToXML((JTable)C.getViewport().getView());
			e.addElement(ex);
			}
		if (C.getViewport().getView() instanceof JTree){
			Element ex=parseToXML((JTree)C.getViewport().getView());
			e.addElement(ex);
			}
		if (C.getViewport().getView() instanceof JTextArea){
			Element ex=parseToXML((JTextArea)C.getViewport().getView());
			e.addElement(ex);
			}
		return e;
	}
	
	private Element parseToXML(JToolBar C){
		Element e=new Element("JToolBar");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		int lon=C.getComponentCount();
		
		for (int i=0;i<lon;i++){
			if (C.getComponent(i) instanceof JPanel){
				Element ex=parseToXML((JPanel)C.getComponent(i));
				e.addElement(ex);
				}
			else {
				Element ex=parseToXML(C.getComponent(i));
				e.addElement(ex);
				}
			}
		
		return e;
	}
	
	private Element parseToXML(JTextField C){
		Element e=new Element("JTextField");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		Atributo text=new Atributo("text");
		text.setValor(C.getText());
		e.addAtribute(text);
	
		return e;
	}
	
	private Element parseToXML(JTextArea C){
		Element e=new Element("JTextArea");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		Atributo text=new Atributo("text");
		text.setValor(C.getText());
		e.addAtribute(text);
		
		return e;
	}
	
	private Element parseToXML(JTable C){
		Element e=new Element("JTable");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		int lon;
		if (C!=null){
			int max=C.getRowCount();
		
			if (max>1000){
				aviso("Esta Aplicacion Tiene Tablas Grandes(Mayor a 10000 Filas). Se Truncara La informacion para su analisis ");
				max=1000;
				}
			for (int i=0;i<max;i++){
				Element row=new Element("row");
				//Atributo row_index=new Atributo("number");
				//row_index.setValor(""+i);
				//row.addAtribute(row_index);
				lon=C.getColumnCount();
				
				for(int j=0;j<lon;j++){
					Element celda=new Element("celda");
					//Atributo column_index=new Atributo("column");
					//column_index.setValor(""+j);
					//celda.addAtribute(column_index);
					
					Atributo valor=new Atributo("valor");
					Object value=null;
					value=C.getValueAt(i, j);
					
					if (value==null){
						value="";
						}
					
					valor.setValor(""+value);
					celda.addAtribute(valor);
					row.addElement(celda);
					}
				e.addElement(row);
				}
			}
		
		return e;
	}
	
	private Element parseToXML(JCheckBox C){
		Element e=new Element("JCheckBox");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		Atributo seleccionado=new Atributo("seleccionado");
		seleccionado.setValor(""+C.isSelected());
		e.addAtribute(seleccionado);
	
		return e;
	}
	
	private Element parseToXML(JComboBox C){
		Element e=new Element("JComboBox");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		Atributo seleccionado=new Atributo("seleccionado");
		String selected="";
		
		try {
			selected=C.getSelectedItem().toString();
			}
		catch (Exception e1) {
			//e1.printStackTrace();
			}
		
		seleccionado.setValor(selected);
		e.addAtribute(seleccionado);
		
		return e;
	}
	
	private Element parseToXML(JButton C){
		Element e=new Element("JButton");
		Atributo clase=new Atributo("clase");
		clase.setValor(C.getClass().getCanonicalName());
		e.addAtribute(clase);
		Atributo nombre=new Atributo("nombre");
		nombre.setValor(C.getName());
		e.addAtribute(nombre);
		Atributo actionCommand=new Atributo("ActionCommand");
		actionCommand.setValor(C.getActionCommand());
		e.addAtribute(actionCommand);
		Atributo enabled=new Atributo("enabled");
		enabled.setValor(""+C.isEnabled());
		e.addAtribute(enabled);
	
		return e;
	}
	
	public boolean esFechaVieja(String fecha){
		boolean vieja=true;
		String hoy=this._data.getSystemDate();
		int n=new Convertidor().getMayorDate(hoy, fecha);
		vieja=n>0;
		
		return vieja;
	}
	
	public String validar_usuario(){
		return this.validar_usuario("Ingrese Su Clave:");
	}
	
	public String validar_usuario(String titulo){
		String idvendedor="";
		String password=this.requestPassword(titulo);
	
		if (password!=null){
			if (password.compareTo("")!=0){
				idvendedor=_data.getUserValidacion(password);
				if (idvendedor.compareTo("")==0){
					error("Error de Validacion de Usuario");
					}	
				}	
			else{
				error("Error de Validacion de Usuario");
			}
			}
		else{
			error("Error de Validacion de Usuario");
			}
		
		return idvendedor;
	}
	/**
	 * Fin Seccion XML////////////////////////////////////////////
	 */
	
	/**
	 * Evaluador de Formato de Fecha dd-MM-yyyy
	 */
	protected boolean evaluarFecha(String s) {
		DateFormat formatter;
		Date date = null;
		boolean error = false;
		String s1 = "";
		
		try {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			date = (Date) formatter.parse(s);
			s1 = formatter.format(date);
			}
		catch (Exception e) {
			error = true;
			e.printStackTrace();
			}
		
		return !error;
	}
	
	/**
	 * Evaluador Basico de Fecha dd-MM-yyyy
	 * @param tx
	 */

	public boolean evaluar_fecha(JTextField tx){
		boolean band=true;

		if (!this.evaluarFecha(tx.getText())){
			error("Error en Fecha");
			band=true;
			}
		else
			band=false;
		
		return band;
	}
	
	/**
	 * Completa la fecha con guiones mientras se tipea para cumplir con el formato dd-MM-yyyy
	 * @param tx
	 * @param ke
	 */
	public void evaluar_fecha(JTextField tx,KeyEvent ke){
		Character c=new Character(ke.getKeyChar());
		
		if (c.toString().compareTo("-")!=0){
			String valor=tx.getText();
			if (valor.length()==2){
				int day=-1;
				try {
					day=new  Integer(valor);
					}
				catch (NumberFormatException e) {
					e.printStackTrace();
					}
				if (day>0){
					valor=valor+"-";
					tx.setText(valor);
					tx.setSelectionStart(valor.length());
					}
				}
			if (valor.length()==5){
				int day=-1;
				
				try {
					day=new  Integer(valor.substring(0, 2));
					}
				catch (NumberFormatException e) {
					e.printStackTrace();
					}
				int month=-1;
				
				try {
					month=new  Integer(valor.substring(3, 5));
					}
				catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				if (day>0 & month>0){
					valor=valor+"-";
					tx.setText(valor);
					tx.setSelectionStart(valor.length());
					}
				}
			if ((valor.length()==8 | valor.length()==7) && ke.getKeyCode()==KeyEvent.VK_ENTER){
				
				int day=-1;
				
				try {
					day=new  Integer(valor.substring(0, 2));
					}
				catch (NumberFormatException e) {
					e.printStackTrace();
					}
				int month=-1;
				
				try{
					month=new  Integer(valor.substring(3, 5));
					}
				catch (NumberFormatException e) {
					e.printStackTrace();
					}
				int year=-1;
				
				try {
					year=new  Integer(valor.substring(6, valor.length()));
					}
				catch (NumberFormatException e) {
					e.printStackTrace();
					}
				
				if (day>0 && month>0 && year>=0){
					if (year<10){
						valor=valor.substring(0,6)+"200"+year;	
						}
					else{
						valor=valor.substring(0,6)+"20"+year;
						}
					tx.setText(valor);
					tx.setSelectionStart(valor.length());
					}
	
				}
			}
	}
	
	
}
