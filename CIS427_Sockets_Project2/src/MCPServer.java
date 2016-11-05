import java.io.BufferedReader;
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


public class MCPServer 
{
	public static void main(String args[]) throws Exception 
	{
		
			//declarations
			String clientSentence; 
			byte Array[] = null; 
			String Array2[] = null;

			ServerSocket welcomeSocket = new ServerSocket(2027); 
			Socket connectionSocket = welcomeSocket.accept(); 	//welcoming socket
			BufferedReader inFromClient =  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 	//creates input stream & attaches to socket
			DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream());  //creates output stream& attaches to socket 
			clientSentence = inFromClient.readLine();				//from client   
			   do { 
			    	  if (clientSentence.equals("Query"))
			    	  {
			    		 inputFile(Array,outToClient);
			    	  }
			    	  else
			    	  {
			    		  
			    		  DatagramSocket serverSocket = new DatagramSocket(6789); 
						 byte[] receiveData = new byte[1024]; 
						  byte[] sendData = new byte[1024]; 
						 //byte receiveData[] = null; 
						  //byte sendData[] = null; 
						  //String sendData=null;
						  System.out.println("In UDP Server side!");
						  DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
						  serverSocket.receive(receivePacket); 
						  InetAddress IPAddress = receivePacket.getAddress(); 
						  System.out.println("UDP server, got IP address: "+IPAddress);
						  int port = receivePacket.getPort();
						  System.out.println("UDP Server, got port num: " + port);
						  //DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, port);
						  //clientSentence = new String(receivePacket.getData());
			    		 // inputFileUDP(Array, sendPacket);
						  java.io.File file = new java.io.File("C:/Users/Colette/Documents/CK School/Fall 2016/CIS 427/AliceInWonderland.txt");

							//while(true) 
							//{
								int totalNumber=0;  
								int i=0;
								int UDPtotal=0;
								
								try 
								{
									Scanner input = new Scanner(file);		//scanner, to read input file 
									sendData=null;
							        while (input.hasNextLine()) 
							        {
							        	String line = input.nextLine();	//reads each line from the input file individually
							        	//Array2[i]=line;
							            Array = line.getBytes();		//converts each line from input file from string to bytes
							            int number = Array.length;		
							            totalNumber = totalNumber + number;		//sums all of the bytes from all of the lines that have been read thus far
							            i++;
							          // outToClient.writeInt(totalNumber);
							            
							            
							            sendData=line.getBytes();
							            
							            int UDPByteLength = sendData.length;
							            UDPtotal = UDPtotal+UDPByteLength;
							            //System.out.println("Send data is: "+UDPtotal);
							            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, port);
							            serverSocket.send(sendPacket);
							        }	//end while loop
							        input.close();	//close scanner
							        serverSocket.close();
							    } 	//end try
							    catch (Exception e) 
								{
							        e.printStackTrace();
							    }	//end catch 
								//serverSocket.send(sendPacket);
							}
			    	 // }
			    	 
			      }	while(true);	//end while
			     
			     

			}	//end main method

			
			
			public static void inputFile(byte Array[], DataOutputStream outToClient) throws Exception
			//this method reads the Alice in Wonderland input file from the text file to the program and converts the strings to bytes
			{
				//import file
				java.io.File file = new java.io.File("C:/Users/Colette/Documents/CK School/Fall 2016/CIS 427/AliceInWonderland.txt");

				//declarations 
				int totalNumber=0;  
				int i=0;
				
				try 
				{
					Scanner input = new Scanner(file);		//scanner, to read input file 

			        while (input.hasNextLine()) 
			        {
			        	String line = input.nextLine();	//reads each line from the input file individually
			            Array = line.getBytes();		//converts each line from input file from string to bytes
			            int number = Array.length;		
			            totalNumber = totalNumber + number;		//sums all of the bytes from all of the lines that have been read thus far
			            i++;
			           outToClient.writeInt(totalNumber);

			        }	//end while loop
			        input.close();	//close scanner
			    } 	//end try
			    catch (Exception e) 
				{
			        e.printStackTrace();
			    }	//end catch 
			}	//end input File method
			
			//end MPC Server class 

		/*	public static void inputFileUDP(byte Array[], DatagramPacket sendPacket,byte sendData) throws Exception
			{
				
				java.io.File file = new java.io.File("C:/Users/Colette/Documents/CK School/Fall 2016/CIS 427/AliceInWonderland.txt");

				while(true) 
				{
					int totalNumber=0;  
					int i=0;
					
					try 
					{
						Scanner input = new Scanner(file);		//scanner, to read input file 

				        while (input.hasNextLine()) 
				        {
				        	String line = input.nextLine();	//reads each line from the input file individually
				            Array = line.getBytes();		//converts each line from input file from string to bytes
				            int number = Array.length;		
				            totalNumber = totalNumber + number;		//sums all of the bytes from all of the lines that have been read thus far
				            i++;
				          // outToClient.writeInt(totalNumber);
				           

				        }	//end while loop
				        input.close();	//close scanner
				    } 	//end try
				    catch (Exception e) 
					{
				        e.printStackTrace();
				    }	//end catch 
					
					
					
					
					serverSocket.send(sendPacket);
				}
	
			}*/

}
