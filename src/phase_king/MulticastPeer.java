package phase_king;

import java.io.*;
import java.util.*;
import java.net.*;

public class MulticastPeer {
	
	// Attributes
	int port;
	String group = "";
	
	MulticastPeer(int port, String groupIP) {
		this.port = port;
		this.group = groupIP;
	};
	
	public String listen() {
		MulticastSocket s = null;
		String message = "";
		try {
			s = new MulticastSocket(this.port);
			InetAddress group = InetAddress.getByName(this.group);
			s.joinGroup(group);
			
			byte[] buffer = new byte[1000];
			DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
			s.receive(messageIn);
			
			message = new String(messageIn.getData());
			
			System.out.println("Received: " + new String(messageIn.getData()));
		} catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		} catch (IOException e){System.out.println("IO: " + e.getMessage());
		} finally { if(s != null) s.close(); }
		return message;
	}
	
	public void talk(String message) {
		MulticastSocket s = null;
		try {
			s = new MulticastSocket(this.port);
			InetAddress group = InetAddress.getByName(this.group);
			s.joinGroup(group);
			
			byte [] m = message.getBytes();
			DatagramPacket messageOut = new DatagramPacket(m, m.length, group, this.port);
			s.send(messageOut);
		} catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		} catch (IOException e){System.out.println("IO: " + e.getMessage());
		} finally { if(s != null) s.close(); }
	}
}

//public class MulticastPeer{
//    public static void main(String args[]){ 
//		// args give message contents and destination multicast group (e.g. "228.5.6.7")
//		MulticastSocket s = null; // set multicast as null
//		try {
//			InetAddress group = InetAddress.getByName(args[0]);
//			s = new MulticastSocket(6789);
//			s.joinGroup(group);
//			String qwerty = "";
//			
//			Thread thread = new Thread() {
//				private boolean flag = true;
//				public void run() {
//					MulticastSocket s = null;
//					try {	
//						InetAddress group = InetAddress.getByName("228.5.6.7");
//						s = new MulticastSocket(6789);
//						s.joinGroup(group);
//						Object[] processos = new Object[5]; // Array de processos
//						while(flag) {
//							#pegaTime //watchdog
//							// Envia HELLO
//							Scanner msg = new Scanner(System.in);
//							qwerty = msg.next();
//							byte [] m = qwerty.getBytes();
//							DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6789);
//							s.send(messageOut);	
//							// Espera HELLO
//							byte[] buffer = new byte[1000];
//							DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
//							s.receive(messageIn);
//							System.out.println("Received:" + new String(messageIn.getData()));
//							// Verificacao SYNC
//							boolean check = true;
//							for (int i = 0; i < 5; i++) {
//								if processos[i] == null
//									check = false;
//							}
//							if(check) {
//								mandaHello.mp4.txt.java();
//								break;
//							}
//							
//							if(timeAgora - #pegaTime >= 30000) {
//								int count = n/4;
//								for(int i=0 ; fwirgnihuo) {
//									
//								}
//							}
//							
//							
//							// await MILISECONDS
//							
//							// AGORA EXECUTA AS PHASES
//							//...
//							
//						}
//					} catch (SocketException e){System.out.println("Socket: " + e.getMessage());
//					} catch (IOException e){System.out.println("IO: " + e.getMessage());
//					} finally { if(s != null) s.close(); }
//				}
//				
//				public void stopRunning() {
//					flag = false;
//				}
//			};
//			
//			thread.start();
//			
//			do {
//				Scanner msg = new Scanner(System.in);
//				qwerty = msg.next();
//				byte [] m = qwerty.getBytes();
//				DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6789);
//				s.send(messageOut);	
////				byte[] buffer = new byte[1000];
////				DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
////				s.receive(messageIn);
////				System.out.println("Received:" + new String(messageIn.getData()));
//			} while(!qwerty.equals("sair"));
//			s.leaveGroup(group);
//		} catch (SocketException e){System.out.println("Socket: " + e.getMessage());
//		} catch (IOException e){System.out.println("IO: " + e.getMessage());
//		} finally {if(s != null) s.close();}
//	}		      	
//}