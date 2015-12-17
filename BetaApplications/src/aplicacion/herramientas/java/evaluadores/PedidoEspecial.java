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
public class PedidoEspecial extends Generic {
	
	
	public String getQuery(String id) {
		String q = "";
		q += "select * ";
		q += "from b_pep ";
		q += "where idpedido like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "No hay Pedidos con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.PedidoEspecial vPedidoEspecial = null;

	public void buscar(JTextField tx) {
		if (vPedidoEspecial != null) {
			vPedidoEspecial.close();
		}
		vPedidoEspecial = new aplicacion.herramientas.java.visualizadores.PedidoEspecial(
				constructor);
		
		int n = vPedidoEspecial.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("No hay Pedidos con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.PedidoEspecial bPedidoEspecial = null;

	public void Buscar(JTextField ext) {
		if (bPedidoEspecial == null) {
			bPedidoEspecial = new aplicacion.herramientas.java.buscadores.PedidoEspecial(
					constructor);	
		}
		
		
		bPedidoEspecial.Buscar(ext);
	}

}
