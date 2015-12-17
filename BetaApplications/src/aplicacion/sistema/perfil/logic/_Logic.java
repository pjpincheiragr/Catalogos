package aplicacion.sistema.perfil.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JTextField;


import aplicacion.asterisk.manager.logic.ImageCompare;
import aplicacion.compras.carga.items.logic.DisplayCanvas;
import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.aplicacion.interfaces._Parametros;
import aplicacion.sistema.perfil.gui._Frame;
import aplicacion.sistema.perfil.interfaces.*;
import aplicacion.sistema.perfil.logic._Data;

import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.XML;


public class _Logic extends Logic {

	private _Frame frame;
	private _Data data;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.sistema.autorizacion.constructor._Constructor aplicaciones = null;
	private Object[][] memory = null;
	private BufferedImage img = null;
	private List<BufferedImage> images=new LinkedList<BufferedImage>();
	private int indice=0;
	private int zoom=4;
	
	DropTarget dropTarget;
	DataFlavor urlFlavor, uriListFlavor, macPictStreamFlavor;

	
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	/*
	 * llama al hilo paralelo q ejecuta el metodo guardar
	 */
	public void guardar(){
		//checkear q todo este bien antes de guardar
		
			this.goTareaPesada();
	
	}
	
	/*
	 * guarda los datos del usuario en memoria
	 */
	public void _guardar(){
		estado="Verificando Datos";
		boolean error=false,band=false, grab=false;
		String iduser=frame.get_txt_idusuario().getText();
		String nombre=frame.get_txt_nombre().getText();
		String superusuario="0";
		String apellido=frame.get_txt_apellido().getText();
		String telefono=frame.get_txt_telefono().getText();
		String email=frame.get_txt_email().getText();
		String DNI=frame.get_txt_DNI().getText();
		String liscencia=frame.get_txt_liscencia().getText();
		String nacimiento=frame.get_txt_nacimiento().getText();
		String domicilio=frame.get_txt_domicilio().getText();
		String q="";
		
		band=this.evaluarNombre(nombre);
		if(!band)
			band=this.evaluarApellido(apellido);
		
		if(!band){
			band=this.evaluarEmail(email);
			/**
			 * guarda! el evaluar mail devuelve un true si esta todo bien
			 * al contrario de los otros evaluadores
			 */
		
			if(band){
				band=this.evaluarTelefono(telefono);
				
				if(!band)
					band=this.evaluarDNI(DNI);
				
				if(!band)
					band=this.evaluar_fecha(frame.get_txt_liscencia());
				
				if(!band)
					band=this.evaluar_fecha(frame.get_txt_nacimiento());
				
				if(!band)
					band=this.evaluarDomicilio(domicilio);

			}
			else
				band=true;// error en mail... cambio la bandera para no guardar
		}
		
		if(!band){
			int lon=this.images.size();
			grab=true;
			estado="Eliminando Fotos Anteriores";
			data.getConnector("MySQL").startTransaction();
			data.getConnector("MySQL").clearBatch();
	    	data.getConnector("MySQL").addBatch("delete from wallpapers where iduser like '"+iduser+"'");
	    	data.getConnector("MySQL").executeBatch();
			
	    	int i=0;
	    	estado="Guardando Fotos";
			if(this.images.size()>0){
				this.lenght=this.images.size();
				while( i<lon && grab){
					this.current=i+1;
					
					System.out.println("images.size: "+this.images.size());
					grab=this.storeImage(iduser,i,this.images.get(i));
					i++;
				}
			}
			estado="Guardando datos de usuario";
			
		}
		
		if (grab){
			data.getConnector("MySQL").commitTransaction();
			data.beginTransaction();
			data.clearBatch();	
			if (data.check_user(iduser)){
				q=data.update(iduser, nombre, apellido,telefono,email,DNI,liscencia,nacimiento,domicilio);
				
			}
			
			
			data.addBatch(q);
			error=data.executeBatch();
			if (!error && !band){
				data.commitTransaction();
				aviso("Se Grabo Correctamente");
			}
			else{
				data.rollbackTransaction();
				error("Error Grabando Usuario");
			}
		}else{
			data.getConnector("MySQL").rollbackTransaction();
			error("Error Grabando Imagen");
		}
		
		done=true;
	}
	
