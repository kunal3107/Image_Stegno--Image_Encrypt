package image_stegno_image_encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

public class Adjuster {

	public static void main(String args[]){
		
		byte[] data;
		
		
		try{
			data = Files.readAllBytes(new File("encrypted.jpeg").toPath());
			for(byte i:data){
				
			if(i<0){
				i = (byte)(i+255);
				
			}	
			else if(i>255){
				i = (byte)(i-255);
			}
			FileOutputStream fos = new FileOutputStream(new File("adjusted.jpeg"));
			fos.write(data);
			fos.close();
			
			}
		}
		catch(Exception e){
			
		}
		
		
	}
	
	
}
