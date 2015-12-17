package aplicacion.sistema.indexer.test;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

import aplicacion.herramientas.conexion.ConnectionHandler;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class PDFTextStripperExtended extends PDFTextStripper {
	
    private String content="";
    
    private List<String> pages;
    
	
    public List<String> getPages(){
    	return this.pages;
    }
    
	public PDFTextStripperExtended() throws IOException {
		pages=new ArrayList<String>();
	}
	
	protected void writeLineSeparator( ) throws IOException
    {
        super.writeLineSeparator();
        System.out.println("");
        content+=" ";
    }
	
	protected void writeWordSeparator() throws IOException
    {
		System.out.print(" ");
        super.writeWordSeparator();
        content+=" ";
    }
	
	protected void writeCharacters( TextPosition text ) throws IOException
    {
		content+=text.getCharacter()+" ";
		System.out.print("writeCharacters=["+text.getCharacter()+"]");
        super.writeCharacters(text);
        
    }
	
	protected void writeString( String text ) throws IOException
    {
		content+=text+" ";
		System.out.println("writeString=["+text+"]");
        super.writeString(text);
    }
	
	protected void startPage( PDPage page ) throws IOException
    {
		content="";
       System.out.println("Procesando Pagina "+super.getCurrentPageNo());
    }
	
	protected void processTextPosition( TextPosition text ){
		
		super.processTextPosition(text);
		//System.out.println("processTextPositon?"+text.getCharacter());
		//content+=text.getCharacter();
	}
	
	protected void endPage( PDPage page ) throws IOException
    {
		//default is to do nothing
		pages.add(content);
		/*
		System.out.println("Cerrando Pagina "+super.getCurrentPageNo());
    	String q="insert into pdf (page,content,file) ";
    	content=content.replaceAll("'", "''");
    	q+="values ("+super.getCurrentPageNo()+",'"+content+"','"+file+"')";
    	System.out.println(q);
    	c.getConnector("MySQL").clearBatch();
    	c.getConnector("MySQL").addBatch(q);
    	c.getConnector("MySQL").executeBatch();
    	content="";*/
    }

}
