package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Linea Linea=null;
	public void initialize_Linea(){
		Linea=new aplicacion.herramientas.java.evaluadores.Linea(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Linea().setText(descripcion);
			}
		};
		Linea.setConstructor(this.getConstructor());
	}
	public void BuscarLinea(JTextField tx){
		Linea.Buscar(tx);
	}
	public void BuscarLinea(){
		Linea.Buscar(frame.get_txt_idLinea());
	}
	public void buscarLinea(JTextField tx){
		Linea.buscar(tx);
	}
	
	public void evaluarLinea(JTextField tx){
		Linea.evaluate(tx);
	}

 */
public class Linea extends Generic {

	public String getQuery(String id) {
		String q = "";
		q += "select descripabrev ";
		q += "from v_ma_articulos ";
		q += "where descripabrev like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Lineas con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Linea vLinea = null;
	public void buscar(JTextField tx) {
		//System.out.println("Creando visualizador con tx "+tx);
		if (vLinea == null) {
			vLinea = new aplicacion.herramientas.java.visualizadores.Linea(
					constructor);
		}
		
		int n = vLinea.Buscar(tx);
		if (n == 0) {
			
			constructor.getLogic().aviso("no hay Lineas con ese codigo");
			
		}
	}

	private aplicacion.herramientas.java.buscadores.Linea bLinea = null;
	public void Buscar(JTextField ext) {
		if (bLinea == null) {
			bLinea = new aplicacion.herramientas.java.buscadores.Linea(
					constructor){
				public void _cargar(String codigo){
					cargar(codigo);
				}
			};
			
		}
		
		bLinea.Buscar(ext);
	}

	
}
