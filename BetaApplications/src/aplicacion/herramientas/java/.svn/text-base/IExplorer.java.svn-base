package aplicacion.herramientas.java;


import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class IExplorer
{

     
  
	public static void main(String[] args)
	{
              new IExplorer("FILE://C:/TEMP/pedidos_1.html");
		
	}//main
 
	public IExplorer(String url)
	{
	 try{
		// IE=new myIE();
	//	 IE.Navigate(url);
//		 IE.Visible(true);
		 //
		 String os = System.getProperty("os.name");
		 System.out.println("Sistema "+os);
			if (os.toUpperCase().contains("LINUX")){
				Runtime.getRuntime().exec("firefox " + url);
			}else {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);		
			}
		 
                      }
	 			catch (Exception e)
	    {
                            e.printStackTrace();
                            
                    }
 
        }//IExplorer
  
 
 
}

