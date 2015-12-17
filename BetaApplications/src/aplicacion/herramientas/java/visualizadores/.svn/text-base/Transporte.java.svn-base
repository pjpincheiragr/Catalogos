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
 
private aplicacion.herramientas.java.visualizadores.Transporte vTransporte=null;
public void buscarTransporte(JTextField tx) {
	if (vTransporte!=null){
		vTransporte.close();
	}
	vTransporte=new aplicacion.herramientas.java.visualizadores.Transporte(this.getConstructor());
	vTransporte.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vTransporte.buscarTransporte(tx);
	if (n == 0) {
			aviso("no hay Transportes con ese codigo");
	}
}
*/
public class Transporte extends Generico{

private String idproveedor="";	

	public String getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Transporte(Constructor C){
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
		c.setNombre("idTransporte");
		c.setAlias("codigo");
		c.setColumnField(tx);
		c.setWidth(100);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("nombre");
		c.setAlias("nombre");
		c.setWidth(140);
		logic.addColumn(c);

		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("nombre");
		f.setValor(tx.getText());
		logic.addFilter(f);

		
		
		logic.setFromTable("Transportes");
		
		logic.setTop(100);
		logic.setOrderby("idTransporte ");
		logic.setTitle("Buscador de Transporte");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	public int buscarTransporte(JTextField tx){
		return this.Buscar(tx);
		
	}
	
}
