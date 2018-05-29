package cz.koscak.jan.ai.utils;

import cz.koscak.jan.ai.model.AIFighter;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.core.Game;
import cz.koscak.jan.core.Team;
import cz.koscak.jan.model.Bullet;
import cz.koscak.jan.model.Direction;
import cz.koscak.jan.model.Fighter;

public class AIActionPerformer {

	private Game game;

	public AIActionPerformer(Game game) {
		this.game = game;
	}
	
	public void doActions(AIScope aIScope) {
		
		//TODO: Perform actions
		
		for (AIFighter aIFighter : aIScope.getMyTeam()) {
			
			if (aIFighter.isMoving()) {
				
				//TODO
				
			}
			
			if (aIFighter.isShooting()) {
				Fighter fighter = findFighter(aIFighter);
				Direction direction = aIFighter.getShootingAction().getDirection();
				Bullet bullet = fighter.shoot(direction);
				game.getListOfBullets().add(bullet);
				aIFighter.doNotShoot();
			}
			
		}
		
	}
	
	private Team findTeam(AIFighter aIFighter) {
		return game.getListOfTeams().get(aIFighter.getTeamId());
	}
	
	private Fighter findFighter(AIFighter aIFighter) {
		Team team = findTeam(aIFighter);
		return team.getListOfFighters().get(aIFighter.getId());
	}
	
}
