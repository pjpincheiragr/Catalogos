package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Host Host=null;
	public void initialize_Host(){
		Host=new aplicacion.herramientas.java.evaluadores.Host(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Host().setText(descripcion);
			}
		};
		Host.setConstructor(this.getConstructor());
	}
	public void BuscarHost(JTextField tx){
		Host.Buscar(tx);
	}
	public void BuscarHost(){
		Host.Buscar(frame.get_txt_idHost());
	}
	public void buscarHost(JTextField tx){
		Host.buscar(tx);
	}
	
	public void evaluarHost(JTextField tx){
		Host.evaluate(tx);
	}

 */
public class Host extends Generic {

	public String getQuery(String id) {
		String q = "";
		q += "select idhost,ip,server,printer,monitor,extension,dhcp ";
		q += "from b_host ";
		q += "where idhost like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "No hay Hosts con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Host vHost = null;
	public void buscar(JTextField tx) {
		System.out.println("Creando visualizador con tx "+tx);
		if (vHost == null) {
			vHost = new aplicacion.herramientas.java.visualizadores.Host(
					constructor);
		}
		
		int n = vHost.Buscar(tx);
		if (n == 0) {
			
			constructor.getLogic().aviso("No hay Host con ese codigo");
			
		}
	}

	private aplicacion.herramientas.java.buscadores.Host bHost = null;
	public void Buscar(JTextField ext) {
		if (bHost == null) {
			bHost = new aplicacion.herramientas.java.buscadores.Host(constructor);
			}
				
		bHost.Buscar(ext);
	}

	
}
