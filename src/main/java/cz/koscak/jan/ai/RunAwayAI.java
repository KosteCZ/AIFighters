package cz.koscak.jan.ai;

import cz.koscak.jan.ai.model.AIFighter;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.model.Direction;

public class RunAwayAI implements Inteligence {

	public void doAction(AIScope scope, int round) {
		
		if (round == 1 || round == 50) {
						
			for (AIFighter fighter : scope.getMyTeam()) {
				fighter.moveInfinitelyInDirection(new Direction(((int) (Math.random() * 3)) - 1, ((int) (Math.random() * 3)) - 1));			
			}
		
		}
		
	}
	
}
