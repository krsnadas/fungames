package com.sdlad.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.tgt.snake.ladder.AppException;
import com.tgt.snake.ladder.BoardState;
import com.tgt.snake.ladder.Initializer;

public class InitializerTest {
	
	@Test
	public void testInitializeConfig1(){
		Initializer init = new Initializer();
		File config = new File("conf/config1.txt");
		try {
			BoardState boardState = init.initialize(config);
			System.out.println(boardState.getPositionStates().size());
		} catch (AppException | IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testInitializeConfig2(){
		Initializer init = new Initializer();
		File config = new File("conf/config2.txt");
		try {
			BoardState boardState = init.initialize(config);
		} catch (AppException | IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testInitializeConfig3(){
		Initializer init = new Initializer();
		File config = new File("conf/config3.txt");
		try {
			BoardState boardState = init.initialize(config);
		} catch (AppException | IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testInitializeConfig4(){
		Initializer init = new Initializer();
		File config = new File("conf/config4.txt");
		try {
			BoardState boardState = init.initialize(config);
		} catch (AppException | IOException e) {
			e.printStackTrace();
		}	
	}
}
