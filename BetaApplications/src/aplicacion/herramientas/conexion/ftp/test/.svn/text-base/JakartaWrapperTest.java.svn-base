package aplicacion.herramientas.conexion.ftp.test;

/* <!-- in case someone opens this in a browser... --> <pre> */

import java.io.*;
import java.util.*;
import org.apache.commons.net.ftp.*;
/**
  * a very simple example of using the JakartaFtpWrapper class,
  * available at http://www.nsftools.com/tips/JavaFtp.htm
  */

public class JakartaWrapperTest {
	public static void main (String[] args) {
		try {
			JakartaFtpWrapper ftp = new JakartaFtpWrapper();
			String serverName = "agustin.servequake.com";
			if (ftp.connectAndLogin(serverName, "sistema", "ernestino1982")) {
				System.out.println("Connected to " + serverName);
				try {
					ftp.setPassiveMode(true);
					
					ftp.changeWorkingDirectory("Beta");
					ftp.changeWorkingDirectory("beta.2009");
					Vector v=ftp.getFiles();
					for (int i=0;i<v.size();i++){
						FTPFile file=(FTPFile)v.get(i);
						System.out.println(file.getName()+" "+file.getSize()+" "+new Date(file.getTimestamp().getTimeInMillis()));
						
					}
					boolean done=ftp.downloadFile("version.txt", "C:\\version.txt");
					if (done){
						System.out.println("Actualizacion Descargada");
					}
					
				} catch (Exception ftpe) {
					ftpe.printStackTrace();
				} finally {
					ftp.logout();
					ftp.disconnect();
				}
			} else {
				System.out.println("Unable to connect to" + serverName);
			}
			System.out.println("Finished");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}


