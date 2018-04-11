package view;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import model.Model;
import view.listener.AllyListener;
import view.listener.OpponentListener;



public class FieldView extends JFrame implements Observer {
		
	private Model model;
	
	// MENU
	private JMenuBar menu = new JMenuBar();
	private JMenu file = new JMenu("File");
	
	private JMenuItem newGame = new JMenuItem("New Game");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem load = new JMenuItem("Load");
	
	// PANELS
	private JPanel ally = new JPanel();
	private JPanel opponent = new JPanel();
	

	public FieldView(Model mod) {
		model = mod;
		setTitle("The Legendary Ships Battle");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 600));
		
		//MENU
		//new game
		file.add(newGame);
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 new Dialog(null, "New Game", true, model);
			}
		});
		
		//save
		file.add(save);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		
		//load
		file.add(load);
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		
		//file
		menu.add(file);
		file.setMnemonic('F');
		
		//menu
		this.setJMenuBar(menu);
		
		
		//PANELS
		//ally
		add(ally, BorderLayout.SOUTH);
		ally.setPreferredSize(new Dimension(250,250));
		battleFieldAlly();
		
		//opponent
		add(opponent, BorderLayout.NORTH);
		opponent.setPreferredSize(new Dimension(250,250));
		battleFieldOpponent();
		
		
		//--
		pack();
		setVisible(true);
	}
	
	
	private void battleFieldAlly() {
		int size = model.getAlly().size();
		ally.setLayout(new GridLayout(size, size));
		for(int i = 0 ; i < size ; i++) {
			for(int j=0 ; j < size ; j++) {
				JButton b = new JButton();
				ally.add(b);
				b.addActionListener(new AllyListener(model, i, j));
			}
		}
		
	}
	
	private void battleFieldOpponent() {
		int size = model.getOpponent().size();
		opponent.setLayout(new GridLayout(size, size));
		for(int i = 0 ; i < size ; i++) {
			for(int j=0 ; j < size ; j++) {
				JButton b = new JButton(); 
				opponent.add(b);
				b.addActionListener(new OpponentListener(model, i, j));
			}
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
