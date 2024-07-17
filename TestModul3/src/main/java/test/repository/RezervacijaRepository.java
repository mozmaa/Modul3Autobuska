package test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

	List<Rezervacija> findByIdIn(List<Long> ids);

	Rezervacija findOneById(Long id);

	List<Rezervacija> findAllByLinijaId(Long id);

}
