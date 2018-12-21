package com.daojian.study.arithmetic.binarytree;

public class Test {
	public static void main(String[] args) {
		BinNode<Integer> root = new BinNode<>(0, null);
		BinTree<Integer> tree = new BinTree<>(root);
		BinNode<Integer> n1 = tree.insertAsLC(root, 1);
		BinNode<Integer> n2 = tree.insertAsRC(root, 2);
		tree.insertAsLC(n1, 3);
		tree.insertAsRC(n1, 4);
		tree.insertAsLC(n2, 5);
		System.out.println(n2.succ());
		
		tree.traversePre(root);
		System.out.println();
		tree.travPre();
		tree.travPre_I2();
		tree.traverseIn(root);
		System.out.println();
		tree.travIn();
		tree.traversePost(root);
		System.out.println();
		tree.travPost();
		tree.travLevel();
	}
}
