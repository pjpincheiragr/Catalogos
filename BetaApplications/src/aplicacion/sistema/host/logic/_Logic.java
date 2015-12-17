package aplicacion.sistema.host.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.util.Date;

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

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.host.gui._Frame;
import aplicacion.sistema.host.interfaces.*;
import aplicacion.sistema.host.logic._Data;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.XML;

public class _Logic extends Logic {

	private _Frame frame;
	private _Data data;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	//private aplicacion.sistema.autorizacion.constructor._Constructor aplicaciones = null;
	private aplicacion.herramientas.java.evaluadores.Host Host = null;

	public void setFrame(JFrame frame) {
		this.frame = (_Frame) frame;
		super.setFrame(frame);
	}
	

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public boolean evaluar() {
		boolean band;
		String idhost = frame.get_txt_idhost().getText();
		String ip = frame.get_txt_ip().getText();
		
		band = idhost.compareTo("") != 0;
		
		if (!band) {
			this.aviso("idhost invalido");
			frame.get_txt_idhost().requestFocusInWindow();
			frame.get_txt_idhost().setSelectionStart(0);
			frame.get_txt_idhost().setSelectionEnd(ip.length());
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
	}

	/**
	 * guarda un nuevo host
	 */
	public void guardar() {
		boolean ok = evaluar();
		if (ok) {
			boolean error = false;
			String idhost = frame.get_txt_idhost().getText();
			String ip = frame.get_txt_ip().getText();
			String monitor = "0";
			String extension = frame.get_txt_extension().getText();
			String email= frame.get_txt_email().getText();
			String os = frame.get_txt_os().getText();
			String mac = frame.get_txt_macAddress().getText();
		
			if (frame.get_chk_monitoreo().isSelected()) {
				monitor = "1";
			}

			String printer = "0";
			boolean dhcp = false;

			if (frame.get_chk_printer().isSelected()) {
				printer = "1";
			}

			String server = "0";

			if (frame.get_chk_server().isSelected()) {
				server = "1";
			}
			if (frame.get_rad_dhcp().isSelected()) {
				ip = "";
				dhcp = true;
			}

			if (data.check_host(idhost)) {
				error = data.update(idhost, ip, server, monitor, printer,
						extension, dhcp,email,os,mac);
			} else {
				error = data.insert(idhost, ip, server, printer, monitor,
						extension, dhcp,email,os,mac);
			}

			if (!error) {
				aviso("Se Grabo Correctamente");
			} else {
				error("Error Grabando Host");
			}
		} else
			error("Operacion cancelada");

	}

	/**
	 * elimina el host seleccionado
	 */
	public void delete() {
		String id = frame.get_txt_idhost().getText();

		if (preguntar("Confirmar", "Desea eliminar el host " + id + "?")) {
			data.delete(id);
			clean();
		}
	}

	/**
	 * limpia los campos de la ventana
	 */
	public void clean() {
		frame.get_txt_idhost().setText("");
		frame.get_txt_ip().setText("");
		frame.get_btn_buscar_host().setEnabled(true);
		frame.get_chk_monitoreo().setSelected(false);
		frame.get_chk_printer().setSelected(false);
		frame.get_chk_server().setSelected(false);
		frame.get_rad_manual().setSelected(true);
		frame.get_rad_dhcp().setSelected(false);
		frame.get_txt_extension().setText("");
		frame.get_txt_email().setText("");
		frame.get_txt_os().setText("");
		frame.get_txt_idhost().setEditable(true);
		frame.get_txt_macAddress().setText("");
		frame.get_txt_idhost().requestFocusInWindow();
		this.block();
		
	}

	/**
	 *carga los parametros del host seleccionado
	 */
	public void cargar_parametros() {
		String idhost = frame.get_txt_idhost().getText();
		this.cargar_parametros(idhost);
		this.unblockLayer();
		

	}

	/**
	 * 
	 * recibe el nombre del host y lo carga
	 */
	public void cargar_parametros(String val) {
		Object[][] results = data.getHost(val);

		if (results != null) {
			if (results.length > 0) {
				String idhost=(String) results[0][0];
				String ip = (String) results[0][1];
				String server = (String) results[0][2];
				String printer = (String) results[0][3];
				String monitor = (String) results[0][4];
				String extension = (String) results[0][5];
				String dhcp = (String) results[0][6];
				String email = (String) results[0][7];
				String os = (String) results[0][8];
				String mac = (String) results[0][9];
				frame.get_chk_monitoreo().setSelected(
						monitor.compareTo("1") == 0);
				frame.get_chk_server().setSelected(server.compareTo("1") == 0);
				frame.get_chk_printer()
						.setSelected(printer.compareTo("1") == 0);
				frame.get_txt_ip().setText(ip);
				frame.get_txt_extension().setText(extension);
				if (dhcp.compareTo("1") == 0)
					frame.get_rad_dhcp().setSelected(true);
				else
					frame.get_rad_manual().setSelected(true);
				
				frame.get_txt_idhost().setEditable(false);
				frame.get_txt_email().setText(email);
				frame.get_txt_os().setText(os);
				frame.get_txt_macAddress().setText(mac);
				this.block();
				this.unblockLayer();
				
			}
		}
	}

	/**
	 * redirecciona el foco en el textfield del idhost
	 */
	public void focus() {
		frame.get_txt_idhost().requestFocusInWindow();
	}

	public void rad() {
		frame.get_rad_manual().setSelected(true);
	}

	/**
	 * inicializa el host
	 */
	public void initialize_Host() {
		Host = new aplicacion.herramientas.java.evaluadores.Host() {
			public void cargar(String codigo) {
				cargar_parametros(codigo);
				
				/*
				Object[][] results = this.getInfo(codigo);
				
				*/
			}
		};
		Host.setConstructor(this.getConstructor());
	
	}

	/**
	 * busca las ocurrencias en del texto ingresado en los idhost
	 */
	public void BuscarHost(JTextField tx) {
		Host.Buscar(tx);
	}

	/**
	 * abre la ventana de busqueda para usuarios
	 */
	public void BuscarHost() {
		Host.Buscar(frame.get_txt_idhost());
	}

	/**
	 * abre la lista de usuarios bajo el textfield de idhost
	 */
	public void buscarHost(JTextField tx) {
		Host.buscar(tx);
	}

	/**
	 * evalua la direccion de ip y 
	 * situa el foco en el textField de la extension
	 * @param tx
	 */
	public boolean evaluarIp(String ip) {
	
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
						/*
						 * de 1 a 254 puede osilar el ultimo numero de la direccion
						 */
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
	}

	/**
	 * evalua el textfield para decidir si tiene q buscar el host, cargar la
	 * configuracion o lanzar un error en caso de q no exista. luego situa el
	 * foco en el textfield del ip
	 */
	public boolean evaluarHost(JTextField tx) {
		String idhost = tx.getText();
		boolean band = true;
		Host.setNumeric_code(false);
		if (idhost.compareTo("")==0){
			Host.evaluate(frame.get_txt_idhost());
			}
		else{
			if (Host.existe(idhost)) {
				if(Host.evaluate(frame.get_txt_idhost())){
					if(frame.get_rad_manual().isSelected())
						frame.get_txt_ip().requestFocusInWindow();
					else
						frame.get_txt_extension().requestFocusInWindow();
					this.unblockButtons();
				}	
			}else{
				this.unblockLayer();
				frame.get_txt_ip().requestFocusInWindow();
				frame.get_btn_eliminar().setEnabled(false);
				frame.get_btn_rename().setEnabled(false);
			}	
		}
		
		
		
		return band;
	}

	/**
	 * evalua el textfield para decidir si tiene q buscar la extension o lanzar
	 * un error en caso de q no sea valida.
	 */
	public void evaluarExtension(JTextField tx) {
		String ext = tx.getText();
		boolean band = ext.compareTo("") == 0;
		
		if (!band) {
			int aux;
			band = true;
			try {
				aux = new Integer(ext);
			} catch (Exception e) {
				band = false;
			}
		}

		if (!band) {
			this.error("numero de extension invalido");
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(ext.length());
		} else
			frame.get_txt_email().requestFocusInWindow();
	}

	/**
	 * pasa el foco del checkbox de etiqueta al de actualizacion
	 */
	public void evaluarEtiqueta() {
		frame.get_chk_server().requestFocusInWindow();
	}

	/**
	 * pasa el foco del checkbox de actualizacion al de monitoreo
	 */
	public void evaluarServer() {
		frame.get_chk_monitoreo().requestFocusInWindow();
	}

	/**
	 * evalua el radio button manual
	 * @param rad
	 */
	public void evaluarManual(JRadioButton rad) {
		this.disable(rad);
		disable(frame.get_rad_dhcp());
		if (rad.isSelected()) {
			frame.get_rad_dhcp().setSelected(false);
			frame.get_txt_ip().setEnabled(true);
			frame.get_txt_ip().requestFocusInWindow();

			// if(idhost.compareTo("")==0){
			// this.aviso("idhost invalido");
			// band=false;
			// }
		} else {
			rad.setSelected(true);
		}
		this.enable(rad);
		enable(frame.get_rad_dhcp());

	}

	/**
	 * desabilita el radio button seleccionado
	 * @param rad
	 */
	public void disable(JRadioButton rad) {
		if (rad!=null){
			if (this.getConstructor()!=null){
				if (this.getConstructor().getItemListener()!=null){
					rad.removeItemListener(this.getConstructor().getItemListener());
				}	
			}
			
		}
		
		
		
	}

	/**
	 * habilita el radio button seleccionado
	 * @param rad
	 */
	public void enable(JRadioButton rad) {
		if (this.getConstructor()!=null){
			if (this.getConstructor().getItemListener()!=null){
				rad.addItemListener(this.getConstructor().getItemListener());
			}	
		}
	}

	/**
	 * evalua el radio button idhcp
	 * @param rad
	 */
	public void evaluarIdhcp(JRadioButton rad) {
		this.disable(rad);
		if (frame.get_rad_manual()!=null){
			disable(frame.get_rad_manual());
		}
		                                  
		
		if (rad.isSelected()) {
			frame.get_rad_manual().setSelected(false);
			frame.get_txt_ip().setEnabled(false);
			frame.get_txt_extension().requestFocusInWindow();
		} else {
			rad.setSelected(true);
		}
		this.enable(rad);
		enable(frame.get_rad_manual());
	}

	/**
	 * verifica la sintaxis del ip
	 * @param tx
	 */
	public void evaluarIP(JTextField tx) {
		String ip = tx.getText();
		
		if (evaluarIp(ip)) {
			frame.get_txt_extension().requestFocusInWindow();
		} else {
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(ip.length());
		}
	}
	
	/**
	 * renombra el ip cargado
	 */
	public void recodificar(){
		String nuevo,viejo=frame.get_txt_idhost().getText();
		boolean band=false;
		
		if(Host.existe(viejo)){

			do{
				nuevo=this.ingresar("ingrese un nuevo nombre para el host");
				if(nuevo.length()>0){
					band=Host.existe(nuevo);
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
			
			frame.get_txt_idhost().setText(nuevo);
			

		}
		else{
			this.error("imposible renombrar /n host inexistente");
		}
		
		
	}
	
	/**
	 * bloquea los botones eliminar, renombrar y el panel de propiedades
	 */
	public void block(){
		frame.get_btn_eliminar().setEnabled(false);
		frame.get_btn_rename().setEnabled(false);
		frame.getLockableUI().setLocked(true);
	}
	
	/**
	 * desbloquea los botones eliminar y renombrar
	 */
	public void unblockButtons(){
		frame.get_btn_rename().setEnabled(true);
		frame.get_btn_eliminar().setEnabled(true);
	}
	
	/**
	 * desbloquea el panel de propiedades
	 */
	public void unblockLayer(){
		frame.getLockableUI().setLocked(false);
	}

	/**
	 * evalua la sintaxis de la direccion de email
	 * @param tx
	 * @return boolean
	 */
	public boolean evaluarEmail(JTextField tx){
		String mail=tx.getText();
		boolean band=false;
		int i=0,lon=mail.length();
		
		if(lon>0){
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
								frame.get_txt_os().requestFocusInWindow();
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
		}
		else{
			band=true;
			frame.get_txt_os().requestFocusInWindow();
			}
		return band;
	}
	
	/**
	 * evalua la sintaxis del TextField del sistema operativo
	 * @param tx
	 * @return
	 */
	public boolean evaluarSistema(JTextField tx){
		boolean band=false;
		String os= tx.getText();

		if(os.length()>0){
			band=true;
			frame.get_txt_macAddress().requestFocusInWindow();
			}
		else{
			band=false;
			this.aviso("ingrese un sistema operativo");
			frame.get_txt_os().requestFocusInWindow();
			frame.get_txt_os().setSelectionStart(0);
			frame.get_txt_os().setSelectionEnd(os.length());	
		}
		return band;
	}
	
	public boolean errorMail(String msj){
		
		this.aviso("direcion de mail invalida");
		frame.get_txt_email().requestFocusInWindow();
		frame.get_txt_email().setSelectionStart(0);
		frame.get_txt_email().setSelectionEnd(msj.length());
		
		return false;
		
	}
	
	public void evaluarMacAddress(JTextField tx){
		
		String Address=tx.getText();
		String sub="";
		int lon=Address.length();
		int cont=0,in=0,fin=0;
		boolean band=true;
		char car=' ';
		
		
		if(lon==17){
			while(fin<lon && band){
				car=Address.charAt(fin);
				if(car=='-' || fin==16){
					if(fin==16)
						sub=Address.substring(in, Address.length());
					else
						sub=Address.substring(in, fin);
					band=this.controlarHex(sub);
					
					in=fin+1;
					
				}
				
					
				if(band)
					fin++;
			}
		}
		else 
			band=false;
		
		if(!band){
			this.error("ingrese una direccion de mac valida");
			frame.get_txt_macAddress().requestFocusInWindow();
			frame.get_txt_macAddress().setSelectionStart(fin-2);
			frame.get_txt_macAddress().setSelectionEnd(fin);
		}
		else
			frame.get_chk_printer().requestFocusInWindow();
	}
	
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

}
