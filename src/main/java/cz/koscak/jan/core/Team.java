package cz.koscak.jan.core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import cz.koscak.jan.ai.Inteligence;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.ai.utils.AIActionPerformer;
import cz.koscak.jan.model.Fighter;

public class Team {
	
	private Color color;
	private Inteligence inteligence;
	private List<Fighter> listOfFighters = new ArrayList<Fighter>();
	private AIScope aIScope;
	private AIActionPerformer aIActionPerformer;
	
	public Team(AIActionPerformer aIActionPerformer, Color color, Inteligence inteligence) {
		
		this.color = color;
		this.inteligence = inteligence;
		this.aIActionPerformer = aIActionPerformer;
				
	}
	
	public void addFighter(Fighter fighter) {
		listOfFighters.add(fighter);
	}
	
	public List<Fighter> getListOfFighters() {
		return listOfFighters;
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

	public void doAction(int round) {
		inteligence.doAction(aIScope, round);
		aIActionPerformer.doActions(aIScope);
	}

	public void setScope(AIScope aIScope) {
		this.aIScope = aIScope;
	}
	
	public int getPlayersAliveCount() {
		int paluyersAliveCount = 0;
		for (Fighter fighter : listOfFighters) {
			if (fighter.isAlive()) {
				paluyersAliveCount++;
			}
		}
		return paluyersAliveCount;
	}

	public Inteligence getInteligence() {
		return inteligence;
	}

	public void setInteligence(Inteligence inteligence) {
		this.inteligence = inteligence;
	}	

}
