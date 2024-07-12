package test.service;

import java.util.List;

import test.model.Rezervacija;

public interface RezervacijaService {

	List<Rezervacija> find(List<Long> ids);

	Rezervacija save(Rezervacija rezervacija);

	Rezervacija findOneById(Long id);

}
