package cz.koscak.jan.core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import cz.koscak.jan.model.Fighter;

public class Team {
	
	private Color color;
	private List<Fighter> listOfFighters = new ArrayList<Fighter>();
	
	public Team (Color color) {
		
		this.color = color;
				
	}
	
	public void addFighter(Fighter fighter) {
		listOfFighters.add(fighter);
	}
	
	public void paint(Graphics graphics) {
		
		for (Fighter fighter : listOfFighters) {
			//System.out.println("Fighter [" + fighter.getX() + "][" + fighter.getY() + "]");
			fighter.print(graphics);
		}
		
	}

	public Color getColor() {
		return color;
	}

}
