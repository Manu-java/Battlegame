package com.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.assignment.models.BattleShip;
import com.assignment.models.Player;
import com.assignment.utility.BattleFieldUtility;

public class BattleGame {
	
	private int[][] groundArea;
	private boolean gameOver;
	
	public static void main(String s[]){
		BattleGame game = new BattleGame();
		Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
		game.readInputTxt(player1, player2);
		game.start(player1, player2, game);
	}
	
	public void readInputTxt(Player player1, Player player2){
		try {
            FileReader reader = new FileReader("C://BattleInput.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            BattleShip battleShip1 = null;
            BattleShip battleShip2 = null;
            List<String> locations = null;
            
            String line;
            int lineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(":");
                String[] values = arr[1].trim().split(" ");
                
                switch(lineNumber){
                	case 1: 
                			System.out.println("Ground area:"+values[0]+", "+values[1]);
                			Integer row = Integer.parseInt(values[0]);
                			char c = values[1].charAt(0);
                			Integer col = (int)c - 64;
                			groundArea = new int[row][col];
                			
                			break;
                	case 2: 
                			System.out.println("Ship type - "+values[0]);
                			battleShip1 = new BattleShip();
                			battleShip1.setType(values[0]);
                			break;
                	case 3: 
                			System.out.println("Ship dimension - "+values[0]+"-"+values[1]);
                			
                			int value0 = Integer.parseInt(values[0]);
                			int value1 = Integer.parseInt(values[1]);
                			battleShip1.setHorizontalDimension(value0);
                			battleShip1.setVerticalDimension(value1);
                			
                			break;
                	case 4: 
                			System.out.println("Location of ship1 for player A - "+values[0]);
                			player1.addShip(values[0], battleShip1);
                			locations = BattleFieldUtility.addDimensionToLocation(values[0], battleShip1.getHorizontalDimension(), battleShip1.getVerticalDimension());
                			for(String loc:locations){
                				player1.addShip(loc, battleShip1);
                			}
                			break;
                	case 5: 
                			System.out.println("Location of ship1 for player B - "+values[0]);
                			player2.addShip(values[0], battleShip1);
                			
            				locations = BattleFieldUtility.addDimensionToLocation(values[0], battleShip1.getHorizontalDimension(), battleShip1.getVerticalDimension());
                			for(String loc:locations){
                				player2.addShip(loc, battleShip1);
                			}
                        	
                			//ship.setLocation(values[0]);
                			break;
                	case 6: 
                			System.out.println("Ship type - "+values[0]);
                			battleShip2 = new BattleShip();
                			battleShip2.setType(values[0]);
                			break;
                	case 7: 
                			System.out.println("Ship dimension - "+values[0]+"-"+values[1]);
                			value0 = Integer.parseInt(values[0]);
                			value1 = Integer.parseInt(values[1]);
                			battleShip2.setHorizontalDimension(value0);
                			battleShip2.setVerticalDimension(value1);
                			
                			break;
                	case 8: 
                			System.out.println("Location of ship2 for player A - "+values[0]);
                			player1.addShip(values[0], battleShip2);
                			locations = BattleFieldUtility.addDimensionToLocation(values[0], battleShip2.getHorizontalDimension(), battleShip2.getVerticalDimension());
                			for(String loc:locations){
                				player1.addShip(loc, battleShip2);
                			}
                			break;
                	case 9: 
                			System.out.println("Location of ship2 for player B - "+values[0]);
                			player2.addShip(values[0], battleShip2);
                			locations = BattleFieldUtility.addDimensionToLocation(values[0], battleShip2.getHorizontalDimension(), battleShip2.getVerticalDimension());
                			for(String loc:locations){
                				player2.addShip(loc, battleShip2);
                			}
                			break;
                	case 10:
                			System.out.println("Missile targets for player A - "+values[0]+","+values[1]+","+values[2]+","+values[3]);
                			
                			for(String val:values){
                				if(val.length() > 0 && !val.equals(" ")){
                					player1.addMissileTarget(val);
                				}
                			}
                			break;
                	case 11:

                			for(String val:values){
                				if(val.length() > 0 && !val.equals(" ")){
                					player2.addMissileTarget(val);
                				}
                			}
                			break;
                }
                lineNumber++;
            }
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void start(Player player1, Player player2, BattleGame game){
		if(!game.isGameOver()){
			if(!player1.getMissileTargets().isEmpty()){
				Iterator iter = player1.getMissileTargets().iterator();
				String value = "";
				for(String val:player1.getMissileTargets()){
					value = val;
					player1.getMissileTargets().remove(val);
					break;
				}
				if(!value.equals("")){
					if(!player2.getShips().isEmpty() && player2.getShips().containsKey(value)){
						player2.getShips().remove(value);
						System.out.println(player2.getName()+"'s ship is HIT @ "+value);
						if(player2.getShips().isEmpty()){
							System.out.println(player1.getName()+" WON the Game...");
							game.setGameOver(true);
						}else{
							game.start(player1, player2, game);
						}
					}else{
						game.start(player2, player1, game);
					}
				}
				if(player1.getMissileTargets().isEmpty() && player2.getMissileTargets().isEmpty() && !game.isGameOver()){
					System.out.println("PEACE situation");
					game.setGameOver(true);
				}else if(player1.getMissileTargets().isEmpty() && !player2.getMissileTargets().isEmpty()){
					
					if(!game.isGameOver()){
						game.start(player2, player1, game);
					}
				}else if(!player1.getMissileTargets().isEmpty() && player2.getMissileTargets().isEmpty()){
					
					if(!game.isGameOver()){
						game.start(player1, player2, game);
					}
				}
			}
		}
		
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
