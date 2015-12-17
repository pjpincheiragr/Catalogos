package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.FVN FVN=null;
	public void initialize_FVN(){
		FVN=new aplicacion.herramientas.java.evaluadores.FVN(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_FVN().setText(descripcion);
			}
		};
		FVN.setConstructor(this.getConstructor());
	}
	public void BuscarFVN(JTextField tx){
		FVN.Buscar(tx);
	}
	public void BuscarFVN(){
		FVN.Buscar(frame.get_txt_idFVN());
	}
	public void buscarFVN(JTextField tx){
		FVN.buscar(tx);
	}
	
	public void evaluarFVN(JTextField tx){
		FVN.evaluate(tx);
	}

 */
public class FVN extends Generic {
	String tc="";
	
	public void setTC(String tc){
		this.tc=tc;
	}
	
	public String getQuery(String id) {
		String q = "";
		q += "select * ";
		q += "from b_fvn ";
		q += "where idcomprobante like '" + id + "' ";
		q += "and tc like '"+tc+"'  ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay FVNs con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.FVN vFVN = null;

	public void buscar(JTextField tx) {
		if (vFVN == null) {
			vFVN = new aplicacion.herramientas.java.visualizadores.FVN(
					constructor);
		}
		
		if (tc.compareTo("")!=0){
			vFVN.setTC(tc);
		}
		int n = vFVN.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay FVNs con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.FVN bFVN = null;

	public void Buscar(JTextField ext) {
		if (bFVN == null) {
			bFVN = new aplicacion.herramientas.java.buscadores.FVN(
					constructor);
		}
		
		if (tc.compareTo("")!=0){
			bFVN.setTC(tc);
		}
		bFVN.Buscar(ext);
	}

}
