package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.PDF PDF=null;
	public void initialize_PDF(){
		PDF=new aplicacion.herramientas.java.evaluadores.PDF(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_PDF().setText(descripcion);
			}
		};
		PDF.setConstructor(this.getConstructor());
	}
	public void BuscarPDF(JTextField tx){
		PDF.Buscar(tx);
	}
	public void BuscarPDF(){
		PDF.Buscar(frame.get_txt_idPDF());
	}
	public void buscarPDF(JTextField tx){
		PDF.buscar(tx);
	}
	
	public void evaluarPDF(JTextField tx){
		PDF.evaluate(tx);
	}

 */
public class PDF extends Generic {

	
	public String getQuery(String id) {
		String q = "";
		q += "select title,filename ";
		q += "from pdf ";
		q += "where title like '" + id + "' ";
		q += "order by title,filename";
		System.out.println(q);
		return q;
	}

	public String getAviso() {
		String aviso = "No hay Archivos con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.PDF vPDF = null;
	public void buscar(JTextField tx) {
		System.out.println("Creando visualizador con tx "+tx);
		if (vPDF == null) {
			vPDF = new aplicacion.herramientas.java.visualizadores.PDF(
					constructor);
		}
		
		int n = vPDF.Buscar(tx);
		if (n == 0) {
			
			constructor.getLogic().aviso("No hay PDF con ese codigo");
			
		}
	}

	private aplicacion.herramientas.java.buscadores.PDF bPDF = null;
	public void Buscar(JTextField ext) {
		if (bPDF == null) {
			bPDF = new aplicacion.herramientas.java.buscadores.PDF(
					constructor);
		}else{
			
		}
		
		bPDF.Buscar(ext);
	}

	
}
