package aplicacion.beta.comprobantes.mv_asientos.test;

import aplicacion.beta.comprobantes.mv_asientos.objetos.Asiento;
import aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon;
import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.MsSQL;

public class _Test {
	public static void main(String[] args){
		ConnectionHandler c=new ConnectionHandler();
		MsSQL mssql=new MsSQL(null);
		mssql.setHost("127.0.0.1");
		mssql.setPort(1433);
		mssql.setUser("sa");
		mssql.setPassword("ernest");
		mssql.setDatabase("CWISKY");
		mssql.setTdsVersion("8.0");
		mssql.setId("BOLSON");
		
		c.addConector(mssql);
		c.setDefault("BOLSON");
		c.connect();
		Asiento asiento=new Asiento(c);
	    
		Renglon renglon=null;
		
		renglon=new Renglon();
	    renglon.setCuenta("112010003");
	    renglon.setCuenta_nombre("");
	    renglon.setDebe_haber("D");
	    renglon.setImporte("700.0");
	    renglon.setIdcajas("   1");
	    asiento.addRenglon(renglon);
	    
	    renglon=new Renglon();
	    renglon.setCuenta("21201");
	    renglon.setCuenta_nombre("");
	    renglon.setDebe_haber("H");
	    renglon.setImporte("121.49");
	    renglon.setIdcajas("   1");
	    asiento.addRenglon(renglon);
	    
	    renglon=new Renglon();
	    renglon.setCuenta("43101");
	    renglon.setCuenta_nombre("");
	    renglon.setDebe_haber("H");
	    renglon.setImporte("578.51");
	    renglon.setIdcajas("   1");
	    
	    asiento.addRenglon(renglon);
	    
	    asiento.setFecha("10-02-2009");
	    
	    
	    asiento.setTc("CR");
	    asiento.setIdcomprobante("0001001A");
	    
	    asiento.setDetalle("prueba");
	    
	    //System.out.println("Existe? "+asiento.existe());
	   //asiento.grabar();
	    
	    
	    asiento.setMes_operativo("2");
	    asiento.setNumero_asiento("8819");
	    asiento.setPeriodo("1");
	    //asiento.grabar();
	    
	    
	}
}
