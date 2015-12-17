package aplicacion.catalogo.catalogo.logic;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;

import aplicacion.herramientas.java.table.*;
import aplicacion.catalogo.catalogo.constructor._Constructor;
import aplicacion.modelo.logic.Logic;
import aplicacion.catalogo.catalogo.gui.*;
import aplicacion.catalogo.catalogo.logic.*;
import aplicacion.modelo.logic.*;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import stock1.PedidoNode;

public class _Logic extends Logic {
	private boolean[] block=null;
	private JComboBox[] Level=null;
	private LinkedList codes=null;  //  @jve:decl-index=0:
	private DefaultTreeModel modelo ;  
	private _Frame frame=null;
	private _Data data=null;
	private String[][] repuestos = null;
	private String[][] aplicacion = null;
	private int selected_level=-1;
	private Object[][] aux=null;
	public void setData(Data data){
		this.data=(_Data) data;
		super.setData(data);
	}
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	
	
	private void create_table(Object[][] results) {
		_Constructor constructor=(_Constructor) this.getConstructor();
		//{"","idArticulo","Descripcion","Publico","Linea","Lleva","Stk","Clasificacion","Desde","Hasta"};
		//{50,120,300,100,120,60,60,100,80,80};
		
		CustomTable table = new CustomTable();

		Column col = new Column();

		col = new Column();
		col.setName("");
		col.setWidth(50);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);
		
		col = new Column();
		col.setName("idArticulo");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Descripcion");
		col.setWidth(300);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("Importe");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Linea");
		col.setWidth(120);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("LLeva");
		col.setWidth(60);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Stock");
		col.setWidth(60);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Clasificacion");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Desde");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Hasta");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);
		
		//table.setSortable(false);
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		//table.setHeaderFont(fuente);
		//table.setFont(fuente);
		table.build();
		table.fillData();
		
		JTable _table=table.getTable();
		_table.setDropMode(DropMode.INSERT_ROWS);
		_table.setTransferHandler(new TransferHandler() {

            public boolean canImport(TransferSupport support) {
                // for the demo, we'll only support drops (not clipboard paste)
                if (!support.isDrop()) {
                    return false;
                }

                // we only import Strings
                if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    return false;
                }

                return true;
            }

            public boolean importData(TransferSupport support) {
                // if we can't handle the import, say so
                if (!canImport(support)) {
                    return false;
                }

                // fetch the drop location
                JTable.DropLocation dl = (JTable.DropLocation)support.getDropLocation();

                

                // fetch the data and bail if this fails
                String data;
                try {
                    data = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                } catch (UnsupportedFlavorException e) {
                    return false;
                } catch (IOException e) {
                    return false;
                }

                System.out.println("Drop Articles?"+data.split(","));
                String[] rowData = data.split(",");
                
                processDrop(rowData);
                // end demo stuff

                return true;
            }
        });

		frame.setJTable(_table);
	}
	
	public Object[][] processData(String[] data){
		LinkedList rows=new LinkedList();
		List<String> row=new ArrayList<String>();
		
		for (int i=0;i<data.length;i++){
			System.out.println("Data <"+i+"> "+data[i]);
			int chrx=9;//este nueve es un caracter de tabulacion
			String linea=data[i];
			String tmp="";
			for (int j=0;j<linea.length();j++){
	       	   if (linea.charAt(j)==chrx){
	       		   row.add(tmp);
	        		tmp="";
	           }else{
	       		tmp+=linea.charAt(j);
	       	   }
			}
			rows.add(row);
		}
		int cols=((List<String>)rows.get(0)).size();
		Object[][] aux=new Object[rows.size()][cols];
		for (int i=0;i<rows.size();i++){
			List<String> list=(List<String>) rows.get(i);
			for (int j=0;j<rows.size();i++){
				aux[i][j]=list.get(j);
			}
		}
		return aux;
	}
	
	public List<String> getArticles(String[] data){
		List<String> row=new ArrayList<String>();
		
		for (int i=0;i<data.length;i++){
			System.out.println("Data <"+i+"> "+data[i]);
			int chrx=9;//este nueve es un caracter de tabulacion
			String linea=data[i];
			String tmp="";
			for (int j=0;j<linea.length();j++){
	       	   if (linea.charAt(j)==chrx){
	       			if(this.data.existeArticulo(tmp)){
	       				System.out.println("sub(3,4)"+tmp.substring(3,4));
	       				System.out.println("sub(4,5)"+tmp.substring(3,4));
		       			row.add(tmp);   
		       		   }   
	       		   tmp="";
	           }else{
	       		tmp+=linea.charAt(j);
	       	   }
			}
		}
		return row;
	}
	
	public void processDrop(String[] data){
		JTable table=frame.getJTable();
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		List<String> lista=this.getArticles(data);
		for (int i=0;i<lista.size();i++){
			System.out.println(">"+lista.get(i));
			this.addArticle(lista.get(i));
			
		}
		
		
		open();
        
	}
	
	public void eliminar(){
		if (confirmar("Confirme para eliminar",2)){
			for (int i=0;i<frame.getJTable().getRowCount();i++){
				boolean b=(Boolean)frame.getJTable().getValueAt(i, 0);
				if (b){
					String idarticulo=frame.getJTable().getValueAt(i, 1).toString();
					String clasificacion=frame.getJTable().getValueAt(i, 7).toString();
					String aplicacion=this.getSecuence();
					String q=this.data.getDeleteClasificacion(idarticulo, clasificacion, aplicacion);
					data.clearBatch();
					data.addBatch(q);
					data.executeBatch();
				}
			}
		}
		open();
	}
	public void load_aplicaciones(){
		String q="select id,clasificacion,padre from b_aplicacion_repuestos order by padre,clasificacion ";
		Object[][] results=this.data.getResults(q);
		if (results!=null){
			if (results.length>0){
				aplicacion=new String[results.length][3];
				for (int i=0;i<results.length;i++){
					for (int j=0;j<results[0].length;j++){
					aplicacion[i][j]=(String)results[i][j];
					}
				}
			}
		}
	}
	
	public void load_repuestos(){
		String q="select id,clasificacion,padre from b_clasificacion order by padre,clasificacion ";
		Object[][] results=this.data.getResults(q);
		if (results!=null){
			if (results.length>0){
				repuestos=new String[results.length][3];
				for (int i=0;i<results.length;i++){
					for (int j=0;j<results[0].length;j++){
					repuestos[i][j]=(String)results[i][j];
					}
				}
			}
		}
	}
	
