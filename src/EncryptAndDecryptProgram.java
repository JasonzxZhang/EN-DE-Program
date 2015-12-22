/*  Encryption/Decryption Program
EncryptAndDecryptProgram.java
Jason Zhang
jzzhang
Your section 1
*/
import java.io.*;
import java.util.Scanner;
public class EncryptAndDecryptProgram
{
	public static void main(String[] args) throws IOException
	{//===============welcome message====================
		Scanner input= new Scanner(System.in);
		char menuSelection=welcomeMessage();
		String allFileName="DecryptedFileNames.txt";
		File file= new File(allFileName);
		//(AR)
		ReadInFiles[] txtFiles= new ReadInFiles[countLength(file)];
		Scanner writeIn= new Scanner(file);
		for (int count=0;count<txtFiles.length;count++) //read in existing file names to object array
		//(INPUT)
			txtFiles[count]= new ReadInFiles(writeIn.nextLine());
		writeIn.close();
	//====================================================
		String enMessageFileName;
		switch (menuSelection)
		{
			case 'E':
				do
				{
					System.out.println("What would you like to name your file (without extention):");
					enMessageFileName=isInputEmpty();
				}while(isNameTaken(txtFiles, enMessageFileName)==true);
				writeNewOutputNameFile(file,enMessageFileName);//append from previous file name menu txt file
				Encryption encrypt = new Encryption(enMessageFileName);
				endMessage();
				break;
			
			case 'D':
				sortFileList(txtFiles);
				int validate=-1;
				do
				{
					System.out.println("Which file would you like to decrypt? (include file extention)");
					String deMessageFileName=isInputEmpty();
					Decryption decrypt=null;
					validate=searchItem(txtFiles,deMessageFileName);
					if(validate==-1)
						System.out.println("FILE DOES NOT EXIST!");
					else
					{
						decrypt = new Decryption(deMessageFileName);
						endMessage();
					}
				}while(validate==-1);
				break;
		}
		input.close();
	}
	
	//displaying a welcome message to the user
	public static char welcomeMessage()

	{
		char choice=' ';
		boolean menuInputValidation = false;
		Scanner input= new Scanner(System.in);

		System.out.println("*************************************************");
		System.out.println("* Welcome to the Encryption/Decryption program! *");
		System.out.println("*************************************************");

		do
			 {
			 	System.out.println("Would you like to Encrypt(E) or Decrypt(D) a message?");
			 	String ans = input.nextLine();
			 	if(ans.isEmpty())
			 		System.out.println("INPUT CANNOT BE EMPTY");
			 	else
			 	{	choice=ans.toUpperCase().charAt(0);
			 		if (choice=='E' || choice=='D')
			 		menuInputValidation=true;
			 	}
			 } while(menuInputValidation==false);
		return choice;
	}
	
	//completion message with ASCII text art
	public static void endMessage()
	{	
		System.out.println();
		System.out.println("   _____                      _      _           _ _ ");
		System.out.println("  / ____|                    | |    | |         | | |");
		System.out.println(" | |     ___  _ __ ___  _ __ | | ___| |_ ___  __| | |");
		System.out.println(" | |    / _ \\| '_ ` _ \\| '_ \\| |/ _ \\ __/ _ \\/ _` | |");
		System.out.println(" | |___| (_) | | | | | | |_) | |  __/ ||  __/ (_| |_|");
		System.out.println("  \\_____\\___/|_| |_| |_| .__/|_|\\___|\\__\\___|\\__,_(_)");
		System.out.println("                       | |                           ");
		System.out.println("                       |_|                           ");
	}
	
	//not allowing users to enter empty string
	public static String isInputEmpty()
	{
		Scanner input= new Scanner(System.in);
		String choice;
		do
		{
			choice=input.nextLine();
			if(choice.isEmpty())
		 		System.out.println("INPUT CANNOT BE EMPTY");
		}while(choice.isEmpty());
		return choice;
	}
	
	//counting how many lines the file(from File) has
	public static int countLength(File fname) throws IOException
	{
		Scanner readIn= new Scanner(fname);
		int countItems=0;
		while(readIn.hasNextLine())
		{
			countItems++;
			readIn.nextLine();
		}
		readIn.close();
		
		return countItems;
		
	}
	
	//(MYMETH(O))
	//take in the array object list and user's chosen filename
	//checks if the file name is take (already existed in the directory file
	public static Boolean isNameTaken(ReadInFiles[] list, String targetFileName)
	{
		if(searchItem(list, (targetFileName+".txt"))==-1)
			return false;
		System.out.println("FILENAME TAKEN!");
		return true;
	}
	
	//(MYMETH)
	//take in the File path and user's chosen file name
	//append to existing directory txt file
	public static void writeNewOutputNameFile(File file,String newFileName) throws IOException
	{
		FileWriter write= new FileWriter(file,true);
		PrintWriter reWrite= new PrintWriter(write);
		reWrite.println(newFileName+".txt");
		reWrite.close();
	}
	
	//(SEARCH)
	public static int searchItem(ReadInFiles[] nameList, String targetFileName)

	{
		for(int index=0;index<nameList.length;index++)
			if(nameList[index].getFileName().compareTo(targetFileName)==0)
				return index;
		return -1;
	}
	
	//(SORT)
	public static void sortFileList(ReadInFiles[] list)
	{
		String[] fileName=new String[list.length];
		if(list.length<1)
		{
			System.out.println("==================================");
			System.out.println("NO FILE HAS BEEN CREATED YET!");
	    	System.out.println("----------------------------------");
	    	System.exit(0);
		}
	    else
		{
			for(int index=0;index<list.length;index++)
				fileName[index]=list[index].getFileName();
			
		    int min;  //index of smallest element
		    String temp;  //for swapping
		    
		    for (int index=0; index<list.length; index++)
		    {
		      //Find index of smallest element in part of array
		      min = index;
		      for (int j=index+1; j<list.length; j++)
		        if (fileName[j].compareTo(fileName[min])< 0)
		          min = j;
		      
		      //swap the values
		      temp = fileName[min];
		      fileName[min] = fileName[index];
		      fileName[index] = temp;
		    }
		    System.out.println("==================================");
		     for(String item:fileName)
		    	 System.out.println(item);
		     System.out.println("----------------------------------");
		}
	}
}

