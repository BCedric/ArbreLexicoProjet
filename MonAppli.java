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
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MonAppli {

	private JFrame frame;
	private ArbreLexicographique arbre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonAppli window = new MonAppli(new ArbreLexicographique());
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
	public MonAppli(ArbreLexicographique arbre) {
		
		initialize(arbre);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArbreLexicographique arbre) {
		//------------------------------------
				this.arbre = arbre;
				
		//------------------------------------
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmSauvegarder = new JMenuItem("Sauvegarder");
		mntmSauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-----------------------------------
				System.out.println("sauvegarde");
				arbre.sauve("coucou");
				//-----------------------------------
			}
		});
		mnFichier.add(mntmSauvegarder);
		
		JMenuItem mntmCharger = new JMenuItem("Charger");
		mntmCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-----------------------------------
				try {
					arbre.charge("coucou");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//-----------------------------------
			}
		});
		
		mnFichier.add(mntmCharger);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		mnFichier.add(mntmQuitter);
		
		JTree tree = new JTree();
		frame.getContentPane().add(tree, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JEditorPane texte = new JEditorPane();
		panel.add(texte);
		
		JTextArea resultat = new JTextArea();
		frame.getContentPane().add(resultat, BorderLayout.SOUTH);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//-------------------------------------------
				if(arbre.ajout(texte.getText()))
					resultat.setText("Le mot "+texte.getText()+" a bien �t� ajout�");
				else resultat.setText("Le mot "+texte.getText()+" n'a pas �t� ajout�");
				
				//-------------------------------------------
				
			}
		});
		panel.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//-------------------------------------------
				if(arbre.suppr(texte.getText()))
					resultat.setText("Le mot "+texte.getText()+" a bien �t� supprim�");
				else resultat.setText("Le mot "+texte.getText()+" n'a pas �t� supprim�");
				
				//-------------------------------------------
			}
		});
		//------------------------------------
		this.arbre = arbre;
		arbre.setVue(tree);
		tree.setModel(arbre);
		//------------------------------------
		panel.add(btnSupprimer);
		
		
	}

}
