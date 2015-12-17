package aplicacion.gestion.plan.logic;

import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;




import aplicacion.catalogo.aplicacion.constructor.*;
import aplicacion.catalogo.aplicacion.gui._Frame;
import aplicacion.catalogo.aplicacion.interfaces._Interface;
import aplicacion.catalogo.aplicacion.logic._Data;
import aplicacion.catalogo.aplicacion.logic.Nodo;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;

public class _Logic extends Logic {
	private _Frame frame=null;
	private _Data data=null;
	private String[][] memory=null;
	
	public void setData(Data data){
		this.data=(_Data) data;
		super.setData(data);
	}
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void buildTree(){
		Nodo raiz=new Nodo("Aplicacion","0");
		
		_Constructor c=(_Constructor) this.getConstructor();
		DefaultTreeModel treeModel = new DefaultTreeModel(raiz);
		//treeModel.addTreeModelListener(c.getTreeModelListener());
		JTree tree = new JTree(treeModel);
        tree.setEditable(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        tree.setName(_Interface._tree);
        this.loadToMemory();
        this.load_clases_from_memory(raiz);
        
        //this.load_clases(raiz);
        frame.setJTree(tree);
	}
	
	public void Recodificar(){
	   	 TreePath currentSelection = frame.getJTree().getSelectionPath();
	        if (currentSelection != null) {
	        	Nodo currentNode = (Nodo)(currentSelection.getLastPathComponent());
	        	Nodo parent=(Nodo) currentNode.getParent();
	        	String aux="";
	        	if (parent!=null){
	        		aux=parent.getValue();
	        		System.out.println(aux);
	        		String valx=aux.replace("-", "");
	        		String act=currentNode.getValue();
	        		act=act.replace(valx, "");
	        		aux=aux+"-"+act;
	        	}
	        	String clasx=JOptionPane.showInputDialog("Ingrese Nuevo Codigo para ("+currentNode.getValue()+") "+currentNode.getUserObject().toString(),aux);
	        	
	        	if (clasx!=null){
	        		if (preguntar("Confirmar","Confirma Recodificacion de "+currentNode.getValue()+" a "+clasx)){
	        			System.out.println("Recodificar "+currentNode.getValue()+" a "+clasx);
	            		boolean error=recodificar(currentNode.getValue(),clasx);	
	            		if (!error){
	             			currentNode.setValue(clasx);
	             			DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel(); 
	             			treeModel.reload(currentNode);
	             		}	
	        		}
	        		
	        		
	        	}
	        }
	   }
	
	private boolean existe(String id){
		boolean existe=data.exist(id);
		return existe;
	}
	
	public int get_Clasificacion(String clasificacion,String padre){
		int _clas=0;
		String q=data.getClasificacion(clasificacion, padre);
		Object[][] results=data.getResults(q);
		if (results!=null){
			if (results.length>0){
				_clas=new Integer((String)results[0][0]);
			}
		}
		return _clas;
	}
	
	public boolean recodificar(String oldcode,String newcode){
    	
    	String q1=data.getUpdate(newcode, oldcode);
    	String q2=data.getUpdatePadre(newcode, oldcode);
    	data.clearBatch();
    	System.out.println(q1);
    	System.out.println(q2);
    	data.addBatch(q1);
    	data.addBatch(q2);

    	boolean error=data.executeBatch();
    	if (!error){
    		aviso("Se Recodifico Correctamente");
    	}else {
    		error("Error Recodificando ");
    	}
    	return error;	
    }

	public Nodo nueva_clasificacion(Nodo padre,Nodo clas){
		if (clas!=null){
			int ch=padre.getChildCount();
			boolean ex=true;
			while (ex){
				ex=this.existe(padre.getValue()+"-"+ch);
				if (ex){
					ch++;	
				}
			}
			clas.setValue(padre.getValue()+"-"+ch);
			String q="insert into b_clasificacion_cuentas (id,clasificacion,padre) values () ";
			q=data.getInsert(clas.getValue(),clas.getUserObject().toString(),padre.getValue());
			System.out.println(q);
			data.clearBatch();
			data.addBatch(q);
			boolean error=data.executeBatch();
			if (!error){
			}else {
				clas=null;
			}
			
		}
		return clas;
	}

public void eliminar_clasificacion(Nodo node,Nodo parent){
	int i=node.getChildCount();
	String clas=(String)node.getUserObject();
	if (node.isLeaf()){
		if (preguntar("confirmar","Desea eliminar la clasificacion? ")){
			
			String q=data.getDelete(clas, parent.getValue());
			System.out.println(q);
			data.clearBatch();
			data.addBatch(q);
			boolean error=data.executeBatch();
			if (!error){
			
			}	
		}
		
	}else {
		aviso("Debe eliminar primero las sub-clasificaciones!");
	}
}

public void renameCurrentNode() {
    //rootNode.removeAllChildren();
    //treeModel.reload();
	TreePath currentSelection = frame.getJTree().getSelectionPath();
    if (currentSelection != null) {
    	Nodo currentNode = (Nodo)(currentSelection.getLastPathComponent());
    	
        Nodo parent = (Nodo)(currentNode.getParent());
        renameCurrentNode(currentNode);
    }else{
    	aviso("selection null");
    }
}

public void removeCurrentNode() {
	
	if (frame.getJTree()!=null){
		
		TreePath currentSelection = frame.getJTree().getSelectionPath();
	    if (currentSelection != null) {
	    	Nodo currentNode = (Nodo)(currentSelection.getLastPathComponent());
	    	
	        Nodo parent = (Nodo)(currentNode.getParent());
	        if (parent != null) {
	        	if (currentNode.isLeaf()){
	        		if (!currentNode.isRoot()){
	        			DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
	        			if (treeModel!=null){
	        				treeModel.removeNodeFromParent(currentNode);
	        				eliminar_clasificacion(currentNode,parent);
	        			}
	        			
	                    
	        			
	                    
	        		}else {
	        			//JOptionPane.showMessageDialog(this, "No se puede Eliminar la raiz!");
	        			error("No se puede Eliminar la raiz!");
	        		}
	        	}else {
	        		error("Debe Eliminar las Sub-Clasificaciones ");
	        	}
	        }
	    }	
	}
     

    // Either there was no selection, or the root was selected.
    //toolkit.beep();
}
public DefaultMutableTreeNode addObject() {
    Nodo parentNode = null;
    TreePath parentPath = frame.getJTree().getSelectionPath();
    DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree().getModel();
    if (parentPath == null) {
        parentNode = (Nodo)treeModel.getRoot();
    } else {
        parentNode = (Nodo)(parentPath.getLastPathComponent());
    }

    return addObject(parentNode, null, true);
}

public DefaultMutableTreeNode addObject(Nodo parent,
                                        Object child) {
    return addObject(parent, child, false);
}

public DefaultMutableTreeNode addObject(Nodo parent,
                                        Object child, 
                                        boolean shouldBeVisible) {
	String clas_p=(String)parent.getUserObject();
	String clas=JOptionPane.showInputDialog("Ingrese sub-clasificacion de "+clas_p);
	Nodo childNode = new Nodo(clas,parent.getValue());
	DefaultTreeModel treeModel = (DefaultTreeModel) frame.getJTree().getModel();
    if (parent == null) {
    	
        parent = (Nodo)treeModel.getRoot();
    }

    if (clas.compareTo("")!=0){
    	childNode=nueva_clasificacion(parent,childNode);
        if (childNode!=null){
            treeModel.insertNodeInto(childNode, parent, 
                    parent.getChildCount());
            if (shouldBeVisible) {
                frame.getJTree().scrollPathToVisible(new TreePath(childNode.getPath()));
            }
        }	
    }
    return childNode;
}

public Nodo renameCurrentNode(Nodo clas){
	if (clas!=null){
		String clasx=JOptionPane.showInputDialog("Ingrese Nuevo Nombre para "+clas);
		if (clasx.compareTo("")!=0){
			String q=data.getUpdate(clasx, clas.getValue());
			System.out.println(q);
			data.clearBatch();
			data.addBatch(q);
			boolean error=data.executeBatch();
			if (!error){
				DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
				clas.setUserObject(clasx);
				treeModel.reload(clas);
			}else {
				clas=null;
			}	
		}
		
	}
	return clas;
}

private void loadToMemory(){
	
		String q=data.getMessage("select_all");
		System.out.println(q);
		Object[][] results=data.getResults(q);
		if (results!=null){
			if (results.length>0){
				System.out.println("Clasificaciones encontradas? "+results.length);
				memory=new String[results.length][results[0].length];
				for (int i=0;i<results.length;i++){
					for (int j=0;j<results[0].length;j++){
						memory[i][j]=(String) results[i][j];	
					}
					
				}
			}
		}
		
}
public void load_clases_from_memory(Nodo abuelo){
	int i=0;
	int childs=0;
	while (i<memory.length){
		if (memory[i][2].compareTo(abuelo.getValue())==0){
			Nodo padre = new Nodo(memory[i][1],memory[i][0]);
			this.load_clases_from_memory(padre);
			DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
			treeModel.insertNodeInto(padre, abuelo, childs);
			childs++;
		}
		i++;
	}
}

public void load_clases(Nodo abuelo){
	
	String q="";
	String[] parameters=new String[]{
		abuelo.getValue()	
	};
	q=data.getQuery("select", parameters);
	System.out.println(q);
	Object[][] results=data.getResults(q);
	if (results!=null){
			if (results.length>0){
				for (int i=0;i<results.length;i++){
					Nodo padre = new Nodo(""+(String)results[i][0]+"",(String)results[i][1]);
					this.load_clases(padre);
					DefaultTreeModel treeModel =(DefaultTreeModel) frame.getJTree().getModel();
					treeModel.insertNodeInto(padre, abuelo, i);
					
				}
			}
		}
	}

}


