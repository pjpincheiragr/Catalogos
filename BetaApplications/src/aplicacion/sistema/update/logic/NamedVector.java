package aplicacion.sistema.update.logic;
import java.util.*;

class NamedVector extends Vector {
	  String name;

	  public NamedVector(String name) {
	    this.name = name;
	  }

	  public NamedVector(String name, Object elements[]) {
	    this.name = name;
	    for (int i = 0, n = elements.length; i < n; i++) {
	      add(elements[i]);
	    }
	  }
	  public String getTitle(){
		  return name;
	  }
	  public String toString() {
	    return "[" + name + "]";
	  }
	}