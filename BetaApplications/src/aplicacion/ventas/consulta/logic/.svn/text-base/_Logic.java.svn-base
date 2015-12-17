package aplicacion.ventas.consulta.logic;

import javax.swing.JTextField;

import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import java.util.*;
public class _Logic extends Logic {
	public int count=0;
	private boolean suspendidov=false;
	private boolean suspendidoc=false;
	public boolean isSuspendidov() {
		return suspendidov;
	}

	public void setSuspendidov(boolean suspendidov) {
		this.suspendidov = suspendidov;
	}

	public boolean isSuspendidoc() {
		return suspendidoc;
	}

	public void setSuspendidoc(boolean suspendidoc) {
		this.suspendidoc = suspendidoc;
	}
	public List<Object[]> items=null;
	
	public _Logic(){
		items=new ArrayList<Object[]>();
	}
	public void showConsulta(){
		this.BuscarArticulo(null);
	}
	public aplicacion.herramientas.java.importadores.Articulo getConsulta(){
		return bArticulo;
	}
	private aplicacion.herramientas.java.importadores.Articulo bArticulo = null;
	public void BuscarArticulo(JTextField ext) {
		if (bArticulo != null) {
			bArticulo.close();
		}
		bArticulo = 
		new aplicacion.herramientas.java.importadores.Articulo(this.getConstructor()){
			public void cargar(Object[] seleccion){
				agregar(seleccion);
			}
		};
		bArticulo.setCaller(this.getConstructor());
		bArticulo.setSuspendidoc(suspendidoc);
		bArticulo.setSuspendidov(suspendidov);
		bArticulo.Buscar(ext);
	}
	
	public void agregar(Object[] item){
		if (count==0){
			if (preguntar("Confirmar","Crea Pedido Con los items Seleccionados?")){
				this.crear_pedido();
			}
			count++;
		}
		if (pedido!=null){
			aplicacion.ventas.pedido.logic._Logic logic=
				(aplicacion.ventas.pedido.logic._Logic)pedido.getLogic();
			logic.agregar(item);	
		}
			
	}
	
	public void exit_command(){
		bArticulo.close();
		super.exit_command();
	}
	aplicacion.ventas.pedido.constructor._Constructor pedido=null;
	public void crear_pedido(){
		System.out.println("Crear Pedido>");
		pedido=new aplicacion.ventas.pedido.constructor._Constructor();
		pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		pedido.build(this.getConstructor());
		pedido.init();
		aplicacion.ventas.pedido.logic._Logic logic=
			(aplicacion.ventas.pedido.logic._Logic)pedido.getLogic();
		logic.automatico();
	}
	
	
}
