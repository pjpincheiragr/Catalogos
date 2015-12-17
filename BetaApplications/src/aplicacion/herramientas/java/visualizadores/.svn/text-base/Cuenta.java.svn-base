package aplicacion.herramientas.java.visualizadores;

import java.awt.Rectangle;

import javax.swing.JTextField;
import aplicacion.modelo.constructor.*;
import aplicacion.herramientas.conexion.ConnectionHandler;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
 
private aplicacion.herramientas.java.visualizadores.Cuenta vCuenta=null;
public void buscarCuenta(JTextField tx) {
	if (vCuenta!=null){
		vCuenta.close();
	}
	vCuenta=new aplicacion.herramientas.java.visualizadores.Cuenta(this.getConstructor().getConnectionHandler());
	int n = vCuenta.Buscar(tx);
	if (n == 0) {
			aviso("no hay Cuentas con ese codigo");
	}
}
*/
public class Cuenta extends Generico{

private String idCuenta="";


	public String getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Cuenta(Constructor C){
		super(C);
	}
	
	public void initializeConstructor(){
		C = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
	}
	public int initializeLogic(JTextField tx) {
		aplicacion.herramientas.java.visualselector.logic._Logic logic = (aplicacion.herramientas.java.visualselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		aplicacion.herramientas.java.visualselector.logic.Columna c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		aplicacion.herramientas.java.visualselector.logic.Filtro f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("codigo");
		c.setAlias("cuenta");
		c.setColumnField(tx);
		c.setWidth(120);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("descripcion");
		c.setAlias("descripcion");
		c.setWidth(240);
		logic.addColumn(c);

		logic.setFromTable("ma_cuentas ");
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("descripcion");
		f.setValor(tx.getText());
		logic.addFilter(f);
		
		
		
		logic.setTop(100);
		logic.setOrderby("descripcion");
		logic.setRestriction("titulo=0");
		logic.setTitle("Buscador de Cuenta");
		
		int n= logic._loadoptions();
		return n;

	}
	
}
