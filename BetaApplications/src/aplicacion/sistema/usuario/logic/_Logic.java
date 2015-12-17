package aplicacion.sistema.usuario.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JTextField;





import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;

import aplicacion.sistema.usuario.gui._Frame;
import aplicacion.sistema.usuario.interfaces.*;
import aplicacion.sistema.usuario.logic._Data;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.XML;

public class _Logic extends Logic {

	private _Frame frame;
	private _Data data;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.sistema.autorizacion.constructor._Constructor aplicaciones = null;
	private Object[][] memory = null;

	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	
	public void guardar(){
		//checkear q todo este bien antes de guardar
		DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
		CheckBoxNode raiz = (CheckBoxNode)treeModel.getRoot();
		int selec=this.cantSelecciones(raiz);
		String idvendedor=frame.get_txt_idvendedor().getText();

		System.out.println("selcciones: "+selec);
		if(selec>0){
			if(Vendedor.existe(idvendedor))
				_guardar();	
			else
				this.error("debe seleccionar un vendedor valido antes de guardar");
		}
		else
			_guardar();
	
	}
	
	public void _guardar(){
		boolean error=false;
		String iduser=frame.get_txt_idusuario().getText();
		String nombre=frame.get_txt_nombre().getText();
//		String pass=frame.get_txt_password().getText();
		String superusuario="0";
		DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
		CheckBoxNode raiz = (CheckBoxNode)treeModel.getRoot();
		List<String> lista=new ArrayList<String>();
		//******************************
		lista=this.guardar(raiz,lista);
		int lon=lista.size();
		data.beginTransaction();
		data.clearBatch();
		String idvendedor=frame.get_txt_idvendedor().getText();
		String q=data.getDeleteHost(idvendedor);
		data.addBatch(q);
		
		if(Vendedor.existe(idvendedor)){
			for(int i=0;i<lon;i++){
				
				q=data.getInsertHost(idvendedor,lista.get(i));
				System.out.println(q);
				data.addBatch(q);
			}
		}
		//*********************
		
		if (frame.get_chk_super().isSelected()){
			superusuario="1";
		}
		
		String monitor="0";
		if (frame.get_chk_monitor().isSelected()){
			monitor="1";
		}
		
		String internet="0";
		if (frame.get_chk_internet().isSelected()){
			internet="1";
		}
		
		String asterisk="0";
		if (frame.get_chk_asterisk().isSelected()){
			asterisk="1";
		}
		
		String background="";
//		idvendedor=frame.get_txt_idvendedor().getText();
		String iddeposito=frame.get_txt_iddeposito().getText();
		String unegocio=frame.get_txt_negocio().getText();
		if (data.check_user(iduser)){
			q=data.update(iduser, nombre, background,superusuario,monitor,internet,idvendedor,asterisk,iddeposito,unegocio);
			
		}
		else{
			q=data.insert(iduser, nombre,background,superusuario,monitor,internet,idvendedor,asterisk,iddeposito,unegocio);
		}
		data.addBatch(q);
		error=data.executeBatch();
		if (!error){
			data.commitTransaction();
			aviso("Se Grabo Correctamente");
		}
		else{
			data.rollbackTransaction();
			error("Error Grabando Usuario");
		}
	}
	
	public void aplicaciones(){
		if (aplicaciones!=null){
			aplicaciones.dispose();
		}
		String iduser=frame.get_txt_idusuario().getText();
		if (data.check_user(iduser)){
			aplicaciones = new aplicacion.sistema.autorizacion.constructor._Constructor();
			aplicaciones.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
			aplicaciones.setParameter(aplicacion.sistema.autorizacion.interfaces._parametros._iduser, iduser);
			aplicaciones.build(this.getConstructor());
			aplicaciones.init();	
		}else{
			error("debe cargar un usuario valido");
		}
	}
	/**
	 * elimina el usuario determinado
	 */
	public void delete(){
		String id=frame.get_txt_idusuario().getText();
		if (preguntar("Confirmar","Desea eliminar el usuario "+id+"?")){
			data.delete(id);
			data.delete_aplicaciones(id);
			clean();
		}
	}
	
