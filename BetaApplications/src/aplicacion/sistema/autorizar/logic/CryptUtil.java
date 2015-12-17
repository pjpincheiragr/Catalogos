package aplicacion.sistema.autorizar.logic;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptUtil {
	private String password="wismix82";
 /**
  * Turns array of bytes into string
  *
  * @param buf
  *            Array of bytes to convert to hex string
  * @return Generated hex string
  */
 public String asHexStr(byte buf[]) {
  StringBuffer strbuf = new StringBuffer(buf.length * 2);
  int i;

  for (i = 0; i < buf.length; i++) {
   if (((int) buf[i] & 0xff) < 0x10)
    strbuf.append("0");
   strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
  }
  return strbuf.toString();
 }
 
 public String asStrHex(String str){
		 String aux="";
		  for (int i=0;i<str.length()-1;i+=2){
			  String tmp=str.substring(i,i+2);
			  int u= Integer.parseInt(tmp,16);
			  char c = (char)u;
			  aux+=c;
		  }
	  return aux;
 }
 
 public  SecretKeySpec getSecretKeySpec(String passphrase,
   String algorithm, int kgenbit)
 throws Exception
 {  
  // 8-byte Salt - SHOULD NOT BE DISCLOSED
  // alternative approach is to have the salt passed from the
  // calling class (pass-the-salt)?
       byte[] salt = {
           (byte)0xA9, (byte)0x87, (byte)0xC8, (byte)0x32,
           (byte)0x56, (byte)0xA5, (byte)0xE3, (byte)0xB2
       };
      
       // Iteration count
       int iterationCount = 1024;
         
  KeySpec keySpec = new PBEKeySpec(passphrase.toCharArray(),
                             salt,
                             iterationCount);
  
       SecretKey secretKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES").
                             generateSecret(keySpec);
      
       MessageDigest md = MessageDigest.getInstance("MD5");
       md.update(secretKey.getEncoded());
       md.update(salt);
       for(int i = 1; i < iterationCount; i++)
       md.update(md.digest());

       byte[] keyBytes = md.digest();
       SecretKeySpec skeyspec = new SecretKeySpec(keyBytes, algorithm);
      
       return skeyspec;
      
 }
 
 /**
  * Encrypt a byte array given the secret key spec
  * @param message
  * @param skeyspec
  * @return
  * @throws Exception
  */
 public byte[] encrypt(byte[] message, SecretKeySpec skeyspec)
 throws Exception
 {
  Cipher cipher = Cipher.getInstance(skeyspec.getAlgorithm());
  cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
  byte[] encrypted = cipher.doFinal(message);
  return encrypted;
 }
 
 /**
  * Decrypt a byte array given the same secret key spec used to encrypt the message
  *
  * @param message
  * @param skeyspec
  * @return
  * @throws Exception
  */
 public byte[] decrypt(byte[] message, SecretKeySpec skeyspec)
 throws Exception
 {
  Cipher cipher = Cipher.getInstance(skeyspec.getAlgorithm());
  cipher.init(Cipher.DECRYPT_MODE, skeyspec);
  byte[] decrypted = cipher.doFinal(message);
  return decrypted;
 }
 
 public String getRandomSecuence(int digitos){
		Random randomGenerator = new Random();
		String secuence="";
		int randomInt =0;
		for (int idx = 0; idx < digitos; idx++) {
			randomInt =randomGenerator.nextInt(3);
			if (randomInt==0){
				randomInt = 65+randomGenerator.nextInt(24);	
			}else{
				if (randomInt==1){
					randomInt = 97+randomGenerator.nextInt(24);		
				}else{
					randomInt = 48+randomGenerator.nextInt(9);		
				}
			}
			secuence+=(char) randomInt;
		}
		return secuence;
	}
	
 
 
 /**
  * Main method, shows how to use the CryptUtil class
  * @param args
  * @throws Exception
  */
 public static void main(String[] args) throws Exception
 {
  
  CryptUtil crypter=new CryptUtil();
  
  String message="TQ8CE";//crypter.getRandomSecuence(3);
  
  System.out.println("Escriba la solucion para >"+message+"??? cual sera?");
  System.out.println("Solucion>"+crypter.getSolution(message));
  String pass="panterarosa";
  
  String pass_encryptado=crypter.getEncriptado(pass);
  byte[] _pass_encryptado=crypter.getCustomEncriptado(pass);
  String tmp="";
  for (int i=0;i<_pass_encryptado.length;i++){
	  tmp+=new Byte(_pass_encryptado[i]);
  }
  
  //String pass_utf8=new String (pass_encryptado.getBytes ("UTF-8"), "ISO-8859-1");
  System.out.println(" encriptado("+pass+")="+pass_encryptado);
  System.out.println(" encriptadoUTF-8("+pass+")="+tmp);
  
//  System.out.println("Encriptar>"+pass+" encrypted= "+crypter.getEncriptado(pass,"okliop"));
//  System.out.println("Encriptar>"+pass+" encrypted= "+crypter.getEncriptado(pass2,"2okliop"));
//  System.out.println("DesEncriptar>"+pass_encryptado+" desencrypted= "+crypter.getDesencryptado(pass_encryptado,"okliop"));
 
  //EC0D90
  /*
  System.out.println("Random:"+message);
  System.out.println("(HEX) Original  : " + crypter.asHexStr(message.getBytes()));
  
  SecretKeySpec skeySpec = crypter.getSecretKeySpec(password,"AES",128);
     
     byte[] encrypted = crypter.encrypt(message.getBytes(), skeySpec);
     
     String key=crypter.asHexStr(encrypted);
     System.out.println("(HEX) Encrypted : " + key.toUpperCase().substring(0,6));
      skeySpec = crypter.getSecretKeySpec(password,"AES",128);
    
     byte[] decrypted = crypter.decrypt(encrypted, skeySpec);
     System.out.println("(HEX) Decrypted : " + crypter.asHexStr(decrypted));
    
     if ( Arrays.equals(decrypted, message.getBytes()))
      System.out.println("THE ONE AND THE SAME");
    */ 
 }
 
 public String getSolution(String message){
	 String password="AgustIn";
	 String _solution="";
	 SecretKeySpec skeySpec = null;
	 try {
		skeySpec=getSecretKeySpec(password,"AES",128);
		 byte[] encrypted = encrypt(message.getBytes(), skeySpec);
		 
		 String key=asHexStr(encrypted);
		 System.out.println("(HEX) Encrypted : " + key.toUpperCase().substring(6,12));
		 _solution=key.toUpperCase().substring(6,12);
	 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return _solution;
 }
 
 public boolean check(String message,String solution){
	 boolean ok=false;
	 String password="AgustIn";
	 SecretKeySpec skeySpec = null;
	 try {
		skeySpec=getSecretKeySpec(password,"AES",128);
		 byte[] encrypted = encrypt(message.getBytes(), skeySpec);
		 
		 String key=asHexStr(encrypted);
		 System.out.println("(HEX) Encrypted : " + key.toUpperCase().substring(6,12));
		 String _solution=key.toUpperCase().substring(6,12);
		 ok=solution.compareTo(_solution)==0;
		 System.out.println("Key:"+message+" given solution="+solution+" vs "+_solution+"="+ok);
		  
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return ok;
 }
 public String getEncriptado(String texto){
	 return this.getEncriptado(texto, password);
 }
 private String getEncriptado(String texto,String password){
	 
	 SecretKeySpec skeySpec = null;
	 byte[] encrypted =null;
	 try {
		skeySpec=getSecretKeySpec(password,"AES",128);
		encrypted =encrypt(texto.getBytes(), skeySpec);
		 
	 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 String tmp="";
	  for (int i=0;i<encrypted.length;i++){
		  tmp+=new Byte(encrypted[i]);
	  }
	return  tmp;
 }
 
 
 public void testEncriptado(String texto,String password){
	 
	 String encriptado="";
	 SecretKeySpec skeySpec = null;
	 try {
		skeySpec=getSecretKeySpec(password,"AES",128);
		 byte[] encrypted = encrypt(texto.getBytes(), skeySpec);
		 
		 String _encriptado=this.asStrHex(this.asHexStr(encrypted));
		 System.out.println("Encriptado:"+_encriptado);
		 byte [] b = _encriptado.getBytes( "8859_1" /* encoding */ );
		 //byte [] b = _encriptado.getBytes( "UTF-8" /* encoding */ );
		 byte[] decrypted = decrypt(b, skeySpec);
		 System.out.println("desencriptado:"+this.asStrHex(this.asHexStr(decrypted)));
		 
	 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
 }
 public String getDesencryptado(String _encriptado){
	 return this.getDesencryptado(_encriptado, password);
 }
 
 private String getDesencryptado(String _encriptado,String password){
	 
	 String solucion="";
	 SecretKeySpec skeySpec = null;
	 try {
		skeySpec=getSecretKeySpec(password,"AES",128);
		 byte [] b = _encriptado.getBytes( "8859_1" /* encoding */ );
		 
		 byte[] decrypted = decrypt(b, skeySpec);
		 solucion=this.asStrHex(this.asHexStr(decrypted));
		 
	 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return solucion;
 }

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
private byte[] getCustomEncriptado(String texto){
	 
	 SecretKeySpec skeySpec = null;
	 
	 byte[] encrypted=null;
	 try {
		skeySpec=getSecretKeySpec(password,"AES",128);
		encrypted= encrypt(texto.getBytes(), skeySpec);
		 
	 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return  encrypted ;
}
}