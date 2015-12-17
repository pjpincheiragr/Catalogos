package aplicacion.herramientas.java.launcher.logic;

import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.herramientas.java.launcher.interfaces._Interface;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.herramientas.conexion.*;
public class _Logic extends Logic {
	
	private SwingWorker worker =null;
	
	
	public _Logic(){
		
	}
	public void runAplication(String clase){
		//aviso("Running Aplicacion "+clase);
		System.out.println("Corriendo Aplicacion "+clase);
		try {
			
			
			Class c=Class.forName(clase);
			Class v0=Class.forName(_Interface._task_model_v0);
			Class v1=Class.forName(_Interface._task_model_v1);
			Object	oc=null;
			if (c!=null){
				boolean error=false;
				try {
					
					oc=c.newInstance();
					
				} catch (java.lang.Error e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					error=true;
					if (error){
						displayError("Error Creando Instancia de Aplicacion", e.getMessage(), e.getLocalizedMessage(), e);
					}
				}
				if (oc!=null & !error){
					if (oc.getClass().getSuperclass()==v1){
						this.run_v1(clase);
					}
					if (oc.getClass().getSuperclass()==v0){
						this.run_v0(clase);
					}		
				}
				
			}
			
			
			
		} catch (Exception e) {
				
				e.printStackTrace();
				displayError("Error En Carga de Aplicacion", e.getMessage(), e.getLocalizedMessage(), e);
		}
	}

	public void run_v1(String clase){
		//aviso("Ejecutando "+clase+" Tipo V1 Params:"+this.getConstructor().getParametros().size());
		Object	oc=null;
		try {
			Class c=Class.forName(clase);
			oc=c.newInstance();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (oc!=null){
			Task_Model T=(Task_Model) oc;
			boolean b=true;//esta_autorizado(iduser,clase);
			if (b){
				
				for (int i=0;i<this.getConstructor().getParametros().size();i++){
					try {
					Object[] p=(Object[]) this.getConstructor().getParametros().get(i);
					String id=(String)p[0];
					Object o=p[1];
					T.setParameter(id, o);
					} catch (Exception e) {
						System.out.println("No se pudo Cargar el parametro "+i+" >"+clase+" "+e.getMessage());
						e.printStackTrace();
					}
				}
				
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					T.run(getConstructor());
				}else{
					final Task_Model _T=T;
					Runnable _execute=new Runnable(){
						public void run(){
							_T.run(getConstructor());
						}
					};
					javax.swing.SwingUtilities.invokeLater(_execute);	
				}	
			}else {
				error("("+this.getConstructor().getIduser()+") No esta Autorizado");
				T=null;
			}	
		}
		
	}
	/**
	 * Este metodo es para mantener la compatibilidad con aplicaciones viejas
	 * Hasta que pueda pasarlas a la version por capas. Q es mas facil de mantener
	 * Entender y Mejorar!!
	 */
	public void run_v0(String clase){
		//aviso("Ejecutando "+clase+" Tipo V0");
		
		Object	oc=null;
		
			
		
		try {
			Class c=Class.forName(clase);
			oc=c.newInstance();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (oc!=null){
			visual.Task_Model T=(visual.Task_Model) oc;
			int i=0;
			beta.tools.connector.GTransfer GX=null;
			
			while (i<this.getConstructor().getParametros().size() & GX==null){
				Object[] p=(Object[]) this.getConstructor().getParametros().get(i);
				String id=(String)p[0];
				Object o=p[1];
				if (id.compareTo("GX")==0){
					GX=(beta.tools.connector.GTransfer)o;
				}
				i++;
			}
			if (GX==null){
				GX=new beta.tools.connector.GTransfer();
				Generic _default=this.getConstructor().getConnectionHandler().getDefaultConnector();
				GX.setHost(_default.getHost());
				GX.setPort(_default.getPort());
				GX.setDatabase(_default.getDatabase());
				GX.setUser(_default.getUser());
				GX.setPassword(_default.getPassword());
				GX.setSucursal(_default.getId());
				GX.ConnectSQL(_default.getId());
			}
			boolean b=true;//esta_autorizado(iduser,clase);
			if (b){
				String NEW_LINE = System.getProperty("line.separator");
				/*String msg="Esta es una aplicacion vieja."+NEW_LINE;
				msg+="Hasta que no se reprograme. Esta aplicacion quedara inhabilitada por problemas de incompatibilida";
				error(msg);*/
				
				T.setParameter("GX", GX);
				T.setParameter("iduser", this.getConstructor().getIduser());
				for (i=0;i<this.getConstructor().getParametros().size();i++){
					try {
					Object[] p=(Object[]) this.getConstructor().getParametros().get(i);
					String id=(String)p[0];
					Object o=p[1];
					T.setParameter(id, o);
					} catch (Exception e) {
						System.out.println("No se pudo Cargar el parametro "+i+" >"+clase+" "+e.getMessage());
					}
				}
				
				
				
				if (javax.swing.SwingUtilities.isEventDispatchThread()){
					T.run();
				}else{
					final visual.Task_Model _T=T;
					Runnable _execute=new Runnable(){
						public void run(){
							_T.run();
						}
					};
					javax.swing.SwingUtilities.invokeLater(_execute);	
				}
				
			}else {
				error("("+this.getConstructor().getIduser()+") No esta Autorizado");
				T=null;
			}	
		}
	}
}
