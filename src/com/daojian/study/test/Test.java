package com.daojian.study.test;

import java.util.Arrays;
import java.util.Vector;

public class Test {
	
	enum EStatus{UNDETERMINED, TREE, CROSS, FORWARD, BACKWARD}

	public static void main(String[] args) {
		 /*Integer a = new Integer(1);
		 Integer b = new Integer(1);
		 int c = 1;
		 System.out.println(a == c);*/
		 /*Integer[] ints = {1, 2, 3, 4, 5, 6};
		 System.out.println(Arrays.toString(ints));
		 int index = 3;
		 int numMoved = 2;
		 System.arraycopy(ints, index+1, ints, index,
                 numMoved);
		 System.out.println(Arrays.toString(ints));*/
		Vector<Integer> vts = new Vector<>(3);
		Integer[] elementData = new Integer[3];
		System.out.println(vts);
	}
}
