package com.tgt.snake.ladder;

public class Construct {

	private Integer id;
	
	private ConstructType type;
	
	private Integer startPos;
	
	public Construct(Integer id, ConstructType type, Integer startPos, Integer endPos, Integer hungerLevel) {
		this.id = id;
		this.type = type;
		this.startPos = startPos;
		this.endPos = endPos;
		this.hungerLevel = hungerLevel;
	}

	private Integer endPos;
	
	public Construct(Integer id, ConstructType type, Integer startPos, Integer endPosHunger) {
		this.id = id;
		this.type = type;
		this.startPos = startPos;
		if (ConstructType.PITSTOP.equals(type)){
			this.hungerLevel = endPosHunger;
		}
		else if(ConstructType.LADDER.equals(type)) {
			this.endPos = endPosHunger;			
		}
	}
	
	

	public Construct(Integer id, ConstructType type, Integer startPos) {
		this.id = id;
		this.type = type;
		this.startPos = startPos;
	}
	
	public Integer getId() {
		return id;
	}

	public void decreaseHunger() {
		this.hungerLevel = this.hungerLevel - 1;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public ConstructType getType() {
		return type;
	}

	public void setType(ConstructType type) {
		this.type = type;
	}

	public Integer getStartPos() {
		return startPos;
	}

	public void setStartPos(Integer startPos) {
		this.startPos = startPos;
	}

	public Integer getEndPos() {
		return endPos;
	}

	public void setEndPos(Integer endPos) {
		this.endPos = endPos;
	}

	public Integer getHungerLevel() {
		return hungerLevel;
	}

	public void setHungerLevel(Integer hungerLevel) {
		this.hungerLevel = hungerLevel;
	}

	private Integer hungerLevel;
}
