package aplicacion.gestion.agenda.gestion.logic;

import java.awt.dnd.DragSource;

import javax.swing.JTable;

public class _DragSource extends DragSource {
	private int x;
	private int y;
	private JTable table=null;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public JTable getTable() {
		return table;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	 
}
