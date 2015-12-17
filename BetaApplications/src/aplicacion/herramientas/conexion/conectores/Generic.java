package aplicacion.herramientas.conexion.conectores;

import aplicacion.herramientas.conexion.Conector;
import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.herramientas.conexion.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import javax.sql.ConnectionPoolDataSource;
import aplicacion.modelo.constructor.*;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import aplicacion.herramientas.conexion.pool.MiniConnectionPoolManager;

/**
 * 
 * @author Agustin Wisky
 *
 */
public class Generic implements Conector {
	protected Constructor C=null;
	protected int allowed_tries=10;
	protected String host = "";
	protected int port = 0;
	protected String database = "";
	protected String instance = "";
	protected String user = "";
	protected boolean mostrar_error=true;
	protected String password = "";
	protected String id="";
	protected String tipo="";
	protected boolean error=false;
	protected boolean debug=true;
	protected boolean connected = false;
	protected Statement statement = null;
	protected Connection connection = null;
	protected String[] columns=null;
	protected String error_code ="";
	protected String error_msg = "";
	protected String error_state = "";
	protected String error_command = "";
	protected boolean pooltype=true;
	protected MiniConnectionPoolManager pool=null;
	
	public boolean getPoolType(){
		return this.pooltype;
	}
	
	public void setConnectionPool(MiniConnectionPoolManager connectionHandler){
		pool=connectionHandler;
	}
	
	public MiniConnectionPoolManager getConnectionPool(){
		return pool;
	}
	public Generic(Constructor C){
		this.C=C;
	}
	
	public Constructor getConstructor(){
		return C;
	}
	public boolean hasTunnel(){
		return (this.getHost().compareTo("127.0.0.1")==0);
	}
	public void setConstructor(Constructor C){
		this.C=C;
	}
	@Override
	public void clearBatch() {
		// TODO Auto-generated method stub
		//System.out.print("ClearBatch <"+this.getId()+"> ");
		boolean reconnect=false;
		if (connection==null){
			reconnect=true;
		}else{
			try {
				reconnect=connection.getAutoCommit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				reconnect=true;
			}
			//System.out.println(" Autocommit="+reconnect);
		}
		if (reconnect){
			if (this.pool!=null){
				boolean error=true;
				int tries=0;
				
				while(error & tries<allowed_tries){
					error=false;
					try {
						connection=pool.getConnection();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						error=true;
						e.printStackTrace();
					}
					tries++;
				}
				
				//System.out.println("Connection Pool. So new Connection!");
			}
		}
		try {
			statement=connection.createStatement();
			statement.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.error(e);
		}
	}

	public PreparedStatement prepareStatement(String s){
		PreparedStatement ps=null;
		try {
			ps = connection.prepareStatement(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.displaySQLErrors(e);
		}
		return ps;
	}
	
	public Statement createStatement(){
		Statement ps=null;
		try {
			ps = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.displaySQLErrors(e);
		}
		return ps;
	}
	
	
	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean executeUpdate(String query) {
		error = false;
		Statement statement=null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			displaySQLErrors(e1);
			error=true;
		}
		

		return error;
	}

	public boolean executeBatch() {
		error = false;
	/*	boolean auto=false;
		
		try {
			auto=connection.getAutoCommit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/	
		try {
			
			statement.executeBatch();
		} catch (SQLException e) {
				displaySQLErrors(e);
			error=true;
		}
		/*if (!auto){
			
			if (!error) {
				commitTransaction();	
			} else {
			rollbackTransaction();
			}	
		}*/
		return error;
	}
	
	public boolean commitTransaction() {
		//System.out.println("COMMIT TRANSACTION");
		boolean aux = true;
		try {
			
			
			if (!this.connection.getAutoCommit()){
				this.connection.commit();
				this.connection.setAutoCommit(true);
				//this.connection.close();
				
			}
		} catch (SQLException s) {

		}
		return aux;
	}

