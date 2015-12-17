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
 * Como utilizarlo? pegar este codigo en la aplicacion private
 * aplicacion.herramientas.java.buscadores.Cheque bCheque=null; public
 * void BuscarCheque(JTextField ext) { if (bCheque!=null){
 * bCheque.close(); } bCheque=new
 * aplicacion.herramientas.java.buscadores.Cheque
 * (this.getConstructor().getConnectionHandler()); bCheque.Buscar(ext); }
 */
public class Cheque extends Generico {
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

	public Cheque(Constructor C) {
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
		c.setNombre("convert(varchar(10),a.fecha,105)");
		c.setAlias("Fecha");
		c.setColumnField(tx);
		c.setWidth(100);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("a.tc");
		c.setAlias("tc");
		c.setColumnField(tx);
		c.setWidth(60);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("a.idcomprobante");
		c.setAlias("idcomprobante");
		c.setColumnField(tx);
		c.setWidth(110);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("b.cht_idbanco");
		c.setAlias("idbanco");
		c.setColumnField(tx);
		c.setWidth(60);
		c.setMaster(true);
		logic.addColumn(c);

		
		c = new columna();
		c.setNombre("banco.descripcion");
		c.setAlias("Nombre");
		c.setColumnField(tx);
		c.setWidth(110);
		c.setMaster(true);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("b.cht_serie");
		c.setAlias("serie");
		c.setColumnField(tx);
		c.setWidth(60);
		c.setMaster(true);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("b.cht_numero");
		c.setAlias("numero");
		c.setColumnField(tx);
		c.setWidth(90);
		c.setMaster(true);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("convert(varchar(10),b.cht_vencimiento,105)");
		c.setAlias("vencimiento");
		c.setColumnField(tx);
		c.setWidth(110);
		c.setMaster(true);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("b.cht_importe");
		c.setAlias("importe");
		c.setColumnField(tx);
		c.setWidth(90);
		c.setMaster(true);
		logic.addColumn(c);
		
		
		String cuenta="";
		if (this.cuenta!=null){
			cuenta=this.cuenta.getText();
		}
		String from="b_mv_asientos a left outer join b_mv_asientos b ";
		from+="on a.mes_operativo=b.mes_operativo and a.numero_asiento=b.numero_asiento and a.cuenta like '%"+cuenta+"%' ";
		from+="left outer join v_ta_bancos banco on ltrim(b.cht_idbanco)=ltrim(banco.idbanco) ";
		logic.addFromTable(from);
		
		
		f = new Filtro();
		f.setNombre("b.cht_importe");
		f.setAlias("importe");
		f.setWidth(250);
		logic.addFilter(f);
		logic.addRestriction("(a.tc like 'pg'or a.tc like 'egr') and b.cht_importe is not null and a.anulado=0 and a.idcajas='"+this.idcaja+"'");
		logic.init();
	}
	
	public void initializeLogicCheques(JTextField tx){
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) C
		.getLogic();
		logic.setCaller(tx);
		Filtro f = new Filtro();
		columna c = new columna();
		c.setNombre("ltrim(a.cht_idbanco)");
		c.setAlias("idbanco");
		c.setColumnField(tx);
		c.setWidth(80);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("b.descripcion");
		c.setAlias("descripcion");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("ltrim(a.cht_serie)");
		c.setAlias("serie");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("a.cht_numero");
		c.setAlias("numero");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("a.cht_importe");
		c.setAlias("importe");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("CONVERT(VARCHAR(10), a.cht_vencimiento  , 105)");
		c.setAlias("vencimiento");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("max(case when a.debe_haber like 'd' then CONVERT(varchar(10), a.fecha, 105) else null end)");
		c.setAlias("fecha entrada");
		c.setWidth(160);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("max(case when a.debe_haber like 'h' then CONVERT(varchar(10), a.fecha, 105) else null end)");
		c.setAlias("fecha salida");
		c.setWidth(160);
		c.setMaster(false);
		logic.addColumn(c);
		
		//
		logic.addFromTable(" b_mv_asientos a left outer join v_ta_bancos b on ltrim(a.cht_idbanco)=ltrim(b.idbanco) ");
		f = new Filtro();
		f.setNombre("ltrim(a.cht_idbanco)");
		f.setAlias("idbanco");
		f.setWidth(120);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("b.descripcion");
		f.setAlias("descripcion");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("a.cht_serie");
		f.setAlias("serie");
		f.setWidth(50);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("a.cht_numero");
		f.setAlias("numero");
		f.setWidth(50);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("a.cht_importe");
		f.setAlias("importe");
		f.setWidth(50);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("a.cht_vencimiento");
		f.setAlias("vencimiento");
		f.setWidth(50);
		logic.addFilter(f);
		
		logic.addRestriction("a.cht_importe is not null and a.anulado =0 and a.idcajas='"+idcaja+"'");
		logic.addGroup("ltrim(a.cht_idbanco),ltrim(a.cht_serie),a.cht_numero,a.cht_importe,a.cht_vencimiento,b.descripcion having sum(case when a.debe_haber like 'd' then 1 else -1 end)>0 ");
		logic.addOrder("a.cht_vencimiento");
		logic.init();
		
	}
	
	public void BuscarCheque(JTextField tx){
		this.initializeConstructor();
		this.initializeConstructorParameters();
		this.initializeLogicCheques(tx);
	}
}
