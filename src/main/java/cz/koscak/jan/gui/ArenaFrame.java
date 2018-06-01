package cz.koscak.jan.gui;

import javax.swing.JFrame;

import cz.koscak.jan.core.Game;

@SuppressWarnings("serial")
public class ArenaFrame extends JFrame {

	public ArenaFrame(Game game) {

		super("AI Fighters");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.add(new ArenaPanel(game));
		this.pack();
		
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);

		// System.out.println("Frame created.");

	}
	
}
