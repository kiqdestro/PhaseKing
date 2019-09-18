package phase_king;

import java.io.*;
import java.util.*;
import java.net.*;

public class MulticastPeer {
	
	MulticastSocket s = null;
	InetAddress group = null;
	
	MulticastPeer(MulticastSocket s, InetAddress group) throws IOException {
		
		this.s = s;
		this.group = group;
		
	};
	
	public String listen() {
		
	}

}
