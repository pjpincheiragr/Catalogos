package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Transporte Transporte=null;
	public void initialize_Transporte(){
		Transporte=new aplicacion.herramientas.java.evaluadores.Transporte(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Transporte().setText(descripcion);
			}
		};
		Transporte.setConstructor(this.getConstructor());
	}
	public void BuscarTransporte(JTextField tx){
		Transporte.Buscar(tx);
	}
	public void BuscarTransporte(){
		Transporte.Buscar(frame.get_txt_idTransporte());
	}
	public void buscarTransporte(JTextField tx){
		Transporte.buscar(tx);
	}
	
	public void evaluarTransporte(JTextField tx){
		Transporte.evaluate(tx);
	}

 */
public class Transporte extends Generic {

	public String getQuery(String id) {
		id=id.replaceAll(" ", "");
		String q = "";
		q += "select idTransporte,nombre ";
		q += "from Transportes ";
		q += "where ltrim(rtrim(idTransporte)) like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Transportes con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Transporte vTransporte = null;
	public void buscar(JTextField tx) {
		if (vTransporte == null) {
			vTransporte = new aplicacion.herramientas.java.visualizadores.Transporte(
					constructor);
		}
		int n = vTransporte.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay Transportes con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Transporte bTransporte = null;
	public void Buscar(JTextField ext) {
		if (bTransporte == null) {
			bTransporte = new aplicacion.herramientas.java.buscadores.Transporte(
					constructor);
		}
		
		bTransporte.Buscar(ext);
	}

}
