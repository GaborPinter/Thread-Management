package com.example.MT;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRunnable implements Callable<Boolean>{
	
	private String id;
	private int waitMilliSeconds;
	
	private static final Random RANDOM=new Random();
	private static final List<Boolean> values=List.of(true,false);
	
	public static Logger logger = LoggerFactory.getLogger(MyRunnable.class);
	
	public MyRunnable(String id, int waitSeconds) {
		super();
		this.id=id;
		this.waitMilliSeconds=waitSeconds;
	}

	@Override
	public Boolean call() throws Exception {
		Thread.currentThread().setName(id);
		logger.info(Thread.currentThread().getName()+" started and running for "+waitMilliSeconds+" milliSeconds");
		
		Thread.sleep(waitMilliSeconds);
		int choosenNumber=RANDOM.nextInt(values.size());
		boolean result=values.get(choosenNumber);
		
		logger.info(Thread.currentThread().getName()+" finished and the result is "+result);
		return result;
	}

}
