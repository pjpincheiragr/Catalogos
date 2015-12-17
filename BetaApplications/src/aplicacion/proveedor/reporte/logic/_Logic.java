package aplicacion.proveedor.reporte.logic;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import aplicacion.proveedor.reporte.gui._Frame;
import aplicacion.proveedor.reporte.logic._Data;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	private aplicacion.herramientas.java.buscadores.Fecha bFecha=null;
	public void BuscarFecha(JTextField tx){
		if (bFecha==null){
			bFecha=new aplicacion.herramientas.java.buscadores.Fecha(this.getConstructor());
		}
		
		
		bFecha.Buscar(tx);
	}
	public void cargarProveedor(String idproveedor){
		
		Object[][] results=data.getProveedorInformation(idproveedor);
		if (results!=null){
			if (results.length>0){
				String descripcion=(String) results[0][0];
				frame.get_txt_proveedor_descripcion().setText(descripcion);
				frame.get_txt_fecha_desde().requestFocusInWindow();
				
			}
		}
	}
	
	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
	public void initialize_proveedor(){
		proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
			public void cargar(String codigo){
				cargarProveedor(codigo);
			}
		};
		proveedor.setConstructor(this.getConstructor());
	}
	public void BuscarProveedor(JTextField tx){
		proveedor.Buscar(tx);
	}
	public void BuscarProveedor(){
		proveedor.Buscar(frame.get_txt_idproveedor());
	}
	public void buscarProveedor(JTextField tx){
		proveedor.buscar(tx);
	}
	public void evaluarProveedor(JTextField tx){
		proveedor.evaluate(tx);
	}

	public void focus(){
		frame.get_lst_tipo().requestFocusInWindow();
	}
	
	public void buscar_fecha_desde(){
		this.BuscarFecha(frame.get_txt_fecha_desde());
	}

	public void buscar_fecha_hasta(){
		this.BuscarFecha(frame.get_txt_fecha_hasta());
	}
	
	public void cargar(){
		reporte();
	}
	
	public void evaluate_fecha_desde(JTextField tx){
		frame.get_txt_fecha_hasta().requestFocusInWindow();
	}
	
	public void evaluate_fecha_hasta(JTextField tx){
		cargar();
	}
	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;
	public void reporte(){
		if (reporte!=null){
			 reporte.dispose();
		}
		String idproveedor=frame.get_txt_idproveedor().getText();
		String desde=frame.get_txt_fecha_desde().getText();
		String hasta=frame.get_txt_fecha_hasta().getText();
		
		String fecha_desde="01-01-1900";
		String fecha_hasta="01-01-2200";
		if (desde.compareTo("")==0){
			desde=fecha_desde;
		}
		if (hasta.compareTo("")==0){
			hasta=fecha_hasta;
		}
		reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param=new HashMap();
		param.put("cuenta",idproveedor);
		param.put("fecha_desde",desde);
		param.put("fecha_hasta",hasta);
		reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
		if (frame.get_lst_tipo().getSelectedIndex()==0){
			reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "proveedor_asientos.jasper");	
		}
		if (frame.get_lst_tipo().getSelectedIndex()==1){
			reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "proveedor_analitico.jasper");	
		}
		reporte.build(this.getConstructor());
		reporte.init();	
	}
	
	public void cargarTipoReportes(){
		frame.get_lst_tipo().removeAllItems();
		frame.get_lst_tipo().addItem("Cuenta Corriente");
		frame.get_lst_tipo().addItem("Analitico");
		
	}
	
	public void CargarProveedor(String idproveedor){
		frame.get_txt_idproveedor().setText(idproveedor);
		this.evaluarProveedor(frame.get_txt_idproveedor());
		frame.get_lst_tipo().requestFocusInWindow();
	}
	
	public void evaluate_tipo_reporte(JComboBox lst){
		frame.get_txt_idproveedor().requestFocusInWindow();
	}
	public void clean(){
		frame.get_txt_proveedor_descripcion().setText("");
		frame.get_txt_fecha_desde().setText("");
		frame.get_txt_fecha_hasta().setText("");
		frame.get_txt_idproveedor().setText("");
	}
}
