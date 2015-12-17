package aplicacion.sistema.error.logic;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;

import aplicacion.herramientas.java.*;


import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.sistema.error.constructor._Constructor;
import aplicacion.sistema.error.gui.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class _Error extends Logic {
	private _Data data=null;
	private _Frame frame=null;
	private String emailFrom="betacoresystems@gmail.com";
	private String emailTo="agustinwisky@gmail.com";
	private String password="ceci@1985";
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	private String estado="";
	private int current;
	private int lenght;
	private boolean done,canceled;
	private int errors=0;
	private String xml;
	
	public void setFrame(JFrame frame){
		this.frame=(_Frame) frame;
		super.setFrame(frame);
	}
	
	public void setData(Data data){
		this.data=(_Data) data;
		super.setData(data);
	}
	
	
	public void setXML(String xml){
		this.xml=xml;
		
	}
	
	

	
private Properties getProperties(){
    Properties props = new Properties();
    props.setProperty("mail.smtp.host", "smtp.gmail.com");
    props.setProperty("mail.smtp.starttls.enable", "true");
    props.setProperty("mail.smtp.port", "587");
    props.setProperty("mail.smtp.user", emailFrom);
    props.setProperty("mail.smtp.auth", "true");
    return props;
}

public Message getMessage(Session session){
	
	   MimeMessage message = new MimeMessage(session);
    try {
		message.setFrom(new InternetAddress(emailFrom));
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo));
		   String subject="Bug Beta "+this.getConstructor().getIduser()+" "+data.getSystemDate()+" "+frame.get_txt_asunto();
		   message.setSubject(subject);
		   String newline = System.getProperty("line.separator");
		   String _text=frame.getJTextArea1().getText();
		   _text+=_text+newline;
		   _text+=frame.getJTextArea().getText();
		   message.setText(_text);
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return message;
}

