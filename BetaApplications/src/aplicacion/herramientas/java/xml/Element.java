package aplicacion.herramientas.java.xml;

import java.util.*;
public class Element {
 private String id="";
 private boolean root=false;
 private List<Atributo> atributos=null;
 private List<Element> elements=null;
 private boolean closed=false;
 
 public boolean isRoot() {
	return root;
}

public void setRoot(boolean root) {
	this.root = root;
}


 public Element(String id){
	 this.id=id;
 }
 
 public void setClose(){
	 this.closed=true;
 }
 
 public boolean isClosed(){
	 return this.closed;
 }
 
 public void addAtribute(Atributo a){
	 if (atributos==null){
		 atributos=new ArrayList<Atributo>();
	 }
	 atributos.add(a);
 }
 
 public void addElement(Element e){
	 if (elements==null){
		 elements=new ArrayList<Element>();
	 }
	 elements.add(e);
 }
 
 public Atributo getAtributo(String attribute_name){
 	
 	Atributo ex=null;
 		boolean found=false;
 		int i=0;
 		while (i<getAtributos().size() & !found){
 			ex=getAtributos().get(i);
 			found=ex.getNombre()==attribute_name;
 		if (!found)i++;	
 		}
 		
 	return ex;
 }
 public List<Atributo> getAtributos(){
	 return this.atributos;
 }
 
 public List<Element> getElements(){
	 return this.elements;
 }
 
 public String getId(){
	 return this.id;
 }
}
