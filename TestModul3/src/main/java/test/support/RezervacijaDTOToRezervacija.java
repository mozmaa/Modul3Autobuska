package test.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.model.Rezervacija;
import test.service.LinijaService;
import test.service.RezervacijaService;
import test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDTOToRezervacija implements Converter<RezervacijaDTO, Rezervacija> {

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private LinijaService linijaService;
	
	@Override
	public Rezervacija convert(RezervacijaDTO dto) {
		Rezervacija rezervacija;

        if(dto.getId() == null){
        	rezervacija = new Rezervacija();
        }else{
        	rezervacija = rezervacijaService.findOneById(dto.getId());
        }
        
        if(rezervacija != null){
        	rezervacija.setDatumIVremeRezervacije(dto.getDatumIVremeRezervacije());
        	rezervacija.setLinija(linijaService.findOneById(dto.getLinijaId()));

        }
        
        return rezervacija;
	}
	
//	private LocalDateTime getLocalDateTime(String datumIVreme) throws DateTimeParseException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate datum = LocalDate.parse(datumIVreme.replace('T', ' ').substring(0, 10), formatter);
//        LocalTime vreme = LocalTime.parse(datumIVreme.substring(11), DateTimeFormatter.ofPattern("HH:mm"));
//        return LocalDateTime.of(datum, vreme);
//    }
}
