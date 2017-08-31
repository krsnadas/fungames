package com.tgt.snake.ladder;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameDriver {

	public static void main(String[] args) throws AppException, IOException {
		File config = new File("conf/config1.txt");
		Initializer init = new Initializer();
		BoardState boardState = init.initialize(config);	
		/**
		 * Board - State 0
		 */		
		boardState.getPlayerStates().forEach(p->System.out.print("["+p.getId()+":"+p.getCurrentPos().getPosition()+":"+p.getEnergyLevel()+"], "));
		System.out.println();
		/**
		 * Game Beginsx
		 */
		Navigator nav = new Navigator();
		
		long totalPlayerCnt = boardState.getPlayerStates().size();
		long compPlayerCnt = boardState.getPlayerStates().stream().filter(p->p.getCurrentPos().getPosition() == 
				p.getCurrentPos().getSqrNum()*p.getCurrentPos().getSqrNum()).count();
		while(totalPlayerCnt > compPlayerCnt + 1)
		{
			for (Player player : boardState.getPlayerStates()){
				Scanner scanner = new Scanner(System.in);
				if(player.getCurrentPos().getPosition() != player.getCurrentPos().getSqrNum()*player.getCurrentPos().getSqrNum()){
					System.out.print("Enter player "+player.getId()+" move:");
					Integer dice = scanner.nextInt();
					if (dice<1 || dice >6){
						System.out.print("Enter proper dice value:");
						System.out.print("Enter player "+player.getId()+" move:");
						dice = scanner.nextInt();
					}
					boardState = nav.navigate(new PlayerMove(player,dice),boardState);
					boardState.getPlayerStates().stream().filter(p->p.getCurrentPos().getPosition() == 
						p.getCurrentPos().getSqrNum()*p.getCurrentPos().getSqrNum()).forEach(p->System.out.print("["+p.getId()+":W],"));
					boardState.getPlayerStates().stream().filter(p->p.getCurrentPos().getPosition() != 
						p.getCurrentPos().getSqrNum()*p.getCurrentPos().getSqrNum()).forEach(p->System.out.print("["+p.getId()+":"+p.getCurrentPos().getPosition()+":"+p.getEnergyLevel()+"], "));
					System.out.println();
					compPlayerCnt = boardState.getPlayerStates().stream().filter(p->p.getCurrentPos().getPosition() == 
							p.getCurrentPos().getSqrNum()*p.getCurrentPos().getSqrNum()).count();
					if(totalPlayerCnt <= compPlayerCnt+1){
						break;
					}
				}
			}
			
		}		
	}
}
