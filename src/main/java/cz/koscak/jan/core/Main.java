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
			
			round++;
			
			try {
				//System.out.println("Timer tick...");
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.doActions(round);
			arenaFrame.repaint();
		}		

	}

}
