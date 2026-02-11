package adt.bst;

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
		
	}


	@Override
	public void insert(T element) {
		BSTNode<T> aux =this.root;
		inserir(aux,element);
	}

	private void inserir(BSTNode<T> aux, T element) {
		if (aux.getData() == null) {
			aux.setData(element);
			BSTNode<T> nilEsquerdo = new BSTNode<>();
			aux.setLeft(nilEsquerdo );
			BSTNode<T> nilDireito = new BSTNode<>();
			aux.setRight(nilDireito);
			nilEsquerdo.setParent(aux);
			nilDireito.setParent(aux);
		} else if (element.compareTo(aux.getData()) < 0) {
			inserir((BSTNode<T>)aux.getLeft(), element);
		} else {
			inserir((BSTNode<T>)aux.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return achaMaximo(this.root);
	}

	private BSTNode<T> achaMaximo(BSTNode<T> aux) {
		
	}

	@Override
	public BSTNode<T> minimum() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
