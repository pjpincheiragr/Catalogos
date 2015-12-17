package aplicacion.herramientas.conexion.test;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Random;

import aplicacion.herramientas.conexion.*;
import aplicacion.herramientas.conexion.conectores.*;

public class TestOK {
	
	
	public SQLite getSqlite(){
		SQLite s=new SQLite(null);
		s.setId("Beta");
		s.setDatabase("c:/beta/lib/beta.sqlite");
		return s;
	}
	
	public MySQL getMySQL(){
		MySQL s=new MySQL(null);
		s.setId("MySQL");
		s.setDatabase("test");
		s.setUser("root");
		s.setPassword("ipsilon");
		s.setHost("192.168.3.1");
		return s;
	}
	
	public MsSQL getMsSQL(){
		MsSQL s=new MsSQL(null);
		s.setId("MsSQL");
		s.setDatabase("cwisky");
		s.setUser("sa");
		s.setPassword("");
		s.setHost("192.168.3.1");
		return s;
	}
	
	public void tester(){
	ConnectionHandler c=new ConnectionHandler();
		
		c.addConector(getSqlite());
		c.addConector(getMySQL());
		c.addConector(getMsSQL());
		c.startConnections();
		//c.Clone();
		int errors=0;
		int operations=30;
		for (int i=0;i<operations;i++){
			//boolean error=tester(c.Clone()); #aca esta el bug 
			boolean error=tester(c);
			Random randomGenerator = new Random();
			int valor=300+randomGenerator.nextInt(400);
			try {
				new Thread().sleep(valor);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			if (error){
				errors++;
				System.out.println("Operaciones "+i+ " Errors?>"+errors );
			}else{
				System.out.println("Operaciones "+i+ " Errors?>"+errors );
			}
		}
		System.out.println("Operaciones "+operations+ " Errors?>"+errors );
	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestOK t=new TestOK();
		t.tester();
		
	}

	public boolean tester(ConnectionHandler c){
		boolean error=false;
		try {
			int connections=c.getConnector("MySQL").getConnectionPool().getActiveConnections();
			System.out.println("Active Connections "+connections);
			Connection con=c.getConnector("MySQL").getConnectionPool().getConnection();
			boolean ok=con.prepareStatement("select 'ok'").execute();
			
			
			con.close();
			
		} catch (SQLException e) {
			error=true;
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return error;
	}
}
