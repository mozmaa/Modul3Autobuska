package test.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.model.Linija;
import test.model.Rezervacija;
import test.service.LinijaService;
import test.service.RezervacijaService;
import test.support.RezervacijaDTOToRezervacija;
import test.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value = "/api/rezervacije", produces = MediaType.APPLICATION_JSON_VALUE)
public class RezervacijaController {

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private RezervacijaDTOToRezervacija toRezervacija;
	
	@Autowired
	private LinijaService linijaService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RezervacijaDTO> create(@Valid @RequestBody RezervacijaDTO rezervacijaDTO){
	 	Rezervacija rezervacija = toRezervacija.convert(rezervacijaDTO);
	 	Linija linija = linijaService.findOneById(rezervacijaDTO.getLinijaId());
        if(rezervacijaDTO.getLinijaId() == null || linija.getBrojMesta() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Rezervacija savedRezervacija = rezervacijaService.save(rezervacija);
       
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
