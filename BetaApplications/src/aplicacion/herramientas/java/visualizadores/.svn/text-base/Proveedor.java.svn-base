package aplicacion.herramientas.java.visualizadores;

import java.awt.Rectangle;

import javax.swing.JTextField;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
 
private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
public void buscarProveedor(JTextField tx) {
	if (vProveedor!=null){
		vProveedor.close();
	}
	vProveedor=new aplicacion.herramientas.java.visualizadores.Proveedor(this.getConstructor());
	int n = vProveedor.Buscar(tx);
	if (n == 0) {
			aviso("no hay Proveedors con ese codigo");
	}
}
*/
public class Proveedor extends Generico{
	private String idproveedor="";	
	private String prefijo="";
	
	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public String getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Proveedor(Constructor C){
		super(C);
	}
	
	
	public int initializeLogicAdivina(JTextField tx){
		
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
		.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("v.cuentaproveedor");
		c.setAlias("idproveedor");
		c.setColumnField(tx);
		c.setMaster(true);
		c.setWidth(100);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("m.descripcion");
		c.setAlias("nombre");
		c.setWidth(180);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("count(*)");
		c.setAlias("utilidad");
		c.setWidth(80);
		logic.addColumn(c);
		
		logic.setTop(50);
		
		logic.setFromTable(" v_ma_articulos v left outer join ma_cuentas m on v.cuentaproveedor = m.codigo ");
		String restriction="";
		restriction+="isnull(v.cuentaproveedor,'') not like '' AND m.DADA_DE_BAJA = 0 ";
		restriction+="and substring(v.idarticulo,0,4) like '"+prefijo+"'";
		logic.setRestriction(restriction);
		logic.setGroupby("v.cuentaproveedor,m.descripcion");
		logic.setOrderby("count(*) desc,v.cuentaproveedor");
		logic.setTitle("Buscador de Proveedor");
		int n= logic._loadoptions();
		return n;
	}
	public int initializeLogic(JTextField tx) {
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("codigo");
		c.setAlias("cuenta");
		c.setColumnField(tx);
		c.setWidth(120);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(240);
		logic.addColumn(c);
		logic.setFromTable("ma_cuentas ");
		logic.setRestriction("titulo = 0 ");

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("descripcion");
		f.setValor(tx.getText());
		logic.addFilter(f);
		
		logic.setTop(100);
		logic.setOrderby("descripcion");
		logic.setRestriction("codigo like '21101%' and codigo not like '21101' AND DADA_DE_BAJA = 0 ");
		logic.setTitle("Buscador de Proveedor");
		int n= logic._loadoptions();
		
		return n;
	}
	
	public int BuscarAdivina(JTextField tx){
		this.intializeConstructor();
		this.initializeConstructorParameters();
		return this.initializeLogicAdivina(tx);
	}
	
	
}
