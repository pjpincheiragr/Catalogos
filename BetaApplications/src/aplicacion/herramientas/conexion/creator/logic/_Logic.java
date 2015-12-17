package aplicacion.herramientas.conexion.creator.logic;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.*;
import aplicacion.herramientas.conexion.*;
import aplicacion.herramientas.conexion.conectores.MsSQL;
import aplicacion.herramientas.conexion.creator.gui.*;
import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.herramientas.conexion.creator.logic.*;
import javax.swing.*;

import aplicacion.herramientas.java.sortableselector.*;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data data){
		this.data=(_Data) data;
		super.setData(data);
	}
	
	public void clean(){
		frame.get_txt_host().setText("");
		frame.get_txt_idconexion().setText("");
		frame.get_txt_password().setText("");
		frame.get_txt_port().setText("");
		frame.get_txt_database().setText("");
		frame.get_txt_usuario().setText("");
		frame.get_txt_ssh_host().setText("");
		frame.get_txt_ssh_port().setText("");
		frame.get_txt_ssh_user().setText("");
		frame.get_txt_ssh_password().setText("");
		this.cargar_conexiones_mysql();
	}
	public void buscar(){
		this.buscar(frame.get_txt_idconexion());
	}
	public void buscar(JTextField tx){
		aplicacion.herramientas.java.sortableselector.constructor._Constructor 
		CC=new aplicacion.herramientas.java.sortableselector.constructor._Constructor()
		{
			@Override
			protected void initialize_logic(){
				_logic=new 
				aplicacion.herramientas.java.sortableselector.logic._Logic(){
					@Override
					public void Close(JTable table,int row){
						super.Close(table, row);
						frame.get_txt_idconexion().requestFocusInWindow();
					}
				};
			}
		};
		CC.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.herramientas.java.sortableselector.logic._Logic 
		logic=(aplicacion.herramientas.java.sortableselector.logic._Logic)CC.getLogic();

		columna c = new columna();
		Filtro f = new Filtro();
		c = new columna();
		c.setNombre("id");
		c.setAlias("id");
		c.setWidth(80);
		c.setColumnField(tx);
		c.setMaster(true);
		
		logic.addColumn(c);

		c = new columna();
		c.setNombre("host");
		c.setAlias("host");
		
		c.setWidth(80);
		c.setMaster(false);
		logic.addColumn(c);
		
				
		logic.addFromTable("Connection");
		
		f = new Filtro();
		f.setNombre("id");
		f.setAlias("id");
		f.setWidth(60);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("host");
		f.setAlias("host");
		f.setWidth(60);
		logic.addFilter(f);
		logic.setIdconector(_Interface._beta_database);
		logic.addOrder("id");
		logic.init();
	}
	public void nuevo(){
		
	}
	
	public void cargarTipos(){
		frame.get_lst_conexion().removeAllItems();
		frame.get_lst_conexion().addItem("MsSQL");
		frame.get_lst_conexion().addItem("MySQL");
		frame.get_lst_conexion().addItem("SQLite");
	}
	public void connectionLocal(){
		MsSQL mssql=new MsSQL(this.getConstructor()){
			protected void connected(){
				aviso("Conexion OK");
			}
		};
		mssql.setHost(frame.get_txt_host().getText());
		int port=new Integer(frame.get_txt_port().getText());
		mssql.setPort(port);
		mssql.setUser(frame.get_txt_usuario().getText());
		mssql.setPassword(frame.get_txt_password().getText());
		mssql.setDatabase(frame.get_txt_database().getText());
		mssql.setInstance(frame.get_txt_instance().getText());
		mssql.setTdsVersion("8.0");
		mssql.setId(frame.get_txt_idconexion().getText());
		mssql.connect();
	}
	public void connectionTunnel(){
		MsSQL mssql=new MsSQL(this.getConstructor()){
			protected void connected(){
				aviso("Conexion OK");
			}
		};
		mssql.setHost("127.0.0.1");
		mssql.setPort(4444);
		mssql.setUser(frame.get_txt_usuario().getText());
		mssql.setPassword(frame.get_txt_password().getText());
		mssql.setDatabase(frame.get_txt_database().getText());
		mssql.setInstance(frame.get_txt_instance().getText());
		mssql.setTdsVersion("8.0");
		mssql.setId(frame.get_txt_idconexion().getText());
		mssql.connect();
	}
	
	public void crearTunnel(String ssh_host,String ssh_port,String ssh_user,String ssh_password,String host,String port){
		Tunnel t=new Tunnel(){
			public void connected(){
				connectionTunnel();
			}
		};
		t.setTunnelHost(ssh_host);
		t.setSSHPort(new Integer(ssh_port));
		t.setTunnelLocalPort(4444);
    	t.setTunnelRemotePort(new Integer(port));
    	t.setTunnelRemoteHost(host);
    	t.setUser(ssh_user);
    	t.setPassword(ssh_port);
    	t.go();
	}
	
	public void cargar_conexiones_mysql(){
		Object[][] results=data.get_conexiones("MySQL");
		frame.get_lst_mysql().removeAllItems();
		frame.get_lst_mysql().addItem("");
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					String id=(String) results[i][0];
					frame.get_lst_mysql().addItem(id);	
				}
				
			}
		}
	}
	private void setTipo(String tipo){
		for (int i=0;i<frame.get_lst_conexion().getItemCount();i++){
			String item=frame.get_lst_conexion().getItemAt(i).toString();
			if (item.compareTo(tipo)==0){
				frame.get_lst_conexion().setSelectedIndex(i);
			}
		}
	}
	
	private void setMySQL(String id){
		for (int i=0;i<frame.get_lst_mysql().getItemCount();i++){
			String item=frame.get_lst_mysql().getItemAt(i).toString();
			if (item.compareTo(id)==0){
				frame.get_lst_mysql().setSelectedIndex(i);
			}
		}
	}
	
	public void login(){
		if (frame.get_lst_conexion().getSelectedIndex()==0){
			//MsSQL
			this.connectionLocal();
		}
	}
	
	public void guardar(){
		String idconexion=frame.get_txt_idconexion().getText();
		String host=frame.get_txt_host().getText();
		String port=frame.get_txt_port().getText();
		String database=frame.get_txt_database().getText();
		String instance=frame.get_txt_instance().getText();
		String user=frame.get_txt_usuario().getText();
		String password=frame.get_txt_password().getText();
		String ssh_host=frame.get_txt_ssh_host().getText();
		String ssh_port=frame.get_txt_ssh_port().getText();
		String ssh_user=frame.get_txt_ssh_user().getText();
		String ssh_password=frame.get_txt_ssh_password().getText();
		String mysql=frame.get_lst_mysql().getSelectedItem().toString();
		String tipo=frame.get_lst_conexion().getSelectedItem().toString();
		boolean error=false;
		if (this.existe(idconexion)){
			error=data.update(idconexion, host, port, database, instance, user, password, tipo, ssh_host, ssh_port, ssh_user, ssh_password,mysql);
		}else{
			error=data.insert(idconexion, host, port, database, user, password, tipo, ssh_host, ssh_port, ssh_user, ssh_password,mysql);
		}
		if (error){
			error("Error al grabar la conexion");
		}else {
			aviso("Se grabo Correctamente la conexion");
			clean();
		}
	}
	
	
	public void eliminar(){
		String idconexion=frame.get_txt_idconexion().getText();
		boolean error=false;
		if (this.existe(idconexion)){
			if (preguntar("Confirmar","Desea Eliminar la conexion "+idconexion+"? ")){
				error=data.delete(idconexion);
				if (error){
					error("Error al eliminar la conexion");
				}else {
					aviso("Se Elimino Correctamente la conexion");
				}
				clean();
			}
		}else{
			
		}
		
	}
	
	public boolean existe(String idconexion){
		boolean ok=false;
		Object[][] results=data.get_conexion(idconexion);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public void cargar(String idconexion){
		
		Object[][] results=data.get_conexion(idconexion);
		if (results!=null){
			if (results.length>0){
				this.cargar_conexiones_mysql();
				String host=(String) results[0][0];
				String port=(String) results[0][1];
				String database=(String) results[0][2];
				String user=(String) results[0][3];
				String password=(String) results[0][4];
				String tipo=(String) results[0][5];
				String ssh_host=(String) results[0][6];
				String ssh_port=(String) results[0][7];
				String ssh_user=(String) results[0][8];
				String ssh_password=(String) results[0][9];
				String mysql=(String) results[0][10];
				String instance=(String) results[0][11];
				frame.get_txt_idconexion().setText(idconexion);
				frame.get_txt_host().setText(host);
				frame.get_txt_port().setText(port);
				frame.get_txt_database().setText(database);
				frame.get_txt_instance().setText(instance);
				frame.get_txt_usuario().setText(user);
				frame.get_txt_password().setText(password);
				frame.get_txt_ssh_host().setText(ssh_host);
				frame.get_txt_ssh_port().setText(ssh_port);
				frame.get_txt_ssh_user().setText(ssh_user);
				frame.get_txt_ssh_password().setText(ssh_password);
				setTipo(tipo);
				setMySQL(mysql);
			}else{
				if (preguntar("Confirmar","La conexion "+idconexion+" es inexistenete. Crea una nueva?")){
					nuevo();
				}else {
					
				}
				
				
			}
		}
		
	}
	
	public void _evaluar_codigo_conexion(JTextField tx){
		String aux=tx.getText();
		if (aux.compareTo("")!=0){
				cargar(aux);
					
		}else {
			error("Ingrese un ID de Conexion existente. Busque con F5");
			
		}
	}
	
	
}

