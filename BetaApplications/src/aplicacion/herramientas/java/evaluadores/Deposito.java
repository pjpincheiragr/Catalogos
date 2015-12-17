package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Deposito Deposito=null;
	public void initialize_Deposito(){
		Deposito=new aplicacion.herramientas.java.evaluadores.Deposito(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Deposito().setText(descripcion);
			}
		};
		Deposito.setConstructor(this.getConstructor());
	}
	public void BuscarDeposito(JTextField tx){
		Deposito.Buscar(tx);
	}
	public void BuscarDeposito(){
		Deposito.Buscar(frame.get_txt_idDeposito());
	}
	public void buscarDeposito(JTextField tx){
		Deposito.buscar(tx);
	}
	
	public void evaluarDeposito(JTextField tx){
		Deposito.evaluate(tx);
	}

 */
public class Deposito extends Generic {

	public String getQuery(String id) {
		id=id.replaceAll(" ", "");
		String q = "";
		q += "select idDeposito,descripcion ";
		q += "from v_ta_Deposito ";
		q += "where ltrim(rtrim(idDeposito)) like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Depositos con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Deposito vDeposito = null;
	public void buscar(JTextField tx) {
		if (vDeposito == null) {
			vDeposito = new aplicacion.herramientas.java.visualizadores.Deposito(
					constructor);
		}
		int n = vDeposito.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay Depositos con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Deposito bDeposito = null;
	public void Buscar(JTextField ext) {
		if (bDeposito == null) {
			bDeposito = new aplicacion.herramientas.java.buscadores.Deposito(
					constructor);
		}
		
		bDeposito.Buscar(ext);
	}

}
