package ArbreLexicoProjet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

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
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class MonAppli {

	private JFrame frame;
	private ArbreLexicographique arbre;
	private JTextArea resultat;
	private JTree tree;
	private JEditorPane texte;
	private JFileChooser chooser;

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
		try{
			chooser = new JFileChooser(new File(".").getCanonicalFile());
		} catch(IOException e) {}
		initialize();
		arbre.setVue(tree);
		tree.setModel(arbre);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tree = new JTree();
		scrollPane.setViewportView(tree);
		
		JMenuItem mntmSauvegarder = new JMenuItem("Sauvegarder");
		mntmSauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-----------------------------------
				
				chooser.showOpenDialog(null);
				if(chooser.getSelectedFile()!=null) {
					arbre.sauve(chooser.getSelectedFile().toString());
					System.out.println("sauvegarde");
				}
				
				//-----------------------------------
			}
		});
		mnFichier.add(mntmSauvegarder);
		
		JMenuItem mntmCharger = new JMenuItem("Charger");
		mntmCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-----------------------------------
				chooser.showOpenDialog(null);
				try {
					arbre.charge(chooser.getSelectedFile().toString());
				} catch (Exception e1) {
					
				}
				expandOrCollapsePath(tree, new TreePath(arbre.getRoot()), true);
				
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
					resultat.setText("Le mot "+texte.getText()+" a bien �t� ajout�");
				else resultat.setText("Le mot "+texte.getText()+" n'a pas �t� ajout�");
				expandOrCollapsePath(tree, new TreePath(arbre.getRoot()), true);
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
				expandOrCollapsePath(tree, new TreePath(arbre.getRoot()), true);
				
				//-------------------------------------------
			}
		});
		
		panel.add(btnSupprimer);
		
		
	}
	
	  /**
	   * Permet d'ouvrir ou de fermer tous les chemins de l'arbre � partir d'un noeud
	   */
	  @SuppressWarnings("unchecked")
	  public static void expandOrCollapsePath(JTree tree, TreePath parent, boolean expand)
	  {
	    // Traverse children
	    TreeNode node = (TreeNode) parent.getLastPathComponent();
	    if (node.getChildCount() >= 0)
	    {
	      for(Enumeration<TreeNode> enm = node.children(); enm.hasMoreElements();)
	      {
	        TreeNode n = enm.nextElement();
	        TreePath path = parent.pathByAddingChild(n);
	        expandOrCollapsePath(tree, path, expand);
	      }
	    }
	 
	    //
	    if (expand)
	      tree.expandPath(parent);
	    else
	      tree.collapsePath(parent);
	  }

}
