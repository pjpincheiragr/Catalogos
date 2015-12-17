package aplicacion.herramientas.java.comprobantes;

import aplicacion.herramientas.java.comprobante.constructor._Constructor;
import aplicacion.herramientas.java.comprobante.logic.Filtro;
import aplicacion.herramientas.java.comprobante.logic._Logic;
import aplicacion.herramientas.java.comprobante.logic._Data;
import aplicacion.herramientas.java.comprobante.logic.columna;
import aplicacion.modelo.constructor.*;
public class fvn extends Generico{
	
	
	/**
	 * Para cargar el encabezado del comprobante.
	 * @return
	 */
	
	public fvn(Constructor C){
		super(C);
	}
	public void initializeLogic(){
			
				_Logic logic=(_Logic) C.getLogic();
				columna c = new columna();
				Filtro f = new Filtro();
				c = new columna();
				c.setNombre("idarticulo");
				c.setAlias("idarticulo");
				c.setWidth(80);
				logic.addColumn(c);
				
				c = new columna();
				c.setNombre("descripcion");
				c.setAlias("descripcion");
				c.setWidth(350);
				logic.addColumn(c);
				
				c = new columna();
				c.setNombre("cantidad");
				c.setAlias("cantidad");
				c.setWidth(120);
				logic.addColumn(c);
				
				c = new columna();
				c.setNombre("precio");
				c.setAlias("precio");
				c.setWidth(100);
				logic.addColumn(c);

				c = new columna();
				c.setNombre("total");
				c.setAlias("total");
				c.setWidth(100);
				logic.addColumn(c);
				
				logic.setFromTable("b_fvn_item ");
				logic.setFromTableEncabezado("b_fvn ");
				f = new Filtro();
				f.setNombre("convert(varchar(10),fecha_comprobante,105)");
				f.setAlias("fecha");
				f.setWidth(70);
				logic.addFilter(f);
				
				f = new Filtro();
				f.setNombre("cuenta");
				f.setAlias("idcliente");
				f.setWidth(90);
				logic.addFilter(f);
				
				f = new Filtro();
				f.setNombre("cuenta_descripcion");
				f.setAlias("nombre");
				f.setWidth(180);
				f.setMaster(false);
				logic.addFilter(f);
				
				f = new Filtro();
				f.setNombre("tc");
				f.setAlias("tc");
				f.setWidth(60);
				f.setValor(tc);
				f.setMaster(true);
				logic.addFilter(f);
				
				f = new Filtro();
				f.setNombre("idcomprobante");
				f.setAlias("idcomprobante");
				f.setValor(idcomprobante);
				f.setWidth(120);
				f.setMaster(true);
				logic.addFilter(f);
				
				f = new Filtro();
				f.setNombre("total_cpte");
				f.setAlias("importe");
				f.setWidth(120);
				logic.addFilter(f);
				
				f = new Filtro();
				f.setNombre("anulada");
				f.setAlias("anulada");
				f.setWidth(80);
				logic.addFilter(f);
				
				logic.addOrder("sec");
				logic.init();
				}

		
			
}
