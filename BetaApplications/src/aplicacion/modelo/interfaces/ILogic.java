package aplicacion.modelo.interfaces;

import aplicacion.modelo.constructor.Constructor;
import aplicacion.modelo.logic.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Clase modelo de la logica que se utiliza en todas las aplicaciones que
 * cumplen con el modelo de 7 capas de Beta version 1. Esta tiene acceso al
 * costructor de la clase, al frame principal y a la base de datos a traves de
 * la clase data. En esta clase se implementan todos los metodos que se
 * consideran basicos y reutilizables para la mayoria de las logicas. Por
 * ejemplo los metodos aviso,error y preguntar. que son cuadros de dialogos muy
 * utilizados en todas las aplicaciones.
 * 
 * @author Agustin Wisky
 * @version 1
 * @since 03-10-2009
 */
public interface ILogic {

	/**
	 * Metodo que retorna un numero aleatorio de una cantidad de digitos =
	 * digitos (parametro) :-)
	 * 
	 * @param digitos
	 * @return
	 */
	public int getRandomNumber(int digitos);

	/**
	 * Metodo para obtener el usuario que corre la aplicacion.
	 * 
	 * @return
	 */
	public String getUser();

	/**
	 * Metodo que genera un cuadro de dialogo que pide al usuario que confirme
	 * 
	 * @param mensaje
	 * @param digitos
	 * @return
	 */
	public boolean confirmar(String mensaje, int digitos);

	/**
	 * Metodo predeterminado para limpiar el frame principal de la aplicacion Se
	 * limpian todos los textfield, se habilitan y deshabilitan botones segun
	 * corresponda
	 */
	public void clean();

	/**
	 * Metodo que Obtiene un mensaje o string almacenado como xml dentro del
	 * paquete xml de la aplicacion cambia los valores de cadena ? por los
	 * parametros en el orden en que se envian. De esta manera se separa la
	 * instruccion o consulta del codigo java en un unico archivo. y hace a la
	 * aplicacion mas sencilla para mantener y leer.
	 * 
	 * @param id
	 * @param params
	 * @return
	 */
	public String getMessage(String id, String[] params);

	/**
	 * Metodo que Obtiene un mensaje o string almacenado como xml dentro del
	 * paquete xml de la aplicacion De esta manera se separa la instruccion o
	 * consulta del codigo java en un unico archivo. y hace a la aplicacion mas
	 * sencilla para mantener y leer.
	 * 
	 * @param idmessage
	 * @return
	 */
	public String getMessage(String idmessage);

	/**
	 * Metodo que devuelve el constructor asociado con la logica
	 * 
	 * @return
	 */
	public Constructor getConstructor();

	/**
	 * Metodo para obtener la interfaz de base de datos.
	 * 
	 * @return
	 */
	public Data getData();

	/**
	 * Metodo que asocia la interfaz de base de datos con la logica. Data tiene
	 * todos los metodos que interactuan directamente con la base de datos. Debe
	 * hacerse un override en cada aplicacion para indicarle al constructor que
	 * utilice la clase data de la aplicacion
	 * 
	 * @param _data
	 */
	public void setData(Data _data);

	/**
	 * Metodo para asociar el frame de la aplicacion con la logica Debe hacerse
	 * un override para indicar al constructor que utilice el frame de la
	 * aplicacion
	 * 
	 * @param _frame
	 */
	public void setFrame(JFrame _frame);

	/**
	 * Metodo que devuelve el frame asociado con la logica
	 * 
	 * @return
	 */
	public JFrame getFrame();

	/**
	 * Meetodo default que se ejecuta al cerrar un frame o al llamar al metodo
	 * exit y confirmar la salida en el cuadro de dialogo. Al crear una
	 * extension de la logica para una aplicacion. Es recomendable hacer un
	 * override de este metodo para indicar a la aplicacion que debe hacer al
	 * cerrarse
	 */
	public void exit_command();

	/**
	 * Metodo default para cerrar la aplicacion
	 */
	public void exit();

	/**
	 * Metodo default para generar un cuadro de dialogo y consultar por si o por
	 * no. ahorra mucho tiempo y lineas de codigo!!
	 * 
	 * @param titulo
	 * @param pregunta
	 * @return
	 */
	public boolean preguntar(String titulo, String pregunta);

	/**
	 * Metodo para generar un cuadro de dialogo y preguntar por si o no. Permite
	 * enviar un icono especifico como parametro
	 * 
	 * @param titulo
	 * @param pregunta
	 * @param icon
	 * @return
	 */
	public boolean preguntar(String titulo, String pregunta, ImageIcon icon);

	/**
	 * Agrega una extension logica a la logica
	 * 
	 * @param logic
	 */
	public void addExtension(Logic_Extension logic);

	/**
	 * Obtiene una extension segun el nombre de clase
	 * 
	 * @param classname
	 * @return
	 */
	public Logic_Extension getExtension(String classname);

	/**
	 * Metodo para asociar el constructor con la logica
	 * 
	 * @param constructor
	 */
	public void setConstructor(Constructor constructor);

	/**
	 * Genera un cuadro de dialogo del tipo error con los parametros de titulo y
	 * mensaje
	 * 
	 * @param titulo
	 * @param mensaje
	 */
	public void error(String titulo, String mensaje);

	/**
	 * Metodo que genera un cuadro de dialogo de error utilizando el parametro
	 * mensaje
	 * 
	 * @param mensaje
	 */
	public void error(String mensaje);

	/**
	 * Metodo que genera un cuadro de dialogo de aviso con un titulo y mensaje
	 * segun los parametros
	 * 
	 * @param titulo
	 * @param mensaje
	 */
	public void aviso(String titulo, String mensaje);

	/**
	 * Metodo que genera un cuadro de dialogo de aviso con un titulo, mensaje e
	 * icono segun los parametros
	 * 
	 * @param titulo
	 * @param mensaje
	 * @param icon
	 */
	public void aviso(String titulo, String mensaje, ImageIcon icon);

	/**
	 * Metodo default para enviar avisos al usuario, utilizando el titulo del
	 * frame y mostrando el mensaje que se envia como parametro. Tiene la
	 * ventaja de no necesitar el parametro de titulo, solo el mensaje que se
	 * necesita mostrar
	 * 
	 * @param mensaje
	 */
	public void aviso(String mensaje);

	/**
	 * Metodo que se utiliza para leer el xml que contiene los mensajes
	 * almacenados en el xml predeterminado para mensajes. genera una estructura
	 * de arbol para buscar y obtener los mensajes rapidamente. Este metodo se
	 * invoca en el momento de la construccion de la aplicacion unicamente. Es
	 * lindo pero no tan practico. Habra que ver si se continua usando o no Lo
	 * mantengo por compatibilidad con aplicaciones anteriores
	 */
	public void _read_messages();

	/**
	 * Metodo default para enviar el worspace a la base! Forma parte del kit de
	 * herramientas para guardar el workspace en la base No utilizar Lo mantengo
	 * por si retomo ese camino :-)
	 * 
	 * @param C
	 */
	public void saveToWorkspace();
	
	/**
	 * Metodo por defecto utilizado cuando se cancela la edicion de una aplicacion
	 */
	public void cancelar();
}
