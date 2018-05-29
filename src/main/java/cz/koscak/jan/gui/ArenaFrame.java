package cz.koscak.jan.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cz.koscak.jan.core.Game;

@SuppressWarnings("serial")
public class ArenaFrame extends JFrame {

	private Game game;

	public ArenaFrame(Game game) {

		super("AI Fighters");

		this.game = game;

		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.add(new ArenaPanel());
		this.pack();
		
		this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// System.out.println("Frame created.");

	}

	class ArenaPanel extends JPanel {

	    public ArenaPanel() {
	        //setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    	setDoubleBuffered(true);
	    }

	    public Dimension getPreferredSize() {
	        return new Dimension(300, 300);
	    }

	    public void paintComponent(Graphics g) {
	    	
	        super.paintComponent(g);       

	        g.drawString("This is my custom Panel!", 10, 20);
	        
			game.paint(g);
			
	    }
	    
	}
	
}
