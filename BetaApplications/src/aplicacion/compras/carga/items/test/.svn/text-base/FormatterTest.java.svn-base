package aplicacion.compras.carga.items.test;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import java.util.Locale;
public class FormatterTest {

	public static void main(String[] args){
		NumberFormat amountDisplayFormat;
	    NumberFormat amountEditFormat;
	    Locale[] locales = NumberFormat.getAvailableLocales();
	    NumberFormat form=NumberFormat.getCurrencyInstance(locales[15]);
	    amountDisplayFormat = NumberFormat.getCurrencyInstance(locales[15]);
        amountDisplayFormat.setMinimumFractionDigits(0);
        amountEditFormat = NumberFormat.getNumberInstance(locales[15]);
        
		final JFormattedTextField amountField = new JFormattedTextField(
                 new DefaultFormatterFactory(
                     new NumberFormatter(amountDisplayFormat),
                     new NumberFormatter(amountDisplayFormat),
                     new NumberFormatter(amountEditFormat)));
		amountField.setValue(new Double(0.0));
		amountField.setColumns(10);
		amountField.addPropertyChangeListener("value", new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent e) {
		        Object source = e.getSource();
		        System.out.println("value>"+amountField.getValue());
		         double  amount = ((Number)amountField.getValue()).doubleValue();
		           }
		});
		 JFrame frame = new JFrame("FormatterFactoryDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().setLayout(null);
	        //Add contents to the window.
	        amountField.setBounds(0,0,100,20);
	        
	        JTextField tx=new JTextField();
	        tx.setBounds(0,40,100,20);
	        frame.add(amountField);
	        frame.add(tx);

	        //Display the window.
	        frame.setSize(200,200);
	        
	        frame.setVisible(true);
	}
}
