package aplicacion.contabilidad.asiento.modelo.logic;
import aplicacion.contabilidad.asiento.modelo.objetos.*;
import aplicacion.modelo.logic.Logic;

public class _Logic extends Logic{
	Asiento asiento=null;
	_Data data=null;
	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}
	
	public boolean grabar(){
		boolean ok=false;
		if (asiento!=null){
			if (asiento.balancea()){
				ok=data.grabar(asiento);	
			}	
		}
		return ok;
	}
	
	public boolean eliminar(){
		boolean eliminado=false;
		if (asiento!=null){
			if (asiento.balancea()){
				eliminado=data.eliminar(asiento);	
			}	
		}
		return eliminado;
	}
	
	
	public boolean existe(){
		boolean existe=false;
		existe=data.existe(asiento);
		return existe;
	}
}
