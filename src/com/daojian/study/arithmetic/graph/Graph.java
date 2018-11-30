package com.daojian.study.arithmetic.graph;

import com.daojian.study.arithmetic.graph.Edge.EStatus;
import com.daojian.study.arithmetic.graph.Vertex.VStatus;

/**
 * @Description 图
 * @author daojian
 * @date  2018年11月30日 上午10:28:03
 */
public class Graph<Tv, Te> { //顶点类型， 边类型
	
	Vertex<Tv>[] tvs;
	Edge<Te>[] tes;
	int[][] status;
	
	public Graph(Vertex<Tv>[] tvs, Edge<Te>[] tes) {
		this.tvs = tvs;
		this.tes = tes;
		status = new int[tvs.length][tes.length];
	}
	
	protected void reset() {
		for(int i=0; i<tvs.length; i++) {
			tvs[i].status = VStatus.UNDISCOVERED;
			tvs[i].dTime = tvs[i].fTime = tvs[i].parent = -1;
			tvs[i].priority = Integer.MAX_VALUE;
			for(int j=0; j<tes.length; j++) {
				if(status[i][j] != 0) status[i][j] = EStatus.UNDETERMINED.ordinal();
			}
		}
	}
	
	
}
