package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Mixto Mixto=null;
	public void initialize_Mixto(){
		Mixto=new aplicacion.herramientas.java.evaluadores.Mixto(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Mixto().setText(descripcion);
			}
		};
		Mixto.setConstructor(this.getConstructor());
	}
	public void BuscarMixto(JTextField tx){
		Mixto.Buscar(tx);
	}
	public void BuscarMixto(){
		Mixto.Buscar(frame.get_txt_idMixto());
	}
	public void buscarMixto(JTextField tx){
		Mixto.buscar(tx);
	}
	
	public void evaluarMixto(JTextField tx){
		Mixto.evaluate(tx);
	}

 */
public class Mixto extends Generic {

	public String getQuery(String id) {
		String q = "";
		q += "select codigo,descripcion ";
		q += "from ma_cuentas ";
		q += "where codigo like '" + id + "' ";
		q += "and ((codigo like '11201%' and codigo not like '11201') or (codigo like '21101%' and codigo not like '21101')) AND DADA_DE_BAJA = 0  ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Mixtos con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Mixto vMixto = null;
	public void buscar(JTextField tx) {
		System.out.println("Creando visualizador con tx "+tx);
		if (vMixto == null) {
			vMixto = new aplicacion.herramientas.java.visualizadores.Mixto(
					constructor);
		}
		
		int n = vMixto.Buscar(tx);
		if (n == 0) {
			
			constructor.getLogic().aviso("no hay Cuenta con ese codigo");
			
		}
	}

	private aplicacion.herramientas.java.buscadores.Mixto bMixto = null;
	public void Buscar(JTextField ext) {
		if (bMixto == null) {
			bMixto = new aplicacion.herramientas.java.buscadores.Mixto(
			constructor);
		}else{
			
		}
		bMixto.Buscar(ext);
	}

	
}
