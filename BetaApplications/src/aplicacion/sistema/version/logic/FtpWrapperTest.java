package aplicacion.sistema.version.logic;

/* <!-- in case someone opens this in a browser... --> <pre> */

import java.io.*;

/**
  * a very simple example of using the SunFtpWrapper class,
  * available at http://www.nsftools.com/tips/JavaFtp.htm
  */

public class FtpWrapperTest {
	public static void main (String[] args) {
		try {
			SunFtpWrapper ftp = new SunFtpWrapper();
			String serverName = "gisbertrepuestos.com.ar";
			ftp.openServer(serverName);
			if (ftp.serverIsOpen()) {
				System.out.println("Connected to " + serverName);
				try {
					ftp.login("gisbertrepuestos.com.ar", "ipsilon@1982");
					System.out.println("Welcome message:\n" + ftp.welcomeMsg);
					
					ftp.cd("beta");
					System.out.println("Current Directory: " + ftp.pwd());
					System.out.println("Results of a raw LIST command:\n" + ftp.listRaw());
					System.out.println("Downloading file beta-patch");
					//ftp.binary();
					ftp.downloadFile("beta-patch.msp", "c:\\beta-patch.msp");
				} catch (Exception ftpe) {
					ftpe.printStackTrace();
				} finally {
					ftp.closeServer();
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

