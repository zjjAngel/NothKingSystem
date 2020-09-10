package com.NorthKingSys.jbf.cn;

import com.NorthKingSys.jbf.cn.controller.CustInfoController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.swing.*;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class NorthKingSysApplicationTests {

	@Test
	void contextLoads() {
		ReentrantLock reentrantLock= new ReentrantLock();
		reentrantLock.lock();
		System.out.println("");
		reentrantLock.unlock();


	}
	@Test
	private void testSpring(){
		AnnotationConfigApplicationContext annotationConf = new AnnotationConfigApplicationContext();
		annotationConf.getBean(CustInfoController.class);
		AnnotationConfigWebApplicationContext annotationConfigWeb = new AnnotationConfigWebApplicationContext();
		annotationConfigWeb.getBean(CustInfoController.class);
		XmlWebApplicationContext xmlWebApplicationContext= new XmlWebApplicationContext();
	}

}
