/*  Encryption/Decryption Program
Magicbox.java
Jason Zhang
jzzhang
Your section 1
*/
import java.io.*;
public class MagicBox
{
	private int decryptionKey,encryptionKey,phi,n;
	private String fileName;
	//take in two prime numbers, output filename
	public MagicBox(NumberGenerator primeOne, NumberGenerator primeTwo, String textName, String userMessage) throws IOException
	{
		fileName="Report"+textName;
		//================ calculation of magic box ====================
		phi=((primeOne.getPrimeNumber()-1)*(primeTwo.getPrimeNumber()-1));
		n=primeOne.getPrimeNumber()*primeTwo.getPrimeNumber();
		NumberGenerator encryptK = new NumberGenerator(phi);
		encryptionKey=encryptK.getEncryptionkey();
		EuclideanMethod coprime = new EuclideanMethod(encryptionKey,phi);
		int arrayLength=coprime.getActualArrayLength();
		int[] firstRemainderRow = new int[arrayLength];
		int[] originalQuotientArray=coprime.getQuotientArray();
		//==================== calculation ends ====================
		
		for(int count=0;count<arrayLength;count++)
			firstRemainderRow[count]=originalQuotientArray[count];
		
		//==================== magic box calculation ====================
		int[][] box = new int[3][firstRemainderRow.length+2];
		box[1][0]=0;
		box[1][1]=1;
		box[2][0]=1;
		box[2][1]=0;
		for (int i=0;i<firstRemainderRow.length;i++)
			box[0][i+2]=firstRemainderRow[i];
		for (int countRow=1;countRow<3;countRow++)
			for (int indexFill=2;indexFill<(firstRemainderRow.length+2);indexFill++)
				box[countRow][indexFill]=(box[0][indexFill]*box[countRow][indexFill-1])+box[countRow][indexFill-2];
		//==================== calculation ends ===================
		
		decryptionKey=box[1][firstRemainderRow.length];
		if(((box[1][firstRemainderRow.length]*box[2][firstRemainderRow.length+1])-(box[1][firstRemainderRow.length+1]*box[2][firstRemainderRow.length]))==-1)
			decryptionKey = box[1][firstRemainderRow.length+1]-box[1][firstRemainderRow.length];
		
		printReport(firstRemainderRow,box,userMessage);
	}
	
	//take in the reaminder role from magic box, magix box array list, and the user's message
	//prints out an encryption process report
	private void printReport(int[] firstRemainderRow,int[][] box, String userMessage) throws IOException
	{
	//==========================Printing out info==================================
		File file= new File(fileName);
		PrintWriter output= new PrintWriter(file);
		output.println("Decryption Key: "+decryptionKey);
		output.println("Encryption Key: "+encryptionKey);
		output.println("phi Value: "+phi);
		output.println("n Value: "+n);
		
		output.print("Remainders: ");
		for(int i=0;i<firstRemainderRow.length;i++)
			output.print(firstRemainderRow[i]+" ");
		output.println("\n");
		
		output.println("\nPrinting out Magic Box algorithm:\n");
		for (int row=0;row<3;row++)
		{
			for (int col=0;col<firstRemainderRow.length+2;col++)
				output.printf("%8d",box[row][col]);
			output.println();
		}
		output.println();
		output.println("\nOriginal Message:\n=========================================");
		output.println(userMessage+" ");
		output.close();
	//=========================info table end===================================
	}
	
	public int getDecryptionKey()
	{
		return decryptionKey;
	}
	
	public int getEncryptionKey()
	{
		return encryptionKey;
	}
	
	public int getPhi()
	{
		return phi;
	}
	
	public int getN()
	{
		return n;
	}

}
