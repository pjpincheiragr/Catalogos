package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Codigo Codigo=null;
	public void initialize_Codigo(){
		Codigo=new aplicacion.herramientas.java.evaluadores.Codigo(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Codigo().setText(descripcion);
			}
		};
		Codigo.setConstructor(this.getConstructor());
	}
	public void BuscarCodigo(JTextField tx){
		Codigo.Buscar(tx);
	}
	public void BuscarCodigo(){
		Codigo.Buscar(frame.get_txt_idCodigo());
	}
	public void buscarCodigo(JTextField tx){
		Codigo.buscar(tx);
	}
	
	public void evaluarCodigo(JTextField tx){
		Codigo.evaluate(tx);
	}

 */
public class Codigo extends Generic {
	private JTextField linea = null;
	private JTextField idproveedor = null;

	public JTextField getLinea() {
		return linea;
	}

	public void setLinea(JTextField linea) {
		this.linea = linea;
	}

	public JTextField getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(JTextField idproveedor) {
		this.idproveedor = idproveedor;
	}

	public String getQuery(String id) {
		String linea=this.linea.getText();
		String idproveedor=this.idproveedor.getText();
		String q = "";
		q += "select idcodigo,descripcion,lineaproveedor,idproveedor ";
		q += "from b_Codigos ";
		q += "where idCodigo like '" + id + "' ";
		q += "and lineaproveedor like '"+linea+"%' ";
		q += "and idproveedor like '"+idproveedor+"%' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Codigos con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Codigo vCodigo = null;
	public void buscar(JTextField tx) {
		System.out.println("Creando visualizador con tx "+tx);
		if (vCodigo == null) {
			vCodigo = new aplicacion.herramientas.java.visualizadores.Codigo(
					constructor);
		}
		vCodigo.setIdproveedor(idproveedor);
		vCodigo.setLinea(linea);
		int n = vCodigo.Buscar(tx);
		if (n == 0) {
			
			constructor.getLogic().aviso("no hay Codigos con ese codigo");
			
		}
	}

	private aplicacion.herramientas.java.buscadores.Codigo bCodigo = null;
	public void Buscar(JTextField ext) {
		if (bCodigo == null) {
			bCodigo = new aplicacion.herramientas.java.buscadores.Codigo(
					constructor);
		}else{
			
		}
		bCodigo.setIdproveedor(idproveedor);
		bCodigo.setLinea(linea);
		bCodigo.Buscar(ext);
	}

	
}
