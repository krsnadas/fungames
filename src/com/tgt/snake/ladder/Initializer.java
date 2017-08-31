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
	
	public BoardState initialize(File file) throws AppException, IOException{
		
		List<String> configContent = new ArrayList<String>();

		configContent = FileUtils.readLines(file);

		String sqrCount = StringUtils.arraySplit(configContent.get(0), '(', true)[0];
		Integer sqrBase = (int) Math.floor(Math.sqrt(Integer.valueOf(sqrCount)));
		if ((sqrBase*sqrBase) != Integer.valueOf(sqrCount) ){
			throw new AppException("Cannot form a square");
		}
		String playerCount = StringUtils.arraySplit(configContent.get(1), '(', true)[0];	
		String [] confList = Arrays.copyOfRange(configContent.toArray(new String[0]), 2, configContent.size());
		int count = 0;

		for (String item : confList){
			System.out.println(item);
			String [] itemList = StringUtils.arraySplit(StringUtils.arraySplit(item, '(', true)[0], ' ', true);
			if (itemList.length == 4){
				Construct construct = new Construct(count++, ConstructType.fromString(itemList[0]),Integer.valueOf(itemList[1]),
						Integer.valueOf(itemList[2]),Integer.valueOf(itemList[3]));
				checkNullAndAdd(construct);				
			}
			else if (itemList.length == 3)
			{
				Construct construct = new Construct(count++, ConstructType.fromString(itemList[0]),Integer.valueOf(itemList[1]),
						Integer.valueOf(itemList[2]));				
				checkNullAndAdd(construct);
			}
			else {
				Construct construct = new Construct(count++, ConstructType.fromString(itemList[0]),Integer.valueOf(itemList[1]));
				checkNullAndAdd(construct);
			}
		}
		/**
		 * Initialize the position
		 */
		Set<Position> positionSet = initPosition(Integer.valueOf(sqrCount));		
		/**
		 * Initialize players
		 */
		Set<Player> playerSet = initPlayers(Integer.valueOf(playerCount),positionSet);	
		BoardState boardState = new BoardState(positionSet,playerSet,constructMap,conPositionMap);
		boardState.setMoveHistory(moveHistory);
		return boardState;
	}
	
	private Set<Player> initPlayers(Integer playerCnt, Set<Position> positionSet){
		Set<Player> playerSet = new HashSet<Player>();
		Position initialPos  = positionSet.stream().filter(p->p.getPosition()==1).findFirst().get();
		for (int i =1;i<=playerCnt;i++){
			playerSet.add(new Player(i,(initialPos.getSqrNum()*initialPos.getSqrNum())/3,initialPos));
			Stack<Integer> stackInt = new Stack<Integer>();
			stackInt.push(Integer.valueOf(1));
			moveHistory.put(Integer.valueOf(i), stackInt);
		}
		return playerSet;
	}
	
	private Set<Position> initPosition(Integer sqrCount){
		Set<Position> posSet = new HashSet<Position>();
		int sqrNum = (int) Math.sqrt(sqrCount);
		for (int i = 0;i<sqrCount;i++){
			Position pos = new Position(i+1,sqrNum,((i/sqrNum)+1),((i%sqrNum)+1));			
			Set<Construct> constructSet = conPositionMap.get(i+1);
			if (null!=constructSet && !constructSet.isEmpty()){
			for (Construct con : constructSet){
				if(con.getType().equals(ConstructType.MAGIC)){
					pos.setIsMagicSqr(true);
				}
				else if(con.getType().equals(ConstructType.MEMORY)){
					pos.setIsMemory(true);
				}
				else if(con.getType().equals(ConstructType.PITSTOP)){
					pos.setIsPitStop(true);
					pos.setEnergyBoost(con.getHungerLevel());
				}
				else {
					pos.setConstruct(con);
				}
			 }
			}
			posSet.add(pos);
		}
		return posSet;
	}
	
	private void checkNullAndAdd(Construct construct){
		if (null!=construct.getEndPos() && null==conPositionMap.get(construct.getEndPos())){
			Set<Construct> constructSet = new HashSet<Construct>();
			constructSet.add(construct);
			conPositionMap.put(construct.getEndPos(), constructSet);
		}
		else if (null!=construct.getEndPos()){
			conPositionMap.get(construct.getEndPos()).add(construct);
		}
		if (null!=construct.getStartPos() && null==conPositionMap.get(construct.getStartPos())){
			Set<Construct> constructSet = new HashSet<Construct>();
			constructSet.add(construct);
			conPositionMap.put(construct.getStartPos(), constructSet);
		}
		else if(null!=construct.getStartPos()) {
			conPositionMap.get(construct.getStartPos()).add(construct);
		}
		if(null==constructMap.get(construct.getType()) ){
			Set<Construct> constructSet = new HashSet<Construct>();
			constructSet.add(construct);
			constructMap.put(construct.getType(), constructSet);
		}
		else {
			constructMap.get(construct.getType()).add(construct);
		}
	}
	
}
