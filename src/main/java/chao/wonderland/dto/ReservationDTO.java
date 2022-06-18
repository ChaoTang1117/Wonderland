package chao.wonderland.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {

    private LocalDate arrivalDate;

    private LocalDate departureDate;
}
