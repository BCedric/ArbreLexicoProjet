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

}
