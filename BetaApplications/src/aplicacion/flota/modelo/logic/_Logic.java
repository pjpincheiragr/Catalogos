package aplicacion.flota.modelo.logic;

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
import aplicacion.flota.modelo.gui._Frame;
import aplicacion.flota.modelo.interfaces.*;
import aplicacion.flota.modelo.logic._Data;
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
	private List<BufferedImage> images=new LinkedList<BufferedImage>();
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
					//System.out.println("Drag&Drop>"+it.next().toString());
					
				File f=null;	
				try {
					f = (File) it.next( );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (f!=null){
					System.out.println("Adding Foto DND >"+f.getAbsolutePath());
					
					this.addFoto(f.getPath());		
				}else{
					//f=new File(it.next().toString());
				}
			
				
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
			System.out.println("hay o no hay?"+images.isEmpty());
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
							
						
					}
					else
						error("No che....");
						
			}else{
				error("No che!");
			}
		
	
				
	}


	public int getIndice() {
		return indice;
	}


	public void setIndice(int indice) {
		this.indice = indice;
	}

	public boolean comparar(BufferedImage img1,BufferedImage img2){
		ImageCompare ic = new ImageCompare(img1, img2);
		ic.setParameters(2, 2, 2, 2);
		ic.setDebugMode(2);
		ic.compare();
		return ic.match();
	}
	
	/*
	  * recupera los iconos de memoria
	  */
//		public void getimages(String idaplicacion){
//			this.images=new LinkedList<BufferedImage>();
//			this.setIndice(0);
//			BufferedImage _icon=null;
//	    	int cantidad=data.getimagesCount(idaplicacion);
//	    	System.out.println("cantidad: "+cantidad);
//	    	if(cantidad>0){
//
//	    		Statement stmt = data.getConnector("MySQL").createStatement();
//				String q="SELECT icono FROM b_aplicaciones where idaplicacion like '"+idaplicacion+"' ";
//				System.out.println(q);
//				try {
//					ResultSet resultSet =stmt.executeQuery(q);
//					
//						 while (resultSet.next()){
//							 
//							 
//							 Blob image = resultSet.getBlob(1);
//							  InputStream input = image.getBinaryStream();
//					          _icon = javax.imageio.ImageIO.read(input);
//					          images.add(_icon);
//					          
//					           
//						 }
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//					 System.out.println("idaplicacion: "+idaplicacion);
//	    	}
//	    	this.setIndice(0);
//			this.mostrarIcono(this.getIndice());
//			
//		}
		
		
		public void mostrar_foto(int i) {
			if(images!=null){
				if(images.size()>0){
					if (i >= 0 & i <= images.size()) {
						
						BufferedImage bi = images.get(i);
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
	
		public void focus() {
			frame.get_txt_marca().requestFocusInWindow();
		}

		public void buscar_imagen(){
			JFileChooser JF = new JFileChooser();
			int rx=JF.showOpenDialog(frame);
			if (rx == JFileChooser.APPROVE_OPTION) {
				
			File file = JF.getSelectedFile();
			
			
			this.addFoto(file.getAbsolutePath());
			
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
		
}
