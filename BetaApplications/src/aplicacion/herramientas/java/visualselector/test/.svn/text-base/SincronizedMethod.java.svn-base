package aplicacion.herramientas.java.visualselector.test;

public class SincronizedMethod extends Object {
	private static int count = 1;
	
	public static synchronized int getCount() {
	int i = count;
	count++;
	return i;
	}
	private static void print(String msg) {
	String threadName = Thread.currentThread().getName();
	System.out.println(threadName + ": " + msg);
	}
	public static void main(String[] args) {
	try {
	Runnable runnable = new Runnable() {
	public void run() {
	System.out.println("count=" + getCount());
	}
	};
	Thread threadA = new Thread(runnable, "ThreadA");
	threadA.start();
	Thread.sleep(500); 
	Thread threadB = new Thread(runnable, "ThreadB");
	threadB.start();
	Thread.sleep(500); 
	Thread threadC = new Thread(runnable, "ThreadC");
	threadC.start();
	Thread.sleep(500); 
	Thread threadD = new Thread(runnable, "ThreadD");
	threadD.start();
	} catch(Exception x ) {}
	}
}
	
	