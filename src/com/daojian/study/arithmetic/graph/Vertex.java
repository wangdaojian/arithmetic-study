package com.daojian.study.arithmetic.graph;
/**
 * @Description 点
 * @author daojian
 * @date  2018年11月30日 上午9:59:41
 */
public class Vertex<Tv> {
	Tv data; //数据
	int inDegree, outDegree; //出度 入度
	public enum VStatus {UNDISCOVERED, DISCOVERED, VISITED}
	VStatus status; //状态
	
	int dTime, fTime; //时间标签
	int parent; //在遍历树中的父节点
	int priority; //在遍历树中的优先级（最短通路， 极短跨边等）
	
	public Vertex(Tv d) {
		this.data = d;
		inDegree = outDegree = 0;
		status = VStatus.UNDISCOVERED;
		dTime = fTime = parent = -1;
		priority = Integer.MAX_VALUE;
	}
	
	
}
