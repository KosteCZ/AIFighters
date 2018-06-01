package cz.koscak.jan.core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import cz.koscak.jan.ai.Inteligence;
import cz.koscak.jan.ai.PredictiveAI;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.ai.utils.AIActionPerformer;
import cz.koscak.jan.model.Bullet;
import cz.koscak.jan.model.Fighter;

public class Game {
	
	public static final int BULLET_SPEED = 4;
	private List<Team> listOfTeams = new ArrayList<Team>();
	private List<Bullet> listOfBullets = new ArrayList<Bullet>();
	private boolean paused = true;
	private boolean end = true;
	private boolean beforeFirstGame = true;
	
	public Game() {
		
		initGame();
		
	}

	public void startNewGame() {
		
		listOfTeams = new ArrayList<Team>();
		listOfBullets = new ArrayList<Bullet>();
		initGame();
		
	}
		
	public void initGame() {
		
		AIActionPerformer aIActionPerformer = new AIActionPerformer(this);
		
		// Team BLUE
		
		//Inteligence inteligenceBlue = new DefaultAI();
		//Inteligence inteligenceBlue = new StupidAI();
		//Inteligence inteligenceBlue = new NoAI();
		//Inteligence inteligenceBlue = new RunAwayAI();
		//Inteligence inteligenceBlue = new RunDownAI();
		//Inteligence inteligenceBlue = new RunSlowlyDownAI();
		Inteligence inteligenceBlue = new PredictiveAI();
		Team teamBlue = new Team(aIActionPerformer, Color.BLUE, inteligenceBlue);
		listOfTeams.add(teamBlue);
		
		Fighter fighterBlue1 = new Fighter(teamBlue, 100, 100);
		Fighter fighterBlue2 = new Fighter(teamBlue, 90, 110);
		Fighter fighterBlue3 = new Fighter(teamBlue, 80, 120);
		Fighter fighterBlue4 = new Fighter(teamBlue, 110, 90);
		Fighter fighterBlue5 = new Fighter(teamBlue, 120, 80);
		
		teamBlue.addFighter(fighterBlue1);
		teamBlue.addFighter(fighterBlue2);
		teamBlue.addFighter(fighterBlue3);
		teamBlue.addFighter(fighterBlue4);
		teamBlue.addFighter(fighterBlue5);
		
		// Team RED
		
		//Inteligence inteligenceRed = new RandomAI();
		//Inteligence inteligenceRed = new NormalAI();
		Inteligence inteligenceRed = new PredictiveAI();
		Team teamRed = new Team(aIActionPerformer, Color.RED, inteligenceRed);
		listOfTeams.add(teamRed);
		
		Fighter fighterRed1 = new Fighter(teamRed, 500 - (Fighter.DIAMETER * 2), 500 - (Fighter.DIAMETER * 2));
		Fighter fighterRed2 = new Fighter(teamRed, 490 - (Fighter.DIAMETER * 2), 510 - (Fighter.DIAMETER * 2));
		Fighter fighterRed3 = new Fighter(teamRed, 480 - (Fighter.DIAMETER * 2), 520 - (Fighter.DIAMETER * 2));
		Fighter fighterRed4 = new Fighter(teamRed, 510 - (Fighter.DIAMETER * 2), 490 - (Fighter.DIAMETER * 2));
		Fighter fighterRed5 = new Fighter(teamRed, 520 - (Fighter.DIAMETER * 2), 480 - (Fighter.DIAMETER * 2));

		teamRed.addFighter(fighterRed1);
		teamRed.addFighter(fighterRed2);
		teamRed.addFighter(fighterRed3);
		teamRed.addFighter(fighterRed4);
		teamRed.addFighter(fighterRed5);		
		
		// Setting up scopes
		
		AIScope blueAIScope = new AIScope(0, teamBlue, teamRed);
		AIScope redAIScope = new AIScope(1, teamRed, teamBlue);
		teamBlue.setScope(blueAIScope);
		teamRed.setScope(redAIScope);
		
	}
	
	public void setBeforeFirstGameToFalse() {
		beforeFirstGame = false;
	}
	
	public boolean isBeforeFirstGame() {
		return beforeFirstGame;
	}
	
	public List<Team> getListOfTeams() {
		return listOfTeams;
	}

	public List<Bullet> getListOfBullets() {
		return listOfBullets;
	}

	public void paint(Graphics graphics) {

		for (Team team : listOfTeams) {
			team.paint(graphics);
		}
		
		for (Bullet bullet : listOfBullets) {
			bullet.print(graphics);
		}
		
	}

	public void doActions(int round) {
		
		for (Team team : listOfTeams) {
			team.doAction(round);
		}
		
		for (int i = 0; i < BULLET_SPEED; i++) {
			List<Bullet> listOfBulletsToRemove = new ArrayList<Bullet>();
			for (Bullet bullet : listOfBullets) {
				bullet.move();
				if (bullet.isOutOfArena()) {
					listOfBulletsToRemove.add(bullet);
				}
			}
			for (Bullet bullet : listOfBulletsToRemove) {
				listOfBullets.remove(bullet);
			}
		}
		
		checkIfBulletsHitFighters();
		
		for (Team team : listOfTeams) {
			
			boolean doEnd = true;
			
			for (Fighter fighter : team.getListOfFighters()) {
				
				if (fighter.isAlive()) {
					
					doEnd = false;
					
				}
			
			}
			
			if (doEnd) {
				end = true;
				paused = true;
			}
			
		}
		
	}
	
	private void checkIfBulletsHitFighters() {
		for (Team team : listOfTeams) {
			for (Fighter fighter : team.getListOfFighters()) {
				for (Bullet bullet : listOfBullets) {
					double diffX = Math.abs((fighter.getX() + Fighter.RADIUS) - (bullet.getX() + Bullet.RADIUS));
					double diffY = Math.abs((fighter.getY() + Fighter.RADIUS) - (bullet.getY() + Bullet.RADIUS));
					double diffTotal = Math.sqrt((diffX * diffX) + (diffY * diffY));
					if (diffTotal < 7) {
						fighter.die();
					}
				}
			}
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	
	public boolean tooglePaused() {
		paused = !paused;
//		System.out.println("Toogle paused: " + paused);
		return paused;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isEnd() {
		return end;
	}
	
	public void setInteligenceForTeam(int index, Inteligence inteligence) {
		if (index < listOfTeams.size()) {
			Team team = listOfTeams.get(index);
			if (!(team.getInteligence().getClass().getName().equals(inteligence.getClass().getName()))) {
				team.setInteligence(inteligence);
			}
		}
	}
	
}
