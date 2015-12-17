package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Pago Pago=null;
	public void initialize_Pago(){
		Pago=new aplicacion.herramientas.java.evaluadores.Pago(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Pago().setText(descripcion);
			}
		};
		Pago.setConstructor(this.getConstructor());
	}
	public void BuscarPago(JTextField tx){
		Pago.Buscar(tx);
	}
	public void BuscarPago(){
		Pago.Buscar(frame.get_txt_idPago());
	}
	public void buscarPago(JTextField tx){
		Pago.buscar(tx);
	}
	
	public void evaluarPago(JTextField tx){
		Pago.evaluate(tx);
	}

 */
public class Pago extends Generic {
	String tc="PG";
	
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
		String aviso = "no hay Pagos con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Pago vPago = null;

	public void buscar(JTextField tx) {
		if (vPago == null) {
			vPago = new aplicacion.herramientas.java.visualizadores.Pago(
					constructor);
		}
		
		if (tc.compareTo("")!=0){
			vPago.setTC(tc);
		}
		int n = vPago.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay Pagos con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Pago bPago = null;

	public void Buscar(JTextField ext) {
		if (bPago == null) {
			bPago = new aplicacion.herramientas.java.buscadores.Pago(
					constructor);
		}
		
		
		bPago.Buscar(ext);
	}

}
