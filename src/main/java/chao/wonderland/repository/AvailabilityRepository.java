package chao.wonderland.repository;

import chao.wonderland.bo.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, String> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT a FROM Availability AS a " +
            "WHERE a.date BETWEEN ?1 AND ?2 AND a.capacity > 0")
    List<Availability> findAllAvailabilitiesInDateRange(String startDate, String endDate);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT count(*) FROM Availability AS a " +
           "WHERE a.date = ?1 AND a.capacity > 0")
    Integer checkDateAvailability(LocalDate date);

    @Modifying
    @Query(value = "UPDATE Availability a set a.capacity = a.capacity - 1 " +
            "WHERE a.date = ?1")
    void reduceCapacity(LocalDate date);

    @Modifying
    @Query(value = "UPDATE availability_info a set a.capacity = a.capacity + 1 " +
            "WHERE a.date = :date", nativeQuery = true)
    void increaseCapacity(@Param("date") LocalDate date);
}
