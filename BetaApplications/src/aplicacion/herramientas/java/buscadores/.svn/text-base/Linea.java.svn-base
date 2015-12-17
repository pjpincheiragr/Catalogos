package aplicacion.herramientas.java.buscadores;

import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CustomCellEditor;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.*;
import java.awt.Point;

/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al
 * pedo. :-)
 * 
 * @author Agustin
 * 
 */
/*
 * 
 * private aplicacion.herramientas.java.buscadores.Linea bLinea; public void
 * BuscarLinea(JTextField tx,int row){ CustomCellEditor
 * cell=(CustomCellEditor)frame.getJTable().getCellEditor(row, 8); JTextField
 * idproveedor=(JTextField)cell.getComponent(); if (bLinea!=null){
 * bLinea.close(); } bLinea=new
 * aplicacion.herramientas.java.buscadores.Linea(this
 * .getConstructor().getConnectionHandler());
 * 
 * bLinea.setIdproveedor(idproveedor);
 * 
 * 
 * boolean
 * ok=data.check_proveedor(idproveedor.getText())&idproveedor.getText().compareTo
 * ("")!=0; if (ok){ bLinea.BuscarLineaProveedor(tx); }else {aviso(
 * "Para buscar una linea en esta fila, debe ingresar un proveedor valido primero!"
 * ); } }
 * 
 * public void BuscarLinea(JTextField tx){ if (bLinea!=null){ bLinea.close(); }
 * bLinea=new
 * aplicacion.herramientas.java.buscadores.Linea(this.getConstructor()
 * .getConnectionHandler()); bLinea.setIdproveedor(frame.get_txt_idproveedor());
 * bLinea.BuscarLinea(tx);
 * 
 * }
 */

public class Linea extends Generico {
	private JTextField idproveedor = null;

	public JTextField getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(JTextField idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Linea(Constructor C) {
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
						
						System.out.println("Close de linea!!!");
						String value=table.getValueAt(row, 0).toString();
						
						_cargar(value);

					}
				};
			}
		};

	}

	public void _cargar(String codigo){
		
	}
	
	public void initializeLogicLineaProveedor(JTextField tx) {
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		columna c = new columna();
		Filtro f = new Filtro();

		c.setNombre("c.lineaproveedor");
		c.setAlias("Linea");
		c.setWidth(200);
		c.setMaster(true);
		c.setColumnField(tx);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.idproveedor");
		c.setAlias("Cod.Proveedor");
		c.setWidth(120);
		logic.addColumn(c);
		c = new columna();
		c.setNombre("m.descripcion");
		c.setAlias("Des.Proveedor");
		c.setWidth(180);
		logic.addColumn(c);
		logic
				.addFromTable("ma_cuentas m left outer join b_codigos c on m.codigo=c.idproveedor ");
		f = new Filtro();
		f.setNombre("c.lineaproveedor");
		f.setAlias("Linea Proveedor");
		f.setFocus(true);
		f.setWidth(200);
		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("c.idproveedor");
		f.setAlias("idproveedor");
		if (idproveedor != null)
			f.setValor(idproveedor.getText());
		f.setFocus(true);
		f.setWidth(200);
		logic.addFilter(f);

		logic.addRestriction("c.idproveedor like '21101%'");
		logic.addGroup("c.lineaproveedor,c.idproveedor,m.descripcion");
		logic.addOrder("c.lineaproveedor");
		logic.setTop(200);
		logic.setTitle("Buscador de Linea");
		
		logic.init();

	}

	public void initializeLogicLinea(JTextField tx) {
		
		aplicacion.herramientas.java.sortableselector.logic._Logic logic = (aplicacion.herramientas.java.sortableselector.logic._Logic) C
				.getLogic();
		//logic.aviso("Linea Busqueda "+tx);
		columna c = new columna();
		Filtro f = new Filtro();

		c.setNombre("c.descripabrev");
		c.setAlias("Linea");
		c.setWidth(200);
		c.setMaster(true);
		if (tx!=null){
			c.setColumnField(tx);	
		}
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.cuentaproveedor");
		c.setAlias("idProveedor");
		c.setWidth(120);
		logic.addColumn(c);
		c = new columna();
		c.setNombre("m.descripcion");
		c.setAlias("Nombre");
		c.setWidth(180);
		logic.addColumn(c);
		logic
				.addFromTable("ma_cuentas m left outer join v_ma_articulos c on m.codigo=c.cuentaproveedor ");
		f = new Filtro();
		f.setNombre("c.descripabrev");
		f.setAlias("Linea");
		f.setFocus(true);
		f.setWidth(200);
		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("c.cuentaproveedor");
		f.setAlias("idProveedor");
		if (idproveedor != null)
			f.setValor(idproveedor.getText());
		f.setFocus(true);
		f.setWidth(120);
		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("m.descripcion");
		f.setAlias("Nombre");
		f.setFocus(true);
		f.setWidth(160);
		logic.addFilter(f);

		logic.addRestriction("c.cuentaproveedor like '21101%'");
		logic.addGroup("c.cuentaproveedor,m.descripcion,c.descripabrev");
		logic.addOrder("c.cuentaproveedor");
		logic.setTop(200);
		logic.setTitle("Buscador de Linea");
		if (tx!=null){
			java.awt.Point p = tx.getLocationOnScreen();
			logic.setLocation(p.x, p.y + tx.getHeight());	
		}else{
			logic.centrar();
		}
		
		logic.init();

	}
	
	public void Buscar(JTextField tx){
		this.initializeConstructor();
		this.initializeConstructorParameters();
		this.initializeLogicLinea(tx);
	}
	
	public void BuscarLineaProveedor(JTextField tx) {
		this.initializeConstructor();
		this.initializeConstructorParameters();
		this.initializeLogicLineaProveedor(tx);
	}

	public void BuscarLinea(JTextField tx) {
		this.initializeConstructor();
		this.initializeConstructorParameters();
		this.initializeLogicLinea(tx);
	}
}
