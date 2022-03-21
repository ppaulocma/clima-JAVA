package br.com.clima.service;

import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;


@Service
public class SerialService implements SerialPortDataListener {
	
	private SerialPort comPort;
	private String portDescription;
	
	public void setPortDescription(String portDescription){
		this.portDescription = portDescription;
		comPort = SerialPort.getCommPort(this.portDescription);
	}
	
	public boolean openConnection(){
		if(comPort.openPort()){
			System.out.println("Porta Serial aberta com sucesso!");
			return true;
		}
		else {
			System.err.println("Error ao abrir a porta serial.");
			return false;
		}
	}
	
	public void closeConnection() {
		comPort.closePort();
		System.out.println("Porta Serial Fechada!");
	}
	
	public String getPortDescription(){
		return portDescription;
	}
	
	public SerialPort getSerialPort(){
		return comPort;
		
	}
	
	public void SerialWrite(String s) {
		try {
			this.comPort.getOutputStream().write(s.getBytes());
		} catch (Exception e) {
			System.err.println("Erro ao enviar mensagem para o Arduino. " + e.getMessage());
		}
	}

	public void startListening() {
		try {
			comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 100 , 0);
			comPort.addDataListener(this);
			System.out.println("Leitura iniciada");
		} catch (Exception e) {
			System.err.println("Falha ao iniciar leitura. " + e.getMessage());
		}
	}
	
	@Override
	public void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
			String out="";
			Scanner in = new Scanner(comPort.getInputStream());
			try
			{
			   while(in.hasNext())
			      out += (in.next()+"\n");
			   	in.close();
			   	System.out.println(out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}
}
