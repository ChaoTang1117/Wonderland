package chao.wonderland.controller;

import chao.wonderland.bo.User;
import chao.wonderland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/registrations")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createReservation(
            @Valid @RequestBody User user
    ){
        var userRegistered = userService.createUser(user);
        return new ResponseEntity<>(userRegistered.getUserId(), HttpStatus.CREATED);
    }
}
