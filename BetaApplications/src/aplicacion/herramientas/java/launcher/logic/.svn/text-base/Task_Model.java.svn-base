package aplicacion.herramientas.java.launcher.logic;
import java.util.*;
import aplicacion.herramientas.conexion.*;
import aplicacion.modelo.interfaces.*;
import aplicacion.modelo.constructor.*;
public class Task_Model {
	
	private LinkedList parametros=null;
	protected Constructor C=null;
	
	public Task_Model(){
	parametros=new LinkedList();
	this.initialize_constructor();
	}
	public void initialize_constructor(){
		C=new Constructor();
	}
	
	public void setParameter(String id,Object value){
		
		C.setParameter(id, value);
	}
	
	public Object getValueParameter(String id){
		return C.getParameter(id);
	}
	

	public Constructor getConstructor(){
		return this.C;
	}
	public void run(Constructor Caller){
		ConnectionHandler c=(ConnectionHandler)C.getParameter(_parametros.connector);
		
		String iduser=null;
		try {
			iduser=(String) this.getValueParameter("iduser");
		}catch(Exception e){
			
		}
		if (c!=null){
			System.out.println("Asignando Conector a Constructor en TaskModel");
			C.setParameter(_parametros.connector, c.Clone());
			
		}
		C.setParameter(_parametros.iduser, iduser);
		C.setCaller(Caller);
		C.build(Caller);
		C.init();
		Caller.addChild(C);
	}
	
	public void run(){
		
		ConnectionHandler c=(ConnectionHandler)C.getParameter(_parametros.connector);
		
		String iduser=null;
		try {
			iduser=(String) this.getValueParameter("iduser");
		}catch(Exception e){
			
		}
		if (c!=null){
			System.out.println("Asignando Conector a Constructor en TaskModel");
			C.setParameter(_parametros.connector, c);
			
		}
		C.setParameter(_parametros.iduser, iduser);
		C.build(this.getConstructor());
		C.init();
		
		
	}
	
	public void kill(){
		
	}
}
