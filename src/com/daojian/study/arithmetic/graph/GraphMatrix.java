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

	private Vector<Vertex<Tv>> V = new Vector<>(); //点集合
	
	private Vector<Vector<Edge<Te>>> E = new Vector<Vector<Edge<Te>>>(); //边集
	
	private int e; //边数
	
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
		e++;
		V.get(i).outDegree++;
		V.get(j).inDegree++;
	}
	/**
	 * 删除边
	 * @param i
	 * @param j
	 * @return
	 */
	Te removeEdge(int i, int j) {
		Te eBak = E.get(i).get(j).data;
		e--;
		V.get(i).outDegree--;
		V.get(j).inDegree--;
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
		for(int j=0; j<V.size(); j++) {
			e.add(null);
		}
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
		
		E.remove(i);
		//备份该顶点，然后删除
		Tv eBak = V.get(i).data;
		V.remove(i);
		
		for(int j=0; j<V.size(); j++) {
			if(E.get(j).remove(i) != null) {
				V.get(j).outDegree--;
			}
		}
		return eBak;
	}
	
}
