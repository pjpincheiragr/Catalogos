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
 
private aplicacion.herramientas.java.visualizadores.PedidoProveedor vPedidoProveedor=null;
public void buscarPedidoProveedor(JTextField tx) {
	if (vPedidoProveedor!=null){
		vPedidoProveedor.close();
	}
	vPedidoProveedor=new aplicacion.herramientas.java.visualizadores.PedidoProveedor(this.getConstructor().getConnectionHandler());
	vPedidoProveedor.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vPedidoProveedor.buscarPedidoProveedor(tx);
	if (n == 0) {
			aviso("no hay PedidoProveedors con ese codigo");
	}
}
*/
public class PedidoProveedor extends Generico{


	public PedidoProveedor(Constructor C){
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
		//logic.addColumn(c);
		
		

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("p.idpedido");
		c.setAlias("idpedido");
		c.setColumnField(tx);
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("p.cuenta");
		c.setAlias("p.cuenta");
		c.setWidth(120);
		logic.addColumn(c);
		
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("m.descripcion");
		c.setAlias("m.descripcion");
		c.setWidth(200);
		c.setMaster(false);
		logic.addColumn(c);
		
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("p.estado");
		c.setAlias("estado");
		c.setWidth(140);
		logic.addColumn(c);
		

		
	
		
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("m.descripcion");
		f.setValor(tx.getText());
		logic.addFilter(f);

		
		
		logic.setFromTable(" b_pdp p left outer join ma_cuentas m on p.cuenta=m.codigo ");
	
		logic.setRestriction("isnull(p.eliminar,0) = 0");
		logic.setTop(100);
		logic.setOrderby("p.idpedido desc");
		logic.setTitle("Buscador de Pedido");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	
	public int buscarPedidoProveedor(JTextField tx){
		return this.Buscar(tx);
		
	}
	
}
