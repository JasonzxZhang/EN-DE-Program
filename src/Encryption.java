import java.util.*;
import java.io.*;
public class Encryption
{
	private String message,userMessage,name;
	private int messageLength;
	public Encryption(String inputName) throws IOException
	{
		Scanner input= new Scanner(System.in);
		name=inputName+".txt";
		
		System.out.println("\nType in your message:");
		System.out.println("====================================================");
		userMessage= input.nextLine();
		System.out.println("====================================================\n");
		messageLength= userMessage.length();
		printReport();
	}
	
	//printing out the encryption report
	//contain encrypted message to the screen an writing to a encrypted file
	private void printReport() throws IOException
	{
		File file= new File(name);
		PrintWriter writeFile= new PrintWriter(file);
		NumberGenerator[] primeNumber = new NumberGenerator[2];
		primeNumber[0]= new NumberGenerator();
		do
		{
			primeNumber[1]= new NumberGenerator();
		}while(primeNumber[1].getPrimeNumber()==primeNumber[0].getPrimeNumber());
		MagicBox calculation = new MagicBox(primeNumber[0],primeNumber[1],name,userMessage);
		
		int dex,en,n,c;
		en=calculation.getEncryptionKey();
		n=calculation.getN();
		System.out.println("Encrypted Message:\n====================================================");
		int skipLine=1;
		//(OUTPUT)
		//loop does the (c^e)mod(n) process by parts
		for(int count=0;count<messageLength;count++)
		{
			dex=(int)userMessage.charAt(count);
			c=(int)userMessage.charAt(count);
			for(int powerCount=2;powerCount<=en;powerCount++)
			{	
				c=c*dex;
				c=c%n;
			}
			writeFile.printf("%-8d",c);
			System.out.printf("%-8d",c);
			if(count>0 && (skipLine%7==0))
			{
				writeFile.write("\n");
				System.out.println();
			}
			skipLine++;
		}// loop ends
		// writing to file and printing to screen the process
		writeFile.printf("%-8d",calculation.getDecryptionKey());
		System.out.printf("%-8d",calculation.getDecryptionKey());
		writeFile.printf("%-8d",calculation.getN());
		System.out.printf("%-8d",calculation.getN());
		writeFile.close();
	}
}



