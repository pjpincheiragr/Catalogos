package aplicacion.flota.auxilio.logic;

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
import aplicacion.flota.auxilio.gui._Frame;
import aplicacion.flota.auxilio.interfaces.*;
import aplicacion.flota.auxilio.logic._Data;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.XML;
import aplicacion.herramientas.java.evaluadores.*;
import aplicacion.herramientas.java.buscadores.Fecha;

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
	private aplicacion.herramientas.java.buscadores.Fecha bFecha = null;
	
	public void setFrame(JFrame frame) {
		this.frame = (_Frame) frame;
		super.setFrame(frame);
	}
	

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}


	public void evaluarIdauxilio(JTextField tx){
		String idauxilio=tx.getText();
		System.out.println("id length: "+idauxilio.length());
		if(idauxilio.length()>0){
			if(/*buscar en tabla*/true){
				frame.get_txt_sucursal().requestFocusInWindow();
			}
			else{
				this.error("vehiculo inexistente");
				frame.get_txt_idauxilio().requestFocusInWindow();
			}
		}else{
			this.error("ingrese un id valido");
			frame.get_txt_idauxilio().requestFocusInWindow();
		}
		
	}
	
	public void evaluarSucursal(JTextField tx){
		String sucursal=tx.getText();
		
		if(sucursal.length()>0){
			if(/*buscar en tabla*/true){
				frame.get_txt_numero().requestFocusInWindow();
			}
			else{
				this.error("ingrese una surcursal valida");
				frame.get_txt_sucursal().requestFocusInWindow();
			}
		}else{
			this.error("ingrese una surcursal valida");
			frame.get_txt_sucursal().requestFocusInWindow();
		}
		
	}
	

	public void evaluarNumero(JTextField tx){
		String numero=tx.getText();
		
		if(numero.length()>0){
			if(/*buscar en tabla*/true){
				frame.get_lst_letra().requestFocusInWindow();
			}
			else{
				this.error("ingrese un numero valido");
				frame.get_txt_numero().requestFocusInWindow();
			}
		}else{
			this.error("ingrese un numero valido");
			frame.get_txt_numero().requestFocusInWindow();
		}
	}
	
	
	public void evaluarLetra(JComboBox com){
	String letra=com.getEditor().getItem().toString();
	
		frame.get_txt_idunidad().requestFocusInWindow();
		
	}
	
	public void evaluarIdunidad(JTextField tx){
		String idunidad=tx.getText();
		
		if(idunidad.length()>0){
			if(/*buscar en tabla*/true){
				frame.get_txt_dominio().requestFocusInWindow();
			}
			else{
				this.error("ingrese un idunidad valido");
				frame.get_txt_idunidad().requestFocusInWindow();
			}
		}else{
			this.error("ingrese un idunidad valido");
			frame.get_txt_idunidad().requestFocusInWindow();
		}
	}
	
	public void evaluarDominio(JTextField tx){
		String dominio=tx.getText();
		
		if(dominio.length()>0){
			if(/*buscar en tabla*/true){
				frame.get_txt_detalle().requestFocusInWindow();
			}
			else{
				this.error("ingrese un dominio valido");
				frame.get_txt_dominio().requestFocusInWindow();
			}
		}else{
			this.error("ingrese un dominio valido");
			frame.get_txt_dominio().requestFocusInWindow();
		}
	}

	public void evaluarDetalle(JTextField tx){
		
		frame.get_lst_provincia().requestFocusInWindow();
	}
	
	public void evaluarProvincia(JComboBox comb){
		
		frame.get_lst_localidad().requestFocusInWindow();
	}
	
	public void evaluarLocalidad(JComboBox comb){
		
		frame.get_txt_ubicacion().requestFocusInWindow();
	}
	
	public void evaluarUbicacion(JTextField tx){
		String ubicacion=tx.getText();
		
		if(ubicacion.length()>0){
			frame.get_txt_idunidadAuxilio().requestFocusInWindow();
		}
	}
	
	public void evaluarIdunidadAuxilio(JTextField tx){
		String idunidadAuxilio=tx.getText();
		
		if(idunidadAuxilio.length()>0){
			if(/*buscar en tabla*/true){
				//carga los datos de la unidad de auxilio
				frame.get_txt_idunidadReemplazo().requestFocusInWindow();
			}
			else{
				this.error("ingrese un id valido");
				frame.get_txt_idunidadAuxilio().requestFocusInWindow();
			}
		}else{
			this.error("ingrese un id valido");
			frame.get_txt_idunidadAuxilio().requestFocusInWindow();
		}
	}
	
	public void evaluarDominioAuxilio(JTextField tx){
		String dominioAuxilio=tx.getText();
		
		if(dominioAuxilio.length()>0){
			if(/*buscar en tabla*/true){
				frame.get_txt_detalleAuxilio().requestFocusInWindow();
			}
			else{
				this.error("ingrese un dominio valido");
				frame.get_txt_dominioAuxilio().requestFocusInWindow();
			}
		}else{
			this.error("ingrese un dominio valido");
			frame.get_txt_dominioAuxilio().requestFocusInWindow();
		}
	}
	
	public void evaluarDetalleAuxilio(JTextField tx){
	
		frame.get_txt_idunidadReemplazo().requestFocusInWindow();
	
	}

	public void evaluarIdunidadReemplazo(JTextField tx){
		String idreemplazo=tx.getText();
		
		if(idreemplazo.length()>0){
			if(/*buscar en tabla*/true){
				//carga los datos de la unidad de reemplazo
				frame.get_txt_chofer().requestFocusInWindow();
			}
			else{
				this.error("ingrese un id valido");
				frame.get_txt_idunidadReemplazo().requestFocusInWindow();
			}
		}else{
			this.error("ingrese un id valido");
			frame.get_txt_idunidadReemplazo().requestFocusInWindow();
		}
	}
	
	public void evaluarDominioReemplazo(JTextField tx){
		String dominioReemplazo=tx.getText();
		
		if(dominioReemplazo.length()>0){
			if(/*buscar en tabla*/true){
				frame.get_txt_detalleReemplazo().requestFocusInWindow();
			}
			else{
				this.error("ingrese un dominio valido");
				frame.get_txt_dominioReemplazo().requestFocusInWindow();
			}
		}else{
			this.error("ingrese un dominio valido");
			frame.get_txt_dominioReemplazo().requestFocusInWindow();
		}
	}
	
	public void evaluarDetalleReemplazo(){
		
		frame.get_txt_chofer().requestFocusInWindow();
	}
	
	public void evaluarChofer(JTextField tx){
		String chofer=tx.getText();
		
		if(chofer.length()>0){
			if(/*buscar en tabla*/true){
				frame.get_txt_choferAuxilio().requestFocusInWindow();
				//carga los datos del chofer
				}
			else{
				this.error("chofer no registrado");
				frame.get_txt_chofer().requestFocusInWindow();
			}
		}else{
			this.error("ingrese un nombre valido");
			frame.get_txt_chofer().requestFocusInWindow();
		}
	}
	
	
	public void evaluarChoferAuxilio(JTextField tx){
		String chofer=tx.getText();
		
		if(chofer.length()>0){
			if(/*buscar en tabla*/true){
				frame.get_lst_tipoFalla().requestFocusInWindow();
				//carga los datos del chofer
				}
			else{
				this.error("chofer no registrado");
				frame.get_txt_choferAuxilio().requestFocusInWindow();
			}
		}else{
			this.error("ingrese un nombre valido");
			frame.get_txt_choferAuxilio().requestFocusInWindow();
		}
	}
	
		
	public void evaluarFalla(JComboBox comb){
		
		frame.get_lst_tipoFalla().requestFocusInWindow();
	}
	
	public void focus() {
		frame.get_txt_idauxilio().requestFocusInWindow();
	}
	
	public void BuscarFecha(JTextField tx) {
		if (bFecha == null) {
			bFecha = new aplicacion.herramientas.java.buscadores.Fecha(this
					.getConstructor());
		}

		bFecha.Buscar(tx);

	}
	
	public void BuscarFecha() {
		JTextField tx = frame.get_txt_fecha();
		this.BuscarFecha(tx);
	}
	
	public void buscar(){
		this.aviso("buscar idauxilio");
	}
	

	public void buscarAuxilio(){
		this.aviso("buscar auxilio");
	}

	public void buscarChofer(){
		this.aviso("buscar chofer");
	}

	public void buscarChoferAuxilio(){
		this.aviso("buscar chofer auxilio");
	}

	public void buscarReemplazo(){
		this.aviso("buscar reemplazo");
	}

	public void buscarUnidad(){
		this.aviso("buscar unidad");
	}
	
	public void eliminar(){
		this.aviso("eliminar");
	}

	public void guardar(){
		this.aviso("guardando");
	}

	
	public void run_v1(String clase){
		//aviso("Ejecutando "+clase+" Tipo V1 Params:"+this.getConstructor().getParametros().size());"
		Object oc=null;
		try {
			System.out.println("clase: "+clase);
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
	
//	public void correrAplicacion(){
//		String lanzador=frame.get_txt_lanzador().getText();
//		
//		if(!this.evaluarLanzador(lanzador)){
//			this.run_v1(lanzador);
//			
//		}
//	}
	
	
	public void editarChofer(){
		String lanzador="aplicacion.flota.chofer.launcher._Launcher";
		 this.run_v1(lanzador);
	}

	public void editarUnidad(){
		String lanzador="aplicacion.flota.vehiculo.launcher._Launcher";
		 this.run_v1(lanzador);
	}

	
	
}
