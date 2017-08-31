package com.sdlad.test;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.tgt.snake.ladder.AppException;
import com.tgt.snake.ladder.BoardState;
import com.tgt.snake.ladder.Initializer;
import com.tgt.snake.ladder.Navigator;
import com.tgt.snake.ladder.Player;
import com.tgt.snake.ladder.PlayerMove;
import com.tgt.snake.ladder.Position;

public class GameSimTest {
	
	@Test
	public void testElevator() {
		Initializer init = new Initializer();
		File config = new File("conf/config1.txt");
		BoardState boardState = null;
		try {
			boardState = init.initialize(config);
		} catch (AppException | IOException e) {
		}
		//
		Navigator nav = new Navigator();
		Player player = boardState.getPlayerStates().stream().filter(p->p.getId()==1).findFirst().get();
		Position position = boardState.getPositionStates().stream().filter(p->p.getPosition()==11).findFirst().get();
		player.setCurrentPos(position);
		try {
			boardState = nav.navigate(new PlayerMove(player,3),boardState);
		} catch (AppException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("Position and Energy Match", player.getCurrentPos().getPosition().equals(24));
	}
		
	@Test
	public void testTrampoline(){
		Initializer init = new Initializer();
		File config = new File("conf/config1.txt");
		BoardState boardState = null;
		try {
			boardState = init.initialize(config);
		} catch (AppException | IOException e) {
		}
		//
		Navigator nav = new Navigator();
		Player player = boardState.getPlayerStates().stream().filter(p->p.getId()==1).findFirst().get();
		Position position = boardState.getPositionStates().stream().filter(p->p.getPosition()==11).findFirst().get();
		player.setCurrentPos(position);
		try {
			boardState = nav.navigate(new PlayerMove(player,1),boardState);
		} catch (AppException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("Position and Energy Match", player.getCurrentPos().getPosition().equals(13));
	}
	
	@Test
	public void testMagicSquare(){
		Initializer init = new Initializer();
		File config = new File("conf/config1.txt");
		BoardState boardState = null;
		try {
			 boardState = init.initialize(config);
		} catch (AppException | IOException e) {
		}
		//
		Navigator nav = new Navigator();
		Player player = boardState.getPlayerStates().stream().filter(p->p.getId()==1).findFirst().get();
		Position position = boardState.getPositionStates().stream().filter(p->p.getPosition()==9).findFirst().get();
		player.setCurrentPos(position);
		try {
			boardState = nav.navigate(new PlayerMove(player,1),boardState);
		} catch (AppException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("Position and Energy Match", player.getCurrentPos().getPosition().equals(10));
		try {
			boardState = nav.navigate(new PlayerMove(player,4),boardState);
		} catch (AppException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("Position and Energy Match", player.getCurrentPos().getPosition().equals(4));
	}
	
	@Test
	public void testPitStop(){
		Initializer init = new Initializer();
		File config = new File("conf/config1.txt");
		BoardState boardState = null;
		try {
			boardState = init.initialize(config);
		} catch (AppException | IOException e) {
		}
		//
		Navigator nav = new Navigator();
		Player player = boardState.getPlayerStates().stream().filter(p->p.getId()==1).findFirst().get();
		Position position = boardState.getPositionStates().stream().filter(p->p.getPosition()==11).findFirst().get();
		player.setCurrentPos(position);
		try {
			boardState = nav.navigate(new PlayerMove(player,5),boardState);
		} catch (AppException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("Position and Energy Match", player.getEnergyLevel().equals(12));
	}
	
	@Test
	public void testSnake(){
		Initializer init = new Initializer();
		File config = new File("conf/config1.txt");
		BoardState boardState = null;
		try {
			boardState = init.initialize(config);
		} catch (AppException | IOException e) {
		}
		//
		Navigator nav = new Navigator();
		Player player = boardState.getPlayerStates().stream().filter(p->p.getId()==1).findFirst().get();
		Position position = boardState.getPositionStates().stream().filter(p->p.getPosition()==6).findFirst().get();
		player.setCurrentPos(position);
		try {
			boardState = nav.navigate(new PlayerMove(player,2),boardState);
		} catch (AppException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("Position and Energy Match", player.getCurrentPos().getPosition().equals(2));
	}
	
	@Test
	public void testLadder(){
		Initializer init = new Initializer();
		File config = new File("conf/config1.txt");
		BoardState boardState = null;
		try {
			boardState = init.initialize(config);
		} catch (AppException | IOException e) {
		}
		//
		Navigator nav = new Navigator();
		Player player = boardState.getPlayerStates().stream().filter(p->p.getId()==1).findFirst().get();
		Position position = boardState.getPositionStates().stream().filter(p->p.getPosition()==1).findFirst().get();
		player.setCurrentPos(position);
		try {
			boardState = nav.navigate(new PlayerMove(player,3),boardState);
		} catch (AppException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("Position and Energy Match", player.getCurrentPos().getPosition().equals(9));
	}
}
