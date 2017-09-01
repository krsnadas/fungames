package com.tgt.snake.ladder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.io.FileUtils;
import org.h2.util.StringUtils;

public class Initializer {
	
	Map<ConstructType,Set<Construct>> constructMap = new HashMap<ConstructType,Set<Construct>>();	
	Map<Integer,Set<Construct>> conPositionMap = new HashMap<Integer,Set<Construct>>();
	Map<Integer,Stack<Integer>> moveHistory = new HashMap<Integer,Stack<Integer>>();
	ResourceFactory resourceFact = new ResourceFactory();
	
	public BoardState initialize(File file) throws AppException, IOException{
		
		List<String> configContent = new ArrayList<String>();
		configContent = FileUtils.readLines(file);
		String sqrCount = StringUtils.arraySplit(configContent.get(0), '(', true)[0];
		Validator validator = new Validator();
		Integer parsedSqrCnt = validator.validateBoard(sqrCount);
		
		String playerCount = StringUtils.arraySplit(configContent.get(1), '(', true)[0];	
		String [] confList = Arrays.copyOfRange(configContent.toArray(new String[0]), 2, configContent.size());
		int count = 0;
		/**
		 * Initialize construct
		 */
		for (String item : confList){
			String [] itemList = StringUtils.arraySplit(StringUtils.arraySplit(item, '(', true)[0], ' ', true);
			Construct construct = resourceFact.getConstruct(itemList, count);
			validator.checkNullAndAdd(construct,constructMap,conPositionMap);	
		}
		/**
		 * Initialize the position
		 */
		Set<Position> positionSet = initPosition(parsedSqrCnt);		
		/**
		 * Initialize players
		 */
		Set<Player> playerSet = initPlayers(Integer.valueOf(playerCount),positionSet);	
		BoardState boardState = new BoardState(positionSet,playerSet,constructMap,conPositionMap);
		boardState.setMoveHistory(moveHistory);
		return boardState;
	}
	
	public Initializer() {
		this.constructMap = new HashMap<ConstructType,Set<Construct>>();
		this.conPositionMap = new HashMap<Integer,Set<Construct>>();
		this.moveHistory = new HashMap<Integer,Stack<Integer>>();
		this.resourceFact = new ResourceFactory();;
	}

	private Set<Player> initPlayers(Integer playerCnt, Set<Position> positionSet){
		Set<Player> playerSet = new HashSet<Player>();
		
		Position initialPos  = positionSet.stream().filter(p->p.getPosition()==1).findFirst().get();
		for (int i =1;i<=playerCnt;i++){
			Player player = resourceFact.getPlayer(i,initialPos);
			playerSet.add(player);
			Stack<Integer> stackInt = new Stack<Integer>();
			stackInt.push(Integer.valueOf(1));
			moveHistory.put(player.getId(), stackInt);
		}
		return playerSet;
	}
	
	private Set<Position> initPosition(Integer sqrCount) throws AppException
	{
		Set<Position> posSet = new HashSet<Position>();
		int sqrNum = (int) Math.sqrt(sqrCount);
		for (int i = 0;i<sqrCount;i++){
			Position pos = resourceFact.getPosition(i, sqrNum,conPositionMap);
			posSet.add(pos);
		 }					
		return posSet;
	}
	
	
	
}
