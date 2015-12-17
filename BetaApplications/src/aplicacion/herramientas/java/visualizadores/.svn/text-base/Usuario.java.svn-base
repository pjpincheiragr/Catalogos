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
 
private aplicacion.herramientas.java.visualizadores.Usuario vUsuario=null;
public void buscarUsuario(JTextField tx) {
	if (vUsuario!=null){
		vUsuario.close();
	}
	vUsuario=new aplicacion.herramientas.java.visualizadores.Usuario(this.getConstructor().getConnectionHandler());
	int n = vUsuario.Buscar(tx);
	if (n == 0) {
			aviso("no hay Usuarios con ese codigo");
	}
}
*/
public class Usuario extends Generico{

	private String idUsuario="";	
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario(Constructor C){
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

		c.setNombre("iduser");
		c.setAlias("idUser");
		c.setColumnField(tx);
		c.setWidth(120);
		logic.addColumn(c);
		
		c = new aplicacion.herramientas.java.visualselector.logic.Columna();
		c.setNombre("nombre");
		c.setAlias("nombre");
		c.setWidth(120);
		logic.addColumn(c);
		logic.setFromTable("b_users ");
		

		f = new aplicacion.herramientas.java.visualselector.logic.Filtro();
		f.setNombre("nombre");
		f.setValor(tx.getText());
		logic.addFilter(f);

		logic.setTop(100);
		
		logic.setOrderby("iduser");

		logic.setTitle("Buscador de Usuario");
		
		int n= logic._loadoptions();
		
		return n;

	}
	
}
