package com.napier.airlinereservation.main;

import javax.swing.JOptionPane;

import com.napier.airlinereservation.gui.ReservationWindowGUI;
import com.napier.airlinereservation.helpers.FileHelper;

public class Main_GUI {

	public static void main(String[] args) {

		String[] choices = { "Create a new File", "Load an Existing File" };
		String message = "Would you like to create a new File or load an existing file?";
		int selectedChoice = JOptionPane.showOptionDialog(null, message, "Choose an option", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

		if (selectedChoice == 1) {
			try {
				FileHelper.deserialize();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error retrieving the records", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		ReservationWindowGUI rwgui = new ReservationWindowGUI();
		rwgui.setVisible(true);
	}

}
