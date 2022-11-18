package com.example.MT;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MtApplication {

	public static Logger logger = LoggerFactory.getLogger(MtApplication.class);
	public static final int threadNumber = 2;
	private static final Random RANDOM=new Random();

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		logger.info("Thread main started");

		usingExecutorService(4);

		logger.info("Thread main finished");
	}

	private static void usingExecutorService(int eventNumber) 
			throws InterruptedException, ExecutionException {
		ExecutorService executorService = 
				Executors.newFixedThreadPool(threadNumber, new MyThreadFactory());
		List<Future<Boolean>> returnedValues = new ArrayList<>();
		
		for(int i=1;i<=eventNumber;i++){
			String name=String.valueOf(i)+".";
			int runningTime=RANDOM.nextInt(5)+1;
			MyRunnable event=new MyRunnable(name,runningTime);
			Future<Boolean> future=executorService.submit(event);
			returnedValues.add(future);
		}

		
		executorService.shutdown();
		executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		
		logger.info("Results: ");
		for (Future<Boolean> value : returnedValues) {
			logger.info(String.valueOf(value.get()));
		}
	}

}
