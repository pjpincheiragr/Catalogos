package aplicacion.sistema.version.test;

import java.io.IOException;

import aplicacion.sistema.version.constructor.*;
import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.herramientas.conexion.*;
import aplicacion.modelo.interfaces.*;
public class _Test {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//_Constructor C=new _Constructor();
		//C.build();
		//C.init();
		try {
			String download="C:\\Archivos de Programa\\Beta Systems\\Beta\\sync.bat";
			Process child = Runtime.getRuntime().exec(download);
			//int x=child.exitValue();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
