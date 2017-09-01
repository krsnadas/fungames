package com.tgt.snake.ladder;

import java.util.Stack;

public class Navigator {
	/**
	 * Navigate as per dice outcome and board rules. Returns the board state which follows the FSD.
	 * @param move
	 * @param boardState
	 * @return
	 * @throws AppException
	 */
	public BoardState navigate(PlayerMove move,BoardState boardState) throws AppException{
		Player player = move.getPlayer();
		/**
		 * dice outcome history
		 */
		boardState.getMoveHistory().get(player.getId()).push(move.getDice());	
		/**
		 * only when energy level is greater than zero
		 */
		if (player.getEnergyLevel()>0){
			Position currentPos = player.getCurrentPos();
			int result = currentPos.getPosition()+move.getDice();
			int finalResult = (Math.min(Math.max(result, 1), currentPos.getSqrNum()*currentPos.getSqrNum()));
			Position resPos = boardState.getPositionStates().stream().filter(p->p.getPosition()==finalResult).findFirst().get();
			Integer finalPosVal = processPositionAndPlayer
						(resPos,player,move.getDice(), boardState);
			Position finalPos  = boardState.getPositionStates().stream().filter(p->p.getPosition()==finalPosVal).findFirst().get();
			player.setCurrentPos(finalPos);
			/**
			 * player - position history
			 */
			player.addToPositionStack(finalPos.getPosition());			
		}
		else {
			Position initialPos  = boardState.getPositionStates().stream().filter(p->p.getPosition()==1).findFirst().get();
			player.setCurrentPos(initialPos);
			player.setEnergyLevel((initialPos.getSqrNum()*initialPos.getSqrNum())/3);
			/**
			 * player - position history
			 */
			player.addToPositionStack(initialPos.getPosition());
		}	
		return boardState;
	}

	/**
	 * Process the player and position based on board rules following the dice outcome.
	 * @param position
	 * @param player
	 * @param dice
	 * @param boardState
	 * @return
	 */
	private Integer processPositionAndPlayer(Position position, Player player,  Integer dice, BoardState boardState){		
		int result = -1;
	    ConstructExecutor conExecutor = new ConstructExecutor();
		if (position.getIsMagicSqr()){
	    	boolean toggle = !player.getIsMagicTricked();
	    	player.setIsMagicTricked(toggle);
	    }
	    if (position.getIsPitStop()){
	    	Integer energyLevel = player.getEnergyLevel()+position.getEnergyBoost();
	    	player.setEnergyLevel(energyLevel);
	    }
	    if (position.getIsMemory()){
	    	result = conExecutor.executeMemory(position, player, boardState);
	    }
	    if (null!=position.getConstruct()){
	    	if (ConstructType.ELEVATOR.equals(position.getConstruct().getType())){
	    		result = conExecutor.executeElevator(position, player, dice);
	    	}
	    	else if (ConstructType.TRAMP.equals(position.getConstruct().getType())){
	    		result = conExecutor.executeTramp(position, player, dice);
	    	}
	    	else if (ConstructType.LADDER.equals(position.getConstruct().getType())){
	    		result = conExecutor.executeLadder(position, player, boardState);
	    	}
	    	else if ((ConstructType.SNAKE.equals(position.getConstruct().getType()))){
	    		result = conExecutor.executeSnake(position, player, boardState);
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
	
   
	
}