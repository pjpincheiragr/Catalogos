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
private aplicacion.herramientas.java.visualizadores.Cobranza vCobranza=null;
public void buscarCobranza(JTextField tx) {
	if (vCobranza!=null){
		vCobranza.close();
	}
	vCobranza=new aplicacion.herramientas.java.visualizadores.Cobranza(this.getConstructor().getConnectionHandler());
	vCobranza.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vCobranza.buscarCobranza(tx);
	if (n == 0) {
			aviso("no hay Cobranzas con ese codigo");
	}
}
*/

public class Cobranza extends Generico{

	private String tc="";	

	public String getTC() {
		return tc;
	}

	public void setTC(String idproveedor) {
		this.tc = idproveedor;
	}

	public Cobranza(Constructor C){
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
		c.setNombre("b.fecha");
		c.setAlias("fecha");
		c.setWidth(100);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("b.tc");
		c.setAlias("tc");
		c.setWidth(100);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("b.idcomprobante");
		c.setAlias("idcomprobante");
		c.setColumnField(tx);
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("b.cuenta");
		c.setAlias("cuenta");
		c.setWidth(120);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.descripcion");
		c.setAlias("nombre");
		c.setWidth(240);
		logic.addColumn(c);

		

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("b.importe");
		c.setAlias("importe");
		c.setWidth(80);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("(case when b.anulada=1 then 'ANULADA' ELSE '' END)");
		c.setAlias("estado");
		c.setWidth(80);
		logic.addColumn(c);
	
		
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("c.descripcion ");
		f.setValor(tx.getText());
		logic.addFilter(f);

		
		
		logic.setFromTable(" b_cbs b left outer join ma_cuentas c on b.cuenta=c.codigo ");
		if (tc.compareTo("")!=0){
			logic.setRestriction(" b.tc like '"+tc+"'");	
		}
		
		logic.setTop(100);
		logic.setOrderby("b.fecha desc,b.idcomprobante desc");
		logic.setTitle("Buscador de Cobranza");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	
	public int buscarCobranza(JTextField tx){
		return this.Buscar(tx);
		
	}
	
}
