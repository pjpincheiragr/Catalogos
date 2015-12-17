package aplicacion.herramientas.java.evaluadores;
import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;


/* Modo de Uso
 //ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
public void initialize_proveedor(){
	proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
		public void cargar(String codigo){
			Object[][] results=this.getInfo(codigo);
			String descripcion=(String) results[0][1];
			frame.get_txt_descripcion_proveedor().setText(descripcion);
		}
	};
	proveedor.setConstructor(this.getConstructor());
}
public void BuscarProveedor(JTextField tx){
	proveedor.Buscar(tx);
}
public void buscarProveedor(JTextField tx){
	proveedor.buscar(tx);
}
public void BuscarProveedor(){
	proveedor.Buscar(frame.get_txt_idproveedor());
}
public void evaluarProveedor(JTextField tx){
	proveedor.evaluate(tx);
}

*/
public class Proveedor extends Generic{

	
	public String getQuery(String id){
		String q="";
		q+="select codigo,descripcion ";
		q+="from ma_cuentas ";
		q+="where codigo like '"+id+"' ";
		q+="and codigo like '21101%' and codigo not like '21101' AND DADA_DE_BAJA = 0";
		return q;
	}
	
	
	public String getAviso(){
		String aviso="No hay Proveedores con ese codigo";
		return aviso;
	}
	
	
	
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	public void buscar(JTextField tx) {
		if (vProveedor==null){
			vProveedor=new aplicacion.herramientas.java.visualizadores.Proveedor(constructor);
		}
		
		int n = vProveedor.Buscar(tx);
		if (n == 0) {
				constructor.getLogic().aviso("no hay Proveedors con ese codigo");
		}
	}
	
	
	
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
	public void Buscar(JTextField ext) {
		 if (bProveedor==null){
			 bProveedor=new aplicacion.herramientas.java.buscadores.Proveedor(constructor);
		 }
	 
	 bProveedor.Buscar(ext);
	}
	
	
	
	
}
