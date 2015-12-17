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
 private aplicacion.herramientas.java.buscadores.PedidoCliente bPedidoCliente=null; 
  public  void BuscarPedidoCliente(JTextField ext) { 
  if (bPedidoCliente!=null){  bPedidoCliente.close(); } 
  bPedidoCliente=new aplicacion.herramientas.java.buscadores.PedidoCliente(this.getConstructor()); 
  bPedidoCliente.Buscar(ext); 
  }
 */
public class PedidoProveedor extends Generico {
	

	public PedidoProveedor(Constructor C) {
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
		c.setNombre("CONVERT(VARCHAR(10), p.fecha, 105)");
		c.setAlias("Fecha");
		c.setWidth(80);
		c.setMaster(false);
		
		logic.addColumn(c);

		
		c = new columna();
		c.setNombre("p.idpedido");
		c.setAlias("idpedido");
		c.setColumnField(tx);
		c.setWidth(80);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("m.descripcion");
		c.setAlias("descripcion");
		c.setWidth(200);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("p.cuenta");
		c.setAlias("Cliente");
		c.setWidth(60);
		c.setMaster(true);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("m.descripcion");
		c.setAlias("Nombre");
		c.setWidth(240);
		c.setMaster(false);
		logic.addColumn(c);

		
		c = new columna();
		c.setNombre("p.estado");
		c.setAlias("Estado");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);
		
		logic.addFromTable("b_pdp p left outer join ma_cuentas m on p.cuenta=m.codigo ");
		
		f = new Filtro();
		f.setNombre("p.fecha");
		f.setAlias("Fecha");
		f.setWidth(60);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("p.idpedido");
		f.setAlias("idpedido");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("p.cuenta");
		f.setAlias("cliente");
		f.setWidth(70);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("m.descripcion");
		f.setAlias("nombre");
		f.setWidth(150);
		logic.addFilter(f);
		logic.addRestriction("isnull(p.eliminar,0) = 0");
		logic.addOrder("p.fecha desc,p.idpedido desc");
		logic.init();
	}
}
