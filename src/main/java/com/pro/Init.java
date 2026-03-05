package com.pro;

import com.pro.annotation.LogAnnotation;

public class Init {

	public Init() {}

	@LogAnnotation("Teste AspectJ")
	public void add() {
		System.out.println("----- Java Annotation Custom | Main -----");
	}
}