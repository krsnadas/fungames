package com.tgt.snake.ladder;

public enum ConstructType {
	
	SNAKE("S"),
	
	LADDER("L"),
	
	TRAMP("T"),
	
	ELEVATOR("E"),
	
	MEMORY("ME"),
	
	MAGIC("MA"),
	
	PITSTOP("P");

	private String value;

	public String getValue() {
		return value;
	}

	private ConstructType(String value) {
		this.value = value;
	}

	public static ConstructType fromString(String value){
		for (ConstructType construct : ConstructType.values()){
			if (value.equalsIgnoreCase(construct.getValue())){
				return construct;
			}
		}
		return null;
	}
	
	
	
}
