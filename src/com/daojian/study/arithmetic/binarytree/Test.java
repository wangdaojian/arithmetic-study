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
		
		tree.traversePre(root);
		System.out.println();
		tree.travPre();
	}
}
