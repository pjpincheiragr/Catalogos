package aplicacion.sistema.menu.logic;
import java.util.Calendar;

import javax.swing.Timer;

public class Crono {
private Timer timer;

private Calendar current;
private Calendar initial;
private double acumulado;
private int done=1;

public Crono(){
	acumulado=0.0;
	
}
public void reset(){
	acumulado=0.0;
}
public String Left(int done,int total){
	Calendar current=Calendar.getInstance();
	long milis=current.getTimeInMillis()-initial.getTimeInMillis();
	Double LeftT=new Double((total-done)*((milis+acumulado)/done));
	return transform(LeftT.doubleValue());
}


public String elapsed(){
	Calendar current=Calendar.getInstance();
	long milis=current.getTimeInMillis()-initial.getTimeInMillis();
	Double LeftT=new Double((milis+acumulado));
	return transform(LeftT.doubleValue());
}

public void start(){
	initial=Calendar.getInstance();
	//System.out.println(initial.getTime());
}

public void pause(){
	Calendar current=Calendar.getInstance();
	long milis=current.getTimeInMillis()-initial.getTimeInMillis();
	Double T=new Double(milis);
	acumulado=acumulado+T.doubleValue();
}


private String transform(double milis){
	String auxTime="";
	Double hours=new Double(milis/(1000*60*60));
	Double minuts=new Double((milis/(1000*60))-hours.intValue()*60);
	Double seconds=new Double((milis/1000)-minuts.intValue()*60);
	String Second="",Minut="",Hour="";
	if (seconds.intValue()<10){Second="0"+seconds.intValue();}
	else {
	Second=""+seconds.intValue();
	}
	if (minuts.intValue()<10){Minut="0"+minuts.intValue();}
	else {
	Minut=""+minuts.intValue();
	}
	if (hours.intValue()<10){Hour="0"+hours.intValue();}
	else {
	Hour=""+hours.intValue();
	}
	auxTime=Hour+":"+Minut+":"+Second;
	return auxTime;
}

private void show(){
	Calendar current=Calendar.getInstance();
	long milis=current.getTimeInMillis()-initial.getTimeInMillis();
	System.out.println(transform(new Double(milis).doubleValue()));
	done++;
	//System.out.println("Done "+done+" Left Time: "+Left(done,100));
	
}



public static void main(String[] args){
	/*
	 *
	 * Crono C=new Crono();
	C.start();
	JFrame J=new JFrame();
	J.show();
	J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 */
	
}
}
