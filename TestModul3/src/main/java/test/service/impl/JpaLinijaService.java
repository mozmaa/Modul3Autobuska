package test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import test.model.Linija;
import test.repository.LinijaRepository;
import test.service.LinijaService;

@Service
public class JpaLinijaService implements LinijaService {

	@Autowired
	private LinijaRepository linijaRepository;

	@Override
	public List<Linija> find(List<Long> ids) {
		
		return linijaRepository.findByIdIn(ids);
	}

	@Override
	public Page<Linija> find(String destinacija, Long prevoznikId, Double maxCena, int pageNo) {
		
		if(maxCena == null)
			maxCena = Double.MAX_VALUE;
		return linijaRepository.search(destinacija, prevoznikId, maxCena, PageRequest.of(pageNo, 3));
	}

	@Override
	public Linija findOneById(Long id) {
		return linijaRepository.findOneById(id);
	}

	@Override
	public Linija save(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija update(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		 Linija linija= findOneById(id);
	        if(linija != null){
	        	linija.getPrevoznik().getLinije().remove(linija);
	        	linijaRepository.delete(linija);
	            return linija;
	        }
	        return null;
	}

}
