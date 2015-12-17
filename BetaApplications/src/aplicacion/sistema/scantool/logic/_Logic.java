package aplicacion.sistema.scantool.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JTextField;




import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.proveedor.archivo.interfaces._Parametros;
import aplicacion.sistema.scantool.gui._Frame;
import aplicacion.sistema.scantool.interfaces.*;
import aplicacion.sistema.scantool.logic._Data;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.XML;
public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private JTextField tx=null;
	
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.sistema.autorizacion.constructor._Constructor aplicaciones = null;
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	
	public void aplicaciones(){
		if (aplicaciones!=null){
			aplicaciones.dispose();
		}
		String iduser=tx.getText();
		if (data.check_user(iduser)){
			aplicaciones = new aplicacion.sistema.autorizacion.constructor._Constructor();
			aplicaciones.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
			aplicaciones.setParameter(aplicacion.sistema.autorizacion.interfaces._parametros._iduser, iduser);
			aplicaciones.build(this.getConstructor());
			aplicaciones.init();	
		}else{
			error("debe cargar un usuario valido");
		}
	}
	
	
	
	
	public void clean(){
	tx.setText("");
	}
	
	
	
	

	
	
	
	
	
	public void evaluate_codigo(JTextField tx){
		String codigo=tx.getText();
		this.evaluate_codigo(codigo);
		tx.setText("");
	}
	
	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;
	public void goMa_Articulos(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
		articulo.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme()
				);
		articulo.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler());
		articulo.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		articulo
				.setParameter(
						aplicacion.inventario.articulo.interfaces._parametros.idarticulo,
						idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}
	
	public void evaluate_codigo(String codigo){
		
		
		boolean ok=false;
		if (codigo.length()==40){
			ok=this.evaluate_cai(codigo);
			
		}
		if (!ok){
			if (codigo.length()>=5){
				String scan=codigo.substring(3, 4);

				if (scan.compareTo("-")==0){
					if (data.existeArticulo(codigo)){
						ok=true;
						int n=preguntar("Confirme","Seleccione su Opcion",new String[]{"Maestro del Articulo","Pedido Nuevo"},"Maestro del Articulo");
						if (n==0){
							this.goMa_Articulos(codigo);
						}
						if (n==1){
							this.crear_pedido(codigo);
						}	
					}
					
				}
			}			
		}

		if (!ok){
			int n=0;
			try {
				n=new Integer(codigo);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			if (n>0){
				if (codigo.length()==8){
					ok=this.evaluate_beta(codigo);
				}
				if (!ok){
					if (codigo.length()==9){
						ok=evaluar_cuenta(codigo);
					}		
				}
				
			}
			
		}
		if (!ok){
			ok=this.evaluate_alfanumerico(codigo);
		}
		if (!ok){
			error("Codigo no Identificado. Verifique barra de idiomas en Español");
			
		}
		
		
	}
	public boolean evaluar_cuenta(String codigo){
		boolean ok=false;
		if (codigo.startsWith("21101")){
			if (data.existeProveedor(codigo)){
				int n=this.preguntar("Confirme", "Seleccione su Opcion", new String[]{"Maestro de Proveedor"}, "Maestro de Proveedor");
				if (n>=0){
					this.editar_proveedor(codigo);
					ok=true;	
				}
				
			}
		}
		if (!ok){
			if (codigo.startsWith("11201")){
				if (data.existeCliente(codigo)){
					int n=this.preguntar("Confirme", "Seleccione su Opcion", new String[]{"Maestro de Cliente"}, "Maestro de Cliente");
					if (n>=0){
						ok=true;
						this.editar_cliente(codigo);	
					}
					
				}
			}
		}
		return ok;
	}
	public void editar_pedido(String idpedido){
		aplicacion.ventas.pedido.constructor._Constructor
		pedido=new aplicacion.ventas.pedido.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		pedido.setParameter(aplicacion.ventas.pedido.interfaces._parametros._idpedido, idpedido);
		pedido.build(this.getConstructor());
		pedido.init();
		this.getConstructor().addChild(pedido);
	}
	
	public boolean evaluate_cai(String cai){
		boolean ok=false;
		if (cai.length()==40){
			String cuit=cai.substring(0,11);
			String tc=cai.substring(11,13);
			String sucursal=cai.substring(13,17);
			String nro=cai.substring(17,31);
			boolean es=false;
			int year=0;
			try{
				year=new Integer(cai.substring(35,39));
			}catch(Exception e){
				
			}
			es=(year>2000);
			String fecha="";
				if (es){
					fecha=cai.substring(31,33)+"-";
					fecha+=cai.substring(33,35)+"-";
					fecha+=cai.substring(35,39);
							
				}else {
					fecha=cai.substring(31,35)+"-";
					fecha+=cai.substring(35,37)+"-";
					fecha+=cai.substring(37,39);
					fecha=new Convertidor().ConvertDate("dd-MM-yyyy", "yyyy-MM-dd", fecha);
				}
			
					String verif=cai.substring(39,40);
					String idproveedor=this.getProveedor(cuit);
					if (idproveedor.compareTo("")!=0){
						int n=this.preguntar("Confirme", "Seleccione su Opcion", new String[]{"Maestro de Proveedor"}, "Maestro de Proveedor");
						if (n>=0){
							ok=true;
							
							this.editar_proveedor(idproveedor);	
						}
						
					}else{
	
					}
	
			
		}
		return ok;
	}
	aplicacion.proveedor.archivo.constructor._Constructor maestro_proveedor = null;

	public void editar_proveedor(String idproveedor) {
		
		if (maestro_proveedor != null) {
			maestro_proveedor.dispose();
		}
		maestro_proveedor = new aplicacion.proveedor.archivo.constructor._Constructor();
		maestro_proveedor.setParameter(_parametros.connector, data
				.getConnectionHandler());
		maestro_proveedor.setParameter(_parametros.iduser, this
				.getConstructor().getIduser());
		maestro_proveedor.setParameter(_parametros.LookAndFeel, this
				.getConstructor().getLookAndFeelTheme());
		maestro_proveedor.setParameter(aplicacion.proveedor.archivo.interfaces._Parametros.idproveedor, idproveedor);
		maestro_proveedor.build(this.getConstructor());
		maestro_proveedor.init();

	}
	
	aplicacion.cliente.archivo.constructor._Constructor maestro_cliente = null;

	public void editar_cliente(String idcliente) {
		
		if (maestro_cliente != null) {
			maestro_cliente.dispose();
		}
		maestro_cliente = new aplicacion.cliente.archivo.constructor._Constructor();
		maestro_cliente.setParameter(_parametros.connector, data
				.getConnectionHandler());
		maestro_cliente.setParameter(_parametros.iduser, this
				.getConstructor().getIduser());
		maestro_cliente.setParameter(_parametros.LookAndFeel, this
				.getConstructor().getLookAndFeelTheme());
		maestro_cliente.setParameter(aplicacion.cliente.archivo.interfaces._Parametros.idcliente, idcliente);
		maestro_cliente.build(this.getConstructor());
		maestro_cliente.init();

	}

	public String getProveedor(String cuit){
		String proveedor="";
		Object[][] results=data.getProveedorCuit(cuit);
		if (results!=null){
			if (results.length>0){
				proveedor=(String) results[0][0];
			}
		}
		return proveedor;
	}
	public void editar_pedido_pep(String idpedido){
		aplicacion.compras.pedidoe.constructor._Constructor
		pedido=new aplicacion.compras.pedidoe.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		pedido.setParameter(aplicacion.compras.pedidoe.interfaces._parametros._idpedido, idpedido);
		pedido.build(this.getConstructor());
		pedido.init();
	}
	
	public void editar_pedido_pdp(String idpedido){
		aplicacion.compras.carga.pedido.constructor._Constructor
		pedido=new aplicacion.compras.carga.pedido.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler());
		pedido.setParameter(_parametros.iduser, this.getConstructor().getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor().getLookAndFeelTheme());
		pedido.setParameter(aplicacion.compras.pedidoe.interfaces._parametros._idpedido, idpedido);
		pedido.build(this.getConstructor());
		pedido.init();
	}
	public boolean evaluate_beta(String codigo){
		boolean ok=false;
		List<String> opciones=new ArrayList<String>();
		boolean pdc=data.existePedidoCliente(codigo);
		boolean pdp=data.existePedidoProveedor(codigo);
		boolean pep=data.existePedidoEspecialProveedor(codigo);
		if (pdc){
			opciones.add("Pedido de Cliente");	
		}
		if (pdp){
			opciones.add("Pedido a Proveedor");	
		}
		if (pep){
			opciones.add("Pedido Especial a Proveedor");	
		}
		if (opciones.size()>0){
			String[] options=new String[opciones.size()];
			for (int i=0;i<opciones.size();i++){
				options[i]=opciones.get(i);
			}
			int n=preguntar("Confirme","Seleccione su Opcion",options,options[0]);
			if (n>=0){
				ok=true;
				
				if (n==0){
					if (pdc){
						this.editar_pedido(codigo);
					}else{
						if (pdp){
							this.editar_pedido_pdp(codigo);	
						}else{
							this.editar_pedido_pep(codigo);
						}
						
					}
				}else{
					if (pdc){
						if (pdp){
							this.editar_pedido_pdp(codigo);	
						}else{
							this.editar_pedido_pep(codigo);
						}
					}else{
						this.editar_pedido_pep(codigo);
					}
				}	
			}
			
		}
		return ok;
		
	}
	
	aplicacion.ventas.pedido.constructor._Constructor pedido=null;
	public void crear_pedido(String idarticulo){
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
		logic.agregar(idarticulo);
	}
	
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo=null;
	public boolean buscarArticulo(JTextField tx) {
		boolean ok=false;
		if (vArticulo != null) {
			vArticulo.close();
		}
		vArticulo = new aplicacion.herramientas.java.visualizadores.Articulo(
				this.getConstructor());
		
		int n = vArticulo.Buscar(tx);
		if (n == 0) {
			vArticulo.close();
			ok=false;
		}else{
			ok=true;
		}
		return ok;
	}
	
	
	private aplicacion.herramientas.java.visualizadores.Cliente vCliente=null;
	public boolean buscarCliente(JTextField tx) {
		boolean ok=false;
		if (vCliente != null) {
			vCliente.close();
		}
		vCliente = new aplicacion.herramientas.java.visualizadores.Cliente(
				this.getConstructor());
		
		int n = vCliente.Buscar(tx);
		if (n == 0) {
			vCliente.close();
			ok=false;
		}else{
			ok=true;
		}
		return ok;
	}
	
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	public boolean buscarProveedor(JTextField tx) {
		boolean ok=false;
		if (vProveedor != null) {
			vProveedor.close();
		}
		vProveedor = new aplicacion.herramientas.java.visualizadores.Proveedor(
				this.getConstructor());
		
		int n = vProveedor.Buscar(tx);
		if (n == 0) {
			vProveedor.close();
			ok=false;
		}else{
			ok=true;
		}
		return ok;
	}
	
	private boolean solo_letras(String codigo){
		int i=0;
		boolean letras=true;
		while (i<codigo.length() & letras){
			if (codigo.substring(i, i+1).compareTo(" ")!=0){
				char c=codigo.charAt(i);
				letras=Character.isLetter(c);	
			}
			if (letras){
				i++;
			}
		}
		return letras;
	}
	
	
	
	public boolean evaluate_alfanumerico(String codigo){
		boolean ok=false;
		if (this.solo_letras(codigo)){
			ok=this.buscarCliente(tx);
			if (!ok){
				ok=this.buscarProveedor(tx);
			}
		}else{
			if (codigo.contains("+")){
				ok=this.buscarArticulo(tx);
			}
			if (codigo.startsWith("PDC")){
				codigo=codigo.replace("PDC", "");
				ok=this.evaluate_beta(codigo);
			}
			if (codigo.startsWith("PDP")){
				codigo=codigo.replace("PDP", "");
				ok=this.evaluate_beta(codigo);
			}
			if (codigo.startsWith("PEP")){
				codigo=codigo.replace("PEP", "");
				ok=this.evaluate_beta(codigo);
			}
			
			if (!ok){
				ok=this.buscarArticulo(tx);
			}	
		}
		
		return ok;
	}

	public JTextField getTx() {
		return tx;
	}

	public void setTx(JTextField tx) {
		this.tx = tx;
	}
	
	}
