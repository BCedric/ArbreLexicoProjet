package ArbreLexicoProjet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTree;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class MonAppli {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonAppli window = new MonAppli();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MonAppli() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JTree tree = new JTree();
		frame.getContentPane().add(tree, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnAjouter = new JButton("Ajouter");
		panel.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		panel.add(btnSupprimer);
		
		JEditorPane dtrpnSqldnln = new JEditorPane();
		panel.add(dtrpnSqldnln);
		
		JTextArea txtrSdqjhsjqhdslqhdlqhdlskqhdklh = new JTextArea();
		txtrSdqjhsjqhdslqhdlqhdlskqhdklh.setText("sdqjhsjqhdslqhdlqhdlskqhdklh");
		frame.getContentPane().add(txtrSdqjhsjqhdslqhdlqhdlskqhdklh, BorderLayout.SOUTH);
	}

}
