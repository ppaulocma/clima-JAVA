package br.com.clima.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clima.service.SerialService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class Controller {
	
	SerialService arduino = new SerialService();
	
	@GetMapping("/conectar")
	public void conectar() {
		arduino.setPortDescription("COM5");
		arduino.openConnection();
		arduino.startListening();
	}
	
	@GetMapping("/desconectar")
	public void desconectar() {
		arduino.closeConnection();
	}
	
	@GetMapping("/ligar")
	public void ligar() {
		arduino.SerialWrite("1");
	}

	@GetMapping("/desligar")
	public void desligar() {
		arduino.SerialWrite("2");
	}
	
}
