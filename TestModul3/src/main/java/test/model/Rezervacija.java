package test.model;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class Rezervacija {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String DatumIVremeRezervacije;
	
	@ManyToOne()
	Linija linija;

	public Rezervacija() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatumIVremeRezervacije() {
		return DatumIVremeRezervacije;
	}

	public void setDatumIVremeRezervacije(String datumIVremeRezervacije) {
		DatumIVremeRezervacije = datumIVremeRezervacije;
	}

	public Linija getLinija() {
		return linija;
	}

	public void setLinija(Linija linija) {
		this.linija = linija;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rezervacija other = (Rezervacija) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Rezervacija [Id=" + id + ", DatumIVremeRezervacije=" + DatumIVremeRezervacije + "]";
	}
	
	
}
