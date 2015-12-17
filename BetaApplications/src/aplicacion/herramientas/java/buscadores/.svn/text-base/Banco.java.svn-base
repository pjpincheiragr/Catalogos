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
  private  aplicacion.herramientas.java.buscadores.Banco bBanco=null; public
  void BuscarBanco(JTextField ext) { if (bBanco!=null){
  bBanco.close(); } bBanco=new
  aplicacion.herramientas.java.buscadores.Banco
  (this.getConstructor()); bBanco.Buscar(ext); }
 */
public class Banco extends Generico {
	private String idcaja="";
	public String getIdcaja() {
		return idcaja;
	}

	public void setIdcaja(String idcaja) {
		this.idcaja = idcaja;
	}

	private JTextField cuenta=null;
	
	public JTextField getCuenta() {
		return cuenta;
	}

	public void setCuenta(JTextField cuenta) {
		this.cuenta = cuenta;
	}

	public Banco(Constructor C) {
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
		c.setNombre("idbanco");
		c.setAlias("idbanco");
		c.setColumnField(tx);
		c.setWidth(80);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);



		logic.addFromTable(" v_ta_bancos");
		f = new Filtro();
		f.setNombre("idbanco");
		f.setAlias("idbanco");
		f.setWidth(120);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("descripcion");
		f.setAlias("descripcion");
		f.setWidth(120);
		logic.addFilter(f);
		logic.addOrder("idbanco");
		logic.init();
	}
	
	
}
