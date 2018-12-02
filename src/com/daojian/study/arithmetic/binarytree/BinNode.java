package com.daojian.study.arithmetic.binarytree;

import java.util.Stack;

/**
 * @Description 二叉树节点
 * @author wangdaojian
 * @date  2018年12月2日 上午11:42:14
 */
public class BinNode<T> {
	BinNode<T> parent, lc, rc; //父亲 左右孩子节点
	T data; //数据域
	int height; //高度
	
	public BinNode() {}
	
	public BinNode(T data, BinNode<T> parent) {
		this.data = data;
		this.parent = parent;
	}
	
	/**
	* @Description  作为左孩子插入节点 
	* @param @param data
	* @param @return
	* @return BinNode<T>    返回类型
	 */
	BinNode<T> insertAsLC(T data) {
		return lc = new BinNode<T>(data, this);
	}
	
	/**
	* @Description 作为右孩子插入节点
	* @param @param data
	* @param @return
	* @return BinNode<T>    返回类型
	 */
	BinNode<T> insertAsRC(T data) {
		return rc = new BinNode<T>(data, this);
	}
	
	/**
	* @Description 子树规模
	* @param @return
	* @return int    返回类型
	 */
	int size() {
		int s=1;
		if(lc != null) s += lc.size();
		if(rc != null) s += rc.size();
		return s;
	}
	
	/**
	* @Description (中序遍历意义下) 当前节点的直接后继
	* @return BinNode<T>    返回类型
	 */
	BinNode<T> succ() {
		return null;
	}
	
	/**
	* @Description 非递归方式 子树先序遍历 
	* @param 
	* @return void    返回类型
	 */
	void travPre() {
		StringBuffer sb = new StringBuffer();
		Stack<BinNode<T>> s = new Stack<>();
		BinNode<T> x = this;
		s.push(x);
		while(!s.isEmpty()) {
			x = s.pop();
			sb.append(x.data + ", ");
			if(x.rc != null) s.push(x.rc);
			if(x.lc != null) s.push(x.lc);
		}
		System.out.println(sb.substring(0, sb.length() - 2));
	}
	
	/**
	* @Description 子树层次遍历
	* @param 
	* @return void    返回类型
	 */
	void travLevel() {
		
	}
	
	
	/**
	* @Description 子树中序遍历
	* @param 
	* @return void    返回类型
	 */
	void travIn() {
		
	}
	
	/**
	* @Description 子树后序遍历
	* @param @return
	* @return viod    返回类型
	 */
	void travPost() {
		
	}
}
