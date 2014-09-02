package com.example.targetapp.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.example.targetapp.EquationAnalysis;

public class MyUnitTest extends TestCase {

	@Test
	public void testPlus() {
		//fail("Not yet implemented");
		assertEquals(24,EquationAnalysis.plus(12, 12));
	}

	@Test
	public void testArithmetic() {
		//fail("Not yet implemented");
		String equation0="(2+3)*5-4";
		if(EquationAnalysis.arithmetic(equation0)!=Double.parseDouble("21"))
				{
					fail("EuqationAnalysis.arithmetic failed!");
				}
	}
}
