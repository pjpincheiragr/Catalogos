package aplicacion.inventario.reporteArticulo.logic;

import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import aplicacion.herramientas.java.*;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomCellEditor;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.gestion.caja.manejo.logic.DateRenderer;
import aplicacion.gestion.caja.manejo.logic.MoneyRenderer;
import aplicacion.inventario.reporteArticulo.gui._Frame;
import aplicacion.inventario.reporteArticulo.interfaces._Interface;

import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.interfaces.*;

public class _Logic extends Logic{
	private _Frame frame=null;
	private _Data data=null;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	
	public _Logic(){
		
	}
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	
	
	public void clean(){
		
		frame.get_txt_idproveedor().setText("");
		frame.get_txt_idarticulo_desde().setText("");
		frame.get_txt_idarticulo_hasta().setText("");
		frame.get_txt_linea().setText("");
		frame.get_txt_idproveedor_descripcion().setText("");
		
		this.setInitialDate();
	}
	
	public void cancelar(){
		if (preguntar("Confirmar","Cancela?")) {
			clean();
		}
	}
	
	
//public void getToday(){
//_Frame _frame=(_Frame) this._frame;
//_frame.get_txt_fecha().setText(
//			new Convertidor().getDateWithFormat("dd-MM-yyyy")
//			);	
//}
	
	
public Double getTotal(Object[][] aux){
		Double tmp=0.0;
		for (int i=0;i<aux.length;i++){
			double _tmp=0.0;
			try {
				_tmp= new Double((String) aux[i][4]);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tmp+=_tmp;
		}
		return tmp;
}
	


//public void cargar(){
//		_Data data=(_Data)this.getData();
//		String desde="01-01-1900";
//		String hasta="01-01-2900";
//		String _desde=frame.get_txt_fecha().getText();
//		String _hasta=frame.get_txt_fecha_hasta().getText();
//		if (_desde.compareTo("")!=0){
//			desde=_desde;
//		}
//		if (_hasta.compareTo("")!=0){
//			hasta=_hasta;
//		}
//		
//		
//}
	

//public void loadModos(){
//	frame.get_lst_tipo().removeItemListener(this.getConstructor().getItemListener());
//	frame.get_lst_tipo().removeAllItems();
//	frame.get_lst_tipo().addItem(_Interface._stock);
//	frame.get_lst_tipo().addItem(_Interface._stock_detallado);
//	frame.get_lst_tipo().addItem(_Interface._stock_resumido);
//	frame.get_lst_tipo().addItem(_Interface._stock_entrada_salida);
//	frame.get_lst_tipo().addItem(_Interface._stock_faltantes);
//	frame.get_lst_tipo().addItem(_Interface._stock_x_deposito);
//	frame.get_lst_tipo().addItemListener(this.getConstructor().getItemListener());
//}

public void init(){
		this.setInitialDate();
		
}
	
public void focus(){
	frame.get_txt_idproveedor().requestFocusInWindow();
}
public _Data getData(){
		return this.data;
	}
	




private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;

public void reporte(){

	
	 this.reporte_comun(); 
	 
	
	//if (frame.get_lst_tipo().getSelectedItem().toString().compareTo(_Interface._stock)==0){
		
//	}
//	if (frame.get_lst_tipo().getSelectedItem().toString().compareTo(_Interface._stock_detallado)==0){
//		this.reporte_detallado();
//	}
//	if (frame.get_lst_tipo().getSelectedItem().toString().compareTo(_Interface._stock_resumido)==0){
//		this.reporte_resumido();
//	}
//	if (frame.get_lst_tipo().getSelectedItem().toString().compareTo(_Interface._stock_faltantes)==0){
//		this.reporte_faltantes();
//	}
}

public void reportebase(){
	if (reporte!=null){
		 reporte.dispose();
	}
	
	String desde="01-01-1900";
	String hasta="01-01-2900";
//	String _desde=frame.get_txt_fecha().getText();
//	String _hasta=frame.get_txt_fecha_hasta().getText();
	String linea=frame.get_txt_linea().getText();
//	if (_desde.compareTo("")!=0){
//		desde=_desde;
//	}
//	if (_hasta.compareTo("")!=0){
//		hasta=_hasta;
//	}
	String idarticulo_desde=frame.get_txt_idarticulo_desde().getText();
	String idarticulo_hasta=frame.get_txt_idarticulo_hasta().getText();
	if (idarticulo_desde.compareTo("")==0){
		idarticulo_desde="001-000";
	}
	if (idarticulo_hasta.compareTo("")==0){
		idarticulo_hasta="999-zzz";
	}
	String idproveedor=frame.get_txt_idproveedor().getText();
	
	String where="s.anulado=0 ";
	String having="";
	
	boolean resumido=false;
	if (!resumido){
		if (desde.compareTo("")!=0 | hasta.compareTo("")!=0){
			if (where.length()>0){
				where+=" and ";
			}
			where+="s.fecha between '"+desde+"' and '"+hasta+"' ";
		}	
	}
	
	if (idarticulo_desde.compareTo("")!=0 & idarticulo_hasta.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" a.idarticulo between '"+idarticulo_desde+"' and '"+idarticulo_hasta+"' ";
	}else{
		if (idarticulo_desde.compareTo("")!=0){
			where+=" a.idarticulo >= '"+idarticulo_desde+"' ";	
		}else{
			if (idarticulo_hasta.compareTo("")!=0){
				where+=" a.idarticulo <= '"+idarticulo_desde+"' ";
			}else{
				
			}
		}
				
		
	}
	if (idproveedor.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" a.cuentaproveedor like '"+idproveedor+"' ";
	}
	
	if (linea.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" isnull(a.descripabrev,'') like '"+linea+"' ";
	}
		
	boolean movimientos=false;
	if (where.length()<=0 &!resumido){
			error("Para esta consulta se utilizara el repote resumido");
			resumido=true;
	}	
	if (where.compareTo("")==0){
		where+=" a.idarticulo is not null ";
	}
	
	
	reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
	HashMap param=new HashMap();
	String fecha=hasta;
	if (fecha.compareTo("")!=0){
		fecha=data.getSystemDate();
	}
	param.put("fecha", hasta);
	param.put("where_clause", where);
	System.out.println("WHERE>"+where);
	
	
	param.put("having_clause", having);
	reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
	reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
//	if (!chk_deposito){
//		if (resumido){
//			reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock_resumido.jasper");
//		}else{
//			if (movimientos){
//				reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock_movimientos.jasper");
//			}else{
				reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock.jasper");	
//			}
				
//		}	
//	}else{
//		if (resumido){
//			//reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock_resumido.jasper");
//		}else{
//			if (movimientos){
//				reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock_movimientos.jasper");
//			}else{
//				reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock.jasper");	
//			}	
//		}
//	}
	
	
	reporte.build(this.getConstructor());
	reporte.init();	
}

public void reporte_comun(){
	if (reporte!=null){
		 reporte.dispose();
	}
	
	String desde="01-01-1900";
	String hasta="01-01-2900";
//	String _desde=frame.get_txt_fecha().getText();
//	String _hasta=frame.get_txt_fecha_hasta().getText();
	String linea=frame.get_txt_linea().getText();
//	if (_desde.compareTo("")!=0){
//		desde=_desde;
//	}
//	if (_hasta.compareTo("")!=0){
//		hasta=_hasta;
//	}
	String idarticulo_desde=frame.get_txt_idarticulo_desde().getText();
	String idarticulo_hasta=frame.get_txt_idarticulo_hasta().getText();
	if (idarticulo_desde.compareTo("")==0){
		idarticulo_desde="001-000";
	}
	if (idarticulo_hasta.compareTo("")==0){
		idarticulo_hasta="999-zzz";
	}
	String idproveedor=frame.get_txt_idproveedor().getText();
	
	String where="";
		
	
	
	if (idarticulo_desde.compareTo("")!=0 & idarticulo_hasta.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" a.idarticulo between '"+idarticulo_desde+"' and '"+idarticulo_hasta+"' ";
	}else{
		if (idarticulo_desde.compareTo("")!=0){
			where+=" a.idarticulo >= '"+idarticulo_desde+"' ";	
		}else{
			if (idarticulo_hasta.compareTo("")!=0){
				where+=" a.idarticulo <= '"+idarticulo_desde+"' ";
			}else{
				
			}
		}
				
		
	}
	if (idproveedor.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" m.cuentaproveedor like '"+idproveedor+"' ";
	}
	
	if (linea.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" isnull(a.linea,'') like '"+linea+"' ";
	}
	
	if (where.compareTo("")==0){
		where+=" m.idarticulo is not null ";
	}
	
	String categoria="a.categoria_"+frame.get_lst_orden().getSelectedItem().toString();
	String order=" order by a.ranking_"+frame.get_lst_orden().getSelectedItem().toString();
	String categorias="";
	if (frame.get_chk_A().isSelected()){
		if (categorias.compareTo("")!=0){
			categorias+=" or ";
		}
		categorias+=" "+categoria+" like 'A' ";
	}
	if (frame.get_chk_B().isSelected()){
		if (categorias.compareTo("")!=0){
			categorias+=" or ";
		}
		categorias+=" "+categoria+" like 'B' ";
	}
	if (frame.get_chk_C().isSelected()){
		if (categorias.compareTo("")!=0){
			categorias+=" or ";
		}
		categorias+=" "+categoria+" like 'C' ";
	}
	if (frame.get_chk_D().isSelected()){
		if (categorias.compareTo("")!=0){
			categorias+=" or ";
		}
		categorias+=" "+categoria+" like 'D' ";
	}
	
	if (categorias.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" ("+categorias+") ";
	}
	if (where.compareTo("")!=0){
		where=" where "+where;
	}
	
	System.out.println(where);
	reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
	HashMap param=new HashMap();
	String fecha=data.getSystemDate();
	
	param.put("fecha", fecha);
	
	param.put("where_clause", where);
	
	
	param.put("order_clause", order);
	reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
	reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
	//reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock_resumido.jasper");
	//reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock_movimientos.jasper");
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_articulos_abc.jasper");	
	reporte.build(this.getConstructor());
	reporte.init();	
}

public void reporte_resumido(){
	if (reporte!=null){
		 reporte.dispose();
	}
	
	String desde="01-01-1900";
	String hasta="01-01-2900";
//	String _desde=frame.get_txt_fecha().getText();
//	String _hasta=frame.get_txt_fecha_hasta().getText();
	String linea=frame.get_txt_linea().getText();
//	if (_desde.compareTo("")!=0){
//		desde=_desde;
//	}
//	if (_hasta.compareTo("")!=0){
//		hasta=_hasta;
//	}
	String idarticulo_desde=frame.get_txt_idarticulo_desde().getText();
	String idarticulo_hasta=frame.get_txt_idarticulo_hasta().getText();
	if (idarticulo_desde.compareTo("")==0){
		idarticulo_desde="001-000";
	}
	if (idarticulo_hasta.compareTo("")==0){
		idarticulo_hasta="999-zzz";
	}
	String idproveedor=frame.get_txt_idproveedor().getText();
	String where="";
	String having="";
	
		if (desde.compareTo("")!=0 | hasta.compareTo("")!=0){
			if (where.length()>0){
				where+=" and ";
			}
			where+="s.fecha between '"+desde+"' and '"+hasta+"' ";
		}	
	
	
	if (idarticulo_desde.compareTo("")!=0 & idarticulo_hasta.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" a.idarticulo between '"+idarticulo_desde+"' and '"+idarticulo_hasta+"' ";
	}else{
		if (idarticulo_desde.compareTo("")!=0){
			where+=" a.idarticulo >= '"+idarticulo_desde+"' ";	
		}else{
			if (idarticulo_hasta.compareTo("")!=0){
				where+=" a.idarticulo <= '"+idarticulo_desde+"' ";
			}else{
				
			}
		}
				
		
	}
	if (idproveedor.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" a.cuentaproveedor like '"+idproveedor+"' ";
	}
	
	if (linea.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" isnull(a.descripabrev,'') like '"+linea+"' ";
	}

	
	if (where.compareTo("")==0){
		where+=" a.idarticulo is not null ";
	}
	
	
	reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
	HashMap param=new HashMap();
	String fecha=hasta;
	if (fecha.compareTo("")!=0){
		fecha=data.getSystemDate();
	}
	param.put("fecha", hasta);
	param.put("where_clause", where);
	System.out.println("WHERE>"+where);

	
	param.put("having_clause", having);
	reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
	reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock_resumido.jasper");
	reporte.build(this.getConstructor());
	reporte.init();	
}

public void reporte_detallado(){
	if (reporte!=null){
		 reporte.dispose();
	}
	
	String desde="01-01-1900";
	String hasta="01-01-2900";
//	String _desde=frame.get_txt_fecha().getText();
//	String _hasta=frame.get_txt_fecha_hasta().getText();
	String linea=frame.get_txt_linea().getText();
//	if (_desde.compareTo("")!=0){
//		desde=_desde;
//	}
//	if (_hasta.compareTo("")!=0){
//		hasta=_hasta;
//	}
	String idarticulo_desde=frame.get_txt_idarticulo_desde().getText();
	String idarticulo_hasta=frame.get_txt_idarticulo_hasta().getText();
	if (idarticulo_desde.compareTo("")==0){
		idarticulo_desde="001-000";
	}
	if (idarticulo_hasta.compareTo("")==0){
		idarticulo_hasta="999-zzz";
	}
	String idproveedor=frame.get_txt_idproveedor().getText();
	String where="";
	String having="";
	
		if (desde.compareTo("")!=0 | hasta.compareTo("")!=0){
			if (where.length()>0){
				where+=" and ";
			}
			where+="s.fecha between '"+desde+"' and '"+hasta+"' ";
		}	
	
	
	if (idarticulo_desde.compareTo("")!=0 & idarticulo_hasta.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" a.idarticulo between '"+idarticulo_desde+"' and '"+idarticulo_hasta+"' ";
	}else{
		if (idarticulo_desde.compareTo("")!=0){
			where+=" a.idarticulo >= '"+idarticulo_desde+"' ";	
		}else{
			if (idarticulo_hasta.compareTo("")!=0){
				where+=" a.idarticulo <= '"+idarticulo_desde+"' ";
			}else{
				
			}
		}
				
		
	}
	if (idproveedor.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" a.cuentaproveedor like '"+idproveedor+"' ";
	}
	
	if (linea.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" isnull(a.descripabrev,'') like '"+linea+"' ";
	}
	
	
	if (where.compareTo("")==0){
		where+=" a.idarticulo is not null ";
	}
	
	
	reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
	HashMap param=new HashMap();
	String fecha=hasta;
	if (fecha.compareTo("")!=0){
		fecha=data.getSystemDate();
	}
	param.put("fecha", hasta);
	param.put("where_clause", where);
	System.out.println("WHERE>"+where);

	
	param.put("having_clause", having);
	reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
	reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
	//reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock_resumido.jasper");
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock_deposito.jasper");
	//reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_stock.jasper");	
	reporte.build(this.getConstructor());
	reporte.init();	
}

public void reporte_faltantes(){
	if (reporte!=null){
		 reporte.dispose();
	}
	String desde="01-01-1900";
	String hasta="01-01-2900";
//	String _desde=frame.get_txt_fecha().getText();
//	String _hasta=frame.get_txt_fecha_hasta().getText();
	String linea=frame.get_txt_linea().getText();
//	if (_desde.compareTo("")!=0){
//		desde=_desde;
//	}
//	if (_hasta.compareTo("")!=0){
//		hasta=_hasta;
//	}
	String idarticulo_desde=frame.get_txt_idarticulo_desde().getText();
	String idarticulo_hasta=frame.get_txt_idarticulo_hasta().getText();
	String idproveedor=frame.get_txt_idproveedor().getText();
	String where="";
	String having="";
	
		if (desde.compareTo("")!=0 | hasta.compareTo("")!=0){
			if (where.length()>0){
				where+=" and ";
			}
			where+="b.fecha between '"+desde+"' and '"+hasta+"' ";
		}	
	
	
	if (idarticulo_desde.compareTo("")!=0 & idarticulo_hasta.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" b.idarticulo between '"+idarticulo_desde+"' and '"+idarticulo_hasta+"' ";
	}else{
		if (idarticulo_desde.compareTo("")!=0){
			where+=" b.idarticulo >= '"+idarticulo_desde+"' ";	
		}else{
			if (idarticulo_hasta.compareTo("")!=0){
				where+=" b.idarticulo <= '"+idarticulo_desde+"' ";
			}else{
				
			}
		}
				
		
	}
	if (idproveedor.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" a.cuentaproveedor like '"+idproveedor+"' ";
	}
	
	if (linea.compareTo("")!=0){
		if (where.length()>0){
			where+=" and ";
		}
		where+=" a.descripabrev like '"+linea+"' ";
	}
	
	
	
	reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
	HashMap param=new HashMap();
	
	param.put("fecha", hasta);
	param.put("where_clause", where);
	param.put("having_clause", "");
	
	reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
	reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
	reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "reporte_faltantes.jasper");
	reporte.build(this.getConstructor());
	reporte.init();	
}


public void setInitialDate(){
	String aux=data.getSystemDate();
//	frame.get_txt_fecha().setText(aux);
}

//public void transfer_fecha_focus(JTextField tx){
//	if (tx.getName()==_Interface._txt_fecha_desde){
//		frame.get_txt_fecha_hasta().requestFocusInWindow();
//	}
//	if (tx.getName()==_Interface._txt_fecha_hasta){
//		frame.get_txt_idproveedor().requestFocusInWindow();
//	}
//}

