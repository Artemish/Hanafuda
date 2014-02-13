package hanafuda.server.networking;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class ServerGUI {
	
	static JFrame serverFrame;
	private static JLabel status;
	private static JPanel serverPanel;
	
	protected static void initialize() {
		serverFrame = new JFrame();
		serverFrame.setTitle("Hanafuda Server");
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverFrame.setSize(400, 50);
		
		serverPanel = new JPanel();
		serverPanel.setLayout(new BorderLayout());
		
		status = new JLabel("Initializing server GUI.");
		status.setPreferredSize(new Dimension(300, 40));
		
		serverPanel.add(status, BorderLayout.CENTER);
		serverFrame.add(serverPanel, BorderLayout.CENTER);
		
		serverFrame.pack();
		serverFrame.setVisible(true);
		
	}
	
	static void setStatus(String message) {
		status.setText(message);
	}
	
}
