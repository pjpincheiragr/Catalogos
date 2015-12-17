package aplicacion.ventas.catalogo.logic;
import org.jawin.DispatchPtr;
import org.jawin.win32.Ole32;

public class Macros {


	  
	public static void main(String[] args) {
	      try {
	         Ole32.CoInitialize();

	         DispatchPtr app = new DispatchPtr("imacros");

	         //Calling iMacros methods
	         app.invoke("iimInit", null);
	         app.invoke("iimPlay", "CODE:URL GOTO=http://www.google.com");
	         app.invoke("iimPlay", "CODE:TAG POS=1 TYPE=A ATTR=TXT:iMacros EXTRACT=TXT");
	         
	         //manually cast return values to correct type
	         String iret = app.invoke("iimGetLastExtract").toString();

	         //app.invoke("iimExit");
	          //              System.out.println(iret);
	                       
	         Ole32.CoUninitialize();
	      }

	      catch (Exception e){
	         e.printStackTrace();
	      }

	   }
}

