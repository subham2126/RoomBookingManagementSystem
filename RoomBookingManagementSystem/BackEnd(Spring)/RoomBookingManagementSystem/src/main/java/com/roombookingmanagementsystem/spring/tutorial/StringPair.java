package com.roombookingmanagementsystem.spring.tutorial;

public class StringPair {
	
	private String first ,second;
	private int third, fourth;
	public StringPair( String first,String second,int third ,int fourth)
	{
		this.setFirst(first);
		this.setSecond(second);
		this.setThird(third);
		this.setFourth(fourth);
		
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public int getFourth() {
		return fourth;
	}
	public void setFourth(int fourth) {
		this.fourth = fourth;
	}
	public int getThird() {
		return third;
	}
	public void setThird(int third) {
		this.third = third;
	}


}
