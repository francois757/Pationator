package lePationator.domain;

import lePationator.domain.Patio;


public class gestionnaireUndoRedo implements java.io.Serializable{

	private noeud indiceCourant = null;
	private noeud NoeudParent = new noeud();

        
        
	public noeud getNodeParent() {
        return this.NoeudParent;
    }
        public noeud getNodeIndex() {
        return this.indiceCourant;
    }
        public void setNodeParent(noeud node) {
            this.NoeudParent=node;
        }


	public gestionnaireUndoRedo(){

		indiceCourant = NoeudParent;

	}

	public gestionnaireUndoRedo(gestionnaireUndoRedo manager){
		this();
		indiceCourant = manager.indiceCourant;
	}

	public void clear(){
		indiceCourant = NoeudParent;
	}

	public void addChangeable(Patio changeable){
		noeud node = new noeud(changeable);
		indiceCourant.right = node;
		node.left = indiceCourant;
		indiceCourant = node;
	}

	public boolean canUndo(){
		return indiceCourant != NoeudParent;
	}

	public boolean canRedo(){
		return indiceCourant.right != null;
	}

	public void undo(){
		if ( !canUndo() ){
			throw new IllegalStateException("Impossible de faire Undo");
		}
		indiceCourant.changeable.undoPatio();
		moveLeft();
	}

	private void moveLeft(){
		if ( indiceCourant.left == null ){
			throw new IllegalStateException("Internal index set to null.");
		}
		indiceCourant = indiceCourant.left;

	}

	private void moveRight(){
		if ( indiceCourant.right == null ){
			throw new IllegalStateException("Internal index set to null.");
		}
		indiceCourant = indiceCourant.right;
	}

	public void redo(){
		if ( !canRedo() ){

			throw new IllegalStateException("Cannot redo. Index is out of range.");
		}
		moveRight();
		indiceCourant.changeable.redoPatio();

	}
        
        public Patio statusPatio(){
            return indiceCourant.changeable;
        }


	private class noeud {

		private noeud left = null;
		private noeud right = null;
		private final Patio changeable;

		public noeud(Patio c){

			changeable = c;

		}

		public noeud(){

			changeable = null;

		}
	}
}