import java.io.*; 
import java.net.*;
class UDPServer 
{
	public static void main(String args[]) throws Exception 
	{
		DatagramSocket serverSocket = new DatagramSocket(6789); 
		byte[] receiveData = new byte[1024]; 
		byte[] sendData = new byte[1024]; 
		String clientSentence; 
		String capitalizedSentence;
		while(true) 
		{
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			serverSocket.receive(receivePacket); 
			clientSentence = new String(receivePacket.getData()); 
			InetAddress IPAddress = receivePacket.getAddress(); 
			int port = receivePacket.getPort();
			System.out.println("FROM CLIENT:" + clientSentence);
			capitalizedSentence = clientSentence.toUpperCase() + '\n'; 
			System.out.println("TO CLIENT:" + capitalizedSentence);
			sendData = capitalizedSentence.getBytes(); 
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}

