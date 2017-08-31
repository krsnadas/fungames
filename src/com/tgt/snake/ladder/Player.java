package com.tgt.snake.ladder;

import java.util.Stack;

public class Player {

	private Integer id;
	
	public Integer getPositionStackatN(int lastDice) {
		return (positionStack.size()>lastDice ? positionStack.get(lastDice-1):null);
	}

	public void addToPositionStack(Integer value) {
		this.positionStack.push(value);
	}

	private Boolean isMagicTricked;
	
	private Integer energyLevel;
	
	private Stack<Integer> positionStack = new Stack<Integer>();
	
	public Integer getId() {
		return id;
	}

	public Boolean getIsMagicTricked() {
		return isMagicTricked;
	}

	public Integer getEnergyLevel() {
		return energyLevel;
	}

	public Position getCurrentPos() {
		return currentPos;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void decrementEnergy() {
		this.energyLevel = this.energyLevel - 1;
	}
	
	
	public void setIsMagicTricked(Boolean isMagicTricked) {
		this.isMagicTricked = isMagicTricked;
	}

	public void setEnergyLevel(Integer energyLevel) {
		this.energyLevel = energyLevel;
	}

	public void setCurrentPos(Position currentPos) {
		this.currentPos = currentPos;
	}

	private Position currentPos;

	public Player(Integer id, Integer energy,Position initialPos) {
		this.id = id;
		this.isMagicTricked = false;
		this.currentPos = initialPos;
		this.energyLevel = energy;
		this.addToPositionStack(initialPos.getPosition());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
