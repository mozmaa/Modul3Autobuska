package test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Prevoznik {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String naziv;
	
	@Column
	private String adresa;
	
	@Column(unique = true, nullable = false)
	private String PIB;
	
	@OneToMany(mappedBy = "prevoznik" , fetch = FetchType.EAGER)
	private List<Linija> linije = new ArrayList<>();
	
	public Prevoznik () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Linija> getLinije() {
		return linije;
	}

	public void setLinije(List<Linija> linije) {
		this.linije = linije;
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
		Prevoznik other = (Prevoznik) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Prevozik [Id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", PIB=" + PIB + "]";
	}
	
	
}
