package chao.wonderland.controller;

import chao.wonderland.bo.Reservation;
import chao.wonderland.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping(value="/reservations")
    public ResponseEntity<String> createReservation(
            @RequestBody Reservation reservation
    ){
        var reservationCreated = reservationService.CreateReservation(reservation);
        return new ResponseEntity<>(reservationCreated.getBookingId(), HttpStatus.CREATED);
    }

//    @GetMapping(value="/search")
//    public ResponseEntity<Capacity> getAvailability(
//            @RequestParam LocalDate startDate,
//            @RequestParam LocalDate endDate
//    ){
//            var users = userRepository.find(userId);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
}
