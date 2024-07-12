package test.support;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.model.Linija;
import test.model.Rezervacija;
import test.web.dto.LinijaDTO;

@Component
public class LinijaToLinijaDTO implements Converter<Linija, LinijaDTO> {

	@Override
	public LinijaDTO convert(Linija linija) {
		LinijaDTO dto = new LinijaDTO();
		dto.setId(linija.getId());
		dto.setBrojMesta(linija.getBrojMesta());
		dto.setCenaKarte(linija.getCenaKarte());
		dto.setDestinacija(linija.getDestinacija());
		dto.setVremePolaska(linija.getVremePolaska());
		dto.setPrevoznikId(linija.getPrevoznik().getId());
		dto.setPrevoznikNaziv(linija.getPrevoznik().getNaziv());
		LinkedHashMap<Long, String> rezervacijaMap = new LinkedHashMap<>();
	        for (Rezervacija rezervacija: linija.getRezervacije()) {
	        	rezervacijaMap.put(rezervacija.getId(), rezervacija.getDatumIVremeRezervacije());
	        }
		dto.setRezervacije(null);
		return dto;
	}
	
	public List<LinijaDTO> convert(List<Linija> linije){
        List<LinijaDTO> linijaDTOs = new ArrayList<>();

        for(Linija linija: linije) {
        	linijaDTOs.add(convert(linija));
        }

        return linijaDTOs;
    }

}
