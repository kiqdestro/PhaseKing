package phase_king;

import java.util.*;
import java.io.*;
import java.net.*;

public class Process {
	
	boolean value;
	int id;
	
	Process (boolean value, int id){
		this.value = value;
		this.id = id;
	}
	
	public void changeValue(boolean newValue) {
		this.value = newValue;
	}
	
	public boolean getValue() {
		return(this.value);
	}
	
	public int getId() {
		return(this.id);
	}
}
