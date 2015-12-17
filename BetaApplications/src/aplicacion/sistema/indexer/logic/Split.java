package aplicacion.sistema.indexer.logic;

import com.itextpdf.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.internal.*;
import java.util.*;
import java.io.*;

public class Split {
    
	/**
	* @param args the command line arguments
	*/
	
	public void split(String source_file,String destination1,int pagenumber){
		try{
			// we create a reader for a certain document
			PdfReader reader = new PdfReader(source_file);
			// we retrieve the total number of pages
			int n = reader.getNumberOfPages();
			System.out.println("There are " + n + " pages in the original file.");
            
			if (pagenumber < 2 || pagenumber > n) {
				throw new DocumentException("You can't split this document at page " + pagenumber + "; there is no such page.");
			}
            
			// step 1: creation of a document-object
			Document document1 = new Document(reader.getPageSizeWithRotation(1));
			// step 2: we create a writer that listens to the document
			File f1=new File(destination1);
			PdfWriter writer1 = PdfWriter.getInstance(document1, new FileOutputStream(f1));
			
			// step 3: we open the document
			document1.open();
			PdfContentByte cb1 = writer1.getDirectContent();
			PdfImportedPage page=null;
			int rotation;
			document1.setPageSize(reader.getPageSizeWithRotation(pagenumber));
			document1.newPage();
			page = writer1.getImportedPage(reader, pagenumber);
			rotation = reader.getPageRotation(pagenumber);
				if (rotation == 90 || rotation == 270) {
					cb1.addTemplate(page, 0, -1f, 1f, 0, 0, reader.getPageSizeWithRotation(pagenumber).getHeight());
				}
				else {
					cb1.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
				}
			
			document1.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void split(String source_file,String destination1,String destination2,int pagenumber){
		
		
			
		try{
			// we create a reader for a certain document
			PdfReader reader = new PdfReader(source_file);
			// we retrieve the total number of pages
			int n = reader.getNumberOfPages();
			System.out.println("There are " + n + " pages in the original file.");
            
			if (pagenumber < 2 || pagenumber > n) {
				throw new DocumentException("You can't split this document at page " + pagenumber + "; there is no such page.");
			}
            
			// step 1: creation of a document-object
			Document document1 = new Document(reader.getPageSizeWithRotation(1));
			Document document2 = new Document(reader.getPageSizeWithRotation(pagenumber));
			// step 2: we create a writer that listens to the document
			File f1=new File(destination1);
			File f2=new File(destination2);
			PdfWriter writer1 = PdfWriter.getInstance(document1, new FileOutputStream(f1));
			PdfWriter writer2 = PdfWriter.getInstance(document2, new FileOutputStream(f2));
			
			// step 3: we open the document
			document1.open();
			PdfContentByte cb1 = writer1.getDirectContent();
			document2.open();
			PdfContentByte cb2 = writer2.getDirectContent();
			PdfImportedPage page=null;
			int rotation;
			int i = 0;
			// step 4: we add content
			while (i < pagenumber - 1) {
				i++;
				document1.setPageSize(reader.getPageSizeWithRotation(i));
				document1.newPage();
				page = writer1.getImportedPage(reader, i);
				rotation = reader.getPageRotation(i);
				if (rotation == 90 || rotation == 270) {
					cb1.addTemplate(page, 0, -1f, 1f, 0, 0, reader.getPageSizeWithRotation(i).getHeight());
				}
				else {
					cb1.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
				}
			}
			while (i < n) {
				i++;
				document2.setPageSize(reader.getPageSizeWithRotation(i));
				document2.newPage();
				page = writer2.getImportedPage(reader, i);
				rotation = reader.getPageRotation(i);
				if (rotation == 90 || rotation == 270) {
					cb2.addTemplate(page, 0, -1f, 1f, 0, 0, reader.getPageSizeWithRotation(i).getHeight());
				}
				else {
					cb2.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
				}
				System.out.println("Processed page " + i);
			}
			// step 5: we close the document
			document1.close();
			document2.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		Split sp=new Split();
		String source_file="e:/indexados/catalogo ford.pdf";
		String destination1="e:/ford1.pdf";
		String destination2="e:/ford2.pdf";
		//sp.split(source_file, destination1, destination2, 10);
		sp.split(source_file, destination1, 50);
	}
    
}