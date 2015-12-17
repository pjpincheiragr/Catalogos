package aplicacion.gestion.agenda.escritor.logic;

import javax.swing.tree.DefaultMutableTreeNode;

public class CheckBoxNode extends DefaultMutableTreeNode {
	  String text;
	  String idclasificacion;
	  boolean selected;

	  public CheckBoxNode(String text, String idclasificacion,boolean selected) {
		super(text,true);
		this.text = text;
	    this.idclasificacion=idclasificacion;
	    this.selected = selected;
	    
	  }

	  public boolean isSelected() {
	    return selected;
	  }

	  public void setSelected(boolean newValue) {
		  System.out.println("Seleccion "+newValue+" en "+idclasificacion);
	    selected = newValue;
	  }

	  public String getText() {
	    return text;
	  }

	  public void setText(String newValue) {
	    text = newValue;
	  }

	  public String toString() {
		  return text;
	    //return getClass().getName() + "[" + text + "/" + selected + "]";
	  }

	public String getIdclasificacion() {
		return idclasificacion;
	}

	public void setIdclasificacion(String idclasificacion) {
		this.idclasificacion = idclasificacion;
	}
	}
