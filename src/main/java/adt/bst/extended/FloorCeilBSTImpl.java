package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		BSTImpl<Integer> impl = new BSTImpl<>();
		for (Integer num: array) {
			impl.insert(num);
		}
		return achaFloor(impl.getRoot(),numero).getData();
	}

	private BSTNode<Integer> achaFloor(BSTNode<Integer> no, double numero) {
		BSTNode<Integer> result = new BSTNode<>();
		if (no.getData() != null) {
			if (no.getData() <= 0) {
				BSTNode<Integer> next = achaFloor((BSTNode<Integer>)no.getRight(), numero);
				if (next.getData() != null) {
					result = next;
				}
			} else {
				result = achaFloor((BSTNode<Integer>)no.getLeft(), numero);
			}
		}
		return result;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		BSTImpl<Integer> impl = new BSTImpl<>();
		for (Integer num: array) {
			impl.insert(num);
		}
		return achaCeil(impl.getRoot(),numero).getData();
	}

	private BSTNode<Integer> achaCeil(BSTNode<Integer> no, double numero) {
		BSTNode<Integer> result = new BSTNode<>();
		if (no.getData() != null) {
			if (no.getData() >= 0) {
				BSTNode<Integer> next = achaCeil((BSTNode<Integer>)no.getLeft(), numero);
				if (next.getData() != null) {
					result = next;
				}
			} else {
				result = achaCeil((BSTNode<Integer>)no.getLeft(), numero);
			}
		}
		return result;
	}

}
