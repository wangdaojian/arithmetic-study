package com.daojian.study.test;

public class Test {
	
	enum EStatus{UNDETERMINED, TREE, CROSS, FORWARD, BACKWARD}

	public static void main(String[] args) {
		System.out.println(EStatus.UNDETERMINED.ordinal());
		int[][] arr = new int[3][5];
		for(int i=0; i<3; i++) {
			for(int j=0; j<5; j++) {
				System.out.print(arr[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
