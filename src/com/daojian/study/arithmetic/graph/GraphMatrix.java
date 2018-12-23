package com.daojian.study.arithmetic.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import com.daojian.study.arithmetic.graph.Edge.EType;
import com.daojian.study.arithmetic.graph.Vertex.VStatus;

/**
 * @Description 基于邻接矩阵实现的图
 * @author daojian
 * @date  2018年11月30日 上午11:01:40
 */
public class GraphMatrix<Tv, Te>{

	private Vector<Vertex<Tv>> V = new Vector<>(); //点集合
	
	private Vector<Vector<Edge<Te>>> E = new Vector<Vector<Edge<Te>>>(); //边集
	
	private int e; //边数
	
	private boolean hasDirect = false; //默认无向图
	
	public GraphMatrix(boolean direct) {
		hasDirect = direct;
	}
	
	public GraphMatrix() {
	}
	
	/**
	 * 第一个邻居
	 * @param i
	 * @return
	 */
	int firstNbr(int i) {
		return nextNbr(i, V.size());
	}
	
	/**
	 * 对于任意顶点i， 若已枚举至邻居j， 则转向下一个邻居
	 * @param i
	 * @param j
	 * @return
	 */
	int nextNbr(int i, int j) {
		while(-1<j && !exists(i, --j));
		return j;
	}
	/**
	 * 判断边(i, j)是否存在, 短路求值
	 * @param i
	 * @param j
	 * @return
	 */
	boolean exists(int i, int j) {
		if(hasDirect) {
			return 0 <= i && i < V.size() && 0 <= j && j < V.size() && E.get(i).get(j) != null;
		}else {
			return 0 <= i && i < V.size() && 0 <= j && j < V.size() && (E.get(i).get(j) != null || E.get(j).get(i) != null);
		}
	}
	
	/**
	 * 所有顶点，边的信息复位
	 */
	private void reset() {
		for(int i=0; i<V.size(); i++) {
			Vertex<Tv> tv = V.get(i);
			tv.status = VStatus.UNDISCOVERED;
			tv.dTime = tv.fTime = 0;
			for(int j=0; j<V.size(); j++) {
				Edge<Te> te = E.get(i).get(j);
				if(exists(i, j)) te.type = EType.UNDETERMINED;
			}
		}
	}
	/**
	 * 插入边
	 * @param edge
	 * @param w
	 * @param i
	 * @param j
	 */
	void insertEdge(Te edge, int weigth, int i, int j) {
		if(exists(i, j)) return;
		Edge<Te> te = new Edge<Te>(edge, weigth);
		E.get(i).set(j, te);
		if(!hasDirect) {
			E.get(j).set(i, te);
		}else {
			V.get(i).outDegree++;
			V.get(j).inDegree++;
		}
		e++;
		
	}
	/**
	 * 删除边
	 * @param i
	 * @param j
	 * @return
	 */
	Te removeEdge(int i, int j) {
		Te eBak = E.get(i).get(j).data;
		E.get(i).set(j, null);
		if(!hasDirect) {
			E.get(j).set(i, null);
		}else {
			V.get(i).outDegree--;
			V.get(j).inDegree--;
		}
		e--;
		return eBak;
		
	}
	/**
	 * 插入点
	 * @param tv
	 */
	void insertVertex(Tv tv) {
		for(int j=0; j<V.size(); j++) {
			E.get(j).add(null);
		}
		V.add(new Vertex<>(tv));
		Vector<Edge<Te>> e = new Vector<>();
		e.setSize(V.size());
		E.add(e);
	}
	
	/**
	 * 删除某顶点，及其关联边，返回顶点信息
	 * @param i
	 * @return
	 */
	Tv removeVertex(int i) {
		//删除所有出边
		for(int j=0; j<V.size(); j++) {
			if(exists(i, j)) 
				V.get(j).inDegree--;
		}
		//删除第i行
		E.remove(i);
		//备份该顶点，然后删除
		Tv eBak = V.get(i).data;
		V.remove(i);
		//删除所有入边 和第i列
		for(int j=0; j<V.size(); j++) {
			if(E.get(j).remove(i) != null) {
				V.get(j).outDegree--;
			}
		}
		return eBak;
	}
	
	/**
	* @Description 广度优先遍历
	* @param v 顶点索引
	* @return
	 */
	int BFS(int v, int clock) {
		Queue<Integer> Q = new LinkedList<Integer>();
		StringBuffer sb = new StringBuffer();
		V.get(v).status = VStatus.DISCOVERED;
		Q.add(v);
		while(!Q.isEmpty()) {
			v = Q.remove();
			V.get(v).dTime = ++clock;
			sb.append(V.get(v).data + ", ");
			for(int u=firstNbr(v); -1<u; u=nextNbr(v, u)) {//考察v的每一个邻居
				if(V.get(u).status == VStatus.UNDISCOVERED) { //若u尚未被发现，则发现该节点， 引入树边
					V.get(u).status = VStatus.DISCOVERED;
					Q.add(u);
					E.get(v).get(u).type = EType.TREE;
					V.get(u).parent = v;
				}else { //若u已被发现（正在队列中）， 或者已访问完毕（已出队列）， 则将（v, u）归位跨边
					E.get(v).get(u).type = EType.CROSS;
				}
			}
			V.get(v).status = VStatus.VISITED;
		}
		System.out.println(sb);
		return clock;
	}
	
	/**
	* @Description 如果存在多个不联通的图
	* @param s
	 */
	void bfd(int s) {
		reset();
		int clock = 0;
		int v = s;
		do {
			if(V.get(v).status == VStatus.UNDISCOVERED) {
				clock = BFS(v, clock);
			}
		}while(s != (v = (++v % V.size())));
		System.out.println("clock = " + clock);
	}
	/**
	* @Description 深度优先算法
	* @param v
	* @param clock
	* @return
	 */
	int DFS(int v, int clock) {
		Vertex<Tv> tv = V.get(v);
		tv.dTime = ++clock;
		tv.status = VStatus.DISCOVERED;
		for(int u=firstNbr(v); -1<u; u=nextNbr(v, u)) {
			Edge<Te> te = E.get(v).get(u);
			Vertex<Tv> tu = V.get(u);
			switch (tu.status) {
			case UNDISCOVERED:
				te.type = EType.TREE;
				tu.parent = v;
				clock = DFS(u, clock);
				break;
			case DISCOVERED:
				te.type = EType.BACKWARD;
				break;
			default:
				te.type = tv.dTime < tu.dTime ? EType.FORWARD : EType.CROSS;
				break;
			}
		}
		tv.status = VStatus.VISITED;
		tv.fTime = ++clock;
		return clock;
	}
	
}
