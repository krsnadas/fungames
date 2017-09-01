package com.tgt.snake.ladder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Validator {

	public Integer validateBoard(String sqrCount) throws AppException{
		if(null!=sqrCount && !sqrCount.isEmpty() && Integer.valueOf(sqrCount)>0){
			Integer sqrBase = (int) Math.floor(Math.sqrt(Integer.valueOf(sqrCount)));
			if ((sqrBase*sqrBase) != Integer.valueOf(sqrCount) ){
				throw new AppException("Cannot form a square");
			}
			else {
				return Integer.valueOf(sqrCount);
			}
		}
		else {
			return null;
		}
	}
	
	
	public void checkNullAndAdd(Construct construct,Map<ConstructType,Set<Construct>> constructMap,Map<Integer,Set<Construct>> conPositionMap){
		if (null!=construct.getEndPos() && null==conPositionMap.get(construct.getEndPos())){
			Set<Construct> constructSet = new HashSet<Construct>();
			constructSet.add(construct);
			conPositionMap.put(construct.getEndPos(), constructSet);
		}
		else if (null!=construct.getEndPos()){
			conPositionMap.get(construct.getEndPos()).add(construct);
		}
		if (null!=construct.getStartPos() && null==conPositionMap.get(construct.getStartPos())){
			Set<Construct> constructSet = new HashSet<Construct>();
			constructSet.add(construct);
			conPositionMap.put(construct.getStartPos(), constructSet);
		}
		else if(null!=construct.getStartPos()) {
			conPositionMap.get(construct.getStartPos()).add(construct);
		}
		if(null==constructMap.get(construct.getType()) ){
			Set<Construct> constructSet = new HashSet<Construct>();
			constructSet.add(construct);
			constructMap.put(construct.getType(), constructSet);
		}
		else {
			constructMap.get(construct.getType()).add(construct);
		}
	}
	
}
