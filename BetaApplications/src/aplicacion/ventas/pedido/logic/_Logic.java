package aplicacion.ventas.pedido.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.ventas.gestion.logic.DateRenderer;
import aplicacion.ventas.gestion.logic.TablePedidosColorCellRenderer;

import aplicacion.ventas.pedido.constructor._Constructor;
import aplicacion.ventas.pedido.gui._Etiquetas;
import aplicacion.ventas.pedido.gui._Relacion;
import aplicacion.ventas.pedido.gui._Faltantes;
import aplicacion.ventas.pedido.gui._Frame;
import aplicacion.ventas.pedido.gui._Remito;
import aplicacion.ventas.pedido.gui._Pedido;
import aplicacion.ventas.pedido.gui._Importar;
import aplicacion.ventas.pedido.interfaces._Interface;

public class _Logic extends Logic {
	private _Data data = null;
	private _Frame frame = null;
	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;
	private aplicacion.herramientas.java.importadores.Articulo bArticulo = null;
	private aplicacion.ventas.catalogo.constructor._Constructor Catalogo = null;
	private aplicacion.herramientas.java.evaluadores.Cliente cliente = null;
	private aplicacion.ventas.presupuesto.constructor._Constructor presupuesto = null;
	private aplicacion.herramientas.java.evaluadores.Provincia Provincia;
	private aplicacion.herramientas.java.ireport.constructor._Constructor reporte = null;
	private aplicacion.herramientas.java.evaluadores.Deposito Deposito = null;
	private aplicacion.herramientas.java.evaluadores.Negocio Negocio = null;
	private aplicacion.herramientas.java.evaluadores.Fecha Fecha = null;
	private aplicacion.cliente.archivo.constructor._Constructor mcliente = null;
	private aplicacion.herramientas.java.evaluadores.PedidoCliente PDC = null;
	private aplicacion.compras.pedidoe.constructor._Constructor pedido = null;
	private aplicacion.ventas.transferencia.constructor._Constructor transferencia = null;
	private aplicacion.inventario.transporte.constructor._Constructor transporte = null;
	private aplicacion.herramientas.java.evaluadores.Transporte Transporte = null;
	private aplicacion.herramientas.java.visualizadores.Articulo vArticulo = null;
	private aplicacion.herramientas.java.visualizadores.ArticuloPrecio vArticuloPrecio = null;
	private aplicacion.herramientas.java.evaluadores.Vendedor Vendedor = null;
	private aplicacion.herramientas.java.visualizadores.Equivalencia vEquivalencia = null;
	private _Pedido pedidoe = null;
	private int errors = 0;
	private int lenght;
	private int current;
	public String equivalencia = "";
	private String estado = "";
	private String tc = "PDC";
	private String validacion = "";
	private String _cliente = "";
	private Crono crono;
	private _Etiquetas etiquetas = null;
	private _Relacion relaciones = null;
	private _Remito remito = null;
	private _Importar importar = null;
	private _Faltantes faltantes = null;
	public boolean check_bloqueos = true;
	public boolean cambios = false;
	public boolean check_su = false;
	private boolean done, canceled;
	public boolean no_a_todo = false;
	private boolean nuevo = true;
	public boolean si_a_todo = false;
	private boolean v2 = false;
	private Timer Timer; // @jve:decl-index=0:

	public boolean _calculate() {

		return this._calculate(-1, false);
	}