	private aplicacion.herramientas.java.evaluadores.Fecha Fecha=null;
//	public void initialize_Fecha(){
//		Fecha=new aplicacion.herramientas.java.evaluadores.Fecha(){
//			public void cargar(JTextField tx){
//				transfer_fecha_focus(tx);
//			}
//		};
//		Fecha.setConstructor(this.getConstructor());
//	}
	public void BuscarFecha(JTextField tx){
		Fecha.Buscar(tx);
	}
//	public void BuscarFecha(){
//		Fecha.Buscar(frame.get_txt_fecha());
//	}
//	public void BuscarFechaHasta(){
//		Fecha.Buscar(frame.get_txt_fecha_hasta());
//	}
	
	public void evaluarFecha(JTextField tx){
		Fecha.evaluate(tx);
	}
	
	public void evaluar_proveedor(JTextField tx){
		String idproveedor=tx.getText();
		if (idproveedor.startsWith("21101") & idproveedor.compareTo("21101")!=0){
			Object[][] results=data.getProveedor(idproveedor);
				if (results!=null){
					if (results.length>0){
						String desc=results[0][1].toString();
						String pol=results[0][2].toString();
						String poldesc=results[0][3].toString();
						String linea=results[0][6].toString();
						frame.get_txt_idproveedor_descripcion().setText(desc);
						frame.get_txt_linea().requestFocusInWindow();
					}else {
						aviso("No se encontro Proveedor "+idproveedor);
					}
				}else {
					aviso("No se encontro Proveedor "+idproveedor);
				}
		}else {
				this.buscarProveedor(tx);
			
				
		}
	}
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
	public void BuscarProveedor(JTextField tx) {
		if (bProveedor!=null){
			bProveedor.dispose();
		}
		bProveedor=new aplicacion.herramientas.java.buscadores.Proveedor(this.getConstructor());
		bProveedor.Buscar(tx);
		
	}
	
