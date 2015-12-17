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
  aplicacion.herramientas.java.buscadores.TransferenciaStock bTransferenciaStock=null; public
  void BuscarTransferenciaStock(JTextField ext) { if (bTransferenciaStock!=null){
  bTransferenciaStock.close(); } bTransferenciaStock=new
  aplicacion.herramientas.java.buscadores.TransferenciaStock
  (this.getConstructor()); bTransferenciaStock.Buscar(ext); }
 */
public class TransferenciaStock extends Generico {

	public TransferenciaStock(Constructor C) {
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
		c.setNombre("c.idTransferencia");
		c.setAlias("idTransferencia");
		c.setColumnField(tx);
		c.setWidth(80);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.fecha");
		c.setAlias("fecha");
		c.setColumnField(tx);
		c.setWidth(80);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("c.iduser");
		c.setAlias("iduser");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.iddeposito_origen");
		c.setAlias("origen");
		c.setWidth(280);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("c.iddeposito_destino");
		c.setAlias("destino");
		c.setWidth(280);
		c.setMaster(false);
		logic.addColumn(c);
	
		c = new columna();
		c.setNombre("(case when count(s.idcomprobante)>0 then 'APLICADO' else 'SIN APLICACION' end)");
		c.setAlias("estado");
		c.setWidth(140);
		logic.addColumn(c);
		
		logic.addFromTable(" b_transferencia c left outer join v_mv_stock s on c.idcontrol like s.idcomprobante and s.tc like 'TRSK' ");
		logic.addGroup("c.idtransferencia,c.iduser");
		f = new Filtro();
		f.setNombre("c.idTransferenciaStock");
		f.setAlias("idTransferenciaStock");
		f.setWidth(60);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("c.iduser");
		f.setAlias("idusuario");
		f.setWidth(120);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("c.iddeposito_origen");
		f.setAlias("origen");
		f.setWidth(70);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("c.iddeposito_destino");
		f.setAlias("destino");
		f.setWidth(70);
		logic.addFilter(f);
		
		logic.addGroup("c.idTransferencia_Stock,c.fecha,c.iduser");
		logic.addOrder("c.idTransferencia_Stock");
		logic.setTitle("Buscardor de Transferencia de Stock");
		logic.init();
	}
}
