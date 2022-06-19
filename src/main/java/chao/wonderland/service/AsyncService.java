package chao.wonderland.service;

import chao.wonderland.bo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Autowired
    private RestTemplate restTemplate;

    //concurrently making POST request to create reservation
    @Async
    public CompletableFuture<String> callMsgService(String startDate, String endDate) throws URISyntaxException {
        String url = "http://localhost:8080/reservations/ca445520-9c17-48fa-bdc8-c6396a41a7f5";
        URI uri = new URI(url);
        Reservation reservationTest = new Reservation();
        reservationTest.setBookingId(UUID.randomUUID().toString());
        reservationTest.setUserId(String.valueOf(1));
        reservationTest.setArrivalDate(LocalDate.parse(startDate));
        reservationTest.setDepartureDate(LocalDate.parse(endDate));
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Reservation> request = new HttpEntity<>(reservationTest, headers);
        var result = restTemplate.postForEntity(uri, request, String.class);

        return CompletableFuture.completedFuture(result.toString());
    }

}