package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Query Query=null;
	public void initialize_Query(){
		Query=new aplicacion.herramientas.java.evaluadores.Query(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Query().setText(descripcion);
			}
		};
		Query.setConstructor(this.getConstructor());
	}
	public void BuscarQuery(JTextField tx){
		Query.Buscar(tx);
	}
	public void BuscarQuery(){
		Query.Buscar(frame.get_txt_idQuery());
	}
	public void buscarQuery(JTextField tx){
		Query.buscar(tx);
	}
	
	public void evaluarQuery(JTextField tx){
		Query.evaluate(tx);
	}

 */
public class Query extends Generic {

	public String getQuery(String id) {
		String q = "";
		q += "select idquery,idproveedor ";
		q += "from b_query ";
		q += "where idquery like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "No hay Querys con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Query vQuery = null;
	public void buscar(JTextField tx) {
		System.out.println("Creando visualizador con tx "+tx);
		if (vQuery == null) {
			vQuery = new aplicacion.herramientas.java.visualizadores.Query(
					constructor);
		}
		
		int n = vQuery.Buscar(tx);
		if (n == 0) {
			
			constructor.getLogic().aviso("No hay Query con ese codigo");
			
		}
	}

	private aplicacion.herramientas.java.buscadores.Query bQuery = null;
	public void Buscar(JTextField ext) {
		if (bQuery == null) {
			bQuery = new aplicacion.herramientas.java.buscadores.Query(
					constructor);
		}else{
			
		}
		
		bQuery.Buscar(ext);
	}

	
}
