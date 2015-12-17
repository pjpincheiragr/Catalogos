
package aplicacion.herramientas.conexion;

/**
 * Definicion de interfaz para la implementacion de un conector
 * independiente de la base de datos
 * @author Agustin Wisky
 *
 */
public interface Conector {

	
	/**
	 * Metodo que retorna el tipo de conector que es. especificado en la interfaz
	 * de tipos de conectores.
	 * @return
	 */
	public String getConnectorType();
	
	/**
	 * Define el tipo de base de datos para la cual esta creado el conector
	 * @param type
	 */
	public void setConnectorType(String type);
	/**
	 * Metodo para definir el ID del conector
	 * @param name
	 */
	public void setId(String id);
	
	/**
	 * Metodo que retorna el identificador del conector
	 * @return
	 */
	public String getId();
	/**
	 * Utilizado para definir la direccion del host al cual connectarse.
	 */
	public void setHost(String host);
	
	/**
	 * Metodo que devuelve el nombre de host
	 * @return
	 */
	public String getHost();

	/**
	 * Utilizado para establecer el puerto de conexion con el motor de la base
	 * de datos en el host indicado.
	 */
	public void setPort(int port);

	/**
	 * Devuelve el puerto configurado para la conexion
	 * @return
	 */
	public int getPort();
	
	/**
	 * Utilizado para definir el nombre de base de datos a utilizar.
	 */
	public void setDataBase(String database_name);
	
	/**
	 * Devuleve el nombre de la base de datos
	 * @return
	 */
	public String getDatabase();
	

	/**
	 * Utilizado para definir el nombre de inicio de sesion en el motor de base
	 * de datos.
	 */
	public void setUser(String user);

	/**
	 * Devuelve el usuario configurado para el conector
	 */
	public String getUser();
	
	/**
	 * Utilizado para definir el password de inicio de sesion en el motor de
	 * base de datos.
	 */
	public void setPassword(String password);

	/**
	 * Devuelve el password configurado para el conector
	 * @return
	 */
	public String getPassword();
	
	/**
	 * Comando para ejecutar el intento de conexion. Devuelve verdadero si logra conectarse
	 */
	public boolean connect();

	/**
	 * Comando para cancelar la conexion.
	 */
	public void disconnect();

	/**
	 * Para conocer el estado de la conexion
	 */
	public boolean isConnected();

	/**
	 * Para definir los intentos de conexion permitidos. antes de arrojar un
	 * error de conexion.
	 */
	public void setConnectionsOportunities(int oportunities);

	/**
	 * Para definir si al perder la conexion esta permitida la reconexion
	 * automatica.
	 */
	public void setAutomaticReconnection(boolean automatic_reconnection);

	/**
	 * Para definir modo debug
	 */
	public void setDebugMode(boolean debug_mode);

	/**
	 * Consulta a la base de datos
	 */
	public Object[][] getResults(String query);

	/**
	 * agregar linea a una ejecucion por lotes (Batch). Es una transaccion.
	 */
	public void addBatch(String command);

	/**
	 * ejecutar lote. (Batch). Devuelve verdadero si existe un error en la
	 * ejecucion. La transaccion retrocede si existe algun error.(rollback)
	 */
	public boolean executeBatch();

	/**
	 * Limpia el lote para ejecucion de transaccion.
	 */
	public void clearBatch();
	
	/**
	 * Define el nombre de la base de datos
	 * @param database
	 */
	public void setDatabase(String database);

	

}