	public boolean rollbackTransaction() {
		//System.out.println("ROLLBACK TRANSACTION <"+this.getId()+">");
		boolean aux = true;
		try {
			if (!this.connection.getAutoCommit()){
				this.connection.rollback();
				this.connection.setAutoCommit(true);
				this.connection.close();
				
			}

			
		} catch (SQLException s) {
			displaySQLErrors(s);
		}
		return aux;
	}
	@Override
	public String getConnectorType() {
		// TODO Auto-generated method stub
		return tipo;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	
	public ResultSet getResulSet(String Query){
		Statement SQLST = null;
		ResultSet rs =null;
		Connection connection=null;
		if (this.pool!=null){
			boolean error=true;
			int tries=0;
			
			while(error & tries<allowed_tries){
				error=false;
				try {
					connection=pool.getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error=true;
					e.printStackTrace();
				}
				tries++;
			}
			
			//System.out.println("Connection Pool. So new Connection!");
		}else{
			connection=this.connection;
		}
		try {
			
		SQLST = connection.createStatement();
		
		rs= SQLST.executeQuery(Query);
		} catch (SQLException s) {
			
			this.error_command=Query;
			displaySQLErrors(s);
		}
		return rs;

	}
	
	public boolean hasResults(String query){
		boolean ok=false;
		Object[][] results=null;
		results=getResults(query);	
		if (results!=null){
			if (results.length>0){

				ok=true;
			}
		}
		return ok;
	}
	
	@Override
	public Object[][] getResults(String Query) {
		Connection connection=null;
		boolean auto=true;
		if (this.connection!=null){
			try {
				auto=this.connection.getAutoCommit();
				if (!auto){
					connection=this.connection;


				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				connection=null;
			}
		}
		if(connection==null){


			if (this.pool!=null){
				boolean error=true;
				int tries=0;

				
				while(error & tries<this.allowed_tries){
					System.out.println("DANGER WHILE "+tries+" error?"+error);
					error=false;
					

					try {
				connection=pool.getConnection();


						System.out.println("INSIDE DANGER WHILE "+connection+" "+tries+" error?"+error+" "+Query);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						error=true;
						e.printStackTrace();
					}
					tries++;
				}
				
				System.out.println("Connection Pool. So new Connection! error?"+error);
			}else{
				connection=this.connection;
			}	
		}
		Object[][] aux = null;
		try {
			System.out.println("try create statement sqlst");
			Statement SQLST = null;
			SQLST = connection.createStatement();
			//System.out.println("AFTER DANGER WHILE "+connection+"  statement? "+SQLST);
			ResultSet rs = SQLST.executeQuery(Query);
			
			//System.out.println("AFTER SQLST execute Query "+connection+"  statement? ");
			ResultSetMetaData rsmd = rs.getMetaData();
			int numbercolumns = rsmd.getColumnCount();
			// se obtienen los nombres de las columnas de los resultados.
			columns = new String[numbercolumns];


			for (int i = 1; i < numbercolumns + 1; i++) {
				columns[i - 1] = rsmd.getColumnName(i);
				
			}

			/* Se obtiene la lista completa de resultados. Los resultados se
			  toman como String.
			 */
			LinkedList l = new LinkedList();
			int row = 0;
			Object[] aux1 = new Object[numbercolumns];
			while (rs.next()) {
				aux1 = new Object[numbercolumns];
				for (int i = 1; i < numbercolumns + 1; i++) {
					aux1[i - 1] = new String("");
					try {


						aux1[i - 1] = new String(rs.getString(i));
					} catch (Exception e) {
						//no tiene sentido procesar los resultados.
					}
				}
				l.add(aux1);
				row++;
			}
			
			aux = new Object[l.size()][numbercolumns];
			for (int i = 0; i < l.size(); i++) {
				Object[] aux2 = (Object[]) l.get(i);
				aux[i] = aux2;
			}

			rs.close();
			if (this.pool!=null){
				System.out.println("auto connection close");

				if (auto)connection.close();
			}
		} catch (SQLException s) {
			boolean error=true;
			if (s.getSQLState().toString().compareTo("08S01")==0|s.getSQLState().toString().compareTo("HY010")==0){
				System.out.println("Error "+s.getErrorCode()+" "+s.getMessage());
				if (this.pool!=null){
					int tries=0;
					while(error & tries<allowed_tries){
						error=false;
						try {
							connection=pool.getConnection();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							error=true;
							e.printStackTrace();
						}
						tries++;
					}
				}
				if (!error){
					aux=this.getResults(Query);
				}else{
					this.error_command=Query;
					displaySQLErrors(s);
						
				}
			}else{
				this.error_command=Query;
				displaySQLErrors(s);
			}
			
				
		}finally{
		}

		//System.out.println("End of getResult! "+Query);
		return aux;
	}

public String[] getColumns(){
		return this.columns;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAutomaticReconnection(boolean automaticReconnection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setConnectionsOportunities(int oportunities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDataBase(String databaseName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDebugMode(boolean debugMode) {
	//	this.debug=debugMode;
	}

	@Override
	public void setHost(String host) {
		this.host=host;
	}

	public String getHost(){
		return this.host;
	}
	
	@Override
	public void setId(String id) {
		this.id=id;
	}

	@Override
	public void setPassword(String password) {
		this.password=password;
	}

	public String getPassword(){
		return this.password;
	}
	
	public int getPort(){
		return this.port;
	}
	
	
	@Override
	public void setPort(int port) {
		this.port=port;
	}

	@Override
	public void setUser(String user) {
		this.user=user;
	}
	
	public String getUser(){
		return this.user;
	}
	
	public void setDatabase(String database){
		this.database=database;
	}

	public String getDatabase(){
		return this.database;
	}
	
	public void setConnectorType(String tipo){
	this.tipo=tipo;	
	}
	
	public void displaySQLErrors(SQLException e) {
		error = true;
		error_code = "" + e.getErrorCode();
		error_msg = e.getMessage();
		error_state = e.getSQLState();
		if (debug){
			System.out.println("("+this.id+")SQLException: " + error_msg);
			System.out.println("("+this.id+")SQLState: " + error_state);
			System.out.println("("+this.id+")VendorError: " + error_code);	
		}
		
		error(e);
		
		
	}
	
	 
	public String getErrorDetails(){
			String mensaje="";
			String newline = System.getProperty("line.separator");
			mensaje+=this.error_code+newline;
			mensaje+=this.error_msg+newline;
			mensaje+=this.error_state+newline;
			
			int length=200;
			while (error_command.length()>length){
				mensaje+=this.error_command.substring(0, length)+newline;
				this.error_command=this.error_command.substring(length);
			}
			mensaje+=this.error_command+newline;
			return mensaje;
			
			
		
	}
	
	public void cleanErrors(){
		this.error_code="";
		this.error_msg="";
		this.error_state="";
		this.error_command="";
	}
	
	public void error(SQLException e) {
		if (debug)System.out.println("Generic Error> "+e.getErrorCode()+" "+e.getSQLState()+" "+e.getLocalizedMessage());
		if (debug)System.out.println(" class:"+C.getClass());
		if (C.getCaller()!=null){
			if (debug)System.out.println(" caller:"+C.getCaller().getClass());	
		}
		
		if (e.getSQLState().toString().compareTo("08S01")==0|e.getSQLState().toString().compareTo("HY010")==0){
			
			
			if (debug)System.out.println("Error de Conexion Reconectar");
			if (this.pool!=null){
				boolean error=true;
				int tries=0;
				
				while(error & tries<4){
					error=false;
					try {
						connection=pool.getConnection();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						error=true;
						ex.printStackTrace();
					}
					tries++;
				}
				
				//System.out.println("Connection Pool. So new Connection!");
			}else{
				if (!(C instanceof aplicacion.herramientas.conexion.login.constructor._Constructor)
						& !(C instanceof aplicacion.herramientas.java.launcher.constructor._Constructor)){
					this.C.reconnect();
					
				}else{
					String xml="";
					try {
						if (debug)System.out.println("Constructor de Generic Connector> "+this.C.getClass());
						xml=this.C.getLogic().saveToXML();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int n=this.C.getErrorHandler().displayError("Error", "Error en "+this.getId(),this.getErrorDetails(), e,xml);
					if (n==2){
						this.mostrar_error=false;
					}
				}	
			}
			
			
			
			
			
		}else{
			if (this.mostrar_error){
				
				 
				String xml="";
				try {
					//System.out.println("Constructor de Generic Connector> "+this.C.getClass());
					if(C!=null){
						if(C.getLogic()!=null){
							xml=this.C.getLogic().saveToXML();	
						}
							
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int n=this.C.getErrorHandler().displayError("Error", "Error en "+this.getId(),this.getErrorDetails(), e,xml);
				if (n==2){
					this.mostrar_error=false;
				}
			}
	
		
		}
				
		
		this.cleanErrors();		
	}
	
	
	
	protected String getConnectionStream(){
		return null;
	}
	
	
	
	public boolean autoconnect(){
		String connection_stream = getConnectionStream();
		error=false;
		try {
			DriverManager.setLoginTimeout(10);
			
			connection = DriverManager.getConnection(connection_stream);
		} catch (SQLException e) {
			if (debug)System.out.println(connection_stream);
			if (debug)displaySQLErrors(e);
			error=true;
			
		}
		if (error) {
			if (debug)System.out.println("Error en conexion");
			
		}
		connected = !error;
		if (connected){
			connected();
		}
		return connected;
	}
	public boolean connect(){
		boolean connected=false;
		if (this.pool!=null){
			connected=this.connect_pool();
		}else{
			connected=this.connect_classic();
		}
		return connected;
	}
	
	public boolean connect_classic(){
		
		
			String connection_stream = getConnectionStream();
			error=false;
			try {
				DriverManager.setLoginTimeout(10);
				
				connection = DriverManager.getConnection(connection_stream);
			} catch (SQLException e) {
				System.out.println(connection_stream);
				//displaySQLErrors(e);
				String NEW_LINE = System.getProperty("line.separator");
				String msj="Error de Conexion con el Servidor ("+id+")";
				msj+=NEW_LINE;
				msj+="Posibles Causas del Problema de Conexion:";
				msj+=NEW_LINE;
				msj+="1) Problema de Red. ";
				msj+=NEW_LINE;
				msj+="	Verifique El Cable de Red en su Puesto o su conexion Wireless ";
				msj+=NEW_LINE;
				msj+="	Verifique Que la infrastructura de red este en funcionamiento (Switchs y Routers) ";
				msj+=NEW_LINE;
				msj+="2) Firewall (Cortafuego)";
				msj+=NEW_LINE;
				msj+="	Verifique que el servidor "+id+" no se encuentre detras de un Firewall que este activado";
				msj+=NEW_LINE;
				msj+="3) Servidor "+id;
				msj+=NEW_LINE;
				msj+="	Verifique que el Servidor "+id+" Este encendido";
				msj+=NEW_LINE;
				msj+="4) Nunca reinicie los servidores! Consulte a Sistemas";
				
				error(msj);
				
				/*
				if (debug)System.out.println(connection_stream);
					displaySQLErrors(e);
				
				*/
				error=true;
			}
			if (error) {
				if (debug)System.out.println("Error en conexion");
				
			}
			connected = !error;
			if (connected){
				connected();
			}		
		
		 
		return connected;
	}
	
public boolean connect_pool(){
		
		connected=true;
		try {
			if (debug)System.out.println("pool connection ("+pool+") "+this.id+" init");	
			connection=pool.getConnection();
			
			if (debug){
				System.out.println("pool connection "+this.id+" > "+connection);
				PreparedStatement st=connection.prepareStatement("select 'ok'");
				st.execute();
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connected=false;	
		} 
		return connected;
	}

	protected void connected(){
		//if (debug)
			System.out.println(this.id+" connected");
	}
	
	public boolean testConection(){
		
		boolean ok=true;
		try {
			Statement SQLST = connection.createStatement();
			SQLST.execute("select getdate()");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			if (e.getSQLState().toString().compareTo("08S01")==0|e.getSQLState().toString().compareTo("HY010")==0){
				ok=false;	
			}else{
				ok=true;
			}
		}
		
		if (debug)System.out.println("TEST CONNECTION "+this.id+" "+ok);
		
		return ok;
		
	}
public void startTransaction(){
	//System.out.println("BEGIN TRANSACTION <"+this.getId()+">");
	boolean reconnect=false;
	if (connection==null){
		reconnect=true;
	}else{
		try {
			reconnect=!connection.getAutoCommit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			reconnect=true;
		}
		
	}
	if (reconnect){
		if (this.pool!=null){
			boolean error=true;
			int tries=0;
			
			while(error & tries<4){
				error=false;
				try {
					connection=pool.getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					error=true;
					//e.printStackTrace();
				}
				tries++;
			}
			
			//System.out.println("Connection Pool. So new Connection!");
		}
	}
	try {
		
		connection.setAutoCommit(false);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		displaySQLErrors(e);
	}
}


public void setAutoComit(boolean commit){
	try {
		connection.setAutoCommit(commit);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		displaySQLErrors(e);
		e.printStackTrace();
	}
}

public Generic Clone(){
	if (debug)System.out.println("Clonando Conector");
	Generic clone=null;
	try {
		clone=new Generic(this.C);
		clone.setId(this.getId());
		clone.setHost(this.getHost());
		clone.setDatabase(this.getDatabase());
		clone.setPassword(this.getPassword());
		clone.setPort(this.getPort());
		clone.setUser(this.getUser());
		clone.setDebugMode(true);
		clone.setConnectorType(this.getConnectorType());
		clone.setConnectionPool(this.getConnectionPool());
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return clone;
}

public Connection getConnection(){
	return this.connection;
}

public void addBatch(String s) {
	try {
		this.error_command=s;
		statement.addBatch(s);
	} catch (SQLException e) {
		displaySQLErrors(e);
	}
	
}


public ConnectionPoolDataSource getConnectionPoolDataSource(){
	return null;
}



public String getInstance() {
	return instance;
}

public void setInstance(String instance) {
	this.instance = instance;
}

public void error(String mensaje) {
	System.out.println(mensaje);
	this.getConstructor().getLogic().error(mensaje);
	
}
}



