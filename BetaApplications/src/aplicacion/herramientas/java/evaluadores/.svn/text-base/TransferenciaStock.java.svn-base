package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.TransferenciaStock TransferenciaStock=null;
	public void initialize_TransferenciaStock(){
		TransferenciaStock=new aplicacion.herramientas.java.evaluadores.TransferenciaStock(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_descripcion_TransferenciaStock().setText(descripcion);
			}
		};
		TransferenciaStock.setConstructor(this.getConstructor());
	}
	public void BuscarTransferenciaStock(JTextField tx){
		TransferenciaStock.Buscar(tx);
	}
	public void BuscarTransferenciaStock(){
		TransferenciaStock.Buscar(frame.get_txt_idTransferenciaStock());
	}
	public void buscarTransferenciaStock(JTextField tx){
		TransferenciaStock.buscar(tx);
	}
	
	public void evaluarTransferenciaStock(JTextField tx){
		TransferenciaStock.evaluate(tx);
	}

 */
public class TransferenciaStock extends Generic {
	String iddeposito="";
	
	public void setDeposito(String tc){
		this.iddeposito=tc;
	}
	
	public String getQuery(String id) {
		String q = "";
		q += "select idTransferencia,iduser,convert(varchar(10),isnull(fecha,getdate()),105),iddeposito_origen,iddeposito_destino,idvendedor ";
		q += "from b_Transferencia_Stock ";
		q += "where idTransferencia like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay TransferenciaStock con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.TransferenciaStock vTransferenciaStock = null;

	public void buscar(JTextField tx) {
		if (vTransferenciaStock == null) {
			vTransferenciaStock = new aplicacion.herramientas.java.visualizadores.TransferenciaStock(
					constructor);
		}
		
		if (iddeposito.compareTo("")!=0){
			vTransferenciaStock.setDeposito(iddeposito);
		}
		int n = vTransferenciaStock.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay TransferenciaStocks con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.TransferenciaStock bTransferenciaStock = null;

	public void Buscar(JTextField ext) {
		if (bTransferenciaStock == null) {
			bTransferenciaStock = new aplicacion.herramientas.java.buscadores.TransferenciaStock(
					constructor);
		}
		
		
		bTransferenciaStock.Buscar(ext);
	}

}