public void createScreenImage(){
	BufferedImage screenImg;  //  @jve:decl-index=0:
 Rectangle screenRect;
 Robot robot=null;
	try {
		robot = new Robot();
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
 
 screenRect=new Rectangle(0,0,screenSize.width,screenSize.height);
 if (robot!=null){
 	screenImg=robot.createScreenCapture(screenRect);
 	
 	try {
 			File file=new File( "bug.jpg" );
 			if (file.exists()){
 				file.delete();
 			}
 			System.out.println(file.getAbsolutePath());
			ImageIO.write( screenImg, "JPEG" /* format desired */, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		
 }
 
}

public String getHostData(){
	String newline = System.getProperty("line.separator");
	  InetAddress Ip =null;
      try {
		Ip=InetAddress.getLocalHost();
	} catch (UnknownHostException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	String host="";
	String iduser=this.getConstructor().getIduser();
	host+="iduser:"+iduser+newline;
	host+="time:"+new Convertidor().getDateWithFormat("dd-MM-yyyy hh:mm:ss")+newline;
	host+="Host Name:"+Ip.getHostName()+newline;
	host+="Host Ip:"+Ip.getHostAddress()+newline;
	host+="Host OS:"+data.getOs()+newline;
	String version=(String)data.getParametroSqlite("version")[0][1];
	host+="Beta Version:"+version+newline;
	return host;
	
	
}

private String rmPath(String fName) {
    int pos = fName.lastIndexOf(File.separatorChar);
    if (pos > -1)
      fName = fName.substring(pos + 1);
    return fName;
  }

public boolean compressFile(String source,String target) {
	System.out.println("Compress "+source+" to "+target);
	boolean ok=true;
	try {
		File _target=new File(target);
		if (_target.exists()){
			_target.delete();
		}
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target));
		FileInputStream fis = new FileInputStream(source);
		// put a new ZipEntry in the ZipOutputStream
		zos.putNextEntry(new ZipEntry(rmPath(source)));
		int size = 0;
		byte[] buffer = new byte[1024];

		// read data to the end of the source file and write it to the zip
		// output stream.
		while ((size = fis.read(buffer, 0, buffer.length)) > 0) {
			zos.write(buffer, 0, size);
		}

		zos.closeEntry();
		fis.close();

		// Finish zip process
		zos.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		ok=false;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		ok=false;
	}
	return ok;
}

public Message getMessage2(Session session) throws Exception{
	   MimeMessage msg = new MimeMessage(session);
	   msg.setFrom(new InternetAddress(emailFrom));
	   InternetAddress[] address = {new InternetAddress(emailTo)};
	   msg.setRecipients(Message.RecipientType.TO, address);
	   msg.setSubject("Bug Beta"+frame.get_txt_asunto().getText());
	   String newline = System.getProperty("line.separator");
	   // create and fill the first message part
	   MimeBodyPart mbp1 = new MimeBodyPart();
	   String _mensaje="";
	   _mensaje+="Error:"+newline;
	   _mensaje+=this.getHostData()+newline;
	   _mensaje+=frame.getJTextArea1().getText()+newline;
	   _mensaje+="Comentario:"+newline;
	   _mensaje+=frame.getJTextArea().getText();
	   mbp1.setText(_mensaje);

	   // create the second message part
	   MimeBodyPart mbp2 = new MimeBodyPart();

	         // attach the file to the message
	   File f=new File("bug.jpg");
	   FileDataSource fds = new FileDataSource(f.getAbsoluteFile());
	   mbp2.setDataHandler(new DataHandler(fds));
	   mbp2.setFileName(fds.getName());
	   

	   // create the Multipart and add its parts to it
	   Multipart mp = new MimeMultipart();
	   mp.addBodyPart(mbp1);

	   File _f3=new File("error.log");
	   if (_f3.exists()){
		   this.compressFile("error.log", "error.zip");
		   File f3=new File("error.zip");   
		   if (f3.exists()){
			   System.out.println("Agregando Error.log");
			   try {
				MimeBodyPart mbp4 = new MimeBodyPart();
				   FileDataSource fds4 = new FileDataSource(f3.getAbsoluteFile());
				   System.out.println("Agregando source file "+f3.getAbsoluteFile());
				   mbp4.setDataHandler(new DataHandler(fds4));
				   System.out.println("Agregando file name"+fds4.getName());
				   mbp4.setFileName(fds4.getName());
				   mp.addBodyPart(mbp4);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		   }   
	   }else{
		   System.out.println("source file dosnt't exist "+_f3.getAbsoluteFile());
	   }

	   
	   File _f4=new File("output.log");
	   if (_f4.exists()){
		   this.compressFile("output.log", "output.zip");
		   File f4=new File("output.zip");   
		   if (f4.exists()){
			   System.out.println("Agregando output.log");
			   try {
				MimeBodyPart mbp5 = new MimeBodyPart();
				   FileDataSource fds5 = new FileDataSource(f4.getAbsoluteFile());
				   System.out.println("Agregando source file "+f4.getAbsoluteFile());
				   mbp5.setDataHandler(new DataHandler(fds5));
				   System.out.println("Agregando file name"+fds5.getName());
				   mbp5.setFileName(fds5.getName());
				   mp.addBodyPart(mbp5);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	   }
	      
	   }else{
		   System.out.println("source file dosnt't exist "+_f4.getAbsoluteFile());
	   }
	   
	   // add the Multipart to the message
	   System.out.println("Message Content null?"+mp);
	   msg.setContent(mp);

	   // set the Date: header
	   msg.setSentDate(new Date());
	   
	   // send the message
	   return msg;
	}
public Message getMessage2Old(Session session) throws Exception{
   MimeMessage msg = new MimeMessage(session);
   msg.setFrom(new InternetAddress(emailFrom));
   InternetAddress[] address = {new InternetAddress(emailTo)};
   msg.setRecipients(Message.RecipientType.TO, address);
   msg.setSubject("Bug Beta"+frame.get_txt_asunto().getText());
   String newline = System.getProperty("line.separator");
   // create and fill the first message part
   MimeBodyPart mbp1 = new MimeBodyPart();
   String _mensaje="";
   _mensaje+="Error:"+newline;
   _mensaje+=this.getHostData()+newline;
   _mensaje+=frame.getJTextArea1().getText()+newline;
   _mensaje+="Comentario:"+newline;
   _mensaje+=frame.getJTextArea().getText();
   mbp1.setText(_mensaje);

   // create the second message part
   MimeBodyPart mbp2 = new MimeBodyPart();

         // attach the file to the message
   File f=new File("bug.jpg");
   FileDataSource fds = new FileDataSource(f.getAbsoluteFile());
   mbp2.setDataHandler(new DataHandler(fds));
   mbp2.setFileName(fds.getName());
   
   MimeBodyPart mbp3 = new MimeBodyPart();
   File f2=new File("bug.xml");
   FileDataSource fds2 = new FileDataSource(f2.getAbsoluteFile());
   mbp3.setDataHandler(new DataHandler(fds2));
   mbp3.setFileName(fds2.getName());

   
   
   
   
   
   // create the Multipart and add its parts to it
   Multipart mp = new MimeMultipart();
   mp.addBodyPart(mbp1);
   mp.addBodyPart(mbp2);
   mp.addBodyPart(mbp3);
   
   File f3=new File("error.log");
   if (f3.exists()){
	   System.out.println("Agregando Error.log");
	   try {
		MimeBodyPart mbp4 = new MimeBodyPart();
		   FileDataSource fds4 = new FileDataSource(f3.getAbsoluteFile());
		   System.out.println("Agregando source file "+f3.getAbsoluteFile());
		   mbp4.setDataHandler(new DataHandler(fds4));
		   System.out.println("Agregando file name"+fds4.getName());
		   mbp4.setFileName(fds4.getName());
		   mp.addBodyPart(mbp4);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}   
   }
   
   File f4=new File("output.log");
   if (f4.exists()){
	   System.out.println("Agregando output.log");
	   try {
		MimeBodyPart mbp5 = new MimeBodyPart();
		   FileDataSource fds5 = new FileDataSource(f4.getAbsoluteFile());
		   System.out.println("Agregando source file "+f4.getAbsoluteFile());
		   mbp5.setDataHandler(new DataHandler(fds5));
		   System.out.println("Agregando file name"+fds5.getName());
		   mbp5.setFileName(fds5.getName());
		   mp.addBodyPart(mbp5);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}   
   }
   
   // add the Multipart to the message
   System.out.println("Message Content null?"+mp);
   msg.setContent(mp);

   // set the Date: header
   msg.setSentDate(new Date());
   
   // send the message
   return msg;
}

public void _taskworkEnviar(){
estado="Enviando EMail";
boolean error=false;
frame.getJProgressBar().setIndeterminate(true);
Properties props =this.getProperties();
Session session = Session.getDefaultInstance(props);
Message message=null;
try {
		message = this.getMessage2(session);
} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
}
try {
	if (message!=null){
		Transport t = session.getTransport("smtp");
		t.connect(emailFrom, password);
		t.sendMessage(message, message.getAllRecipients());
		t.close();
		}
} catch (NoSuchProviderException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		error=true;
		
} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		error=true;
	}
done=true;
if (error){
	error("Error enviando email. Guarde el Error Para poder examinarlo en su computadora");
}else{
	aviso("El E-Mail Se Envio Correctamente. ");
	exit_command();
}


}

public void guardar(){
	JFileChooser fc=new JFileChooser();
	fc.showSaveDialog(null);
	File fx=fc.getSelectedFile();
	if (fx!=null){
		boolean ok=this.create_xml_file(xml,fx.getAbsolutePath());
		if (ok){
			this.aviso("Se Grabo Correctamente");
		}else{
			this.error("Error Grabando XML");
		}
	}
	
}
public boolean create_xml_file(String xml,String file_path){
	 boolean ok=true;
	 
	 try {
		///aca va el lector de archivo log de la tarea
		
		File file=new File(file_path);
		file = file.getAbsoluteFile();
		if (file.exists())file.delete();
		file.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write(xml);
		out.close();
	}catch (Exception e){
		ok=false;
	}
	return ok;
}

public void createTimer(){
	crono=new Crono();
	crono.start();
	lenght=0;
	current=0;
	errors=0;
	done = false;
	canceled=false;
	Timer=new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			if (done |canceled){
				endbar();
				Timer.stop();
				}else {
					
				updateBar();
			}
		}
	}); 
	
}


