package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import model.Model;
import model.ship.factory.ModernShipFactory;
import model.ship.factory.ShipFactory;
import model.strategy.ComputerStrategy;
import model.strategy.CrossComputerStrategy;
import model.strategy.PlacementRandomStrategy;
import model.strategy.PlacementStrategy;
import model.strategy.RandomComputerStrategy;

public class Dialog extends JDialog {
	private static final String[] AGE_BOAT = {"Modern"};
	private static final String[] PLACEMENT_STRATEGY = {"Random"};
	private static final String[] ATTACK_STRATEGY = {"Random", "Cross"};
	//--
  private JLabel ageLabel, placementLabel, strategyLabel;
  private JComboBox age, placement, strategy;
  private Model model;

  public Dialog(JFrame parent, String title, boolean modal, Model mod){
    super(parent, title, modal);
    model = mod;
    this.setSize(550, 270);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    this.initComponent();
    this.setVisible(true);
  }


  private void initComponent(){
	  //PANELS
	// age of boats
	    JPanel panAge = new JPanel();
	    panAge.setBackground(Color.white);
	    panAge.setPreferredSize(new Dimension(300, 60));
	    panAge.setBorder(BorderFactory.createTitledBorder("Chose an Age for the boats"));
	    age = new JComboBox();
	    age.setPreferredSize(new Dimension(100, 25));
	    for(String s : AGE_BOAT) {
	    	age.addItem(s);
	    }
	    ageLabel = new JLabel("Age : ");
	    panAge.add(ageLabel);
	    panAge.add(age);
	    
	  // computer placement
	    JPanel panPlacement = new JPanel();
	    panPlacement.setBackground(Color.white);
	    panPlacement.setPreferredSize(new Dimension(300, 60));
	    panPlacement.setBorder(BorderFactory.createTitledBorder("Choose a placement strategy for the computer"));
	    placement = new JComboBox();
	    placement.setPreferredSize(new Dimension(100, 25));
	    for(String s : PLACEMENT_STRATEGY) {
		    placement.addItem(s);
	    }
	    placementLabel = new JLabel("Strategy : ");
	    panPlacement.add(placementLabel);
	    panPlacement.add(placement);
	    
	  // computer strategy
	    JPanel panStrategy = new JPanel();
	    panStrategy.setBackground(Color.white);
	    panStrategy.setPreferredSize(new Dimension(300, 60));
	    panStrategy.setBorder(BorderFactory.createTitledBorder("Choose an attack strategy for the computer"));
	    strategy = new JComboBox();
	    strategy.setPreferredSize(new Dimension(100, 25));
	    for(String s : ATTACK_STRATEGY) {
		    strategy.addItem(s);
	    }
	    strategyLabel = new JLabel("Strategy : ");
	    panStrategy.add(strategyLabel);
	    panStrategy.add(strategy);
	    

    // CONTROLS
    JPanel control = new JPanel();
    // ok
    JButton okBouton = new JButton("OK");
    okBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {        
    	  model.newGame(getAge(age), getStrategy(strategy),  getPlacement(placement));
        setVisible(false);
      }
    });

    // cancel
    JButton cancelBouton = new JButton("Annuler");
    cancelBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
      }      
    });
    
    // --

    JPanel content = new JPanel();
    content.setBackground(Color.white);
    content.add(panAge);
    content.add(panPlacement);
    content.add(panStrategy);

    control.add(okBouton);
    control.add(cancelBouton);
 
    this.getContentPane().add(content, BorderLayout.CENTER);
    this.getContentPane().add(control, BorderLayout.SOUTH);

  }  
  
  //GETTER
  
  private ShipFactory getAge(JComboBox age) {
	  String a = (String) age.getSelectedItem();
	  if (a.equals("Modern")) {
		  return ModernShipFactory.getInstance();
	  }
	  return null;
  }
  
  private ComputerStrategy getStrategy(JComboBox strategy) {
	  String s = (String) strategy.getSelectedItem();
	  if (s.equals("Random")) {
		  return RandomComputerStrategy.getInstance();
	  }
	  if (s.equals("Cross")) {
		  return CrossComputerStrategy.getInstance();
	  }
	  return null;
  }
  
  
  private PlacementStrategy getPlacement(JComboBox placement) {
	  String s = (String) placement.getSelectedItem();
	  if (s.equals("Random")) {
		  return PlacementRandomStrategy.getInstance();
	  }
	  return null;
  }
  
  
}