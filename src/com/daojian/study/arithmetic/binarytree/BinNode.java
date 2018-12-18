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
		BinNode<T> s = this;
		if(rc != null) {//若有右孩子，则直接后继必在右子树中
			s = rc;
			while(s.lc != null) //右子树中最小节点
				s = s.lc;
		} else {//否则 后继应是“将当前节点包含于其左子树中的最低祖先”
			while(isRChild(s)) //根节点是右则继续向上
				s = s.parent;
			s = s.parent;
		}
		return s;
	}
	
	private boolean isRChild(BinNode<T> x) {
		if(x.parent != null) {
			return x == x.parent.rc ? true : false;
		}else {
			return false;
		}
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
	
	
	private void visitAlongLeftBranch(BinNode<T> x, Stack<BinNode<T>> s, StringBuffer sb) {
		while(x != null) {
			sb.append(x.data).append(" ,");
			s.push(x.rc);
			x = x.lc;
		}
	}
	
	void travPre_I2() {
		Stack<BinNode<T>> s = new Stack<>();
		StringBuffer sb = new StringBuffer();
		BinNode<T> x = this;
		while(true) {
			visitAlongLeftBranch(x, s, sb);
			if(s.isEmpty()) break;
			x = s.pop();
		}
		System.out.println(sb.substring(0, sb.length()-2));
	}
	
	
	/**
	* @Description 子树层次遍历
	* @param 
	* @return void    返回类型
	 */
	void travLevel() {
		
	}
	
	
	private void goAlongLeftBranch(BinNode<T> x, Stack<BinNode<T>> s) {
		while(x != null) {
			s.push(x);
			x = x.lc;
		}
	}
	
	/**
	* @Description 子树中序遍历
	* @param 
	* @return void    返回类型
	 */
	void travIn() {
		Stack<BinNode<T>> s = new Stack<>();
		StringBuffer sb = new StringBuffer();
		BinNode<T> x = this;
		while(true) {
			goAlongLeftBranch(x, s);
			if(s.isEmpty()) break;
			x = s.pop();
			sb.append(x.data).append(", ");
			x = x.rc;
		}
	}
	
	/**
	* @Description 子树后序遍历
	* @param @return
	* @return viod    返回类型
	 */
	void travPost() {
		
	}
}
