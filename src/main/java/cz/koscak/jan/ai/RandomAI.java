package cz.koscak.jan.ai;

import cz.koscak.jan.ai.model.AIFighter;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.model.Direction;

public class RandomAI implements Inteligence {

	public void doAction(AIScope scope, int round) {
		
		if (round == 1 || round % 50 == 0) {
			
			for (AIFighter fighter : scope.getMyTeam()) {
				//fighter.shootTo(new Direction((Math.random() * 2) - 1, (Math.random() * 2) - 1));
				fighter.moveInfinitelyInDirection(new Direction(((int) (Math.random() * 3)) - 1, ((int) (Math.random() * 3)) - 1));			
			}
			
		}
		
		if (round % 25 == 0) {
			scope.getMyTeam().get(0).doNotMove();
		}
		
	}
	
}
