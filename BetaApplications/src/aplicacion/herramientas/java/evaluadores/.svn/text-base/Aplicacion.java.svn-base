package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;
import javax.swing.*;

public class Aplicacion extends Generic {
	




	/* Modo de Uso
	 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
	 	private aplicacion.herramientas.java.evaluadores.aplicacion aplicacion=null;
		public void initialize_aplicacion(){
			aplicacion=new aplicacion.herramientas.java.evaluadores.aplicacion(){
				public void cargar(String codigo){
					Object[][] results=this.getInfo(codigo);
					String descripcion=(String) results[0][1];
					frame.get_txt_descripcion_aplicacion().setText(descripcion);
				}
			};
			aplicacion.setConstructor(this.getConstructor());
		}
		public void Buscaraplicacion(JTextField tx){
			aplicacion.Buscar(tx);
		}
		public void Buscaraplicacion(){
			aplicacion.Buscar(frame.get_txt_idaplicacion());
		}
		public void buscaraplicacion(JTextField tx){
			aplicacion.buscar(tx);
		}
		
		public void evaluaraplicacion(JTextField tx){
			aplicacion.evaluate(tx);
		}

	 */
	

		public String getQuery(String id) {
			String q = "";
			q += "select idaplicacion,menu_nombre,lanzador ";
			q += "from b_aplicaciones ";
			q += "where idaplicacion like '" + id + "' ";
			return q;
		}

		public String getAviso() {
			String aviso = "No hay aplicacions con ese codigo";
			return aviso;
		}

		private aplicacion.herramientas.java.visualizadores.Aplicacion vaplicacion = null;
		public void buscar(JTextField tx) {
			System.out.println("Creando visualizador con tx "+tx);
			if (this.vaplicacion == null) {
				vaplicacion = new aplicacion.herramientas.java.visualizadores.Aplicacion(
						constructor);
			}
			
			int n = vaplicacion.Buscar(tx);
			if (n == 0) {
				
				constructor.getLogic().aviso("No hay aplicacion con ese codigo");
				
			}
		}

		private aplicacion.herramientas.java.buscadores.Aplicacion baplicacion = null;
		public void Buscar(JTextField ext) {
			if (baplicacion == null) {
				baplicacion = new aplicacion.herramientas.java.buscadores.Aplicacion(constructor);
				}
					
			baplicacion.Buscar(ext);
		}

		
}


