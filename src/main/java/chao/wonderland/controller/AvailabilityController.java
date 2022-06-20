package chao.wonderland.controller;

import chao.wonderland.bo.Availability;
import chao.wonderland.repository.AvailabilityRepository;
import chao.wonderland.service.AvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping(value="/search")
    public ResponseEntity<List<Availability>> getAvailability(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ){
            LocalDate arrivalDate = null;
            LocalDate departureDate = null;
            if(startDate == null && endDate == null){
                arrivalDate = LocalDate.now();
                departureDate = LocalDate.now().plusMonths(1);
            }
            else if(startDate != null && endDate == null){
                arrivalDate = LocalDate.parse(startDate);
                departureDate = arrivalDate.plusMonths(1);
;            }
            else if(startDate == null){
                departureDate = LocalDate.parse(endDate);
                arrivalDate = departureDate.minusMonths(1);
            } else{
                arrivalDate = LocalDate.parse(startDate);
                departureDate = LocalDate.parse(endDate);
            }

            var availabilities = availabilityService.getAvailabilities(arrivalDate, departureDate);
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }
}
