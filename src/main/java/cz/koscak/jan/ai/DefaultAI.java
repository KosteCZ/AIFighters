package cz.koscak.jan.ai;

import cz.koscak.jan.ai.model.AIFighter;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.model.Direction;

public class DefaultAI implements Inteligence {

	public void doAction(AIScope scope, int round) {
		
		if (round == 1) {
			
			for (AIFighter fighter : scope.getMyTeam()) {
				fighter.shootTo(new Direction(1, 1));
			}
			
		}
		
	}
	
}
