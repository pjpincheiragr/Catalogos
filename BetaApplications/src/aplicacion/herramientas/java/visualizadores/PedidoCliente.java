package aplicacion.herramientas.java.visualizadores;

import javax.swing.JTextField;
import java.awt.Rectangle;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.java.sortableselector.logic.columna;
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
public class PedidoCliente extends Generico{
	private String negocio = "";

	public String getNegocio() {
		return negocio;
	}

	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}

	public PedidoCliente(Constructor C){
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
		c.setNombre("fecha_creacion");
		c.setAlias("fecha");
		c.setWidth(100);
		logic.addColumn(c);
		
		

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("idpedido");
		c.setAlias("idpedido");
		c.setColumnField(tx);
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(200);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("cliente");
		c.setAlias("cliente");
		c.setWidth(120);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("cliente_descripcion");
		c.setAlias("nombre");
		c.setWidth(240);
		logic.addColumn(c);

		

		
	
		
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("descripcion");
		f.setValor(tx.getText());
		logic.addFilter(f);

		
		
		logic.setFromTable(" b_pdc ");
		if (negocio.compareTo("")!=0){
			logic.setRestriction("negocio like '" + negocio + "'");	
		}
		
		logic.setTop(100);
		logic.setOrderby("fecha_creacion desc,idpedido desc");
		logic.setTitle("Buscador de Pedido");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	
	public int buscarFVN(JTextField tx){
		return this.Buscar(tx);
		
	}
	
}
