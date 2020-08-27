import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MatrixRow extends JPanel implements ActionListener, KeyListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//variables
	int buttonSize = 20;
	int inputFieldSize = 14;
	
	
	//classes
	private InputHandler inputHandler;
	
	public JTextField inputField;
	JButton deleteButton;
	
	public MatrixRow(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
		GridBagConstraints gbc = new GridBagConstraints();
		
		inputField = new JTextField("", inputFieldSize);
		inputField.addKeyListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(inputField, gbc);
		
		deleteButton = new JButton("X");
		deleteButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
		deleteButton.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(deleteButton, gbc);
		
	}
	
	//@Override //don't override from panel
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteButton) {
			this.inputHandler.deleteMatrixRow(this);
			return;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) { 
			inputHandler.addMatrixRow();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
