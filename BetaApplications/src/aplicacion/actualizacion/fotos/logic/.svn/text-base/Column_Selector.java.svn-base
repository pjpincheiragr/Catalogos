package aplicacion.actualizacion.fotos.logic;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class Column_Selector implements ActionListener{
	private JFrame frame;
	private JLayeredPane Second;
	private JLabel[] labels;
	private JComboBox[] JBoxes;
	
	private String[] slabels;
	private JButton Ok,Cancel;
	private int Columns;
	private Object[] Settings;
	private boolean closed=false;
	private boolean Saved=false;
	private int changelimit=3;
	private int change=0;
	private boolean fine=false;
	private boolean[] required=null;

	public Column_Selector(int Columns,String[] labels,boolean[] required,Object[] Settings){
		this.slabels=labels;
		this.required=required;
		frame=new JFrame("Configurar Columnas");
		
		this.Columns=Columns;
		this.Settings=Settings;
		frame.addWindowListener(new WindowListener(){
				public void windowClosing(WindowEvent e){
				System.out.print("cerrando ventanita");
				if (change>changelimit+1){
					System.out.println(change+" "+changelimit);
					int n = JOptionPane.showConfirmDialog(
						    frame,
						    "Guarda los Cambios",
						    "",
						    JOptionPane.YES_NO_OPTION);
					if ( n == JOptionPane.YES_OPTION) {
					check();
					}else {
						frame.hide();
						closed=true;
					}	
				}
				else {
					frame.hide();
					closed=true;
				}
				}
				
				public void windowDeactivated(WindowEvent e){
				}
				public void windowClosed(WindowEvent e){
				}
				public void windowActivated(WindowEvent e){
				}
				public void windowDeiconified(WindowEvent e){
				}
				public void windowIconified(WindowEvent e){
				}
				public void windowOpened(WindowEvent e){
				}
		});
		this.gui();
		
	}
	
	public void Save(){
		if (this.Settings==null){
			this.Settings=new Object[slabels.length];
		}
		for (int i=0;i<slabels.length;i++){
			
			if (JBoxes[i].getSelectedIndex()==(JBoxes[i].getItemCount()-1)){
				this.Settings[i]=-1;
			}else {
				this.Settings[i]=JBoxes[i].getSelectedIndex();
				
			}
		}
		
		Saved=true;
		this.fine=true;
	}
	
	public boolean fine(){
		return this.fine;
	}
	
	public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String SelName = (String)cb.getSelectedItem();
        //System.out.println(cb.getName()+" "+SelName);
        boolean alarm=false;
        int i=0;
        
        change++;
        while(i<JBoxes.length & !alarm){
        	
        	    if (JBoxes[i]!=null){
        		if (cb.getName().compareTo(JBoxes[i].getName())!=0) {
        			alarm=(JBoxes[i].getSelectedIndex()==cb.getSelectedIndex());
        		}
        	    }
        	if (alarm){
            	cb.setSelectedIndex(cb.getItemCount()-1);
            }
        	i++;
        }
        if (alarm){
        	//System.out.println("error");
        } else {
        	if (change>changelimit) {
				System.out.println("something has change");
			}
        }
        
        
    	
        
    }
	
	public Object[] getSettings(){
		
		return this.Settings;
	}
	public void setLabels(String[] labels){
		slabels=labels;
	}
	public void gui(){
		Second=new JLayeredPane();
		GridBagLayout ck=new GridBagLayout();
		Second.setLayout(ck);
		int w=400;
		
		int h=slabels.length*30+30;
		int[] heights=new int[slabels.length+1];
		double[] hweights=new double[slabels.length+1];
		for (int i=0;i<slabels.length+1;i++){
			heights[i]=30;
			hweights[i]=10.0;
		}
		ck.columnWidths=new int[] {20,100,20,100,20,100,20};
		ck.columnWeights=new double[] {0,10.0,10,0.10,0}; 
		ck.rowHeights=heights;
		ck.rowWeights=hweights;
		labels=new JLabel[slabels.length];
		JBoxes=new JComboBox[slabels.length];
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.HORIZONTAL;
		
		String[] options=new String[Columns+1];
		for (int i=0;i<Columns;i++){
			options[i]=new String(""+i);
		}
		options[Columns]=" ";
		for (int i=0;i<slabels.length;i++){
			c2.gridy=i;
			c2.gridx=1;
			labels[i]=new JLabel(slabels[i]);
			Second.add(labels[i],c2);
			c2.gridx=3;
			JBoxes[i]=new JComboBox(options);
			
			JBoxes[i].setName(slabels[i]);
			
			boolean intr=false;
			if (this.Settings!=null){
				try {
					intr=(Settings[i].getClass()==Integer.class);
				}catch (Exception e){
					
				}
				
				if (intr){
					if (((Integer) Settings[i])>JBoxes[i].getItemCount()-1){
						JBoxes[i].setSelectedIndex(JBoxes[i].getItemCount()-1);	
					}
					
					else {
						JBoxes[i].setSelectedIndex((Integer) Settings[i]);
						}
				}
				
			}else {
				JBoxes[i].setSelectedIndex(JBoxes[i].getItemCount()-1);
			}
			JBoxes[i].addActionListener(this);
			Second.add(JBoxes[i],c2);
		}
		if (this.Settings!=null){
			
		}
		//c2.gridy=slabels.length;
		c2.gridy=3;
		c2.gridx=5;
		
		frame.resize(w, h);
		Second.setSize(w, h);
		Second.setMaximumSize(new Dimension(w, h));
		Second.setMinimumSize(new Dimension(w, h));
		JToolBar common=new JToolBar();
		common.setFloatable(false);
		common.setMaximumSize(new Dimension(w,20));
		common.setMinimumSize(new Dimension(w,20));
		common.setSize(new Dimension(w,20));
		ImageIcon Img = new ImageIcon("/root/Upgrade/images/button_ok.png");
		Ok=new JButton(Img);
		Ok.setToolTipText("Aplicar");
		Ok.setIcon(new ImageIcon(getClass().getResource("/icons/emblem-default.png")));
		Ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 check();
			}
		});
		common.add(Ok);
		frame.add(common,BorderLayout.NORTH);
		frame.add(Second);
		frame.setBounds(200, 200, w, h);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
			
	}
	public void setLinea(String linea){
		this.JBoxes[3].setSelectedIndex(-1);
		
	}
	public void check(){
		boolean alarm=false;
		int i=0;
		while (!alarm & i<slabels.length){
			alarm=((JBoxes[i].getSelectedIndex()<0 | JBoxes[i].getSelectedIndex()==JBoxes[i].getItemCount()-1)& required[i]);
			if (!alarm)i++;
		}
		if (alarm){
			JOptionPane.showMessageDialog(frame, " Seleccione la Columna que Contiene "+slabels[i]);
		}else{
			System.out.println("everything its ok");
			this.Save();
			this.frame.hide();
		}
	}
	
	
	
	public void show(){
		frame.show();
	}
	
	public static void main(String[] args){
		Object[] test={1,2,3,4,5};
		boolean[] required=new boolean[]{true,true,true,false,false};
		//Object[] test=null;
		String[] slabels=new String[]{"Codigo","LineaProveedor","Proveedor","idArticulo","Linea"};
		Column_Selector PF=new Column_Selector(10,slabels,required,test);
		
		PF.setLabels(slabels);
		PF.show();
		test=PF.getSettings();
		if (test!=null){
			for (int i=0;i<test.length;i++){
				System.out.println(test[i].toString());
			}	
		}
		
		
	}
	
}
