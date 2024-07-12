package test.web.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


public class LinijaDTO {

	private Long Id;
	
	@PositiveOrZero(message = "Broj mesta ne moze biti negativna vrednost")
	private Integer brojMesta;
	
	private Double cenaKarte;
	
	private String vremePolaska;
	
	@NotBlank(message = "Destinacija nije uneta")
	private String destinacija;
	
	private Long prevoznikId;
	
	private String prevoznikNaziv;

	private Map<Long , String> rezervacije = new LinkedHashMap<>();

	public LinijaDTO() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Integer getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(Integer brojMesta) {
		this.brojMesta = brojMesta;
	}

	public Double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(Double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public String getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public Long getPrevoznikId() {
		return prevoznikId;
	}

	public void setPrevoznikId(Long prevoznikId) {
		this.prevoznikId = prevoznikId;
	}

	public String getPrevoznikNaziv() {
		return prevoznikNaziv;
	}

	public void setPrevoznikNaziv(String prevoznikNaziv) {
		this.prevoznikNaziv = prevoznikNaziv;
	}

	public Map<Long, String> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Map<Long, String> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	
}
