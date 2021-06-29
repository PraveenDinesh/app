package com.restcall.demo;

import java.util.List;

import javax.inject.Named;

import org.apache.camel.Header;
import org.apache.camel.cdi.ContextName;

@ContextName("restfulldemo")
@Named("employeDAO")
public class EmployeeDAO {
	
	public List<Employee> getAllEmps(){
		return TempDataBase.getEmpList();
	}

	public Employee getEmpById(@Header("empNo") int rno) throws BadRequestException {
		if(rno>1000) {
			throw new BadRequestException("the given id is not in the range, give less than that");
		}
		System.out.println(" fetching emp info of rno :"+rno);
		return TempDataBase.getEmpList().get(0);
	}
	
	public String saveEmployee(Employee e) {
		TempDataBase.getEmpList().add(e);
		return "Employee Successfully saved";
	}
	
	public String updateEmployee(Employee e) {
		TempDataBase.getEmpList().set(0, e);
		return "Employee Successfully updated";
	}
	
	public String deleteEmployee(Employee e) {
		TempDataBase.getEmpList().remove(0);
		return "Employee Successfully deleted";
	}
}
