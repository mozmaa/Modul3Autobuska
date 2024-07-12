package test.web.dto;

import java.util.LinkedHashMap;
import java.util.Map;


public class PrevoznikDTO {

	private Long Id;
	
	private String naziv;
	
	private String adresa;
	
	private String PIB;
	
	private Map<Long, String> linije = new LinkedHashMap<>();

	public PrevoznikDTO() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPIB() {
		return PIB;
	}

	public void setPIB(String pIB) {
		PIB = pIB;
	}

	public Map<Long, String> getLinije() {
		return linije;
	}

	public void setLinije(Map<Long, String> linije) {
		this.linije = linije;
	}
	
	
}
