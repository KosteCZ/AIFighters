package cz.koscak.jan.ai;

import cz.koscak.jan.ai.model.AIFighter;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.model.Direction;

public class RunSlowlyDownAI implements Inteligence {

	public void doAction(AIScope scope, int round) {
		
		if (round % 2 == 1) {
			
			for (AIFighter fighter : scope.getMyTeam()) {
				fighter.moveInfinitelyInDirection(new Direction(0, 1));			
			}
		
		} else {
			
			for (AIFighter fighter : scope.getMyTeam()) {
				fighter.doNotMove();			
			}
			
		}

		
	}
	
}
