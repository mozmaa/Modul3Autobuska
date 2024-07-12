package test.support;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.model.Linija;
import test.model.Prevoznik;
import test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikToTPrevoznikDTO implements Converter<Prevoznik, PrevoznikDTO> {

	@Override
	public PrevoznikDTO convert(Prevoznik prevoznik) {
		PrevoznikDTO dto = new PrevoznikDTO();
        dto.setId(prevoznik.getId());
        dto.setNaziv(prevoznik.getNaziv());
        dto.setAdresa(prevoznik.getAdresa());
        dto.setPIB(prevoznik.getPIB());
        
        LinkedHashMap<Long, String> linijaMap = new LinkedHashMap<>();
        for (Linija linija: prevoznik.getLinije()) {
        	linijaMap.put(linija.getId(), linija.getDestinacija());
        }
        dto.setLinije(linijaMap);
        return dto;
	}
	
	public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici){
	    List<PrevoznikDTO> prevoznikDTOs = new ArrayList<>();
	
	    for(Prevoznik prevoznik: prevoznici) {
	    	prevoznikDTOs.add(convert(prevoznik));
	    }
	
	    return prevoznikDTOs;
	}

}
