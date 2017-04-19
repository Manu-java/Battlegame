package com.assignment.utility;

import java.util.ArrayList;
import java.util.List;

public class BattleFieldUtility {

	public static int convertToDigit(char row){
		return (int)row - 64;
	}
	
	public static String convertToCharacter(int number){
		
		return Character.toString((char)(number + 64));
	}
	
	public static List<String> addDimensionToLocation(String location, int horizontalDimension, int verticalDimension){
		List<String> locations = new ArrayList<String>();
		
		char row = location.charAt(0);
		String column = location.substring(1, 2);
		
		if(verticalDimension > 1 && horizontalDimension <= 1){
			int rowNum = BattleFieldUtility.convertToDigit(row);
			
			for(int i=1;i<verticalDimension;i++){
				locations.add(BattleFieldUtility.convertToCharacter(rowNum + i) + column);
			}
		}else if(horizontalDimension > 1 && verticalDimension > 1){
			
			
			for(int i=0;i<horizontalDimension;i++){
				int rowNum = Integer.parseInt(column);
				column = String.valueOf(rowNum + i);
				location = Character.toString(row) + (column);
				locations.add(location);
				
				if(verticalDimension > 1){
					row = location.charAt(0);
					rowNum = BattleFieldUtility.convertToDigit(row);
					
					for(int j=1;j<verticalDimension;j++){
						locations.add(BattleFieldUtility.convertToCharacter(rowNum + j) + column);
					}
				}
			}
		}else if(horizontalDimension > 1 && verticalDimension <= 1){
			int rowNum = Integer.parseInt(column);
			
			for(int i=1;i<horizontalDimension;i++){
				location = Character.toString(row) + (rowNum + i);
				locations.add(location);
			}
		}
		return locations;
	}
}
