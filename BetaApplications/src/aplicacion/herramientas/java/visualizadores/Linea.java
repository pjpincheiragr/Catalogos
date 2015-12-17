package aplicacion.herramientas.java.visualizadores;

import java.awt.Rectangle;

import javax.swing.JTextField;

import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;

/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al
 * pedo. :-)
 * 
 * @author Agustin
 * 
 */
/*
 * Como utilizarlo? pegar este codigo en la aplicacion
 * 
  private aplicacion.herramientas.java.visualizadores.Linea vLinea=null; public
  void buscarLinea(JTextField tx) { if (vLinea!=null){ vLinea.close(); }
  vLinea=new
  aplicacion.herramientas.java.visualizadores.Linea(this.getConstructor
  ());
  vLinea.setIdproveedor(frame.get_txt_idproveedor().getText()); int n =
  vLinea.buscarLinea(tx); if (n == 0) { aviso("no hay Lineas con ese codigo");
  } }
 */
public class Linea extends Generico {

	private String idproveedor = "";

	public String getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}

	private String prefijo = "";

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public Linea(Constructor C) {
		super(C);
	}

	public void initializeConstructor() {
		C = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
	}

	public int buscarLinea(JTextField tx) {
		this.initializeConstructor();
		this.initializeConstructorParameters();
		return this.initializeLogicLinea(tx);
	}

	public int initializeLogicLinea(JTextField tx) {
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("r.idrubro");
		c.setAlias("IdRubro");
		c.setMaster(true);
		c.setColumnField(tx);
		c.setWidth(70);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("r.descripcion");
		c.setAlias("Linea");
		c.setWidth(320);
		logic.addColumn(c);

		logic.setFromTable("v_ma_articulos v left outer join v_ta_rubros r on v.idrubro = r.idrubro  ");
		if (idproveedor.compareTo("")!=0){
			logic.setRestriction("v.cuentaproveedor like '" + idproveedor + "'");	
		}

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("r.descripcion");
		f.setValor(tx.getText());
		logic.addFilter(f);
		logic.setTop(100);
		
		logic.setGroupby("r.descripcion,r.idrubro");
		logic.setOrderby("r.descripcion");
		logic.setTitle("Buscador de Linea");
		int n = logic._loadoptions();
		return n;

	}

	public int buscarLineaProveedor(JTextField tx) {
		this.initializeConstructor();
		this.initializeConstructorParameters();
		return this.initializeLogicLineaProveedor(tx);
	}

	public int initializeLogicLineaProveedor(JTextField tx) {

		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("lineaproveedor");
		c.setAlias("Linea");
		c.setColumnField(tx);
		c.setWidth(320);
		logic.addColumn(c);

		logic.setFromTable("b_codigos ");

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("lineaproveedor");
		f.setAlias("Linea");
		f.setValor(tx.getText());
		logic.addFilter(f);

		logic.setTop(100);
		logic.setGroupby("Lineaproveedor");
		logic.setOrderby("Lineaproveedor");
		logic.setTitle("Buscador de Linea");
		int n = logic._loadoptions();

		return n;

	}

	public int buscarLineaAdivina(JTextField tx){
		this.initializeConstructor();
		this.initializeConstructorParameters();
		return this.initalizeLogicAdivina(tx);
	}
	
	public int initalizeLogicAdivina(JTextField tx) {

		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("r.idrubro");
		c.setAlias("IdRubro");
		c.setMaster(true);
		c.setColumnField(tx);
		c.setWidth(70);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("r.descripcion");
		c.setAlias("Linea");
		c.setWidth(320);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("count(*)");
		c.setAlias("utilidad");
		c.setWidth(120);
		logic.addColumn(c);
		logic.setFromTable(" v_ma_articulos v left outer join v_ta_rubros r on v.idrubro = r.idrubro ");

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("r.descripcion");
		f.setAlias("Linea");
		f.setValor(tx.getText());
		logic.addFilter(f);

		String restriction = "";
		restriction += "isnull(r.descripcion,'') not like '' ";
		restriction += "and substring(v.idarticulo,0,4) like '" + prefijo + "' ";
		restriction += "";
		logic.setTop(100);
		logic.setRestriction(restriction);
		logic.setGroupby("r.descripcion, r.idrubro");
		logic.setOrderby("count(*), r.descripcion, r.idrubro ");
		logic.setTitle("Buscador de Linea");

		int n = logic._loadoptions();
		return n;
	}
}
