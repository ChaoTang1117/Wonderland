package chao.wonderland.controller;

import chao.wonderland.bo.Reservation;
import chao.wonderland.dto.ReservationDTO;
import chao.wonderland.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("{userId}")
    public ResponseEntity<String> createReservation(
            @PathVariable String userId,
            @RequestBody ReservationDTO dto
    ){
        var reservationCreated = reservationService.createReservation(userId, dto);
        return new ResponseEntity<>(reservationCreated.getBookingId(), HttpStatus.CREATED);
    }

    @PutMapping("{bookingId}")
    public ResponseEntity<String> modifyReservation(
            @PathVariable String bookingId,
            @RequestBody ReservationDTO dto
    ) {
        reservationService.updateReservation(bookingId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{bookingId}")
    public ResponseEntity<String> cancelReservation(
            @PathVariable String bookingId
    ) {
        reservationService.deleteReservation(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
