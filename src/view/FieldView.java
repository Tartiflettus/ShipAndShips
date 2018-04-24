package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import model.Model;
import model.ship.Ship;
import view.listener.AllyListener;
import view.listener.OpponentListener;
import view.listener.StrategyListener;

public class FieldView extends JFrame implements Observer {

	private Model model;
	
	// MENU
	private JMenuBar menu = new JMenuBar();
	private JMenu file = new JMenu("File");

	private JMenuItem newGame = new JMenuItem("New Game");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem load = new JMenuItem("Load");

	//MENU STRATEGY
	private JMenu strategy;
	private JMenuItem cross;
	private JMenuItem random;	

	// PANELS
	private JPanel ally = new JPanel();
	private JPanel opponent = new JPanel();
	private JPanel shipsPanel = new JPanel();
	
	// INTERFACE TO PLACE A SHIP
	private JComboBox<Ship> comboShip = new JComboBox<>();
	private JButton rotate;
	private JButton play;

	public FieldView(Model mod) {
		
		model = mod;
		mod.addObserver(this);
		
		setTitle("The Legendary Ships Battle");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 600));
		
		//currentShip = shipsNoPlaced.get(0);
		
		// MENU
		// new game
		file.add(newGame);
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Dialog(null, "New Game", true, model);
			}
		});

		// save
		file.add(save);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));

		// load
		file.add(load);
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));

		// file
		menu.add(file);
		file.setMnemonic('F');
		
		// menu
		this.setJMenuBar(menu);

		// PANELS
		// ally
		add(ally, BorderLayout.SOUTH);
		ally.setPreferredSize(new Dimension(250, 250));
		battleFieldAlly();

		// opponent
		add(opponent, BorderLayout.NORTH);
		opponent.setPreferredSize(new Dimension(250, 250));
		battleFieldOpponent();

		//MENU STRATEGY
		strategy = new JMenu("Strategy");
		menu.add(strategy);
		
		cross = new JMenuItem("Cross");
		random = new JMenuItem("Random");
		
		cross.addActionListener(new StrategyListener(mod));
		random.addActionListener(new StrategyListener(mod));
		strategy.add(cross);
		strategy.add(random);
		
		//INTERFACE TO PLACE A SHIP
		for(Ship s : model.getShipsNoPlaced()) {
			comboShip.addItem(s);
		}
		shipsPanel.add(comboShip);
		add(shipsPanel, BorderLayout.CENTER);
		
		//button rotate
		rotate = new JButton("vertical");
		rotate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//currentShip.changeOrientation();
				rotate.setText(rotate.getText().equals("vertical") ? "horizontal" : "vertical");
			}
		});
		shipsPanel.add(rotate);

		//button play
		play = new JButton("Play!");
		play.setEnabled(false);
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//faire passer le model en mode play
				model.placeShipComputer();
				model.setGameState(Model.GameState.IN_GAME);
			}
		});
		shipsPanel.add(play);
		
		
		
		// --
		pack();
		setVisible(true);
	}

	private void battleFieldAlly() {
		ally.removeAll();
		int size = model.getAlly().size();
		ally.setLayout(new GridLayout(size, size));
		for (int i = 0; i < size; i++) { //y
			for (int j = 0; j < size; j++) { //x
				JButton b = new JButton();
				final Ship s = model.getAllyShip(j, i);
				if(s != null) {
					b.setEnabled(false);
					b.setText(s.letter());
				}
				if(model.allyTouched(j, i)) {
					if(s != null){
						b.setBackground(Color.RED);
					} else {
						b.setBackground(Color.GREEN);
					}					
				}
				ally.add(b);
				b.addActionListener(new AllyListener(model, this, j, i));
			}
		}

	}

	private void battleFieldOpponent() {
		opponent.removeAll();
		int size = model.getOpponent().size();
		opponent.setLayout(new GridLayout(size, size));
		for (int i = 0; i < size; i++) { //y
			for (int j = 0; j < size; j++) { //x
				JButton b = new JButton();
				if(model.opponentTouched(j, i)) {
					if(model.getOpponentShip(j, i) != null){
						b.setText("F");
						b.setBackground(Color.RED);
					} else {
						b.setText("X");
						b.setBackground(Color.GREEN);
					}
					b.setEnabled(false);
				}
				opponent.add(b);
				b.addActionListener(new OpponentListener(model, j, i));
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		//update ship combobox
		comboShip.removeAllItems();
		for(Ship s : model.getShipsNoPlaced()) {
			comboShip.addItem(s);
		}
		
		//play available or not
		if(model.getShipsNoPlaced().isEmpty()) {
			play.setEnabled(true);
		}else {
			play.setEnabled(false);
		}
		
		if(model.getGameState() == Model.GameState.IN_GAME) {
			comboShip.setEnabled(false);
			play.setEnabled(false);
			rotate.setEnabled(false);
		}
		
		battleFieldAlly();
		battleFieldOpponent();
		this.revalidate();
	}
	
	
	public Ship getCurrentShip() {
		return (Ship) comboShip.getSelectedItem();
	}
	
	
	public boolean currentOrientationchanged() {
		return rotate.getText().equals("vertical") ? false : true;
	}

}
