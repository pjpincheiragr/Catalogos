package aplicacion.ventas.gestion2.logic;

import java.io.*;
import java.util.*;
import javax.swing.tree.*;

public class Nodo extends DefaultMutableTreeNode {

public final static int SINGLE_SELECTION = 0;
public final static int DIG_IN_SELECTION = 4;
protected int selectionMode;
protected String value;

public Nodo() {
this(null,"");

}

public Nodo(Object userObject,String value) {
this(userObject, value,true);
}

public Nodo(Object userObject, String value,boolean allowsChildren) {
super(userObject, allowsChildren);
this.value=value;

setSelectionMode(DIG_IN_SELECTION);
}

public void setSelectionMode(int mode) {
selectionMode = mode;
}

public void setValue(String tipo){
	this.value=tipo;
}

public int getSelectionMode() {
return selectionMode;
}
public String getValue() {
	return value;
}



}