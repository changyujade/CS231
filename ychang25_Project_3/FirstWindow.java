import javax.swing.*;
import java.awt.event.*;

public class FirstWindow implements ActionListener {
	
	JButton myButton;
	
	public static void main(String[] args) {
		(new FirstWindow()).go();	
	}
	
	public void go() {
		JFrame myFrame = new JFrame();
		myButton = new JButton("Hit Me");
		
		// myButton.addActionListener(Sudoku.actionPerformed());
        
		
		myFrame.getContentPane().add(myButton);
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		myFrame.setSize(200,200);
		myFrame.setVisible(true);		
	}

    public void actionPerformed(ActionEvent event) {
		myButton.setText("Got Hit");
        // myButton.Sudoku.sudoku();
	}
	
	
}