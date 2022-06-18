package chao.wonderland.repository;

import chao.wonderland.bo.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, String> {
    @Query(value = "SELECT a.* FROM availability_info AS a WHERE a.date BETWEEN :startDate AND :endDate AND a.capacity > 0"
           , nativeQuery = true)
    List<Availability> findAllAvailabilitiesInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
