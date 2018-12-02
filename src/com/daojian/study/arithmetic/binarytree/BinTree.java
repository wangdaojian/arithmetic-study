package com.daojian.study.arithmetic.binarytree;

import java.text.NumberFormat;

/**
 * @Description 二叉树
 * @author wangdaojian
 * @date  2018年12月2日 下午12:07:48
 */
public class BinTree<T> {

	protected int _size; //规模
	protected BinNode<T> _root; //根节点
	
	/**
	* @Description 更新节点x的高度
	* @param x
	* @return int    返回类型
	*/
	int updateHeight(BinNode<T> x) {
		return x.height = 1 + max(height(x.lc), height(x.rc));
	}
	
	int max(int a, int b) {
		return a > b ? a : b;
	}
	
	
	/**
	* @Description 更新x以及祖先的高度
	* @param x
	* @return void    返回类型
	 */
	void updateHeightAbove(BinNode<T> x) {
		while(x != null) {
			updateHeight(x);
			x = x.parent;
		}
	}
	
	/**
	* @Description 作为某节点的右节点插入树中
	* @param x 某节点
	* @param e 插入节点
	* @return 新节点
	 */
	BinNode<T> insertAsRC(BinNode<T> x, T e) {
		_size++;
		x.insertAsRC(e);
		updateHeightAbove(x);
		return x.rc;
	}
	
	/**
	* @Description 作为某节点的左节点插入树中
	* @param x
	* @param e
	* @return
	 */
	BinNode<T> insertAsLC(BinNode<T> x, T e) {
		_size++;
		x.insertAsLC(e);
		updateHeightAbove(x);
		return x.lc;
	}
	
	/**
	* @Description 插入右子树
	* @param x 节点
	* @param s 右子树
	* @return
	 */
	BinNode<T> attachAsRC(BinNode<T> x, BinTree<T> s) {
		if(s.root() != null) {
			x.rc = s.root();
			x.rc.parent = x;
		}
		
		_size += s.size();
		updateHeightAbove(x);
		s._root = null;
		s._size = 0;
		s = null;
		return x;
	}
	
	/**
	* @Description 插入左子树
	* @param x
	* @param s
	* @return
	 */
	BinNode<T> attachAsLC(BinNode<T> x, BinTree<T> s) {
		if(s.root() != null) {
			x.lc = s.root();
			x.lc.parent = x;
		}
		
		_size += s.size();
		updateHeightAbove(x);
		s._root = null;
		s._size = 0;
		s = null;
		return x;
	}
	
	/**
	* @Description 删除指定节点下的子树
	* @param x
	* @return 返回被删除节点个数
	 */
	public int removeAt(BinNode<T> x) {
		if(x == null) return 0;
		int n = 1 + removeAt(x.lc) + removeAt(x.rc);
		x.data = null;
		x = null;
		return n;
	}
	
	public void fromParentTo(BinNode<T> x) {
		if(x == x.parent.lc) 
			x.parent.lc = null;
		else 
			x.parent.rc = null;
	}
	
	
	/**
	* @Description 子树删除
	* @param x
	* @return
	 */
	int remove(BinNode<T> x) {
		fromParentTo(x); //切断来自父节点的指针
		updateHeightAbove(x.parent);
		int n = removeAt(x);
		_size -= n;
		return n;
	}
	
	/**
	* @Description 与remove类似，不同之处在于，需要对分离出来的子树重新封装， 并返回给上层调用者
	* @param x
	* @return
	 */
	BinTree<T> secede(BinNode<T> x) {
		fromParentTo(x);
		updateHeightAbove(x.parent);
		//对分离出的子树做封装
		BinTree<T> s = new BinTree<>();
		s._root = x;
		x.parent = null;
		s._size = x.size();
		_size -= s._size;
		return s;
	}
	
	/**
	* @Description 节点高度 约定空树的高度为 -1
	* @param @param node
	* @param @return
	* @return int    返回类型
	 */
	int height(BinNode<T> node) {
		return node != null ? node.height : -1;
	}
	
	public int size() {
		return _size;
	}
	
	boolean empty() {
		return _root == null;
	}
	
	BinNode<T> root() {
		return _root;
	}
	
	
}
