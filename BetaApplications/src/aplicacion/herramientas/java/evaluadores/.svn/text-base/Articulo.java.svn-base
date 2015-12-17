package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Articulo Articulo=null;
	public void initialize_Articulo(){
		Articulo=new aplicacion.herramientas.java.evaluadores.Articulo(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Articulo().setText(descripcion);
			}
		};
		Articulo.setConstructor(this.getConstructor());
	}
	public void BuscarArticulo(JTextField tx){
		Articulo.Buscar(tx);
	}
	public void BuscarArticulo(){
		Articulo.Buscar(frame.get_txt_idArticulo());
	}
	public void buscarArticulo(JTextField tx){
		Articulo.buscar(tx);
	}
	
	public void evaluarArticulo(JTextField tx){
		Articulo.evaluate(tx);
	}

 */
public class Articulo extends Generic {

	public String getQuery(String id) {
		String q = "";
		q += "select idarticulo,descripcion,descripabrev ";
		q += "from v_ma_articulos ";
		q += "where idarticulo like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Articulos con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	public void buscar(JTextField tx) {
		System.out.println("Creando visualizador con tx "+tx);
		if (vArticulo == null) {
			vArticulo = new aplicacion.herramientas.java.visualizadores.Articulo(
					constructor);
		}
		
		int n = vArticulo.Buscar(tx);
		if (n == 0) {
			
			constructor.getLogic().aviso("no hay Articulos con ese codigo");
			
		}
	}

	private aplicacion.herramientas.java.buscadores.Articulo bArticulo = null;
	public void Buscar(JTextField ext) {
		if (bArticulo == null) {
			bArticulo = new aplicacion.herramientas.java.buscadores.Articulo(
					constructor);
		}else{
			
		}
		bArticulo.Buscar(ext);
	}

	
}
