package test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import test.model.Linija;
import test.model.Prevoznik;
import test.service.LinijaService;
import test.service.PrevoznikService;
import test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDTOToPrevoznik implements Converter<PrevoznikDTO, Prevoznik> {

	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private LinijaService linijaService;
	
	@Override
	public Prevoznik convert(PrevoznikDTO dto) {
		Prevoznik prevoznik;

        if(dto.getId() == null){
        	prevoznik = new Prevoznik();
        }else{
        	prevoznik = prevoznikService.findOneById(dto.getId());
        }
        
        if(prevoznik != null){
        	prevoznik.setNaziv(dto.getNaziv());
        	prevoznik.setAdresa(dto.getAdresa());
        	prevoznik.setPIB(dto.getPIB());
        	if(dto.getLinije() != null) {
	        	List<Linija> linije = linijaService.find(new ArrayList<>(dto.getLinije().keySet()));
	        	prevoznik.setLinije(linije);
        	}

        }
        
        return prevoznik;
	}

}
