package aplicacion.modelo.constructor;
import javax.swing.*;

import aplicacion.herramientas.conexion.*;
import aplicacion.herramientas.conexion.conectores.*;

import aplicacion.modelo.events._ActionListener;
import aplicacion.modelo.events._ItemListener;
import aplicacion.modelo.events._KeyListener;
import aplicacion.modelo.events._MouseListener;
import aplicacion.modelo.events._MouseMotionListener;
import aplicacion.modelo.events._TableTransferHandler;
import aplicacion.modelo.events._TreeModelListener;
import aplicacion.modelo.events._TreeSelectionListener;
import aplicacion.modelo.logic.Logic;

/**
* Esta clase especifica los metodos que debe implementar la clase Constructor
* que forma parte del paquete modelo de Beta version 1
* 
* Los metodoss getListeners que se defininen en esta clase 
* tienen utilidad para los casos que en tiempo de ejecucion la aplicacion 
* cree objetos dinamicos que tienen eventos que deban ser procesados por la
* logica. A los cuales le debe asignar los listener para que puedan ser controlados
* por los handlers.
* Estos metodos no deben ser reescritos. Se utilizan para todas las aplicaciones
* y es un vinculo directo entre los eventos y los handlers
* Listeners(Detectan los eventos) Estos no deben ser reescritos
* Handlers(Manejadores de eventos) Son los que evaluan quien produce el evento
* y que tipo y ejecuta el metodo que corresponda en la logica para resolverlo.
* Estos si se deben crear en cada aplicacion segun que eventos necesiten ser controlados
* @return
*/
public interface IConstructor {

	/**
	 * Devuelve el Frame Principal de la aplicacion
	 * @return
	 */
	public JFrame getFrame();
	/**
	 * Devuelve la Logica Principal de la aplicacion
	 * @return
	 */
	public Logic getLogic();
	
	/**
	 * Devuelve el conector que esta utilizando el constructor
	 * @return
	 */
	public ConnectionHandler getSql();
	
	
	
	/**
	 * Busca un parametro segun el id especificado en la interfaz de la aplicacion
	 * Y Devuelve el valor que se ha enviado al constructor.
	 * @param id
	 * @return
	 */
	public Object getValueParameter(String id);
	
	/**
	 * Metodo para obtener el Actionlistener asociado al constructor.
	 * Action listener se utiliza para detectar los eventos de botones
	 * @return
	 */
	public _ActionListener getActionListener();

	/**
	 * Metodo para obtener el Keylistener asociado al constructor.
	 * Keylistener se utiliza para detectar los eventos de teclado.
	 * @return
	 */
	public _KeyListener getKeyListener();
	
	/**
	 * Metodo para obtener el Itemlistener asociado al constructor.
	 * Keylistener se utiliza para detectar los eventos de combobox, listbox y checkbox.
	 * @return
	 */
	public _ItemListener getItemListener();
	
	/**
	 * Metodo para obtener el MouseMotionlistener asociado al constructor.
	 * MouseMotonlistener se utiliza para detectar los eventos de movimiento del mouse.
	 * @return
	 */
	public _MouseMotionListener getMouseMotionListener();

	
	public _TableTransferHandler getTableTransferHandler();
	
	/**
	 * Metodo para obtener el Mouselistener asociado al constructor.
	 * Mouselistener se utiliza para detectar los eventos del mouse. Click y Doble Click.
	 * @return
	 */
	public _MouseListener getMouseListener();

	/**
	 * Metodo para obtener el TreeNodelistener asociado al constructor.
	 * Keylistener se utiliza para detectar los eventos de un objeto tree(Arbol) ;-)
	 * @return
	 */
	public _TreeModelListener getTreeModelListener();
	
	/**
	 * Metodo para obtener el TreeSelecionlistener asociado al constructor.
	 * TreeSelecionlistener se utiliza para detectar los eventos de teclado.
	 */
	public _TreeSelectionListener getTreeSelectionListener();
	
	/**
	 * Metodo para enviar un parametro al constructor especificado en la interfaz de la aplicacion
	 * @param id
	 * @param value
	 */
	public void setParameter(String id,Object value);
	
	/**
	 * Metodo default para eliminar todas los objetos utilizados por la aplicacion
	 * para que el garbagecolector de JVM sea efectivo y libere recursos.
	 * En ocaciones las aplicaciones no deben cerrar a sus aplicaciones enlazadas.
	 * Ej. Visor de Pedidos que abre un pedido nuevo
	 */
	public void dispose(boolean kill_childs);
	
	
	/**
	 * Este metodo inicializa los nombres de los objetos que producen eventos y
	 * deben ser controlados.
	 * Es una de las instrucciones que ejecuta este constructor en su metodo build()
	 * Aca usted puede configurar los elementos graficos para definir su nombre 
	 * segun una interfaz que corresponda a su aplicacion; tambien definir 
	 * actionCommands y los listeners o adapter que utilizan para detectar 
	 * 
	 */
	public void initialize_components();
	
	/**
	 * Metodo para indicar al constructor si la aplicacion tiene definido un frame
	 * o no. El valor por defecto es verdadero.
	 * Si la aplicacion no tiene un frame. No necesita de uno.
	 * entonces debe invocarse el metodo setGraphical(false); durante la instanciacion del Constructor
	 * al correr la aplicacion.
	 * @param graphical
	 */
	public void setGraphical(boolean graphical);
	
	/**
	 * Metodo que devuelve verdadero si el constructor tiene definido un frame
	 * Por defecto es verdadero.
	 * Si la aplicacion no tiene un frame. No necesita de uno.
	 * entonces debe invocarse el metodo setGraphical(false); durante la instanciacion del Constructor
	 * al correr la aplicacion.
	 */
	public boolean isGraphical();
	
}
