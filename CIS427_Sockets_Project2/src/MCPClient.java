import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
	     byte[] modifiedSentenceUDP;
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
	      
	      if (sentence.equals("Query"))
	      {
	    	  outToServer.writeBytes(sentence + '\n'); 
		      
	    	  for (i =0; i<3598; i++)
	    	  {
	    		  modifiedSentence = inFromServer.readInt();
	    		  System.out.println(modifiedSentence); 
	    	  }
	    	  i=0;
	    	 
	      }
	      else
	      { 
	        outToServer.writeBytes(sentence + '\n'); 
	    	BufferedReader inFromUserUDP = new BufferedReader(new InputStreamReader(System.in));
	  		DatagramSocket clientSocketUDP = new DatagramSocket(); 
	  		InetAddress IPAddress = InetAddress.getByName("localhost"); /*InetAddress.getByName("esun2.engin.umd.umich.edu");*/ /*InetAddress.getByName("141.215.10.124");*/
	  		System.out.println("IP address:" + IPAddress);
	  		
	  		byte[] sendData = new byte[1024]; 
	  		byte[] receiveData = new byte[1024]; 
	  		//byte[] receiveData = new byte[10240]; 
	  		//byte sendData []=null; 
	  		//byte receiveData [] = null; 
	  		//int sendData=0;
	  		//String receiveData=null;
	  		//String sentenceUDP = inFromUser.readLine(); 
	  		sendData = sentence.getBytes();
	  		DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, 6789);
	  		System.out.println("Data Sent from Client.");
	  		clientSocketUDP.send(sendPacket); 
	  		//DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
	  		//System.out.println("ReceiveData: " + receiveData);
	  		//System.out.println("ReceiveData length: " + receiveData.length);
	  		//clientSocketUDP.receive(receivePacket); 
	  		int recvdLength =0;
	  		for (int j =0;j<3598;j++)
	  		{
	  			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
	  			//String value = new String(receiveData,"UTF-8");
	  			String value = new String(receiveData);
	  			//x= receiveData.length;
	  			//=[x];
	  			System.out.println("ReceiveData: " + value);
		  		//System.out.println("ReceiveData length: " + receiveData.length);
		  		clientSocketUDP.receive(receivePacket); 
		  		byte[] data = new byte[receivePacket.getLength()];
	  			modifiedSentenceUDP = (receivePacket.getData());
	  			recvdLength = recvdLength + data.length;   //to keep running track of bytes ** need to add 4 for sequence num I think
	  			System.out.println("Sequence num:  " + recvdLength);
	  			//System.out.println("FROM SERVER:" + modifiedSentenceUDP); 
	  		}
	  		clientSocketUDP.close();
	      } 
	    }
	}
	      

}
