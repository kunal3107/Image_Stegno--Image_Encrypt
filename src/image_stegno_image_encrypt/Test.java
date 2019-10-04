package image_stegno_image_encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import image_stegno_image_encrypt.AES;

public class Test {
	public  byte[] originalfile;
	public  byte[] encryptedfile;
	
	/**
	 * 
	 * @author Nihar Patel
	 * @return Reads a image file on disk and converts to byte[]
	 */
	public  void ReadData(String filename){
		try{
			originalfile = Files.readAllBytes(new File(filename).toPath());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @author Nihar Patel
	 * @return Encrypt an image with the location given in filename and save at the outputfilename
	 */
	public  void Encrypt(String filename,String outputfilename, String key){
		try{
			ReadData(filename);
			encryptedfile = AES.encrypt(originalfile,key);
			if(encryptedfile!=null){
				WriteData(outputfilename);
				System.out.println("Encryption Done");
			}
		}catch (Exception e)
		{
			System.out.println("Error while encrypting: "+e.toString());
		}
	}
	
	/**
	 * 
	 * @author Nihar Patel
	 * @return Wrtie the output image at the location given by filename
	 */
	public  void WriteData(String filename){
		try{
			FileOutputStream fos = new FileOutputStream(new File(filename));
			fos.write(encryptedfile);
			fos.flush();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author Nihar Patel
	 * @return Decrypt an image with the location given in filename and save at the outputfilename
	 */
	public  void Decrypt(String filename, String outputfilename,String Key){

		ReadData(filename);
		encryptedfile = AES.decrypt(originalfile,Key);
		if(encryptedfile!=null){
			WriteData(outputfilename);
			System.out.println("Decryption Done");
		}
	}
	
	public static void main(String args[]){
		//Mention the key to use here
		final String Key = "Hello Cryptography";
		Test t = new Test();
		// Input file, Output file, key
		t.Encrypt("C:\\Users\\Acer\\Desktop\\FY Project\\Code\\Demo\\steg.png", "C:\\Users\\Acer\\Desktop\\FY Project\\Code\\Demo\\encrypted.jpeg",Key);
		// Input file, Output file, key
		t.Decrypt("C:\\Users\\Acer\\Desktop\\FY Project\\Code\\Demo\\encrypted.jpeg","C:\\Users\\Acer\\Desktop\\FY Project\\Code\\Demo\\decrypted.jpeg",Key);
	}
}
