package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return ehIgual((BSTNode<T>)tree1.getRoot(), (BSTNode<T>)tree2.getRoot());
	}

	private boolean ehIgual(BSTNode<T> no1, BSTNode<T> no2) {
		boolean result = true;
		if (no1.isEmpty() && !no2.isEmpty() || !no1.isEmpty() && no2.isEmpty()) {
			result = false;
		} else if (!no1.isEmpty() && !no2.isEmpty()) {
			if (!no1.getData().equals(no2.getData())) {
				result = false;
			} else {
				result = ehIgual((BSTNode<T>)no1.getLeft(), (BSTNode<T>)no2.getLeft()) && ehIgual((BSTNode<T>)no1.getRight(), (BSTNode<T>)no2.getRight());
			}
		}
		return result;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return ehSimilar((BSTNode<T>)tree1.getRoot(), (BSTNode<T>)tree2.getRoot());
	}

	private boolean ehSimilar(BSTNode<T> no1, BSTNode<T> no2) {
		boolean result = true;
		if (no1.isEmpty() && !no2.isEmpty() || !no1.isEmpty() && no2.isEmpty()) {
			result = false;
		} else if (!no1.isEmpty() && !no2.isEmpty()) {
			result = ehSimilar((BSTNode<T>)no1.getLeft(),(BSTNode<T>) no2.getLeft()) && ehSimilar((BSTNode<T>)no1.getRight(),(BSTNode<T>) no2.getRight());
		}
		return result;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		return kEsimoMenor((BSTNode<T>) tree.getRoot(), k);
	}

	private T kEsimoMenor(BSTNode<T> no, int k) {
		T result = null;
		if (!no.isEmpty()) {
			int tamanhoEsquerda = size((BSTNode<T>)no.getLeft());
			if (k == tamanhoEsquerda + 1) {
				result = no.getData();
			} else if (k <= tamanhoEsquerda) {
				result = kEsimoMenor((BSTNode<T>)no.getLeft(), k);
			} else {
				result = kEsimoMenor((BSTNode<T>)no.getRight(), k - tamanhoEsquerda - 1);
			}
		}
		return result;	
	}

	private int size(BSTNode<T> no) {
		int result = 0;
		if (!no.isEmpty()) {
			result = 1 + size((BSTNode<T>) no.getLeft()) + size((BSTNode<T>) no.getRight());
		}
		return result;
	}

}
