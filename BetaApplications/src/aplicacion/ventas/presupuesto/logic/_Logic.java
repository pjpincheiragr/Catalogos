package aplicacion.ventas.presupuesto.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.Timer;

import aplicacion.ventas.presupuesto.interfaces._Interface;
import aplicacion.ventas.presupuesto.gui._Email;
import aplicacion.ventas.presupuesto.gui._Frame;
import aplicacion.ventas.presupuesto.logic._Data;
import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private _Email email = null;
	private String emailFrom = "";
	private String password = "";
	private String email_asunto = "";
	private Timer Timer; // @jve:decl-index=0:
	private Crono crono;

	private String estado = "";
	private int current;	private int lenght;
	private boolean done, canceled;
	private int errors = 0;

	
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
	public void cargarCliente(String idcliente){
		
		Object[][] results=data.getClientInformation(idcliente);
		if (results!=null){
			if (results.length>0){
				String descripcion=(String) results[0][0];
				frame.get_txt_cliente_descripcion().setText(descripcion);
				frame.get_txt_fecha_desde().requestFocusInWindow();
				
			}
		}
	}
	
	private aplicacion.herramientas.java.evaluadores.PedidoCliente PedidoCliente=null;
	public void initialize_PedidoCliente(){
		PedidoCliente=new aplicacion.herramientas.java.evaluadores.PedidoCliente(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_cliente_descripcion().setText(descripcion);
			}
		};
		PedidoCliente.setConstructor(this.getConstructor());
	}
	public void BuscarPedidoCliente(JTextField tx){
		PedidoCliente.Buscar(tx);
	}
	public void BuscarPedidoCliente(){
		PedidoCliente.Buscar(frame.get_txt_idpedido());
	}
	public void buscarPedidoCliente(JTextField tx){
		PedidoCliente.buscar(tx);
	}
	
	public void evaluarPedidoCliente(JTextField tx){
		PedidoCliente.evaluate(tx);
	}


	
	public void buscar_fecha_desde(){
		this.BuscarFecha(frame.get_txt_fecha_desde());
	}

	
	
	public void cargar(){
		reporte();
	}
	
	public void evaluate_fecha_desde(JTextField tx){
		frame.get_txt_fecha_desde().requestFocusInWindow();
	}
	
	public void evaluate_fecha_hasta(JTextField tx){
		cargar();
	}
	
	
	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte=null;
	@SuppressWarnings("unchecked")
	public void reporte(){
		if (reporte!=null){
			 reporte.dispose();
		}
		String empresa=data.getNombreEmpresa();
		String telefono=data.getTelefonoEmpresa();
		String email=data.getEmail();
		String idpedido=frame.get_txt_idpedido().getText();
		String fecha=frame.get_txt_fecha_desde().getText();
		String subtotal=frame.get_txt_subtotal().getText();
		String iva=frame.get_txt_iva().getText();
		String total=frame.get_txt_total().getText();
		String plazo_entrega=frame.get_lst_plazo_de_entrega().getSelectedItem().toString();
		String condicion_venta=frame.get_lst_condicion_de_venta().getSelectedItem().toString();
		String mantenimiento=frame.get_lst_mantenimiento().getSelectedItem().toString();
		
		String observaciones=frame.get_txt_observacion().getText();
		
		String mostrar_marcas="0";
		if(frame.get_chk_marcas().isSelected()){
			mostrar_marcas="1";
		}
		String mostrar_iva="0";
		if(frame.get_chk_iva().isSelected()){
			mostrar_iva="1";
		}
		reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param=new HashMap();
		param.put("idpedido",idpedido);
		param.put("fecha",fecha);
		param.put("subtotal",subtotal);
		param.put("iva",iva);
		param.put("total",total);
		param.put("observacion",observaciones);
		param.put("condicion",condicion_venta);
		param.put("mantenimiento",mantenimiento);
		param.put("plazo_entrega",plazo_entrega);
		param.put("empresa",empresa);
		param.put("telefono",telefono);
		param.put("email",email);
		param.put("mostrar_marcas",mostrar_marcas);
		param.put("mostrar_iva",mostrar_iva);
		reporte.setParameter(_parametros.LookAndFeel,this.getConstructor().getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector,this.getConstructor().getConnectionHandler());
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.parametros, param);
		reporte.setParameter(aplicacion.herramientas.java.ireport.interfaces._parametros.reporte, "PRESUPUESTO.jasper");	
		reporte.build(this.getConstructor());
		reporte.init();	
	}
	                             
	public void cargarPlazodeEntrega(){
		frame.get_lst_plazo_de_entrega().removeAllItems();
		frame.get_lst_plazo_de_entrega().addItem("(*) Sujeto a disponibilidad de stock");
		frame.get_lst_plazo_de_entrega().addItem("Inmediata");
		frame.get_lst_plazo_de_entrega().addItem("24 Horas");
		frame.get_lst_plazo_de_entrega().addItem("24-48 Horas");
		frame.get_lst_plazo_de_entrega().addItem("48-72 Horas");
		frame.get_lst_plazo_de_entrega().addItem("7 Dias");
		frame.get_lst_plazo_de_entrega().addItem("10 Dias");
		frame.get_lst_plazo_de_entrega().addItem("15 Dias");
		frame.get_lst_plazo_de_entrega().addItem("30 Dias");
		frame.get_lst_plazo_de_entrega().addItem("45 Dias");
		frame.get_lst_plazo_de_entrega().addItem("60 Dias");
	}
		
	public void cargarMantenimientoOferta(){
		frame.get_lst_mantenimiento().removeAllItems();
		frame.get_lst_mantenimiento().addItem("(*} Los precios pueden variar sin previo aviso");
		frame.get_lst_mantenimiento().addItem("7 dias");
		frame.get_lst_mantenimiento().addItem("15 dias");
		frame.get_lst_mantenimiento().addItem("20 Dias");
		frame.get_lst_mantenimiento().addItem("30 Dias");
		frame.get_lst_mantenimiento().addItem("45 Dias");
	}
	
	public void cargarCondiciondeVenta(){
		frame.get_lst_condicion_de_venta().removeAllItems();
		frame.get_lst_condicion_de_venta().addItem("Contado Efectivo");
		frame.get_lst_condicion_de_venta().addItem("10 Dias Fecha Factura");
		frame.get_lst_condicion_de_venta().addItem("15 Dias Fecha Factura");
		frame.get_lst_condicion_de_venta().addItem("30 Dias Fecha Factura");
		frame.get_lst_condicion_de_venta().addItem("45 Dias Fecha Factura");
	}
	
	public void CargarPedido(String idpedido){
		frame.get_txt_idpedido().setText(idpedido);
		this.evaluarPedidoCliente(frame.get_txt_idpedido());
	}
	
	public void evaluate_tipo_reporte(JComboBox lst){
		frame.get_txt_idcliente().requestFocusInWindow();
	}
	
	public void clean(){
		frame.get_txt_cliente_descripcion().setText("");
		frame.get_txt_fecha_desde().setText("");
		frame.get_txt_idpedido().setText("");
		frame.get_txt_iva().setText("");
		frame.get_txt_subtotal().setText("");
		frame.get_txt_total().setText("");
		frame.get_txt_idcliente().setText("");
		getToday();
	}
	
	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_fecha_desde().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy"));
	}

	public void exit_command(){
		if (reporte!=null){
			reporte.dispose();	
		}
		super.exit_command();
		
	}
	
	public void Enviar() {
		this.emailFrom=data.getParametroServer("email");
		this.password=data.getParametroServer("email_pass");
		String empresa = data.getNombreEmpresa();
		this.email_asunto="Presupuesto de "+empresa;
		String vendedor=frame.get_txt_vendedor().getText();
		if (emailFrom.compareTo("")!=0 & password.compareTo("")!=0){
			String newline = System.getProperty("line.separator");
			String idpedido = frame.get_txt_idpedido().getText();
				String emailTo = frame.get_txt_email().getText();
				if (emailTo.compareTo("") != 0) {
					
								String cuenta = frame.get_txt_idcliente().getText();
								String cliente_descripcion = frame
										.get_txt_cliente_descripcion().getText();
								String fecha = frame.get_txt_fecha_desde().getText();
								String emailto = frame.get_txt_email().getText();

								
								String telefono = data.getTelefonoEmpresa();
								String _email = data.getEmail();
								//String info = frame.get_txt_observacion().getText();
								String nombre = frame.get_txt_cliente_descripcion()
										.getText();
								if (email != null) {
									email.dispose();
								}
								String _mensaje = "Sr/es "
										+ nombre
										+ ": Adjuntamos un Presupuesto en Formato PDF.";
								_mensaje += newline;
								//_mensaje += info;
								_mensaje += newline;
								_mensaje += "Por Favor comuniquese con nosotros ante cualquier Inquietud.";
								_mensaje += newline;
								
								_mensaje += newline;
								_mensaje += "Muchas Gracias.";
								_mensaje += newline;
								_mensaje += "Saluda Atte. "+vendedor;
								_mensaje += newline;
								_mensaje += newline;
								_mensaje += empresa;
								_mensaje += newline;
								_mensaje += telefono;
								_mensaje += newline;
								_mensaje += _email;
								_mensaje += newline;

								email = new _Email();
								email.setName(_Interface._email);
								email
										.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
								email.addWindowListener(this.getConstructor()
										.getWindowListener());
								this.centrar_frame(email);
								email.get_txt_emailfrom().setText(emailFrom);
								email.get_txt_emailTo().setText(emailto);
								if (email_asunto.compareTo("")==0){
									email_asunto="Pedido de Mercaderia";
								}
								String asunto = this.email_asunto;
								asunto+=" "+frame.get_txt_idpedido().getText();
								email.get_txt_asunto().setText(asunto);
								email.get_txt_mensaje().setText(_mensaje);
								email.get_btn_enviar().setActionCommand(
										_Interface._btn_enviar_email);
								email.get_btn_enviar().addActionListener(
										this.getConstructor().getActionListener());
								email.get_btn_salir().setActionCommand(
										_Interface._btn_close_email);
								email.get_btn_salir().addActionListener(
										this.getConstructor().getActionListener());
								email.setVisible(true);

							
						
					
				}else{
					error("Debe ingresar El email del cliente al cual se debe enviar el Presupuesto");
					frame.get_txt_email().requestFocusInWindow();
				}
			

		}else{
			error("Deben Configurarse Los Parametros de Sistema de E-mail");
		}
	}

	public void enviar_email(){
		String to=email.get_txt_emailTo().getText();
		String asunto=email.get_txt_asunto().getText();
		String mensaje=email.get_txt_mensaje().getText();
		if (to.compareTo("")!=0){
			if (asunto.compareTo("")!=0){
				if (mensaje.compareTo("")!=0){
					if (confirmar(
							"Confirme para enviar el email con el presupuesto a ("
									+ to + "):", 3)) {
						this.goEnviar();	
					}else{
						error("OPERACION CANCELADA");
					}
					
				}else{
					error("El mensaje no puede ser nulo");
				}
			}else{
				error("El asunto no puede ser nulo");
			}
		}
		else{
			error("Complete la direccion de destino para enviar el email");
		}
	}

	public void createTimer() {
		crono = new Crono();
		crono.start();
		lenght = 0;
		current = 0;
		errors = 0;
		done = false;
		canceled = false;
		Timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done | canceled) {
					endbar();
					Timer.stop();
				} else {

					updateBar();
				}
			}
		});

	}
	

	public void endbar() {
		estado = "";
		email.getJProgressBar().setString("");
		email.getJProgressBar().setIndeterminate(false);
		email.getJProgressBar().setValue(0);

	}


	public void updateBar() {
		email.getJProgressBar().setMaximum(lenght);
		email.getJProgressBar().setValue(current);
		email.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		email.getJProgressBar().setStringPainted(true);

	}


	public void goEnviar() {

		this.createTimer();
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskEnviar();
				}
			};
		}
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	class _taskEnviar {
		_taskEnviar() {
			_taskworkEnviar();
		}
	}

	private Properties getProperties() {

		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.user", emailFrom);
		props.setProperty("mail.smtp.auth", "true");
		return props;
	}

	public Message getMessage2(Session session, String emailTo, String file)
			throws Exception {
		MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(emailFrom));
		InternetAddress[] address = { new InternetAddress(emailTo) };
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(email.get_txt_asunto().getText());

		MimeBodyPart mbp1 = new MimeBodyPart();

		String _mensaje = this.email.get_txt_mensaje().getText();
		mbp1.setText(_mensaje);

		// create the second message part
		MimeBodyPart mbp2 = new MimeBodyPart();

		// attach the file to the message

		File f = new File(file);
		FileDataSource fds = new FileDataSource(f.getAbsoluteFile());
		mbp2.setDataHandler(new DataHandler(fds));
		mbp2.setFileName(fds.getName());

		// create the Multipart and add its parts to it
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);
		mp.addBodyPart(mbp2);

		// add the Multipart to the message
		System.out.println("Message Content null?" + mp);
		msg.setContent(mp);

		// set the Date: header
		msg.setSentDate(new Date());

		// send the message
		return msg;
	}

	public boolean preparar_reporte() {
		String emailTo = frame.get_txt_email().getText();
		estado = "Preparando Reporte";
						email.getJProgressBar().setIndeterminate(true);
						String idcomprobante = frame.get_txt_idpedido()
								.getText();

						String idpedido = frame.get_txt_idpedido().getText();
						String empresa = data.getNombreEmpresa();
						String telefono = data.getTelefonoEmpresa();
						String email = data.getEmail();
						//String info = frame.get_txt_observacion().getText();
						aplicacion.herramientas.java.ireport.logic._Logic logic = new aplicacion.herramientas.java.ireport.logic._Logic();
						logic.setConstructor(this.getConstructor());
						String fecha=frame.get_txt_fecha_desde().getText();
						String subtotal=frame.get_txt_subtotal().getText();
						String iva=frame.get_txt_iva().getText();
						String total=frame.get_txt_total().getText();
						String plazo_entrega=frame.get_lst_plazo_de_entrega().getSelectedItem().toString();
						String condicion_venta=frame.get_lst_condicion_de_venta().getSelectedItem().toString();
						String mantenimiento=frame.get_lst_mantenimiento().getSelectedItem().toString();
						
						String observaciones=frame.get_txt_observacion().getText();
						
						String mostrar_marcas="0";
						if(frame.get_chk_marcas().isSelected()){
							mostrar_marcas="1";
						}
						String mostrar_iva="0";
						if(frame.get_chk_iva().isSelected()){
							mostrar_iva="1";
						}
						reporte=new aplicacion.herramientas.java.ireport.constructor._Constructor();
						HashMap param=new HashMap();
						param.put("idpedido",idpedido);
						param.put("fecha",fecha);
						param.put("subtotal",subtotal);
						param.put("iva",iva);
						param.put("total",total);
						param.put("observacion",observaciones);
						param.put("condicion",condicion_venta);
						param.put("mantenimiento",mantenimiento);
						param.put("plazo_entrega",plazo_entrega);
						param.put("empresa",empresa);
						param.put("telefono",telefono);
						param.put("email",email);
						param.put("mostrar_marcas",mostrar_marcas);
						param.put("mostrar_iva",mostrar_iva);
						
						String output = idcomprobante + ".pdf";
						File fx = new File(output);
						if (fx.exists()) {
							try {
								fx.delete();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						boolean ok=true;
						try {
							logic.pdfReport("PRESUPUESTO.jasper", param, output);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							ok=false;
						}
						return ok;
	}

	public void _taskworkEnviar() {
		boolean ok=this.preparar_reporte();
		estado = "Enviando EMail";
		boolean error = false;
		email.getJProgressBar().setIndeterminate(true);
		Properties props = this.getProperties();
		Session session = Session.getDefaultInstance(props);
		Message message = null;
		// message.setFlag(Flag, set)
		String file = frame.get_txt_idpedido().getText() + ".pdf";
		String emailTo = frame.get_txt_email().getText();
		try {
			message = this.getMessage2(session, emailTo, file);
			message.addHeader("Disposition-Notification-To", emailFrom);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		try {
			if (message != null) {
				Transport t = session.getTransport("smtp");
				t.connect(emailFrom, password);
				t.sendMessage(message, message.getAllRecipients());
				t.close();
			}
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		}
		done = true;
		if (error) {
			error("Error enviando email. La falla puede deberse a un problema de internet. Intente nuevamente");
		} else {
			aviso("El E-Mail Se Envio Correctamente. ");
			
			 
			dispose_email();
			exit();
			
		}

	}
	
	public void dispose_email() {
		try {
			this.Timer.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		Timer = null;
		email.setVisible(false);
		email.dispose();

	}
}
