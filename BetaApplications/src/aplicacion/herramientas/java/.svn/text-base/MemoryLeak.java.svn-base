package aplicacion.herramientas.java;
import java.util.*;
public class MemoryLeak {

	 static HashSet myContainer = new HashSet();
	  public void leak(int numObjects) {
	    for (int i = 0; i < numObjects; ++i) {
	      String leakingUnit = new String("this is leaking object: " + i);
	      myContainer.add(leakingUnit);
	    }
	  }
	/**
	 * @param args
	 */
	  public static void main(String[] args) throws Exception {
		    System.out.print("May I start leaking? (hit enter)");  
		    System.in.read(new byte[4]);
		    {
		      MemoryLeak myObj = new MemoryLeak();
		      myObj.leak(1000000000);
		    }
		    System.out.println("oops!!! I have leaked " +
		                        myContainer.size() + " objects!!!!!");
		    System.out.print("hit enter to exit"); System.in.read();
		  }
}