public PedidoNode make_master_abuelo(){
		
		PedidoNode abuelo = new PedidoNode("Repuestos ","0","Usuarios");
		modelo = new DefaultTreeModel(abuelo);
		this.load_clases(abuelo);
		return abuelo;
	}

public boolean utilizable(String code){
	boolean found=false;
	int i=0;
	while (!found & i<codes.size()){
		String cx=(String)codes.get(i);
		found=code.compareTo(cx)==0;
		if (!found){
			i++;
		}
	}
	return found;
}
public void load_clases(PedidoNode abuelo){
	String q="";
	q=q+" select clasificacion,id "; 
	q=q+" from b_clasificacion  ";
	if (abuelo!=null){
		q=q+" where padre like '"+abuelo.getValue()+"'  ";	
	}
	q=q+" order by clasificacion ";
	//System.out.println("LoadClases(abuelo:"+abuelo.getValue()+"):"+q);
	Object[][] results=this.data.getResults(q);
	if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					PedidoNode padre = new PedidoNode(""+(String)results[i][0]+"",(String)results[i][1],"MSN");
					if (utilizable(padre.getValue())){
						//System.out.println("La clase "+padre.getValue()+" es utilizable");
						this.load_clases(padre);
						modelo.insertNodeInto(padre, abuelo, abuelo.getChildCount());	
					}
				}
			}
		}
	}

class treeMouseListener implements MouseListener {
	 public void mouseClicked(MouseEvent e) {
		 
			 
			if (e.getButton()==3){
				
			 }else {
			
					 if (e.getClickCount() == 1 ){
						 //goTaskRapido(node.getValue().toString());
						 seleccionar_clasificacion();
				 	 
				 }
				
			 }
			
              
     }
	 
	 public void seleccionar_clasificacion(){
			
			//int i=node.getChildCount();
			//String clas=JOptionPane.showInputDialog("Ingrese nuevo nombre Clasificacion "+node.getValue());
			
			open();
			
		}
     public void mouseEntered(MouseEvent e) {
    	try{
        JTree tree=(JTree)e.getSource();
        int x = e.getX();
 		int y = e.getY();
		int row = tree.getRowForLocation(x, y);
		TreePath path = tree.getPathForRow(row);
		//System.out.println(path);
		if (path != null) {
		PedidoNode node = (PedidoNode)path.getLastPathComponent();
		if (node.getTipo().compareTo("Pedido")==0){
			 System.out.println("show tooltip Pedido de: "+node.getValue());
		 }
		if (node.getTipo().compareTo("Cuenta")==0){
			System.out.println("show tooltip Cuenta de: "+node.getValue());
		 }
		 
		}
       }catch(Exception ex){
         System.out.println(ex.getMessage());
        }                
     }

