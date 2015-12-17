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
 private aplicacion.herramientas.java.visualizadores.Politica
 vPolitica=null; public void buscarPolitica(JTextField tx) { if
 (vPolitica!=null){ vPolitica.close(); } vPolitica=new
 aplicacion.herramientas
 .java.visualizadores.Politica(this.getConstructor());
 int n = vPolitica.Buscar(tx); if (n == 0) {
 aviso("no hay Politicas con ese codigo"); } }
 */
public class Politica extends Generico {
	private String prefijo="";
	
	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	private String idproveedor = "";

	public String getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Politica(Constructor C) {
		super(C);
		
	}

	public int BuscarAdivina(JTextField tx){
		intializeConstructor();
		initializeConstructorParameters();
		int n=this.initializeLogicAdivina(tx);
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
		c.setMaster(true);
		c.setWidth(120);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(240);
		logic.addColumn(c);
		logic.setFromTable("v_ta_politicaprecios ");

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("descripcion");
		f.setValor(tx.getText());
		logic.addFilter(f);

		logic.setTop(100);
		logic.setOrderby("codigo");

		logic.setTitle("Buscador de Politica");
		int n = logic._loadoptions();

		return n;
	}

	public int initializeLogicAdivina(JTextField tx) {
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("v.politicaprecios");
		c.setAlias("codigo");
		c.setColumnField(tx);
		c.setMaster(true);
		c.setWidth(70);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("m.descripcion");
		c.setAlias("nombre");
		c.setWidth(170);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("count(*)");
		c.setAlias("utilidad");
		c.setWidth(100);
		logic.addColumn(c);
		logic
				.setFromTable("v_ma_articulos v left outer join v_ta_politicaprecios m on v.politicaprecios = m.codigo");

		String restriction = "isnull(v.politicaprecios,'') not like '' ";
		restriction += "and substring(v.idarticulo,0,4) like '"+prefijo+"' ";
		logic.setRestriction(restriction);
		logic.setGroupby("v.politicaprecios,m.descripcion");
		logic.setOrderby("count(*) desc,v.politicaprecios");
		logic.setTop(50);
		logic.setTitle("Buscador de Politica");
		int n = logic._loadoptions();

		return n;
	}

}
