package com.tgt.snake.ladder;

import java.util.Map;
import java.util.Set;

public class ResourceFactory {
	
	public Construct getConstruct(String [] itemList,int count){
		Construct construct = null;
		if (itemList.length == 4){
			construct = new Construct(count++, ConstructType.fromString(itemList[0]),Integer.valueOf(itemList[1]),
					Integer.valueOf(itemList[2]),Integer.valueOf(itemList[3]));			
		}
		//LADDER and PITSTOP scenario
		else if (itemList.length == 3)
		{
			 construct = new Construct(count++, ConstructType.fromString(itemList[0]),Integer.valueOf(itemList[1]),
					Integer.valueOf(itemList[2]));				
		}
		//Memory, Magic, Elevator and Trampoline scenarios
		else {
			 construct = new Construct(count++, ConstructType.fromString(itemList[0]),Integer.valueOf(itemList[1]));
		}
		return construct;
	}

	public Position getPosition(int i,int sqrNum,Map<Integer,Set<Construct>> conPositionMap) throws AppException{

	  Position pos = new Position(i+1,sqrNum,((i/sqrNum)+1),((i%sqrNum)+1));			
	  Set<Construct> constructSet = conPositionMap.get(i+1);
	  if (null!=constructSet && !constructSet.isEmpty()){
		for (Construct con : constructSet)
		  {
			if(ConstructType.MAGIC.equals(con.getType())){
				pos.setIsMagicSqr(true);
			}
			else if(ConstructType.MEMORY.equals(con.getType())){
				pos.setIsMemory(true);
			}
			else if(ConstructType.PITSTOP.equals(con.getType())){
				pos.setIsPitStop(true);
				pos.setEnergyBoost(con.getHungerLevel());
			}
			else {
				pos.setConstruct(con);
			}
		 }
		}
	return pos;
	}
	
	Player getPlayer(int i, Position initialPos){
		Player player = new Player(i,(initialPos.getSqrNum()*initialPos.getSqrNum())/3,initialPos);
		return player;
	}
}
