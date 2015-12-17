package aplicacion.contabilidad.asiento.modelo.logic;
import aplicacion.contabilidad.asiento.modelo.objetos.Asiento;
import aplicacion.modelo.logic.Data;
import aplicacion.contabilidad.asiento.modelo.objetos.Renglon;
import aplicacion.contabilidad.asiento.modelo.interfaces.*;

public class _Data extends Data{

	public boolean existe(Asiento A){
		boolean existe=false;
		String[] parameters=new String[]{
		A.getPeriodo(),
		A.getMes(),
		A.getNumero()
		};
		String q=this.getQuery(_Interface.existe, parameters);
		Object[][] results=this.connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	
	
	private boolean insertar(Asiento A){
		boolean ok=false;
		connection_handler.getDefaultConnector().clearBatch();
		for (int secuencia=0;secuencia<A.getRenglones();secuencia++){
			Renglon R=A.getRenglon(secuencia);
			Object[] parameters=new Object[]{
			A.getFecha(),
			A.getPeriodo(),
			A.getMes(),
			A.getNumero(),
			secuencia,
			A.getDetalle(),
			R.getCuenta(),
			R.getDetalle(),
			R.getDebe(),
			R.getHaber(),
			R.getCaja()
			};
			String q=this.getQuery(_Interface.insertar, parameters);
			connection_handler.getDefaultConnector().addBatch(q);
		}
		ok=!connection_handler.getDefaultConnector().executeBatch();
		return ok;
	}
	
	public boolean grabar(Asiento A){
		boolean ok=false;
		if (existe(A)){
			if (this.esCorrecto(A)){
				ok=this.actualizar(A);	
			}
			
		}else {
			if (this.esNuevo(A)){
				this.cargar_idnuevo(A);
			}
			if (this.esCorrecto(A)){
				ok=this.insertar(A);	
			}
			
		}
		return ok;
	}
	
	private boolean actualizar(Asiento A){
		boolean ok=false;
		connection_handler.getDefaultConnector().clearBatch();
		for (int secuencia=0;secuencia<A.getRenglones();secuencia++){
			Renglon R=A.getRenglon(secuencia);
			Object[] parameters=new Object[]{
			A.getFecha(),
			A.getPeriodo(),
			A.getMes(),
			A.getNumero(),
			secuencia,
			A.getDetalle(),
			R.getCuenta(),
			R.getDetalle(),
			R.getDebe(),
			R.getHaber(),
			R.getCaja()
			};
			String q=this.getQuery(_Interface.actualizar, parameters);
			connection_handler.getDefaultConnector().addBatch(q);
		}
		ok=!connection_handler.getDefaultConnector().executeBatch();
		return ok;
	}
	
	public boolean cargar_idnuevo(Asiento A){
		boolean ok=false;
		Object[] parameters=new Object[]{
				A.getFecha()
			};
		String q=this.getQuery(_Interface.nuevo, parameters);
		Object[][] results=connection_handler.getDefaultConnector().getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
				String periodo=(String)results[0][0];
				String mes=(String)results[0][1];
				String numero=(String)results[0][2];
				Integer _numero=new Integer(numero);
				_numero++;
				numero=""+_numero;
				A.setPeriodo(periodo);
				A.setMes(mes);
				A.setNumero(numero);
			}
		}
		return ok;
	}
	
	private boolean esNuevo(Asiento A){
		boolean nuevo=(A.getMes().compareTo("")==0
				& A.getPeriodo().compareTo("")==0
				& A.getNumero().compareTo("")==0
				);
			
		return nuevo;
	}
	
	private boolean esCorrecto(Asiento A){
		boolean correcto=false;
		correcto=(A.getMes().compareTo("")!=0
				& A.getPeriodo().compareTo("")!=0
				& A.getNumero().compareTo("")!=0);
		if (correcto){
			correcto=A.balancea();
		}
		return correcto;
	}
	
	public boolean eliminar(Asiento A){
		boolean eliminado=false;
		Object[] parameters=new Object[]{
			A.getPeriodo(),
			A.getMes(),
			A.getNumero()
		};
		String q=this.getQuery(_Interface.eliminar, parameters);
		connection_handler.getDefaultConnector().addBatch(q);
		eliminado=!this.existe(A);
		return eliminado;
	}
	
}
