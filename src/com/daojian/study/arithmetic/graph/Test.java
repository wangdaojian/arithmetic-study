package com.daojian.study.arithmetic.graph;

public class Test {
	public static void main(String[] args) {
		GraphMatrix<Character, Integer> gm = new GraphMatrix<>();
		gm.insertVertex('a');
		gm.insertVertex('s');
		gm.insertVertex('e');
		gm.insertVertex('d');
		gm.insertVertex('c');
		gm.insertVertex('b');
		gm.insertVertex('f');
		gm.insertVertex('g');
		
		
		gm.insertEdge(3, 0, 0, 1);
		//gm.insertEdge(2, 0, 0, 2);
		gm.insertEdge(4, 4, 0, 4);
		gm.insertEdge(3, 4, 1, 3);
		gm.insertEdge(4, 4, 1, 4);
		gm.insertEdge(4, 4, 3, 5);
		gm.insertEdge(4, 4, 4, 5);
		gm.insertEdge(4, 4, 2, 6);
		gm.insertEdge(4, 4, 2, 7);
		gm.insertEdge(4, 4, 6, 7);
		
		//gm.BFS(1, 0);
		gm.bfd(1);
		
		
		GraphMatrix<Character, Integer> gm2 = new GraphMatrix<>();
		gm2.insertVertex('a');
		gm2.insertVertex('b');
		gm2.insertVertex('c');
		gm2.insertVertex('d');
		gm2.insertVertex('e');
		gm2.insertVertex('f');
		gm2.insertVertex('g');
		gm2.insertVertex('h');
		gm2.insertVertex('i');
		gm2.insertVertex('j');
		
		
		gm2.insertEdge(3, 0, 0, 1);
		gm2.insertEdge(2, 0, 0, 3);
		gm2.insertEdge(4, 4, 0, 4);
		gm2.insertEdge(3, 4, 1, 2);
		gm2.insertEdge(4, 4, 1, 4);
		gm2.insertEdge(4, 4, 2, 5);
		gm2.insertEdge(4, 4, 5, 6);
		gm2.insertEdge(4, 4, 5, 7);
		gm2.insertEdge(4, 4, 6, 7);
		gm2.insertEdge(4, 4, 6, 7);
	}
}
