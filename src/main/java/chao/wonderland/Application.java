package chao.wonderland;

//import chao.wonderland.repository.UserRepository;

import chao.wonderland.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
		//concurrently call create Reservation api 40 times with date range 2022-07-08 to 2022-07-09
		//initially date 2022-07-08 and date 2022-07-09 have capacity of 1000 people
		//after the calls, both date should have 900 capacities.
//		for (int i = 0; i < 100; i++) {
//			allFutures.add(asyncService.callMsgService("2022-07-08", "2022-07-09"));
//		}
//		CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0])).join();
//		for (int i = 0; i < 100; i++) {
//			System.out.println("response: " + allFutures.get(i).get());
//		}


//------------------------------------------------------------------------------------------
		//concurrently call create Reservation api 5 times with date range 2022-07-04 to 2022-07-05
		//initially date 2022-07-04 and date 2022-07-05 have capacity of 1000 people and 1
		//after the calls, only 1 reservation should be created and all subsequent call should fail
//		for (int i = 0; i < 5; i++) {
//			allFutures.add(asyncService.callMsgService("2022-07-04", "2022-07-05"));
//		}
//		CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0])).join();
//		for (int i = 0; i < 5; i++) {
//			System.out.println("response: " + allFutures.get(i).get());
//		}


//------------------------------------------------------------------------------------------
		//concurrently call create Reservation api 1000000 times with date 2022-07-18
		//result takes around 20 minutes.
//		for (int i = 0; i < 1000000; i++) {
//			allFutures.add(asyncService.callMsgService("2022-07-18", "2022-07-18"));
//		}
//		CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0])).join();



		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		System.out.println("Total time: " + timeElapsed + " ms");

	}
}
