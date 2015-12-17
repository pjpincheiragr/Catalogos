package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Provincia Provincia=null;
	public void initialize_Provincia(){
		Provincia=new aplicacion.herramientas.java.evaluadores.Provincia(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Provincia().setText(descripcion);
			}
		};
		Provincia.setConstructor(this.getConstructor());
	}
	public void BuscarProvincia(JTextField tx){
		Provincia.Buscar(tx);
	}
	public void BuscarProvincia(){
		Provincia.Buscar(frame.get_txt_idProvincia());
	}
	public void buscarProvincia(JTextField tx){
		Provincia.buscar(tx);
	}
	
	public void evaluarProvincia(JTextField tx){
		Provincia.evaluate(tx);
	}

 */
public class Provincia extends Generic {

	public String getQuery(String id) {
		id=id.replaceAll(" ", "");
		String q = "";
		q += "select codigo,descripcion ";
		q += "from ta_estados ";
		q += "where ltrim(rtrim(codigo)) like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Provincias con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Provincia vProvincia = null;
	public void buscar(JTextField tx) {
		if (vProvincia == null) {
			vProvincia = new aplicacion.herramientas.java.visualizadores.Provincia(
					constructor);
		}
		int n = vProvincia.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay Provincias con ese codigo");
		}
	}

	public void dispose(){
		if (vProvincia!=null){
			vProvincia.dispose();	
		}
		
		if (bProvincia!=null){
			bProvincia.dispose();	
		}
		
	}
	private aplicacion.herramientas.java.buscadores.Provincia bProvincia = null;
	public void Buscar(JTextField ext) {
		if (bProvincia == null) {
			bProvincia = new aplicacion.herramientas.java.buscadores.Provincia(
					constructor);
		}
		
		bProvincia.Buscar(ext);
	}

}
