package aplicacion.herramientas.java.xml;
   

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;      
import java.io.File;

import javax.swing.JFileChooser;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.Attribute;
import javax.xml.namespace.QName;
import java.net.URI;
import beta.tools.utils.FileCreator;



import java.util.*;
public class XML {      

    private String configFile="";      
    private File file;
    private String start_element="";
    private InputStream in =null;
    private LinkedList atributos;
    private LinkedList pila;
    
    private Element root=null;
    private Element current=null;
    private URI uri=null;
    
    public void setInputStream(InputStream in){
    	this.in=in;
    }
    private boolean clave_existente(Atributo atributo){
		boolean existe=false;
		int i=0;
		while (!existe & i<atributos.size()){
			Atributo _atributo=(Atributo) atributos.get(i);
			existe=_atributo.getNombre().toLowerCase().compareTo(atributo.getNombre().toLowerCase())==0;
			i++;
		}
		return existe;
	}
    
    private void setURI(URI uri){
    	this.uri=uri;
    }
    
    private boolean actualizar(Atributo atributo){
		boolean existe=false;
		int i=0;
		while (!existe & i<atributos.size()){
			Atributo _atributo=(Atributo) atributos.get(i);
			existe=_atributo.getNombre().toLowerCase().compareTo(atributo.getNombre().toLowerCase())==0;
			if (existe){
				atributos.set(i, atributo);
				
			}else {
				i++;	
			}
			
		}
		return existe;
	}
    
	public void setAtributos(String[] atributos){
		for (int i=0;i<atributos.length;i++){
			Atributo A=new Atributo(atributos[i]);
			A.setTipo(String.class);
			//System.out.println("atributo "+atributos[i]+" = "+this.agregarAtributo(A));
		}
	}
	
