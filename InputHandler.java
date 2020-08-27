import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InputHandler implements ActionListener, KeyListener {
	
	
	private class HandlerClass implements ItemListener {
		
		public void itemStateChanged(ItemEvent event) {
			if (showCalculationsBox.isSelected()) {
				showCalculationSteps = true;
			} else {
				showCalculationSteps = false;
			}
		}
	}
	
	
	//variables
	String title = "GMA-GeneratorMatrixApplication";
	String linearCodes = "";
	String topText = "Generator Matrix";
	
	int scale = 3;
	int height = 100 * scale;
	int width = height * 16 / 9;
	
	boolean showCalculationSteps = false;
	
	
	
	
	//classes
	JFrame frame;
	JPanel panel;
	JPanel rightPanel;
	JPanel leftPanel;
	JPanel subPanel;
	JPanel matrixPanel;
	JLabel topLabel;
	JLabel consoleTitle;
	JLabel consoleLabel;
	JTextField inputField;
	JTextArea codesArea;
	JScrollPane scrollWindow;
	JScrollPane matrixWindow;
	MatrixRow matrixrow;
	Calculator calculator;
	
	JButton plusButton;
	JButton generateButton;
	JButton exportButton;
	JButton closeRowButton;
	
	JCheckBox showCalculationsBox;
	
	public ArrayList<MatrixRow> matrixRows;
	public ArrayList<String> binaryNumbers;
	
	GridBagConstraints constraints;
	GridBagConstraints subConstraints;
	GridBagConstraints matrixConstraints;
	GridBagConstraints leftConstraints;
	GridBagConstraints rightConstraints;
	
	
	public InputHandler() {
		
		
		constraints = new GridBagConstraints();
		subConstraints = new GridBagConstraints();
		matrixConstraints = new GridBagConstraints();
		leftConstraints = new GridBagConstraints();
		rightConstraints = new GridBagConstraints();
		
		binaryNumbers = new ArrayList<String>();
		
		matrixRows = new ArrayList<MatrixRow>();
		
		
		frame = new JFrame();
		frame.addKeyListener(this);
		
		panel = new JPanel(new GridBagLayout());
		panel.addKeyListener(this);
		frame.add(panel, BorderLayout.CENTER);
		
		
		rightPanel = new JPanel(new GridBagLayout());
		constraints.gridy = 0;
		constraints.gridx = GridBagConstraints.WEST;
		constraints.weightx = 0.25;
		rightPanel.addKeyListener(this);
		panel.add(rightPanel, constraints);
		
		
		leftPanel = new JPanel(new GridBagLayout());
		constraints.gridy = 0;
		constraints.gridx = GridBagConstraints.EAST;
		constraints.weightx = 0.25;
		leftPanel.addKeyListener(this);
		panel.add(leftPanel, constraints);
		
		
		topLabel = new JLabel(topText, JLabel.CENTER);
		topLabel.addKeyListener(this);
		leftConstraints.gridx = 0;
		leftConstraints.gridy = 0;
		leftPanel.add(topLabel, leftConstraints);
		
		
		matrixPanel = new JPanel(new GridBagLayout());
		matrixPanel.addKeyListener(this);
		
		
		matrixrow = new MatrixRow(this);
		matrixRows.add(matrixrow);
		matrixConstraints.gridx = 0;
		matrixConstraints.gridy = matrixRows.size();
		matrixPanel.add(matrixrow, matrixConstraints);
		
		
		matrixWindow = new JScrollPane(matrixPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		matrixWindow.setPreferredSize(new Dimension(250, 200));
		matrixWindow.addKeyListener(this);
		leftConstraints.gridx = 0;
		leftConstraints.gridy = 1;
		leftPanel.add(matrixWindow, leftConstraints);
		
		
		plusButton = new JButton("new row");
		plusButton.setPreferredSize(new Dimension(200 , 20));
		plusButton.addActionListener(this);
		plusButton.addKeyListener(this);
		leftConstraints.gridy = 2;
		leftPanel.add(plusButton, leftConstraints);
		
		
		showCalculationsBox = new JCheckBox("show calculations");
		showCalculationsBox.setPreferredSize(new Dimension(200, 20));
		HandlerClass handler = new HandlerClass();
		showCalculationsBox.addItemListener(handler);
		showCalculationsBox.addKeyListener(this);
		leftConstraints.gridy = 3;
		leftPanel.add(showCalculationsBox, leftConstraints);
		
		
		consoleTitle = new JLabel("console", JLabel.CENTER);
		consoleTitle.setPreferredSize(new Dimension(100, 20));
		consoleTitle.setOpaque(true);
		consoleTitle.setBackground(Color.gray);
		consoleTitle.addKeyListener(this);
		rightConstraints.gridx = 0;
		rightConstraints.gridy = 0;
		rightPanel.add(consoleTitle, rightConstraints);	
		
		
		consoleLabel = new JLabel("", JLabel.CENTER);
		consoleLabel.setPreferredSize(new Dimension(200, 50));
		consoleLabel.setOpaque(true);
		consoleLabel.setBackground(Color.lightGray);
		consoleLabel.addKeyListener(this);
		rightConstraints.gridx = 0;
		rightConstraints.gridy = 1;
		rightPanel.add(consoleLabel, rightConstraints);	
		
		
		subPanel = new JPanel(new GridBagLayout());
		subPanel.addKeyListener(this);
		rightConstraints.gridx = 0;
		rightConstraints.gridy = 2;
		rightPanel.add(subPanel, rightConstraints);
		
		
		generateButton = new JButton("generate");
		generateButton.setPreferredSize(new Dimension(100, 20));
		generateButton.addKeyListener(this);
		generateButton.addActionListener(this);
		subConstraints.gridx = GridBagConstraints.EAST;
		subPanel.add(generateButton, subConstraints);
		
		
		exportButton = new JButton("export");
		exportButton.setPreferredSize(new Dimension(100, 20));
		exportButton.addKeyListener(this);
		exportButton.addActionListener(this);
		subConstraints.gridx = GridBagConstraints.WEST;
		subConstraints.weightx = 0.5;
		subPanel.add(exportButton, subConstraints);
		
		
		codesArea = new JTextArea();
		codesArea.setLineWrap(true);
		
		scrollWindow = new JScrollPane(codesArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollWindow.addKeyListener(this);
		scrollWindow.setPreferredSize(new Dimension(200, 150));
		rightConstraints.gridx = 0;
		rightConstraints.gridy = 3;
		rightPanel.add(scrollWindow, rightConstraints);
		
		
		//frame.addKeyListener(this);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.pack();
		frame.setVisible(true);
		
	}
	

	
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == plusButton) {
			
			addMatrixRow();
			
		} else if (e.getSource() == generateButton) {
			consoleLabel.setText("");
			
			
			binaryNumbers.clear();
			for (int i = 0; i < matrixRows.size(); i++) {
				String binaryNumber = matrixRows.get(i).inputField.getText();
				binaryNumbers.add(binaryNumber);
			}
			
			if (checkLengths(binaryNumbers)) {
				if (checkCharacters(binaryNumbers)) {
					
					linearCodes = "";
					
					if (binaryNumbers.get(0).length() < 1) {
						consoleLabel.setText("<html>The codewords can't be <br> generated if the rows are empty.</html>");
						codesArea.setText("");
					} else {
						calculator = new Calculator();
						String firstElementCalculation = "";
						if (showCalculationSteps) {
							firstElementCalculation += binaryNumbers.get(0) + " + " + binaryNumbers.get(0) + " = ";
						}
						
						linearCodes += firstElementCalculation;
						linearCodes += calculator.calculate(binaryNumbers, showCalculationSteps);

						codesArea.setText(linearCodes);
						
						calculator = null; 
						
						consoleLabel.setText("Generation successful.");
					}
					
					
					
				} else {
					consoleLabel.setText("<html>The characters in the <br> codewords must be 1 or 0.</html>");
					codesArea.setText("");
				}
				
			} else {
				consoleLabel.setText("<html>The codewords in the matrix <br> must have an equal length.</html>");
				codesArea.setText("");
			}
			
		} else if (e.getSource() == exportButton) {
			
			String fileName = JOptionPane.showInputDialog("Type the name of the file.");
			if (fileName.length() <= 0) {
				consoleLabel.setText("<html>The text can't be exported <br> if the filename is empty.</html>");
			} else {
				//export linear codes to .txt file
				fileName += ".txt";
				try {
					PrintWriter outputStream = new PrintWriter(fileName);
					outputStream.println(linearCodes);
					outputStream.close();
				} catch (FileNotFoundException e1) {
					consoleLabel.setText("<html>Exporting the file <br> wasn't possible.</html>");
					e1.printStackTrace();
				}
			}
			
		}
	}
	
	public void addMatrixRow () {
		matrixrow = new MatrixRow(this);
		matrixRows.add(matrixrow);
		matrixConstraints.gridx = 0;
		matrixConstraints.gridy = matrixRows.size();
		
		matrixPanel.add(matrixrow, matrixConstraints);
		
		
		panel.revalidate();
		panel.repaint();
	}
	
	public void deleteMatrixRow(MatrixRow matrixRow) {
		
		int i = matrixRows.size();
		
		if (i > 1) {
			matrixRows.remove(matrixRow);
		    matrixPanel.remove(matrixRow);
			panel.revalidate();
			panel.repaint();
		} 
		
		
	}
	
	public boolean checkLengths (ArrayList<String> inputCodes) {
		int codeLength = inputCodes.get(0).length();
		for (String code : inputCodes) {
			if (code.length() != codeLength) return false;
		}
		return true;
	}
	
	public boolean checkCharacters (ArrayList<String> inputCodes) {
		for (String code : inputCodes) {
			for (int j = 0; j < code.length(); j++) {
				if (code.charAt(j) != '1' && code.charAt(j) != '0') return false;
			}
		}
		return true;
	}



	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) { 
			addMatrixRow();
		}
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}