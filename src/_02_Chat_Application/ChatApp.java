package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
	JFrame frame = new JFrame("Messanger");
	JTextField messanger = new JTextField(50);
	JButton send = new JButton("Send");
	JPanel panel = new JPanel();
	JLabel label = new JLabel("Type here");
	String message = "";
	String message2 = "";

	ChatServer cs;
	ChatClient cc;

	public static void main(String[] args) {
		ChatApp ca = new ChatApp();
		ca.gui();
	}

	public void gui() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!",
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			cs = new ChatServer(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null,"Server started at: " + cs.getIPAddress() + "\nPort: " + cs.getPort());
			
			panel.add(label);
			panel.add(messanger);
			panel.add(send);
			
			
			frame.add(panel);
			send.addActionListener((e)-> {
				message += messanger.getText();
				//need to display on the text area
				messanger.setText("");
				cs.sendMessage(message);
			});
			frame.setVisible(true);
			frame.setSize(550,300);
			String message = messanger.getText();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			cs.start();

		} else {
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			cc = new ChatClient(ipStr, port);
		
			//gui does not display on the client screen 
			panel.add(label);
			panel.add(messanger);
			panel.add(send);
			add(panel);
			setSize(550,300);
			
			send.addActionListener((e)->{
				message2 = messanger.getText();
				//need to display on the text area
				messanger.setText("");
				cc.sendMessage(message2);
			});
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			cc.start();
		}
	}
}
