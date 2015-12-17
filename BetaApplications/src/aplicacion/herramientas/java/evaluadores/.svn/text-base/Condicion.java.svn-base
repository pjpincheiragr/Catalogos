package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Condicion Condicion=null;
	public void initialize_Condicion(){
		Condicion=new aplicacion.herramientas.java.evaluadores.Condicion(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Condicion().setText(descripcion);
			}
		};
		Condicion.setConstructor(this.getConstructor());
	}
	public void BuscarCondicion(JTextField tx){
		Condicion.Buscar(tx);
	}
	public void BuscarCondicion(){
		Condicion.Buscar(frame.get_txt_idCondicion());
	}
	public void buscarCondicion(JTextField tx){
		Condicion.buscar(tx);
	}
	
	public void evaluarCondicion(JTextField tx){
		Condicion.evaluate(tx);
	}

 */
public class Condicion extends Generic {

	public String getQuery(String id) {
		id=id.replaceAll(" ", "");
		String q = "";
		q += "select ltrim(rtrim(idcond_cpra_vta)),descripcion ";
		q += "from v_ta_cpra_vta ";
		q += "where ltrim(rtrim(idcond_cpra_vta)) like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Condicions con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Condicion vCondicion = null;

	public void buscar(JTextField tx) {
		if (vCondicion == null) {
			vCondicion = new aplicacion.herramientas.java.visualizadores.Condicion(
					constructor);
		}
		
		int n = vCondicion.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay Condicions con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Condicion bCondicion = null;

	public void Buscar(JTextField ext) {
		if (bCondicion == null) {
			bCondicion = new aplicacion.herramientas.java.buscadores.Condicion(
					constructor);
		}
		
		bCondicion.Buscar(ext);
	}

}
