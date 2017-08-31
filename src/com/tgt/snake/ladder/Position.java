package com.tgt.snake.ladder;

public class Position {
	
	public Integer getPosition() {
		return position;
	}
	
	public Integer getRowIdx() {
		return rowIdx;
	}

	public Integer getColIdx() {
		return colIdx;
	}
	
	private Integer rowIdx;
	
	private Integer colIdx;
	
	private Integer sqrNum;

	private Integer position;
	
	private Boolean isMagicSqr;
	
	public Position(Integer position) {
		this.position = position;
		this.isMagicSqr = false;
		this.isMemory = false;
		this.isPitStop = false;
		this.energyBoost = Integer.valueOf(0);
	}

	public Position(Integer position,Integer sqrNum,Integer rowIdx,Integer colIdx) {
		this.position = position;
		this.sqrNum = sqrNum;
		this.isMagicSqr = false;
		this.isMemory = false;
		this.isPitStop = false;
		this.rowIdx = rowIdx;
		this.colIdx = colIdx;
		this.energyBoost = Integer.valueOf(0);
	}
	
	public Integer getSqrNum() {
		return sqrNum;
	}

	public void setSqrNum(Integer sqrNum) {
		this.sqrNum = sqrNum;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public void setIsMagicSqr(Boolean isMagicSqr) {
		this.isMagicSqr = isMagicSqr;
	}

	public void setIsPitStop(Boolean isPitStop) {
		this.isPitStop = isPitStop;
	}

	public void setIsMemory(Boolean isMemory) {
		this.isMemory = isMemory;
	}

	public void setEnergyBoost(Integer energyBoost) {
		this.energyBoost = energyBoost;
	}

	public void setConstruct(Construct construct) {
		this.construct = construct;
	}

	public Boolean getIsMagicSqr() {
		return isMagicSqr;
	}

	public Boolean getIsPitStop() {
		return isPitStop;
	}

	public Boolean getIsMemory() {
		return isMemory;
	}

	public Integer getEnergyBoost() {
		return energyBoost;
	}

	public Construct getConstruct() {
		return construct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		Position other = (Position) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	private Boolean isPitStop;
	
	private Boolean isMemory;
	
	private Integer energyBoost;
	
	private Construct construct;
	
}
