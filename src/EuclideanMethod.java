/*  Encryption/Decryption Program
EuclideanMethod.java
Jason Zhang
jzzhang
Your section 1
*/
public class EuclideanMethod 
{
	private int a,b,dividend,divisor,actualArrayLength,declareArrayLength=100;
	private int[] quotient= new int[declareArrayLength];
	private int[] remainder = new int[declareArrayLength];
	
	public EuclideanMethod(int num1, int num2)
	{
		if (num1>num2)
		{	
			dividend=num1; divisor=num2;
			a=num1; b=num2;
			declareArrayLength=num1/10;
		}
		else
		{
			dividend=num2; divisor=num1;
			a=num2; b=num1;
			declareArrayLength=num2/10;
		}
		euclideanAlgorithm(dividend, divisor);
		quotient=shortenArray(quotient);
		remainder=shortenArray(remainder);
	}
	
	//Printing the quotients and remainder in the Euclidean method
	public void printBothArrays()
	{
		for (int i=0;i<remainder.length;i++)
		{
			System.out.println(remainder[i]+" ");
		}
		System.out.println();
		for (int i=0;i<quotient.length;i++)
		{
			System.out.println(quotient[i]+" ");
		}
	}
	
	//Euclidean calculation method with taken in the two number
	public void euclideanAlgorithm(int divid,int divis)
	{
		int arrayCount=0;
		
		for(int i=0;i<100;i++)
		{
			quotient[i]=divid*10;
			remainder[i]=divid*10;
		}
		
		//Euclidean algorithm process
		while(divis!=0)
		{
				quotient[arrayCount]=divid/divis;
				remainder[arrayCount]=divid-(divis*quotient[arrayCount]);
				divid=divis;
				divis=remainder[arrayCount];
				arrayCount++;
		}
	}
	
	//take in old array and shorten down the unused indexes
	private int[] shortenArray(int[] oldArray)
	{
		int[] newArray = new int[getActualArrayLength()];
		for(int count=0;count<newArray.length;count++)
			newArray[count]=oldArray[count];
		return newArray;
	}
	
	public int[] getQuotientArray()
	{
		return quotient;
	}
	
	public int getActualArrayLength()
	{
		actualArrayLength=findZeroIndex(remainder)+1;
		return actualArrayLength;
	}
	
	public int getDeclareArrayLength()
	{
		return declareArrayLength;
	}
	
	public int[] getRemainderArray()
	{
		return remainder;
	}
	
	//(BOOL)
	//takes a list and target item
	//check if two numbers are relatively prime with each other
	public boolean checkIfCoPrime(int[] arrayList, int targetIndex)
	{
		boolean checkIndex=false;
		for(int index=0;index<arrayList.length;index++)
			if(arrayList[index]==targetIndex)
				if (arrayList[index-1]==1)
				{	
					checkIndex=true;
					return checkIndex;
				}
		return checkIndex;
	}
	
	//takes the list and check where 0 remainder appears
	public int findZeroIndex(int[] arrayList)
	{
		int index=0;
		for(int count=0;count<arrayList.length;count++)
			if(arrayList[count]==0)
			{	index=count;
				return index;
			}
		return index;
	}
}