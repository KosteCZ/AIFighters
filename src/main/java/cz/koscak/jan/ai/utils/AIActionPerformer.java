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
		
		// Perform actions
		
		for (AIFighter aIFighter : aIScope.getMyTeam()) {
			
			Fighter fighter = findFighter(aIFighter);
			
			if (fighter.isAlive()) {
			
				if (aIFighter.isMoving()) {
					
					Direction direction = aIFighter.getMovingAction().getDirection();
					
					//TODO: Stopping (edges of arena, reaching desired destination)
					
					fighter.move(direction);
					
					boolean doOnlyOnce = aIFighter.getMovingAction().isDoOnlyOnce();
					if (doOnlyOnce) {
						aIFighter.doNotMove();
					}
				}
				
				if (aIFighter.isShooting()) {
					
					Direction direction = aIFighter.getShootingAction().getDirection();
					Bullet bullet = fighter.shoot(direction);
					game.getListOfBullets().add(bullet);
					aIFighter.doNotShoot();
					
				}
			
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
