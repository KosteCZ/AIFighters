package cz.koscak.jan.ai;

import cz.koscak.jan.ai.model.AIFighter;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.model.Direction;

public class StupidAI implements Inteligence {

	public void doAction(AIScope scope, int round) {
		
		if (round == 1) {
			AIFighter fighter1 = scope.getMyTeam().get(0);
			AIFighter fighter2 = scope.getMyTeam().get(1);
			AIFighter fighter3 = scope.getMyTeam().get(2);
			AIFighter fighter4 = scope.getMyTeam().get(3);
			AIFighter fighter5 = scope.getMyTeam().get(4);
			
			fighter1.shootTo(new Direction(1, 1));
			fighter2.shootTo(new Direction(1, 1));
			fighter3.shootTo(new Direction(1, 1));
			fighter4.shootTo(new Direction(1, 1));
			fighter5.shootTo(new Direction(1, 1));

			fighter1.moveInfinitelyInDirection(new Direction(-1, 1));			
			fighter2.moveInfinitelyInDirection(new Direction(-1, 1));			
			fighter3.moveInfinitelyInDirection(new Direction(-1, 1));			
			fighter4.moveInfinitelyInDirection(new Direction(1, -1));			
			fighter5.moveInfinitelyInDirection(new Direction(1, -1));			
		}
		
		if (round % 50 == 0) {
				
			for (AIFighter fighter : scope.getMyTeam()) {
				fighter.shootTo(new Direction((Math.random() * 2) - 1, (Math.random() * 2) - 1));
				fighter.moveInfinitelyInDirection(new Direction(((int) (Math.random() * 3)) - 1, ((int) (Math.random() * 3)) - 1));			
			}
			
		}
		
		if (round % 25 == 0) {
			scope.getMyTeam().get(0).doNotMove();
		}
		
	}
	
}
