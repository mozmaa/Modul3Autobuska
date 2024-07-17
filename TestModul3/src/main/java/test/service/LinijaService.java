package test.service;

import java.util.List;

import org.springframework.data.domain.Page;

import test.model.Linija;

public interface LinijaService {

	List<Linija> find(List<Long> ids);

	Page<Linija> find(String destinacija, Long prevoznikId, Double maxCena, Boolean top3Mesta, int brojMesta, int pageNo);

	Linija findOneById(Long id);

	Linija save(Linija linija);

	Linija update(Linija linija);

	Linija delete(Long id);

}
