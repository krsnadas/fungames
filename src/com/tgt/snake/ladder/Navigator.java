package com.tgt.snake.ladder;

import java.util.Stack;

public class Navigator {

	public BoardState navigate(PlayerMove move,BoardState boardState) throws AppException{
		Player player = move.getPlayer();
		if (player.getEnergyLevel()>0){
			Position currentPos = player.getCurrentPos();
			int result = currentPos.getPosition()+move.getDice();
			int finalResult = (Math.min(Math.max(result, 1), currentPos.getSqrNum()*currentPos.getSqrNum()));
			Position resPos = boardState.getPositionStates().stream().filter(p->p.getPosition()==finalResult).findFirst().get();
			Integer finalPosVal = processPositionAndPlayer
						(resPos,player,move.getDice(), boardState);
			Position finalPos  = boardState.getPositionStates().stream().filter(p->p.getPosition()==finalPosVal).findFirst().get();
			player.setCurrentPos(finalPos);
			player.addToPositionStack(finalPos.getPosition());			
		}
		else {
			Position initialPos  = boardState.getPositionStates().stream().filter(p->p.getPosition()==1).findFirst().get();
			player.setCurrentPos(initialPos);
			player.setEnergyLevel((initialPos.getSqrNum()*initialPos.getSqrNum())/3);
			player.addToPositionStack(initialPos.getPosition());
		}
		boardState.getMoveHistory().get(player.getId()).push(move.getDice());		
		return boardState;
	}


	private Integer processPositionAndPlayer(Position position, Player player,  Integer dice, BoardState boardState){		
		int result = -1;
		Stack<Integer> playerHistory = boardState.getMoveHistory().get(player.getId());
		if (position.getIsMagicSqr()){
	    	boolean toggle = !player.getIsMagicTricked();
	    	player.setIsMagicTricked(toggle);
	    }
	    if (position.getIsPitStop()){
	    	Integer energyLevel = player.getEnergyLevel()+position.getEnergyBoost();
	    	player.setEnergyLevel(energyLevel);
	    }
	    if (position.getIsMemory()){
	    	int lastDice = playerHistory.peek();
	    	if(null==player.getPositionStackatN(lastDice)){
	    		result = 1;
	    	}
	    	else {
	    		result = player.getPositionStackatN(lastDice);
	    	}
	    }
	    if (null!=position.getConstruct()){
	    	if (ConstructType.ELEVATOR.equals(position.getConstruct().getType())){
	    		if(player.getIsMagicTricked()){
	    			result = computePosition(position, dice, true,false);
	    		}
	    		else {
	    			result = computePosition(position, dice, true,true);
	    		}
	    	}
	    	else if (ConstructType.TRAMP.equals(position.getConstruct().getType())){
	    		if(player.getIsMagicTricked()){
	    			result = position.getPosition()-dice;
	    		}
	    		else {
	    			result = position.getPosition()+dice;
	    		}
	    	}
	    	else if (ConstructType.LADDER.equals(position.getConstruct().getType())){
	    		if (!player.getIsMagicTricked() && position.getPosition() == position.getConstruct().getStartPos()){	    				    			
	    			result = (boardState.getPlayerStates().stream().filter(p->p.getCurrentPos().getPosition()==position.getConstruct().getEndPos()).
	    					findAny().isPresent()) ? position.getPosition() : position.getConstruct().getEndPos();
	    		}
	    		else if (player.getIsMagicTricked() && position.getPosition() == position.getConstruct().getEndPos()){
	    			result = (boardState.getPlayerStates().stream().filter(p->p.getCurrentPos().getPosition()==position.getConstruct().getStartPos()).
	    					findAny().isPresent()) ? position.getPosition() : position.getConstruct().getStartPos();
	    		}
	    	}
	    	else if ((ConstructType.SNAKE.equals(position.getConstruct().getType()))){
	    		if (!player.getIsMagicTricked() && position.getPosition() == position.getConstruct().getEndPos() && position.getConstruct().getHungerLevel()!=0){
	    			result = position.getConstruct().getStartPos();
	    			position.getConstruct().decreaseHunger();
	    		}
	    		else if (player.getIsMagicTricked() && position.getPosition() == position.getConstruct().getStartPos() && position.getConstruct().getHungerLevel()!=0){
	    			result = position.getConstruct().getEndPos();
	    		}
	    	}	    	
	    }
    	player.decrementEnergy();
	    if (-1 == result){
	    	return (Math.min(Math.max(position.getPosition(), 1), position.getSqrNum()*position.getSqrNum()));
	    }
	    else {
			return (Math.min(Math.max(result, 1), position.getSqrNum()*position.getSqrNum()));
	    }    
	}
	
	private Integer computePosition(Position position,Integer count, Boolean isRow, Boolean increase){
		if(isRow){
			int rowVal = increase ? position.getRowIdx()+count : position.getRowIdx()-count;
			int rowLimitVal = (Math.min(Math.max(rowVal, 1), position.getSqrNum()));
			return (rowLimitVal-1)*position.getSqrNum() + position.getColIdx();
		}
		else {
			int colVal = increase ? position.getColIdx()+count : position.getColIdx()-count;
			int colLimitVal = (Math.min(Math.max(colVal, 1), position.getSqrNum()));
			return (position.getRowIdx()-1)*position.getSqrNum() + colLimitVal;
		}
	}
	
   
	
}