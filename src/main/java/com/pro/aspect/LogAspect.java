package com.pro.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.pro.annotation.LogAnnotation;

@Aspect
public class LogAspect {

	@Before("@annotation(logAnnotation)")
	public void antesDeExecutar(LogAnnotation logAnnotation) {
		System.out.println("Aspect acionado: " + logAnnotation.value());
	}
}