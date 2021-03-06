package aplicacion.compras.carga.items.logic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;



public class printing {
	  private FileWriter out=null;
	  private OutputStream outs=null;
	  private char esc=(char)(27);
	  
	  private boolean done=false;
	  private Timer TimerP=null;
	  private int chars=24;
	  private int wait=3;
	  private int waiting=0;
	  private String start="A";
	  private String end="Z";
	  private String port="LPT1";
	  private String posicion_horizontal_columna0="H0010";
	  private String posicion_horizontal_columna1="H0460";
	  private String posicion_vertical_fila0="V0005";
	  private String posicion_vertical_fila1="V0030";
	  private String posicion_vertical_fila1e="V0090";
	  private String codigo_de_barras="BG01050";
	  private int posicion_fila_descripcion=88;
	  private int incremento_fila_descripcion=21;
		  
	  public String getPort() {
		return port;
	}

	public String getPosicion_horizontal_columna0() {
		return posicion_horizontal_columna0;
	}

	public String getPosicion_horizontal_columna1() {
		return posicion_horizontal_columna1;
	}

	public String getPosicion_vertical_fila0() {
		return posicion_vertical_fila0;
	}

	public String getPosicion_vertical_fila1() {
		return posicion_vertical_fila1;
	}

	public String getPosicion_vertical_fila1e() {
		return posicion_vertical_fila1e;
	}

	public String getCodigo_de_barras() {
		return codigo_de_barras;
	}

	public int getPosicion_fila_descripcion() {
		return posicion_fila_descripcion;
	}

