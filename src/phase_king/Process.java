package phase_king;

import java.util.*;
import java.io.*;
import java.net.*;

public class Process extends Thread {
	
	// Process attributes
	private boolean value;
	private long id;
	private long ids[];
	
	// MulticastPeer
	private int socketPort;
	private String groupIP;
	
	Process(boolean value, int id, int port, String group) {
		this.value = value;
		this.id = id;
		this.socketPort = port;
		this.groupIP = group;
		// Processes IDs
		ids = new long[]{0, 0, 0, 0, 0};
	}
	
	public void changeValue(boolean newValue) {
		this.value = newValue;
	}
	
	public boolean getValue() {
		return(this.value);
	}
	
	public long getId() {
		return(this.id);
	}
	
	public void run() {
		// Sync
		MulticastPeer multicast = new MulticastPeer(socketPort, groupIP);
		multicast.talk(this.id + ": Hello");
		
		String message = "";
		
		int index = 0;
		while (ids[index] == 0) {
			message = multicast.listen();
			if (message.contains("Hello")) {
				String[] parts = message.split(":");
				System.out.println("ID: " + parts[0]);
				ids[index] = Long.parseLong(parts[0]);
				index++;
			}
		}
    }
}
