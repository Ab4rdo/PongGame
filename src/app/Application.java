package app;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Application extends JFrame{

	public Application() {
		initUI();
	}
	
	private void initUI() {
		add(new Board());
		
		setTitle("Pong Game");
		
		setResizable(false);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) throws Exception{
		
		Sound.playMainSound("sound/808_State_-_Pacific_State.wav");
		        
		EventQueue.invokeLater(()->{
			Application exe = new Application();
			exe.setVisible(true);
		});
	}
}
