package aplicacion.sistema.indexer.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.*;
import javax.swing.JTextField;

import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.indexer.gui._Frame;
import aplicacion.sistema.indexer.logic._Data;
import aplicacion.sistema.indexer.test.PDFTextStripperExtended;
import aplicacion.ventas.catalogo.interfaces._Interface;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private PDFParser parser;
	private String parsedText;
	private PDFTextStripperExtended pdfStripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	private PDDocumentInformation pdDocInfo;
	private String estado = "";
	private int current;
	private int errors;
	private int lenght, max;
	private Timer Timer;
	private boolean debug = false;
	private boolean done = false;
	private boolean canceled = false;
	private boolean override;
	private Crono crono;
	private String carpeta_destino = "e:/indexados/";

	// private String
	// carpeta_destino="//192.168.4.150/windows share/indexados/";

	class _taskUpdate {
		_taskUpdate() {

			_indexar();
		}
	}

	class _taskDelete {
		_taskDelete() {

			_eliminar();
		}
	}

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void clean() {
		frame.get_txt_archivo().setText("");
		frame.setJTable1(null);
	}

	public void focus() {
		frame.get_txt_archivo().requestFocusInWindow();
	}

	public boolean storePDF(String file) {
		File fx = new File(file);
		try {
			PdfReader reader = new PdfReader(file);
			// we retrieve the total number of pages
			int n = reader.getNumberOfPages();
			System.out.println("There are " + n
					+ " pages in the original file.");
			int i = 0;
			lenght = n;

			while (i < n) {
				String destination = "tmp_" + (i + 1) + ".pdf";
				File fz = new File(destination);
				if (fz.exists()) {
					fz.delete();
				}
				current = i;
				boolean ok = this.split(file, destination, i + 1);
				if (ok) {
					ok = this.storePDF(fx.getName(), destination, i + 1);
					fz = new File(destination);
					if (fz.exists()) {
						fz.delete();
					}
				}
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean split(String source_file, String destination1, int pagenumber) {
		boolean ok = true;
		try {
			// we create a reader for a certain document
			PdfReader reader = new PdfReader(source_file);
			// we retrieve the total number of pages
			int n = reader.getNumberOfPages();

			if (pagenumber >= 0) {
				// step 1: creation of a document-object
				Document document1 = new Document(reader
						.getPageSizeWithRotation(pagenumber));
				// step 2: we create a writer that listens to the document
				File f1 = new File(destination1);
				PdfWriter writer1 = PdfWriter.getInstance(document1,
						new FileOutputStream(f1));

				// step 3: we open the document
				document1.open();
				PdfContentByte cb1 = writer1.getDirectContent();
				PdfImportedPage page = null;
				int rotation;
				document1.setPageSize(reader
						.getPageSizeWithRotation(pagenumber));
				document1.newPage();
				page = writer1.getImportedPage(reader, pagenumber);
				rotation = reader.getPageRotation(pagenumber);
				if (rotation == 90 || rotation == 270) {
					cb1.addTemplate(page, 0, -1f, 1f, 0, 0, reader
							.getPageSizeWithRotation(pagenumber).getHeight());
				} else {
					cb1.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
				}

				document1.close();
			}

		} catch (Exception e) {
			ok = false;
			System.out.println("Error Obteniendo pagina=" + pagenumber + " ");
			e.printStackTrace();

		}
		return ok;
	}

	private void crearTabla(Object[][] results) {
		final CustomTable Table = new CustomTable();
		System.out.println("Creando Tabla Archivos");

		Column col = new Column();
		col.setName("");
		col.setWidth(40);
		col.setClass(Boolean.class);
		col.setEditable(false);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this.getConstructor().getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_chk_articulos);
		col.setCellEditor(chkce.getCellCheck());
		Table.addColumn(col);

		col = new Column();
		col.setName("archivo");
		col.setWidth(280);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);

		col = new Column();
		col.setName("proveedor");
		col.setWidth(120);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(180);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);

		col = new Column();
		col.setName("resumen");
		col.setWidth(80);
		col.setClass(String.class);
		col.setEditable(false);
		Table.addColumn(col);
		Table.setData(results);
		Table.build();

		Table.fillData();
		frame.setJTable1(Table.getTable());

	}

	public int getSelected() {
		int selected = 0;
		for (int i = 0; i < frame.getJTable1().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) frame.getJTable1().getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (b)
				selected++;
		}
		return selected;
	}

	public void indexar() {
		this.createTimer();
		this._goUpdate();
	}

	public void eliminar() {
		this.createTimer();
		this._goDelete();
	}

	public void cargar_directorio() {
		String directory = frame.get_txt_archivo().getText();
		File dir = new File(directory);
		List<String> files = new ArrayList<String>();
		String[] children = dir.list();
		if (children == null) {
			// Either dir does not exist or is not a directory
		} else {
			for (int i = 0; i < children.length; i++) {
				// Get filename of file or directory
				String filename = children[i];
				files.add(filename);
				System.out.println(filename);
				// this.proccess(directory+"/"+filename);
			}
		}
		Object[][] results = new Object[files.size()][5];
		for (int i = 0; i < results.length; i++) {
			results[i][0] = false;
			results[i][1] = files.get(i);
			results[i][2] = "";
			results[i][3] = "";
			results[i][4] = "";
		}
		this.crearTabla(results);
	}

	public boolean storePDF(String filename, String source, int page) {
		FileInputStream fis = null;
		PreparedStatement ps = null;
		boolean ok = true;
		String INSERT_PICTURE = "insert into pdf_split(filename,page,split) values (?, ?, ?)";
		File file = new File(source);
		if (file.exists()) {
			if (file.isFile()) {
				try {

					try {
						fis = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {

						ps = data.getConnector("MySQL").prepareStatement(
								INSERT_PICTURE);
						ps.setString(1, filename);
						ps.setInt(2, page);
						ps.setBinaryStream(3, fis, (int) file.length());
						ps.executeUpdate();
						System.out.println("Insercion de imagen:"
								+ file.getName() + ">?");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						ok = false;
					}

				} finally {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				System.out.println(filename + " no existe");
			}

		}

		return ok;
	}

	public boolean storeCompletePDF(String filename) {
		FileInputStream fis = null;
		PreparedStatement ps = null;
		File file = new File(filename);
		estado = "Guardando PDF Completo " + file.getName();

		boolean ok = true;
		String INSERT_PICTURE = "insert into pdf_archivo (filename,data) values (?, ?)";

		if (file.exists()) {
			if (file.isFile()) {
				try {

					try {
						fis = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {

						ps = data.getConnector("MySQL").prepareStatement(
								INSERT_PICTURE);
						ps.setString(1, file.getName());
						ps.setBinaryStream(2, fis, (int) file.length());
						ps.executeUpdate();
						System.out.println("Insercion de imagen:"
								+ file.getName() + ">?");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						ok = false;
					}

				} finally {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				System.out.println(filename + " no existe");
			}

		}

		return ok;
	}

	private void cargarProveedor(String idproveedor) {
		Object[][] results = this.data.getProveedor(idproveedor);
		if (results != null) {
			if (results.length > 0) {

			}
		}
	}

	private aplicacion.herramientas.java.evaluadores.Proveedor proveedor = null;

	public void initialize_proveedor() {
		proveedor = new aplicacion.herramientas.java.evaluadores.Proveedor() {
			public void cargar(String codigo) {
				cargarProveedor(codigo);
			}
		};
		proveedor.setConstructor(this.getConstructor());
	}

	public void BuscarProveedor(JTextField tx) {
		proveedor.Buscar(tx);
	}

	public void BuscarProveedor() {
		// proveedor.Buscar(frame.get_txt_idproveedor());
	}

	public void evaluarProveedor(JTextField tx) {
		proveedor.evaluate(tx);
	}

	public boolean pdftoText(String file, String resumen, String idproveedor,
			String linea) {

		System.out.println("Parsing text from PDF file " + file + "....");
		File f = new File(file);

		if (!f.isFile()) {
			System.out.println("File " + file + " does not exist.");
			return false;
		}

		if (f.exists()) {
			estado = "Guardando PDF Completo " + f.getName();
			boolean ok = this.storeCompletePDF(file);
			estado = "Guardando Por Paginas " + f.getName();
			ok = this.storePDF(file);
			if (ok) {
				try {
					parser = new PDFParser(new FileInputStream(f));
				} catch (Exception e) {
					System.out.println("Unable to open PDF Parser.");
					return false;
				}

				try {
					estado = "Examinando Indice " + f.getName();
					parser.parse();
					cosDoc = parser.getDocument();
					pdfStripper = new PDFTextStripperExtended();
					pdfStripper.setSortByPosition(true);
					// pdfStripper.setStartPage(48);
					// pdfStripper.setEndPage(51);
					pdDoc = new PDDocument(cosDoc);
					pdfStripper.getText(pdDoc);
					estado = "Extrayendo Contenido de Paginas " + f.getName();
					List<String> pages = pdfStripper.getPages();
					estado = "Guardando en Base de Datos " + f.getName();
					for (int i = 0; i < pages.size(); i++) {
						String content = pages.get(i);
						content = content.replaceAll("'", "''");
						String _file = f.getName();
						String q = data.getInsert(_file, i + 1, content,
								resumen, idproveedor, linea);
						data.getConnector("MySQL").clearBatch();
						data.getConnector("MySQL").addBatch(q);
						data.getConnector("MySQL").executeBatch();
					}
				} catch (Exception e) {
					System.out
							.println("An exception occured in parsing the PDF Document.");
					this.displayError("Error Incorporando Archivo", e
							.getMessage(), e.getLocalizedMessage(), e);

					e.printStackTrace();
					try {
						if (cosDoc != null)
							cosDoc.close();
						if (pdDoc != null)
							pdDoc.close();
					} catch (Exception e1) {
						e.printStackTrace();
					}
					return false;
				}
			}

		}

		System.out.println("Done.");
		// aviso("Indexacion Completa");
		try {
			if (cosDoc != null)
				cosDoc.close();
			if (pdDoc != null)
				pdDoc.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return true;
	}

	public void _indexar() {

		String folder = frame.get_txt_archivo().getText();
		this.lenght = this.getSelected();
		for (int i = 0; i < frame.getJTable1().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) frame.getJTable1().getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String filename = (String) frame.getJTable1().getValueAt(i, 1);
			String idproveedor = "";
			String linea = "";
			String resumen = "";
			if (b) {
				current++;
				this.indexar(folder + "/" + filename, idproveedor, linea,
						resumen);
			}

		}
		aviso("Indexacion Completa");
		done = true;

	}

	public void _eliminar() {

		String folder = frame.get_txt_archivo().getText();
		this.lenght = this.getSelected();
		for (int i = 0; i < frame.getJTable1().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) frame.getJTable1().getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String filename = (String) frame.getJTable1().getValueAt(i, 1);

			String idproveedor = "";
			String linea = "";
			String resumen = "";
			if (b) {
				current++;
				estado = "Limpiado Indice " + filename;

				data.getConnector("MySQL").clearBatch();
				String q = data.getDelete(filename);
				System.out.println(q);
				data.getConnector("MySQL").addBatch(q);
				String q1 = data.getDeleteFile(filename);
				System.out.println(q1);
				data.getConnector("MySQL").addBatch(q1);
				String q2 = data.getDeleteSplit(filename);
				System.out.println(q2);
				data.getConnector("MySQL").addBatch(q2);
				data.getConnector("MySQL").executeBatch();
			}

		}
		aviso("Eliminacion Completa");
		done = true;

	}

	public void seleccionar(boolean b) {
		if (frame.getJTable1() != null) {
			for (int i = 0; i < frame.getJTable1().getRowCount(); i++) {
				frame.getJTable1().setValueAt(b, i, 0);
			}
		}

	}

	public void quitar_todos() {
		if (confirmar("Confirme la eliminacion de todos los Catalogos PDF :", 3)) {
			data.getConnector("MySQL").clearBatch();
			String q = data.getDelete("%");
			System.out.println(q);
			data.getConnector("MySQL").addBatch(q);
			String q1 = data.getDeleteFile("%");
			System.out.println(q1);
			data.getConnector("MySQL").addBatch(q1);
			String q2 = data.getDeleteSplit("%");
			System.out.println(q2);
			data.getConnector("MySQL").addBatch(q2);
			boolean error = data.getConnector("MySQL").executeBatch();
			if (!error) {
				aviso("Se elimino correctamente");
			} else {
				error("error eliminando");
			}
		}
	}

	public void indexar(String file, String idproveedor, String linea,
			String contenido) {

		if (file.compareTo("") != 0) {
			File fx = new File(file);
			if (fx.exists()) {
				if (fx.isFile()) {

					this.lenght = frame.getJTable1().getRowCount();
					estado = "Limpiado Indice " + fx.getName();

					data.getConnector("MySQL").clearBatch();
					String q = data.getDelete(fx.getName());
					System.out.println(q);
					data.getConnector("MySQL").addBatch(q);
					String q1 = data.getDeleteFile(fx.getName());
					System.out.println(q1);
					data.getConnector("MySQL").addBatch(q1);
					String q2 = data.getDeleteSplit(fx.getName());
					System.out.println(q2);
					data.getConnector("MySQL").addBatch(q2);
					data.getConnector("MySQL").executeBatch();
					this.pdftoText(file, contenido, idproveedor, linea);

				} else {
					error(file + " no es un archivo");
				}
			} else {
				error("el archivo no existe");
			}
		} else {
			error("debe seleccionar un archivo para indexar");
		}

	}

	public void buscar_archivo() {
		JFileChooser JF = new JFileChooser();
		JF.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int rx = JF.showOpenDialog(frame);
		if (rx == JFileChooser.APPROVE_OPTION) {
			File file = JF.getSelectedFile();
			frame.get_txt_archivo().setText(file.getAbsolutePath());
		}
	}

	private boolean copyfile(String srFile, String dtFile) {
		boolean ok = true;
		try {
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			if (f2.exists()) {
				f2.delete();
			}
			InputStream in = new FileInputStream(f1);

			// For Append the file.
			// OutputStream out = new FileOutputStream(f2,true);

			// For Overwrite the file.
			OutputStream out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			System.out.println("File copied.");
		} catch (FileNotFoundException ex) {
			ok = false;
			System.out
					.println(ex.getMessage() + " in the specified directory.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			ok = false;
		}
		return ok;
	}

	public void _goUpdate() {
		frame.getJProgressBar().setIndeterminate(false);
		this.createTimer();

		frame.get_btn_indexar().setEnabled(false);

		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskUpdate();
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void _goDelete() {
		frame.getJProgressBar().setIndeterminate(false);
		this.createTimer();

		frame.get_btn_indexar().setEnabled(false);

		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskDelete();
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void updateBar() {
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}

	public void endbar() {
		estado = "";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
		frame.get_btn_buscar_archivo().setEnabled(true);
		frame.get_btn_indexar().setEnabled(true);
		frame.get_btn_quitar_indice().setEnabled(true);
	}

	public void createTimer() {
		current = 0;
		errors = 0;
		canceled = false;
		done = false;
		crono = new Crono();
		crono.start();
		Timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done | canceled) {
					endbar();
					Timer.stop();
					canceled = false;
					done = false;
				} else {
					updateBar();
				}
			}
		});

	}

}
