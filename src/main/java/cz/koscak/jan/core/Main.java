package cz.koscak.jan.core;

import cz.koscak.jan.gui.ArenaFrame;

public class Main {
	
	//private static ArenaFrame arenaFrame;

	public static void main(String[] args) {

		Game game = new Game();
		
		ArenaFrame arenaFrame = new ArenaFrame(game);
				
		//arenaFrame.paint(graphics);
		//arenaFrame.repaint();

		int round = 0;
		
		while(true) {
			
			if (!game.isPaused()) {
			
				round++;
				game.doActions(round);
				arenaFrame.repaint();
				
			}
			
			try {
				//System.out.println("Timer tick...");
				Thread.sleep(50);
				//Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}		

	}

}
