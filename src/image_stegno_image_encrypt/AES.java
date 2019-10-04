package image_stegno_image_encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES
{
	
	private static SecretKeySpec secretKey ;
    private static byte[] key ;
    
	/**
	 * 
	 * @author Nihar Patel
	 * @return Converts the plain text to a fixed sized key using sha-1
	 */
	public static void setKey(String myKey){
    	MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			//System.out.println(key.length);
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
	    	key = Arrays.copyOf(key, 16); // use only first 128 bit
	    	System.out.println("Key: "+new String(key,"UTF-8"));
	    	System.out.println("Key Length: "+key.length);
	    	secretKey = new SecretKeySpec(key, "AES");
		    
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * 
	 * @author Nihar Patel
	 * @return Encrypt the byte[] using AES
	 */
	public static byte[] encrypt(byte[] data, String key)
    {
			AES.setKey(key);
		
		try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
             return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: "+e.toString());
        }
        return null;
    }

	/**
	 * 
	 * @author Nihar Patel
	 * @return Decrypt the byte[] using AES
	 */
    public static byte[] decrypt(byte[] data,String key)
    {
    	AES.setKey(key);
        try
        {
        	
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: "+e.toString());
        }
        return null;
    }
}