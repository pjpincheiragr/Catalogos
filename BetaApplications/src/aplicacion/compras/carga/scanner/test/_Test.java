package aplicacion.compras.carga.scanner.test;
import uk.co.mmscomputing.device.scanner.Scanner;
import uk.co.mmscomputing.device.scanner.ScannerIOException;
import aplicacion.compras.carga.scanner.constructor.*;
import aplicacion.compras.carga.scanner.interfaces.*;
public class _Test {
	public static void main(String[] argv){
		/*
		_Constructor C=new _Constructor();
		C.build();
		C.setParameter(_Parametros._idproveedor, "211010029");
		C.setParameter(_Parametros._descripcion_proveedor, "Etman");
		C.setParameter(_Parametros._tc, "FCC");
		C.setParameter(_Parametros._idcomprobante, "000100004503A");
		C.setParameter(_Parametros._secuencia,"1");
		C.init();*/
		
		Scanner scanner=Scanner.getDevice();
		
		try {
			scanner.setCancel(true);	
			String[] lists=scanner.getDeviceNames();
			scanner.select(lists[1]);
			
			
			scanner.acquire();
		} catch (ScannerIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
