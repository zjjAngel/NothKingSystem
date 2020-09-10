package com.NorthKingSys.jbf.cn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class NorthKingSysApplicationTests {

	@Test
	void contextLoads() {

		ReentrantLock reentrantLock= new ReentrantLock();
		reentrantLock.lock();
		System.out.println("");

	}

}
