package aplicacion.gestion.tablero.test;
import org.nfunk.jep.*;

public class Jep {
	public static void main(String[] args) {
		// crea una nueva instancia de JEP con la configuraci´on por defecto
		JEP funcion = new JEP();
		String expresion = "250000*0.3+(10-4)";
		double value;
		System.out.println(
		"Ejemplo del uso de JEP con la funci´on: " + expresion);
		funcion.addStandardFunctions(); // adiciona las funciones matem´aticas
		funcion.addStandardConstants(); // adiciona las constantes matem´aticas
		// adiciona las variables y sus valores iniciales
		funcion.addVariable("x", 2.0);
		funcion.addVariable("y", -2.0);
		funcion.parseExpression(expresion); // paso de la expresi´on a evaluar
		// revisar si han ocurrido errores durante el an´alisis de la expresi´on
		if (funcion.hasError()) {
		System.out.println("Error durante el an´alisis sint´actico");
		System.out.println(funcion.getErrorInfo());
		return;
		}
		// obtener el resultado de evaluar la expresi´on
		value = funcion.getValue();
		// revisar si han ocurrido errores durante la evaluaci´on de la expresi´on
		if (funcion.hasError()) {
		System.out.println("Error durante la evaluaci´on");
		System.out.println(funcion.getErrorInfo());
		return;
		}
		// imprime la expresi´on evaluada y el resultado obtenido al evaluarla
		System.out.println(expresion + " = " + value);
		// cambiar el valor de los paramentros para evaluar la expresi´on
		funcion.addVariable("x", -2.0);
		funcion.addVariable("y", 2.0);
		value = funcion.getValue();
		System.out.println(expresion + " = " + value);
		}
}
