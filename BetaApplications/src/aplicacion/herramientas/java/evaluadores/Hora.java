package aplicacion.herramientas.java.evaluadores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import aplicacion.herramientas.java.hora.*;
import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

/* Modo de Uso
 	//ATENCION:Debe inicializar esta clase en el constructor de la aplicacion que utilice este codigo!
 	private aplicacion.herramientas.java.evaluadores.Hora Hora=null;
	public void initialize_Hora(){
		Hora=new aplicacion.herramientas.java.evaluadores.Hora(){
			public void cargar(String codigo){
			//transfer Focus
			}
		};
		Hora.setConstructor(this.getConstructor());
	}
	public void BuscarHora(JTextField tx){
		Hora.Buscar(tx);
	}
	public void BuscarHora(){
		Hora.Buscar(frame.get_txt_idHora());
	}
	
	public void evaluarHora(JTextField tx){
		Hora.evaluate(tx);
	}

 */
public class Hora extends Generic {
	String formato="dd-MM-yyyy HH:mm:ss";
	
	public void cargar(JTextField tx){
		
	}

	public String getAviso() {
		String aviso = "La Hora es Incorrecta";
		return aviso;
	}

	

	private aplicacion.herramientas.java.buscadores.Hora bHora = null;
	public void Buscar(JTextField ext) {
		if (bHora == null) {
			bHora = new aplicacion.herramientas.java.buscadores.Hora(
					constructor);
		}
		
		bHora.Buscar(ext);
	}
	
	public boolean evaluate(JTextField tx){
		
		String aux=tx.getText();
		boolean band=true;
		
			if (aux.compareTo("")!=0){
					String parsed=parse(aux);
					if (parsed.compareTo("")!=0){
						tx.setText(parsed);
						cargar(tx);
					}else{
						constructor.getLogic().aviso(this.getAviso());
						band=false;
						tx.setSelectionStart(0);
						tx.setSelectionEnd(aux.length());
						tx.requestFocusInWindow();
							
					}
				
				}
			else{
				this.Buscar(tx);
			}
		return band;
	}
	
	/**
	 * Devuelve la Hora String en un objeto Date
	 * @param Hora
	 * @return
	 */
	public Date getDate(String Hora){
		DateFormat formatter;
		Locale locale = Locale.getDefault();
	    Date date=null;
	    try {
	    	
	    	date = new Date();
	    	formatter = new SimpleDateFormat(formato, locale);
	    	date = (Date)formatter.parse(Hora);
	    
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	    return date;
	}
	
	/**
	 * Pasa la Hora al formato dd-MM-yyyy si no puede devuelve ""
	 * @param Hora
	 * @return
	 */
	public String parse(String Hora){
		
		DateFormat formatter;
		Locale locale = Locale.getDefault();
	    String s1="";
	    Date date=null;
	    
	    try {
	    	
	    	date = new Date();
	    	formatter = new SimpleDateFormat(formato, locale);
	    	date = (Date)formatter.parse(Hora);
	    	s1 = formatter.format(date);
	    
	    }catch (Exception e){
	    	s1="";
	    	e.printStackTrace();
	    }
	    return s1;
	}
	
	
	/**
	 * Verifica si el formato de la Hora es correcto
	 * @param Hora
	 * @return
	 */
	public boolean esCorrecta(String Hora){
		boolean correcta=false;
		if (Hora!=null){
			if (Hora.compareTo("")!=0){
				Hora=Hora.substring(0, 19);
				String s1=this.parse(Hora);
				correcta=(s1.compareTo(Hora)==0);
				if (!correcta){
					System.out.println("Fecha Incorrecta:"+s1+"|"+Hora);
				}		
			}
		}
		
		
		return correcta;
	}
	
	/**
	 * Devuelve la diferencia en dias (int) entre 2 Horas
	 * @param Hora
	 * @param Hora2
	 * @return
	 */
	public int diferencia_dias(String Hora1,String Hora2){
		Date date1=this.getDate(Hora1);
		Date date2=this.getDate(Hora2);
		int diferencia=-1;
		if (date1!=null & date2!=null){
			try {
				long dif = date1.getTime() - date2.getTime();
				System.out.println("Dias entre Horas: " + dif / 86400000L);
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
