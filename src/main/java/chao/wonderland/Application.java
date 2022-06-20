package chao.wonderland;

import chao.wonderland.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private AsyncService asyncService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		}


	//Concurrency tests
	@Override
	public void run(String... args) throws Exception {

		Instant start = Instant.now();

		List<CompletableFuture<String>> allFutures = new ArrayList<>();

//------------------------------------------------------------------------------------------
		//concurrently call create Reservation api 100 times with date range 2022-07-08 to 2022-07-09
		//for every 10th call, try to call cancel reservation api.
		//initially date 2022-07-08 and date 2022-07-09 have capacity of 1000 people
		//after the calls, both date should have 910 capacities.
//		for (int i = 0; i < 100; i++) {
//			allFutures.add(asyncService.callCreateReservationService("2022-07-08", "2022-07-09"));
//			if(i % 10 == 0){
//				allFutures.add(asyncService.callCancelReservationService(allFutures.get(i).get()));
//			}
//		}
//
//		CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0])).join();
//		for (int i = 0; i < 100; i++) {
//			System.out.println("response: " + allFutures.get(i).get());
//		}





//------------------------------------------------------------------------------------------
		//concurrently call create Reservation api 5 times with date range 2022-07-04 to 2022-07-05
		//initially date 2022-07-04 and date 2022-07-05 have capacity of 1000 people and 1
		//after the calls, only 1 reservation should be created and all subsequent call should fail
//		for (int i = 0; i < 5; i++) {
//			allFutures.add(asyncService.callCreateReservationService("2022-07-04", "2022-07-05"));
//		}
//		CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0])).join();
//		for (int i = 0; i < 5; i++) {
//			System.out.println("response: " + allFutures.get(i).get());
//		}


//------------------------------------------------------------------------------------------
		//concurrently call create Reservation api 1000000 times with date 2022-07-18
		//result takes around 20 minutes.
//		for (int i = 0; i < 1000000; i++) {
//			allFutures.add(asyncService.callCreateReservationService("2022-07-18", "2022-07-18"));
//		}
//		CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0])).join();



		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		System.out.println("Total time: " + timeElapsed + " ms");

	}
}
