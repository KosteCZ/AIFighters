package cz.koscak.jan.core;

import cz.koscak.jan.gui.ArenaFrame;

public class Main {
	
	//private static ArenaFrame arenaFrame;

	public static void main(String[] args) {

		Game game = new Game();
		
		ArenaFrame arenaFrame = new ArenaFrame(game);
				
		//arenaFrame.paint(graphics);
		//arenaFrame.repaint();

		while(true) {
			
			try {
				//System.out.println("Timer tick...");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.doActions();
			arenaFrame.repaint();
		}		

	}

}
