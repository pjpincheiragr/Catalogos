package aplicacion.compras.carga.control.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawRect extends JPanel
{
    private int pointCount =0;
    private Point points[]  = new Point[100];
    private Point points2[] = new Point[100];
    private Point start = new Point();
    private Point end   = new Point();
    Rectangle rect = new Rectangle();
    

    public DrawRect()
    {
        addMouseMotionListener( new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent ev)
            {
                end = ev.getPoint();
                rect.setFrameFromDiagonal(start, end);
                repaint();
            }//end mouse drag
        });
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                start = e.getPoint();
            }

            public void mouseReleased(MouseEvent ev)
            {
                points[pointCount] = start;
                points2[pointCount] = ev.getPoint();
                pointCount++;
                rect.setFrameFromDiagonal(start, start);
                repaint();
            }
        });
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw line being dragged.
        g2.setPaint(Color.red);
        g2.draw(rect);
        // Draw lines between points in arrays.
        g2.setPaint(Color.blue);
        Rectangle r = new Rectangle();
        /*for (int i =0; i < pointCount; i++)
        {
            r.setFrameFromDiagonal(points[i], points2[i]);
            g2.fill(r);
        }*/
    }
	
    public static void main(String[] args)
    {
        DrawRect test = new DrawRect();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(test);
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}