     public void mouseExited(MouseEvent e) {
        // System.out.println("Tray Icon - Mouse exited!");                 
     }

     public void mousePressed(MouseEvent e) {
       //  System.out.println("Tray Icon - Mouse pressed!");                 
     }

     public void mouseReleased(MouseEvent e) {
    	 
     }
};

	public PedidoNode load_node(String id){
		PedidoNode padre=null;
		String q="";
		q=q+" select clasificacion,id "; 
		q=q+" from b_clasificacion  ";
		q=q+" where id like '"+id+"'  ";
		
		//System.out.println(q);
		Object[][] results=this.data.getResults(q);
		if (results!=null){
				if (results.length>0){
						padre = new PedidoNode(""+(String)results[0][0]+"",(String)results[0][1],"");
				}
		}
		if (padre!=null){
			System.out.println("LoadNode OK "+padre.getValue());
		}
		return padre;
	}
	public void load_variables(){
		this.load_repuestos();
		this.load_aplicaciones();
	}
	
	public void init(){
		int qty=6;
		block=new boolean[qty];
		for (int i=0;i<4;i++){
			block[i]=false;
		}
		Level=new JComboBox[qty];
		for (int i=0;i<qty;i++){
			final int levx=i;
			Level[i]= new JComboBox();
			Level[i].setBounds(new Rectangle(14, 13+i*22, 185, 18));
			Level[i].addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (!block[levx]){
						load_newOptions(levx+1);
						create_pedidos();
					}
					 
				}
			});
			Level[i].setVisible(false);
			frame.getAplicacion().add(Level[i], null);		
		}
		Level[0].setVisible(true);
	}
	public void load_Options(){
		String q="select id,clasificacion from b_aplicacion_repuestos where padre like '0' ";
		Object[][] results=this.data.getResults(q);
		block[0]=true;
		Level[0].removeAllItems();
		Level[0].addItem("");
		if (results!=null){
			for (int i=0;i<results.length;i++){
				String opt=results[i][1].toString();
				Level[0].addItem(opt);
				}
		}
		Level[0].setSelectedIndex(-1);
		block[0]=false;
	}
	
	public String getSecuence(){
		System.out.println("Secuencer");
		String secuence="";
		String anterior="";
		for (int i=0;i<=this.selected_level;i++){
			try {
			String nombre=Level[i].getSelectedItem().toString();
			anterior=this.getId(nombre, anterior);
			System.out.println("nivel "+i+" >"+anterior);}
			catch(Exception e){
				
			}
		}
		return anterior;
	}
	
	public String getId(String nombre,String padre){
		String q="";
		q=q+" select b.id,b.clasificacion ";
		q=q+" from b_aplicacion_repuestos b ";
		q=q+" where b.clasificacion like '"+nombre+"' ";
		if (padre.compareTo("")!=0){
			q=q+" and padre like '"+padre+"' ";
		}
		Object[][] results=this.data.getResults(q);
		String qs="";
		if (results!=null){
			if (results.length>0){
				qs=(String)results[0][0];	
			}
			
		}
		return qs;
	}
	
	public LinkedList getFromAplicacion(String id){
		//System.out.println("get from aplicacion "+id+" ");
		int i=0;
		LinkedList L=new LinkedList();
		//L.add(id);
		while (i<aplicacion.length){
			String idx=aplicacion[i][2];
			String code=aplicacion[i][0];
			if (idx.compareTo(id)==0){
				//System.out.println("Adding "+code+" ");
				L.add(code);
				/*LinkedList sx=this.getFromAplicacion(code);
				if (sx.size()>0){
					for (int u=0;u<sx.size();u++){
						String qs="";
						qs=(String)sx.get(u);
						L.add(qs);
					}
				}*/
			}
			i++;
		}
		return L;
	}
	
	public void load_newOptions(int level){
		String qs="";
		
		int current=Level[level-1].getSelectedIndex();
		System.out.println("Load new Options from level "+level+" selected option is "+current);
		block[level]=true;
		if (current>=1){
			selected_level=level-1;
			try {
				qs=Level[level-1].getSelectedItem().toString();
			}catch(Exception e){
				
			}
			
			//String code=this.getidAplicacion(qs);
			String code=this.getSecuence();
			LinkedList L=this.getFromAplicacion(code);
			
			
			JComboBox Levelx=Level[level];
			for (int i=level;i<Level.length;i++){
				Level[i].removeAllItems();
				Level[i].setVisible(false);
			}
			if (L.size()>0){
				Levelx.addItem("");
				for (int i=0;i<L.size();i++){
					String opt=this.getDescripcionAplicacion((String)L.get(i));
					Levelx.addItem(opt);
					}
				
				Levelx.setVisible(true);
				Levelx.setSelectedIndex(-1);
				
			}else {
				
				Levelx.setVisible(false);
				
			}
				
		}else {
			for (int i=level;i<Level.length;i++){
				Level[i].removeAllItems();
				Level[i].setVisible(false);
			}
			selected_level=level-2;
		}
		
		open();
		block[level]=false;
		
	
	}
	
	
	public String getDescripcionAplicacion(String id){
		//System.out.println("get from aplicacion "+id+" ");
		String aux="";
		int i=0;
		boolean found=false;
		while (i<aplicacion.length & !found){
			found=aplicacion[i][0].compareTo(id)==0;
			if (found){
				aux=aplicacion[i][1];
			}
			i++;
		}
		return aux;
	}
	
	
	public void load_newOptions_back(int level){
		String qs="";
		int current=Level[level-1].getSelectedIndex();
		block[level]=true;
		if (current>=1){
			selected_level=level-1;
			try {
				qs=Level[level-1].getSelectedItem().toString();
			}catch(Exception e){
				
			}
			
			String q="";//select id,clasificacion from b_clasificacion where padre like '"+o+"' ";
			q=q+" select c.id,c.clasificacion ";
			q=q+" from b_aplicacion_repuestos b left outer join b_aplicacion_repuestos c ";
			q=q+" on c.padre=b.id ";
			q=q+" where b.clasificacion like '"+qs+"' ";
			
			Object[][] results=this.data.getResults(q);
			
			JComboBox Levelx=Level[level];
			for (int i=level;i<Level.length-1;i++){
				Level[i].removeAllItems();
			}
			if (results!=null){
				Levelx.addItem("");
				for (int i=0;i<results.length;i++){
					String opt=results[i][1].toString();
					Levelx.addItem(opt);
					}
				
				Levelx.setSelectedIndex(-1);
				
			}else {
				Levelx.setEnabled(false);
			}
				
		}else {
			for (int i=level;i<Level.length-1;i++){
				Level[i].removeAllItems();
			}
			selected_level=level-2;
		}
		open();
		block[level]=false;
		
	
	}
	
	public void open(){
		PedidoNode node=null;
		
		 TreePath currentSelection = null;
		 try {
			 currentSelection = frame.getJTree().getSelectionPath();
		 }catch(Exception e){
			 
		 }
	       if (currentSelection != null) {
	        	try {
					node = (PedidoNode)(currentSelection.getLastPathComponent());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
	        }
	       if (node!=null){
	    	   this.fillTable(node);	   
	       }
	     
		
	}
	
	public boolean addArticle(String idarticulo){
		boolean ok=false;
		PedidoNode node=null;
		
		 TreePath currentSelection = null;
		 try {
			 currentSelection = frame.getJTree().getSelectionPath();
		 }catch(Exception e){
			 
		 }
	       if (currentSelection != null) {
	        	try {
					node = (PedidoNode)(currentSelection.getLastPathComponent());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
	        }
	       if (node!=null){
	    	 String clasificacion=node.getValue();
	    	 String aplicacion=this.getSecuence();
	    	 Object[][] results=data.getArticulo(idarticulo);
	    	 String descripcion=results[0][0].toString();
	    	 String precio2=results[0][1].toString();
	    	 String linea=results[0][3].toString();
	    	data.clearBatch();
	    	String q=data.getInsert(idarticulo, aplicacion, clasificacion, descripcion);
	    	data.addBatch(q);
	    	ok=!data.executeBatch();
	       }
		return ok;
	}
	
	public String getQuery(PedidoNode node){
		String q="select top 200 v.idarticulo,a.descripcion,v.precio2,v.descripabrev,a.cantidad,ISNULL(s.cantidadud, 0),isnull(a.clasificacion,''),a.desde,a.hasta ";
		q=q+"from V_MA_ARTICULOS v LEFT OUTER JOIN Stk_Stock_UDKG s ON  v.IDARTICULO = s.IdArticulo ";
		q=q+" left outer join b_articulos_aplicacion a on v.idarticulo=a.idarticulo ";
		q=q+" where ";
		String w="";
		if (node!=null){
			w=w+" a.clasificacion like '"+node.getValue()+"' ";
			if (frame.get_chk_raiz().isSelected()){
				w=w+ " or a.clasificacion like '"+node.getValue()+"-%'  ";	
			}
			w="("+w+")";
			
		}
				
		String wx="";
		
		String sel=this.getSecuence();
		String url="clas="+node.getValue()+"&aplic="+sel;
		frame.get_txt_url().setText(url);
		if (sel.compareTo("")!=0){
			wx=" (a.aplicacion like '"+sel+"' or a.aplicacion like '"+sel+"-%') ";
		}
		
		if (w.length()>0 & wx.length()>0){
			w="("+w+") and ("+wx+") ";	
		}else {
			if (wx.length()>0){
				w=w+"  ("+wx+") ";	
			}else {
				if (w.length()>0){
					w="( "+w+" ) ";
				}
			}
			
		}
		
			int year=-1;
			try{
				year=new Integer(frame.get_txt_modelo().getText());
			}catch(Exception e){
				
			}
			if (year>0){
				if (w.length()>0){
					w=w+" and (isnull(a.desde,-1)<="+year+" and "+year+" <=isnull(a.hasta,99999)) ";	
				}
			}
		
		if (w.length()>0){
			q=q+w;	
		}
		q=q+" order by v.clasificacion,v.idarticulo ";
		System.out.println(q);
		return q;
	}
	
	public void fillTable(PedidoNode node){
		String url="&clasificacion="+node.getValue();
		frame.get_txt_url().setText(url);
		String Query=this.getQuery(node);
		aux=data.getResults(Query);
		
		if (aux!=null){
			if (aux.length>0){
				Object[][] tmp=new Object[aux.length][aux[0].length+1];
				for (int i=0;i<aux.length;i++){
					tmp[i][0]=false;
					for (int j=0;j<aux[0].length;j++){
						tmp[i][j+1]=aux[i][j];	
					}
					
				}
				this.create_table(tmp);
			}
		}
	
	}
	
	public LinkedList getFromRepuestos(String id){
		//System.out.println("get from repuestos "+id+" ");
		int i=0;
		LinkedList L=new LinkedList();
		L.add(id);
		while (i<repuestos.length){
			String idx=repuestos[i][0];
			String code=repuestos[i][2];
			if (idx.compareTo(id)==0){
				//System.out.println("Adding "+code+" ");
				L.add(code);
				LinkedList sx=this.getFromRepuestos(code);
				if (sx.size()>0){
					for (int u=0;u<sx.size();u++){
						String qs="";
						qs=(String)sx.get(u);
						L.add(qs);
					}
				}
			}
			i++;
		}
		return L;
	}
	
	public void remake_tree(String apl){
		String q="select clasificacion from b_articulos_aplicacion ";
		q=q+" where aplicacion like '"+apl+"%' ";
		q=q+" group by clasificacion ";
		System.out.println(q);
		codes=new LinkedList();
		Object[][] results=this.data.getResults(q);
		LinkedList l=null;
		if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					String code=(String)results[i][0];
					l=this.getFromRepuestos(code);
					for (int j=0;j<l.size();j++){
						addIncludes((String) l.get(j));
					}
					
				}
				//System.out.println("Clasificaciones utilizables "+codes.size());
			}
		}
		/*
		LinkedList lx=this.getLinks();
		for (int j=0;j<lx.size();j++){
			codes.add(lx.get(j));
		}
		*/
		PedidoNode abuelo = new PedidoNode("Repuestos ","0","Usuarios");
		modelo = new DefaultTreeModel(abuelo);
		
		for (int j=0;j<codes.size();j++){
			//System.out.println(">/"+(String)codes.get(j));
		}
		
	}
	
	public void addIncludes(String code){
		boolean found=false;
		int i=0;
		while (!found & i<codes.size()){
			String cx=(String)codes.get(i);
			found=code.compareTo(cx)==0;
			if (!found){
				i++;
			}
		}
		if (!found){
			codes.add(code);
		}
	}
	public void create_pedidos(){
		System.out.println("Remake Tree ");
		this.remake_tree(this.getSecuence());
		System.out.println("Secuence? "+this.getSecuence());
		PedidoNode abuelo = this.make_master_abuelo();
		DefaultTreeModel modelo = new DefaultTreeModel(abuelo);
		
		JTree jTree = new JTree(modelo);
		Font fuente=new Font("Arial", Font.PLAIN, 9);
		jTree.setFont(fuente);
		jTree.addMouseListener(new treeMouseListener());
		
		
		jTree.repaint();
		frame.setJTree(jTree);
	}
	
	
}

