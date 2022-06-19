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

    @Query(value = "SELECT a.* FROM availability_info AS a " +
            "WHERE a.date BETWEEN :startDate AND :endDate AND a.capacity > 0"
           , nativeQuery = true)
    List<Availability> findAllAvailabilitiesInDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT count(a.*) FROM availability_info AS a " +
           "WHERE a.date = :date AND a.capacity > 0", nativeQuery = true)
    Integer checkDateAvailability(@Param("date") LocalDate date);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Modifying
    @Query(value = "UPDATE availability_info a set a.capacity = a.capacity - 1 " +
            "WHERE a.date = :date", nativeQuery = true)
    void reduceCapacity(@Param("date") LocalDate date);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Modifying
    @Query(value = "UPDATE availability_info a set a.capacity = a.capacity + 1 " +
            "WHERE a.date = :date", nativeQuery = true)
    void increaseCapacity(@Param("date") LocalDate date);
}
