package chao.wonderland.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Table(name= "reservation")
@Entity
public class Reservation {
    @Id
    @Column(name="booking_id", updatable = false)
    @JsonIgnore
    private String bookingId;

    @Column(name="user_id", updatable = false)
    private String userId;

    @Column(name="arrival_date")
    private LocalDate arrivalDate;

    @Column(name="departure_date")
    private LocalDate departureDate;
}
