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
    public CompletableFuture<String> callCreateReservationService(String startDate, String endDate) throws URISyntaxException {
        String url = "http://localhost:8080/reservations/ca445520-9c17-48fa-bdc8-c6396a41a7f5";
        URI uri = new URI(url);
        Reservation reservationTest = new Reservation();
        reservationTest.setBookingId(UUID.randomUUID().toString());
        reservationTest.setUserId(String.valueOf(1));
        reservationTest.setArrivalDate(LocalDate.parse(startDate));
        reservationTest.setDepartureDate(LocalDate.parse(endDate));
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Reservation> request = new HttpEntity<>(reservationTest, headers);
        try {
            var result = restTemplate.postForEntity(uri, request, String.class);
            return CompletableFuture.completedFuture(result.getBody()); //return booking ID
        } catch (Exception e) {
            return CompletableFuture.completedFuture("full");
        }


    }

    @Async
    public CompletableFuture<String> callCancelReservationService(String bookingId) throws URISyntaxException {
        String createRsvUrl = "http://localhost:8080/reservations/" + bookingId;
        URI uri = new URI(createRsvUrl);
        Reservation reservationTest = new Reservation();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Reservation> request = new HttpEntity<>(reservationTest, headers);
        restTemplate.delete(createRsvUrl, request, String.class);

        return CompletableFuture.completedFuture(bookingId + " deleted");
    }

//    @Async
//    public CompletableFuture<String> callCancelReservationService(String startDate, String endDate) throws URISyntaxException {
//        String url = "http://localhost:8080/reservations/ca445520-9c17-48fa-bdc8-c6396a41a7f5";
//        URI uri = new URI(url);
//        Reservation reservationTest = new Reservation();
//        reservationTest.setBookingId(UUID.randomUUID().toString());
//        reservationTest.setUserId(String.valueOf(1));
//        reservationTest.setArrivalDate(LocalDate.parse(startDate));
//        reservationTest.setDepartureDate(LocalDate.parse(endDate));
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Reservation> request = new HttpEntity<>(reservationTest, headers);
//        var result = restTemplate.delete(url, request, String.class);
//
//        return CompletableFuture.completedFuture(result.toString());
//    }

}