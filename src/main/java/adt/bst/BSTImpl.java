package adt.bst;
import java.util.LinkedList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> search(T element) {
		return busca(this.root, element);
	}

	private BSTNode<T> busca(BSTNode<T> no, T element) {
		BSTNode<T> result = no;
		if (no.getData() == null || element.equals(no.getData())) {
			result = no;
		} else if (element.compareTo(no.getData()) < 0) {
			result = busca((BSTNode<T>)no.getLeft(), element);
		} else {
			result = busca((BSTNode<T>)no.getRight(), element);
		}
		return result;
	}


	@Override
	public void insert(T element) {
		BSTNode<T> no = this.root;
		inserir(no,element);
	}

	private void inserir(BSTNode<T> no, T element) {
		if (no.getData() == null) {
			no.setData(element);
			BSTNode<T> nilEsquerdo = new BSTNode<>();
			no.setLeft(nilEsquerdo );
			BSTNode<T> nilDireito = new BSTNode<>();
			no.setRight(nilDireito);
			nilEsquerdo.setParent(no);
			nilDireito.setParent(no);
		} else if (element.compareTo(no.getData()) < 0) {
			inserir((BSTNode<T>)no.getLeft(), element);
		} else {
			inserir((BSTNode<T>)no.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return achaMaximo(this.root);
	}

	private BSTNode<T> achaMaximo(BSTNode<T> no) {
		BSTNode<T> result = no;
		if (no.getData() != null) {
			BSTNode<T> nextMax = this.achaMaximo((BSTNode<T>)no.getRight());
			if (nextMax.getData() != null) {
				result = nextMax;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		return this.achaMinimo(this.root);
	}

	private BSTNode<T> achaMinimo(BSTNode<T> no) {
		BSTNode<T> result = no;
		if (no.getData() != null) {
			BSTNode<T> nextMin = this.achaMinimo((BSTNode<T>)no.getLeft());
			if (nextMin.getData() != null) {
				result = nextMin;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		LinkedList<T> l = new LinkedList<>();
		listarEmPreOrdem(this.root,l);
		return (T[]) l.toArray();
	}

	private void listarEmPreOrdem(BSTNode<T> no,LinkedList<T> l ) {
		if (no.getData() != null) {
			l.add(no.getData());
			listarEmPreOrdem((BSTNode<T>) no.getLeft(), l);
			listarEmPreOrdem((BSTNode<T>) no.getRight(), l);
		}
	}

	@Override
	public T[] order() {
		LinkedList<T> l = new LinkedList<>();
		listarEmOrdem(this.root,l);
		return (T[]) l.toArray(); 
	}

	private void listarEmOrdem(BSTNode<T> no, LinkedList<T> l) {
		if (no.getData() != null) {
			listarEmOrdem((BSTNode<T>)no.getLeft(), l);
			l.add(no.getData());
			listarEmOrdem((BSTNode<T>)no.getRight(), l);
		}
	}

	@Override
	public T[] postOrder() {
		LinkedList<T> l = new LinkedList<>();
		listarEmPosOrdem(this.root,l);
		return (T[]) l.toArray();
	}

	private void listarEmPosOrdem(BSTNode<T> no,LinkedList<T> l ) {
		if (no.getData() != null) {
			listarEmPosOrdem((BSTNode<T>) no.getLeft(), l);
			listarEmPosOrdem((BSTNode<T>) no.getRight(), l);
			l.add(no.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
