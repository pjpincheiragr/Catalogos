package aplicacion.sistema.aplicacion2.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import com.sun.jndi.cosnaming.IiopUrl.Address;

import aplicacion.asterisk.manager.logic.ImageCompare;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.herramientas.java.launcher.logic.Task_Model;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.aplicacion2.gui._Frame;
import aplicacion.sistema.aplicacion2.interfaces.*;
import aplicacion.sistema.aplicacion2.logic._Data;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.XML;
import aplicacion.herramientas.java.evaluadores.*;

public class _Logic extends Logic {

	private _Frame frame;
	private _Data data;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	//private aplicacion.sistema.autorizacion.constructor._Constructor aplicaciones = null;
	private aplicacion.herramientas.java.evaluadores.Aplicacion _aplicacion = null;
	private List<BufferedImage> icons=new LinkedList<BufferedImage>();
	private int indice;
	private String estado="";
	DropTarget dropTarget;
	DataFlavor urlFlavor, uriListFlavor, macPictStreamFlavor;

	
	public void setFrame(JFrame frame) {
		this.frame = (_Frame) frame;
		super.setFrame(frame);
	}
	

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

/* 	public boolean evaluar() {
		boolean band;
		String idaplicacion = frame.get_txt_idaplicacion().getText();
		String ip = frame.get_txt_ip().getText();
		
		band = idaplicacion.compareTo("") != 0;
		
		if (!band) {
			this.aviso("idaplicacion invalido");
			frame.get_txt_idaplicacion().requestFocusInWindow();
			frame.get_txt_idaplicacion().setSelectionStart(0);
			frame.get_txt_idaplicacion().setSelectionEnd(ip.length());
		} else {
			if(frame.get_rad_manual().isSelected()){

				band = this.evaluarIp(ip);
				if (!band) {
					frame.get_txt_ip().requestFocusInWindow();
					frame.get_txt_ip().setSelectionStart(0);
					frame.get_txt_ip().setSelectionEnd(ip.length());
				}
			}
			else{
				frame.get_txt_ip().setText("");
			}

			if(frame.get_txt_email().getText()!=""){
				band=this.evaluarEmail(frame.get_txt_email());
				if(band)
					band=this.evaluarSistema(frame.get_txt_os());
			}
			
			
		}
		return band;
	}*/

	/**
	 * guarda un nuevo aplicacion
	 */
	public void guardar(){
		
			boolean error = false;
			String idaplicacion = frame.get_txt_idaplicacion().getText();
			String nombre = frame.get_txt_nombre().getText();
			String lanzador= frame.get_txt_lanzador().getText();
			String area="";
			if(frame.get_lst_area().getSelectedItem()!=null)
				area= frame.get_lst_area().getSelectedItem().toString();
			
			boolean band=false;//this.evaluarAplicacion(frame.get_txt_idaplicacion());
			
			if(!band){
				band=this.evaluarNombre(frame.get_txt_nombre());
				
				if(!band){
					band=this.evaluarLanzador(frame.get_txt_lanzador().getText());
					
					if(!band){
						int lon=this.icons.size();
						
						
						//data.getConnector("MySQL").startTransaction();
						data.getConnector("MySQL").clearBatch();
						data.getConnector("MySQL").addBatch("delete from b_aplicaciones where idaplicacion like '"+idaplicacion+"'");
						data.getConnector("MySQL").executeBatch();
						
				    	//int i=0;
				    	System.out.println("indice: "+this.indice);
				    	System.out.println("icons.size: "+this.icons.size());
						
				    	if(this.icons.size()>0){
								System.out.println("icons.size: "+this.icons.size());
								band=!this.storeIcons(idaplicacion,0,this.icons.get(this.indice));
							
						}
					}
					
					if(!band){
						if (data.check_aplicacion(idaplicacion)){
							error = data.update(idaplicacion, nombre, lanzador, area);
						} else {
							error = data.insert(idaplicacion, nombre, lanzador, area);
						}

						if (!error) {
							aviso("Se Grabo Correctamente");
						} else {
							error("Error Grabando aplicacion");
						}
					}
				}
				

			}

	}
	
	
	public void buscarIcono(){
		JFileChooser JF = new JFileChooser();
		int rx=JF.showOpenDialog(frame);
		if (rx == JFileChooser.APPROVE_OPTION) {
			
		File file = JF.getSelectedFile();
		
		
		this.addFoto(file.getAbsolutePath());
		
		}
	}
	
