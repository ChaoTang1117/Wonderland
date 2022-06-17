package chao.wonderland.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createReservations")
public class ReservationController {
    @PostMapping
    public ResponseEntity createReservation(){
        return new ResponseEntity(new Object(), HttpStatus.CREATED);
    }
}
