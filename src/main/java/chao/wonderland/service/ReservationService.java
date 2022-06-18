package chao.wonderland.service;

import chao.wonderland.bo.Reservation;
import chao.wonderland.bo.User;
import chao.wonderland.repository.ReservationRepository;
import chao.wonderland.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.UUID;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    public Reservation CreateReservation(Reservation reservation){
        Reservation createdReservation;
        var existingUser = userRepository.find(reservation.getUserId());
        if(existingUser == null) {
            log.error("User does not exist");
            throw new PersistenceException("User does not exist");
        }
        reservation.setBookingId(UUID.randomUUID().toString());
        return reservationRepository.save(reservation);
    }
}
