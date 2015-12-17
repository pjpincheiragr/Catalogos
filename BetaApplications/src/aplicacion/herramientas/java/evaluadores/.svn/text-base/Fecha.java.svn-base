package aplicacion.herramientas.java.evaluadores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Fecha Fecha=null;
	public void initialize_Fecha(){
		Fecha=new aplicacion.herramientas.java.evaluadores.Fecha(){
			public void cargar(String codigo){
			//transfer Focus
			}
		};
		Fecha.setConstructor(this.getConstructor());
	}
	public void BuscarFecha(JTextField tx){
		Fecha.Buscar(tx);
	}
	public void BuscarFecha(){
		Fecha.Buscar(frame.get_txt_idFecha());
	}
	
	public void evaluarFecha(JTextField tx){
		Fecha.evaluate(tx);
	}

 */
public class Fecha extends Generic {
	String formato="dd-MM-yyyy";
	private aplicacion.herramientas.java.buscadores.Fecha bFecha = null;
	
	public void cargar(JTextField tx){
		
	}

	public String getAviso() {
		String aviso = "La Fecha es Incorrecta";
		return aviso;
	}

	

	
	public void Buscar(JTextField ext) {
		if (bFecha == null) {
			bFecha = new aplicacion.herramientas.java.buscadores.Fecha(
					constructor);
		}
		
		bFecha.Buscar(ext);
	}
	
	public boolean evaluate(JTextField tx){
		String codigo=tx.getText();
		String aux=tx.getText();
		boolean band=true;
		
			if (aux.compareTo("")!=0){
					String parsed=parse(codigo);
					if (parsed.compareTo("")!=0){
						tx.setText(parsed);
						cargar(tx);
					}else{
						constructor.getLogic().aviso(this.getAviso());
						band=false;
						tx.setSelectionStart(0);
						tx.setSelectionEnd(codigo.length());
						tx.requestFocusInWindow();
							
					}
				
				}
			else{
				this.Buscar(tx);
			}
		return band;
	}
	
	/**
	 * Devuelve la fecha String en un objeto Date
	 * @param fecha
	 * @return
	 */
	public Date getDate(String fecha){
		DateFormat formatter;
		Locale locale = Locale.getDefault();
	    Date date=null;
	    try {
	    	
	    	date = new Date();
	    	formatter = new SimpleDateFormat(formato, locale);
	    	date = (Date)formatter.parse(fecha);
	    
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	    return date;
	}
	
	/**
	 * Pasa la fecha al formato dd-MM-yyyy si no puede devuelve ""
	 * @param fecha
	 * @return
	 */
	public String parse(String fecha){
		
		DateFormat formatter;
		Locale locale = Locale.getDefault();
	    String s1="";
	    Date date=null;
	    
	    try {
	    	
	    	date = new Date();
	    	formatter = new SimpleDateFormat(formato, locale);
	    	date = (Date)formatter.parse(fecha);
	    	s1 = formatter.format(date);
	    
	    }catch (Exception e){
	    	s1="";
	    	e.printStackTrace();
	    }
	    return s1;
	}
	
	
	/**
	 * Verifica si el formato de la fecha es correcto
	 * @param fecha
	 * @return
	 */
	public boolean esCorrecta(String fecha){
		boolean correcta=false;
		String s1=this.parse(fecha);
		correcta=(s1.compareTo(fecha)==0);
		return correcta;
	}
	
	/**
	 * Devuelve la diferencia en dias (int) entre 2 fechas
	 * @param fecha
	 * @param fecha2
	 * @return
	 */
	public int diferencia_dias(String fecha1,String fecha2){
		Date date1=this.getDate(fecha1);
		Date date2=this.getDate(fecha2);
		int diferencia=-1;
		if (date1!=null & date2!=null){
			try {
				long dif = date1.getTime() - date2.getTime();
				System.out.println("Dias entre fechas: " + dif / 86400000L);
				// 3600 * 24 * 1000
				diferencia=new Long(dif / 86400000L).intValue();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return diferencia;
	}
}
