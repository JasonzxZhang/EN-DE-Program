/*  Encryption/Decryption Program
NumberGenerator.java
Jason Zhang
jzzhang
Your section 1
*/
import java.util.*;
public class NumberGenerator
{
	private int primeNumber, encryptionKey;
	public NumberGenerator()
	{
		generatePrime();
	}
	
	public NumberGenerator(int phi)
	{
		generateEncryptionKey(phi);
	}
	
	//(RANDOM)
	//generate prime number at the limit of 2^6+49 
	public void generatePrime()
	{
		boolean validation;
		Random gen= new Random();
		do
		{
			primeNumber=(gen.nextInt((int)Math.pow(2,6)))+50;
			validation = true;
			//all prime numbers besides 2, are odd numbers
			//using a loop to check if the number has other factors otherv the 1 and itself
			for (int divisor=2;divisor<primeNumber;divisor++)
				if(primeNumber%divisor==0)
					validation=false;
		} while(validation==false);
	}
	
	//generate the e key with a limit
	public void generateEncryptionKey(int phi)
	{	boolean validation;
		int upperLimit=phi/10;
		Random gen= new Random();
		do// repeat until a relatively prime number is generated
		{
			encryptionKey=gen.nextInt(upperLimit)+1;
			validation = true;
				if(checkCoprime(encryptionKey,phi)==false)
					validation=false;
				else if (encryptionKey<2)
					validation=false;
		} while(validation==false);
	}
	
	public int getPrimeNumber()
	{
		return primeNumber;
	}
	
	public int getEncryptionkey()
	{
		return encryptionKey;
	}

	//check if two numbers are relatively prime with each other
	public boolean checkCoprime(int e, int phi)
	{
		for(int divisor=2;divisor<=e;divisor++)
			if((e%divisor==0)&&(phi%divisor==0))
				return false;
		return true;	
	}
}

