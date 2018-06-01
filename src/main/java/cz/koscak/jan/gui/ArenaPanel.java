package cz.koscak.jan.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cz.koscak.jan.ai.Inteligence;
import cz.koscak.jan.core.Game;
import cz.koscak.jan.model.Arena;

@SuppressWarnings("serial")
public class ArenaPanel extends JPanel {

	private static final String BUTTON_PAUSE_NEW_GAME = "New game";
	private static final String BUTTON_PAUSE_START = "Start";
	private static final String BUTTON_PAUSE_PAUSE = "Pause";
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	private static final String[] AI_NAMES = new String[] { "NoAI", "DefaultAI", "StupidAI", "RandomAI", "RunAwayAI", "RunDownAI", "RunSlowlyDownAI",
			"NormalAI", "PredictiveAI" };
	
	private Game game;
	private JLabel labelBlueAliveText;
	private JLabel labelRedAliveText;
	private JLabel labelBlueAliveNumber;
	private JLabel labelRedAliveNumber;
	private JLabel blueAILabel;
	private JLabel redAILabel;
	private JComboBox<String> blueAIComboBox;
	private JComboBox<String> redAIComboBox;
	private JButton buttonPause;
	
	public ArenaPanel(Game game) {
		
		this.game = game;
		
		this.setLayout(null);

		//setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	setDoubleBuffered(true);
    	
		createPauseButton();
		
		createLabel();
		
		createBlueAISelection();
		createRedAISelection();
		
    }

    private void createLabel() {
    	
    	labelBlueAliveText = new JLabel("BLUE alive: ");
    	labelBlueAliveText.setForeground(Color.BLUE);
    	//labelBlueAliveText.setBounds(10, 10, 100, 30);
    	labelBlueAliveText.setBounds(10, 20, 100, 30);
    	labelBlueAliveText.setHorizontalAlignment(JLabel.LEFT);
    	labelBlueAliveText.setVerticalAlignment(JLabel.CENTER);
    	add(labelBlueAliveText);
		
    	labelBlueAliveNumber = new JLabel("BLUE alive: " + 5);
    	labelBlueAliveNumber.setForeground(Color.BLUE);
    	//labelBlueAliveNumber.setBounds(10, 10, 100, 30);
    	labelBlueAliveNumber.setBounds(10 + 200, 20, 20, 30);
    	labelBlueAliveNumber.setHorizontalAlignment(JLabel.RIGHT);
    	labelBlueAliveNumber.setVerticalAlignment(JLabel.CENTER);
    	add(labelBlueAliveNumber);
		
    	labelRedAliveText = new JLabel("RED alive: ");
    	labelRedAliveText.setForeground(Color.RED);
    	//labelRedAliveText.setBounds(WIDTH - 110, 10, 100, 30);
    	labelRedAliveText.setBounds(WIDTH - 110 - 123, 20, 100, 30);
    	labelRedAliveText.setHorizontalAlignment(JLabel.LEFT);
    	labelRedAliveText.setVerticalAlignment(JLabel.CENTER);
    	add(labelRedAliveText);
		
    	labelRedAliveNumber = new JLabel("RED alive: " + 5);
    	labelRedAliveNumber.setForeground(Color.RED);
    	//labelRedAliveNumber.setBounds(WIDTH - 110, 10, 100, 30);
    	labelRedAliveNumber.setBounds(WIDTH - 110, 20, 100, 30);
    	labelRedAliveNumber.setHorizontalAlignment(JLabel.RIGHT);
    	labelRedAliveNumber.setVerticalAlignment(JLabel.CENTER);
    	add(labelRedAliveNumber);
		
	}

