package aplicacion.herramientas.conexion;

import com.jcraft.jsch.*;
import java.util.*;
/**
 * Herramienta para abrir tuneles con ssh desde Java
 * @since 01-09-2008
 * @author Agustin Wisky
 *
 */
public class Tunnel {
	private String host="esquel.servequake.com";
	private String user="root";
	private String password="demoniaco";
	private int port=22;
	private int tunnelLocalPort=4433;
	private String tunnelRemoteHost="192.168.4.1";
	private int tunnelRemotePort=1433;
    private boolean connected=false;
    private JSch jsch=null;
    private Session session=null;
    private List<Object[]> port_redirections;
    public Tunnel(){
    	jsch=new JSch();
    	port_redirections=new ArrayList<Object[]>();
    }
    
    public void addRedirection(int localport,String host,int remoteport){
    	port_redirections.add(new Object[]{localport,host,remoteport});
    }
	public void setTunnelLocalPort(int localport){
		this.tunnelLocalPort=localport;
	}
	
	public void setTunnelRemotePort(int remoteport){
		this.tunnelRemotePort=remoteport;
	}
	
	public void setTunnelHost(String tunnel_host){
		this.host=tunnel_host;
	}
	
	public void setTunnelRemoteHost(String remote_host){
		this.tunnelRemoteHost=remote_host;
	}
	public void setUser(String user){
		this.user=user;
	}
	public void setSSHPort(int port){
		this.port=port;
	}
	public void setPassword(String password){
		this.password=password;
	}
	
    public static void main(String[] args){
        Tunnel t=new Tunnel();
        try{
        	t.setSSHPort(12568);
        	t.setTunnelHost("agustin.servegame.org");
        	t.addRedirection(4445,"192.168.4.150",3306);
        	t.setUser("root");
        	t.setPassword("ipsilon");
            t.go();
        } catch(Exception ex){
        	//System.out.println("");
            ex.printStackTrace();
        }
    }
    
    
    public boolean go() {
    	
        
    	boolean error=false;

            try{
            	if (session!=null){
            		session.disconnect();
            	}else{
            		
            		session=jsch.getSession(user, host, port);
            	}
            	
            	MyUserInfo minfo=new MyUserInfo();
            	minfo.setPassword(password);
            	session.setUserInfo(minfo);
                session.setPassword(password);
                
                
            	//System.out.println("User> "+user+" "+host+" "+port+" "+tunnelLocalPort+" "+tunnelRemoteHost+" "+tunnelRemotePort);
            	
            	
                boolean linuxok=true;
                try {
					session.connect();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					linuxok=false;
				}
                //session.setServerAliveInterval(30);
				if (linuxok){
					for (int i=0;i<this.port_redirections.size();i++){
						Object[] redirection=this.port_redirections.get(i);
						String remotehost=(String)redirection[1];
						int remoteport=(Integer)redirection[2];
						int localport=(Integer)redirection[0];
						//System.out.println("PortForwardingL> localhost:"+localport+" >"+remotehost+":"+remoteport);
						session.setPortForwardingL(localport,remotehost,remoteport);
						//session.setPortForwardingL(tunnelLocalPort,tunnelRemoteHost,tunnelRemotePort);	
					}
					
					java.util.Properties config=new java.util.Properties();
					config.put("compression.s2c", "zlib@openssh.com,zlib,none");
					config.put("compression.c2s", "zlib@openssh.com,zlib,none");
					config.put("cipher.s2c", "blowfish-cbc,aes128-cbc,3des-cbc");
					config.put("cipher.c2s", "blowfish-cbc,aes128-cbc,3des-cbc");
					config.put("compression_level", "4");
					session.setConfig(config);
	                
				}else{
					error=true;
				}
                
            }catch(Exception e){
            	error=true;
            	error(e.getMessage());
            	
            	e.printStackTrace();
            	
            }
            
            if (!error){
            	connected();
            }else{
            	//System.out.println("Error creating tunnel User> "+user+" "+host+" "+port);
            }
        	return error;
    }
    
    public void error(String message){
    	
    }
    public void connected(){
        //System.out.println("Tunnel Created");
        connected=true;
    }
    
    public void close(){
    	try {
			session.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public boolean isConnected(){
    	return this.connected;
    }
   
} 