package aplicacion.herramientas.conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.sql.ConnectionPoolDataSource;
import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.herramientas.conexion.pool.MiniConnectionPoolManager;
import aplicacion.modelo.constructor.*;

public class ConnectionHandler {
	protected List<MiniConnectionPoolManager> pools;
	protected List<Generic> conectores = null;
	private int Default = 0;
	private int maxConnections = 200;

	public ConnectionHandler() {
		conectores = new ArrayList<Generic>();
	}

	public ConnectionHandler Clone() {
		/*System.out.println("Clonando ConnectionHandler");
		ConnectionHandler clone = new ConnectionHandler();
		
		for (int i = 0; i < conectores.size(); i++) {
			System.out.println("Clonando Conector "+i+"> "+conectores.get(i).getId());
			clone.addConector(conectores.get(i).Clone());
		}
		clone.setDefault(this.getDefaultConnector().getId());
		clone.startConnections();
		
		 System.out.println("Clonado Terminado");
		*/
		return this;
	}

	public void setConstructor(Constructor C) {
		for (int i = 0; i < conectores.size(); i++) {
			// System.out.println("Iniciando Conexion del conector "+conectores.get(i).getId());
			conectores.get(i).setConstructor(C);
		}
	}

	public List<Generic> getConnectores() {
		return this.conectores;
	}
	public Generic removeConnector(String id) {
		Generic c = null;
		for (int i = 0; i < conectores.size(); i++) {
			if (conectores.get(i).getId().compareTo(id) == 0) {
				c = conectores.remove(i);
			}
		}
		return c;
	}
	public Generic getConnector(String id) {
		Generic c = null;
		System.out.println("Conectores?"+conectores.size());
		for (int i = 0; i < conectores.size(); i++) {
			System.out.println("Conector?"+conectores.get(i).getId());
			if (conectores.get(i).getId().compareTo(id) == 0) {
				c = conectores.get(i);
			}
		}
		return c;
	}

	public void addConector(Generic c) {
		if (this.getConnector(c.getId()) == null) {
			// System.out.println("Agregando Conector "+c.getId()+" a Connection Handler");
			conectores.add(c);
		} else {
			Conector old = this.getConnector(c.getId());
			this.conectores.remove(old);
			conectores.add(c);
		}
	}
	
	public Generic getDefaultConnector() {
		Generic g = null;
		if (conectores != null) {
			if (!conectores.isEmpty()) {
				g = conectores.get(Default);
				//System.out.println("Default Connector: "+g.getId());
			}

		}
		return g;
	}

	private int getDefault() {
		return this.Default;
	}

	public void setDefault(String id) {
		Generic c = null;
		for (int i = 0; i < conectores.size(); i++) {
			if (conectores.get(i).getId().compareTo(id) == 0) {
				c = conectores.get(i);
			}
		}
		List<Generic> tmp = new ArrayList<Generic>();
		tmp.add(c);
		for (int i = 0; i < conectores.size(); i++) {
			if (conectores.get(i).getId().compareTo(id) != 0) {
				tmp.add(conectores.get(i));
			}
		}
		conectores = tmp;
	}

	public void connect() {
		Generic c = this.getDefaultConnector();
		c.connect();
	}

	public boolean connected() {
		return this.getDefaultConnector().isConnected();
	}

	public void startConnectionPools() {
		pools=new ArrayList<MiniConnectionPoolManager>();
		for (int i = 0; i < conectores.size(); i++) {
		ConnectionPoolDataSource dataSource = conectores.get(i).getConnectionPoolDataSource();
			if (dataSource!=null){
				MiniConnectionPoolManager pool = new MiniConnectionPoolManager(
						dataSource, maxConnections);
				
				Connection conn = null;
				try {
					try {
						conn = pool.getConnection();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("initDb connected");
				} finally {
					if (conn != null)
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				System.out.println("initDb done");
				pools.add(pool);
			}
		}
	}

	private static void initDb() throws SQLException {

	}

	public void startConnections() {
		for (int i = 0; i < conectores.size(); i++) {

			//if (!conectores.get(i).isConnected()) {
				// System.out.println("Iniciando Conexion del conector "+conectores.get(i).getId());
				conectores.get(i).setDebugMode(true);
				
				boolean ok=false;
				if (conectores.get(i).getPoolType()){
					if (conectores.get(i).getConnectionPool()==null){
						
						MiniConnectionPoolManager pool;
						try {
							pool = new MiniConnectionPoolManager(conectores.get(i).getConnectionPoolDataSource(),maxConnections);
							conectores.get(i).setConnectionPool(pool);
							ok= conectores.get(i).connect();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						System.out.println("("+conectores.get(i).getId()+") Active Connections "+conectores.get(i).getConnectionPool().getActiveConnections()+" ");
					}
				}else{
					ok= conectores.get(i).connect();	
				}
				
				 
				if (!ok) {
					/*
					 * ok=this.conectores.get(i).getConstructor().getLogic().preguntar
					 * ("Confirmar",
					 * "Se perdio la conexion. Intenta Reconexion?"); if (ok){
					 * conectores.get(i).getConstructor().reconnect(); }
					 */
				}
			}
			// 6207

		//}
	}

}