	public boolean _calculate(int row, boolean selected) {
		boolean ok = true;

		double items = 0.0;
		double subtotal = 0.0;
		double suma = 0.0;

		double unidades = 0.0;
		double rows = 0;
		double siva = 0.0;
		double iva = 0.0;
		String idcliente = frame.get_txt_idcliente().getText();
		boolean ri = data.esResponsableInscripto(idcliente);
		if (frame.getJTable() != null) {
			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {

				boolean seleccionado = false;

				String _cantidad = "";
				String _idarticulo = "";
				String _precio = "";
				String _descuento = "";
				double cantidad = 0.0;
				double precio = 0.0;
				double descuento = 0.0;
				double importe = 0.0;

				if (i == row) {
					seleccionado = selected;
				} else {
					try {
						seleccionado = (Boolean) frame.getJTable().getValueAt(
								i, _Interface._columna_selected);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				try {
					_idarticulo = frame.getJTable().getValueAt(i,
							_Interface._columna_idarticulo).toString();
				} catch (Exception e) {

				}
				if (_idarticulo.compareTo("") != 0) {
					try {
						_cantidad = frame.getJTable().getValueAt(i,
								_Interface._columna_cantidad).toString();
						_precio = frame.getJTable().getValueAt(i,
								_Interface._columna_precio).toString();
						_descuento = frame.getJTable().getValueAt(i,
								_Interface._columna_descuento).toString();
						_cantidad = _cantidad.replace(",", "");
						_precio = _precio.replace(",", "");
						_descuento = _descuento.replace(",", "");

						cantidad = new Double(_cantidad);
						precio = new Double(_precio);
						descuento = new Double(_descuento);
						if (descuento > 0 & descuento <= 99.99) {
							System.out.println(i + " dsc=" + descuento);
							precio = precio * (100 - descuento) / 100;
							System.out.println(i + " precio=" + precio);
							importe = cantidad * precio;

						} else {
							importe = cantidad * precio;
						}

					} catch (Exception e) {
						ok = true;
					}
					if (importe > 0) {
						rows++;
					}
					if (seleccionado) {
						suma += importe;
						unidades += cantidad;
					}

				}

			}

			siva = suma / 1.21;
			iva = suma - siva;
			/*
			 * if (ri){ siva=suma/1.21; iva=suma-siva; }else{ siva=suma; iva=0;
			 * }
			 */
		}
		Convertidor c = new Convertidor();
		String _unidades = c.getMoney(unidades, 2);
		frame.get_txt_items().setText(_unidades);
		String _subtotal = c.getMoney(siva, 2);
		frame.get_txt_subtotal().setText(_subtotal);
		String _iva = c.getMoney(iva, 2);
		frame.get_txt_iva().setText(_iva);
		String total = c.getMoney(suma, 2);
		frame.get_txt_total().setText(total);

		return ok;
	}

	public double _calculate_descuento(JTextField tx) {
		String _descuento = tx.getText();
		_descuento = _descuento.replace(",", "");
		double dsc = 0.0;

		if (!_descuento.contains("+")) {
			try {
				dsc = new Double(_descuento);
			} catch (Exception e) {

			}
		} else {
			dsc = this.formula(_descuento).doubleValue();
		}
		return dsc;
	}

	public void _copia(String idpedido, String idremito) {
		if (reporte != null) {
			reporte.dispose();
		}

		reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param = new HashMap();
		param.put("idpedido", idpedido);
		param.put("idremito", idremito);
		reporte.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler().Clone());
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.parametros,
						param);
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.reporte,
						"pdc_remito.jasper");
		reporte.build(this.getConstructor());
		reporte.init();
	}

	public void _retiro(String idpedido, String idremito) {
		if (reporte != null) {
			reporte.dispose();
		}

		reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param = new HashMap();
		param.put("idpedido", idpedido);
		param.put("idremito", idremito);
		reporte.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler().Clone());
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.parametros,
						param);
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.reporte,
						"pdc_retiro.jasper");
		reporte.build(this.getConstructor());
		reporte.init();
	}

	public void _delete_item_articulo(JTextField tx, int row) {
		String value = tx.getText();
		if (value.compareTo("") == 0) {
			this.borrarRenglon(row);
		}
	}

	public void _eliminar_pedido(String idpedido, String iduser) {
		boolean ok = true;
		Object[][] results = data.getRemitos(idpedido);
		data.beginTransaction();
		data.clearBatch();
		if (results != null) {
			if (results.length > 0) {
				int i = 0;
				while (i < results.length & ok) {
					String remito = (String) results[i][1];
					if (remito.compareTo("") != 0
							& remito.compareTo("NULL") != 0) {
						ok = this.puede_eliminar_remito(remito);
						if (ok) {
							List<String> batchlist = this
									.getInstruccionesEliminarRemito(remito);
							for (int j = 0; j < batchlist.size(); j++) {
								data.addBatch(batchlist.get(j));
								sacar_remito_de_tabla(remito);
							}

						}
					}

					i++;
				}

			}
		}
		if (ok) {
			data.addBatch(data.getEliminar(idpedido));
			boolean error = data.executeBatch();
			if (!error) {
				String idoperacion = "" + data.getProximoOperacion();
				List<String> instrucciones = this
						.getInstruccionesGuardarEncabezado(idoperacion,
								"ELIMINAR", iduser);
				List<String> instrucciones_items = this
						.getInstruccionesGuardarItems(idoperacion);
				data.clearBatch();
				for (int i = 0; i < instrucciones.size(); i++) {
					System.out.println(instrucciones.get(i));
					data.addBatch(instrucciones.get(i));
				}

				for (int i = 0; i < instrucciones_items.size(); i++) {
					data.addBatch(instrucciones_items.get(i));
					System.out.println(instrucciones_items.get(i));
				}
				error = data.executeBatch();

			}

			if (!error) {
				ok = true;
				data.commitTransaction();
				aviso("Pedido eliminado Correctamente");
				frame.get_btn_guardar().setEnabled(!ok);
				frame.get_btn_preparar().setEnabled(!ok);
				frame.get_btn_transferir().setEnabled(!ok);
				frame.get_btn_aviso().setEnabled(!ok);
				frame.get_btn_presupuesto().setEnabled(!ok);
				frame.get_btn_remito().setEnabled(!ok);
				frame.get_btn_envio().setEnabled(!ok);
				frame.get_btn_eliminar().setEnabled(!ok);
				this.exit_command();
			} else {
				data.rollbackTransaction();
				error("no se pudo eliminar el pedido");
			}

		}

	}

	public void _envio() {
		if (reporte != null) {
			reporte.dispose();
		}
		String idpedido = frame.get_txt_idpedido().getText();
		String empresa = data.getNombreEmpresa();
		String telefono = data.getTelefonoEmpresa();
		String email = data.getEmail();
		String fecha = frame.get_txt_fecha().getText();
		reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();

		HashMap param = new HashMap();
		param.put("idpedido", idpedido);
		param.put("fecha", fecha);
		param.put("empresa", empresa);
		param.put("telefono", telefono);
		param.put("email", email);
		reporte.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler().Clone());
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.parametros,
						param);
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.reporte,
						"rotulo.jasper");
		reporte.build(this.getConstructor());
		reporte.init();
		this.getConstructor().addChild(reporte);
	}

	public void _eval_item_articulo(JTextField tx, int row) {
		String aux = "";
		cambios = true;

		try {
			aux = tx.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		tx.requestFocusInWindow();
		if (aux.compareTo("") != 0) {
			if (aux.compareTo("*") == 0) {
				complete_asterisco(row);
				frame.getJTable().changeSelection(row,
						_Interface._columna_descripcion, false, false);
				frame.getJTable().editCellAt(row,
						_Interface._columna_descripcion);
				frame.getJTable().transferFocus();

			} else {
				if (aux.length() > 4) {
					if (aux.substring(3, 4).compareTo("-") == 0) {
						_evaluate_idarticulo(tx, row);
					} else {
						this.buscarArticulo(tx);
					}
				} else {
					this.buscarArticulo(tx);

				}

			}
		} else {

			tx.setText("*");
			tx.requestFocusInWindow();

		}
	}

	public void _eval_item_cantidad(JTextField tx, int row) {
		cambios = true;
		String cantidad = tx.getText();
		cantidad = cantidad.replace(",", "");
		double qty = 0.0;
		boolean error = false;
		try {
			qty = new Double(cantidad);
		} catch (Exception e) {
			error = true;
		}
		if (!error) {
			if (qty == 0) {
				this.eval_variables_from_cantidad(row, "1.0");
				tx.setText("1.0");
				tx.requestFocusInWindow();

			} else {
				try {
					frame.getJTable().setValueAt(true, row,
							_Interface._columna_selected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tx.setText(new Convertidor().getMoney(qty, 2));
				int next = _Interface._columna_precio;
				this.eval_variables_from_cantidad(row, cantidad);
				if (frame.get_chk_costo().isSelected()) {
					next = _Interface._columna_costo;
				}
				frame.getJTable().changeSelection(row, next, false, false);
				frame.getJTable().editCellAt(row, next);
				frame.getJTable().transferFocus();
			}
		} else {
			this.eval_variables_from_cantidad(row, "1.0");
			tx.setText("1.0");
			tx.requestFocusInWindow();
			// error("error en cantidad");
		}
		this._calculate();
	}

	public void _eval_item_costo(JTextField tx, int row) {
		String _precio = tx.getText();

		_precio = _precio.replaceAll(",", "");
		if (_precio.compareTo("") == 0) {
			_precio = "0.0";
		}
		double prc = 0.0;
		boolean error = false;
		try {
			prc = new Double(_precio);
		} catch (Exception e) {
			error = true;
		}
		if (!error) {
			if (prc >= 0) {
				Convertidor Cv = new Convertidor();
				tx.setText(Cv.getMoney(prc, 2));
				frame.getJTable().changeSelection(row,
						_Interface._columna_precio, false, false);
				frame.getJTable().editCellAt(row, _Interface._columna_precio);
				frame.getJTable().transferFocus();
				this._calculate();
			} else {
				error("el precio debe ser mayor igual a cero");
			}
		} else {
			error("error en precio");
		}

	}

	public void _eval_item_descripcion(JTextField tx, int row) {
		String desc = tx.getText();
		cambios = true;
		if (desc.compareTo("") != 0) {
			frame.getJTable().changeSelection(row,
					_Interface._columna_cantidad, false, false);
			frame.getJTable().editCellAt(row, _Interface._columna_cantidad);
			frame.getJTable().transferFocus();
		} else {
			error("la descripcion no puede ser nula");
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}

	public void _eval_item_descuento(JTextField tx, int row) {
		cambios = true;
		String descuento = tx.getText();
		descuento = descuento.replace(",", "");
		double dsc = 0.0;
		boolean error = false;
		try {
			String _cantidad = frame.getJTable().getValueAt(row,
					_Interface._columna_cantidad).toString();
			String _precio = frame.getJTable().getValueAt(row,
					_Interface._columna_precio).toString();
			_cantidad.replace(",", "");
			_precio = _precio.replace(",", "");
		} catch (Exception e) {

		}
		if (!descuento.contains("+")) {
			try {
				dsc = new Double(descuento);
			} catch (Exception e) {

			}
		} else {
			dsc = this.formula(descuento).doubleValue();
		}

		if (!error) {
			if (dsc >= 0 & dsc <= 99.99) {
				descuento = "" + dsc;
				tx.setText(new Convertidor().getMoney(descuento, 2));
				this.eval_variables_from_descuento(row, descuento);

				DefaultTableModel model = (DefaultTableModel) this.frame
						.getJTable().getModel();
				if (row == model.getRowCount() - 1) {
					model.setRowCount(model.getRowCount() + 1);
					model.setValueAt(true, model.getRowCount() - 1,
							_Interface._columna_selected);
					model.setValueAt(model.getRowCount(),
							model.getRowCount() - 1, _Interface._columna_item);
				} else {

				}

				frame.getJTable().changeSelection(row + 1,
						_Interface._columna_idarticulo, false, false);
				frame.getJTable().editCellAt(row + 1,
						_Interface._columna_idarticulo);
				frame.getJTable().transferFocus();
				this._calculate();
			} else {
				tx.setText("0.0");
				this.eval_variables_from_descuento(row, "0.0");
				tx.requestFocusInWindow();

			}
		} else {
			this.eval_variables_from_descuento(row, "0.0");
			tx.setText("0.0");
			tx.requestFocusInWindow();
		}
		if (error) {
			tx.requestFocusInWindow();
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
		}
	}

	public void _eval_item_precio_historico(JTextField tx, int row, int col) {
		String idarticulo = frame.getJTable().getValueAt(row,
				_Interface._columna_idarticulo).toString();
		tx.requestFocusInWindow();

	}

	public void _eval_item_precio(JTextField tx, int row) {
		cambios = true;
		String idcliente = frame.get_txt_idcliente().getText();
		String precio = tx.getText();
		precio = precio.replaceAll(",", "");
		String idarticulo = frame.getJTable().getValueAt(row,
				_Interface._columna_idarticulo).toString();
		if (precio.compareTo("") == 0) {
			if (idarticulo != null) {
				if (idarticulo.compareTo("") != 0) {
					this.buscarArticuloPrecio(tx, idarticulo);

				}
			}
		} else {
			double prc = 0.0;
			boolean error = false;
			try {
				prc = new Double(precio);
			} catch (Exception e) {
				error = true;
			}

			if (!error) {
				if (prc >= 0) {

					boolean publico = data.utilizaPrecioPublico(idcliente);
					double sys_prc = 0.0;
					if (publico) {
						sys_prc = data.getPrecioPublico(idarticulo);
					} else {
						sys_prc = data.getPrecioCosto(idarticulo);
					}

					if (sys_prc - prc > 0.1) {
						error("Precio " + prc + " Menor al de Sistema "
								+ sys_prc);
					}
					Convertidor Cv = new Convertidor();
					tx.setText(Cv.getMoney(prc, 2));
					frame.getJTable().changeSelection(row,
							_Interface._columna_descuento, false, false);
					frame.getJTable().editCellAt(row,
							_Interface._columna_descuento);
					frame.getJTable().transferFocus();
					this.eval_variables_from_precio(row, precio);
					this._calculate();
				} else {
					this.eval_variables_from_precio(row, "0.0");
					error("el precio debe ser mayor a cero");

				}
			} else {
				this.eval_variables_from_precio(row, "0.0");
				error("error en precio");

			}

		}

	}

	public void _eval_item_total(JTextField tx, int row) {
		cambios = true;
		DefaultTableModel model = (DefaultTableModel) this.frame.getJTable()
				.getModel();
		if (row == model.getRowCount() - 1) {
			model.setRowCount(model.getRowCount() + 1);
			model.setValueAt(model.getRowCount(), model.getRowCount() - 1,
					_Interface._columna_item);

		}
		this._calculate();
		frame.getJTable().changeSelection(row + 1,
				_Interface._columna_idarticulo, false, false);
		frame.getJTable().editCellAt(row + 1, _Interface._columna_idarticulo);
		frame.getJTable().transferFocus();

	}

	public void _evaluate_idarticulo(JTextField tx, int row) {
		cambios = true;
		String idarticulo = tx.getText();
		if (idarticulo.compareTo("") != 0) {
			Object[][] results = data.getArticulo(idarticulo);
			boolean exist = false;
			boolean bloqueado = false;
			String idcliente = frame.get_txt_idcliente().getText();
			boolean publico = data.utilizaPrecioPublico(idcliente);
			int equiv = 0;
			if (results != null) {
				if (results.length > 0) {
					exist = true;
					String suspendidov = (String) results[0][4];
					String stock = (String) results[0][6];
					double _stock = 0.0;
					try {
						_stock = new Double(stock);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					boolean ok = true;
					if (_stock <= 0 | suspendidov.compareTo("1") == 0) {

						if (idarticulo.compareTo(equivalencia) != 0) {
							equivalencia = idarticulo;
							System.out.println("Buscar Equivalencia para "
									+ idarticulo);
							ok = false;
							equiv = this.buscarEquivalencia(tx);
							if (equiv == 0) {
								ok = true;
							}
						} else {
							ok = true;
						}
					} else {
						equivalencia = "";
					}

					if (!ok) {
					} else {
						equivalencia = "";

						tx.setText(idarticulo.toUpperCase());
						String descripcion = (String) results[0][0];
						String precio = (String) results[0][1];
						String costo = (String) results[0][2];
						if (costo.compareTo("") == 0) {
							costo = "0.0";
						}
						if (precio.compareTo("") == 0) {
							precio = "0.0";
						}
						boolean empty = true;
						try {
							empty = frame.getJTable().getValueAt(row,
									_Interface._columna_descripcion).toString()
									.compareTo("") == 0;
						} catch (Exception e) {

						}
						boolean doit = true;
						String cantidad = "";
						try {
							cantidad = frame.getJTable().getValueAt(row,
									_Interface._columna_cantidad).toString();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String descuento = "";
						try {
							descuento = frame.getJTable().getValueAt(row,
									_Interface._columna_descuento).toString();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						double _precio = 0.0;
						double _costo = 0.0;
						double _cantidad = 0.0;
						double _descuento = 0.0;
						double _total = 0.0;
						try {
							if (descuento != null) {
								_descuento = new Double(descuento.replaceAll(
										",", ""));
							}
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							if (cantidad != null) {
								if (cantidad.compareTo("") != 0) {
									_cantidad = new Double(cantidad.replaceAll(
											",", ""));
								}

							}
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String total = "";
						if (!publico) {
							_costo = new Double(costo);
							_precio = _costo * 1.21;
							_precio = _precio * (100 - _descuento) / 100;
							precio = new Convertidor().getMoney(_precio, 2);

							_total = _precio * _cantidad;
							total = new Convertidor().getMoney(_total, 2);
						} else {
							_precio = new Double(precio);
							_precio = _precio * (100 - _descuento) / 100;
							_total = _precio * _cantidad;
							total = new Convertidor().getMoney(_total, 2);
						}

						if (_precio <= 0) {
							aviso("Precio Nulo.Verifique Precio de Articulo");
						}
						if (doit) {
							frame.getJTable().setValueAt(descripcion, row,
									_Interface._columna_descripcion);
							if (cantidad.compareTo("") == 0) {
								frame.getJTable().setValueAt("1.0", row,
										_Interface._columna_cantidad);
							}

							frame.getJTable().setValueAt(
									new Convertidor().getMoney(costo, 2), row,
									_Interface._columna_costo);
							frame.getJTable().setValueAt(
									new Convertidor().getMoney(precio, 2), row,
									_Interface._columna_precio);
							frame.getJTable().setValueAt(
									new Convertidor().getMoney(_descuento, 2),
									row, _Interface._columna_descuento);
							frame.getJTable().setValueAt(
									new Convertidor().getMoney(total, 2), row,
									_Interface._columna_total);
						} else {
							frame.getJTable().changeSelection(row,
									_Interface._columna_descripcion, false,
									false);
							frame.getJTable().editCellAt(row,
									_Interface._columna_descripcion);
							frame.getJTable().transferFocus();
						}
						this._calculate();
						fillExtra(idarticulo);

					}

				}
			}

			if (exist) {
				if (bloqueado) {

				} else {
					if (equiv <= 0) {
						frame.getJTable().changeSelection(row,
								_Interface._columna_cantidad, false, false);
						frame.getJTable().editCellAt(row,
								_Interface._columna_cantidad);
						frame.getJTable().transferFocus();
					}

				}

			} else {

				if (preguntar("confirmar", "El articulo " + idarticulo
						+ " no existe. ")) {
					tx.requestFocusInWindow();
					tx.setSelectionStart(0);
					tx.setSelectionEnd(tx.getText().length());
				} else {
					tx.requestFocusInWindow();
					tx.setSelectionStart(0);
					tx.setSelectionEnd(tx.getText().length());
				}

			}

		}
	}

	public boolean _generar_remito() {

		data.beginTransaction();
		List<String> instrucciones = this
				.getInstruccionesGuardarEncabezadoRemito();
		List<String> instrucciones_puntero = this
				.getInstruccionesActualizarPunterosRemito();
		List<String> instrucciones_items = this
				.getInstruccionesGuardarRemitoItems();
		List<String> instrucciones_control = this.getInstruccionesControl();
		data.clearBatch();
		for (int i = 0; i < instrucciones.size(); i++) {
			System.out.println(instrucciones.get(i));
			data.addBatch(instrucciones.get(i));
		}
		for (int i = 0; i < instrucciones_puntero.size(); i++) {
			System.out.println(instrucciones_puntero.get(i));
			data.addBatch(instrucciones_puntero.get(i));
		}
		for (int i = 0; i < instrucciones_items.size(); i++) {
			System.out.println(instrucciones_items.get(i));
			data.addBatch(instrucciones_items.get(i));
		}
		for (int i = 0; i < instrucciones_control.size(); i++) {
			System.out.println(instrucciones_control.get(i));
			data.addBatch(instrucciones_control.get(i));
		}
		boolean error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
		} else {
			data.rollbackTransaction();
		}
		return error;

	}

	/*
	 * private aplicacion.herramientas.java.evaluadores.Condicion
	 * Condicion=null; public void initialize_Condicion(){ Condicion=new
	 * aplicacion.herramientas.java.evaluadores.Condicion(){ public void
	 * cargar(String codigo){ Object[][] results=this.getInfo(codigo); String
	 * descripcion=(String) results[0][1]; String cod=(String) results[0][0];
	 * frame.get_txt_idcondicion().setText(cod);
	 * frame.get_txt_condicion_detalle().setText(descripcion); guardar(); }
	 * 
	 * public void completar(String codigo){ Object[][]
	 * results=this.getInfo(codigo); String descripcion=(String) results[0][1];
	 * String cod=(String) results[0][0];
	 * frame.get_txt_idcondicion().setText(cod);
	 * frame.get_txt_condicion_detalle().setText(descripcion);
	 * 
	 * } }; Condicion.setConstructor(this.getConstructor()); } public void
	 * BuscarCondicion(JTextField tx){ Condicion.Buscar(tx); } public void
	 * BuscarCondicion(){ Condicion.Buscar(frame.get_txt_idcondicion()); }
	 * public void buscarCondicion(JTextField tx){ Condicion.buscar(tx); }
	 * 
	 * public void evaluarCondicion(JTextField tx){ Condicion.evaluate(tx); }
	 */

	public boolean _guardar(String operacion, String iduser) {
		boolean error = true;
		boolean ok = true;

		String idcliente = frame.get_txt_idcliente().getText();
		String idpedido = frame.get_txt_idpedido().getText();
		if (idcliente.compareTo("") != 0) {
			if (cliente.existe(idcliente)) {
				ok = true;
			} else {
				ok = false;
				error("Cuenta de Cliente Incorrecta. Verifique");
				frame.get_txt_idcliente().requestFocusInWindow();
			}
		} else {
			ok = false;
			error("Cuenta de Cliente Incorrecta. Verifique");
			frame.get_txt_idcliente().requestFocusInWindow();
		}
		if (ok) {
			ok = this.check_deposito();
			if (!ok) {
				error("Error idDeposito Inexistente");
			}
		}
		if (ok) {
			String idvendedor = frame.get_txt_idvendedor().getText();
			if (idvendedor.compareTo("") != 0) {
				if (this.Vendedor.existe(idvendedor)) {
					ok = true;
				} else {
					ok = this.elegir_vendedor();
					if (!ok) {
						frame.get_txt_idvendedor().requestFocusInWindow();
						error("Codigo de Vendedor Incorrecto. Verifique");
					}

				}
			} else {
				ok = this.elegir_vendedor();
				if (!ok) {
					frame.get_txt_idvendedor().requestFocusInWindow();
					error("Codigo de Vendedor Incorrecto. Verifique");
				}
			}
		}

		if (ok) {
			String fecha = frame.get_txt_fecha().getText();
			if (fecha.compareTo("") != 0) {
				ok = this.Fecha.esCorrecta(fecha);
				if (ok) {
					if (nuevo) {
						String sistema = data.getSystemDate();
						if (fecha.compareTo(sistema) != 0) {
							aviso("La Fecha de Agenda del Pedido se Ajustara a "
									+ sistema);
							frame.get_txt_fecha().setText(sistema);
						}
					}
				} else {
					error("Fecha de Agenda Incorrecta. Verifique");
					frame.get_txt_fecha().requestFocusInWindow();
					ok = false;
				}
			} else {
				error("Fecha de Agenda Incorrecta. Verifique");
				frame.get_txt_fecha().requestFocusInWindow();
				ok = false;
			}

		}
		if (ok) {
			ok = this.check_articulos();
			if (!ok) {
				if (this.nuevo) {
					if (frame.getJTable() != null) {
						ok = frame.getJTable().getRowCount() <= 1;
					} else {
						ok = true;
					}
				} else {
					error("Error en Articulos. Verifique");
				}

			}
		}

		if (ok) {
			if (idpedido.compareTo("") != 0) {
				if (!this.nuevo) {
					String modificado = frame.get_txt_modificado().getText();
					String modificacion = data.getUltimaModificacion(idpedido);
					if (modificado.compareTo(modificacion) != 0) {
						String NEW_LINE = System.getProperty("line.separator");
						String msg = "Advertencia: El pedido fue modificado ("
								+ modificacion
								+ ") posiblemente desde otra ubicacion";
						msg += NEW_LINE;
						msg += "Se Recomienda Verificar La Informacion Modificada antes de Confirmar la grabacion.";
						msg += NEW_LINE;
						msg += "Confirme para modificarlo de todas formas :";
						ok = confirmar(msg, 2);
					}
				}
			}
		}

		if (ok) {
			String descripcion = frame.get_txt_pedido_descripcion().getText();
			if (descripcion.compareTo("") != 0) {
				ok = true;
			} else {
				error("Ingrese una descripcion para este pedido");
				frame.get_txt_pedido_descripcion().requestFocusInWindow();
				ok = false;
			}

		}
		if (ok) {
			String descripcion = frame.get_txt_cliente_descripcion().getText();
			if (descripcion.compareTo("") != 0) {
				ok = true;
			} else {
				error("Ingrese el nombre de cliente para este pedido");
				frame.get_txt_pedido_descripcion().requestFocusInWindow();
				ok = false;
			}
		}
		if (ok) {
			error = this._guardar_post(operacion, iduser);
		}
		return error;
	}

	public boolean _guardar_post(String operacion, String iduser) {
		boolean error = true;
		String idpedido = frame.get_txt_idpedido().getText();

		String owner = data.getOwner(idpedido);
		String idvendedor = frame.get_txt_idvendedor().getText();
		String iddeposito=data.getIdDeposito(iduser);
		String negocio=data.getNegocioId(iduser);
		if (owner.compareTo(idvendedor) == 0 | !v2) {
			data.beginTransaction();
			if (nuevo) {
				String numero = frame.get_txt_idpedido().getText();
				int times = 0;
				boolean existe = true;
				while (existe) {
					this.obtener_proximo_cpte();
					numero = frame.get_txt_idpedido().getText();
					existe = data.existe(numero);
					if (existe & times > 5) {
						// aviso("El sistema ajusto el numero de pedido para poder grabar sin riesgos");
						data.updateTC("PDC",negocio);
						times = 0;
					}
					times++;
				}

				boolean seguimiento = frame.get_chk_seguimiento().isSelected();
				String info = frame.get_txt_informacion().getText();
				if (!seguimiento & info.compareTo("") != 0) {
					seguimiento = preguntar("Confirmar",
							"Se detecto informacion adicional. Quiere tener un seguimiento de este Pedido?");
					if (seguimiento) {
						frame.get_chk_seguimiento().setSelected(true);
					}
				}
			}
			String idoperacion = "" + data.getProximoOperacion();

			String registra_operacion = ""
					+ data
							.getOperacion(idoperacion, iduser, "PDC "
									+ operacion);
			List<String> instrucciones = this
					.getInstruccionesGuardarEncabezado(idoperacion, operacion,
							iduser);
			instrucciones.add(registra_operacion);
			List<String> instrucciones_items = this
					.getInstruccionesGuardarItems(idoperacion);

			data.clearBatch();
			for (int i = 0; i < instrucciones.size(); i++) {
				System.out.println(instrucciones.get(i));
				data.addBatch(instrucciones.get(i));
			}

			for (int i = 0; i < instrucciones_items.size(); i++) {
				data.addBatch(instrucciones_items.get(i));
				System.out.println(instrucciones_items.get(i));
			}
			if (nuevo) {
				List<String> instrucciones_punteros = this
						.getInstruccionesActualizarPunteros();
				for (int i = 0; i < instrucciones_punteros.size(); i++) {
					System.out.println(instrucciones_punteros.get(i));
					data.addBatch(instrucciones_punteros.get(i));
				}
			}

			error = data.executeBatch();
			if (!error) {
				data.commitTransaction();
				cambios = false;
				nuevo = false;
				this.update_modificado();
				frame.get_btn_eliminar().setEnabled(true);
			} else {
				data.rollbackTransaction();
			}
		} else {
			error("Este Pedido pertenece a otro Usuario");
			this.cambios = false;
			error = true;
		}

		return error;
	}

	public void _pedir() {
		String idproveedor = "211010029";

		Object[][] results = data.getUltimoPedido(idproveedor);
		String idpedido = "";
		String fecha = "";
		if (results != null) {
			if (results.length > 0) {
				idpedido = (String) results[0][0];
				fecha = (String) results[0][1];
			}
		}
		boolean editar = false;
		if (idpedido.compareTo("") != 0) {
			int n = this.preguntar("Confirme", "Seleccione su opcion",
					new String[] { "Agrega al Pedido Existente " + idpedido,
							"Crea un pedido nuevo" },
					"Agrega al Pedido Existente " + idpedido);
			if (n == 0) {
				editar = true;
			}
		}
		if (editar) {
			this.editar_pedido(idpedido);
		} else {
			nuevo_pedido();
		}
	}

	public void _preparar() {
		if (reporte != null) {
			reporte.dispose();
		}
		String idpedido = frame.get_txt_idpedido().getText();

		reporte = new aplicacion.herramientas.java.ireport.constructor._Constructor();
		HashMap param = new HashMap();
		param.put("idpedido", idpedido);
		reporte.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		reporte.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler().Clone());
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.parametros,
						param);
		reporte
				.setParameter(
						aplicacion.herramientas.java.ireport.interfaces._parametros.reporte,
						"pdc_preparar.jasper");
		reporte.build(this.getConstructor());
		reporte.init();
	}

	public void _presupuesto() {
		String idcliente = frame.get_txt_idcliente().getText();
		String idpedido = frame.get_txt_idpedido().getText();
		String cliente_descripcion = frame.get_txt_cliente_descripcion()
				.getText();
		String subtotal = frame.get_txt_subtotal().getText();
		String iva = frame.get_txt_iva().getText();
		String total = frame.get_txt_total().getText();
		String email = frame.get_txt_email().getText();
		String vendedor = frame.get_txt_vendedor_descripcion().getText();
		boolean ok = this.check_seleccion_del_pedido();
		if (ok) {
			if (presupuesto != null) {
				presupuesto.dispose();
			}

			boolean ri = data.esResponsableInscripto(idcliente);

			presupuesto = new aplicacion.ventas.presupuesto.constructor._Constructor();
			presupuesto.setParameter(_parametros.LookAndFeel, this
					.getConstructor().getLookAndFeelTheme());
			presupuesto.setParameter(_parametros.connector, this
					.getConstructor().getConnectionHandler());
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.idcliente,
							idcliente);
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.descripcion_cliente,
							cliente_descripcion);
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.idpedido,
							idpedido);
			presupuesto.setParameter(
					aplicacion.ventas.presupuesto.interfaces._Parametros.email,
					email);
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.vendedor,
							vendedor);
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.subtotal,
							subtotal);
			presupuesto.setParameter(
					aplicacion.ventas.presupuesto.interfaces._Parametros.iva,
					iva);
			presupuesto.setParameter(
					aplicacion.ventas.presupuesto.interfaces._Parametros.total,
					total);
			presupuesto
					.setParameter(
							aplicacion.ventas.presupuesto.interfaces._Parametros.discriminar,
							ri);
			presupuesto.build(this.getConstructor());
			presupuesto.init();
			this.getConstructor().addChild(presupuesto);
		}

	}

	public void _recalculate_precio(String idarticulo, boolean publico, int row) {
		Object[][] results = data.getArticulo(idarticulo);
		boolean exist = false;
		String idcliente = frame.get_txt_idcliente().getText();
		try {
			TableCellEditor celleditor = frame.getJTable().getCellEditor();
			if (celleditor != null) {
				celleditor.stopCellEditing();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (results != null) {
			if (results.length > 0) {
				exist = true;
				String precio = (String) results[0][1];
				String costo = (String) results[0][2];
				if (!publico) {
					double _costo = new Double(costo);
					double _precio = _costo * 1.21;
					precio = new Convertidor().getMoney(_precio, 2);
				}
				this.eval_variables_from_precio(row, precio);
				frame.getJTable().setValueAt(
						new Convertidor().getMoney(precio, 2), row,
						_Interface._columna_precio);

			}
		}
	}

	public void _recalculate_precios() {

		String idcliente = frame.get_txt_idcliente().getText();
		boolean publico = data.utilizaPrecioPublico(idcliente);
		if (frame.getJTable() != null) {
			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
				String idarticulo = (String) frame.getJTable().getValueAt(i,
						_Interface._columna_idarticulo);
				this._recalculate_precio(idarticulo, publico, i);
			}
		}
		this._calculate();
	}

	public void _transferir() {

		String idpedido = frame.get_txt_idpedido().getText();
		String idcliente = frame.get_txt_idcliente().getText();
		String idvendedor = frame.get_txt_idvendedor().getText();
		boolean ok = true;
		if (ok) {
			if (transferencia != null) {
				transferencia.dispose();
			}
			transferencia = new aplicacion.ventas.transferencia.constructor._Constructor();
			transferencia.setParameter(_parametros.LookAndFeel, this
					.getConstructor().getLookAndFeelTheme());
			transferencia.setParameter(_parametros.connector, this
					.getConstructor().getConnectionHandler());
			transferencia
					.setParameter(
							aplicacion.ventas.transferencia.interfaces._Parametros.pedido,
							this.getConstructor());
			transferencia.build(this.getConstructor());
			transferencia.init();
		}
	}

	public void addItem(boolean seleccionado, String idarticulo,
			String descripcion, String cantidad, String costo, String precio,
			String descuento) {
		this.addItem(seleccionado, idarticulo, descripcion, cantidad, costo,
				precio, descuento);
	}

	public void addItem(boolean seleccionado, String idarticulo,
			String descripcion, String cantidad, String costo, String precio,
			String descuento, boolean editable) {
		JTable table = frame.getJTable();
		if (table == null) {
			this.crear_tabla_items();
			table = frame.getJTable();
		} else {
			if (table.getRowCount() <= 0) {
				this.crear_tabla_items(editable);
				table = frame.getJTable();
			}
		}

		int row = table.getModel().getRowCount() - 1;
		if (table.getValueAt(row, _Interface._columna_idarticulo).toString()
				.compareTo("") != 0) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(model.getRowCount() + 1);
			model.setValueAt(model.getRowCount(), model.getRowCount() - 1,
					_Interface._columna_item);
			row = model.getRowCount() - 1;
		}
		table.setValueAt(seleccionado, row, _Interface._columna_selected);
		table.setValueAt(idarticulo, row, _Interface._columna_idarticulo);
		table.setValueAt(descripcion, row, _Interface._columna_descripcion);
		table.setValueAt(cantidad, row, _Interface._columna_cantidad);
		table.setValueAt(costo, row, _Interface._columna_costo);
		table.setValueAt(precio, row, _Interface._columna_precio);
		table.setValueAt(descuento, row, _Interface._columna_descuento);
		this.eval_variables(row, cantidad, precio, descuento);
	}

	public void agregar(String idpedido, String item) {
		System.out.println("Agregar Seleccion ");
		cambios = true;

		String idarticulo = "";
		String descripcion = "";
		String cantidad = "1.0";
		String descuento = "0.0";
		JTable table = frame.getJTable();
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		String costo = "0.0";
		String precio = "0.0";

		Object[][] results = data.getPedidoItemsShow(idpedido, item);
		if (results != null) {
			if (results.length > 0) {
				idarticulo = results[0][2].toString();
				descripcion = results[0][3].toString();
				cantidad = results[0][4].toString();

				int exist = this.existArticulo(idarticulo);

				try {
					costo = "0.0";
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					precio = results[0][5].toString();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (exist >= 0) {
					int n = this.preguntar("Confirmar",
							"El articulo ya se encuentra en el pedido.",
							new String[] { "Agregar", "No Agregar" },
							"No Agregar");
					if (n == 0) {
						exist = -1;
					}

				}
				if (exist < 0) {
					int row = 0;
					if (table != null) {

						row = this.existEmpty();
						if (row < 0) {
							DefaultTableModel model = (DefaultTableModel) table
									.getModel();
							model.setRowCount(model.getRowCount() + 1);
							model.setValueAt(model.getRowCount(), model
									.getRowCount() - 1,
									_Interface._columna_item);
							row = model.getRowCount() - 1;
						}

					} else {
						this.crear_tabla_items();
					}
					table.setValueAt(true, row, _Interface._columna_selected);
					table.setValueAt(idarticulo, row,
							_Interface._columna_idarticulo);
					table.setValueAt(descripcion, row,
							_Interface._columna_descripcion);
					table.setValueAt(cantidad, row,
							_Interface._columna_cantidad);

					double _precio = 0.0;
					double _costo = 0.0;
					try {
						_costo = new Double(costo.replaceAll(",", ""));
						_precio = new Double(precio.replaceAll(",", ""));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					table.setValueAt(""
							+ new Convertidor().roundDouble(_costo, 2), row,
							_Interface._columna_costo);
					table.setValueAt(""
							+ new Convertidor().roundDouble(_precio, 2), row,
							_Interface._columna_precio);
					table.setValueAt(descuento, row,
							_Interface._columna_descuento);
					this.eval_variables(row, cantidad, precio, descuento);
					table.changeSelection(row, _Interface._columna_idarticulo,
							false, false);
					table.editCellAt(row, _Interface._columna_idarticulo);
					table.transferFocus();
				}

				System.out.println(idarticulo + " " + descripcion + " "
						+ cantidad + " " + costo + " " + precio);

			}
		}
		this._calculate();
	}

	public void agregar(Object[] seleccion) {
		System.out.println("Agregar Seleccion ");
		cambios = true;

		String idarticulo = (String) seleccion[0];
		String descripcion = (String) seleccion[1];
		String cantidad = "1.0";
		String descuento = "0.0";
		JTable table = frame.getJTable();
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		String costo = "0.0";
		String precio = "0.0";

		Object[][] results = data.getArticulo(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				int exist = this.existArticulo(idarticulo);

				try {
					costo = results[0][2].toString();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					precio = results[0][1].toString();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (exist >= 0) {
					int n = this.preguntar("Confirmar",
							"El articulo ya se encuentra en el pedido.",
							new String[] { "Agregar", "No Agregar" },
							"No Agregar");
					if (n == 0) {
						exist = -1;
					}

				}
				if (exist < 0) {
					int row = 0;
					if (table != null) {

						row = this.existEmpty();
						if (row < 0) {
							DefaultTableModel model = (DefaultTableModel) table
									.getModel();
							model.setRowCount(model.getRowCount() + 1);
							model.setValueAt(model.getRowCount(), model
									.getRowCount() - 1,
									_Interface._columna_item);
							row = model.getRowCount() - 1;
						}

					} else {
						this.crear_tabla_items();
					}
					table.setValueAt(true, row, _Interface._columna_selected);
					table.setValueAt(idarticulo, row,
							_Interface._columna_idarticulo);
					table.setValueAt(descripcion, row,
							_Interface._columna_descripcion);
					table.setValueAt(cantidad, row,
							_Interface._columna_cantidad);
					double _precio = 0.0;
					double _costo = 0.0;
					try {
						_costo = new Double(costo.replaceAll(",", ""));
						_precio = new Double(precio.replaceAll(",", ""));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					table.setValueAt(""
							+ new Convertidor().roundDouble(_costo, 2), row,
							_Interface._columna_costo);
					table.setValueAt(""
							+ new Convertidor().roundDouble(_precio, 2), row,
							_Interface._columna_precio);
					table.setValueAt(descuento, row,
							_Interface._columna_descuento);
					this.eval_variables(row, cantidad, precio, descuento);
					table.changeSelection(row, _Interface._columna_idarticulo,
							false, false);
					table.editCellAt(row, _Interface._columna_idarticulo);
					table.transferFocus();
				}

				System.out.println(idarticulo + " " + descripcion + " "
						+ cantidad + " " + costo + " " + precio);

			}
		}
		this._calculate();
	}

	public void agregar(String idarticulo) {
		cambios = true;

		String cantidad = "1.0";
		String descuento = "0.0";
		JTable table = frame.getJTable();
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		Object[][] results = data.getArticulo(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				int exist = this.existArticulo(idarticulo);
				String costo = results[0][2].toString();
				String precio = results[0][1].toString();
				String descripcion = results[0][0].toString();
				if (exist < 0) {
					int row = 0;
					if (table != null) {

						row = this.existEmpty();
						if (row < 0) {
							DefaultTableModel model = (DefaultTableModel) table
									.getModel();
							model.setRowCount(model.getRowCount() + 1);
							model.setValueAt(model.getRowCount(), model
									.getRowCount() - 1,
									_Interface._columna_item);
							row = model.getRowCount() - 1;
						}

					} else {
						this.crear_tabla_items();
					}
					table.setValueAt(true, row, _Interface._columna_selected);
					table.setValueAt(idarticulo, row,
							_Interface._columna_idarticulo);
					table.setValueAt(descripcion, row,
							_Interface._columna_descripcion);
					table.setValueAt(cantidad, row,
							_Interface._columna_cantidad);
					double _precio = 0.0;
					double _costo = 0.0;
					try {
						_costo = new Double(costo.replaceAll(",", ""));
						_precio = new Double(precio.replaceAll(",", ""));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					table.setValueAt(""
							+ new Convertidor().roundDouble(_costo, 2), row,
							_Interface._columna_costo);
					table.setValueAt(""
							+ new Convertidor().roundDouble(_precio, 2), row,
							_Interface._columna_precio);
					table.setValueAt(descuento, row,
							_Interface._columna_descuento);
					this.eval_variables(row, cantidad, precio, descuento);
					table.changeSelection(row, _Interface._columna_idarticulo,
							false, false);
					table.editCellAt(row, _Interface._columna_idarticulo);
					table.transferFocus();
				}

				System.out.println(idarticulo + " " + descripcion + " "
						+ cantidad + " " + costo + " " + precio);

			}
		}
		this._calculate();
	}

	public void automatico() {
		System.out.println("pedido automatico>");
		frame.get_btn_guardar().setEnabled(true);
		frame.get_btn_preparar().setEnabled(true);
		frame.get_btn_transferir().setEnabled(true);
		frame.get_btn_aviso().setEnabled(true);
		frame.get_btn_presupuesto().setEnabled(true);
		frame.get_btn_remito().setEnabled(true);
		frame.get_btn_envio().setEnabled(true);
		frame.getLockableUI().setLocked(false);
		nuevo = true;
		frame.get_txt_idpedido().setEditable(false);
		frame.get_txt_idcliente().setEnabled(true);
		frame.get_txt_idvendedor().setEnabled(true);
		frame.get_btn_buscar_cliente().setEnabled(true);
		frame.get_btn_cargar_cliente().setEnabled(true);
		frame.get_txt_pedido_descripcion().setText("VENTA");
		frame.get_txt_pedido_descripcion().setSelectionStart(0);
		frame.get_txt_pedido_descripcion().setSelectionEnd(5);
		frame.get_txt_pedido_descripcion().requestFocusInWindow();

		this.consumidor_final();
		this.fillCreado();
		this.fillModificado();
		this.crear_tabla_items();
		// this.cargarVendedor();
		frame.get_txt_pedido_descripcion().requestFocusInWindow();
		frame.get_txt_pedido_descripcion().setSelectionStart(0);
		frame.get_txt_pedido_descripcion().setSelectionEnd(5);

	}

	public void block() {

	}

	public void borrarRenglon(int row) {
		String idpedido = frame.get_txt_idpedido().getText();
		boolean remitos = data.getRemitosValidos(idpedido) > 0;
		if (remitos & !nuevo) {
			error("no esta permitido modificar un pedido con remitos generados");
		} else {
			if (preguntar("Confirmar", "Elimina Renglon " + row
					+ " de la tabla?")) {
				try {
					frame.getJTable().getCellEditor().stopCellEditing();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
				DefaultTableModel model = (DefaultTableModel) frame.getJTable()
						.getModel();
				model.removeRow(row);
				if (model.getRowCount() <= 0) {
					model.setRowCount(1);
					model.setValueAt(true, 0, _Interface._columna_selected);
					model.setValueAt(1, 0, _Interface._columna_item);
					this.editCell(0, _Interface._columna_idarticulo);
				} else {
					int rx = model.getRowCount() - 1;
					this.editCell(rx, _Interface._columna_idarticulo);
				}
				for (int i = 0; i < model.getRowCount(); i++) {
					model.setValueAt(i + 1, i, _Interface._columna_item);
				}
				this._calculate();
				cambios = true;
			}

		}
	}

	public void buscarArticulo(JTextField tx) {
		if (vArticulo != null) {
			vArticulo.close();
		}
		vArticulo = new aplicacion.herramientas.java.visualizadores.Articulo(
				this.getConstructor());

		vArticulo.setPublico(true);
		vArticulo.setSuspendidov(true);

		int n = vArticulo.Buscar(tx);
		if (n == 0) {
			vArticulo.close();
			aviso("No hay articulos con ese codigo");
		}
	}

	public void buscarArticuloPrecio(JTextField tx, String idarticulo) {
		
		if (vArticuloPrecio != null) {
			vArticuloPrecio.close();
		}
		vArticuloPrecio = new aplicacion.herramientas.java.visualizadores.ArticuloPrecio(
				this.getConstructor());
		String idcliente = frame.get_txt_idcliente().getText();
		vArticuloPrecio.setIdCliente(idcliente);
		vArticuloPrecio.setIdarticulo(idarticulo);
		vArticuloPrecio.setPublico(true);
		vArticuloPrecio.setSuspendidov(true);

		int n = vArticuloPrecio.Buscar(tx);
		if (n == 0) {
			vArticuloPrecio.close();
			aviso("No hay articulos con ese codigo");
		}
	}

	public void BuscarArticulo(JTextField ext) {
		if (bArticulo != null) {
			bArticulo.close();
		}

		bArticulo = new aplicacion.herramientas.java.importadores.Articulo(this
				.getConstructor()) {
			public void cargar(Object[] seleccion) {
				agregar(seleccion);
			}
		};
		bArticulo.setSuspendidov(true);
		bArticulo.Buscar(ext);
	}

	public void BuscarCatalogo() {
		if (Catalogo != null) {
			Catalogo.dispose();
			Catalogo = null;
		}
		if (Catalogo == null) {
			Catalogo = new aplicacion.ventas.catalogo.constructor._Constructor();

			Catalogo.setParameter(_parametros.connector, data
					.getConnectionHandler().Clone());
			Catalogo.setParameter(_parametros.LookAndFeel, this
					.getConstructor().getLookAndFeelTheme());
			Catalogo.build(this.getConstructor());
			Catalogo.init();
			this.getConstructor().addChild(Catalogo);
		}

		aplicacion.ventas.catalogo.logic._Logic logic = (aplicacion.ventas.catalogo.logic._Logic) Catalogo
				.getLogic();
		if (logic != null) {
			logic
					.setPedido((aplicacion.ventas.pedido.constructor._Constructor) this
							.getConstructor());
		}

	}

	public void BuscarCatalogo(JTextField ext) {
		if (Catalogo != null) {
			Catalogo.dispose();
			Catalogo = null;
		}
		if (Catalogo == null) {
			Catalogo = new aplicacion.ventas.catalogo.constructor._Constructor();

			Catalogo.setParameter(_parametros.connector, data
					.getConnectionHandler().Clone());
			Catalogo.setParameter(_parametros.LookAndFeel, this
					.getConstructor().getLookAndFeelTheme());
			Catalogo.build(this.getConstructor());
			Catalogo.init();
			this.getConstructor().addChild(Catalogo);
		}

		aplicacion.ventas.catalogo.logic._Logic logic = (aplicacion.ventas.catalogo.logic._Logic) Catalogo
				.getLogic();
		if (logic != null) {
			logic
					.setPedido((aplicacion.ventas.pedido.constructor._Constructor) this
							.getConstructor());
		}

	}

	public void buscarCliente(JTextField tx) {
		cliente.buscar(tx);
	}

	public void BuscarCliente() {
		cliente.Buscar(frame.get_txt_idcliente());
	}

	public void BuscarCliente(JTextField tx) {
		cliente.Buscar(tx);
	}

	public void buscarDeposito(JTextField tx) {
		Deposito.buscar(tx);
	}

	public void BuscarDeposito() {
		Deposito.Buscar(frame.get_txt_iddeposito());
	}

	public void BuscarDeposito(JTextField tx) {
		Deposito.Buscar(tx);
	}
	public void buscarNegocio(JTextField tx) {
		Negocio.buscar(tx);
	}

	public void BuscarNegocio() {
		Negocio.Buscar(frame.get_txt_iddeposito());
	}

	public void BuscarNegocio(JTextField tx) {
		Negocio.Buscar(tx);
	}
	public int buscarEquivalencia(JTextField tx) {
		if (vEquivalencia != null) {
			vEquivalencia.close();
		}
		vEquivalencia = new aplicacion.herramientas.java.visualizadores.Equivalencia(
				this.getConstructor());

		int n = vEquivalencia.Buscar(tx);
		/*
		 * if (n == 0) { aviso("no hay Equivalencias con ese codigo"); }
		 */
		return n;
	}

	public void BuscarFecha() {
		Fecha.Buscar(frame.get_txt_fecha());
	}

	public void BuscarFecha(JTextField tx) {
		Fecha.Buscar(tx);
	}

	public void BuscarFechaEnvio() {
		this.BuscarFecha(frame.get_txt_fecha_envio());
	}

	public void buscarPDC(JTextField tx) {
		PDC.buscar(tx);
	}

	public void BuscarPDC() {
		PDC.Buscar(frame.get_txt_idpedido());
	}

	public void BuscarPDC(JTextField tx) {
		PDC.Buscar(tx);
	}

	public void buscarProvincia(JTextField tx) {
		Provincia.buscar(tx);
	}

	public void BuscarProvincia() {
		Provincia.Buscar(frame.get_txt_idprovincia());
	}

	public void BuscarProvincia(JTextField tx) {
		Provincia.Buscar(tx);
	}

	public void buscarTransporte(JTextField tx) {
		Transporte.buscar(tx);
	}

	public void BuscarTransporte() {
		Transporte.Buscar(frame.get_txt_idtransporte());
	}

	public void BuscarTransporte(JTextField tx) {
		Transporte.Buscar(tx);
	}

	public void buscarVendedor(JTextField tx) {
		Vendedor.buscar(tx);
	}

	public void BuscarVendedor() {
		Vendedor.Buscar(frame.get_txt_idvendedor());
	}

	public void BuscarVendedor(JTextField tx) {
		Vendedor.Buscar(tx);
	}

	public void cancelar() {
		if (this.preguntar("Confirmar", "cancela?")) {
			clean();
			frame.get_btn_buscar_vendedor().setEnabled(false);
			frame.get_txt_idvendedor().setEditable(false);
			frame.get_txt_idpedido().setEditable(true);
			frame.get_txt_negocio().setEditable(true);
			frame.get_txt_numero().setEditable(true);
			String iduser=this.getConstructor().getIduser();
			String iddeposito=data.getIdDeposito(iduser);
			String negocio=data.getNegocioId(iduser);
			frame.get_txt_negocio().setText(negocio);
			frame.get_txt_negocio().requestFocusInWindow();
		}
	}

	private boolean cargado(String idarticulo, JTable table) {
		boolean cargado = false;
		int i = 0;
		while (i < table.getRowCount() & !cargado) {
			String _idarticulo = "";
			try {
				_idarticulo = table.getValueAt(i,
						_Interface._columna_idarticulo).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			cargado = (_idarticulo.compareTo(idarticulo) == 0);
			i++;
		}
		return cargado;
	}

	public void cargar_compras() {
		String idpedido = frame.get_txt_idpedido().getText();
		this.cargarCompras(idpedido);
	}

	public void cargar_equivalencias(String _idarticulo) {

		JTable table = this.getDetalle(_idarticulo);
		frame.setJTable_equivalencias(table);
		this.cargar_equivalencias(_idarticulo, frame.getJTable_equivalencias());
	}

	public void cargar_equivalencias(String _idarticulo, JTable table) {
		Object[][] _results = data.getData(_idarticulo);
		if (_results != null) {
			if (_results.length > 0) {
				String _articulo = (String) _results[0][0];
				String _descripcion = (String) _results[0][1];
				String _linea = (String) _results[0][2];
				String _stock = (String) _results[0][3];
				String _suspendidov = (String) _results[0][4];
				String _actualizacion = (String) _results[0][5];
				String _publico = (String) _results[0][8];

				Object[][] results = data.getEquivalencias(_idarticulo);
				for (int i = 0; i < results.length; i++) {
					String idarticulo = (String) results[i][0];
					if (!this.cargado(idarticulo, table)) {
						boolean empty = true;
						DefaultTableModel model = (DefaultTableModel) table
								.getModel();
						int row = model.getRowCount() - 1;
						try {
							empty = table.getValueAt(row,
									_Interface._columna_idarticulo).toString()
									.compareTo("") == 0;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (!empty) {
							model.setRowCount(model.getRowCount() + 1);
							model.setValueAt(model.getRowCount(), model
									.getRowCount() - 1,
									_Interface._columna_item);
							row = model.getRowCount() - 1;
						}
						boolean selected = false;// this.isSelected((String)results[i][0]);
						table.setValueAt(selected, row,
								_Interface._columna_selected);
						for (int j = 0; j < results[0].length; j++) {
							table.setValueAt(results[i][j].toString(), row,
									j + 1);
						}

					}
				}

			}

		}

	}

	public void cargar_items(String idpedido, boolean editable) {

		Object[][] results = data.getPedidoItems(idpedido);
		if (results != null) {
			if (results.length > 0) {
				for (int i = 0; i < results.length; i++) {
					String idarticulo = (String) results[i][0];
					String descripcion = (String) results[i][1];
					String cantidad = (String) results[i][2];
					String costo = (String) results[i][3];
					String precio = (String) results[i][4];
					String seleccionado = (String) results[i][5];
					String descuento = (String) results[i][6];
					boolean selected = false;
					selected = seleccionado.compareTo("1") == 0;
					this.addItem(selected, idarticulo, descripcion, cantidad,
							costo, precio, descuento, editable);
				}
			}
		}
	}

	public void cargar_pedido() {
		String idpedido = frame.get_txt_idpedido().getText();
		String negocio = frame.get_txt_idpedido().getText();
		cargar_pedido(idpedido);
	}

	public void cargar_pedido(String idpedido) {
		this.clean();
		frame.get_txt_idpedido().setText(idpedido);
		frame.get_txt_idpedido().setEditable(false);
		frame.get_txt_numero().setEditable(false);
		frame.get_txt_negocio().setEditable(false);

		Object[][] results = data.getPedido(idpedido);
		if (results != null) {
			if (results.length > 0) {
				nuevo = false;
				frame.get_txt_idvendedor().setEditable(false);
				frame.get_txt_numero().setEditable(false);
				frame.get_btn_buscar_vendedor().setEnabled(false);
				boolean _eliminado = data.eliminado(idpedido);
				frame.getLockableUI().setLocked(false);
				frame.get_btn_pedidoe().setEnabled(!_eliminado);

				frame.get_btn_guardar().setEnabled(!_eliminado);
				frame.get_btn_preparar().setEnabled(!_eliminado);
				frame.get_btn_transferir().setEnabled(!_eliminado);
				frame.get_btn_aviso().setEnabled(!_eliminado);
				frame.get_btn_presupuesto().setEnabled(!_eliminado);
				frame.get_btn_remito().setEnabled(!_eliminado);

				frame.get_btn_envio().setEnabled(!_eliminado);
				frame.get_btn_copia().setEnabled(!_eliminado);
				frame.get_btn_faltantes().setEnabled(!_eliminado);
				frame.get_btn_eliminar().setEnabled(!_eliminado);

				frame.get_btn_eliminar().setEnabled(true);
				frame.get_btn_remito().setEnabled(true);
				frame.get_btn_identificador().setEnabled(true);
				String descripcion = (String) results[0][0];
				String fecha_creacion = (String) results[0][1];
				String fecha_modificacion = (String) results[0][2];
				String cliente = (String) results[0][3];
				String cliente_descripcion = (String) results[0][4];
				String datos_extra = (String) results[0][5];
				String idvendedor = (String) results[0][6];
				String vendedor = (String) results[0][7];
				String idtransporte = (String) results[0][8];
				String transporte = (String) results[0][9];
				String fecha_envio = (String) results[0][10];
				String guia = (String) results[0][11];
				String agenda = (String) results[0][12];
				String seguimiento = (String) results[0][13];
				String domicilio = (String) results[0][14];
				String ciudad = (String) results[0][15];
				String idprovincia = (String) results[0][16];
				String cpostal = (String) results[0][17];
				String telefono = (String) results[0][18];
				String idcreador = (String) results[0][19];
				String iddeposito = (String) results[0][20];
				String negocio = (String) results[0][21];
				String numero = (String) results[0][22];
				fillCreador(idcreador);
				this.cargarDeposito(iddeposito);
				frame.get_txt_pedido_descripcion().setText(descripcion);
				frame.get_txt_fecha().setText(agenda);
				frame.get_txt_fecha_creacion().setText(fecha_creacion);
				frame.get_txt_modificado().setText(fecha_modificacion);
				frame.get_txt_idcliente().setText(cliente);
				frame.get_txt_cliente_descripcion()
						.setText(cliente_descripcion);
				frame.get_txt_informacion().setText(datos_extra);
				frame.get_txt_idvendedor().setText(idvendedor);
				frame.get_txt_vendedor_descripcion().setText(vendedor);
				frame.get_txt_idtransporte().setText(idtransporte);
				frame.get_txt_transporte_descripcion().setText(transporte);
				frame.get_txt_fecha_envio().setText(fecha_envio);
				frame.get_txt_guia().setText(guia);
				frame.get_chk_seguimiento().setSelected(
						seguimiento.compareTo("1") == 0);
				frame.get_txt_domicilio().setText(domicilio);
				frame.get_txt_cpostal().setText(cpostal);
				frame.get_txt_idciudad().setText(ciudad);
				frame.get_txt_idprovincia().setText(idprovincia);
				frame.get_txt_tel().setText(telefono);
				frame.get_txt_idcreador().setText(idcreador);

				if (idprovincia.compareTo("") != 0) {
					this.evaluarProvincia(frame.get_txt_idprovincia());
				}
				if (idtransporte.compareTo("") != 0) {
					this.evaluarTransporte(frame.get_txt_idtransporte());
				}
				frame.get_txt_tel().setText(telefono);
				frame.get_txt_negocio().setText(negocio);
				frame.get_txt_numero().setText(numero);
				boolean remitos = data.getRemitosValidos(idpedido) > 0;
				frame.get_btn_importar().setEnabled(!remitos);
				this.cargar_items(idpedido, !remitos);
				this._calculate();
				this.cargarCompras(idpedido);
				this.cargarTransferencias(idpedido);
				frame.get_txt_idvendedor().requestFocusInWindow();
				cambios = false;
				if (remitos) {
					frame.get_txt_estado().setText("con remito");
				} else {
					frame.get_txt_estado().setText("editando");
				}
			}
		}
	}

	public void cargar_remitos() {
		String idpedido = frame.get_txt_idpedido().getText();
		this.cargarRemitos(idpedido);
	}

	public void cargarCliente() {
		String idcliente = frame.get_txt_idcliente().getText();
		this.evaluarCliente(frame.get_txt_idcliente());
	}

	public boolean check_saldo() {
		// String idcuenta=
		boolean check = false;
		String cuenta = frame.get_txt_idcliente().getText();
		if (data.esCtaCte(cuenta)) {
			double saldoa = data.getSaldoAlfa(cuenta);
			double saldob = data.getSaldoBeta(cuenta);
			double saldo = saldoa + saldob;
			String _saldo = new Convertidor().getMoney(saldo, 2);
			double limite = data.getLimite(cuenta);
			if (limite <= 0) {
				aviso("DEBE ESTABLECER EL LIMITE DE CREDITO PARA ESTE CLIENTE");
			}
			String _limite = new Convertidor().getMoney(limite, 2);
			String _importe = frame.get_txt_total().getText();
			_importe = _importe.replaceAll(",", "");
			double importe = 0;
			try {
				importe = new Double(_importe);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (saldo >= limite) {
				error("EL SALDO ACTUAL DE LA CUENTA  ES MAYOR AL PERMITIDO CONSULTE A ADMINISTRACION ");
				check = false;
				if (confirmar("Confirme para autorizar esta operacion: ", 3)) {
					check = true;
				}
			} else {
				saldo += importe;
				_saldo = new Convertidor().getMoney(saldo, 2);
				if (saldo >= limite) {
					error("CON ESTA OPERACION EL SALDO DE LA CUENTA SUPERARA EL PERMITIDO ");
					check = false;
					if (confirmar("Confirme para autorizar esta operacion: ", 3)) {
						check = true;
					}
				} else {
					check = true;
				}
			}
		} else {
			check = true;
		}
		return check;
	}

	private void cargarCliente(String idcliente) {
		Object[][] results = this.data.getCliente(idcliente);
		if (results != null) {
			if (results.length > 0) {
				frame.get_txt_cliente_descripcion().setEditable(true);
				// frame.get_txt_idcliente().setEditable(false);
				String descripcion = results[0][1].toString();
				String telefono = (String) results[0][2];
				String fax = (String) results[0][3];
				String localidad = (String) results[0][6];
				String calle = (String) results[0][12];
				String numero = (String) results[0][13];
				String piso = (String) results[0][14];
				String cpostal = (String) results[0][15];
				String idprovincia = (String) results[0][16];
				String domicilio = calle + " " + numero + " " + piso;
				String limite = (String) results[0][21];
				String descuento = (String) results[0][22];
				double _limite = new Double(limite);
				double _descuento = new Double(descuento);
				/*
				 * double salfa = data.getSaldoAlfa(idcliente); double sbeta =
				 * data.getSaldoBeta(idcliente); double saldo = salfa + sbeta;
				 * if (idcliente.compareTo("112010001") == 0) { saldo = 0; }
				 * 
				 * if (saldo > 0) { if (saldo >= _limite * 0.9) {
				 * frame.get_txt_saldo().setBackground(Color.red); } else {
				 * frame.get_txt_saldo().setBackground(Color.white); } } else {
				 * frame.get_txt_saldo().setBackground(Color.white); }
				 * 
				 * 
				 * frame.get_txt_saldo().setText( new
				 * Convertidor().getMoney(saldo, 2));
				 * frame.get_txt_descuento().setText(descuento);
				 * frame.get_txt_limite().setText(limite);
				 */
				frame.get_txt_cliente_descripcion().setText(descripcion);
				frame.get_txt_cpostal().setText(cpostal);
				frame.get_txt_domicilio().setText(domicilio);
				frame.get_txt_tel().setText(telefono);
				frame.get_txt_tel().setText(fax);
				frame.get_txt_idciudad().setText(localidad);
				frame.get_txt_idprovincia().setText(idprovincia);
				if (idprovincia.compareTo("") != 0) {
					if (Provincia.existe(idprovincia)) {
						this.evaluarProvincia(frame.get_txt_idprovincia());
					}
				}
				frame.get_txt_cliente_descripcion().requestFocusInWindow();
				if (idcliente.compareTo("112010001") == 0) {
					frame.get_txt_cliente_descripcion().setSelectionStart(0);
					frame.get_txt_cliente_descripcion().setSelectionEnd(
							descripcion.length());
				}
				if (frame.getJTable() != null) {
					if (frame.getJTable().getRowCount() > 0) {
						if (this._cliente.compareTo("") != 0) {
							if (this.data.utilizaPrecioPublico(_cliente) != this.data
									.utilizaPrecioPublico(idcliente)) {
								if (confirmar(
										"Para Recalcular Precios Ingrese el Codigo:",
										2)) {
									this._recalculate_precios();
								}
							}

						}

					}
				}
				_cliente = idcliente;
			}
		}
	}

	private void cargarCompras(String idpedido) {
		Object[][] results = data.getCompras(idpedido);
		frame.setJTable_compras(null);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_compras(results);
			}
		}
	}

	public void cargarDeposito(String iddeposito) {
		System.out.println("Cargando depo "+iddeposito);
		frame.get_txt_iddeposito().setText(iddeposito);
		this.evaluarDeposito(frame.get_txt_iddeposito());

	}
	
	public void cargarNegocio(String negocio) {
		System.out.println("Cargando nego "+negocio);
		frame.get_txt_negocio().setText(negocio);
		this.evaluarNegocio(frame.get_txt_negocio());

	}

	private void cargarRemitos(String idpedido) {
		Object[][] results = data.getRemitos(idpedido);
		frame.setJTable1(null);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_remitos(results);
			}
		}
	}

	private void cargarTransferencias(String idpedido) {
		Object[][] results = data.getTransferencias(idpedido);
		frame.setJTable_transferencia(null);
		if (results != null) {
			if (results.length > 0) {
				this.create_table_transferencia(results);
			}
		}
	}

	public void cargarVendedor() {
		String iduser = this.getConstructor().getIduser();
		Object[][] results = data.getVendedor(iduser);
		boolean loaded = false;
		if (results != null) {
			if (results.length > 0) {
				String idvendedor = (String) results[0][0];
				String nombre = (String) results[0][1];
				frame.get_txt_idvendedor().setText(idvendedor);
				frame.get_txt_vendedor_descripcion().setText(nombre);
				idvendedor = idvendedor.replaceAll(" ", "");
				loaded = idvendedor.compareTo("") != 0;
				frame.get_txt_idvendedor().setEditable(false);
				frame.get_btn_buscar_vendedor().setEnabled(false);
				frame.get_txt_idcreador().setText(idvendedor);
				frame.get_txt_creador().setText(nombre);
			}
		}
		System.out.println("cargado de vendedor por defecto? " + loaded);
		if (!loaded) {
			if (javax.swing.SwingUtilities.isEventDispatchThread()) {
				Runnable runnable = new Runnable() {
					public void run() {
						elegir_vendedor();
					}
				};
				this.invokeLater(runnable);
			} else {
				elegir_vendedor();
			}

		}
	}

	// ----------------------hasta aca esta ok
	public boolean check_articulos() {
		boolean ok = true;
		if (frame.getJTable() != null) {
			int i = 0;
			while (i < frame.getJTable().getRowCount() & ok) {
				if (!this.eval_row_empty(i)) {
					ok = this.eval_row(i);
				} else {

					ok = this.eval_incomplete_row(i);
					if (!ok) {
						if (!nuevo) {
							error("La fila "
									+ i
									+ " de Articulos Esta Incompleta. Verifique que los datos (idarticulo+descripcion+cantidad+precio) sean correctos");
						}
					} else {

					}

				}
				i++;
			}
		}
		return ok;
	}

	/**
	 * Devuelve verdadero si los precios estan actualizados o se aceptan los
	 * errores
	 * 
	 * @return
	 */
	public boolean check_articulos_actualizados() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		boolean vencido = false;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !vencido) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i,
							_Interface._columna_idarticulo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					if (articulo.compareTo("") != 0) {
						if (articulo.compareTo("*") != 0) {
							vencido = this.check_vencimiento(articulo);
							if (vencido) {
								int sel = preguntar("Confirmar", "EL ARTICULO "
										+ articulo + " NO ESTA ACTUALIZADO!!",
										new String[] { "Continuar",
												"Voy a Verificar el Precio" },
										"Voy a Verificar el Precio");
								if (sel == 0) {
									vencido = false;
								}
							}
						}
					}

				}

			}
			i++;
		}
		ok = !vencido;
		return ok;
	}

	public boolean check_articulos_actualizados_auto() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		boolean vencido = false;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !vencido) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i,
							_Interface._columna_idarticulo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					if (articulo.compareTo("*") != 0) {
						vencido = this.check_vencimiento(articulo);

					}
				}

			}
			i++;
		}
		ok = !vencido;
		return ok;
	}

	/**
	 * Devuelve verdadero si no hay asteriscos en el pedido
	 * 
	 * @return
	 */
	public boolean check_articulos_asterisco() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		boolean asterisco = false;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !asterisco) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i,
							_Interface._columna_idarticulo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					asterisco = articulo.compareTo("*") == 0;
				}

			}
			i++;
		}
		ok = !asterisco;
		return ok;
	}

	/**
	 * Devuelve verdadero si los precios estan bien o se aceptan los errores
	 * 
	 * @return
	 */
	public boolean check_articulos_precios() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;

		int i = 0;
		while (i < frame.getJTable().getRowCount() & ok) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i,
							_Interface._columna_idarticulo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					if (articulo.compareTo("") != 0) {
						if (articulo.compareTo("*") != 0) {
							if (data.existeArticulo(articulo)) {
								ok = this.check_precio(i);

							}
						}
					}
				}

			}
			i++;
		}

		return ok;
	}

	/**
	 * Devuelve verdadero si el stock esta bien o si se acepta el error
	 * 
	 * @return
	 */
	public boolean check_articulos_stock() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		boolean stock = true;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & stock) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i,
							_Interface._columna_idarticulo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				double cantidad = 0.0;
				try {
					cantidad = new Double((String) frame.getJTable()
							.getValueAt(i, _Interface._columna_cantidad));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					if (articulo.compareTo("*") != 0) {
						stock = this.check_stock(articulo, cantidad);
						if (!stock) {
							int sel = preguntar(
									"Confirmar",
									"El stock de "
											+ articulo
											+ " es menor a la cantidad necesaria para este pedido ",
									new String[] { "Continuar",
											"Voy a Verificar el STOCK" },
									"Voy a Verificar el STOCK");
							if (sel == 0) {
								stock = true;
							}
						}
					}
				}

			}
			i++;
		}
		ok = stock;

		return ok;
	}

	/**
	 * Devuelve verdadero si los articulos del pedido son validos
	 * 
	 * @return
	 */
	public boolean check_articulos_validos() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		boolean suspendidov = false;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !suspendidov) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i,
							_Interface._columna_idarticulo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					if (articulo.compareTo("*") != 0) {
						suspendidov = data.bloqueado(articulo);
						if (suspendidov) {
							error("El Articulo " + articulo
									+ " esta bloqueado para la venta.");
						}
					}
				}

			}
			i++;
		}
		ok = !suspendidov;
		return ok;
	}

	/**
	 * Devuelve verdadero si el desposito esta ok
	 * 
	 * @return
	 */
	public boolean check_deposito() {
		boolean ok = true;
		String codigo = frame.get_txt_iddeposito().getText();
		ok = this.Deposito.existe(codigo);
		return ok;
	}

	/**
	 * Devuelve verdadero si los descuentos estan bien o se acepta el error
	 * 
	 * @return
	 */
	public boolean check_descuentos_del_pedido() {
		System.out.println("Check descuento de pedidos");
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_real = 0.0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String articulo = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_idarticulo);
			String cantidad = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_cantidad);
			String total = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_total);
			if (total == null) {
				total = "0.0";
			}
			double _total = 0.0;
			try {
				_total = new Double(total.replace(",", ""));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double _cantidad = 0.0;
			try {
				if (cantidad != null) {
					_cantidad = new Double(cantidad.replace(",", ""));
				}

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (articulo != null) {
				if (articulo.compareTo("") != 0) {
					items++;

					if (selected) {
						selections++;
						if (articulo.compareTo("*") != 0) {
							double real = this.getPrecioReal(articulo, "");
							if (real >= 0) {
								suma_selections += _total;
								suma_real += real * _cantidad;
							}

						}
					}

				}
			}
		}
		if (items >= 1) {
			if (selections >= 1) {
				double dif = new Convertidor().roundDouble(suma_selections
						- suma_real, 2);
				if (dif > 0) {
					ok = true;
				} else {
					if (dif < 0) {

						// perdida
						double desc = ((suma_real - suma_selections) / suma_real) * 100;
						desc = new Convertidor().roundDouble(desc, 2);

						if (desc > 5) {
							boolean su = true;
							if (su) {
								String NEW_LINE = System
										.getProperty("line.separator");
								String msj = "Confirme la operacion de descuento del "
										+ desc + "% ";
								msj += NEW_LINE;
								msj += "Sistema   :$"
										+ new Convertidor().roundDouble(
												suma_real, 2);
								msj += NEW_LINE;
								msj += "Cotizacion:$"
										+ new Convertidor().roundDouble(
												suma_selections, 2);
								msj += NEW_LINE;

								msj += "Descuento   :$"
										+ new Convertidor().roundDouble(
												suma_real - suma_selections, 2);
								msj += NEW_LINE;
								msj += "Codigo:";
								su = this.confirmar(msj, 2);
							}
							ok = su;

						}

					} else {
						ok = true;
					}
				}

			} else {
				error("Debe Seleccionar Los items para el remito");
				ok = false;
			}

		} else {
			error("No existem items para el remito");
			ok = false;
		}

		return ok;
	}

	/**
	 * Devuelve verdadero si existe stock mayor igual a la cantidad indicada
	 * 
	 * @param idarticulo
	 * @param cantidad
	 * @return
	 */
	public double check_diference_stock(String idarticulo, double cantidad) {

		double stock = 0.0;
		Object[][] results = data.getData(idarticulo);
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		if (results != null) {
			if (results.length > 0) {
				Convertidor cv = new Convertidor();
				String _stock = (String) results[0][3];
				try {
					stock = new Double(_stock);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return stock - cantidad;
	}

	/**
	 * Retorna la diferencia entre le precio ingresado y el sistema
	 * 
	 * @param row
	 * @return
	 */
	public double check_global(int row) {
		double global = 0.0;
		double qty = 0.0;
		double prc = 0.0;

		String idcliente = frame.get_txt_idcliente().getText();
		String idarticulo = "";
		try {
			idarticulo = frame.getJTable().getValueAt(row,
					_Interface._columna_idarticulo).toString();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (idarticulo.compareTo("*") != 0) {
			String precio = "";
			try {
				precio = frame.getJTable().getValueAt(row,
						_Interface._columna_precio).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String cantidad = "";
			try {
				cantidad = frame.getJTable().getValueAt(row,
						_Interface._columna_cantidad).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			precio = precio.replaceAll(",", "");
			cantidad = cantidad.replaceAll(",", "");
			boolean ok = true;
			try {
				prc = new Double(precio);
			} catch (Exception e) {
				ok = false;
				;
			}
			try {
				qty = new Double(cantidad);
			} catch (Exception e) {
				ok = false;
				;
			}
			if (ok) {
				boolean publico = data.utilizaPrecioPublico(idcliente);
				double sys_prc = 0.0;
				if (publico) {
					sys_prc = data.getPrecioPublico(idarticulo);
				} else {
					sys_prc = data.getPrecioCosto(idarticulo);
				}

				global = (prc - sys_prc) * qty;
			}
		}
		return global;
	}

	/**
	 * Devuelve la variacion global de precios del total de items seleccionados
	 * 
	 * @return
	 */
	public double check_global_precios() {
		double global = 0.0;
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;

		int i = 0;
		while (i < frame.getJTable().getRowCount() & ok) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String articulo = "";
			try {
				articulo = (String) frame.getJTable().getValueAt(i,
						_Interface._columna_idarticulo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (articulo != null) {
				if (articulo.compareTo("") != 0) {
					if (articulo.compareTo("*") != 0) {
						global += this.check_global(i);

					}
				}
			}

			i++;
		}
		System.out.println("GLOBAL>" + global);
		return global;
	}

	/**
	 * devuelve verdadero si el precio ingresado es mayor igual al del sistema
	 * 
	 * @param row
	 * @return
	 */
	public boolean check_precio(int row) {
		boolean selected = false;
		boolean ok = true;
		try {
			selected = (Boolean) frame.getJTable().getValueAt(row,
					_Interface._columna_selected);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (selected) {
			String idcliente = frame.get_txt_idcliente().getText();
			String idarticulo = "";
			try {
				idarticulo = frame.getJTable().getValueAt(row,
						_Interface._columna_idarticulo).toString();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String precio = "";
			try {
				precio = frame.getJTable().getValueAt(row,
						_Interface._columna_precio).toString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			precio = precio.replaceAll(",", "");
			double prc = 0.0;

			try {
				prc = new Double(precio);
			} catch (Exception e) {
				ok = false;
				;
			}
			if (ok) {
				boolean publico = data.utilizaPrecioPublico(idcliente);
				double sys_prc = 0.0;
				if (publico) {
					sys_prc = data.getPrecioPublico(idarticulo);
				} else {
					sys_prc = data.getPrecioCosto(idarticulo);
				}

				if (sys_prc - prc > 0.5) {
					ok = false;

					int sel = preguntar("Confirmar", "El Precio de "
							+ idarticulo + " $" + prc
							+ " es Menor al del sistema => $" + sys_prc + " ",
							new String[] { "Continuar",
									"Voy a Verificar el Precio" },
							"Voy a Verificar el Precio");
					if (sel == 0) {
						ok = true;
					}
				} else {

				}
			}
		}

		return ok;
	}

	/**
	 * devuelve verdadero si la seleccion es correcta o aceptada
	 * 
	 * @return
	 */
	public boolean check_seleccion_del_pedido() {
		boolean ok = true;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String articulo = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_idarticulo);
			String total = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_total);
			if (total == null) {
				total = "0.0";
			}
			double _total = 0.0;
			try {
				_total = new Double(total.replace(",", ""));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (articulo != null) {
				if (articulo.compareTo("") != 0) {
					items++;
					suma_items += _total;
					if (selected) {
						selections++;
						suma_selections += _total;
					}
				}
			}

		}
		if (items >= 1) {
			if (selections >= 1) {
				if (selections != items) {
					double dif = suma_selections - suma_items;
					dif = new Convertidor().roundDouble(dif, 2);
					String mensaje = "Confirme Que la Eleccion de items es Correcta";
					String NEW_LINE = System.getProperty("line.separator");
					mensaje += NEW_LINE;
					mensaje += "Selecciono " + selections + " de " + items
							+ ". Diferencia $" + (dif);
					mensaje += NEW_LINE;
					mensaje += "Codigo:";
					ok = (this.confirmar(mensaje, 2));
				} else {
					ok = true;
				}
			} else {
				error("Debe Seleccionar Los items para el remito");
				ok = false;
			}

		} else {
			error("No existem items para el remito");
			ok = false;
		}

		return ok;
	}

	/**
	 * Devuelve verdadero si existe stock mayor igual a la cantidad indicada
	 * 
	 * @param idarticulo
	 * @param cantidad
	 * @return
	 */
	public boolean check_stock(String idarticulo, double cantidad) {
		boolean stock_ok = false;
		double stock = 0.0;
		Object[][] results = data.getData(idarticulo);
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		if (results != null) {
			if (results.length > 0) {
				Convertidor cv = new Convertidor();
				String _stock = (String) results[0][3];
				try {
					stock = new Double(_stock);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		stock_ok = stock >= cantidad;
		return stock_ok;
	}

	/**
	 * devuelve verdadero si el articulo esta vencido
	 * 
	 * @param idarticulo
	 * @return
	 */
	public boolean check_vencimiento(String idarticulo) {
		boolean vencido = true;
		Object[][] results = data.getData(idarticulo);
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		if (results != null) {
			if (results.length > 0) {
				Convertidor cv = new Convertidor();
				String _actualizacion = (String) results[0][5];
				if (_actualizacion.compareTo("") == 0) {

				} else {
					if (this.eval_venc(_actualizacion)) {
						vencido = false;
					} else {

					}
				}

			}
		}
		return vencido;
	}

	public void clean() {
		nuevo = false;
		cambios = false;
		no_a_todo = false;
		si_a_todo = false;
		frame.setJTable(null);
		frame.setJTable1(null);
		// frame.get_txt_saldo().setBackground(Color.white);
		frame.setJTable_equivalencias(null);
		frame.setJTable_transferencia(null);
		frame.get_txt_idvendedor().setEditable(true);
		frame.get_btn_buscar_vendedor().setEnabled(true);
		frame.get_btn_importar().setEnabled(false);
		frame.get_txt_idcreador().setText("");
		frame.get_txt_creador().setText("");
		frame.get_txt_fecha().setText("");
		frame.get_txt_estado().setText("");
		frame.get_txt_idcliente().setText("");
		frame.get_txt_idvendedor().setText("");
		frame.get_txt_cliente_descripcion().setText("");
		frame.get_txt_informacion().setText("");
		frame.get_txt_pedido_descripcion().setText("");
		frame.get_txt_vendedor_descripcion().setText("");
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_actualizacion().setText("");
		frame.get_txt_articulo_bloqueado().setText("");
		frame.get_txt_articulo_linea().setText("");
		frame.get_txt_articulo_stock().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_idpedido().setText("");
		frame.get_txt_items().setText("");
		frame.get_txt_informacion().setText("");
		frame.get_txt_iva().setText("");
		frame.get_txt_total().setText("");
		frame.get_txt_modificado().setText("");
		frame.get_txt_subtotal().setText("");
		frame.get_txt_idpedido().setEditable(true);
		frame.get_txt_fecha_envio().setText("");
		frame.get_txt_guia().setText("");
		frame.get_txt_idtransporte().setText("");
		frame.get_txt_transporte_descripcion().setText("");
		frame.get_txt_fecha_creacion().setText("");
		// frame.get_txt_
		frame.get_btn_guardar().setEnabled(false);
		frame.get_btn_copia().setEnabled(false);
		frame.get_btn_faltantes().setEnabled(false);
		frame.get_btn_pedidoe().setEnabled(false);
		frame.get_btn_transferir().setEnabled(false);
		frame.get_btn_preparar().setEnabled(false);
		frame.get_btn_aviso().setEnabled(false);
		frame.get_btn_presupuesto().setEnabled(false);
		frame.get_btn_remito().setEnabled(false);

		frame.get_btn_envio().setEnabled(false);
		frame.get_btn_eliminar().setEnabled(false);
		frame.get_btn_identificador().setEnabled(false);
		this.getToday();
		// frame.get_txt_descuento().setText("");
		// frame.get_txt_saldo().setText("");
		// frame.get_txt_descuento().setText("");
		// frame.get_txt_limite().setText("");
		// this.obtener_proximo_cpte();
		frame.get_txt_idpedido().setText("");
		this.focus();
		frame.getLockableUI().setLocked(true);
		frame.get_txt_iddeposito().setText("");
		frame.get_txt_negocio().setText("");
		frame.get_txt_numero().setText("");
		
		frame.get_txt_deposito_descripcion().setText("");
		this.seleccionimportar = null;
	}

	public void cleanx() {
		if (javax.swing.SwingUtilities.isEventDispatchThread()) {
			Runnable runnable = new Runnable() {
				public void run() {
					clean();
				}
			};
			this.invokeLater(runnable);
		} else {
			clean();
		}

	}

	public void complete_asterisco(int row) {
		String cantidad = "";
		try {
			cantidad = frame.getJTable().getValueAt(row,
					_Interface._columna_cantidad).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String costo = "";
		try {
			costo = frame.getJTable()
					.getValueAt(row, _Interface._columna_costo).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String precio = "";
		try {
			precio = frame.getJTable().getValueAt(row,
					_Interface._columna_precio).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String descuento = "";
		try {
			descuento = frame.getJTable().getValueAt(row,
					_Interface._columna_descuento).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String total = "";
		try {
			total = frame.getJTable()
					.getValueAt(row, _Interface._columna_total).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cantidad.compareTo("") == 0) {
			cantidad = "0.0";
			frame.getJTable().setValueAt(cantidad, row,
					_Interface._columna_cantidad);
		}
		if (costo.compareTo("") == 0) {
			costo = "0.0";
			frame.getJTable().setValueAt(costo, row, _Interface._columna_costo);
		}
		if (precio.compareTo("") == 0) {
			precio = "0.0";
			frame.getJTable().setValueAt(precio, row,
					_Interface._columna_precio);
		}
		if (descuento.compareTo("") == 0) {
			descuento = "0.0";
			frame.getJTable().setValueAt(descuento, row,
					_Interface._columna_descuento);
		}
		if (total.compareTo("") == 0) {
			total = "0.0";
			frame.getJTable().setValueAt(total, row, _Interface._columna_total);
		}
	}

	public void consumidor_final() {
		frame.get_txt_idcliente().setText("112010001");
		frame.get_txt_cliente_descripcion().setText("Consumidor Final");
		this.evaluarCliente(frame.get_txt_idcliente());
	}

	public void retiro() {
		// boolean seleccion_pedido=this.check_seleccion_del_pedido();
		// boolean articulos_actualizados=check_articulos_actualizados();
		// boolean articulos_validos=check_articulos_validos();

		String idpedido = frame.get_txt_idpedido().getText();

		boolean ok = false;

		String idremito = data.getRemitoGenerado(idpedido);
		if (idremito.compareTo("") != 0) {
			if (data.existeRemitoGenerado(idremito)) {
				if (idremito.compareTo("") != 0) {
					ok = true;
					String cuenta = frame.get_txt_idcliente().getText();

					if (cliente.existe(cuenta)) {
						if (cuenta.compareTo("112010001") != 0) {
							this._retiro(idpedido, idremito);
						} else {
							error("El retiro de mercaderia no esta permitido para esta cuenta");
						}
					} else {
						error("Cuenta inexistente");
					}
				}
			} else {
				error("El Remito Generado por este pedido fue eliminado o cambiado desde otra aplicacion");
			}
		} else {
			error("No hay registros de remitos generados desde este pedido.");
		}

	}

	public void copia() {
		// boolean seleccion_pedido=this.check_seleccion_del_pedido();
		// boolean articulos_actualizados=check_articulos_actualizados();
		// boolean articulos_validos=check_articulos_validos();

		String idpedido = frame.get_txt_idpedido().getText();

		boolean ok = false;

		String idremito = data.getRemitoGenerado(idpedido);
		if (idremito.compareTo("") != 0) {
			if (data.existeRemitoGenerado(idremito)) {
				if (idremito.compareTo("") != 0) {
					ok = true;
					int n = this.preguntar("Tipo de Remito",
							"Seleccione como quiere la copia", new String[] {
									"REMITO CON VALORES",
									"REMITO SOLO CANTIDADES" },
							"REMITO SOLO CANTIDADES");
					if (n == 0) {
						this._copia(idpedido, idremito);
					} else {
						this._retiro(idpedido, idremito);
					}

				}
			} else {
				error("El Remito Generado por este pedido fue eliminado o cambiado desde otra aplicacion");
			}
		} else {
			error("No hay registros de remitos generados desde este pedido.");
		}

	}

	private void crear_empty_etiquetas() {
		Object[][] results = new Object[][] { { false, "", "", "" } };
		this.create_table_etiquetas(results);
	}

	private void crear_empty_faltantes() {
		Object[][] results = new Object[][] { { false, "", "", "" } };
		this.create_table_faltantes(results);
	}

	private void crear_empty_pedidoe() {
		Object[][] results = new Object[][] { { false, "", "", "", "", "" } };
		this.create_table_pedidoe(results);
	}

	private void crear_tabla_items() {
		this.crear_tabla_items(true);
	}

	private void crear_tabla_items(boolean editable) {
		System.out.println("Create Table of Items");
		Object[][] results = new Object[][] { { true, "1", "", "", "", "", "",
				"", "" } };
		this.create_table(results, editable);
	}

	private void crear_tabla_remitos() {
		System.out.println("Create Table of remitos");
		Object[][] results = new Object[][] { { true, "", "", "" } };
		this.create_table_remitos(results);
	}

	private void create_faltantes(boolean automatic) {
		if (this.faltantes != null) {
			faltantes.setVisible(false);
			faltantes.dispose();
		}
		faltantes = new _Faltantes();
		faltantes.setName(_Interface._faltantes);
		centrar_frame(faltantes);
		faltantes.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		faltantes.addWindowListener(this.getConstructor().getWindowListener());

		faltantes.get_chk_seleccionar().setName(
				_Interface._chk_seleccionar_etiquetas);
		faltantes.get_chk_seleccionar().addItemListener(
				this.getConstructor().getItemListener());
		faltantes.get_txt_idcomprobante().setText(
				frame.get_txt_idpedido().getText());
		if (automatic) {
			faltantes.get_btn_marcar_faltantes().setActionCommand(
					_Interface._btn_marcar_faltantes_pedido);
			faltantes.get_chk_asociar().setSelected(false);
		} else {
			this.faltantes.get_btn_marcar_faltantes().setActionCommand(
					_Interface._btn_marcar_faltantes);
			faltantes.get_chk_asociar().setSelected(true);
		}

		faltantes.get_btn_marcar_faltantes().addActionListener(
				this.getConstructor().getActionListener());
		faltantes.get_btn_salir().setActionCommand(
				_Interface._btn_salir_faltantes);
		faltantes.get_btn_salir().addActionListener(
				this.getConstructor().getActionListener());
		faltantes.setVisible(true);
		faltantes.requestFocus();
		faltantes.requestFocusInWindow();
		crear_empty_faltantes();

	}

	private void create_table(Object[][] results, boolean editable) {
		editable=true;
		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;
		CellEditor pce = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(editable);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		chkce.setName(_Interface._table_items_seleccionar);
		col.setCellEditor(chkce.getCellCheck());

		col.setClass(Boolean.class);
		table.addColumn(col);

		col = new Column();
		col.setName("item");
		col.setWidth(30);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(editable);
		col.setAligment(JTextField.RIGHT);

		pce = new CellEditor();
		pce.setAligment(JTextField.RIGHT);
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_idarticulo);
		pce.setTipo(String.class);

		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(370);
		col.setEditable(true);
		col.setAligment(JTextField.LEFT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_descripcion);
		pce.setTipo(String.class);
		pce.setAligment(JTextField.LEFT);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(editable);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_cantidad);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("costo");
		col.setWidth(70);
		col.setEditable(editable);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_costo);
		pce.setTipo(String.class);
		pce.setAligment(JTextField.RIGHT);
		col.setCellEditor(pce.getCellEditor());
		TableItemColorCellRenderer cellrenderer = new TableItemColorCellRenderer();
		cellrenderer.setLogic(this);
		col.setCellRenderer(cellrenderer);
		table.addColumn(col);

		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(editable);
		col.setAligment(JTextField.RIGHT);

		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_precio);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("Desc");
		col.setWidth(50);
		col.setEditable(editable);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_descuento);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_items_total);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_items);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		_table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "none");
		_table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "none");
		// _table.setColumnSelectionAllowed(false);
		System.out.println("Creation Table!");
		frame.setJTable(_table);
	}

	/**
	 * fecha|idpedido|descripcion|idproveedor|nombre|estado
	 * 
	 * @param results
	 */
	private void create_table_compras(Object[][] results) {

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("fecha");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("idpedido");
		col.setWidth(80);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("idproveedor");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("nombre");
		col.setWidth(140);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("estado");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.setName(_Interface._table_compras);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		frame.setJTable_compras(_table);
	}

	private JTable create_table_equivalencias(Object[][] auzx) {
		final CustomTable Table = new CustomTable();
		System.out.println("Creando Tabla Articulos");

		Column col = new Column();
		col.setName("");
		col.setWidth(20);
		col.setClass(Boolean.class);
		col.setEditable(false);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this.getConstructor().getItemListener());
		chkce.setTipo(Boolean.class);
		// chkce.setName(_Interface._table_chk_articulos);
		col.setCellEditor(chkce.getCellCheck());
		Table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(120);
		col.setClass(String.class);
		col.setEditable(false);
		TableColorCellRenderer cellrender = new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender = new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(180);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender = new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);

		col = new Column();
		col.setName("publico");
		col.setWidth(80);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender = new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);

		col = new Column();
		col.setName("stock");
		col.setWidth(70);
		col.setClass(String.class);
		col.setEditable(false);
		cellrender = new TableColorCellRenderer();
		cellrender.setLogic(this);
		col.setCellRenderer(cellrender);
		Table.addColumn(col);
		Table.setData(auzx);
		Table.setFont(new Font("Dialog", Font.PLAIN, 10));
		// Table.setName(_Interface._table_articulo);
		Table.build();
		Table.fillData();

		return Table.getTable();

	}

	/**
	 * chk|idarticulo|descripcion|cant
	 * 
	 * @param results
	 */
	private void create_table_etiquetas(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		CheckBoxCellEditor cbce = null;

		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		col.setClass(Boolean.class);
		cbce = new CheckBoxCellEditor();
		cbce.setName(_Interface._table_etiquetas_seleccion);
		cbce.setItemListener(constructor.getItemListener());
		col.setCellEditor(cbce.getCellCheck());
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_etiquetas_cantidad);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_etiquetas);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		etiquetas.setJTable(_table);
	}

	/**
	 * chk|idarticulo|descripcion|cant
	 * 
	 * @param results
	 */
	private void create_table_relaciones(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		CheckBoxCellEditor cbce = null;

		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		col.setClass(Boolean.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(240);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(140);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("stock");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("referencias");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_referencias);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		relaciones.setJTable(_table);
	}

	/**
	 * chk|idarticulo|descripcion|cant
	 * 
	 * @param results
	 */
	private void create_table_remito_preview(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		CheckBoxCellEditor cbce = null;

		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(310);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("cantidad");
		col.setWidth(60);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("desc");
		col.setWidth(50);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("total");
		col.setWidth(90);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		table.setData(results);
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		remito.setJTable(_table);
	}

	/**
	 * chk|idarticulo|descripcion|cant
	 * 
	 * @param results
	 */
	private void create_table_faltantes(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		CheckBoxCellEditor cbce = null;

		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		col.setClass(Boolean.class);
		cbce = new CheckBoxCellEditor();
		cbce.setName(_Interface._table_faltantes_seleccion);
		cbce.setItemListener(constructor.getItemListener());
		col.setCellEditor(cbce.getCellCheck());
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_etiquetas_cantidad);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_faltantes);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		faltantes.setJTable(_table);
	}

	/**
	 * chk|idarticulo|descripcion|cant
	 * 
	 * @param results
	 */
	private void create_table_pedidoe(Object[][] results) {
		_Constructor constructor = (_Constructor) this.getConstructor();
		CustomTable table = new CustomTable();
		CellEditor pce = null;
		CheckBoxCellEditor cbce = null;

		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		col.setClass(Boolean.class);
		cbce = new CheckBoxCellEditor();
		cbce.setName(_Interface._table_pedidoe_seleccion);
		cbce.setItemListener(constructor.getItemListener());
		col.setCellEditor(cbce.getCellCheck());
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_etiquetas_cantidad);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("idproveedor");
		col.setWidth(80);
		col.setEditable(true);
		col.setAligment(JTextField.RIGHT);
		pce = new CellEditor();
		pce.addKeyListener(constructor.getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_etiquetas_cantidad);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);

		col = new Column();
		col.setName("nombre");
		col.setWidth(180);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table_pedidoe);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();

		pedidoe.setJTable(_table);
	}

	/**
	 * fecha|idremito|idusuario|estado
	 * 
	 * @param results
	 */
	private void create_table_remitos(Object[][] results) {

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("fecha");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("idremito");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("idusuario");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("estado");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("importe");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		table.setData(results);
		// table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.setName(_Interface._table_remitos);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		frame.setJTable1(_table);
	}

	/**
	 * fecha|idtransferencia|origen|destino
	 * 
	 * @param results
	 */
	private void create_table_transferencia(Object[][] results) {

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("idtransferencia");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("fecha");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("origen");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("destino");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);

		table.setData(results);
		// table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.setName(_Interface._table_transferencia);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		frame.setJTable_transferencia(_table);
	}

	protected JTable create_table_xml(String nombre) {
		JTable table = null;
		if (nombre.compareTo(_Interface._table_items) == 0) {
			this.crear_tabla_items();
			table = frame.getJTable();
		}
		if (nombre.compareTo(_Interface._table_remitos) == 0) {
			this.crear_tabla_remitos();
			table = frame.getJTable1();
		}
		return table;
	}

	public void dispose_faltantes() {
		faltantes.setVisible(false);
		faltantes.dispose();
	}

	public void editar_pedido(String idpedido) {
		if (pedido != null) {
			pedido.dispose();
		}
		pedido = new aplicacion.compras.pedidoe.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler().Clone());
		pedido.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		pedido.setParameter(
				aplicacion.compras.pedidoe.interfaces._parametros._idpedido,
				idpedido);
		pedido.build(this.getConstructor());
		pedido.init();
		aplicacion.compras.pedidoe.logic._Logic logic = (aplicacion.compras.pedidoe.logic._Logic) pedido
				.getLogic();
		String idproveedor = "211010029";
		String idvendedor = frame.get_txt_idvendedor().getText();
		// logic.nuevo(idproveedor, idvendedor);

		for (int i = 0; i < frame.getJTable1().getRowCount(); i++) {
			boolean seleccion = false;
			try {
				seleccion = (Boolean) frame.getJTable().getValueAt(i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (seleccion) {
				String idarticulo = (String) frame.getJTable().getValueAt(i, 1);
				Object[][] results = data.getData(idarticulo);
				if (results != null) {
					if (results.length > 0) {
						String _idproveedor = (String) results[0][7];
					}
				}
			}
		}

		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean seleccion = false;
			try {
				seleccion = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (seleccion) {
				String idarticulo = (String) frame.getJTable().getValueAt(i,
						_Interface._columna_idarticulo);
				String descripcion = (String) frame.getJTable().getValueAt(i,
						_Interface._columna_descripcion);
				String cantidad = (String) frame.getJTable().getValueAt(i,
						_Interface._columna_cantidad);
				String _idpedido = frame.get_txt_idpedido().getText();
				logic.agregar_pdc(new String[] { idarticulo, descripcion,
						cantidad, _idpedido });
			}
		}

	}

	public void editarArticulo(JTextField tx) {
		String idarticulo = tx.getText();
		Object[][] results = data.getArticulo(idarticulo);
		boolean exist = false;
		if (results != null) {
			if (results.length > 0) {
				exist = true;

			}
		}
		if (exist) {
			this.goMa_Articulos(idarticulo);
		} else {
			error("El articulo " + idarticulo + " es inexistente");
		}
	}

	public void editarCliente() {
		String idcliente = frame.get_txt_idcliente().getText();
		if (idcliente.compareTo("") != 0) {
			if (mcliente != null) {
				mcliente.dispose();
				mcliente = null;
			}
			mcliente = new aplicacion.cliente.archivo.constructor._Constructor();
			mcliente.setParameter(_parametros.connector, this._data
					.getConnectionHandler().Clone());
			mcliente.setParameter(_parametros.LookAndFeel, this
					.getConstructor().getLookAndFeelTheme());
			mcliente
					.setParameter(
							aplicacion.cliente.archivo.interfaces._Parametros.idcliente,
							idcliente);
			mcliente.build(this.getConstructor());
			mcliente.init();
			this.getConstructor().addChild(mcliente);
		}
	}

	public void editarTransporte() {
		String idtransporte = frame.get_txt_idtransporte().getText();
		if (transporte != null) {
			transporte.dispose();
			transporte = null;
		}
		transporte = new aplicacion.inventario.transporte.constructor._Constructor();
		transporte.setParameter(_parametros.connector, this._data
				.getConnectionHandler().Clone());
		transporte.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		transporte
				.setParameter(
						aplicacion.inventario.transporte.interfaces._parametros.idtransporte,
						idtransporte);
		transporte.build(this.getConstructor());
		transporte.init();
		this.getConstructor().addChild(transporte);

	}

	public void editCell(int row, int col) {
		JTable table = frame.getJTable();
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
	}

	public void editCell(JTable table, int row, int col) {
		if (row < table.getRowCount() & row >= 0) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			if (col >= 0 & col < model.getColumnCount()) {
				try {
					table.getCellEditor().stopCellEditing();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
				table.changeSelection(row, col, false, false);
				table.editCellAt(row, col);
				table.transferFocus();
			}

		}

	}

	public boolean elegir_vendedor() {
		boolean ok = false;
		System.out.println("elegir vendedor");
		String idvendedor = this.validar_vendedor();
		frame.get_txt_idvendedor().setText(idvendedor);

		if (idvendedor.compareTo("") != 0) {
			ok = true;
			this.evaluarVendedor(frame.get_txt_idvendedor());

			frame.get_txt_idvendedor().setEditable(false);
			frame.get_btn_buscar_vendedor().setEnabled(false);
			if (nuevo) {
				frame.get_txt_idcreador().setText(idvendedor);
				this.fillCreador(idvendedor);
			}
		} else {
			ok = false;
		}

		return ok;
	}

	public void eliminar_pedido() {
		aviso("EL SISTEMA VERIFICARA QUE PUEDA ELIMINARSE ESTE PEDIDO. ESPERE POR FAVOR");
		boolean ok = true;
		String idpedido = frame.get_txt_idpedido().getText();
		Object[][] results = data.getRemitos(idpedido);
		int rmx = 0;
		if (results != null) {
			if (results.length > 0) {
				int i = 0;
				while (i < results.length & ok) {
					String remito = (String) results[i][1];
					if (remito.compareTo("") != 0
							& remito.compareTo("NULL") != 0) {
						rmx++;
						ok = this.puede_eliminar_remito(remito);
					}

					i++;
				}

			}
		}
		if (rmx > 0) {
			error("ESTE PEDIDO TIENE GENERADO UN REMITO. VERIFIQUE ANTES DE CONTINUAR");
		}
		if (ok) {
			String iduser = this.validar_usuario();
			if (iduser.compareTo("") != 0) {
				String idvendedor = "";
				try {
					idvendedor = (String) data.getVendedor(iduser)[0][0];
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idvendedor.compareTo("") != 0) {
					String owner = data.getOwner(idpedido);
					if (owner.compareTo(idvendedor) == 0 | !v2) {
						ok = true;
					} else {
						ok = false;
						error("Este pedido le pertenece a otro usuario");
					}
					frame.get_txt_idvendedor().setText(idvendedor);
					this.evaluarVendedor(frame.get_txt_idvendedor());
				} else {
					ok = false;
					error("Usuario Invalido");
				}

			} else {
				error("OPERACION CANCELADA");
				ok = false;
			}
			if (ok) {
				this._eliminar_pedido(idpedido, iduser);
			}
		}

	}

	public void eliminar_remito() {
		int row = frame.getJTable1().getSelectedRow();
		if (row >= 0 & row <= frame.getJTable1().getRowCount() - 1) {
			String remito = frame.getJTable1().getValueAt(row, 1).toString();
			if (remito.compareTo("") != 0) {
				this.eliminar_remito(remito);
			} else {
				aviso("Debe seleccionar el remito que desea eliminar");
			}

		} else {
			aviso("Debe seleccionar el remito que desea eliminar");
		}
	}

	public void eliminar_remito(String remito) {
		if (remito.compareTo("") != 0) {
			boolean link = false;
			Object[][] results = data.tieneComprobanteBetaAsociado(remito);
			if (results != null) {
				if (results.length > 0) {
					link = true;
					String tc = (String) results[0][0];
					String idcomprobante = (String) results[0][1];
					error("No puede eliminar este remito porque esta asociado al comprobante "
							+ tc + "-" + idcomprobante);
				}
			}
			if (!link) {
				results = data.tieneComprobanteAlfaAsociado(remito);
				if (results != null) {
					if (results.length > 0) {
						link = true;
						String tc = (String) results[0][0];
						String idcomprobante = (String) results[0][1];
						error("No puede eliminar este remito porque esta asociado al comprobante "
								+ tc + "-" + idcomprobante);

					}
				}
			}
			if (!link) {
				String iduser = this.validar_usuario();
				if (iduser.compareTo("") != 0) {
					String idvendedor = "";
					try {
						idvendedor = (String) data.getVendedor(iduser)[0][0];
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (idvendedor.compareTo("") != 0) {
						frame.get_txt_idvendedor().setText(idvendedor);
						this.evaluarVendedor(frame.get_txt_idvendedor());
					}
					List<String> batchlist = this
							.getInstruccionesEliminarRemito(remito);
					data.beginTransaction();
					data.clearBatch();
					for (int i = 0; i < batchlist.size(); i++) {
						data.addBatch(batchlist.get(i));
					}
					boolean error = data.executeBatch();
					if (!error) {
						String idoperacion = "" + data.getProximoOperacion();
						List<String> instrucciones = this
								.getInstruccionesGuardarEncabezado(idoperacion,
										"ELIMINAR REMITO", iduser);
						List<String> instrucciones_items = this
								.getInstruccionesGuardarItems(idoperacion);
						data.clearBatch();
						for (int i = 0; i < instrucciones.size(); i++) {
							System.out.println(instrucciones.get(i));
							data.addBatch(instrucciones.get(i));
						}

						for (int i = 0; i < instrucciones_items.size(); i++) {
							data.addBatch(instrucciones_items.get(i));
							System.out.println(instrucciones_items.get(i));
						}
						error = data.executeBatch();
					}
					if (!error) {
						data.commitTransaction();
						frame.getJTable().setEnabled(true);
						aviso("Se Anulo El Remito Correctamente");
						sacar_remito_de_tabla(remito);
						String idpedido = frame.get_txt_idpedido().getText();
						this.cargar_pedido(idpedido);
					} else {
						data.rollbackTransaction();
						error("Error Anulando Remito");
					}
					data.setAutoCommit();
				} else {
					error("OPERACION CANCELADA");
				}
			}

		}
	}

	public void envio() {
		String idtransporte = frame.get_txt_idtransporte().getText();
		String idprovincia = frame.get_txt_idprovincia().getText();
		String domicilio = frame.get_txt_domicilio().getText();
		String ciudad = frame.get_txt_idciudad().getText();
		String fecha = frame.get_txt_fecha_envio().getText();
		if (idtransporte.compareTo("") != 0) {
			if (domicilio.compareTo("") != 0) {
				if (ciudad.compareTo("") != 0) {
					if (fecha.compareTo("") != 0) {
						if (!this.esFechaVieja(fecha)) {

							String iduser = this.validar_usuario();
							if (iduser.compareTo("") != 0) {
								String idvendedor = "";
								try {
									idvendedor = (String) data
											.getVendedor(iduser)[0][0];
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (idvendedor.compareTo("") != 0) {
									frame.get_txt_idvendedor().setText(
											idvendedor);
									this.evaluarVendedor(frame
											.get_txt_idvendedor());
								}
								boolean error = this._guardar("ENVIO", iduser);
								if (!error) {
									this._envio();
								} else {
									error("Error al Guardar Pedido");
								}
							} else {
								error("OPERACION CANCELADA");
							}
						} else {
							error("La fecha de envio no puede ser vieja");
						}

					} else {
						error("Por Favor Ingrese Una Fecha de Agenda a este pedido");
						frame.get_txt_fecha().requestFocusInWindow();
					}

				} else {
					error("Por Favor Ingrese La Localidad a la cual enviara este pedido");
					frame.get_txt_idciudad().requestFocusInWindow();
				}
			} else {
				error("Por Favor Ingrese El Domicilio al cual enviara este pedido");
				frame.get_txt_domicilio().requestFocusInWindow();
			}
		} else {
			error("Por Favor Ingrese El Transporte Por El cual enviara este pedido");
			frame.get_txt_idtransporte().requestFocusInWindow();
		}
	}

	public void cancelar_remito() {
		if (remito != null) {
			remito.setVisible(false);
			remito.dispose();
			frame.getJTable().setEnabled(true);
			remito = null;
		}
	}

	public void cancelar_importar() {
		if (importar != null) {
			importar.setVisible(false);
			importar.dispose();
			importar = null;
		}
	}

	public void Etiquetar() {
		String idpedido = frame.get_txt_idpedido().getText();
		if (data.existe(idpedido)) {
			frame.getJTable().setEnabled(false);
			String cuenta = frame.get_txt_idcliente().getText();
			String cliente_descripcion = frame.get_txt_cliente_descripcion()
					.getText();
			String idcomprobante = frame.get_txt_idpedido().getText();
			String descripcion = frame.get_txt_pedido_descripcion().getText();
			String fecha = frame.get_txt_fecha().getText();
			String _descripcion = cuenta + " " + cliente_descripcion + " "
					+ descripcion + " " + fecha;
			Object[][] results = new String[3][3];
			results[0][0] = idcomprobante;
			results[0][1] = "MUESTRA " + _descripcion;
			results[0][2] = "1.0";
			results[1][0] = idcomprobante;
			results[1][1] = "ENTREGA " + _descripcion;
			results[1][2] = "1.0";
			results[2][0] = idcomprobante;
			results[2][1] = "GARANTIA " + _descripcion;
			results[2][2] = "1.0";
			if (results != null) {
				if (results.length > 0) {
					_Constructor constructor = (_Constructor) this
							.getConstructor();
					if (this.etiquetas != null) {
						etiquetas.setVisible(false);
						etiquetas.dispose();
					}
					this.etiquetas = new _Etiquetas();
					this.centrar_frame(etiquetas);
					this.etiquetas.get_chk_seleccionar().setName(
							_Interface._chk_seleccionar_etiquetas);
					this.etiquetas.get_chk_seleccionar().addItemListener(
							constructor.getItemListener());
					this.etiquetas.get_btn_imprimir_etiquetas()
							.setActionCommand(
									_Interface._btn_imprimir_etiquetas);
					this.etiquetas.get_btn_imprimir_etiquetas()
							.addActionListener(constructor.getActionListener());
					this.etiquetas.setVisible(true);
					this.etiquetas.requestFocus();
					this.etiquetas.requestFocusInWindow();
					this.crear_empty_etiquetas();
					int units = 0;
					for (int i = 0; i < results.length; i++) {
						String idarticulo = "";

						String cantidad = "";
						double _cantidad = 0.0;
						try {
							idarticulo = results[i][0].toString();
							descripcion = results[i][1].toString();
							cantidad = results[i][2].toString();
							_cantidad = new Double(cantidad.replace(",", ""));

						} catch (Exception e) {

						}
						if (_cantidad > 0) {
							DefaultTableModel model = (DefaultTableModel) etiquetas
									.getJTable().getModel();
							int row = model.getRowCount() - 1;
							etiquetas.getJTable().setValueAt(true, row, 0);
							etiquetas.getJTable()
									.setValueAt(idarticulo, row, 1);
							etiquetas.getJTable().setValueAt(descripcion, row,
									2);
							etiquetas.getJTable().setValueAt(cantidad, row, 3);

							model.setRowCount(model.getRowCount() + 1);

							units += _cantidad;
							etiquetas.get_txt_unidades().setText("" + units);
						}
					}
				}
			}

			frame.getJTable().setEnabled(true);
		} else {
			error("Grabe El pedido para poder imprimir identificadores");
		}

	}

	public void Relaciones(JTextField tx) {
		String idarticulo = tx.getText();
		if (idarticulo.compareTo("") != 0) {
			if (data.existeArticulo(idarticulo)) {
				this.Relaciones(idarticulo);
			} else {
				error("No hay relaciones disponibles para este articulo");
			}
		}
	}

	public void Relaciones(String _idarticulo) {
		Object[][] results = data.getArticulosRelacionados(_idarticulo);
		if (results != null) {
			if (results.length > 0) {
				Object[][] tmp = new Object[results.length][results[0].length + 1];
				for (int i = 0; i < results.length; i++) {
					tmp[i][0] = false;
					for (int j = 0; j < results[0].length; j++) {
						tmp[i][j + 1] = results[i][j];
					}

				}

				if (tmp != null) {
					if (tmp.length > 0) {
						_Constructor constructor = (_Constructor) this
								.getConstructor();
						if (this.relaciones != null) {
							relaciones.setVisible(false);
							relaciones.dispose();
						}
						this.relaciones = new _Relacion();
						this.centrar_frame(relaciones);
						this.relaciones.get_chk_seleccionar().setName(
								_Interface._chk_seleccionar_etiquetas);
						this.relaciones.get_chk_seleccionar().addItemListener(
								constructor.getItemListener());
						this.relaciones.get_btn_importar_relaciones()
								.setActionCommand(
										_Interface._btn_importar_relaciones);
						this.relaciones.get_btn_importar_relaciones()
								.addActionListener(
										constructor.getActionListener());
						this.relaciones.setVisible(true);
						this.relaciones.requestFocus();
						this.relaciones.requestFocusInWindow();
						this.create_table_relaciones(tmp);
						int units = 0;

					}
				}

				frame.getJTable().setEnabled(true);

			}
		}

	}

	public void evaluate_enviar_a_cta(JCheckBox chk) {
		if (chk.isSelected()) {
			remito.get_chk_imprimir_copia().setSelected(true);
		} else {
			boolean copia = data.copia();
			remito.get_chk_imprimir_copia().setSelected(copia);
		}
	}

	public void Down(JTextField tx, int col) {
		this.Down(importar.getJTable());
		// this.focus(col);
		tx.requestFocusInWindow();
	}

	public void Down(JTable table) {
		int row = -1;
		try {
			row = table.getSelectedRow();
		} catch (Exception e) {
		}
		try {

			if (row < table.getRowCount() - 1) {
				table.changeSelection(row + 1, 0, false, false);

				table.transferFocus();

				this.show_pedido_nuevos(row + 1);
				// frame.requestFocusInWindow();
				// frame.getJTable_fields().requestFocusInWindow();
			}
		} catch (Exception e) {
		}

	}

	public void Up(JTextField tx, int col) {
		this.Up(importar.getJTable());
		// this.focus(col);
		tx.requestFocusInWindow();
	}

	public void Up(JTable table) {
		int row = -1;
		try {
			row = table.getSelectedRow();
		} catch (Exception e) {
		}

		try {
			if (row > 0) {
				table.changeSelection(row - 1, 0, false, false);
				table.transferFocus();
				this.show_pedido_nuevos(row - 1);
				frame.requestFocusInWindow();
				table.requestFocusInWindow();
			}

		} catch (Exception e) {
		}
	}

	public void Remito() {
		String idcliente = frame.get_txt_idcliente().getText();
		String idpedido = frame.get_txt_idpedido().getText();
		boolean ok = false;
		boolean copia = data.copia();

		if (!nuevo) {
			if (data.getRemitosValidos(idpedido) <= 0) {
				ok = true;
			} else {
				error("Ya se genero un remito para este pedido.Verifique");
				ok = false;
				// frame.getJTable().setEnabled(false);
			}
		} else {
			ok = true;
		}
		if (ok) {
			String title = "PREVIEW REMITO";
			ok = true;
			String idvendedor = frame.get_txt_idvendedor().getText();
			boolean error = this._guardar(title, idvendedor);
			if (!error) {
				try {
					frame.getJTable().getCellEditor().stopCellEditing();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					// //e2.printStackTrace();
				}
				frame.getJTable().setEnabled(false);
				if (frame.getJTable() != null) {
					if (frame.getJTable().getRowCount() > 0) {
						int count = 0;

						for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
							boolean selected = false;
							try {
								selected = (Boolean) frame.getJTable()
										.getValueAt(i,
												_Interface._columna_selected);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if (selected) {
								String idarticulo = "";
								try {
									idarticulo = frame.getJTable().getValueAt(
											i, _Interface._columna_idarticulo)
											.toString();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (idarticulo.compareTo("") != 0) {
									count++;
								}
							}
						}
						if (count > 0) {

							Object[][] tmp = new Object[count][6];
							for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
								boolean selected = false;
								try {
									selected = (Boolean) frame
											.getJTable()
											.getValueAt(
													i,
													_Interface._columna_selected);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								if (selected) {
									String idarticulo = "";
									try {
										idarticulo = frame
												.getJTable()
												.getValueAt(
														i,
														_Interface._columna_idarticulo)
												.toString();
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if (idarticulo.compareTo("") != 0) {
										try {
											tmp[i][0] = frame
													.getJTable()
													.getValueAt(
															i,
															_Interface._columna_idarticulo)
													.toString();
											tmp[i][1] = frame
													.getJTable()
													.getValueAt(
															i,
															_Interface._columna_descripcion)
													.toString();
											tmp[i][2] = frame
													.getJTable()
													.getValueAt(
															i,
															_Interface._columna_cantidad)
													.toString();
											tmp[i][3] = frame
													.getJTable()
													.getValueAt(
															i,
															_Interface._columna_precio)
													.toString();
											tmp[i][4] = frame
													.getJTable()
													.getValueAt(
															i,
															_Interface._columna_descuento)
													.toString();
											tmp[i][5] = frame
													.getJTable()
													.getValueAt(
															i,
															_Interface._columna_total)
													.toString();
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}

								}

							}

							if (tmp != null) {
								if (tmp.length > 0) {
									_Constructor constructor = (_Constructor) this
											.getConstructor();
									if (this.remito != null) {
										remito.setVisible(false);
										remito.dispose();
									}
									this.remito = new _Remito();
									this.centrar_frame(remito);
									remito.setName(_Interface._remito);
									remito
											.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
									remito.addWindowListener(this
											.getConstructor()
											.getWindowListener());
									this.remito
											.get_txt_cliente_descripcion()
											.setText(
													frame
															.get_txt_cliente_descripcion()
															.getText());
									this.remito.get_txt_idcliente().setText(
											idcliente);
									this.remito.get_txt_fecha().setText(
											frame.get_txt_fecha().getText());
									this.remito.get_txt_idpedido().setText(
											idpedido);
									this.remito
											.get_txt_pedido_descripcion()
											.setText(
													frame
															.get_txt_pedido_descripcion()
															.getText());
									this.remito.get_txt_total().setText(
											frame.get_txt_total().getText());
									this.remito.get_chk_imprimir_copia()
											.setSelected(copia);
									if (data.esCtaCte(idcliente)) {
										this.remito.get_chk_enviar_ctacte()
												.setEnabled(true);
									} else {
										this.remito.get_chk_enviar_ctacte()
												.setSelected(false);
										this.remito.get_chk_enviar_ctacte()
												.setEnabled(false);
									}
									this.remito
											.get_btn_importar_relaciones()
											.setActionCommand(
													_Interface._btn_generar_remito);
									this.remito
											.get_btn_importar_relaciones()
											.addActionListener(
													constructor
															.getActionListener());
									this.remito
											.get_btn_cancelar()
											.setActionCommand(
													_Interface._btn_cancelar_remito);
									this.remito
											.get_btn_cancelar()
											.addActionListener(
													constructor
															.getActionListener());
									this.remito
											.get_btn_salir()
											.setActionCommand(
													_Interface._btn_salir_remito);
									this.remito
											.get_btn_salir()
											.addActionListener(
													constructor
															.getActionListener());
									this.remito
											.get_chk_enviar_ctacte()
											.setName(_Interface._chk_enviar_cta);
									this.remito.get_chk_enviar_ctacte()
											.addItemListener(
													this.getConstructor()
															.getItemListener());
									this.remito.setVisible(true);
									this.remito.requestFocus();
									this.remito.requestFocusInWindow();
									this.create_table_remito_preview(tmp);

								}
							}

						} else {
							error("seleccione los items para generar el remito");
							frame.getJTable().setEnabled(true);
						}

					}
				}

			} else {
				error("Error Guardando");
			}

		}

	}

	public boolean eval_incomplete_row(int row) {
		boolean incomplete = false;
		String descripcion = null;
		String cantidad = null;
		String precio = null;
		try {
			descripcion = (String) frame.getJTable().getValueAt(row,
					_Interface._columna_descripcion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cantidad = (String) frame.getJTable().getValueAt(row,
					_Interface._columna_cantidad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		try {
			precio = (String) frame.getJTable().getValueAt(row,
					_Interface._columna_precio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		incomplete = (precio == null | cantidad == null | descripcion == null);
		if (!incomplete) {
			incomplete = (precio.compareTo("") == 0
					& cantidad.compareTo("") == 0 & descripcion.compareTo("") == 0);
		}

		return incomplete;

	}

	public boolean eval_row(int row) {
		boolean ok = true;
		String idarticulo = null;
		String descripcion = null;
		String cantidad = null;
		String precio = null;
		boolean seleccionado = false;
		try {
			seleccionado = (Boolean) frame.getJTable().getValueAt(row,
					_Interface._columna_selected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ok = false;

		}
		if (ok) {
			try {
				idarticulo = (String) frame.getJTable().getValueAt(row,
						_Interface._columna_idarticulo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok = false;
			}
			if (idarticulo.compareTo("") != 0) {
				if (idarticulo.compareTo("*") == 0) {
					ok = true;
				} else {
					ok = data.existeArticulo(idarticulo);
					if (!ok) {
						error("Error en fila " + row + ". El articulo ("
								+ idarticulo + ") no existe ");
					}
				}
			} else {
				ok = false;
			}

		}

		if (ok) {
			try {
				descripcion = (String) frame.getJTable().getValueAt(row,
						_Interface._columna_descripcion);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok = false;
			}
		}
		if (ok) {
			try {
				cantidad = (String) frame.getJTable().getValueAt(row,
						_Interface._columna_cantidad);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok = false;
			}
		}

		if (ok) {
			try {
				precio = (String) frame.getJTable().getValueAt(row,
						_Interface._columna_precio);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ok = false;
			}
		}
		return ok;
	}

	public boolean eval_row_empty(int row) {
		boolean empty = false;
		String idarticulo = null;

		try {
			idarticulo = (String) frame.getJTable().getValueAt(row,
					_Interface._columna_idarticulo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			empty = true;
		}
		if (idarticulo != null) {
			if (idarticulo.compareTo("") != 0) {
				empty = false;

			} else {
				empty = true;
			}
		} else {
			empty = true;
		}

		return empty;
	}

	public void eval_variables(int row, String cantidad, String precio,
			String descuento) {

		double dsc = 0.0;
		double prc = 0.0;
		double total = 0.0;
		double qty = 0.0;

		try {
			if (descuento.contains("+")) {
				dsc = new Double(descuento);
			} else {
				dsc = this.formula(descuento).doubleValue();
			}
		} catch (Exception e) {

		}
		if (precio.compareTo("") == 0) {
			precio = "0.0";
		}
		if (cantidad.compareTo("") == 0) {
			cantidad = "0.0";
		}
		prc = new Double(precio.replace(",", ""));
		qty = new Double(cantidad.replace(",", ""));
		prc = prc * (100 - dsc) / 100;
		total = prc * qty;
		frame.getJTable().setValueAt(new Convertidor().getMoney(dsc, 2), row,
				_Interface._columna_descuento);
		frame.getJTable().setValueAt(new Convertidor().getMoney(total, 2), row,
				_Interface._columna_total);
	}

	public void eval_variables_from_cantidad(int row, String cantidad) {
		String precio = "0.0";
		String descuento = "0.0";
		try {
			precio = frame.getJTable().getValueAt(row,
					_Interface._columna_precio).toString();
			descuento = frame.getJTable().getValueAt(row,
					_Interface._columna_descuento).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.eval_variables(row, cantidad, precio, descuento);
	}

	public void eval_variables_from_descuento(int row, String descuento) {
		String precio = "0.0";
		String cantidad = "0.0";
		try {
			precio = frame.getJTable().getValueAt(row,
					_Interface._columna_precio).toString();
			cantidad = frame.getJTable().getValueAt(row,
					_Interface._columna_cantidad).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.eval_variables(row, cantidad, precio, descuento);
	}

	public void eval_variables_from_precio(int row, String precio) {
		String descuento = "0.0";
		String cantidad = "0.0";
		try {
			descuento = frame.getJTable().getValueAt(row,
					_Interface._columna_descuento).toString();
			cantidad = frame.getJTable().getValueAt(row,
					_Interface._columna_cantidad).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.eval_variables(row, cantidad, precio, descuento);
		this._calculate();
	}

	private boolean eval_venc(String fecha) {
		boolean ok = false;
		if (fecha.compareTo("") != 0) {
			Calendar _fecha_actualizacion = Calendar.getInstance();
			Date date = null;
			DateFormat formatter;
			Locale locale = Locale.getDefault();
			try {
				formatter = new SimpleDateFormat("dd-MM-yyyy", locale);
				date = (Date) formatter.parse(fecha);

			} catch (Exception e) {

			}
			long diferencia = 1;
			if (date != null) {
				_fecha_actualizacion.setTime(date);
				Date today = new Date();
				java.util.GregorianCalendar _fecha_limite = new java.util.GregorianCalendar();
				_fecha_limite.setTime(today);
				// _today.roll(Calendar.DAY_OF_YEAR, days);
				_fecha_limite.add(Calendar.DAY_OF_YEAR, -30);

				Calendar now = Calendar.getInstance();
				_fecha_actualizacion.add(Calendar.DATE, 27);
				ok = _fecha_limite.before(_fecha_actualizacion);
				// System.out.println(now.getTime()+" "+cal.getTime());
			}
			// System.out.println("dif "+c+" "+diferencia);
		} else {
			// "Fecha Nula"
		}
		return ok;
	}

	public void evaluar_numero(JTextField tx) {

		String numero = tx.getText();
		String tc = "PDC";
		String iduser=this.getConstructor().getIduser();
		String iddeposito=data.getIdDeposito(iduser);
		String negocio=data.getNegocioId(iduser);
		if (data.existe(numero)) {
			if (!nuevo) {
				this.evaluarPDC(tx);
			} else {
				String proximo = data.getProximoPGCorrecto(tc,negocio);
				frame.get_txt_idpedido().setText(proximo);
				this.evaluar_numero(frame.get_txt_idpedido());

			}

		} else {
			frame.get_txt_estado().setText("Nuevo");
			String correcto = data.getProximoPGCorrecto(tc,negocio);
			if (correcto.compareTo(numero) == 0) {
				System.out.println("Nuevo " + tc + " " + numero);
				frame.get_btn_pedidoe().setEnabled(true);
				frame.get_btn_guardar().setEnabled(true);
				frame.get_btn_preparar().setEnabled(true);
				frame.get_btn_transferir().setEnabled(true);
				frame.get_btn_aviso().setEnabled(true);
				frame.get_btn_presupuesto().setEnabled(true);
				frame.get_btn_remito().setEnabled(true);

				frame.get_btn_envio().setEnabled(true);
				frame.get_btn_copia().setEnabled(true);
				frame.get_btn_faltantes().setEnabled(true);
				frame.getLockableUI().setLocked(false);
				frame.get_btn_identificador().setEnabled(true);
				nuevo = true;
				tx.setEditable(false);
				frame.get_txt_idcliente().setEnabled(true);
				frame.get_txt_idvendedor().setEnabled(true);
				frame.get_btn_buscar_cliente().setEnabled(true);
				frame.get_btn_cargar_cliente().setEnabled(true);

				this.consumidor_final();
				this.cargarVendedor();
				
				System.out.println("iduser_"+iduser);

				System.out.println("iddeposiot_"+iddeposito);
				this.cargarDeposito(iddeposito);
				this.cargarNegocio(negocio);
				this.fillCreado();
				this.fillModificado();
				frame.get_txt_pedido_descripcion().setText("VENTA");
				frame.get_txt_pedido_descripcion().setSelectionStart(0);
				frame.get_txt_pedido_descripcion().setSelectionEnd(5);
				frame.get_txt_pedido_descripcion().requestFocusInWindow();
			} else {
				this.evaluarPDC(tx);
			}
		}
	}

	public void evaluarCliente(JTextField tx) {
		cambios = true;
		cliente.evaluate(tx);
	}

	public void evaluarDeposito(JTextField tx) {
		cambios = true;
		tx.setText(tx.getText().replaceAll(" ", ""));
		Deposito.evaluate(tx);
	}
	public void evaluarNegocio(JTextField tx) {
		cambios = true;
		tx.setText(tx.getText().replaceAll(" ", ""));
		Negocio.evaluate(tx);
	}
	public void evaluarFecha(JTextField tx) {
		cambios = true;
		Fecha.evaluate(tx);
	}

	public void evaluarGuia(JTextField tx) {
		cambios = true;
		String guia = tx.getText();
		frame.get_txt_idvendedor().requestFocusInWindow();
	}

	public void evaluarPDC(JTextField tx) {
		
		PDC.evaluate(tx);
	}

	public void evaluarProvincia(JTextField tx) {
		cambios = true;
		Provincia.evaluate(tx);
	}

	public void evaluarTransporte(JTextField tx) {
		cambios = true;
		Transporte.evaluate(tx);
	}

	public void evaluarVendedor(JTextField tx) {
		cambios = true;
		tx.setText(tx.getText().replaceAll(" ", ""));
		Vendedor.evaluate(tx);
	}

	public void evaluate_ciudad(JTextField tx) {
		cambios = true;
		String ciudad = tx.getText();
		if (ciudad.compareTo("") != 0) {
			frame.get_txt_domicilio().requestFocusInWindow();
		} else {
			error("Ingrese una ciudad");
			tx.requestFocusInWindow();
		}
	}

	public void evaluate_cliente_descripcion(JTextField tx) {
		cambios = true;
		String valor = tx.getText();
		if (valor.compareTo("") != 0) {

			String idcliente = frame.get_txt_idcliente().getText();
			Object[][] results = data.getCliente(idcliente);
			String _descripcion = "";
			if (results != null) {
				if (results.length > 0) {
					_descripcion = (String) results[0][1];
				}
			}
			if (valor.compareTo(_descripcion) == 0) {

			} else {

				// frame.get_txt_idprovincia().requestFocusInWindow();
			}
			frame.get_txt_idvendedor().requestFocusInWindow();
			this.evaluarVendedor(frame.get_txt_idvendedor());
			// frame.get_txt_idprovincia().requestFocusInWindow();
		} else {

			tx.setText("Consumidor Final");
			tx.setSelectionStart(0);
			tx.setSelectionEnd(tx.getText().length());
			tx.requestFocusInWindow();
		}

	}

	public void evaluate_cpostal(JTextField tx) {
		cambios = true;
		String cpostal = tx.getText();
		if (cpostal.compareTo("") != 0) {
			frame.get_txt_tel().requestFocusInWindow();
		} else {
			error("Ingrese un codigo postal");
			tx.requestFocusInWindow();
		}
	}

	public void evaluate_descripcion(JTextField tx) {
		cambios = true;
		String valor = tx.getText();
		if (valor.compareTo("") != 0) {
			frame.get_txt_idcliente().requestFocusInWindow();

		} else {
			error("Ingrese una descripcion breve");
		}
	}

	public void evaluate_domicilio(JTextField tx) {
		cambios = true;
		String domicilio = tx.getText();
		if (domicilio.compareTo("") != 0) {
			frame.get_txt_cpostal().requestFocusInWindow();
		} else {
			error("Ingrese una direccion");
			tx.requestFocusInWindow();
		}
	}

	public void evaluate_informacion(JTextArea txa) {
		cambios = true;
		String value = "";
		value = txa.getText();
		frame.get_txt_idvendedor().requestFocusInWindow();
	}

	public void evaluate_sobrescribir(JCheckBox chk) {
		cambios = true;
		si_a_todo = false;
		no_a_todo = !chk.isSelected();

	}

	public void evaluate_telefono(JTextField tx) {
		cambios = true;
		String telefono = tx.getText();
		if (telefono.compareTo("") != 0) {
			frame.get_txt_guia().requestFocusInWindow();
		} else {
			error("Ingrese un numero de telefono");
			tx.requestFocusInWindow();
		}
	}

	public int existArticulo(String idarticulo) {
		int exist = -1;
		int i = 0;
		JTable table = frame.getJTable();
		if (table != null) {
			while (i < table.getRowCount() & exist < 0) {
				String _idarticulo = table.getValueAt(i,
						_Interface._columna_idarticulo).toString();
				if (idarticulo.compareTo(_idarticulo) == 0) {
					exist = i;
				}
				i++;
			}
		}
		return exist;
	}

	/**
	 * Devuelve verdadero si detecta que existe un canje
	 * 
	 * @return
	 */
	public boolean existeCanje() {
		boolean canje = false;
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		boolean stock = false;
		int i = 0;
		while (i < frame.getJTable().getRowCount() & !stock) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				String cantidad = "";

				try {
					articulo = (String) frame.getJTable().getValueAt(i,
							_Interface._columna_idarticulo);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo != null) {
					if (articulo.compareTo("") != 0) {
						double qty = 0;
						try {
							cantidad = (String) frame.getJTable().getValueAt(i,
									_Interface._columna_cantidad);
							qty = new Double(cantidad.replaceAll(",", ""));
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (articulo != null) {
							if (articulo.compareTo("*") != 0) {
								stock = qty < 0;

							}
						}
					}
				}

			}
			i++;
		}
		canje = stock;

		return canje;
	}

	public int existEmpty() {
		int exist = -1;
		int i = 0;
		JTable table = frame.getJTable();
		if (table != null) {
			while (i < table.getRowCount() & exist < 0) {
				String _idarticulo = table.getValueAt(i,
						_Interface._columna_idarticulo).toString();
				if (_idarticulo.compareTo("") == 0) {
					exist = i;
				}
				i++;
			}
		}
		return exist;
	}

	/**
	 * metodo para salir de pedido
	 * 
	 */
	public void exit() {
		boolean error = false;
		String idpedido = frame.get_txt_idpedido().getText();
		if (nuevo) {
			if (cambios) {
				cambios = preguntar("Confirmar",
						"Desde guardar Los Cambios Realizados?");
			}
		} else {
			if (cambios) {
				boolean eliminado = data.eliminado(idpedido);
				if (!eliminado) {
					cambios = preguntar("Confirmar",
							"Desde guardar Los Cambios Realizados?");
				}
			}

		}

		if (cambios) {
			String iduser = this.validar_usuario();

			if (iduser.compareTo("") != 0) {
				String idvendedor = "";
				try {
					idvendedor = (String) data.getVendedor(iduser)[0][0];
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idvendedor.compareTo("") != 0) {
					frame.get_txt_idvendedor().setText(idvendedor);
					this.evaluarVendedor(frame.get_txt_idvendedor());
				}
				error = this._guardar("GUARDAR", iduser);
				if (!error) {

				}
			} else {
				error("PARA GUARDAR LOS CAMBIOS INDENTIFIQUESE CORRECTAMENTE ");
			}

		} else {
			super.exit();
		}

	}

	public void exit_command() {

		if (this.vArticulo != null) {
			this.vArticulo.dispose();
			this.vArticulo = null;
		}
		if (this.Vendedor != null) {
			this.Vendedor.dispose();
			this.Vendedor = null;
		}
		if (this.vEquivalencia != null) {
			this.vEquivalencia.dispose();
			this.vEquivalencia = null;
		}
		if (this.bArticulo != null) {
			this.bArticulo.dispose();
			this.bArticulo = null;
		}
		if (this.cliente != null) {
			this.cliente.dispose();
			this.cliente = null;
		}
		if (this.Catalogo != null) {
			this.Catalogo.dispose();
			this.Catalogo = null;
		}
		if (this.PDC != null) {
			this.PDC.dispose();
			this.PDC = null;
		}
		if (this.Provincia != null) {
			this.Provincia.dispose();
			this.Provincia = null;
		}

		if (this.cliente != null) {
			this.cliente.dispose();
			this.cliente = null;
		}
		if (this.etiquetas != null) {
			this.etiquetas.dispose();
			this.etiquetas = null;
		}
		if (this.importar != null) {
			this.importar.dispose();
			this.importar = null;
		}
		if (this.relaciones != null) {
			this.relaciones.dispose();
			this.relaciones = null;
		}
		if (this.faltantes != null) {
			this.faltantes.dispose();
			this.faltantes = null;
		}
		if (this.articulo != null) {
			this.articulo.dispose();
			this.articulo = null;
		}
		if (this.Vendedor != null) {
			this.Vendedor.dispose();
			this.Vendedor = null;
		}

		if (this.presupuesto != null) {
			this.presupuesto.dispose();
			this.presupuesto = null;
		}
		if (this.remito != null) {
			this.remito.dispose();
			this.remito = null;
		}
		if (this.reporte != null) {
			this.reporte.dispose();
			this.reporte = null;
		}
		if (this.Deposito != null) {
			this.Deposito.dispose();
			this.Deposito = null;
		}
		if (this.Fecha != null) {
			this.Fecha.dispose();
			this.Fecha = null;
		}

		mcliente = null;
		PDC = null;
		pedido = null;
		transferencia = null;
		transporte = null;
		Transporte = null;
		Vendedor = null;
		vEquivalencia = null;
		pedidoe = null;

		equivalencia = null;
		estado = null;
		tc = null;
		validacion = null;
		_cliente = null;
		crono = null;

		super.exit_command();
	}

	public void Faltantes() {
		String idpedido = frame.get_txt_idpedido().getText();
		List<String[]> _faltantes = new ArrayList<String[]>();
		if (data.existe(idpedido)) {
			frame.getJTable().setEnabled(false);

			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
				boolean selected = false;
				try {
					selected = (Boolean) frame.getJTable().getValueAt(i,
							_Interface._columna_selected);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (selected) {
					String articulo = "";
					try {
						articulo = (String) frame.getJTable().getValueAt(i,
								_Interface._columna_idarticulo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String descripcion = "";
					try {
						descripcion = (String) frame.getJTable().getValueAt(i,
								_Interface._columna_descripcion);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					double cantidad = 0.0;
					try {
						cantidad = new Double((String) frame.getJTable()
								.getValueAt(i, _Interface._columna_cantidad));

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (articulo != null) {
						if (articulo.compareTo("*") != 0) {
							double stock = this.check_diference_stock(articulo,
									cantidad);

							String[] faltante = new String[] { articulo,
									descripcion };
							if (stock <= 0) {
								_faltantes.add(faltante);
							}

						}
					}
				}
			}
			if (_faltantes.size() > 0) {

				Object[][] results = new String[_faltantes.size()][3];
				for (int i = 0; i < _faltantes.size(); i++) {
					results[i][0] = _faltantes.get(i)[0];
					results[i][1] = _faltantes.get(i)[1];
					results[i][2] = "1.0";
				}
				if (results != null) {
					if (results.length > 0) {
						this.create_faltantes(true);

						int units = 0;
						for (int i = 0; i < results.length; i++) {
							String idarticulo = "";

							String cantidad = "";
							String descripcion = "";
							double _cantidad = 0.0;
							try {
								idarticulo = results[i][0].toString();
								descripcion = results[i][1].toString();
								cantidad = results[i][2].toString();
								_cantidad = new Double(cantidad
										.replace(",", ""));

							} catch (Exception e) {

							}
							if (_cantidad > 0) {
								DefaultTableModel model = (DefaultTableModel) faltantes
										.getJTable().getModel();
								int row = model.getRowCount() - 1;
								faltantes.getJTable().setValueAt(true, row, 0);
								faltantes.getJTable().setValueAt(idarticulo,
										row, 1);
								faltantes.getJTable().setValueAt(descripcion,
										row, 2);
								faltantes.getJTable().setValueAt(cantidad, row,
										3);

								model.setRowCount(model.getRowCount() + 1);
								units += _cantidad;
								faltantes.get_txt_unidades()
										.setText("" + units);
							}
						}
					}
				}

			}

			frame.getJTable().setEnabled(true);
		} else {
			error("Grabe El pedido para poder marcar los faltantes");
		}

	}

	public void Importar2() {
		aviso("funcion no habilitada");
	}

	public void Importar() {
		String iduser = this.validar_usuario();
		boolean ok = false;
		if (!nuevo) {
			String idpedido = frame.get_txt_idpedido().getText();
			boolean remitos = data.getRemitosValidos(idpedido) > 0;
			if (!remitos) {
				ok = true;
			} else {
				error("No puede importar desde este pedido porque ya ha generado un remito");
			}
		} else {
			ok = true;
		}
		if (ok) {
			if (iduser.compareTo("") != 0) {
				ok = true;
				String idvendedor = "";
				try {
					idvendedor = (String) data.getVendedor(iduser)[0][0];
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idvendedor.compareTo("") != 0) {
					frame.get_txt_idvendedor().setText(idvendedor);
					this.evaluarVendedor(frame.get_txt_idvendedor());
				}
				boolean error = this._guardar("IMPORTAR", idvendedor);
				if (!error) {
					_Importar();
				}
			}
		} else {

		}

	}

	public void _Importar() {

		if (this.importar != null) {
			importar.setVisible(false);
			importar.dispose();
		}
		importar = new _Importar();
		importar.setName(_Interface._importar);
		centrar_frame(importar);
		importar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		importar.addWindowListener(this.getConstructor().getWindowListener());
		importar.get_btn_salir().setActionCommand(
				_Interface._btn_salir_importar);
		importar.get_btn_salir().addActionListener(
				this.getConstructor().getActionListener());
		importar.get_btn_importar_relaciones().setActionCommand(
				_Interface._btn_ejecutar_importacion);
		importar.get_btn_importar_relaciones().addActionListener(
				this.getConstructor().getActionListener());
		importar.get_txt_idpedido().setName(_Interface._importar_idpedido);
		importar.get_txt_idpedido().addKeyListener(
				this.getConstructor().getKeyListener());
		importar.get_txt_pedido_descripcion().setName(
				_Interface._importar_pedido_descripcion);
		importar.get_txt_pedido_descripcion().addKeyListener(
				this.getConstructor().getKeyListener());
		importar.get_txt_idarticulo().setName(_Interface._importar_idarticulo);
		importar.get_txt_idarticulo().addKeyListener(
				this.getConstructor().getKeyListener());
		importar.get_txt_idarticulo_descripcion().setName(
				_Interface._importar_articulo_descripcion);
		importar.get_txt_idarticulo_descripcion().addKeyListener(
				this.getConstructor().getKeyListener());
		importar.get_lst_estado().setName(_Interface._importar_estado);
		importar.get_lst_estado().addKeyListener(
				this.getConstructor().getKeyListener());
		importar.get_txt_idcliente().setName(_Interface._importar_idcliente);
		importar.get_txt_idcliente().addKeyListener(
				this.getConstructor().getKeyListener());
		importar.get_txt_cliente_descripcion().setName(
				_Interface._importar_cliente_descripcion);
		importar.get_txt_cliente_descripcion().addKeyListener(
				this.getConstructor().getKeyListener());
		importar.get_txt_creador().setName(_Interface._importar_creador);
		importar.get_txt_creador().addKeyListener(
				this.getConstructor().getKeyListener());
		importar.get_txt_vendedor().setName(_Interface._importar_vendedor);
		importar.get_txt_vendedor().addKeyListener(
				this.getConstructor().getKeyListener());
		importar.get_txt_informacion()
				.setName(_Interface._importar_informacion);
		importar.get_txt_informacion().addKeyListener(
				this.getConstructor().getKeyListener());
		importar.setVisible(true);
		importar.requestFocus();
		importar.requestFocusInWindow();
		importar.get_txt_idpedido().requestFocusInWindow();

	}

	public void doclean() {
		acumulators = new ArrayList<Object[]>();

		importar.setJTable1(null);
		importar.setJTable(null);

		importar.get_txt_idcliente().setText("");
		importar.get_txt_cliente_descripcion().setText("");
		importar.get_chk_estado().setSelected(false);
		importar.get_txt_idarticulo().setText("");
		importar.get_txt_idpedido().setText("");
		importar.get_txt_vendedor().setText("");
		importar.get_txt_idarticulo_descripcion().setText("");
		// importar.get_txt_idarticulo_linea().setText("");
		importar.get_txt_pedido_descripcion().setText("");
		importar.get_txt_informacion().setText("");
		importar.get_txt_idpedido().requestFocusInWindow();

	}

	public void faltantes_automaticos() {
		boolean asociar = faltantes.get_chk_asociar().isSelected();
		marcar_faltantes(true, asociar);
	}

	/**
	 * 
	 */
	public void faltantes_manual() {
		boolean asociar = faltantes.get_chk_asociar().isSelected();
		marcar_faltantes(false, asociar);
	}

	public void FaltantesPedir() {
		String idpedido = frame.get_txt_idpedido().getText();
		List<String[]> _faltantes = new ArrayList<String[]>();
		if (data.existe(idpedido)) {
			frame.getJTable().setEnabled(false);

			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
				boolean selected = false;
				try {
					selected = (Boolean) frame.getJTable().getValueAt(i,
							_Interface._columna_selected);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (selected) {
					String articulo = "";
					try {
						articulo = (String) frame.getJTable().getValueAt(i,
								_Interface._columna_idarticulo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String descripcion = "";
					try {
						descripcion = (String) frame.getJTable().getValueAt(i,
								_Interface._columna_descripcion);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String _cantidad = (String) frame.getJTable().getValueAt(i,
							_Interface._columna_cantidad);
					double cantidad = 0.0;
					try {
						cantidad = new Double(_cantidad);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (articulo != null) {
						if (articulo.compareTo("") != 0) {
							if (cantidad > 0) {
								String[] faltante = new String[] { articulo,
										descripcion, _cantidad };
								_faltantes.add(faltante);

							}

						}
					}
				}
			}
			if (_faltantes.size() > 0) {

				Object[][] results = new String[_faltantes.size()][3];
				for (int i = 0; i < _faltantes.size(); i++) {
					results[i][0] = _faltantes.get(i)[0];
					results[i][1] = _faltantes.get(i)[1];
					results[i][2] = _faltantes.get(i)[2];
				}
				if (results != null) {
					if (results.length > 0) {
						this.create_faltantes(false);
						int units = 0;
						for (int i = 0; i < results.length; i++) {
							String idarticulo = "";

							String cantidad = "";
							String descripcion = "";
							double _cantidad = 0.0;
							try {
								idarticulo = results[i][0].toString();
								descripcion = results[i][1].toString();
								cantidad = results[i][2].toString();
								_cantidad = new Double(cantidad
										.replace(",", ""));

							} catch (Exception e) {

							}
							if (_cantidad > 0) {
								DefaultTableModel model = (DefaultTableModel) faltantes
										.getJTable().getModel();
								int row = model.getRowCount() - 1;
								faltantes.getJTable().setValueAt(true, row, 0);
								faltantes.getJTable().setValueAt(idarticulo,
										row, 1);
								faltantes.getJTable().setValueAt(descripcion,
										row, 2);
								faltantes.getJTable().setValueAt(cantidad, row,
										3);
								faltantes.get_txt_idcomprobante().setText(
										idpedido);
								model.setRowCount(model.getRowCount() + 1);
								units += _cantidad;
								faltantes.get_txt_unidades()
										.setText("" + units);
							}
						}
					}
				}

			}

			frame.getJTable().setEnabled(true);
		} else {
			error("Grabe El pedido para poder grabar faltantes");
		}

	}

	public void fillCreado() {
		String fecha = new Convertidor()
				.getDateWithFormat("dd-MM-yyyy HH:mm:ss");
		frame.get_txt_fecha_creacion().setText(fecha);
	}

	public void fillCreador() {
		String idcreador = frame.get_txt_idvendedor().getText();
		frame.get_txt_idcreador().setText(idcreador);
		this.fillCreador(idcreador);
	}

	public void fillCreador(String idcreador) {
		Object[][] results = this.Vendedor.getInfo(idcreador);
		if (results != null) {
			if (results.length > 0) {
				String descripcion = (String) results[0][1];
				frame.get_txt_creador().setText(descripcion);
			}
		}

	}

	public void fillExtra(String idarticulo) {
		Object[][] results = data.getData(idarticulo);
		frame.get_txt_articulo().setText("");
		frame.get_txt_articulo_descripcion().setText("");
		frame.get_txt_articulo_stock().setText("");
		if (results != null) {
			if (results.length > 0) {
				Convertidor cv = new Convertidor();
				String _articulo = (String) results[0][0];
				String _descripcion = (String) results[0][1];
				String _linea = (String) results[0][2];
				String _stock = (String) results[0][3];
				String _suspendidov = (String) results[0][4];
				String _actualizacion = (String) results[0][5];
				frame.get_txt_articulo().setText(_articulo);
				frame.get_txt_articulo_descripcion().setText(_descripcion);
				frame.get_txt_articulo_stock().setText(_stock);
				frame.get_txt_articulo_linea().setText(_linea);
				if (_suspendidov.compareTo("1") == 0) {
					frame.get_txt_articulo_bloqueado().setText("BLOQUEADO");
					frame.get_txt_articulo_bloqueado().setBackground(Color.red);
				} else {
					frame.get_txt_articulo_bloqueado().setText("HABILITADO");
					frame.get_txt_articulo_bloqueado().setBackground(
							Color.green);
				}

				if (_actualizacion.compareTo("") == 0) {
					frame.get_txt_articulo_actualizacion().setBackground(
							Color.red);
					_actualizacion = "SIN FECHA";
				} else {
					if (this.eval_venc(_actualizacion)) {
						frame.get_txt_articulo_actualizacion().setBackground(
								Color.green);
					} else {
						frame.get_txt_articulo_actualizacion().setBackground(
								Color.red);
					}
				}
				frame.get_txt_articulo_actualizacion().setText(_actualizacion);

				double _pedido = data.getPedidoCantidad(idarticulo);
				double stock = new Double(_stock);
				frame.get_txt_articulo_pedido()
						.setText(cv.getMoney(_pedido, 2));
				this.cargar_equivalencias(idarticulo);
			}
		}
	}

	public void fillModificado() {
		String fecha = new Convertidor()
				.getDateWithFormat("dd-MM-yyyy HH:mm:ss");
		frame.get_txt_modificado().setText(fecha);
	}

	public void fillStock(int row) {
		if (row >= 0) {
			String idarticulo = "";
			try {
				idarticulo = frame.getJTable().getValueAt(row,
						_Interface._columna_idarticulo).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.fillExtra(idarticulo);
		}

	}

	public void focus() {
		frame.get_txt_idpedido().requestFocusInWindow();

	}

	public void focus(JTable table, int row, int col) {
		table.changeSelection(row, col, false, false);
		table.editCellAt(row, col);
		table.transferFocus();
	}

	public BigDecimal formula(String fx) {

		boolean error = false;
		double multi = 1.0;
		int i = 0;
		int last = 0;
		int len = fx.length();
		if (fx.contains("+")) {
			System.out.println(fx + " contiene +");
			while (i < len) {

				if (fx.substring(i, i + 1).compareTo("+") == 0) {
					System.out.println("(+) encontrado en " + i);
					String number = fx.substring(last, i);
					double _number = 0.0;
					try {
						_number = new Double(number);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (_number > 0) {
						System.out.println("descuento " + _number);
						double ax = (100 - _number) / 100;
						multi = multi * ax;
					}

					last = i + 1;
				}
				i++;
			}
		} else {
			i = fx.length();
		}

		String number = fx.substring(last, i);
		System.out.println(i + "> " + number + " " + last);
		if (number.compareTo("") == 0) {
			number = "0.0";
		}
		double _number = 0.0;
		try {
			_number = new Double(number);
		} catch (Exception e) {
			// /printStackTrace();
		}
		if (_number > 0) {
			System.out.println("descuento " + _number);
			double ax = (100 - _number) / 100;
			multi = multi * ax;
		}
		multi = (1.0 - multi) * 100;
		BigDecimal bd = new BigDecimal(Double.toString(multi));
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
	public void generar_remito_retiro() {
		if (remito.get_chk_enviar_ctacte().isSelected()){
			this.generar_retiro();
		}else{
			this.generar_remito();
		}
	}
	
	public void generar_remito() {
		// boolean seleccion_pedido=this.check_seleccion_del_pedido();
		// boolean articulos_actualizados=check_articulos_actualizados();
		// boolean articulos_validos=check_articulos_validos();

		String idpedido = frame.get_txt_idpedido().getText();
		this.uncheck_null();
		boolean ok = false;

		if (!nuevo) {
			if (data.getRemitosValidos(idpedido) <= 0) {
				ok = true;
			} else {
				error("Ya se genero un remito para este pedido.Verifique");
				ok = false;
			}
		} else {
			ok = true;
		}
		String iduser = "";

		if (ok) {

			ok = this.check_seleccion_del_pedido();
			if (ok) {
//				boolean articulos_validos = false;
//				boolean articulos_canje = false;
//				double global = 0.0;
//				boolean check_descuentos = false;
//				boolean articulos_global = false;
				boolean articulos_validos = true;
				boolean articulos_canje = false;
				double global = 0.0;
				boolean check_descuentos = true;
				boolean articulos_global = true;
				boolean articulos_asterisco = true;
				boolean generar_remito = true;
				// aviso("articulos asterisco="+articulos_asterisco+" si v => ok");
				if (!articulos_asterisco) {
					int sel = preguntar("Confirmar",
							"Genera un remito con asteriscos ",
							new String[] { "Continuar",
									"Voy a Verificar los Codigos" },
							"Voy a Verificar los Codigos");
					if (sel == 0) {
						articulos_asterisco = true;
					}
				}
//				boolean saldo = this.check_saldo();
				boolean saldo = false;
				if (saldo) {
					if (articulos_asterisco) {
						boolean articulos_precios = check_articulos_precios();

						// aviso("articulos precios="+articulos_precios+" si v => ok");
						if (articulos_precios) {
							boolean articulos_actualizados = this
									.check_articulos_actualizados();
							// aviso("articulos actualizados="+articulos_actualizados+" si v => ok");
							if (articulos_actualizados) {
								boolean articulos_stock = check_articulos_stock();
								// aviso("articulos stock="+articulos_stock+" si v => ok");
								if (articulos_stock) {
									articulos_validos = check_articulos_validos();
									// aviso("articulos validos="+articulos_validos+" si v => ok");
									if (articulos_validos) {
										articulos_canje = this.existeCanje();
										if (articulos_canje) {
											int sel = preguntar(
													"Confirmar",
													"Genera un Remito Con CANJE ",
													new String[] { "Continuar",
															"Voy a Verificar las Cantidades" },
													"Voy a Verificar las Cantidades");
											if (sel == 0) {
												articulos_canje = false;
											}
										}
										if (!articulos_canje) {
											// aviso("articulos canje="+articulos_canje);
											global = this
													.check_global_precios();
											articulos_global = (new Convertidor()
													.roundDouble(global, 2) + 0.10) < 0;
											// aviso("articulos global="+articulos_global+" si v => ok");
											if (articulos_global) {
												check_descuentos = check_descuentos_del_pedido();
												// aviso("check descuentos="+check_descuentos+" si v => ok");
												if (check_descuentos) {
													generar_remito = true;
												}

											} else {
												generar_remito = true;
											}
										}
									}
								}
							}

						}

					}
				}

				// ok=(articulos_asterisco & articulos_precios & articulos_stock
				// & articulos_validos & !articulos_canje); orignal

				ok = (generar_remito);
				// aviso("genera remito="+ok);
				// ok=false;
				if (ok) {
					iduser = this.validar_usuario();
					if (iduser.compareTo("") != 0) {
						ok = true;
						String idvendedor = "";
						try {
							idvendedor = (String) data.getVendedor(iduser)[0][0];
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (idvendedor.compareTo("") != 0) {
							frame.get_txt_idvendedor().setText(idvendedor);
							this.evaluarVendedor(frame.get_txt_idvendedor());
						}
					} else {
						ok = false;
					}
				} else {

				}

				if (ok) {
					String title = "REMITO";
					if (articulos_canje) {
						title += " CANJE ";
					}
					boolean error = this._guardar(title, iduser);
					if (!error) {
						error = this._generar_remito();
						if (!error) {

							boolean copia = this.remito
									.get_chk_imprimir_copia().isSelected();
							if (this.remito != null) {
								this.remito.setVisible(false);
								this.remito.dispose();
								this.remito = null;
							}
							aviso("Se Genero el Remito Correctamente");
							if (copia) {
								this.copia();
							}
							// this.cargarRemitos(idpedido);
							if (frame.get_chk_seguimiento().isSelected()) {
								String[] options = new String[] {
										"Quitar Seguimiento",
										"Mantener Seguimiento" };
								int n = (this
										.preguntar(
												"Confirmar",
												"Tal vez ya no necesite el seguimiento de este pedido",
												options, options[0]));
								if (n == 0) {
									frame.get_chk_seguimiento().setSelected(
											false);
									boolean erro = this._guardar("REMITO",
											iduser);
									if (erro) {
										error("Error Quitando Seguimiento");
									} else {
										aviso("Se Quito El Seguimiento");
									}
								}
							}

							if (this.getFaltantes() > 0) {
								frame.setVisible(false);
								this.Faltantes();
							} else {
								this.exit_command();
							}

						} else {
							error("Error Generando Remito");
						}
					}
				} else {
					error("OPERACION CANCELADA");
				}

			} else {
				error("Verifique la seleccion de los Articulos");
			}

		} else {
			error("OPERACION CANCELADA");

		}

	}

	public void generar_retiro() {
		// boolean seleccion_pedido=this.check_seleccion_del_pedido();
		// boolean articulos_actualizados=check_articulos_actualizados();
		// boolean articulos_validos=check_articulos_validos();
		String cuenta = frame.get_txt_idcliente().getText();
		if (cliente.existe(cuenta)) {
			if (cuenta.compareTo("112010001") != 0) {
				String idpedido = frame.get_txt_idpedido().getText();
				this.uncheck_null();
				boolean ok = false;

				if (!nuevo) {
					if (data.getRemitosValidos(idpedido) <= 0) {
						ok = true;
					} else {
						error("Ya se genero un remito para este pedido. Verifique");
						ok = false;
					}
				} else {
					ok = true;
				}
				String iduser = "";

				if (ok) {
					ok = this.check_seleccion_del_pedido();
					if (ok) {
						boolean articulos_validos = false;
						boolean articulos_canje = false;
						double global = 0.0;
						boolean check_descuentos = false;
						boolean articulos_global = false;

						boolean articulos_asterisco = check_articulos_asterisco();
						boolean generar_remito = false;
						// aviso("articulos asterisco="+articulos_asterisco+" si v => ok");
						if (!articulos_asterisco) {
							int sel = preguntar("Confirmar",
									"Genera un remito con asteriscos ",
									new String[] { "Continuar",
											"Voy a Verificar los Codigos" },
									"Voy a Verificar los Codigos");
							if (sel == 0) {
								articulos_asterisco = true;
							}
						}
						boolean saldo = this.check_saldo();
						if (saldo) {
							if (articulos_asterisco) {
								boolean articulos_precios = check_articulos_precios();

								// aviso("articulos precios="+articulos_precios+" si v => ok");
								if (articulos_precios) {
									boolean articulos_actualizados = this
											.check_articulos_actualizados();
									// aviso("articulos actualizados="+articulos_actualizados+" si v => ok");
									if (articulos_actualizados) {
										boolean articulos_stock = check_articulos_stock();
										// aviso("articulos stock="+articulos_stock+" si v => ok");
										if (articulos_stock) {
											articulos_validos = check_articulos_validos();
											// aviso("articulos validos="+articulos_validos+" si v => ok");
											if (articulos_validos) {
												articulos_canje = this
														.existeCanje();
												if (articulos_canje) {
													int sel = preguntar(
															"Confirmar",
															"Genera un Remito Con CANJE ",
															new String[] {
																	"Continuar",
																	"Voy a Verificar las Cantidades" },
															"Voy a Verificar las Cantidades");
													if (sel == 0) {
														articulos_canje = false;
													}
												}
												if (!articulos_canje) {
													// aviso("articulos canje="+articulos_canje);
													global = this
															.check_global_precios();
													articulos_global = (new Convertidor()
															.roundDouble(
																	global, 2) + 0.10) < 0;
													// aviso("articulos global="+articulos_global+" si v => ok");
													if (articulos_global) {
														check_descuentos = check_descuentos_del_pedido();
														// aviso("check descuentos="+check_descuentos+" si v => ok");
														if (check_descuentos) {
															generar_remito = true;
														}

													} else {
														generar_remito = true;
													}
												}
											}
										}
									}

								}

							}
						}

						// ok=(articulos_asterisco & articulos_precios &
						// articulos_stock
						// & articulos_validos & !articulos_canje); orignal

						ok = (generar_remito);
						// aviso("genera remito="+ok);
						// ok=false;
						if (ok) {
							iduser = this.validar_usuario();
							if (iduser.compareTo("") != 0) {
								ok = true;
								String idvendedor = "";
								try {
									idvendedor = (String) data
											.getVendedor(iduser)[0][0];
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (idvendedor.compareTo("") != 0) {
									frame.get_txt_idvendedor().setText(
											idvendedor);
									this.evaluarVendedor(frame
											.get_txt_idvendedor());
								}
							} else {
								ok = false;
							}
						} else {

						}

						if (ok) {
							String title = "REMITO";
							if (articulos_canje) {
								title += " CANJE ";
							}
							boolean error = this._guardar(title, iduser);
							if (!error) {
								error = this._generar_remito();
								if (!error) {

									boolean copia = this.remito
											.get_chk_imprimir_copia()
											.isSelected();
									if (this.remito != null) {
										this.remito.setVisible(false);
										this.remito.dispose();
										this.remito = null;
									}
									String idremito = data
											.getRemitoGenerado(idpedido);
									if (idremito.compareTo("") != 0) {
										String idcomprobante = this.getFvn();
										if (idcomprobante.compareTo("") != 0) {
											this.grabarCtaCte(idremito,
													idcomprobante);
											if (copia) {
												this.copia();
											}
											frame.get_chk_seguimiento()
													.setSelected(false);
											boolean erro = this._guardar(
													"REMITO", iduser);
										}
									}

									if (this.getFaltantes() > 0) {
										frame.setVisible(false);
										this.Faltantes();
									} else {
										this.exit_command();
									}

								} else {
									error("Error Generando Remito");
								}
							}
						} else {
							error("OPERACION CANCELADA");
						}

					} else {
						error("Verifique la seleccion de los Articulos");
					}

				} else {
					error("OPERACION CANCELADA");

				}

			} else {
				error("cuenta no habilitada para retiro");
			}
		}

	}

	public Color getColor(int row, int col, JTable table) {
		Color color = Color.white;
		double stock = 0.0;
		String _stock = "0.0";
		if (table != null) {
			try {
				_stock = table.getValueAt(row, 5).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_stock != null) {
				if (_stock.compareTo("") != 0) {
					stock = new Double(_stock.replaceAll(",", ""));
				}
			}
		}
		// System.out.println("Stock>"+_stock);
		if (stock > 0) {
			color = Color.GREEN;
		}
		return color;
	}

	public _Data getData() {
		return this.data;
	}

	public JTable getDetalle(String _idarticulo) {
		JTable table = null;
		Object[][] _results = data.getData(_idarticulo);
		if (_results != null) {
			if (_results.length > 0) {
				String _articulo = (String) _results[0][0];
				String _descripcion = (String) _results[0][1];
				String _linea = (String) _results[0][2];
				String _stock = (String) _results[0][3];
				String _suspendidov = (String) _results[0][4];
				String _actualizacion = (String) _results[0][5];
				String _publico = (String) _results[0][8];
				Object[][] tmp = new Object[][] { { false, _idarticulo,
						_descripcion, _linea, _publico, _stock } };
				table = create_table_equivalencias(tmp);
			}
		}
		return table;
	}

	public int getFaltantes() {
		String idpedido = frame.get_txt_idpedido().getText();
		int faltantes = 0;
		if (data.existe(idpedido)) {
			frame.getJTable().setEnabled(false);

			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
				boolean selected = false;
				try {
					selected = (Boolean) frame.getJTable().getValueAt(i,
							_Interface._columna_selected);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (selected) {
					String articulo = "";
					try {
						articulo = (String) frame.getJTable().getValueAt(i,
								_Interface._columna_idarticulo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String descripcion = "";
					try {
						descripcion = (String) frame.getJTable().getValueAt(i,
								_Interface._columna_descripcion);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					double cantidad = 0.0;
					try {
						cantidad = new Double((String) frame.getJTable()
								.getValueAt(i, _Interface._columna_cantidad));

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (articulo != null) {
						if (articulo.compareTo("*") != 0) {
							double stock = this.check_diference_stock(articulo,
									cantidad);
							if (stock <= 0) {
								faltantes++;
							}
						}
					}
				}
			}
		}
		return faltantes;
	}

	private String getInstruccionControlItem(int i) {
		String idvendedor = frame.get_txt_idvendedor().getText();
		String idarticulo = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_idarticulo);
		Object[][] results = data.getData(idarticulo);
		String _stock = "";
		String _actualizacion = "";
		if (results != null) {
			_stock = (String) results[0][3];
			_actualizacion = (String) results[0][5];
		}
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String cantidad = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_cantidad);
		String importe = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_precio);
		importe = importe.replaceAll(",", "");
		cantidad = cantidad.replaceAll(",", "");
		Boolean selected = (Boolean) frame.getJTable().getValueAt(i,
				_Interface._columna_selected);
		double _precio = new Double(importe.replaceAll(",", ""));
		double stock = new Double(_stock.replaceAll(",", ""));
		double _cantidad = new Double(cantidad.replaceAll(",", ""));
		double precio_sistema = data.getPrecioPublico(idarticulo);
		String idcliente = frame.get_txt_idcliente().getText();
		data.esResponsableInscripto(idcliente);
		String iduser = this.getConstructor().getIduser();
		String ip = data.getIp();
		if (selected) {
			q = this.data.getInsertControl(idpedido, idvendedor, idarticulo,
					_precio, precio_sistema, _cantidad, stock, _actualizacion,
					iduser, validacion, ip);
		}

		return q;
	}

	private String getInstruccionEncabezado() {
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String descripcion = frame.get_txt_pedido_descripcion().getText();
		descripcion = descripcion.replaceAll("'", "");
		String cliente = frame.get_txt_idcliente().getText();
		String cliente_descripcion = frame.get_txt_cliente_descripcion()
				.getText();
		String idvendedor = frame.get_txt_idvendedor().getText();
		String info = frame.get_txt_informacion().getText();
		String idtransporte = frame.get_txt_idtransporte().getText();
		String fecha_envio = frame.get_txt_fecha_envio().getText();
		String fecha_agenda = frame.get_txt_fecha().getText();
		String guia = frame.get_txt_guia().getText();
		String total = frame.get_txt_total().getText().replaceAll(",", "");

		String domicilio = frame.get_txt_domicilio().getText();
		String ciudad = frame.get_txt_idciudad().getText();
		String idprovincia = frame.get_txt_idprovincia().getText();
		String cpostal = frame.get_txt_cpostal().getText();
		domicilio = domicilio.replaceAll("'", "");
		ciudad = ciudad.replaceAll("'", "");
		String telefono = frame.get_txt_tel().getText();
		total = total.replaceAll(",", "");
		String iddeposito = frame.get_txt_iddeposito().getText();
		if (total.compareTo("") == 0) {
			total = "0.0";
		}
		String seguimiento = "0";
		if (frame.get_chk_seguimiento().isSelected()) {
			seguimiento = "1";
		}
		if (!nuevo) {
			q = data.getUpdateQuery(idpedido, descripcion, cliente,
					cliente_descripcion, idvendedor, info, idtransporte,
					fecha_envio, guia, fecha_agenda, total, seguimiento,
					domicilio, ciudad, idprovincia, cpostal, telefono,
					iddeposito);
		} else {
			q = data.getGuardarQuery(idpedido, descripcion, cliente,
					cliente_descripcion, idvendedor, info, idtransporte,
					fecha_envio, guia, fecha_agenda, total, seguimiento,
					domicilio, ciudad, idprovincia, cpostal, telefono,
					iddeposito);
		}
		return q;
	}

	private String getInstruccionEncabezadoHistory(String idoperacion,
			String operacion, String iduser) {
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String descripcion = frame.get_txt_pedido_descripcion().getText();
		descripcion = descripcion.replaceAll("'", "");
		String cliente = frame.get_txt_idcliente().getText();
		String cliente_descripcion = frame.get_txt_cliente_descripcion()
				.getText();
		String idvendedor = frame.get_txt_idvendedor().getText();
		String info = frame.get_txt_informacion().getText();
		String idtransporte = frame.get_txt_idtransporte().getText();
		String fecha_envio = frame.get_txt_fecha_envio().getText();
		String fecha_agenda = frame.get_txt_fecha().getText();
		String guia = frame.get_txt_guia().getText();
		String total = frame.get_txt_total().getText().replaceAll(",", "");

		String domicilio = frame.get_txt_domicilio().getText();
		String ciudad = frame.get_txt_idciudad().getText();
		String idprovincia = frame.get_txt_idprovincia().getText();
		String cpostal = frame.get_txt_cpostal().getText();
		domicilio = domicilio.replaceAll("'", "");
		ciudad = ciudad.replaceAll("'", "");
		String telefono = frame.get_txt_tel().getText();
		total = total.replaceAll(",", "");
		if (total.compareTo("") == 0) {
			total = "0.0";
		}
		String seguimiento = "0";
		if (frame.get_chk_seguimiento().isSelected()) {
			seguimiento = "1";
		}

		String ip = this.data.getIp();
		q = data.getGuardarQueryHistory(idoperacion, operacion, iduser, ip,
				idpedido, descripcion, cliente, cliente_descripcion,
				idvendedor, info, idtransporte, fecha_envio, guia,
				fecha_agenda, total, seguimiento, domicilio, ciudad,
				idprovincia, cpostal, telefono);
		// q=data.getGuardarQueryHistory(idpedido, descripcion, cliente,
		// cliente_descripcion, idvendedor,
		// info,idtransporte,fecha_envio,guia,fecha_agenda,total,seguimiento,domicilio,ciudad,idprovincia,cpostal,telefono);

		return q;
	}

	private String getInstruccionEncabezadoRemito() {
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String descripcion = frame.get_txt_pedido_descripcion().getText();
		String cliente = frame.get_txt_idcliente().getText();
		String cliente_descripcion = frame.get_txt_cliente_descripcion()
				.getText();
		String idvendedor = frame.get_txt_idvendedor().getText();
		String info = frame.get_txt_informacion().getText();
		String iva = frame.get_txt_iva().getText().replace(",", "");
		String importe = frame.get_txt_total().getText().replace(",", "");
		double _importe = 0.0;
		double _importe_siva = 0.0;
		double _iva = 0.0;
		try {
			_importe = new Double(importe);
			_iva = new Double(iva);
			_importe_siva = _importe - _iva;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String importe_s_iva = ""
				+ new Convertidor().roundDouble(_importe_siva, 2);
		String condicion = "  10";
		String fecha = frame.get_txt_fecha().getText();
		q = data.getInsertRemito(cliente, fecha, idvendedor, importe,
				importe_s_iva, iva, condicion);
		return q;
	}

	private List<String> getInstruccionesActualizarPunteros() {
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = data.getUpdateTc(tc);
		instrucciones.add(instruccion);
		return instrucciones;
	}

	private List<String> getInstruccionesActualizarPunterosRemito() {
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = data.getUpdateRM();
		instrucciones.add(instruccion);
		String idpedido = frame.get_txt_idpedido().getText();
		String iduser = this.getConstructor().getIduser();
		instruccion = data.getInstruccionAsociacionRemitoPedido(idpedido,
				iduser);
		instrucciones.add(instruccion);
		return instrucciones;
	}

	private List<String> getInstruccionesControl() {
		List<String> instrucciones = new ArrayList<String>();
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String idarticulo = "";
			try {
				idarticulo = frame.getJTable().getValueAt(i, 1).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (idarticulo.compareTo("") != 0) {
				Boolean selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
				if (selected) {
					String instruccion = this.getInstruccionControlItem(i);
					instrucciones.add(instruccion);
				}

			}

		}
		return instrucciones;
	}

	private List<String> getInstruccionesEliminarRemito(String remito) {
		// desaparecer el remito del sistema
		// cuando se paso mal el remito..
		// se lo marca como anulado. y se lo desvincula del pedido para q se
		// pueda generar otro
		List<String> instrucciones = new ArrayList<String>();

		String _instruccion = data.getMarcarAnuladoRemito(remito);
		instrucciones.add(_instruccion);
		_instruccion = data.getDeleteRemitoPedidoQuery(remito);
		instrucciones.add(_instruccion);
		return instrucciones;
	}

	private List<String> getInstruccionesGuardarEncabezado(String idoperacion,
			String operacion, String iduser) {
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = this.getInstruccionEncabezado();
		instrucciones.add(instruccion);
		instruccion = this.getInstruccionEncabezadoHistory(idoperacion,
				operacion, iduser);
		instrucciones.add(instruccion);
		instruccion = data.getUpdateTc("PDCH");
		instrucciones.add(instruccion);
		return instrucciones;
	}

	private List<String> getInstruccionesGuardarEncabezadoRemito() {
		List<String> instrucciones = new ArrayList<String>();
		String instruccion = this.getInstruccionEncabezadoRemito();
		instrucciones.add(instruccion);
		return instrucciones;

	}

	private List<String> getInstruccionesGuardarItems(String idoperacion) {
		String idpedido = frame.get_txt_idpedido().getText();
		List<String> instrucciones = new ArrayList<String>();
		instrucciones.add(this.getInstruccionItemDeleteLast());
		int item = 0;
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {

			if (!this.eval_row_empty(i)) {

				if (this.eval_row(i)) {

					String instruccion = this.getInstruccionItem(i, "" + i);
					instrucciones.add(instruccion);
					instruccion = this.getInstruccionItemHistory(idoperacion,
							i, "" + i);
					instrucciones.add(instruccion);

				} else {
					String instruccion = this.data.getDeleteItemNumberQuery(
							idpedido, i);
					instrucciones.add(instruccion);
				}

			} else {
				String instruccion = this.data.getDeleteItemNumberQuery(
						idpedido, i);
				instrucciones.add(instruccion);
			}

		}

		return instrucciones;
	}

	private List<String> getInstruccionesGuardarRemitoItems() {
		List<String> instrucciones = new ArrayList<String>();

		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {

			String idarticulo = "";
			try {
				idarticulo = frame.getJTable().getValueAt(i,
						_Interface._columna_idarticulo).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			if (idarticulo.compareTo("") != 0) {
				Boolean selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
				if (selected) {
					String instruccion = this.getInstruccionRemitoItem(i);
					instrucciones.add(instruccion);
				}

			}

		}

		return instrucciones;
	}

	private String getInstruccionItem(int i, String item) {
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();

		String idarticulo = "";
		try {
			idarticulo = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_idarticulo);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String descripcion = "";
		try {
			descripcion = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_descripcion);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String cantidad = "";
		try {
			cantidad = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_cantidad);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String costo = "0.0";
		String cotiza = "0.0";
		String total = "0.0";
		String descuento = "0.0";
		try {
			costo = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_costo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cotiza = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_precio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			descuento = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_descuento);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			total = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_total);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (total == null) {
			total = "0.0";
		} else {
			if (total.compareTo("") == 0) {
				total = "0.0";
			}
		}
		String seleccionado = "0";
		if (idarticulo == null) {
			idarticulo = "";
		}
		if (descripcion == null) {
			descripcion = "";
		}
		if (cantidad == null) {
			cantidad = "";
		}
		if (cotiza == null) {
			cotiza = "";
		}
		if (costo == null) {
			costo = "";
		}
		try {
			descripcion = descripcion.replaceAll("'", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		total = total.replaceAll(",", "");
		cotiza = cotiza.replaceAll(",", "");
		costo = costo.replaceAll(",", "");
		cantidad = cantidad.replaceAll(",", "");
		Boolean selected = false;
		try {
			selected = (Boolean) frame.getJTable().getValueAt(i,
					_Interface._columna_selected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (selected == null) {
			selected = false;
		}
		if (selected) {
			seleccionado = "1";
		}
		if (!nuevo) {
			if (data.existItemPedido(idpedido, i)) {
				q = data.getUpdateItems(idpedido, item, idarticulo,
						descripcion, cantidad, costo, cotiza, total,
						seleccionado, descuento);
			} else {
				q = data.getGuardarItemQuery(idpedido, item, idarticulo,
						descripcion, cantidad, costo, cotiza, total,
						seleccionado, descuento);
			}

		} else {
			q = data.getGuardarItemQuery(idpedido, item, idarticulo,
					descripcion, cantidad, costo, cotiza, total, seleccionado,
					descuento);
		}
		System.out.println(q);
		return q;
	}

	private String getInstruccionItemDeleteLast() {
		String idpedido = frame.get_txt_idpedido().getText();
		int last = frame.getJTable().getRowCount() - 1;
		boolean empty = this.eval_row_empty(last);
		while (empty & last >= 0) {
			last--;
			empty = this.eval_row_empty(last);
		}
		String instruccion = this.data.getDeleteItemQuery(idpedido, last);
		return instruccion;
	}

	private String getInstruccionItemHistory(String idoperacion, int i,
			String item) {
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();

		String idarticulo = "";
		try {
			idarticulo = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_idarticulo);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String descripcion = "";
		try {
			descripcion = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_descripcion);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String cantidad = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_cantidad);

		String costo = "0.0";
		String cotiza = "0.0";
		String total = "0.0";
		String descuento = "0.0";
		try {
			costo = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_costo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cotiza = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_precio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			descuento = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_descuento);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			total = (String) frame.getJTable().getValueAt(i,
					_Interface._columna_total);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (total == null) {
			total = "0.0";
		} else {
			if (total.compareTo("") == 0) {
				total = "0.0";
			}
		}
		String seleccionado = "0";
		if (idarticulo == null) {
			idarticulo = "";
		}
		if (descripcion == null) {
			descripcion = "";
		}
		if (cantidad == null) {
			cantidad = "";
		}
		if (cotiza == null) {
			cotiza = "";
		}
		if (costo == null) {
			costo = "";
		}

		try {
			descripcion = descripcion.replaceAll("'", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		descripcion = descripcion.replaceAll("'", "");
		total = total.replaceAll(",", "");
		cotiza = cotiza.replaceAll(",", "");
		costo = costo.replaceAll(",", "");
		cantidad = cantidad.replaceAll(",", "");
		Boolean selected = false;
		try {
			selected = (Boolean) frame.getJTable().getValueAt(i,
					_Interface._columna_selected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (selected == null) {
			selected = false;
		}
		if (selected) {
			seleccionado = "1";
		}
		q = data.getGuardarItemQueryHistory(idoperacion, idpedido, item,
				idarticulo, descripcion, cantidad, costo, cotiza, total,
				seleccionado, descuento);

		return q;
	}

	private String getInstruccionRemitoItem(int i) {
		double aliciva = 1.21;
		String q = "";
		String idpedido = frame.get_txt_idpedido().getText();
		String item = "" + i;
		String idcliente = frame.get_txt_idcliente().getText();
		String idarticulo = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_idarticulo);
		String descripcion = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_descripcion);
		String cantidad = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_cantidad);
		String costo = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_costo);
		String importe = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_precio);
		String descuento = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_descuento);
		String _total = (String) frame.getJTable().getValueAt(i,
				_Interface._columna_total);
		_total = _total.replaceAll(",", "");
		importe = importe.replaceAll(",", "");
		costo = costo.replaceAll(",", "");
		cantidad = cantidad.replaceAll(",", "");
		double _descuento = 0.0;
		double _cantidad = 0.0;
		double _precio = 0.0;
		double total = 0.0;
		try {
			_descuento = new Double(descuento);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			_cantidad = new Double(cantidad);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			_precio = new Double(importe);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		_precio = _precio * (100 - _descuento) / 100;

		total = _precio * _cantidad;

		descripcion = descripcion.replaceAll("'", "");

		Boolean selected = (Boolean) frame.getJTable().getValueAt(i,
				_Interface._columna_selected);

		double _neto = _precio / aliciva;
		_precio = new Convertidor().roundDouble(_precio, 2);
		_neto = new Convertidor().roundDouble(_neto, 2);
		total = new Convertidor().roundDouble(total, 2);
		double _costo = new Double(costo.replaceAll(",", ""));

		if (selected) {

			boolean ri = data.esResponsableInscripto(idcliente);
			boolean raro = data.getParametroServer("genera_raro")
					.compareTo("1") == 0;
			if (raro) {
//				if (ri) {
//					total = _neto * _cantidad;
//					q = this.data.getInsertRemitoItem(idarticulo, descripcion,
//							_cantidad, _costo, _neto, _neto, total, total);
//				} else {
					total = new Convertidor().roundDouble(_precio * _cantidad,
							2);
					q = this.data.getInsertRemitoItem(idarticulo, descripcion,
							_cantidad, _costo, _precio, _neto, total, total);
//				}
			} else {
//				if (ri) {
//					_neto = new Convertidor().roundDouble(_neto, 2);
//					total = _neto * _cantidad;
//					_neto = new Convertidor().roundDouble(_neto, 2);
//					q = this.data.getInsertRemitoItem(idarticulo, descripcion,
//							_cantidad, _costo, _precio, _neto, total, total);
//				} else {
					total = new Convertidor().roundDouble(_precio * _cantidad,
							2);
					q = this.data.getInsertRemitoItem(idarticulo, descripcion,
							_cantidad, _costo, _precio, _neto, total, total);
				//}
			}

		}

		return q;
	}

	public double getPrecioReal(String idarticulo, String clase) {
		double real = 0.0;
		Object[][] results = data.getArticulo(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				String precio2 = (String) results[0][1];
				String costo = (String) results[0][2];
				if (precio2.compareTo("") != 0) {
					try {
						real = new Double(precio2);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
		return real;

	}

	public boolean getShowCost() {
		boolean b = frame.get_chk_costo().isSelected();

		return b;
	}

	public void getToday() {
		_Frame _frame = (_Frame) this._frame;
		_frame.get_txt_fecha().setText(
				new Convertidor().getDateWithFormat("dd-MM-yyyy"));
	}

	public void goMa_Articulos(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
		articulo.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		articulo.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler().Clone());
		articulo.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		articulo
				.setParameter(
						aplicacion.inventario.articulo.interfaces._parametros.idarticulo,
						idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}

	public void guardar() {
		this._calculate();
		String idpedido = frame.get_txt_idpedido().getText();

		String iduser = this.validar_usuario();
		if (iduser.compareTo("") != 0) {
			String idvendedor = "";
			try {
				idvendedor = (String) data.getVendedor(iduser)[0][0];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean ok = true;

			frame.get_txt_idvendedor().setText(idvendedor);
			this.evaluarVendedor(frame.get_txt_idvendedor());

			if (ok) {
				boolean error = this._guardar("GUARDAR", iduser);
				if (!error) {
					if (nuevo) {
						fillCreador();
					}

					nuevo = false;
					this.aviso("Se Grabo Correctamente");
				} else {
					this.error("Error Grabando Comprobante");
				}
			} else {
				error("OPERACION CANCELADA");
			}

		} else {
			error("OPERACION CANCELADA");
		}

	}

	public void importar_equivalencias() {
		JTable equivalencias = frame.getJTable_equivalencias();
		if (equivalencias != null) {
			for (int i = 0; i < equivalencias.getRowCount(); i++) {
				String idarticulo = equivalencias.getValueAt(i, 1).toString();
				this.agregar(idarticulo);
			}
		}
	}

	public void imprimir_etiquetas() {

		LinkedList l = new LinkedList();
		try {
			etiquetas.getJTable().clearSelection();
			etiquetas.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {

		}
		etiquetas.getJTable().setEnabled(false);
		int etqs = 0;
		for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) etiquetas.getJTable().getValueAt(i, 0);
			} catch (Exception e) {

			}
			if (b)
				etqs++;
		}
		if (etqs > 0) {
			if (confirmar("Confirme Para Imprimir Etiquetas:", 2)) {
				data.beginTransaction();
				data.clearBatch();
				for (int i = 0; i < etiquetas.getJTable().getRowCount(); i++) {
					boolean b = false;
					try {
						b = (Boolean) etiquetas.getJTable().getValueAt(i, 0);
					} catch (Exception e) {

					}

					String idarticulo = "";
					String descripcion = "";
					String cant = "";
					double _cant = 0.0;

					try {
						idarticulo = etiquetas.getJTable().getValueAt(i, 1)
								.toString();
						descripcion = etiquetas.getJTable().getValueAt(i, 2)
								.toString();
						cant = etiquetas.getJTable().getValueAt(i, 3)
								.toString();
						_cant = new Double(cant);
					} catch (Exception e) {

					}
					if (b & _cant >= 1) {
						String q = "insert into b_etiquetas (fecha,idarticulo,descripcion,cantidad) ";
						q += "values (getdate(),'" + idarticulo + "','"
								+ descripcion + "'," + _cant + ")";
						data.addBatch(q);
					}

				}
				data.executeBatch();
				data.commitTransaction();
				etiquetas.setVisible(false);
				etiquetas.dispose();
			}

		} else {
			aviso("No hay etiquetas para imprimir");
			if (preguntar("Etiquetas", "Cierra Ventana de Etiquetas?")) {
				etiquetas.setVisible(false);
				etiquetas.dispose();
			}
		}
	}

	public void importar_relaciones() {

		LinkedList l = new LinkedList();
		try {
			relaciones.getJTable().clearSelection();
			relaciones.getJTable().getCellEditor().stopCellEditing();
		} catch (Exception e) {

		}
		relaciones.getJTable().setEnabled(false);
		int etqs = 0;
		for (int i = 0; i < relaciones.getJTable().getRowCount(); i++) {
			boolean b = false;
			try {
				b = (Boolean) relaciones.getJTable().getValueAt(i, 0);
			} catch (Exception e) {

			}
			if (b)
				etqs++;
		}
		if (etqs > 0) {
			if (confirmar("Confirme Para Importar relaciones:", 2)) {
				for (int i = 0; i < relaciones.getJTable().getRowCount(); i++) {
					boolean b = false;
					try {
						b = (Boolean) relaciones.getJTable().getValueAt(i, 0);
					} catch (Exception e) {

					}

					String idarticulo = "";
					String descripcion = "";
					String cant = "";
					double _cant = 0.0;

					try {
						idarticulo = relaciones.getJTable().getValueAt(i, 1)
								.toString();
					} catch (Exception e) {

					}
					if (b) {
						this.agregar(idarticulo);
					}

				}
				relaciones.setVisible(false);
				relaciones.dispose();
			}

		} else {
			aviso("No hay relaciones para agregar");
			if (preguntar("Etiquetas", "Cierra Ventana de Relaciones?")) {
				relaciones.setVisible(false);
				relaciones.dispose();
			}
		}
	}

	public void init_deposito(String iddeposito) {
		frame.get_txt_iddeposito().setText(iddeposito);
		this.evaluarDeposito(frame.get_txt_iddeposito());
	}

	public void initialize_cliente() {
		cliente = new aplicacion.herramientas.java.evaluadores.Cliente() {
			public void cargar(String codigo) {
				cargarCliente(codigo);
			}
		};
		cliente.setConstructor(this.getConstructor());

	}

	public void initialize_Deposito() {
		Deposito = new aplicacion.herramientas.java.evaluadores.Deposito() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				frame.get_txt_iddeposito().setText(cod);
				frame.get_txt_deposito_descripcion().setText(descripcion);
			}
		};
		Deposito.setConstructor(this.getConstructor());
	}
	
	public void initialize_Negocio() {
		Negocio = new aplicacion.herramientas.java.evaluadores.Negocio() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				frame.get_txt_negocio().setText(cod);
				obtener_proximo_cpte();
				frame.get_txt_numero().requestFocusInWindow();
				
			}
		};
		Negocio.setConstructor(this.getConstructor());
	}

	public void initialize_Fecha() {
		Fecha = new aplicacion.herramientas.java.evaluadores.Fecha() {
			public void cargar(JTextField tx) {
				transfer_fecha_focus(tx);
			}
		};
		Fecha.setConstructor(this.getConstructor());
	}

	public void initialize_PDC() {
		PDC = new aplicacion.herramientas.java.evaluadores.PedidoCliente() {
			public void cargar(String codigo) {
				//cargar_pedido(codigo);
			}
		};
		PDC.setConstructor(this.getConstructor());
	}

	public void initialize_Provincia() {
		Provincia = new aplicacion.herramientas.java.evaluadores.Provincia() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				frame.get_txt_provincia_descripcion().setText(descripcion);
				frame.get_txt_idciudad().requestFocusInWindow();
			}
		};
		Provincia.setConstructor(this.getConstructor());
	}

	public void initialize_Transporte() {
		Transporte = new aplicacion.herramientas.java.evaluadores.Transporte() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				frame.get_txt_transporte_descripcion().setText(descripcion);
				String fecha_envio = frame.get_txt_fecha_envio().getText();
				if (fecha_envio.compareTo("") == 0
						| fecha_envio.compareTo("01-01-1900") == 0) {
					frame.get_txt_fecha_envio().setText(
							new Convertidor().getDateWithFormat("dd-MM-yyyy"));
				}
				frame.get_txt_fecha_envio().requestFocusInWindow();
			}
		};
		Transporte.setConstructor(this.getConstructor());
	}

	public void initialize_Vendedor() {
		Vendedor = new aplicacion.herramientas.java.evaluadores.Vendedor() {
			public void cargar(String codigo) {
				Object[][] results = this.getInfo(codigo);
				String descripcion = (String) results[0][1];
				String cod = (String) results[0][0];
				frame.get_txt_idvendedor().setText(cod);
				frame.get_txt_vendedor_descripcion().setText(descripcion);
				transfer_focus_articulos();
			}
		};
		Vendedor.setConstructor(this.getConstructor());
	}

	public void loadFromXML() {
		frame.getLockableUI().setLocked(false);
		super.loadFromXML();
	}

	/**
	 * graba los faltantes seleccionados. si close es true entonces cierra todo
	 * al terminar
	 * 
	 * @param close
	 */
	public void marcar_faltantes(boolean close, boolean asociar) {
		String idcomprobante = "";
		if (asociar) {
			idcomprobante = frame.get_txt_idpedido().getText();
		}

		String iduser = this.validar_usuario();
		if (iduser.compareTo("") != 0) {
			String idvendedor = "";
			try {
				idvendedor = (String) data.getVendedor(iduser)[0][0];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (idvendedor.compareTo("") != 0) {
				LinkedList l = new LinkedList();
				try {
					faltantes.getJTable().clearSelection();
					faltantes.getJTable().getCellEditor().stopCellEditing();
				} catch (Exception e) {

				}
				faltantes.getJTable().setEnabled(false);
				int etqs = 0;
				for (int i = 0; i < faltantes.getJTable().getRowCount(); i++) {
					boolean b = false;
					try {
						b = (Boolean) faltantes.getJTable().getValueAt(i, 0);
					} catch (Exception e) {

					}
					if (b)
						etqs++;
				}
				if (etqs > 0) {
					int errors = 0;
					for (int i = 0; i < faltantes.getJTable().getRowCount(); i++) {
						boolean b = false;
						try {
							b = (Boolean) faltantes.getJTable()
									.getValueAt(i, 0);
						} catch (Exception e) {

						}

						String idarticulo = "";
						String descripcion = "";
						String cant = "";
						double _cant = 0.0;

						try {
							idarticulo = faltantes.getJTable().getValueAt(i, 1)
									.toString();
							descripcion = faltantes.getJTable()
									.getValueAt(i, 2).toString();
							cant = faltantes.getJTable().getValueAt(i, 3)
									.toString();
							_cant = new Double(cant);
						} catch (Exception e) {

						}
						if (b & _cant >= 1) {
							boolean error = this.registrar_faltante(idarticulo,
									descripcion, idvendedor, idcomprobante);
							if (error) {
								errors++;
							}

						}

					}
					if (errors > 0) {
						error("Error Grabando Faltantes. Verifique");
					} else {
						aviso("Se Registraron Faltantes Correctamente");
					}

					faltantes.setVisible(false);
					faltantes.dispose();
					if (close) {
						this.exit_command();
					}

				} else {
					aviso("No hay faltantes para marcar");
					if (preguntar("Faltantes", "Cierra Ventana de Faltantes?")) {
						faltantes.setVisible(false);
						faltantes.dispose();
						if (close) {
							this.exit_command();
						}
					}
				}

			}
		} else {
			error("Operacion Cancelada");
		}

	}

	public void marcar_faltantes_pedido() {
		String idcomprobante = faltantes.get_txt_idcomprobante().getText();
		String iduser = this.validar_usuario();
		if (iduser.compareTo("") != 0) {
			String idvendedor = "";
			try {
				idvendedor = (String) data.getVendedor(iduser)[0][0];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (idvendedor.compareTo("") != 0) {
				LinkedList l = new LinkedList();
				try {
					faltantes.getJTable().clearSelection();
					faltantes.getJTable().getCellEditor().stopCellEditing();
				} catch (Exception e) {

				}
				faltantes.getJTable().setEnabled(false);
				int etqs = 0;
				for (int i = 0; i < faltantes.getJTable().getRowCount(); i++) {
					boolean b = false;
					try {
						b = (Boolean) faltantes.getJTable().getValueAt(i, 0);
					} catch (Exception e) {

					}
					if (b)
						etqs++;
				}
				if (etqs > 0) {
					int errors = 0;
					for (int i = 0; i < faltantes.getJTable().getRowCount(); i++) {
						boolean b = false;
						try {
							b = (Boolean) faltantes.getJTable()
									.getValueAt(i, 0);
						} catch (Exception e) {

						}

						String idarticulo = "";
						String descripcion = "";
						String cant = "";
						double _cant = 0.0;

						try {
							idarticulo = faltantes.getJTable().getValueAt(i, 1)
									.toString();
							descripcion = faltantes.getJTable()
									.getValueAt(i, 2).toString();
							cant = faltantes.getJTable().getValueAt(i, 3)
									.toString();
							_cant = new Double(cant);
						} catch (Exception e) {

						}
						if (b & _cant >= 1) {
							boolean error = this.registrar_faltante(idarticulo,
									descripcion, idvendedor, idcomprobante);
							if (error) {
								errors++;
							}

						}

					}
					if (errors > 0) {
						error("Error Grabando Faltantes. Verifique");
					} else {
						aviso("Se Registraron Faltantes Correctamente");
					}

					faltantes.setVisible(false);
					faltantes.dispose();
					this.exit_command();

				} else {
					aviso("No hay faltantes para marcar");
					if (preguntar("Faltantes", "Cierra Ventana de Faltantes?")) {
						faltantes.setVisible(false);
						faltantes.dispose();
						this.exit_command();
					}
				}

			}
		} else {
			error("Operacion Cancelada");
		}

	}

	public void nuevo() {

		if (this.cambios) {
			if (preguntar("Guardar", "Desea Guardar los cambios realizados?")) {
				this.guardar();
			}
		}

		this.clean();
		this.nuevo = true;

		String iduser= this.getConstructor().getIduser();
		String negocio= data.getNegocioId(iduser);
		frame.get_txt_negocio().setText(negocio);
		this.evaluarNegocio(frame.get_txt_negocio());

		//this.evaluar_numero(frame.get_txt_idpedido());
		this.cambios = false;
	}

	public void nuevo_pedido() {
		if (pedido != null) {
			pedido.dispose();
		}
		pedido = new aplicacion.compras.pedidoe.constructor._Constructor();
		pedido.setParameter(_parametros.connector, this.getConstructor()
				.getConnectionHandler().Clone());
		pedido.setParameter(_parametros.iduser, this.getConstructor()
				.getIduser());
		pedido.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		pedido.build(this.getConstructor());
		pedido.init();

		aplicacion.compras.pedidoe.logic._Logic logic = (aplicacion.compras.pedidoe.logic._Logic) pedido
				.getLogic();
		String idvendedor = frame.get_txt_idvendedor().getText();
		logic.nuevo(idvendedor);
		String idpedido = frame.get_txt_idpedido().getText();
		String descripcion = frame.get_txt_pedido_descripcion().getText();
		String cliente = frame.get_txt_idcliente().getText();
		String cliente_descripcion = frame.get_txt_cliente_descripcion()
				.getText();
		String vendedor = frame.get_txt_idvendedor().getText();
		String fecha = frame.get_txt_fecha().getText();
		String[] pdc = new String[] { idpedido, descripcion, fecha, cliente,
				cliente_descripcion, vendedor };
		logic.agregar_pedido(pdc);

		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			boolean seleccion = false;
			try {
				seleccion = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (seleccion) {
				String idarticulo = (String) frame.getJTable().getValueAt(i,
						_Interface._columna_idarticulo);
				String _descripcion = (String) frame.getJTable().getValueAt(i,
						_Interface._columna_descripcion);
				String cantidad = (String) frame.getJTable().getValueAt(i,
						_Interface._columna_cantidad);

				logic.agregar_pdc(new String[] { idarticulo, _descripcion,
						cantidad, idpedido });
			}
		}

		logic.focus_cuenta();
	}

	public void obtener_proximo_cpte() {
		String iduser=this.getConstructor().getIduser();
		String negocio=data.getNegocioId(iduser);
		String cb = data.getProximoPGCorrecto(tc,negocio);
		frame.get_txt_numero().setText(cb);

	}

	public boolean old_elegir_vendedor() {
		boolean ok = false;
		System.out.println("elegir vendedor");
		String q = "select nombre from v_ta_vendedores order by nombre";
		Object[][] results = data.getResults(q);
		String[] options = null;
		if (results != null) {
			if (results.length > 0) {
				options = new String[results.length + 1];
				options[0] = "";
				for (int i = 0; i < results.length; i++) {
					options[i + 1] = (String) results[i][0];
				}
			}
		}
		if (options != null) {
			System.out.println("elegir vendedor");
			String idvendedor = "  11";
			String selected = "";
			while (selected.compareTo("") == 0) {
				selected = this.seleccion("Seleccion", "Seleccione Vendedor",
						options);
			}
			if (selected.compareTo("") != 0) {
				Object[][] results2 = data.getVendedorPorNombre(selected);
				if (results2 != null) {
					if (results2.length > 0) {
						idvendedor = (String) results2[0][0];
						ok = true;
					}
				}
			}
			frame.get_txt_idvendedor().setText(idvendedor);
			this.evaluarVendedor(frame.get_txt_idvendedor());
		} else {
			System.out.println("no hay vendedores con ese codigo");
		}
		return ok;
	}

	public void pedir() {

		if (this.check_seleccion_del_pedido()) {

			String iduser = this.validar_usuario();
			if (iduser.compareTo("") != 0) {
				String idvendedor = "";
				try {
					idvendedor = (String) data.getVendedor(iduser)[0][0];
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idvendedor.compareTo("") != 0) {
					frame.get_txt_idvendedor().setText(idvendedor);
					this.evaluarVendedor(frame.get_txt_idvendedor());
				}
				boolean error = this._guardar("PEDIR", iduser);
				if (!error) {
					this.nuevo_pedido();
				}
			} else {
				error("OPERACION CANCELADA");
			}
		}
	}

	public void Pedir() {
		String idpedido = frame.get_txt_idpedido().getText();
		Object[][] aux = null;
		if (data.existe(idpedido)) {
			int items = 0;
			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
				boolean seleccion = false;
				try {
					seleccion = (Boolean) frame.getJTable().getValueAt(i,
							_Interface._columna_selected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (seleccion) {
					String idarticulo = "";
					try {
						idarticulo = (String) frame.getJTable().getValueAt(i,
								_Interface._columna_idarticulo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (idarticulo.compareTo("") != 0) {
						items++;
					}

				}
			}
			if (items > 0) {
				aux = new Object[items][6];
				items = 0;
				for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
					boolean seleccion = false;
					try {
						seleccion = (Boolean) frame.getJTable().getValueAt(i,
								_Interface._columna_selected);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (seleccion) {
						String idarticulo = "";
						try {
							idarticulo = (String) frame.getJTable().getValueAt(
									i, _Interface._columna_idarticulo);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (idarticulo.compareTo("") != 0) {
							String descripcion = (String) frame.getJTable()
									.getValueAt(i,
											_Interface._columna_descripcion);
							String cantidad = (String) frame
									.getJTable()
									.getValueAt(i, _Interface._columna_cantidad);
							Object[][] results = data.getData(idarticulo);
							String _idproveedor = "";
							String _nombre = "";
							if (results != null) {
								if (results.length > 0) {
									_idproveedor = (String) results[0][6];
									_nombre = (String) results[0][7];
								}
							}
							aux[items][0] = false;
							aux[items][1] = idarticulo;
							aux[items][2] = descripcion;
							aux[items][3] = cantidad;
							aux[items][4] = _idproveedor;
							aux[items][5] = _nombre;
							items++;
						}

					}
				}
			}

			if (aux != null) {
				if (aux.length > 0) {
					_Constructor constructor = (_Constructor) this
							.getConstructor();
					if (this.pedidoe != null) {
						pedidoe.setVisible(false);
						pedidoe.dispose();
					}
					this.pedidoe = new _Pedido();
					/*
					 * this.pedidoe.get_chk_seleccionar().setName(
					 * _Interface._chk_seleccionar_pedidoe);
					 * this.pedidoe.get_chk_seleccionar().addItemListener(
					 * constructor.getItemListener());
					 * this.pedidoe.get_btn_imprimir_pedidoe().setActionCommand(
					 * _Interface._btn_imprimir_pedidoe);
					 * this.pedidoe.get_btn_imprimir_pedidoe
					 * ().addActionListener( constructor.getActionListener());
					 */
					this.pedidoe.setVisible(true);
					this.pedidoe.requestFocus();
					this.pedidoe.requestFocusInWindow();
					this.create_table_pedidoe(aux);
				}
			}

			frame.getJTable().setEnabled(true);
		} else {
			error("Grabe El pedido para poder pedir");
		}

	}

	public void preparar() {

		if (this.check_seleccion_del_pedido()) {

			String iduser = this.validar_usuario();
			if (iduser.compareTo("") != 0) {
				String idvendedor = "";
				try {
					idvendedor = (String) data.getVendedor(iduser)[0][0];
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (idvendedor.compareTo("") != 0) {
					frame.get_txt_idvendedor().setText(idvendedor);
					this.evaluarVendedor(frame.get_txt_idvendedor());
				}
				boolean error = this._guardar("PREPARAR", iduser);
				if (!error) {
					this._preparar();
				}
			} else {
				error("OPERACION CANCELADA");
			}
		}
	}

	public void presupuesto() {
		this.uncheck_null();
		String idvendedor = frame.get_txt_idvendedor().getText();
		String idcliente = frame.get_txt_idcliente().getText();
		if (idcliente.compareTo("") != 0) {
			String cliente_descripcion = frame.get_txt_cliente_descripcion()
					.getText();
			if (cliente_descripcion.compareTo("") != 0) {
				if (idvendedor.compareTo("") != 0) {
					boolean articulos_precios = check_articulos_precios();
					boolean articulos_global = this.check_global_precios() < 0;
					boolean articulos_actualizados = this
							.check_articulos_actualizados();
					if (!articulos_precios) {
						error("Esta Generando un Presupuesto Con Precios Bajos!!!");
					}
					if (!articulos_actualizados | articulos_global) {
						error("Esta Generando un Presupuesto Con Articulos Desactualizados!!!");
					}
					String iduser = this.validar_usuario();
					if (iduser.compareTo("") != 0) {
						idvendedor = "";
						try {
							idvendedor = (String) data.getVendedor(iduser)[0][0];
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (idvendedor.compareTo("") != 0) {
							frame.get_txt_idvendedor().setText(idvendedor);
							this.evaluarVendedor(frame.get_txt_idvendedor());
						}
						boolean error = this._guardar("PRESUPUESTO", iduser);
						if (!error) {
							this._presupuesto();
						}
					} else {
						error("OPERACION CANCELADA");
					}
				} else {
					error("Debe Ingresar Vendedor");
				}
			} else {
				error("Debe ingresar na descripcion para el cliente");
			}

		} else {
			error("Debe definir la cuenta de cliente(por defecto es 112010001 Consumidor final)");
		}

	}

	public boolean puede_eliminar_remito(String remito) {
		System.out.println("Puede Eliminar Remito " + remito + "?");
		boolean free = true;
		Object[][] results = data.tieneComprobanteBetaAsociado(remito);
		if (results != null) {
			if (results.length > 0) {
				free = false;
				String tc = (String) results[0][0];
				String idcomprobante = (String) results[0][1];
				error("No puede eliminar este remito porque esta asociado al comprobante "
						+ tc + "-" + idcomprobante);
			}
		}
		if (free) {
			results = data.tieneComprobanteAlfaAsociado(remito);
			if (results != null) {
				if (results.length > 0) {
					free = false;
					String tc = (String) results[0][0];
					String idcomprobante = (String) results[0][1];
					error("No puede eliminar este remito porque esta asociado al comprobante "
							+ tc + "-" + idcomprobante);

				}
			}
		}
		return free;
	}

	public void reconnect_cliente() {
		if (cliente != null) {
			cliente.setConstructor(this.getConstructor());
		}
	}

	public void reconnect_Fecha() {
		if (Fecha != null) {
			Fecha.setConstructor(this.getConstructor());
		}
	}

	public void reconnect_PDC() {
		if (PDC != null) {
			PDC.setConstructor(this.getConstructor());
		}

	}

	public void reconnect_Provincia() {
		if (Provincia != null) {
			Provincia.setConstructor(this.getConstructor());
		}

	}

	public void reconnect_Transporte() {
		if (Transporte != null) {
			Transporte.setConstructor(this.getConstructor());
		}
	}

	public void reconnect_Vendedor() {
		if (Vendedor != null) {
			Vendedor.setConstructor(this.getConstructor());
		}

	}

	public void registrar_faltante(int row) {
		String idarticulo = "";
		try {
			idarticulo = frame.getJTable().getValueAt(row,
					_Interface._columna_idarticulo).toString();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			// e2.printStackTrace();
		}
		String descripcion = "";
		try {
			descripcion = frame.getJTable().getValueAt(row,
					_Interface._columna_descripcion).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		this.registrar_faltante(idarticulo, descripcion);
	}

	public void registrar_faltante(String idarticulo, String descripcion) {
		String iduser = this.validar_usuario();
		if (iduser.compareTo("") != 0) {
			String idvendedor = "";
			try {
				idvendedor = (String) data.getVendedor(iduser)[0][0];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (idvendedor.compareTo("") != 0) {
				frame.get_txt_idvendedor().setText(idvendedor);
				this.evaluarVendedor(frame.get_txt_idvendedor());
			}
			boolean error = this._guardar("GUARDAR", iduser);
			if (!error) {
				if (idarticulo != null) {
					if (idarticulo.compareTo("") != 0) {
						boolean ok = false;
						if (idarticulo.compareTo("*") == 0) {
							if (descripcion.compareTo("") != 0) {
								ok = true;
							} else {
								error("Para Registrar un Faltante * debe indicar la descripcion en la misma fila.");
							}
						} else {
							if (data.existeArticulo(idarticulo)) {
								ok = true;
							} else {
								error("Articulo "
										+ idarticulo
										+ " inexistente. No puede Indicar este faltante");
							}
						}

						if (ok) {
							String ip = data.getIp();
							String cantidad = "1.0";
							if (idvendedor.compareTo("") != 0) {
								String stock = data.getStock(idarticulo)[0][2]
										.toString();
								String tc = "";
								String idcomprobante = "";
								int sel = preguntar(
										"Confirmar",
										"Quiere Relacionar el Faltante con el pedido?",
										new String[] {
												"Relacionar con este Pedido",
												"No, Solo Pedir para Stock" },
										"Relacionar con este Pedido");
								if (sel == 0) {
									tc = "PDC";
									idcomprobante = frame.get_txt_idpedido()
											.getText();
								}
								String q = data.getInsertFaltante(idarticulo,
										descripcion, ip, idvendedor, cantidad,
										stock, tc, idcomprobante);
								data.beginTransaction();
								data.clearBatch();
								data.addBatch(q);
								boolean _error = data.executeBatch();
								if (!_error) {
									data.commitTransaction();
									aviso("Se grabo correctamente el faltante");
								} else {
									data.rollbackTransaction();
									aviso("Error grabando faltante");
								}

							} else {
								error("OPERACION CANCELADA");
							}

						}

					} else {
						error("Articulo Inexistente para Indicar Faltante");
					}
				} else {
					error("Articulo Inexistente para Indicar Faltante");
				}

			}
		}

	}

	public boolean registrar_faltante(String idarticulo, String descripcion,
			String idvendedor, String idcomprobante) {
		String ip = data.getIp();
		String cantidad = "1.0";
		String tc = "";
		String stock = data.getStock(idarticulo)[0][2].toString();
		if (idcomprobante.compareTo("") != 0) {
			tc = "PDC";
		}
		String q = data.getInsertFaltante(idarticulo, descripcion, ip,
				idvendedor, cantidad, stock, tc, idcomprobante);

		data.clearBatch();
		data.addBatch(q);
		boolean _error = data.executeBatch();

		return _error;
	}

	public void registrar_faltante_descripcion(JTextField tx, int row) {
		String idarticulo = "";
		try {
			idarticulo = frame.getJTable().getValueAt(row,
					_Interface._columna_idarticulo).toString();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			// e2.printStackTrace();
		}
		String descripcion = "";
		try {
			descripcion = tx.getText();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		this.registrar_faltante(idarticulo, descripcion);
	}

	public void registrar_faltante_idarticulo(JTextField tx, int row) {
		String idarticulo = "";
		try {
			idarticulo = tx.getText();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			// e2.printStackTrace();
		}
		String descripcion = "";
		try {
			descripcion = frame.getJTable().getValueAt(row,
					_Interface._columna_descripcion).toString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		this.registrar_faltante(idarticulo, descripcion);
	}

	public void sacar_remito_de_tabla(String remito) {

		for (int i = 0; i < frame.getJTable1().getRowCount(); i++) {
			String value = frame.getJTable1().getValueAt(i, 1).toString();
			if (value.compareTo(remito) == 0) {
				frame.getJTable1().setValueAt("Eliminado", i, 3);

			}
		}
	}

	public void seleccionar(boolean b) {
		if (frame.getJTable() != null) {
			for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
				frame.getJTable().setValueAt(b, i, 0);
			}
		}
		this._calculate();
	}

	public void setCambios(boolean cambios) {
		this.cambios = cambios;
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void setDefaultTab() {
		frame.getJTabbedPane().setSelectedIndex(1);
	}

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public void show(int row) {
		this.show(row, false);
	}

	public void show(int row, boolean focus) {
		String idarticulo = "";
		try {
			idarticulo = frame.getJTable().getValueAt(row,
					_Interface._columna_idarticulo).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (idarticulo.compareTo("") != 0) {
			this.fillExtra(idarticulo);
		}
	}

	public void showCost(JCheckBox chk) {
		frame.getJTable().repaint();
	}

	public void transfer_fecha_focus(JTextField tx) {
		if (tx.getName() == _Interface._txt_fecha) {
			frame.get_txt_idcliente().requestFocusInWindow();
		}
		if (tx.getName() == _Interface._txt_fecha_envio) {
			frame.get_txt_domicilio().requestFocusInWindow();
		}
	}

	public void transfer_focus_articulos() {
		System.out.println("TRansfer Focus to Table");
		if (frame.getJTable() == null) {
			this.crear_tabla_items();

		} else {
			if (frame.getJTable().getRowCount() <= 0) {
				this.crear_tabla_items();
			}
		}

		frame.getJTable().requestFocusInWindow();
		frame.getJTable().changeSelection(0, _Interface._columna_idarticulo,
				false, false);
		frame.getJTable().editCellAt(0, _Interface._columna_idarticulo);
		frame.getJTable().transferFocus();
		if (nuevo) {
			frame.get_btn_importar().setEnabled(true);
		} else {
			String idpedido = frame.get_txt_idpedido().getText();
			boolean remitos = data.getRemitosValidos(idpedido) > 0;
			frame.get_btn_importar().setEnabled(!remitos);
		}

	}

	public void transferir() {
		this.uncheck_null();
		String idvendedor = frame.get_txt_idvendedor().getText();
		String idcliente = frame.get_txt_idcliente().getText();
		if (idcliente.compareTo("") != 0) {
			String cliente_descripcion = frame.get_txt_cliente_descripcion()
					.getText();
			if (cliente_descripcion.compareTo("") != 0) {
				if (idvendedor.compareTo("") != 0) {

					String iduser = this.validar_usuario();
					if (iduser.compareTo("") != 0) {
						idvendedor = "";
						try {
							idvendedor = (String) data.getVendedor(iduser)[0][0];
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (idvendedor.compareTo("") != 0) {
							frame.get_txt_idvendedor().setText(idvendedor);
							this.evaluarVendedor(frame.get_txt_idvendedor());
						}
						boolean error = this._guardar("TRANSFERIR", iduser);
						if (!error) {
							this._transferir();
						}
					} else {
						error("OPERACION CANCELADA");
					}
				} else {
					error("Debe Ingresar Vendedor");
				}
			} else {
				error("Debe ingresar una descripcion para el cliente");
			}

		} else {
			error("Debe definir la cuenta de cliente");
		}

	}

	public void uncheck_null() {
		int selections = 0;
		int items = 0;
		double suma_selections = 0.0;
		double suma_items = 0.0;
		int i = 0;
		while (i < frame.getJTable().getRowCount()) {
			boolean selected = false;
			try {
				selected = (Boolean) frame.getJTable().getValueAt(i,
						_Interface._columna_selected);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (selected) {
				String articulo = "";
				try {
					articulo = (String) frame.getJTable().getValueAt(i,
							_Interface._columna_idarticulo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (articulo == null) {
					try {
						frame.getJTable().setValueAt(false, i,
								_Interface._columna_selected);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

					if (articulo.compareTo("") == 0) {
						try {
							frame.getJTable().setValueAt(false, i,
									_Interface._columna_selected);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			}
			i++;
		}

	}

	public void update_modificado() {
		String idpedido = frame.get_txt_idpedido().getText();
		String modificado = data.getUltimaModificacion(idpedido);
		frame.get_txt_modificado().setText(modificado);
	}

	public String validar_vendedor() {
		String idvendedor = "";

		while (idvendedor.compareTo("") == 0) {
			String password = this.requestPassword("Ingrese Su Clave:");
			idvendedor = data.getVendedorValidacion(password);
			if (idvendedor.compareTo("") == 0) {
				error("Error de Validacion de Usuario");

			}

		}

		return idvendedor;
	}

	public void nuevoAviso() {

		aplicacion.gestion.agenda.escritor.constructor._Constructor CC = new aplicacion.gestion.agenda.escritor.constructor._Constructor();
		CC.setParameter(_parametros.connector, this._data
				.getConnectionHandler().Clone());
		CC.setParameter(_parametros.LookAndFeel, this.getConstructor()
				.getLookAndFeelTheme());
		CC.build(this.getConstructor());
		CC.init();
		aplicacion.gestion.agenda.escritor.logic._Logic logic = (aplicacion.gestion.agenda.escritor.logic._Logic) CC
				.getLogic();
		logic.nuevo();

	}

	public String getFvn() {
		String tipo = "FVN";
		String iduser=this.getConstructor().getIduser();
		String negocio=data.getNegocioId(iduser);
		String pedido = data.getProximoPGCorrecto(tipo,negocio);

		int inc = 0;
		while (data.existe(tipo, pedido) & inc < 3) {
			String q = data.getUpdateTC(tipo);
			data.executeUpdate(q);
			pedido = data.getProximoPGCorrecto(tipo,negocio);
			inc++;
		}
		return pedido;
	}

	public void grabarCtaCte(String idremito, String idcomprobante) {
		String cuenta = frame.get_txt_idcliente().getText();
		String cuenta_descripcion = frame.get_txt_cliente_descripcion()
				.getText();
		String importe = frame.get_txt_total().getText();
		importe = new Convertidor().getMoney(importe, 2);
		boolean error = this.grabar(null, idcomprobante, idremito);
	}

	private List<String> getInstruccionesDeComprobante(String remito,
			String idcomprobante) {
		List<String> instrucciones = new ArrayList<String>();
		String cuenta = frame.get_txt_idcliente().getText();
		String descripcion = frame.get_txt_cliente_descripcion().getText();
		String tc = "FVN";

		String caja = "   1"; // que pasa si es otro punto de venta?

		String subtotal = frame.get_txt_subtotal().getText().replace(",", "");
		String fecha = frame.get_txt_fecha().getText();

		String idvendedor = frame.get_txt_idvendedor().getText();
		String articulos = "1";
		String total = frame.get_txt_total().getText().replace(",", "");
		String _instruccion = data.getGuardarQuery(tc, idcomprobante,
				idvendedor, fecha, cuenta, descripcion, articulos, subtotal,
				total, remito);
		instrucciones.add(_instruccion);
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String _idarticulo = frame.getJTable().getValueAt(i,
					_Interface._columna_idarticulo).toString();
			if (_idarticulo != null) {
				if (_idarticulo.compareTo("") != 0) {
					String _descripcion = frame.getJTable().getValueAt(i,
							_Interface._columna_descripcion).toString();
					String _cantidad = frame.getJTable().getValueAt(i,
							_Interface._columna_cantidad).toString();
					String _precio = frame.getJTable().getValueAt(i,
							_Interface._columna_precio).toString();
					String _total = frame.getJTable().getValueAt(i,
							_Interface._columna_total).toString();

					String _desc = frame.getJTable().getValueAt(i,
							_Interface._columna_descuento).toString();
					String desc_imp = "0";
					String _instruccioni = data.getGuardarItems(tc,
							idcomprobante, i, _idarticulo, _descripcion,
							_cantidad, _precio, _desc, desc_imp, _total);
					instrucciones.add(_instruccioni);
				}
			}

		}
		return instrucciones;
	}

	private List<String> getInstruccionesAsientoDeComprobante(
			String idcomprobante) {
		String cuenta = frame.get_txt_idcliente().getText();
		String descripcion = frame.get_txt_cliente_descripcion().getText();
		String tc = "FVN";
		String caja = "   1"; // que pasa si es otro punto de venta?

		String importe = frame.get_txt_total().getText().replace(",", "");
		String fecha = frame.get_txt_fecha().getText();
		boolean credito = true;
		;
		List<String> instrucciones = data.getInstruccionesAsientoDeComprobante(
				cuenta, descripcion, tc, caja, idcomprobante, importe, fecha,
				credito);
		return instrucciones;

	}

	/**
	 * Procedimiento para marcar como anulado el remito q se transformo a FVN.
	 * Esto sirve por si se anula la FVN
	 */
	private List<String> getInstruccionesEsconderRemito(String remito) {
		List<String> instrucciones = new ArrayList<String>();
		String _instruccion = data.getMarcarAnuladoRemito(remito);
		instrucciones.add(_instruccion);

		return instrucciones;
	}

	public boolean grabar(List<String> instrucciones_cobranza,
			String idcomprobante, String idremito) {
		boolean error = false;

		data.beginTransaction();
		data.clearBatch();
		List<String> instrucciones_cpte = this.getInstruccionesDeComprobante(
				idremito, idcomprobante);
		List<String> instrucciones_asiento_cpte = this
				.getInstruccionesAsientoDeComprobante(idcomprobante);
		List<String> instrucciones_stock = this
				.getInstruccionesDeStock(idcomprobante);

		for (int i = 0; i < instrucciones_cpte.size(); i++) {
			data.addBatch(instrucciones_cpte.get(i));
		}
		for (int i = 0; i < instrucciones_asiento_cpte.size(); i++) {
			data.addBatch(instrucciones_asiento_cpte.get(i));
		}
		if (instrucciones_cobranza != null) {
			for (int i = 0; i < instrucciones_cobranza.size(); i++) {
				data.addBatch(instrucciones_cobranza.get(i));
			}
		}

		for (int i = 0; i < instrucciones_stock.size(); i++) {
			data.addBatch(instrucciones_stock.get(i));
		}

		error = data.executeBatch();
		if (!error) {
			data.commitTransaction();
		} else {
			data.rollbackTransaction();
		}
		if (!error) {

			data.beginTransaction();
			data.clearBatch();

			List<String> instrucciones_remito = this
					.getInstruccionesEsconderRemito(idremito);
			List<String> instrucciones_punteros = this
					.getInstruccionesActualizacionDePunteros();
			for (int i = 0; i < instrucciones_remito.size(); i++) {
				data.addBatch(instrucciones_remito.get(i));
			}
			for (int i = 0; i < instrucciones_punteros.size(); i++) {
				System.out.println(instrucciones_punteros.get(i));
				data.addBatch(instrucciones_punteros.get(i));
			}
			error = data.executeBatch();
			if (!error) {
				data.commitTransaction();
			} else {
				data.rollbackTransaction();
			}
		}
		data.setAutoCommit();
		return error;
	}

	private List<String> getInstruccionesActualizacionDePunteros() {
		List<String> instrucciones = new ArrayList<String>();
		String tc = "FVN";
		String _instruccion = data.getUpdateTC(tc);
		instrucciones.add(_instruccion);
		return instrucciones;
	}

	private List<String> getInstruccionesDeStock(String idcomprobante) {
		List<String> instrucciones = new ArrayList<String>();
		String tc = "FVN";

		String fecha = frame.get_txt_fecha().getText();
		String cuenta = frame.get_txt_idcliente().getText();
		for (int i = 0; i < frame.getJTable().getRowCount(); i++) {
			String _idarticulo = "";
			try {
				_idarticulo = frame.getJTable().getValueAt(i,
						_Interface._columna_idarticulo).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (_idarticulo != null) {
				if (_idarticulo.compareTo("") != 0) {
					String _descripcion = frame.getJTable().getValueAt(i,
							_Interface._columna_descripcion).toString();
					String _cantidad = frame.getJTable().getValueAt(i,
							_Interface._columna_cantidad).toString()
							.replaceAll(",", "");
					String _precio = frame.getJTable().getValueAt(i,
							_Interface._columna_precio).toString().replaceAll(
							",", "");
					String _total = frame.getJTable().getValueAt(i,
							_Interface._columna_total).toString().replaceAll(
							",", "");
					String desc = "0";
					String desc_imp = "0";
					double cantidad = new Double(_cantidad);
					if (tc.compareTo("FVN") == 0) {
						cantidad = -cantidad;
					}
					String _instruccioni = data.getInsertQueryVMVStock(tc,
							idcomprobante, _idarticulo, _descripcion, ""
									+ cantidad, i, fecha, _precio, cuenta);
					instrucciones.add(_instruccioni);
				}
			}

		}
		return instrucciones;
	}

	public void insertarFila(int row) {
		String idpedido = frame.get_txt_idpedido().getText();
		boolean remitos = data.getRemitosValidos(idpedido) > 0;
		if (remitos & !nuevo) {
			error("no esta permitido modificar un pedido con remitos generados");
		} else {
			_insertarFila(row);
		}
	}

	public void _insertarFila(int row) {
		DefaultTableModel model = (DefaultTableModel) frame.getJTable()
				.getModel();

		if (model.getRowCount() <= 0) {
			model.setRowCount(1);
			model.setValueAt(true, 0, _Interface._columna_selected);
			model.setValueAt(1, 0, _Interface._columna_item);
			this.editCell(0, _Interface._columna_idarticulo);
		} else {
			// 0
			// 1
			// 2
			model.setRowCount(model.getRowCount() + 1);
			for (int i = model.getRowCount() - 2; i >= row; i--) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					Object value = model.getValueAt(i, j);
					model.setValueAt(value, i + 1, j);
				}
			}
			for (int j = 0; j < model.getColumnCount(); j++) {
				model.setValueAt(null, row, j);
			}
			for (int i = 0; i < model.getRowCount(); i++) {
				model.setValueAt(i + 1, i, _Interface._columna_item);
			}
		}
	}

	public void show_popup(Point P, int irow) {
		Point location = frame.getJTable().getLocationOnScreen();
		P.setLocation(P.x - location.x, P.y - location.y);
		int column = frame.getJTable().columnAtPoint(P);

		int row = frame.getJTable().rowAtPoint(P);
		CustomPopup popup = new CustomPopup();
		popup.setRow(irow);
		Menu m = new Menu();

		MenuItem menuItem;
		menuItem = new MenuItem("Insertar");
		menuItem.setActionCommand(_Interface._btn_insertar_fila);
		menuItem.addActionListener(this.getConstructor().getActionListener());
		menuItem.setFont(new Font("Dialog", Font.PLAIN, 10));
		m.add(menuItem);
		popup.add(m);
		frame.getJTable().add(popup);
		popup.show(frame.getJTable(), P.x, P.y);
	}

	public Object[][] process_data(Object[][] results) {
		Object[][] tmp = null;
		if (results != null) {
			Convertidor cv = new Convertidor();
			if (results.length > 0) {
				int matches = results.length;

				// if (matches>0){
				tmp = new Object[matches][results[0].length];
				matches = 0;
				for (int i = 0; i < results.length; i++) {

					for (int j = 0; j < results[0].length; j++) {
						tmp[i][j] = results[i][j];
					}
					tmp[i][2] = cv.getDateWithFormat2("dd-MM-yyyy",
							(String) results[i][2]);
					tmp[i][8] = cv.getDateWithFormat2("dd-MM-yyyy",
							(String) results[i][8]);

				}

			}

		}
		return tmp;
	}

	private void create_table_seguimiento(Object[][] results) {
		// idpedido|descripcion|agenda|cuenta|nombre|importe|vendedor|estado|creado|seguimiento

		_Constructor constructor = (_Constructor) this.getConstructor();
		// TablePedidosColorCellRenderer renderer = new
		// TablePedidosColorCellRenderer();
		// renderer.setLogic(this);
		CustomTable table = new CustomTable();

		Column col = new Column();

		col = new Column();
		col.setName("idpedido");
		col.setWidth(74);
		col.setEditable(false);
		col.setClass(String.class);

		// col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Descripcion");
		col.setWidth(180);
		col.setEditable(false);
		// col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Agenda");
		col.setWidth(72);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		DateRenderer daterenderer = new DateRenderer();
		col.setClass(Date.class);
		col.setCellRenderer(daterenderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Cuenta");
		col.setWidth(60);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		// col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Nombre");
		col.setWidth(130);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		// col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Importe");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		// col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Vendedor");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		// col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Creador");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		// col.setCellRenderer(renderer);
		table.addColumn(col);

		col = new Column();
		col.setName("Creado");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		col.setCellRenderer(daterenderer);
		col.setClass(Date.class);
		table.addColumn(col);

		col = new Column();
		col.setName("estado");
		col.setWidth(60);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		// col.setCellRenderer(renderer);
		col.setClass(String.class);
		table.addColumn(col);

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		// col.setCellRenderer(renderer);
		col.setClass(Boolean.class);
		table.addColumn(col);

		table.setName(_Interface._table_pedidos_importar);
		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this.getConstructor().getMouseListener());
		Font fuente = new Font("Dialog", Font.BOLD, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);
		table.build();
		table.fillData();

		JTable _table = table.getTable();
		_table.setColumnSelectionAllowed(false);
		_table.setRowSelectionAllowed(true);
		importar.setJTable(_table);

	}

	private List<Object[]> acumulators = null;

	public int getAcumulator(JTextField tx) {
		int i = 0;
		boolean found = false;
		while (i < acumulators.size() & !found) {
			Object[] tmp = acumulators.get(i);
			JTextField atx = (JTextField) tmp[0];
			found = (atx.getName() == tx.getName());
			if (!found)
				i++;
		}
		if (!found)
			i = -1;
		return i;
	}

	public void addAcumulator(JTextField tx, String column) {
		this.addAcumulator(tx, column, false);
	}

	public void addAcumulator(JTextField tx, String column, boolean having) {

		int n = this.getAcumulator(tx);
		List<String> values = new ArrayList<String>();
		Object[] tmp = null;

		if (n >= 0) {
			tmp = this.acumulators.get(n);
			values = (List<String>) tmp[1];

		} else {
			tmp = new Object[] { tx, values, column, having };
		}
		values.add(tx.getText());
		System.out.println("column:" + column + " search>" + tx.getName()
				+ " (" + values.toString() + ")");
		tx.setText("");
		tmp[0] = tx;
		if (n >= 0) {
			acumulators.set(n, tmp);
		} else {
			acumulators.add(tmp);
		}

	}

	public String getAcumulatorQuery(int i, boolean have) {

		Object[] tmp = this.acumulators.get(i);
		List<String> values = (List<String>) tmp[1];
		JTextField tx = (JTextField) tmp[0];
		String column = (String) tmp[2];
		boolean having = (Boolean) tmp[3];
		String description = "";
		if (having == have) {
			if (values.size() > 0) {
				int j = 0;
				while (j < values.size()) {
					String flt = values.get(j);
					String aux = flt;
					while (aux.contains(" ")) {
						String sub = aux.substring(0, aux.indexOf(" "));
						if (sub.compareTo("") != 0) {
							if (description.length() > 0) {
								description = description + " and ";
							}
							description = description + " " + column
									+ " like '%" + sub + "%'";
						}
						aux = aux.substring(aux.indexOf(" ") + 1);
					}
					if (aux.compareTo("") != 0) {
						if (description.length() > 0) {
							description = description + " and ";
						}
						description = description + "  " + column + " like '%"
								+ aux + "%'";
					}

					j++;
				}
			}

		}
		return description;
	}

	public String getAcumulatedQuery() {
		String q = "";
		if (acumulators != null) {
			for (int i = 0; i < this.acumulators.size(); i++) {
				String acumulator = this.getAcumulatorQuery(i, false);
				if (acumulator.length() > 0) {
					if (q.length() > 0) {
						q += "and ";
					}
					q += acumulator + " ";
				}
			}
		}

		return q;
	}

	public String getAcumulatedHave() {
		String q = "";
		if (acumulators != null) {
			for (int i = 0; i < this.acumulators.size(); i++) {
				String acumulator = this.getAcumulatorQuery(i, true);
				if (acumulator.length() > 0) {
					if (q.length() > 0) {
						q += "and ";
					}
					q += acumulator + " ";
				}
			}
		}

		return q;
	}

	public void acumulate() {
		this.addAcumulator(importar.get_txt_pedido_descripcion(),
				"pedido.descripcion");
		this.addAcumulator(importar.get_txt_idpedido(), "pedido.idpedido");
		this.addAcumulator(importar.get_txt_idcliente(), "pedido.cliente");
		this.addAcumulator(importar.get_txt_cliente_descripcion(),
				"pedido.cliente_descripcion");
		this.addAcumulator(importar.get_txt_vendedor(), "vendedor.nombre");
		this.addAcumulator(importar.get_txt_idarticulo(), "items.idarticulo");
		this.addAcumulator(importar.get_txt_idarticulo_descripcion(),
				"items.descripcion");
		// this.addAcumulator(frame.get_txt_idarticulo_linea(),"articulo.descripabrev");
		this
				.addAcumulator(importar.get_txt_informacion(),
						"pedido.datos_extra");
		this.addAcumulator(importar.get_txt_creador(), "creador.nombre");
		// this.addAcumulator(importar.get_txt_estado(), _estado,true);

	}

	public void _cargarPedidos() {
		estado = "Buscando";
		this.acumulate();
		String cuenta = "";
		if (importar.get_chk_cliente().isSelected()) {
			cuenta = frame.get_txt_idcliente().getText();
		}
		String filters = this.getAcumulatedQuery();
		String _estado = "";
		_estado += "(case when count(remitos.remito)=count(alfa.tc) and count(remitos.remito)>0 then 'Alfa' else ( ";
		_estado += "(case when count(remitos.remito)=count(fvn.tc) and fvn.anulada=0 and count(remitos.remito)>0  then 'Beta' else ( ";
		_estado += "(case when count(remitos.remito)>0 and isnull(rmx.anulada,0)=0 then 'RM' else '' end) )  end ";
		_estado += ")) end) ";
		String selected = importar.get_lst_estado().getSelectedItem()
				.toString();
		String having = "";
		if (selected.compareTo("") != 0) {
			having = _estado + " ";
			if (importar.get_chk_estado().isSelected()) {
				having += " not ";
			}
			having += " like '" + selected + "' ";
		}

		String q = data.getPedidosEstadoQuery(100, cuenta, filters, having);
		Object[][] results = data.getResults(q);
		results = this.process_data(results);
		this.create_table_seguimiento(results);
		done = true;
	}

	private class _taskCargar {
		_taskCargar() {
			_cargarPedidos();
		}
	}

	public void goCargar() {
		if (acumulators == null) {
			acumulators = new ArrayList<Object[]>();
		}
		System.out.println("Cargar");
		this.createTimer();
		importar.getJProgressBar().setIndeterminate(true);
		SwingWorker worker = null;
		if (worker == null) {
			worker = new SwingWorker() {
				public Object construct() {
					return new _taskCargar();
				}
			};
		}
		if (Timer != null) {
			Timer.start();
		}
		worker.start();

	}

	public void createTimer() {
		crono = new Crono();
		crono.start();
		current = 0;
		lenght = 0;
		done = false;
		canceled = false;
		// showLayer(false);
		Timer = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done & !canceled) {
					endbar();
					Timer.stop();
				} else {
					updateBar();
				}
			}
		});

	}

	public void endbar() {
		done = true;
		// showLayer(true);
		importar.getJProgressBar().setIndeterminate(false);
		importar.getJProgressBar().setValue(lenght);
		importar.getJProgressBar().setString("");
	}

	public void updateBar() {
		importar.getJProgressBar().setMaximum(lenght);
		importar.getJProgressBar().setValue(current);
		importar.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		importar.getJProgressBar().setStringPainted(true);
	}

	public void cargar_items_show(String idpedido) {
		Object[][] results = data.getPedidoItemsShow(idpedido);

		if (results != null) {
			if (results.length > 0) {
				for (int i = 0; i < results.length; i++) {
					results[i][0] = ((String) results[i][0]).compareTo("1") == 0;

					results[i][1] = (String) results[i][1];
					results[i][2] = (String) results[i][2];
					results[i][3] = (String) results[i][3];
					results[i][4] = (String) results[i][4];
					results[i][5] = (String) results[i][5];

				}
				this.create_table_items_show(results);
			} else {
				importar.setJTable1(null);
			}
		} else {
			frame.setJTable1(null);
		}

	}

	private void create_table_items(Object[][] results) {

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(false);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		col.setCellEditor(chkce.getCellCheck());

		col.setClass(Boolean.class);
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(380);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Linea");
		col.setWidth(160);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);

		table.build();

		table.fillData();

		JTable _table = table.getTable();
		frame.setJTable1(_table);
	}

	private void create_table_items_show(Object[][] results) {

		CustomTable table = new CustomTable();
		table.setSortable(false);
		Column col = null;

		col = new Column();
		col.setName("");
		col.setWidth(30);
		col.setEditable(true);
		CheckBoxCellEditor chkce = new CheckBoxCellEditor();
		chkce.setName(_Interface._chk_seleccionar_item_importar);
		chkce.setItemListener(this._constructor.getItemListener());
		chkce.setTipo(Boolean.class);
		col.setCellEditor(chkce.getCellCheck());

		col.setClass(Boolean.class);
		table.addColumn(col);

		col = new Column();
		col.setName("item");
		col.setWidth(30);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("idarticulo");
		col.setWidth(100);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("descripcion");
		col.setWidth(380);
		col.setEditable(false);
		col.setAligment(JTextField.LEFT);
		table.addColumn(col);

		col = new Column();
		col.setName("cant");
		col.setWidth(46);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("precio");
		col.setWidth(70);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Total");
		col.setWidth(80);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		col = new Column();
		col.setName("Linea");
		col.setWidth(160);
		col.setEditable(false);
		col.setAligment(JTextField.RIGHT);
		table.addColumn(col);

		table.setData(results);
		table.addKeyListener(this.getConstructor().getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Dialog", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setFont(fuente);

		table.build();

		table.fillData();

		JTable _table = table.getTable();
		importar.setJTable1(_table);
	}

	public void show_pedido_nuevos(int row) {
		if (importar.getJTable() != null) {
			if (row >= 0 & row < importar.getJTable().getRowCount()) {
				String pedido = importar.getJTable().getValueAt(row, 0)
						.toString();
				// show_pedido(pedido);
				this.cargar_items_show(pedido);
			}
		}
	}

	private List<Object[]> seleccionimportar;

	public void evaluarSeleccion(JCheckBox chk, JTable table, int row) {
		if (chk.isSelected()) {
			this.agregarSeleccion(chk, table, row);

		} else {
			this.quitarSeleccion(chk, table, row);
			System.out.println("Quitando seleccion item " + row);
		}
	}

	public void quitarSeleccion(JCheckBox chk, JTable table, int row) {
		if (seleccionimportar != null) {
			int rowp = importar.getJTable().getSelectedRow();
			String idpedido = importar.getJTable().getValueAt(rowp, 0)
					.toString();
			String item = importar.getJTable1().getValueAt(row, 1).toString();
			System.out.println("Quitando seleccion item " + item);
			if (this.tieneSeleccion(idpedido, item)) {
				int pos = this.tieneSeleccionPosition(idpedido, item);
				seleccionimportar.remove(pos);
			}
		}

	}

	public void ejecutar_importacion() {
		int imports = 0;
		if (seleccionimportar != null) {
			for (int i = 0; i < seleccionimportar.size(); i++) {
				Object[] tmp = seleccionimportar.get(i);
				String _idpedido = tmp[0].toString();
				String _item = tmp[1].toString();
				this.agregar(_idpedido, _item);
				imports++;
			}
		}
		if (imports > 0) {
			this.cancelar_importar();
		} else {
			error("Debe seleccionar items para importar");
		}
	}

	public void agregarSeleccion(JCheckBox chk, JTable table, int row) {
		if (seleccionimportar == null) {
			seleccionimportar = new ArrayList<Object[]>();
		}
		int rowp = importar.getJTable().getSelectedRow();
		String idpedido = importar.getJTable().getValueAt(rowp, 0).toString();
		String item = importar.getJTable1().getValueAt(row, 1).toString();

		System.out.println("Agregando seleccion item " + item);
		if (!this.tieneSeleccion(idpedido, item)) {
			Object[] tmp = new Object[] { idpedido, item };
			seleccionimportar.add(tmp);
		}

	}

	public boolean tieneSeleccion(String idpedido, String item) {
		boolean found = false;
		int i = 0;
		while (!found & i < seleccionimportar.size()) {
			Object[] tmp = seleccionimportar.get(i);
			String _idpedido = tmp[0].toString();
			String _item = tmp[1].toString();

			found = _idpedido.compareTo(idpedido) == 0
					& _item.compareTo(item) == 0;
			if (!found)
				i++;
		}
		return found;
	}

	public int tieneSeleccionPosition(String idpedido, String item) {
		boolean found = false;
		int i = 0;
		while (!found & i < seleccionimportar.size()) {
			Object[] tmp = seleccionimportar.get(i);
			String _idpedido = tmp[0].toString();
			String _item = tmp[1].toString();
			found = _idpedido.compareTo(idpedido) == 0
					& _item.compareTo(item) == 0;
			if (!found)
				i++;
		}
		return i;
	}

	public void down(JTextField tx, int row, int col) {
		boolean change = true;
		if (col == _Interface._columna_costo) {
			change = frame.get_chk_costo().isSelected();
		}
		if (change) {
			try {
				frame.getJTable().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (row < frame.getJTable().getRowCount() - 1) {
				frame.getJTable().requestFocusInWindow();
				frame.getJTable().changeSelection(row + 1, col, false, false);
				frame.getJTable().editCellAt(row + 1, col);
				frame.getJTable().transferFocus();
			}

		}
		show(row + 1);
	}

	public void up(JTextField tx, int row, int col) {
		boolean change = true;
		if (col == _Interface._columna_costo) {
			change = frame.get_chk_costo().isSelected();
		}
		if (change) {
			try {
				frame.getJTable().getCellEditor().stopCellEditing();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (row > 0) {
				frame.getJTable().requestFocusInWindow();
				frame.getJTable().changeSelection(row - 1, col, false, false);
				frame.getJTable().editCellAt(row - 1, col);
				frame.getJTable().transferFocus();
			}

		}
		show(row);
	}

}