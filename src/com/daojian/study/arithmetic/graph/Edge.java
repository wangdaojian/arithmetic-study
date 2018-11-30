package com.daojian.study.arithmetic.graph;

/**
 * @Description 边
 * @author daojian
 * @date  2018年11月30日 上午9:59:57
 */
public class Edge<Te> {
	Te data; //数据
	int weight; //权重
	enum EStatus{UNDETERMINED, TREE, CROSS, FORWARD, BACKWARD}
	EStatus status; //类型
	public Edge(Te d, int w) {
		this.data = d;
		this.weight = w;
		this.status = EStatus.UNDETERMINED;
	}
	
	
}
