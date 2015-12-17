package aplicacion.gestion.agenda.gestion.logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import aplicacion.gestion.agenda.gestion.interfaces.*;
import aplicacion.gestion.agenda.gestion.logic.*;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.icepdf.core.pobjects.annotations.BorderEffect;

public class JScrollPaneCellRenderer implements TableCellRenderer {
	private _Logic logic = null;
	private int minHeight = -1;
	private int currHeight = -1;
	Color selectionBorderColor, selectionForeground, selectionBackground,
			textForeground, textBackground;

	private JScrollPane tableRenderer = new JScrollPane();

	public void setLogic(_Logic logic) {
		this.logic = logic;
	}

	protected JScrollPane getTableRenderer() {
		return tableRenderer;
	}

	public JScrollPaneCellRenderer() {
		Font fontValue;
		fontValue = UIManager.getFont("JTable.font");
		if (fontValue != null) {
			tableRenderer.setFont(fontValue);
		}
		selectionBorderColor = UIManager
				.getColor("JTable.selectionBorderColor");
		selectionForeground = UIManager.getColor("JTable.selectionForeground");
		selectionBackground = UIManager.getColor("JTable.selectionBackground");
		textForeground = UIManager.getColor("JTable.textForeground");
		textBackground = UIManager.getColor("JTable.textBackground");

	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component c = null;
		this.tableRenderer = null;
		if (value != null) {
			if (value instanceof String) {
				JLabel label = new JLabel(value.toString());
				Font fuente = new Font("Dialog", Font.PLAIN, 10);
				label.setFont(fuente);
				label.setHorizontalAlignment(JLabel.CENTER);
				c = label;
			}
			if (value instanceof JScrollPane) {
				JScrollPane passed = (JScrollPane) value;
				this.tableRenderer = passed;
				c = passed;

				if (table.getName() == _Interface._table_mensual) {
					if (this.logic.isOtherMonth(row, column)) {
						passed.getViewport().setBackground(
								new Color(135, 135, 135));
					} else {
						if (this.logic.isToday(row, column)) {

							passed.getViewport().setBackground(
									new Color(235, 165, 165));
						} else {
							if (this.logic.isSelected(row, column)) {
								passed.getViewport().setBackground(
								new Color(165, 165, 235));
							}else{
								if (this.logic.isFinde(row, column)) {
									passed.getViewport().setBackground(
									new Color(220,220,220));
								}
							}
						}
					}
				}
				if (table.getName() == _Interface._table_semanal) {
					if (logic!=null){
						if (this.logic.isOtherMonthWeek(row, column)) {
							passed.getViewport().setBackground(
									new Color(225, 225, 225));
						} else {
							if (this.logic.isTodayWeek(row, column)) {

								passed.getViewport().setBackground(
										new Color(235, 235, 235));
							} else {
								if (this.logic.isSelectedWeek(row, column)) {
									passed.getViewport().setBackground(
											new Color(225, 225, 225));
								}
							}
						}
					}

				}

			}

		}

		return c;

	}

}