	public void BuscarProveedor() {
		this.buscarProveedor(frame.get_txt_idproveedor());
	}
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	public void buscarProveedor(JTextField tx) {
		if (vProveedor!=null){
			vProveedor.close();
		}
		vProveedor=new aplicacion.herramientas.java.visualizadores.Proveedor(this.getConstructor());
		int n = vProveedor.Buscar(tx);
		if (n == 0) {
				aviso("no hay Proveedors con ese codigo");
		}
	}

	public void evaluar_linea(JTextField tx){
		String linea=tx.getText();
		if (linea.compareTo("")!=0){
			if (data.check_linea(linea)){
				cargar_prefijos(linea);
			}else{
				this.buscarLinea(tx);
			}
		}else{
			this.buscarLinea(tx);
		}
	}
	
	
	private aplicacion.herramientas.java.visualizadores.Linea vLinea=null;
	public void buscarLinea(JTextField tx) {
		if (vLinea!=null){
			vLinea.close();
		}
		vLinea=new aplicacion.herramientas.java.visualizadores.Linea(this.getConstructor());
		vLinea.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vLinea.buscarLinea(tx);
		if (n == 0) {
				aviso("no hay Lineas con ese codigo");
		}
	}
	private aplicacion.herramientas.java.buscadores.Linea bLinea;
	public void BuscarLinea(JTextField tx){
		if (bLinea==null){
			bLinea=new aplicacion.herramientas.java.buscadores.Linea(this.getConstructor());
		}
	
		bLinea.setIdproveedor(frame.get_txt_idproveedor());
		bLinea.BuscarLinea(tx);	
	}
	

