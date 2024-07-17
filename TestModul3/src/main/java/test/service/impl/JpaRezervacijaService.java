package test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.model.Linija;
import test.model.Rezervacija;
import test.repository.RezervacijaRepository;
import test.service.LinijaService;
import test.service.RezervacijaService;

@Service
public class JpaRezervacijaService implements RezervacijaService {

	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Autowired
	private LinijaService linijaService;
	
	@Override
	public List<Rezervacija> find(List<Long> ids) {
		return rezervacijaRepository.findByIdIn(ids);
	}

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		Linija linija = rezervacija.getLinija();
		linija.setBrojMesta(linija.getBrojMesta()-1);
		linija.getRezervacije().add(rezervacija);
		
		linijaService.save(linija);
		return rezervacijaRepository.save(rezervacija);
	}

	@Override
	public Rezervacija findOneById(Long id) {
		return rezervacijaRepository.findOneById(id);
	}

	@Override
	public List<Rezervacija> findAllByLinijaId(Long id) {
		return rezervacijaRepository.findAllByLinijaId(id);
	}

}
