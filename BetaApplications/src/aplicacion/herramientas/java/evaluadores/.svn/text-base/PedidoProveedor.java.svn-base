package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.PedidoProveedor PedidoProveedor=null;
	public void initialize_PedidoProveedor(){
		PedidoProveedor=new aplicacion.herramientas.java.evaluadores.PedidoProveedor(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_PedidoProveedor().setText(descripcion);
			}
		};
		PedidoProveedor.setConstructor(this.getConstructor());
	}
	public void BuscarPedidoProveedor(JTextField tx){
		PedidoProveedor.Buscar(tx);
	}
	public void BuscarPedidoProveedor(){
		PedidoProveedor.Buscar(frame.get_txt_idPedidoProveedor());
	}
	public void buscarPedidoProveedor(JTextField tx){
		PedidoProveedor.buscar(tx);
	}
	
	public void evaluarPedidoProveedor(JTextField tx){
		PedidoProveedor.evaluate(tx);
	}

 */
public class PedidoProveedor extends Generic {
	
	
	public String getQuery(String id) {
		String q = "";
		q += "select * ";
		q += "from b_pdp ";
		q += "where idpedido like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "No hay Pedidos con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.PedidoProveedor vPedidoProveedor = null;

	public void buscar(JTextField tx) {
		if (vPedidoProveedor != null) {
			vPedidoProveedor.close();
		}
		vPedidoProveedor = new aplicacion.herramientas.java.visualizadores.PedidoProveedor(
				constructor);
		
		int n = vPedidoProveedor.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("No hay Pedidos con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.PedidoProveedor bPedidoProveedor = null;

	public void Buscar(JTextField ext) {
		if (bPedidoProveedor == null) {
			bPedidoProveedor = new aplicacion.herramientas.java.buscadores.PedidoProveedor(
					constructor);	
		}
		
		
		bPedidoProveedor.Buscar(ext);
	}

}