	public boolean storeIcons(String idaplicacion,int secuencia, BufferedImage img) {
		ByteArrayInputStream fis = null;
		PreparedStatement ps = null;
		System.out.println("Store Image " + idaplicacion);
		boolean ok = true;
		
		if (idaplicacion.compareTo("") != 0) {
			
        
        	String INSERT_PICTURE = "insert into b_aplicaciones(idaplicacion,icono) values (?,?)";
        	ByteArrayOutputStream buffer_img = new ByteArrayOutputStream();
        	
			try {
				ImageIO.write(img, "jpg", buffer_img);
				fis = new ByteArrayInputStream(buffer_img.toByteArray());
				data.getConnector("MySQL").startTransaction();
				ps = data.getConnector("MySQL").prepareStatement(INSERT_PICTURE);
				ps.setString(1,idaplicacion);
				ps.setBinaryStream(2, fis, (int) buffer_img.size());

				int n = ps.executeUpdate();
				ok = n > 0;
			
				if ( ok) {
					data.getConnector("MySQL").commitTransaction();
				}else{
					data.getConnector("MySQL").rollbackTransaction();
				
					}
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

	public boolean comparar(BufferedImage img1,BufferedImage img2){
		ImageCompare ic = new ImageCompare(img1, img2);
		ic.setParameters(2, 2, 2, 2);
		ic.setDebugMode(2);
		ic.compare();
		return ic.match();
	}
	
	public void eliminarFoto(){
		
		if(this.icons.size()>1){
		
			if(this.getIndice()>0){
				this.icons.remove(this.getIndice());
				this.anteriorFoto();
				}
			else if(this.getIndice()==0){
					this.icons.remove(0);
					this.setIndice(-1);
					this.siguienteFoto();
					
			}
		}
		else{
			if(this.icons.size()>0){
				frame.getCanvas().resetVars();
				frame.getCanvas().dispose();
				frame.getCanvas().repaint();			
				this.setIndice(-1);
				this.icons.remove(0);
			}
		}
	}
	
	/**
	 * muestra la primer foto del arreglo de imagenes
	 */
	public void goFirst(){
		if(this.getIndice()>0){
			this.mostrarIcono(0);
			this.setIndice(0);
		}
	}

	/**
	 * muestra la ultima foto del arreglo de imagenes
	 */
	public void goLast(){
		if(this.getIndice()<this.icons.size()-1){
			this.mostrarIcono(this.icons.size()-1);
			this.setIndice(this.icons.size()-1);
			}
	}
	
	public void siguienteFoto(){
		if(this.getIndice()<this.icons.size()-1){
			this.setIndice(this.getIndice()+1);
			System.out.println("indice: "+this.indice);
			this.mostrarIcono(getIndice());
		}
		
	}
	
	public void anteriorFoto(){
		if(this.getIndice()>0 && this.icons.size()>=1){
			this.setIndice(this.getIndice()-1);
			System.out.println("indice: "+this.indice);
			this.mostrarIcono(getIndice());
		}
	}

	
	/**
	 * elimina la aplicacion seleccionado
	 */
	public void delete() {
		String id = frame.get_txt_idaplicacion().getText();

		if (preguntar("Confirmar", "Desea eliminar el aplicacion " + id + "?")) {
			data.delete(id);
			clean();
		}
	}

	
	public void mostrarIcono(int i) {
		if(icons!=null){
			if(icons.size()>0){
				if (i >= 0 & i <= icons.size()) {
					
					BufferedImage bi = icons.get(i);
					System.out.println("bi: "+bi.toString());
					if (bi != null) {
						
						frame.getCanvas().setImage(bi);
						//frame.getCanvas().setTools(false);
						frame.getCanvas().setVisible(true);
						frame.getCanvas().zoom(0);
						//frame.getCanvas().repaint();
						//frame.get_btn_zoom_in().setEnabled(true);
						//frame.get_btn_zoom_oute().setEnabled(true);
					}
					
				}
			}
		}

	}

	/*
	 * procesa el evento de drag and drop
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
			System.out.println("hay o no hay?"+icons.isEmpty());
		}
		
		
	}
	
	
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
		icons.add(bimg);
			logic.setImage(icons.get(0));
			
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
					try {
						logic.loadImage();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						ok=false;
					}
					if(ok){
						BufferedImage aux=frame.getCanvas().getScale(logic.getImage(),frame.getCanvas().getWidth(),frame.getCanvas().getHeight());
						boolean repetida=false;
						int i=0;
						while (!repetida & i<icons.size()){
							repetida=this.comparar(icons.get(i), aux);
							i++;	
						}
						if (!repetida){
							frame.getCanvas().setImage(aux);
							frame.getCanvas().zoom(0);
							this.icons.add(aux);	
							this.setIndice(this.icons.size()-1);
						}else{
							error("Foto Repetida. No se Agregara");
						}
							
						
					}
					else
						error("No che....");
						
			}else{
				error("No che!");
			}
		
	
				
	}
	
	/**
	 * limpia los campos de la ventana
	 */
	public void clean() {
		frame.get_txt_idaplicacion().setText("");
		frame.get_txt_nombre().setText("");
		frame.get_txt_lanzador().setText("");
		frame.get_btn_buscar_aplicacion().setEnabled(true);
		frame.get_txt_idaplicacion().setEditable(true);
		frame.get_txt_idaplicacion().requestFocusInWindow();
		frame.get_lst_area().setSelectedIndex(-1);
		this.icons=new LinkedList<BufferedImage>();
		this.setIndice(0);
		frame.getCanvas().setImage(null);
		frame.getCanvas().dispose();
		frame.getCanvas().repaint();
		
	}

	/**
	 *carga los parametros del aplicacion seleccionado
	 */
	public void cargar_parametros() {
		String idaplicacion = frame.get_txt_idaplicacion().getText();
		this.cargar_parametros(idaplicacion);

	}

	/**
	 * 
	 * recibe el nombre del aplicacion y lo carga
	 */
	public void cargar_parametros(String val) {
		Object[][] results = data.getaplicacion(val);
		this.setIndice(0);
		if (results != null) {
			if (results.length > 0) {
				String idaplicacion=(String) results[0][0];
				String nombre = (String) results[0][1];
				String area = (String) results[0][2];
				String lanzador = (String) results[0][3];
				
				frame.get_txt_idaplicacion().setText(idaplicacion);
				frame.get_txt_idaplicacion().setEditable(false);
				frame.get_txt_nombre().setText(nombre);
				frame.get_txt_lanzador().setText(lanzador);
				this.seleccionarArea(area);
				this.getIcons(idaplicacion);
				
				
			}
		}
		else
			frame.get_lst_area().setSelectedIndex(-1);
			
	}

	public void seleccionarArea(String area){
		boolean band=false;
		int cont=0,cant=frame.get_lst_area().getItemCount();
		
		while(!band && cont<cant){
			if(frame.get_lst_area().getItemAt(cont).equals(area))
				band=true;
			cont++;
		}
		if(band)
			frame.get_lst_area().setSelectedIndex(cont-1);
	}
	
	public void initializeJCombobox (){
		
		String 	q=data.getAreas();
		System.out.println(q);
		Object[][] results=null;
		try {
			results = data.getResults(q);
			int cont=0, lon=0;
			
			if(results!=null){
				lon=results.length;
				
				while(cont<lon){
					frame.get_lst_area().addItem(results[cont][0]);
					cont++;
				}
				frame.get_lst_area().setSelectedIndex(-1);
			}
			else{
				this.error("error al cargar areas");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	/**
	 * redirecciona el foco en el textfield del idaplicacion
	 */
	public void focus() {
		frame.get_txt_idaplicacion().requestFocusInWindow();
	}


	/**
	 * inicializa el aplicacion
	 */
	public void initialize_aplicacion() {
		
		this._aplicacion = new aplicacion.herramientas.java.evaluadores.Aplicacion() {
			public void cargar(String codigo) {
				cargar_parametros(codigo);
				
				/*
				Object[][] results = this.getInfo(codigo);
				
				*/
			}
		};
		this._aplicacion.setConstructor(this.getConstructor());
	
	}
	
	public boolean evaluarNombre(JTextField tx){
		String nombre=tx.getText();
		
		if(nombre.length()<=0){
			this.error("ingrese un nombre");
			frame.get_txt_nombre().requestFocusInWindow();
			return true;
		}
		else{
			frame.get_txt_lanzador().requestFocusInWindow();
			return false;
		}
	}
	
	public boolean evaluarLanzador(String lanzador){
		
		if(lanzador.length()<=0){
			this.error("ingrese un lanzador");
			frame.get_txt_lanzador().requestFocusInWindow();
			return true;
		}
		else{
			frame.get_txt_nombre().requestFocusInWindow();
			return false;
			}
	}
	
	/**
	 * busca las ocurrencias en del texto ingresado en los idaplicacion
	 */
	public void Buscaraplicacion(JTextField tx) {
		this._aplicacion.Buscar(tx);
	}

	/**
	 * abre la ventana de busqueda para usuarios
	 */
	public void Buscaraplicacion() {
		this._aplicacion.Buscar(frame.get_txt_idaplicacion());
	}

	/**
	 * abre la lista de usuarios bajo el textfield de idaplicacion
	 */
	public void buscaraplicacion(JTextField tx) {
		this._aplicacion.buscar(tx);
	}
 /*
  * recupera los iconos de memoria
  */
	public void getIcons(String idaplicacion){
		this.icons=new LinkedList<BufferedImage>();
		this.setIndice(0);
		BufferedImage _icon=null;
    	int cantidad=data.getIconsCount(idaplicacion);
    	System.out.println("cantidad: "+cantidad);
    	if(cantidad>0){

    		Statement stmt = data.getConnector("MySQL").createStatement();
			String q="SELECT icono FROM b_aplicaciones where idaplicacion like '"+idaplicacion+"' ";
			System.out.println(q);
			try {
				ResultSet resultSet =stmt.executeQuery(q);
				
					 while (resultSet.next()){
						 
						 
						 Blob image = resultSet.getBlob(1);
						  InputStream input = image.getBinaryStream();
				          _icon = javax.imageio.ImageIO.read(input);
				          icons.add(_icon);
				          
				           
					 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				 System.out.println("idaplicacion: "+idaplicacion);
    	}
    	this.setIndice(0);
		this.mostrarIcono(this.getIndice());
		
	}
	
	public void nuevaAplicacion(){
		boolean band=true;
		
		do{String nueva=this.ingresar("ingrese el nombre de la aplicacion");
		String nueva2=nueva.replace(' ','_');
		
		if(!_aplicacion.existe(nueva2)){
			frame.get_txt_idaplicacion().setText(nueva2);
			frame.get_txt_nombre().setText(nueva);
			
			String lanzador=ingresar("ingrese la dirrecion del lanzador");
			
			band=!this.evaluarLanzador(lanzador);
		}
		else
			this.error("ya existe una aplicacion con ese nombre");
		}while(band);
		
		
		
	}

	
	

/*	public boolean evaluarIp(String ip) {
	
		boolean band = true;
		String aux = "";
		
		int in = 0, fin = 0, lon = ip.length(), cont = 0;
		int mincar=10;//variable q determina el numero minimo de caracteres q puede tener la direccion de ip
		int pun=2;//variable q sirve para extraer la primera parte de la dirreccion de ip(contando los primeros 2 puntos)

		if (ip.length() > mincar) {
			while (cont < pun) {
				if (ip.charAt(fin) == '.') {
					cont++;
					fin++;
				} else {
					aux += ip.charAt(fin);
					fin++;
				}
			}

			if (aux.compareTo("192168") != 0) {
				frame.get_txt_ip().requestFocusInWindow();
				this.aviso("ip invalido");
				band = false;
			} else {
				switch (ip.charAt(fin)) {
				case '3':
				case '4':
				case '5':
				case '6': {
					fin += 2;

					try {
						in = new Integer(ip.substring(fin, lon));
						
						 // de 1 a 254 puede osilar el ultimo numero de la direccion
						 
						if (1 > in || in > 254) {
							band = false;
							frame.get_txt_ip().requestFocusInWindow();
							this.aviso("ip invalido");
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
						band = false;
					}
				}
					;
					break;
				default: {
					frame.get_txt_ip().requestFocusInWindow();
					this.aviso("ip invalido");
					band = false;
				}
					;
					break;

				}
			}
		} else {
			band = false;
			frame.get_txt_ip().requestFocusInWindow();
			this.aviso("ip invalido");
		}

		return band;
	}*/

	/**
	 * evalua el textfield para decidir si tiene q buscar el aplicacion, cargar la
	 * configuracion o lanzar un error en caso de q no exista. luego situa el
	 * foco en el textfield del ip
	 */
	public boolean evaluarAplicacion(JTextField tx) {
		String idaplicacion = tx.getText();
		boolean band = true;
		this._aplicacion.setNumeric_code(false);
		if (idaplicacion.compareTo("")==0){
			this._aplicacion.evaluate(frame.get_txt_idaplicacion());
			}
		else{
			if (this._aplicacion.existe(idaplicacion)) {
				if (this._aplicacion.evaluate(frame.get_txt_idaplicacion())){
					frame.get_txt_nombre().requestFocusInWindow();
					band=false;
				}	
			}else{
				frame.get_txt_nombre().requestFocusInWindow();
			}
		}
		
		
		
		return band;
	}

	
//	if (Host.existe(idhost)) {
//		if (Host.evaluate(frame.get_txt_idhost())){
//			if(frame.get_rad_manual().isSelected())
//				frame.get_txt_ip().requestFocusInWindow();
//			else
//				frame.get_txt_extension().requestFocusInWindow();
//			this.unblockButtons();
//		}	
//	}
	


	/**
	 * renombra el ip cargado
	 */
	public void recodificar(){
		String nuevo,viejo=frame.get_txt_idaplicacion().getText();
		boolean band=false;
		
		if(this._aplicacion.existe(viejo)){

			do{
				nuevo=this.ingresar("ingrese un nuevo nombre para el aplicacion");
				if(nuevo.length()>0){
					band=this._aplicacion.existe(nuevo);
					if (band){
						this.error("el nombre ya esta registrado");
					}
				}
				else{
					this.error("ingrese un nombre");
					band =true;
				}
			}while(band);
			
			
			
			data.recodificar(viejo,nuevo);
			
			frame.get_txt_idaplicacion().setText(nuevo);
			

		}
		else{
			this.error("imposible renombrar /n aplicacion inexistente");
		}
		
		
	}
	
	/**
	 * bloquea los botones eliminar, renombrar y el panel de propiedades
	 */
/*	public void block(){
		frame.get_btn_eliminar().setEnabled(false);
		frame.get_btn_rename().setEnabled(false);
		frame.getLockableUI().setLocked(true);
	}*/
	
	/**
	 * desbloquea los botones eliminar y renombrar
	 */
	/*public void unblockButtons(){
		frame.get_btn_rename().setEnabled(true);
		frame.get_btn_eliminar().setEnabled(true);
	}*/
	
	
	public boolean controlarHex(String sub){
		sub=sub.toLowerCase();
		boolean band=true;
		int cont=0;
		
		if(sub.length()==2){
			while(cont<2 && band){

				switch(sub.charAt(cont)){
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':band=true;cont++;break;
				default:band=false;break;
				}
			
			}
			
			if(cont==2)
				band=true;
		}
		else
			band=false;
		
		return band;
	}
	
	public void evaluarString(JTextField tx){
		String text=tx.getText();
		boolean band=false;
		
		if(text.length()<15&&text.length()>=2){
			String sub=text.substring(text.length()-2,text.length());
			if(sub.charAt(sub.length()-1)!='-')
				
				band=this.controlarHex(sub);
		}
		if(band){
			tx.setText(text+"-");
		}
	}


	public int getIndice() {
		return indice;
	}


	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public void runAplication(String clase){
		//aviso("Running Aplicacion "+clase);
		estado="Corriendo Aplicacion "+clase;
		System.out.println("Corriendo Aplicacion "+clase);
		
		try {


		Class c=Class.forName(clase);
		Class v0=Class.forName(_Interface._task_model_v0);
		Class v1=Class.forName(_Interface._task_model_v1);
		Object oc=null;
		if (c!=null){
		boolean error=false;
		try {

		oc=c.newInstance();

		} catch (java.lang.Error e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		error=true;
		if (error){
		displayError("Error Creando Instancia de Aplicacion", e.getMessage(), e.getLocalizedMessage(), e);
		}
		}
		if (oc!=null & !error){
		if (oc.getClass().getSuperclass()==v1){
		this.run_v1(clase);
		}
		if (oc.getClass().getSuperclass()==v0){
		this.run_v0(clase);
		}
		}

		}



		} catch (Exception e) {

		e.printStackTrace();
		displayError("Error En Carga de Aplicacion", 
		e.getMessage(), e.getLocalizedMessage(), e);
		}
		Runnable _execute=new Runnable(){
		public void run(){
		//this.endbar();
		}
		};
		this.invokeLater(_execute);
		}

		/**
		* Este metodo es para mantener la compatibilidad con aplicaciones 
		viejas
		* Hasta que pueda pasarlas a la version por capas. Q es mas facil 
		de mantener
		* Entender y Mejorar!!
		*/
		public void run_v0(String clase){
		//aviso("Ejecutando "+clase+" Tipo V0");

		Object oc=null;



		try {
		Class c=Class.forName(clase);
		oc=c.newInstance();
		} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		} catch (InstantiationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		if (oc!=null){
		visual.Task_Model T=(visual.Task_Model) oc;
		int i=0;
		beta.tools.connector.GTransfer GX=null;

		while (i<this.getConstructor().getParametros().size() & 
		GX==null){
		Object[] p=(Object[]) 
		this.getConstructor().getParametros().get(i);
		String id=(String)p[0];
		Object o=p[1];
		if (id.compareTo("GX")==0){
		GX=(beta.tools.connector.GTransfer)o;
		}
		i++;
		}
/*		if (GX==null){
		GX=new beta.tools.connector.GTransfer();
		Generic _default=this.getConstructor().getConnectionHandler().getDefaultConnector();
		GX.setHost(_default.getHost());
		GX.setPort(_default.getPort());
		GX.setDatabase(_default.getDatabase());
		GX.setUser(_default.getUser());
		GX.setPassword(_default.getPassword());
		GX.setSucursal(_default.getId());
		GX.ConnectSQL(_default.getId());
		}*/
		boolean b=true;//esta_autorizado(iduser,clase);
		if (b){
		String NEW_LINE = System.getProperty("line.separator");
		/*String msg="Esta es una aplicacion vieja."+NEW_LINE;
		msg+="Hasta que no se reprograme. Esta aplicacion 
		quedara inhabilitada por problemas de incompatibilida";
		error(msg);*/

		T.setParameter("GX", GX);
		T.setParameter("iduser", 
		this.getConstructor().getIduser());
		int lon=this.getConstructor().getParametros().size();
		for(i=0;i<lon;i++){
			try {
				Object[] p=(Object[]) 
				this.getConstructor().getParametros().get(i);
				String id=(String)p[0];
				Object o=p[1];
				T.setParameter(id, o);
			}
			catch (Exception e) {
			System.out.println("No se pudo Cargar el parametro "+i+" >"+clase+" "+e.getMessage());
				}
		}



		if (javax.swing.SwingUtilities.isEventDispatchThread()){
		T.run();
		}else{
		final visual.Task_Model _T=T;
		Runnable _execute=new Runnable(){
		public void run(){
		_T.run();
		}
		};
		javax.swing.SwingUtilities.invokeLater(_execute);
		}

		}else {
		error("("+this.getConstructor().getIduser()+") No esta Autorizado");
		T=null;
		}
		}
		}

		public void run_v1(String clase){
		//aviso("Ejecutando "+clase+" Tipo V1 Params:"+this.getConstructor().getParametros().size());"
		Object oc=null;
		try {
		Class c=Class.forName(clase);
		oc=c.newInstance();
		} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		} catch (InstantiationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		if (oc!=null){
		Task_Model T=(Task_Model) oc;
		boolean b=true;//esta_autorizado(iduser,clase);
		int lon=this.getConstructor().getParametros().size();
		if (b){

		for (int i=0;i<lon;i++){

			try {
				Object[] p=(Object[]) 
				this.getConstructor().getParametros().get(i);
				String id=(String)p[0];
				Object o=p[1];
				T.setParameter(id, o);
			} 
			catch (Exception e) {
				System.out.println("No se pudo Cargar el parametro "+i+" >"+clase+" "+e.getMessage());
				e.printStackTrace();
				}
		}

			if (javax.swing.SwingUtilities.isEventDispatchThread()){
				T.run(getConstructor());
			}else{
				final Task_Model _T=T;
				Runnable _execute=new Runnable(){
			
					public void run(){
							_T.run(getConstructor());
					}
				};
				javax.swing.SwingUtilities.invokeLater(_execute);
			}
		}else {
		error("("+this.getConstructor().getIduser()+") No esta Autorizado");
		T=null;
		}
		}

		}

		public void launchApplicacion(String lanzador){
		//String menu="";

		this.runAplication(lanzador);

		}
		
		public void correrAplicacion(){
			String lanzador=frame.get_txt_lanzador().getText();
			
			if(!this.evaluarLanzador(lanzador)){
				this.run_v1(lanzador);
				
			}
		}

}
