package test.web.dto;


public class RezervacijaDTO {

	private Long Id;
	
	private String DatumIVremeRezervacije;
	
	private Long linijaId;
	
	private String destinacija;

	public RezervacijaDTO() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDatumIVremeRezervacije() {
		return DatumIVremeRezervacije;
	}

	public void setDatumIVremeRezervacije(String datumIVremeRezervacije) {
		DatumIVremeRezervacije = datumIVremeRezervacije;
	}

	public Long getLinijaId() {
		return linijaId;
	}

	public void setLinijaId(Long linijaId) {
		this.linijaId = linijaId;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}
	
	
}