	/**
	 * libera todos los campos del frame
	 */
/*	public void clean(){
		frame.get_txt_idusuario().setText("");
		frame.get_txt_nombre().setText("");
		frame.get_txt_idusuario().setEnabled(true);
		
	}*/
	
	/**
	 * carga los datos del usuario desde memoria
	 */
	public void cargar_parametros(){
		String iduser=frame.get_txt_idusuario().getText();
		this.cargar_parametros(iduser);
		
	}
	
	public void cargar_parametros(String val){
		Object[][] results=data.getUser(val);
		
		if (results!=null){
			if (results.length>0){
				estado="cargando datos de usuario";
				String iduser=(String) results[0][0];
				String nombre=(String) results[0][1];
				String apellido=(String) results[0][2];
				String email=(String) results[0][3];
				String telefono=(String) results[0][4];
				String dni=(String) results[0][5];
				String fecha_liscencia=(String) results[0][6];
				String fecha_nacimiento=(String) results[0][7];
				String domicilio=(String) results[0][8];

				frame.get_txt_idusuario().setText(iduser);
				frame.get_txt_nombre().setText(nombre);
				frame.get_txt_idusuario().setEnabled(false);
				frame.get_txt_apellido().setText(apellido);
				frame.get_txt_email().setText(email);
				frame.get_txt_telefono().setText(telefono);
				frame.get_txt_DNI().setText(dni);
				frame.get_txt_liscencia().setText(fecha_liscencia);
				frame.get_txt_nacimiento().setText(fecha_nacimiento);
				frame.get_txt_domicilio().setText(domicilio);
				frame.getCanvas().resetVars();
				frame.getCanvas().dispose();
				frame.getCanvas().repaint();
				estado="cargando fotos de usuario";
				this.goTareaPesada2(iduser);
				
			}
		}
	}
	
	/*
	 * inicializa el foco
	 */
	public void focus(){
		frame.get_txt_idusuario().requestFocusInWindow();
	}
	

