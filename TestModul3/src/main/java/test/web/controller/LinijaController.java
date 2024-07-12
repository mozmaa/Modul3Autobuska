package test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.model.Linija;
import test.service.LinijaService;
import test.support.LinijaDTOToLinija;
import test.support.LinijaToLinijaDTO;
import test.web.dto.LinijaDTO;


@RestController
@RequestMapping(value = "/api/linije", produces = MediaType.APPLICATION_JSON_VALUE)
public class LinijaController {

	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private LinijaToLinijaDTO toDTO;
	
	@Autowired
	private LinijaDTOToLinija toLinija;
	
	@PreAuthorize("permitAll()")
	@GetMapping
    public ResponseEntity<List<LinijaDTO>> getAll(
            @RequestParam(required=false) String destinacija,
            @RequestParam(required=false) Long prevoznikId,
            @RequestParam(required=false) Double maxCena,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

        Page<Linija> page = linijaService.find(destinacija, prevoznikId, maxCena, pageNo);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

        return new ResponseEntity<>(toDTO.convert(page.getContent()),headers, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	 public ResponseEntity<LinijaDTO> getOne(@PathVariable Long id){
		 Linija linija = linijaService.findOneById(id);

	     if(linija != null) {
	         return new ResponseEntity<>(toDTO.convert(linija), HttpStatus.OK);
	     }else {
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	     }
	 }
	
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<LinijaDTO> create(@Valid @RequestBody LinijaDTO linijaDTO){
		 	Linija linija = toLinija.convert(linijaDTO);

	        if(linijaDTO.getPrevoznikId() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        Linija savedLinija = linijaService.save(linija);
	        
	        
	        return new ResponseEntity<>(toDTO.convert(savedLinija), HttpStatus.CREATED);
	    }
	 
	 @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<LinijaDTO> update(@PathVariable Long id, @Valid @RequestBody LinijaDTO linijaDTO){

	        if(!id.equals(linijaDTO.getId())) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Linija linija = toLinija.convert(linijaDTO);

	        if(linijaDTO.getPrevoznikId() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        
	        Linija savedLinija = linijaService.update(linija);

	        return new ResponseEntity<>(toDTO.convert(savedLinija),HttpStatus.OK);
	    }
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id){
		 Linija deletedLinija = linijaService.delete(id);

	        if(deletedLinija != null) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
}