	private boolean agregarAtributo(Atributo atributo){
		boolean agregado=false;
		if (atributos==null){
			atributos=new LinkedList();
		}
		if (!this.clave_existente(atributo)){
			atributos.add(atributo);
			agregado=true;
		}
		return agregado;
	}
	
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }      

    public void setFile(File file){
    	this.file=file;
    }
    private void apilar(Element e){
    	//System.out.println(">>>apila "+e.getId());
    	if (pila==null){
    		pila=new LinkedList();
    	}
    	pila.addFirst(e);
    }
    
    
    
    public Element deasapilar(){
    	Element e=null;
    	if (pila!=null){
    		if (pila.size()>0){
    			e=(Element)pila.getFirst();
        		if (e!=null){
        			pila.removeFirst();
        		}	
    		}
    		
    	}
    	//if (e!=null)System.out.println(">>>desapila "+e.getId());
    	return e;
    }
    
    public void recorrerPila(){
    	if (root!=null){
    		
        	this.printElement(root);
    	}
    }
    
    public void printElement(Element e){
    	System.out.print("<"+e.getId()+" ");
    	if (e.getAtributos()!=null){
    		for (int i=0;i<e.getAtributos().size();i++){
        		Atributo a=(Atributo)e.getAtributos().get(i);
        		System.out.print(""+a.getNombre()+"=\""+a.getValor()+"\" ");
        	}	
    	}
    	//System.out.println(">");
    	if (e.getElements()!=null){
    		for (int i=0;i<e.getElements().size();i++){
        		Element h=(Element)e.getElements().get(i);
        		this.printElement(h);
        	}	
    	}
    	//System.out.println("</"+e.getId()+" >");
    }
    
    public String toString(Element e){
    	String NEW_LINE = System.getProperty("line.separator");
    	String xml="";
    	xml+="<"+e.getId()+" ";
    	if (e.getAtributos()!=null){
    		for (int i=0;i<e.getAtributos().size();i++){
        		Atributo a=(Atributo)e.getAtributos().get(i);
        		xml+=(""+a.getNombre()+"=\""+a.getValor()+"\" ");
        	}	
    	}
    	xml+=">"+NEW_LINE;
    	if (e.getElements()!=null){
    		for (int i=0;i<e.getElements().size();i++){
        		Element h=(Element)e.getElements().get(i);
        		xml+=this.toString(h);
        		xml+=""+NEW_LINE;
        	}	
    	}
    	xml+=("</"+e.getId()+" >");
    	return xml;
    }
    
    public void setRoot(Element e){
    	this.root=e;
    }
    private void printElement(FileCreator out,Element e){
    	if (e.isRoot()){
    		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");	
    	}
    	out.print("<"+e.getId()+" ");
    	if (e.getAtributos()!=null){
    		for (int i=0;i<e.getAtributos().size();i++){
        		Atributo a=(Atributo)e.getAtributos().get(i);
        		out.print(""+a.getNombre()+"=\""+a.getValor()+"\" ");
        	}	
    	}
    	out.println(">");
    	if (e.getElements()!=null){
    		for (int i=0;i<e.getElements().size();i++){
        		Element h=(Element)e.getElements().get(i);
        		this.printElement(out,h);
        	}	
    	}
    	out.println("</"+e.getId()+" >");
    }
    
    public void readAll(){
    	try {
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            
            boolean start=false;
            if (in==null){
            	if (configFile.compareTo("")!=0){
                	in = new FileInputStream(configFile);
                }else {
                	try {
						in = new FileInputStream(file);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
                }	
            }
            try {
            	
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            while (eventReader.hasNext()) { 
            	
            		
            	
               XMLEvent event = eventReader.nextEvent();  
               
               if (event.isStartElement()) {
                                 	   
                	   Element e=new Element(event.asStartElement().getName().toString());
                	                   	   
                	   Iterator it=event.asStartElement().getAttributes();
                	   while (it.hasNext()){
                		   Object obj=it.next();
                		   
                		   com.sun.xml.internal.stream.events.AttributeImpl 
                		   tmp=(com.sun.xml.internal.stream.events.AttributeImpl) obj;
                		   Atributo a=new Atributo(tmp.getName().toString());
                		   a.setValor(tmp.getValue());
                		   e.addAtribute(a);
                	   }
                	   Element p=this.deasapilar();
                	   if (p!=null){
                		   p.addElement(e);
                		   this.apilar(p);
                		   this.apilar(e);
                		  // System.out.println("Apilando "+e.getId()+" en "+p.getId());
                	   }else {
                		   //System.out.println("Apilando primer elemento");
                		   
                		   this.apilar(e);
                	   }
                	     continue;
                   
               }else {
            	   if (event.isEndElement()) {
            		   this.root=this.deasapilar();
            		   //System.out.println("End Element! "+event.asEndElement().getName());
            	   }
               }
            }
            }catch(javax.xml.stream.XMLStreamException ex){
        		
        	}
        } catch (FileNotFoundException e) {
        	//System.out.println("XML> FILE NOT FOUND EXCEPTION ");
            e.printStackTrace();
            
        }
        
    }
    
    public Element buscarElemento(String element_name){
    	Element e=root;
    	e=this.getElement(e, element_name);
    	return e;
    }
    
    public Element getElement(Element e,String element_name){
    	
    	Element ex=null;
    	
    		if (e.getId().compareTo(element_name)==0){
    			ex=e;
    		}else {
    			int i=0;
    			if (e.getElements()!=null){
    				while (i<e.getElements().size() & ex==null){
                		Element h=(Element)e.getElements().get(i);
                		ex=this.getElement(h, element_name);
                		i++;
                	}	
    			}
    				
    		}
    			
    	
    	return ex;
    }
    
    
public Atributo getAtributo(Element e,String attribute_name){
    	
    	Atributo ex=null;
    		boolean found=false;
    		int i=0;
    		while (i<e.getAtributos().size() & !found){
    			ex=(Atributo)e.getAtributos().get(i);
    			found=ex.getNombre()==attribute_name;
    		if (!found)i++;	
    		}
    		
    	return ex;
    }

    public void readConfig() {      

        try {
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in =null;
            if (configFile.compareTo("")!=0){
            	in = new FileInputStream(configFile);
            }else {
            	in = new FileInputStream(file);
            }
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            while (eventReader.hasNext()) {      
               XMLEvent event = eventReader.nextEvent();      
               if (event.isStartElement()) {
                   if (event.asStartElement().getName().getLocalPart() == (this.start_element)) {
                	   if (this.atributos!=null){
                		   for (int i=0;i<this.atributos.size();i++){
                			   Atributo a=(Atributo) this.atributos.get(i);
                			   Attribute tmp=event.asStartElement().getAttributeByName(QName.valueOf(a.getNombre()));
                			   if (tmp!=null){
                				   a.setValor(tmp.getValue());
                    			   //System.out.println(a.getNombre()+">"+a.getValor());
                    			   this.actualizar(a);   
                			   }
                			   
                		   }
                	   }
                	   continue;
                   }
               }
            }
        } catch (FileNotFoundException e) {
        	//System.out.println("XML> FILE NOT FOUND EXCEPTION");
            e.printStackTrace();
            
        } catch (XMLStreamException e) {
            //System.out.println("XML> STREAM EXCEPTION ");
            e.printStackTrace();
            
        }
    }      
    
    public void setStartElement(String id){
    	this.start_element=id;
    }
    
    public Atributo getAtributo(String nombre){
    	boolean existe=false;
		int i=0;
		Atributo _atributo=null;
		while (!existe & i<atributos.size()){
			_atributo=(Atributo) atributos.get(i);
			existe=_atributo.getNombre().toLowerCase().compareTo(nombre.toLowerCase())==0;
			i++;
		}
		return _atributo;
    }
    
    public String getValorAtributo(String nombre){
    	String tmp="";
    	Atributo a=this.getAtributo(nombre);
    	if (a!=null){
    		tmp=a.getValor();
    	}
    	return tmp;
    }
    public void guardar(){
    	JFileChooser JF = new JFileChooser();
		int fx=JF.showSaveDialog(null);
		String output="";
		if (fx == JFileChooser.APPROVE_OPTION) {
	        File file = JF.getSelectedFile();
	        output=file.getAbsolutePath();
	        this._guardar(output);
		}
    }
    
    public boolean _guardar(String output){
    		boolean ok=true;
	        if (output.compareTo("")!=0){
	        	System.out.println("Guardando XML "+output);
	        	FileCreator Logs=new FileCreator(output);
				Logs.Open();
				
				this.printElement(Logs, root);
				Logs.Close();
	        }
	        return ok;
		
    }
    
    
    public Element getRoot(){
    	return this.root;
    }
    public static void main(String args[]) {
        XML xml = new XML();
        Atributo atributo=null;
        Element root=new Element("versiones");
        root.setRoot(true);
        Element version=new Element("version");
        
        atributo=new Atributo("id");
        atributo.setValor("00000011");
        version.addAtribute(atributo);
        
        atributo=new Atributo("fecha");
        atributo.setValor("21-04-2010 11:58 AM");
        version.addAtribute(atributo);
        
        atributo=new Atributo("idproveedor");
        atributo.setValor("211010029");
        version.addAtribute(atributo);
        
        atributo=new Atributo("nombre");
        atributo.setValor("etman");
        version.addAtribute(atributo);
        
        Element update=new Element("update");
        atributo=new Atributo("directory");
        atributo.setValor("/beta/actualizacion");
        update.addAtribute(atributo);
        
        atributo=new Atributo("source");
        atributo.setValor("00000011.zip");
        update.addAtribute(atributo);
        
        atributo=new Atributo("destination");
        atributo.setValor("actualizacion\\");
        update.addAtribute(atributo);
        version.addElement(update);
        root.addElement(version);
        
        xml.setRoot(root);
        xml.guardar();
        /*
        //host="192.168.4.1" database="prueba" userid="prueba" password="prueba"
        JFileChooser JF = new JFileChooser();
		int fx=JF.showOpenDialog(null);
		String output="";
		if (fx == JFileChooser.APPROVE_OPTION) {
	        File file = JF.getSelectedFile();
	        output=file.getAbsolutePath();
	        read.setConfigFile(output);
	       
	        read.readAll();
	        read.recorrerPila();
	        
	        
	        Element select=read.buscarElemento("select");
	        if (select!=null){
	        	if (select.getElements()!=null){
	        		for (int i=0;i<select.getElements().size();i++){
		        		Element h=(Element)select.getElements().get(i);
		        		System.out.println();	
		        	}	
	        	}
	        	
	        	//System.out.println("Elemento select encontrado");
	        	
	        }
	        read.guardar();
	    
	        Element from=read.buscarElemento("from");
	        if (from!=null){
	        	System.out.println("Elemento from encontrado");
	        	
	        }
        
	        
		}
        */
        
        
    }
}
