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
 
private aplicacion.herramientas.java.visualizadores.Remito vRemito=null;
public void buscarRemito(JTextField tx) {
	if (vRemito!=null){
		vRemito.close();
	}
	vRemito=new aplicacion.herramientas.java.visualizadores.Remito(this.getConstructor().getConnectionHandler());
	vRemito.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vRemito.buscarRemito(tx);
	if (n == 0) {
			aviso("no hay Remitos con ese codigo");
	}
}
*/
public class Remito extends Generico{

private String idproveedor="";	

	public String getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Remito(Constructor C){
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
		c.setNombre("fecha");
		c.setAlias("fecha");
		c.setWidth(100);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("tc");
		c.setAlias("tc");
		c.setWidth(100);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("idcomprobante");
		c.setAlias("idcomprobante");
		c.setColumnField(tx);
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("cuenta");
		c.setAlias("cuenta");
		c.setWidth(120);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("nombre");
		c.setAlias("nombre");
		c.setWidth(240);
		logic.addColumn(c);

		

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("importe");
		c.setAlias("importe");
		c.setWidth(80);
		logic.addColumn(c);
		
		String txt = tx.getText();
		String linea = "";
		if (txt.contains("+")) {
			linea = txt.substring(0, txt.indexOf("+"));
			txt = txt.substring(txt.indexOf("+") + 1, txt.length());
		}

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("nombre");
		f.setValor(txt);
		logic.addFilter(f);

		
		
		logic.setFromTable(" v_mv_cpte ");
		logic.setRestriction("finalizada=0 and tc like 'rm'");
		logic.setTop(100);
		logic.setOrderby("fecha desc,idcomprobante desc");
		logic.setTitle("Buscador de Remito");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	public int buscarRemito(JTextField tx){
		return this.Buscar(tx);
		
	}
	
}
