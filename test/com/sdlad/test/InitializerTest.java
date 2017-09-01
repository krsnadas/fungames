package com.sdlad.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.tgt.snake.ladder.AppException;
import com.tgt.snake.ladder.BoardState;
import com.tgt.snake.ladder.Initializer;
import org.junit.Assert;

public class InitializerTest {
	
	@Test
	public void testInitializeConfig1(){
		Initializer init = new Initializer();
		File config = new File("conf/config1.txt");
		try {
			BoardState boardState = init.initialize(config);
		} catch (AppException | IOException e) {
			Assert.assertTrue("Exception Message matches", e.getMessage().equals(""));
		}	
	}
	
	@Test
	public void testInitializeConfig2(){
		Initializer init = new Initializer();
		File config = new File("conf/config2.txt");
		try {
			BoardState boardState = init.initialize(config);
		} catch (AppException | IOException e) {
			Assert.assertTrue("Exception Message matches", e.getMessage().equals("Cannot form a square"));
		}	
	}
	
	@Test()
	public void testInitializeConfig3(){
		Initializer init = new Initializer();
		File config = new File("conf/config3.txt");
		try {
			BoardState boardState = init.initialize(config);
		} catch (AppException | IOException e) {
			Assert.assertTrue("Exception Message matches", e.getMessage().equals("Cannot have multiple constructs in same square"));
		}	
	}
	
	@Test
	public void testInitializeConfig4(){
		Initializer init = new Initializer();
		File config = new File("conf/config4.txt");
		try {
			BoardState boardState = init.initialize(config);
		} catch (AppException | IOException e) {
			Assert.assertTrue("Exception Message matches", e.getMessage().equals("Cannot have multiple constructs in same square"));
		}	
	}
}
