package aplicacion.modelo.events;

import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.constructor.*;

public class ExceptionHandler {
protected Constructor Constructor=null;	
private double memory_limit=8;
private double memory_warning=15;
public Constructor getConstructor() {
	return Constructor;
}
public void setConstructor(Constructor constructor) {
	Constructor = constructor;
}



protected Logic _logic=null;
	
	private boolean print=false;
	private boolean file=false;
	private int log_level=2;
	
	public int getLogLevel() {
		return log_level;
	}
	public void setLogLevel(int loglevel) {
		this.log_level = loglevel;
	}
	public void setLogic(Logic _logic){
		this._logic=_logic;
	}
	private void log(Exception e){
		
		try{
			
			error(e);
			
		}
		catch(Exception ex){
			e.printStackTrace();
		}
		
	}
	
	
	public void error(Exception e) {
		if (this.log_level>1){
			String xml="";
			try {
				xml=Constructor.getLogic().saveToXML();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int n=Constructor.getErrorHandler().displayError("Error", "Error en "+_logic.getClass(),e.getMessage(), e,xml);
			if (n==2){
				this.log_level=1;
			}
		}
	}
	
	public void procesar(DragSourceDragEvent dtde){
		try{
			//updateLastTimeActivity();
			if (this.processMemory()){
				this.procesarEvento(dtde);
				
			}else{
				this.getConstructor().getLogic().error("Advertencia","EL nucleo de Beta Cancelo Esta Operacion Por Falta de Memoria");
			}
		}catch(Exception ex){
			this.log(ex);
		}	
	}
	
	public void procesar(DragSourceDropEvent dtde){
		try{
			//updateLastTimeActivity();
			if (this.processMemory()){
				this.procesarEvento(dtde);
				
			}else{
				this.getConstructor().getLogic().error("Advertencia","EL nucleo de Beta Cancelo Esta Operacion Por Falta de Memoria");
			}
		}catch(Exception ex){
			this.log(ex);
		}
	}
	public void procesar(ItemEvent e){
		try{
			//updateLastTimeActivity();
			if (this.processMemory()){
				this.procesarEvento(e);
				
			}else{
				this.getConstructor().getLogic().error("Advertencia","EL nucleo de Beta Cancelo Esta Operacion Por Falta de Memoria");
			}
		}catch(Exception ex){
			this.log(ex);
		}
	}
	public void procesar(ChangeEvent e){
		try{
			//updateLastTimeActivity();
			if (this.processMemory()){
				this.procesarEvento(e);
				
			}else{
				this.getConstructor().getLogic().error("Advertencia","EL nucleo de Beta Cancelo Esta Operacion Por Falta de Memoria");
			}
			
		}catch(Exception ex){
			this.log(ex);
		}
	}
	public void procesar(KeyEvent e){
		try{
			//updateLastTimeActivity();
			if (this.processMemory()){
				this.log_event(e);
				this.procesarEvento(e);
				
			}else{
				this.getConstructor().getLogic().error("Advertencia","EL nucleo de Beta Cancelo Esta Operacion Por Falta de Memoria");
			}
			
		}catch(Exception ex){
			this.log(ex);
		}
	}
	
	public void procesar(ActionEvent e){
		try{
			//updateLastTimeActivity();
			if (this.processMemory()){
				procesarEvento(e);
			}else{
				this.getConstructor().getLogic().error("Advertencia","EL nucleo de Beta Cancelo Esta Operacion Por Falta de Memoria");
				
			}
					
				
			
		}catch(Exception ex){
			
			this.log(ex);
		}
	}
	
	public void procesar(MouseEvent e){
		try{
			//updateLastTimeActivity();
			if (this.processMemory()){
				this.procesarEvento(e);
				
			}else{
				this.getConstructor().getLogic().error("Advertencia","EL nucleo de Beta Cancelo Esta Operacion Por Falta de Memoria");
			}
		}catch(Exception ex){
			this.log(ex);
		}
	}
	
	public void procesar(WindowEvent e){
		try{
			//updateLastTimeActivity();
			if (this.processMemory()){
				this.procesarEvento(e);
				
			}else{
				this.getConstructor().getLogic().error("Advertencia","EL nucleo de Beta Cancelo Esta Operacion Por Falta de Memoria");
			}
		}catch(Exception ex){
			this.log(ex);
		}
	}
	
	public void procesarEvento(DragSourceDragEvent dtde){
		
	}
	public void procesarEvento(MouseEvent e){
		
	}
	public void procesarEvento(DragSourceDropEvent dtde){
		
	}
	public void procesarEvento(WindowEvent we){
		
	}
	
	//para action listener
	public void procesarEvento(ActionEvent e){
		
		
	}
	
	
	
	//para item listener
	public void procesarEvento(ItemEvent e) {
		
	}
	
	public void procesarEvento(ChangeEvent event){
		
	}
	
	//para keylistener
	public void procesarEvento(KeyEvent event){
		
	}
	
	public void procesar(DropTargetDropEvent dtde){
		
	}
	
	public double getFreeMemory(){
		double free=0;
		int mb = 1024*1024;
		try {
			Runtime runtime = Runtime.getRuntime ();
			free=runtime.freeMemory()/runtime.maxMemory();
			double max=runtime.maxMemory()/mb;
			double avaible=runtime.freeMemory()/mb;
			double total=runtime.totalMemory()/mb;
			//System.out.println("Memory free:"+avaible+" (MB)");
			//System.out.println("Memory Maximum:"+max+" (MB)");
			//System.out.println("Memory Total:"+total+" (MB)");
			free=((max-(total-avaible))/max)*100;
			//System.out.println("Memory free(%):"+free);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return free;
	}
	
	public double FreeMemory(){
		double free=0.0;
		try {
			Runtime runtime = Runtime.getRuntime ();
			free=runtime.freeMemory();
			runtime.gc();
			free=runtime.freeMemory()-free;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return free;
	}
	double roundTwoDecimals(double d) {
    	DecimalFormat twoDForm = new DecimalFormat("#.##");
	return Double.valueOf(twoDForm.format(d).replaceAll(",", "."));
	}
	public void killAplicacion(){
		int n=this.Constructor.getLogic().preguntar("Liberar", "Cierra Esta Aplicacion Para Liberar Memoria?", new String[]{"Cerrar Aplicacion para Liberar Memoria","No Cierre Esta Aplicacion"}, "No Cierre Esta Aplicacion");
		if (n==0){
			this.getConstructor().dispose();
		}
	}
	
	public void fupdateLastTimeActivity(){
		try {
			String fecha=new aplicacion.herramientas.java.Convertidor().getDateWithFormat("dd-MM-yyyy HH:mm:ss");
			if (this.getConstructor()!=null){
				if (this.getConstructor().getLogic()!=null){
					if (this.getConstructor().getLogic().getData()!=null){
						this.getConstructor().getLogic().getData().setParameteroSqlite("user_activity", fecha);	
					}
						
				}
					
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public boolean processMemory(){
		boolean ok=false;
		double free=this.getFreeMemory();
		
		if (free<this.memory_warning){
			this.FreeMemory();
			free=this.getFreeMemory();
		}
		
		if (free<this.memory_limit){
			free=this.roundTwoDecimals(free);
			this.Constructor.getLogic().error("Memoria Disponible "+free+"% menor al Limite Permitido. El sistema bloqueara todas las operaciones");
			killAplicacion();
			ok=false;
		}else{
			ok=true;
			
			
			if (free<this.memory_warning){
				free=this.roundTwoDecimals(free);
				this.Constructor.getLogic().error("Advertencia: Memoria Disponible "+free+"%. El sistema esta entrando en una Zona de Riesgo de Bloqueo. Recuerde cerrar las aplicaciones que no utiliza ");
				int n=this.Constructor.getLogic().preguntar("Confirmar", "Si continua sin cerrar aplicaciones corre riesgo de colgar el sistema!!", new String[]{"Continuar, asumo el riesgo","No Continuar. Voy a cerrar algunas aplicaciones"}, "No Continuar. Voy a cerrar algunas aplicaciones");
				ok=n==0;
				this.FreeMemory();
				if (ok){
					this.Constructor.getLogic().sendInfo("Guardar Informacion Urgente", "El sistema le ofrece guardar informacion de la aplicacion por posible riesgo de bloqueo");
				}
					
			}
		}
		return ok;
	}
	
	private void log_event(KeyEvent e){
		//String iduser = this.getConstructor().getIduser();
		//System.out.println("Log to database session "+e+" "+iduser);
	}
}
