package aplicacion.proveedor.guia.logic;

import javax.swing.JCheckBox;

public class MyCheckBox extends JCheckBox {
	private String idclasificacion="";

	public String getIdclasificacion() {
		return idclasificacion;
	}

	public void setIdclasificacion(String idclasificacion) {
		this.idclasificacion = idclasificacion;
	}
	
}
