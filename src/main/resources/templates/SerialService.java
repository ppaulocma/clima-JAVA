package br.com.arduino.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.springframework.stereotype.Service;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import br.com.arduino.controller.WebSocket;


@Service
public class SerialService implements SerialPortDataListener {

	private BufferedReader input;
	

	@Override
	public void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
			try {
				WebSocket.enviarMensagemClientes(this.input.readLine());
			} catch (Exception e) {
				System.err.println("Erro ao ler dados do arduino. " + e.getMessage());
			}
		}
	}

	private OutputStream output;

	public void enviarMensagemArduino(String message) {
		try {
			this.output.write(message.getBytes());
		} catch (Exception e) {
			System.err.println("Erro ao enviar mensagem para o Arduino. " + e.getMessage());
		}
	}


	public void abrirPortaSerial() {
		try {
			SerialPort serialPort;
			
			serialPort = SerialPort.getCommPort("COM3"); 
			serialPort.openPort();
			
			serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000 , 0);
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();
			
	
			serialPort.addDataListener(this);
			System.out.println("Porta Aberta com Sucesso!");
		} catch (Exception e) {
			System.err.println("Error ao abrir a porta serial.");
		}
	}


	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}
}
