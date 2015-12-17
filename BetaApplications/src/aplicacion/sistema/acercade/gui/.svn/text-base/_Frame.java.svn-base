package aplicacion.sistema.acercade.gui;

/*
 * Name:     Water
 * Date:     December 2004
 * Author:   Neil Wallis
 * Purpose:  Simulate ripples on water.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.net.URL;

import java.awt.Dimension;

public class _Frame extends JFrame  {
	private Image offImage;
	
    public Image getOffImage() {
		return offImage;
	}



	public void setOffImage(Image offImage) {
		this.offImage = offImage;
	}



	/**
	 * This method initializes 
	 * 
	 */
	public _Frame() {
		super();
		initialize();
	}



	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(300, 300));
        this.setTitle("Acerca de Beta RC 2009");
        this.setResizable(false);
	}



	
    public void paint(Graphics g) {
      update(g);
    }

    public void update(Graphics g) {
      g.drawImage(offImage,0,0,this);
      
    }


}  //  @jve:decl-index=0:visual-constraint="10,10"

