package AttentionAssistant;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Parent_Child_Menu {
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	private Settings set;
	private DataBase db;
	
	public Parent_Child_Menu(Settings settings,DataBase database) {
		this.set = settings;
		this.db = database;
	}
	
	public void child_menu(){
		//place holder************************************
		UIManager.put("Button.foreground", aa_purple);
		UIManager.put("Button.background", aa_grey);
		UIManager.put("OptionPane.background", Color.black);
		UIManager.put("Panel.setOpaque", true);
		UIManager.put("Panel.background", aa_grey);
		UIManager.put("TextField.selectionBackground", Color.WHITE);
		UIManager.put("TextField.selectionForeground", Color.WHITE);
		
		JLabel warning = new JLabel("<html><center>This will open child menu" + "ok to shut down AA, cancel to go back<br/></center></html>");
		warning.setFont(new Font("Serif", Font.BOLD, 16));
		warning.setForeground(Color.white);
		
		int response = JOptionPane.showConfirmDialog(null, warning, "The Attention Assistant", JOptionPane.OK_CANCEL_OPTION);
		switch (response) {
		case JOptionPane.OK_OPTION:
			System.exit(0); 
		case JOptionPane.CANCEL_OPTION:
			break; 
		}
		
		//access guide
		//settings parent can change/lock
		//access PM
		//access progress report
	}
}
