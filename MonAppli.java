package ArbreLexicoProjet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;

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
	private JTextArea resultat;
	private JTree tree;
	private JEditorPane texte;

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
		arbre = new ArbreLexicographique();
		initialize();
		arbre.setVue(tree);
		tree.setModel((TreeModel) arbre);
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
		
		tree = new JTree();
		frame.getContentPane().add(tree, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		texte = new JEditorPane();
		panel.add(texte);
		
		resultat = new JTextArea();
		frame.getContentPane().add(resultat, BorderLayout.SOUTH);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//-------------------------------------------
				if(arbre.ajout(texte.getText()))
					resultat.setText("Le mot "+texte.getText()+" a bien été ajouté");
				else resultat.setText("Le mot "+texte.getText()+" n'a pas été ajouté");
				
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
					resultat.setText("Le mot "+texte.getText()+" a bien été supprimé");
				else resultat.setText("Le mot "+texte.getText()+" n'a pas été supprimé");
				
				//-------------------------------------------
			}
		});
		
		panel.add(btnSupprimer);
		
		
	}

}
