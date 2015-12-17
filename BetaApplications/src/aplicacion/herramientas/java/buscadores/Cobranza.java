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
 private aplicacion.herramientas.java.buscadores.Cobranza bCobranza=null; 
  public  void BuscarCobranza(JTextField ext) { 
  if (bCobranza!=null){  bCobranza.close(); } 
  bCobranza=new aplicacion.herramientas.java.buscadores.Cobranza(this.getConstructor()); 
  bCobranza.Buscar(ext); 
  }
 */
public class Cobranza extends Generico {

	public Cobranza(Constructor C) {
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
		c.setNombre("CONVERT(VARCHAR(10), a.fecha, 105)");
		c.setAlias("fecha");
		c.setWidth(80);
		c.setMaster(false);
		
		logic.addColumn(c);

		c = new columna();
		c.setNombre("a.tc");
		c.setAlias("TC");
		c.setWidth(50);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("a.idcomprobante");
		c.setAlias("idcomprobante");
		c.setColumnField(tx);
		c.setWidth(80);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("a.importe");
		c.setAlias("importe");
		c.setWidth(60);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("a.cuenta");
		c.setAlias("cuenta");
		c.setWidth(60);
		c.setMaster(true);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("c.descripcion");
		c.setAlias("descripcion");
		c.setWidth(160);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("(case when a.anulada = 1 then  'ANULADO' else 'OK' end)");
		c.setAlias("Estado");
		
		c.setWidth(80);
		c.setMaster(false);
		logic.addColumn(c);
		
		logic.addFromTable("b_cbs a left outer join ma_cuentas c on a.cuenta=c.codigo ");
		
		f = new Filtro();
		f.setNombre("a.fecha");
		f.setAlias("fecha");
		f.setWidth(60);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("a.idcomprobante");
		f.setAlias("idcomprobante");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("c.codigo");
		f.setAlias("cuenta");
		f.setWidth(70);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("c.descripcion");
		f.setAlias("descripcion");
		f.setWidth(150);
		logic.addFilter(f);
		logic.addRestriction(" a.cuenta like '11201%' and a.tc like 'CBCT' ");
		logic.addOrder("a.fecha desc");
		logic.init();
	}
}
