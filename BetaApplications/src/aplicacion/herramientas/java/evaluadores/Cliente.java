package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Cliente cliente=null;
	public void initialize_cliente(){
		cliente=new aplicacion.herramientas.java.evaluadores.Cliente(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_cliente().setText(descripcion);
			}
		};
		cliente.setConstructor(this.getConstructor());
	}
	public void BuscarCliente(JTextField tx){
		cliente.Buscar(tx);
	}
	public void BuscarCliente(){
		cliente.Buscar(frame.get_txt_idcliente());
	}
	public void buscarCliente(JTextField tx){
		cliente.buscar(tx);
	}
	
	public void evaluarCliente(JTextField tx){
		cliente.evaluate(tx);
	}

 */
public class Cliente extends Generic {

	public String getQuery(String id) {
		String q = "";
		q += "select codigo,descripcion ";
		q += "from ma_cuentas ";
		q += "where codigo like '" + id + "' ";
		q += "and codigo like '11201%' and codigo not like '11201' AND DADA_DE_BAJA = 0 ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Clientes con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Cliente vCliente = null;
	public void buscar(JTextField tx) {
		System.out.println("Creando visualizador con tx "+tx);
		if (vCliente == null) {
			vCliente = new aplicacion.herramientas.java.visualizadores.Cliente(
					constructor);
		}
		
		int n = vCliente.Buscar(tx);
		if (n == 0) {
			
			constructor.getLogic().aviso("no hay Clientes con ese codigo");
			
		}
	}

	private aplicacion.herramientas.java.buscadores.Cliente bCliente = null;
	public void Buscar(JTextField ext) {
		if (bCliente == null) {
			bCliente = new aplicacion.herramientas.java.buscadores.Cliente(
					constructor);
		}else{
			
		}
		
		bCliente.Buscar(ext);
	}

	
}
