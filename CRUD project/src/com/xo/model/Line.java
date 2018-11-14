package com.xo.model;

public class Line {

	private int[] line;

	
	public Line(){
		this.line = new int[10];
	}
	
	public int[] getLine() {
		return this.line;
	}
	public void setLine(int pos, int value) {
		this.line[pos] = value;
	}
	
	@Override
	public String toString() {
		return line.toString();
	}
		
}
