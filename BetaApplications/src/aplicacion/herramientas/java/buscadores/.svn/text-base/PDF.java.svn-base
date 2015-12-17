package aplicacion.herramientas.java.buscadores;

import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.herramientas.conexion.*;
import aplicacion.herramientas.conexion.conectores.MySQL;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
private aplicacion.herramientas.java.buscadores.PDF bPDF=null;
public void BuscarPDF(JTextField ext) {
	 if (bPDF!=null){
		 bPDF.close();
	 }
 bPDF=new aplicacion.herramientas.java.buscadores.PDF(this.getConstructor());
 bPDF.Buscar(ext);
} 
 */
public class PDF extends Generico{

	
	
	public PDF(Constructor C){
		super(C);
	}
	
	
	public void initializeConstructor() {
		C = new aplicacion.herramientas.java.sortableselector.constructor._Constructor() {
			@Override
			protected void initialize_logic() {
				_logic = new aplicacion.herramientas.java.sortableselector.logic._Logic() {
					@Override
					public void Close(JTable table, int row) {
						super.Close(table, row);
					}
				};
			}
		};
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
	
	public void initializeLogic(JTextField tx){
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) C
		.getLogic();
		logic.setCaller(tx);

		columna c = new columna();
		Filtro f = new Filtro();
		
		c.setNombre("titulo");
		c.setAlias("title");
		c.setWidth(120);
		c.setMaster(true);
		c.setColumnField(tx);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("archivo");
		c.setAlias("filename");
		c.setWidth(220);
		logic.addColumn(c);

		logic.addFromTable("pdf");
		f = new Filtro();
		f.setNombre("title");
		f.setAlias("titulo");
		f.setWidth(200);
		f.setFocus(true);
		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("filename");
		f.setAlias("Archivo");
		f.setWidth(200);
		f.setFocus(true);
		logic.addFilter(f);
		logic.addOrder("title,filename");
		logic.addGroup("title,filename");
		//logic.setTop(200);
		logic.getData().getConnectionHandler().addConector(this.getMySQL());
		logic.setIdconector("MySQL");
		logic.init();
	}
}
