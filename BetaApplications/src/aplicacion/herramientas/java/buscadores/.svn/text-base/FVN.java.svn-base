package aplicacion.herramientas.java.buscadores;

import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.*;

/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al
 * pedo. :-)
 * 
 * @author Agustin
 * 
 */
/*
 * Como utilizarlo? pegar este codigo en la aplicacion 
 private aplicacion.herramientas.java.buscadores.FVN bFVN=null; 
  public  void BuscarFVN(JTextField ext) { 
  if (bFVN!=null){  bFVN.close(); } 
  bFVN=new aplicacion.herramientas.java.buscadores.FVN(this.getConstructor()); 
  bFVN.Buscar(ext); 
  }
 */
public class FVN extends Generico {
	private String TC="";
	public String getTC() {
		return TC;
	}

	public void setTC(String tc) {
		TC = tc;
	}

	public FVN(Constructor C) {
		super(C);
	}

	public void initializeConstructor() {
		C = new aplicacion.herramientas.java.sortableselector.constructor._Constructor() {
			@Override
			protected void initialize_logic() {
				_logic = new aplicacion.herramientas.java.sortableselector.logic._Logic() {
					@Override
					public void Close(JTable table, int row) {

						super.Close(table, row);

					}
				};
			}
		};
	}

	public void initializeLogic(JTextField tx) {
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		columna c = new columna();
		Filtro f = new Filtro();
		c = new columna();
		c.setNombre("CONVERT(VARCHAR(10), fecha_comprobante, 105)");
		c.setAlias("fecha_comprobante");
		c.setWidth(80);
		c.setMaster(false);
		
		logic.addColumn(c);

		c = new columna();
		c.setNombre("tc");
		c.setAlias("TC");
		c.setWidth(50);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("idcomprobante");
		c.setAlias("idcomprobante");
		c.setColumnField(tx);
		c.setWidth(80);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("total_cpte");
		c.setAlias("importe");
		c.setWidth(60);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("cuenta");
		c.setAlias("Cuenta");
		c.setWidth(60);
		c.setMaster(true);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("cuenta_descripcion");
		c.setAlias("Nombre");
		c.setWidth(160);
		c.setMaster(false);
		logic.addColumn(c);

		
		logic.addFromTable("b_fvn ");
		
		f = new Filtro();
		f.setNombre("fecha_comprobante");
		f.setAlias("Fecha");
		f.setWidth(60);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("idcomprobante");
		f.setAlias("idcomprobante");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("cuenta");
		f.setAlias("cuenta");
		f.setWidth(70);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("cuenta_descripcion");
		f.setAlias("nombre");
		f.setWidth(150);
		logic.addFilter(f);
		if (TC.compareTo("")!=0){
			logic.addRestriction("tc like '"+TC+"'");
		}
		logic.addOrder("fecha_comprobante,idcomprobante");
		logic.init();
	}
}
