package aplicacion.herramientas.java.visualizadores;

import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Rectangle;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
 
private aplicacion.herramientas.java.visualizadores.Codigo vCodigo=null;
public void buscarCodigo(JTextField tx) {
	if (vCodigo!=null){
		vCodigo.close();
	}
	vCodigo=new aplicacion.herramientas.java.visualizadores.Codigo(this.getConstructor().getConnectionHandler());
	vCodigo.setIdproveedor(frame.get_txt_idproveedor().getText());
	int n = vCodigo.buscarCodigo(tx);
	if (n == 0) {
			aviso("no hay Codigos con ese codigo");
	}
}
*/

public class Codigo extends Generico{
	private boolean suspendidov=false;
	private boolean suspendidoc=false;
	public boolean isSuspendidov() {
		return suspendidov;
	}
	public Codigo(Constructor C){
		super(C);
	}
	
	
	public void initializeConstructor(){
		C = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
	}
	
	private JTextField linea = null;
	private JTextField idproveedor = null;
	private JTable table;
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
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
	private int col_proveedor=-1;
	public int getCol_proveedor() {
		return col_proveedor;
	}
	public void setCol_proveedor(int col_proveedor) {
		this.col_proveedor = col_proveedor;
	}

	private int col_linea=-1;
	public int initializeLogic(JTextField tx) {
	
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();

		c.setNombre("c.idcodigo");
		c.setAlias("idCodigo");
		c.setColumnField(tx);
		
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.descripcion");
		c.setAlias("descripcion");
		c.setWidth(200);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.lineaproveedor");
		c.setAlias("linea");
		if (linea != null)
			c.setColumnField(linea);
		if (col_linea>=0){
			c.setColumn(col_linea);
		}
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.idproveedor");
		c.setAlias("idproveedor");
		c.setWidth(120);
		if (idproveedor != null) {
			c.setColumnField(idproveedor);
			
		}
		
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.precio5");
		c.setAlias("Precio");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("m.descripcion");
		c.setAlias("nombre");
		c.setWidth(180);
		c.setMaster(false);
		logic.addColumn(c);

		logic.setFromTable("ma_cuentas m left outer join b_codigos c on m.codigo=c.idproveedor ");
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setFocus(true);
		f.setNombre("c.idCodigo");
		f.setAlias("idCodigo");
		f.setWidth(120);
		f.setValor(tx.getText());
		logic.addFilter(f);

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("c.lineaproveedor");
		f.setAlias("linea");
		f.setWidth(250);
		if (this.linea != null)
			f.setValor(linea.getText());

		logic.addFilter(f);

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("c.idproveedor");
		f.setAlias("idProveedor");
		if (this.idproveedor != null)
			f.setValor(idproveedor.getText());
		f.setWidth(150);
		logic.addFilter(f);

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("m.descripcion");
		f.setAlias("Nombre");
		f.setWidth(150);
		logic.addFilter(f);

		logic.setTitle("Buscador de Codigo");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	public int initializeLogic(JTextField tx,JTable table,int col,int row,int colproveedor,int collinea) {
		
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		logic.setTop(50);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();

		c.setNombre("c.idcodigo");
		c.setAlias("idCodigo");
		c.setJTable(table);
		c.setColumn(col);
		c.setRow(row);
		c.setColumnField(tx);
		
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.descripcion");
		c.setAlias("descripcion");
		c.setWidth(200);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.lineaproveedor");
		c.setAlias("linea");
		c.setJTable(table);
		c.setColumn(collinea);
		c.setRow(row);
		c.setWidth(140);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.idproveedor");
		c.setAlias("idproveedor");
		c.setWidth(120);
		c.setJTable(table);
		c.setColumn(colproveedor);
		c.setRow(row);
		
		
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("c.precio5");
		c.setAlias("Precio");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);

		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("m.descripcion");
		c.setAlias("nombre");
		c.setWidth(180);
		c.setMaster(false);
		logic.addColumn(c);

		logic.setFromTable("ma_cuentas m left outer join b_codigos c on m.codigo=c.idproveedor ");
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setFocus(true);
		f.setNombre("c.idCodigo");
		f.setAlias("idCodigo");
		f.setWidth(120);
		
		f.setValor(tx.getText());
		logic.addFilter(f);

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("c.lineaproveedor");
		f.setAlias("linea");
		f.setWidth(250);
		String linea="";
		try {
			linea=table.getValueAt(row, collinea).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (linea.compareTo("")!=0){
			f.setValor(linea);
		}
		logic.addFilter(f);

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("c.idproveedor");
		f.setAlias("idProveedor");
		String idproveedor="";
		try {
			idproveedor=table.getValueAt(row, colproveedor).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (idproveedor.compareTo("")!=0){
			f.setValor(idproveedor);
		}
			
		f.setWidth(150);
		logic.addFilter(f);

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("m.descripcion");
		f.setAlias("Nombre");
		f.setWidth(150);
		logic.addFilter(f);

		logic.setTitle("Buscador de Codigo");
		
		int n= logic._loadoptions();
		
		return n;

		
	}
	
	public int Buscar(JTextField tx,JTable table,int col,int row,int colproveedor,int collinea){
		if (C!=null){
			System.out.println("Ocultando Visualizador anterior");
			C.getFrame().setVisible(false);
			C.dispose();
		}else{
			System.err.println("(((C==null))");

		}
		System.out.println("Iniciando Constructor");
		intializeConstructor();
		initializeConstructorParameters();
		System.out.println("Iniciando logica");
		
		
		int n=initializeLogic(tx, table, col, row, colproveedor, collinea);
				
		
		return n;
	}
	
}
