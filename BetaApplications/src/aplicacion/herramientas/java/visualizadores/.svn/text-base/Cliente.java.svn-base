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
 
private aplicacion.herramientas.java.visualizadores.Cliente vCliente=null;
public void buscarCliente(JTextField tx) {
	if (vCliente!=null){
		vCliente.close();
	}
	vCliente=new aplicacion.herramientas.java.visualizadores.Cliente(this.getConstructor());
	int n = vCliente.Buscar(tx);
	if (n == 0) {
			aviso("no hay Clientes con ese codigo");
	}
}
*/
public class Cliente extends Generico{

	private String idCliente="";	
	
	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public Cliente(Constructor C){
		super(C);
	}
	
	public void initializeConstructor(){
		C = new aplicacion.herramientas.java.visualselector.constructor._Constructor();
	}
	
	
	public int initializeLogic(JTextField tx) {
	
		aplicacion.herramientas.java.visualselector.logic._Logic logic = 
			(aplicacion.herramientas.java.visualselector.logic._Logic) C.getLogic();
		
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
		logic.setRestriction("titulo = 0 ");
		
		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("descripcion");
		f.setValor(tx.getText());
		logic.addFilter(f);
		
		
		
		logic.setTop(100);
		logic.setOrderby("codigo,descripcion");
		logic.setRestriction("codigo like '11201%' and codigo not like '11201' AND DADA_DE_BAJA = 0 ");
		logic.setTitle("Buscador de Cliente");
		
		int n= logic._loadoptions();
		if (n<=0){
			
		}
		return n;

	}
	
}
