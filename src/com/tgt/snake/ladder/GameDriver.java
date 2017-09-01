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
		/**
		 * Game continues until n-1 players wins and so we can ascertain the order/rank of players.
		 */
		while(totalPlayerCnt > compPlayerCnt + 1)
		{
			/**
			 * Iterate over players move - excluding players who have reached winning spot.
			 */
			for (Player player : boardState.getPlayerStates()){
				Scanner scanner = new Scanner(System.in);
				//Exclude the winners by comparing the players current position with top most position.
				if(player.getCurrentPos().getPosition() != player.getCurrentPos().getSqrNum()*player.getCurrentPos().getSqrNum()){
					System.out.print("Enter player "+player.getId()+" move:");
					Integer dice = scanner.nextInt();
					//validate the dice outcome is between defined range (eg: 1 - 6)
					if (dice<1 || dice >6){
						System.out.print("Enter proper dice value:");
						System.out.print("Enter player "+player.getId()+" move:");
						dice = scanner.nextInt();
					}
					//Navigates the board and arrives at new board state
					boardState = nav.navigate(new PlayerMove(player,dice),boardState);
					/**
					 * prints the winner states and then rest of player states.
					 */
					boardState.getPlayerStates().stream().filter(p->p.getCurrentPos().getPosition() == 
						p.getCurrentPos().getSqrNum()*p.getCurrentPos().getSqrNum()).forEach(p->System.out.print("["+p.getId()+":W],"));
					boardState.getPlayerStates().stream().filter(p->p.getCurrentPos().getPosition() != 
						p.getCurrentPos().getSqrNum()*p.getCurrentPos().getSqrNum()).forEach(p->System.out.print("["+p.getId()+":"+p.getCurrentPos().getPosition()+":"+p.getEnergyLevel()+"], "));
					System.out.println();
					//calculates the winners count and if it reaches n-1 players, it ends the game.
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