	public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g);       

        //g.drawString("This is my custom Panel!", 10, 20);
		//g.drawOval(0, 0, 4, 4);
        
        paintArena(g);
        
		game.paint(g);
		
		updateFighterAlives();
		
		printTexts(g);
		
		checkGameStateAndHandleComboboxes();
		
    }
    
	private void checkGameStateAndHandleComboboxes() {
		if (game.isEnd()) {
			enableAIComboboxes(true);
		} else {
			enableAIComboboxes(false);
		}
		
	}

	private void updateFighterAlives() {
		
		labelBlueAliveNumber.setText("" + game.getListOfTeams().get(0).getPlayersAliveCount());
		labelRedAliveNumber.setText("" + game.getListOfTeams().get(1).getPlayersAliveCount());
		
	}

	private void printTexts(Graphics g) {
		
		if (game.isEnd() && !game.isBeforeFirstGame()) {
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Dialog", Font.BOLD, 16));
			g.drawString("END", (ArenaPanel.WIDTH / 2) - 15, (ArenaPanel.HEIGHT / 2) - 10);
			
			enableAIComboboxes(true);
			buttonPause.setText(BUTTON_PAUSE_NEW_GAME);
			
		}
		
	}

	private void paintArena(Graphics g) {

		g.drawRect(Arena.ARENA_X, Arena.ARENA_Y, Arena.ARENA_WIDTH, Arena.ARENA_HEIGHT);
		
	}
	
	private void createPauseButton() {
		
		buttonPause = new JButton(BUTTON_PAUSE_NEW_GAME);
		buttonPause.setBounds(WIDTH / 2 - 50, 10, 100, 30);
		
		buttonPause.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				boolean paused = game.tooglePaused();
				if (paused) {
					game.setBeforeFirstGameToFalse();
					buttonPause.setText(BUTTON_PAUSE_START);
					/*if (game.isEnd() && !game.isBeforeFirstGame()) {
					game.setEnd(false);
					game.startNewGame();
					}*/
				} else {
					game.setEnd(false);
					buttonPause.setText(BUTTON_PAUSE_PAUSE);
				}
			}

		});
		
		add(buttonPause);
		
	}

	private void enableAIComboboxes(boolean enable) {
		blueAIComboBox.setEnabled(enable);
		redAIComboBox.setEnabled(enable);
	}
	
	private void createBlueAISelection() {
		
		blueAILabel = new JLabel("BLUE AI: ", JLabel.LEFT);
		blueAILabel.setForeground(Color.BLUE);
    	blueAILabel.setBounds(10, 5 + 5, 80, 10);   	
		add(blueAILabel);
		
		
		
		blueAIComboBox = new JComboBox<String>(AI_NAMES);
		//blueAIComboBox.setSelectedIndex(0);
		blueAIComboBox.setSelectedIndex(8);
		
		blueAIComboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				int selectedAI = blueAIComboBox.getSelectedIndex();
				
				try {
					@SuppressWarnings("unchecked")
					Class<Inteligence> blueAI = (Class<Inteligence>) Class.forName("cz.koscak.jan.ai." + AI_NAMES[selectedAI]);
					System.out.println("BLUE AI: " + blueAI.getName());
					Inteligence inteligence = blueAI.newInstance();
					game.setInteligenceForTeam(0, inteligence);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		});
		
		blueAIComboBox.setBounds(80, 5, 150, 20);

		add(blueAIComboBox);
		
	}
	
	private void createRedAISelection() {
		
		redAILabel = new JLabel("RED AI: ", JLabel.RIGHT);
		redAILabel.setForeground(Color.RED);
		redAILabel.setBounds(WIDTH - 270, 5 + 5, 80, 10);   	
		add(redAILabel);
		
		
		
		redAIComboBox = new JComboBox<String>(AI_NAMES);
		//redAIComboBox.setSelectedIndex(0);
		redAIComboBox.setSelectedIndex(8);
		
		redAIComboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				int selectedAI = redAIComboBox.getSelectedIndex();
				
				try {
					@SuppressWarnings("unchecked")
					Class<Inteligence> redAI = (Class<Inteligence>) Class.forName("cz.koscak.jan.ai." + AI_NAMES[selectedAI]);
					System.out.println("RED AI: " + redAI.getName());
					Inteligence inteligence = redAI.newInstance();
					game.setInteligenceForTeam(1, inteligence);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		});
		
		redAIComboBox.setBounds(WIDTH - 160, 5, 150, 20);

		add(redAIComboBox);
		
	}
	
}