	public int getIncremento_fila_descripcion() {
		return incremento_fila_descripcion;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setPosicion_horizontal_columna0(String posicion_horizontal_columna0) {
		this.posicion_horizontal_columna0 = posicion_horizontal_columna0;
	}

	public void setPosicion_horizontal_columna1(String posicion_horizontal_columna1) {
		this.posicion_horizontal_columna1 = posicion_horizontal_columna1;
	}

	public void setPosicion_vertical_fila0(String posicion_vertical_fila0) {
		this.posicion_vertical_fila0 = posicion_vertical_fila0;
	}

	public void setPosicion_vertical_fila1(String posicion_vertical_fila1) {
		this.posicion_vertical_fila1 = posicion_vertical_fila1;
	}

	public void setPosicion_vertical_fila1e(String posicion_vertical_fila1e) {
		this.posicion_vertical_fila1e = posicion_vertical_fila1e;
	}

	public void setCodigo_de_barras(String codigo_de_barras) {
		System.out.println("Set Codigo de Barras> "+codigo_de_barras);
		this.codigo_de_barras = codigo_de_barras;
	}

	public void setPosicion_fila_descripcion(int posicion_fila_descripcion) {
		this.posicion_fila_descripcion = posicion_fila_descripcion;
	}

	public void setIncremento_fila_descripcion(int incremento_fila_descripcion) {
		this.incremento_fila_descripcion = incremento_fila_descripcion;
	}


	  
	  private int col=0;
	  private int limit=6;
	  private boolean debug=false;
	  private LinkedList etqs=null;
	  private LinkedList parcial=new LinkedList();
	  private int max_len=50;
	  
	  public printing(){
		  etqs=new LinkedList();
		  
	  }
	  
	  public void setPrintList(LinkedList etqs){
		  this.etqs=etqs;
	  }
	  public boolean print_parcial(){
		  System.out.println("printparcial");

		  boolean ok=!this.toPrinter();
		  
		  //this.run_timer();
		  //while(!done);
		  parcial=new LinkedList();
		  return ok;
	  }
	  
	  private void run_timer() {
			done = false;
			if (TimerP != null)
				TimerP.stop();
			    TimerP = new Timer(500, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if (waiting>wait) {
						TimerP.stop();
						done=true;
						waiting=0;
					}else {
						waiting++;
					}
				}
			});
			TimerP.start();
		}
	  
	  public boolean print_job(){
		  System.out.println("printjob");
		  int qty=0;
		  int total=0;
		  int rows=0;
		  col=0;
		  boolean ok=false;
		  for (int i=0;i<etqs.size();i++){
			  StrEtiqueta str=(StrEtiqueta) etqs.get(i);
			  
			  for (int u=0;u<str.getCantidad();u++){
				  if (col<=0){
					  begin_row();  
				  }
				  
				  if (str.isEspecial()){
					  
					  print_etiqueta_especial(str, col);
				  }else{
					  print_etiqueta(str, col);  
				  }
				  
				  if (col>0){
					  end_row();
					  col=0;
					  rows++;
					  
					  //System.out.println("new row "+rows);
				  }else {
					
					  col++;
				  }
				  qty++;
				  total++;
			  }
		  }
		  if (col>0){
			  this.end_row();
		  }
		  
		  if (parcial.size()>0){
			  if (this.isLinux()){
				  ok=  this.toPrinterLinux();
			  }else{
				  
				  ok=  this.toPrinter();  
			  }
				
		 }  
		  
		  return ok;
	  }
	  public boolean old_print_job(){
		  //System.out.println("printjob");
		  int qty=0;
		  int total=0;
		  int rows=0;
		  col=0;
		  boolean ok=false;
		  for (int i=0;i<etqs.size();i++){
			  StrEtiqueta str=(StrEtiqueta) etqs.get(i);
			  //System.out.println("etq>"+str.getCodigo()+"? "+str.getCantidad());
			  for (int u=0;u<str.getCantidad();u++){
				  if (qty>=limit){
					  
					  ok=print_parcial();
					  rows=0;
					  col=0;
					  qty=0;
				  }
				  if (col<=0){
					  begin_row();  
				  }
				  if (str.isEspecial()){
					  print_etiqueta_especial(str, col);
				  }else{
					  print_etiqueta(str, col);  
				  }
				  
				 System.out.println("Etiq> "+total+"> "+str.getCodigo());
				  if (col>0){
					  end_row();
					  col=0;
					  rows++;
					  
					  //System.out.println("new row "+rows);
				  }else {
					
					  col++;
				  }
				  qty++;
				  total++;
			  }
		  }
		  if (col>0){
			  this.end_row();
		  }
		  if (ok){
			  if (parcial.size()>0){
				ok=  print_parcial();
			  }  
		  }
		  return ok;
	  }
	  
	  public void begin_row(){
			parcial.addLast(esc);
			parcial.addLast(start);
			//out.write(esc);
			//out.write(start);
			//out.write(end);
	  }
	  
	  public void end_row(){
			parcial.addLast(esc); parcial.addLast("Q1");
			parcial.addLast(esc); parcial.addLast(end);
	  }
	  

	  
	  public void print_etiqueta(StrEtiqueta etq,int col){
		System.out.println("print_etiqueta> agregando etiqueta "+etq.getCodigo());
		String columna=posicion_horizontal_columna0;
		if (col==1){
			columna=posicion_horizontal_columna1;
		}
		parcial.addLast(esc);
		parcial.addLast(columna);  //primera etiqueta columna 0
		parcial.addLast(esc);; 
		parcial.addLast(posicion_vertical_fila0); 
		parcial.addLast(esc); 
		parcial.addLast("M"+etq.getCodigo());
		
		
		parcial.addLast(esc); 
		parcial.addLast(columna); 
		parcial.addLast(esc); 
		parcial.addLast(posicion_vertical_fila1); 
		parcial.addLast(esc); 
		parcial.addLast(codigo_de_barras+etq.getCodigo());
		
	
		
		
		String aux=etq.getDescripcion();
		
		int row_i=0;
		int row_max=4;
		 while (aux.length()>chars & row_i<row_max){
			parcial.addLast(esc); 
			parcial.addLast(columna); 
			parcial.addLast(esc);
			String row=""+(this.posicion_fila_descripcion+row_i*this.incremento_fila_descripcion);
			 while (row.length()<4){
					 row="0"+row;
			 }
			parcial.addLast("V"+row); 
			parcial.addLast(esc);
			parcial.addLast("M"+aux.substring(0, chars));
			 aux=aux.substring(chars, aux.length());
			 
			 row_i++;
		 }
		 if (aux.length()>0){
			 
			 parcial.addLast(esc); 
			 parcial.addLast(columna); 
			 parcial.addLast(esc);
			 String row=""+(this.posicion_fila_descripcion+row_i*this.incremento_fila_descripcion);
			 while (row.length()<4){
						 row="0"+row;
			 }
			 parcial.addLast("V"+row); 
			 parcial.addLast(esc);
			 if (aux.length()>chars){
				 aux=aux.substring(0,chars-3)+"...";
			 }
		 	 parcial.addLast("M"+aux);
		 }
		
		
	}
	  
	  public void print_etiqueta_especial(StrEtiqueta etq,int col){
			System.out.println("print_etiqueta> agregando etiqueta "+etq.getCodigo());
			String columna=posicion_horizontal_columna0;
			if (col==1){
				columna=posicion_horizontal_columna1;
			}
			parcial.addLast(esc);
			parcial.addLast(columna);  //primera etiqueta columna 0
			parcial.addLast(esc);; 
			parcial.addLast(posicion_vertical_fila0); 
			parcial.addLast(esc); 
			parcial.addLast("$A,"+etq.getWidth()+","+etq.getHeight()+",0");
			parcial.addLast(esc);
			if (etq.isQuitar_prefijo()){
				if (etq.getCodigo().length()>4){
					parcial.addLast("$="+etq.getCodigo().substring(4));	
				}else{
					parcial.addLast("$="+etq.getCodigo());	
				}
					
			}else{
				parcial.addLast("$="+etq.getCodigo());
			}
			
			parcial.addLast(esc); 
			parcial.addLast(columna); 
			parcial.addLast(esc);
			String row1e="V0000";
			String height=new Integer((etq.getHeight()+2)).toString();
			row1e=row1e.substring(0,5-height.length())+height;
			parcial.addLast(row1e); 
			parcial.addLast(esc); 
			parcial.addLast(codigo_de_barras+etq.getCodigo());
			
		
			/*
			
			String aux=etq.getDescripcion();
			
			int row_i=0;
			int row_max=4;
			 while (aux.length()>chars & row_i<row_max){
				parcial.addLast(esc); 
				parcial.addLast(columna); 
				parcial.addLast(esc);
				String row=""+(this.row_desc+row_i*this.inc_desc);
				 while (row.length()<4){
						 row="0"+row;
				 }
				parcial.addLast("V"+row); 
				parcial.addLast(esc);
				parcial.addLast("S"+aux.substring(0, chars));
				 aux=aux.substring(chars, aux.length());
				 
				 row_i++;
			 }
			 if (aux.length()>0){
				 
				 parcial.addLast(esc); 
				 parcial.addLast(columna); 
				 parcial.addLast(esc);
				 String row=""+(this.row_desc+row_i*this.inc_desc);
				 while (row.length()<4){
							 row="0"+row;
				 }
				 parcial.addLast("V"+row); 
				 parcial.addLast(esc);
				 if (aux.length()>chars){
					 aux=aux.substring(0,chars-3)+"...";
				 }
			 	 parcial.addLast("S"+aux);
			 }
			
			*/
		}
	  
		public boolean isLinux(){
			return this.getOS().toLowerCase().contains("linux");
		}
		public String getOS(){
			String os=System.getProperty("os.name");
			return os;
		}

	  public boolean toPrinter(){
		  	System.out.println("toPrinter()? Enviando a Impresora !!");
		  boolean error=false;
		 	try {
		 		PrintStream ps =null;
		 		if (!debug){
		 				out= new FileWriter(port);	
		 			
		 			
		 			
		 			
		 		}
			   	for (int i=0;i<parcial.size();i++){
			   		
			   		if (debug){
			   			if (parcial.get(i).toString().compareTo(end)==0){
			   				System.out.println(parcial.get(i).toString());	
			   			}else {
			   				System.out.print(parcial.get(i).toString());
			   			}
			   				
			   		}else {
			   			if (parcial.get(i).getClass()==char.class){
				   			out.write((char)27);
				   			
				   			
				   			
				   		}else {
				   			out.write(parcial.get(i).toString());
				   		}	
			   		}
			   		
			   			
			   	}
			   	if (!debug){
			   		out.close();
			   	}
			}catch(Exception e){
				//e.printStackTrace();
					//JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Error Imprimiendo");
					error=true;
			}
			return !error;
	  }
	  
	  public boolean toPrinterLinux(){
		  	System.out.println("toPrinterLinux()? Enviando a Impresora !!");
		  boolean error=false;
		 	try {
		 		PrintStream ps =null;
		 		if (!debug){
		 			Process p = Runtime.getRuntime().exec("lpr -P SATO-LMX ");
		 				outs= p.getOutputStream();	
		 				ps=new PrintStream(outs);
		 		}
			   	for (int i=0;i<parcial.size();i++){
			   		
			   		if (debug){
			   			if (parcial.get(i).toString().compareTo(end)==0){
			   				System.out.println(parcial.get(i).toString());	
			   			}else {
			   				System.out.print(parcial.get(i).toString());
			   			}
			   				
			   		}else {
			   			if (parcial.get(i).getClass()==char.class){
				   			ps.write((char)27);
				   		}else {
				   			ps.println(parcial.get(i).toString());
				   		}	
			   		}
			   		
			   			
			   	}
			   	if (!debug){
			   		ps.close();
			   	}
			}catch(Exception e){
				
				e.printStackTrace();
					//JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Error Imprimiendo");
					error=true;
			}
			return !error;
	  }
	  public void setDebug(boolean debug){
		  this.debug=debug;
	  }
	  
	public static void main(String[] args){
		/*
		try {
		   FileWriter out= new FileWriter("LPT1");
		
		   out.write(esc);
		   out.write("A");
		   out.write(esc);
		   out.write("H0290");
		   out.write(esc);; 
		   out.write("V0002"); 
		   out.write(esc); 
		   out.write("S144-HM807046/QCL7C");
		   out.write(esc); out.write("H0290"); out.write(esc); out.write("V0034"); out.write(esc); out.write("BG01030144-HM807046/QCL7C");
		   out.write(esc); out.write("H0290"); out.write(esc); out.write("V0080"); out.write(esc); out.write("SRODAMIENTO RUEDA");
			out.write(esc); out.write("H0850"); out.write(esc); out.write("V0002"); out.write(esc); out.write("S144-HKJLOP/2RS/2RS");
			out.write(esc); out.write("H0850"); out.write(esc); out.write("V0034"); out.write(esc); out.write("BG01030144-HKJLOP/2RS/2RS");
			out.write(esc); out.write("H0850"); out.write(esc); out.write("V0080"); out.write(esc); out.write("SRODAMIENTO RUEDA");
			out.write(esc); out.write("Q5");
			out.write(esc); out.write("Z");
			
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		LinkedList etiquetas=new LinkedList();
		StrEtiqueta str_etiqueta=new StrEtiqueta();
		str_etiqueta.setCodigo("144-CRBR930064");
		str_etiqueta.setDescripcion("Rodamiento Maza de Rueda Ford");
		str_etiqueta.setCantidad(2);
		etiquetas.add(str_etiqueta);
		
		str_etiqueta=new StrEtiqueta();
		str_etiqueta.setCodigo("144-HM807046/QCL7C");
		str_etiqueta.setDescripcion("Centro de Cardan Toyota");
		str_etiqueta.setCantidad(2);
		//etiquetas.add(str_etiqueta);
		
		/*
		
		str_etiqueta=new StrEtiqueta();
		str_etiqueta.setCodigo("074-713572/6075");
		str_etiqueta.setDescripcion("COJINETE BANCADA FORD FALCON");
		str_etiqueta.setCantidad(21);
		etiquetas.add(str_etiqueta);
		
		str_etiqueta=new StrEtiqueta();
		str_etiqueta.setCodigo("377-000110819");
		str_etiqueta.setDescripcion("SOPORTE VARILLA MOTOR FORD FALCON 221 ECONOMAX BLA BLA");
		str_etiqueta.setCantidad(20);
		etiquetas.add(str_etiqueta);*/
		printing prt=new printing();
		prt.setPrintList(etiquetas);
		prt.setDebug(true);
		prt.print_job();
	}
}
