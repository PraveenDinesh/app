package com.restcall.demo;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private int rno;
	private String name;
	private String desig;
	
	public Employee(int rno, String name, String desig) {
		super();
		this.rno = rno;
		this.name = name;
		this.desig = desig;
	}

	public Employee() {
		super();
	
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	@Override
	public String toString() {
		return "Employee [rno=" + rno + ", name=" + name + ", desig=" + desig + "]";
	}
	
	

}
