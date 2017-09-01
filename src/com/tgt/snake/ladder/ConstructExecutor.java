package com.tgt.snake.ladder;

import java.util.Stack;

public class ConstructExecutor {
	
	/**
	 * Compute the vertical/horizontal climb  up and down based magic turned on/off.
	 * 
	 * @param position
	 * @param count
	 * @param isRow
	 * @param increase
	 * @return
	 */
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
	
	/**
	 *  Executes snake construct rule.
	 * @param position
	 * @param player
	 * @param boardState
	 * @return
	 */
	public Integer executeSnake(Position position,Player player,BoardState boardState){
		int result = -1;
		if (!player.getIsMagicTricked() && position.getPosition() == position.getConstruct().getEndPos() && position.getConstruct().getHungerLevel()!=0){
			result = position.getConstruct().getStartPos();
			position.getConstruct().decreaseHunger();
		}
		else if (player.getIsMagicTricked() && position.getPosition() == position.getConstruct().getStartPos() && position.getConstruct().getHungerLevel()!=0){
			result = position.getConstruct().getEndPos();
		}
		return result;
	}
	/**
	 * Executes snake ladder rule.
	 * @param position
	 * @param player
	 * @param boardState
	 * @return
	 */
	public Integer executeLadder(Position position,Player player,BoardState boardState){
		int result = -1;
		if (!player.getIsMagicTricked() && position.getPosition() == position.getConstruct().getStartPos()){	    				    			
			result = (boardState.getPlayerStates().stream().filter(p->p.getCurrentPos().getPosition()==position.getConstruct().getEndPos()).
					findAny().isPresent()) ? position.getPosition() : position.getConstruct().getEndPos();
		}
		else if (player.getIsMagicTricked() && position.getPosition() == position.getConstruct().getEndPos()){
			result = (boardState.getPlayerStates().stream().filter(p->p.getCurrentPos().getPosition()==position.getConstruct().getStartPos()).
					findAny().isPresent()) ? position.getPosition() : position.getConstruct().getStartPos();
		}
		return result;
	}
	
	/**
	 * Executes  elevator rule.
	 * @param position
	 * @param player
	 * @param dice
	 * @return
	 */
	public Integer executeElevator(Position position,Player player,Integer dice){
		int result = -1;
    		if(player.getIsMagicTricked()){
    			result = computePosition(position, dice, true,false);
    		}
    		else {
    			result = computePosition(position, dice, true,true);
    		}
		return result;
	}
	/**
	 * Executes tramp rule.
	 * @param position
	 * @param player
	 * @param dice
	 * @return
	 */
	public Integer executeTramp(Position position,Player player,Integer dice){
		int result = -1;
		if(player.getIsMagicTricked()){
			result = position.getPosition()-dice;
		}
		else {
			result = position.getPosition()+dice;
		}
		return result;
	}
	/**
	 * Executes memory construct rule.
	 * @param position
	 * @param player
	 * @return
	 */
	public Integer executeMemory(Position position,Player player,BoardState boardState){
		Stack<Integer> playerHistory = boardState.getMoveHistory().get(player.getId());
		int result = -1;
		int lastDice = playerHistory.peek();
    	if(null==player.getPositionStackatN(lastDice)){
    		result = 1;
    	}
    	else {
    		result = player.getPositionStackatN(lastDice);
    	}
		return result;
	}
}
