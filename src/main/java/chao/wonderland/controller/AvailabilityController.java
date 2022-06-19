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

            if(startDate == null && endDate == null){
                startDate = LocalDate.now().toString();
                endDate = LocalDate.now().plusMonths(1).toString();
            }
            else if(startDate != null && endDate == null){
                endDate = (LocalDate.parse(startDate).plusMonths(1)).toString();
;            }
            else if(startDate == null){
                startDate = (LocalDate.parse(endDate).minusMonths(1)).toString();
            }
            var availabilities = availabilityService.getAvailabilities(startDate, endDate);
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }
}
