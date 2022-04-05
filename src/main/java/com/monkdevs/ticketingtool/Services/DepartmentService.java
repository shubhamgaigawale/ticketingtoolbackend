package com.monkdevs.ticketingtool.Services;

import com.monkdevs.ticketingtool.Models.Department;

public interface DepartmentService {

	Department findDepartmentbydeptId (long deptId);
	
	Department findDepartmentbydeptName(String deptName);

	Department saveORUpdate(Department department);

	void deleteDepartmenttById(Long deptId);

	Department createDepartment(Department department);
	
	

}
