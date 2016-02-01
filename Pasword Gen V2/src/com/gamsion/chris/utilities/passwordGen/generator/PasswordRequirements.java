package com.gamsion.chris.utilities.passwordGen.generator;

public class PasswordRequirements {
	private final int lower;
	private final int upper;
	private final int numb;
	
	
	public PasswordRequirements(int upper, int lower, int numb){
		this.lower = lower;
		this.upper = upper;
		this.numb = numb;
		
	}
	
	public int getLowerRequire(){
		return lower;
	}
	public int getCapitalRequire(){
		return upper;
	}
	public int getNumberRequire(){
		return numb;
	}
	
}
