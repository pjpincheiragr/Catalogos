package aplicacion.herramientas.java.importadores;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import aplicacion.herramientas.java.image.logic.ImageComponent;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.herramientas.conexion.*;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
private aplicacion.herramientas.java.buscadores.Articulo bArticulo=null;
public void BuscarArticulo(JTextField ext) {
	 if (bArticulo!=null){
		 bArticulo.close();
	 }
 bArticulo=new aplicacion.herramientas.java.buscadores.Articulo(this.getConstructor().getConnectionHandler());
 bArticulo.Buscar(ext);
} 
 */
import aplicacion.modelo.constructor.*;
public class PedidoCliente extends Generico{
	private JFrame frame=null;
	
		public PedidoCliente(Constructor C){
		super(C);
	}

	public void cargar(Object[] selection){
		System.out.println("Cargar Seleccion de PDC");
	}
	
	public void initializeConstructor() {
		C = new aplicacion.herramientas.java.msortableselector.constructor._Constructor() {
			@Override
			protected void initialize_logic() {
				_logic = new aplicacion.herramientas.java.msortableselector.logic._Logic() {
					
					@Override
					public void add(Object[] selection) {
						cargar(selection);
					}
					public void exit_command(){
						
						if (frame!=null){
							frame.setVisible(false);	
						}
						
						super.exit_command();
					}
					
					public void DoSomethingSeleccion(JTextField tx,int row,int col,JTable table){
						System.out.println(">"+table.getValueAt(row, col).toString()+"<");
						String idarticulo=table.getValueAt(row, 1).toString();
						
						
						super.focus(col);
					}
					
					public Color getColor(int row,int col,JTable table){
						Color color=Color.white;
						double stock=0.0;
						/*
						String _stock=table.getValueAt(row, 4).toString();
						if (_stock!=null){
							if (_stock.compareTo("")!=0){
								stock=new Double(_stock.replaceAll(",", ""));
							}
						}
						//System.out.println("Stock>"+_stock);
						if (stock>0){
							//System.out.println("Stock>"+stock);
							color=Color.GREEN;
						}*/
						return color;
					}
					
					
				};
			}
		};
		
		this.getConstructor().addChild(C);
	}
	
	public void initializeLogic(JTextField tx){
		aplicacion.herramientas.java.msortableselector.logic._Logic logic = (aplicacion.herramientas.java.msortableselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		columna c = new columna();
		Filtro f = new Filtro();
		c = new columna();
		c.setNombre("p.idpedido");
		c.setAlias("idpedido");
		c.setColumnField(tx);
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("p.descripcion");
		c.setAlias("descripcion");
		c.setWidth(300);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("p.fecha_agenda");
		c.setAlias("fecha");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("p.cliente");
		c.setAlias("cliente");
		c.setWidth(100);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("p.cliente_descripcion");
		c.setAlias("nombre");
		c.setWidth(180);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("v.nombre");
		c.setAlias("vendedor");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);

		logic
				.addFromTable("b_pdc p left outer join v_ta_vendedores v on p.idvendedor=v.idvendedor ");
		f = new Filtro();
		f.setNombre("p.idpedido");
		f.setAlias("idpedido");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("p.descripcion");
		f.setAlias("descripcion");
		f.setWidth(350);
		f.setFocus(true);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("p.cliente");
		f.setAlias("idcliente");
		f.setWidth(150);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("p.cliente_descripcion");
		f.setAlias("nombre");
		f.setWidth(150);
		logic.addFilter(f);
		
		f = new Filtro();
		f.setNombre("v.nombre");
		f.setAlias("vendedor");
		f.setWidth(150);
		logic.addFilter(f);
		logic.setTop(200);
		logic.addOrder("p.idpedido desc");
		String restriction="";
		restriction+=" p.eliminar = 0 ";
		
		if (restriction.length()>0){
			logic.addRestriction(restriction);	
		}
		
		logic.setTitle("Buscador de Pedido de Cliente ");
		
		
		logic.init();
		
	}
	
	
}
