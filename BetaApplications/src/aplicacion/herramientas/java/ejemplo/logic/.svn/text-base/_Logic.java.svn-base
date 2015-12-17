package aplicacion.herramientas.java.ejemplo.logic;

import aplicacion.modelo.logic.Logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import aplicacion.herramientas.java.ejemplo.gui.*;
import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;

public class _Logic extends Logic {

	private Crono crono;// esto es un cronometro
	private Crono subcrono;// esto es otro cronometro
	private Timer Reloj; // @jve:decl-index=0:
	/*
	 * Variables para seguir el progreso de la tarea
	 */
	private String estado = "";
	private int current;
	private int lenght;
	
	/*
	 * Variables para seguir el progreso de la subtarea
	 */
	private String estado2 = "";
	private int current2;
	private int lenght2;
	
	private int errors;
	private _Frame frame;
	
	/*
	 * Variables para indicar si la tarea se cancela o termina
	 */
	private boolean done, canceled;

	
	
	/*
	 * (non-Javadoc)
	 * @see aplicacion.modelo.logic.Logic#setFrame(javax.swing.JFrame)
	 */
	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	class ObjetoQueConstruyeElSwingWorker {
		ObjetoQueConstruyeElSwingWorker() {
			metodoQueEjecutaElObjetoQueConstruyeElSwingWorker();
		}
	}

	/**
	 * Este metodo es una simulacion de tarea pesada Aca va todo lo que tiene
	 * que hacer la tarea. En este caso es un sleep que pausa la ejecucion
	 * durante 1 segundo La tarea tambien tiene que indicar su progreso a traves
	 * de variables: estado,length y current. Cuando finaliza coloca la variable
	 * done=true
	 */
	public void metodoQueEjecutaElObjetoQueConstruyeElSwingWorker() {
		boolean ok=this.metodosPesados();
		done = true;
	}
	public boolean otroMetodo(){
		estado2 = "Simulacion Tarea Pesada Indeterminada";
		frame.getJProgressBar1().setIndeterminate(true);
		try {
			//Un retardo aleatorio
			new Thread().sleep(new Double((Math.random()*1000)+10000).longValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Runnable _execute=new Runnable(){
			public void run(){
				LimpiarBarraDeProgreso2();		
			}
		};
		this.invokeAndWait(_execute);
		
		return true;
	}
	public boolean metodosPesados(){
		this.lenght = 5;
		int i = 0;
		while (i < lenght & !canceled) {
			estado = "Ejecutando Paso Pesado ";
			current = i;
			boolean ok=this.submetodo();
			i++;
		}
		return true;
	}
	/**
	 * Esta es la subtarea 
	 * @return
	 */
	public boolean submetodo(){
		subcrono=new Crono();
		subcrono.start();
		
		this.lenght2 = 30;
		int i = 0;
		while (i < lenght2 & !canceled) {
			estado2 = "Ejecutando Retardo Aleatorio ";
			current2 = i;
			try {
				//Un retardo aleatorio
				new Thread().sleep(new Double(Math.random()*1000).longValue());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		boolean ok=this.otroMetodo();
		return ok;	
	}
	
	public void limpiarEstados(){
		LimpiarBarraDeProgreso();
		LimpiarBarraDeProgreso2();
	}
	
	public void actualizarEstados(){
		actualizarEstadoDeBarraDeProgreso();
		actualizarEstadoDeBarraDeProgreso2();
	}
	/**
	 * Sirve para monitorear todo lo que se ejecute en el swingworker..
	 * En este caso monitorea la tarea y la subtarea!
	 */
	public void crearRelojParaMonitorearPeriodicamenteLaTareaQueEjecutaElSwingWorker() {
		crono = new Crono();
		crono.start();
		lenght = 0;
		current = 0;
		errors = 0;
		done = false;
		canceled = false;
		Reloj = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done | canceled) {
					limpiarEstados();
					Reloj.stop();
				} else {
					actualizarEstados();
				}
			}
		});

	}

	public void actualizarEstadoDeBarraDeProgreso() {
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);

	}

	public void LimpiarBarraDeProgreso() {
		estado = "";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
		if (canceled) {
			error("Tarea Cancelada");
		} else {
			aviso("La Tarea Termino");
		}

	}

	public void actualizarEstadoDeBarraDeProgreso2() {
		frame.getJProgressBar1().setMaximum(lenght2);
		frame.getJProgressBar1().setValue(current2);
		frame.getJProgressBar1().setString(
				estado2 + " " + current2 + "/" + lenght2+ " " + subcrono.elapsed());
		frame.getJProgressBar1().setStringPainted(true);

	}

	public void LimpiarBarraDeProgreso2() {
		estado2 = "";
		frame.getJProgressBar1().setString("");
		frame.getJProgressBar1().setIndeterminate(false);
		frame.getJProgressBar1().setValue(0);
		

	}

	/**
	 * Metodo para crear el Objeto que contiene la tarea pesada
	 * 
	 * @return
	 */
	public SwingWorker crearSwingWorker() {
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new ObjetoQueConstruyeElSwingWorker();
				}
			};
		}
		return worker;
	}

	public void EjecutarTareaParalelaConMonitoreo() {
		/*
		 * Primero creo el reloj con todas las variables para monitorear la
		 * tarea pesada
		 */
		crearRelojParaMonitorearPeriodicamenteLaTareaQueEjecutaElSwingWorker();
		/**
		 * Creo el objeto que se va a ejecutar en forma paralela que contiene la
		 * tarea pesada
		 */
		SwingWorker worker = this.crearSwingWorker();

		/**
		 * Enciendo el Reloj para que comience a monitorear la tarea....aunque
		 * aun no inicio la tarea..
		 */
		Reloj.start();

		/**
		 * Inicio la tarea pesada
		 */
		worker.start();
	}

	public void cancelar() {
		canceled = true;
	}
}
