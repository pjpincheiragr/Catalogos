package aplicacion.herramientas.java.visualizadores;

import java.awt.Rectangle;

import javax.swing.JTextField;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.MySQL;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
 
private aplicacion.herramientas.java.visualizadores.PDF vPDF=null;
public void buscarPDF(JTextField tx) {
	if (vPDF!=null){
		vPDF.close();
	}
	vPDF=new aplicacion.herramientas.java.visualizadores.PDF(this.getConstructor().getConnectionHandler());
	int n = vPDF.Buscar(tx);
	if (n == 0) {
			aviso("no hay PDFs con ese codigo");
	}
}
*/
public class PDF extends Generico{

	private String idPDF="";	
	
	public String getIdPDF() {
		return idPDF;
	}
	
	
	public MySQL getMySQL(){
		MySQL c=new MySQL(null);
		c.setId("MySQL");
		c.setDatabase("test");
		c.setUser("root");
		c.setPassword("ipsilon");
		c.connect();
		return c;
	}

	public void setIdPDF(String idPDF) {
		this.idPDF = idPDF;
	}

	public PDF(Constructor C){
		super(C);
	}
	
	public void initializeConstructor(){
		C = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
	}
	public int initializeLogic(JTextField tx) {
	
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();

		c.setNombre("title");
		c.setAlias("titulo");
		c.setColumnField(tx);
		c.setWidth(200);
		logic.addColumn(c);
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("filename");
		c.setAlias("Archivo");
		c.setWidth(220);
		logic.addColumn(c);
		logic.setFromTable("pdf ");
		

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("title");
		f.setValor(tx.getText());
		logic.addFilter(f);

		//logic.setTop(100);
		
		logic.setOrderby("title,filename");
		logic.setGroupby("title,filename");
		logic.setTitle("Buscador de PDF");
		logic.getData().getConnectionHandler().addConector(this.getMySQL());
		int n= logic._loadoptions("MySQL");
		
		return n;

	}
	
	
}
