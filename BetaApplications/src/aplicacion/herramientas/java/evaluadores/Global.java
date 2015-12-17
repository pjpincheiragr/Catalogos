package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Global Global=null;
	public void initialize_Global(){
		Global=new aplicacion.herramientas.java.evaluadores.Global(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Global().setText(descripcion);
			}
		};
		Global.setConstructor(this.getConstructor());
	}
	public void BuscarGlobal(JTextField tx){
		Global.Buscar(tx);
	}
	public void BuscarGlobal(){
		Global.Buscar(frame.get_txt_idGlobal());
	}
	public void buscarGlobal(JTextField tx){
		Global.buscar(tx);
	}
	
	public void evaluarGlobal(JTextField tx){
		Global.evaluate(tx);
	}

 */
public class Global extends Generic {

	public String getQuery(String id) {
		id=id.replaceAll(" ", "");
		String q = "";
		q += "select idcomprobante,fecha ";
		q += "from b_global ";
		q += "where ltrim(rtrim(idcomprobante)) like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay idcomprobante con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Global vGlobal = null;
	public void buscar(JTextField tx) {
		if (vGlobal == null) {
			vGlobal = new aplicacion.herramientas.java.visualizadores.Global(
					constructor);
		}
		int n = vGlobal.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay idcomprobante con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Global bGlobal = null;
	public void Buscar(JTextField ext) {
		if (bGlobal == null) {
			bGlobal = new aplicacion.herramientas.java.buscadores.Global(
					constructor);
		}
		
		bGlobal.Buscar(ext);
	}

}