	/**
	 * libera todos los campos del frame
	 */
	public void clean(){
		frame.get_txt_idusuario().setText("");
		frame.get_txt_nombre().setText("");
		frame.get_txt_idusuario().setEnabled(true);
		frame.get_btn_buscar_usuario().setEnabled(true);
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_iddeposito().setText("");
		frame.get_txt_deposito_descripcion().setText("");
		frame.get_txt_negocio().setText("");
		frame.get_txt_negocio_descripcion().setText("");
		
		frame.get_txt_vendedor_descripcion().setText("");
		frame.get_chk_internet().setSelected(false);
		frame.get_chk_monitor().setSelected(false);
		frame.get_chk_super().setSelected(false);
		frame.get_chk_asterisk().setSelected(false);
		frame.setJTable(null);
		frame.setJTree(null);
	}
	
	/**
	 * carga los datos del usuario desde memoria
	 */
	public void cargar_parametros(){
		String iduser=frame.get_txt_idusuario().getText();
		this.cargar_parametros(iduser);
		this.cargarCajas(iduser);
	}
	
	public void cargar_parametros(String val){
		Object[][] results=data.getParametroSqlite(val);
		if (results!=null){
			if (results.length>0){
				String iduser=(String) results[0][0];
				String nombre=(String) results[0][1];
				String pass=(String) results[0][2];
				pass=this.getDecrypted(pass);
				String background=(String) results[0][3];
				String superusuario=(String) results[0][4];
				String monitor=(String) results[0][5];
				String internet=(String) results[0][6];
				String idvendedor=(String) results[0][7];
				String asterisk=(String) results[0][8];
				String iddeposito=(String) results[0][9];
				String unegocio=(String) results[0][10];
				frame.get_chk_super().setSelected(superusuario.compareTo("1")==0);
				frame.get_chk_monitor().setSelected(monitor.compareTo("1")==0);
				frame.get_chk_internet().setSelected(internet.compareTo("1")==0);
				frame.get_chk_asterisk().setSelected(asterisk.compareTo("1")==0);
				frame.get_txt_nombre().setText(nombre);
				frame.get_txt_idusuario().setEnabled(false);
				frame.get_btn_buscar_usuario().setEnabled(false);
				frame.get_txt_idvendedor().setText(idvendedor);
				if (idvendedor.compareTo("")!=0){
					this.evaluarVendedor(frame.get_txt_idvendedor());	
					this.buildTree(idvendedor);
				}
				frame.get_txt_iddeposito().setText(iddeposito);
				if (iddeposito.compareTo("")!=0){
					this.evaluarDeposito(frame.get_txt_iddeposito());	
				}
				frame.get_txt_negocio().setText(unegocio);
				if (unegocio.compareTo("")!=0){
					this.evaluarNegocio(frame.get_txt_negocio());	
				}
				if (unegocio.compareTo("")!=0){
					this.evaluarDeposito(frame.get_txt_iddeposito());	
				}
				this.cargarCajas(iduser);
				
			
			}
		}
	}
	

	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor=null;
	public void initialize_Vendedor(){
		Vendedor=new aplicacion.herramientas.java.evaluadores.Vendedor(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				String cod=(String) results[0][0];
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_vendedor_descripcion().setText(descripcion);
				frame.get_txt_idusuario().requestFocusInWindow();
			}
		};
		Vendedor.setConstructor(this.getConstructor());
	}
	
	private aplicacion.herramientas.java.evaluadores.Deposito Deposito=null;
	public void initialize_Deposito() {
		Deposito = new aplicacion.herramientas.java.evaluadores.Deposito() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				frame.get_txt_iddeposito().setText(cod);
				frame.get_txt_deposito_descripcion().setText(descripcion);
			}
		};
		Deposito.setConstructor(this.getConstructor());
	}
	
	public void evaluarDeposito(JTextField tx) {
		tx.setText(tx.getText().replaceAll(" ", ""));
		Deposito.evaluate(tx);
	}
	
	private aplicacion.herramientas.java.evaluadores.Negocio Negocio=null;
	public void initialize_Negocio() {
		Negocio = new aplicacion.herramientas.java.evaluadores.Negocio() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				frame.get_txt_negocio().setText(cod);
				frame.get_txt_negocio_descripcion().setText(descripcion);
			}
		};
		Negocio.setConstructor(this.getConstructor());
	}
	
	public void evaluarNegocio(JTextField tx) {
		tx.setText(tx.getText().replaceAll(" ", ""));
		Negocio.evaluate(tx);
	}
	public void reconnect_Vendedor(){
		if (Vendedor!=null){
			Vendedor.setConstructor(this.getConstructor());	
		}
	}
	public void BuscarVendedor(JTextField tx){
		Vendedor.Buscar(tx);
	}
	public void BuscarVendedor(){
		Vendedor.Buscar(frame.get_txt_idvendedor());
	}
	public void buscarVendedor(JTextField tx){
		Vendedor.buscar(tx);
	}
	
	public void evaluarVendedor(JTextField tx){
		tx.setText(tx.getText().replaceAll(" ", ""));
		Vendedor.evaluate(tx);
	}
	
	
	public void focus(){
		frame.get_txt_idusuario().requestFocusInWindow();
	}
	
	public void cargarCajas(String iduser){
		Object[][] results=data.getCajas(iduser);
		this.create_table(results);
	}
	public Object[][] processData(Object[][] results){
		for (int i=0;i<results.length;i++){
			results[i][2]=results[i][2].toString().compareTo("1")==0;
			results[i][3]=results[i][3].toString().compareTo("1")==0;
		}
		return results;
	}
	
	public void updateOrigen(boolean origen,int row){
		String iduser=frame.get_txt_idusuario().getText();
		String idcaja=frame.getJTable().getValueAt(row, 0).toString();
		boolean destino=(Boolean)frame.getJTable().getValueAt(row, 3);
		if (data.isCajaConfigured(iduser, idcaja)){
			data.update(iduser, idcaja, origen, destino);
		}else{
			data.insert(iduser, idcaja, origen, destino);
		}
	}
	
	public void updateDestino(boolean destino,int row){
		String iduser=frame.get_txt_idusuario().getText();
		String idcaja=frame.getJTable().getValueAt(row, 0).toString();
		boolean origen=(Boolean)frame.getJTable().getValueAt(row, 2);
		if (data.isCajaConfigured(iduser, idcaja)){
			data.update(iduser, idcaja, origen, destino);
		}else{
			data.insert(iduser, idcaja, origen, destino);
		}
	}
	
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();
		
		
		col = new Column();
		col.setName("Caja");
		col.setWidth(60);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Descripcion");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Origen");
		col.setWidth(80);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._chk_origen);
		col.setCellEditor(chkce.getCellCheck());
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Destino");
		col.setClass(Boolean.class);
		col.setWidth(80);
		col.setEditable(true);
		chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._chk_destino);
		col.setCellEditor(chkce.getCellCheck());
		
		table.addColumn(col);
		
		
		
		results=this.processData(results);
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 8);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		
		frame.setJTable(table.getTable());
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
	/**
	 * construye el arbol
	 */
	public void buildTreeCheckBoxNode() {
		Runnable _execute=new Runnable(){
			public void run(){
				buildTree("");
			}
		};
		this.invokeLater(_execute);
	}
	
	private void loadToMemory() {

	Object[][] results = data.getHostList();
		
	if (results != null) {
			if (results.length > 0) {
				System.out.println("Clasificaciones encontradas? "
						+ results.length);
				
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
	
	public void load_clases_from_memory(CheckBoxNode abuelo) {
		int i = 0;
		int childs = 0;
		if (memory != null) {
			while (i < memory.length) {
				if (memory[i][2].toString().compareTo(abuelo.getIdclasificacion()) == 0) {
					CheckBoxNode padre = new CheckBoxNode(memory[i][1].toString(), memory[i][0].toString(), (Boolean)memory[i][3]);
					
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
	
	private void buildTree(String idaviso) {
		this.buildTree(idaviso,null);
	}
	
	private void buildTree(String idaviso,String default_iduser) {
		CheckBoxNode raiz = new CheckBoxNode("Usuarios", "0",false);
		this.loadToMemory();
		this.cargar_seleccion_idhost(idaviso);
		if (default_iduser!=null){
			this.modificar_memoria(default_iduser);
		}
		this.load_clases_from_memory(raiz);
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
		checkBoxNodeEditor.setName(_Interface._chk_tree);
		checkBoxNodeEditor.setLogic(this);
		tree.setCellEditor(checkBoxNodeEditor);
		tree.setEditable(true);
		// this.load_clases(raiz);
		//tree.addMouseListener(this.getConstructor().getMouseListener());
		//tree.addKeyListener(this.getConstructor().getKeyListener());
		
		frame.setJTree(tree);	
		
		
		
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
					System.out.println("envcontrado en memoria:("+memory[i][0].toString()+")"+idclasificacion+" "+true);	
					memory[i][3]=true;
				}
				i++;
			}	
		}
		
	}
	
	
	/**
	 *selleciona todos los checkbox hijos del nodo seleccionado 
	 * @param chk
	 * @param b
	 */
	public void seleccionarDesde(CheckBoxNode chk,boolean b){
		int cantH;
		chk.setSelected(b);
		cantH=chk.getChildCount();

		for (int i=0;i<cantH;i++){
				CheckBoxNode hijo=(CheckBoxNode)chk.getChildAt(i);	
			    	
				if(hijo!=null){
					System.out.println("======hijo:"+(i+1)+"/"+cantH+"");
					this.seleccionarDesde(hijo,b);
				}
		}
		
//		DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
//		treeModel.reload(chk);

	}
	
	public void evaluate_checkbox(MyCheckBox chk){
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
					
					this.seleccionarDesde(nodo,chk.isSelected());
				
					
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			System.out.println("Check box tree selection "+tree.getSelectionPath()+" "+chk.isSelected()+" "+chk.getIdclasificacion());
		}
	}
	
	public void cargar_seleccion_idhost(String iduser){
		String[] simulacion_base=data.getUserHostList(iduser);
		for (int i=0;i<simulacion_base.length;i++){
			this.modificar_memoria(simulacion_base[i]);
		}
	}

	/**
	 * guarda en una lista los checkbox seleccionados
	 * @param nodo
	 * @param lista
	 * @return
	 */
	private List<String> guardar(CheckBoxNode nodo,List<String> lista){
		int cantH;
		cantH=nodo.getChildCount();
		
		if (nodo.isSelected()){
			System.out.println("idclasificacion: "+nodo.getIdclasificacion());
			lista.add(nodo.getIdclasificacion());	
	 	}
		
		for(int i=0;i<cantH;i++){
			lista=guardar((CheckBoxNode)nodo.getChildAt(i),lista);	
			}
		
		return lista;
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
			System.out.println(cantSele);
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
		String newPass;
		boolean band;
		//ingresa la nueva clave por teclado
		do{
			newPass=this.requestPassword("Ingrese la nueva password");
			newPass=this.getEncrypted(newPass);
			newPass=newPass.replaceAll("'", "''");
			System.out.println("Pass: "+newPass); 
			if(this.evaluarPass(newPass))
				band=true;
			else
				band=false;
		
		}while(band);

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
	
	/**
	 * verifica la sintaxis de la direccion de mail
	 * @param tx
	 * @return
	 */
	public boolean evaluarEmail(JTextField tx){
		String mail=tx.getText();
		boolean band=false;
		int i=0,lon=mail.length();
		
		try{
			while(!band && i<lon){
				if(mail.charAt(i)=='@' && i>1){
						if(i<lon-1)
							if(mail.charAt(i+1)!='.')//controla si entre el @ y el punto hay caracteres
								band=true;
							else
								band=false;
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
							frame.get_txt_idvendedor().requestFocusInWindow();
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
		
		this.aviso("direcion de mail invalida");
		frame.get_txt_email().requestFocusInWindow();
		frame.get_txt_email().setSelectionStart(0);
		frame.get_txt_email().setSelectionEnd(msj.length());
		
		return false;
		
	}
	
}


