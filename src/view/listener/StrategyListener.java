package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Model;

public class StrategyListener implements ActionListener{

	private Model model;

	public StrategyListener(Model mod) {
		model = mod;
	}

	public void actionPerformed(ActionEvent e) {
		JMenuItem j = (JMenuItem) e.getSource();
		model.setStrategy(j.getText());
	}

}
