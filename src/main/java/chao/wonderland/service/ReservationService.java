package chao.wonderland.service;

import chao.wonderland.bo.Reservation;
import chao.wonderland.dto.ReservationDTO;
import chao.wonderland.repository.AvailabilityRepository;
import chao.wonderland.repository.ReservationRepository;
import chao.wonderland.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private UserRepository userRepository;

    public Reservation createReservation(String userId, ReservationDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        var reservation = modelMapper.map(dto, Reservation.class);
        var arrivalDate = reservation.getArrivalDate();
        var departureDate = reservation.getDepartureDate();

        //check non existing user
        checkExistingUser(userId);


        validateReservationDates(arrivalDate, departureDate);
        reservation.setBookingId(UUID.randomUUID().toString());
        reservation.setUserId(userId);
        var createdReservation = reservationRepository.save(reservation);
        reduceCapacity(arrivalDate, departureDate);
        return createdReservation;
    }

    public void updateReservation(String bookingId, ReservationDTO dto){
        var arrivalDate = dto.getArrivalDate();
        var departureDate = dto.getDepartureDate();
        var existingReservation = findExistingBooking(bookingId);

        validateReservationDates(arrivalDate, departureDate);
        //increase capacities for the previously booked dates
        increaseCapacity(existingReservation.getArrivalDate(), existingReservation.getDepartureDate());
        //update reservation with the new date range
        reservationRepository.updateReservation(arrivalDate, departureDate, bookingId);
        //reduce capacities for the new dates
        reduceCapacity(arrivalDate, departureDate);
    }

    public void deleteReservation(String bookingId){
        var existingReservation = findExistingBooking(bookingId);
        increaseCapacity(existingReservation.getArrivalDate(), existingReservation.getDepartureDate());
        reservationRepository.delete(existingReservation);
    }

    private void reduceCapacity(LocalDate arrivalDate, LocalDate departureDate){
        var dateList = getAllDates(arrivalDate, departureDate);
        for(var date : dateList){
            availabilityRepository.reduceCapacity(date);
        }
    }

    private void increaseCapacity(LocalDate arrivalDate, LocalDate departureDate){
        var dateList = getAllDates(arrivalDate, departureDate);
        for(var date : dateList){
            availabilityRepository.increaseCapacity(date);
        }
    }

    private void validateReservationDates(LocalDate arrivalDate, LocalDate departureDate) {
        //check arrival date against departure date
        if(arrivalDate.isAfter(departureDate)){
            log.error("Arrival date must be before departure date");
            throw new PersistenceException("Arrival date must be before departure date");
        }

        //check reservation date against arrival date
        if (LocalDate.now().until(arrivalDate, ChronoUnit.DAYS) < 1
                || LocalDate.now().until(arrivalDate, ChronoUnit.DAYS) > 30) {
            log.error("The campsite can be reserved minimum 1 day(s) ahead of arrival and up to 1 month in advance");
            throw new PersistenceException("The campsite can be reserved minimum 1 day(s) ahead of arrival and up to 1 month in advance");
        }

        //check duration of reservation
        if (arrivalDate.until(departureDate, ChronoUnit.DAYS) >= 3) {
            log.error("The campsite can be reserved for max 3 days");
            throw new PersistenceException("The campsite can be reserved for max 3 days");
        }

        //check dates availability of reservation
        var dateList = getAllDates(arrivalDate, departureDate);
        for(var date : dateList){
            var dateAvailability = availabilityRepository.checkDateAvailability(date);
            if(dateAvailability == 0){
                throw new PersistenceException(date + " is full, please choose another date");
            }
        }
    }


    private Reservation findExistingBooking(String bookingId){
        var existingReservation = reservationRepository.findReservation(bookingId);
        if(existingReservation == null) {
            log.error("Booking information cannot be found");
            throw new PersistenceException("Booking information cannot be found");
        }
        return existingReservation;
    }

    private void checkExistingUser(String userId){
        if(userRepository.find(userId) == null) {
            log.error("User does not exist");
            throw new PersistenceException("User does not exist");
        }
    }


    //get all dates including arrival date until departure date
    private List<LocalDate> getAllDates(LocalDate arrivalDate, LocalDate departureDate){
        var dateList = arrivalDate.datesUntil(departureDate).collect(Collectors.toList());
        if(!departureDate.equals(arrivalDate)) {
            dateList.add(departureDate);
        } else { // if both dates are the same, dateList will be empty
            dateList.add(arrivalDate);
        }
        return dateList;
    }
}