	public void BuscarLinea() {
		this.BuscarLinea(frame.get_txt_linea());
	}

	
	
	

	
	public void cargar_prefijos(String linea){
		Object[][] results=data.getLinePrefix(linea);
		if (results!=null){
			if (results.length>0){
				String prefix=(String) results[0][0];
				frame.get_txt_idarticulo_desde().setText(prefix+"-000");
				frame.get_txt_idarticulo_hasta().setText(prefix+"-zzz");
				frame.get_txt_idarticulo_hasta().requestFocusInWindow();
				}
		}
	}
	
	private aplicacion.herramientas.java.evaluadores.Deposito Deposito = null;

	public void initialize_Deposito() {
		
		Deposito = new aplicacion.herramientas.java.evaluadores.Deposito() {
			public void cargar(JTextField tx) {
				
				String codigo=tx.getText();
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				
			}
		};
		Deposito.setConstructor(this.getConstructor());
	}

	public void BuscarDeposito(JTextField tx) {
		Deposito.Buscar(tx);
	}

	public void buscarDeposito(JTextField tx) {
		Deposito.buscar(tx);
	}

	public void evaluarDeposito(JTextField tx) {
		tx.setText(tx.getText().replaceAll(" ", ""));
		Deposito.evaluate(tx);
	}
	
