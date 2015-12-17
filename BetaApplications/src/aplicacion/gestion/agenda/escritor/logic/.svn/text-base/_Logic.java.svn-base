package aplicacion.gestion.agenda.escritor.logic;

import java.awt.Font;
import java.awt.dnd.DropTarget;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.buscadores.Host;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.gestion.agenda.escritor.gui._Frame;
import aplicacion.gestion.agenda.escritor.interfaces._Interface;
import aplicacion.gestion.agenda.escritor.logic.*;


import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;




public class _Logic extends Logic {
	private _Frame frame = null;
	private _Data data = null;
	private boolean nuevo=false;
	private Object[][] memoryUsuarios = null;
	private Object[][] memory = null;
	private String formato="dd-MM-yyyy HH:mm:ss";
	private aplicacion.gestion.agenda.gestion.constructor._Constructor gestion=null;
	public _Logic() {
		
	}

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void clean() {
		frame.setJTree(null);
		frame.setJTree1(null);
		frame.setJTable_asociado(null);
		frame.get_txt_agenda().setText("");
		frame.get_txt_titulo().setText("");
		frame.get_txt_mensaje().setText("");
		frame.get_txt_idcreador().setText("");
		frame.get_txt_creador_descripcion().setText("");
		frame.get_txt_idevento().setText("");
		frame.get_txt_idevento().setEditable(true);
		frame.get_txt_idevento().setEnabled(true);
		frame.get_chk_mensaje().setSelected(true);
		nuevo=false;
		frame.get_txt_idevento().requestFocusInWindow();
	}

	
	public void init(){
		this.init("");
	}
	