	public Object[][] processData(Object[][] results){
		for (int i=0;i<results.length;i++){
			results[i][2]=results[i][2].toString().compareTo("1")==0;
			results[i][3]=results[i][3].toString().compareTo("1")==0;
		}
		return results;
	}
	
	
	private aplicacion.herramientas.java.evaluadores.Usuario usuario;
	public void initialize_usuario(){
		usuario=new aplicacion.herramientas.java.evaluadores.Usuario(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_nombre().setText(descripcion);
				cargar_parametros(codigo);
			}
		};
		usuario.setConstructor(this.getConstructor());
	}
	
	public void Buscarusuario(JTextField tx){
		usuario.Buscar(tx);
	}
	
	public void Buscarusuario(){
		usuario.Buscar(frame.get_txt_idusuario());
	}
	
	public void buscarusuario(JTextField tx){
		usuario.buscar(tx);
	}
	
	public void evaluarusuario(JTextField tx){
		String codigo=tx.getText();
		if (usuario.existe(codigo)){
			usuario.setNumeric_code(false);
		}
		usuario.evaluate(tx);	
		
		
	}

	
	private void loadToMemory() {

	Object[][] results = data.getHostList();
		
	if (results != null) {
			if (results.length > 0) {
				System.out.println("Clasificaciones encontradas? "+ results.length);
				memory = new Object[results.length][4];
				for (int i = 0; i < results.length; i++) {
					for (int j = 0; j < 3; j++) {
						memory[i][j] = (String) results[i][j];
					}
					memory[i][3] = results[i][3].toString().compareTo("1")==0;
				}
			}
		}
	}
	
	
	private void modificar_memoria(String idclasificacion){
		System.out.println("modificacion memoria:"+idclasificacion+" "+true);
		boolean found=false;
		int i=0;
		if (memory!=null){
			while (i<memory.length & !found){
				System.out.println("buscando en memoria:("+memory[i][0].toString()+")"+idclasificacion+" "+true);
				found=(memory[i][0].toString().compareTo(idclasificacion)==0);
				if (found){
					System.out.println("encontrado en memoria:("+memory[i][0].toString()+")"+idclasificacion+" "+true);	
					memory[i][3]=true;
				}
				i++;
			}	
		}
		
	}
	
	public void cargar_seleccion_idhost(String iduser){
		String[] simulacion_base=data.getUserHostList(iduser);
		for (int i=0;i<simulacion_base.length;i++){
			this.modificar_memoria(simulacion_base[i]);
		}
	}


	
	/**
	 * devuelve un entero con la cantidad de nodos seleccionados
	 * @param nodo
	 * @return
	 */
	private int cantSelecciones(CheckBoxNode nodo){
		int cantH,cantSele=0;
		cantH=nodo.getChildCount();
		
		if (nodo.isSelected()){
			System.out.println("idclasificacion: "+nodo.getIdclasificacion());
			cantSele++;	
			//System.out.println(cantSele);
	 	}
		
		for(int i=0;i<cantH;i++){
			cantSele+=cantSelecciones((CheckBoxNode)nodo.getChildAt(i));	
			}
		
		return cantSele;
	}
	
	/**
	 * evalua q la contrasena sea unica
	 * @param tx
	 * @return
	 */
	public boolean evaluarPass(String tx){
		boolean band=false;
		
		if(tx.length()>0){
			Object[][] result=data.getUserCheck(tx);
			
			if(result!=null)
				if(result.length>0){
					band=true;
				}
			
		}
		return band;
		
	}
	
	
	public void cambiarPass(){
		String newPass,pass,newPass2;
		boolean band=false,coso=false;
		
		//ingresa la password actual
		String aux=this.validar_usuario("ingrese la password actual");
		
		//ingresa la nueva clave por teclado
		
		if(aux.compareTo("")!=0){
			System.out.println("usuario: "+aux); 

			do{
				do{
					newPass=this.requestPassword("Ingrese la nueva password");
					if(newPass.compareTo("")!=0){
						//newPass=this.getEncrypted(newPass);
						newPass=newPass.replaceAll("'", "''");
						System.out.println("new Pass: "+newPass); 
						if(!this.evaluarPass(newPass))
							band=true;
						else{
							band=false;
							this.error("password insegura, ingrese otra");
						}
					}
					else{
						coso=true;
						band=true;}
				
				}while(!band);
				
			if(!coso){
				newPass2=this.requestPassword("ingrese la password nuevamente");
				//newPass2=this.getEncrypted(newPass2);
				newPass=newPass.replaceAll("'", "''");
				System.out.println("new Pass: "+newPass2); 

				
				if(newPass.compareTo(newPass2)!='0')
					band=true;
				else{
					band=false;
					this.aviso("las passwords deben coincidir!");
				}
			}
				
			}while(!band);
		
		
			if(!coso){
				String user=frame.get_txt_idusuario().getText();
				
				data.beginTransaction();
				data.clearBatch();
				String q=data.getUpdatePassword(user, newPass);
				data.addBatch(q);
				band=data.executeBatch();
				
				if(band){
					data.rollbackTransaction();
					this.error("error al grabar la password");
				}
				else{
					data.commitTransaction();
					this.aviso("la password a cambiado");
				}
				
				}
				
			}
	
	
		

	}
	
	/**
	 * verifica la sintaxis de la direccion del mail
	 * @param tx
	 * @return
	 */
	public boolean evaluarEmail(String mail){
		
		boolean band=false;
		int i=0,lon=mail.length();
		
		try{
			while(!band && i<lon){
				if(mail.charAt(i)=='@' && i>1){
						if(i<lon-1)
							if(mail.charAt(i+1)!='.')//controla si entre el @ y el punto hay caracteres
								band=true;
					}

				i++;
			}
			
			if(band){
				
				while(band && i<lon){
					if(mail.charAt(i)=='.')
						band=false;
					i++;
				}
				if(band){
					band=this.errorMail(mail);
				}
				else{
					
					if(i<lon-1){
						if(mail.substring(i).length()>0){
							frame.get_txt_telefono().requestFocusInWindow();
							band=true;
							}
						else{
							band=this.errorMail(mail);
						}

						}
					else{
						band=this.errorMail(mail);
					}
				}
			}
			else{
				band=this.errorMail(mail);
			}

		}
		catch(Exception e){
			
		}
		return band;
	}

	/**
	 * error para el evaluador de direccion mail
	 * @param msj
	 * @return
	 */
	public boolean errorMail(String msj){
		
		this.error("direcion de mail invalida");
		frame.get_txt_email().requestFocusInWindow();
		frame.get_txt_email().setSelectionStart(0);
		frame.get_txt_email().setSelectionEnd(msj.length());
		
		return false;
		
	}
	
	/**
	 * prosesa el evento de drag & drop
	 */
	public void processDND(DropTargetDropEvent dtde){
		System.out.println ("drop");
		dtde.acceptDrop (DnDConstants.ACTION_COPY_OR_MOVE);   
		Transferable trans = dtde.getTransferable( );
		System.out.println ("Flavors:"); 
		boolean gotData = false;
		try {
			// try to get an image
			if (trans.isDataFlavorSupported (DataFlavor.imageFlavor)) { 
				System.out.println ("image flavor is supported"); 
				Image img = (Image) trans.getTransferData (DataFlavor.imageFlavor); 
				
				this.addFoto(img);
				gotData = true;
			} else if (trans.isDataFlavorSupported (
				DataFlavor.javaFileListFlavor)) {
				System.out.println ("javaFileList is supported");
				java.util.List list = (java.util.List)
				trans.getTransferData (DataFlavor.javaFileListFlavor);
				ListIterator it = list.listIterator( );    
				while (it.hasNext( )) {
					System.out.println("Drag&Drop>"+it.next().toString());
				File f = (File) it.next( );
				System.out.println("Adding Foto DND >"+f.getAbsolutePath());
			
				this.addFoto(f.getPath());
				
				}
				gotData = true;
			} else if (trans.isDataFlavorSupported (uriListFlavor)) {
				System.out.println ("uri-list flavor is supported"); 
				String uris = (String)
				trans.getTransferData (uriListFlavor);
				
				// url-lists are defined by rfc 2483 as crlf-delimited 
				
				StringTokenizer izer = new StringTokenizer (uris, "\r\n");   
				while (izer.hasMoreTokens ( )) {
					
				//String uri = izer.nextToken( );
				String uri=java.net.URLDecoder.decode (izer.nextToken( ), "utf-8");
				System.out.println ("uri:"+uri);
				
				
				this.addFoto(uri);
				
				}
				gotData = true;
			} else if (trans.isDataFlavorSupported (urlFlavor)) {
				System.out.println ("url flavor is supported");
				URL url = (URL) trans.getTransferData (urlFlavor);
				System.out.println ("url:"+url.toString( ));
				
				System.out.println (url);
				this.addFoto(url.getFile());
			
				gotData = true;
			}
			  
			
			
			
		} catch (Exception e) {
			e.printStackTrace( );
		} finally {
			System.out.println ("gotData is " + gotData);
			dtde.dropComplete (gotData);
		}
		
		
	}
	
	/**
	 * agrega la foto al frame
	 * @param img
	 */
	public void addFoto(Image img){
		//System.out.println("addfoto");
		aplicacion.herramientas.java.image.constructor._Constructor C=
		new aplicacion.herramientas.java.image.constructor._Constructor();
		C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		
		C.setParameter(aplicacion.herramientas.java.image.interfaces._Parametros._eliminar, false);
		C.setShowOnStartup(false);
		C.build(this.getConstructor());
		C.init();
		
		aplicacion.herramientas.java.image.logic._Logic logic=
			(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
		
		//In response to a button click:
		BufferedImage bimg=frame.getCanvas().toBufferedImage(img);
		images.add(bimg);
			logic.setImage(images.get(0));
			
	}
	
	/**
	 * agrega al frame la foto con la direccion recibida por parametro
	 * @param path
	 */
	public void addFoto(String path){
			
			boolean ok=new File(path).exists();
			if (!ok){
				URL url=null;
				try {
					System.out.println("path: "+path);
					url=new URL(path);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (url!=null){
					
					
					path=url.getFile();
					try {
						path=java.net.URLDecoder.decode (url.getFile(), "utf-8");
						System.out.println("path: "+path);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					
				}
				ok=new File(path).exists();	
			}
			
			
			if (ok){

				//System.out.println("addfoto metodo "+path);
				
				aplicacion.herramientas.java.image.constructor._Constructor C=
				new aplicacion.herramientas.java.image.constructor._Constructor();
				C.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
				C.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
				
				C.setParameter(aplicacion.herramientas.java.image.interfaces._Parametros._eliminar, false);
				C.setShowOnStartup(false);
				C.build(this.getConstructor());
				C.init();
				
				aplicacion.herramientas.java.image.logic._Logic logic=
					(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
				
				//In response to a button click:
					
					logic.setFileName(path);
					logic.loadImage();
					BufferedImage aux=frame.getCanvas().getScale(logic.getImage(),frame.getCanvas().getWidth(),frame.getCanvas().getHeight());
					boolean repetida=false;
					int i=0;
					while (!repetida & i<images.size()){
						repetida=this.comparar(images.get(i), aux);
						i++;	
					}
					if (!repetida){
						frame.getCanvas().setImage(aux);
						frame.getCanvas().zoom(0);
						this.images.add(aux);	
						this.setIndice(this.images.size()-1);
					}else{
						error("Foto Repetida. No se Agregara");
					}
						
					
						
			}else{
				error("No che!");
			}
		
	
				
	}
	
	
	public boolean evaluarTelefono(String telefono){
		boolean band=true;
		if(telefono.length()>0){
			try{
				int aux= new Integer(telefono);
				frame.get_txt_DNI().requestFocusInWindow();
				band= false;
			}catch(Exception e){
				this.error("numero de telefono invalido");
				frame.get_txt_telefono().requestFocusInWindow();			
				band= true;
			}
		}
		else{
			this.error("numero de telefono invalido");
			frame.get_txt_telefono().requestFocusInWindow();
			band= true;
		}
			return band;
	}
	
	public boolean evaluarDNI(String DNI){
		boolean band=true;
		int lon=DNI.length(),cont=0;
		String sub="";
		
		try{
			while(cont<lon){
				if(DNI.charAt(cont)!='.')
					sub+=DNI.charAt(cont);
				cont++;
			}
			
			int aux= new Integer(sub);
			frame.get_txt_liscencia().requestFocusInWindow();
			band=false;
		}catch(Exception e){
			this.error("DNI invalido");
			frame.get_txt_DNI().requestFocusInWindow();
			band= true;
		}
		return band;
	}
	
	public boolean evaluarNombre(String nombre){
		
		if(nombre.length()<=0){
			this.error("ingrese un nombre");
			frame.get_txt_nombre().requestFocusInWindow();
			return true;
		}
		else{
			frame.get_txt_apellido().requestFocusInWindow();
			return false;
		}
		
	}
	
	public boolean evaluarApellido(String Apellido){
		if(Apellido.length()<=0){
			this.error("ingrese un apellido");
			frame.get_txt_apellido().requestFocusInWindow();
			return true;
		}
		else{
			frame.get_txt_email().requestFocusInWindow();
			return false;
		}
	}

	public boolean evaluarDomicilio(String domicilio){
		if(domicilio.length()<=0){
			this.error("ingrese un domicilio");
			frame.get_txt_domicilio().requestFocusInWindow();
			return true;
		}
		else{
			frame.get_btn_guardar().requestFocusInWindow();
			return false;
		}
	}
	
	public void initialize_dnd(){
		try { 
			urlFlavor = 
			new DataFlavor ("application/x-java-url; class=java.net.URL"); 
			uriListFlavor = 
			new DataFlavor ("text/uri-list; class=java.lang.String");
		} catch (ClassNotFoundException cnfe) { 
			cnfe.printStackTrace( );
		}
		dropTarget = new DropTarget (this.frame.getCanvas(),this.getConstructor().getDropTargetListener());
		
	}
	
	public void evaluarIdusuario(JTextField tx){
		String nombre=tx.getText();
		if(nombre.length()<=0){
			this.error("ingrese un nombre");
			frame.get_txt_idusuario().requestFocusInWindow();
		}
		else
			frame.get_txt_nombre().requestFocusInWindow();
	}
	
	public void __initial_elegir_vendedor() {
		if (javax.swing.SwingUtilities.isEventDispatchThread()){
			  elegir_vendedor();
		}else{
			Runnable _execute=new Runnable() {
				   public void run() {
					   elegir_vendedor();		   
				   }
			};
			this.invokeAndWait(_execute);	
		}
		
	}

	public boolean elegir_vendedor() {
		boolean ok = false;
		
		
		
		String password = this.requestPassword("Ingrese Su Clave:");
		if (password.compareTo("")!=0){
			String idvendedor = data.getVendedorValidacion(password);
			
			System.out.println("idvendedor: "+idvendedor);
			if (idvendedor.compareTo("") == 0) {
					error("Error de Validacion de Usuario");
					ok = true;
					
				} 
				else {
					//inicializa la imagen
					images=new LinkedList<BufferedImage>();
					this.cargar_parametros(idvendedor);
					ok = false;
				}	
		}
		
		
		
		return ok;
		}
	
	public String validar_vendedor() {
		String idvendedor = "";
		while (idvendedor.compareTo("") == 0) {
		String password = this.requestPassword("Ingrese Su Clave:");
		idvendedor = data.getVendedorValidacion(password);
		if (idvendedor.compareTo("") == 0) {
		error("Error de Validacion de Usuario");
		}
		}
		return idvendedor;
		}
	
	/**
	 * evalua la fecha de vencimiento de el carnet
	 * @param tx
	 */
	public void evaluarVencimiento(JTextField tx){
		this.evaluar_fecha(tx);
		frame.get_txt_nacimiento().requestFocusInWindow();
	}
	
	public void evaluarNacimiento(JTextField tx){
		this.evaluar_fecha(tx);
		System.out.println("fecha nacimiento: "+tx.getText());
		frame.get_txt_domicilio().requestFocusInWindow();

	}

	public void clean(){
		this.elegir_vendedor();
		
		
		
	}
	
	/**
	    * Método donde cargamos la imagen

	    */
/*	    public void leerImagen(){
	    	this.addFoto("file:///home/ventas1/Im%C3%A1genes/BosqueTK.jpg");
	    }*/
	
	    /**
	     * recupera las imagenes de memoria del usuario predeterminado
	     */
	    public void getImages(String iduser){
	    	BufferedImage _image=null;
	    	int cantidad=data.getWallpapersCount(iduser);
	    	int cont=1;
	    	
	    	if(cantidad>0){
	    		this.lenght=cantidad;
	    	
		    	try {
					Statement stmt = data.getConnector("MySQL").createStatement();
					
						String q="SELECT imagen FROM wallpapers  where iduser like '"+iduser+"'";	
						System.out.println(q);
						ResultSet resultSet = stmt.executeQuery(q);
						
							 while (resultSet.next()){
								 this.current=cont;
								 Blob image = resultSet.getBlob(1);
								  InputStream input = image.getBinaryStream();
						          _image = javax.imageio.ImageIO.read(input);
						          images.add(_image);
						           cont++;
							 }
							 System.out.println("iduser: "+iduser);
							 this.current=0;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
	    	}
	    	this.setIndice(0);
			this.mostrar_foto(this.getIndice());
			done=true;
	    	
				
			
	    }
	    
	    
		public void mostrar_foto(int i) {
			if(images!=null){
				if(images.size()>0){
					if (i >= 0 & i <= images.size()) {
						
						BufferedImage bi = images.get(i);
						if (bi != null) {
							frame.getCanvas().setImage(bi);
							frame.getCanvas().maximizar();
							frame.getCanvas().setVisible(true);
							//frame.get_btn_zoom_in().setEnabled(true);
							//frame.get_btn_zoom_oute().setEnabled(true);
						}
						
					}
				}
			}

		}

		/**
		 * guarda la foto en la tabla de memoria
		 * @param iduser
		 * @param secuencia
		 * @param img
		 * @return
		 */
		public boolean storeImage(String iduser,int secuencia, BufferedImage img) {
			ByteArrayInputStream fis = null;
			PreparedStatement ps = null;
			System.out.println("Store Image " + iduser);
			boolean ok = true;
			
			if (iduser.compareTo("") != 0) {
				
	        
	        	String INSERT_PICTURE = "insert into wallpapers(iduser,secuencia,imagen) values (?,?,?)";
	        	ByteArrayOutputStream buffer_img = new ByteArrayOutputStream();

				try {
					ImageIO.write(img, "jpg", buffer_img);
					fis = new ByteArrayInputStream(buffer_img.toByteArray());

					ps = data.getConnector("MySQL").prepareStatement(INSERT_PICTURE);
					ps.setString(1,iduser);
					ps.setInt(2,secuencia);
					ps.setBinaryStream(3, fis, (int) buffer_img.size());

					int n = ps.executeUpdate();
					ok = n > 0;
				
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
      	          try {
      				ps.close();
      			} catch (SQLException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      				ok=false;
      			}
		  		  try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ok=false;
				}
				
				if (ok){
    	        	
    	        	System.out.println("foto guardada exitosamente");
    	        	
    	        }
    	        else{
    	        	System.out.println("Error al guardar foto!");
    	        
    	        }
			}
			}
			return ok;
			
		}
	
		/**
		 * StoreImage viejo!
		 * @param iduser
		 * @param filename
		 * @return
		 */
		public boolean storeImageold(String iduser,String filename){
	    	FileInputStream fis = null;
	    	int secuencia=0;
	        PreparedStatement ps = null;
	    	boolean ok=false;
	        if (iduser.compareTo("")!=0){
	        	data.beginTransaction();
	        	data.getConnector("MySQL").clearBatch();
	        	data.getConnector("MySQL").addBatch("delete from wallpapers where iduser like '"+iduser+"' and secuencia = "+secuencia);
	        	data.getConnector("MySQL").executeBatch();
	        	String INSERT_PICTURE = "insert into wallpapers(iduser,secuencia,imagen) values (?,?,?)";
	        	
	        	ok=true;
	        	System.out.println(INSERT_PICTURE);
	        	
	        	filename=filename.substring(7,filename.length());
	        	File file = new File(filename);
	        	
	        	if (file.exists()){
	        		
	        		if (file.isFile()){
	            	  try {
	        	          
	        	          try {
	        				fis = new FileInputStream(file);
	        	          } catch (FileNotFoundException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
		        	          

	        				ok=false;
	        	          }
	        	          
	        	          try {
	        				ps = data.getConnector("MySQL").prepareStatement(INSERT_PICTURE);
	        				  ps.setString(1,iduser);
	        				  ps.setInt(2,secuencia);
	        				  ps.setBinaryStream(3, fis, (int) file.length());
	        				  
	        				  ps.executeUpdate();
	        				  
	        				  System.out.println("Insercion de imagen:"+file.getName()+">?");
	        				  ok=true;
	        			} catch (SQLException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        				ok=false;
	        			}
	        	          
	        	        } finally {
	        	          try {
	        				ps.close();
	        			} catch (SQLException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        				ok=false;
	        			}
	        	          try {
	        				fis.close();
	        			} catch (IOException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        				ok=false;
	        			}
	        	        }
	        	        if (ok){
	        	        	data.commitTransaction();
	        	        	System.out.println("foto guardada exitosamente");
	        	        	
	        	        }
	        	        else{
	        	        	data.rollbackTransaction();
	        	        	System.out.println("Error al guardar foto!");
	        	        
	        	        }
	              }else{
	            	  System.out.println(filename+" no es un archivo ");
	              }
	          	
	        }else{
	        	  System.out.println(filename+" no existe");
	        }
	        }else{
	        	  System.out.println("iduser nulo");
	        }
	          return ok;
	    }
		
		
	public void buscar_imagen(){
		JFileChooser JF = new JFileChooser();
		int rx=JF.showOpenDialog(frame);
		if (rx == JFileChooser.APPROVE_OPTION) {
			
		File file = JF.getSelectedFile();
		
		
		this.addFoto(file.getAbsolutePath());
		
		}
	}
	
	public boolean comparar(BufferedImage img1,BufferedImage img2){
		ImageCompare ic = new ImageCompare(img1, img2);
		ic.setParameters(2, 2, 2, 2);
		ic.setDebugMode(2);
		ic.compare();
		return ic.match();
	}
	
	public void eliminarFoto(){
		
		if(this.images.size()>1){
		
			if(this.getIndice()>0){
				this.images.remove(this.getIndice());
				this.anteriorFoto();
				}
			else if(this.getIndice()==0){
					this.images.remove(0);
					this.setIndice(-1);
					this.siguienteFoto();
					
			}
		}
		else{
			if(this.images.size()>0){
				frame.getCanvas().resetVars();
				frame.getCanvas().dispose();
				frame.getCanvas().repaint();			
				this.setIndice(-1);
				this.images.remove(0);
			}
		}
	}
	
	/**
	 * muestra la primer foto del arreglo de imagenes
	 */
	public void goFirst(){
		if(this.getIndice()>0){
			this.mostrar_foto(0);
			this.setIndice(0);
		}
	}

	/**
	 * muestra la ultima foto del arreglo de imagenes
	 */
	public void goLast(){
		if(this.getIndice()<this.images.size()-1){
			this.mostrar_foto(this.images.size()-1);
			this.setIndice(this.images.size()-1);
			}
	}
	
	public void siguienteFoto(){
		if(this.getIndice()<this.images.size()-1){
			this.setIndice(this.getIndice()+1);
			this.mostrar_foto(getIndice());
		}
		
	}
	
	public void anteriorFoto(){
		if(this.getIndice()>0 && this.images.size()>=1){
			this.setIndice(this.getIndice()-1);
			this.mostrar_foto(getIndice());
		}
	}
	
	public void zoomIn(){
		frame.getCanvas().zoom_add(zoom);
	}
	
	public void zoomOut(){
		frame.getCanvas().zoom_rem(zoom);
	}

	/**
	 * 
	 */
	public void resetFoto(){
		frame.getCanvas().resetVars();
		frame.getCanvas().maximizar();
		//frame.getCanvas().repaint();
	}
	
	/**
	 * visualiza la foto en un frame emergente con las dimensiones reales
	 */
	private JFrame mostrar=null;
	public void view(){
		if (mostrar!=null){
			mostrar.dispose();
		}
		mostrar=new JFrame("Visor de Imagen");
		DisplayCanvas canvas=new DisplayCanvas();
		BufferedImage img=images.get(getIndice());
		canvas.setImage(img);
		mostrar.add(canvas);
		this.maximizar(mostrar);
		mostrar.setVisible(true);
		mostrar.repaint();
		mostrar.pack();
	}
	
	/*
	 * MANEJO DE TAREAS CON OTRO HILO. SE UTILIZA PARA APLICACIONES PESADITAS
	 */
	private int current,lenght, max;
	private boolean debug, done, canceled, override;
	private String estado = "";
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;
	
	public void createTimer() {
		crono = new Crono();
		crono.start();
		current = 0;
		done = false;
		canceled = false;
		Timer = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done) {
					endbar();
					Timer.stop();
				} else {
					updateBar();
				}
			}
		});

	}

	public void endbar() {
		done = true;
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
		frame.getJProgressBar().setString("");
	}

	public void updateBar() {
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(estado + " " + current + "/" + lenght + " " + crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}

	class heavyTask {
		heavyTask() {
			//aca va el metodo pesadito
			_guardar();
		}
	}

	class heavyTask2 {
		heavyTask2(String idvendedor) {
			getImages(idvendedor);
			//aca va el metodo pesadito
			
//			if (javax.swing.SwingUtilities.isEventDispatchThread()){
//				
//		
//				}
//			else{
//				final String _idvendedor=idvendedor; 
//				Runnable _execute=new Runnable() {
//					   public void run() {
//						   cargar_parametros(_idvendedor);	   
//					   }
//				};
//				invokeAndWait(_execute);
//				
//			}
		}
	}

	/**
	 * ejecuta la un hilo
	 */
	public void goTareaPesada() {
		
		this.createTimer();
		estado = ""; //aca va el detalle para mostrar en la barra de prog.
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new heavyTask();
			}
		};

		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}
	
	public void goTareaPesada2(String idvendedor){
		
		this.createTimer();
		final String _idvendedor=idvendedor;
		estado = ""; //aca va el detalle para mostrar en la barra de prog.
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new heavyTask2(_idvendedor);
			}
		};

		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}
	
	

}


