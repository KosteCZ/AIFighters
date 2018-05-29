package cz.koscak.jan.ai.model;

import java.util.ArrayList;
import java.util.List;

import cz.koscak.jan.core.Team;
import cz.koscak.jan.model.Fighter;

public class AIScope {

	private List<AIFighter> myTeam = new ArrayList<AIFighter>();
	private List<AIEnemy> enemyTeam = new ArrayList<AIEnemy>();
	
	public AIScope(int teamId, Team myOriginalTeam, Team enemyOriginalTeam) {
		for (int fighterIndex = 0; fighterIndex < myOriginalTeam.getListOfFighters().size(); fighterIndex++) {
			Fighter fighter = myOriginalTeam.getListOfFighters().get(fighterIndex);
			myTeam.add(new AIFighter(fighter, fighterIndex, teamId));
		}
		for (int fighterIndex = 0; fighterIndex < enemyOriginalTeam.getListOfFighters().size(); fighterIndex++) {
			Fighter fighter = enemyOriginalTeam.getListOfFighters().get(fighterIndex);
			enemyTeam.add(new AIEnemy(fighter, fighterIndex));
		}
	}

	public List<AIFighter> getMyTeam() {
		return myTeam;
	}

	public List<AIEnemy> getEnemyTeam() {
		return enemyTeam;
	}
	
}
