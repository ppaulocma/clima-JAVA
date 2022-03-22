package br.com.clima.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Tempo")
public class Tempo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double temperature;
	
    private Double pressure;
	
    private Double humidity;
	
	private Double windSpeed;
	
	private Double winDirection;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Double getWinDirection() {
		return winDirection;
	}

	public void setWinDirection(Double winDirection) {
		this.winDirection = winDirection;
	}

	@Override
	public String toString() {
		return "Tempo [id=" + id + ", temperature=" + temperature + ", pressure=" + pressure + ", humidity=" + humidity
				+ ", windSpeed=" + windSpeed + ", winDirection=" + winDirection + "]";
	}

	
}
