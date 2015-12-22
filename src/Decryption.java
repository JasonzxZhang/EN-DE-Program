/*  Encryption/Decryption Program
Decryption.java
Jason Zhang
jzzhang
Your section 1
*/
import java.io.*;
import java.util.*;
public class Decryption {
	private String decryptedMessage="";
	public Decryption(String fileName)throws IOException
	{
		int arrayLength=0,dex,de,n,c;
		File encryptedFile= new File(fileName);
		//some files names stay in the file
		//check the file from the directory exists or not
		if(!encryptedFile.exists())
		{
			System.out.println("FILE NO LONGER EXISTED!");
			System.exit(0);
		}
		//============== check ends ===================
		
		//counting lines
		Scanner countLength= new Scanner(encryptedFile);
		while(countLength.hasNext())
		{
			if(countLength.hasNextInt())
				arrayLength++;
			countLength.next();
		}
		countLength.close();
		
		//decryption process
		Scanner store= new Scanner(encryptedFile);
		int[] message= new int[arrayLength];
		for(int i=0;i<arrayLength;i++)
			message[i]=store.nextInt();
		
		//decryption convertion process
		//breaking down (c^d)mod(n)process by parts
		de=message[arrayLength-2];
		n=message[arrayLength-1];
		for(int count=0;count<arrayLength-2;count++)
		{
			dex=message[count];
			c=message[count];
			for(int powerCount=2;powerCount<=de;powerCount++)
			{	c=c*dex;
				c=c%n;
			}
			decryptedMessage+=""+((char)c);
		}
		System.out.println("Decrypted Message: \n========================================\n"+decryptedMessage);
		printResult(fileName);
	}
	
	//(OUTPUT)
	//Printing out the encrypted message to a txt file
	private void printResult(String fileName)throws IOException
	{
		File outputResult= new File("Decrypted"+fileName);
		PrintWriter result= new PrintWriter(outputResult);
		result.write("Decrypted Message: \n========================================\n"+decryptedMessage);
		result.close();
	}
}
