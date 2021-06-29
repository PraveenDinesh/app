package com.restcall.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.cdi.ContextName;

@ContextName("restfulldemo")
public class TempDataBase {
	
	private static List<Employee> empList = new ArrayList<Employee>();
	
	static {
		empList.add(new Employee(100, "pavan", "manager"));
		empList.add(new Employee(101, "kiran", "salesman"));
		empList.add(new Employee(102, "syam", "account"));
		empList.add(new Employee(103, "adi", "clerk"));
	}

	public static List<Employee> getEmpList() {
		return empList;
	}

	public static void setEmpList(List<Employee> empList) {
		TempDataBase.empList = empList;
	}

}