	public void BuscarArticuloDesde() {
		this.BuscarArticulo(frame.get_txt_idarticulo_desde());
	}

	public void BuscarArticuloHasta() {
		this.BuscarArticulo(frame.get_txt_idarticulo_hasta());
	}


	private aplicacion.herramientas.java.buscadores.Articulo bArticulo=null;
    
	public void BuscarArticulo(JTextField ext) {
		 if (bArticulo==null){
			 bArticulo=new aplicacion.herramientas.java.buscadores.Articulo(this.getConstructor());
		 }
		 
		 bArticulo.Buscar(ext);
	}

	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo=null;
	public void buscarArticulo(JTextField tx) {
		if (vArticulo!=null){
			vArticulo.close();
		}
		vArticulo=new aplicacion.herramientas.java.visualizadores.Articulo(this.getConstructor());
		vArticulo.setIdproveedor(frame.get_txt_idproveedor().getText());
		int n = vArticulo.Buscar(tx);
		if (n == 0) {
			vArticulo.close();
			aviso("no hay articulos con ese codigo");
			
		}
	}

	public void evaluar_idarticulo_desde(JTextField tx) {
		String idarticulo = "";
		idarticulo = tx.getText();
		if (idarticulo.compareTo("") == 0) {
			aviso("Ingrese un Articulo. Busqueda F5");
		} else {
			if (idarticulo.length() > 4) {
				if (idarticulo.substring(3, 4).compareTo("-") == 0) {
					frame.get_txt_idarticulo_hasta().requestFocusInWindow();	
				} else {
					this.buscarArticulo(tx);
				}
			} else {
				this.buscarArticulo(tx);
			}
		}
	}
	
	public void evaluar_idarticulo_hasta(JTextField tx) {
		String idarticulo = "";
		idarticulo = tx.getText();
		if (idarticulo.compareTo("") == 0) {
			aviso("Ingrese un Articulo. Busqueda F5");
		} else {
			if (idarticulo.length() > 4) {
				if (idarticulo.substring(3, 4).compareTo("-") == 0) {
					frame.get_lst_orden().requestFocusInWindow();	
				} else {
					this.buscarArticulo(tx);
				}
			} else {
				this.buscarArticulo(tx);
			}
		}
	}

	public void loadModos(){
	frame.get_lst_orden().removeItemListener(this.getConstructor().getItemListener());
	frame.get_lst_orden().removeAllItems();
	frame.get_lst_orden().addItem(_Interface._orden_pickups);
	frame.get_lst_orden().addItem(_Interface._orden_rotacion);
	frame.get_lst_orden().addItem(_Interface._orden_volumen);
	frame.get_lst_orden().addItemListener(this.getConstructor().getItemListener());
	}
	
	
	
}