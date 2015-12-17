package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Control Control=null;
	public void initialize_Control(){
		Control=new aplicacion.herramientas.java.evaluadores.Control(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_Control().setText(descripcion);
			}
		};
		Control.setConstructor(this.getConstructor());
	}
	public void BuscarControl(JTextField tx){
		Control.Buscar(tx);
	}
	public void BuscarControl(){
		Control.Buscar(frame.get_txt_idControl());
	}
	public void buscarControl(JTextField tx){
		Control.buscar(tx);
	}
	
	public void evaluarControl(JTextField tx){
		Control.evaluate(tx);
	}

 */
public class Control extends Generic {
	String iddeposito="";
	
	public void setDeposito(String tc){
		this.iddeposito=tc;
	}
	
	public String getQuery(String id) {
		String q = "";
		q += "select idcontrol,idusuario,linea,convert(varchar(10),isnull(fecha,getdate()),105),isnull(iddeposito,'   1'),isnull(movimiento,''),isnull(desde,''),isnull(hasta,''),isnull(punto,0) ";
		q += "from b_Control ";
		q += "where idcontrol like '" + id + "' and isnull(eliminar,0)=0";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay Control con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Control vControl = null;

	public void buscar(JTextField tx) {
		if (vControl == null) {
			vControl = new aplicacion.herramientas.java.visualizadores.Control(
					constructor);
		}
		
		if (iddeposito.compareTo("")!=0){
			vControl.setDeposito(iddeposito);
		}
		int n = vControl.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay Controls con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Control bControl = null;

	public void Buscar(JTextField ext) {
		if (bControl == null) {
			bControl = new aplicacion.herramientas.java.buscadores.Control(
					constructor);
			
		}
		if (iddeposito.compareTo("")!=0){
			bControl.setDeposito(iddeposito);
		}
		
		bControl.Buscar(ext);
	}

}
