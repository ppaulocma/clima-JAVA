package br.com.clima.service;

import org.springframework.stereotype.Service;
import br.com.clima.model.Tempo;

@Service
public class TempoService {
	
	public void getObj(String values){
		
		Tempo tempo = new Tempo();
		
		String value[] = values.split(values, '\n');
		
		for (String string : value) {
			
		}
	}
}
