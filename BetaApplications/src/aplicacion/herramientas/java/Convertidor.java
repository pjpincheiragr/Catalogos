package aplicacion.herramientas.java;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.*;
import java.text.*;
public class Convertidor {
	private boolean debug=false;
	private boolean forced=false;
	private LinkedList P;
	private int max;
	
	public void setMax(int i){
		this.max=i+1;
	}
	
	public Convertidor(){
		forced=true;
	}
	double roundTwoDecimals(double d) {
    	DecimalFormat twoDForm = new DecimalFormat("#.##");
	return Double.valueOf(twoDForm.format(d));
	}
	public String getEnters(String q){
		String s="";
		for (int i=0;i<q.length();i++){
			char c=q.charAt(i);
			System.out.println("ASCII de ("+c+") = ("+(int) c+")");
				
			if ((int) c==10){
				System.out.println("Enter Detected!");
				s=s+"\n";
			}else {
				s=s+q.charAt(i);	
			}
			
		}
		return s;
	}
	public int getWidth(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		return dim.width;
	}
	public int getHeight(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		return dim.height;
	}
		
	public String ConvertDate(String formatodeseado,String formatoactual,String s){
		DateFormat formatter;
	    Locale locale = Locale.getDefault();
	    String s1="";
	    Date date=null;
	    Date date2=null;
	    try {
	    	
	    	formatter = new SimpleDateFormat(formatoactual, locale);
	    	date = (Date)formatter.parse(s);
	    	formatter = new SimpleDateFormat(formatodeseado);
	        s1 = formatter.format(date);
	        //date2 = (Date)formatter.parse(s1);
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return s1;
	}
	
	public String getDateWithFormat(String formatodeseado){
		DateFormat formatter;
	    
	    String s1="";
	    Date date=null;
	    
	    try {
	    	
	    	
	    	date = new Date();
	    	formatter = new SimpleDateFormat(formatodeseado);
	        s1 = formatter.format(date);
	    
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return s1;
	}
	
	public String getDateWithFormat(String formatodeseado,Date date){
		DateFormat formatter;
	    
	    String s1="";
	    
	    
	    try {
	    	
	    	
	    	formatter = new SimpleDateFormat(formatodeseado);
	        s1 = formatter.format(date);
	    
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return s1;
	}
	public Date getDateWithFormat2(String formatoutilizado,String fecha){
		DateFormat formatter;
		Locale locale = Locale.getDefault();
	    String s1="";
	    Date date=null;
	 	formatter = new SimpleDateFormat(formatoutilizado, locale);
	    try {
	    	
	    	
	    	date = new Date();
	   
	    	date = (Date)formatter.parse(fecha);
	        //s1 = formatter.parse(formatoutilizado) fecha;
	    
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    	date=null;
	    }
	    if (date==null){
	    	try {
				date = (Date)formatter.parse("01-01-1900");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return date;
	}
	
	public String remove_last_spaces(String s){
    	String aux=s;
    	if (s!=null){
    		int i=s.length();
        	boolean spaces=true;
        	while (i>0 & spaces){
        		aux=s.substring(0, i);
        		spaces=aux.substring(aux.length()-1).compareTo(" ")==0;
        	i--;	
        	}	
    	}else{
    		s="";
    	}
    	
       	return aux;
    }
	
	public int getLevel(String code){
		int level=-1;
		String s0=code.substring(2,4);
		//System.out.println("fraction 1:"+s0);
		String s1=code.substring(5,7);
	    //System.out.println("fraction 2:"+s1);
		String s2=code.substring(8,11);
		//System.out.println("fraction 3:"+s2);
		int n0,n1,n2;
		n0=0;n1=0;n2=0;
		try {
			n0=new Integer(s0);
		}catch(Exception e){};
		try {
			n1=new Integer(s1);
		}catch(Exception e){};
		try {
			n2=new Integer(s2);
		}catch(Exception e){};
		if (n0==0 && n1==0 && n2==0){
			level=0;
		}
		if (n0>0 && n1==0 && n2==0){
			level=1;
		}
		if (n0>0 && n1>0 && n2==0){
			level=2;
		}
		if (n0>0 && n1>0 && n2>0){
			level=3;
		}
		return level;
	}
	
	public String getMoney(String num){
		String aux=num;
		String first="";
		String decimals="";
		if (aux.contains(".")){
			first=aux.substring(0, aux.indexOf("."));
			decimals=aux.substring(aux.indexOf(".")+1, aux.length());
			if (decimals.length()<2){
				for (int i=decimals.length();i<3;i++){
					decimals=decimals+"0";
				}
			}
			if (decimals.length()>2){
				decimals=decimals.substring(0,2);
			}
		}else {
			first=aux;
			decimals="00";
		}
		
		aux=first+"."+decimals;
		return aux;
	}
	
	public String getMes_Operativo(String fecha){
		SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");
		Date now =null;
		String s="";
		try {
			now = (Date)format.parse(fecha);
		}catch(Exception e){
			System.out.println("algo mal con la fecha");
		}
		if (now!=null){
			SimpleDateFormat format2 = new SimpleDateFormat("M");
			s = format2.format(now);	
		}
		
		return s;
	}
	
	/*
	 * Devuelve fecha del dia de la fecha con el formato yy/M/d HH:mm:ss
	 */
	public String getDate(){
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yy/M/d HH:mm:ss");
		String s = format.format(now);
		return s;
	}
	
	public void PrintEnter(String text){
		String aux=text;
		System.out.println("el enter empieza en :"+text.indexOf("\n"));
		if (text.contains("\n")){
			aux=text.substring(0,text.indexOf("\n")-1)+text.substring(text.indexOf("\n")+1);
		}
		System.out.println(aux);
	}
	
	public String sacarEnter(String text){
		String aux=text;
		while (aux.contains("\n")){
			aux=aux.substring(0,aux.indexOf("\n")-1)+aux.substring(aux.indexOf("\n")+1);
		}
		return aux;
	}
	/**
	 * Devuelve un Objeto Date dada un String con fecha con formato dd-MM-yyyy
	 * @param s
	 * @return
	 */
	public Date getDate(String s){
		DateFormat formatter;
	    Date date=null;
	    try {
	    	formatter = new SimpleDateFormat("dd-MM-yyyy");
	    	date = (Date)formatter.parse(s);
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return date;
	}
	
	public Date getDateWithFormat(String formatodeseado,String formatoactual,String s){
		DateFormat formatter;
	    s=this.ConvertDate(formatodeseado, formatoactual, s);
	    Date date=null;
	    try {
	    	formatter = new SimpleDateFormat(formatodeseado);
	    	date = (Date)formatter.parse(s);
	        
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return date;
	}
	
	public String getDate5x(String s){
		DateFormat formatter;
	    Locale locale = Locale.getDefault();
	    String s1="";
	    Date date=null;
	    try {
	    	
	    	formatter = new SimpleDateFormat("yyyy-MM-d hh:mm:ss", locale);
	    	date = (Date)formatter.parse(s);
	    	formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	        s1 = formatter.format(date);
	        System.out.println(s+" ->"+s1);
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return s1;
	}
	
	public String getDate2(){
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("d-M-yy");
		String s = format.format(now);
		return s;
	}
	public String getDate3(){
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String s = format.format(now);
		return s;
	}
	
	public String getDate4(){
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String s = format.format(now);
		return s;
	}

	
	public String getDate5(){
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm EEEE d 'de' MMMM 'del' yyyy");
		String s = format.format(now);
		return s;
	}
	
	public void setForced(boolean b){
		this.forced=b;
	}
	
	public Object[][] getEmptyResutl(int columns){
		Object[][] temp=new Object[1][columns];
		for (int i=0;i<columns;i++){
			temp[0][i]="";
		}
		return temp;
	}
	
	public String replace(String cadena,char c,char x){
		int ii;
		if (cadena.contains(""+c)){
			ii=1;
			while (cadena.charAt(ii)!=c) {
				ii++;
			}
			if (cadena.charAt(ii)==c){
				cadena=cadena.substring(0,ii)+x+cadena.substring(ii+1,cadena.length());
			}
		}
		return cadena;
	}
	
	
	
	
	
	
	 
		
public String LimpiarPrecio(String cadena){ 
        if (cadena.contains(",")){
        	int i=0;
        	String aux="";
        	while (cadena.charAt(i)!=','){
        		aux=aux+cadena.charAt(i);
        		i++;
        	}
        	if (cadena.charAt(i)==','){
        		aux=aux+"."+cadena.substring(i+1,cadena.length());
        	}
        	cadena=aux;
        }
        if (cadena.contains("\"")){
        	cadena=this.LimpiarString(cadena,"\"");
        }
        double c=0.0;
        try {
        	c=new Double(cadena).doubleValue();
        }
        catch (Exception e){
            System.out.println("No se puede convertir "+cadena+" a doble devolviendo 0.0");
        }
     if (c ==0){
    	 return "0.0";
     }else {
    	 return (new Double(c).toString());
     }
     
    }
    
    public Object[][] QuickSort(Object[][] lista,int col){
    	System.out.println("> Size = "+lista.length+"x"+lista[0].length);
    	return this.quicksortForRows(lista, 0, lista.length-1,col);
    }
    
	public Object[][] GQuickSort(Object[][] lista,int inferior,int superior,int col){
		boolean work=true;
		String elem_div=lista[superior][col].toString();
		int i=inferior-1;
		int j=superior;
		if (inferior>=superior){
			return lista;
		} else
		{
			
		}
		System.out.println("working "+lista[i][col].toString()+" "+elem_div);
		while (work){
			
			while(lista[i][col].toString().compareTo(elem_div)<0 && i<superior){
							i++;
			}
			while(lista[j][col].toString().compareTo(elem_div)>0 && j>1){
				j--;
			}
		if (i<j){
			 for (int u=0;u<lista[0].length;u++){
				 String temp = lista[i][u].toString();
				 lista[i][u] = lista[j][u];
				 lista[j][u] = temp;	 
			 }
		} else {
			work=false;
//	System.out.println("working");
		}
	}
		
		for (int u=0;u<lista[0].length;u++){
			 String temp = lista[i][u].toString();
			 lista[i][u] = lista[superior][u];
			 lista[superior][u] = temp;	 
		 }
		
		lista=GQuickSort(lista,inferior,i-1,col);
		lista=GQuickSort(lista,i+1,superior,col);
		return lista;	
			
	}
	
	public String[] QuickSort(String[] lista,int inferior,int superior){
		boolean work=true;
		String elem_div=lista[superior];
		int i=inferior-1;
		int j=superior;
	
		if (inferior>=superior){
			return lista;
		}
		while (work){
			while(lista[i].compareTo(elem_div)<0 && i<superior){
				i++;
			}
			while(lista[j].compareTo(elem_div)>0 && j>1){
				j--;
			}
		if (i<j){
			 String temp = lista[i];
			 lista[i] = lista[j];
			 lista[j] = temp;
		} else {
			work=false;
		}
	}
		String temp = lista[i];
		lista[i] = lista[j];
		lista[j] = temp;
		lista=QuickSort(lista,inferior,i-1);
		lista=QuickSort(lista,i+1,superior);
		return lista;
	}
	
	public double formula(String Sx){
		int u=0,v=0;
		boolean error=false;
		double multi=1.0;
		String chrx=new String("");
		String matchx=new String("");
		String S=Sx+"%";
		//System.out.println(S);
		while (u<S.length()){
			chrx=S.substring(u,u+1);
			if (chrx.compareTo("%")==0 & u<S.length()){
				matchx="";
				v=u+1;
				while (v<S.length() && matchx.compareTo("")==0){
					chrx=S.substring(v,v+1);
					//System.out.println("u:"+u+"v:"+v);
					if (chrx.compareTo("%")==0){
						try {
							matchx=S.substring(u+1,v);
						}
						catch (Exception e){
							u=S.length();v=S.length();
						}
						if (matchx.substring(0,1).compareTo("-")==0){
							matchx=matchx.substring(1,matchx.length());
							Double aux=0.0;
							try {
								aux=new Double(matchx);
							}catch(Exception e){
								error=true;
							}
							double ax=(100-aux.doubleValue())/100;
							multi=multi*ax;
						}else {
							matchx=matchx.substring(1,matchx.length());
							Double aux=new Double(matchx);
							double ax=(100+aux.doubleValue())/100;
							multi=multi*ax;
						}
						
						//System.out.println("matchx "+matchx+" multi:"+multi);
						u=v;
					}else {
						v++;
					}
					
				}
			} else {
				u++;
			}
			if (chrx.compareTo("%")==0 && u==S.length()-1) {
				u++;
				//System.out.println("u:"+u);
			}
			}
		if (v>=S.length()){
			try {
			matchx=S.substring(u+1,v);
			Double aux=new Double(matchx);
			double ax=(100-aux.doubleValue())/100;
			multi=multi*ax;
			System.out.println("matchx "+matchx);
			}catch (Exception e){
				u=S.length();v=S.length();
			}
		}
		
		
		return multi;
	}
	
	
	public Object[][] orderbyprice(Object[][] aux){
		boolean prueba=false;
		int min=0;
		double diference=0.01;
		double precio_viejo=0.0;
		double precio_nuevo=0.0;
		int vacios=0;
		for (int u=0;u<aux.length;u++){
		    prueba=aux[u][5].toString().compareTo("")!=0 && aux[u][3].toString().compareTo("")!=0;
		    if (prueba){
		    	precio_nuevo=0.0;
		    	precio_viejo=0.0;
		    	try {
		    	precio_nuevo=new Double(aux[u][3].toString()).doubleValue();
		    	}
		    	catch(Exception e){
		    		prueba=false;
		    	}
		    	try {
		    	precio_viejo=new Double(aux[u][5].toString()).doubleValue();
		    	}
		    	catch(Exception e){
		    		prueba=false;
		    	}
		    	if (prueba){
		    		if (forced) {
						prueba=true;
					} else {
						prueba=Math.abs(precio_nuevo-precio_viejo)>diference;
					}
		    		if (prueba){
				    	prueba=(precio_nuevo>0);
				    	if (prueba) {
							prueba=(aux[u][6].toString().compareTo("")!=0);
						}
				    	//para asegurar que esta en una lista de precio o tiene politica, nose.
						if (prueba){
							min++;
						}else {
					    	System.out.println("Convertidor.sorbyprice(): Error,el articulo no tiene lista de precio");
					    }
						
				    }else {
				    	System.out.println("Convertidor.sorbyprice(): Aviso,los precios no han variado");
				    }
		    	}else {
			    	System.out.println("Convertidor.sorbyprice(): Aviso,error posible en transformacion de precios");
			    }
		    	
		    	
		    }else {
		    	System.out.println("Convertidor.sorbyprice(): Error, alguno de los campos de precio nuevo o viejo esta vacio");
		    }
		    
		
		    
		}
		Object[][] minaux= new Object[min][aux[0].length];
		min=0;
		for (int u=0;u<aux.length;u++){
			if (prueba){
		    	precio_nuevo=0.0;
		    	precio_viejo=0.0;
		    	try {
		    	precio_nuevo=new Double(aux[u][3].toString()).doubleValue();
		    	}
		    	catch(Exception e){
		    		prueba=false;
		    	}
		    	try {
		    	precio_viejo=new Double(aux[u][5].toString()).doubleValue();
		    	}
		    	catch(Exception e){
		    		prueba=false;
		    	}
		    	if (prueba){
		    		if (forced) {
						prueba=true;
					} else {
						prueba=Math.abs(precio_nuevo-precio_viejo)>diference;
					}
		    		if (prueba){
				    	prueba=(precio_nuevo>0);
				    	if (prueba){
							if (debug) {
								System.out.println(precio_viejo+" <<>> "+precio_nuevo);
							}
							for (int v=0;v<aux[0].length;v++){
								minaux[min][v]=aux[u][v];
							}
							min++;
							}
				    }
		    	}
		    }
		       
				
		}
		JOptionPane.showMessageDialog(new JFrame("Aviso"), "Convertidor.sortbyprice(): Se encontraron "+min+" articulos con precios desactualizados de "+aux.length);
		//compara el precio ubicado e la columna [u][3] para ordenar 
		for (int u=0;u<minaux.length-1;u++){
			for (int v=u+1;v<minaux.length;v++){
				Double auxv=new Double("0.0");
				Double auxu=new Double("0.0");
				if (minaux[v][3].toString().compareTo("")!=0){
					auxv=new Double(minaux[v][3].toString());	
				}
				if (minaux[u][3].toString().compareTo("")!=0){
					auxu=new Double(minaux[u][3].toString());	
				}
		        
				if (auxu.compareTo(auxv)>0){
					for (int w=0;w<minaux[0].length;w++){
					Object auxs=minaux[u][w];
					minaux[u][w]=minaux[v][w];
					minaux[v][w]=auxs;
					}
				}
			}
		}
		return minaux;
	}
	public String LimpiarStringCompleto(String cadena,String match){
		String aux=cadena;int val;
		val=aux.indexOf(match);
		if (val>=0){
			aux=aux.replace(match, "");
		}
		return aux;
	}
	public String LimpiarString(String cadena,String match){ 
        String aux=cadena;int val;
        val=aux.indexOf(match);
        String aux2="";
        while (val >-1){
        aux2="";
       for (int y=0;y<aux.length();y++){
            if (y!=val){
            aux2=aux2+aux.substring(y,y+1);
            }
        }
        
        aux=aux2;
        val=aux.indexOf(match);
        }
        return aux;
     }
	
	public int getOcurrences(String cadena,String match){ 
        String aux=cadena;int val;
        val=aux.indexOf(match);
        String aux2="";
        int matches=0;
        while (val >-1){
        	matches++;
        	aux2="";
       for (int y=0;y<aux.length();y++){
            if (y!=val){
            aux2=aux2+aux.substring(y,y+1);
            }
        }
        
        aux=aux2;
        val=aux.indexOf(match);
        }
        return matches;
     }
	
	public String normalize(String precio){
		
		int qty=this.getOcurrences(precio,",");
		int z=-1;
		if (qty>1){
			System.out.print("normalizar: "+precio+" con "+qty+"(,) ");
			int i=0;
			int x=0;
			while (i<qty){
				if (precio.indexOf(",", x)<0){
				z=x;	
				}
				x=precio.indexOf(",", x);
				i++;
			}
			precio=precio.substring(0, z-1).replaceAll(",", "")+precio.substring(z,precio.length());
			System.out.println("=>"+precio);
		}
		
		precio=precio.replaceAll(",", ".");
		
		return precio;
	}
	
	public String replace(String cadena,String match,String r){
		String aux=cadena;int val;
        aux=aux.replaceAll(match, r);
        return aux;
	}
	
	public String[] quicksort (String[] a, int lo, int hi)
	{
//	  lo is the lower index, hi is the upper index
//	  of the region of array a that is to be sorted
	    int i=lo, j=hi;
	    String x=a[(lo+hi)/2];
	    String temp;
	    //  partition
	    do
	    {    
	        while (a[i].compareTo(x)<0) {
				i++;
			} 
	        while (a[j].compareTo(x)>0) {
				j--;
			}
	        if (i<=j)
	        {
	            temp=a[i]; a[i]=a[j]; a[j]=temp;
	            i++; j--;
	        }
	    } while (i<=j);

	    //  recursion
	    if (lo<j) {
			quicksort(a, lo, j);
		}
	    if (i<hi) {
			quicksort(a, i, hi);
		}
	    return a;
	}

	public Object[][] quicksortForRows (Object[][] a, int lo, int hi,int col)
	{
//	  lo is the lower index, hi is the upper index
//	  of the region of array a that is to be sorted
	    int i=lo, j=hi;
	    String x=a[(lo+hi)/2][col].toString();
	    Object[] temp;
	    //  partition
	    do
	    {   System.out.println("X> "+x); 
	        while (a[i][col].toString().compareTo(x)<0) {
	        	System.out.println("i> "+x+" "+a[i][col].toString()+" ");
	        	i++; 
	        }
	        while (a[j][col].toString().compareTo(x)>0) {
	        	System.out.println("j> "+x+" "+a[j][col].toString()+" ");
	        	j--;
	        }
	        if (i<=j)
	        {
	        	System.out.println("change");
	            temp=a[i];
	            a[i]=a[j];
	            a[j]=temp;
	            i++; j--;
	        }
	    } while (i<=j);

	    //  recursion
	    if (lo<j) {
			quicksortForRows(a, lo, j,col);
		}
	    if (i<hi) {
			quicksortForRows(a, i, hi,col);
		}
	    return a;
	}

	public String sacarRepeticiones(String s,String match){
		
		
		int idx=0;
		int u=s.toUpperCase().indexOf(match.toUpperCase());
		idx=u;
		//System.out.println("matches? "+u+" now >"+s);
		while (u>-1){
			s=s.substring(0,u)+s.substring(u+match.length(),s.length());
			u=s.toUpperCase().indexOf(match.toUpperCase());
			//System.out.println("matches? "+u+" now >"+s);
		}
		s=s.subSequence(0, idx)+match+s.subSequence(idx, s.length());
		//System.out.println(" now >"+s);
		return s;
	}
	
	public String[] valores(String cadena,char divisor){
		int i=0;
		while(cadena.charAt(i)==divisor) {
			i++;
		}
		int count=1;
		
		while (i<cadena.length()){
			if (cadena.charAt(i)==divisor){
				while(cadena.charAt(i)==divisor & (i+1)<cadena.length()) {
					i++;
				}
				count++;
			}
			i++;
		}
		String[] aux=new String[count];
		i=0;
		count=0;
		
		while(cadena.charAt(i)==divisor) {
			i++;
		}
		int inf=i;
		while (i<cadena.length()){
			if (cadena.charAt(i)==divisor){
				aux[count]=cadena.substring(inf,i);
				count++;
				while(cadena.charAt(i)==divisor & (i+1)<cadena.length()) {
					i++;
				}
				inf=i;
			}
			i++;
		}
		if (!cadena.substring(inf,i).contains(" ")) {
			aux[count]=cadena.substring(inf,i);
		}
		return aux;
	}
	
	public void print(Object[] a){
		for (int i=0;i<a.length;i++){
			System.out.print(a[i].toString()+" ");
		}
		System.out.println();
	}
	
	public void print(Object[][] a){
		for (int i=0;i<a.length;i++){
			this.print(a[i]);
		}
	}
	
	public Object[][] rotate(Object[][] a){
		Object[][] temp=new Object[a[0].length][a.length];
		for (int i=0;i<a.length;i++){
			for (int j=0;j<a[0].length;j++){
				temp[j][i]=a[i][j];
			}
		}
		return temp;
	}
	
	public String getMoney_con_separador_de_miles(String money){
		String aux=money;
		int i=aux.indexOf(".");
		int now_miles=aux.substring(0,i).length();
		//System.out.println("cifras? "+now_miles);
		int comas=Math.round((now_miles-1)/3);
		//System.out.println("comas? "+comas);
		int cx=i;
		for (int c=0;c<comas;c++){
			cx=cx-3;
			aux=aux.substring(0,cx)+","+aux.substring(cx,aux.length());
		}
		
		return aux;
		
	}
	public String getMoney(String val,int decimals){
		String aux="";
		double tmp=0.0;
		try {
			tmp=new Double(val.replace(",", ""));
		}catch(Exception e){
			System.out.println("Error calculando money en convertidor "+e.getMessage());
		}
		aux=this.getMoney(tmp, decimals);
		return aux;
	}
	
	public String getMoney(double val,int decimals){
		String aux="";
		
		
		Double ax=this.roundDouble(val, decimals);
		//System.out.println("ax"+ax);
		aux=ax.toString();
		int i=aux.indexOf(".");
		//System.out.println("punto . en "+i);
		int now_decimals=0;
		if (i>=0){
			now_decimals=aux.substring(i+1).length();
		}
		//System.out.println(" "+i+"+"+decimals+"="+(i+decimals)+" y aux.length="+aux.length()+" now decimals?"+now_decimals);
		
		if (now_decimals<decimals){
			//System.out.println("Agregar ceros");
		for (int y=0;y<decimals-(now_decimals);y++){
			aux=aux+"0";
		}
		
		//aux=aux.substring(0, i+decimals+1);
		
		}else {
			/*
			int fval=new Integer(aux.substring(i+2,i+3));;
			if (now_decimals>decimals){
				String rounds=aux.substring(i+3,i+4);
				
				//System.out.println("Redondeo=?"+rounds);
				int round=0;
				try {
					round=new Integer(rounds);
				}catch(Exception e){}
				if (round>5){
					//System.out.println("Redondeo=?"+round);
					fval++;
				}
				aux=ax.toString();		
			}*/
			//aux=aux.substring(0, i+2)+fval;	
		}
		String init="";
		if (aux.contains("-")){
			init="-";
			aux=aux.substring(1);
			}
		aux=init+this.getMoney_con_separador_de_miles(aux);
		return aux;
	}
	
	public String cutSlashes(String s){
		s=s.replace("\\", "\\\\");
		return s;
	}
	/**
	 * Devuelve 1 si la primer fecha es mayor que la segunda
	 */
	public int getMayorDate(Date date1,Date date2){
		int ix=1;
		Calendar C=Calendar.getInstance();
		Calendar C2=Calendar.getInstance();
		C.setTime(date1);
    	C2.setTime(date2);
    	if (C.getTimeInMillis()>C2.getTimeInMillis()) {
        	ix=1;
        }else {
        	ix=0;
        }
		return ix;
	}
	
	/**
	 * Devuelve 1 si la primer fecha es mayor que la segunda
	 */
	public int getMayorDate(String s1,String s2){
		DateFormat formatter;
	    Locale locale = Locale.getDefault();
	    int ix=1;
	    Date date1=null;
	    Date date2=null;
	    
	    try {
	    	formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
	    	date1 = (Date)formatter.parse(s1);
	    	//System.out.println("Transformado fecha: "+s1+" "+date1);
	    	formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
	    	date2 = (Date)formatter.parse(s2);
	    	
	    	//System.out.println("Transformado fecha: "+s2+" "+date2);
	    	
	    	
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    ix=this.getMayorDate(date1, date2);
	    return ix;
	}
	/**
	 * Devuelve los dias entre la fecha menor desde y la fecha mayor hasta
	 * @param menor
	 * @param mayor
	 * @return
	 */
	public int getDateDiff(String menor,String mayor){
		DateFormat formatter;
	    Locale locale = Locale.getDefault();
	    int ix=1;
	    Date date1=null;
	    Date date2=null;
	    Calendar C=Calendar.getInstance();
		Calendar C2=Calendar.getInstance();
	    try {
	    	formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
	    	date1 = (Date)formatter.parse(menor);
	    	//System.out.println("Transformado fecha: "+s1+" "+date1);
	    	formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
	    	date2 = (Date)formatter.parse(mayor);
	    	//System.out.println("Transformado fecha: "+s2+" "+date2);
	    	Date now=new Date();
	    	C.setTime(date1);
	    	C2.setTime(date2);
	    	ix=now.compareTo(date1);
	    	long diff = C2.getTimeInMillis() - C.getTimeInMillis();
	        
	        long diffDays = diff / (24 * 60 * 60 * 1000);
	        ix=new Long(diffDays).intValue();
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return ix;
	}
	public int getDateVenc(String s){
		DateFormat formatter;
	    Locale locale = Locale.getDefault();
	    int ix=0;
	    Date date=null;
	    Calendar C=Calendar.getInstance();
		Calendar C2=Calendar.getInstance();
	    try {
	    	formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
	    	date = (Date)formatter.parse(s);
	    	//System.out.println("Transformado fecha: "+s+" "+date);
	    	Date now=new Date();
	    	C.setTime(now);
	    	C2.setTime(date);
	    	ix=now.compareTo(date);
	        if (C.getTimeInMillis()>C2.getTimeInMillis()) {
	        //	System.out.println("Porque esta vencida?");
	        //	System.out.println("Ahora es:"+C.getTimeInMillis()+" = "+C.getTime());
	        //	System.out.println("Lo Programado era:"+C2.getTimeInMillis()+" = "+C2.getTime());
	        	ix=1;
	        }else {
	        	ix=0;
	        }
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return ix;
	}
	
	public String getDate6x(String s){
		DateFormat formatter;
	    Locale locale = Locale.getDefault();
	    String s1="";
	    Date date=null;
	    try {
	    	
	    	formatter = new SimpleDateFormat("yyyy-MM-d hh:mm:ss", locale);
	    	date = (Date)formatter.parse(s);
	    	formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	        s1 = formatter.format(date);
	        System.out.println(s+" ->"+s1);
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return s1;
	}
	
	 public double roundDouble(double d, int places) {
	        return Math.round(d * Math.pow(10, (double) places)) / Math.pow(10,
	            (double) places);
	    }
	 
	public static void main(String[] args){
		Convertidor Cv=new Convertidor();
		Double v=new Double("110.6645608301542064");
		//v=Cv.roundDouble(v, 3);
		System.out.println(Cv.getMoney(v, 5));
		
		
		
	
	}
	
}
