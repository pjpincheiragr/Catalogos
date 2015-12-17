package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Politica Politica=null;
	public void initialize_Politica(){
		Politica=new aplicacion.herramientas.java.evaluadores.Politica(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Politica().setText(descripcion);
			}
		};
		Politica.setConstructor(this.getConstructor());
	}
	public void BuscarPolitica(JTextField tx){
		Politica.Buscar(tx);
	}
	public void BuscarPolitica(){
		Politica.Buscar(frame.get_txt_idPolitica());
	}
	public void buscarPolitica(JTextField tx){
		Politica.buscar(tx);
	}
	
	public void evaluarPolitica(JTextField tx){
		Politica.evaluate(tx);
	}

 */
public class Politica extends Generic {

	public String getQuery(String id) {
		String q = "";
		q += "select codigo,descripcion ";
		q += "from v_ta_Politicaprecios ";
		q += "where codigo like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Politicas con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Politica vPolitica = null;
	public void buscar(JTextField tx) {
		System.out.println("Creando visualizador con tx "+tx);
		if (vPolitica == null) {
			vPolitica = new aplicacion.herramientas.java.visualizadores.Politica(
					constructor);
		}
		
		int n = vPolitica.Buscar(tx);
		if (n == 0) {
			
			constructor.getLogic().aviso("no hay Politicas con ese codigo");
			
		}
	}

	private aplicacion.herramientas.java.buscadores.Politica bPolitica = null;
	public void Buscar(JTextField ext) {
		if (bPolitica == null) {
			bPolitica = new aplicacion.herramientas.java.buscadores.Politica(
					constructor);
		}else{
			
		}
		bPolitica.Buscar(ext);
	}

	
}
