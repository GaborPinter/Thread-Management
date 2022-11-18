package com.example.MT;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyThreadFactory implements ThreadFactory {

	private static final String threadName = "Thread-";
	private static int count = 1;
	Lock lock = new ReentrantLock();
	
	public static Logger logger = LoggerFactory.getLogger(MyThreadFactory.class);

	@Override
	public Thread newThread(Runnable r) {
		lock.lock();
		try {
			Thread t = new Thread(r);
			String name=threadName+count++;
			t.setName(name);
			logger.info(name+" created...");
			return t;
		} finally {
			lock.unlock();
		}
	}
}
