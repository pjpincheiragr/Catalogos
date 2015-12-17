package aplicacion.herramientas.conexion.test;

	import java.sql.Connection;
	import java.sql.DatabaseMetaData;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	public class sql2008 {
	    public static void main(String[] args) throws SQLException {
	        Connection conn = null;
	        ResultSet rs = null;
	        String url = "jdbc:jtds:sqlserver://192.168.4.4;instance=alfanet;DatabaseName=NEUQUEN2013;user=NEUQUEN2013;password=NEUQUEN2013;";
	        String driver = "net.sourceforge.jtds.jdbc.Driver";
	        String userName = "NEUQUEN2013";
	        String password = "NEUQUEN2013";
	        try {
	            Class.forName(driver);
	            conn = DriverManager.getConnection(url, userName, password);
	            net.sourceforge.jtds.jdbcx.JtdsDataSource dataSource = new net.sourceforge.jtds.jdbcx.JtdsDataSource();
	  	        dataSource.setAppName ("Neuquen");
	  	        dataSource.setDatabaseName ("NEUQUEN2013");
	  	        dataSource.setServerName ("192.168.4.4");
	  	        dataSource.setInstance("alfanet");
	  	        dataSource.setUser ("NEUQUEN2013");
	  	        dataSource.setPassword ("NEUQUEN2013");
	  	        
	            System.out.println("Connected to the database!!! Getting table list...");
	            DatabaseMetaData dbm = dataSource.getConnection().getMetaData();
	            rs = dbm.getTables(null, null, "%", new String[] { "TABLE" });
	            while (rs.next()) { System.out.println(rs.getString("TABLE_NAME")); }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            conn.close();
	            rs.close();
	        }
	    }
	}

