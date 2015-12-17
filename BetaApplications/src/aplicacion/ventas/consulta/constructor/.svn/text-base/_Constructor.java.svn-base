package aplicacion.ventas.consulta.constructor;
import aplicacion.herramientas.conexion.*;
import aplicacion.modelo.constructor.Constructor;
import aplicacion.ventas.consulta.logic.*;
public class _Constructor extends Constructor {

	public void initialize_logic(){
		_logic=new _Logic();
	}
	
	public void init(){
		
		this.setShowOnStartup(false);
		super.init();
		_Logic logic=(_Logic) this.getLogic();
		logic.setSuspendidov(true);
		logic.showConsulta();
	}
	
	public void setConnectionHandler(ConnectionHandler CH){
		
		_Logic logic=(_Logic) this.getLogic();
		logic.getConsulta().getConstructor().setConnectionHandler(CH);
		//logic.getConsulta().getCaller().setConnectionHandler(CH);
	}
}
