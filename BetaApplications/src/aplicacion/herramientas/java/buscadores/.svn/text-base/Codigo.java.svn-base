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

public class Codigo extends Generico {
	private JTextField linea = null;
	private JTextField idproveedor = null;

	public JTextField getLinea() {
		return linea;
	}

	public void setLinea(JTextField linea) {
		this.linea = linea;
	}

	public JTextField getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(JTextField idproveedor) {
		this.idproveedor = idproveedor;
	}

	public Codigo(Constructor C) {
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
		c.setNombre("c.idcodigo");
		c.setAlias("idCodigo");
		c.setColumnField(tx);
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.descripcion");
		c.setAlias("descripcion");
		c.setWidth(200);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.lineaproveedor");
		c.setAlias("linea");
		if (linea != null)
			c.setColumnField(linea);
		c.setWidth(140);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.idproveedor");
		c.setAlias("idproveedor");
		c.setWidth(120);
		if (idproveedor != null) {
			c.setColumnField(idproveedor);
		}
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.precio5");
		c.setAlias("Precio");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("m.descripcion");
		c.setAlias("nombre");
		c.setWidth(180);
		c.setMaster(false);
		logic.addColumn(c);

		logic
				.addFromTable("ma_cuentas m left outer join b_codigos c on m.codigo=c.idproveedor ");
		f = new Filtro();
		f.setFocus(true);
		f.setNombre("c.idCodigo");
		f.setAlias("idCodigo");
		f.setWidth(120);
		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("c.lineaproveedor");
		f.setAlias("linea");
		f.setWidth(250);
		if (this.linea != null)
			f.setValor(linea.getText());

		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("c.idproveedor");
		f.setAlias("idProveedor");
		if (this.idproveedor != null)
			f.setValor(idproveedor.getText());
		f.setWidth(150);
		logic.addFilter(f);

		f = new Filtro();
		f.setNombre("m.descripcion");
		f.setAlias("Nombre");
		f.setWidth(150);
		logic.addFilter(f);

		logic.setTop(200);
		logic.addOrder("c.idCodigo");
		logic.setTitle("Buscador de Codigo");
		
		logic.init();
	}

}
