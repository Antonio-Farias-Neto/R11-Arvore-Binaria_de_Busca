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
		return altura(this.root);
	}

	private int altura(BSTNode<T> no) {
		int result = -1;
		if (no.getData() != null) {
			result = 1 + Math.max(altura((BSTNode<T>)no.getLeft()), altura((BSTNode<T>)no.getRight()));
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		return busca(this.root, element);
	}

	private BSTNode<T> busca(BSTNode<T> no, T element) {
		BSTNode<T> result = new BSTNode<>();
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
			if (element != null) {
			BSTNode<T> no = this.root;
			inserir(no,element);
		}
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
		BSTNode<T> result = null;
		if (no.getData() != null) {
			BSTNode<T> nextMax = this.achaMaximo((BSTNode<T>)no.getRight());
			if (nextMax != null && nextMax.getData() != null) {
				result = nextMax;
			} else {
				result = no;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		return this.achaMinimo(this.root);
	}

	private BSTNode<T> achaMinimo(BSTNode<T> no) {
		BSTNode<T> result = null;
		if (no.getData() != null) {
			BSTNode<T> nextMin = this.achaMinimo((BSTNode<T>)no.getLeft());
			if (nextMin != null && nextMin.getData() != null) {
				result = nextMin;
			} else {
				result = no;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> no = search(element);
		if (no.getData() != null) {
			result = achaSucessor(no);
		}
		return result;
		
	}

	private BSTNode<T> achaSucessor(BSTNode<T> no) {
		BSTNode<T> result = null;
		if (no.getRight().getData() != null) {
			result = achaMinimo((BSTNode<T>)no.getRight());
		} else {
			BSTNode<T> pai = (BSTNode<T>)no.getParent();
			while (pai != null && no == pai.getRight()) {
				no = pai;
				pai = (BSTNode<T>)pai.getParent();
			}
			result = pai;
		}
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> no = search(element);
		if (no.getData() != null) {
			result = achaPredecessor(no);
		}
		return result;
	}

	private BSTNode<T> achaPredecessor(BSTNode<T> no) {
		BSTNode<T> result = null;
		if (no.getLeft().getData() != null) {
			result = this.achaMaximo((BSTNode<T>)no.getLeft());
		} else {
			BSTNode<T> pai = (BSTNode<T>)no.getParent();
			while (pai != null && no == pai.getLeft()) {
				no = pai;
				pai = (BSTNode<T>)pai.getParent();
			}
			result = pai; 
		}
		return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> no = this.search(element);
		if (no.getData() != null) {
			if (isLeaf(no)) {
				no.setData(null);
			} else if (temUmUnicoFilho(no)) {
				if (!isRoot(no)) {
					if (no == no.getParent().getLeft()) {
						if (no.getLeft().getData() != null) {
							no.getParent().setLeft(no.getLeft());
							no.getLeft().setParent(no.getParent());
						} else {
							no.getParent().setLeft(no.getRight());
							no.getRight().setParent(no.getParent());
						}
					} else {
						if (no.getLeft().getData() != null) {
							no.getParent().setRight(no.getLeft());
							no.getLeft().setParent(no.getParent());
						} else {
							no.getParent().setRight(no.getRight());
							no.getRight().setParent(no.getParent());
						}
					}
				} else {
					if (no.getLeft().getData() != null) {
						this.root = (BSTNode<T>)no.getLeft();
						this.root.setParent(null);
					} else {
						this.root = (BSTNode<T>)no.getRight();
						this.root.setParent(null);
					}
				}
			} else {
				BSTNode<T> sucessor = this.achaSucessor(no);
    			T sucessorData = sucessor.getData();
    			remove(sucessor.getData());
    			no.setData(sucessorData);
			}
		}
	}

	private boolean isLeaf(BSTNode<T> no) {
		return no.getLeft().getData() == null && no.getRight().getData() == null;
	}

	private boolean isRoot(BSTNode<T> no) {
		return no.getParent() == null;
	}

	private boolean temUmUnicoFilho(BSTNode<T> no) {
		return (no.getRight().getData() != null && no.getLeft().getData() == null) ||
		(no.getRight().getData() == null && no.getLeft().getData() != null);
	}

	@Override
	public T[] preOrder() {
		LinkedList<T> l = new LinkedList<>();
		listarEmPreOrdem(this.root,l);
		return (T[]) l.toArray(new Comparable[this.size()]);
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
		return (T[]) l.toArray(new Comparable[this.size()]); 
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
		return (T[]) l.toArray(new Comparable[this.size()]);
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
