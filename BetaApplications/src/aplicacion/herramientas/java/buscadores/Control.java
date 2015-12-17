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
  private
  aplicacion.herramientas.java.buscadores.Control bControl=null; public
  void BuscarControl(JTextField ext) { if (bControl!=null){
  bControl.close(); } bControl=new
  aplicacion.herramientas.java.buscadores.Control
  (this.getConstructor()); bControl.Buscar(ext); }
 */
public class Control extends Generico {

	private String iddeposito="";	

	public String getDeposito() {
		return iddeposito;
	}

	public void setDeposito(String deposito) {
		this.iddeposito = deposito;
	}
	
	public Control(Constructor C) {
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
		c.setNombre("c.idcontrol");
		c.setAlias("idcontrol");
		c.setColumnField(tx);
		c.setWidth(80);
		c.setMaster(false);
		
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.idusuario");
		c.setAlias("idusuario");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.linea");
		c.setAlias("linea");
		c.setWidth(280);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("s.iddeposito");
		c.setAlias("iddeposito");
		c.setWidth(280);
		c.setMaster(false);
		logic.addColumn(c);
		
		
		c = new columna();
		c.setNombre("(case when count(s.idcomprobante)>0 then 'APLICADO' else 'SIN APLICACION' end)");
		c.setAlias("estado");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);
		
		
		logic.addFromTable("b_control c left outer join v_mv_stock s on c.idcontrol like s.idcomprobante and s.tc like 'CTRL' ");
		
		f = new Filtro();
		f.setNombre("c.idcontrol");
		f.setAlias("idcontrol");
		f.setWidth(60);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("c.idusuario");
		f.setAlias("idusuario");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("c.linea");
		f.setAlias("linea");
		f.setWidth(70);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("s.iddeposito");
		f.setAlias("iddeposito");
		f.setWidth(70);
		logic.addFilter(f);
		
		logic.addGroup("c.idcontrol,c.idusuario,c.linea,s.iddeposito");
		logic.addOrder("c.idcontrol");
		String restriction=" isnull(c.eliminar,0)=0 ";
		if (iddeposito.compareTo("")!=0){
			restriction+=" and s.iddeposito like '"+iddeposito+"'";
		}
		logic.addRestriction(restriction);
		logic.init();
	}
}
