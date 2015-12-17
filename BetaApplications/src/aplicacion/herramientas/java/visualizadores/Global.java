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
 
private aplicacion.herramientas.java.visualizadores.Global vGlobal=null;
public void buscarGlobal(JTextField tx) {
	if (vGlobal!=null){
		vGlobal.close();
	}
	vGlobal=new aplicacion.herramientas.java.visualizadores.Global(this.getConstructor().getConnectionHandler());
	vGlobal.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vGlobal.buscarGlobal(tx);
	if (n == 0) {
			aviso("no hay Global con ese codigo");
	}
}
*/
public class Global extends Generico{

private String idproveedor="";	

	public String getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Global(Constructor C){
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
		c.setNombre("idcomprobante");
		c.setAlias("idcomprobante");
		c.setColumnField(tx);
		c.setWidth(100);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("fecha");
		c.setAlias("fecha");
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("finalizado");
		c.setAlias("finalizado");
		c.setWidth(140);
		logic.addColumn(c);

		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("idcomprobante");
		f.setValor(tx.getText());
		logic.addFilter(f);

		
		
		logic.setFromTable("b_global");
		
		logic.setTop(100);
		logic.setOrderby("idcomprobante ");
		logic.setTitle("Buscador de comprobante");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	public int buscarGlobal(JTextField tx){
		return this.Buscar(tx);
		
	}
	
}
