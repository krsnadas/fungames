package com.tgt.snake.ladder;

public class PlayerMove {

	private Player player;
	
	private Integer dice;

	public PlayerMove(Player player, Integer dice) {
		this.player = player;
		this.dice = dice;
	}

	public Player getPlayer() {
		return player;
	}

	public Integer getDice() {
		return dice;
	}

	
	
}
