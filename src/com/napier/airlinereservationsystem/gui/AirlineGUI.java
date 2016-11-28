package com.napier.airlinereservation.gui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.table.DefaultTableModel;

import com.napier.airlinereservation.datatypes.Airline;
import com.napier.airlinereservation.helpers.DataHelper;
import com.napier.airlinereservation.helpers.DataHelper.DataType;
import com.napier.airlinereservation.helpers.DataHelper.OpType;
import com.napier.airlinereservation.helpers.IObserver;

import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AirlineGUI extends JPanel implements ActionListener, IObserver<Airline> {

	private static final long serialVersionUID = -5768781871242697490L;
	private JPanel formPanel;
	private JTable tableAirline;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private Box mainBox;

	private JLabel lblAirlineCode;
	private JLabel lblAirlineName;

	private JTextField txtAirlineCode;
	private JTextField txtAirlineName;

	private JButton btnSaveAirline;

	private int DEFAULT_WIDTH = 400;
	private int DEFAULT_HEIGHT = 40;

	private JPanel airlineCodePanel;
	private JPanel airlineNamePanel;
	private JPanel buttonPanel;

	private Airline airline = null;

	/**
	 * Create the panel.
	 */
	public AirlineGUI() {
		buildGUI();
	}

	public void buildGUI() {
		setLayout(new GridLayout(1, 2));
		drawForm();
		initializeTable();
		add(formPanel);
		add(scrollPane);
	}

	public void drawForm() {

		formPanel = new JPanel();
		formPanel.setBorder(BorderFactory.createTitledBorder("Airlines Form"));
		formPanel.setLayout(new BorderLayout());

		lblAirlineCode = new JLabel("Airline Code");
		lblAirlineName = new JLabel("Airline Name");

		txtAirlineCode = new JTextField(5);
		txtAirlineName = new JTextField(10);

		btnSaveAirline = new JButton("Add Airline");
		btnSaveAirline.addActionListener(this);

		airlineCodePanel = new JPanel();
		airlineNamePanel = new JPanel();
		buttonPanel = new JPanel();

		airlineCodePanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		airlineCodePanel.setMaximumSize(airlineCodePanel.getPreferredSize());
		airlineCodePanel.setLayout(new FlowLayout());
		airlineCodePanel.add(lblAirlineCode);
		airlineCodePanel.add(txtAirlineCode);

		airlineNamePanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		airlineNamePanel.setMaximumSize(airlineNamePanel.getPreferredSize());
		airlineNamePanel.setLayout(new FlowLayout());
		airlineNamePanel.add(lblAirlineName);
		airlineNamePanel.add(txtAirlineName);

		buttonPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(btnSaveAirline);

		mainBox = Box.createVerticalBox();
		mainBox.add(airlineCodePanel);
		mainBox.add(airlineNamePanel);
		mainBox.add(buttonPanel);

		formPanel.add(mainBox, BorderLayout.CENTER);

	}

	private void initializeTable() {
		Object columnNames[] = { "Airline Code", "Airline Name" };
		tableModel = new DefaultTableModel(columnNames, 0);
		tableAirline = new JTable(tableModel);
		scrollPane = new JScrollPane(tableAirline);
		setUpTableData();
	}

	private void setUpTableData() {
		tableModel = (DefaultTableModel) tableAirline.getModel();
		tableModel.setRowCount(0);

		LinkedHashMap<String, Airline> list = DataHelper.Instance.getList(DataType.AIRLINE);
		Iterator<Airline> iterator = list.values().iterator();
		while (iterator.hasNext()) {
			Airline airline = iterator.next();
			Vector<Object> vector = returnObjectArray(airline);
			tableModel.addRow(vector);
		}
		tableAirline.repaint();
	}

	private Vector<Object> returnObjectArray(Airline airline2) {
		Vector<Object> vector = new Vector<>();
		vector.add(airline2.getAirlineCode());
		vector.add(airline2.getAirlineName());
		return vector;
	}

	@Override
	public void notify(Airline model, OpType opType) {
		setUpTableData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		airline = new Airline();
		airline.setAirlineCode(txtAirlineCode.getText());
		airline.setAirlineName(txtAirlineName.getText());
		airline.addObserver(this);

		if (e.getSource() == btnSaveAirline) {
			String result = validateControls(DataHelper.OpType.INSERT);
			if (result != null) {
				showError(result);
				return;
			} else {
				saveRecord(OpType.INSERT);
				return;
			}
		}

	}

	public String validateControls(OpType opType) {
		String airlineCode = airline.getAirlineCode();
		String airlineName = airline.getAirlineName();

		if (airlineCode.length() <= 0) {
			return "Airline Code can not be blank.";
		}

		if (airlineName.length() <= 0) {
			return "Airline Name can not be blank.";
		}

		boolean exists = DataHelper.Instance.containsKey(airlineCode, DataHelper.DataType.AIRLINE);
		if (exists && opType == OpType.INSERT) {
			return "Airline already exists in the system. No duplicates allowed";
		}

		return null;
	}

	public void saveRecord(OpType opType) {
		boolean isSaved = DataHelper.Instance.addObject(airline, airline.getAirlineCode(), DataType.AIRLINE, opType);
		if (isSaved) {
			JOptionPane.showMessageDialog(formPanel, "Record Saved", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
			clearForm();
		} else {
			JOptionPane.showMessageDialog(formPanel, "Unable to save the record", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearForm() {
		txtAirlineCode.setText(null);
		txtAirlineName.setText(null);
	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(formPanel, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
