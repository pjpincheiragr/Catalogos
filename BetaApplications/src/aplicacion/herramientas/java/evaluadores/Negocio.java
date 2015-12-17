package aplicacion.herramientas.java.evaluadores;

import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.modelo.constructor.Constructor;

import javax.swing.*;

public class Negocio extends Generic {

	public String getQuery(String id) {
		id=id.replaceAll(" ", "");
		String q = "";
		q += "select codigo,descripcion ";
		q += "from v_ta_unidadnegocio ";
		q += "where ltrim(rtrim(codigo)) like '" + id + "' ";
		return q;
	}

	public String getAviso() {
		String aviso = "no hay unidades de negocio con ese codigo";
		return aviso;
	}

	private aplicacion.herramientas.java.visualizadores.Negocio negocio = null;
	public void buscar(JTextField tx) {
		if (negocio == null) {
			negocio = new aplicacion.herramientas.java.visualizadores.Negocio(
					constructor);
		}
		int n = negocio.Buscar(tx);
		if (n == 0) {
			constructor.getLogic().aviso("no hay Negocios con ese codigo");
		}
	}

	private aplicacion.herramientas.java.buscadores.Negocio bNegocio = null;
	public void Buscar(JTextField ext) {
		if (bNegocio == null) {
			bNegocio = new aplicacion.herramientas.java.buscadores.Negocio(
					constructor);
		}
		
		bNegocio.Buscar(ext);
	}

}
