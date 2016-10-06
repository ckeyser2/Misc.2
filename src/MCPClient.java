import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MCPClient 
{
	public static void main(String args[]) throws IOException 
	{
		//declarations
	      String sentence; 
	      //String modifiedSentence; 
	     int modifiedSentence = 0;
	     int i=0;
	      Scanner input = new Scanner(System.in); //to get user input

	      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
	      Socket clientSocket = new Socket("localhost",2027); 
	      
	      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
	      DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());	    
	     
	      while(true)
	      {
	      //display menu to user
	      System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
	      System.out.println("+-+-+-+-+-+-+ Multiple Channel Protocol +-+-+-+-+-+-+");
	      System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
	      System.out.println("Commands allowed by user:");
	      System.out.println("-Query");
	      System.out.println("-Download");
	      
	      //prompt user
	      System.out.println("command: ");
	      
	      //read input from user
	      sentence=input.next();
	      
	      outToServer.writeBytes(sentence + '\n'); 
	      
	      for (i =0; i<3598; i++)
		     {
		    modifiedSentence = inFromServer.readInt();
		     System.out.println(modifiedSentence); 
		     }
	      	i=0;
	     } 
	}
	      

}