public void updateBar(){
	frame.getJProgressBar().setMaximum(lenght);
	frame.getJProgressBar().setValue(current);
	frame.getJProgressBar().setString(estado+" "+current+"/"+lenght+" "+crono.elapsed());
	frame.getJProgressBar().setStringPainted(true);
}

public void endbar(){
	estado="";
	frame.getJProgressBar().setString("");
	frame.getJProgressBar().setIndeterminate(false);
	frame.getJProgressBar().setValue(0);

}
public void setAsunto(String asunto){
	this.frame.get_txt_asunto().setText(asunto);
}

public void setError(String error){
	this.frame.getJTextArea1().setText(error);
}
public void goEnviar() {
	this.create_xml_file(xml,"bug.xml");
	this.createTimer();
	SwingWorker worker = null;
	if (worker == null) {
		worker = new SwingWorker() {
			public Object construct() {
				return new _taskEnviar();
			}
		};
	}
	if (Timer!=null) {
		Timer.start();
	}
	worker.start();
}
class _taskEnviar {
	_taskEnviar() {
		_taskworkEnviar();
	}
}

public void evaluate_asunto(JTextField tx){
	String value="";
	value=tx.getText();
	if (value.compareTo("")!=0){
		frame.getJTextArea().requestFocusInWindow();
	}else{
		aviso("Por favor ingrese una descripcion breve que describa el problema. Muchas Gracias");
		tx.requestFocusInWindow();
	}
}

public void focus(){
	frame.get_txt_asunto().requestFocusInWindow();
}

}
