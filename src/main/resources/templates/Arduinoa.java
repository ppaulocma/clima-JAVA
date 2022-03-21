package br.com.clima.controller;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import com.fazecast.jSerialComm.*;



public class Arduino{
	
	private SerialPort comPort;
	private String portDescription;
	private int baud_rate;
	
	public Arduino() {
		//empty constructor if port undecided
	}
	public Arduino(String portDescription) {
		//make sure to set baud rate after
		this.portDescription = portDescription;
		comPort = SerialPort.getCommPort(this.portDescription);
	}
	
	public Arduino(String portDescription, int baud_rate) {
		//preferred constructor
		this.portDescription = portDescription;
		comPort = SerialPort.getCommPort(this.portDescription);
		this.baud_rate = baud_rate;
		comPort.setBaudRate(this.baud_rate);
	}
	
	
	
	public boolean openConnection(){
		if(comPort.openPort()){
			
			return true;
		}
		else {
			System.err.println("Could not open connection.");
			return false;
		}
	}
	
	public void closeConnection() {
		comPort.closePort();
	}
	
	public void setPortDescription(String portDescription){
		this.portDescription = portDescription;
		comPort = SerialPort.getCommPort(this.portDescription);
	}

	
	public String getPortDescription(){
		return portDescription;
	}
	
	public SerialPort getSerialPort(){
		return comPort;
	}
	
	public String serialWriteAndRead(String s) {
		
		PrintWriter pout = new PrintWriter(comPort.getOutputStream());
		pout.print(s);
		pout.flush();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(comPort.getInputStream()));
		StringBuilder content = new StringBuilder();
		String line = null;
		String result = null;
		int i = 0;
		try
		{	
            while (in.readLine() != null) {
            	i++;
            	if(in.readLine() != null) {
            		line = in.readLine();
            		content.append(line);
            		content.append(System.lineSeparator());
            	}
            	
            	System.out.println(i);
            	
            }
            
            in.close();
            result = content.toString();
		} catch (Exception e) {
			
			return null;
			
		}
		System.out.println(result);
		return result;
	}
	
	
}
