package aplicacion.herramientas.java.visualizadores;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
 
private aplicacion.herramientas.java.visualizadores.Comprobantes vComprobantes=null;
public void buscarComprobantes(JTextField tx) {
	if (vComprobantes!=null){
		vComprobantes.close();
	}
	vComprobantes=new aplicacion.herramientas.java.visualizadores.Comprobantes(this.getConstructor().getConnectionHandler());
	vComprobantes.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vComprobantes.buscarComprobantes(tx);
	if (n == 0) {
			aviso("no hay Comprobantess con ese codigo");
	}
}
*/
public class Comprobantes extends Generico{


private boolean shift=false;
private boolean su=false;
	public boolean isSu() {
	return su;
}


public void setSu(boolean su) {
	this.su = su;
}

	private JTextField proveedor=null;
	private JComboBox tc=null;


	public JTextField getProveedor() {
		return proveedor;
	}


	public void setProveedor(JTextField proveedor) {
		this.proveedor = proveedor;
	}



	public boolean isShift() {
		return shift;
	}


	public void setShift(boolean shift) {
		this.shift = shift;
	}

	public Comprobantes(Constructor C){
		super(C);
	}
	
	
	public void initializeConstructor(){
		C = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
	}
	
	public int initializeLogic(JTextField tx,String[] options) {
		System.out.println("Initialize logic combobox");
		aplicacion.herramientas.java.visualselector.logic._Logic logic=
			(aplicacion.herramientas.java.visualselector.logic._Logic)C.getLogic();
		logic.setCaller(tx);
		logic.setList_values(options);
		aplicacion.herramientas.java.visualselector.logic.Columna c 
		= new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f 
		= new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("convert(varchar(10) ,fecha_carga,105)");
		c.setAlias("Cargado");
		c.setWidth(100);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("d.tc");
		c.setAlias("tc");
		c.setWidth(40);
		c.setColumnList(tc);
		c.setMaster(true);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("d.idcomprobante");
		c.setAlias("IdComprobante");
		c.setWidth(100);
		c.setColumnField(tx);
		c.setMaster(true);
		logic.addColumn(c);
		
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("d.cuenta");
		c.setAlias("idproveedor");
		c.setWidth(100);
		c.setColumnField(proveedor);
		c.setMaster(true);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("m.descripcion");
		c.setAlias("descripcion");
		c.setWidth(220);
		
		logic.addColumn(c);
		
		
		logic.setFromTable("b_cpte_digital d left outer join ma_cuentas m on d.cuenta=m.codigo ");
		String restriction=" d.carga_articulos=1";
		if (shift){
			restriction="";
		}
		
		if (!su){
				if (restriction.length()>0){
					restriction+=" and ";	
				}
				restriction+=" d.tc not like 'FCN' ";
		}
		
		
		logic.setRestriction(restriction);
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("d.cuenta");
		System.out.println("proveedor value?"+proveedor.getText());
		f.setValor(proveedor.getText());
		logic.addFilter(f);
		
		
		
		
		
		logic.setTop(100);
		logic.setOrderby("d.fecha_carga desc,d.cuenta,d.idcomprobante");
		

		int n=logic._loadoptions();		
		return n;

		
	}
	
	public int initializeLogicPendientes(JTextField tx) {
		
		aplicacion.herramientas.java.visualselector.logic._Logic logic=
			(aplicacion.herramientas.java.visualselector.logic._Logic)C.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c 
		= new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f 
		= new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("d.cuenta");
		c.setAlias("idproveedor");
		c.setWidth(100);
		c.setMaster(true);
		c.setColumnField(tx);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("m.descripcion");
		c.setAlias("Nombre");
		c.setWidth(160);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("count(distinct d.idcomprobante)");
		c.setAlias("Pendientes");
		c.setWidth(100);
		logic.addColumn(c);
		
		
		
		logic.setFromTable("b_cpte_digital d left outer join ma_cuentas m on d.cuenta=m.codigo   ");
		String restriction=" d.carga_articulos=1 ";
		if (shift){
			restriction="";
		}
		if (!su){
			if (restriction.length()>0){
				restriction+=" and ";	
			}
			restriction+=" d.tc not like 'FCN' ";
		}
		logic.setRestriction(restriction);
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("m.descripcion");
		f.setValor(tx.getText());
		logic.addFilter(f);
		logic.setGroupby("d.cuenta,m.descripcion");
		
		
		logic.setTop(100);
		logic.setOrderby("count(distinct d.idcomprobante) desc ,d.cuenta,m.descripcion");
		

		int n=logic._loadoptions();		
		return n;

		
	}
	public int buscarProveedoresComprobantes(JTextField tx){
		this.initializeConstructor();
		this.initializeConstructorParameters();
		return this.initializeLogicPendientes(tx);
	}
	
	public int buscarComprobantes(JTextField tx,String[] options){
		
		return this.Buscar(tx,options);
		
	}


	public JComboBox getTc() {
		return tc;
	}


	public void setTc(JComboBox tc) {
		this.tc = tc;
	}
	
}
