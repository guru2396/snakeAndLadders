package snakeAndLadder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class DriverClass {

	private static final Integer min=1;
	
	private static final Integer max=7;
	
	private static final Integer win=100;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file=new File("src/snakeAndLadder/input.txt");
		FileInputStream in=new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		int noofSnakes=Integer.parseInt(bufferedReader.readLine());
		SnakeService snakeService=SnakeService.getInstance();
		for(int i=0;i<noofSnakes;i++) {
			String snake=bufferedReader.readLine();
			String[] snakePos=snake.split(":");
			try {
				snakeService.addSnake(Integer.parseInt(snakePos[0]), Integer.parseInt(snakePos[1]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidSnakeException e) {
				System.out.print("\nSnake "+(i+1)+" not added due to exception:"+e.getMessage());
			}
		}
		int noOfLadders=Integer.parseInt(bufferedReader.readLine());
		LadderService ladderService=LadderService.getInstance();
		for(int i=0;i<noOfLadders;i++) {
			String ladder=bufferedReader.readLine();
			String[] ladderPos=ladder.split(":");
			try {
				ladderService.addLadder(Integer.parseInt(ladderPos[0]), Integer.parseInt(ladderPos[1]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidLadderException e) {
				// TODO Auto-generated catch block
				System.out.print("\nLadder "+(i+1)+" not added due to exception:"+e.getMessage());
			}
		}
		int noOfPlayers=Integer.parseInt(bufferedReader.readLine());
		PlayerService playerservice=PlayerService.getInstance();
		for(int i=0;i<noOfPlayers;i++) {
			String name=bufferedReader.readLine();
			playerservice.addPlayer(i+1, name);
		}
		
		boolean wins=false;
		int chance=1;
		while(!wins) {
			int die=ThreadLocalRandom.current().nextInt(min,max);
			int currPlayerPos=playerservice.getPlayerPos((chance%noOfPlayers)+1);
			String name=playerservice.getPlayerName((chance%noOfPlayers)+1);
			int newPos=currPlayerPos+die;
			if(newPos==win) {
				wins=true;
				System.out.print("\n"+name+" rolled a "+die+" and moved from "+currPlayerPos+" to "+newPos);
				System.out.print("\n"+name+" wins");
			}
			else if(newPos>win) {
				System.out.print("\n"+name+" rolled a "+die+" and moved from "+currPlayerPos+" to "+currPlayerPos);
			}
			else {
				int snakeAtpos=snakeService.findSnakeAtPos(newPos);
				int ladderAtpos=ladderService.findLadderAtPos(newPos);
				if(snakeAtpos!=-1) {
					newPos=snakeAtpos;
				}
				else if(ladderAtpos!=-1) {
					newPos=ladderAtpos;
					if(newPos==win) {
						wins=true;
						System.out.print("\n"+name+" rolled a "+die+" and moved from "+currPlayerPos+" to "+newPos);
						System.out.print("\n"+name+" wins");
						break;
					}
				}
				playerservice.updatePlayerPos((chance%noOfPlayers)+1, newPos);
				System.out.print("\n"+name+" rolled a "+die+" and moved from "+currPlayerPos+" to "+newPos);
			}
			chance++;
		}
		bufferedReader.close();

	}

}
