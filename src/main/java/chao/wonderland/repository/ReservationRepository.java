package chao.wonderland.repository;

import chao.wonderland.bo.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query(value = "SELECT r.* FROM reservation AS r " +
            "WHERE r.booking_id = :bookingId", nativeQuery = true)
    Reservation findReservation(@Param("bookingId") String bookingId);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Modifying
    @Query(value = "UPDATE reservation r set r.arrival_date = :startDate " +
            ", r.departure_date = :endDate " +
            "WHERE r.booking_id = :bookingId", nativeQuery = true)
    void updateReservation(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("bookingId") String bookingId);
}
