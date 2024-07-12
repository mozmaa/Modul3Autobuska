package test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.model.Linija;
import test.model.Rezervacija;
import test.service.LinijaService;
import test.service.PrevoznikService;
import test.service.RezervacijaService;
import test.web.dto.LinijaDTO;

@Component
public class LinijaDTOToLinija implements Converter<LinijaDTO, Linija> {
	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private RezervacijaService rezervacijaService;

	@Override
	public Linija convert(LinijaDTO dto) {
		Linija linija;

        if(dto.getId() == null){
        	linija = new Linija();
        }else{
        	linija = linijaService.findOneById(dto.getId());
        }
        
        if(linija != null){
        	linija.setBrojMesta(dto.getBrojMesta());
        	linija.setCenaKarte(dto.getCenaKarte());
        	linija.setDestinacija(dto.getDestinacija());
        	linija.setPrevoznik(prevoznikService.findOneById(dto.getPrevoznikId()));
        	linija.setVremePolaska(dto.getVremePolaska());
        	if(dto.getRezervacije() != null) {
	        	List<Rezervacija> rezervacije = rezervacijaService.find(new ArrayList<>(dto.getRezervacije().keySet()));
	        	linija.setRezervacije(rezervacije);
        	}
        }
        
        return linija;
	}

}
