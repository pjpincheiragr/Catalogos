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
 
private aplicacion.herramientas.java.visualizadores.Control vControl=null;
public void buscarControl(JTextField tx) {
	if (vControl!=null){
		vControl.close();
	}
	vControl=new aplicacion.herramientas.java.visualizadores.Control(this.getConstructor().getConnectionHandler());
	vControl.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vControl.buscarControl(tx);
	if (n == 0) {
			aviso("no hay Controls con ese codigo");
	}
}
*/
public class Control extends Generico{

private String iddeposito="";	

	public String getDeposito() {
		return iddeposito;
	}

	public void setDeposito(String deposito) {
		this.iddeposito = deposito;
	}

	public Control(Constructor C){
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
		c.setNombre("c.idcontrol");
		c.setAlias("idcontrol");
		c.setColumnField(tx);
		c.setWidth(100);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.idusuario");
		c.setAlias("idusuario");
		c.setWidth(100);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.linea");
		c.setAlias("linea");
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("(case when count(s.idcomprobante)>0 then 'APLICADO' else 'SIN APLICACION' end)");
		c.setAlias("estado");
		c.setWidth(140);
		logic.addColumn(c);
			
		
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("c.linea");
		f.setValor(tx.getText());
		logic.addFilter(f);

		
		
		logic.setFromTable(" b_control c left outer join v_mv_stock s on c.idcontrol like s.idcomprobante and s.tc like 'CTRL' ");
		logic.setGroupby("c.idcontrol,c.idusuario,c.linea");
		String restriction=" isnull(c.eliminar,0)=0 ";
		if (iddeposito.compareTo("")!=0){
			restriction+=" and s.iddeposito like '"+iddeposito+"'";
		}
		logic.setRestriction(restriction);
		logic.setTop(100);
		logic.setOrderby("c.idcontrol desc");
		logic.setTitle("Buscador de Control");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	
	public int buscarControl(JTextField tx){
		return this.Buscar(tx);
		
	}
	
}
