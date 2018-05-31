package cz.koscak.jan.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cz.koscak.jan.core.Game;
import cz.koscak.jan.model.Arena;

@SuppressWarnings("serial")
public class ArenaFrame extends JFrame {

	private Game game;

	public ArenaFrame(Game game) {

		super("AI Fighters");

		this.game = game;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.add(new ArenaPanel());
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		// System.out.println("Frame created.");

	}

	class ArenaPanel extends JPanel {

		public ArenaPanel() {
	        //setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    	setDoubleBuffered(true);
	    }

	    public Dimension getPreferredSize() {
	        return new Dimension(600, 600);
	    }

	    public void paintComponent(Graphics g) {
	    	
	        super.paintComponent(g);       

	        //g.drawString("This is my custom Panel!", 10, 20);
			//g.drawOval(0, 0, 4, 4);
	        
	        paintArena(g);
	        
			game.paint(g);
			
	    }

		private void paintArena(Graphics g) {

			g.drawRect(Arena.ARENA_X, Arena.ARENA_Y, Arena.ARENA_WIDTH, Arena.ARENA_HEIGHT);
			
		}
	    
	}
	
}
