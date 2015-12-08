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

public aspect Visualisation {
	
	declare parents : ArbreLexicographique implements TreeModel;
	private DefaultTreeModel  ArbreLexicographique.model;
	private JTree ArbreLexicographique.vue;
	
	declare parents : NoeudAbstrait implements TreeNode;
	private DefaultMutableTreeNode NoeudAbstrait.treeNode;
	
	public void ArbreLexicographique.setVue(JTree jt){
		this.vue = jt;
	}
	
	pointcut deserialiser() : call(void ArbreLexicographique.charge(String) );
	
	pointcut initArbreLexico(ArbreLexicographique a) : target(a) && execution(ArbreLexicographique.new());
	after(ArbreLexicographique a) : initArbreLexico(a){
		a.model = new DefaultTreeModel(a.entree.treeNode);
	}
	
	
	pointcut ajoutArbreLexico(ArbreLexicographique a) : 
		target(a) && (
				execution(boolean ArbreLexicographique.ajout(String)) || 
				execution(boolean ArbreLexicographique.suppr(String))
				);
	after(ArbreLexicographique a) : ajoutArbreLexico(a){
		a.model.setRoot(a.entree.treeNode);
	}
	
	
	
	pointcut initNoeudAbstrait(NoeudAbstrait n) : target(n) && execution(NoeudAbstrait.new(NoeudAbstrait));
	before(NoeudAbstrait n) : initNoeudAbstrait(n){
		n.treeNode = new DefaultMutableTreeNode();
	}
	
	
	
	pointcut ajoutLettre(NoeudAbstrait n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : target(n) && args(frere, fils, val) && execution(Noeud.new(NoeudAbstrait, NoeudAbstrait, char));
	after(NoeudAbstrait n, NoeudAbstrait frere, NoeudAbstrait fils, char val) : ajoutLettre(n, frere,  fils,  val){
		System.out.println(val);
		n.treeNode = new DefaultMutableTreeNode();
		n.treeNode.add(fils.treeNode);
	}
	
	pointcut ajoutsurNoeud(Noeud n) : target(n) && (call(NoeudAbstrait Noeud.ajout(String)));
	
	before(Noeud n) : ajoutsurNoeud(n){
		
	}
	
	
	
	
	after() : deserialiser(){
		
		System.out.println("chargement");
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
