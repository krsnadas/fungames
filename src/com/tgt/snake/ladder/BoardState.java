package com.tgt.snake.ladder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class BoardState {

	private Set<Position> positionStates;
	
	private Map<Integer,Stack<Integer>> moveHistory = new LinkedHashMap<Integer,Stack<Integer>>();
	
	public BoardState(Set<Position> positionStates, Set<Player> playerStates,
			Map<ConstructType, Set<Construct>> constructMap, Map<Integer, Set<Construct>> conPositionMap) {
		this.positionStates = positionStates;
		this.playerStates = playerStates;
		this.constructMap = constructMap;
		this.conPositionMap = conPositionMap;
		
	}

	public Set<Position> getPositionStates() {
		return positionStates;
	}

	public void setPositionStates(Set<Position> positionStates) {
		this.positionStates = positionStates;
	}

	public Set<Player> getPlayerStates() {
		return playerStates;
	}

	public Map<Integer, Stack<Integer>> getMoveHistory() {
		return moveHistory;
	}

	public void setMoveHistory(Map<Integer, Stack<Integer>> moveHistory) {
		this.moveHistory = moveHistory;
	}

	public void setPlayerStates(Set<Player> playerStates) {
		this.playerStates = playerStates;
	}

	public Map<ConstructType, Set<Construct>> getConstructMap() {
		return constructMap;
	}

	public void setConstructMap(Map<ConstructType, Set<Construct>> constructMap) {
		this.constructMap = constructMap;
	}

	public Map<Integer, Set<Construct>> getConPositionMap() {
		return conPositionMap;
	}

	public void setConPositionMap(Map<Integer, Set<Construct>> conPositionMap) {
		this.conPositionMap = conPositionMap;
	}

	private Set<Player> playerStates;
	
	Map<ConstructType,Set<Construct>> constructMap = new HashMap<ConstructType,Set<Construct>>();
	
	Map<Integer,Set<Construct>> conPositionMap = new HashMap<Integer,Set<Construct>>();
	
 }
