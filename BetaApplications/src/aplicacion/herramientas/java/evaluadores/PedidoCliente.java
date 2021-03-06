package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.PedidoCliente PedidoCliente=null;
	public void initialize_PedidoCliente(){
		PedidoCliente=new aplicacion.herramientas.java.evaluadores.PedidoCliente(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_PedidoCliente().setText(descripcion);
			}
		};
		PedidoCliente.setConstructor(this.getConstructor());
	}
	public void BuscarPedidoCliente(JTextField tx){
		PedidoCliente.Buscar(tx);
	}
	public void BuscarPedidoCliente(){
		PedidoCliente.Buscar(frame.get_txt_idPedidoCliente());
	}
	public void buscarPedidoCliente(JTextField tx){
		PedidoCliente.buscar(tx);
	}
	
	public void evaluarPedidoCliente(JTextField tx){
		PedidoCliente.evaluate(tx);
	}

 */
public class PedidoCliente extends Generic {
	

	public String getQuery(String id) {
		String q = "";
		q += "select * ";
		q += "from b_pdc ";
		q += "where idpedido like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "No hay Pedidos con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.PedidoCliente vPedidoCliente = null;

	public void buscar(JTextField tx) {
		if (vPedidoCliente != null) {
			vPedidoCliente.close();
		}
		vPedidoCliente = new aplicacion.herramientas.java.visualizadores.PedidoCliente(
				constructor);
		
		int n = vPedidoCliente.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("No hay Pedidos con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.PedidoCliente bPedidoCliente = null;

	public void Buscar(JTextField ext) {
		if (bPedidoCliente == null) {
			bPedidoCliente = new aplicacion.herramientas.java.buscadores.PedidoCliente(
					constructor);	
		}
		
		
		bPedidoCliente.Buscar(ext);
	}

}
