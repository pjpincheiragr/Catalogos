package aplicacion.herramientas.java.visualizadores;

import javax.swing.JTextField;
import java.awt.Rectangle;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
 
private aplicacion.herramientas.java.visualizadores.Provincia vProvincia=null;
public void buscarProvincia(JTextField tx) {
	if (vProvincia!=null){
		vProvincia.close();
	}
	vProvincia=new aplicacion.herramientas.java.visualizadores.Provincia(this.getConstructor().getConnectionHandler());
	vProvincia.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vProvincia.buscarProvincia(tx);
	if (n == 0) {
			aviso("no hay Provincias con ese codigo");
	}
}
*/
public class Provincia extends Generico{

private String idproveedor="";	

	public String getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Provincia(Constructor C){
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
		c.setNombre("codigo");
		c.setAlias("codigo");
		c.setColumnField(tx);
		c.setWidth(100);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(140);
		logic.addColumn(c);

		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("descripcion");
		f.setValor(tx.getText());
		logic.addFilter(f);

		
		
		logic.setFromTable("ta_estados");
		
		logic.setTop(100);
		logic.setOrderby("codigo ");
		logic.setTitle("Buscador de Provincia");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	public int buscarProvincia(JTextField tx){
		return this.Buscar(tx);
		
	}
	
}
