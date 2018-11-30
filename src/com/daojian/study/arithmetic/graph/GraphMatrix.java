package com.daojian.study.arithmetic.graph;

import java.util.Vector;

import javax.net.ssl.SSLEngineResult.Status;

import com.daojian.study.arithmetic.graph.Edge.EType;
import com.daojian.study.arithmetic.graph.Vertex.VStatus;

/**
 * @Description 基于邻接矩阵实现的图
 * @author daojian
 * @date  2018年11月30日 上午11:01:40
 */
public class GraphMatrix<Tv, Te>{

	private Vector<Vertex<Tv>> V; //点集合
	private Vector<Vector<Edge<Te>>> E; //边集
	
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
		return 0 <= i && i < V.size() && 0 <= j && j < V.size() && E.get(i).get(j) != null;
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
		
	}
}