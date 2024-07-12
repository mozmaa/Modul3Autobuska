package test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.model.Prevoznik;
import test.service.PrevoznikService;
import test.support.PrevoznikDTOToPrevoznik;
import test.support.PrevoznikToTPrevoznikDTO;
import test.web.dto.PrevoznikDTO;


@RestController
@RequestMapping(value = "/api/prevoznici", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrevoznikController {

	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private PrevoznikToTPrevoznikDTO toDTO;
	
	@Autowired
	private PrevoznikDTOToPrevoznik toPrevoznik;
	
	@GetMapping()
	public ResponseEntity<List<PrevoznikDTO>> getAll(){
		List<Prevoznik> sprintovi = prevoznikService.findAll();
		return new ResponseEntity<> (toDTO.convert(sprintovi), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrevoznikDTO> create(@Valid @RequestBody PrevoznikDTO prevoznikDTO){
	 	Prevoznik prevoznik = toPrevoznik.convert(prevoznikDTO);

        if(prevoznik.getLinije().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Prevoznik savePrevoznik = prevoznikService.save(prevoznik);
        
        
        return new ResponseEntity<>(toDTO.convert(savePrevoznik), HttpStatus.CREATED);
    }
}
