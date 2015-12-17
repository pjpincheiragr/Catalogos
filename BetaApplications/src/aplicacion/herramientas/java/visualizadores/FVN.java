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
 
private aplicacion.herramientas.java.visualizadores.FVN vFVN=null;
public void buscarFVN(JTextField tx) {
	if (vFVN!=null){
		vFVN.close();
	}
	vFVN=new aplicacion.herramientas.java.visualizadores.FVN(this.getConstructor().getConnectionHandler());
	vFVN.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vFVN.buscarFVN(tx);
	if (n == 0) {
			aviso("no hay FVNs con ese codigo");
	}
}
*/
public class FVN extends Generico{

private String tc="";	

	public String getTC() {
		return tc;
	}

	public void setTC(String idproveedor) {
		this.tc = idproveedor;
	}

	public FVN(Constructor C){
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
		c.setNombre("fecha_comprobante");
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
		c.setNombre("cuenta_descripcion");
		c.setAlias("cuenta");
		c.setWidth(120);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("cuenta_descripcion");
		c.setAlias("nombre");
		c.setWidth(240);
		logic.addColumn(c);

		

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("total_cpte");
		c.setAlias("importe");
		c.setWidth(80);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("(case when anulada=1 then 'ANULADA' ELSE '' END)");
		c.setAlias("estado");
		c.setWidth(80);
		logic.addColumn(c);
	
		
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("cuenta_descripcion");
		f.setValor(tx.getText());
		logic.addFilter(f);

		
		
		logic.setFromTable(" b_fvn ");
		if (tc.compareTo("")!=0){
			logic.setRestriction(" tc like '"+tc+"'");	
		}
		
		logic.setTop(100);
		logic.setOrderby("fecha_comprobante desc,idcomprobante desc");
		logic.setTitle("Buscador de FVN");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	
	public int buscarFVN(JTextField tx){
		return this.Buscar(tx);
		
	}
	
}
