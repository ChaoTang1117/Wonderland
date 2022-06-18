package chao.wonderland.bo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Table(name= "availability_info")
@Entity
public class Availability {

    @Id
    @Column(name="date", updatable = false)
    private LocalDate date;

    @Column(name="capacity")
    private Integer capacity;
}
