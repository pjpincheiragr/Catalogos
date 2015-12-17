package aplicacion.sistema.indexer.test;

/*
 * PDFTextParser.java
 *
 * Created on January 24, 2009, 11:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


/**
 *
 * @author prasanna
 */
import java.awt.Dimension;
import javax.swing.*;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdfviewer.PageDrawer;
import org.apache.pdfbox.util.PDFOperator;
import org.apache.pdfbox.util.TextPosition;
import org.apache.pdfbox.cos.*;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFStreamEngine;

import java.awt.image.BufferedImage;

import aplicacion.herramientas.conexion.ConnectionHandler;
import aplicacion.herramientas.conexion.conectores.MySQL;
import aplicacion.herramientas.java.image.constructor._Constructor;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
//import org.apache.pdfbox.util.PDFTextStripper;

public class PDFTextParser {
	ConnectionHandler c;
    PDFParser parser;
    String parsedText;
    PDFTextStripperExtended pdfStripper;
    PDDocument pdDoc;
    COSDocument cosDoc;
    PDDocumentInformation pdDocInfo;
    
    public void loadImage(BufferedImage img){
    	_Constructor C=new _Constructor();
		C.build(null);
		//C.init();
		aplicacion.herramientas.java.image.logic._Logic logic=
			(aplicacion.herramientas.java.image.logic._Logic)C.getLogic();
		logic.setImage(img);
		logic.setEliminar(false);
    }
    // PDFTextParser Constructor 
    public PDFTextParser() {
    }
    
    
    
    // Extract text from PDF Document
    public boolean pdftoText(String fileName) {
     this.initConnector();   
        System.out.println("Parsing text from PDF file " + fileName + "....");
        File f = new File(fileName);
        
        if (!f.isFile()) {
            System.out.println("File " + fileName + " does not exist.");
            return false;
        }
        
        try {
            parser = new PDFParser(new FileInputStream(f));
        } catch (Exception e) {
            System.out.println("Unable to open PDF Parser.");
            return false;
        }
        
        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripperExtended();
            pdfStripper.setSortByPosition(true);
            
            //pdfStripper.setStartPage(48);
            //pdfStripper.setEndPage(51);
            
            pdDoc = new PDDocument(cosDoc);
            int npages=pdDoc.getNumberOfPages();
            List pages = pdDoc.getDocumentCatalog().getAllPages();
            
            /*
            
            for( int i=0; i<pages.size(); i++ )            {
            PDPage page = (PDPage)pages.get( i );
            PDFStreamParser parser = new PDFStreamParser(page.getContents().getStream() );
            parser.parse();
            parser.getTokens();
            } 
            */
            
            //System.out.println("Pages?"+npages);
            parsedText = pdfStripper.getText(pdDoc); 
        } catch (Exception e) {
            System.out.println("An exception occured in parsing the PDF Document.");
            e.printStackTrace();
            try {
                   if (cosDoc != null) cosDoc.close();
                   if (pdDoc != null) pdDoc.close();
               } catch (Exception e1) {
               e.printStackTrace();
            }
            return false;
        }      
        System.out.println("Done.");
        try {
            if (cosDoc != null) cosDoc.close();
            if (pdDoc != null) pdDoc.close();
        } catch (Exception e1) {
        e1.printStackTrace();
        }
        return true;
    }
    
    
    // Write the parsed text from PDF to a file
    public void writeTexttoFile(String pdfText, String fileName) {
    	
    	System.out.println("\nWriting PDF text to output text file " + fileName + "....");
    	try {
    		PrintWriter pw = new PrintWriter(fileName);
    		pw.print(pdfText);
    		pw.close();    	
    	} catch (Exception e) {
    		System.out.println("An exception occured in writing the pdf text to file.");
    		e.printStackTrace();
    	}
    	System.out.println("Done.");
    }
    
    public void list(String directory){
    	File dir = new File(directory); 
    	String[] children = dir.list();
    	 if (children == null) { 
    	// Either dir does not exist or is not a directory 
    	} else { 
    	for (int i=0; i<children.length; i++) { 
    	// Get filename of file or directory 
    	String filename = children[i];
    	System.out.println(filename);
    	//this.proccess(directory+"/"+filename);
    	} 
    	}
    }
    
    public void open(String fileName,int page){
    	File f = new File(fileName);
        if (!f.isFile()) {
            System.out.println("File " + fileName + " does not exist.");
            
            
        }else{
        	try {
                parser = new PDFParser(new FileInputStream(f));
                parser.parse();
                cosDoc = parser.getDocument();
                pdDoc = new PDDocument(cosDoc);
                
                int npages=pdDoc.getNumberOfPages();
                List pages = pdDoc.getDocumentCatalog().getAllPages();
                JFrame fx=new JFrame();
                fx.setSize(400,400);
                fx.setVisible(true);
                PDPage pagex = (PDPage)pages.get( 1 );
                pagex.convertToImage();
                
                
            } catch (Exception e) {
                System.out.println("Unable to open PDF Parser.");
                e.printStackTrace();
            }
        }
        
        
        
    }
    public void proccess(String filename){
    	
        pdftoText(filename);
    }
    //Extracts text from a PDF Document and writes it to a text file
    public static void main(String args[]) {
    	
    	/*
    	if (args.length != 2) {
        	System.out.println("Usage: java PDFTextParser <InputPDFFilename> <OutputTextFile>");
        	System.exit(1);
        }*/
//    	String filein="C:/Documents and Settings/Agustin/Escritorio/prestolite";
    	
    	//String filein="C:/Documents and Settings/Agustin/Escritorio/juan pdf";
    	String filein="C:/Reygoma/HTML/media";
    	//String filein="C:/Documents and Settings/Agustin/Escritorio/juan pdf/LUK Liv Pes 2009-2010.pdf";
    	//String filein="C:/Documents and Settings/Agustin/Escritorio/altamira";
    	//String filein="C:/Documents and Settings/Agustin/Escritorio/altamira/catalogo ford.pdf";
    	//String filein="C:/Documents and Settings/Agustin/Escritorio/prestolite/Catalogo_Baterias.pdf";
    	
    	//C:\Documents and Settings\Agustin\Escritorio\prestolite
    	//C:/Documents and Settings/Agustin/Escritorio/altamira
    	PDFTextParser pdfTextParserObj = new PDFTextParser();
    	//pdfTextParserObj.open(filein+"/Catalogo_Solenoides.pdf", 1);
    	
    	//procesar directorio
    	pdfTextParserObj.list(filein);
    	//pdfTextParserObj.proccess(filein);
    	/*
    	String fileout="e:/indexer/Catalogo Volskwagen 2008.txt";
        PDFTextParser pdfTextParserObj = new PDFTextParser();
        
        String pdfToText = pdfTextParserObj.pdftoText(filein);
        
        if (pdfToText == null) {
        	System.out.println("PDF to Text Conversion failed.");
        }
        else {
        	System.out.println("\nThe text parsed from the PDF Document....\n" + pdfToText);
        	pdfTextParserObj.writeTexttoFile(pdfToText, fileout);
        }*/
    }  
    
    public MySQL getMySQL(){
		MySQL s=new MySQL(null);
		s.setId("MySQL");
		s.setDatabase("test");
		s.setUser("root");
		s.setPassword("ipsilon");
		return s;
	}
    
    public void initConnector(){
    	c=new ConnectionHandler();
		c.addConector(getMySQL());
		c.startConnections();
    }
    
}