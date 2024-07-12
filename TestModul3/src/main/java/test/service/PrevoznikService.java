package test.service;

import java.util.List;

import test.model.Prevoznik;

public interface PrevoznikService {

	List<Prevoznik> findAll();

	Prevoznik save(Prevoznik prevoznik);

	Prevoznik findOneById(Long id);

}
