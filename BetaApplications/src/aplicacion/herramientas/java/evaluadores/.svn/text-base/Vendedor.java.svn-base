package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor=null;
	public void initialize_Vendedor(){
		Vendedor=new aplicacion.herramientas.java.evaluadores.Vendedor(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Vendedor().setText(descripcion);
			}
		};
		Vendedor.setConstructor(this.getConstructor());
	}
	public void BuscarVendedor(JTextField tx){
		Vendedor.Buscar(tx);
	}
	public void BuscarVendedor(){
		Vendedor.Buscar(frame.get_txt_idVendedor());
	}
	public void buscarVendedor(JTextField tx){
		Vendedor.buscar(tx);
	}
	
	public void evaluarVendedor(JTextField tx){
		Vendedor.evaluate(tx);
	}

 */
public class Vendedor extends Generic {

	public String getQuery(String id) {
		id=id.replaceAll(" ", "");
		String q = "";
		q += "select idvendedor,nombre ";
		q += "from v_ta_vendedores ";
		q += "where ltrim(rtrim(idvendedor)) like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Vendedors con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Vendedor vVendedor = null;
	public void buscar(JTextField tx) {
		if (vVendedor == null) {
			vVendedor = new aplicacion.herramientas.java.visualizadores.Vendedor(
					constructor);
		}
		int n = vVendedor.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay Vendedors con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Vendedor bVendedor = null;
	public void Buscar(JTextField ext) {
		if (bVendedor == null) {
			bVendedor = new aplicacion.herramientas.java.buscadores.Vendedor(
					constructor);
		}
		
		bVendedor.Buscar(ext);
	}

}
