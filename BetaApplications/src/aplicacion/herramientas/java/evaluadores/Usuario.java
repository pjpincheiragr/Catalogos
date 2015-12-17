package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.usuario usuario=null;
	public void initialize_usuario(){
		usuario=new aplicacion.herramientas.java.evaluadores.usuario(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_usuario().setText(descripcion);
			}
		};
		usuario.setConstructor(this.getConstructor());
	}
	public void Buscarusuario(JTextField tx){
		usuario.Buscar(tx);
	}
	public void Buscarusuario(){
		usuario.Buscar(frame.get_txt_idusuario());
	}
	public void buscarusuario(JTextField tx){
		usuario.buscar(tx);
	}
	
	public void evaluarusuario(JTextField tx){
		usuario.evaluate(tx);
	}

 */
public class Usuario extends Generic {

	public String getQuery(String id) {
		String q = "";
		q += "select iduser,nombre ";
		q += "from b_users ";
		q += "where iduser like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "No hay Usuarios con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Usuario vUsuario = null;
	public void buscar(JTextField tx) {
		System.out.println("Creando visualizador con tx "+tx);
		if (vUsuario == null) {
			vUsuario = new aplicacion.herramientas.java.visualizadores.Usuario(
					constructor);
		}
		
		int n = vUsuario.Buscar(tx);
		if (n == 0) {
			
			constructor.getLogic().aviso("No hay Usuario con ese codigo");
			
		}
	}

	private aplicacion.herramientas.java.buscadores.Usuario busuario = null;
	public void Buscar(JTextField ext) {
		if (busuario == null) {
			busuario = new aplicacion.herramientas.java.buscadores.Usuario(
					constructor);
		}else{
			
		}
		
		busuario.Buscar(ext);
	}

	
}
