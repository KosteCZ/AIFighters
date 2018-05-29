package cz.koscak.jan.core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import cz.koscak.jan.ai.DefaultAI;
import cz.koscak.jan.ai.Inteligence;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.ai.utils.AIActionPerformer;
import cz.koscak.jan.model.Bullet;
import cz.koscak.jan.model.Direction;
import cz.koscak.jan.model.Fighter;

public class Game {
	
	private List<Team> listOfTeams = new ArrayList<Team>();
	private List<Bullet> listOfBullets = new ArrayList<Bullet>();

	public Game() {
		
		initGame();
		
	}

	private void initGame() {
		
		AIActionPerformer aIActionPerformer = new AIActionPerformer(this);
		
		// Team BLUE
		
		Inteligence inteligenceRed = new DefaultAI();
		Team teamBlue = new Team(aIActionPerformer, Color.BLUE, inteligenceRed);
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
		
		Bullet bullet1 = fighterBlue1.shoot(new Direction(1, 1));
		listOfBullets.add(bullet1);

		// Team RED
		
		Inteligence inteligenceBlue = new DefaultAI();
		Team teamRed = new Team(aIActionPerformer, Color.RED, inteligenceBlue);
		listOfTeams.add(teamRed);
		
		Fighter fighterRed1 = new Fighter(teamRed, 200, 200);
		Fighter fighterRed2 = new Fighter(teamRed, 190, 210);
		Fighter fighterRed3 = new Fighter(teamRed, 180, 220);
		Fighter fighterRed4 = new Fighter(teamRed, 210, 190);
		Fighter fighterRed5 = new Fighter(teamRed, 220, 180);

		teamRed.addFighter(fighterRed1);
		teamRed.addFighter(fighterRed2);
		teamRed.addFighter(fighterRed3);
		teamRed.addFighter(fighterRed4);
		teamRed.addFighter(fighterRed5);		
		
		Bullet bullet2 = fighterRed1.shoot(new Direction(-1, -1));
		listOfBullets.add(bullet2);
		
		// Setting up scopes
		
		AIScope blueAIScope = new AIScope(0, teamBlue, teamRed);
		AIScope redAIScope = new AIScope(1, teamRed, teamBlue);
		teamBlue.setScope(blueAIScope);
		teamRed.setScope(redAIScope);
		
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
		
		for (Bullet bullet : listOfBullets) {
			bullet.move();
		}
	}

}
