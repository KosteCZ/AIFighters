package cz.koscak.jan.core;

public class GameStatus {
	
	public static final int NO_GAME = 0;
	public static final int RUNNING = 1;
	public static final int PAUSED = 2;
	
	private int gameStatus = NO_GAME;
	
	public GameStatus() {
	}
	
	public int startGame() {
		gameStatus = RUNNING;
		return gameStatus;
	}

	public int pauseGame() {
		gameStatus = PAUSED;
		return gameStatus;
	}

	public int endGame() {
		gameStatus = NO_GAME;
		return gameStatus;
	}

	public int getGameStatus() {
		return gameStatus;
	}	

}
