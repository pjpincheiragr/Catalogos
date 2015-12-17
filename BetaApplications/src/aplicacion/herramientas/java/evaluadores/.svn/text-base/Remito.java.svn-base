package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Remito Remito=null;
	public void initialize_Remito(){
		Remito=new aplicacion.herramientas.java.evaluadores.Remito(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Remito().setText(descripcion);
			}
		};
		Remito.setConstructor(this.getConstructor());
	}
	public void BuscarRemito(JTextField tx){
		Remito.Buscar(tx);
	}
	public void BuscarRemito(){
		Remito.Buscar(frame.get_txt_idRemito());
	}
	public void buscarRemito(JTextField tx){
		Remito.buscar(tx);
	}
	
	public void evaluarRemito(JTextField tx){
		Remito.evaluate(tx);
	}

 */
public class Remito extends Generic {

	public String getQuery(String id) {
		String q = "";
		q += "select * ";
		q += "from v_mv_cpte ";
		q += "where idcomprobante like '" + id + "' ";
		q += "and tc like 'RM' and finalizada=0 ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Remitos con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Remito vRemito = null;

	public void buscar(JTextField tx) {
		if (vRemito == null) {
			vRemito = new aplicacion.herramientas.java.visualizadores.Remito(
					constructor);
		}
		
		int n = vRemito.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay Remitos con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Remito bRemito = null;

	public void Buscar(JTextField ext) {
		if (bRemito == null) {
			bRemito = new aplicacion.herramientas.java.buscadores.Remito(
					constructor);
		}
	
		bRemito.Buscar(ext);
	}

}
