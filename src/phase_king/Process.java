package phase_king;

import java.util.*;
import java.io.*;
import java.net.*;

public class Process extends Thread {
	
	// Process attributes
	private boolean value;
	private int id;
	private int ids[];
	
	// MulticastPeer
	private int port;
	private String group;
	
	Process(boolean value, int id, int port, String group) {
		this.value = value;
		this.id = id;
		this.port = port;
		this.group = group;
		// Processes IDs
		ids = new int[]{0, 0, 0, 0, 0};
	}
	
	public void run() {
		
		talk(this.id + ": Hello");
		sync();
		
		// ... Phase King Alg.
		
//		int index = 0;
//		while (ids[index] == 0) {
//			message = multicast.listen();
//			if (message.contains("Hello")) {
//				String[] parts = message.split(":");
//				System.out.println("ID: " + parts[0]);
//				ids[index] = Long.parseLong(parts[0]);
//				index++;
//			}
//		}
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
	
	// TODO: WatchDog
	public void sync() { 
		MulticastSocket s = null;
		try {
			s = new MulticastSocket(this.port);
			InetAddress group = InetAddress.getByName(this.group);
			s.joinGroup(group);
			
			while (true) { // TEMP
				byte[] buffer = new byte[1000];
				DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
				s.receive(messageIn);
				
				//message = new String(messageIn.getData());
				
				System.out.println("Received: " + new String(messageIn.getData()));
			}
			
		} catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		} catch (IOException e){System.out.println("IO: " + e.getMessage());
		} finally { if(s != null) s.close(); }
	}
}
