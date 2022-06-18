package chao.wonderland.controller;

import chao.wonderland.bo.User;
import chao.wonderland.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity createReservation(){
        return new ResponseEntity(new Object(), HttpStatus.CREATED);
    }

    @GetMapping(value="/{userId}")
    public ResponseEntity<User> getReservation(
            @PathVariable String userId
    ){
            var users = userRepository.find(userId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
