package aplicacion.herramientas.java.ireport.constructor;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.herramientas.java.ireport.gui.*;
import aplicacion.herramientas.java.ireport.logic.*;
import aplicacion.herramientas.java.ireport.interfaces.*;
import java.util.*;
public class _Constructor extends Constructor {

	public void initialize_frame(){
		_frame=new _Frame();
	}
	
	public void initialize_logic(){
		_logic=new _Logic();
	}
	
	public void initialize_data(){
		_data=new _Data();
	}
	
	public void init(){
		super.init();
		_Logic logic=(_Logic) this.getLogic();
		logic.maximizar();
		
		HashMap parameter=null;
		try {
			parameter=(HashMap)this.getParameter(_parametros.parametros);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileName="";
		boolean ok=this.getConnectionHandler().getDefaultConnector().connect();
		if(ok){
			fileName=(String)this.getParameter(_parametros.reporte);
			if (fileName.compareTo("")!=0){
				//logic.excelReport(fileName, parameter, "c:/tmp.xls");
				logic.viewReport(fileName, parameter);	
			}else{
				this.dispose();
			}	
		}else{
			this.getLogic().error("Error de Conexion");
				this.dispose();
			
		}
		
		
		
	}
}
