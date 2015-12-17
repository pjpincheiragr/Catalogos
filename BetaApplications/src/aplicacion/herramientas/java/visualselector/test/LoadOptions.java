package aplicacion.herramientas.java.visualselector.test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class LoadOptions extends SwingWorker<Integer, Integer> {
	 
    @Override
    public Integer doInBackground() {
 	   
 	   System.out.println("doInBackground() esta en el hilo "
                + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
     	   
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("interrumpido");
            }

            // Se pasa valor para la barra de progreso. ESto llamara al metodo
            // process() en el hilo de despacho de eventos.
            publish(i + 1);
        }

        // Supuesto resultado de la tarea que tarda mucho.
        
 	   //int n=_loadoptions_back();
 	   return 0;
    }

    @Override
    protected void process(List<Integer> chunks) {
 	   System.out.println("process() esta en el hilo "
                + Thread.currentThread().getName());
        
 	   System.out.println("process() "+chunks.get(0));
 	   //block();
 	   //loadoptions_gui();
    }
    
    public Integer loadOptions(){
    	this.execute();
    	int n=0;
    	try {
			n=this.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return n;
    }
    public static void main(String[] args){
    	LoadOptions lo=new LoadOptions();
    	lo.loadOptions();
    }
}

