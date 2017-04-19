package com.assignment.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Player {

	private String name;
	private List<String> missileTargets = new ArrayList<String>();
	private Map<String, BattleShip> ships = new HashMap<String, BattleShip>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getMissileTargets() {
		return missileTargets;
	}
	public void setMissileTargets(List<String> missileTargets) {
		this.missileTargets = missileTargets;
	}
	public Map<String, BattleShip> getShips() {
		return ships;
	}
	public void setShips(Map<String, BattleShip> ships) {
		this.ships = ships;
	}
	
	public void addShip(String location, BattleShip ship) {
		this.ships.put(location, ship);
	}
	
	public void addMissileTarget(String target) {
		this.missileTargets.add(target);
	}
	public Player(String name) {
		super();
		this.name = name;
	}
	
	
}
