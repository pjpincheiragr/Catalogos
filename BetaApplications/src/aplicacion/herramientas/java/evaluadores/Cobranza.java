package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Cobranza Cobranza=null;
	public void initialize_Cobranza(){
		Cobranza=new aplicacion.herramientas.java.evaluadores.Cobranza(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Cobranza().setText(descripcion);
			}
		};
		Cobranza.setConstructor(this.getConstructor());
	}
	public void BuscarCobranza(JTextField tx){
		Cobranza.Buscar(tx);
	}
	public void BuscarCobranza(){
		Cobranza.Buscar(frame.get_txt_idCobranza());
	}
	public void buscarCobranza(JTextField tx){
		Cobranza.buscar(tx);
	}
	
	public void evaluarCobranza(JTextField tx){
		Cobranza.evaluate(tx);
	}

 */
public class Cobranza extends Generic {
	String tc="CBCT";
	
	public void setTC(String tc){
		this.tc=tc;
	}
	
	public String getQuery(String id) {
		String q = "";
		q += "select * ";
		q += "from b_cbs ";
		q += "where idcomprobante like '" + id + "' ";
		q += "and tc like '"+tc+"'  ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Cobranzas con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Cobranza vCobranza = null;

	public void buscar(JTextField tx) {
		if (vCobranza == null) {
			vCobranza = new aplicacion.herramientas.java.visualizadores.Cobranza(
					constructor);
		}
		
		if (tc.compareTo("")!=0){
			vCobranza.setTC(tc);
		}
		int n = vCobranza.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay Cobranzas con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Cobranza bCobranza = null;

	public void Buscar(JTextField ext) {
		if (bCobranza == null) {
			bCobranza = new aplicacion.herramientas.java.buscadores.Cobranza(
					constructor);
		}
		
		
		bCobranza.Buscar(ext);
	}

}
