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
 
private aplicacion.herramientas.java.visualizadores.Condicion vCondicion=null;
public void buscarCondicion(JTextField tx) {
	if (vCondicion!=null){
		vCondicion.close();
	}
	vCondicion=new aplicacion.herramientas.java.visualizadores.Condicion(this.getConstructor().getConnectionHandler());
	vCondicion.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vCondicion.buscarCondicion(tx);
	if (n == 0) {
			aviso("no hay Condicions con ese codigo");
	}
}
*/
public class Condicion extends Generico{

private String idproveedor="";	

	public String getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Condicion(Constructor C){
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
		c.setNombre("idcond_cpra_vta");
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

		
		
		logic.setFromTable("v_ta_cpra_vta");
		
		logic.setTop(100);
		logic.setOrderby("idcond_cpra_vta ");
		logic.setTitle("Buscador de Condicion");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	public int buscarCondicion(JTextField tx){
		return this.Buscar(tx);
		
	}
	
}
