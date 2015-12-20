package ArbreLexicoProjet;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.JTree;

import java.util.Enumeration;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;

public privileged aspect Visualisation {
	
	declare parents : ArbreLexicographique implements TreeModel;
	private DefaultTreeModel  ArbreLexicographique.model;
	private JTree ArbreLexicographique.vue;
	
	declare parents : NoeudAbstrait implements TreeNode;
	private DefaultMutableTreeNode NoeudAbstrait.treeNode;
	
	public void ArbreLexicographique.setVue(JTree jt){
		this.vue = jt;
	}
	
	pointcut deserialiser() : call(void ArbreLexicographique.charge(String) );
	
	// initialisation de la racine
	pointcut initArbreLexico(ArbreLexicographique a) : target(a) && execution(ArbreLexicographique.new());
	after(ArbreLexicographique a) : initArbreLexico(a){
		a.model = new DefaultTreeModel(new DefaultMutableTreeNode());
	}
	
	//modification de la racine
	pointcut modifRacine(ArbreLexicographique n) : this(n) && set(NoeudAbstrait ArbreLexicographique.entree);
	
	
	
	// modifictaion de la racine lors de l'ajout
	pointcut modifRacineAjout(ArbreLexicographique n) : target(n) && modifRacine(ArbreLexicographique) && withincode(boolean ArbreLexicographique.ajout(String));
	after(ArbreLexicographique n) : modifRacineAjout(n){
		((DefaultMutableTreeNode) n.model.getRoot()).insert(n.entree.treeNode, 0);
		((DefaultTreeModel)n.model).reload();
	}
	
	// modifictaion de la racine lors de la suppression
	pointcut modifRacineSuppr(ArbreLexicographique n) : target(n) && modifRacine(ArbreLexicographique) && withincode(boolean ArbreLexicographique.suppr(String));
	before(ArbreLexicographique n) : modifRacineSuppr(n){
		((DefaultMutableTreeNode)n.getRoot()).remove(n.entree.treeNode);
	}
	after(ArbreLexicographique n) : modifRacineSuppr(n){
		if(n.entree != NoeudVide.getInstance()){
			((DefaultMutableTreeNode) n.model.getRoot()).insert(n.entree.treeNode, 0);
		}
		((DefaultTreeModel)n.model).reload();
	}
	
	
	//initialisation d'un noeud abstrait
	pointcut initNoeudAbstrait(NoeudAbstrait n) : target(n) && execution(NoeudAbstrait.new(NoeudAbstrait));
	before(NoeudAbstrait n) : initNoeudAbstrait(n){
		n.treeNode = new DefaultMutableTreeNode();
	}
	
	
	
	pointcut ajoutLettre(Noeud n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : target(n) && args(frere, fils, val) && execution(Noeud.new(NoeudAbstrait, NoeudAbstrait, char));
	after(Noeud n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : ajoutLettre(n, frere,  fils,  val){
		n.treeNode = new DefaultMutableTreeNode(Character.toString(val));
		n.treeNode.add(fils.treeNode);		
	}
	
	//modification d'un frere
	pointcut modifFrere(NoeudAbstrait n) : target(n) && set(NoeudAbstrait NoeudAbstrait.frere);
	
	//modification d'un fils
	pointcut modifFils(Noeud n, NoeudAbstrait n1) : this(n) && target(n1) && set(NoeudAbstrait Noeud.fils);
	
	//modification d'un frere dans la méthode ajout
	pointcut modifFrereAjout(NoeudAbstrait n) : target(n) && modifFrere(NoeudAbstrait) && withincode(NoeudAbstrait NoeudAbstrait+.ajout(String));
	after(NoeudAbstrait n) : modifFrereAjout(n){
		if(n.getParent() != null) ((MutableTreeNode) n.treeNode.getParent()).insert(n.frere.treeNode,0);
	}
	
	//modification d'un fils dans la méthode ajout
	pointcut modifFilsAjout(Noeud n) : target(n) &&  modifFils(Noeud, NoeudAbstrait) && withincode(NoeudAbstrait NoeudAbstrait+.ajout(String));
	after(Noeud n) : modifFilsAjout(n){
		n.treeNode.add(n.fils.treeNode);
	}
	
	//modification d'un fils dans la mï¿½thode suppr
	pointcut modifFilsSuppr(Noeud n) : target(n) &&  modifFils(Noeud, NoeudAbstrait) && withincode(NoeudAbstrait NoeudAbstrait+.suppr(String));
	before(Noeud n) : modifFilsSuppr(n){
		if(n.fils.treeNode.getParent() != null) n.treeNode.remove(n.fils.treeNode);
	}
	after(Noeud n) : modifFilsSuppr(n){
		if(n.fils != NoeudVide.getInstance()) n.treeNode.add(n.fils.treeNode);
	}
	
	//modification d'un frere dans la mï¿½thode ajout
	pointcut modifFrereSuppr(NoeudAbstrait n) : target(n) && modifFrere(NoeudAbstrait) && withincode(NoeudAbstrait NoeudAbstrait+.suppr(String));
	before(NoeudAbstrait n) : modifFrereSuppr(n){
		((MutableTreeNode)n.treeNode.getParent()).remove(n.frere.treeNode);
	}
	after(NoeudAbstrait n) : modifFrereSuppr(n){
		if(n.frere != NoeudVide.getInstance())
		((MutableTreeNode) n.treeNode.getParent()).insert(n.frere.treeNode, 0);
	}
	
	pointcut ajoutsurMarque(Marque m, String s) : target(m) && args(s) && execution(NoeudAbstrait Marque.ajout(String));
	after(Marque m, String s) : ajoutsurMarque(m, s){
		if(m.frere != NoeudVide.getInstance())
		((MutableTreeNode) m.treeNode.getParent()).insert(m.frere.treeNode,0);
	}
	
	pointcut supprsurMarque(Marque m, String s) : target(m) && args(s) && execution(NoeudAbstrait Marque.suppr(String));
	after(Marque m, String s) : supprsurMarque(m, s){
		if(s.isEmpty()){
			
			((MutableTreeNode)m.treeNode.getParent()).remove(m.treeNode);
		}
	}
	
	
	
	
	after() : deserialiser(){
		System.out.println("chargement");
	}
	
	private boolean contient(TreeNode treeNode, Noeud n){
		Enumeration e = treeNode.children();
		MutableTreeNode m;
		while(e.hasMoreElements()){
			m = (MutableTreeNode) e.nextElement();
			if(m.equals(n)) return true;
		}
		return false;
	}
	
	//------------------- IMPLEMENTATION ArbreLexicographique.TreeModel -----------------
	
	public void ArbreLexicographique.addTreeModelListener(TreeModelListener l) {
		this.model.addTreeModelListener(l);	
	}

	public Object ArbreLexicographique.getChild(Object parent, int index) {
		return this.model.getChild(parent, index);
	}

	public int ArbreLexicographique.getChildCount(Object parent) {
		return this.model.getChildCount(parent);
	}

	public int ArbreLexicographique.getIndexOfChild(Object parent, Object child) {
		return this.model.getIndexOfChild(parent, child);
	}

	public Object ArbreLexicographique.getRoot() {
		return this.model.getRoot();
	}

	public boolean ArbreLexicographique.isLeaf(Object node) {
		return this.model.isLeaf(node);
	}

	public void ArbreLexicographique.removeTreeModelListener(TreeModelListener l) {
		this.model.removeTreeModelListener(l);
	}

	public void ArbreLexicographique.valueForPathChanged(TreePath path, Object newValue) {
		this.model.valueForPathChanged(path, newValue);
	}
	
	//---------------------------------------------------------------------------------------
	
	//------------------- IMPLEMENTATION NoeudAbstrait.TreeNode -----------------
	
	public Enumeration NoeudAbstrait.children() {
		return this.treeNode.children();
	}

	public boolean NoeudAbstrait.getAllowsChildren() {
		return this.treeNode.getAllowsChildren();
	}

	public TreeNode NoeudAbstrait.getChildAt(int arg0) {
		return this.treeNode.getChildAt(arg0);
	}

	public int NoeudAbstrait.getChildCount() {
		return this.treeNode.getChildCount();
	}

	public int NoeudAbstrait.getIndex(TreeNode arg0) {
		return this.treeNode.getIndex(arg0);
	}

	public TreeNode NoeudAbstrait.getParent() {
		return this.treeNode.getParent();
	}

	public boolean NoeudAbstrait.isLeaf() {
		return this.treeNode.isLeaf();
	}

	public void NoeudAbstrait.insert(MutableTreeNode arg0, int arg1) {
		this.treeNode.insert(arg0, arg1);
	}

	public void NoeudAbstrait.remove(int arg0) {
		this.treeNode.remove(arg0);
		
	}

	public void NoeudAbstrait.remove(MutableTreeNode arg0) {
		this.treeNode.remove(arg0);
		
	}

	public void NoeudAbstrait.removeFromParent() {
		this.removeFromParent();
		
	}

	public void NoeudAbstrait.setParent(MutableTreeNode arg0) {
		this.treeNode.setParent(arg0);
		
	}

	public void NoeudAbstrait.setUserObject(Object arg0) {
		this.treeNode.setUserObject(arg0);		
	}
	
	//---------------------------------------------------------------------------------------

}