	public void init(String idvendedor){
		this.clean();
		if (idvendedor.compareTo("")!=0){
			frame.get_txt_idcreador().setText(idvendedor);
			this.evaluarVendedor(frame.get_txt_idcreador());
		}
	}
	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_agenda().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy HH:mm:ss"));
	}

	public _Data getData() {
		return this.data;
	}

	private aplicacion.herramientas.java.evaluadores.Hora Hora = null;

	public void initialize_Hora() {
		Hora = new aplicacion.herramientas.java.evaluadores.Hora() {
			public void cargar(JTextField tx) {
				frame.get_txt_titulo().requestFocusInWindow();
			}
		};
		Hora.setConstructor(this.getConstructor());
	}

	public void BuscarHora(JTextField tx) {
		Hora.Buscar(tx);
	}

	public void BuscarHora() {
		Hora.Buscar(frame.get_txt_agenda());
	}

	public void evaluarHora(JTextField tx) {
		Hora.evaluate(tx);
	}
	
	
	
	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor=null;
	public void initialize_Vendedor(){
		Vendedor=new aplicacion.herramientas.java.evaluadores.Vendedor(){
			public void cargar(JTextField tx){
				String codigo=tx.getText();
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				String cod=(String) results[0][0];
					
					frame.get_txt_idcreador().setText(cod);
					frame.get_txt_creador_descripcion().setText(descripcion);
					frame.get_txt_titulo().requestFocusInWindow();
				
			}
		};
		Vendedor.setConstructor(this.getConstructor());
	}
	
	public void BuscarVendedor(JTextField tx){
		Vendedor.Buscar(tx);
	}
	public void BuscarVendedor(){
		Vendedor.Buscar(frame.get_txt_idcreador());
	}
	public void buscarVendedor(JTextField tx){
		Vendedor.buscar(tx);
	}
	
	public void evaluarVendedor(JTextField tx){
		tx.setText(tx.getText().replaceAll(" ", ""));
		Vendedor.evaluate(tx);
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
	
	public boolean elegir_vendedor() {
		boolean ok = false;
		String idvendedor = this.validar_vendedor();
		frame.get_txt_idcreador().setText(idvendedor);
		if (idvendedor.compareTo("") != 0) {
			ok = true;
			this.evaluarVendedor(frame.get_txt_idcreador());
			frame.get_txt_idcreador().setEditable(false);
						
		} else {
			ok = false;
		}
		return ok;
	}

	public void evaluate_idaviso(JTextField tx){
		String idaviso=tx.getText();
		if (idaviso.compareTo("")!=0){
			if (nuevo){
				String proximo=data.getProximoPGCorrecto();
				if (idaviso.compareTo(proximo)==0){
					frame.get_txt_idevento().setEnabled(false);
				}else{
					proximo=data.getProximoPGCorrecto();
					frame.get_txt_idevento().setText(proximo);
					frame.get_txt_idevento().setEnabled(false);
				}
				frame.get_txt_agenda().requestFocusInWindow();
				this.buildTreeUsuarios("");
				String iduser=frame.get_txt_idcreador().getText();
				this.buildTree("", iduser);
			}else{
				if (data.exist(idaviso)){
					this.cargarAviso(idaviso);
				}else{
					error("Aviso Inexistente");
				}
			}
		}
	}
	
	public void cargarAviso(String idaviso){
		Object[][] results=data.getAviso(idaviso);
		if (results!=null){
			if (results.length>0){
				String _idaviso=(String)results[0][0];
				String titulo=(String)results[0][1];
				String mensaje=(String)results[0][2];
				String agenda=(String)results[0][3];
				String idcreador=(String)results[0][4];
				String privado=(String)results[0][5];
				String pantalla=(String)results[0][6];
				String validacion=this.validar_vendedor();
				System.out.println("Validacion:"+validacion+" vs "+idcreador);
				if (validacion.compareTo(idcreador)==0){
					frame.get_txt_idevento().setEditable(false);
					frame.get_txt_idevento().setEnabled(false);
					frame.get_txt_agenda().setText(agenda);
					frame.get_txt_idcreador().setText(idcreador);
					frame.get_txt_titulo().setText(titulo);
					frame.get_txt_mensaje().setText(mensaje);
					frame.get_chk_privado().setSelected(privado.compareTo("1")==0);
					frame.get_chk_mensaje().setSelected(pantalla.compareTo("1")==0);
					this.evaluarVendedor(frame.get_txt_idcreador());
					this.buildTreeUsuarios(idaviso);
					this.buildTree(_idaviso, validacion);
					this.cargarAsociado(_idaviso);
				}else{
					error("No puede ver este mensaje");
				}
				
			}
		}
	}
	
	public void cargarAviso(String idaviso,String validacion){
		
		Object[][] results=data.getAviso(idaviso,validacion);
		if (results!=null){
			if (results.length>0){
				String _idaviso=(String)results[0][0];
				String titulo=(String)results[0][1];
				String mensaje=(String)results[0][2];
				String agenda=(String)results[0][3];
				String idcreador=(String)results[0][4];
				String privado=(String)results[0][5];
				String pantalla=(String)results[0][6];
				if (validacion.compareTo(idcreador)==0){
					frame.get_txt_idevento().setText(idaviso);
					frame.get_txt_idevento().setEditable(false);
					frame.get_txt_idevento().setEnabled(false);
					frame.get_txt_agenda().setText(agenda);
					frame.get_txt_idcreador().setText(idcreador);
					frame.get_txt_titulo().setText(titulo);
					frame.get_txt_mensaje().setText(mensaje);
					frame.get_chk_mensaje().setSelected(pantalla.compareTo("1")==0);
					frame.get_chk_privado().setSelected(privado.compareTo("1")==0);
					this.evaluarVendedor(frame.get_txt_idcreador());
					this.buildTreeUsuarios(idaviso);
					this.buildTree(_idaviso, validacion);
					this.cargarAsociado(_idaviso);
				}else{
					error("No puede ver este mensaje");
				}
				
			}
		}
	}
	private int seleccion_destinatarios(){
		return this.getDestinatariosUsuarios().size();
	}
	
	private List<String> getDestinatariosUsuarios(){
		
		
		DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
		CheckBoxNode raiz = (CheckBoxNode)treeModel.getRoot();
			
		List<String> lista=new ArrayList<String>();
		
		
		lista=this.guardarUsuarios(raiz,lista);
		
		return lista;
	
	}

	private List<String> guardarUsuarios(CheckBoxNode nodo,List<String> lista){
		int cantH;
			if (nodo.isSelected()){
			System.out.println("idclasificacion: "+nodo.getIdclasificacion());
			lista.add(nodo.getIdclasificacion());	
			}
			cantH=nodo.getChildCount();
 
			for(int i=0;i<cantH;i++){
				
				lista=guardarUsuarios((CheckBoxNode)nodo.getChildAt(i),lista);	

			}
		return lista;
	}

	private int seleccion_categorias(){
		return this.getDestinatariosCategorias().size();
	}
	
	private List<String> getDestinatariosCategorias(){
		
		
		DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree1().getModel();
		CheckBoxNode raiz = (CheckBoxNode)treeModel.getRoot();
			
		List<String> lista=new ArrayList<String>();
		
		
		lista=this.guardarCategorias(raiz,lista);
		
		return lista;
	
	}

	private List<String> guardarCategorias(CheckBoxNode nodo,List<String> lista){
		int cantH;
			if (nodo.isSelected()){
			System.out.println("idclasificacion: "+nodo.getIdclasificacion());
			lista.add(nodo.getIdclasificacion());	
			}
			cantH=nodo.getChildCount();
 
			for(int i=0;i<cantH;i++){
				
				lista=guardarCategorias((CheckBoxNode)nodo.getChildAt(i),lista);	

			}
		return lista;
	}

	
	public void obtener_proximo_cpte() {
		String numero = data.getProximoPGCorrecto();
		frame.get_txt_idevento().setText(numero);
		nuevo=true;
	}
		
	public void nuevo() {
		String fecha=data.getSystemDateTime();
		this.nuevo(fecha,"");		
	}
	
	public void nuevo(String fecha,String idvendedor) {
		this.clean();
		boolean ok=false;;
		if (idvendedor.compareTo("")!=0){
			frame.get_txt_idcreador().setText(idvendedor);
			this.evaluarVendedor(frame.get_txt_idcreador());
			ok=true;
		}else{
			ok=this.elegir_vendedor();	
		}
		
		if (ok){
		this.obtener_proximo_cpte();
		this.evaluate_idaviso(frame.get_txt_idevento());
		
		String idcreador=frame.get_txt_idcreador().getText();
		this.buildTreeUsuarios("",idcreador);
		this.buildTree("", idcreador);
		if (fecha!=null){
			frame.get_txt_agenda().setText(fecha);	
		}
		
		frame.get_txt_agenda().requestFocusInWindow();
		}else{
			clean();
		}
		
	}
	public void evaluar_titulo(JTextField tx){
		String titulo=tx.getText();
		if (titulo.compareTo("")!=0){
			frame.get_txt_mensaje().requestFocusInWindow();
		}
	}
	
	public void evaluar_mensaje(JTextArea tx){
		String titulo=tx.getText();
		if (titulo.compareTo("")!=0){
			frame.get_btn_guardar().requestFocusInWindow();
		}
	}
	private void _eliminar(String idaviso,String iduser){
		data.beginTransaction();
		data.clearBatch();
		String q=data.getEliminar(idaviso);
		data.addBatch(q);
		q=data.getEvent(idaviso, iduser, _Interface._event_updated);
		data.addBatch(q);
		boolean error=data.executeBatch();
		if (!error){
			data.commitTransaction();
			aviso("Se Elimino El Aviso");
			if (this.gestion!=null){
				aplicacion.gestion.agenda.gestion.logic._Logic Logic=
					(aplicacion.gestion.agenda.gestion.logic._Logic)gestion.getLogic();
				Logic.goCargar();
			
			}
			this.exit_command();
		} else {
			data.rollbackTransaction();
			error("Error Eliminando Aviso");
		}
	}
	
	public void eliminar(){
		if (!nuevo){
			if (elegir_vendedor()){
				String idaviso=frame.get_txt_idevento().getText();
				String titulo=frame.get_txt_titulo().getText();
				String mensaje=frame.get_txt_mensaje().getText();
				String idcreador=frame.get_txt_idcreador().getText();
				String agenda=frame.get_txt_agenda().getText();
				if (this.autorizado(idaviso, idcreador)){
					this._eliminar(idaviso, idcreador);
				}
			}	
		}
		
	}
	
	private void _guardar(String idaviso,String titulo,String mensaje,String idcreador,String agenda,String privado,String pantalla,String leido,String idcategoria){
		String q="";
		data.beginTransaction();
		List<String> current=new ArrayList<String>();
		Object[][] results=data.getUserList();
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					current.add((String)results[i][0]);	
				}
			}
		}
		
		if (nuevo){
			int n=0;
			boolean existe=data.exist(idaviso);
			while (n<3 & existe){
				this.obtener_proximo_cpte();
				idaviso=frame.get_txt_idevento().getText();
				existe=data.exist(idaviso);
				n++;
			}
			if (!existe){
				q=data.getInsert(idaviso, titulo, mensaje, idcreador, agenda,privado,pantalla);
			}
		}else{
			
			q=data.getUpdate(idaviso, titulo, mensaje, agenda,privado,pantalla);
		}		
		data.clearBatch();
		
		data.addBatch(q);
		System.out.println("Guardar Aviso >"+q);
		if (data.existeCategoria(idaviso, idcreador)){
			q=data.updateCategoria(idaviso, idcreador, idcategoria);
			
		}else{
			q=data.insertCategoria(idaviso, idcreador, idcategoria);
		}
		System.out.println("Categoria Update >"+q);
		data.addBatch(q);
		boolean readed=false;
		readed=leido.compareTo("1")==0;
		
		List<String> usuarios=this.getDestinatariosUsuarios();
		for (int i=0;i<current.size();i++){
			boolean found=false;
			int j=0;
			while (j<usuarios.size() &!found){
				found=current.get(i).compareTo(usuarios.get(j))==0;
				if (!found){
					j++;	
				}
			}
			if (!found){
				q=data.getDeleteAvisoUsers(idaviso, current.get(i));
				data.addBatch(q);
			}
		}
		for (int i=0;i<usuarios.size();i++) {
			if (data.existAvisoUsers(idaviso, usuarios.get(i))){
				q=data.getUpdateAvisoUsers(idaviso, usuarios.get(i), agenda, pantalla);
			}else{
				q=data.getInsertAvisoUser(idaviso, usuarios.get(i),agenda,pantalla);	
			}
			
			data.addBatch(q);	
		}
		
		if (nuevo){
			q=data.getUpdatePG();
			data.addBatch(q);	
		}else{
			
			q=data.getEvent(idaviso, idcreador, _Interface._event_updated);
			data.addBatch(q);
		}
		if (readed){
			q=data.getUpdate(idaviso, idcreador, agenda, leido, pantalla);
			data.addBatch(q);	
		}
		boolean error=data.executeBatch();
		if (!error){
			data.commitTransaction();
			nuevo=false;
			aviso("Se Grabo Correctamente");
			if (this.gestion!=null){
				aplicacion.gestion.agenda.gestion.logic._Logic Logic=
					(aplicacion.gestion.agenda.gestion.logic._Logic)gestion.getLogic();
				Logic.goCargar();
			
			}
			this.exit_command();
		}else{
			data.rollbackTransaction();
			error("Error Grabando");
		}
	}
	
	public String getRoll(String fecha,int months,int days,int hours,int minutes) {
		Date today = new Convertidor().getDateWithFormat(formato, formato, fecha);
		java.util.GregorianCalendar _date = new java.util.GregorianCalendar();
		_date.setTime(today);
		if (months!=0){
			_date.add(Calendar.MONTH, months);	
		}
		if (days!=0){
			_date.add(Calendar.DAY_OF_MONTH, days);	
		}
		if (hours!=0){
			_date.add(Calendar.HOUR_OF_DAY, hours);	
		}
		if (minutes!=0){
			_date.add(Calendar.MINUTE, minutes);	
		}
		// _today.add(Calendar.DAY_OF_YEAR, days);
		Date before = _date.getTime();
		String s2 = new Convertidor().getDateWithFormat(formato, before);
		return s2;
	}
	
	
	public void evaluar_posponer(JComboBox cb){
		int option=cb.getSelectedIndex();
		String fecha=data.getSystemDateTime();
		if (Hora.esCorrecta(fecha)){
			if (option==1){
				fecha=this.getRoll(fecha, 0, 0, 0, 5);
			}
			if (option==2){
				fecha=this.getRoll(fecha, 0, 0, 0, 10);
			}
			if (option==3){
				fecha=this.getRoll(fecha, 0, 0, 0, 15);
			}
			if (option==4){
				fecha=this.getRoll(fecha, 0, 0, 0, 30);
			}
			if (option==5){
				fecha=this.getRoll(fecha, 0, 0, 1, 0);
			}
			if (option==6){
				fecha=this.getRoll(fecha, 0, 0, 2, 0);
			}
			if (option==7){
				fecha=this.getRoll(fecha, 0, 1, 0, 0);
			}
			frame.get_txt_agenda().setText(fecha);
		}
		
	}
	public boolean autorizado(String idaviso,String idupdater){
		boolean ok=true;
		if (!nuevo){
			if (data.exist(idaviso)){
				ok=false;
				Object[][] results=data.getAviso(idaviso);
				if (results!=null){
					if (results.length>0){
						String _idaviso=(String)results[0][0];
						String titulo=(String)results[0][1];
						String mensaje=(String)results[0][2];
						String agenda=(String)results[0][3];
						String idcreador=(String)results[0][4];
						System.out.println("Validacion:"+idupdater+" vs "+idcreador);
						if (idupdater.compareTo(idcreador)==0){
							ok=true;
						}
					}
				}
			}else{
				ok=true;
			}
		}
		return ok;
	}
	
	public void guardar(){
		if (elegir_vendedor()){
			String idaviso=frame.get_txt_idevento().getText();
			String titulo=frame.get_txt_titulo().getText();
			String mensaje=frame.get_txt_mensaje().getText();
			
			String idcreador=frame.get_txt_idcreador().getText();
			String agenda=frame.get_txt_agenda().getText();
			String privado="0";
			if (frame.get_chk_privado().isSelected())privado="1";
			String pantalla="0";
			if (frame.get_chk_mensaje().isSelected())pantalla="1";
			String leido="0";
			if (frame.get_chk_leido().isSelected())leido="1";
			String idcategoria="";
			if (this.getDestinatariosCategorias().size()>0){
				idcategoria = this.getDestinatariosCategorias().get(0);
			}
			
			if (Vendedor.existe(idcreador)){
					if (Hora.esCorrecta(agenda)){
							if (titulo.compareTo("")!=0){
								if (this.seleccion_destinatarios()>0){
									
										if (this.autorizado(idaviso, idcreador)){
											
											this._guardar(idaviso, titulo, mensaje,  idcreador, agenda,privado,pantalla,leido,idcategoria);	
										}else{
											error("No puede modificar este mensaje");
										}	
									
										
								}else{
									error("Debe seleccionar al menos 1 destinatario");
								}
								
								
							}else{
								error("Titulo Nulo");	
							}
								
						
								
					}else{
						error("Error en fecha");
					}
					
				}else{
					error("Creador Invalido");
				}
				
		}else{
			error("Operacion Cancelada");
		}
		
		
		
	}
	
	
	public void buildTreeCheckBoxNodeUsuarios() {
		Runnable _execute=new Runnable(){
			public void run(){
				buildTreeUsuarios("");
			}
		};
		this.invokeLater(_execute);
	}
	
	

	private void loadToMemoryUsuarios() {

		
		Object[][] results = data.getUserList();
		if (results != null) {
			if (results.length > 0) {
				System.out.println("Clasificaciones encontradas? "
						+ results.length);
				
				memoryUsuarios = new Object[results.length][4];
				for (int i = 0; i < results.length; i++) {
					for (int j = 0; j < 3; j++) {
						memoryUsuarios[i][j] = (String) results[i][j];
					}
					memoryUsuarios[i][3] = results[i][3].toString().compareTo("1")==0;
				}
				
			}
		}
		
	}
	
	public void load_clases_from_memoryUsuarios(CheckBoxNode abuelo) {
		int i = 0;
		int childs = 0;
		if (memoryUsuarios != null) {
			while (i < memoryUsuarios.length) {
				if (memoryUsuarios[i][2].toString().compareTo(abuelo.getIdclasificacion()) == 0) {
					CheckBoxNode padre = new CheckBoxNode(memoryUsuarios[i][1].toString(), memoryUsuarios[i][0].toString(), (Boolean)memoryUsuarios[i][3]);
					
					this.load_clases_from_memory(padre);
					DefaultTreeModel treeModel = (DefaultTreeModel) frame
							.getJTree().getModel();
					treeModel.insertNodeInto(padre, abuelo, childs);
					childs++;
				}
				i++;
			}
		}

	}
	
	public void cargar_seleccion_idaviso_usuarios(String idaviso){
		String[] simulacion_base=data.getAvisoUserList(idaviso);
		for (int i=0;i<simulacion_base.length;i++){
			this.modificar_memoria_usuarios(simulacion_base[i]);
		}
	}
	
	private void modificar_memoria_usuarios(String idclasificacion){
		System.out.println("modificacion memoria:"+idclasificacion+" "+true);
		boolean found=false;
		int i=0;
		if (memoryUsuarios!=null){
			while (i<memoryUsuarios.length & !found){
				System.out.println("buscando en memoria:("+memoryUsuarios[i][0].toString()+")"+idclasificacion+" "+true);
				found=(memoryUsuarios[i][0].toString().compareTo(idclasificacion)==0);
				if (found){
					System.out.println("envcontrado en memoria:("+memoryUsuarios[i][0].toString()+")"+idclasificacion+" "+true);	
					memoryUsuarios[i][3]=true;
				}
				i++;
			}	
		}
		
	}
	private void buildTreeUsuarios(String idaviso) {
		this.buildTreeUsuarios(idaviso,null);
	}
	private void buildTreeUsuarios(String idaviso,String default_iduser) {
		CheckBoxNode raiz = new CheckBoxNode("Usuarios", "0",false);
		this.loadToMemoryUsuarios();
		this.cargar_seleccion_idaviso_usuarios(idaviso);
		if (default_iduser!=null){
			this.modificar_memoria_usuarios(default_iduser);
		}
		this.load_clases_from_memoryUsuarios(raiz);
		DefaultTreeModel treeModel = new DefaultTreeModel(raiz);
		treeModel.addTreeModelListener(this.getConstructor()
				.getTreeModelListener());
		JTree tree = new JTree(treeModel);
		
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this.getConstructor()
				.getTreeSelectionListener());
		tree.setShowsRootHandles(true);
		tree.setName(_Interface._tree);
		CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		tree.setCellRenderer(renderer);
		CheckBoxNodeEditor checkBoxNodeEditor=new CheckBoxNodeEditor(tree);
		checkBoxNodeEditor.setLogic(this);
		checkBoxNodeEditor.setName(_Interface._chk_user);
		tree.setCellEditor(checkBoxNodeEditor);
		tree.setEditable(true);
		// this.load_clases(raiz);
		//tree.addMouseListener(this.getConstructor().getMouseListener());
		//tree.addKeyListener(this.getConstructor().getKeyListener());
		
		frame.setJTree(tree);	
		
		
		
	}
	
	public void deseleccionar(CheckBoxNode chk,CheckBoxNode selection){
		int cantH;
		if (chk.getIdclasificacion()!=selection.getIdclasificacion()){
			chk.setSelected(false);
		}
		cantH=chk.getChildCount();
		for (int i=0;i<cantH;i++){
				CheckBoxNode hijo=(CheckBoxNode)chk.getChildAt(i);	
			    	

				if(hijo!=null){
					
					this.deseleccionar(hijo, selection);
				}
			
		}
		DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree1().getModel();
		treeModel.reload(chk);
	}
	
	public void seleccionarDesdeUsuarios(CheckBoxNode chk,boolean b){
		int cantH;
		chk.setSelected(b);
		cantH=chk.getChildCount();
		for (int i=0;i<cantH;i++){
				CheckBoxNode hijo=(CheckBoxNode)chk.getChildAt(i);	
			    	

				if(hijo!=null){
					System.out.println("======hijo:"+(i+1)+"/"+cantH+"");
					this.seleccionarDesdeUsuarios(hijo,b);
				}
			
		}
		DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
		treeModel.reload(chk);
	}
	
	public void evaluate_checkbox(MyCheckBox chk) {
		JTree tree = null;
		if (chk.getParent() instanceof JTree) {
			tree = (JTree) chk.getParent();
			try {
				tree.stopEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			
		}

		if (tree != null) {
			try {

				CheckBoxNode nodo = (CheckBoxNode) tree.getSelectionPath()
						.getLastPathComponent();
				if (!nodo.isRoot()){
					nodo.setSelected(chk.isSelected());
					DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree1().getModel();
					CheckBoxNode raiz = (CheckBoxNode)treeModel.getRoot();
					this.deseleccionar(raiz, nodo);
				}
				
					
				
				
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			System.out.println("Check box tree selection "
					+ tree.getSelectionPath() + " " + chk.isSelected() + " "
					+ chk.getIdclasificacion());
		}
	}

	
	public void evaluate_checkbox_Usuarios(MyCheckBox chk){
		JTree tree=null;
		if (chk.getParent() instanceof JTree){
			tree=(JTree) chk.getParent();
			try {
				tree.stopEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
			//e.printStackTrace();
			}
		}
	
		if (tree!=null){
			try {
				
//				
				//

					CheckBoxNode nodo=(CheckBoxNode)tree.getSelectionPath().getLastPathComponent();
					System.out.println("Nodo >"+nodo+" "+nodo.isSelected());
					
					this.seleccionarDesdeUsuarios(nodo,chk.isSelected());
				
					
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			System.out.println("Check box tree selection "+tree.getSelectionPath()+" "+chk.isSelected()+" "+chk.getIdclasificacion());
		}
	}

	public void cargar_posponer(){
		frame.get_lst_posponer().removeItemListener(this.getConstructor().getItemListener());
		frame.get_lst_posponer().removeAllItems();
		frame.get_lst_posponer().addItem("");
		frame.get_lst_posponer().addItem("5 Minutos");
		frame.get_lst_posponer().addItem("10 Minutos");
		frame.get_lst_posponer().addItem("15 Minutos");
		frame.get_lst_posponer().addItem("30 Minutos");
		frame.get_lst_posponer().addItem("1 Hora");
		frame.get_lst_posponer().addItem("2 Horas");
		//frame.get_lst_posponer().addItem("Proximo Turno");
		frame.get_lst_posponer().addItem("1 Dia");
		frame.get_lst_posponer().addItemListener(this.getConstructor().getItemListener());
	}
	
	/**
	 * fecha|idtransferencia|origen|destino
	 * 
	 * @param results
	 */
	private void create_table_asociado(Object[][] results) {

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("tc");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("idcomprobante");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);


		table.setData(results);
		// table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.setName(_Interface._table_asociado);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		frame.setJTable_asociado(_table);
	}
	
	public void crearAsociado(List<String[]> asociados) {
		Object[][] results = new String[asociados.size()][2];
		for (int i=0;i<asociados.size();i++){
			String[] tmp=asociados.get(i);
			results[i]=tmp;
		}
		frame.setJTable_asociado(null);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_asociado(results);
			}
		}
	}
	
	private void cargarAsociado(String idaviso) {
		Object[][] results = data.getAsociados(idaviso);
		frame.setJTable_asociado(null);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_asociado(results);
			}
		}
	}

	public aplicacion.gestion.agenda.gestion.constructor._Constructor getGestion() {
		return gestion;
	}

	public void setGestion(
			aplicacion.gestion.agenda.gestion.constructor._Constructor gestion) {
		this.gestion = gestion;
	}
	
	public void load_clases_from_memory(CheckBoxNode abuelo) {
		int i = 0;
		int childs = 0;
		if (memory != null) {
			while (i < memory.length) {
				if (memory[i][2].toString().compareTo(
						abuelo.getIdclasificacion()) == 0) {
					CheckBoxNode padre = new CheckBoxNode(memory[i][1]
							.toString(), memory[i][0].toString(),
							(Boolean) memory[i][3]);
					this.load_clases_from_memory(padre);
					DefaultTreeModel treeModel = (DefaultTreeModel) frame
							.getJTree().getModel();
					treeModel.insertNodeInto(padre, abuelo, childs);
					childs++;
				}
				i++;
			}
		}

	}

	public void cargar_seleccion_idaviso_categorias(String idaviso,String iduser) {
		String[] simulacion_base = data.getAvisoCategoriaList(idaviso,iduser);
		for (int i = 0; i < simulacion_base.length; i++) {
			this.modificar_memoria(simulacion_base[i]);
		}
	}

	private void modificar_memoria(String idclasificacion) {
		System.out.println("modificacion memoria:" + idclasificacion + " "
				+ true);

		int i = 0;
		boolean found=false;
		
		if (memory!=null){
			while (i<memory.length & !found){
				System.out.println("buscando en memoria:("+memory[i][0].toString()+")"+idclasificacion+" "+true);
				found=(memory[i][0].toString().compareTo(idclasificacion)==0);
				if (found){
					System.out.println("envcontrado en memoria:("+memory[i][0].toString()+")"+idclasificacion+" "+true);	
					memory[i][3]=true;
				}
				i++;
			}	
		}

	}


	private void buildTree(String idaviso, String iduser) {
		CheckBoxNode raiz = new CheckBoxNode("Categorias", "0", false);
		this.loadToMemory(iduser);
		this.cargar_seleccion_idaviso_categorias(idaviso, iduser);
		this.load_clases_from_memory(raiz);
		DefaultTreeModel treeModel = new DefaultTreeModel(raiz);
		treeModel.addTreeModelListener(this.getConstructor()
				.getTreeModelListener());
		JTree tree = new JTree(treeModel);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		tree.setEditable(true);
		tree.setShowsRootHandles(true);

		tree.setName(_Interface._tree_categorias);
		CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		//renderer.setLogic(this);
		tree.setCellRenderer(renderer);
		CheckBoxNodeEditor checkBoxNodeEditor = new CheckBoxNodeEditor(tree);
		checkBoxNodeEditor.setLogic(this);
		checkBoxNodeEditor.setName(_Interface._chk_categorias);
		tree.setCellEditor(checkBoxNodeEditor);
		tree.addMouseListener(this.getConstructor().getMouseListener());
		final JTree _tree = tree;
		Runnable _execute = new Runnable() {
			public void run() {
				frame.setJTree1(_tree);
			}
		};
		this.invokeLater(_execute);

	}
	
	private void loadToMemory(String iduser) {
		
		String q = "select id,clasificacion,padre,0,background from b_categorias_avisos where iduser like '"
				+ iduser + "' order by padre ,clasificacion ";
		Object[][] results = data.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				System.out.println("Clasificaciones encontradas? "
						+ results.length);

				memory = new Object[results.length + 1][5];
				for (int i = 0; i < results.length; i++) {
					for (int j = 0; j < 5; j++) {
						memory[i][j] = (String) results[i][j];
					}
					memory[i][3] = false;

				}
				memory[results.length][0] = "";
				memory[results.length][1] = "Sin Clasificar";
				memory[results.length][2] = "0";
				memory[results.length][3] = false;
				memory[results.length][4] = "";

			}
		}

	}


}