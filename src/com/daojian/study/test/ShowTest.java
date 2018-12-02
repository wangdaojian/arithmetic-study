package com.daojian.study.test;

import java.io.Serializable;
import java.util.Date;

public class ShowTest implements Show<String, Date> {

	@Override
	public void show(String t, Date u) {
	}
	
	public static <T extends Comparable<T>&Serializable> T get(T t1, T t2) {
		if(t1.compareTo(t2) > 0)
			return t1;
		else 
			return t2;
	}

}
