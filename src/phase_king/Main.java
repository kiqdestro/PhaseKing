package phase_king;

import java.util.*;
import java.io.*;
import java.net.*;

public class Main {

	public static void main(String[] args) {
		// Value
		Random randomObj = new Random();
		boolean value = randomObj.nextBoolean();
		
		// ID
		Scanner input = new Scanner(System.in);
		System.out.println("Process ID: ");
		int id = input.nextInt();
		input.close();
		
		// MulticastPeer
		int socketPort = 6789;
		String groupIP = "224.0.0.2";
		
		Process process = new Process(value, id, socketPort, groupIP);
		process.start();
	}
}